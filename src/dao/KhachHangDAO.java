package dao;

import database.DatabaseConnection;
import model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    // 1. Lấy danh sách toàn bộ khách hàng
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("ID"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm khách hàng mới
    public boolean add(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (HoTen, SoDienThoai, DiemTichLuy) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setInt(3, kh.getDiemTichLuy()); // Thường lúc mới tạo sẽ là 0

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Cập nhật thông tin khách hàng
    public boolean update(KhachHang kh) {
        String sql = "UPDATE KhachHang SET HoTen = ?, SoDienThoai = ? WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setInt(3, kh.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Tìm kiếm khách hàng bằng Số điện thoại (Dùng cho form Bán Hàng của Thái)
    public KhachHang findByPhone(String phone) {
        String sql = "SELECT * FROM KhachHang WHERE SoDienThoai = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setId(rs.getInt("ID"));
                    kh.setHoTen(rs.getString("HoTen"));
                    kh.setSoDienThoai(rs.getString("SoDienThoai"));
                    kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                    return kh;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy
    }

    // 5. Cộng/Trừ điểm tích lũy sau khi thanh toán hóa đơn
    public boolean updateDiem(int id, int diemThayDoi) {
        String sql = "UPDATE KhachHang SET DiemTichLuy = DiemTichLuy + ? WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, diemThayDoi);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 6. Lấy khách hàng theo ID
    public KhachHang getById(int id) {
        String sql = "SELECT * FROM KhachHang WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setId(rs.getInt("ID"));
                    kh.setHoTen(rs.getString("HoTen"));
                    kh.setSoDienThoai(rs.getString("SoDienThoai"));
                    kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                    return kh;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 7. Xóa khách hàng (Lưu ý: Sẽ bị lỗi khóa ngoại nếu khách này đã có Hóa đơn, trừ khi Database setup ON DELETE CASCADE)
    public boolean delete(int id) {
        String sql = "DELETE FROM KhachHang WHERE ID = ?";
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