package view;

import entity.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	private JPanel pnSouthThongTin;
	private JPanel pnSouthButton;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private HoaDon hoaDon;
	private JScrollPane scrollDetail;
	private DefaultTableModel modelDetail;
	private JTable tableDetail;

	public GD_ThanhToan(HoaDon currentHoaDon) {
		hoaDon = currentHoaDon;
		nhanVien = hoaDon.getNhanVien();
		khachHang = hoaDon.getKhachHang();
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

		lblPhoneNum = new JLabel(khachHang.getSdt());
		lblPhoneNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblPhoneNum);
		lblPhoneNum.setBorder(new EmptyBorder(topPadding, 0, botPadding, rightPadding));

		lblCusNameTitle = new JLabel("Tên khách hàng:");
		lblCusNameTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblCusNameTitle);
		lblCusNameTitle.setBorder(new EmptyBorder(topPadding, leftPadding, botPadding, rightPadding));

		lblCusName = new JLabel(khachHang.getTenKhachHang());
		lblCusName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblCusName);

		lblEmpNameTitle = new JLabel("Tên nhân viên:");
		lblEmpNameTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmpNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoLeft.add(lblEmpNameTitle);
		lblEmpNameTitle.setBorder(new EmptyBorder(topPadding, leftPadding, 10, rightPadding));

		lblEmpName = new JLabel(nhanVien.getTen());
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

		lblCheckIn = new JLabel(formatDate(hoaDon.getPhieuDatPhongList().get(0).getThoiGianBatDau()));
		lblCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckIn);
		lblCheckIn.setBorder(new EmptyBorder(topPadding, 0, botPadding, rightPadding));

		lblCheckOutTitle = new JLabel("Ngày trả phòng:");
		lblCheckOutTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckOutTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckOutTitle);

		lblCheckOut = new JLabel(formatDate(new Time(System.currentTimeMillis())));
		lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblCheckOut);

		lblTotalTimeTitle = new JLabel("Tống thời gian:");
		lblTotalTimeTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalTimeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblTotalTimeTitle);

		lblTotalTime = new JLabel("");
		Duration totalTime = calculateTotalTime(hoaDon.getPhieuDatPhongList().get(0).getThoiGianBatDau(),
				new Time(System.currentTimeMillis()));
		lblTotalTime.setText(formatDuration(totalTime));
		lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnInfoRight.add(lblTotalTime);

		pnCenter.add(pnTable = new JPanel());
		pnTable.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		createTable();
		pnTable.setPreferredSize(new Dimension(750, 100));

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

		btnPrint.setPreferredSize(new Dimension(100, 20));

	}

	private Duration calculateTotalTime(Time checkInTime, Time checkOutTime) {
		LocalTime checkInLocalTime = checkInTime.toLocalTime();
		LocalTime checkOutLocalTime = checkOutTime.toLocalTime();

		return Duration.between(checkInLocalTime, checkOutLocalTime);
	}

	private String formatDuration(Duration duration) {
		long hours = duration.toHours();
		long minutes = duration.minusHours(hours).toMinutes();

		return String.format("%02d:%02d", hours, minutes);
	}

	private String formatDate(Time time) {
		Date currentDate = new Date(System.currentTimeMillis());
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

		String formattedDate = sdfDate.format(currentDate);
		String formattedTime = sdfTime.format(time);

		return formattedDate + " - " + formattedTime;
	}

	private void createTable() {
		pnTable.setLayout(null);
		String row[] = { "Mã phiếu đặt phòng", "Tên Phòng", "Loại Phòng", "Sức chứa", "Từ lúc", "Đến lúc" };

		model = new DefaultTableModel(row, 0);
		table = new JTable(model);
		scroll = new JScrollPane(table);

		scroll.setBounds(6, 23, 774, 103);
		pnTable.add(scroll);

		String modelDetailRow[] = { "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Loại dịch vụ", "Giá" };

		modelDetail = new DefaultTableModel(modelDetailRow, 0);
		tableDetail = new JTable(modelDetail);
		scrollDetail = new JScrollPane(tableDetail);

		scrollDetail.setBounds(6, 136, 774, 172);
		pnTable.add(scrollDetail);

		loadInvoiceDetailData();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadInvoiceServiceDetail();
			}
		});
	}

	private void loadInvoiceDetailData() {
		model.setRowCount(0);
		List<PhieuDatPhong> invoiceDetails = hoaDon.getPhieuDatPhongList();
		for (PhieuDatPhong invoiceDetail : invoiceDetails) {
			Object[] rowData = { invoiceDetail.getMaPhieuDatPhong(), invoiceDetail.getPhong().getTenPhong(),
					invoiceDetail.getPhong().getLoaiPhong(), invoiceDetail.getPhong().getSucChua(),
					invoiceDetail.getThoiGianBatDau(), invoiceDetail.getThoiGianKetThuc() };

			model.addRow(rowData);
		}
	}

	private void loadInvoiceServiceDetail() {
		modelDetail.setRowCount(0);
		List<ChiTietDatDichVu> chiTietDatDichVuList = hoaDon.getPhieuDatPhongList().get(table.getSelectedRow())
				.getChiTietDatDichVuList();
		for (ChiTietDatDichVu chiTietDatDichVu : chiTietDatDichVuList) {
			Object[] rowData = { chiTietDatDichVu.getDichVu().getMaDichVu(),
					chiTietDatDichVu.getDichVu().getTenDichVu(), chiTietDatDichVu.getSoLuong(),
					chiTietDatDichVu.getDichVu().getLoaiDichVu().getTenLoaiDichVu(),
					chiTietDatDichVu.getDichVu().getLichSuGiaDichVuList().get(0).getGia() };
			modelDetail.addRow(rowData);
		}
	}
}
