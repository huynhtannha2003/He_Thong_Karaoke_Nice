package entity;

import enums.TrangThaiLoaiDichVu;

public class LoaiDichVu {
	private String maLoaiDichVu;
	private String tenLoaiDichVu;
	private TrangThaiLoaiDichVu trangThai;

	public LoaiDichVu() {
	}

	public LoaiDichVu(String maLoaiDichVu, String tenLoaiDichVu, TrangThaiLoaiDichVu trangThai) {
		super();
		this.maLoaiDichVu = maLoaiDichVu;
		this.tenLoaiDichVu = tenLoaiDichVu;
		this.trangThai = trangThai;
	}

	public String getMaLoaiDichVu() {
		return maLoaiDichVu;
	}

	public void setMaLoaiDichVu(String maLoaiDichVu) {
		this.maLoaiDichVu = maLoaiDichVu;
	}

	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}

	public void setTenLoaiDichVu(String tenLoaiDichVu) {
		this.tenLoaiDichVu = tenLoaiDichVu;
	}

	public TrangThaiLoaiDichVu getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiLoaiDichVu trangThai) {
		this.trangThai = trangThai;
	}
}
