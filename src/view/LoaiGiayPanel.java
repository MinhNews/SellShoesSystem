package view;

import javax.swing.*;
import java.awt.*;

public class LoaiGiayPanel extends JPanel {
    public LoaiGiayPanel() {
        setLayout(new BorderLayout());
        // Tạm thời để một nhãn thông báo
        JLabel label = new JLabel("Giao diện Quản lý Loại Giày", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }
}