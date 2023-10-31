package entity;

import java.sql.Time;

public class Ca {
	private String maCa;
	private Time thoiGianBatDau;
	private Time thoiGianKetThuc;
	private int trangThai;

	public Ca() {
	}

	public Ca(String maCa, Time thoiGianBatDau, Time thoiGianKetThuc, int trangThai) {
		this.maCa = maCa;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.trangThai = trangThai;
	}

	public String getMaCa() {
		return maCa;
	}

	public void setMaCa(String maCa) {
		this.maCa = maCa;
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

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}
