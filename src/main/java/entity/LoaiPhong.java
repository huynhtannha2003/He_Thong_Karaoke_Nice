package entity;

import enums.TrangThaiLoaiPhong;

public class LoaiPhong {
	private String maLoaiPhong;
	private String tenLoaiPhong;
	private TrangThaiLoaiPhong trangThai;

	public LoaiPhong() {
	}

	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, TrangThaiLoaiPhong trangThai) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.trangThai = trangThai;
	}

	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public TrangThaiLoaiPhong getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiLoaiPhong trangThai) {
		this.trangThai = trangThai;
	}
}
