package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	public TaiKhoan(ResultSet rs) throws SQLException {
		this.maTaiKhoan = rs.getString("TaiKhoan_MaTaiKhoan");
		this.tenTaiKhoan = rs.getString("TaiKhoan_TenTaiKhoan");
		this.matKhau = rs.getString("TaiKhoan_MatKhau");
		this.trangThai = TrangThaiTaiKhoan.values()[rs.getInt("TaiKhoan_TrangThai")];
		this.nhanVien = new NhanVien(rs);
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

	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau
				+ ", trangThai=" + trangThai + ", nhanVien=" + nhanVien + "]";
	}

}