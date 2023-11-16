package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class LichSuGiaPhong {
	private String maLichSuGiaPhong;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private Time thoiDiemBatDau;
	private Time thoiDiemKetThuc;
	private double gia;
	private LoaiPhong phong;

	public LichSuGiaPhong() {
	}

	public LichSuGiaPhong(String maLichSuGiaPhong, Date ngayBatDau, Date ngayKetThuc, Time thoiDiemBatDau,
			Time thoiDiemKetThuc, double gia, LoaiPhong phong) {
		this.maLichSuGiaPhong = maLichSuGiaPhong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.thoiDiemBatDau = thoiDiemBatDau;
		this.thoiDiemKetThuc = thoiDiemKetThuc;
		this.gia = gia;
		this.phong = phong;
	}

	public LichSuGiaPhong(ResultSet rs) throws SQLException {
		this.maLichSuGiaPhong = rs.getString("LichSuGiaPhong_MaLoaiPhong");
		this.ngayBatDau = rs.getDate("LichSuGiaPhong_NgayBatDau");
		this.ngayKetThuc = rs.getDate("LichSuGiaPhong_NgayKetThuc");
		this.thoiDiemBatDau = rs.getTime("LichSuGiaPhong_ThoiDiemBatDau");
		this.thoiDiemKetThuc = rs.getTime("LichSuGiaPhong_ThoiDiemKetThuc");
		this.gia = rs.getDouble("LichSuGiaPhong_Gia");
	}

	public String getMaLichSuGiaPhong() {
		return maLichSuGiaPhong;
	}

	public void setMaLichSuGiaPhong(String maLichSuGiaPhong) {
		this.maLichSuGiaPhong = maLichSuGiaPhong;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public Time getThoiDiemBatDau() {
		return thoiDiemBatDau;
	}

	public void setThoiDiemBatDau(Time thoiDiemBatDau) {
		this.thoiDiemBatDau = thoiDiemBatDau;
	}

	public Time getThoiDiemKetThuc() {
		return thoiDiemKetThuc;
	}

	public void setThoiDiemKetThuc(Time thoiDiemKetThuc) {
		this.thoiDiemKetThuc = thoiDiemKetThuc;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public LoaiPhong getPhong() {
		return phong;
	}

	public void setPhong(LoaiPhong phong) {
		this.phong = phong;
	}

}