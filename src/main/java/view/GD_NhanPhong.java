package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDAO;
import dao.LoaiPhongDAO;
import dao.PhieuDatPhongDAO;
import dao.PhongDAO;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.Phong;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class GD_NhanPhong extends JFrame implements ActionListener {
	private JPanel pnNorth, pnCenter, pnSouth;
	private PhongDAO phongDAO;
	private LoaiPhongDAO loaiPhongDAO;
	private DefaultTableModel modelTable;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtNumber;
	private JTextField txtCustomer;
	private JButton btnSearch;
	private JButton btnNhanMotPhong;
	private JButton btnNhanNhieuPhong;
	private JButton btnOut;
	private KhachHangDAO khachHangDAO;
	private KhachHang khachHang;
	private PhieuDatPhongDAO phieuDatPhongDAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanPhong frame = new GD_NhanPhong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_NhanPhong() {
		khachHangDAO = new KhachHangDAO();
		phongDAO = new PhongDAO();
		loaiPhongDAO = new LoaiPhongDAO();
		initGUI();
	}

	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setLocationRelativeTo(null);
		setBackground(new Color(255, 255, 255));
	}

	private void initGUI() {
		setupFrame();

		addPanelNorth();

		addPanelCenter();

		addPanelSouth();
	}

	private void addPanelNorth() {
		pnNorth = new JPanel();
		pnNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnNorth.setBackground(new Color(97, 250, 204));
		getContentPane().add(pnNorth, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Nhận phòng chờ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnNorth.add(lblTitle);

	}

	private void addPanelCenter() {
		pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 20));

		addPanelRoom();
	}

	private void addPanelRoom() {
		JPanel pnForm = new JPanel();
		pnForm.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnForm, BorderLayout.NORTH);

		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.X_AXIS));

		Box verticalForm = Box.createVerticalBox();
		pnForm.add(verticalForm);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalForm.add(verticalStrut);

		Box horizontalFormFirst = Box.createHorizontalBox();
		verticalForm.add(horizontalFormFirst);

		horizontalFormFirst.add(Box.createHorizontalStrut(20));

		JLabel lblNumber = new JLabel("Số điện thoại khách hàng: ");
		lblNumber.setPreferredSize(new Dimension(200, 25));
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalFormFirst.add(lblNumber);

		horizontalFormFirst.add(Box.createHorizontalStrut(20));

		txtNumber = new JTextField("0345678912");
		txtNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumber.setColumns(10);
		horizontalFormFirst.add(txtNumber);

		horizontalFormFirst.add(Box.createHorizontalStrut(20));

		btnSearch = new JButton("Tìm kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBackground(new Color(107, 208, 107));
		horizontalFormFirst.add(btnSearch);

		horizontalFormFirst.add(Box.createHorizontalStrut(20));

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalForm.add(verticalStrut_1);

		Box horizontalFormSecond = Box.createHorizontalBox();
		verticalForm.add(horizontalFormSecond);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalFormSecond.add(horizontalStrut);

		JLabel lblCustomer = new JLabel("Tên khách hàng: ");
		lblCustomer.setPreferredSize(new Dimension(200, 25));
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalFormSecond.add(lblCustomer);

		horizontalFormSecond.add(Box.createHorizontalStrut(20));

		txtCustomer = new JTextField();
		txtCustomer.setEditable(false);
		txtCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCustomer.setColumns(10);
		horizontalFormSecond.add(txtCustomer);

		Component horizontalStrut_1 = Box.createHorizontalStrut(40);
		horizontalFormSecond.add(horizontalStrut_1);

		String[] headers = { "STT","Mã phòng" ,"Tên phòng", "Số điện thoại", "Tên khách hàng","Ngày đặt"};
		modelTable = new DefaultTableModel(headers, 0);
		table = new JTable(modelTable);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(255, 255, 255));
		table.setMinimumSize(new Dimension(90, 0));
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnCenter.add(scrollPane, BorderLayout.CENTER);
		btnSearch.addActionListener(this);
	}

	private void addPanelSouth() {
		pnSouth = new JPanel();
		pnSouth.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnSouth, BorderLayout.SOUTH);

		Box horizontalControl = Box.createHorizontalBox();
		pnSouth.add(horizontalControl);

		btnNhanMotPhong = new JButton("Nhận 1 phòng");
		btnNhanMotPhong.setBackground(new Color(107, 208, 107));
		btnNhanMotPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalControl.add(btnNhanMotPhong);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalControl.add(horizontalStrut);

		btnNhanNhieuPhong = new JButton("Nhận nhiều phòng");
		btnNhanNhieuPhong.setBackground(new Color(107, 208, 107));
		btnNhanNhieuPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalControl.add(btnNhanNhieuPhong);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalControl.add(horizontalStrut_1);

		btnOut = new JButton("Thoát");
		btnOut.setBackground(new Color(107, 208, 107));
		btnOut.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalControl.add(btnOut);

		btnNhanMotPhong.addActionListener(this);
		btnNhanNhieuPhong.addActionListener(this);
		btnOut.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSearch)){
			if(txtNumber.getText().length() > 0){
				khachHang = khachHangDAO.getCustomerByPhoneNumber(txtNumber.getText());
				if(khachHang == null){
					JOptionPane.showMessageDialog(this,"Không tìm thấy khách hàng","Thông báo",JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					txtCustomer.setText(khachHang.getTenKhachHang());
				}
			}
		}
		if(o.equals(btnNhanMotPhong)){

		}
		if(o.equals(btnNhanNhieuPhong)){

		}
		if(o.equals(btnOut)){
			System.exit(0);
		}
	}

	private void loadData(){

	}

}
