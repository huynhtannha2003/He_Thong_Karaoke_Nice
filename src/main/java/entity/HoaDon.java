package entity;

import java.sql.Date;

public class HoaDon {
	private String maHoaDon;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private float tongTien;
	private Date thoiGianThanhToan;

	public HoaDon() {
	}

	public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, float tongTien, Date thoiGianThanhToan) {
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.tongTien = tongTien;
		this.thoiGianThanhToan = thoiGianThanhToan;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
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

	public float getTongTien() {
		return tongTien;
	}

	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public Date getThoiGianThanhToan() {
		return thoiGianThanhToan;
	}

	public void setThoiGianThanhToan(Date thoiGianThanhToan) {
		this.thoiGianThanhToan = thoiGianThanhToan;
	}
}
