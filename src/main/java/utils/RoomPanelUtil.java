package utils;

import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;

import dao.HoaDonDAO;
import entity.DichVu;
import entity.HoaDon;
import entity.Phong;
import enums.TrangThaiPhong;

public class RoomPanelUtil {
    private static List<Phong> selectedPhongList = new ArrayList<>();
    static Calendar currentTime = Calendar.getInstance();
    static int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);

    public static List<JPanel> createPhongPanels(List<Phong> listPhong, PhongPanelClickListener listener) {
        HoaDonDAO hoaDonDao = new HoaDonDAO();
        List<HoaDon> listHoaDon = hoaDonDao.getTodayPhieuDatPhongCho();
        List<JPanel> panels = new ArrayList<>();

        for (Phong phong : listPhong) {
            boolean isHoaDonCho = listHoaDon.stream()
                    .anyMatch(hoaDon ->
                            hoaDon.getPhieuDatPhongList().get(0).getPhong().getMaPhong().equals(phong.getMaPhong())
                                    && Math.abs(hoaDon.getPhieuDatPhongList().get(0).getThoiGianBatDau().toLocalTime().getHour() - currentHour) <= 2);

            if (!isHoaDonCho && phong.getTrangThai().equals(TrangThaiPhong.PHONG_CHO)) {
                phong.setTrangThai(TrangThaiPhong.PHONG_TRONG);
            }

            JPanel phongPanel = createRoomPanel(phong, listener);
            panels.add(phongPanel);
        }

        return panels;
    }

    private static JPanel createRoomPanel(Phong phong, PhongPanelClickListener listener) {
        JPanel phongPanel = new JPanel();
        phongPanel.setLayout(new BoxLayout(phongPanel, BoxLayout.Y_AXIS));
        phongPanel.setBackground(new Color(255, 255, 255));

        phongPanel.add(Box.createVerticalStrut(15));
        JLabel imageLabel = new JLabel(ResizeImageUtil.getImageByTypePhong(phong.getTrangThai(), 110, 110));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phongPanel.add(imageLabel);

        addLabelToPanel(phongPanel, "Phòng: " + phong.getTenPhong());
        addLabelToPanel(phongPanel, "Sức chứa: " + phong.getSucChua());
        addLabelToPanel(phongPanel, "Loại phòng: " + phong.getLoaiPhong().getTenLoaiPhong());
        phongPanel.add(Box.createVerticalStrut(15));

        phongPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleRoomPanelClick(phong, phongPanel, listener);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                phongPanel.setBackground(new Color(200, 200, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!selectedPhongList.contains(phong)) {
                    phongPanel.setBackground(new Color(255, 255, 255));
                }
            }
        });

        return phongPanel;
    }

    private static void addLabelToPanel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
    }

    private static void handleRoomPanelClick(Phong phong, JPanel phongPanel, PhongPanelClickListener listener) {
        if (selectedPhongList.contains(phong)) {
            selectedPhongList.remove(phong);
        } else {
            selectedPhongList.add(phong);
        }

        listener.onPhongPanelClicked(selectedPhongList);
        phongPanel.setBackground(selectedPhongList.contains(phong) ? new Color(200, 200, 200) : new Color(255, 255, 255));
        System.out.println("getSelectedPhongList() = " + getSelectedPhongList());
    }

    public static List<Phong> getSelectedPhongList() {
        return selectedPhongList;
    }

    public static List<JPanel> createDichVuPanels(List<DichVu> listDichVu, DichVuPanelClickListener listener) {
        List<JPanel> panels = new ArrayList<>();

        for (DichVu dichVu : listDichVu) {
            JPanel dichVuPanel = createDichVuPanel(dichVu, listener);
            panels.add(dichVuPanel);
        }

        return panels;
    }

    private static JPanel createDichVuPanel(DichVu dichVu, DichVuPanelClickListener listener) {
        JPanel dichVuPanel = new JPanel();
        dichVuPanel.setLayout(new BoxLayout(dichVuPanel, BoxLayout.Y_AXIS));
        dichVuPanel.setBackground(new Color(255, 255, 255));

        dichVuPanel.add(Box.createVerticalStrut(15));
        JLabel imageLabel = new JLabel(ResizeImageUtil.getResizedImage(dichVu.getHinhAnh(), 110, 110));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dichVuPanel.add(imageLabel);

        addLabelToPanel(dichVuPanel, "Dịch vụ: " + dichVu.getTenDichVu());
        addLabelToPanel(dichVuPanel, "Số lượng: " + dichVu.getSoLuong());
        addLabelToPanel(dichVuPanel, "Loại dịch vụ: " + dichVu.getLoaiDichVu().getTenLoaiDichVu());
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

        return dichVuPanel;
    }

}
