package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);    // Slate 800
    private Color primaryColor = new Color(14, 165, 233); // Sky Blue
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public LoginView() {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 360); // Tăng kích thước cửa sổ cho thoáng
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Khóa kích thước để form luôn đẹp
        setLayout(new BorderLayout());

        // Nền chính chứa form
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(BorderFactory.createEmptyBorder(25, 40, 30, 40));

        // Header - Tiêu đề
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(primaryText);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSubTitle = new JLabel("Hệ Thống Quản Lý Bán Giày");
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubTitle.setForeground(new Color(100, 116, 139)); // Slate 500
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Box Username
        JPanel pnlUser = new JPanel(new BorderLayout(0, 5));
        pnlUser.setBackground(Color.WHITE);
        pnlUser.setMaximumSize(new Dimension(400, 60)); // Giới hạn chiều cao
        
        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setFont(boldFont);
        lblUser.setForeground(primaryText);
        
        txtUsername = new JTextField();
        txtUsername.setFont(mainFont);
        txtUsername.setPreferredSize(new Dimension(0, 35)); // Input cao hơn
        txtUsername.putClientProperty("JTextField.placeholderText", "Nhập username...");
        
        pnlUser.add(lblUser, BorderLayout.NORTH);
        pnlUser.add(txtUsername, BorderLayout.CENTER);

        // Box Password
        JPanel pnlPass = new JPanel(new BorderLayout(0, 5));
        pnlPass.setBackground(Color.WHITE);
        pnlPass.setMaximumSize(new Dimension(400, 60));
        
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(boldFont);
        lblPass.setForeground(primaryText);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(mainFont);
        txtPassword.setPreferredSize(new Dimension(0, 35));
        txtPassword.putClientProperty("JTextField.placeholderText", "Nhập password...");
        
        pnlPass.add(lblPass, BorderLayout.NORTH);
        pnlPass.add(txtPassword, BorderLayout.CENTER);

        // Nút Đăng nhập
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setBackground(primaryColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(400, 45)); // Nút dãn đều 2 bên
        
        // --- RÁP CÁC THÀNH PHẦN LẠI VỚI NHAU ---
        pnlMain.add(lblTitle);
        pnlMain.add(Box.createVerticalStrut(5));  // Khoảng cách nhỏ
        pnlMain.add(lblSubTitle);
        pnlMain.add(Box.createVerticalStrut(25)); // Khoảng cách lớn
        pnlMain.add(pnlUser);
        pnlMain.add(Box.createVerticalStrut(15));
        pnlMain.add(pnlPass);
        pnlMain.add(Box.createVerticalStrut(25));
        pnlMain.add(btnLogin);

        add(pnlMain, BorderLayout.CENTER);
        
        // Focus sẵn vào ô Username khi vừa mở app
        SwingUtilities.invokeLater(() -> txtUsername.requestFocus());
    }

    // ==========================================
    // --- CÁC HÀM TRONG HỢP ĐỒNG (GIỮ NGUYÊN) ---
    // ==========================================
    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}