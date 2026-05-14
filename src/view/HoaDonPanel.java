package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
 ThaiBranch

import java.awt.event.ActionListener; 
 main

public class HoaDonPanel extends JPanel {
    private JTable tblHoaDon, tblChiTiet;
    private DefaultTableModel modelHoaDon, modelChiTiet;
 ThaiBranch

    private JButton btnLamMoi; 
 main

    public HoaDonPanel() {
        setLayout(new BorderLayout(10, 10));

 ThaiBranch
        // 1. Bảng Hóa Đơn (Phía trên)
        String[] colHoaDon = {"Mã HĐ", "Nhân Viên", "Khách Hàng", "Ngày Lập", "Tổng Tiền"};
        modelHoaDon = new DefaultTableModel(colHoaDon, 0);

        // --- KHU VỰC TOP: NÚT LÀM MỚI ---
        JPanel pnlControl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLamMoi = new JButton("Làm Mới Danh Sách");
        btnLamMoi.putClientProperty("JButton.buttonType", "roundRect");
        pnlControl.add(btnLamMoi);

        // 1. Bảng Hóa Đơn (Phía trên)
        String[] colHoaDon = {"Mã HĐ", "Nhân Viên", "Khách Hàng", "Ngày Lập", "Tổng Tiền"};
        modelHoaDon = new DefaultTableModel(colHoaDon, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
 main
        tblHoaDon = new JTable(modelHoaDon);
        setupTable(tblHoaDon);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBorder(new TitledBorder("Danh sách Hóa Đơn (Master)"));
 ThaiBranch

        pnlTop.add(pnlControl, BorderLayout.NORTH); // Đưa nút làm mới lên góc phải bảng Hóa Đơn
 main
        pnlTop.add(new JScrollPane(tblHoaDon), BorderLayout.CENTER);

        // 2. Bảng Chi Tiết Hóa Đơn (Phía dưới)
        String[] colChiTiet = {"Mã Giày", "Tên Giày", "Size", "Số lượng", "Đơn giá"};
 ThaiBranch
        modelChiTiet = new DefaultTableModel(colChiTiet, 0);

        modelChiTiet = new DefaultTableModel(colChiTiet, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
 main
        tblChiTiet = new JTable(modelChiTiet);
        setupTable(tblChiTiet);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.setBorder(new TitledBorder("Chi Tiết Sản Phẩm Trong Hóa Đơn (Detail)"));
        pnlBottom.add(new JScrollPane(tblChiTiet), BorderLayout.CENTER);

        // 3. Sử dụng SplitPane để chia đôi trên-dưới
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlTop, pnlBottom);
 ThaiBranch
        splitPane.setDividerLocation(300); // Độ cao bảng trên

        splitPane.setDividerLocation(300); 
 main
        add(splitPane, BorderLayout.CENTER);
    }

    private void setupTable(JTable table) {
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // --- CÁC HÀM TRONG HỢP ĐỒNG VỚI MINH LEAD ---
    public JTable getTblHoaDon() { return tblHoaDon; }
 ThaiBranch

    public DefaultTableModel getModelHoaDon() { return modelHoaDon; }

    public JTable getTblChiTiet() { return tblChiTiet; }

    public DefaultTableModel getModelChiTiet() { return modelChiTiet; }

    public DefaultTableModel getModelHoaDon() { return modelHoaDon; }
    public JTable getTblChiTiet() { return tblChiTiet; }
    public DefaultTableModel getModelChiTiet() { return modelChiTiet; }

    // --- THÊM HÀM LISTENER CHO NÚT LÀM MỚI ---
    public void addLamMoiListener(ActionListener l) {
        btnLamMoi.addActionListener(l);
    }
 main
}