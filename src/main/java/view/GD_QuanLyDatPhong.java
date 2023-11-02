package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class GD_QuanLyDatPhong extends JFrame {

	private JPanel contentPane;
	private JTextField txtName, txtCapacity;
	private JPanel pnCenter;
	private List<Phong> listPhong;
	private JPanel pnListRoom;
	private JPanel pnNote;

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
		
			String roomStatus = getRoomStatusToString(currentTrangThaiPhong);
			
			JLabel imageLabel = new JLabel(new ImageIcon(getImageByTypePhong(roomStatus, 40, 40)));
			pnNotePhong.add(imageLabel);
			imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			JLabel lblPhongTrong = new JLabel(currentTrangThaiPhong.getCustomName());
			lblPhongTrong.setFont(new Font("Tahoma", Font.PLAIN, 13));
			pnNotePhong.add(lblPhongTrong);

			pnNote.add(pnNotePhong);
		}
	}

	private String getRoomStatusToString(TrangThaiPhong trangThai) {
		switch (trangThai) {
		case PHONG_TRONG:
			return "phongTrong.png";
		case PHONG_DANG_SU_DUNG:
			return "phongBan.png";
		case PHONG_CHO:
			return "phongCho.png";
		case PHONG_DANG_BAO_TRI:
			return "phongDangSuaChua.png";
		default:
			return null;
		}
	}

	private Image getImageByTypePhong(String typePhong, int width, int height) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/image/" + typePhong));
		Image resizedIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return resizedIcon;
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

		listPhong.forEach(phong -> {
			JPanel phongPanel = new JPanel();
			phongPanel.setLayout(new BoxLayout(phongPanel, BoxLayout.Y_AXIS));
			phongPanel.setBackground(new Color(255, 255, 255));

			phongPanel.add(Box.createVerticalStrut(15));

			String roomStatus = getRoomStatusToString(phong.getTrangThai());
			JLabel imageLabel = new JLabel(new ImageIcon(getImageByTypePhong(roomStatus, 110, 110)));
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
			pnListRoom.add(phongPanel);
		});
	}

	private void initData() {
		listPhong = new ArrayList<>();
		LoaiPhong loaiPhong = new LoaiPhong("001", "Thường", TrangThaiLoaiPhong.HIEU_LUC);
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

		JComboBox cbStatus = new JComboBox();
		cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstFormHorizontalBox.add(cbStatus);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JLabel lblType = new JLabel("Loại phòng");
		lblType.setPreferredSize(lblStatus.getPreferredSize());
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstFormHorizontalBox.add(lblType);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JComboBox cbType = new JComboBox();
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
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
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

}
