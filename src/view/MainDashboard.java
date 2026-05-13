package view;

import model.NhanVien;
import javax.swing.*;

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
        setSize(1200, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        // --- TẠO MENU BÊN TRÁI (Sidebar) ---
        JPanel sidebarPanel = new JPanel();
        // Tăng GridLayout lên 8 hàng để đủ chỗ cho nút Loại Giày
        sidebarPanel.setLayout(new GridLayout(8, 1, 10, 10)); 
        sidebarPanel.setPreferredSize(new Dimension(200, 600));
        sidebarPanel.setBackground(Color.LIGHT_GRAY);

        JButton btnLoaiGiay = new JButton("Danh mục Loại Giày"); // THÊM NÚT LOẠI GIÀY
        JButton btnGiay = new JButton("Quản lý Giày");
        JButton btnHoaDon = new JButton("Lập Hóa Đơn"); 
        JButton btnKhachHang = new JButton("Khách Hàng");
        JButton btnThongKe = new JButton("Thống Kê");
        JButton btnDangXuat = new JButton("Đăng Xuất");

        sidebarPanel.add(new JLabel("  MENU CHÍNH", SwingConstants.CENTER));
        sidebarPanel.add(btnLoaiGiay); // GẮN NÚT VÀO SIDEBAR
        sidebarPanel.add(btnGiay);
        sidebarPanel.add(btnHoaDon);
        sidebarPanel.add(btnKhachHang);
        
        // Phân quyền cơ bản: Chỉ Admin (Quyen == 0) mới thấy nút Thống kê
        if (currentUser.getQuyen() == 0) {
            sidebarPanel.add(btnThongKe);
        }
        
        sidebarPanel.add(new JLabel("")); 
        sidebarPanel.add(btnDangXuat);

        // --- TẠO KHU VỰC NỘI DUNG BÊN PHẢI ---
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout); 
        mainContentPanel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Chọn một chức năng bên Menu để bắt đầu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainContentPanel.add(welcomeLabel, "Welcome");

        // --- KHỞI TẠO CÁC PANEL VÀ CONTROLLER ---
        // 1. Khởi tạo Loại Giày
        LoaiGiayPanel pnlLoaiGiay = new LoaiGiayPanel();
        new LoaiGiayController(pnlLoaiGiay);

        // 2. Khởi tạo Quản lý Giày (Gán vào biến giayCtrl để lát gọi refresh)
        QuanLyGiayPanel pnlGiay = new QuanLyGiayPanel();
        GiayController giayCtrl = new GiayController(pnlGiay); 

        // 3. Khởi tạo Bán Hàng
        BanHangPanel pnlBanHang = new BanHangPanel();
        BanHangController banHangCtrl = new BanHangController(pnlBanHang, currentUser);

        // 4. Khởi tạo Khách Hàng
        KhachHangPanel pnlKhachHang = new KhachHangPanel();
        new KhachHangController(pnlKhachHang); 

        // Gắn vào CardLayout với các từ khóa nhận diện
        mainContentPanel.add(pnlLoaiGiay, "LoaiGiay"); // Gắn Loại Giày
        mainContentPanel.add(pnlGiay, "Giay");
        mainContentPanel.add(pnlBanHang, "BanHang");
        mainContentPanel.add(pnlKhachHang, "KhachHang");

        // --- XỬ LÝ SỰ KIỆN BẤM NÚT ĐỂ CHUYỂN TRANG ---
        btnLoaiGiay.addActionListener(e -> cardLayout.show(mainContentPanel, "LoaiGiay"));

        btnGiay.addActionListener(e -> {
            giayCtrl.refreshLoaiGiay(); // <-- F5 lại ComboBox Loại Giày trước khi show
            cardLayout.show(mainContentPanel, "Giay");
        });

        btnHoaDon.addActionListener(e -> {
            banHangCtrl.loadKhoGiay(); // <-- F5 lại kho giày trước khi show
            cardLayout.show(mainContentPanel, "BanHang");
        });

        btnKhachHang.addActionListener(e -> cardLayout.show(mainContentPanel, "KhachHang"));

        // Xử lý Đăng xuất
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
}