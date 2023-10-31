package entity;

import java.sql.Date;

import enums.TrangThaiKhuyenMai;

public class KhuyenMai {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private float phanTram;
	private float gioiHan;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private TrangThaiKhuyenMai trangThai;
	private Ca ca;

	public KhuyenMai() {
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, float phanTram, float gioiHan, Date ngayBatDau,
			Date ngayKetThuc, TrangThaiKhuyenMai trangThai, Ca ca) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.phanTram = phanTram;
		this.gioiHan = gioiHan;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.trangThai = trangThai;
		this.ca = ca;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public float getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(float phanTram) {
		this.phanTram = phanTram;
	}

	public float getGioiHan() {
		return gioiHan;
	}

	public void setGioiHan(float gioiHan) {
		this.gioiHan = gioiHan;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public TrangThaiKhuyenMai getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiKhuyenMai trangThai) {
		this.trangThai = trangThai;
	}

	public Ca getCa() {
		return ca;
	}

	public void setCa(Ca ca) {
		this.ca = ca;
	}

}
