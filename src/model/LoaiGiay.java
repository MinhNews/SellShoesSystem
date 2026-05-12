package model;

public class LoaiGiay {
    private int id;
    private String tenLoai;

    public LoaiGiay() {}

    public LoaiGiay(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenLoai() { return tenLoai; }
    public void setTenLoai(String tenLoai) { this.tenLoai = tenLoai; }

    // Override toString để JComboBox hiển thị đúng Tên Loại
    @Override
    public String toString() {
        return tenLoai;
    }
}