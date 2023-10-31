package entity;

public class NhanVien {
	private String maNhanVien;
	private String ten;
	private String chucVu;
	private String sdt;
	private String email;
	private String diaChi;
	private int trangThai;

	public NhanVien() {
	}

	public NhanVien(String maNhanVien, String ten, String chucVu, String sdt, String email, String diaChi,
			int trangThai) {
		this.maNhanVien = maNhanVien;
		this.ten = ten;
		this.chucVu = chucVu;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}
