import view.LoginView;
import controller.LoginController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf; // Import FlatLaf

public class Main {
    public static void main(String[] args) {
        // 1. KÍCH HOẠT GIAO DIỆN FLATLAF (Phải làm trước khi vẽ UI)
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Nếu thích màu tối ngầu ngầu thì dùng: new com.formdev.flatlaf.FlatDarkLaf()
        } catch (Exception ex) {
            System.err.println("Không thể khởi tạo giao diện FlatLaf!");
        }
        // 2. KHỞI CHẠY LUỒNG ĐĂNG NHẬP
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView view = new LoginView();
                // Nạp View vào Controller để Controller quản lý
                new LoginController(view);
                view.setVisible(true);
            }
        });
    }
}