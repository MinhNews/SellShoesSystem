import view.LoginView;
import controller.LoginController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf; // Import FlatLaf

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); 
        } catch (Exception ex) {
            System.err.println("Không thể khởi tạo giao diện FlatLaf!");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView view = new LoginView();
                new LoginController(view);
                view.setVisible(true);
            }
        });
    }
}