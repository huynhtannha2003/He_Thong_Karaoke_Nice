package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

import enums.TrangThaiPhong;

public class RoomImageUtil {
	private static String getRoomStatusToString(TrangThaiPhong trangThai) {
		switch (trangThai) {
		case PHONG_TRONG:
			return "phongTrong";
		case PHONG_DANG_SU_DUNG:
			return "phongBan";
		case PHONG_CHO:
			return "phongCho";
		case PHONG_DANG_BAO_TRI:
			return "phongDangSuaChua";
		default:
			return null;
		}
	}

	public static ImageIcon getImageByTypePhong(TrangThaiPhong trangThai, int width, int height) {
		String status = getRoomStatusToString(trangThai);
		ImageIcon icon = new ImageIcon(RoomImageUtil.class.getResource("/image/" + status + ".png"));
		Image resizedIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedIcon);
	}
}
