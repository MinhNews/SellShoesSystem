package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BanHangPanel extends JPanel {
    // Khai báo các thành phần cần dùng để sau này dễ dàng gọi từ Controller
    private JTable tblGiay, tblGioHang;
    private JTextField txtSearch, txtSdt, txtTienKhach;
    private JLabel lblTotal;
    private JButton btnThanhToan;

    public BanHangPanel() {
        // Thiết lập khoảng cách cho Panel chính
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- 1. PHẦN TRÊN: Tìm kiếm & Khách hàng ---
        JPanel pnlHeader = new JPanel(new GridLayout(1, 2, 25, 0));

        txtSearch = new JTextField();
        txtSearch.putClientProperty("JTextField.placeholderText", "🔍 Nhập tên giày hoặc mã sản phẩm...");

        txtSdt = new JTextField();
        txtSdt.putClientProperty("JTextField.placeholderText", "👤 Nhập số điện thoại khách hàng...");

        pnlHeader.add(txtSearch);
        pnlHeader.add(txtSdt);
        add(pnlHeader, BorderLayout.NORTH);

        // --- 2. PHẦN GIỮA: Chia đôi Danh sách & Giỏ hàng ---
        JPanel pnlCenter = new JPanel(new GridLayout(1, 2, 15, 0));

        // 2.1 Bên trái: Danh sách giày
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBorder(new TitledBorder("Danh sách giày đang bán"));

        tblGiay = new JTable(new DefaultTableModel(new Object[]{"Mã", "Tên Giày", "Size", "Giá", "Tồn kho"}, 0));
        setupTable(tblGiay);
        pnlLeft.add(new JScrollPane(tblGiay), BorderLayout.CENTER);

        // 2.2 Bên phải: Giỏ hàng
        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setBorder(new TitledBorder("Giỏ hàng của khách"));

        tblGioHang = new JTable(new DefaultTableModel(new Object[]{"Tên Giày", "Số lượng", "Đơn giá", "Thành tiền"}, 0));
        setupTable(tblGioHang);
        pnlRight.add(new JScrollPane(tblGioHang), BorderLayout.CENTER);

        pnlCenter.add(pnlLeft);
        pnlCenter.add(pnlRight);
        add(pnlCenter, BorderLayout.CENTER);

        // --- 3. PHẦN DƯỚI: Tổng tiền & Thanh toán ---
        JPanel pnlFooter = new JPanel(new BorderLayout());
        pnlFooter.setPreferredSize(new Dimension(0, 80));

        // Nhóm bên trái: Nhập tiền khách đưa
        JPanel pnlSubInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 20));
        pnlSubInfo.add(new JLabel("Tiền khách đưa:"));
        txtTienKhach = new JTextField(12);
        txtTienKhach.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlSubInfo.add(txtTienKhach);

        // Nhóm bên phải: Tổng tiền & Nút hành động
        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 10));
        lblTotal = new JLabel("TỔNG TIỀN: 0 VNĐ");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotal.setForeground(new Color(220, 53, 69)); // Màu đỏ cảnh báo

        btnThanhToan = new JButton("THANH TOÁN (F5)");
        btnThanhToan.setPreferredSize(new Dimension(180, 50));
        btnThanhToan.setFont(new Font("Arial", Font.BOLD, 15));

        // Bí kíp FlatLaf: Bo góc tròn & màu xanh lá
        btnThanhToan.putClientProperty("JButton.buttonType", "roundRect");
        btnThanhToan.setBackground(new Color(40, 167, 69));
        btnThanhToan.setForeground(Color.WHITE);

        pnlAction.add(lblTotal);
        pnlAction.add(btnThanhToan);

        pnlFooter.add(pnlSubInfo, BorderLayout.WEST);
        pnlFooter.add(pnlAction, BorderLayout.EAST);
        add(pnlFooter, BorderLayout.SOUTH);
    }

    private void setupTable(JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setReorderingAllowed(false); // Không cho kéo đổi cột
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // --- CÁC HÀM GETTER ĐỂ CONTROLLER TRUY CẬP DỮ LIỆU ---
    public JTable getTblDanhSachGiay() {
        return tblGiay;
    }

    public JTable getTblGioHang() {
        return tblGioHang;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JTextField getTxtSdt() {
        return txtSdt;
    }

    public JTextField getTxtTienKhach() {
        return txtTienKhach;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public JButton getBtnThanhToan() {
        return btnThanhToan;
    }
}