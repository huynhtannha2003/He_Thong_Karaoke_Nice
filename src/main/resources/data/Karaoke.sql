USE
    master
GO
DROP
    DATABASE Karaoke
GO
CREATE
    DATABASE Karaoke
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
    hinhAnh      VARCHAR(255),
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
    maHoaDon          VARCHAR(13) PRIMARY KEY,
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
    maPhieuDatPhong VARCHAR(15) PRIMARY KEY,
    thoiGianBatDau  TIME,
    thoiGianKetThuc TIME,
    maHoaDon        VARCHAR(13),
    maPhong         VARCHAR(7),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon (maHoaDon),
    FOREIGN KEY (maPhong) REFERENCES Phong (maPhong)
);
GO
CREATE TABLE ChiTietDatDichVu
(
    maPhieuDatPhong VARCHAR(15),
    maDichVu        VARCHAR(10),
    soLuong         INT,
    PRIMARY KEY (maPhieuDatPhong, maDichVu),
    FOREIGN KEY (maPhieuDatPhong) REFERENCES PhieuDatPhong (maPhieuDatPhong),
    FOREIGN KEY (maDichVu) REFERENCES DichVu (maDichVu)
);


INSERT INTO LoaiPhong
VALUES ('LP001', N'Thường', 1),
       ('LP002', N'Vip', 1)
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
VALUES ('GP.041123.01', '2018-01-01', NULL, '6:00', '12:00', 100000, 'LP001'),
       ('GP.041123.02', '2018-01-01', NULL, '18:00', '23:00', 200000, 'LP002')
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
VALUES ('DV.001.001', N'Trái cây', 20, 'LDV001', 1, 'traicay.png'),
       ('DV.001.002', N'Mỳ xào hải sản', 10, 'LDV001', 1, 'myxao.png'),
       ('DV.001.003', N'Cá điêu hồng chiên xù', 15, 'LDV001', 1, 'caDieuHong.png'),
       ('DV.001.004', N'Bánh ngọt', 30, 'LDV001', 1, 'banhNgot.png'),
       ('DV.001.005', N'Bánh kem', 10, 'LDV001', 1, 'banhKem.png'),
       ('DV.002.001', N'Coca Cola', 420, 'LDV002', 1, 'cocaCola.png'),
       ('DV.002.002', N'Heniken', 320, 'LDV002', 1, 'heineiken.png'),
       ('DV.002.003', N'Sting', 100, 'LDV002', 1, 'sting.png'),
       ('DV.002.004', N'Trà ô long', 50, 'LDV002', 1, 'traOLong.png'),
       ('DV.002.005', N'Wine', 30, 'LDV002', 1, 'wine.png')
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
VALUES ('KH.000000.000', N'Khách vãng lai', '0000000000'),
       ('KH.041123.001', N'Nguyễn Văn A', '0345678912'),
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
VALUES ('PDP.040123.0001', '15:00', '18:00', 'HD.040123.001', 'P0101'),
       ('PDP.040123.0002', '10:00', '12:00', 'HD.040123.002', 'P0203'),
       ('PDP.050123.0001', '8:00', '9:00', 'HD.050123.001', 'P0102'),
       ('PDP.050523.0001', '15:00', '22:00', 'HD.050523.002', 'P0202'),
       ('PDP.060523.0001', '18:00', '21:00', 'HD.060523.001', 'P0101'),
       ('PDP.060523.0002', '12:00', '15:00', 'HD.060523.002', 'P0204'),
       ('PDP.060723.0001', '8:00', '10:00', 'HD.060723.001', 'P0205'),
       ('PDP.070723.0001', '10:00', '15:00', 'HD.070723.001', 'P0105')
GO

