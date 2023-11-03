package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

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
import javax.swing.table.DefaultTableModel;

public class ThanhToan extends JFrame {
	private JPanel pnNorth, pnCenter, pnSouth, pnInfo, pnTable, pnSouthLeft, pnSouthRight;
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

	public ThanhToan() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Thanh toán");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);

		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("THANH TOÁN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

		getContentPane().add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

		pnCenter.add(pnInfo = new JPanel());
		pnInfo.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		pnInfo.setLayout(new GridLayout(3, 2));

		pnInfo.add(lblPhoneNumTitle = new JLabel("SĐT khách:"));
		pnInfo.add(lblPhoneNum = new JLabel("0986148209"));
		lblPhoneNum.setHorizontalAlignment(SwingConstants.LEFT);
		pnInfo.add(lblCheckInTitle = new JLabel("Ngày đặt:"));
		pnInfo.add(lblCheckIn = new JLabel("03/11/2023"));

		pnInfo.add(lblCusNameTitle = new JLabel("Tên khách hàng:"));
		pnInfo.add(lblCusName = new JLabel("Nguyễn Văn A"));
		pnInfo.add(lblCheckOutTitle = new JLabel("Ngày trả phòng:"));
		pnInfo.add(lblCheckOut = new JLabel("03/11/2023"));

		pnInfo.add(lblEmpNameTitle = new JLabel("Tên nhân viên:"));
		pnInfo.add(lblEmpName = new JLabel("Trần Trung Tiến"));
		pnInfo.add(lblTotalTimeTitle = new JLabel("Tống thời gian:"));
		pnInfo.add(lblTotalTime = new JLabel("2:00"));

		lblCusName.setPreferredSize(lblPhoneNum.getPreferredSize());

		pnCenter.add(pnTable = new JPanel());
		pnTable.setLayout(new BoxLayout(pnTable, BoxLayout.X_AXIS));
		pnTable.add(box = Box.createHorizontalBox());
		pnTable.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		createTable();

		getContentPane().add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		pnSouth.setLayout(new GridLayout());
		pnSouth.add(pnSouthLeft = new JPanel());
		pnSouth.add(pnSouthRight = new JPanel());

		pnSouthLeft.setLayout(new BoxLayout(pnSouthLeft, BoxLayout.Y_AXIS));
		pnSouthLeft.add(box = Box.createVerticalBox());
		box.add(box1 = Box.createHorizontalBox());
		box.add(box2 = Box.createHorizontalBox());
		box.add(box3 = Box.createHorizontalBox());

		box1.add(lblCouponID = new JLabel("Nhập mã khuyến mãi:"));
		box1.add(txtCouponID = new JTextField());
		box1.add(btnAdd = new JButton("Thêm"));

		box2.add(lblReceive = new JLabel("Tiền nhận:"));
		box2.add(txtReceive = new JTextField());

		box3.add(lblRefund = new JLabel("Tiền trả lại:"));
		box3.add(txtRefund = new JTextField());
		txtRefund.setEditable(false);

		lblReceive.setPreferredSize(lblCouponID.getPreferredSize());
		lblRefund.setPreferredSize(lblCouponID.getPreferredSize());

		pnSouthRight.setLayout(new GridLayout(3, 1));

		pnSouthRight.add(lblTaxTitle = new JLabel("Thuế:"));
		lblTaxTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnSouthRight.add(lblTax = new JLabel("123456"));

		pnSouthRight.add(lblTotalTitle = new JLabel("Tổng tiến thanh toán:"));
		lblTotalTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnSouthRight.add(lblTotal = new JLabel("10000.00VND"));

		pnSouthRight.add(btnPrint = new JButton("In hóa đơn"));
		pnSouthRight.add(btnPayment = new JButton("Thanh toán"));

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
		new ThanhToan().setVisible(true);
	}
}
