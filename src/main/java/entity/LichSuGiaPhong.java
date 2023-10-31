package entity;

import java.sql.Date;

public class LichSuGiaPhong {
	private String maLichSuGiaPhong;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private float gia;
	private Ca ca;
	private Phong phong;

	public LichSuGiaPhong() {
	}

	public LichSuGiaPhong(String maLichSuGiaPhong, Date ngayBatDau, Date ngayKetThuc, float gia, Ca ca, Phong phong) {
		this.maLichSuGiaPhong = maLichSuGiaPhong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.gia = gia;
		this.ca = ca;
		this.phong = phong;
	}

	public String getMaLichSuGiaPhong() {
		return maLichSuGiaPhong;
	}

	public void setMaLichSuGiaPhong(String maLichSuGiaPhong) {
		this.maLichSuGiaPhong = maLichSuGiaPhong;
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

	public float getGia() {
		return gia;
	}

	public void setGia(float gia) {
		this.gia = gia;
	}

	public Ca getCa() {
		return ca;
	}

	public void setCa(Ca ca) {
		this.ca = ca;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}
}
