package enums;

public enum TrangThaiDichVu {
	VO_HIEU(0,"Vô hiệu"), HIEU_LUC(1,"Hiệu lực");

	private int value;
	private String name;


	private TrangThaiDichVu(int value, String name) {
		this.value = value;
		this.name = name;
	}


	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
