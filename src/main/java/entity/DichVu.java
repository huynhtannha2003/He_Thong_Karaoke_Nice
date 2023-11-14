package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import enums.TrangThaiDichVu;

public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private int soLuong;
	private TrangThaiDichVu trangThai;
	private LoaiDichVu loaiDichVu;
	private LichSuGiaDichVu gia;

	public DichVu() {
	}

	public DichVu(String maDichVu, String tenDichVu, int soLuong, TrangThaiDichVu trangThai, LoaiDichVu loaiDichVu,
			LichSuGiaDichVu gia) {
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
		this.loaiDichVu = loaiDichVu;
		this.gia = gia;
	}

	public DichVu(ResultSet rs) throws SQLException {
		this.maDichVu = rs.getString("DichVu_MaDichVu");
		this.tenDichVu = rs.getString("DichVu_TenDichVu");
		this.soLuong = rs.getInt("DichVu_SoLuong");
		this.gia = new LichSuGiaDichVu(rs);
		this.trangThai = TrangThaiDichVu.values()[rs.getInt("DichVu_TrangThai")];
		this.loaiDichVu = new LoaiDichVu(rs);
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

	public LichSuGiaDichVu getGia() {
		return gia;
	}

	public void setGia(LichSuGiaDichVu gia) {
		this.gia = gia;
	}

}
