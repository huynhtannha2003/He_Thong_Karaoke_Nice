package view;

import dao.LoaiPhongDAO;
import dao.PhieuDatPhongDAO;
import dao.PhongDAO;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.FormatCurrencyUtil;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GD_ChuyenPhong extends JDialog implements PhongPanelClickListener, ActionListener {
    private JTextField txtRoomName;
    private JPanel pnCenter;
    private List<Phong> rooms;
    private JTextField txtFollowRoomName;
    private JTextField txtFollowRoomPrice;
    private Box hBoxFollowRoomType;
    private JTextField txtFollowRoomType;
    private JTextField txtCurrentRoomName;
    private JTextField txtCurrentRoomPrice;
    private JTextField txtCurrentRoomType;
    private PhongDAO phongDao = new PhongDAO();
    private LoaiPhongDAO loaiPhongDao;
    private JComboBox<LoaiPhong> cbTypeRoom;
    private JButton btnFind;
    private JButton btnApply;
    private JPanel pnRoomScrollPane;
    private Phong phong, selectedPhong;
    private HoaDon hoaDon;
    PhieuDatPhongDAO phieuDatPhongDAO;

    public static void main(String[] args) {
        try {
            GD_ChuyenPhong dialog = new GD_ChuyenPhong(new HoaDon(), new Phong());
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GD_ChuyenPhong(HoaDon currentHoaDon, Phong selectedPhong) {
        phong = selectedPhong;
        hoaDon = currentHoaDon;
        loaiPhongDao = new LoaiPhongDAO();
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setSize(784, 600);
        setLocationRelativeTo(null);

        addPanelNorth();

        addPanelCenter();
    }

    private void addPanelCenter() {
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        getContentPane().add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(new BorderLayout(0, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addFormPanel();

        addRoomsPanel();

        JPanel pnRoomChange = new JPanel();
        pnRoomChange.setBackground(new Color(255, 255, 255));
        pnCenter.add(pnRoomChange, BorderLayout.SOUTH);
        pnRoomChange.setLayout(new BoxLayout(pnRoomChange, BoxLayout.Y_AXIS));

        pnRoomChange.add(Box.createVerticalStrut(20));

        Box horizontalBoxRoomChange = Box.createHorizontalBox();
        pnRoomChange.add(horizontalBoxRoomChange);

        horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

        JPanel pnCurrentRoom = new JPanel();
        pnCurrentRoom.setBackground(new Color(255, 255, 255));
        horizontalBoxRoomChange.add(pnCurrentRoom);
        pnCurrentRoom.setBorder(BorderFactory.createLineBorder(Color.black));
        pnCurrentRoom.setLayout(new BoxLayout(pnCurrentRoom, BoxLayout.Y_AXIS));

        pnCurrentRoom.add(Box.createVerticalStrut(20));

        JLabel lblCurrentRoomName = new JLabel("Tên phòng:");
        lblCurrentRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCurrentRoomName.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box hBoxCurrentRoomName = Box.createHorizontalBox();
        pnCurrentRoom.add(hBoxCurrentRoomName);

        hBoxCurrentRoomName.add(Box.createHorizontalStrut(20));
        hBoxCurrentRoomName.add(lblCurrentRoomName);

        hBoxCurrentRoomName.add(Box.createHorizontalStrut(20));

        txtCurrentRoomName = new JTextField(phong.getTenPhong());
        txtCurrentRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtCurrentRoomName.setEditable(false);
        hBoxCurrentRoomName.add(txtCurrentRoomName);
        txtCurrentRoomName.setColumns(10);

        hBoxCurrentRoomName.add(Box.createHorizontalStrut(20));

        pnCurrentRoom.add(Box.createVerticalStrut(20));

        JLabel lblCurrentRoomPrice = new JLabel("Giá phòng:");
        lblCurrentRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCurrentRoomPrice.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box hBoxCurrentRoomType = Box.createHorizontalBox();
        pnCurrentRoom.add(hBoxCurrentRoomType);

        hBoxCurrentRoomType.add(Box.createHorizontalStrut(20));
        hBoxCurrentRoomType.add(lblCurrentRoomPrice);

        hBoxCurrentRoomType.add(Box.createHorizontalStrut(20));

        txtCurrentRoomPrice = new JTextField(FormatCurrencyUtil.formatCurrency(phong.getLoaiPhong().getGia()));
        txtCurrentRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtCurrentRoomPrice.setEditable(false);
        hBoxCurrentRoomType.add(txtCurrentRoomPrice);
        txtCurrentRoomPrice.setColumns(10);

        hBoxCurrentRoomType.add(Box.createHorizontalStrut(20));

        pnCurrentRoom.add(Box.createVerticalStrut(20));

        JLabel lblCurrentRoomType = new JLabel("Loại phòng:");
        lblCurrentRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCurrentRoomType.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box hBoxCurrentRoomPrice = Box.createHorizontalBox();
        pnCurrentRoom.add(hBoxCurrentRoomPrice);

        hBoxCurrentRoomPrice.add(Box.createHorizontalStrut(20));
        hBoxCurrentRoomPrice.add(lblCurrentRoomType);

        hBoxCurrentRoomPrice.add(Box.createHorizontalStrut(20));

        txtCurrentRoomType = new JTextField(phong.getLoaiPhong().getTenLoaiPhong());
        txtCurrentRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtCurrentRoomType.setEditable(false);
        hBoxCurrentRoomPrice.add(txtCurrentRoomType);
        txtCurrentRoomType.setColumns(10);

        hBoxCurrentRoomPrice.add(Box.createHorizontalStrut(20));

        pnCurrentRoom.add(Box.createVerticalStrut(20));

        horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

        JPanel pnFollowRoom = new JPanel();
        pnFollowRoom.setBackground(new Color(255, 255, 255));
        horizontalBoxRoomChange.add(pnFollowRoom);
        pnFollowRoom.setBorder(BorderFactory.createLineBorder(Color.black));
        pnFollowRoom.setLayout(new BoxLayout(pnFollowRoom, BoxLayout.Y_AXIS));

        pnFollowRoom.add(Box.createVerticalStrut(20));

        JLabel lblFollowRoomName = new JLabel("Tên phòng:");
        lblFollowRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFollowRoomName.setAlignmentX(Component.CENTER_ALIGNMENT);
        Box hBoxFollowRoomName = Box.createHorizontalBox();

        hBoxFollowRoomName.add(Box.createHorizontalStrut(20));
        hBoxFollowRoomName.add(lblFollowRoomName);

        pnFollowRoom.add(hBoxFollowRoomName);

        hBoxFollowRoomName.add(Box.createHorizontalStrut(20));

        txtFollowRoomName = new JTextField();
        txtFollowRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtFollowRoomName.setEditable(false);
        hBoxFollowRoomName.add(txtFollowRoomName);
        txtFollowRoomName.setColumns(10);

        hBoxFollowRoomName.add(Box.createHorizontalStrut(20));

        pnFollowRoom.add(Box.createVerticalStrut(20));

        JLabel lblFollowRoomPrice = new JLabel("Giá phòng:");
        lblFollowRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFollowRoomPrice.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box hBoxFollowRoomPrice = Box.createHorizontalBox();

        hBoxFollowRoomPrice.add(Box.createHorizontalStrut(20));
        hBoxFollowRoomPrice.add(lblFollowRoomPrice);

        pnFollowRoom.add(hBoxFollowRoomPrice);

        hBoxFollowRoomPrice.add(Box.createHorizontalStrut(20));

        txtFollowRoomPrice = new JTextField();
        txtFollowRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtFollowRoomPrice.setEditable(false);
        hBoxFollowRoomPrice.add(txtFollowRoomPrice);
        txtFollowRoomPrice.setColumns(10);

        hBoxFollowRoomPrice.add(Box.createHorizontalStrut(20));

        pnFollowRoom.add(Box.createVerticalStrut(20));

        JLabel lblFollowRoomType = new JLabel("Loại phòng:");
        lblFollowRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFollowRoomType.setAlignmentX(Component.CENTER_ALIGNMENT);

        hBoxFollowRoomType = Box.createHorizontalBox();
        pnFollowRoom.add(hBoxFollowRoomType);

        hBoxFollowRoomType.add(Box.createHorizontalStrut(20));
        hBoxFollowRoomType.add(lblFollowRoomType);

        hBoxFollowRoomType.add(Box.createHorizontalStrut(20));

        txtFollowRoomType = new JTextField();
        txtFollowRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtFollowRoomType.setEditable(false);
        hBoxFollowRoomType.add(txtFollowRoomType);
        txtFollowRoomType.setColumns(10);

        hBoxFollowRoomType.add(Box.createHorizontalStrut(20));

        pnFollowRoom.add(Box.createVerticalStrut(20));

        horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

        btnApply = new JButton("Xác nhận");
        btnApply.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnApply.setBackground(new Color(107, 208, 107));
        btnApply.addActionListener(this);
        horizontalBoxRoomChange.add(btnApply);

        horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

        pnRoomChange.add(Box.createVerticalStrut(20));
    }

    private List<Phong> validRoom(List<Phong> validRooms) {
        List<Phong> rooms = new ArrayList<>();

        for (Phong room : validRooms) {
            if (room.getTrangThai() == TrangThaiPhong.PHONG_TRONG) {
                rooms.add(room);
            }
        }
        return rooms;
    }

    private void initData() {
        rooms = validRoom(phongDao.getPhongLoaiPhongLichSuaGiaByConditionTime());

        List<LoaiPhong> loaiPhongList = loaiPhongDao.getAllLoaiPhong();
        cbTypeRoom.addItem((new LoaiPhong(null, "tất cả", TrangThaiLoaiPhong.HIEU_LUC)));
        loaiPhongList.forEach(loaiPhong -> {
            cbTypeRoom.addItem(loaiPhong);
        });
    }

    private void addRoomsPanel() {
        pnRoomScrollPane = new JPanel();
        pnRoomScrollPane.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(pnRoomScrollPane);
        pnRoomScrollPane.setLayout(new GridLayout(0, 4, 0, 0));

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);

        JPanel pnRooms = new JPanel();
        pnRooms.setBackground(new Color(255, 255, 255));
        pnRooms.setLayout(new BoxLayout(pnRooms, BoxLayout.X_AXIS));

        pnCenter.add(pnRooms, BorderLayout.CENTER);

        Box scrollPaneBox = Box.createVerticalBox();
        scrollPaneBox.add(scrollPane);

        pnRooms.add(Box.createHorizontalStrut(20));

        pnRooms.add(scrollPaneBox);

        scrollPaneBox.add(Box.createVerticalStrut(10));

        pnRooms.add(Box.createHorizontalStrut(20));

        initData();

        loadRooms(rooms);
    }

    private void loadRooms(List<Phong> newRooms) {
        pnRoomScrollPane.removeAll();
        List<JPanel> roomPanels = RoomPanelUtil.createPhongPanels(newRooms, this);
        roomPanels.forEach(pnRoomScrollPane::add);

        pnRoomScrollPane.revalidate();
        pnRoomScrollPane.repaint();
    }

    private void addFormPanel() {
        JPanel pnForm = new JPanel();
        pnForm.setBackground(new Color(255, 255, 255));
        pnCenter.add(pnForm, BorderLayout.NORTH);
        pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));

        pnForm.add(Box.createVerticalStrut(20));

        Box horizontalBoxForm = Box.createHorizontalBox();
        pnForm.add(horizontalBoxForm);

        horizontalBoxForm.add(Box.createHorizontalStrut(20));

        JLabel lblRoomName = new JLabel("Tên phòng:");
        lblRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBoxForm.add(lblRoomName);

        horizontalBoxForm.add(Box.createHorizontalStrut(20));

        txtRoomName = new JTextField();
        txtRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBoxForm.add(txtRoomName);
        txtRoomName.setColumns(10);

        horizontalBoxForm.add(Box.createHorizontalStrut(20));

        JLabel lblRoomType = new JLabel("Loại phòng:");
        lblRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBoxForm.add(lblRoomType);

        horizontalBoxForm.add(Box.createHorizontalStrut(20));

        cbTypeRoom = new JComboBox<LoaiPhong>();
        cbTypeRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBoxForm.add(cbTypeRoom);

        horizontalBoxForm.add(Box.createHorizontalStrut(20));

        pnForm.add(Box.createVerticalStrut(20));

        JPanel pnButton = new JPanel();
        pnButton.setBackground(new Color(255, 255, 255));
        pnButton.setLayout(new BorderLayout(0, 0));

        btnFind = new JButton("Tìm kiếm");
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setBackground(new Color(107, 208, 107));
        pnButton.add(btnFind, BorderLayout.EAST);
        btnFind.addActionListener(this);

        Box horizontalBoxButton = Box.createHorizontalBox();
        pnForm.add(horizontalBoxButton);
        horizontalBoxButton.add(pnButton);

        horizontalBoxButton.add(Box.createHorizontalStrut(20));

        pnForm.add(Box.createVerticalStrut(10));
    }

    private void addPanelNorth() {
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(new Color(97, 250, 204));
        getContentPane().add(pnNorth, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Chuyển phòng");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnNorth.add(lblTitle);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnFind)) {
            rooms = validRoom(phongDao.GetPhongByTenAndLoaiPhong(txtRoomName.getText(), (LoaiPhong) cbTypeRoom.getSelectedItem()));
            loadRooms(rooms);
        } else if (o.equals(btnApply)) {
            if(!(txtFollowRoomName.getText().length() > 0)){
                JOptionPane.showMessageDialog(this, "Chưa chọn phòng mới", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            PhieuDatPhong phieuDatPhong = new PhieuDatPhong(null, Time.valueOf(LocalTime.now()), null, hoaDon, selectedPhong);
            if (phieuDatPhongDAO.changeRoom(phieuDatPhong)) {
                JOptionPane.showMessageDialog(this, "Chuyển phòng thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Chuyển phòng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            setVisible(false);
        }
    }

    @Override
    public void onPhongPanelClicked(List<Phong> listPhong) {
        Phong phong = listPhong.get(listPhong.size() - 1);
        selectedPhong = phong;
        txtFollowRoomName.setText(phong.getTenPhong());
        txtFollowRoomPrice.setText(FormatCurrencyUtil.formatCurrency(phong.getLoaiPhong().getGia()));
        txtFollowRoomType.setText(phong.getLoaiPhong().getTenLoaiPhong());
    }
}