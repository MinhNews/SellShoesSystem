package view;

import com.toedter.calendar.JDateChooser; 
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ThongKePanel extends JPanel {
    // Khai báo các thành phần giao diện
    private JDateChooser jdTuNgay, jdDenNgay;
    private JButton btnLoc;
    private JTable tblThongKe;
    private DefaultTableModel modelThongKe;
    private JLabel lblTongSoDon, lblTongDoanhThu;
    private JPanel pnlChart;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);    // Slate 800
    private Color borderColor = new Color(226, 232, 240); // Slate 200
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public ThongKePanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ==========================================
        // --- KHU VỰC TRÊN: BỘ LỌC --- 
        // ==========================================
        JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlNorth.setBackground(Color.WHITE);
        pnlNorth.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Bộ lọc thời gian"),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));

        jdTuNgay = new JDateChooser();
        jdTuNgay.setPreferredSize(new Dimension(150, 30));
        jdTuNgay.setFont(mainFont);
        
        jdDenNgay = new JDateChooser();
        jdDenNgay.setPreferredSize(new Dimension(150, 30));
        jdDenNgay.setFont(mainFont);
        
        btnLoc = new JButton("Lọc Doanh Thu");
        styleButton(btnLoc, new Color(14, 165, 233), Color.WHITE); // Sky Blue

        JLabel lblTu = new JLabel("Từ ngày:"); lblTu.setFont(mainFont);
        JLabel lblDen = new JLabel("Đến ngày:"); lblDen.setFont(mainFont);

        pnlNorth.add(lblTu);
        pnlNorth.add(jdTuNgay);
        pnlNorth.add(lblDen);
        pnlNorth.add(jdDenNgay);
        pnlNorth.add(btnLoc);

        // ==========================================
        // --- KHU VỰC GIỮA: CHIA ĐÔI (CHART & TABLE)
        // ==========================================
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(550); 
        splitPane.setBackground(Color.WHITE);
        splitPane.setBorder(null); // Bỏ viền gồ ghề của SplitPane

        // Bên trái: Panel biểu đồ
        pnlChart = new JPanel(new BorderLayout());
        pnlChart.setBackground(Color.WHITE);
        pnlChart.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Biểu đồ tăng trưởng (Top Giày)"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        splitPane.setLeftComponent(pnlChart);

        // Bên phải: Bảng số liệu chi tiết
        String[] cols = {"Ngày", "Số Đơn Hàng", "Doanh Thu"};
        modelThongKe = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblThongKe = new JTable(modelThongKe);
        setupTable(tblThongKe);
        
        JScrollPane scrollTable = new JScrollPane(tblThongKe);
        scrollTable.getViewport().setBackground(Color.WHITE);
        scrollTable.setBorder(BorderFactory.createCompoundBorder(
            createModernTitledBorder("Chi tiết số liệu"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        splitPane.setRightComponent(scrollTable);

        // ==========================================
        // --- KHU VỰC DƯỚI: TỔNG KẾT --- 
        // ==========================================
        JPanel pnlSouth = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlSouth.setBackground(Color.WHITE);
        pnlSouth.setPreferredSize(new Dimension(0, 100));

        // Thẻ Card 1: Tổng Đơn
        JPanel pnlCardDon = new JPanel(new BorderLayout());
        pnlCardDon.setBackground(new Color(240, 249, 255)); // Nền xanh nhạt Sky
        pnlCardDon.setBorder(BorderFactory.createLineBorder(new Color(186, 230, 253), 2, true));
        lblTongSoDon = new JLabel("TỔNG SỐ ĐƠN: 0", JLabel.CENTER);
        lblTongSoDon.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTongSoDon.setForeground(new Color(2, 132, 199)); // Chữ Sky đậm
        pnlCardDon.add(lblTongSoDon, BorderLayout.CENTER);

        // Thẻ Card 2: Tổng Doanh Thu
        JPanel pnlCardTien = new JPanel(new BorderLayout());
        pnlCardTien.setBackground(new Color(254, 242, 242)); // Nền đỏ nhạt Rose
        pnlCardTien.setBorder(BorderFactory.createLineBorder(new Color(254, 202, 202), 2, true));
        lblTongDoanhThu = new JLabel("TỔNG DOANH THU: 0 VNĐ", JLabel.CENTER);
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTongDoanhThu.setForeground(new Color(225, 29, 72)); // Chữ Rose đậm
        pnlCardTien.add(lblTongDoanhThu, BorderLayout.CENTER);

        pnlSouth.add(pnlCardDon);
        pnlSouth.add(pnlCardTien);

        // Gắn tất cả vào Panel chính
        add(pnlNorth, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
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
    // --- GETTER & SETTER BẢN GỐC ---
    // ==========================================
    public java.util.Date getTuNgay() { return jdTuNgay.getDate(); }
    public java.util.Date getDenNgay() { return jdDenNgay.getDate(); }
    
    public DefaultTableModel getModelThongKe() { return modelThongKe; }
    public JPanel getChartPanel() { return pnlChart; }

    public void setTongDoanhThu(double tien) {
        lblTongDoanhThu.setText("TỔNG DOANH THU: " + String.format("%,.0f", tien) + " VNĐ");
    }

    public void setTongSoDon(int soDon) {
        lblTongSoDon.setText("TỔNG SỐ ĐƠN: " + soDon);
    }

    public void addLocThongKeListener(ActionListener l) {
        btnLoc.addActionListener(l);
    }
}