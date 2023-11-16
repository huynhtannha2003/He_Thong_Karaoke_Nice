package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHang {
	private String maKhachHang;
	private String tenKhachHang;
	private String sdt;

	public KhachHang() {
	}

	public KhachHang(String maKhachHang, String tenKhachHang, String sdt) {
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
	}

	public KhachHang(ResultSet rs) throws SQLException {
		this.maKhachHang = rs.getString("KhachHang_MaKhachHang");
		this.tenKhachHang = rs.getString("KhachHang_TenKhachHang");
		this.sdt = rs.getString("KhachHang_SDT");
	}
	
	
	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
}