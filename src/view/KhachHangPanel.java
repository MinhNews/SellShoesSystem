package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class KhachHangPanel extends JPanel {
    private JTextField txtId, txtHoTen, txtSdt, txtDiem, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblKhachHang;
    private DefaultTableModel modelKhachHang;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);    // Slate 800
    private Color borderColor = new Color(226, 232, 240); // Slate 200
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public KhachHangPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE); // Nền trắng toàn tập
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ==========================================
        // --- TOP: TÌM KIẾM & FORM ---
        // ==========================================
        JPanel pnlNorth = new JPanel(new BorderLayout(10, 15));
        pnlNorth.setBackground(Color.WHITE);
        
        // 1. Khu vực Tìm kiếm
        JPanel pnlTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlTimKiem.setBackground(Color.WHITE);
        
        txtTimKiem = new JTextField(25);
        txtTimKiem.setFont(mainFont);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Nhập Số điện thoại cần tìm...");
        
        btnTimKiem = new JButton("Tìm Kiếm");
        styleButton(btnTimKiem, new Color(30, 41, 59), Color.WHITE); // Nền xám đậm, chữ trắng
        
        btnLamMoi = new JButton("Làm Mới");
        styleButton(btnLamMoi, Color.WHITE, primaryText); // Nền trắng, chữ xám
        btnLamMoi.setBorder(BorderFactory.createLineBorder(borderColor)); // Thêm viền nhẹ
        
        pnlTimKiem.add(txtTimKiem); 
        pnlTimKiem.add(btnTimKiem); 
        pnlTimKiem.add(btnLamMoi);

        // 2. Khu vực Form Hồ Sơ
        JPanel pnlForm = new JPanel(new GridLayout(2, 4, 15, 10));
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Hồ Sơ Khách Hàng"),
            BorderFactory.createEmptyBorder(10, 15, 15, 15)
        ));
        
        // Dòng 1
        JLabel lblId = new JLabel("ID:"); lblId.setFont(mainFont);
        txtId = new JTextField(); 
        txtId.setFont(mainFont);
        txtId.setEditable(false); 
        txtId.setBackground(new Color(248, 250, 252)); // Xám nhạt báo hiệu không cho sửa
        pnlForm.add(lblId); pnlForm.add(txtId);
        
        JLabel lblHoTen = new JLabel("Họ và Tên:"); lblHoTen.setFont(mainFont);
        txtHoTen = new JTextField(); 
        txtHoTen.setFont(mainFont);
        pnlForm.add(lblHoTen); pnlForm.add(txtHoTen);
        
        // Dòng 2
        JLabel lblSdt = new JLabel("Số Điện Thoại:"); lblSdt.setFont(mainFont);
        txtSdt = new JTextField(); 
        txtSdt.setFont(mainFont);
        pnlForm.add(lblSdt); pnlForm.add(txtSdt);
        
        JLabel lblDiem = new JLabel("Điểm Tích Lũy:"); lblDiem.setFont(mainFont);
        txtDiem = new JTextField("0"); 
        txtDiem.setFont(mainFont);
        txtDiem.setEditable(false); 
        txtDiem.setBackground(new Color(248, 250, 252)); 
        txtDiem.setForeground(new Color(239, 68, 68)); // Đổi màu điểm nhấn cho nổi
        txtDiem.setFont(boldFont);
        pnlForm.add(lblDiem); pnlForm.add(txtDiem);
        
        pnlNorth.add(pnlTimKiem, BorderLayout.NORTH);
        pnlNorth.add(pnlForm, BorderLayout.CENTER);

        // ==========================================
        // --- CENTER: BẢNG DỮ LIỆU ---
        // ==========================================
        String[] cols = {"ID", "Họ Tên Khách Hàng", "Số Điện Thoại", "Điểm Tích Lũy"};
        modelKhachHang = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblKhachHang = new JTable(modelKhachHang);
        setupTable(tblKhachHang);

        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor));

        // ==========================================
        // --- BOTTOM: NÚT CHỨC NĂNG ---
        // ==========================================
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pnlSouth.setBackground(Color.WHITE);
        
        btnThem = new JButton("Thêm Mới"); 
        styleButton(btnThem, new Color(16, 185, 129), Color.WHITE); // Emerald Green
        
        btnSua = new JButton("Cập Nhật"); 
        styleButton(btnSua, new Color(14, 165, 233), Color.WHITE);   // Sky Blue
        
        btnXoa = new JButton("Xóa"); 
        styleButton(btnXoa, new Color(239, 68, 68), Color.WHITE);     // Rose Red
        
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
    // --- GETTER & SETTER & LISTENER BẢN GỐC ---
    // ==========================================
    public JTable getTblKhachHang() { return tblKhachHang; }
    public DefaultTableModel getModelKhachHang() { return modelKhachHang; }
    
    public String getTimKiem() { return txtTimKiem.getText().trim(); }
    public String getId() { return txtId.getText().trim(); }
    public String getHoTen() { return txtHoTen.getText().trim(); }
    public String getSdt() { return txtSdt.getText().trim(); }

    public void setFormData(String id, String hoTen, String sdt, String diem) {
        txtId.setText(id); txtHoTen.setText(hoTen); txtSdt.setText(sdt); txtDiem.setText(diem);
    }
    public void clearForm() { 
        txtId.setText(""); txtHoTen.setText(""); txtSdt.setText(""); txtDiem.setText("0"); 
    }

    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}