INSERT INTO ChiTietDatDichVu
VALUES ('PDP.040123.0001', 'DV.001.001', 1),
       ('PDP.040123.0001', 'DV.001.002', 2),
       ('PDP.040123.0001', 'DV.001.003', 3),
       ('PDP.040123.0002', 'DV.001.004', 4),
       ('PDP.040123.0002', 'DV.001.005', 5),
       ('PDP.040123.0002', 'DV.002.001', 1),
       ('PDP.040123.0002', 'DV.002.002', 2),
       ('PDP.040123.0002', 'DV.002.003', 3),
       ('PDP.050123.0001', 'DV.002.004', 4),
       ('PDP.050123.0001', 'DV.002.005', 5),
       ('PDP.050123.0001', 'DV.001.001', 1),
       ('PDP.050123.0001', 'DV.001.002', 2),
       ('PDP.050523.0001', 'DV.001.003', 3),
       ('PDP.050523.0001', 'DV.001.004', 4),
       ('PDP.050523.0001', 'DV.001.005', 5),
       ('PDP.050523.0001', 'DV.002.001', 1),
       ('PDP.050523.0001', 'DV.002.002', 2),
       ('PDP.050523.0001', 'DV.002.003', 3),
       ('PDP.060523.0001', 'DV.002.004', 4),
       ('PDP.060523.0001', 'DV.002.005', 5),
       ('PDP.060523.0001', 'DV.001.001', 1),
       ('PDP.060523.0001', 'DV.001.002', 2),
       ('PDP.060523.0001', 'DV.001.003', 3),
       ('PDP.060523.0001', 'DV.001.004', 4),
       ('PDP.060523.0002', 'DV.001.005', 5),
       ('PDP.060523.0002', 'DV.002.001', 1),
       ('PDP.060523.0002', 'DV.002.002', 2),
       ('PDP.060523.0002', 'DV.002.003', 3),
       ('PDP.060723.0001', 'DV.002.004', 4),
       ('PDP.060723.0001', 'DV.002.005', 5),
       ('PDP.060723.0001', 'DV.001.001', 1),
       ('PDP.070723.0001', 'DV.001.002', 2),
       ('PDP.070723.0001', 'DV.001.003', 3)
GO

CREATE VIEW LoaiPhongView AS
SELECT maLoaiPhong  AS LoaiPhong_MaLoaiPhong,
       tenLoaiPhong AS LoaiPhong_TenLoaiPhong,
       trangThai    AS LoaiPhong_TrangThai
FROM LoaiPhong;
GO
CREATE VIEW LichSuGiaPhongView AS
SELECT LP.maLoaiPhong       AS LoaiPhong_MaLoaiPhong,
       LP.tenLoaiPhong      AS LoaiPhong_TenLoaiPhong,
       LP.trangThai         AS LoaiPhong_TrangThai,
       LSGP.maLoaiPhong     AS LichSuGiaPhong_MaLoaiPhong,
       LSGP.ngayBatDau      AS LichSuGiaPhong_NgayBatDau,
       LSGP.ngayKetThuc     AS LichSuGiaPhong_NgayKetThuc,
       LSGP.thoiDiemBatDau  AS LichSuGiaPhong_ThoiDiemBatDau,
       LSGP.thoiDiemKetThuc AS LichSuGiaPhong_ThoiDiemKetThuc,
       LSGP.gia             AS LichSuGiaPhong_Gia
FROM LoaiPhong LP
         Join LichSuGiaPhong LSGP on LP.maLoaiPhong = LSGP.maLoaiPhong
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

CREATE VIEW PhongLoaiPhongLichSuaGiaByConditionTimeView AS
SELECT P.maPhong            AS Phong_MaPhong,
       P.tenPhong           AS Phong_TenPhong,
       P.sucChua            AS Phong_SucChua,
       P.maLoaiPhong        AS Phong_MaLoaiPhong,
       P.trangThai          AS Phong_TrangThai,
       LP.maLoaiPhong       AS LoaiPhong_MaLoaiPhong,
       LP.tenLoaiPhong      AS LoaiPhong_TenLoaiPhong,
       LP.trangThai         AS LoaiPhong_TrangThai,
       LSGP.maLoaiPhong     AS LichSuGiaPhong_MaLoaiPhong,
       LSGP.ngayBatDau      AS LichSuGiaPhong_NgayBatDau,
       LSGP.ngayKetThuc     AS LichSuGiaPhong_NgayKetThuc,
       LSGP.thoiDiemBatDau  AS LichSuGiaPhong_ThoiDiemBatDau,
       LSGP.thoiDiemKetThuc AS LichSuGiaPhong_ThoiDiemKetThuc,
       LSGP.gia             AS LichSuGiaPhong_Gia
FROM Phong P
         JOIN LoaiPhong LP ON P.maLoaiPhong = LP.maLoaiPhong
         JOIN LichSuGiaPhong LSGP on LP.maLoaiPhong = LSGP.maLoaiPhong
