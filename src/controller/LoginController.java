package controller;

import dao.NhanVienDAO;
import model.NhanVien;
import view.LoginView;
import view.MainDashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private NhanVienDAO nhanVienDAO;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.nhanVienDAO = new NhanVienDAO();

        // Gắn sự kiện click cho nút Đăng nhập bên View
        this.loginView.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String user = loginView.getUsername();
        String pass = loginView.getPassword();

        NhanVien nv = nhanVienDAO.checkLogin(user, pass);

        if (nv != null) {
            loginView.showMessage("Đăng nhập thành công!");
            loginView.dispose(); // Đóng form login
            // Mở form chính
            MainDashboard mainView = new MainDashboard(nv);
            mainView.setVisible(true);
        } else {
            loginView.showMessage("Sai tài khoản hoặc mật khẩu!");
        }
    }
}