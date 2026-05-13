package controller;

import dao.NhanVienDAO;
import model.NhanVien;
import view.NhanVienPanel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NhanVienController {
    private NhanVienPanel view;
    private NhanVienDAO dao;
    private NhanVien currentUser; // Người đang đăng nhập (để bảo vệ khỏi tự xóa)

    public NhanVienController(NhanVienPanel view, NhanVien currentUser) {
        this.view = view;
        this.dao = new NhanVienDAO();
        this.currentUser = currentUser;

        // Load dữ liệu lần đầu
        loadDataToTable();

        // Gắn sự kiện nút bấm
        this.view.addThemListener(new AddListener());
        this.view.addSuaListener(new EditListener());
        this.view.addXoaListener(new DeleteListener());
        this.view.addLamMoiListener(e -> { view.clearForm(); loadDataToTable(); });

        // Xử lý sự kiện CLICK CHUỘT vào Bảng -> Đổ dữ liệu lên Form
        this.view.getTblNhanVien().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblNhanVien().getSelectedRow();
                if (row >= 0) {
                    // Cột 4 là Vai trò (String "Admin" hoặc "Nhân viên"), ta ép về Index cho ComboBox
                    String vaiTro = view.getTblNhanVien().getValueAt(row, 4).toString();
                    int quyenIndex = vaiTro.equals("Admin") ? 0 : 1;

                    view.setFormData(
                            view.getTblNhanVien().getValueAt(row, 0).toString(), // ID
                            view.getTblNhanVien().getValueAt(row, 1).toString(), // Username
                            view.getTblNhanVien().getValueAt(row, 2).toString(), // Họ tên
                            view.getTblNhanVien().getValueAt(row, 3).toString(), // ĐT
                            quyenIndex // Index quyền
                    );
                }
            }
        });
    }

    private void loadDataToTable() {
        DefaultTableModel model = view.getModelNhanVien();
        model.setRowCount(0);
        List<NhanVien> list = dao.getAll();
        for (NhanVien nv : list) {
            String vaiTro = (nv.getQuyen() == 0) ? "Admin" : "Nhân viên";
            model.addRow(new Object[]{nv.getId(), nv.getUsername(), nv.getHoTen(), nv.getDienThoai(), vaiTro});
        }
    }

    // --- CÁC CLASS XỬ LÝ SỰ KIỆN NÚT BẤM ---

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getUsername().isEmpty() || view.getPassword().isEmpty() || view.getHoTen().isEmpty()) {
                view.showMessage("Tên đăng nhập, Mật khẩu và Họ tên không được để trống!"); return;
            }
            
            NhanVien nv = new NhanVien();
            nv.setUsername(view.getUsername()); 
            nv.setPassword(view.getPassword());
            nv.setHoTen(view.getHoTen()); 
            nv.setDienThoai(view.getDienThoai());
            nv.setQuyen(view.getQuyen());

            if (dao.add(nv)) { 
                view.showMessage("Thêm nhân viên thành công!"); 
                view.clearForm(); 
                loadDataToTable();
            } else { 
                view.showMessage("Thêm thất bại! Có thể Tên đăng nhập đã bị trùng."); 
            }
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) { view.showMessage("Chọn nhân viên trên bảng để sửa!"); return; }
            
            NhanVien nv = new NhanVien();
            nv.setId(Integer.parseInt(view.getId()));
            nv.setUsername(view.getUsername());
            nv.setHoTen(view.getHoTen()); 
            nv.setDienThoai(view.getDienThoai()); 
            nv.setQuyen(view.getQuyen());
            
            // Xử lý mật khẩu: Nếu user để "******" nghĩa là không muốn đổi mật khẩu
            String pass = view.getPassword();
            if (!pass.equals("******") && !pass.isEmpty()) {
                nv.setPassword(pass);
            } else {
                nv.setPassword(""); // Chuỗi rỗng để DAO biết mà không cập nhật cột Password
            }

            if (dao.update(nv)) { 
                view.showMessage("Cập nhật thành công!"); 
                view.clearForm(); 
                loadDataToTable();
            } else { 
                view.showMessage("Cập nhật thất bại!"); 
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) { view.showMessage("Chọn nhân viên để xóa!"); return; }
            
            int idXoa = Integer.parseInt(view.getId());
            
            // BẢO VỆ: Không cho Admin tự sát
            if (idXoa == currentUser.getId()) {
                view.showMessage("Lỗi: Bạn không thể tự xóa tài khoản đang đăng nhập của chính mình!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.delete(idXoa)) { 
                    view.showMessage("Xóa thành công!"); 
                    view.clearForm(); 
                    loadDataToTable();
                } else { 
                    view.showMessage("Không thể xóa nhân viên này vì đã có dữ liệu Hóa Đơn trên hệ thống!"); 
                }
            }
        }
    }
}