package enums;

public enum TrangThaiPhong {
    PHONG_TRONG(0, "Phòng trống"),
    PHONG_DANG_SU_DUNG(1, "Phòng đang sử dụng"),
    PHONG_CHO(2, "Phòng chờ"),
    PHONG_DANG_BAO_TRI(3, "Phòng đang bảo trì");

    private final int value;
    private final String customName;

    TrangThaiPhong(int value, String customName) {
        this.value = value;
        this.customName = customName;
    }

    public int getValue() {
        return value;
    }

    public String getTypePhong() {
        return customName;
    }
}