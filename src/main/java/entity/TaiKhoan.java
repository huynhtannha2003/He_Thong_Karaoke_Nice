package entity;

import enums.TrangThaiTaiKhoan;

public class TaiKhoan {
	private String maTaiKhoan;
	private String tenTaiKhoan;
	private String matKhau;
	private TrangThaiTaiKhoan trangThai;
	private NhanVien nhanVien;

	public TaiKhoan() {
	}

	public TaiKhoan(String maTaiKhoan, String tenTaiKhoan, String matKhau, TrangThaiTaiKhoan trangThai,
			NhanVien nhanVien) {
		this.maTaiKhoan = maTaiKhoan;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.trangThai = trangThai;
		this.nhanVien = nhanVien;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public TrangThaiTaiKhoan getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiTaiKhoan trangThai) {
		this.trangThai = trangThai;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
}
