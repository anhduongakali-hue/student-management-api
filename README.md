# Student Management REST API
Đây là một dự án Backend cung cấp các API RESTful để quản lý thông tin sinh viên. Dự án được áp dụng kiến trúc 3 lớp (3-Tier Architecture) chuẩn của Spring Boot, kết hợp xử lý ngoại lệ tập trung (Global Exception Handling) và thiết kế Data Transfer Object (DTO).
## Công nghệ sử dụng
* **Ngôn ngữ:** Java
* **Framework:** Spring Boot (Web, Data JPA, Validation)
* **Database:** MySQL
* **Công cụ hỗ trợ:** Lombok, Maven, Postman
## Hướng dẫn cài đặt và chạy dự án
### Bước 1: Khởi tạo Database
Dự án này sử dụng phương pháp Database-First. Bạn cần mở MySQL và chạy đoạn script sau để tạo cơ sở dữ liệu và bảng:

```sql
CREATE DATABASE IF NOT EXISTS tutor_school;
USE tutor_school;

CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    major VARCHAR(255),
    deleted BOOLEAN DEFAULT FALSE
);
```

### Bước 2: Clone dự án và cấu hình
1. Clone repository này về máy.
2. Mở file `src/main/resources/application.properties`.
3. Thay đổi thông tin đăng nhập MySQL của bạn:
```properties
spring.datasource.username=root
spring.datasource.password=mat_khau_cua_ban
```
### Bước 3: Khởi chạy
Mở project bằng IntelliJ IDEA (hoặc Eclipse), đợi Maven tải xong các thư viện (dependencies) và chạy class `TutorApplication.java`.
Server sẽ khởi động ở cổng `8080`.
## Danh sách API (Endpoints)

| HTTP Method | Endpoint         | Mô tả chức năng |
| :--- |:-----------------| :--- |
| `GET` | `/students`      | Lấy danh sách toàn bộ sinh viên (chưa bị xóa) |
| `GET` | `/students/{id}` | Lấy thông tin chi tiết một sinh viên theo ID |
| `POST` | `/students`      | Thêm mới một sinh viên |
| `PUT` | `/students/{id}` | Cập nhật thông tin sinh viên theo ID |
| `DELETE` | `/students/{id}` | Xóa mềm (Soft Delete) sinh viên |

## Các tính năng kỹ thuật nổi bật
* **Soft Delete:** Khi gọi API xóa, dữ liệu không mất đi mà chỉ được cập nhật trạng thái `deleted = true` trong cơ sở dữ liệu.
* **Global Exception Handler:** Bắt và định dạng lại toàn bộ lỗi (Validation, RuntimeException) thành file JSON thân thiện với người dùng và trả về đúng HTTP Status Code (400, 404).

## 🛡️ Bảo mật & Xác thực (Security)

Dự án đã được tích hợp **Spring Security** để quản lý xác thực (Authentication) và phân quyền (Authorization) người dùng.

### ✨ Các tính năng bảo mật hiện tại:
* **Xác thực qua Database:** Người dùng đăng nhập bằng tài khoản lưu trực tiếp trong cơ sở dữ liệu (`User` & `Role`).
* **Mã hóa mật khẩu an toàn:** Sử dụng `BCryptPasswordEncoder` để băm mật khẩu trước khi lưu.
* **Tự động hóa dữ liệu (Data Seeding):** Sử dụng `CommandLineRunner` để tự động tiêm tài khoản Admin vào Database ngay khi khởi động ứng dụng.
* **Tối ưu mã nguồn:** Áp dụng triệt để `Lombok` (`@Builder`, `@Getter`, `@Setter`) cho các Entity để code gọn gàng, dễ bảo trì.

### 🚀 Hướng dẫn Test API trên Postman

Hiện tại hệ thống đang sử dụng cơ chế **HTTP Basic Auth**. Bất kỳ API nào gọi vào hệ thống (VD: `GET /students`) đều yêu cầu phải có thẻ thông hành.

**Các bước để test:**
1. Khởi động Server. Hệ thống sẽ tự động tạo một tài khoản mặc định (Username: `admin` | Password: `123456`).
2. Mở Postman, nhập URL của API cần test.
3. Bên dưới ô URL, chọn tab **Authorization**.
4. Ở mục **Type**, chọn **Basic Auth**.
5. Nhập Username: `admin` và Password: `akalitt7` vào khung bên phải.
6. Bấm **Send** và nhận kết quả trả về (Mã 200 OK). 
*(Lưu ý: Nếu không nhập hoặc nhập sai, hệ thống sẽ chặn đứng và trả về mã lỗi 401 Unauthorized).*