WHERE LSGP.ngayKetThuc IS NULL;
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

CREATE VIEW LichSuGiaPhongByConditionTimeyView AS
SELECT maLichSuGiaPhong AS LichSuGiaPhong_MaLichSuGiaPhong,
       ngayBatDau       AS LichSuGiaPhong_NgayBatDau,
       ngayKetThuc      AS LichSuGiaPhong_NgayKetThuc,
       thoiDiemBatDau   AS LichSuGiaPhong_ThoiDiemBatDau,
       thoiDiemKetThuc  AS LichSuGiaPhong_ThoiDiemKetThuc,
       gia              AS LichSuGiaPhong_Gia,
       maLoaiPhong      AS LichSuGiaPhong_MaLoaiPhong
FROM LichSuGiaPhong
WHERE ngayKetThuc IS NULL;
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
       trangThai    AS DichVu_TrangThai,
       hinhAnh      AS DichVu_HinhAnh
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
       DV.hinhAnh             AS DichVu_HinhAnh,
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
       DV.hinhAnh             AS DichVu_HinhAnh,
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
       LSG.gia              AS LichSuGiaPhong_Gia,
       LSG.maLoaiPhong      AS LichSuGiaPhong_MaLoaiPhong
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
       DV.hinhAnh             AS DichVu_HinhAnh,
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
SELECT HD.maHoaDon            AS HoaDon_MaHoaDon,
       CDD.maPhieuDatPhong    AS ChiTietDatDichVu_MaPhieuDatPhong,
       CDD.maDichVu           AS ChiTietDatDichVu_MaDichVu,
       CDD.soLuong            AS ChiTietDatDichVu_SoLuong,
       DV.maDichVu            AS DichVu_MaDichVu,
       DV.tenDichVu           AS DichVu_TenDichVu,
       DV.soLuong             AS DichVu_SoLuong,
       DV.maLoaiDichVu        AS DichVu_MaLoaiDichVu,
       DV.trangThai           AS DichVu_TrangThai,
       DV.hinhAnh             AS DichVu_HinhAnh,
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

CREATE VIEW HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView AS
SELECT HD.maHoaDon          AS HoaDon_MaHoaDon,
       HD.tongTien          AS HoaDon_TongTien,
       HD.NgayThanhToan     AS HoaDon_NgayThanhToan,
       HD.thoiDiemThanhToan AS HoaDon_ThoiDiemThanhToan,
       HD.maKhachHang       AS HoaDon_MaKhachHang,
       HD.maNhanVien        AS HoaDon_MaNhanVien,
       HD.maKhuyenMai       AS HoaDon_MaKhuyenMai,
       PDP.maPhieuDatPhong  AS PhieuDatPhong_MaPhieuDatPhong,
       PDP.thoiGianBatDau   AS PhieuDatPhong_ThoiGianBatDau,
       PDP.thoiGianKetThuc  AS PhieuDatPhong_ThoiGianKetThuc,
       PDP.maPhong          AS PhieuDatPhong_MaPhong,
       NV.maNhanVien        AS NhanVien_MaNhanVien,
       NV.ten               AS NhanVien_Ten,
       NV.chucVu            AS NhanVien_ChucVu,
       NV.sdt               AS NhanVien_SDT,
       NV.email             AS NhanVien_Email,
       NV.diaChi            AS NhanVien_DiaChi,
       NV.trangThai         AS NhanVien_TrangThai,
       KH.maKhachHang       AS KhachHang_MaKhachHang,
       KH.tenKhachHang      AS KhachHang_TenKhachHang,
       KH.sdt               AS KhachHang_SDT,
       KM.maKhuyenMai       AS KhuyenMai_MaKhuyenMai,
       KM.tenKhuyenMai      AS KhuyenMai_TenKhuyenMai,
       KM.phanTram          AS KhuyenMai_PhanTram,
       KM.gioiHan           AS KhuyenMai_GioiHan,
       KM.ngayBatDau        AS KhuyenMai_NgayBatDau,
       KM.ngayKetThuc       AS KhuyenMai_NgayKetThuc,
       KM.thoiDiemBatDau    AS KhuyenMai_ThoiDiemBatDau,
       KM.thoiDiemKetThuc   AS KhuyenMai_ThoiDiemKetThuc,
       P.maPhong            AS Phong_MaPhong,
       P.tenPhong           AS Phong_TenPhong,
       P.sucChua            AS Phong_SucChua,
       P.maLoaiPhong        AS Phong_MaLoaiPhong,
       P.trangThai          AS Phong_TrangThai,
       LP.tenLoaiPhong      AS LoaiPhong_TenLoaiPhong,
       LP.trangThai         AS LoaiPhong_TrangThai,
       LP.maLoaiPhong       AS LoaiPhong_MaLoaiPhong
