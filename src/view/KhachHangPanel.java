package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KhachHangPanel extends JPanel {
    private JTable tblKhachHang;
    private JTextField txtTimKiem;
    private JLabel lblTongKhach, lblDiemTB;

    public KhachHangPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- 1. PHẦN TRÊN: Thanh tìm kiếm & Nút chức năng ---
        JPanel pnlTop = new JPanel(new BorderLayout(15, 0));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "🔍 Nhập tên hoặc số điện thoại khách hàng để tìm nhanh...");
        txtTimKiem.setPreferredSize(new Dimension(0, 40));

        JButton btnAdd = new JButton("Thêm Khách Hàng Mới");
        btnAdd.putClientProperty("JButton.buttonType", "roundRect");
        btnAdd.setBackground(new Color(0, 123, 255));
        btnAdd.setForeground(Color.WHITE);

        pnlTop.add(txtTimKiem, BorderLayout.CENTER);
        pnlTop.add(btnAdd, BorderLayout.EAST);
        add(pnlTop, BorderLayout.NORTH);

        // --- 2. PHẦN GIỮA: Bảng danh sách khách hàng ---
        tblKhachHang = new JTable(new DefaultTableModel(
                new Object[]{"Mã KH", "Họ và Tên", "Số Điện Thoại", "Địa Chỉ", "Điểm Tích Lũy"}, 0
        ));
        setupTable(tblKhachHang);
        add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);

        // --- 3. PHẦN DƯỚI: Thống kê nhanh ---
        JPanel pnlBottom = new JPanel(new GridLayout(1, 2, 15, 0));
        pnlBottom.setPreferredSize(new Dimension(0, 60));

        JPanel pnlStats = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        pnlStats.setBorder(new TitledBorder("Thống kê khách hàng"));

        lblTongKhach = new JLabel("Tổng số khách: 0");
        lblDiemTB = new JLabel("Điểm tích lũy cao nhất: 0");
        lblTongKhach.setFont(new Font("Arial", Font.BOLD, 14));

        pnlStats.add(lblTongKhach);
        pnlStats.add(new JSeparator(JSeparator.VERTICAL));
        pnlStats.add(lblDiemTB);

        pnlBottom.add(pnlStats);
        add(pnlBottom, BorderLayout.SOUTH);
    }

    private void setupTable(JTable table) {
        table.setRowHeight(35);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
    }
}