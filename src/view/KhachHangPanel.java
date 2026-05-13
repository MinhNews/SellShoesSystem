package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class KhachHangPanel extends JPanel {
    private JTextField txtId, txtHoTen, txtSdt, txtDiem, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblKhachHang;
    private DefaultTableModel modelKhachHang;

    public KhachHangPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- TOP: TÌM KIẾM & FORM ---
        JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
        
        JPanel pnlTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTimKiem = new JTextField(25);
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Nhập Số điện thoại cần tìm...");
        btnTimKiem = new JButton("Tìm Kiếm");
        btnLamMoi = new JButton("Làm Mới");
        btnTimKiem.putClientProperty("JButton.buttonType", "roundRect");
        btnLamMoi.putClientProperty("JButton.buttonType", "roundRect");
        pnlTimKiem.add(txtTimKiem); pnlTimKiem.add(btnTimKiem); pnlTimKiem.add(btnLamMoi);

        JPanel pnlForm = new JPanel(new GridLayout(2, 4, 10, 10));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Hồ Sơ Khách Hàng"));
        
        pnlForm.add(new JLabel("ID:")); txtId = new JTextField(); txtId.setEditable(false); pnlForm.add(txtId);
        pnlForm.add(new JLabel("Họ và Tên:")); txtHoTen = new JTextField(); pnlForm.add(txtHoTen);
        pnlForm.add(new JLabel("Số Điện Thoại:")); txtSdt = new JTextField(); pnlForm.add(txtSdt);
        pnlForm.add(new JLabel("Điểm Tích Lũy:")); txtDiem = new JTextField("0"); txtDiem.setEditable(false); pnlForm.add(txtDiem); // Điểm do hệ thống tự tính
        
        pnlNorth.add(pnlTimKiem, BorderLayout.NORTH);
        pnlNorth.add(pnlForm, BorderLayout.CENTER);

        // --- CENTER: BẢNG ---
        String[] cols = {"ID", "Họ Tên Khách Hàng", "Số Điện Thoại", "Điểm Tích Lũy"};
        modelKhachHang = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblKhachHang = new JTable(modelKhachHang);
        tblKhachHang.setRowHeight(25);

        // --- BOTTOM: NÚT ---
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnThem = new JButton("Thêm Mới"); btnThem.setBackground(new Color(40, 167, 69)); btnThem.setForeground(Color.WHITE);
        btnSua = new JButton("Cập Nhật"); btnSua.setBackground(new Color(23, 162, 184)); btnSua.setForeground(Color.WHITE);
        btnXoa = new JButton("Xóa"); btnXoa.setBackground(new Color(220, 53, 69)); btnXoa.setForeground(Color.WHITE);
        
        JButton[] btns = {btnThem, btnSua, btnXoa};
        for (JButton b : btns) { b.putClientProperty("JButton.buttonType", "roundRect"); pnlSouth.add(b); }

        add(pnlNorth, BorderLayout.NORTH);
        add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    public JTable getTblKhachHang() { return tblKhachHang; }
    public DefaultTableModel getModelKhachHang() { return modelKhachHang; }
    
    public String getTimKiem() { return txtTimKiem.getText().trim(); }
    public String getId() { return txtId.getText().trim(); }
    public String getHoTen() { return txtHoTen.getText().trim(); }
    public String getSdt() { return txtSdt.getText().trim(); }

    public void setFormData(String id, String hoTen, String sdt, String diem) {
        txtId.setText(id); txtHoTen.setText(hoTen); txtSdt.setText(sdt); txtDiem.setText(diem);
    }
    public void clearForm() { txtId.setText(""); txtHoTen.setText(""); txtSdt.setText(""); txtDiem.setText("0"); }

    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}