FROM HoaDon HD
         JOIN PhieuDatPhong PDP ON HD.maHoaDon = PDP.maHoaDon
         JOIN Phong P ON PDP.maPhong = P.maPhong
         LEFT JOIN NhanVien NV ON HD.maNhanVien = NV.maNhanVien
         LEFT JOIN KhachHang KH ON HD.maKhachHang = KH.maKhachHang
         LEFT JOIN KhuyenMai KM on HD.maKhuyenMai = KM.maKhuyenMai
         LEFT JOIN LoaiPhong LP on P.maLoaiPhong = LP.maLoaiPhong;
GO

CREATE PROCEDURE GetPhieuDatPhongByMaHoaDon @MaHoaDon VARCHAR(255)
AS
BEGIN
    SELECT *
--     FROM PhieuDatPhongView
    FROM PhieuDatPhongConditionByTimeView
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

CREATE PROCEDURE GetChiTietDatDichVuByPhieuDatPhongNew @MaPhieuDatPhong VARCHAR(15)
AS
BEGIN
    SELECT *
    FROM ChiTietDatDichVuView
    where ChiTietDatDichVu_MaPhieuDatPhong = @MaPhieuDatPhong;
END;
GO

CREATE PROCEDURE GetPhongByTenAndLoaiPhong @tenPhong NVARCHAR(255),
                                           @maLoaiPhong VARCHAR(5) = NULL
AS
BEGIN
    SELECT *
    FROM PhongLoaiPhongLichSuaGiaByConditionTimeView
    WHERE Phong_TenPhong LIKE '%' + @tenPhong + '%'
      AND (@maLoaiPhong IS NULL OR Phong_MaLoaiPhong = @maLoaiPhong);
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
                                  @trangThai TINYINT,
                                  @hinhAnh VARCHAR(255)
AS
BEGIN
    INSERT INTO DichVu (maDichVu, tenDichVu, soLuong, maLoaiDichVu, trangThai, hinhAnh)
    VALUES (@maDichVu, @tenDichVu, @soLuong, @maLoaiDichVu, @trangThai, @hinhAnh)
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

CREATE PROCEDURE GetHoaDonByMaHoaDon(@MaHoaDon VARCHAR(13))
AS
BEGIN
    SELECT *
    FROM HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView
    WHERE HoaDon_MaHoaDon = @MaHoaDon
END
GO

CREATE PROCEDURE GetNewHoaDonByMaPhong(@MaPhong VARCHAR(7))
AS
BEGIN
    SELECT HoaDon_MaHoaDon,
           HoaDon_TongTien,
           HoaDon_NgayThanhToan,
           HoaDon_ThoiDiemThanhToan,
           HoaDon_MaKhachHang,
           HoaDon_MaNhanVien,
           HoaDon_MaKhuyenMai,
           NhanVien_MaNhanVien,
           NhanVien_Ten,
           NhanVien_ChucVu,
           NhanVien_DiaChi,
           NhanVien_Email,
           NhanVien_Email,
           NhanVien_SDT,
           NhanVien_TrangThai,
           KhachHang_MaKhachHang,
           KhachHang_TenKhachHang,
           KhachHang_SDT,
           KhuyenMai_MaKhuyenMai,
           KhuyenMai_TenKhuyenMai,
           KhuyenMai_PhanTram,
           KhuyenMai_GioiHan,
           KhuyenMai_NgayBatDau,
           KhuyenMai_NgayKetThuc,
           KhuyenMai_ThoiDiemBatDau,
           KhuyenMai_ThoiDiemKetThuc
    FROM HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView
    WHERE PhieuDatPhong_MaPhong = @MaPhong
      AND HoaDon_ThoiDiemThanhToan IS NULL;
END;
GO

