package dao;

import database.DatabaseConnection;
import model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    
    // 1. Kiểm tra đăng nhập 
    public NhanVien checkLogin(String username, String password) {
        String sql = "SELECT * FROM NhanVien WHERE Username = ? AND Password = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Truyền tham số vào câu SQL để tránh SQL Injection
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            // Nếu có kết quả trả về -> Đăng nhập đúng
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt("ID"));
                nv.setUsername(rs.getString("Username"));
                nv.setPassword(rs.getString("Password"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setQuyen(rs.getInt("Quyen"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }

    // 2. Lấy danh sách toàn bộ nhân viên (Dành cho form Quản lý của Admin)
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt("ID"));
                nv.setUsername(rs.getString("Username"));
                nv.setPassword(rs.getString("Password"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setQuyen(rs.getInt("Quyen"));
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Lấy thông tin 1 nhân viên theo ID
    public NhanVien getById(int id) {
        String sql = "SELECT * FROM NhanVien WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setId(rs.getInt("ID"));
                    nv.setUsername(rs.getString("Username"));
                    nv.setPassword(rs.getString("Password"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setDienThoai(rs.getString("DienThoai"));
                    nv.setQuyen(rs.getInt("Quyen"));
                    return nv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Thêm nhân viên mới (Admin cấp tài khoản)
    public boolean add(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (Username, Password, HoTen, DienThoai, Quyen) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nv.getUsername());
            ps.setString(2, nv.getPassword());
            ps.setString(3, nv.getHoTen());
            ps.setString(4, nv.getDienThoai());
            ps.setInt(5, nv.getQuyen());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Cập nhật thông tin nhân viên
    public boolean update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET Username = ?, Password = ?, HoTen = ?, DienThoai = ?, Quyen = ? WHERE ID = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nv.getUsername());
            ps.setString(2, nv.getPassword());
            ps.setString(3, nv.getHoTen());
            ps.setString(4, nv.getDienThoai());
            ps.setInt(5, nv.getQuyen());
            ps.setInt(6, nv.getId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6. Xóa nhân viên 
    public boolean delete(int id) {
        String sql = "DELETE FROM NhanVien WHERE ID = ?";
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