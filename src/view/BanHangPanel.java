package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BanHangPanel extends JPanel {
    private JTable tblKhoGiay, tblGioHang;
    private DefaultTableModel modelKho, modelGioHang;
    private JTextField txtTimGiay, txtSdtKhach, txtSoLuongMua;
    private JButton btnThemVaoGio, btnXoaKhoiGio, btnThanhToan, btnTimKhach;
    private JLabel lblTenKhach, lblTongTien;
    // --- THÊM MỚI CHO QUEST 3 ---
    private JCheckBox chkDungDiem;

    public BanHangPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        // --- 1. PANEL BÊN TRÁI: KHO GIÀY ---
        JPanel pnlTrai = new JPanel(new BorderLayout(5, 5));
        pnlTrai.setBorder(BorderFactory.createTitledBorder("1. Chọn Giày Từ Kho"));

        txtTimGiay = new JTextField();
        txtTimGiay.putClientProperty("JTextField.placeholderText", "Tìm kiếm giày...");

        modelKho = new DefaultTableModel(new String[]{"ID", "Tên Giày", "Hãng", "Size", "Giá", "Tồn"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblKhoGiay = new JTable(modelKho); tblKhoGiay.setRowHeight(25);

        JPanel pnlThem = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlThem.add(new JLabel("Số Lượng:"));
        txtSoLuongMua = new JTextField("1", 5);
        btnThemVaoGio = new JButton(">> Thêm Vào Giỏ >>");
        btnThemVaoGio.putClientProperty("JButton.buttonType", "roundRect");
        pnlThem.add(txtSoLuongMua); pnlThem.add(btnThemVaoGio);

        pnlTrai.add(txtTimGiay, BorderLayout.NORTH);
        pnlTrai.add(new JScrollPane(tblKhoGiay), BorderLayout.CENTER);
        pnlTrai.add(pnlThem, BorderLayout.SOUTH);

        // --- 2. PANEL BÊN PHẢI: GIỎ HÀNG ---
        JPanel pnlPhai = new JPanel(new BorderLayout(5, 5));
        pnlPhai.setBorder(BorderFactory.createTitledBorder("2. Giỏ Hàng"));

        modelGioHang = new DefaultTableModel(new String[]{"ID Giày", "Tên Giày", "Số Lượng", "Đơn Giá", "Thành Tiền"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblGioHang = new JTable(modelGioHang); tblGioHang.setRowHeight(25);

        JPanel pnlXoa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnXoaKhoiGio = new JButton("<< Xóa Khỏi Giỏ");
        btnXoaKhoiGio.putClientProperty("JButton.buttonType", "roundRect");
        btnXoaKhoiGio.setBackground(new Color(220, 53, 69)); btnXoaKhoiGio.setForeground(Color.WHITE);
        pnlXoa.add(btnXoaKhoiGio);

        pnlPhai.add(new JScrollPane(tblGioHang), BorderLayout.CENTER);
        pnlPhai.add(pnlXoa, BorderLayout.SOUTH);

        splitPane.setLeftComponent(pnlTrai);
        splitPane.setRightComponent(pnlPhai);

        // --- 3. KHU VỰC THANH TOÁN (BOTTOM) ---
        JPanel pnlThanhToan = new JPanel(new BorderLayout(10, 10));
        pnlThanhToan.setBorder(BorderFactory.createTitledBorder("3. Thanh Toán Hóa Đơn"));

        // Form khách hàng (Bên trái)
        JPanel pnlKhach = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        pnlKhach.add(new JLabel("SĐT Khách Hàng:"));
        txtSdtKhach = new JTextField(15);
        txtSdtKhach.putClientProperty("JTextField.placeholderText", "Để trống nếu là Khách Lẻ...");
        btnTimKhach = new JButton("Check SĐT");
        btnTimKhach.putClientProperty("JButton.buttonType", "roundRect");
        lblTenKhach = new JLabel("Tên KH: Khách Vãng Lai");
        lblTenKhach.setFont(new Font("Arial", Font.ITALIC, 14));
        lblTenKhach.setForeground(Color.BLUE);
        pnlKhach.add(txtSdtKhach); pnlKhach.add(btnTimKhach); pnlKhach.add(lblTenKhach);

        // Nút Thanh toán & Checkbox dùng điểm (Bên phải)
        JPanel pnlTien = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));

        // --- NÂNG CẤP QUEST 3: JCheckBox dùng điểm ---
        chkDungDiem = new JCheckBox("Sử dụng điểm tích lũy (1 điểm = 1.000đ)");
        chkDungDiem.setFont(new Font("Arial", Font.PLAIN, 13));

        lblTongTien = new JLabel("TỔNG CỘNG: 0 VNĐ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 20));
        lblTongTien.setForeground(new Color(220, 53, 69));

        btnThanhToan = new JButton("XUẤT HÓA ĐƠN");
        btnThanhToan.setFont(new Font("Arial", Font.BOLD, 18));
        btnThanhToan.putClientProperty("JButton.buttonType", "roundRect");
        btnThanhToan.setBackground(new Color(40, 167, 69));
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setPreferredSize(new Dimension(200, 40));

        pnlTien.add(chkDungDiem); // Thêm checkbox vào trước
        pnlTien.add(lblTongTien);
        pnlTien.add(btnThanhToan);

        pnlThanhToan.add(pnlKhach, BorderLayout.WEST);
        pnlThanhToan.add(pnlTien, BorderLayout.EAST);

        add(splitPane, BorderLayout.CENTER);
        add(pnlThanhToan, BorderLayout.SOUTH);
    }

    // --- GETTER & SETTER ---
    public DefaultTableModel getModelKho() { return modelKho; }
    public DefaultTableModel getModelGioHang() { return modelGioHang; }
    public JTable getTblKhoGiay() { return tblKhoGiay; }
    public JTable getTblGioHang() { return tblGioHang; }

    public String getSoLuongMua() { return txtSoLuongMua.getText().trim(); }
    public String getSdtKhach() { return txtSdtKhach.getText().trim(); }

    // --- HÀM QUAN TRỌNG TRONG HỢP ĐỒNG QUEST 3 ---
    public boolean isDungDiem() { return chkDungDiem.isSelected(); }

    public void setTenKhach(String ten) { lblTenKhach.setText("Tên KH: " + ten); }
    public void setTongTien(double tien) { lblTongTien.setText("TỔNG CỘNG: " + tien + " VNĐ"); }

    public void clearThanhToan() {
        txtSdtKhach.setText("");
        lblTenKhach.setText("Tên KH: Khách Vãng Lai");
        setTongTien(0);
        modelGioHang.setRowCount(0);
        chkDungDiem.setSelected(false);
    }

    // --- ĐĂNG KÝ LISTENER ---
    public void addThemVaoGioListener(ActionListener l) { btnThemVaoGio.addActionListener(l); }
    public void addXoaKhoiGioListener(ActionListener l) { btnXoaKhoiGio.addActionListener(l); }
    public void addTimKhachListener(ActionListener l) { btnTimKhach.addActionListener(l); }
    public void addThanhToanListener(ActionListener l) { btnThanhToan.addActionListener(l); }

    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}