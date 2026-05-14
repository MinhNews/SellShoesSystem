package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoaiGiayPanel extends JPanel {
    private JTextField txtId, txtTenLoai;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private JTable tblLoaiGiay;
    private DefaultTableModel modelLoaiGiay;

    // --- ĐỊNH NGHĨA MÀU SẮC & FONT CHUẨN UI MỚI ---
    private Color primaryText = new Color(30, 41, 59);   // Chữ xám đậm
    private Color borderColor = new Color(226, 232, 240); // Viền xám nhạt
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

    public LoaiGiayPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE); // Nền trắng toàn tập
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- KHU VỰC TOP: FORM NHẬP LIỆU ---
        JPanel pnlNorth = new JPanel(new GridLayout(1, 4, 15, 10));
        pnlNorth.setBackground(Color.WHITE);
        
        // Custom TitledBorder hiện đại
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(borderColor, 1, true),
            "Thông tin Danh Mục Loại Giày",
            TitledBorder.LEFT, TitledBorder.TOP,
            boldFont, primaryText
        );
        pnlNorth.setBorder(BorderFactory.createCompoundBorder(
            titledBorder, 
            BorderFactory.createEmptyBorder(10, 15, 15, 15) // Padding bên trong khung
        ));
        
        JLabel lblId = new JLabel("ID (Tự động):"); lblId.setFont(mainFont);
        pnlNorth.add(lblId);
        
        txtId = new JTextField(); 
        txtId.setFont(mainFont);
        txtId.setEditable(false); 
        txtId.setBackground(new Color(248, 250, 252)); // Nền hơi xám cho ô không được sửa
        pnlNorth.add(txtId);
        
        JLabel lblTen = new JLabel("Tên Loại Giày:"); lblTen.setFont(mainFont);
        pnlNorth.add(lblTen);
        
        txtTenLoai = new JTextField(); 
        txtTenLoai.setFont(mainFont);
        pnlNorth.add(txtTenLoai);

        // --- KHU VỰC CENTER: BẢNG DỮ LIỆU ---
        modelLoaiGiay = new DefaultTableModel(new String[]{"ID Loại", "Tên Loại Giày"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblLoaiGiay = new JTable(modelLoaiGiay);
        
        // Làm đẹp Table
        tblLoaiGiay.setFont(mainFont);
        tblLoaiGiay.setRowHeight(30); // Dãn dòng cho dễ đọc
        tblLoaiGiay.setSelectionBackground(new Color(226, 232, 240));
        tblLoaiGiay.setSelectionForeground(Color.BLACK);
        tblLoaiGiay.setShowVerticalLines(false); // Bỏ vạch kẻ dọc nhìn cho Web-style
        tblLoaiGiay.setGridColor(borderColor);
        
        // Làm đẹp Header của Table
        tblLoaiGiay.getTableHeader().setFont(boldFont);
        tblLoaiGiay.getTableHeader().setBackground(new Color(241, 245, 249));
        tblLoaiGiay.getTableHeader().setForeground(primaryText);
        tblLoaiGiay.getTableHeader().setPreferredSize(new Dimension(100, 35)); // Header cao hơn tí
        tblLoaiGiay.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));

        JScrollPane scrollPane = new JScrollPane(tblLoaiGiay);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor));

        // --- KHU VỰC BOTTOM: NÚT CHỨC NĂNG ---
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pnlSouth.setBackground(Color.WHITE);
        
        btnLamMoi = new JButton("Làm Mới");
        btnThem = new JButton("Thêm Mới");
        btnSua = new JButton("Cập Nhật");
        btnXoa = new JButton("Xóa");
        
        JButton[] btns = {btnLamMoi, btnThem, btnSua, btnXoa};
        for (JButton btn : btns) { 
            btn.setFont(boldFont);
            btn.putClientProperty("JButton.buttonType", "roundRect"); 
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
            pnlSouth.add(btn);
        }
        
        // Set màu đặc trưng
        btnThem.setBackground(new Color(16, 185, 129)); btnThem.setForeground(Color.WHITE); // Emerald Green
        btnSua.setBackground(new Color(14, 165, 233)); btnSua.setForeground(Color.WHITE);   // Sky Blue
        btnXoa.setBackground(new Color(239, 68, 68)); btnXoa.setForeground(Color.WHITE);     // Red
        btnLamMoi.setBackground(Color.WHITE); btnLamMoi.setForeground(primaryText);

        add(pnlNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- CÁC HÀM GETTER / THAO TÁC / LISTENER GIỮ NGUYÊN BẢN GỐC ---
    public JTable getTblLoaiGiay() { return tblLoaiGiay; }
    public DefaultTableModel getModelLoaiGiay() { return modelLoaiGiay; }
    public String getId() { return txtId.getText().trim(); }
    public String getTenLoai() { return txtTenLoai.getText().trim(); }

    public void setFormData(String id, String tenLoai) {
        txtId.setText(id);
        txtTenLoai.setText(tenLoai);
    }

    public void clearForm() {
        txtId.setText("");
        txtTenLoai.setText("");
    }

    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}