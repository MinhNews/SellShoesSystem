package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Đăng nhập hệ thống");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(30, 30, 80, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 30, 180, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 180, 25);
        add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(120, 110, 100, 30);
        add(btnLogin);
    }

    // --- CÁC HÀM ĐỂ CONTROLLER LẤY DỮ LIỆU VÀ GẮN SỰ KIỆN ---
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