CREATE PROCEDURE GetNewHoaDonByTenKhachHang @TenKhachHang NVARCHAR(255)
AS
BEGIN
    SELECT A.*,
           P.maPhong       AS Phong_MaPhong,
           P.tenPhong      AS Phong_TenPhong,
           P.sucChua       AS Phong_SucChua,
           P.maLoaiPhong   AS Phong_MaLoaiPhong,
           P.trangThai     AS Phong_TrangThai,
           LP.tenLoaiPhong AS LoaiPhong_TenLoaiPhong,
           LP.trangThai    AS LoaiPhong_TrangThai,
           LP.maLoaiPhong  AS LoaiPhong_MaLoaiPhong
    FROM HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView A
             JOIN Phong P
                  ON A.PhieuDatPhong_MaPhong = P.maPhong
             JOIN LoaiPhong LP on P.maLoaiPhong = LP.maLoaiPhong
    WHERE KhachHang_TenKhachHang = N'' + @TenKhachHang
      AND PhieuDatPhong_ThoiGianKetThuc IS NULL;
END;
GO


CREATE PROCEDURE GetHoaDonPaged @PageNumber INT,
                                @RowsPerPage INT
AS
BEGIN
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetPhongByCondition(
    @trangThai INT,
    @maLoaiPhong VARCHAR(5),
    @tenPhong NVARCHAR(255)
)
AS
BEGIN
    SELECT *
    FROM PhongLoaiPhongLichSuaGiaByConditionTimeView
    WHERE (@trangThai = -1 OR Phong_TrangThai = @trangThai)
      AND (@maLoaiPhong IS NULL OR Phong_MaLoaiPhong = @maLoaiPhong)
      AND (@tenPhong IS NULL OR Phong_TenPhong LIKE '%' + @tenPhong + '%');
END;
GO

CREATE PROCEDURE GetHoaDonPagedByMaHoaDon @MaHoaDon VARCHAR(20),
                                          @PageNumber INT,
                                          @RowsPerPage INT
AS
BEGIN
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    WHERE HoaDon_MaHoaDon LIKE '%' + @MaHoaDon + '%'
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;

GO
CREATE PROCEDURE GetHoaDonPagedByTenKhachHangLike @TenKhachHang NVARCHAR(255),
                                                  @PageNumber INT,
                                                  @RowsPerPage INT
AS
BEGIN
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM HoaDonDetailsView
    WHERE KhachHang_TenKhachHang LIKE N'%' + @TenKhachHang + N'%'
    ORDER BY HoaDon_NgayThanhToan
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE PROCEDURE GetHoaDonPagedByDateRange @FromDay DATE,
                                           @ToDay DATE,
                                           @PageNumber INT,
                                           @RowsPerPage INT
AS
BEGIN
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

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
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

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
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

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
    DECLARE
        @OffsetRows INT = (@PageNumber - 1) * @RowsPerPage;

    SELECT *
    FROM KhachHang
    ORDER BY maKhachHang
    OFFSET @OffsetRows ROWS FETCH NEXT @RowsPerPage ROWS ONLY;
END;
GO

CREATE TRIGGER UpdateDichVuQuantity
    ON ChiTietDatDichVu
    AFTER INSERT
    AS
BEGIN
    SET
        NOCOUNT ON;
    UPDATE DichVu
    SET SoLuong = DV.SoLuong - i.SoLuong
    FROM DichVu DV
             INNER JOIN inserted i
                        ON DV.MaDichVu = i.MaDichVu;
END;
GO

CREATE FUNCTION GeneratePhieuDatPhongID()
    RETURNS VARCHAR(15)
AS
BEGIN
    DECLARE
        @ngayTao NVARCHAR(2) = FORMAT(GETDATE(), 'dd');
    DECLARE
        @thangTao NVARCHAR(2) = FORMAT(GETDATE(), 'MM');
    DECLARE
        @namTao NVARCHAR(2) = FORMAT(GETDATE(), 'yy');

    DECLARE
        @soThuTuPhieuDatPhong INT;
    SELECT @soThuTuPhieuDatPhong = ISNULL(MAX(CAST(SUBSTRING(maPhieuDatPhong, 12, 4) AS INT)), 0) + 1
    FROM PhieuDatPhong
    WHERE SUBSTRING(maPhieuDatPhong, 5, 6) = @ngayTao + @thangTao + @namTao;

    RETURN 'PDP.' + @ngayTao + @thangTao + @namTao + '.' +
           RIGHT('0000' + CAST(@soThuTuPhieuDatPhong AS VARCHAR(4)), 4);
