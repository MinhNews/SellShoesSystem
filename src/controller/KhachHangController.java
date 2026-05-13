package controller;

import dao.KhachHangDAO;
import model.KhachHang;
import view.KhachHangPanel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class KhachHangController {
    private KhachHangPanel view;
    private KhachHangDAO khachHangDAO;

    public KhachHangController(KhachHangPanel view) {
        this.view = view;
        this.khachHangDAO = new KhachHangDAO();

        // Load dữ liệu lần đầu
        loadDataToTable();

        // Gắn sự kiện nút bấm
        this.view.addThemListener(new AddListener());
        this.view.addSuaListener(new EditListener());
        this.view.addXoaListener(new DeleteListener());
        this.view.addTimKiemListener(new SearchListener());
        this.view.addLamMoiListener(e -> {
            view.clearForm();
            loadDataToTable();
        });

        // Xử lý sự kiện CLICK CHUỘT vào Bảng -> Đổ dữ liệu lên Form
        this.view.getTblKhachHang().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblKhachHang().getSelectedRow();
                if (row >= 0) {
                    view.setFormData(
                            view.getTblKhachHang().getValueAt(row, 0).toString(),
                            view.getTblKhachHang().getValueAt(row, 1).toString(),
                            view.getTblKhachHang().getValueAt(row, 2).toString(),
                            view.getTblKhachHang().getValueAt(row, 3).toString()
                    );
                }
            }
        });
    }

    private void loadDataToTable() {
        DefaultTableModel model = view.getModelKhachHang();
        model.setRowCount(0); // Xóa sạch dữ liệu cũ
        List<KhachHang> list = khachHangDAO.getAll();
        for (KhachHang kh : list) {
            model.addRow(new Object[]{
                kh.getId(), kh.getHoTen(), kh.getSoDienThoai(), kh.getDiemTichLuy()
            });
        }
    }

    // --- CÁC CLASS XỬ LÝ SỰ KIỆN NÚT BẤM ---

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sdt = view.getSdt();
            if (sdt.isEmpty() || view.getHoTen().isEmpty()) {
                view.showMessage("Vui lòng nhập đủ Họ tên và Số điện thoại!");
                return;
            }
            
            // Kiểm tra trùng số điện thoại
            if (khachHangDAO.findByPhone(sdt) != null) {
                view.showMessage("Số điện thoại này đã được đăng ký!");
                return;
            }

            KhachHang kh = new KhachHang();
            kh.setHoTen(view.getHoTen());
            kh.setSoDienThoai(sdt);
            kh.setDiemTichLuy(0); // Khách mới mặc định 0 điểm

            if (khachHangDAO.add(kh)) {
                view.showMessage("Thêm khách hàng thành công!");
                view.clearForm();
                loadDataToTable();
            } else {
                view.showMessage("Thêm thất bại!");
            }
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) {
                view.showMessage("Vui lòng chọn 1 khách hàng trên bảng để sửa!");
                return;
            }
            
            KhachHang kh = new KhachHang();
            kh.setId(Integer.parseInt(view.getId()));
            kh.setHoTen(view.getHoTen());
            kh.setSoDienThoai(view.getSdt());
            // Điểm tích lũy không cho phép tự sửa bằng tay ở form này

            if (khachHangDAO.update(kh)) {
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
            if (view.getId().isEmpty()) {
                view.showMessage("Vui lòng chọn 1 khách hàng để xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(view.getId());
                if (khachHangDAO.delete(id)) {
                    view.showMessage("Xóa thành công!");
                    view.clearForm();
                    loadDataToTable();
                } else {
                    view.showMessage("Xóa thất bại! Khách hàng này có thể đang có hóa đơn trên hệ thống.");
                }
            }
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sdt = view.getTimKiem();
            if (sdt.isEmpty()) {
                loadDataToTable();
                return;
            }
            
            DefaultTableModel model = view.getModelKhachHang();
            model.setRowCount(0);
            
            KhachHang kh = khachHangDAO.findByPhone(sdt);
            if (kh != null) {
                model.addRow(new Object[]{
                    kh.getId(), kh.getHoTen(), kh.getSoDienThoai(), kh.getDiemTichLuy()
                });
            } else {
                view.showMessage("Không tìm thấy khách hàng với SĐT này!");
            }
        }
    }
}