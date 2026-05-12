package dao;

import database.DatabaseConnection;
import model.Giay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiayDAO {

    public List<Giay> getAll() {
        List<Giay> list = new ArrayList<>();
        String sql = "SELECT * FROM Giay";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Giay g = new Giay();
                g.setId(rs.getInt("ID"));
                g.setIdLoaiGiay(rs.getInt("ID_LoaiGiay")); // THÊM DÒNG NÀY
                g.setTenGiay(rs.getString("TenGiay"));
                g.setThuongHieu(rs.getString("ThuongHieu"));
                g.setSize(rs.getInt("Size"));
                g.setMauSac(rs.getString("MauSac"));
                g.setGiaBan(rs.getDouble("GiaBan"));
                g.setSoLuongTon(rs.getInt("SoLuongTon"));
                g.setTrangThai(rs.getString("TrangThai"));
                list.add(g);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean add(Giay g) {
        String sql = "INSERT INTO Giay (ID_LoaiGiay, TenGiay, ThuongHieu, Size, MauSac, GiaBan, SoLuongTon, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, g.getIdLoaiGiay());
            ps.setString(2, g.getTenGiay());
            ps.setString(3, g.getThuongHieu());
            ps.setInt(4, g.getSize());
            ps.setString(5, g.getMauSac());
            ps.setDouble(6, g.getGiaBan());
            ps.setInt(7, g.getSoLuongTon());
            ps.setString(8, g.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(Giay g) {
        String sql = "UPDATE Giay SET ID_LoaiGiay = ?, TenGiay = ?, ThuongHieu = ?, Size = ?, MauSac = ?, GiaBan = ?, SoLuongTon = ?, TrangThai = ? WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, g.getIdLoaiGiay());
            ps.setString(2, g.getTenGiay());
            ps.setString(3, g.getThuongHieu());
            ps.setInt(4, g.getSize());
            ps.setString(5, g.getMauSac());
            ps.setDouble(6, g.getGiaBan());
            ps.setInt(7, g.getSoLuongTon());
            ps.setString(8, g.getTrangThai());
            ps.setInt(9, g.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Giay WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<Giay> search(String keyword) {
        List<Giay> list = new ArrayList<>();
        String sql = "SELECT * FROM Giay WHERE TenGiay LIKE ? OR ThuongHieu LIKE ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Giay g = new Giay();
                    g.setId(rs.getInt("ID"));
                    g.setIdLoaiGiay(rs.getInt("ID_LoaiGiay"));
                    g.setTenGiay(rs.getString("TenGiay"));
                    g.setThuongHieu(rs.getString("ThuongHieu"));
                    g.setSize(rs.getInt("Size"));
                    g.setMauSac(rs.getString("MauSac"));
                    g.setGiaBan(rs.getDouble("GiaBan"));
                    g.setSoLuongTon(rs.getInt("SoLuongTon"));
                    g.setTrangThai(rs.getString("TrangThai"));
                    list.add(g);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Giay getById(int id) {
        String sql = "SELECT * FROM Giay WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Giay g = new Giay();
                    g.setId(rs.getInt("ID"));
                    g.setIdLoaiGiay(rs.getInt("ID_LoaiGiay"));
                    g.setTenGiay(rs.getString("TenGiay"));
                    g.setThuongHieu(rs.getString("ThuongHieu"));
                    g.setSize(rs.getInt("Size"));
                    g.setMauSac(rs.getString("MauSac"));
                    g.setGiaBan(rs.getDouble("GiaBan"));
                    g.setSoLuongTon(rs.getInt("SoLuongTon"));
                    g.setTrangThai(rs.getString("TrangThai"));
                    return g;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void truTonKho(Connection con, int idGiay, int soLuongBan) throws SQLException {
        String sql = "UPDATE Giay SET SoLuongTon = SoLuongTon - ? WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuongBan);
            ps.setInt(2, idGiay);
            ps.executeUpdate();
        }
    }
}