package model;

public class Giay {
    private int id;
    private int idLoaiGiay; 
    private String tenGiay;
    private String thuongHieu;
    private int size;
    private String mauSac;
    private double giaBan;
    private int soLuongTon;
    private String trangThai;

    public Giay() {}

    public Giay(int id, int idLoaiGiay, String tenGiay, String thuongHieu, int size, String mauSac, double giaBan, int soLuongTon, String trangThai) {
        this.id = id;
        this.idLoaiGiay = idLoaiGiay;
        this.tenGiay = tenGiay;
        this.thuongHieu = thuongHieu;
        this.size = size;
        this.mauSac = mauSac;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.trangThai = trangThai;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdLoaiGiay() { return idLoaiGiay; }
    public void setIdLoaiGiay(int idLoaiGiay) { this.idLoaiGiay = idLoaiGiay; }

    public String getTenGiay() { return tenGiay; }
    public void setTenGiay(String tenGiay) { this.tenGiay = tenGiay; }
    public String getThuongHieu() { return thuongHieu; }
    public void setThuongHieu(String thuongHieu) { this.thuongHieu = thuongHieu; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }
    public double getGiaBan() { return giaBan; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }
    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}