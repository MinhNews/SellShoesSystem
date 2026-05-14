package view;

import model.LoaiGiay;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLyGiayPanel extends JPanel {
    private JTextField txtTimKiem, txtId, txtTenGiay, txtThuongHieu, txtSize, txtMauSac, txtGiaBan, txtSoLuongTon;
    private JComboBox<String> cbTrangThai;
    private JComboBox<LoaiGiay> cbLoaiGiay; 
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblGiay;
    private DefaultTableModel modelGiay;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);    // Slate 800
    private Color borderColor = new Color(226, 232, 240); // Slate 200
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public QuanLyGiayPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ==========================================
        // --- KHU VỰC TOP: TÌM KIẾM & FORM NHẬP LIỆU ---
        // ==========================================
        JPanel pnlNorth = new JPanel(new BorderLayout(10, 15));
        pnlNorth.setBackground(Color.WHITE);
        
        // 1. Thanh tìm kiếm
        JPanel pnlTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlTimKiem.setBackground(Color.WHITE);
        
        txtTimKiem = new JTextField(30);
        txtTimKiem.setFont(mainFont);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Nhập tên giày hoặc thương hiệu...");
        
        btnTimKiem = new JButton("Tìm Kiếm");
        styleButton(btnTimKiem, new Color(30, 41, 59), Color.WHITE);
        
        btnLamMoi = new JButton("Làm Mới");
        styleButton(btnLamMoi, Color.WHITE, primaryText);
        btnLamMoi.setBorder(BorderFactory.createLineBorder(borderColor));
        
        pnlTimKiem.add(txtTimKiem); 
        pnlTimKiem.add(btnTimKiem); 
        pnlTimKiem.add(btnLamMoi);

        // 2. Form nhập liệu
        JPanel pnlForm = new JPanel(new GridLayout(5, 4, 15, 15));
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Thông tin Giày"),
            BorderFactory.createEmptyBorder(10, 15, 15, 15)
        ));
        
        // Cột 1 & 2
        JLabel lblId = new JLabel("ID (Tự động):"); lblId.setFont(mainFont);
        txtId = new JTextField(); 
        txtId.setFont(mainFont);
        txtId.setEditable(false); 
        txtId.setBackground(new Color(248, 250, 252));
        pnlForm.add(lblId); pnlForm.add(txtId);
        
        JLabel lblLoai = new JLabel("Loại Giày:"); lblLoai.setFont(mainFont);
        cbLoaiGiay = new JComboBox<>(); 
        cbLoaiGiay.setFont(mainFont);
        cbLoaiGiay.setBackground(Color.WHITE);
        pnlForm.add(lblLoai); pnlForm.add(cbLoaiGiay); 
        
        // Cột 3 & 4
        JLabel lblTen = new JLabel("Tên Giày:"); lblTen.setFont(mainFont);
        txtTenGiay = new JTextField(); 
        txtTenGiay.setFont(mainFont);
        pnlForm.add(lblTen); pnlForm.add(txtTenGiay);
        
        JLabel lblHang = new JLabel("Thương Hiệu:"); lblHang.setFont(mainFont);
        txtThuongHieu = new JTextField(); 
        txtThuongHieu.setFont(mainFont);
        pnlForm.add(lblHang); pnlForm.add(txtThuongHieu);
        
        // Cột 5 & 6
        JLabel lblSize = new JLabel("Size:"); lblSize.setFont(mainFont);
        txtSize = new JTextField(); 
        txtSize.setFont(mainFont);
        pnlForm.add(lblSize); pnlForm.add(txtSize);
        
        JLabel lblMau = new JLabel("Màu Sắc:"); lblMau.setFont(mainFont);
        txtMauSac = new JTextField(); 
        txtMauSac.setFont(mainFont);
        pnlForm.add(lblMau); pnlForm.add(txtMauSac);
        
        // Cột 7 & 8
        JLabel lblGia = new JLabel("Giá Bán:"); lblGia.setFont(mainFont);
        txtGiaBan = new JTextField(); 
        txtGiaBan.setFont(mainFont);
        pnlForm.add(lblGia); pnlForm.add(txtGiaBan);
        
        JLabel lblSL = new JLabel("Số Lượng Tồn:"); lblSL.setFont(mainFont);
        txtSoLuongTon = new JTextField(); 
        txtSoLuongTon.setFont(mainFont);
        pnlForm.add(lblSL); pnlForm.add(txtSoLuongTon);
        
        // Cột 9 & 10
        JLabel lblTT = new JLabel("Trạng Thái:"); lblTT.setFont(mainFont);
        cbTrangThai = new JComboBox<>(new String[]{"Available", "Out of stock"}); 
        cbTrangThai.setFont(mainFont);
        cbTrangThai.setBackground(Color.WHITE);
        pnlForm.add(lblTT); pnlForm.add(cbTrangThai);
        
        pnlForm.add(new JLabel("")); pnlForm.add(new JLabel("")); // Đệm khoảng trống
        
        pnlNorth.add(pnlTimKiem, BorderLayout.NORTH);
        pnlNorth.add(pnlForm, BorderLayout.CENTER);

        // ==========================================
        // --- KHU VỰC CENTER: BẢNG DỮ LIỆU ---
        // ==========================================
        String[] columns = {"ID", "Loại Giày", "Tên Giày", "Thương Hiệu", "Size", "Màu Sắc", "Giá Bán", "Số Lượng", "Trạng Thái"};
        modelGiay = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; } 
        };
        tblGiay = new JTable(modelGiay);
        setupTable(tblGiay);

        JScrollPane scrollPane = new JScrollPane(tblGiay);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor));
        
        // ==========================================
        // --- KHU VỰC BOTTOM: NÚT CHỨC NĂNG ---
        // ==========================================
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pnlSouth.setBackground(Color.WHITE);
        
        btnThem = new JButton("Thêm Mới");
        styleButton(btnThem, new Color(16, 185, 129), Color.WHITE);
        
        btnSua = new JButton("Cập Nhật");
        styleButton(btnSua, new Color(14, 165, 233), Color.WHITE);
        
        btnXoa = new JButton("Xóa");
        styleButton(btnXoa, new Color(239, 68, 68), Color.WHITE);

        pnlSouth.add(btnThem); 
        pnlSouth.add(btnSua); 
        pnlSouth.add(btnXoa);

        add(pnlNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- HÀM TIỆN ÍCH DÙNG CHUNG ĐỂ STYLE GIAO DIỆN ---
    private TitledBorder createModernTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 1, true),
            title, TitledBorder.LEFT, TitledBorder.TOP, boldFont, primaryText
        );
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(boldFont);
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
    }

    private void setupTable(JTable table) {
        table.setFont(mainFont);
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(226, 232, 240)); 
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(false); 
        table.setGridColor(borderColor);
        
        table.getTableHeader().setFont(boldFont);
        table.getTableHeader().setBackground(new Color(241, 245, 249)); 
        table.getTableHeader().setForeground(primaryText);
        table.getTableHeader().setPreferredSize(new Dimension(100, 35));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // ==========================================
    // --- GETTER & SETTER BẢN GỐC ---
    // ==========================================
    public void setLoaiGiayData(List<LoaiGiay> list) {
        cbLoaiGiay.removeAllItems();
        for (LoaiGiay lg : list) {
            cbLoaiGiay.addItem(lg);
        }
    }

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

    public void setFormData(String id, String tenLoai, String ten, String hang, String size, String mau, String gia, String sl, String trangThai) {
        txtId.setText(id); 
        txtTenGiay.setText(ten); 
        txtThuongHieu.setText(hang);
        txtSize.setText(size); 
        txtMauSac.setText(mau); 
        txtGiaBan.setText(gia);
        txtSoLuongTon.setText(sl); 
        cbTrangThai.setSelectedItem(trangThai);

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

    public void addThemListener(ActionListener listener) { btnThem.addActionListener(listener); }
    public void addSuaListener(ActionListener listener) { btnSua.addActionListener(listener); }
    public void addXoaListener(ActionListener listener) { btnXoa.addActionListener(listener); }
    public void addTimKiemListener(ActionListener listener) { btnTimKiem.addActionListener(listener); }
    public void addLamMoiListener(ActionListener listener) { btnLamMoi.addActionListener(listener); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}