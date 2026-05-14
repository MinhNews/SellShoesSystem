package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);    // Slate 800
    private Color borderColor = new Color(226, 232, 240); // Slate 200
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public BanHangPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5);
        splitPane.setBackground(Color.WHITE);
        splitPane.setBorder(null); // Bỏ viền mặc định của SplitPane

        // ==========================================
        // --- 1. PANEL BÊN TRÁI: KHO GIÀY ---
        // ==========================================
        JPanel pnlTrai = new JPanel(new BorderLayout(10, 10));
        pnlTrai.setBackground(Color.WHITE);
        pnlTrai.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("1. Chọn Giày Từ Kho"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        txtTimGiay = new JTextField();
        txtTimGiay.setFont(mainFont);
        txtTimGiay.putClientProperty("JTextField.placeholderText", "Tìm kiếm giày...");
        
        modelKho = new DefaultTableModel(new String[]{"ID", "Tên Giày", "Hãng", "Size", "Giá", "Tồn"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblKhoGiay = new JTable(modelKho); 
        setupTable(tblKhoGiay);

        JPanel pnlThem = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlThem.setBackground(Color.WHITE);
        
        JLabel lblSoLuong = new JLabel("Số Lượng:"); lblSoLuong.setFont(mainFont);
        pnlThem.add(lblSoLuong);
        
        txtSoLuongMua = new JTextField("1", 5);
        txtSoLuongMua.setFont(mainFont);
        
        btnThemVaoGio = new JButton(">> Thêm Vào Giỏ >>");
        styleButton(btnThemVaoGio, new Color(16, 185, 129), Color.WHITE); // Emerald Green
        
        pnlThem.add(txtSoLuongMua); 
        pnlThem.add(btnThemVaoGio);

        JScrollPane scrollKho = new JScrollPane(tblKhoGiay);
        styleScrollPane(scrollKho);

        pnlTrai.add(txtTimGiay, BorderLayout.NORTH);
        pnlTrai.add(scrollKho, BorderLayout.CENTER);
        pnlTrai.add(pnlThem, BorderLayout.SOUTH);

        // ==========================================
        // --- 2. PANEL BÊN PHẢI: GIỎ HÀNG ---
        // ==========================================
        JPanel pnlPhai = new JPanel(new BorderLayout(10, 10));
        pnlPhai.setBackground(Color.WHITE);
        pnlPhai.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("2. Giỏ Hàng"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        modelGioHang = new DefaultTableModel(new String[]{"ID Giày", "Tên Giày", "Số Lượng", "Đơn Giá", "Thành Tiền"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblGioHang = new JTable(modelGioHang); 
        setupTable(tblGioHang);

        JPanel pnlXoa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlXoa.setBackground(Color.WHITE);
        
        btnXoaKhoiGio = new JButton("<< Xóa Khỏi Giỏ");
        styleButton(btnXoaKhoiGio, new Color(239, 68, 68), Color.WHITE); // Rose Red
        pnlXoa.add(btnXoaKhoiGio);

        JScrollPane scrollGio = new JScrollPane(tblGioHang);
        styleScrollPane(scrollGio);

        pnlPhai.add(scrollGio, BorderLayout.CENTER);
        pnlPhai.add(pnlXoa, BorderLayout.SOUTH);

        splitPane.setLeftComponent(pnlTrai);
        splitPane.setRightComponent(pnlPhai);

        // ==========================================
        // --- 3. KHU VỰC THANH TOÁN (BOTTOM) ---
        // ==========================================
        JPanel pnlThanhToan = new JPanel(new GridLayout(2, 1, 10, 10));
        pnlThanhToan.setBackground(Color.WHITE);
        pnlThanhToan.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("3. Thanh Toán Hóa Đơn"),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));

        // DÒNG 1: Form khách hàng
        JPanel pnlKhach = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        pnlKhach.setBackground(Color.WHITE);
        
        JLabel lblSdt = new JLabel("SĐT Khách Hàng:"); lblSdt.setFont(mainFont);
        pnlKhach.add(lblSdt);
        
        txtSdtKhach = new JTextField(15);
        txtSdtKhach.setFont(mainFont);
        txtSdtKhach.putClientProperty("JTextField.placeholderText", "Để trống nếu là Khách Lẻ...");
        
        btnTimKhach = new JButton("Check SĐT");
        styleButton(btnTimKhach, new Color(14, 165, 233), Color.WHITE); // Sky Blue
        
        lblTenKhach = new JLabel("Tên KH: Khách Vãng Lai");
        lblTenKhach.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblTenKhach.setForeground(new Color(14, 165, 233));
        
        pnlKhach.add(txtSdtKhach); pnlKhach.add(btnTimKhach); pnlKhach.add(lblTenKhach);

        // DÒNG 2: Checkbox, Tổng tiền & Nút Thanh toán
        JPanel pnlTien = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 5));
        pnlTien.setBackground(Color.WHITE);

        chkDungDiem = new JCheckBox("Sử dụng điểm tích lũy (1 điểm = 1.000đ)");
        chkDungDiem.setFont(mainFont);
        chkDungDiem.setBackground(Color.WHITE);
        chkDungDiem.setFocusPainted(false);

        lblTongTien = new JLabel("TỔNG CỘNG: 0 VNĐ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTongTien.setForeground(new Color(239, 68, 68)); // Đỏ nổi bật

        btnThanhToan = new JButton("XUẤT HÓA ĐƠN");
        styleButton(btnThanhToan, new Color(16, 185, 129), Color.WHITE); // Lục Emerald bảo chứng thành công
        btnThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnThanhToan.setPreferredSize(new Dimension(200, 45)); // Nút to đùng dễ bấm

        pnlTien.add(chkDungDiem); 
        pnlTien.add(lblTongTien);
        pnlTien.add(btnThanhToan);

        pnlThanhToan.add(pnlKhach);
        pnlThanhToan.add(pnlTien);

        add(splitPane, BorderLayout.CENTER);
        add(pnlThanhToan, BorderLayout.SOUTH);
    }

    // --- HÀM TIỆN ÍCH DÙNG CHUNG ĐỂ STYLE GIAO DIỆN ---
    private TitledBorder createModernTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 1, true),
            title, TitledBorder.LEFT, TitledBorder.TOP, boldFont, primaryText
        );
    }

    private void styleScrollPane(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(borderColor));
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
    public DefaultTableModel getModelKho() { return modelKho; }
    public DefaultTableModel getModelGioHang() { return modelGioHang; }
    public JTable getTblKhoGiay() { return tblKhoGiay; }
    public JTable getTblGioHang() { return tblGioHang; }

    public String getSoLuongMua() { return txtSoLuongMua.getText().trim(); }
    public String getSdtKhach() { return txtSdtKhach.getText().trim(); }
    public String getTimKiemGiay() { return txtTimGiay.getText().trim(); }

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

    public void addThemVaoGioListener(ActionListener l) { btnThemVaoGio.addActionListener(l); }
    public void addXoaKhoiGioListener(ActionListener l) { btnXoaKhoiGio.addActionListener(l); }
    public void addTimKhachListener(ActionListener l) { btnTimKhach.addActionListener(l); }
    public void addThanhToanListener(ActionListener l) { btnThanhToan.addActionListener(l); }
    public void addTimKiemGiayListener(java.awt.event.KeyListener l) { txtTimGiay.addKeyListener(l); }
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}