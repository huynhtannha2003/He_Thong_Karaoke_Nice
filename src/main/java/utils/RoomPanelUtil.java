package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.DichVu;
import entity.Phong;

public class RoomPanelUtil {
	public static List<JPanel> createPhongPanels(List<Phong> listPhong, PhongPanelClickListener listener) {
		List<JPanel> panels = new ArrayList<JPanel>();
		for (Phong phong : listPhong) {
			JPanel phongPanel = new JPanel();
			phongPanel.setLayout(new BoxLayout(phongPanel, BoxLayout.Y_AXIS));
			phongPanel.setBackground(new Color(255, 255, 255));

			phongPanel.add(Box.createVerticalStrut(15));

			JLabel imageLabel = new JLabel(ResizeImageUtil.getImageByTypePhong(phong.getTrangThai(), 110, 110));
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
			
			phongPanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					phongPanel.setBackground(new Color(255, 255, 255));
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					phongPanel.setBackground(new Color(200, 200, 200));
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onPhongPanelClicked(phong);					
				}
			});
		}
		return panels;
	}
	
	public static List<JPanel> createDichVuPanels(List<DichVu> listDichVu, DichVuPanelClickListener listener) {
        List<JPanel> panels = new ArrayList<>();

        for (DichVu dichVu : listDichVu) {
            JPanel dichVuPanel = new JPanel();
            dichVuPanel.setLayout(new BoxLayout(dichVuPanel, BoxLayout.Y_AXIS));
            dichVuPanel.setBackground(new Color(255, 255, 255));

            dichVuPanel.add(Box.createVerticalStrut(15));

             JLabel imageLabel = new JLabel(ResizeImageUtil.getResizedImage(dichVu.getHinhAnh(), 110, 110));
             imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
             dichVuPanel.add(imageLabel);

             JLabel lblServiceName = new JLabel("Dịch vụ: " + dichVu.getTenDichVu());
            lblServiceName.setAlignmentX(Component.CENTER_ALIGNMENT);
            dichVuPanel.add(lblServiceName);

            dichVuPanel.add(Box.createVerticalStrut(10));

            JLabel lblQuantity = new JLabel("Số lượng: " + dichVu.getSoLuong());
            lblQuantity.setAlignmentX(Component.CENTER_ALIGNMENT);
            dichVuPanel.add(lblQuantity);

            dichVuPanel.add(Box.createVerticalStrut(10));

            JLabel lblType = new JLabel("Loại dịch vụ: " + dichVu.getLoaiDichVu().getTenLoaiDichVu());
            lblType.setAlignmentX(Component.CENTER_ALIGNMENT);
            dichVuPanel.add(lblType);

            dichVuPanel.add(Box.createVerticalStrut(15));

			dichVuPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onDichVuPanelClicked(dichVu);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					dichVuPanel.setBackground(new Color(200, 200, 200));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					dichVuPanel.setBackground(new Color(255, 255, 255));
				}
			});
            panels.add(dichVuPanel);
        }
        return panels;
    }
}