package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import entity.Phong;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class QuanLyPhong extends JFrame {
	private JPanel pnNorth, pnCenter, pnSouth, pnInfo, pnFind, pnListRoom;
	private JLabel lblTitle, lblMaPhong, lblTenPhong, lblSoLuong, lblTrangThai, lblLoai;
	private JTextField txtMaPhong, txtTenPhong, txtTrangThai;
	private JSpinner spnSoLuong;
	private JButton btnThem, btnCapNhat, btnXoaTrang, btnTim;
	private Box box, box1, box2, box3;
	private JComboBox<String> cmbLoai;
	private JScrollPane scroll;
	private List<Phong> listPhong;

	public QuanLyPhong() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Quản lý phòng");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(this);

		getContentPane().add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("QUẢN LÝ PHÒNG"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

		getContentPane().add(pnCenter = new JPanel(), BorderLayout.CENTER);
//		pnCenter.setLayout(new GridLayout(3, 1));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

		pnCenter.add(pnInfo = new JPanel());
		pnCenter.add(pnFind = new JPanel());
//		pnCenter.add(pnListRoom = new JPanel());

		pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));
		pnInfo.add(box = Box.createVerticalBox());
		box.add(box1 = Box.createHorizontalBox());
		box.add(box.createVerticalStrut(10));
		box.add(box2 = Box.createHorizontalBox());
		box.add(box.createVerticalStrut(10));
		box.add(box3 = Box.createHorizontalBox());

		box1.add(lblMaPhong = new JLabel("Mã phòng:"));
		box1.add(txtMaPhong = new JTextField());
		box1.add(Box.createHorizontalStrut(10));
		box1.add(lblTenPhong = new JLabel("Tên phòng:"));
		box1.add(txtTenPhong = new JTextField());

		box2.add(lblSoLuong = new JLabel("Sức chứa:"));
		box2.add(spnSoLuong = new JSpinner());
		box2.add(Box.createHorizontalStrut(10));
		box2.add(lblTrangThai = new JLabel("Trạng thái:"));
		box2.add(txtTrangThai = new JTextField());

		box3.add(Box.createHorizontalStrut(700));
		box3.add(btnThem = new JButton("Thêm"));
		box3.add(Box.createHorizontalStrut(10));
		box3.add(btnCapNhat = new JButton("Cập nhật"));
		box3.add(Box.createHorizontalStrut(10));
		box3.add(btnXoaTrang = new JButton("Xóa trắng"));

		spnSoLuong.setPreferredSize(new Dimension(415, 20));
		lblMaPhong.setPreferredSize(lblSoLuong.getPreferredSize());

		pnFind.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		pnFind.setLayout(new BoxLayout(pnFind, BoxLayout.Y_AXIS));
		pnFind.add(box = Box.createHorizontalBox());

		box.add(lblLoai = new JLabel("Loại phòng"));
		box.add(cmbLoai = new JComboBox<String>());
		cmbLoai.setPreferredSize(new Dimension(400, 20));
		box.add(lblTenPhong = new JLabel("Tên phòng:"));
		box.add(txtTenPhong = new JTextField());

		createListRoom();
	}

	private void createListRoom() {
		pnListRoom = new JPanel();
		pnListRoom.setBackground(new Color(255, 255, 255));
		scroll = new JScrollPane(pnListRoom);
		pnCenter.add(scroll);

		pnListRoom.setLayout(new GridLayout(0, 6, 0, 0));
		pnListRoom.setBorder(BorderFactory.createLineBorder(Color.black));

//		listPhong.forEach(phong -> {
//			JPanel roomPanel = new JPanel();
//			roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
//			roomPanel.setBackground(new Color(255, 255, 255));
//
//			roomPanel.add(Box.createHorizontalStrut(15));

//			JLabel imageLabel = 
//		});

	}

	public static void main(String[] args) {
		new QuanLyPhong().setVisible(true);
	}
}
