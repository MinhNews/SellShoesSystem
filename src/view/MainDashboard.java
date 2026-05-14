package view;

import model.NhanVien;
import javax.swing.*;
import controller.BanHangController;
import controller.GiayController;
 ThaiBranch
import controller.KhachHangController;
import controller.LoaiGiayController;

import controller.KhachHangController; 
import controller.LoaiGiayController;
import controller.ThongKeController;
import controller.NhanVienController; // THÊM IMPORT
import controller.HoaDonController;   // THÊM IMPORT

 main
import java.awt.*;

public class MainDashboard extends JFrame {
    private NhanVien currentUser;
    private JPanel mainContentPanel;
    private CardLayout cardLayout;

    public MainDashboard(NhanVien user) {
        this.currentUser = user;

        setTitle("Hệ Thống Quản Lý Bán Giày - Nhân viên: " + currentUser.getHoTen());
        setSize(1300, 750); // Tăng nhẹ kích thước để bảng Master-Detail hiển thị thoải mái
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // --- 1. TẠO MENU BÊN TRÁI (Sidebar) ---
        JPanel sidebarPanel = new JPanel();
ThaiBranch
        // Tăng lên 9 hàng để đủ chỗ cho các tính năng mới của Quest 3
        sidebarPanel.setLayout(new GridLayout(9, 1, 10, 10));

        // Tăng GridLayout lên 10 hàng để đủ chỗ cho tất cả các nút
        sidebarPanel.setLayout(new GridLayout(10, 1, 10, 10)); 
 main
        sidebarPanel.setPreferredSize(new Dimension(200, 600));
        sidebarPanel.setBackground(new Color(245, 245, 245));

 ThaiBranch
        JButton btnLoaiGiay = new JButton("Danh mục Loại Giày");
        JButton btnGiay = new JButton("Quản lý Giày");
        JButton btnBanHang = new JButton("Lập Hóa Đơn (POS)");
        JButton btnLichSuHD = new JButton("Lịch sử Hóa Đơn"); // <-- QUEST 3

        JButton btnLoaiGiay = new JButton("Danh mục Loại Giày"); 
        JButton btnGiay = new JButton("Quản lý Giày");
        JButton btnBanHang = new JButton("Lập Hóa Đơn (POS)"); // Đổi tên biến cho rõ ràng
        JButton btnLichSuHoaDon = new JButton("Lịch Sử Hóa Đơn"); // THÊM NÚT LỊCH SỬ HÓA ĐƠN
 main
        JButton btnKhachHang = new JButton("Khách Hàng");
        
        // Các nút của Admin
        JButton btnNhanVien = new JButton("Quản Lý Nhân Viên"); // THÊM NÚT NHÂN VIÊN
        JButton btnThongKe = new JButton("Thống Kê");
        
        JButton btnDangXuat = new JButton("Đăng Xuất");

        sidebarPanel.add(new JLabel("  MENU CHÍNH", SwingConstants.CENTER));
ThaiBranch
        sidebarPanel.add(btnLoaiGiay);
        sidebarPanel.add(btnGiay);
        sidebarPanel.add(btnBanHang);
        sidebarPanel.add(btnLichSuHD); // THÊM NÚT VÀO SIDEBAR
        sidebarPanel.add(btnKhachHang);


        sidebarPanel.add(btnLoaiGiay); 
        sidebarPanel.add(btnGiay);
        sidebarPanel.add(btnBanHang);
        sidebarPanel.add(btnLichSuHoaDon);
        sidebarPanel.add(btnKhachHang);
        
        // Phân quyền cơ bản: Chỉ Admin (Quyen == 0) mới thấy nút Nhân viên và Thống kê
 main
        if (currentUser.getQuyen() == 0) {
            sidebarPanel.add(btnNhanVien);
            sidebarPanel.add(btnThongKe);
        }

        sidebarPanel.add(new JLabel(""));
        sidebarPanel.add(btnDangXuat);

        // --- 2. TẠO KHU VỰC NỘI DUNG BÊN PHẢI ---
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Chào mừng " + currentUser.getHoTen() + "! Chọn chức năng để bắt đầu.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainContentPanel.add(welcomeLabel, "Welcome");

 ThaiBranch
        // --- 3. KHỞI TẠO CÁC PANEL VÀ CONTROLLER ---

        // 3.1. Loại Giày
        LoaiGiayPanel pnlLoaiGiay = new LoaiGiayPanel();
        new LoaiGiayController(pnlLoaiGiay);

        // 3.2. Quản lý Giày

        // --- KHỞI TẠO CÁC PANEL VÀ CONTROLLER ---
        // 1. Loại Giày
        LoaiGiayPanel pnlLoaiGiay = new LoaiGiayPanel();
        new LoaiGiayController(pnlLoaiGiay);

        // 2. Quản lý Giày
 main
        QuanLyGiayPanel pnlGiay = new QuanLyGiayPanel();
        GiayController giayCtrl = new GiayController(pnlGiay);

 ThaiBranch
        // 3.3. Bán Hàng (Đã nâng cấp Checkbox trong BanHangPanel)
        BanHangPanel pnlBanHang = new BanHangPanel();
        BanHangController banHangCtrl = new BanHangController(pnlBanHang, currentUser);

        // 3.4. Lịch sử Hóa Đơn (Phần mới của Thái)
        HoaDonPanel pnlHoaDon = new HoaDonPanel();
        // Lưu ý: Minh Lead sẽ khởi tạo HoaDonController sau khi bạn bàn giao UI

        // 3.5. Khách Hàng

        // 3. Bán Hàng (POS)
        BanHangPanel pnlBanHang = new BanHangPanel();
        BanHangController banHangCtrl = new BanHangController(pnlBanHang, currentUser);

        // 4. Lịch Sử Hóa Đơn (MỚI)
        HoaDonPanel pnlLichSuHoaDon = new HoaDonPanel();
        new HoaDonController(pnlLichSuHoaDon);

        // 5. Khách Hàng
 main
        KhachHangPanel pnlKhachHang = new KhachHangPanel();
        new KhachHangController(pnlKhachHang);

 ThaiBranch
        // Gắn tất cả vào CardLayout
        mainContentPanel.add(pnlLoaiGiay, "LoaiGiay");
        mainContentPanel.add(pnlGiay, "Giay");
        mainContentPanel.add(pnlBanHang, "BanHang");
        mainContentPanel.add(pnlHoaDon, "LichSuHD"); // ĐĂNG KÝ VÀO CARDLAYOUT

        // 6. Thống Kê
        ThongKePanel pnlThongKe = new ThongKePanel();
        new ThongKeController(pnlThongKe); 

        // 7. Quản lý Nhân Viên (MỚI)
        NhanVienPanel pnlNhanVien = new NhanVienPanel();
        new NhanVienController(pnlNhanVien, currentUser); // Nhớ truyền currentUser vào để chặn tự xóa

        // Gắn vào CardLayout
        mainContentPanel.add(pnlLoaiGiay, "LoaiGiay");
        mainContentPanel.add(pnlGiay, "Giay");
        mainContentPanel.add(pnlBanHang, "BanHang");
        mainContentPanel.add(pnlLichSuHoaDon, "LichSuHoaDon"); // Gắn Lịch Sử HĐ
 main
        mainContentPanel.add(pnlKhachHang, "KhachHang");
        mainContentPanel.add(pnlThongKe, "ThongKe");
        mainContentPanel.add(pnlNhanVien, "NhanVien"); // Gắn Nhân Viên

        // --- 4. XỬ LÝ SỰ KIỆN CHUYỂN TRANG ---
        btnLoaiGiay.addActionListener(e -> cardLayout.show(mainContentPanel, "LoaiGiay"));

        btnGiay.addActionListener(e -> {
 ThaiBranch
            giayCtrl.refreshLoaiGiay();

            giayCtrl.refreshLoaiGiay(); 
 main
            cardLayout.show(mainContentPanel, "Giay");
        });

        btnBanHang.addActionListener(e -> {
 ThaiBranch
            banHangCtrl.loadKhoGiay();
            cardLayout.show(mainContentPanel, "BanHang");
        });

        btnLichSuHD.addActionListener(e -> cardLayout.show(mainContentPanel, "LichSuHD")); // SỰ KIỆN MỚI

            banHangCtrl.loadKhoGiay(); 
            cardLayout.show(mainContentPanel, "BanHang");
        });

        // Bắt sự kiện cho các tab mới
        btnLichSuHoaDon.addActionListener(e -> cardLayout.show(mainContentPanel, "LichSuHoaDon"));
 main
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

        add(sidebarPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }
}