END;
GO

CREATE PROCEDURE BookKaraokeRoom(
    @maKhachHang VARCHAR(13),
    @maNhanVien VARCHAR(8),
    @maPhong VARCHAR(7),
    @thoiGianBatDau TIME,
    @ngayThanhToan DATE
)
AS
BEGIN
    DECLARE
        @maHoaDon VARCHAR(13);
    DECLARE
        @maPhieuDatPhong VARCHAR(15);

    DECLARE
        @ngayTao NVARCHAR(2) = FORMAT(GETDATE(), 'dd');
    DECLARE
        @thangTao NVARCHAR(2) = FORMAT(GETDATE(), 'MM');
    DECLARE
        @namTao NVARCHAR(2) = FORMAT(GETDATE(), 'yy');

    DECLARE
        @soThuTuHoaDon INT;
    SELECT @soThuTuHoaDon = ISNULL(MAX(CAST(SUBSTRING(maHoaDon, 11, 3) AS INT)), 0) + 1
    FROM HoaDon
    WHERE SUBSTRING(maHoaDon, 4, 6) = @ngayTao + @thangTao + @namTao;

    SET
        @maHoaDon = 'HD.' + @ngayTao + @thangTao + @namTao + '.' + RIGHT('000' + CAST(@soThuTuHoaDon AS VARCHAR(3)), 3);

    INSERT INTO HoaDon (maHoaDon, maKhachHang, maNhanVien, NgayThanhToan, tongTien)
    VALUES (@maHoaDon, @maKhachHang, @maNhanVien, @ngayThanhToan, 0);

    DECLARE
        @soThuTuPhieuDatPhong INT;
    SELECT @soThuTuPhieuDatPhong = ISNULL(MAX(CAST(SUBSTRING(maPhieuDatPhong, 12, 4) AS INT)), 0) + 1
    FROM PhieuDatPhong
    WHERE SUBSTRING(maPhieuDatPhong, 5, 6) = @ngayTao + @thangTao + @namTao;
    SET
        @maPhieuDatPhong = 'PDP.' + @ngayTao + @thangTao + @namTao + '.' +
                           RIGHT('0000' + CAST(@soThuTuPhieuDatPhong AS VARCHAR(4)), 4);

    INSERT INTO PhieuDatPhong (maPhieuDatPhong, thoiGianBatDau, maHoaDon, maPhong)
    VALUES (@maPhieuDatPhong, @thoiGianBatDau, @maHoaDon, @maPhong);

    IF NOT EXISTS (SELECT 1
                   FROM HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView
                   WHERE KhachHang_MaKhachHang = @maKhachHang
                     AND (
                           (CAST(PhieuDatPhong_ThoiGianBatDau AS TIME) > CAST(GETDATE() AS TIME)
                               AND CONVERT(DATE, HoaDon_NgayThanhToan) >= CONVERT(DATE, GETDATE()))
                           OR
                           (CAST(PhieuDatPhong_ThoiGianBatDau AS TIME) <= CAST(GETDATE() AS TIME)
                               AND CONVERT(DATE, HoaDon_NgayThanhToan) > CONVERT(DATE, GETDATE()))
                       ))
        BEGIN
            UPDATE Phong
            SET trangThai = 1
            WHERE maPhong = @maPhong;
        END

END;

GO
CREATE PROCEDURE ChangeKarokeRoom(
    @maHoaDon VARCHAR(13),
    @maPhong VARCHAR(7)
)
AS
BEGIN
    DECLARE
        @currentTime TIME = FORMAT(GETDATE(), 'HH:mm:ss');
    DECLARE
        @maPhieuDatPhong VARCHAR(15);
    DECLARE
        @oldPhongID VARCHAR(7);

    SELECT @oldPhongID = pdp.maPhong
    FROM PhieuDatPhong pdp
    WHERE pdp.maHoaDon = @maHoaDon
      AND pdp.thoiGianKetThuc IS NULL;

    UPDATE Phong
    SET trangThai = 0
    WHERE maPhong = @oldPhongID;

    UPDATE PhieuDatPhong
    SET thoiGianKetThuc = @currentTime
    WHERE maHoaDon = @maHoaDon
      AND thoiGianKetThuc IS NULL;

    SET
        @maPhieuDatPhong = dbo.GeneratePhieuDatPhongID();
    INSERT INTO PhieuDatPhong (maPhieuDatPhong, thoiGianBatDau, maHoaDon, maPhong)
    VALUES (@maPhieuDatPhong, @currentTime, @maHoaDon, @maPhong);

    UPDATE Phong
    SET trangThai = 1
    WHERE maPhong = @maPhong
