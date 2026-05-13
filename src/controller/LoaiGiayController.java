package controller;

import dao.LoaiGiayDAO;
import model.LoaiGiay;
import view.LoaiGiayPanel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LoaiGiayController {
    private LoaiGiayPanel view;
    private LoaiGiayDAO dao;

    public LoaiGiayController(LoaiGiayPanel view) {
        this.view = view;
        this.dao = new LoaiGiayDAO();

        loadDataToTable();

        this.view.addThemListener(new AddListener());
        this.view.addSuaListener(new EditListener());
        this.view.addXoaListener(new DeleteListener());
        this.view.addLamMoiListener(e -> { view.clearForm(); loadDataToTable(); });

        this.view.getTblLoaiGiay().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblLoaiGiay().getSelectedRow();
                if (row >= 0) {
                    view.setFormData(
                            view.getTblLoaiGiay().getValueAt(row, 0).toString(),
                            view.getTblLoaiGiay().getValueAt(row, 1).toString()
                    );
                }
            }
        });
    }

    private void loadDataToTable() {
        DefaultTableModel model = view.getModelLoaiGiay();
        model.setRowCount(0);
        List<LoaiGiay> list = dao.getAll();
        for (LoaiGiay lg : list) {
            model.addRow(new Object[]{lg.getId(), lg.getTenLoai()});
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenLoai = view.getTenLoai();
            if (tenLoai.isEmpty()) { view.showMessage("Vui lòng nhập tên loại giày!"); return; }
            LoaiGiay lg = new LoaiGiay(); lg.setTenLoai(tenLoai);
            if (dao.add(lg)) { view.showMessage("Thêm thành công!"); view.clearForm(); loadDataToTable(); } 
            else { view.showMessage("Thêm thất bại!"); }
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) { view.showMessage("Chọn danh mục để sửa!"); return; }
            LoaiGiay lg = new LoaiGiay();
            lg.setId(Integer.parseInt(view.getId()));
            lg.setTenLoai(view.getTenLoai());
            if (dao.update(lg)) { view.showMessage("Cập nhật thành công!"); view.clearForm(); loadDataToTable(); } 
            else { view.showMessage("Cập nhật thất bại!"); }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getId().isEmpty()) { view.showMessage("Chọn danh mục để xóa!"); return; }
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.delete(Integer.parseInt(view.getId()))) {
                    view.showMessage("Xóa thành công!"); view.clearForm(); loadDataToTable();
                } else {
                    view.showMessage("Không thể xóa do đang có giày thuộc loại này!");
                }
            }
        }
    }
}