package entity;

import java.sql.Date;
import java.sql.Time;

public class KhuyenMai {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private double phanTram;
	private double gioiHan;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private Time thoiDiemBatDau;
	private Time thoiDiemKetThuc;

	public KhuyenMai() {
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, double phanTram, double gioiHan, Date ngayBatDau,
			Date ngayKetThuc, Time thoiDiemBatDau, Time thoiDiemKetThuc) {
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.phanTram = phanTram;
		this.gioiHan = gioiHan;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.thoiDiemBatDau = thoiDiemBatDau;
		this.thoiDiemKetThuc = thoiDiemKetThuc;
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

	public double getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(double phanTram) {
		this.phanTram = phanTram;
	}

	public double getGioiHan() {
		return gioiHan;
	}

	public void setGioiHan(double gioiHan) {
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

	public Time getThoiDiemBatDau() {
		return thoiDiemBatDau;
	}

	public void setThoiDiemBatDau(Time thoiDiemBatDau) {
		this.thoiDiemBatDau = thoiDiemBatDau;
	}

	public Time getThoiDiemKetThuc() {
		return thoiDiemKetThuc;
	}

	public void setThoiDiemKetThuc(Time thoiDiemKetThuc) {
		this.thoiDiemKetThuc = thoiDiemKetThuc;
	}
}