END;
GO

CREATE PROCEDURE UpdatePaymentDetails(
    @hoaDonID NVARCHAR(13),
    @tongTien FLOAT,
    @thoiDiemThanhToan DATETIME,
    @phieuDatPhongID NVARCHAR(15),
    @thoiGianKetThuc DATETIME,
    @maKhuyenMai NVARCHAR(13) = NULL -- Default to NULL if not provided
)
AS
BEGIN
    UPDATE HoaDon
    SET tongTien          = @tongTien,
        thoiDiemThanhToan = @thoiDiemThanhToan,
        maKhuyenMai       = @maKhuyenMai
    WHERE MaHoaDon = @hoaDonID;

    UPDATE PhieuDatPhong
    SET thoiGianKetThuc = @thoiGianKetThuc
    WHERE MaPhieuDatPhong = @phieuDatPhongID;

    UPDATE Phong
    SET trangThai = 0
    WHERE maPhong IN (SELECT maPhong FROM PhieuDatPhong WHERE MaPhieuDatPhong = @phieuDatPhongID);
END;
GO

CREATE VIEW DanhSachPhieu_View AS
SELECT pdp.maPhieuDatPhong, hd.maHoaDon, p.maPhong, kh.tenKhachHang, kh.sdt, pdp.thoiGianBatDau
FROM PhieuDatPhong pdp
         join Phong p ON pdp.maPhong = p.maPhong
         join HoaDon hd ON pdp.maHoaDon = hd.maHoaDon
         join KhachHang kh ON hd.maKhachHang = kh.maKhachHang
WHERE tongTien IS NULL
GO

CREATE PROCEDURE xoaPhieuDatPhongCho(
    @maHoaDon NVARCHAR(13)
)
AS
BEGIN
    DECLARE
        @maPhieuDatPhong VARCHAR(15);
    DECLARE
        @maPhong VARCHAR(7);

    SET
        @maPhieuDatPhong = (SELECT maPhieuDatPhong FROM DanhSachPhieu_View WHERE maHoaDon = @maHoaDon);
    SET
        @maPhong = (SELECT maPhong FROM PhieuDatPhong WHERE maHoaDon = @maHoaDon);

    DELETE
    FROM ChiTietDatDichVu
    WHERE maPhieuDatPhong = @maPhieuDatPhong;
    DELETE
    FROM PhieuDatPhong
    WHERE maHoaDon = @maHoaDon;

    UPDATE PHONG
    SET trangThai = 0
    WHERE maPhong = @maPhong;
END;
GO

CREATE PROCEDURE [dbo].[BookRoomBefore](
    @maKhachHang VARCHAR(13),
    @maNhanVien VARCHAR(8),
    @maPhong VARCHAR(7),
    @thoiGianBatDau TIME,
    @ngayThanhToan DATE
)
AS
BEGIN
    DECLARE @maHoaDon VARCHAR(13);
    DECLARE @maPhieuDatPhong VARCHAR(15);
    DECLARE @ngayTao NVARCHAR(2) = FORMAT(GETDATE(), 'dd');
    DECLARE @thangTao NVARCHAR(2) = FORMAT(GETDATE(), 'MM');
    DECLARE @namTao NVARCHAR(2) = FORMAT(GETDATE(), 'yy');
    DECLARE @soThuTuHoaDon INT;
    SELECT @soThuTuHoaDon = ISNULL(MAX(CAST(SUBSTRING(maHoaDon, 11, 3) AS INT)), 0) + 1
    FROM HoaDon
    WHERE SUBSTRING(maHoaDon, 4, 6) = @ngayTao + @thangTao + @namTao;

    SET @maHoaDon = 'HD.' + @ngayTao + @thangTao + @namTao + '.' + RIGHT('000' + CAST(@soThuTuHoaDon AS VARCHAR(3)), 3);

    INSERT INTO HoaDon (maHoaDon, maKhachHang, maNhanVien, NgayThanhToan)
    VALUES (@maHoaDon, @maKhachHang, @maNhanVien, @ngayThanhToan);

    DECLARE @soThuTuPhieuDatPhong INT;
    SELECT @soThuTuPhieuDatPhong = ISNULL(MAX(CAST(SUBSTRING(maPhieuDatPhong, 12, 4) AS INT)), 0) + 1
    FROM PhieuDatPhong
    WHERE SUBSTRING(maPhieuDatPhong, 5, 6) = @ngayTao + @thangTao + @namTao;

    SET
        @maPhieuDatPhong = 'PDP.' + @ngayTao + @thangTao + @namTao + '.' +
                           RIGHT('0000' + CAST(@soThuTuPhieuDatPhong AS VARCHAR(4)), 4);

    INSERT INTO PhieuDatPhong (maPhieuDatPhong, thoiGianBatDau, maHoaDon, maPhong)
    VALUES (@maPhieuDatPhong, @thoiGianBatDau, @maHoaDon, @maPhong);

    UPDATE Phong
    SET trangThai = 2
    WHERE maPhong = @maPhong;
