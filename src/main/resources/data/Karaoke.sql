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
       ('P0105', '01.005', 10, 'LP001', 0),
    ('P0201', '02.001', 10, 'LP001', 0),
    ('P0202', '02.002', 8, 'LP001', 0),
    ('P0203', '02.003', 12, 'LP001', 0),
    ('P0204', '02.004', 10, 'LP001', 0),
    ('P0205', '02.005', 8, 'LP001', 0),
    ('P0301', '03.001', 10, 'LP001', 0),
    ('P0302', '03.002', 8, 'LP001', 0),
    ('P0303', '03.003', 12, 'LP001', 0),
    ('P0304', '03.004', 10, 'LP001', 0),
    ('P0305', '03.005', 8, 'LP001', 0),
    ('P0401', '04.001', 10, 'LP001', 0),
    ('P0402', '04.002', 8, 'LP001', 0),
    ('P0403', '04.003', 12, 'LP001', 0),
    ('P0404', '04.004', 10, 'LP001', 0),
    ('P0405', '04.005', 8, 'LP001', 0),
    ('P0501', '05.001', 10, 'LP001', 0),
    ('P0502', '05.002', 8, 'LP001', 0),
    ('P0503', '05.003', 12, 'LP001', 0),
    ('P0504', '05.004', 10, 'LP001', 0),
    ('P0505', '05.005', 8, 'LP001', 0),
    ('P0601', '06.001', 10, 'LP002', 0),
    ('P0602', '06.002', 8, 'LP002', 0),
    ('P0603', '06.003', 12, 'LP002', 0),
    ('P0604', '06.004', 10, 'LP002', 0),
    ('P0605', '06.005', 8, 'LP002', 0);
GO

INSERT INTO LichSuGiaPhong
VALUES ('GP.041123.01', '2018-01-01', NULL, '6:00', '12:00', 100000, 'LP001'),
       ('GP.041123.02', '2018-01-01', NULL, '18:00', '23:00', 200000, 'LP002');
GO

