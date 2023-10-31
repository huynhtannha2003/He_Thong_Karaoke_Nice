package entity;

public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private String tenPhong;
	private int sucChua;
	private int trangThai;
	
	public Phong() {
	}

	public Phong(String maPhong, LoaiPhong loaiPhong, String tenPhong, int sucChua, int trangThai) {
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.tenPhong = tenPhong;
		this.sucChua = sucChua;
		this.trangThai = trangThai;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}