END;
GO
CREATE PROCEDURE UpdateTrangThaiAndThoiGianBatDau @maHoaDon VARCHAR(13), @maPhong VARCHAR(7)
AS
BEGIN
    UPDATE HoaDon
    SET tongTien = 0
    WHERE maHoaDon = @maHoaDon

    UPDATE Phong
    SET trangThai = 1
    WHERE maPhong = @maPhong;

    UPDATE PhieuDatPhong
    SET thoiGianBatDau = GETDATE()
    WHERE maPhong = @maPhong;
END;
GO

CREATE PROCEDURE GetHoaDonBySDTAndTime(@SDT NVARCHAR(255))
AS
BEGIN
    SELECT *
    FROM HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView
    WHERE KhachHang_SDT = @SDT
      AND HoaDon_TongTien IS NULL
END;
GO

CREATE PROCEDURE InsertOrUpdateChiTietDatDichVu @p_maPhieuDatPhong VARCHAR(15),
                                                @p_maDichVu VARCHAR(10),
                                                @p_soLuong INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @existingCount INT;

    SELECT @existingCount = COUNT(*)
    FROM ChiTietDatDichVu
    WHERE maPhieuDatPhong = @p_maPhieuDatPhong
      AND maDichVu = @p_maDichVu;

    IF @existingCount > 0
        BEGIN
            UPDATE ChiTietDatDichVu
            SET soLuong = @p_soLuong
            WHERE maPhieuDatPhong = @p_maPhieuDatPhong
              AND maDichVu = @p_maDichVu;
        END
    ELSE
        BEGIN
            INSERT INTO ChiTietDatDichVu (maPhieuDatPhong, maDichVu, soLuong)
            VALUES (@p_maPhieuDatPhong, @p_maDichVu, @p_soLuong);
        END;
END;
GO

CREATE PROCEDURE GetTodayPhieuDatPhongCho
AS
BEGIN
    SELECT *
    FROM HoaDonPhieuDatPhongPhongNhanVienKhachHangKhuyenMaiView
    WHERE CONVERT(DATE, HoaDon_NgayThanhToan) = CONVERT(DATE, GETDATE())
      AND Phong_TrangThai = 2;
END;
GO

CREATE PROCEDURE GetDichVuByMaDichVu @MaDichVu VARCHAR(20)
AS
BEGIN
    SELECT *
    FROM DichVuLichSuGiaByConditionTimeView
    WHERE DichVu_MaDichVu LIKE '%' + @MaDichVu + '%'
END;
GO

CREATE PROCEDURE GetDichVuByTenDichVu @TenDichVu VARCHAR(20)
AS
BEGIN
    SELECT *
    FROM DichVuLichSuGiaByConditionTimeView
    WHERE DichVu_TenDichVu LIKE N'%' + @TenDichVu + N'%'
END;
GO

CREATE PROCEDURE GetDichVuByGia @Gia FLOAT
AS
BEGIN
    SELECT *
    from DichVuLichSuGiaByConditionTimeView
    WHERE LichSuGiaDichVu_Gia = '%' + @Gia + '%'
END;
GO




