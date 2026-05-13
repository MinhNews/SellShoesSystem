package view;

import model.LoaiGiay;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLyGiayPanel extends JPanel {
    // Các UI Components
    private JTextField txtTimKiem, txtId, txtTenGiay, txtThuongHieu, txtSize, txtMauSac, txtGiaBan, txtSoLuongTon;
    private JComboBox<String> cbTrangThai;
    private JComboBox<LoaiGiay> cbLoaiGiay; // MỚI: ComboBox cho Loại Giày
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblGiay;
    private DefaultTableModel modelGiay;

    public QuanLyGiayPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- KHU VỰC TOP: TÌM KIẾM & FORM NHẬP LIỆU ---
        JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
        
        // 1. Thanh tìm kiếm
        JPanel pnlTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTimKiem = new JTextField(30);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Nhập tên giày hoặc thương hiệu...");
        btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.putClientProperty("JButton.buttonType", "roundRect");
        btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.putClientProperty("JButton.buttonType", "roundRect");
        pnlTimKiem.add(txtTimKiem); pnlTimKiem.add(btnTimKiem); pnlTimKiem.add(btnLamMoi);

        // 2. Form nhập liệu (Chuyển thành GridLayout 5 dòng 4 cột để đủ chỗ)
        JPanel pnlForm = new JPanel(new GridLayout(5, 4, 10, 10));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Thông tin Giày"));
        
        pnlForm.add(new JLabel("ID (Tự động):")); 
        txtId = new JTextField(); txtId.setEditable(false); pnlForm.add(txtId);
        
        pnlForm.add(new JLabel("Loại Giày:")); 
        cbLoaiGiay = new JComboBox<>(); pnlForm.add(cbLoaiGiay); // Thêm cbLoaiGiay vào form
        
        pnlForm.add(new JLabel("Tên Giày:")); 
        txtTenGiay = new JTextField(); pnlForm.add(txtTenGiay);
        
        pnlForm.add(new JLabel("Thương Hiệu:")); 
        txtThuongHieu = new JTextField(); pnlForm.add(txtThuongHieu);
        
        pnlForm.add(new JLabel("Size:")); 
        txtSize = new JTextField(); pnlForm.add(txtSize);
        
        pnlForm.add(new JLabel("Màu Sắc:")); 
        txtMauSac = new JTextField(); pnlForm.add(txtMauSac);
        
        pnlForm.add(new JLabel("Giá Bán:")); 
        txtGiaBan = new JTextField(); pnlForm.add(txtGiaBan);
        
        pnlForm.add(new JLabel("Số Lượng Tồn:")); 
        txtSoLuongTon = new JTextField(); pnlForm.add(txtSoLuongTon);
        
        pnlForm.add(new JLabel("Trạng Thái:")); 
        cbTrangThai = new JComboBox<>(new String[]{"Available", "Out of stock"}); pnlForm.add(cbTrangThai);
        
        // Cột trống cho đẹp layout
        pnlForm.add(new JLabel("")); pnlForm.add(new JLabel(""));
        
        pnlNorth.add(pnlTimKiem, BorderLayout.NORTH);
        pnlNorth.add(pnlForm, BorderLayout.CENTER);

        // --- KHU VỰC CENTER: BẢNG DỮ LIỆU ---
        // MỚI: Thêm cột "Loại Giày" vào bảng
        String[] columns = {"ID", "Loại Giày", "Tên Giày", "Thương Hiệu", "Size", "Màu Sắc", "Giá Bán", "Số Lượng", "Trạng Thái"};
        modelGiay = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } 
        };
        tblGiay = new JTable(modelGiay);
        tblGiay.setRowHeight(25); 
        
        // --- KHU VỰC BOTTOM: NÚT CHỨC NĂNG ---
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnThem = new JButton("Thêm Mới");
        btnSua = new JButton("Cập Nhật");
        btnXoa = new JButton("Xóa");
        
        JButton[] btns = {btnThem, btnSua, btnXoa};
        for (JButton btn : btns) { btn.putClientProperty("JButton.buttonType", "roundRect"); }
        btnThem.setBackground(new Color(40, 167, 69)); btnThem.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(23, 162, 184)); btnSua.setForeground(Color.WHITE);
        btnXoa.setBackground(new Color(220, 53, 69)); btnXoa.setForeground(Color.WHITE);

        pnlSouth.add(btnThem); pnlSouth.add(btnSua); pnlSouth.add(btnXoa);

        add(pnlNorth, BorderLayout.NORTH);
        add(new JScrollPane(tblGiay), BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- HÀM ĐỔ DỮ LIỆU CHO COMBOBOX ---
    public void setLoaiGiayData(List<LoaiGiay> list) {
        cbLoaiGiay.removeAllItems();
        for (LoaiGiay lg : list) {
            cbLoaiGiay.addItem(lg);
        }
    }

    // --- GETTER ---
    public JTable getTblGiay() { return tblGiay; }
    public DefaultTableModel getModelGiay() { return modelGiay; }
    public JComboBox<LoaiGiay> getCbLoaiGiay() { return cbLoaiGiay; }
    
    public String getTimKiem() { return txtTimKiem.getText().trim(); }
    public String getId() { return txtId.getText().trim(); }
    public String getTenGiay() { return txtTenGiay.getText().trim(); }
    public String getThuongHieu() { return txtThuongHieu.getText().trim(); }
    public String getSizeGiay() { return txtSize.getText().trim(); }
    public String getMauSac() { return txtMauSac.getText().trim(); }
    public String getGiaBan() { return txtGiaBan.getText().trim(); }
    public String getSoLuongTon() { return txtSoLuongTon.getText().trim(); }
    public String getTrangThai() { return cbTrangThai.getSelectedItem().toString(); }

    // Đổ dữ liệu từ Bảng lên Form khi click (Cập nhật có tenLoai)
    public void setFormData(String id, String tenLoai, String ten, String hang, String size, String mau, String gia, String sl, String trangThai) {
        txtId.setText(id); 
        txtTenGiay.setText(ten); 
        txtThuongHieu.setText(hang);
        txtSize.setText(size); 
        txtMauSac.setText(mau); 
        txtGiaBan.setText(gia);
        txtSoLuongTon.setText(sl); 
        cbTrangThai.setSelectedItem(trangThai);

        // Tìm tên loại trong bảng để Set cho ComboBox
        for (int i = 0; i < cbLoaiGiay.getItemCount(); i++) {
            if (cbLoaiGiay.getItemAt(i).getTenLoai().equals(tenLoai)) {
                cbLoaiGiay.setSelectedIndex(i);
                break;
            }
        }
    }

    public void clearForm() {
        txtId.setText(""); txtTenGiay.setText(""); txtThuongHieu.setText("");
        txtSize.setText(""); txtMauSac.setText(""); txtGiaBan.setText(""); txtSoLuongTon.setText("");
        if(cbLoaiGiay.getItemCount() > 0) cbLoaiGiay.setSelectedIndex(0);
        cbTrangThai.setSelectedIndex(0);
    }

    // --- ĐĂNG KÝ SỰ KIỆN ---
    public void addThemListener(ActionListener listener) { btnThem.addActionListener(listener); }
    public void addSuaListener(ActionListener listener) { btnSua.addActionListener(listener); }
    public void addXoaListener(ActionListener listener) { btnXoa.addActionListener(listener); }
    public void addTimKiemListener(ActionListener listener) { btnTimKiem.addActionListener(listener); }
    public void addLamMoiListener(ActionListener listener) { btnLamMoi.addActionListener(listener); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}