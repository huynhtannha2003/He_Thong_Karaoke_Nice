package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class HoaDon {
	private String maHoaDon;
	private double tongTien;
	private Date ngayThanhToan;
	private Time thoiDiemThanhToan;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private KhuyenMai khuyenMai;

	public HoaDon() {
	}

	public HoaDon(String maHoaDon, double tongTien, Date ngayThanhToan, Time thoiDiemThanhToan, KhachHang khachHang,
			NhanVien nhanVien, KhuyenMai khuyenMai) {
		this.maHoaDon = maHoaDon;
		this.tongTien = tongTien;
		this.ngayThanhToan = ngayThanhToan;
		this.thoiDiemThanhToan = thoiDiemThanhToan;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.khuyenMai = khuyenMai;
	}

	public HoaDon(ResultSet rs) throws SQLException {
		this.maHoaDon = rs.getString("HoaDon_MaHoaDon");
        this.tongTien = rs.getDouble("HoaDon_TongTien");
        this.ngayThanhToan = rs.getDate("HoaDon_NgayThanhToan");
        this.thoiDiemThanhToan = rs.getTime("HoaDon_ThoiDiemThanhToan");
        this.khachHang = new KhachHang(rs);
        this.nhanVien = new NhanVien(rs);
        this.khuyenMai= new KhuyenMai(rs);
	}
	
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(Date ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public Time getThoiDiemThanhToan() {
		return thoiDiemThanhToan;
	}

	public void setThoiDiemThanhToan(Time thoiDiemThanhToan) {
		this.thoiDiemThanhToan = thoiDiemThanhToan;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
}
