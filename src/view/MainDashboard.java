package view;

import model.NhanVien;
import javax.swing.*;

import controller.BanHangController;
import controller.GiayController;
import controller.KhachHangController; 
import controller.LoaiGiayController;
import controller.ThongKeController;
import controller.NhanVienController; 
import controller.HoaDonController;   

import java.awt.*;

public class MainDashboard extends JFrame {
    private NhanVien currentUser;
    private JPanel mainContentPanel; 
    private CardLayout cardLayout; 

    public MainDashboard(NhanVien user) {
        this.currentUser = user;
        
        // Kiểm tra quyền: 0 là Admin, còn lại là Nhân viên
        String chucVu = (currentUser.getQuyen() == 0) ? "Admin" : "Nhân viên";
        setTitle("Hệ Thống Quản Lý Bán Giày - " + chucVu + ": " + currentUser.getHoTen());
        setSize(1200, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        // --- TẠO MENU BÊN TRÁI (Sidebar) - LÊN ĐỒ GIAO DIỆN MỚI ---
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(10, 1, 0, 5)); // Chỉnh lại gap cho mượt
        sidebarPanel.setPreferredSize(new Dimension(220, 600));
        sidebarPanel.setBackground(new Color(30, 41, 59)); // Màu nền Slate-800 cực sang

        // Tiêu đề Menu
        JLabel lblMenuTitle = new JLabel("  MENU CHÍNH", SwingConstants.CENTER);
        lblMenuTitle.setForeground(Color.WHITE);
        lblMenuTitle.setFont(new Font("Arial", Font.BOLD, 18));
        sidebarPanel.add(lblMenuTitle);

        // Khởi tạo các nút
        JButton btnLoaiGiay = new JButton("Danh mục Loại Giày"); 
        JButton btnGiay = new JButton("Quản lý Giày");
        JButton btnBanHang = new JButton("Lập Hóa Đơn"); 
        JButton btnLichSuHoaDon = new JButton("Lịch Sử Hóa Đơn"); 
        JButton btnKhachHang = new JButton("Khách Hàng");
        
        // Gắn style đồng bộ cho các nút thường
        JButton[] normalButtons = {btnLoaiGiay, btnGiay, btnBanHang, btnLichSuHoaDon, btnKhachHang};
        for (JButton btn : normalButtons) {
            styleMenuButton(btn);
            sidebarPanel.add(btn);
        }
        
        // Các nút của Admin
        JButton btnNhanVien = new JButton("Quản Lý Nhân Viên"); 
        JButton btnThongKe = new JButton("Thống Kê");
        if (currentUser.getQuyen() == 0) {
            styleMenuButton(btnNhanVien);
            styleMenuButton(btnThongKe);
            sidebarPanel.add(btnNhanVien);
            sidebarPanel.add(btnThongKe);
        }
        
        sidebarPanel.add(new JLabel("")); // Khoảng trống đệm
        
        // Nút Đăng xuất (Riêng biệt màu đỏ nổi bật)
        JButton btnDangXuat = new JButton("Đăng Xuất");
        btnDangXuat.setBackground(new Color(220, 53, 69)); // Đỏ Danger
        btnDangXuat.setForeground(Color.WHITE);
        btnDangXuat.setFont(new Font("Arial", Font.BOLD, 14));
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setBorderPainted(false);
        btnDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebarPanel.add(btnDangXuat);

        // --- TẠO KHU VỰC NỘI DUNG BÊN PHẢI ---
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout); 
        mainContentPanel.setBackground(new Color(248, 250, 252)); // Nền trắng xám nhạt dịu mắt
        
        // Trang chào mừng
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>Chào mừng trở lại, <b>" + currentUser.getHoTen() + "</b>!<br><br>Chọn một chức năng bên Menu để bắt đầu làm việc.</div></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setForeground(new Color(71, 85, 105));
        mainContentPanel.add(welcomeLabel, "Welcome");

        // --- KHỞI TẠO CÁC PANEL VÀ CONTROLLER ---
        LoaiGiayPanel pnlLoaiGiay = new LoaiGiayPanel();
        new LoaiGiayController(pnlLoaiGiay);

        QuanLyGiayPanel pnlGiay = new QuanLyGiayPanel();
        GiayController giayCtrl = new GiayController(pnlGiay); 

        BanHangPanel pnlBanHang = new BanHangPanel();
        BanHangController banHangCtrl = new BanHangController(pnlBanHang, currentUser);

        HoaDonPanel pnlLichSuHoaDon = new HoaDonPanel();
        new HoaDonController(pnlLichSuHoaDon);

        KhachHangPanel pnlKhachHang = new KhachHangPanel();
        new KhachHangController(pnlKhachHang); 

        ThongKePanel pnlThongKe = new ThongKePanel();
        new ThongKeController(pnlThongKe); 

        NhanVienPanel pnlNhanVien = new NhanVienPanel();
        new NhanVienController(pnlNhanVien, currentUser); 

        // Gắn vào CardLayout
        mainContentPanel.add(pnlLoaiGiay, "LoaiGiay");
        mainContentPanel.add(pnlGiay, "Giay");
        mainContentPanel.add(pnlBanHang, "BanHang");
        mainContentPanel.add(pnlLichSuHoaDon, "LichSuHoaDon"); 
        mainContentPanel.add(pnlKhachHang, "KhachHang");
        mainContentPanel.add(pnlThongKe, "ThongKe");
        mainContentPanel.add(pnlNhanVien, "NhanVien"); 

        // --- XỬ LÝ SỰ KIỆN BẤM NÚT ĐỂ CHUYỂN TRANG ---
        btnLoaiGiay.addActionListener(e -> cardLayout.show(mainContentPanel, "LoaiGiay"));

        btnGiay.addActionListener(e -> {
            giayCtrl.refreshLoaiGiay(); 
            cardLayout.show(mainContentPanel, "Giay");
        });

        btnBanHang.addActionListener(e -> {
            banHangCtrl.loadKhoGiay(); 
            cardLayout.show(mainContentPanel, "BanHang");
        });

        btnLichSuHoaDon.addActionListener(e -> cardLayout.show(mainContentPanel, "LichSuHoaDon"));
        btnKhachHang.addActionListener(e -> cardLayout.show(mainContentPanel, "KhachHang"));
        btnThongKe.addActionListener(e -> cardLayout.show(mainContentPanel, "ThongKe"));
        btnNhanVien.addActionListener(e -> cardLayout.show(mainContentPanel, "NhanVien"));

        btnDangXuat.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); 
                view.LoginView login = new view.LoginView();
                new controller.LoginController(login);
                login.setVisible(true);
            }
        });

        // Gắn 2 mảng vào Khung chính
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }

    // Hàm tiện ích để làm đẹp các nút menu
    private void styleMenuButton(JButton btn) {
        btn.setBackground(new Color(30, 41, 59)); // Nền tệp với Sidebar
        btn.setForeground(new Color(226, 232, 240)); // Chữ xám trắng
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT); // Căn chữ sang trái nhìn chuyên nghiệp hơn
        btn.setFocusPainted(false); // Bỏ khung viền xấu xí khi click
        btn.setBorderPainted(false); // Bỏ viền nổi
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Trỏ chuột biến thành bàn tay
    }
}