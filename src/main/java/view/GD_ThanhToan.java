package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GD_ThanhToan extends JFrame {
	private JPanel pnNorth, pnCenter, pnSouth, pnTable, pnSouthLeft, pnSouthRight;
	private JLabel lblTitle, lblPhoneNumTitle, lblCheckInTitle, lblCusNameTitle, lblCheckOutTitle, lblEmpNameTitle,
			lblTotalTimeTitle, lblTaxTitle, lblTotalTitle;
	private JLabel lblPhoneNum, lblCusName, lblEmpName, lblCheckIn, lblCheckOut, lblTotalTime, lblCouponID, lblTax,
			lblTotal, lblReceive, lblRefund;
	private Box box, box1, box2, box3, box4;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTextField txtCouponID, txtReceive, txtRefund;
	private JButton btnAdd, btnPrint, btnPayment;
	private JPanel pnInfo;
	private JPanel pnInfoLeft;
	private JPanel pnInfoRight;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JPanel pnSouthThongTin;
	private JPanel pnSouthButton;

	public GD_ThanhToan() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Thanh toán");
		setSize(800, 650);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);

		getContentPane().add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("Thanh toán"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));

		getContentPane().add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

		pnInfo = new JPanel();
		pnCenter.add(pnInfo);
		pnInfo.setLayout(new GridLayout(0, 2, 150, 0));
		pnInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		pnInfo.setMaximumSize(new Dimension(800, 100));

		pnInfoLeft = new JPanel();
		pnInfo.add(pnInfoLeft);
		pnInfoLeft.setLayout(new GridLayout(0, 2, -20, 10));

		lblPhoneNumTitle = new JLabel("SĐT khách:");
		lblPhoneNumTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblPhoneNumTitle);
		
		int topPadding = 10;
		int leftPadding = 20;
		int botPadding = 0;
		int rightPadding = 0;
		lblPhoneNumTitle.setBorder(new EmptyBorder(topPadding, leftPadding, botPadding, rightPadding));

		lblPhoneNum = new JLabel("0986148209");
		lblPhoneNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblPhoneNum);
		lblPhoneNum.setBorder(new EmptyBorder(topPadding, 0, botPadding, rightPadding));

		lblCusNameTitle = new JLabel("Tên khách hàng:");
		lblCusNameTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblCusNameTitle);
		lblCusNameTitle.setBorder(new EmptyBorder(topPadding, leftPadding, botPadding, rightPadding));

		lblCusName = new JLabel("Nguyễn Văn A");
		lblCusName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblCusName);

		lblEmpNameTitle = new JLabel("Tên nhân viên:");
		lblEmpNameTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblEmpNameTitle);
		lblEmpNameTitle.setBorder(new EmptyBorder(topPadding, leftPadding, 10, rightPadding));

		lblEmpName = new JLabel("Trần Trung Tiến");
		lblEmpName.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblEmpName);

		pnInfoRight = new JPanel();
		pnInfo.add(pnInfoRight);
		pnInfoRight.setLayout(new GridLayout(0, 2, -60, 0));

		lblCheckInTitle = new JLabel("Ngày đặt:");
		lblCheckInTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckInTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckInTitle);
		lblCheckInTitle.setBorder(new EmptyBorder(topPadding, 0, botPadding, rightPadding));

		lblCheckIn = new JLabel("04/11/2023 - 10:30");
		lblCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckIn);
		lblCheckIn.setBorder(new EmptyBorder(topPadding, 0, botPadding, rightPadding));

		lblCheckOutTitle = new JLabel("Ngày trả phòng:");
		lblCheckOutTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckOutTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckOutTitle);

		lblCheckOut = new JLabel("04/11/2023 - 12:30");
		lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckOut);

		lblTotalTimeTitle = new JLabel("Tống thời gian:");
		lblTotalTimeTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalTimeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblTotalTimeTitle);

		lblTotalTime = new JLabel("2:00");
		lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblTotalTime);

		pnCenter.add(pnTable = new JPanel());
		pnTable.setLayout(new BoxLayout(pnTable, BoxLayout.X_AXIS));
		pnTable.add(box = Box.createHorizontalBox());
		pnTable.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		createTable();
		pnTable.setPreferredSize(new Dimension(750,100));

		getContentPane().add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		GridLayout gl_pnSouth = new GridLayout();
		gl_pnSouth.setHgap(20);
		gl_pnSouth.setColumns(2);
		gl_pnSouth.setRows(0);
		pnSouth.setLayout(gl_pnSouth);
		pnSouth.add(pnSouthLeft = new JPanel());
		pnSouth.add(pnSouthRight = new JPanel());

		pnSouthLeft.setLayout(new BoxLayout(pnSouthLeft, BoxLayout.Y_AXIS));
		pnSouthLeft.add(box = Box.createVerticalBox());
		box.add(Box.createVerticalStrut(15));
		box.add(box1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		box.add(box2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		box.add(box3 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(15));

		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblCouponID = new JLabel("Nhập mã khuyến mãi:"));
		lblCouponID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(15));
		box1.add(txtCouponID = new JTextField());
		txtCouponID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(btnAdd = new JButton("Thêm"));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBackground(new Color(107, 208, 107));

		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblReceive = new JLabel("Tiền nhận:"));
		lblReceive.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(15));
		box2.add(txtReceive = new JTextField());
		txtReceive.setFont(new Font("Tahoma", Font.PLAIN, 14));

		box3.add(Box.createHorizontalStrut(20));
		box3.add(lblRefund = new JLabel("Tiền trả lại:"));
		lblRefund.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box3.add(Box.createHorizontalStrut(15));
		box3.add(txtRefund = new JTextField());
		txtRefund.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRefund.setEditable(false);

		lblReceive.setPreferredSize(lblCouponID.getPreferredSize());
		lblRefund.setPreferredSize(lblCouponID.getPreferredSize());

		GridLayout gl_pnSouthRight = new GridLayout(2, 1);
		pnSouthRight.setLayout(gl_pnSouthRight);

		pnSouthThongTin = new JPanel();
		pnSouthRight.add(pnSouthThongTin);
		pnSouthThongTin.setLayout(new GridLayout(0, 2, 20, 0));
		pnSouthThongTin.add(lblTaxTitle = new JLabel("Thuế:"));
		lblTaxTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTaxTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pnSouthThongTin.add(lblTax = new JLabel("123456"));
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 14));

		pnSouthThongTin.add(lblTotalTitle = new JLabel("Tổng tiến thanh toán:"));
		lblTotalTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pnSouthThongTin.add(lblTotal = new JLabel("10000.00VND"));
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));

		pnSouthButton = new JPanel();
		pnSouthRight.add(pnSouthButton);
		pnSouthButton.setLayout(new BoxLayout(pnSouthButton, BoxLayout.X_AXIS));
		pnSouthButton.add(Box.createHorizontalStrut(100));
		pnSouthButton.add(btnPrint = new JButton("In hóa đơn"));
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnSouthButton.add(Box.createHorizontalStrut(10));
		pnSouthButton.add(btnPayment = new JButton("Thanh toán"));
		btnPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnPrint.setBackground(new Color(107, 208, 107));
		btnPayment.setBackground(new Color(107, 208, 107));
		
		btnPrint.setPreferredSize(new Dimension(100,20));

	}

	private void createTable() {
		model = new DefaultTableModel();
		table = new JTable(model);

		model.addColumn("STT");
		model.addColumn("Tên");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá");
		model.addColumn("Đơn vị");
		model.addColumn("Thành tiền");

		box.add(scroll = new JScrollPane(table));
	}

	public static void main(String[] args) {
		new GD_ThanhToan().setVisible(true);
	}
}
