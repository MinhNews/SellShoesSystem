package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoaiGiayPanel extends JPanel {
    private JTextField txtId, txtTenLoai;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private JTable tblLoaiGiay;
    private DefaultTableModel modelLoaiGiay;

    public LoaiGiayPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- KHU VỰC TOP: FORM NHẬP LIỆU ---
        JPanel pnlNorth = new JPanel(new GridLayout(1, 4, 10, 10));
        pnlNorth.setBorder(BorderFactory.createTitledBorder("Thông tin Danh Mục Loại Giày"));
        
        pnlNorth.add(new JLabel("ID (Tự động):"));
        txtId = new JTextField(); 
        txtId.setEditable(false); 
        pnlNorth.add(txtId);
        
        pnlNorth.add(new JLabel("Tên Loại Giày:"));
        txtTenLoai = new JTextField(); 
        pnlNorth.add(txtTenLoai);

        // --- KHU VỰC CENTER: BẢNG DỮ LIỆU ---
        modelLoaiGiay = new DefaultTableModel(new String[]{"ID Loại", "Tên Loại Giày"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblLoaiGiay = new JTable(modelLoaiGiay);
        tblLoaiGiay.setRowHeight(25);

        // --- KHU VỰC BOTTOM: NÚT CHỨC NĂNG ---
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnThem = new JButton("Thêm Mới");
        btnSua = new JButton("Cập Nhật");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm Mới");
        
        JButton[] btns = {btnThem, btnSua, btnXoa, btnLamMoi};
        for (JButton btn : btns) { btn.putClientProperty("JButton.buttonType", "roundRect"); }
        btnThem.setBackground(new Color(40, 167, 69)); btnThem.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(23, 162, 184)); btnSua.setForeground(Color.WHITE);
        btnXoa.setBackground(new Color(220, 53, 69)); btnXoa.setForeground(Color.WHITE);

        pnlSouth.add(btnLamMoi); pnlSouth.add(btnThem); pnlSouth.add(btnSua); pnlSouth.add(btnXoa);

        add(pnlNorth, BorderLayout.NORTH);
        add(new JScrollPane(tblLoaiGiay), BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- GETTER ---
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

    // --- LISTENER ---
    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}