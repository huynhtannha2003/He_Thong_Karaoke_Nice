package entity;

import java.sql.Date;
import java.sql.Time;

public class LichSuGiaPhong {
	private String maLichSuGiaPhong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Time thoiDiemBatDau;
    private Time thoiDiemKetThuc;
    private double gia;
	private Phong phong;

	public LichSuGiaPhong() {
	}

	public LichSuGiaPhong(String maLichSuGiaPhong, Date ngayBatDau, Date ngayKetThuc, Time thoiDiemBatDau,
			Time thoiDiemKetThuc, double gia, Phong phong) {
		super();
		this.maLichSuGiaPhong = maLichSuGiaPhong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.thoiDiemBatDau = thoiDiemBatDau;
		this.thoiDiemKetThuc = thoiDiemKetThuc;
		this.gia = gia;
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

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}
}
