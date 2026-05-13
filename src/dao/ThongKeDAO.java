package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeDAO {

    // 1. Lấy tổng doanh thu theo khoảng thời gian
    public double getTongDoanhThu(Date tuNgay, Date denNgay) {
        String sql = "SELECT SUM(TongTien) AS DoanhThu FROM HoaDon WHERE DATE(NgayLap) BETWEEN ? AND ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(tuNgay.getTime()));
            ps.setDate(2, new java.sql.Date(denNgay.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("DoanhThu");
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // 2. Lấy tổng số hóa đơn đã bán ra
    public int getTongSoDonHang(Date tuNgay, Date denNgay) {
        String sql = "SELECT COUNT(ID) AS SoDon FROM HoaDon WHERE DATE(NgayLap) BETWEEN ? AND ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(tuNgay.getTime()));
            ps.setDate(2, new java.sql.Date(denNgay.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoDon");
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // 3. Truy vấn Top 5 đôi giày bán chạy nhất (Dùng để vẽ biểu đồ tròn)
    // Trả về một List chứa mảng Object, Object[0] là Tên Giày, Object[1] là Số lượng bán
    public List<Object[]> getTop5GiayBanChay(Date tuNgay, Date denNgay) {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT g.TenGiay, SUM(c.SoLuongMua) AS TongSoLuong " +
                     "FROM ChiTietHoaDon c " +
                     "JOIN HoaDon h ON c.ID_HoaDon = h.ID " +
                     "JOIN Giay g ON c.ID_Giay = g.ID " +
                     "WHERE DATE(h.NgayLap) BETWEEN ? AND ? " +
                     "GROUP BY g.ID, g.TenGiay " +
                     "ORDER BY TongSoLuong DESC LIMIT 5";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(tuNgay.getTime()));
            ps.setDate(2, new java.sql.Date(denNgay.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Object[]{ rs.getString("TenGiay"), rs.getInt("TongSoLuong") });
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}