package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.PhongPanelClickListener;
import utils.ResizeImageUtil;
import utils.RoomPanelUtil;
import javax.swing.DefaultComboBoxModel;

public class GD_QuanLyDatPhong extends JFrame implements PhongPanelClickListener {

	private JPanel contentPane;
	private JTextField txtName, txtCapacity;
	private JPanel pnCenter;
	private List<Phong> listPhong;
	private JPanel pnListRoom;
	private JPanel pnNote;
	private JComboBox cbStatus;
	private JComboBox cbType;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QuanLyDatPhong frame = new GD_QuanLyDatPhong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_QuanLyDatPhong() throws IOException {
		initGUI();
	}

	private void setupFrame() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }
	
	private void initGUI() throws IOException {
		setupFrame();
		
		addMenuBar();

		addPanelNorth();

		addLeftPanelButton();

		addPanelCenter();
	}

	private void addPanelCenter() throws IOException {
		pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		addForm();

		initData();

		addPanelRoom();

		addNotePanel();	
	}

	private void addNotePanel() {
		pnNote = new JPanel();
		pnNote.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnNote, BorderLayout.SOUTH);

		pnNote.setLayout(new GridLayout(0, 4, 0, 0));
		
		TrangThaiPhong[] dsTrangThaiPhong = TrangThaiPhong.values();
		for(TrangThaiPhong currentTrangThaiPhong : dsTrangThaiPhong) {
			JPanel pnNotePhong = new JPanel();
			pnNotePhong.setBackground(new Color(255, 255, 255));
		
			
			JLabel imageLabel = new JLabel(ResizeImageUtil.getImageByTypePhong(currentTrangThaiPhong, 40, 40));
			pnNotePhong.add(imageLabel);
			imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			JLabel lblPhongTrong = new JLabel(currentTrangThaiPhong.getCustomName());
			lblPhongTrong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			pnNotePhong.add(lblPhongTrong);

			pnNote.add(pnNotePhong);
		}
	}

	private void addPanelRoom() throws IOException {
		pnListRoom = new JPanel();
		pnListRoom.setBackground(new Color(255, 255, 255));
		JScrollPane scrollPane = new JScrollPane(pnListRoom);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setUnitIncrement(16);
		pnCenter.add(scrollPane, BorderLayout.CENTER);

		pnListRoom.setLayout(new GridLayout(0, 5, 0, 0));
		pnListRoom.setBorder(BorderFactory.createLineBorder(Color.black));

		List<JPanel> phongPanels = RoomPanelUtil.createPhongPanels(listPhong, this);
		phongPanels.forEach(pnListRoom::add);
	}

	private void initData() {
		listPhong = new ArrayList<Phong>();
		LoaiPhong loaiPhong = new LoaiPhong("001", "Phòng thường", TrangThaiLoaiPhong.HIEU_LUC);
		for (int i = 0; i < 18; i++) {
			listPhong.add(new Phong("00" + (i + 1), loaiPhong, "00" + (i + 1), 5, TrangThaiPhong.PHONG_TRONG));
		}
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
		cbStatus.setModel(new DefaultComboBoxModel<>(TrangThaiPhong.values()));
		cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstFormHorizontalBox.add(cbStatus);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JLabel lblType = new JLabel("Loại phòng");
		lblType.setPreferredSize(lblStatus.getPreferredSize());
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstFormHorizontalBox.add(lblType);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

		cbType = new JComboBox();
		//please change in future
		cbType.setModel(new DefaultComboBoxModel(new String[] {"Phòng vip", "Phòng thường"}));
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstFormHorizontalBox.add(cbType);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JButton btnFind = new JButton("Tìm kiếm");
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFind.setBackground(new Color(107, 208, 107));
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

		JLabel lblCapacity = new JLabel("Sức chứa");
		lblCapacity.setPreferredSize(lblStatus.getPreferredSize());
		lblCapacity.setFont(new Font("Tahoma", Font.BOLD, 14));
		secondFormHorizontalBox.add(lblCapacity);

		secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

		txtCapacity = new JTextField();
		txtCapacity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secondFormHorizontalBox.add(txtCapacity);
		txtCapacity.setColumns(10);

		secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JButton btnNewButton = new JButton("Xóa trắng");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(107, 208, 107));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		secondFormHorizontalBox.add(btnNewButton);

		secondFormHorizontalBox.add(Box.createHorizontalStrut(20));
	}

	private void addPanelNorth() {
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(new Color(97, 250, 204));
		getContentPane().add(pnNorth, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Quản lý đặt phòng");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnNorth.add(lblTitle);
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

	private void addLeftPanelButton() {
		JPanel pnLeft = new JPanel();
		pnLeft.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnLeft, BorderLayout.WEST);

		JPanel pnLeftButton = new JPanel();
		pnLeftButton.setBackground(new Color(255, 255, 255));
		pnLeftButton.setLayout(new GridLayout(0, 1, 20, 30));

		JButton btnDatPhong = new JButton("Đặt Phòng");
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDatPhong.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnDatPhong);

		JButton btnChuyenPhong = new JButton("Chuyển Phòng");
		btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChuyenPhong.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnChuyenPhong);

		JButton btnHuyDatPhong = new JButton("Hủy Đặt Phòng");
		btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuyDatPhong.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnHuyDatPhong);

		JButton btnDatPhongCho = new JButton("Đặt Phòng Chờ");
		btnDatPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDatPhongCho.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnDatPhongCho);

		JButton btnNhanPhongCho = new JButton("Nhận Phòng Chợ");
		btnNhanPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNhanPhongCho.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnNhanPhongCho);

		JButton btnXemChiTiet = new JButton("Xem Chi Tiết");
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnXemChiTiet);

		JButton btnDichVu = new JButton("Đặt Dịch Vụ");
		btnDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDichVu.setBackground(new Color(107, 208, 107));
		pnLeftButton.add(btnDichVu);

		JButton btnThanhToan = new JButton("Thanh Toán");
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

	}

	@Override
	public void onPhongPanelClicked(Phong phong) {
		txtName.setText(phong.getTenPhong());
        txtCapacity.setText(String.valueOf(phong.getSucChua()));
        System.out.println(phong.getTrangThai());
        cbType.setSelectedItem(phong.getLoaiPhong().getTenLoaiPhong());
        cbStatus.setSelectedItem(phong.getTrangThai());
	}

}
