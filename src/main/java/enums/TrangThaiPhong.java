package enums;

public enum TrangThaiPhong {
	PHONG_TRONG("Phòng trống"),
    PHONG_DANG_SU_DUNG("Phòng đang sử dụng"),
    PHONG_CHO("Phòng chờ"),
    PHONG_DANG_BAO_TRI("Phòng đang bảo trì");

    private final String customName;

    TrangThaiPhong(String customName) {
        this.customName = customName;
    }

    public String getCustomName() {
        return customName;
    }
}
