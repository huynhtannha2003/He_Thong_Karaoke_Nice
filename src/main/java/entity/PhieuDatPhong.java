package entity;

import java.sql.Time;

public class PhieuDatPhong {
	private String maPhieuDatPhong;
	private Time thoiGianBatDau;
	private Time thoiGianKetThuc;
	private HoaDon hoaDon;
	private Phong phong;
	
	public PhieuDatPhong() {
	}

	public PhieuDatPhong(String maPhieuDatPhong, Time thoiGianBatDau, Time thoiGianKetThuc, HoaDon hoaDon,
			Phong phong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.hoaDon = hoaDon;
		this.phong = phong;
	}

	public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}

	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}

	public Time getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(Time thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public Time getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(Time thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}
}
