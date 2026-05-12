package model;

import java.util.Date;

public class HoaDon {
    private int id;
    private int idNhanVien;
    private int idKhachHang;
    private Date ngayLap;
    private double tongTien;

    // Constructor rỗng
    public HoaDon() {
    }

    // Constructor đầy đủ
    public HoaDon(int id, int idNhanVien, int idKhachHang, Date ngayLap, double tongTien) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}