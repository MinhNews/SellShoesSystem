package model;

public class ChiTietHoaDon {
    private int idHoaDon;
    private int idGiay;
    private int soLuongMua;
    private double donGia;

    // Constructor rỗng
    public ChiTietHoaDon() {
    }

    // Constructor đầy đủ
    public ChiTietHoaDon(int idHoaDon, int idGiay, int soLuongMua, double donGia) {
        this.idHoaDon = idHoaDon;
        this.idGiay = idGiay;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;
    }

    // Getter và Setter
    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdGiay() {
        return idGiay;
    }

    public void setIdGiay(int idGiay) {
        this.idGiay = idGiay;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}