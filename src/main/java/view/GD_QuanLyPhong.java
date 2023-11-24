package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GD_QuanLyPhong extends JPanel implements ActionListener, PhongPanelClickListener {
	private JPanel pnNorth, pnCenter, pnSouth, pnInfo, pnFind, pnListRoom;
	private JLabel lblTitle, lblMaPhong, lblTenPhong, lblSoLuong, lblTrangThai, lblLoai, lblTuKhoaTim;
	private JTextField txtMaPhong, txtTenPhong, txtTrangThai, txtTuKhoaTim;
	private JSpinner spnSoLuong;
	private JButton btnThem, btnCapNhat, btnXoaTrang, btnTim;
	private Box box, box1, box2, box3;
	private JComboBox<String> cmbLoai;
	private JScrollPane scroll;
	private List<Phong> listPhong;

	public GD_QuanLyPhong() {
		createGUI();
	}

	private void createGUI() {
		setSize(1000, 700);
		setLayout(new BorderLayout());

		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("Quản lý phòng"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));

		add(pnCenter = new JPanel(), BorderLayout.CENTER);
//		pnCenter.setLayout(new GridLayout(3, 1));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

		pnCenter.add(pnInfo = new JPanel());
		pnCenter.add(pnFind = new JPanel());

		pnInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));

		pnInfo.add(box = Box.createVerticalBox());
		box.add(Box.createVerticalStrut(10));
		box.add(box1 = Box.createHorizontalBox());
		box.add(box.createVerticalStrut(10));
		box.add(box2 = Box.createHorizontalBox());
		box.add(box.createVerticalStrut(10));
		box.add(box3 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));

		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblMaPhong = new JLabel("Mã phòng:"));
		lblMaPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		box1.add(Box.createHorizontalStrut(15));
		box1.add(txtMaPhong = new JTextField());
		txtMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(25));
		box1.add(lblTenPhong = new JLabel("Tên phòng:"));
		lblTenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		box1.add(Box.createHorizontalStrut(20));
		box1.add(txtTenPhong = new JTextField());
		txtTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(20));

		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblSoLuong = new JLabel("Sức chứa:"));
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		box2.add(Box.createHorizontalStrut(20));
		box2.add(spnSoLuong = new JSpinner());
		spnSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblTrangThai = new JLabel("Trạng thái:"));
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		box2.add(Box.createHorizontalStrut(23));
		box2.add(txtTrangThai = new JTextField());
		txtTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(20));

		box3.add(Box.createHorizontalStrut(670));
		box3.add(btnThem = new JButton("Thêm"));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3.add(Box.createHorizontalStrut(10));
		box3.add(btnCapNhat = new JButton("Cập nhật"));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3.add(Box.createHorizontalStrut(10));
		box3.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));

		spnSoLuong.setPreferredSize(new Dimension(370, 20));
		lblMaPhong.setPreferredSize(new Dimension(79, 17));

		pnFind.setBorder(BorderFactory.createLineBorder(Color.black));
		pnFind.setLayout(new BoxLayout(pnFind, BoxLayout.Y_AXIS));
		pnFind.add(box = Box.createVerticalBox());

		box.add(Box.createVerticalStrut(10));
		box.add(box1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));

		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblLoai = new JLabel("Tìm kiếm theo:"));
		lblLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(cmbLoai = new JComboBox<String>());
		cmbLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbLoai.setPreferredSize(new Dimension(300, 20));
		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblTuKhoaTim = new JLabel("Nhập vào từ khóa:"));
		lblTuKhoaTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(txtTuKhoaTim = new JTextField());
		txtTuKhoaTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(20));

		initRoomData();
		createListRoom();

		btnThem.setBackground(new Color(107, 208, 107));
		btnCapNhat.setBackground(new Color(107, 208, 107));
		btnTim.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setBackground(new Color(107, 208, 107));

		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);

	}

	private void initRoomData() {
		listPhong = new ArrayList<>();
		LoaiPhong loaiPhong = new LoaiPhong("001", "Thường", TrangThaiLoaiPhong.HIEU_LUC);
		for (int i = 0; i < 18; i++) {
			listPhong.add(new Phong("00" + (i + 1), loaiPhong, "00" + (i + 1), 5, TrangThaiPhong.PHONG_TRONG));
		}
	}

	private void createListRoom() {
		pnListRoom = new JPanel();
		pnListRoom.setBackground(new Color(255, 255, 255));
		scroll = new JScrollPane(pnListRoom);
		JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
		verticalScrollBar.setUnitIncrement(15);
		pnCenter.add(scroll);

		pnListRoom.setLayout(new GridLayout(0, 6, 0, 0));
		pnListRoom.setBorder(BorderFactory.createLineBorder(Color.black));

		List<JPanel> phongPanel = RoomPanelUtil.createPhongPanels(listPhong, this);
		phongPanel.forEach(pnListRoom::add);
	}

	private void clearInput() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		spnSoLuong.setValue(0);
		txtTrangThai.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			clearInput();
		}
	}

	public static void main(String[] args) {
		new GD_QuanLyPhong().setVisible(true);
	}

	@Override
	public void onPhongPanelClicked(Phong phong) {

	}

}