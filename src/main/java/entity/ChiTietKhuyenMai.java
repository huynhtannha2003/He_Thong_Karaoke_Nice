package entity;

public class ChiTietKhuyenMai {
	private String maChiTietKhuyenMai;
	private HoaDon hoaDon;
	private KhuyenMai khuyenMai;
	private float tienGiam;

	public ChiTietKhuyenMai() {
	}

	public ChiTietKhuyenMai(String maChiTietKhuyenMai, HoaDon hoaDon, KhuyenMai khuyenMai, float tienGiam) {
		this.maChiTietKhuyenMai = maChiTietKhuyenMai;
		this.hoaDon = hoaDon;
		this.khuyenMai = khuyenMai;
		this.tienGiam = tienGiam;
	}

	public String getMaChiTietKhuyenMai() {
		return maChiTietKhuyenMai;
	}

	public void setMaChiTietKhuyenMai(String maChiTietKhuyenMai) {
		this.maChiTietKhuyenMai = maChiTietKhuyenMai;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public float getTienGiam() {
		return tienGiam;
	}

	public void setTienGiam(float tienGiam) {
		this.tienGiam = tienGiam;
	}
}
