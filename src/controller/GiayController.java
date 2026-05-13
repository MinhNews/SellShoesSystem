package controller;

import dao.GiayDAO;
import dao.LoaiGiayDAO;
import model.Giay;
import model.LoaiGiay;
import view.QuanLyGiayPanel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GiayController {
    private QuanLyGiayPanel view;
    private GiayDAO giayDAO;
    private LoaiGiayDAO loaiDAO; 

    public GiayController(QuanLyGiayPanel view) {
        this.view = view;
        this.giayDAO = new GiayDAO();
        this.loaiDAO = new LoaiGiayDAO(); 

        // Nạp danh sách Loại Giày lên ComboBox trước
        refreshLoaiGiay();

        // Load dữ liệu lần đầu khi mở tab
        loadDataToTable();

        // Gắn sự kiện click vào các nút
        this.view.addThemListener(new AddListener());
        this.view.addSuaListener(new EditListener());
        this.view.addXoaListener(new DeleteListener());
        this.view.addTimKiemListener(new SearchListener());
        this.view.addLamMoiListener(e -> {
            view.clearForm();
            loadDataToTable();
        });

        // Xử lý sự kiện CLICK CHUỘT vào Bảng -> Đổ dữ liệu lên Form
        this.view.getTblGiay().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblGiay().getSelectedRow();
                if (row >= 0) {
                    view.setFormData(
                            view.getTblGiay().getValueAt(row, 0).toString(), // ID
                            view.getTblGiay().getValueAt(row, 1).toString(), // Loại Giày (Tên)
                            view.getTblGiay().getValueAt(row, 2).toString(), // Tên Giày
                            view.getTblGiay().getValueAt(row, 3).toString(), // Thương Hiệu
                            view.getTblGiay().getValueAt(row, 4).toString(), // Size
                            view.getTblGiay().getValueAt(row, 5).toString(), // Màu
                            view.getTblGiay().getValueAt(row, 6).toString(), // Giá
                            view.getTblGiay().getValueAt(row, 7).toString(), // SL
                            view.getTblGiay().getValueAt(row, 8).toString()  // Trạng thái
                    );
                }
            }
        });
    }

    public void refreshLoaiGiay() {
        List<LoaiGiay> dsLoai = loaiDAO.getAll();
        view.setLoaiGiayData(dsLoai);
    }

    private void loadDataToTable() {
        DefaultTableModel model = view.getModelGiay();
        model.setRowCount(0); 
        List<Giay> list = giayDAO.getAll();
        
        for (Giay g : list) {
            // Translate ID_LoaiGiay sang Tên Loại để hiển thị lên bảng cho đẹp
            LoaiGiay lg = loaiDAO.getById(g.getIdLoaiGiay());
            String tenLoai = (lg != null) ? lg.getTenLoai() : "N/A";

            model.addRow(new Object[]{
                g.getId(), tenLoai, g.getTenGiay(), g.getThuongHieu(), g.getSize(),
                g.getMauSac(), g.getGiaBan(), g.getSoLuongTon(), g.getTrangThai()
            });
        }
    }

    // --- CÁC CLASS XỬ LÝ SỰ KIỆN NÚT BẤM ---

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                LoaiGiay selectedLoai = (LoaiGiay) view.getCbLoaiGiay().getSelectedItem();
                if (selectedLoai == null) {
                    view.showMessage("Vui lòng thêm Loại Giày vào Database trước!");
                    return;
                }

                Giay g = new Giay();
                g.setIdLoaiGiay(selectedLoai.getId()); // Gán ID Loại Giày
                g.setTenGiay(view.getTenGiay());
                g.setThuongHieu(view.getThuongHieu());
                g.setSize(Integer.parseInt(view.getSizeGiay()));
                g.setMauSac(view.getMauSac());
                g.setGiaBan(Double.parseDouble(view.getGiaBan()));
                g.setSoLuongTon(Integer.parseInt(view.getSoLuongTon()));
                g.setTrangThai(view.getTrangThai());

                if (giayDAO.add(g)) {
                    view.showMessage("Thêm giày thành công!");
                    view.clearForm();
                    loadDataToTable();
                } else {
                    view.showMessage("Thêm thất bại!");
                }
            } catch (Exception ex) {
                view.showMessage("Vui lòng nhập đúng định dạng số cho Size, Giá và Tồn Kho!");
            }
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) {
                view.showMessage("Vui lòng chọn 1 đôi giày trên bảng để sửa!");
                return;
            }
            try {
                LoaiGiay selectedLoai = (LoaiGiay) view.getCbLoaiGiay().getSelectedItem();

                Giay g = new Giay();
                g.setId(Integer.parseInt(view.getId()));
                g.setIdLoaiGiay(selectedLoai.getId()); // Gán ID Loại Giày
                g.setTenGiay(view.getTenGiay());
                g.setThuongHieu(view.getThuongHieu());
                g.setSize(Integer.parseInt(view.getSizeGiay()));
                g.setMauSac(view.getMauSac());
                g.setGiaBan(Double.parseDouble(view.getGiaBan()));
                g.setSoLuongTon(Integer.parseInt(view.getSoLuongTon()));
                g.setTrangThai(view.getTrangThai());

                if (giayDAO.update(g)) {
                    view.showMessage("Cập nhật thành công!");
                    view.clearForm();
                    loadDataToTable();
                } else {
                    view.showMessage("Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                view.showMessage("Lỗi định dạng dữ liệu!");
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) {
                view.showMessage("Vui lòng chọn 1 đôi giày để xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(view.getId());
                if (giayDAO.delete(id)) {
                    view.showMessage("Xóa thành công!");
                    view.clearForm();
                    loadDataToTable();
                } else {
                    view.showMessage("Xóa thất bại! Không tìm thấy dữ liệu.");
                }
            }
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = view.getTimKiem();
            DefaultTableModel model = view.getModelGiay();
            model.setRowCount(0);
            List<Giay> list = giayDAO.search(keyword);
            for (Giay g : list) {
                LoaiGiay lg = loaiDAO.getById(g.getIdLoaiGiay());
                String tenLoai = (lg != null) ? lg.getTenLoai() : "N/A";

                model.addRow(new Object[]{
                    g.getId(), tenLoai, g.getTenGiay(), g.getThuongHieu(), g.getSize(),
                    g.getMauSac(), g.getGiaBan(), g.getSoLuongTon(), g.getTrangThai()
                });
            }
        }
    }
}