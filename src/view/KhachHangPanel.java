package view;

import javax.swing.*;
import java.awt.*;

public class KhachHangPanel extends JPanel {
    public KhachHangPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Giao diện Quản lý Khách Hàng", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }
}