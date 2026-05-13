package view;

import model.NhanVien;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.BanHangController;
import controller.GiayController;
import controller.KhachHangController;
import controller.LoaiGiayController;
import java.awt.*;

public class MainDashboard extends JFrame {
    private NhanVien currentUser;
    private JPanel mainContentPanel;
    private CardLayout cardLayout;

    public MainDashboard(NhanVien user) {
        this.currentUser = user;

        setTitle("Hệ Thống Quản Lý Bán Giày - Nhân viên: " + currentUser.getHoTen());
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // --- 1. TẠO MENU BÊN TRÁI (Sidebar) ---
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(10, 1, 5, 5)); // Tăng lên 10 để các nút không bị quá to
        sidebarPanel.setPreferredSize(new Dimension(230, 700));

        // Màu Sidebar xám đậm sang trọng
        sidebarPanel.setBackground(new Color(245, 246, 250));
        sidebarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnLoaiGiay = new JButton(" Danh mục Loại Giày");
        JButton btnGiay = new JButton(" Quản lý Giày");
        JButton btnHoaDon = new JButton(" Lập Hóa Đơn");
        JButton btnKhachHang = new JButton(" Khách Hàng");
        JButton btnThongKe = new JButton(" Thống Kê");
        JButton btnDangXuat = new JButton(" Đăng Xuất");

        // --- LÀM ĐẸP NÚT BẤM VỚI FLATLAF ---
        JButton[] menuButtons = {btnLoaiGiay, btnGiay, btnHoaDon, btnKhachHang, btnThongKe, btnDangXuat};
        for (JButton btn : menuButtons) {
            btn.putClientProperty("JButton.buttonType", "menu"); // Kiểu nút phẳng
            btn.setHorizontalAlignment(SwingConstants.LEFT); // Căn lề trái
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setPreferredSize(new Dimension(200, 45));
        }

        JLabel lblMenuTitle = new JLabel("  MENU CHÍNH");
        lblMenuTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblMenuTitle.setForeground(new Color(45, 52, 54));

        sidebarPanel.add(lblMenuTitle);
        sidebarPanel.add(btnLoaiGiay);
        sidebarPanel.add(btnGiay);
        sidebarPanel.add(btnHoaDon);
        sidebarPanel.add(btnKhachHang);

        if (currentUser.getQuyen() == 0) {
            sidebarPanel.add(btnThongKe);
        }

        // Khoảng trống đẩy nút Đăng xuất xuống dưới
        sidebarPanel.add(new JLabel(""));
        sidebarPanel.add(new JLabel(""));
        sidebarPanel.add(btnDangXuat);

        // Nút đăng xuất cho màu đỏ nhẹ để phân biệt
        btnDangXuat.setForeground(new Color(231, 76, 60));

        // --- 2. TẠO KHU VỰC NỘI DUNG BÊN PHẢI ---
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Chọn một chức năng bên Menu để bắt đầu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        mainContentPanel.add(welcomeLabel, "Welcome");

        // --- 3. KHỞI TẠO CÁC PANEL VÀ CONTROLLER ---
        LoaiGiayPanel pnlLoaiGiay = new LoaiGiayPanel();
        new LoaiGiayController(pnlLoaiGiay);

        QuanLyGiayPanel pnlGiay = new QuanLyGiayPanel();
        GiayController giayCtrl = new GiayController(pnlGiay);

        // Sử dụng BanHangPanel & KhachHangPanel Thái vừa hoàn thành Quest 2
        BanHangPanel pnlBanHang = new BanHangPanel();
        BanHangController banHangCtrl = new BanHangController(pnlBanHang, currentUser);

        KhachHangPanel pnlKhachHang = new KhachHangPanel();
        new KhachHangController(pnlKhachHang);

        mainContentPanel.add(pnlLoaiGiay, "LoaiGiay");
        mainContentPanel.add(pnlGiay, "Giay");
        mainContentPanel.add(pnlBanHang, "BanHang");
        mainContentPanel.add(pnlKhachHang, "KhachHang");

        // --- 4. XỬ LÝ SỰ KIỆN CHUYỂN TRANG ---
        btnLoaiGiay.addActionListener(e -> cardLayout.show(mainContentPanel, "LoaiGiay"));

        btnGiay.addActionListener(e -> {
            giayCtrl.refreshLoaiGiay();
            cardLayout.show(mainContentPanel, "Giay");
        });

        btnHoaDon.addActionListener(e -> {
            banHangCtrl.loadKhoGiay();
            cardLayout.show(mainContentPanel, "BanHang");
        });

        btnKhachHang.addActionListener(e -> cardLayout.show(mainContentPanel, "KhachHang"));

        btnDangXuat.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                view.LoginView login = new view.LoginView();
                new controller.LoginController(login);
                login.setVisible(true);
            }
        });

        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }
}