package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class HoaDonPanel extends JPanel {
    private JTable tblHoaDon, tblChiTiet;
    private DefaultTableModel modelHoaDon, modelChiTiet;
    private JButton btnLamMoi;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);   // Chữ xám đậm
    private Color borderColor = new Color(226, 232, 240); // Viền xám nhạt
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public HoaDonPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE); // Phủ trắng toàn Panel
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- KHU VỰC TOP: NÚT LÀM MỚI ---
        JPanel pnlControl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlControl.setBackground(Color.WHITE);
        
        btnLamMoi = new JButton("Làm Mới Danh Sách");
        btnLamMoi.setFont(boldFont);
        btnLamMoi.setBackground(Color.WHITE); 
        btnLamMoi.setForeground(primaryText);
        btnLamMoi.putClientProperty("JButton.buttonType", "roundRect");
        btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLamMoi.setFocusPainted(false);
        pnlControl.add(btnLamMoi);

        // 1. Bảng Hóa Đơn (Phía trên)
        String[] colHoaDon = {"Mã HĐ", "Nhân Viên", "Khách Hàng", "Ngày Lập", "Tổng Tiền"};
        modelHoaDon = new DefaultTableModel(colHoaDon, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblHoaDon = new JTable(modelHoaDon);
        setupTable(tblHoaDon);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(Color.WHITE);
        pnlTop.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Danh sách Hóa Đơn (Master)"),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));
        pnlTop.add(pnlControl, BorderLayout.NORTH); 
        
        JScrollPane scrollHoaDon = new JScrollPane(tblHoaDon);
        styleScrollPane(scrollHoaDon);
        pnlTop.add(scrollHoaDon, BorderLayout.CENTER);

        // 2. Bảng Chi Tiết Hóa Đơn (Phía dưới)
        String[] colChiTiet = {"Mã Giày", "Tên Giày", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        modelChiTiet = new DefaultTableModel(colChiTiet, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblChiTiet = new JTable(modelChiTiet);
        setupTable(tblChiTiet);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Chi Tiết Sản Phẩm Trong Hóa Đơn (Detail)"),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));
        
        JScrollPane scrollChiTiet = new JScrollPane(tblChiTiet);
        styleScrollPane(scrollChiTiet);
        pnlBottom.add(scrollChiTiet, BorderLayout.CENTER);

        // 3. Sử dụng SplitPane để chia đôi trên-dưới
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlTop, pnlBottom);
        splitPane.setDividerLocation(300); 
        splitPane.setBackground(Color.WHITE);
        splitPane.setBorder(null); // Bỏ cái viền gồ ghề mặc định của SplitPane
        add(splitPane, BorderLayout.CENTER);
    }

    // --- HÀM TIỆN ÍCH DÙNG CHUNG ĐỂ STYLE GIAO DIỆN ---
    
    // Tạo TitledBorder hiện đại
    private TitledBorder createModernTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 1, true),
            title,
            TitledBorder.LEFT, TitledBorder.TOP,
            boldFont, primaryText
        );
    }

    // Style cho JScrollPane (Bỏ viền 3D cũ kỹ)
    private void styleScrollPane(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(borderColor));
    }

    // Style cho JTable phẳng và hiện đại
    private void setupTable(JTable table) {
        table.setFont(mainFont);
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(226, 232, 240)); // Màu khi click chọn dòng
        table.setSelectionForeground(Color.BLACK);
        table.setShowVerticalLines(false); // Xóa kẻ dọc
        table.setGridColor(borderColor);
        
        table.getTableHeader().setFont(boldFont);
        table.getTableHeader().setBackground(new Color(241, 245, 249)); // Nền xám nhạt cho Header
        table.getTableHeader().setForeground(primaryText);
        table.getTableHeader().setPreferredSize(new Dimension(100, 35));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // --- CÁC HÀM TRONG HỢP ĐỒNG VỚI MINH LEAD ---
    public JTable getTblHoaDon() { return tblHoaDon; }
    public DefaultTableModel getModelHoaDon() { return modelHoaDon; }
    public JTable getTblChiTiet() { return tblChiTiet; }
    public DefaultTableModel getModelChiTiet() { return modelChiTiet; }

    public void addLamMoiListener(ActionListener l) {
        btnLamMoi.addActionListener(l);
    }
}