INSERT INTO NhanVien
VALUES ('NV230001', N'Trần Trung Tiến', N'Người quản lý', '0986148209', N'tien@gmail.com',N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
       ('NV230002', N'La Minh Tâm', N'Người quản lý', '0886700046', N'tam@gmail.com',N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
       ('NV230003', N'Vũ Quốc Huy', N'Nhân viên', '0366895412', N'huy@gmail.com',N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
       ('NV230004', N'Lương Tấn Đạt', N'Nhân viên', '0962145578', N'dat@gmail.com', N'12 Nguyễn Văn Bảo, Gò Vấp, Hồ Chí Minh', 1),
	   ('NV230005', 'Vũ Hoàng Anh', 'Nhân viên', '0936456789', 'anh.vu@gmail.com', '213 Bình Lợi, Quận Bình Thạnh, Hồ Chí Minh', 0),
       ('NV230006', 'Nguyễn Thị Loan', 'Nhân viên', '0945567890', 'loan.nguyen@gmail.com', '415 Phan Huy Ích, Quận Tân Bình, Hồ Chí Minh', 1),
       ('NV230007', 'Vũ Thị Hương', 'Nhân viên', '0954678901', 'huong.vu@gmail.com', '117 Tô Ngọc Vân, Quận Thủ Đức, Hồ Chí Minh', 0),
       ('NV230008', 'Đỗ Văn Hải', 'Nhân viên', '0963789012', 'hai.do@gmail.com', '181 Cô Bắc, Quận 9, Hồ Chí Minh', 1),
       ('NV230009', 'Lê Thị Mai', 'Nhân viên', '0972890123', 'mai.le@gmail.com', '116 Nguyễn Văn Nghi , Quận Gò Vấp, Hồ Chí Minh', 0),
       ('NV230010', 'Mai Văn Tài', 'Nhân viên', '0981901234', 'tai.mai@gmail.com', '12 Nguyễn Gia Trí, Quận Bình Thạnh, Hồ Chí Minh', 1),
       ('NV230011', 'Đỗ Văn Khôi', 'Nhân viên', '0990012345', 'khoi.do@gmail.com', '456/12 Ung Văn Khiêm, Quận Bình Thạnh, Hồ Chí Minh', 0),
       ('NV230012', 'Nguyễn Văn Quang', 'Nhân viên', '0911123456', 'quang.nguyen@gmail.com', '765 Cộng Hòa, Quận Tân Bình, Hồ Chí Minh', 1),
       ('NV230013', 'Phạm Thị Quỳnh', 'Nhân viên', '0922234567', 'quynh.pham@gmail.com', '132 Trần Quốc Tuấn, Quận Gò Vấp, Hồ Chí Minh', 0),
       ('NV230014', 'Võ Văn Bảo', 'Nhân viên', '0933345678', 'bao.vo@gmail.com', '698 Quan Trung, Quận Gò Vấp, Hồ Chí Minh', 1),
       ('NV230015', 'Đặng Thị Thơ', 'Nhân viên', '0944456789', 'tho.dang@gmail.com', '44 Phạm Văn Chiêu, Quận Gò Vấp, Hồ Chí Minh', 0),
       ('NV230016', 'Trần Văn Hòa', 'Nhân viên', '0955567890', 'hoa.tran@gmail.com', '11 Điện Biên Phủ, Quận 1, Hồ Chí Minh', 1),
       ('NV230017', 'Nguyễn Thị Hạnh', 'Nhân viên', '0966678901', 'hanh.nguyen@gmail.com', '997/17 Xa lộ Hà Nội, Quận 2, Hồ Chí Minh', 0),
       ('NV230018', 'Lê Văn Tâm', 'Nhân viên', '0977789012', 'tam.le@gmail.com', '333 Kha Vạn Cân, Quận Thủ Đức, Hồ Chí Minh', 1),
       ('NV230019', 'Trần Thị Nga', 'Nhân viên', '0988890123', 'nga.tran@gmail.com', '404 Linh Đông, Quận Thủ Đức, Hồ Chí Minh', 0),
       ('NV230020', 'Nguyễn Văn Sơn', 'Nhân viên', '0999901234', 'son.nguyen@gmail.com', '945/55 Phạm Văn Đồng, Quận Thủ Đức, Hồ Chí Minh', 1),
	   ('NV230021', 'Nguyễn Thị Hằng', 'Nhân viên', '0987654321', 'hang.nguyen@gmail.com', '123 Điện Biên Phủ, Quận 1, Hồ Chí Minh', 0),
	   ('NV230022', 'Nguyễn Thị Anh', 'Nhân viên', '0987654321', 'anh.nguyen@gmail.com', '123 Điện Biên Phủ, Quận 1, Hồ Chí Minh', 0),
       ('NV230023', 'Trần Văn Bảo', 'Nhân viên', '0909123456', 'bao.tran@gmail.com', '456 Lê Lai, Quận 3, Hồ Chí Minh', 1),
       ('NV230024', 'Lê Thị Cẩm', 'Nhân viên', '0918234567', 'cam.le@gmail.com', '789 Nguyễn Trãi, Quận 5, Hồ Chí Minh', 0),
       ('NV230025', 'Phạm Văn Dũng', 'Nhân viên', '0927345678', 'dung.pham@gmail.com', '1011 CMT8, Quận 10, Hồ Chí Minh', 1),
       ('NV230026', 'Vũ Hoàng Em', 'Nhân viên', '0936456789', 'em.vu@gmail.com', '1213 Bình Thạnh, Quận Bình Thạnh, Hồ Chí Minh', 0),
       ('NV230027', 'Ngô Thị Ên', 'Nhân viên', '0945567890', 'en.ngo@gmail.com', '1415 Cộng Hòa, Quận Tân Bình, Hồ Chí Minh', 1),
       ('NV230028', 'Vũ Thị Phương', 'Nhân viên', '0954678901', 'phuong.vu@gmail.com', '617 Kha Vạn Cân, Quận Thủ Đức, Hồ Chí Minh', 0),
       ('NV230029', 'Đỗ Văn Giang', 'Nhân viên', '0963789012', 'giang.do@gmail.com', '119 Phan Xích Long, Quận Phú Nhuận, Hồ Chí Minh', 1),
       ('NV230030', 'Lê Thị Hà', 'Nhân viên', '0972890123', 'ha.le@gmail.com', '202 Lê Đức Thọ, Quận Gò Vấp, Hồ Chí Minh', 0),
       ('NV230031', 'Mai Văn I', 'Nhân viên', '0981901234', 'i.mai@gmail.com', '32 Lê Đức Thọ, Quận Gò Vấp, Hồ Chí Minh', 1),
       ('NV230032', 'Đỗ Văn Khải', 'Nhân viên', '0990012345', 'khai.do@gmail.com', '425 Lê Văn Thọ, Quận Gò Vấp, Hồ Chí Minh', 0),
       ('NV230033', 'Nguyễn Văn Lâm', 'Nhân viên', '0911123456', 'lam.nguyen@gmail.com', '233 Đặng Thùy Trâm, Quận Bình Thạnh, Hồ Chí Minh', 1),
       ('NV230034', 'Phạm Thị Mỹ', 'Nhân viên', '0922234567', 'my.pham@gmail.com', '445/67 Trường Chinh, Quận Tân Bình, Hồ Chí Minh', 0),
       ('NV230035', 'Võ Văn Nghĩa', 'Nhân viên', '0933345678', 'nghia.vo@gmail.com', '303 Âu Cơ, Quận Tân Bình, Hồ Chí Minh', 1),
       ('NV230036', 'Đặng Thị Oanh', 'Nhân viên', '0944456789', 'oanh.dang@gmail.com', '667 Âu Cơ, Quận Tân Bình, Hồ Chí Minh', 0),
       ('NV230037', 'Trần Văn Phúc', 'Nhân viên', '0955567890', 'phuc.tran@gmail.com', '1108 Nguyễn Kiệm, Quận Gò Vấp, Hồ Chí Minh', 1),
       ('NV230038', 'Nguyễn Thị Quỳnh', 'Nhân viên', '0966678901', 'quynh.nguyen@gmail.com', '223 Bạch Đằng, Quận Gò Vấp, Hồ Chí Minh', 0),
       ('NV230039', 'Lê Văn Rồng', 'Nhân viên', '0977789012', 'rong.le@gmail.com', '98 Nguyễn Xí, Quận Bình Thạnh, Hồ Chí Minh', 1),
       ('NV230040', 'Trần Thị Sáng', 'Nhân viên', '0988890123', 'sang.tran@gmail.com', '298 Lương Ngọc Quyến, Quận Gò Vấp, Hồ Chí Minh', 0);
GO

INSERT INTO TaiKhoan
VALUES ('TK23001', N'trantrungtien', N'Tien123456@', 1, 'NV230001'),
       ('TK23002', N'laminhtam', N'Tam123456@', 1, 'NV230002'),
       ('TK23003', N'vuquochuy', N'Huy123456@', 1, 'NV230003'),
       ('TK23004', N'luongtandat', N'Dat123456@', 1, 'NV230004'),
	   ('TK23005', 'vuhoanganh', 'Anh123456@', 1, 'NV230005'),
       ('TK23006', 'nguyenthiloan', 'Loan123456@', 0, 'NV230006'),
       ('TK23007', 'vuthihuong', 'Huong123456@', 1, 'NV230007'),
       ('TK23008', 'dovanhai', 'Hai123456@', 0, 'NV230008'),
       ('TK23009', 'lethimai', 'Mai123456@', 1, 'NV230009'),
       ('TK23010', 'maivantai', 'Tai123456@', 0, 'NV230010'),
       ('TK23011', 'dovankhoi', 'Khoi123456@', 1, 'NV230011'),
       ('TK23012', 'nguyenvanquang', 'Quang123456@', 0, 'NV230012'),
       ('TK23013', 'phamthiquynh', 'Quynh123456@', 1, 'NV230013'),
       ('TK23014', 'vovanbao', 'Bao123456@', 0, 'NV230014'),
       ('TK23015', 'dangthitho', 'Tho123456@', 1, 'NV230015'),
       ('TK23016', 'tranvanhoa', 'Hoa123456@', 0, 'NV230016'),
       ('TK23017', 'nguyenthithanh', 'Thanh123456@', 1, 'NV230017'),
       ('TK23018', 'levantam', 'Tam123456@', 0, 'NV230018'),
       ('TK23019', 'tranthinga', 'Nga123456@', 1, 'NV230019'),
       ('TK23020', 'nguyenvanson', 'Son123456@', 0, 'NV230020'),
       ('TK23021', 'nguyenthihang', 'Hang123456@', 1, 'NV230021'),
       ('TK23022', 'nguyenthianh', 'Anh123456@', 0, 'NV230022'),
       ('TK23023', 'tranvanbao', 'Bao123456@', 1, 'NV230023'),
       ('TK23024', 'lethicam', 'Cam123456@', 0, 'NV230024'),
       ('TK23025', 'phamvandung', 'Dung123456@', 1, 'NV230025'),
       ('TK23026', 'vuhoangem', 'Em123456@', 0, 'NV230026'),
       ('TK23027', 'ngothien', 'Thien123456@', 1, 'NV230027'),
       ('TK23028', 'vuphuong', 'Phuong123456@', 0, 'NV230028'),
       ('TK23029', 'dogiang', 'Giang123456@', 1, 'NV230029'),
       ('TK23030', 'lethiha', 'Ha123456@', 0, 'NV230030'),
       ('TK23031', 'maivani', 'I123456@', 1, 'NV230031'),
       ('TK23032', 'dovankhai', 'Khai123456@', 0, 'NV230032'),
       ('TK23033', 'nguyenvanlam', 'Lam123456@', 1, 'NV230033'),
       ('TK23034', 'phamthimy', 'My123456@', 0, 'NV230034'),
       ('TK23035', 'vovannghia', 'Nghia123456@', 1, 'NV230035'),
       ('TK23036', 'dangthioanh', 'Oanh123456@', 0, 'NV230036'),
       ('TK23037', 'tranvanphuc', 'Phuc123456@', 1, 'NV230037'),
       ('TK23038', 'nguyenthiquynh', 'Quynh123456@', 0, 'NV230038'),
       ('TK23039', 'levanrong', 'Rong123456@', 1, 'NV230039'),
       ('TK23040', 'tranthisang', 'Sang123456@', 0, 'NV230040');
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
       ('DV.002.005', N'Wine', 30, 'LDV002', 1, 'wine.png'),
	   ('DV.001.006', 'Bánh mì ốp la', 30, 'LDV001', 1, 'banhmi_opla.png'),
       ('DV.001.007', 'Cơm rang dưa bò', 25, 'LDV001', 0, 'com_rang_dua_bo.png'),
       ('DV.001.008', 'Gỏi cuốn tôm thịt', 40, 'LDV001', 1, 'goi_cuon_tom_thit.png'),
       ('DV.001.009', 'Bún riêu cua', 35, 'LDV001', 0, 'bun_rieu_cua.png'),
       ('DV.001.010', 'Xôi mặn', 30, 'LDV001', 1, 'xoi_man.png'),
       ('DV.002.006', 'Sinh tố dừa', 50, 'LDV002', 0, 'sinh_to_dua.png'),
       ('DV.002.007', 'Nước cam tươi', 45, 'LDV002', 1, 'nuoc_cam_tuoi.png'),
       ('DV.002.008', 'Trà đào', 60, 'LDV002', 0, 'tra_dao.png'),
       ('DV.002.009', 'Nước lọc', 55, 'LDV002', 1, 'nuoc_loc.png'),
       ('DV.002.010', 'Soda chanh', 40, 'LDV002', 0, 'soda_chanh.png'),
       ('DV.001.011', 'Bò lúc lắc', 25, 'LDV001', 0, 'bo_luc_lac.png'),
       ('DV.001.012', 'Bánh mì chảo', 40, 'LDV001', 1, 'banhmi_chao.png'),
       ('DV.001.013', 'Mì quảng gà', 35, 'LDV001', 0, 'mi_quang_ga.png'),
       ('DV.001.014', 'Bún bò Huế', 30, 'LDV001', 1, 'bun_bo_hue.png'),
       ('DV.002.011', 'Pepsi', 45, 'LDV002', 1, 'pepsi.png'),
       ('DV.002.012', '7UP', 60, 'LDV002', 0, '7up.png'),
       ('DV.002.013', 'Nước ngọt Fanta', 40, 'LDV002', 0, 'nuoc_ngot_fanta.png'),
       ('DV.001.015', 'Bún ốc', 30, 'LDV001', 1, 'bun_oc.png'),
	   ('DV.001.016', 'Bia tiger', 30, 'LDV002', 1, 'bia_tiger.png');
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
       ('GDV.061123014', '2018-01-01', NULL, '6:00', '12:00', 830000, 'DV.002.005'),
	   ('GDV.041123011', '2023-11-04', NULL, '6:00', '12:00', 80000, 'DV.001.006'),
       ('GDV.041123012', '2023-11-04', NULL, '12:01', '18:00', 75000, 'DV.001.007'),
       ('GDV.041123013', '2023-11-04', NULL, '18:01', '23:59', 90000, 'DV.001.008'),
       ('GDV.041123014', '2023-11-05', NULL, '00:00', '06:00', 85000, 'DV.001.009'),
       ('GDV.041123015', '2023-11-05', NULL, '06:01', '12:00', 80000, 'DV.001.010'),
       ('GDV.041123016', '2023-11-06', NULL, '12:01', '18:00', 75000, 'DV.002.006'),
       ('GDV.041123017', '2023-11-06', NULL, '18:01', '23:59', 90000, 'DV.002.007'),
       ('GDV.041123018', '2023-11-07', NULL, '00:00', '06:00', 85000, 'DV.002.008'),
       ('GDV.041123019', '2023-11-07', NULL, '6:01', '12:00', 80000, 'DV.002.009'),
       ('GDV.041123020', '2023-11-08', NULL, '12:01', '18:00', 75000, 'DV.002.010'),
       ('GDV.041123022', '2023-11-09', NULL, '00:00', '06:00', 85000, 'DV.001.011'),
       ('GDV.041123023', '2023-11-09', NULL, '06:01', '12:00', 80000, 'DV.001.012'),
       ('GDV.041123024', '2023-11-10', NULL, '12:01', '18:00', 75000, 'DV.001.013'),
       ('GDV.041123025', '2023-11-10', NULL, '18:01', '23:59', 90000, 'DV.001.014'),
       ('GDV.041123026', '2023-11-11', NULL, '00:00', '06:00', 85000, 'DV.002.011'),
       ('GDV.041123027', '2023-11-11', NULL, '6:01', '12:00', 80000, 'DV.002.012'),
       ('GDV.041123028', '2023-11-12', NULL, '12:01', '18:00', 75000, 'DV.002.013'),
       ('GDV.041123029', '2023-11-12', NULL, '18:01', '23:59', 90000, 'DV.001.015'),
       ('GDV.041123030', '2023-11-13', NULL, '00:00', '06:00', 85000, 'DV.001.016')
GO

INSERT INTO KhuyenMai
VALUES ('KM.041123.001', N'Happy time', 20, 5, '2023-11-04', '2023-11-20', '8:00', '23:00'),
       ('KM.051123.002', N'Cùng vui sinh nhật', 20, 10, '2023-11-05', '2023-11-05', '9:00', '21:00'),
       ('KM.061123.003', N'Đầu tuần', 50, 3, '2023-11-06', '2023-11-06', '6:00', '23:00'),
       ('KM.071123.004', N'Sáng thứ 3 ưu đãi', 30, 10, '2023-11-07', '2023-11-07', '6:00', '12:00'),
       ('KM.081123.005', N'Buổi tối vào hội', 20, 5, '2023-11-08', '2023-11-08', '18:00', '21:00'),
	   ('KM.041123.006', 'Super Deal', 15, 10, '2023-11-04', '2023-11-15', '08:00', '22:00'),
       ('KM.041123.007', 'Weekend Special', 25, 20, '2023-11-06', '2023-11-18', '10:00', '21:00'),
       ('KM.041123.008', 'Midnight Madness', 30, 15, '2023-11-08', '2023-11-21', '23:00', '03:00'),
       ('KM.041123.009', 'Lunch Bonanza', 10, 25, '2023-11-10', '2023-11-25', '12:00', '14:00'),
       ('KM.041123.010', 'Happy Hour', 20, 30, '2023-11-12', '2023-11-23', '17:00', '19:00'),
       ('KM.041123.011', 'Family Feast', 18, 15, '2023-11-14', '2023-11-26', '18:00', '20:00'),
       ('KM.041123.012', 'Sunday Funday', 12, 20, '2023-11-16', '2023-11-27', '14:00', '19:00'),
       ('KM.041123.013', 'Festive Delight', 22, 18, '2023-11-18', '2023-11-30', '09:00', '21:00'),
       ('KM.041123.014', 'Evening Elegance', 28, 22, '2023-11-20', '2023-12-02', '16:00', '20:00'),
       ('KM.041123.015', 'Date Night', 15, 12, '2023-11-22', '2023-12-05', '19:00', '23:00');
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
       ('KH.061123.004', N'Nguyễn Văn H', '0345678919'),
	   ('KH.000000.001', 'Khách vãng lai', '0000000000'),
	   ('KH.000000.002', 'Khách vãng lai', '0000000000'),
	   ('KH.000000.003', 'Khách vãng lai', '0000000000'),
       ('KH.041123.003', 'Lê Thị Hương', '0345678915'),
       ('KH.041123.004', 'Trần Minh Tuấn', '0345678916'),
       ('KH.041123.005', 'Phạm Thị Thảo', '0345678917'),
       ('KH.041123.006', 'Ngô Văn Hùng', '0345678918'),
       ('KH.041123.007', 'Mai Thị Ngọc', '0345678919'),
       ('KH.041123.008', 'Nguyễn Đình Quân', '0345678920'),
       ('KH.041123.009', 'Lê Thị Lan', '0345678921'),
       ('KH.041123.010', 'Vũ Văn Tâm', '0345678922'),
       ('KH.041123.011', 'Đỗ Thị Hạnh', '0345678923'),
       ('KH.041123.012', 'Hoàng Văn Hải', '0345678924'),
       ('KH.041123.013', 'Nguyễn Thị Thu', '0345678925'),
       ('KH.041123.014', 'Phan Văn Hùng', '0345678926'),
       ('KH.041123.015', 'Lê Văn Tùng', '0345678927'),
       ('KH.041123.016', 'Nguyễn Thị Thúy', '0345678928'),
       ('KH.041123.017', 'Trần Đình Long', '0345678929'),
       ('KH.041123.018', 'Lê Thị Phương', '0345678930'),
       ('KH.041123.019', 'Đặng Văn Quý', '0345678931'),
       ('KH.041123.020', 'Phạm Thị Hồng', '0345678932'),
       ('KH.041123.021', 'Nguyễn Văn Đức', '0345678933'),
       ('KH.041123.022', 'Trần Thị Hằng', '0345678934'),
       ('KH.041123.023', 'Lê Thị Mai', '0345678935'),
       ('KH.041123.024', 'Vũ Thị Loan', '0345678936'),
       ('KH.041123.025', 'Nguyễn Văn Hoàng', '0345678937'),
       ('KH.041123.026', 'Đỗ Văn Hùng', '0345678938'),
       ('KH.041123.027', 'Nguyễn Thị Tâm', '0345678939'),
       ('KH.041123.028', 'Lê Văn Dũng', '0345678940'),
       ('KH.041123.029', 'Trần Thị Loan', '0345678941'),
       ('KH.041123.030', 'Mai Văn Hưng', '0345678942');
GO

INSERT INTO HoaDon
VALUES ('HD.040123.001', 800000, '2023-01-04', '18:00', 'KH.041123.001', 'NV230003', 'KM.041123.001'),
       ('HD.040123.002', 120000, '2023-01-04', '12:00', 'KH.041123.002', 'NV230001', NULL),
       ('HD.050123.001', 300000, '2023-01-05', '9:00', 'KH.051123.001', 'NV230002', NULL),
       ('HD.050523.002', 520000, '2023-05-05', '22:00', 'KH.051123.002', 'NV230002', NULL),
       ('HD.060523.001', 180000, '2023-05-06', '21:00', 'KH.041123.001', 'NV230004', NULL),
       ('HD.060523.002', 420000, '2023-05-06', '15:00', 'KH.041123.002', 'NV230002', NULL),
       ('HD.060723.001', 100000, '2023-07-06', '10:00', 'KH.041123.002', 'NV230001', NULL),
       ('HD.070723.001', 800000, '2023-07-07', '15:00', 'KH.061123.004', 'NV230003', NULL),
       ('HD.040123.003', 250000, '2023-01-04', '13:00', 'KH.041123.003', 'NV230003', NULL),
       ('HD.040123.004', 180000, '2023-01-04', '14:00', 'KH.041123.004', 'NV230004', NULL),
       ('HD.040123.005', 300000, '2023-01-04', '15:00', 'KH.041123.005', 'NV230005', NULL),
       ('HD.040123.006', 400000, '2023-01-04', '16:00', 'KH.041123.006', 'NV230006', NULL),
       ('HD.040123.007', 550000, '2023-01-04', '17:00', 'KH.041123.007', 'NV230007', NULL),
       ('HD.040123.008', 420000, '2023-01-04', '18:00', 'KH.041123.008', 'NV230008', NULL),
       ('HD.040123.009', 280000, '2023-01-04', '19:00', 'KH.041123.009', 'NV230009', NULL),
       ('HD.040123.010', 350000, '2023-01-04', '20:00', 'KH.041123.010', 'NV230010', NULL),
	   ('HD.040123.011', 220000, '2023-01-04', '21:30', 'KH.041123.011', 'NV230011',NULL ),
    ('HD.040123.012', 330000, '2023-01-04', '22:45', 'KH.041123.012', 'NV230012', NULL),
    ('HD.040123.013', 440000, '2023-01-04', '23:30', 'KH.041123.013', 'NV230013',NULL ),
    ('HD.040123.014', 110000, '2023-01-04', '12:30', 'KH.041123.014', 'NV230014',NULL),
    ('HD.040123.015', 220000, '2023-01-04', '15:30', 'KH.041123.015', 'NV230015', NULL),
    ('HD.040123.016', 330000, '2023-01-04', '17:45', 'KH.041123.016', 'NV230016', NULL),
    ('HD.040123.017', 440000, '2023-01-04', '19:30', 'KH.041123.017', 'NV230017', NULL),
    ('HD.040123.018', 220000, '2023-01-04', '21:45', 'KH.041123.018', 'NV230018', NULL),
    ('HD.040123.019', 110000, '2023-01-04', '13:30', 'KH.041123.019', 'NV230019', NULL),
    ('HD.040123.020', 220000, '2023-01-04', '15:45', 'KH.041123.020', 'NV230020', NULL),
    ('HD.040123.021', 330000, '2023-01-04', '18:45', 'KH.041123.021', 'NV230021', NULL),
    ('HD.040123.022', 440000, '2023-01-04', '20:30', 'KH.041123.022', 'NV230022', NULL),
    ('HD.040123.023', 110000, '2023-01-04', '22:30', 'KH.041123.023', 'NV230023', NULL),
    ('HD.040123.024', 220000, '2023-01-04', '23:45', 'KH.041123.024', 'NV230024', NULL),
    ('HD.040123.025', 330000, '2023-01-04', '15:30', 'KH.041123.025', 'NV230025', NULL),
    ('HD.040123.026', 440000, '2023-01-04', '17:45', 'KH.041123.026', 'NV230026', NULL),
    ('HD.040123.027', 110000, '2023-01-04', '19:30', 'KH.041123.027', 'NV230027', NULL),
    ('HD.040123.028', 220000, '2023-01-04', '21:45', 'KH.041123.028', 'NV230028', NULL),
    ('HD.040123.029', 330000, '2023-01-04', '13:30', 'KH.041123.029', 'NV230029', NULL),
    ('HD.040123.030', 440000, '2023-01-04', '15:45', 'KH.041123.030', 'NV230030', NULL);

GO

INSERT INTO PhieuDatPhong
VALUES ('PDP.040123.0001', '15:00', '18:00', 'HD.040123.001', 'P0101'),
       ('PDP.040123.0002', '10:00', '12:00', 'HD.040123.002', 'P0203'),
       ('PDP.050123.0001', '8:00', '9:00', 'HD.050123.001', 'P0102'),
       ('PDP.050523.0001', '15:00', '22:00', 'HD.050523.002', 'P0202'),
       ('PDP.060523.0001', '18:00', '21:00', 'HD.060523.001', 'P0101'),
       ('PDP.060523.0002', '12:00', '15:00', 'HD.060523.002', 'P0204'),
       ('PDP.060723.0001', '8:00', '10:00', 'HD.060723.001', 'P0205'),
       ('PDP.070723.0001', '10:00', '15:00', 'HD.070723.001', 'P0105'),
       ('PDP.040123.0003', '12:00', '14:00', 'HD.040123.003', 'P0203'),
       ('PDP.040123.0004', '13:00', '15:00', 'HD.040123.004', 'P0204'),
       ('PDP.040123.0005', '14:00', '16:00', 'HD.040123.005', 'P0205'),
       ('PDP.040123.0006', '15:00', '17:00', 'HD.040123.006', 'P0301'),
       ('PDP.040123.0007', '16:00', '18:00', 'HD.040123.007', 'P0302'),
       ('PDP.040123.0008', '17:00', '19:00', 'HD.040123.008', 'P0304'),
       ('PDP.040123.0009', '18:00', '20:00', 'HD.040123.009', 'P0305'),
       ('PDP.040123.0010', '19:00', '21:00', 'HD.040123.010', 'P0401'),
	   ('PDP.040123.0011', '20:00', '22:00', 'HD.040123.011', 'P0402'),
    ('PDP.040123.0012', '21:00', '23:00', 'HD.040123.012', 'P0403'),
    ('PDP.040123.0013', '22:00', '23:59', 'HD.040123.013', 'P0404'),
    ('PDP.040123.0014', '10:00', '12:00', 'HD.040123.014', 'P0405'),
    ('PDP.040123.0015', '14:00', '16:00', 'HD.040123.015', 'P0205'),
    ('PDP.040123.0016', '16:00', '18:00', 'HD.040123.016', 'P0201'),
    ('PDP.040123.0017', '18:00', '20:00', 'HD.040123.017', 'P0102'),
    ('PDP.040123.0018', '20:00', '22:00', 'HD.040123.018', 'P0103'),
    ('PDP.040123.0019', '12:00', '14:00', 'HD.040123.019', 'P0104'),
    ('PDP.040123.0020', '14:00', '16:00', 'HD.040123.020', 'P0205'),
    ('PDP.040123.0021', '16:00', '18:00', 'HD.040123.021', 'P0601'),
    ('PDP.040123.0022', '18:00', '20:00', 'HD.040123.022', 'P0602'),
    ('PDP.040123.0023', '20:00', '22:00', 'HD.040123.023', 'P0603'),
    ('PDP.040123.0024', '22:00', '23:59', 'HD.040123.024', 'P0604'),
    ('PDP.040123.0025', '10:00', '12:00', 'HD.040123.025', 'P0605'),
    ('PDP.040123.0026', '14:00', '16:00', 'HD.040123.026', 'P0301'),
    ('PDP.040123.0027', '16:00', '18:00', 'HD.040123.027', 'P0101'),
    ('PDP.040123.0028', '18:00', '20:00', 'HD.040123.028', 'P0202'),
    ('PDP.040123.0029', '20:00', '22:00', 'HD.040123.029', 'P0505'),
    ('PDP.040123.0030', '22:00', '23:59', 'HD.040123.030', 'P0501');
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
	   ('PDP.040123.0011', 'DV.001.012', 2),
    ('PDP.040123.0012', 'DV.001.013', 3),
    ('PDP.040123.0013', 'DV.001.014', 4),
    ('PDP.040123.0014', 'DV.001.015', 1),
    ('PDP.040123.0015', 'DV.001.016', 2),
    ('PDP.040123.0016', 'DV.002.011', 3),
    ('PDP.040123.0017', 'DV.002.008', 4);
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
    IF
        @maLoaiPhong IS NOT NULL
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
CREATE PROCEDURE UpdateTrangThaiAndThoiGianBatDau @maHoaDon  VARCHAR(13), @maPhong VARCHAR(7)
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
CREATE FUNCTION GenerateEmployeeCode()
RETURNS VARCHAR(8)
AS
BEGIN
    DECLARE @stt INT
    DECLARE @formattedStt NVARCHAR(10)
    DECLARE @employeeCode NVARCHAR(10)

    -- Get the current count of records in the table
    SET @stt = (SELECT COUNT(*) + 2 FROM NhanVien)

    -- Determine the number of leading zeros based on the value of stt
    IF @stt >= 10
        SET @formattedStt = '00'
	ELSE IF @stt >= 100
	    SET @formattedStt = '0'
	ELSE IF @stt >= 1000
	    SET @formattedStt = ''
    ELSE IF @stt < 10
        SET @formattedStt = '000'
    SET @employeeCode = 'NV' + '23' + @formattedStt + CAST(@stt AS VARCHAR(5))

    RETURN @employeeCode
END;
GO
CREATE FUNCTION GenerateCustomerCode()
RETURNS NVARCHAR(20)
AS
BEGIN
    DECLARE @stt INT
    DECLARE @formattedStt NVARCHAR(10)
    DECLARE @customerCode NVARCHAR(20)

    -- Get the current count of records in the table
   SET @stt = (
        SELECT COUNT(*)
        FROM KhachHang
        WHERE maKhachHang LIKE '%' + FORMAT(GETDATE(), 'ddMMyy') + '%'
    ) + 1

    -- Determine the number of leading zeros based on the value of stt
    IF @stt >= 10
        SET @formattedStt = '0'
	ELSE IF @stt >= 100
	    SET @formattedStt = ''
	ELSE IF @stt <10
	    SET @formattedStt = '00'
    -- Concatenate "KH.dayMonthYear." with the formatted sequence number
   SET @customerCode = 'KH.' + FORMAT(GETDATE(), 'ddMM') + RIGHT(YEAR(GETDATE()), 2) + '.' + @formattedStt + CAST(@stt AS VARCHAR(5))

    RETURN @customerCode
END;
GO
CREATE FUNCTION GeneratePromotionCode()
RETURNS NVARCHAR(20)
AS
BEGIN
    DECLARE @stt INT
    DECLARE @formattedStt NVARCHAR(10)
    DECLARE @customerCode NVARCHAR(20)

    -- Get the current count of records in the table
   SET @stt = (
        SELECT COUNT(*)
        FROM KhuyenMai
        WHERE maKhuyenMai LIKE '%' + FORMAT(GETDATE(), 'ddMMyy') + '%' ) + 1
    -- Determine the number of leading zeros based on the value of stt
    IF @stt >= 10
        SET @formattedStt = '0'
	ELSE IF @stt >= 100
	    SET @formattedStt = ''
	ELSE IF @stt <10
	    SET @formattedStt = '00'
    -- Concatenate "KH.dayMonthYear." with the formatted sequence number
   SET @customerCode = 'KM.' + FORMAT(GETDATE(), 'ddMM') + RIGHT(YEAR(GETDATE()), 2) + '.' + @formattedStt + CAST(@stt AS VARCHAR(5))

    RETURN @customerCode
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