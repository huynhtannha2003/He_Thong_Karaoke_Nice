package entity;

public class ChiTietDatDichVu {
	private String maChiTietDatDichVu;
	private int soLuong;
	private PhieuDatPhong phieuDatPhong;
	private DichVu dichVu;

	public ChiTietDatDichVu() {
	}

	public ChiTietDatDichVu(String maChiTietDatDichVu, int soLuong, PhieuDatPhong phieuDatPhong, DichVu dichVu) {
		this.maChiTietDatDichVu = maChiTietDatDichVu;
		this.soLuong = soLuong;
		this.phieuDatPhong = phieuDatPhong;
		this.dichVu = dichVu;
	}

	public String getMaChiTietDatDichVu() {
		return maChiTietDatDichVu;
	}

	public void setMaChiTietDatDichVu(String maChiTietDatDichVu) {
		this.maChiTietDatDichVu = maChiTietDatDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}
}
