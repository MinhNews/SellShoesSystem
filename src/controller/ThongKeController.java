package controller;

import dao.ThongKeDAO;
import view.ThongKePanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class ThongKeController {
    private ThongKePanel view;
    private ThongKeDAO dao;

    public ThongKeController(ThongKePanel view) {
        this.view = view;
        this.dao = new ThongKeDAO();

        this.view.addLocThongKeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienThongKe();
            }
        });
    }

    private void thucHienThongKe() {
        Date tuNgay = view.getTuNgay();
        Date denNgay = view.getDenNgay();

        // 1. Kiểm tra đầu vào
        if (tuNgay == null || denNgay == null) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn đầy đủ khoảng thời gian!");
            return;
        }

        if (tuNgay.after(denNgay)) {
            JOptionPane.showMessageDialog(view, "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            return;
        }

        // 2. Lấy dữ liệu từ DAO và cập nhật các nhãn (Label)
        double tongDoanhThu = dao.getTongDoanhThu(tuNgay, denNgay);
        int tongSoDon = dao.getTongSoDonHang(tuNgay, denNgay);

        view.setTongDoanhThu(tongDoanhThu);
        view.setTongSoDon(tongSoDon);

        // 3. Xử lý vẽ biểu đồ Top 5 Giày bán chạy
        veBieuDoTopGiay(tuNgay, denNgay);

    }

    private void veBieuDoTopGiay(Date tuNgay, Date denNgay) {
        // Lấy dữ liệu Top 5 từ DAO
        List<Object[]> listTop5 = dao.getTop5GiayBanChay(tuNgay, denNgay);

        // Tạo tập dữ liệu cho JFreeChart
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Object[] obj : listTop5) {
            dataset.setValue(obj[0].toString(), (Number) obj[1]);
        }

        // Tạo biểu đồ hình tròn (Pie Chart)
        JFreeChart chart = ChartFactory.createPieChart(
                "Top 5 Giày Bán Chạy Nhất", // Tiêu đề
                dataset,                    // Dữ liệu
                true,                       // Hiện chú thích (Legend)
                true,                       // Hiện tooltip
                false                       // Không dùng URL
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));

        view.getChartPanel().removeAll();
        view.getChartPanel().add(chartPanel, BorderLayout.CENTER);
        view.getChartPanel().validate();
        view.getChartPanel().repaint();
    }
}