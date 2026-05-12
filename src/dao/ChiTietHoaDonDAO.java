package dao;

import database.DatabaseConnection;
import model.ChiTietHoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {

    // Lấy chi tiết của một hóa đơn cụ thể dựa vào ID Hóa Đơn
    public List<ChiTietHoaDon> getByIdHoaDon(int idHoaDon) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon WHERE ID_HoaDon = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idHoaDon);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietHoaDon cthd = new ChiTietHoaDon();
                    cthd.setIdHoaDon(rs.getInt("ID_HoaDon"));
                    cthd.setIdGiay(rs.getInt("ID_Giay"));
                    cthd.setSoLuongMua(rs.getInt("SoLuongMua"));
                    cthd.setDonGia(rs.getDouble("DonGia"));
                    list.add(cthd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ném lỗi SQLException ra ngoài để HoaDonDAO bắt và Rollback
    public void insert(Connection con, ChiTietHoaDon cthd) throws SQLException {
        String sql = "INSERT INTO ChiTietHoaDon (ID_HoaDon, ID_Giay, SoLuongMua, DonGia) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cthd.getIdHoaDon());
            ps.setInt(2, cthd.getIdGiay());
            ps.setInt(3, cthd.getSoLuongMua());
            ps.setDouble(4, cthd.getDonGia());
            ps.executeUpdate();
        }
    }
}