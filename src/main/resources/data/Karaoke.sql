USE master
GO
DROP DATABASE Karaoke
GO
CREATE DATABASE Karaoke
GO
USE Karaoke
GO
CREATE TABLE LoaiPhong
(
    maLoaiPhong  VARCHAR(5) PRIMARY KEY,
    tenLoaiPhong NVARCHAR(255),
    trangThai    TINYINT
);
GO
CREATE TABLE Phong
(
    maPhong     VARCHAR(7) PRIMARY KEY,
    tenPhong    NVARCHAR(255),
    sucChua     INT,
    maLoaiPhong VARCHAR(5),
    trangThai   TINYINT,
    FOREIGN KEY (maLoaiPhong) REFERENCES LoaiPhong (maLoaiPhong)
);
GO
CREATE TABLE LichSuGiaPhong
(
    maLichSuGiaPhong VARCHAR(13) PRIMARY KEY,
    ngayBatDau       DATE,
    ngayKetThuc      DATE,
    thoiDiemBatDau   TIME,
    thoiDiemKetThuc  TIME,
    gia              FLOAT,
    maLoaiPhong      VARCHAR(5),
    FOREIGN KEY (maLoaiPhong) REFERENCES LoaiPhong (maLoaiPhong)
);
GO
CREATE TABLE NhanVien
(
    maNhanVien VARCHAR(8) PRIMARY KEY,
    ten        NVARCHAR(255),
    chucVu     NVARCHAR(255),
    sdt        VARCHAR(10),
    email      NVARCHAR(255),
    diaChi     NVARCHAR(255),
    trangThai  TINYINT,
);
GO
CREATE TABLE TaiKhoan
(
    maTaiKhoan  VARCHAR(8) PRIMARY KEY,
    tenTaiKhoan NVARCHAR(255),
    matKhau     NVARCHAR(255),
    trangThai   NVARCHAR(20),
    maNhanVien  VARCHAR(8),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien)
)
GO
CREATE TABLE LoaiDichVu
(
    maLoaiDichVu  VARCHAR(6) PRIMARY KEY,
    tenLoaiDichVu NVARCHAR(255),
    trangThai     TINYINT,
);
GO
CREATE TABLE DichVu
(
    maDichVu     VARCHAR(10) PRIMARY KEY,
    tenDichVu    NVARCHAR(255),
    soLuong      INT,
    maLoaiDichVu VARCHAR(6),
    trangThai    TINYINT,
    FOREIGN KEY (maLoaiDichVu) REFERENCES LoaiDichVu (maLoaiDichVu)
);
GO
CREATE TABLE LichSuGiaDichVu
(
    maLichSuGiaDichVu VARCHAR(14) PRIMARY KEY,
    ngayBatDau        DATE,
    ngayKetThuc       DATE,
    thoiDiemBatDau    TIME,
    thoiDiemKetThuc   TIME,
    gia               FLOAT,
    maDichVu          VARCHAR(10),
    FOREIGN KEY (maDichVu) REFERENCES DichVu (maDichVu)
);
GO
CREATE TABLE KhuyenMai
(
    maKhuyenMai     VARCHAR(13) PRIMARY KEY,
    tenKhuyenMai    NVARCHAR(255),
    phanTram        FLOAT,
    gioiHan         FLOAT,
    ngayBatDau      DATE,
    ngayKetThuc     DATE,
    thoiDiemBatDau  TIME,
    thoiDiemKetThuc TIME,
);
GO
CREATE TABLE KhachHang
(
    maKhachHang  VARCHAR(13) PRIMARY KEY,
    tenKhachHang NVARCHAR(255),
    sdt          NVARCHAR(10)
)
GO
CREATE TABLE HoaDon
(
    maHoaDon          VARCHAR(14) PRIMARY KEY,
    tongTien          FLOAT,
    NgayThanhToan     DATE,
    thoiDiemThanhToan TIME,
    maKhachHang       VARCHAR(13),
    maNhanVien        VARCHAR(8),
    maKhuyenMai       VARCHAR(13),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang (maKhachHang),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien),
    FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai (maKhuyenMai)
);
GO
CREATE TABLE PhieuDatPhong
(
    maPhieuDatPhong VARCHAR(14) PRIMARY KEY,
    thoiGianBatDau  TIME,
    thoiGianKetThuc TIME,
    maHoaDon        VARCHAR(14),
    maPhong         VARCHAR(7),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon (maHoaDon),
    FOREIGN KEY (maPhong) REFERENCES Phong (maPhong)
);
GO
CREATE TABLE ChiTietDatDichVu
(
    maPhieuDatPhong VARCHAR(14),
    maDichVu        VARCHAR(10),
    soLuong         INT,
    PRIMARY KEY (maPhieuDatPhong, maDichVu),
    FOREIGN KEY (maPhieuDatPhong) REFERENCES PhieuDatPhong (maPhieuDatPhong),
    FOREIGN KEY (maDichVu) REFERENCES DichVu (maDichVu)
);


INSERT INTO LoaiPhong
VALUES ('LP001', N'Phòng thường', 1),
       ('LP002', N'Phòng vip', 1)
GO

INSERT INTO Phong
VALUES ('P0101', '01.001', 10, 'LP001', 0),
       ('P0102', '01.002', 10, 'LP001', 0),
       ('P0103', '01.003', 10, 'LP001', 0),
       ('P0104', '01.004', 10, 'LP001', 0),
       ('P0105', '01.005', 10, 'LP001', 0)

