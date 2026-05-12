package dao;

import database.DatabaseConnection;
import model.LoaiGiay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiGiayDAO {

    public List<LoaiGiay> getAll() {
        List<LoaiGiay> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiGiay";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new LoaiGiay(rs.getInt("ID"), rs.getString("TenLoai")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public LoaiGiay getById(int id) {
        String sql = "SELECT * FROM LoaiGiay WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LoaiGiay(rs.getInt("ID"), rs.getString("TenLoai"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean add(LoaiGiay lg) {
        String sql = "INSERT INTO LoaiGiay (TenLoai) VALUES (?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lg.getTenLoai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(LoaiGiay lg) {
        String sql = "UPDATE LoaiGiay SET TenLoai = ? WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lg.getTenLoai());
            ps.setInt(2, lg.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM LoaiGiay WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}