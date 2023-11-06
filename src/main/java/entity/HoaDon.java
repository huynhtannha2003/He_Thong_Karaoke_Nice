package entity;

import java.sql.Date;
import java.sql.Time;

public class HoaDon {
	private String maHoaDon;
	private double tongTien;
	private Date NgayThanhToan;
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
		NgayThanhToan = ngayThanhToan;
		this.thoiDiemThanhToan = thoiDiemThanhToan;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.khuyenMai = khuyenMai;
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
		return NgayThanhToan;
	}

	public void setNgayThanhToan(Date ngayThanhToan) {
		NgayThanhToan = ngayThanhToan;
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
