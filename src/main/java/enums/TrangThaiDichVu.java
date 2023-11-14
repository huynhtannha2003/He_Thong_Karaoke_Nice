package enums;

public enum TrangThaiDichVu {
	VO_HIEU("Vô hiệu"), HIEU_LUC("Hiệu lực");

	private String name;

	TrangThaiDichVu(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
