package entity;

import java.sql.Date;

public class LichSuGiaDichVu {
	private String maLichSuGiaDichVu;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private float gia;
	private DichVu dichVu;

	public LichSuGiaDichVu() {
	}

	public LichSuGiaDichVu(String maLichSuGiaDichVu, Date ngayBatDau, Date ngayKetThuc, float gia, DichVu dichVu) {
		this.maLichSuGiaDichVu = maLichSuGiaDichVu;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.gia = gia;
		this.dichVu = dichVu;
	}

	public String getMaLichSuGiaDichVu() {
		return maLichSuGiaDichVu;
	}

	public void setMaLichSuGiaDichVu(String maLichSuGiaDichVu) {
		this.maLichSuGiaDichVu = maLichSuGiaDichVu;
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

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
}
