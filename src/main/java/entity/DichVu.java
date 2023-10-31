package entity;

import enums.TrangThaiDichVu;

public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private int soLuong;
	private TrangThaiDichVu trangThai;
	private LoaiDichVu loaiDichVu;

	public DichVu() {
	}

	public DichVu(String maDichVu, String tenDichVu, int soLuong, TrangThaiDichVu trangThai, LoaiDichVu loaiDichVu) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
		this.loaiDichVu = loaiDichVu;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public TrangThaiDichVu getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiDichVu trangThai) {
		this.trangThai = trangThai;
	}

	public LoaiDichVu getLoaiDichVu() {
		return loaiDichVu;
	}

	public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}

}
