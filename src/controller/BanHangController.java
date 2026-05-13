package controller;

import dao.GiayDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import model.ChiTietHoaDon;
import model.Giay;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import view.BanHangPanel;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BanHangController {
    private BanHangPanel view;
    private GiayDAO giayDAO;
    private KhachHangDAO khDAO;
    private HoaDonDAO hdDAO;
    
    private NhanVien nhanVienDangTruc; // Cần biết ai đang bán hàng
    private KhachHang khachHangHienTai; // Lưu thông tin khách đang mua
    private List<ChiTietHoaDon> gioHang; // Lưu danh sách đồ trong giỏ

    public BanHangController(BanHangPanel view, NhanVien nv) {
        this.view = view;
        this.nhanVienDangTruc = nv;
        this.giayDAO = new GiayDAO();
        this.khDAO = new KhachHangDAO();
        this.hdDAO = new HoaDonDAO();
        this.gioHang = new ArrayList<>();

        loadKhoGiay(); // Đổ dữ liệu kho lúc khởi tạo

        this.view.addTimKhachListener(new TimKhachListener());
        this.view.addThemVaoGioListener(new ThemVaoGioListener());
        this.view.addXoaKhoiGioListener(new XoaKhoiGioListener());
        this.view.addThanhToanListener(new ThanhToanListener());
    }

    public void loadKhoGiay() {
        DefaultTableModel model = view.getModelKho();
        model.setRowCount(0);
        List<Giay> list = giayDAO.getAll();
        for (Giay g : list) {
            // Chỉ hiện những đôi còn hàng
            if (g.getSoLuongTon() > 0 && g.getTrangThai().equals("Available")) {
                model.addRow(new Object[]{
                    g.getId(), g.getTenGiay(), g.getThuongHieu(), g.getSize(), g.getGiaBan(), g.getSoLuongTon()
                });
            }
        }
    }

    private void capNhatGioHang() {
        DefaultTableModel model = view.getModelGioHang();
        model.setRowCount(0);
        double tongTien = 0;

        for (ChiTietHoaDon ct : gioHang) {
            double thanhTien = ct.getSoLuongMua() * ct.getDonGia();
            tongTien += thanhTien;
            
            // Tìm tên giày (chỉ để hiển thị cho đẹp)
            Giay g = giayDAO.getById(ct.getIdGiay());
            String tenGiay = (g != null) ? g.getTenGiay() : "Giày ID " + ct.getIdGiay();

            model.addRow(new Object[]{
                ct.getIdGiay(), tenGiay, ct.getSoLuongMua(), ct.getDonGia(), thanhTien
            });
        }
        view.setTongTien(tongTien);
    }

    // --- CÁC LISTENER ---

    class TimKhachListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sdt = view.getSdtKhach();
            if (sdt.isEmpty()) return;

            KhachHang kh = khDAO.findByPhone(sdt);
            if (kh != null) {
                khachHangHienTai = kh;
                view.setTenKhach(kh.getHoTen() + " (Điểm: " + kh.getDiemTichLuy() + ")");
                view.showMessage("Tìm thấy khách hàng!");
            } else {
                khachHangHienTai = null;
                view.setTenKhach("Không tìm thấy!");
                view.showMessage("Số điện thoại chưa được đăng ký!");
            }
        }
    }

    class ThemVaoGioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTblKhoGiay().getSelectedRow();
            if (row < 0) {
                view.showMessage("Chọn một đôi giày trong kho để thêm!");
                return;
            }

            try {
                int soLuongMua = Integer.parseInt(view.getSoLuongMua());
                if (soLuongMua <= 0) throw new Exception();

                int idGiay = (int) view.getTblKhoGiay().getValueAt(row, 0);
                double giaBan = (double) view.getTblKhoGiay().getValueAt(row, 4);
                int tonKho = (int) view.getTblKhoGiay().getValueAt(row, 5);

                if (soLuongMua > tonKho) {
                    view.showMessage("Kho không đủ số lượng!");
                    return;
                }

                // Kiểm tra xem trong giỏ đã có đôi này chưa, nếu có thì cộng dồn
                boolean daCo = false;
                for (ChiTietHoaDon ct : gioHang) {
                    if (ct.getIdGiay() == idGiay) {
                        ct.setSoLuongMua(ct.getSoLuongMua() + soLuongMua);
                        daCo = true;
                        break;
                    }
                }

                if (!daCo) {
                    ChiTietHoaDon ctMoi = new ChiTietHoaDon();
                    ctMoi.setIdGiay(idGiay);
                    ctMoi.setSoLuongMua(soLuongMua);
                    ctMoi.setDonGia(giaBan);
                    gioHang.add(ctMoi);
                }

                capNhatGioHang();
            } catch (Exception ex) {
                view.showMessage("Số lượng mua phải là số nguyên dương!");
            }
        }
    }

    class XoaKhoiGioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTblGioHang().getSelectedRow();
            if (row < 0) {
                view.showMessage("Chọn món hàng trong giỏ để xóa!");
                return;
            }
            gioHang.remove(row);
            capNhatGioHang();
        }
    }

    class ThanhToanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gioHang.isEmpty()) {
                view.showMessage("Giỏ hàng trống, không thể thanh toán!");
                return;
            }

            // 1. Tạo đối tượng Hóa Đơn
            HoaDon hd = new HoaDon();
            hd.setIdNhanVien(nhanVienDangTruc.getId());
            
            // Nếu có khách hàng thì gắn ID, không thì gán 0
            if (khachHangHienTai != null) {
                hd.setIdKhachHang(khachHangHienTai.getId());
            } else {
                hd.setIdKhachHang(0); 
            }

            // Tính lại tổng tiền cho chắc ăn
            double tongTien = 0;
            for (ChiTietHoaDon ct : gioHang) tongTien += ct.getSoLuongMua() * ct.getDonGia();
            hd.setTongTien(tongTien);

            // 2. Gọi Hóa Đơn DAO xử lý Transaction
            if (hdDAO.thanhToanHoaDon(hd, gioHang)) {
                // 3. Nếu thành công, cộng điểm cho khách (Giả sử 20k = 1 điểm)
                if (khachHangHienTai != null) {
                    int diemCongThêm = (int) (tongTien / 20000);
                    khDAO.updateDiem(khachHangHienTai.getId(), diemCongThêm);
                }

                view.showMessage("THANH TOÁN THÀNH CÔNG!\nTổng tiền: " + tongTien + " VNĐ");
                
                // 4. Dọn dẹp để chuẩn bị bán đơn mới
                gioHang.clear();
                khachHangHienTai = null;
                view.clearThanhToan();
                capNhatGioHang();
                loadKhoGiay(); // Trừ tồn kho xong thì phải load lại kho
            } else {
                view.showMessage("Thanh toán thất bại! Đã Rollback giao dịch.");
            }
        }
    }
}