package view;

import com.toedter.calendar.JDateChooser;
import dao.KhachHangDAO;
import dao.LoaiPhongDAO;
import dao.PhieuDatPhongDAO;
import dao.PhongDAO;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GD_DatPhongCho extends JFrame implements PhongPanelClickListener, ActionListener {

    private JPanel pnCenter, pnNorth, pnSouth;
    private JTextField txtNumber, txtCustomer, txtNameRoom;
    private List<Phong> listPhong;
    private ButtonGroup group;
    private JComboBox<String> cbMin, cbHours;
    private PhongDAO phongDAO;
    private LoaiPhongDAO loaiPhongDAO;
    private JLabel lblType;
    private JButton btnCheck;
    private JLabel lblHours;
    private JPanel pnRoomScrollPane;
    private JButton btnConfirm;
    private JComboBox cbType;
    private JButton btnSearch;
    private JRadioButton rbtnOderDay;
    private JRadioButton rbtnToday;
    private JDateChooser dateChooser;
    private KhachHangDAO khachHangDAO;
    private PhieuDatPhongDAO phieuDatPhongDAO;
    private KhachHang kh;
    private Phong Phong;
    private NhanVien nhanVien;
    private String ph;

    public GD_DatPhongCho(NhanVien currentNhanVien) {
        nhanVien = currentNhanVien;
        phongDAO = new PhongDAO();
        loaiPhongDAO = new LoaiPhongDAO();
        khachHangDAO = new KhachHangDAO();
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        initGUI();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(784, 600);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 255, 255));
    }

    private void initGUI() {
        setupFrame();

        addPanelNorth();

        addPanelCenter();

        addPanelSouth();
    }

    private void addPanelCenter() {
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        getContentPane().add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(new BorderLayout(0, 20));

        addForm();

        initData();

        addPanelRoom();

    }

    private void addPanelRoom() {

        pnRoomScrollPane = new JPanel();
        pnRoomScrollPane.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(pnRoomScrollPane);
        pnRoomScrollPane.setLayout(new GridLayout(0, 4, 0, 0));

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);

        JPanel pnRooms = new JPanel();
        pnRooms.setBackground(new Color(255, 255, 255));
        pnCenter.add(pnRooms, BorderLayout.CENTER);

        Box scrollPaneBox = Box.createVerticalBox();
        scrollPaneBox.add(scrollPane);
        pnRooms.setLayout(new BoxLayout(pnRooms, BoxLayout.X_AXIS));

        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setBackground(new Color(255, 255, 255));
        pnRooms.add(horizontalStrut);

        pnRooms.add(scrollPaneBox);

        scrollPaneBox.add(Box.createVerticalStrut(20));

        pnRooms.add(Box.createHorizontalStrut(20));

        initData();

        List<JPanel> phongPanels = RoomPanelUtil.createPhongPanels(listPhong, this);
        phongPanels.forEach(pnRoomScrollPane::add);
    }

    private void addPanelNorth() {
        pnNorth = new JPanel();
        pnNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnNorth.setBackground(new Color(97, 250, 204));
        getContentPane().add(pnNorth, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Đặt phòng chờ");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnNorth.add(lblTitle);

    }

    private void addForm() {
        JPanel pnForm = new JPanel();
        pnForm.setBackground(new Color(255, 255, 255));
        pnCenter.add(pnForm, BorderLayout.NORTH);
        pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.X_AXIS));

        Box formVerticalBox = Box.createVerticalBox();
        pnForm.add(formVerticalBox);

        formVerticalBox.add(Box.createVerticalStrut(20));

        Box firstFormHorizontalBox = Box.createHorizontalBox();
        formVerticalBox.add(firstFormHorizontalBox);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(30));

        JLabel lblNumber = new JLabel("Số điện thoại khách hàng:");
        lblNumber.setPreferredSize(new Dimension(200, 25));
        lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstFormHorizontalBox.add(lblNumber);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        txtNumber = new JTextField();
        txtNumber.setText("0345678912");
        txtNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        firstFormHorizontalBox.add(txtNumber);
        txtNumber.setColumns(10);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        btnCheck = new JButton("Kiếm tra");
        btnCheck.setBackground(new Color(107, 208, 107));
        btnCheck.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstFormHorizontalBox.add(btnCheck);

        firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

        formVerticalBox.add(Box.createVerticalStrut(20));

        Box secondFormHorizontalBox = Box.createHorizontalBox();
        formVerticalBox.add(secondFormHorizontalBox);

        secondFormHorizontalBox.add(Box.createHorizontalStrut(30));

        JLabel lblCustomer = new JLabel("Khách hàng:");
        lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCustomer.setPreferredSize(new Dimension(200, 25));
        secondFormHorizontalBox.add(lblCustomer);

        secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

        txtCustomer = new JTextField();
        txtCustomer.setEditable(false);
        txtCustomer.setPreferredSize(new Dimension(7, 23));
        txtCustomer.setMinimumSize(new Dimension(9, 23));
        txtCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
        secondFormHorizontalBox.add(txtCustomer);
        txtCustomer.setColumns(10);

        secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

        formVerticalBox.add(Box.createVerticalStrut(20));

        Box thirdFormHorizontalBox = Box.createHorizontalBox();
        formVerticalBox.add(thirdFormHorizontalBox);

        thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

        JLabel lblNameRoom = new JLabel("Tên phòng:");
        lblNameRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        thirdFormHorizontalBox.add(lblNameRoom);

        thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

        txtNameRoom = new JTextField();
        txtNameRoom.setPreferredSize(new Dimension(5, 20));
        txtNameRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        thirdFormHorizontalBox.add(txtNameRoom);
        txtNameRoom.setColumns(10);

        thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

        lblType = new JLabel("Loại phòng:");
        lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
        thirdFormHorizontalBox.add(lblType);

        thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

        cbType = new JComboBox();
        cbType.setPreferredSize(new Dimension(100, 22));
        cbType.addItem(new LoaiPhong(null, "Tất cả", TrangThaiLoaiPhong.HIEU_LUC));
        loaiPhongDAO.getAllLoaiPhong().forEach(cbType::addItem);
        cbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
        thirdFormHorizontalBox.add(cbType);

        thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

        btnSearch = new JButton("Tìm kiếm");
        btnSearch.setBackground(new Color(107, 208, 107));
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        thirdFormHorizontalBox.add(btnSearch);

        thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));
    }

    private void addPanelSouth() {
        pnSouth = new JPanel();
        getContentPane().add(pnSouth, BorderLayout.SOUTH);
        pnSouth.setBackground(new Color(255, 255, 255));
        pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.X_AXIS));

        Box south_VerticalBox = Box.createVerticalBox();
        pnSouth.add(south_VerticalBox);

        south_VerticalBox.add(Box.createVerticalStrut(20));

        Box controlHorizontalBox = Box.createHorizontalBox();
        south_VerticalBox.add(controlHorizontalBox);

        controlHorizontalBox.add(Box.createHorizontalStrut(20));

        Box controlVerticalBox = Box.createVerticalBox();
        controlHorizontalBox.add(controlVerticalBox);

        Box firstControlHorizontalBox = Box.createHorizontalBox();
        controlVerticalBox.add(firstControlHorizontalBox);

        JLabel lblCheckIn = new JLabel("Nhận phòng:");
        lblCheckIn.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstControlHorizontalBox.add(lblCheckIn);

        firstControlHorizontalBox.add(Box.createHorizontalStrut(20));

        rbtnToday = new JRadioButton("Hôm nay");
        rbtnToday.setBackground(new Color(255, 255, 255));
        rbtnToday.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstControlHorizontalBox.add(rbtnToday);

        firstControlHorizontalBox.add(Box.createHorizontalStrut(20));

        rbtnOderDay = new JRadioButton("Ngày khác");
        rbtnOderDay.setBackground(new Color(255, 255, 255));
        rbtnOderDay.setFont(new Font("Tahoma", Font.BOLD, 14));
        firstControlHorizontalBox.add(rbtnOderDay);

        group = new ButtonGroup();
        group.add(rbtnOderDay);
        group.add(rbtnToday);
        firstControlHorizontalBox.add(Box.createHorizontalStrut(50));

        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(20, 20));
        firstControlHorizontalBox.add(dateChooser);

        controlVerticalBox.add(Box.createVerticalStrut(20));

        Box secondControlHorizontalBox = Box.createHorizontalBox();
        controlVerticalBox.add(secondControlHorizontalBox);

        JLabel lblTime = new JLabel("Thời gian nhận phòng:");
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        secondControlHorizontalBox.add(lblTime);

        secondControlHorizontalBox.add(Box.createHorizontalStrut(20));

        cbHours = new JComboBox();
        cbHours.setFont(new Font("Tahoma", Font.PLAIN, 14));
        secondControlHorizontalBox.add(cbHours);
        
                Component horizontalStrut = Box.createHorizontalStrut(20);
                secondControlHorizontalBox.add(horizontalStrut);

        lblHours = new JLabel("Giờ");
        lblHours.setFont(new Font("Tahoma", Font.BOLD, 14));
        secondControlHorizontalBox.add(lblHours);

        secondControlHorizontalBox.add(Box.createHorizontalStrut(20));

        cbMin = new JComboBox();
        cbMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        secondControlHorizontalBox.add(cbMin);

        JLabel lblMin = new JLabel("Phút");
        lblMin.setFont(new Font("Tahoma", Font.BOLD, 14));
        secondControlHorizontalBox.add(lblMin);

        secondControlHorizontalBox.add(Box.createHorizontalStrut(20));

        btnConfirm = new JButton("Xác nhận");
        btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnConfirm.setBackground(new Color(107, 208, 107));
        secondControlHorizontalBox.add(btnConfirm);

        controlHorizontalBox.add(Box.createHorizontalStrut(20));

        south_VerticalBox.add(Box.createVerticalStrut(20));
        inputHours();
        inputMin();
        btnCheck.addActionListener(this);
        btnSearch.addActionListener(this);
        btnConfirm.addActionListener(this);
        rbtnToday.addActionListener(this);
    }

    private void initData() {
        listPhong = phongDAO.getAllPhongTrong();
    }

    private void loadRooms(List<Phong> newRooms) {
        pnRoomScrollPane.removeAll();
        List<JPanel> roomPanels = RoomPanelUtil.createPhongPanels(newRooms, this);
        roomPanels.forEach(pnRoomScrollPane::add);

        pnRoomScrollPane.revalidate();
        pnRoomScrollPane.repaint();
    }

    private void inputHours() {
        String s;
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String formattedHour = currentTime.format(formatter);
        int currentHours = Integer.parseInt(formattedHour);
        for (int i = currentHours; i < 25; i++) {
            if (i < 10) {
                s = "0" + i + "";
            } else {
                s = i + "";
            }
            cbHours.addItem(s);
        }
    }

    private void inputMin() {
        String s;
        for (int i = 0; i < 56; i = i + 5) {
            if (i < 10) {
                s = "0" + i + "";
            } else {
                s = i + "";
            }
            cbMin.addItem(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnCheck)) {
            if (txtNumber.getText().length() > 0) {
                kh = khachHangDAO.getCustomerByPhoneNumber(txtNumber.getText());
                if (kh == null) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    txtCustomer.setText(kh.getTenKhachHang());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hãy nhập vào số điện thoại khách hàng cần tìm", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (o.equals(btnSearch)) {
            listPhong = phongDAO.GetPhongByTenAndLoaiPhong(txtNameRoom.getText(), (LoaiPhong) cbType.getSelectedItem());
            loadRooms(listPhong);
        }
        if (o.equals(rbtnToday)) {
            Calendar calendar = Calendar.getInstance();
            Date current = calendar.getTime();
            dateChooser.setDate(current);
        }
        if (o.equals(btnConfirm)) {
            if (txtCustomer.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần tìm khách hàng trước", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } else if (txtNameRoom.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn phòng trước", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } else if (rbtnToday.isSelected() == false && rbtnOderDay.isSelected() == false) {
                JOptionPane.showMessageDialog(this, "Bạn cần chọn thời gian đặt trước", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                Date time = dateChooser.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String formattedDate = dateFormat.format(time);
                String selectHours = (String) cbHours.getSelectedItem();
                String selectMin = (String) cbMin.getSelectedItem();
                String timeFull = formattedDate + " " + selectHours + ":" + selectMin;
                Date full;
                boolean booking = false;
                try {
                    full = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(timeFull);
                    booking = phieuDatPhongDAO.bookRoomBefore(kh.getMaKhachHang(), nhanVien.getMaNhanVien(),
                            ph, new Time(full.getTime()), new java.sql.Date(full.getTime()));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                if (booking) {
                    JOptionPane.showMessageDialog(this, "Đặt phòng thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Đặt phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        }
    }

    @Override
    public void onPhongPanelClicked(List<Phong> phong) {
        Phong currentPhong = phong.get(phong.size() - 1);
        ph = currentPhong.getMaPhong();
        txtNameRoom.setText(currentPhong.getTenPhong());
        cbType.setSelectedItem(currentPhong.getLoaiPhong());
    }
}