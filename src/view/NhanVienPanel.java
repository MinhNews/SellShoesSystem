package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class NhanVienPanel extends JPanel {
    private JTextField txtId, txtUsername, txtHoTen, txtSdt;
    private JPasswordField txtPassword; 
    private JComboBox<String> cbQuyen; 
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private JTable tblNhanVien;
    private DefaultTableModel modelNhanVien;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);    // Slate 800
    private Color borderColor = new Color(226, 232, 240); // Slate 200
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public NhanVienPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE); // Phủ nền trắng
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ==========================================
        // --- FORM NHẬP LIỆU ---
        // ==========================================
        JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
        pnlNorth.setBackground(Color.WHITE);
        
        JPanel pnlForm = new JPanel(new GridLayout(3, 4, 15, 15));
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Quản Lý Tài Khoản Nhân Viên"),
            BorderFactory.createEmptyBorder(10, 15, 15, 15)
        ));
        
        // Dòng 1
        JLabel lblId = new JLabel("Mã Nhân Viên:"); lblId.setFont(mainFont);
        txtId = new JTextField(); 
        txtId.setFont(mainFont);
        txtId.setEditable(false); 
        txtId.setBackground(new Color(248, 250, 252)); // Nền xám nhạt chỉ định không được sửa
        pnlForm.add(lblId); pnlForm.add(txtId);
        
        JLabel lblUsername = new JLabel("Tên Đăng Nhập:"); lblUsername.setFont(mainFont);
        txtUsername = new JTextField(); 
        txtUsername.setFont(mainFont);
        pnlForm.add(lblUsername); pnlForm.add(txtUsername);
        
        // Dòng 2
        JLabel lblPass = new JLabel("Mật Khẩu:"); lblPass.setFont(mainFont);
        txtPassword = new JPasswordField(); 
        txtPassword.setFont(mainFont);
        pnlForm.add(lblPass); pnlForm.add(txtPassword);
        
        JLabel lblHoTen = new JLabel("Họ và Tên:"); lblHoTen.setFont(mainFont);
        txtHoTen = new JTextField(); 
        txtHoTen.setFont(mainFont);
        pnlForm.add(lblHoTen); pnlForm.add(txtHoTen);
        
        // Dòng 3
        JLabel lblSdt = new JLabel("Số Điện Thoại:"); lblSdt.setFont(mainFont);
        txtSdt = new JTextField(); 
        txtSdt.setFont(mainFont);
        pnlForm.add(lblSdt); pnlForm.add(txtSdt);
        
        JLabel lblQuyen = new JLabel("Quyền Hạn:"); lblQuyen.setFont(mainFont);
        String[] quyen = {"Admin", "Nhân viên"};
        cbQuyen = new JComboBox<>(quyen); 
        cbQuyen.setFont(mainFont);
        cbQuyen.setBackground(Color.WHITE);
        pnlForm.add(lblQuyen); pnlForm.add(cbQuyen);
        
        pnlNorth.add(pnlForm, BorderLayout.CENTER);

        // ==========================================
        // --- BẢNG HIỂN THỊ ---
        // ==========================================
        String[] cols = {"Mã NV", "Tên Đăng Nhập", "Họ Tên", "SĐT", "Quyền"};
        modelNhanVien = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblNhanVien = new JTable(modelNhanVien);
        setupTable(tblNhanVien);

        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor));

        // ==========================================
        // --- CÁC NÚT BẤM (BOTTOM) ---
        // ==========================================
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pnlSouth.setBackground(Color.WHITE);
        
        btnLamMoi = new JButton("Làm Mới");
        styleButton(btnLamMoi, Color.WHITE, primaryText);
        btnLamMoi.setBorder(BorderFactory.createLineBorder(borderColor));
        
        btnThem = new JButton("Thêm Mới"); 
        styleButton(btnThem, new Color(16, 185, 129), Color.WHITE); // Emerald
        
        btnSua = new JButton("Cập Nhật"); 
        styleButton(btnSua, new Color(14, 165, 233), Color.WHITE);   // Sky Blue
        
        btnXoa = new JButton("Xóa"); 
        styleButton(btnXoa, new Color(239, 68, 68), Color.WHITE);     // Rose Red
        
        pnlSouth.add(btnLamMoi);
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
    public String getId() { return txtId.getText().trim(); }
    public String getUsername() { return txtUsername.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public String getHoTen() { return txtHoTen.getText().trim(); }
    public String getDienThoai() { return txtSdt.getText().trim(); }
    
    public int getQuyen() { return cbQuyen.getSelectedIndex(); } 

    public DefaultTableModel getModelNhanVien() { return modelNhanVien; }
    public JTable getTblNhanVien() { return tblNhanVien; }

    public void setFormData(String id, String user, String hoten, String sdt, int quyenIndex) {
        txtId.setText(id);
        txtUsername.setText(user);
        txtHoTen.setText(hoten);
        txtSdt.setText(sdt);
        cbQuyen.setSelectedIndex(quyenIndex);
        txtPassword.setText("******"); 
    }

    public void clearForm() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtHoTen.setText("");
        txtSdt.setText("");
        cbQuyen.setSelectedIndex(1); 
    }

    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }

    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
}