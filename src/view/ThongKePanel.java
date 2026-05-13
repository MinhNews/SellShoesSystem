package view;

import com.toedter.calendar.JDateChooser; // thư viện jcalendar.jar 
import javax.swing.*;
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

    public ThongKePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- KHU VỰC TRÊN: BỘ LỌC --- 
        JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlNorth.setBorder(BorderFactory.createTitledBorder("Bộ lọc thời gian"));

        jdTuNgay = new JDateChooser();
        jdTuNgay.setPreferredSize(new Dimension(150, 25));
        jdDenNgay = new JDateChooser();
        jdDenNgay.setPreferredSize(new Dimension(150, 25));
        btnLoc = new JButton("Lọc Doanh Thu");
        btnLoc.putClientProperty("JButton.buttonType", "roundRect");

        pnlNorth.add(new JLabel("Từ ngày:"));
        pnlNorth.add(jdTuNgay);
        pnlNorth.add(new JLabel("Đến ngày:"));
        pnlNorth.add(jdDenNgay);
        pnlNorth.add(btnLoc);

        // --- KHU VỰC GIỮA: CHIA ĐÔI (CHART & TABLE)
        // Sử dụng SplitPane để chia đôi màn hình
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500); // Độ rộng ban đầu cho phần biểu đồ

        // Bên trái: Panel biểu đồ
        pnlChart = new JPanel(new BorderLayout());
        pnlChart.setBorder(BorderFactory.createTitledBorder("Biểu đồ tăng trưởng"));
        
        splitPane.setLeftComponent(pnlChart);

        // Bên phải: Bảng số liệu chi tiết
        String[] cols = {"Ngày", "Số Đơn Hàng", "Doanh Thu"};
        modelThongKe = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblThongKe = new JTable(modelThongKe);
        JScrollPane scrollTable = new JScrollPane(tblThongKe);
        scrollTable.setBorder(BorderFactory.createTitledBorder("Chi tiết số liệu"));
        splitPane.setRightComponent(scrollTable);

        // --- KHU VỰC DƯỚI: TỔNG KẾT (JLabel khổng lồ) --- 
        JPanel pnlSouth = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlSouth.setPreferredSize(new Dimension(0, 80));

        lblTongSoDon = new JLabel("TỔNG SỐ ĐƠN: 0", JLabel.CENTER);
        lblTongSoDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTongSoDon.setForeground(new Color(0, 102, 204));

        lblTongDoanhThu = new JLabel("TỔNG DOANH THU: 0 VNĐ", JLabel.CENTER);
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTongDoanhThu.setForeground(Color.RED);

        pnlSouth.add(lblTongSoDon);
        pnlSouth.add(lblTongDoanhThu);

        // Thêm các phần vào Panel chính
        add(pnlNorth, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // NHÓM HÀM GETTER LẤY NGÀY THÁNG
    public java.util.Date getTuNgay() {
        return jdTuNgay.getDate();
    }

    public java.util.Date getDenNgay() {
        return jdDenNgay.getDate();
    }

    // NHÓM HÀM THAO TÁC UI
    public DefaultTableModel getModelThongKe() {
        return modelThongKe;
    }

    public void setTongDoanhThu(double tien) {
        lblTongDoanhThu.setText("TỔNG DOANH THU: " + String.format("%,.0f", tien) + " VNĐ");
    }

    public void setTongSoDon(int soDon) {
        lblTongSoDon.setText("TỔNG SỐ ĐƠN: " + soDon);
    }

    public JPanel getChartPanel() {
        return pnlChart;
    }

    //HÀM GẮN LISTENER
    public void addLocThongKeListener(ActionListener l) {
        btnLoc.addActionListener(l);
    }
}