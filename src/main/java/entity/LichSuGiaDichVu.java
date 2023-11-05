package entity;

import java.sql.Date;
import java.sql.Time;

public class LichSuGiaDichVu {
	private String maLichSuGiaDichVu;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private Time thoiDiemBatDau;
	private Time thoiDiemKetThuc;
	private Double gia;
	private DichVu dichVu;

	public LichSuGiaDichVu() {
	}

	public LichSuGiaDichVu(String maLichSuGiaDichVu, Date ngayBatDau, Date ngayKetThuc, Time thoiDiemBatDau,
			Time thoiDiemKetThuc, Double gia, DichVu dichVu) {
		this.maLichSuGiaDichVu = maLichSuGiaDichVu;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.thoiDiemBatDau = thoiDiemBatDau;
		this.thoiDiemKetThuc = thoiDiemKetThuc;
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

	public Double getGia() {
		return gia;
	}

	public void setGia(Double gia) {
		this.gia = gia;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
}
