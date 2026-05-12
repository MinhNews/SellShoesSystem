package dao;

import database.DatabaseConnection;
import model.ChiTietHoaDon;
import model.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    // Lấy danh sách toàn bộ hóa đơn (Dùng cho tab Quản lý Hóa Đơn sau này)
    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY NgayLap DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("ID"));
                hd.setIdNhanVien(rs.getInt("ID_NhanVien"));
                hd.setIdKhachHang(rs.getInt("ID_KhachHang"));
                hd.setNgayLap(rs.getDate("NgayLap")); // Chú ý: Có thể dùng getTimestamp nếu cần cả giờ phút
                hd.setTongTien(rs.getDouble("TongTien"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // XỬ LÝ TRANSACTION: Thanh toán hóa đơn
    public boolean thanhToanHoaDon(HoaDon hd, List<ChiTietHoaDon> listCTHD) {
        Connection con = null;
        ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();
        GiayDAO giayDAO = new GiayDAO();

        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false); // Bắt đầu Transaction

            // 1. Tự lo việc INSERT HoaDon
            String sqlHoaDon = "INSERT INTO HoaDon (ID_NhanVien, ID_KhachHang, TongTien) VALUES (?, ?, ?)";
            PreparedStatement psHoaDon = con.prepareStatement(sqlHoaDon, Statement.RETURN_GENERATED_KEYS);
            psHoaDon.setInt(1, hd.getIdNhanVien());
            if (hd.getIdKhachHang() == 0) {
                psHoaDon.setNull(2, java.sql.Types.INTEGER);
            } else {
                psHoaDon.setInt(2, hd.getIdKhachHang());
            }
            psHoaDon.setDouble(3, hd.getTongTien());
            psHoaDon.executeUpdate();

            // Lấy ID hóa đơn vừa được tạo
            ResultSet rsID = psHoaDon.getGeneratedKeys();
            int idHoaDonMoi = -1;
            if (rsID.next()) {
                idHoaDonMoi = rsID.getInt(1);
            }

            // 2. Giao việc cho các DAO khác xử lý, TRUYỀN CHUNG CÁI CONNECTION VÀO
            for (ChiTietHoaDon cthd : listCTHD) {
                cthd.setIdHoaDon(idHoaDonMoi); // Gán ID hóa đơn mới vào chi tiết
                
                cthdDAO.insert(con, cthd); // Lệnh gọi ChiTietHoaDonDAO
                giayDAO.truTonKho(con, cthd.getIdGiay(), cthd.getSoLuongMua()); // Lệnh gọi GiayDAO
            }

            con.commit(); // Thành công tất cả thì chốt đơn!
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Có lỗi ở bất kỳ khâu nào do các DAO ném ra -> Hoàn tác
                    System.out.println("Đã Rollback Transaction!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    // Ở đây có thể đóng con.close() tùy vào kiến trúc DatabaseConnection của ông
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    // Lấy Hóa đơn theo ID
    public HoaDon getById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setId(rs.getInt("ID"));
                    hd.setIdNhanVien(rs.getInt("ID_NhanVien"));
                    hd.setIdKhachHang(rs.getInt("ID_KhachHang"));
                    hd.setNgayLap(rs.getDate("NgayLap"));
                    hd.setTongTien(rs.getDouble("TongTien"));
                    return hd;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Xóa Hóa đơn
    public boolean delete(int id) {
        String sql = "DELETE FROM HoaDon WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}