package entity;

public class ChiTietDatDichVu {
	private int soLuong;
	private PhieuDatPhong phieuDatPhong;
	private DichVu dichVu;

	public ChiTietDatDichVu() {
	}

	public ChiTietDatDichVu(int soLuong, PhieuDatPhong phieuDatPhong, DichVu dichVu) {
		this.soLuong = soLuong;
		this.phieuDatPhong = phieuDatPhong;
		this.dichVu = dichVu;
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
