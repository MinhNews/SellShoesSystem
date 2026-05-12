package model;

public class NhanVien {
    private int id;
    private String username;
    private String password;
    private String hoTen;
    private String dienThoai;
    private int quyen; // 0: Admin, 1: Nhan Vien

    // Constructor rỗng
    public NhanVien() {}

    // Constructor đầy đủ
    public NhanVien(int id, String username, String password, String hoTen, String dienThoai, int quyen) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.quyen = quyen;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }
}