# 👟 Quản Lý Kinh Doanh Giày Dép

Ứng dụng Desktop quản lý cửa hàng giày dép xây dựng bằng **Java Swing + MySQL**, theo mô hình **MVC**.

---

## 👥 Thành viên nhóm

| Tên                  | Vai trò                                   |
|----------------------|-------------------------------------------|
| Nguyễn Đức Minh      | Database, DAO, Business Logic             |
| Nguyễn Huy Hùng      | Model, View (Kho hàng, Nhân sự, Thống kê) |
| Nguyễn Phú Minh Thái | UI Architecture, POS, Giao diện chính     |

---

## ✨ Tính năng

- 🔐 **Đăng nhập** – Xác thực tài khoản, phân quyền Admin / Nhân viên
- 📂 **Quản lý Loại Giày** – CRUD danh mục
- 👟 **Quản lý Giày** – CRUD kho hàng, tìm kiếm theo tên / thương hiệu
- 🧾 **Bán hàng (POS)** – Giỏ hàng, tìm kiếm real-time, tích điểm khách hàng, Transaction nguyên tử
- 👥 **Khách hàng** – Hồ sơ + điểm tích lũy (100k VNĐ = 1 điểm, 1 điểm = 1.000 VNĐ giảm giá)
- 📋 **Lịch sử Hóa đơn** – Xem Master–Detail
- 🧑‍💼 **Quản lý Nhân viên** – CRUD tài khoản, phân quyền *(chỉ Admin)*
- 📊 **Thống kê Doanh thu** – Lọc theo ngày, biểu đồ Top 5 sản phẩm bán chạy *(chỉ Admin)*

---

## 🛠️ Công nghệ sử dụng

| Thành phần | Công nghệ |
|---|---|
| Ngôn ngữ | Java 8+ |
| Giao diện | Java Swing + [FlatLaf](https://www.formdev.com/flatlaf/) |
| Cơ sở dữ liệu | MySQL 8.0 |
| Kết nối DB | JDBC (MySQL Connector/J) |
| Biểu đồ | JFreeChart |
| Chọn ngày | JCalendar |

---

## 🗂️ Cấu trúc project

```
src/
├── Main.java
├── model/          # POJO: Giay, KhachHang, HoaDon, NhanVien...
├── database/       # DatabaseConnection.java
├── dao/            # Truy vấn SQL (GiayDAO, HoaDonDAO, ThongKeDAO...)
├── controller/     # Logic nghiệp vụ (BanHangController, LoginController...)
└── view/           # Giao diện Swing (BanHangPanel, MainDashboard...)
lib/
├── flatlaf-x.x.jar
├── mysql-connector-j-x.x.jar
├── jfreechart-x.x.jar
└── jcalendar-x.x.jar
QuanLyCuaHangGiay.sql
```

---

## ⚙️ Cài đặt & Chạy

### Yêu cầu
- JDK 8 trở lên
- MySQL Server 8.0+
- IDE: IntelliJ IDEA hoặc Eclipse

### Các bước

**1. Tạo cơ sở dữ liệu**
```sql
-- Chạy file SQL trong MySQL Workbench hoặc terminal:
source QuanLyCuaHangGiayDB.sql;
```

**2. Cấu hình kết nối**

Mở `src/database/DatabaseConnection.java`, sửa thông tin phù hợp với máy:
```java
private static final String URL = "jdbc:mysql://localhost:3306/QuanLyCuaHangGiay?useUnicode=true&characterEncoding=utf-8";
private static final String USER = "root";
private static final String PASSWORD = "your_password"; // ← sửa ở đây
```

**3. Thêm thư viện**

Thêm toàn bộ file `.jar` trong thư mục `lib/` vào classpath của project.

**4. Chạy ứng dụng**

Chạy file `src/Main.java`.

### Tài khoản mặc định
| Username | Password | Quyền |
|----------|----------|-------|
| `admin` | `admin123`| Admin |

---

## 📸 Giao diện
<img width="945" height="551" alt="image" src="https://github.com/user-attachments/assets/97e616c2-deab-41f5-a0f4-73695aaa20a6" />
<img width="945" height="554" alt="image" src="https://github.com/user-attachments/assets/cdbf9daf-5e88-406d-957c-c2ebd5a1c515" />
<img width="945" height="551" alt="image" src="https://github.com/user-attachments/assets/aa645b2d-9466-45e3-8233-26b1668fb683" />

---

## 📄 Giấy phép

Đề tài nhóm – Môn Lập Trình Java – Khoa Công Nghệ Thông Tin.
MIT License
