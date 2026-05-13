package view;

import javax.swing.*;
import java.awt.*;

public class BanHangPanel extends JPanel {
    public BanHangPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Giao diện Lập Hóa Đơn Bán Hàng", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }
}