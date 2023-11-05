package utils;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Phong;

public class RoomPanelUtil {

	public static List<JPanel> createPhongPanels(List<Phong> listPhong) {
		List<JPanel> panels = new ArrayList<JPanel>();
		for (Phong phong : listPhong) {
			JPanel phongPanel = new JPanel();
			phongPanel.setLayout(new BoxLayout(phongPanel, BoxLayout.Y_AXIS));
			phongPanel.setBackground(new Color(255, 255, 255));

			phongPanel.add(Box.createVerticalStrut(15));

			JLabel imageLabel = new JLabel(RoomImageUtil.getImageByTypePhong(phong.getTrangThai(), 110, 110));
			imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			phongPanel.add(imageLabel);

			phongPanel.add(Box.createVerticalStrut(10));

			JLabel lblRoomName = new JLabel("Phòng: " + phong.getTenPhong());
			lblRoomName.setAlignmentX(Component.CENTER_ALIGNMENT);
			phongPanel.add(lblRoomName);

			phongPanel.add(Box.createVerticalStrut(10));

			JLabel lblCapacity = new JLabel("Sức chứa: " + phong.getSucChua());
			lblCapacity.setAlignmentX(Component.CENTER_ALIGNMENT);
			phongPanel.add(lblCapacity);

			phongPanel.add(Box.createVerticalStrut(10));

			JLabel lblType = new JLabel("Loại phòng: " + phong.getLoaiPhong().getTenLoaiPhong());
			lblType.setAlignmentX(Component.CENTER_ALIGNMENT);
			phongPanel.add(lblType);

			phongPanel.add(Box.createVerticalStrut(15));
			panels.add(phongPanel);
		}
		return panels;
	}
}