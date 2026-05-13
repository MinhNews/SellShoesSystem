package controller;

import dao.HoaDonDAO;
import dao.ChiTietHoaDonDAO;
import dao.GiayDAO;
import model.HoaDon;
import model.ChiTietHoaDon;
import model.Giay;
import view.HoaDonPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class HoaDonController {
    private HoaDonPanel view;
    private HoaDonDAO hdDAO;
    private ChiTietHoaDonDAO cthdDAO;
    private GiayDAO giayDAO;

    public HoaDonController(HoaDonPanel view) {
        this.view = view;
        this.hdDAO = new HoaDonDAO();
        this.cthdDAO = new ChiTietHoaDonDAO();
        this.giayDAO = new GiayDAO();

        loadHoaDon();

        // NÚT LÀM MỚI DANH SÁCH
        this.view.addLamMoiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHoaDon();
                view.getModelChiTiet().setRowCount(0); // Xóa trắng bảng chi tiết
            }
        });

        // LOGIC CHÍNH: Click bảng trên -> Load bảng dưới
        this.view.getTblHoaDon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblHoaDon().getSelectedRow();
                if (row >= 0) {
                    int idHoaDon = (int) view.getTblHoaDon().getValueAt(row, 0);
                    loadChiTietHoaDon(idHoaDon);
                }
            }
        });
    }

    private void loadHoaDon() {
        view.getModelHoaDon().setRowCount(0);
        List<HoaDon> list = hdDAO.getAll();
        for (HoaDon hd : list) {
            view.getModelHoaDon().addRow(new Object[]{
                hd.getId(), hd.getIdNhanVien(), hd.getIdKhachHang(), hd.getNgayLap(), hd.getTongTien()
            });
        }
    }

    private void loadChiTietHoaDon(int idHoaDon) {
        view.getModelChiTiet().setRowCount(0);
        List<ChiTietHoaDon> dsChiTiet = cthdDAO.getByIdHoaDon(idHoaDon);
        
        for (ChiTietHoaDon ct : dsChiTiet) {
            Giay g = giayDAO.getById(ct.getIdGiay());
            String tenGiay = (g != null) ? g.getTenGiay() : "Unknown";
            
            view.getModelChiTiet().addRow(new Object[]{
                ct.getIdGiay(), tenGiay, ct.getSoLuongMua(), ct.getDonGia(), (ct.getSoLuongMua() * ct.getDonGia())
            });
        }
    }
}