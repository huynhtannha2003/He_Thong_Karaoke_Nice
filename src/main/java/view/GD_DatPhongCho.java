package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

public class GD_DatPhongCho extends JFrame implements PhongPanelClickListener{

	private JPanel contentPane, pnCenter, pnNorth, pnSouth;
	private JTextField txtNumber, txtCustomer, txtName;
	private List<Phong> listPhong;
	private ButtonGroup group;
	private JComboBox<String> cbMin, cbHours;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_DatPhongCho frame = new GD_DatPhongCho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_DatPhongCho() throws IOException {
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

		JPanel pnRoomScrollPane = new JPanel();
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

	private void initData() {
		listPhong = new ArrayList<Phong>();
		LoaiPhong loaiPhong = new LoaiPhong("001", "Thường", TrangThaiLoaiPhong.HIEU_LUC);
		for (int i = 0; i < 18; i++) {
			listPhong.add(new Phong("00" + (i + 1), loaiPhong, "00" + (i + 1), 5, TrangThaiPhong.PHONG_TRONG));
		}
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

		JButton btnCheck = new JButton("Kiếm tra");
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

		JLabel lblType = new JLabel("Loại phòng:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		thirdFormHorizontalBox.add(lblType);

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JComboBox cbType = new JComboBox();
		cbType.setPreferredSize(new Dimension(100, 22));
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thirdFormHorizontalBox.add(cbType);

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JButton btnSearch = new JButton("Tìm kiếm");
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

		Component verticalStrut = Box.createVerticalStrut(20);
		south_VerticalBox.add(verticalStrut);

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

		JRadioButton rbtnToday = new JRadioButton("Hôm nay");
		rbtnToday.setBackground(new Color(255, 255, 255));
		rbtnToday.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstControlHorizontalBox.add(rbtnToday);

		firstControlHorizontalBox.add(Box.createHorizontalStrut(20));

		JRadioButton rbtnOderDay = new JRadioButton("Ngày khác");
		rbtnOderDay.setBackground(new Color(255, 255, 255));
		rbtnOderDay.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstControlHorizontalBox.add(rbtnOderDay);

		group = new ButtonGroup();
		group.add(rbtnOderDay);
		group.add(rbtnToday);
		firstControlHorizontalBox.add(Box.createHorizontalStrut(50));

		JDateChooser dateChooser = new JDateChooser();
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

		JLabel lblHours = new JLabel("Giờ");
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

		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConfirm.setBackground(new Color(107, 208, 107));
		secondControlHorizontalBox.add(btnConfirm);

		secondControlHorizontalBox.add(Box.createHorizontalStrut(20));

		JButton btnPrint = new JButton("In phiếu đặt");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrint.setBackground(new Color(107, 208, 107));
		secondControlHorizontalBox.add(btnPrint);

		controlHorizontalBox.add(Box.createHorizontalStrut(20));

		south_VerticalBox.add(Box.createVerticalStrut(20));
		inputHours();
		inputMin();
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
		for (int i = 0; i < 61; i= i + 5) {
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
		
	}
}
