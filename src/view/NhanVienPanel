package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class NhanVienPanel extends JPanel {
    // 1. Khai báo các thành phần giao diện theo đúng yêu cầu [cite: 10, 22]
    private JTextField txtId, txtUsername, txtHoTen, txtSdt;
    private JPasswordField txtPassword; // Dùng JPasswordField để bảo mật 
    private JComboBox<String> cbQuyen; // Chọn Admin (0) hoặc Nhân viên (1) [cite: 10, 30]
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private JTable tblNhanVien;
    private DefaultTableModel modelNhanVien;

    public NhanVienPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- FORM NHẬP LIỆU  ---
        JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
        
        JPanel pnlForm = new JPanel(new GridLayout(3, 4, 10, 10)); // Tăng lên 3 hàng để đủ chỗ
        pnlForm.setBorder(BorderFactory.createTitledBorder("Quản Lý Tài Khoản Nhân Viên"));
        
        pnlForm.add(new JLabel("Mã Nhân Viên:")); 
        txtId = new JTextField(); txtId.setEditable(false); pnlForm.add(txtId);
        
        pnlForm.add(new JLabel("Tên Đăng Nhập:")); 
        txtUsername = new JTextField(); pnlForm.add(txtUsername);
        
        pnlForm.add(new JLabel("Mật Khẩu:")); 
        txtPassword = new JPasswordField(); pnlForm.add(txtPassword); // Thành phần bổ sung 
        
        pnlForm.add(new JLabel("Họ và Tên:")); 
        txtHoTen = new JTextField(); pnlForm.add(txtHoTen);
        
        pnlForm.add(new JLabel("Số Điện Thoại:")); 
        txtSdt = new JTextField(); pnlForm.add(txtSdt);
        
        pnlForm.add(new JLabel("Quyền Hạn:")); 
        String[] quyen = {"Admin", "Nhân viên"};
        cbQuyen = new JComboBox<>(quyen); // 0 - Admin, 1 - Nhân viên [cite: 10, 30]
        pnlForm.add(cbQuyen);
        
        pnlNorth.add(pnlForm, BorderLayout.CENTER);

        // --- BẢNG HIỂN THỊ [cite: 32, 33] ---
        String[] cols = {"Mã NV", "Tên Đăng Nhập", "Họ Tên", "SĐT", "Quyền"};
        modelNhanVien = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblNhanVien = new JTable(modelNhanVien);
        tblNhanVien.setRowHeight(25);

        // --- CÁC NÚT BẤM ---
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnThem = new JButton("Thêm Mới"); btnThem.setBackground(new Color(40, 167, 69)); btnThem.setForeground(Color.WHITE);
        btnSua = new JButton("Cập Nhật"); btnSua.setBackground(new Color(23, 162, 184)); btnSua.setForeground(Color.WHITE);
        btnXoa = new JButton("Xóa"); btnXoa.setBackground(new Color(220, 53, 69)); btnXoa.setForeground(Color.WHITE);
        btnLamMoi = new JButton("Làm Mới");
        
        JButton[] btns = {btnThem, btnSua, btnXoa, btnLamMoi};
        for (JButton b : btns) { 
            b.putClientProperty("JButton.buttonType", "roundRect"); 
            pnlSouth.add(b); 
        }

        add(pnlNorth, BorderLayout.NORTH);
        add(new JScrollPane(tblNhanVien), BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // NHÓM HÀM GETTER 
    public String getId() { return txtId.getText().trim(); }
    public String getUsername() { return txtUsername.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public String getHoTen() { return txtHoTen.getText().trim(); }
    public String getDienThoai() { return txtSdt.getText().trim(); }
    
    // Lấy index: 0 là Admin, 1 là Nhân viên 
    public int getQuyen() { return cbQuyen.getSelectedIndex(); } 

    // NHÓM HÀM THAO TÁC UI
    public DefaultTableModel getModelNhanVien() { return modelNhanVien; }
    public JTable getTblNhanVien() { return tblNhanVien; }

    public void setFormData(String id, String user, String hoten, String sdt, int quyenIndex) {
        txtId.setText(id);
        txtUsername.setText(user);
        txtHoTen.setText(hoten);
        txtSdt.setText(sdt);
        cbQuyen.setSelectedIndex(quyenIndex);
        txtPassword.setText("******"); // Gán cứng mật khẩu khi đổ data lên 
    }

    public void clearForm() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtHoTen.setText("");
        txtSdt.setText("");
        cbQuyen.setSelectedIndex(1); // Mặc định chọn Nhân viên
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    //NHÓM HÀM GẮN LISTENER
    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
}