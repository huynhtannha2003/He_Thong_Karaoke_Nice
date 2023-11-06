package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GD_DatPhong extends JFrame implements ActionListener {

	private JPanel pnNorth, pnCenter, pnSouth;
	private JLabel lblTitle;
	private JButton btnDatPhong, btnThoat;
	private Box box, box1, box2, box3;
	private Box box2_2;
	private Box box1_2;
	private Box box_1;
	private Box box3_1;
	private Box box2_1;
	private Box box1_1;
	private JPanel pnInfoPhong;
	private JPanel pnInfoKhach;
	private JPanel pnInfoPhongLeft;
	private JPanel pnInfoPhongRight;
	private JLabel lblTenPhongTitle;
	private JLabel lblTenPhong;
	private JLabel lblTrangThaiTitle;
	private JLabel lblTrangThai;
	private JLabel lblLoaiPhongTitle;
	private JLabel lblLoaiPhong;
	private JLabel lblNull;
	private JLabel lblSucChuaTitle;
	private JLabel lblSuaChua;
	private JLabel lblGiaTienTitle;
	private JLabel lblGiaTien;
	private JLabel lblSDTKhach;
	private JTextField txtSDTKhach;
	private JButton btnKiemTra;
	private JLabel lblTenKH;
	private JTextField txtTenKH;
	private Component horizontalStrut;

	public GD_DatPhong() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Đặt phòng");
		setSize(555, 385);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		setResizable(false);

		getContentPane().add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("Đặt phòng"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

		getContentPane().add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.setLayout(new GridLayout(0, 1, 50, 30));

		pnInfoPhong = new JPanel();
		pnCenter.add(pnInfoPhong);
		pnInfoPhong.setLayout(new GridLayout(0, 2, 0, 0));

		pnInfoPhongLeft = new JPanel();
		pnInfoPhong.add(pnInfoPhongLeft);
		pnInfoPhongLeft.setLayout(new BoxLayout(pnInfoPhongLeft, BoxLayout.Y_AXIS));
		pnInfoPhongLeft.add(box = Box.createVerticalBox());
		box.add(Box.createVerticalStrut(20));
		box.setAlignmentX(Component.CENTER_ALIGNMENT);
		box.add(box1 = Box.createHorizontalBox());
		box1.setAlignmentX(Component.LEFT_ALIGNMENT);
		box1.setAlignmentY(Component.CENTER_ALIGNMENT);
		box.add(Box.createVerticalStrut(20));

		lblTenPhongTitle = new JLabel("Tên phòng:");
		lblTenPhongTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(50));
		box1.add(lblTenPhongTitle);

		box1.add(Box.createHorizontalStrut(10));
		lblTenPhong = new JLabel("001");
		lblTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(lblTenPhong);

		box.add(box2 = Box.createHorizontalBox());
		box2.setAlignmentX(Component.LEFT_ALIGNMENT);
		box2.setAlignmentY(Component.CENTER_ALIGNMENT);
		box.add(Box.createVerticalStrut(20));

		lblTrangThaiTitle = new JLabel("Trạng thái:");
		lblTrangThaiTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(50));
		box2.add(lblTrangThaiTitle);

		box2.add(Box.createHorizontalStrut(10));
		lblTrangThai = new JLabel("Phòng trống");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(lblTrangThai);

		box.add(box3 = Box.createHorizontalBox());
		box3.setAlignmentX(Component.LEFT_ALIGNMENT);
		box3.setAlignmentY(Component.CENTER_ALIGNMENT);

		lblLoaiPhongTitle = new JLabel("Loại phòng:");
		lblLoaiPhongTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3.add(Box.createHorizontalStrut(50));
		box3.add(lblLoaiPhongTitle);

		box3.add(Box.createHorizontalStrut(10));
		lblLoaiPhong = new JLabel("Phòng vip");
		lblLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3.add(lblLoaiPhong);

		//

		pnInfoPhongRight = new JPanel();
		pnInfoPhong.add(pnInfoPhongRight);
		pnInfoPhongRight.setLayout(new BoxLayout(pnInfoPhongRight, BoxLayout.X_AXIS));

		pnInfoPhongRight.add(box_1 = Box.createVerticalBox());
		box_1.add(box1_1 = Box.createHorizontalBox());
		box1_1.setAlignmentY(Component.CENTER_ALIGNMENT);
		box1_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		box1_1.add(Box.createHorizontalStrut(100));

		lblNull = new JLabel(" ");
		lblNull.setFont(new Font("Tahoma", Font.PLAIN, 13));
		box1_1.add(lblNull);
		box_1.add(Box.createVerticalStrut(20));
		box_1.add(box2_1 = Box.createHorizontalBox());
		box2_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		box2_1.add(Box.createHorizontalStrut(90));

		lblSucChuaTitle = new JLabel("Sức chứa:");
		lblSucChuaTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblSucChuaTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2_1.add(lblSucChuaTitle);

		box2_1.add(Box.createHorizontalStrut(10));
		lblSuaChua = new JLabel("05");
		lblSuaChua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2_1.add(lblSuaChua);
		box_1.add(Box.createVerticalStrut(20));
		box_1.add(box3_1 = Box.createHorizontalBox());
		box3_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		box3_1.add(Box.createHorizontalStrut(90));

		lblGiaTienTitle = new JLabel("Giá tiền:");
		lblGiaTienTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3_1.add(lblGiaTienTitle);

		box3_1.add(Box.createHorizontalStrut(10));
		lblGiaTien = new JLabel("100 000 VND");
		lblGiaTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3_1.add(lblGiaTien);

		pnInfoKhach = new JPanel();
		pnCenter.add(pnInfoKhach);
		pnInfoKhach.setLayout(new BoxLayout(pnInfoKhach, BoxLayout.Y_AXIS));

		pnInfoKhach.add(box = Box.createVerticalBox());
		box.setMaximumSize(new Dimension(555, 80));
		box.add(box1_2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(20));
		box1_2.add(Box.createHorizontalStrut(50));

		lblSDTKhach = new JLabel("SĐT khách:");
		lblSDTKhach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1_2.add(lblSDTKhach);

		box1_2.add(Box.createHorizontalStrut(10));
		txtSDTKhach = new JTextField();
		txtSDTKhach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1_2.add(txtSDTKhach);
		txtSDTKhach.setColumns(10);

		box1_2.add(Box.createHorizontalStrut(10));
		btnKiemTra = new JButton("Kiểm tra");
		btnKiemTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1_2.add(btnKiemTra);
		box1_2.add(Box.createHorizontalStrut(45));

		box.add(box2_2 = Box.createHorizontalBox());
		box2_2.add(Box.createHorizontalStrut(50));

		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2_2.add(lblTenKH);

		box2_2.add(Box.createHorizontalStrut(10));
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2_2.add(txtTenKH);
		txtTenKH.setColumns(10);

		txtTenKH.setEditable(false);

		horizontalStrut = Box.createHorizontalStrut(140);
		box2_2.add(horizontalStrut);
		lblSDTKhach.setPreferredSize(lblTenKH.getPreferredSize());
		pnInfoKhach.setPreferredSize(box.getPreferredSize());

		getContentPane().add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) pnSouth.getLayout();
		flowLayout.setVgap(15);
		pnSouth.add(Box.createHorizontalStrut(300));
		pnSouth.add(btnDatPhong = new JButton("Đặt phòng"));
		btnDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnSouth.add(Box.createHorizontalStrut(10));
		pnSouth.add(btnThoat = new JButton("Thoát"));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnDatPhong.setBackground(new Color(107, 208, 107));
		btnKiemTra.setBackground(new Color(107, 208, 107));
		btnThoat.setBackground(new Color(236, 73, 73));

		btnDatPhong.addActionListener(this);
		btnKiemTra.addActionListener(this);
		btnThoat.addActionListener(this);
	}

	private void exitButton() {
		int key = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy?", "Thoát", JOptionPane.YES_NO_OPTION);
		if (key == JOptionPane.YES_OPTION) {
			setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			exitButton();
		}
	}

	public static void main(String[] args) {
		new GD_DatPhong().setVisible(true);
	}

}
