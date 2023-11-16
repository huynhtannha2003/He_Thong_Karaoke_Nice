package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

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
import enums.TrangThaiPhong;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

public class GD_DatPhongCho extends JFrame implements PhongPanelClickListener, ActionListener {

	private JPanel contentPane, pnCenter, pnNorth, pnSouth;
	private JTextField txtNumber, txtCustomer, txtName;
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
	private JButton btnPrint;
	private JButton btnSearch;
	private JRadioButton rbtnOderDay;
	private JRadioButton rbtnToday;
	private JDateChooser dateChooser;
	private KhachHangDAO khachHangDAO;
	private PhieuDatPhongDAO phieuDatPhongDAO;
	private KhachHang kh;
	private Phong phong;
	private NhanVien nhanVien;

	public static void main(String[] args) throws IOException {
		try {
			new GD_DatPhongCho(new Phong(), new NhanVien()).setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GD_DatPhongCho(Phong selected, NhanVien currentNhanVien) throws IOException {
		phong = selected;
		nhanVien = currentNhanVien;
		phongDAO = new PhongDAO();
		loaiPhongDAO = new LoaiPhongDAO();
		khachHangDAO = new KhachHangDAO();
		phieuDatPhongDAO = new PhieuDatPhongDAO();
		initGUI();
	}

	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setBackground(new Color(255, 255, 255));
	}

	private void initGUI() throws IOException {
		setupFrame();

		addMenuBar();

		addPanelNorth();

		addPanelCenter();

		addPanelSouth();
	}

	private void addPanelCenter() throws IOException {
		pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 20));

		addForm();

		initData();

		addPanelRoom();

	}

	public void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuHeThong = new JMenu("Hệ thống");
		menuBar.add(menuHeThong);

		JMenu menuDanhMuc = new JMenu("Danh mục");
		menuBar.add(menuDanhMuc);

		JMenu menuXuLy = new JMenu("Xử lý");
		menuBar.add(menuXuLy);

		JMenu menuThongKe = new JMenu("Thống kê");
		menuBar.add(menuThongKe);

		JMenu menuTroGiup = new JMenu("Trợ giúp");
		menuBar.add(menuTroGiup);
	}

	private void addPanelRoom() throws IOException {

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
		lblNumber.setPreferredSize(new Dimension(185, 25));
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstFormHorizontalBox.add(lblNumber);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

		txtNumber = new JTextField();
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
		lblCustomer.setPreferredSize(new Dimension(185, 25));
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

		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(5, 20));
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thirdFormHorizontalBox.add(txtName);
		txtName.setColumns(10);

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

		lblType = new JLabel("Loại phòng:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		thirdFormHorizontalBox.add(lblType);

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

		cbType = new JComboBox();
		cbType.setPreferredSize(new Dimension(100, 22));
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

		secondControlHorizontalBox.add(Box.createHorizontalStrut(20));

		btnPrint = new JButton("In phiếu đặt");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrint.setBackground(new Color(107, 208, 107));
		secondControlHorizontalBox.add(btnPrint);

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
		List<LoaiPhong> loaiPhongList = loaiPhongDAO.getAllLoaiPhong();
		cbType.addItem((new LoaiPhong(null, "Tất cả", TrangThaiLoaiPhong.HIEU_LUC)));
		loaiPhongList.forEach(loaiPhong -> {
			cbType.addItem(loaiPhong);
		});
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
		for (int i = 8; i < 25; i++) {
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
		for (int i = 0; i < 61; i = i + 5) {
			if (i < 10) {
				s = "0" + i + "";
			} else {
				s = i + "";
			}
			cbMin.addItem(s);
		}
	}

	@Override
	public void onPhongPanelClicked(Phong phong) {
		txtName.setText(phong.getTenPhong());
		cbType.setSelectedItem(phong.getLoaiPhong().getTrangThai());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnCheck)) {
			if (txtNumber.getText().length() > 0) {
				kh = khachHangDAO.getCustomerByPhoneNumber(txtNumber.getText());
				if (kh == null) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
				} else {
					txtCustomer.setText(kh.getTenKhachHang());
				}
			}
		}
		if (o.equals(btnSearch)) {
			listPhong = phongDAO.GetPhongByTenAndLoaiPhong(txtName.getText(), (LoaiPhong) cbType.getSelectedItem());
			loadRooms(listPhong);
		}
		if (o.equals(rbtnToday)) {
			Calendar calendar = Calendar.getInstance();
			Date current = calendar.getTime();
			dateChooser.setDate(current);
		}
		if (o.equals(btnConfirm)) {
			if (txtNumber.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập khách hàng trước");
			}
			if (txtName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần chọn phòng trước");
			} else {
				Date time = dateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String formattedDate = dateFormat.format(time);
				String selectHours = (String) cbHours.getSelectedItem();
				String selectMin = (String) cbMin.getSelectedItem();
				DateFormat formatter = new SimpleDateFormat("HH:mm");
				
				Date d1 = formatter.parse(selectMin)
				String fullTime = formattedDate + " " + selectHours + ":" + selectMin;
	
				Date full = new SimpleDateFormat("yyyy-MM-dd").parse(fullTime);
				boolean booking = phieuDatPhongDAO.bookKaraokeRoomBefore(kh.getMaKhachHang(), kh.getTenKhachHang(),
						phong.getMaPhong(),fullTime);
				if (booking) {
					JOptionPane.showMessageDialog(this, "Đặt phòng thành công", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Đặt phòng thất bại", "Thông báo", JOptionPane.OK_OPTION);
				}
			}

		}

		if (o.equals(btnPrint))
			
		{

		}
	}
}