INSERT INTO Phong
VALUES ('P0201', '02.001', 10, 'LP002', 0),
       ('P0202', '02.002', 10, 'LP002', 0),
       ('P0203', '02.003', 10, 'LP002', 0),
       ('P0204', '02.004', 10, 'LP002', 0),
       ('P0205', '02.005', 10, 'LP002', 0)
GO

INSERT INTO LichSuGiaPhong
VALUES ('GP.041123.01', '2018-01-01', '2023-11-10', '6:00', '12:00', 100000, 'LP001'),
       ('GP.041123.02', '2018-01-01', '2023-11-20', '18:00', '23:00', 100000, 'LP002')
GO

INSERT INTO NhanVien
VALUES ('NV230001', N'Trần Trung Tiến', N'Người quản lý', '0986148209', N'tien@gmail.com',
        N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
       ('NV230002', N'La Minh Tâm', N'Người quản lý', '0886700046', N'tam@gmail.com',
        N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
       ('NV230003', N'Vũ Quốc Huy', N'Nhân viên', '0366895412', N'huy@gmail.com',
        N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
       ('NV230004', N'Lương Tấn Đạt', N'Nhân viên', '0962145578', N'dat@gmail.com',
        N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1)
GO

INSERT INTO TaiKhoan
VALUES ('TK23001', N'trantrungtien', N'Tien123456@', 1, 'NV230001'),
       ('TK23002', N'laminhtam', N'Tam123456@', 1, 'NV230002'),
       ('TK23003', N'vuquochuy', N'Huy123456@', 1, 'NV230003'),
       ('TK23004', N'luongtandat', N'Dat123456@', 1, 'NV230004')
GO

INSERT INTO LoaiDichVu
VALUES ('LDV001', N'Thực phẩm', 1),
       ('LDV002', N'Nước uống', 1)
GO

INSERT INTO DichVu
VALUES ('DV.001.001', N'Trái cây', 20, 'LDV001', 1),
       ('DV.001.002', N'Mỳ xào hải sản', 10, 'LDV001', 1),
       ('DV.001.003', N'Cá điêu hồng chiên xù', 15, 'LDV001', 1),
       ('DV.001.004', N'Bánh ngọt', 30, 'LDV001', 1),
       ('DV.001.005', N'Bánh kem', 10, 'LDV001', 1),
       ('DV.002.001', N'Coca Cola', 420, 'LDV002', 1),
       ('DV.002.002', N'Heniken', 320, 'LDV002', 1),
       ('DV.002.003', N'Sting', 100, 'LDV002', 1),
       ('DV.002.004', N'Trà ô long', 50, 'LDV002', 1),
       ('DV.002.005', N'Wine', 30, 'LDV002', 1)
GO

INSERT INTO LichSuGiaDichVu
VALUES ('GDV.041123001', '2018-01-01', NULL, '6:00', '12:00', 80000, 'DV.001.001'),
       ('GDV.041123002', '2018-01-01', NULL, '6:00', '12:00', 120000, 'DV.001.002'),
       ('GDV.041123003', '2018-01-01', NULL, '12:00', '18:00', 100000, 'DV.001.003'),
       ('GDV.041123004', '2018-01-01', NULL, '12:00', '18:00', 40000, 'DV.001.004'),
       ('GDV.051123001', '2018-01-01', NULL, '18:00', '23:00', 150000, 'DV.001.005'),
       ('GDV.051123002', '2018-01-01', NULL, '18:00', '23:00', 20000, 'DV.002.001'),
       ('GDV.061123001', '2018-01-01', NULL, '6:00', '12:00', 560000, 'DV.002.002'),
       ('GDV.061123002', '2018-01-01', NULL, '6:00', '12:00', 18000, 'DV.002.003'),
       ('GDV.061123003', '2018-01-01', NULL, '6:00', '12:00', 15000, 'DV.002.004'),
       ('GDV.061123014', '2018-01-01', NULL, '6:00', '12:00', 830000, 'DV.002.005')
GO

INSERT INTO KhuyenMai
VALUES ('KM.041123.001', N'Happy time', 20, 5, '2023-11-04', '2023-11-20', '8:00', '23:00'),
       ('KM.051123.002', N'Cùng vui sinh nhật', 20, 10, '2023-11-05', '2023-11-05', '9:00', '21:00'),
       ('KM.061123.003', N'Đầu tuần', 50, 3, '2023-11-06', '2023-11-06', '6:00', '23:00'),
       ('KM.071123.004', N'Sáng thứ 3 ưu đãi', 30, 10, '2023-11-07', '2023-11-07', '6:00', '12:00'),
       ('KM.081123.005', N'Buổi tối vào hội', 20, 5, '2023-11-08', '2023-11-08', '18:00', '21:00')
GO

INSERT INTO KhachHang
VALUES ('KH.041123.001', N'Nguyễn Văn A', '0345678912'),
       ('KH.041123.002', N'Nguyễn Văn B', '0345678913'),
       ('KH.051123.001', N'Nguyễn Văn C', '0345678914'),
       ('KH.051123.002', N'Nguyễn Văn D', '0345678915'),
       ('KH.061123.001', N'Nguyễn Văn E', '0345678916'),
       ('KH.061123.002', N'Nguyễn Văn F', '0345678917'),
       ('KH.061123.003', N'Nguyễn Văn G', '0345678918'),
       ('KH.061123.004', N'Nguyễn Văn H', '0345678919')
GO

INSERT INTO HoaDon
VALUES ('HD.040123.001', 800000, '2023-01-04', '18:00', 'KH.041123.001', 'NV230003', 'KM.041123.001'),
       ('HD.040123.002', 120000, '2023-01-04', '12:00', 'KH.041123.002', 'NV230001', NULL),
       ('HD.050123.001', 300000, '2023-01-05', '9:00', 'KH.051123.001', 'NV230002', NULL),
       ('HD.050523.002', 520000, '2023-05-05', '22:00', 'KH.051123.002', 'NV230002', NULL),
       ('HD.060523.001', 180000, '2023-05-06', '21:00', 'KH.041123.001', 'NV230004', NULL),
       ('HD.060523.002', 420000, '2023-05-06', '15:00', 'KH.041123.002', 'NV230002', NULL),
       ('HD.060723.001', 100000, '2023-07-06', '10:00', 'KH.041123.002', 'NV230001', NULL),
       ('HD.070723.001', 800000, '2023-07-07', '15:00', 'KH.061123.004', 'NV230003', NULL)
GO

INSERT INTO PhieuDatPhong
VALUES ('PDP.040123.001', '15:00', '18:00', 'HD.040123.001', 'P0101'),
       ('PDP.040123.002', '10:00', '12:00', 'HD.040123.002', 'P0203'),
       ('PDP.050123.001', '8:00', '9:00', 'HD.050123.001', 'P0102'),
       ('PDP.050523.001', '15:00', '22:00', 'HD.050523.002', 'P0202'),
       ('PDP.060523.001', '18:00', '21:00', 'HD.060523.001', 'P0101'),
       ('PDP.060523.002', '12:00', '15:00', 'HD.060523.002', 'P0204'),
       ('PDP.060723.001', '8:00', '10:00', 'HD.060723.001', 'P0205'),
       ('PDP.070723.001', '10:00', '15:00', 'HD.070723.001', 'P0105')
GO

INSERT INTO ChiTietDatDichVu
VALUES ('PDP.040123.001', 'DV.001.001', 1),
       ('PDP.040123.001', 'DV.001.002', 2),
       ('PDP.040123.001', 'DV.001.003', 3),
       ('PDP.040123.002', 'DV.001.004', 4),
       ('PDP.040123.002', 'DV.001.005', 5),
       ('PDP.040123.002', 'DV.002.001', 1),
       ('PDP.040123.002', 'DV.002.002', 2),
       ('PDP.040123.002', 'DV.002.003', 3),
       ('PDP.050123.001', 'DV.002.004', 4),
       ('PDP.050123.001', 'DV.002.005', 5),
       ('PDP.050123.001', 'DV.001.001', 1),
       ('PDP.050123.001', 'DV.001.002', 2),
       ('PDP.050523.001', 'DV.001.003', 3),
       ('PDP.050523.001', 'DV.001.004', 4),
       ('PDP.050523.001', 'DV.001.005', 5),
       ('PDP.050523.001', 'DV.002.001', 1),
       ('PDP.050523.001', 'DV.002.002', 2),
       ('PDP.050523.001', 'DV.002.003', 3),
       ('PDP.060523.001', 'DV.002.004', 4),
       ('PDP.060523.001', 'DV.002.005', 5),
       ('PDP.060523.001', 'DV.001.001', 1),
       ('PDP.060523.001', 'DV.001.002', 2),
       ('PDP.060523.001', 'DV.001.003', 3),
       ('PDP.060523.001', 'DV.001.004', 4),
       ('PDP.060523.002', 'DV.001.005', 5),
       ('PDP.060523.002', 'DV.002.001', 1),
       ('PDP.060523.002', 'DV.002.002', 2),
       ('PDP.060523.002', 'DV.002.003', 3),
       ('PDP.060723.001', 'DV.002.004', 4),
       ('PDP.060723.001', 'DV.002.005', 5),
       ('PDP.060723.001', 'DV.001.001', 1),
       ('PDP.070723.001', 'DV.001.002', 2),
       ('PDP.070723.001', 'DV.001.003', 3)
GO

CREATE VIEW LoaiPhongView AS
SELECT maLoaiPhong  AS LoaiPhong_MaLoaiPhong,
       tenLoaiPhong AS LoaiPhong_TenLoaiPhong,
       trangThai    AS LoaiPhong_TrangThai
FROM LoaiPhong;
GO

CREATE VIEW PhongView AS
SELECT P.maPhong       AS Phong_MaPhong,
       P.tenPhong      AS Phong_TenPhong,
       P.sucChua       AS Phong_SucChua,
       P.maLoaiPhong   AS Phong_MaLoaiPhong,
       P.trangThai     AS Phong_TrangThai,
       LP.maLoaiPhong  AS LoaiPhong_MaLoaiPhong,
       LP.tenLoaiPhong AS LoaiPhong_TenLoaiPhong,
       LP.trangThai    AS LoaiPhong_TrangThai
FROM Phong P
         JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong
GO

CREATE VIEW PhongTrongView AS
SELECT P.maPhong       AS Phong_MaPhong,
       P.tenPhong      AS Phong_TenPhong,
       P.sucChua       AS Phong_SucChua,
       P.maLoaiPhong   AS Phong_MaLoaiPhong,
       P.trangThai     AS Phong_TrangThai,
       LP.maLoaiPhong  AS LoaiPhong_MaLoaiPhong,
       LP.tenLoaiPhong AS LoaiPhong_TenLoaiPhong,
       LP.trangThai    AS LoaiPhong_TrangThai
FROM Phong P
         JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong
WHERE P.trangThai = 0;
GO

CREATE VIEW LichSuGiaPhongView AS
SELECT maLichSuGiaPhong AS LichSuGiaPhong_MaLichSuGiaPhong,
       ngayBatDau       AS LichSuGiaPhong_NgayBatDau,
       ngayKetThuc      AS LichSuGiaPhong_NgayKetThuc,
       thoiDiemBatDau   AS LichSuGiaPhong_ThoiDiemBatDau,
       thoiDiemKetThuc  AS LichSuGiaPhong_ThoiDiemKetThuc,
       gia              AS LichSuGiaPhong_Gia,
       maLoaiPhong      AS LichSuGiaPhong_MaLoaiPhong
FROM LichSuGiaPhong;
GO

CREATE VIEW NhanVienView AS
SELECT maNhanVien AS NhanVien_MaNhanVien,
       ten        AS NhanVien_Ten,
       chucVu     AS NhanVien_ChucVu,
       sdt        AS NhanVien_Sdt,
       email      AS NhanVien_Email,
       diaChi     AS NhanVien_DiaChi,
       trangThai  AS NhanVien_TrangThai
FROM NhanVien;
GO

CREATE VIEW TaiKhoanView AS
SELECT TK.maTaiKhoan  AS TaiKhoan_MaTaiKhoan,
       TK.tenTaiKhoan AS TaiKhoan_TenTaiKhoan,
       TK.matKhau     AS TaiKhoan_MatKhau,
       TK.trangThai   AS TaiKhoan_TrangThai,
       TK.maNhanVien  AS TaiKhoan_MaNhanVien,
       NV.maNhanVien  AS NhanVien_MaNhanVien,
       NV.ten         AS NhanVien_Ten,
       NV.chucVu      AS NhanVien_ChucVu,
       NV.sdt         AS NhanVien_SDT,
       NV.email       AS NhanVien_Email,
       NV.diaChi      AS NhanVien_DiaChi,
       NV.trangThai   AS NhanVien_TrangThai
FROM TaiKhoan TK
         LEFT JOIN NhanVien NV ON TK.maNhanVien = NV.maNhanVien;
GO

CREATE VIEW LoaiDichVuView AS
SELECT maLoaiDichVu  AS LoaiDichVu_MaLoaiDichVu,
       tenLoaiDichVu AS LoaiDichVu_TenLoaiDichVu,
       trangThai     AS LoaiDichVu_TrangThai
FROM LoaiDichVu;
GO

CREATE VIEW DichVuView AS
SELECT maDichVu     AS DichVu_MaDichVu,
       tenDichVu    AS DichVu_TenDichVu,
       soLuong      AS DichVu_SoLuong,
       maLoaiDichVu AS DichVu_MaLoaiDichVu,
       trangThai    AS DichVu_TrangThai
FROM DichVu;
GO

CREATE VIEW LichSuGiaDichVuView AS
SELECT maLichSuGiaDichVu AS LichSuGiaDichVu_MaLichSuGiaDichVu,
       ngayBatDau        AS LichSuGiaDichVu_NgayBatDau,
       ngayKetThuc       AS LichSuGiaDichVu_NgayKetThuc,
       thoiDiemBatDau    AS LichSuGiaDichVu_ThoiDiemBatDau,
       thoiDiemKetThuc   AS LichSuGiaDichVu_ThoiDiemKetThuc,
       gia               AS LichSuGiaDichVu_Gia,
       maDichVu          AS LichSuGiaDichVu_MaDichVu
FROM LichSuGiaDichVu;
GO

CREATE VIEW DichVuLichSuGiaByConditionTimeView AS
SELECT DV.maDichVu            AS DichVu_MaDichVu,
       DV.tenDichVu           AS DichVu_TenDichVu,
       DV.soLuong             AS DichVu_SoLuong,
       DV.maLoaiDichVu        AS DichVu_MaLoaiDichVu,
       DV.trangThai           AS DichVu_TrangThai,
       LGDV.maLichSuGiaDichVu AS LichSuGiaDichVu_MaLichSuGiaDichVu,
       LGDV.ngayBatDau        AS LichSuGiaDichVu_NgayBatDau,
       LGDV.ngayKetThuc       AS LichSuGiaDichVu_NgayKetThuc,
       LGDV.thoiDiemBatDau    AS LichSuGiaDichVu_ThoiDiemBatDau,
       LGDV.thoiDiemKetThuc   AS LichSuGiaDichVu_ThoiDiemKetThuc,
       LGDV.gia               AS LichSuGiaDichVu_Gia,
       LDV.maLoaiDichVu       AS LoaiDichVu_MaLoaiDichVu,
       LDV.tenLoaiDichVu      AS LoaiDichVu_TenLoaiDichVu,
       LDV.trangThai          AS LoaiDichVu_TrangThai
FROM DichVu DV
         JOIN LichSuGiaDichVu LGDV ON DV.maDichVu = LGDV.maDichVu
         JOIN LoaiDichVu LDV ON DV.maLoaiDichVu = LDV.maLoaiDichVu
WHERE LGDV.ngayKetThuc IS NULL;
GO

CREATE VIEW DichVuLichSuGiaView AS
SELECT DV.maDichVu            AS DichVu_MaDichVu,
       DV.tenDichVu           AS DichVu_TenDichVu,
       DV.soLuong             AS DichVu_SoLuong,
       DV.maLoaiDichVu        AS DichVu_MaLoaiDichVu,
       DV.trangThai           AS DichVu_TrangThai,
       LGDV.maLichSuGiaDichVu AS LichSuGiaDichVu_MaLichSuGiaDichVu,
       LGDV.ngayBatDau        AS LichSuGiaDichVu_NgayBatDau,
       LGDV.ngayKetThuc       AS LichSuGiaDichVu_NgayKetThuc,
       LGDV.thoiDiemBatDau    AS LichSuGiaDichVu_ThoiDiemBatDau,
       LGDV.thoiDiemKetThuc   AS LichSuGiaDichVu_ThoiDiemKetThuc,
       LGDV.gia               AS LichSuGiaDichVu_Gia
FROM DichVu DV
         JOIN LichSuGiaDichVu LGDV ON DV.maDichVu = LGDV.maDichVu;
GO

CREATE VIEW KhuyenMaiView AS
SELECT maKhuyenMai     AS KhuyenMai_MaKhuyenMai,
       tenKhuyenMai    AS KhuyenMai_TenKhuyenMai,
       phanTram        AS KhuyenMai_PhanTram,
       gioiHan         AS KhuyenMai_GioiHan,
       ngayBatDau      AS KhuyenMai_NgayBatDau,
       ngayKetThuc     AS KhuyenMai_NgayKetThuc,
       thoiDiemBatDau  AS KhuyenMai_ThoiDiemBatDau,
       thoiDiemKetThuc AS KhuyenMai_ThoiDiemKetThuc
FROM KhuyenMai;
GO

CREATE VIEW KhachHangView AS
SELECT maKhachHang  AS KhachHang_MaKhachHang,
       tenKhachHang AS KhachHang_TenKhachHang,
       sdt          AS KhachHang_SDT
FROM KhachHang;
GO

CREATE VIEW HoaDonView AS
SELECT maHoaDon          AS HoaDon_MaHoaDon,
       tongTien          AS HoaDon_TongTien,
       NgayThanhToan     AS HoaDon_NgayThanhToan,
       thoiDiemThanhToan AS HoaDon_ThoiDiemThanhToan,
       maKhachHang       AS HoaDon_MaKhachHang,
       maNhanVien        AS HoaDon_MaNhanVien,
       maKhuyenMai       AS HoaDon_MaKhuyenMai
FROM HoaDon;
GO

CREATE VIEW PhieuDatPhongConditionByTimeView AS
SELECT PDP.maPhieuDatPhong  AS PhieuDatPhong_MaPhieuDatPhong,
       PDP.thoiGianBatDau   AS PhieuDatPhong_ThoiGianBatDau,
       PDP.thoiGianKetThuc  AS PhieuDatPhong_ThoiGianKetThuc,
       PDP.maHoaDon         AS PhieuDatPhong_MaHoaDon,
       PDP.maPhong          AS PhieuDatPhong_MaPhong,
       P.tenPhong           AS Phong_TenPhong,
       P.sucChua            AS Phong_SucChua,
       P.maLoaiPhong        AS Phong_MaLoaiPhong,
       LP.tenLoaiPhong      AS LoaiPhong_TenLoaiPhong,
       LP.trangThai         AS LoaiPhong_TrangThai,
       LSG.maLichSuGiaPhong AS LichSuGiaPhong_MaLichSuGiaPhong,
       LSG.ngayBatDau       AS LichSuGiaPhong_NgayBatDau,
       LSG.ngayKetThuc      AS LichSuGiaPhong_NgayKetThuc,
       LSG.thoiDiemBatDau   AS LichSuGiaPhong_ThoiDiemBatDau,
       LSG.thoiDiemKetThuc  AS LichSuGiaPhong_ThoiDiemKetThuc,
       LSG.gia              AS LichSuGiaPhong_Gia
FROM PhieuDatPhong PDP
         LEFT JOIN Phong P ON PDP.maPhong = P.maPhong
         LEFT JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong
         LEFT JOIN HoaDon HD ON PDP.maHoaDon = HD.maHoaDon
         LEFT JOIN LichSuGiaPhong LSG ON P.maLoaiPhong = LSG.maLoaiPhong
WHERE LSG.ngayBatDau <= HD.NgayThanhToan
  AND (LSG.ngayKetThuc >= HD.NgayThanhToan OR LSG.ngayKetThuc IS NULL)
GO

CREATE VIEW PhieuDatPhongView AS
SELECT PDP.maPhieuDatPhong  AS PhieuDatPhong_MaPhieuDatPhong,
       PDP.thoiGianBatDau   AS PhieuDatPhong_ThoiGianBatDau,
       PDP.thoiGianKetThuc  AS PhieuDatPhong_ThoiGianKetThuc,
       PDP.maHoaDon         AS PhieuDatPhong_MaHoaDon,
       PDP.maPhong          AS PhieuDatPhong_MaPhong,
       P.maPhong            AS Phong_MaPhong,
       P.tenPhong           AS Phong_TenPhong,
       P.sucChua            AS Phong_SucChua,
       P.maLoaiPhong        AS Phong_MaLoaiPhong,
       P.trangThai          AS Phong_TrangThai,
       LP.tenLoaiPhong      AS LoaiPhong_TenLoaiPhong,
       LP.trangThai         AS LoaiPhong_TrangThai,
       LP.maLoaiPhong       AS LoaiPhong_MaLoaiPhong,
       LSG.maLichSuGiaPhong AS LichSuGiaPhong_MaLichSuGiaPhong,
       LSG.ngayBatDau       AS LichSuGiaPhong_NgayBatDau,
       LSG.ngayKetThuc      AS LichSuGiaPhong_NgayKetThuc,
       LSG.thoiDiemBatDau   AS LichSuGiaPhong_ThoiDiemBatDau,
       LSG.thoiDiemKetThuc  AS LichSuGiaPhong_ThoiDiemKetThuc,
       LSG.gia              AS LichSuGiaPhong_Gia
FROM PhieuDatPhong PDP
         LEFT JOIN Phong P ON PDP.maPhong = P.maPhong
         LEFT JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong
         LEFT JOIN HoaDon HD ON PDP.maHoaDon = HD.maHoaDon
         LEFT JOIN LichSuGiaPhong LSG ON P.maLoaiPhong = LSG.maLoaiPhong
GO

CREATE VIEW ChiTietDatDichVuByConditionTimeView AS
SELECT HD.maHoaDon            AS HoaDon_MaHoaDon,
       CDD.maPhieuDatPhong    AS ChiTietDatDichVu_MaPhieuDatPhong,
       CDD.maDichVu           AS ChiTietDatDichVu_MaDichVu,
       CDD.soLuong            AS ChiTietDatDichVu_SoLuong,
       DV.maDichVu            AS DichVu_MaDichVu,
       DV.tenDichVu           AS DichVu_TenDichVu,
       DV.soLuong             AS DichVu_SoLuong,
       DV.maLoaiDichVu        AS DichVu_MaLoaiDichVu,
       DV.trangThai           AS DichVu_TrangThai,
       LDV.maLoaiDichVu       AS LoaiDichVu_MaLoaiDichVu,
       LDV.tenLoaiDichVu      AS LoaiDichVu_TenLoaiDichVu,
       LDV.trangThai          AS LoaiDichVu_TrangThai,
       LGDV.maLichSuGiaDichVu AS LichSuGiaDichVu_MaLichSuGiaDichVu,
       LGDV.ngayBatDau        AS LichSuGiaDichVu_NgayBatDau,
       LGDV.ngayKetThuc       AS LichSuGiaDichVu_NgayKetThuc,
       LGDV.thoiDiemBatDau    AS LichSuGiaDichVu_ThoiDiemBatDau,
       LGDV.thoiDiemKetThuc   AS LichSuGiaDichVu_ThoiDiemKetThuc,
       LGDV.gia               AS LichSuGiaDichVu_Gia
FROM ChiTietDatDichVu CDD
         JOIN DichVu DV ON CDD.maDichVu = DV.maDichVu
         JOIN LoaiDichVu LDV ON DV.maLoaiDichVu = LDV.maLoaiDichVu
         JOIN PhieuDatPhong PDT ON CDD.maPhieuDatPhong = PDT.maPhieuDatPhong
         JOIN HoaDon HD ON PDT.maHoaDon = HD.maHoaDon
         JOIN LichSuGiaDichVu LGDV ON DV.maDichVu = LGDV.maDichVu
WHERE LGDV.ngayBatDau <= HD.NgayThanhToan
  AND (LGDV.ngayKetThuc >= HD.NgayThanhToan OR LGDV.ngayKetThuc IS NULL);
GO

CREATE VIEW ChiTietDatDichVuView AS
SELECT HD.maHoaDon         AS HoaDon_MaHoaDon,
       CDD.maPhieuDatPhong AS ChiTietDatDichVu_MaPhieuDatPhong,
       CDD.maDichVu        AS ChiTietDatDichVu_MaDichVu,
       CDD.soLuong         AS ChiTietDatDichVu_SoLuong,
       DV.maDichVu         AS DichVu_MaDichVu,
       DV.tenDichVu        AS DichVu_TenDichVu,
       DV.soLuong          AS DichVu_SoLuong,
       DV.maLoaiDichVu     AS DichVu_MaLoaiDichVu,
       DV.trangThai        AS DichVu_TrangThai,
       LDV.maLoaiDichVu    AS LoaiDichVu_MaLoaiDichVu,
       LDV.tenLoaiDichVu   AS LoaiDichVu_TenLoaiDichVu,
       LDV.trangThai       AS LoaiDichVu_TrangThai,
       LGDV.gia            AS LichSuGiaDichVu_Gia
FROM ChiTietDatDichVu CDD
         JOIN DichVu DV ON CDD.maDichVu = DV.maDichVu
         JOIN LoaiDichVu LDV ON DV.maLoaiDichVu = LDV.maLoaiDichVu
         JOIN PhieuDatPhong PDT ON CDD.maPhieuDatPhong = PDT.maPhieuDatPhong
         JOIN HoaDon HD ON PDT.maHoaDon = HD.maHoaDon
         JOIN LichSuGiaDichVu LGDV ON DV.maDichVu = LGDV.maDichVu
GO

CREATE VIEW HoaDonDetailsView AS
SELECT HD.maHoaDon          AS HoaDon_MaHoaDon,
       HD.tongTien          AS HoaDon_TongTien,
       HD.NgayThanhToan     AS HoaDon_NgayThanhToan,
       HD.thoiDiemThanhToan AS HoaDon_ThoiDiemThanhToan,
       HD.maKhachHang       AS HoaDon_MaKhachHang,
       HD.maNhanVien        AS HoaDon_MaNhanVien,
       HD.maKhuyenMai       AS HoaDon_MaKhuyenMai,
       KH.maKhachHang       AS KhachHang_MaKhachHang,
       KH.tenKhachHang      AS KhachHang_TenKhachHang,
       KH.sdt               AS KhachHang_SDT,
       NV.maNhanVien        AS NhanVien_MaNhanVien,
       NV.ten               AS NhanVien_Ten,
       NV.chucVu            AS NhanVien_ChucVu,
       NV.sdt               AS NhanVien_SDT,
       NV.email             AS NhanVien_Email,
       NV.diaChi            AS NhanVien_DiaChi,
       NV.trangThai         AS NhanVien_TrangThai,
       KM.maKhuyenMai       AS KhuyenMai_MaKhuyenMai,
       KM.tenKhuyenMai      AS KhuyenMai_TenKhuyenMai,
       KM.phanTram          AS KhuyenMai_PhanTram,
       KM.gioiHan           AS KhuyenMai_GioiHan,
       KM.ngayBatDau        AS KhuyenMai_NgayBatDau,
       KM.ngayKetThuc       AS KhuyenMai_NgayKetThuc,
       KM.thoiDiemBatDau    AS KhuyenMai_ThoiDiemBatDau,
       KM.thoiDiemKetThuc   AS KhuyenMai_ThoiDiemKetThuc
FROM HoaDon HD
         LEFT JOIN KhachHang KH ON HD.maKhachHang = KH.maKhachHang
         LEFT JOIN NhanVien NV ON HD.maNhanVien = NV.maNhanVien
         LEFT JOIN KhuyenMai KM ON HD.maKhuyenMai = KM.maKhuyenMai;
GO

CREATE PROCEDURE GetPhieuDatPhongByMaHoaDon @MaHoaDon VARCHAR(255)
AS
BEGIN
    SELECT *
    FROM PhieuDatPhongView
    WHERE PhieuDatPhong_MaHoaDon = @MaHoaDon;
END;

GO

CREATE PROCEDURE GetChiTietDatDichVuByPhieuDatPhong @MaPhieuDatPhong VARCHAR(14)
AS
BEGIN
    SELECT *
    FROM ChiTietDatDichVuByConditionTimeView
    WHERE ChiTietDatDichVu_MaPhieuDatPhong = @MaPhieuDatPhong;
END;
GO

CREATE PROCEDURE GetPhongByTenAndLoaiPhong @tenPhong NVARCHAR(255),
                                           @maLoaiPhong VARCHAR(5) = NULL
AS
BEGIN
    IF @maLoaiPhong IS NOT NULL
        BEGIN
            SELECT *
            FROM PhongView
            WHERE Phong_TenPhong LIKE '%' + @tenPhong + '%'
              AND Phong_MaLoaiPhong = @maLoaiPhong;
        END
    ELSE
        BEGIN
            SELECT *
            FROM PhongView
            WHERE Phong_TenPhong LIKE '%' + @tenPhong + '%';
        END
END;
GO

CREATE PROCEDURE FindCustomerByPhoneNumber @sdt NVARCHAR(15)
AS
BEGIN
    SELECT *
    FROM KhachHangView
    WHERE KhachHang_SDT = @sdt;
END;
GO

CREATE PROCEDURE GetLichSuGiaDichVuByMaDichVu @MaDichVu VARCHAR(14)
AS
BEGIN
    SELECT *
    FROM LichSuGiaDichVuView
    WHERE LichSuGiaDichVu_MaDichVu = @MaDichVu;
END;
GO

CREATE PROCEDURE InsertIntoLoaiPhong @maLoaiPhong VARCHAR(5),
                                     @tenLoaiPhong NVARCHAR(255),
                                     @trangThai TINYINT
AS
BEGIN
    INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, trangThai)
    VALUES (@maLoaiPhong, @tenLoaiPhong, @trangThai)
END;
GO

CREATE PROCEDURE InsertIntoPhong @maPhong VARCHAR(7),
                                 @tenPhong NVARCHAR(255),
                                 @sucChua INT,
                                 @maLoaiPhong VARCHAR(5),
                                 @trangThai TINYINT
AS
BEGIN
    INSERT INTO Phong (maPhong, tenPhong, sucChua, maLoaiPhong, trangThai)
    VALUES (@maPhong, @tenPhong, @sucChua, @maLoaiPhong, @trangThai)
END;
GO

CREATE PROCEDURE InsertIntoLichSuGiaPhong @maLichSuGiaPhong VARCHAR(13),
                                          @ngayBatDau DATE,
                                          @ngayKetThuc DATE,
                                          @thoiDiemBatDau TIME,
                                          @thoiDiemKetThuc TIME,
                                          @gia FLOAT,
                                          @maLoaiPhong VARCHAR(5)
AS
BEGIN
    INSERT INTO LichSuGiaPhong (maLichSuGiaPhong, ngayBatDau, ngayKetThuc, thoiDiemBatDau, thoiDiemKetThuc, gia,
                                maLoaiPhong)
    VALUES (@maLichSuGiaPhong, @ngayBatDau, @ngayKetThuc, @thoiDiemBatDau, @thoiDiemKetThuc, @gia, @maLoaiPhong)
END;
GO

CREATE PROCEDURE InsertIntoNhanVien @maNhanVien VARCHAR(8),
                                    @ten NVARCHAR(255),
                                    @chucVu NVARCHAR(255),
                                    @sdt VARCHAR(10),
                                    @email NVARCHAR(255),
                                    @diaChi NVARCHAR(255),
                                    @trangThai TINYINT
AS
BEGIN
    INSERT INTO NhanVien (maNhanVien, ten, chucVu, sdt, email, diaChi, trangThai)
    VALUES (@maNhanVien, @ten, @chucVu, @sdt, @email, @diaChi, @trangThai)
END;
GO

CREATE PROCEDURE InsertIntoTaiKhoan @maTaiKhoan VARCHAR(8),
                                    @tenTaiKhoan NVARCHAR(255),
                                    @matKhau NVARCHAR(255),
                                    @trangThai NVARCHAR(20),
                                    @maNhanVien VARCHAR(8)
AS
BEGIN
    INSERT INTO TaiKhoan (maTaiKhoan, tenTaiKhoan, matKhau, trangThai, maNhanVien)
    VALUES (@maTaiKhoan, @tenTaiKhoan, @matKhau, @trangThai, @maNhanVien)
END;
GO

CREATE PROCEDURE InsertIntoLoaiDichVu @maLoaiDichVu VARCHAR(6),
                                      @tenLoaiDichVu NVARCHAR(255),
                                      @trangThai TINYINT
AS
BEGIN
    INSERT INTO LoaiDichVu (maLoaiDichVu, tenLoaiDichVu, trangThai)
    VALUES (@maLoaiDichVu, @tenLoaiDichVu, @trangThai)
END;
GO

CREATE PROCEDURE InsertIntoDichVu @maDichVu VARCHAR(10),
                                  @tenDichVu NVARCHAR(255),
                                  @soLuong INT,
                                  @maLoaiDichVu VARCHAR(6),
                                  @trangThai TINYINT
AS
BEGIN
    INSERT INTO DichVu (maDichVu, tenDichVu, soLuong, maLoaiDichVu, trangThai)
    VALUES (@maDichVu, @tenDichVu, @soLuong, @maLoaiDichVu, @trangThai)
END;
GO

CREATE PROCEDURE InsertIntoLichSuGiaDichVu @maLichSuGiaDichVu VARCHAR(14),
                                           @ngayBatDau DATE,
                                           @ngayKetThuc DATE,
                                           @thoiDiemBatDau TIME,
                                           @thoiDiemKetThuc TIME,
                                           @gia FLOAT,
                                           @maDichVu VARCHAR(10)
AS
BEGIN
    INSERT INTO LichSuGiaDichVu (maLichSuGiaDichVu, ngayBatDau, ngayKetThuc, thoiDiemBatDau, thoiDiemKetThuc, gia,
                                 maDichVu)
    VALUES (@maLichSuGiaDichVu, @ngayBatDau, @ngayKetThuc, @thoiDiemBatDau, @thoiDiemKetThuc, @gia, @maDichVu)
END;
GO

CREATE PROCEDURE InsertIntoKhuyenMai @maKhuyenMai VARCHAR(13),
                                     @tenKhuyenMai NVARCHAR(255),
                                     @phanTram FLOAT,
                                     @gioiHan FLOAT,
                                     @ngayBatDau DATE,
                                     @ngayKetThuc DATE,
                                     @thoiDiemBatDau TIME,
                                     @thoiDiemKetThuc TIME
AS
BEGIN
    INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, phanTram, gioiHan, ngayBatDau, ngayKetThuc, thoiDiemBatDau,
                           thoiDiemKetThuc)
    VALUES (@maKhuyenMai, @tenKhuyenMai, @phanTram, @gioiHan, @ngayBatDau, @ngayKetThuc, @thoiDiemBatDau,
            @thoiDiemKetThuc)
END;
GO

CREATE PROCEDURE InsertIntoKhachHang @maKhachHang VARCHAR(13),
                                     @tenKhachHang NVARCHAR(255),
                                     @sdt NVARCHAR(10)
AS
BEGIN
    INSERT INTO KhachHang (maKhachHang, tenKhachHang, sdt)
    VALUES (@maKhachHang, @tenKhachHang, @sdt)
END;
GO

CREATE PROCEDURE InsertIntoHoaDon @maHoaDon VARCHAR(14),
                                  @tongTien FLOAT,
                                  @NgayThanhToan DATE,
                                  @thoiDiemThanhToan TIME,
                                  @maKhachHang VARCHAR(13),
                                  @maNhanVien VARCHAR(8),
                                  @maKhuyenMai VARCHAR(13)
AS
BEGIN
    INSERT INTO HoaDon (maHoaDon, tongTien, NgayThanhToan, thoiDiemThanhToan, maKhachHang, maNhanVien, maKhuyenMai)
    VALUES (@maHoaDon, @tongTien, @NgayThanhToan, @thoiDiemThanhToan, @maKhachHang, @maNhanVien, @maKhuyenMai)
END;
GO

CREATE PROCEDURE InsertIntoPhieuDatPhong @maPhieuDatPhong VARCHAR(14),
                                         @thoiGianBatDau TIME,
                                         @thoiGianKetThuc TIME,
                                         @maHoaDon VARCHAR(14),
                                         @maPhong VARCHAR(7)
AS
BEGIN
    INSERT INTO PhieuDatPhong (maPhieuDatPhong, thoiGianBatDau, thoiGianKetThuc, maHoaDon, maPhong)
    VALUES (@maPhieuDatPhong, @thoiGianBatDau, @thoiGianKetThuc, @maHoaDon, @maPhong)
END;
GO

CREATE PROCEDURE InsertIntoChiTietDatDichVu @maPhieuDatPhong VARCHAR(14),
                                            @maDichVu VARCHAR(10),
                                            @soLuong INT
AS
BEGIN
    INSERT INTO ChiTietDatDichVu (maPhieuDatPhong, maDichVu, soLuong)
    VALUES (@maPhieuDatPhong, @maDichVu, @soLuong)
END;
GO

CREATE PROCEDURE GetHoaDonPaged @PageNumber INT,
                                @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetHoaDonPagedByMaHoaDon
    @MaHoaDon VARCHAR(20),
    @PageNumber INT,
    @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    WHERE HoaDon_MaHoaDon LIKE '%' + @MaHoaDon + '%'
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO
CREATE PROCEDURE GetHoaDonPagedByTenKhachHangLike
    @TenKhachHang NVARCHAR(255),
    @PageNumber INT,
    @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    WHERE KhachHang_TenKhachHang LIKE N'%' + @TenKhachHang + N'%'
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetHoaDonPagedByDateRange
    @FromDay DATE,
    @ToDay DATE,
    @PageNumber INT,
    @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    WHERE HoaDon_NgayThanhToan BETWEEN @FromDay AND @ToDay
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetDichVuPaged @PageNumber INT,
                                @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM DichVu
    ORDER BY maDichVu
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetNhanVienPaged @PageNumber INT,
                                  @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM NhanVien
    ORDER BY maNhanVien
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetKhachHangPaged @PageNumber INT,
                                   @RowsPerPage INT
AS
BEGIN
    DECLARE @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM KhachHang
    ORDER BY maKhachHang
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
