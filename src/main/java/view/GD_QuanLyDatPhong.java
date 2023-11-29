package view;

import dao.HoaDonDAO;
import dao.LoaiPhongDAO;
import dao.PhongDAO;
import entity.*;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.PhongPanelClickListener;
import utils.ResizeImageUtil;
import utils.RoomPanelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GD_QuanLyDatPhong extends JPanel implements PhongPanelClickListener, ActionListener {

    private final LoaiPhongDAO loaiPhongDAO;
    private final HoaDonDAO hoaDonDAO;
    private JPanel contentPane;
    private JTextField txtName;
    private JPanel pnCenter;
    private List<Phong> listPhong;
    private List<LoaiPhong> loaiPhongList;
    private JPanel pnListRoom;
    private JPanel pnNote;
    private JComboBox cbStatus;
    private JComboBox cbType;
    private Phong phongSelected;
    private JButton btnDatPhong, btnChuyenPhong, btnHuyDatPhong, btnDatPhongCho, btnNhanPhongCho, btnXemChiTiet, btnDichVu, btnThanhToan, btnFind, btnClear;
    private final PhongDAO phongDAO;
    private NhanVien nhanVien;

    public GD_QuanLyDatPhong(NhanVien currentNhanVien) {
        nhanVien = currentNhanVien;
        phongDAO = new PhongDAO();
        loaiPhongDAO = new LoaiPhongDAO();
        hoaDonDAO = new HoaDonDAO();
        initGUI();
    }

    private void setupFrame() {
        setSize(1000, 700);
    }

    private void initGUI() {
        setupFrame();

        addPanelNorth();

        addLeftPanelButton();

        addPanelCenter();
    }

    private void addPanelCenter() {
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        add(pnCenter);
        pnCenter.setLayout(new BorderLayout(0, 0));

        addForm();

        addPanelRoom();

        addNotePanel();

    }

    private void addNotePanel() {
        pnNote = new JPanel();
        pnNote.setBackground(new Color(255, 255, 255));
        pnCenter.add(pnNote, BorderLayout.SOUTH);

        pnNote.setLayout(new GridLayout(0, 4, 0, 0));

        TrangThaiPhong[] dsTrangThaiPhong = TrangThaiPhong.values();
        for (TrangThaiPhong currentTrangThaiPhong : dsTrangThaiPhong) {
            JPanel pnNotePhong = new JPanel();
            pnNotePhong.setBackground(new Color(255, 255, 255));

            JLabel imageLabel = new JLabel(ResizeImageUtil.getImageByTypePhong(currentTrangThaiPhong, 40, 40));
            pnNotePhong.add(imageLabel);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblPhongTrong = new JLabel(currentTrangThaiPhong.getTypePhong());
            lblPhongTrong.setFont(new Font("Tahoma", Font.PLAIN, 13));
            pnNotePhong.add(lblPhongTrong);

            pnNote.add(pnNotePhong);
        }
    }

    private void addPanelRoom() {
        pnListRoom = new JPanel();
        pnListRoom.setBackground(new Color(255, 255, 255));
        JScrollPane scrollPane = new JScrollPane(pnListRoom);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        pnCenter.add(scrollPane, BorderLayout.CENTER);

        pnListRoom.setLayout(new GridLayout(0, 5, 0, 0));
        pnListRoom.setBorder(BorderFactory.createLineBorder(Color.black));

        getAllRoom();
    }

    private void getAllRoom() {
        listPhong = phongDAO.getPhongLoaiPhongLichSuaGiaByConditionTime();
        loadRooms(listPhong);
    }

    private void loadRooms(List<Phong> newRooms) {
        pnListRoom.removeAll();
        List<JPanel> roomPanels = RoomPanelUtil.createPhongPanels(newRooms, this);
        roomPanels.forEach(pnListRoom::add);

        pnListRoom.revalidate();
        pnListRoom.repaint();

    }

    private void addForm() {
        JPanel pnForm = new JPanel();
        pnForm.setBackground(new Color(255, 255, 255));
        pnCenter.add(pnForm, BorderLayout.NORTH);
        pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.X_AXIS));

        Box formVerticalBox = Box.createVerticalBox();

        formVerticalBox.add(Box.createVerticalStrut(20));

        pnForm.add(formVerticalBox);

        Box firstFormHorizontalBox = Box.createHorizontalBox();
        formVerticalBox.add(firstFormHorizontalBox);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        JLabel lblStatus = new JLabel("Trạng thái phòng");
        lblStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstFormHorizontalBox.add(lblStatus);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        cbStatus = new JComboBox();
        cbStatus.addItem("Tất cả");

        List.of(Arrays.stream(TrangThaiPhong.values())
                .map(TrangThaiPhong::getTypePhong)
                .toArray(String[]::new)).forEach(cbStatus::addItem);

        cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        firstFormHorizontalBox.add(cbStatus);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        JLabel lblType = new JLabel("Loại phòng");
        lblType.setPreferredSize(lblStatus.getPreferredSize());
        lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstFormHorizontalBox.add(lblType);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        cbType = new JComboBox();
        cbType.addItem(new LoaiPhong(null, "Tất cả", TrangThaiLoaiPhong.HIEU_LUC));
        loaiPhongDAO.getAllLoaiPhong().forEach(cbType::addItem);
        cbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
        firstFormHorizontalBox.add(cbType);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        btnFind = new JButton("Tìm kiếm");
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setBackground(new Color(107, 208, 107));
        btnFind.addActionListener(this);

        firstFormHorizontalBox.add(btnFind);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        formVerticalBox.add(Box.createVerticalStrut(20));

        Box secondFormHorizontalBox = Box.createHorizontalBox();
        formVerticalBox.add(secondFormHorizontalBox);

        formVerticalBox.add(Box.createVerticalStrut(20));

        secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

        JLabel lblName = new JLabel("Tên phòng");
        lblName.setPreferredSize(lblStatus.getPreferredSize());
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        secondFormHorizontalBox.add(lblName);

        secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        secondFormHorizontalBox.add(txtName);
        txtName.setColumns(10);

        secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

        btnClear = new JButton("Xóa trắng");
        btnClear.addActionListener(this);
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClear.setBackground(new Color(107, 208, 107));
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        secondFormHorizontalBox.add(btnClear);

        secondFormHorizontalBox.add(Box.createHorizontalStrut(20));
    }

    private void addPanelNorth() {
        setLayout(new BorderLayout(0, 0));
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(new Color(97, 250, 204));
        add(pnNorth, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Quản lý đặt phòng");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnNorth.add(lblTitle);
    }

    private void addLeftPanelButton() {
        JPanel pnLeft = new JPanel();
        pnLeft.setBackground(new Color(255, 255, 255));
        add(pnLeft, BorderLayout.WEST);

        JPanel pnLeftButton = new JPanel();
        pnLeftButton.setBackground(new Color(255, 255, 255));
        pnLeftButton.setLayout(new GridLayout(0, 1, 20, 30));

        btnDatPhong = new JButton("Đặt Phòng");
        btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDatPhong.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnDatPhong);

        btnChuyenPhong = new JButton("Chuyển Phòng");
        btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnChuyenPhong.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnChuyenPhong);

        btnDatPhongCho = new JButton("Đặt Phòng Chờ");
        btnDatPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDatPhongCho.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnDatPhongCho);

        btnHuyDatPhong = new JButton("Hủy Đặt Phòng Chờ");
        btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnHuyDatPhong.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnHuyDatPhong);

        btnNhanPhongCho = new JButton("Nhận Phòng Chờ");
        btnNhanPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNhanPhongCho.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnNhanPhongCho);

        btnXemChiTiet = new JButton("Xem Chi Tiết");
        btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXemChiTiet.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnXemChiTiet);

        btnDichVu = new JButton("Đặt Dịch Vụ");
        btnDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDichVu.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnDichVu);

        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThanhToan.setBackground(new Color(107, 208, 107));
        pnLeftButton.add(btnThanhToan);

        pnLeft.setBorder(BorderFactory.createLineBorder(Color.black));
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(pnLeftButton);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalStrut(20));
        horizontalBox.add(verticalBox);
        horizontalBox.add(Box.createHorizontalStrut(20));

        pnLeft.add(Box.createVerticalStrut(20));
        pnLeft.add(horizontalBox);
        pnLeft.add(Box.createVerticalStrut(20));
        btnDatPhong.addActionListener(this);
        btnChuyenPhong.addActionListener(this);
        btnHuyDatPhong.addActionListener(this);
        btnDatPhongCho.addActionListener(this);
        btnNhanPhongCho.addActionListener(this);
        btnXemChiTiet.addActionListener(this);
        btnDichVu.addActionListener(this);
        btnThanhToan.addActionListener(this);
    }

    @Override
    public void onPhongPanelClicked(Phong phong) {
        txtName.setText(phong.getTenPhong());
        cbType.setSelectedItem(phong.getLoaiPhong());
        cbStatus.setSelectedIndex(phong.getTrangThai().getValue() + 1);
        phongSelected = phong;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (phongSelected == null && source != btnFind && source != btnClear) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một phòng để đặt", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (source.equals(btnDatPhong)) {
            openDatPhongWindow();
        } else if (source.equals(btnChuyenPhong)) {
            openChuyenPhongWindow();
        } else if (source.equals(btnHuyDatPhong)) {// Handle HuyDatPhong action
        } else if (source.equals(btnDatPhongCho)) {
            GD_DatPhongCho gdDatPhongCho = new GD_DatPhongCho(phongSelected, nhanVien);
            gdDatPhongCho.setVisible(true);
        } else if (source.equals(btnNhanPhongCho)) {// Handle NhanPhongCho action
        } else if (source.equals(btnXemChiTiet)) {// Handle XemChiTiet action
        } else if (source.equals(btnDichVu)) {
            HoaDon hoaDon = hoaDonDAO.getHoaDonByMaPhong(phongSelected.getMaPhong());
            GD_DatDichVu gdDatDichVu = new GD_DatDichVu(hoaDon);
            gdDatDichVu.setVisible(true);
        } else if (source.equals(btnThanhToan)) {
            HoaDon hoaDon = hoaDonDAO.getHoaDonByMaPhong(phongSelected.getMaPhong());
            GD_ThanhToan gdThanhToan = new GD_ThanhToan(hoaDon);
            gdThanhToan.setVisible(true);
        } else if (source.equals(btnFind)) {
            handleFindAction();
        } else if (source.equals(btnClear)) {
            handleClearAction();
        } else if (source.equals(btnNhanPhongCho)) {

        }
    }

    private void openDatPhongWindow() {
        if (phongSelected != null && phongSelected.getTrangThai() == TrangThaiPhong.PHONG_TRONG) {
            GD_DatPhong gdDatPhong = new GD_DatPhong(phongSelected, nhanVien);
            gdDatPhong.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    getAllRoom();
                }
            });
            gdDatPhong.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn một phòng trống để đặt", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void openChuyenPhongWindow() {
        if (phongSelected != null) {
            HoaDon hoaDon = hoaDonDAO.getHoaDonByMaPhong(phongSelected.getMaPhong());
            GD_ChuyenPhong gdChuyenPhong = new GD_ChuyenPhong(hoaDon, phongSelected);
            gdChuyenPhong.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    getAllRoom();
                }
            });
            gdChuyenPhong.setVisible(true);
        }
    }

    private void handleFindAction() {
        listPhong = phongDAO.getPhongByCondition(
                cbStatus.getSelectedIndex() - 1,
                ((LoaiPhong) cbType.getSelectedItem()).getMaLoaiPhong(),
                txtName.getText()
        );
        loadRooms(listPhong);
    }

    private void handleClearAction() {
        cbType.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        txtName.setText("");
    }

}