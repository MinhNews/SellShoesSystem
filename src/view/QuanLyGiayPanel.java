package view;

import javax.swing.*;
import java.awt.*;

public class QuanLyGiayPanel extends JPanel {
    public QuanLyGiayPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Giao diện Quản lý Danh mục Giày", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }
}