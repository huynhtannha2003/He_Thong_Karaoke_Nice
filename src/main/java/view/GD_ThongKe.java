package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;

public class GD_ThongKe extends JPanel implements ActionListener {
	private JPanel pnNorth, pnCenter, pnEast, pnInfo, pnChart;
	private JLabel lblTitle, lblNgayBD, lblNgayKT, lblLoaiTK;
	private JDateChooser ngayBatDau, ngayKetThuc;
	private JComboBox<String> cmbLoaiTK;
	private JButton btnThongKe, btnXoaTrang;
	private Box box, box1, box2;
	private JLabel lblTongTitle, lblTongHoaDonTitle, lblDoanhThuPhongThuongTitle, lblDoanhThuPhongVipTitle,
			lblTongThoiGianTitle, lblTongTienDichVuTitle, lblTrungBinhTitle;
	private JLabel lblTong, lblTongHoaDon, lblDoanhThuPhongThuong, lblDoanhThuPhongVip, lblTongThoiGian,
			lblTongTienDichVu, lblTrungBinh;

	private ArrayList<String> listMonthName;
	private ArrayList<Double> listScore;
	private ArrayList<Integer> listMonth;
	private ArrayList<Date> listDate;
	private ArrayList<Date> dateRange;
	private ArrayList<String> listNhanVien;
	private double tongDanhThu;
	private int tongHoaDon;
	private double doanhThuPhongThuong;
	private double doanhThuPhongVip;
	private double tongTienDichVu;
	private double doanhThuTrungBinh;

	private ConnectDB connectDB;

	public GD_ThongKe() {
		connectDB = ConnectDB.getInstance();
		createGUI();
	}

	private void createGUI() {
		setSize(1000, 700);
		setLayout(new BorderLayout());

		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("Thống kê"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));

		add(pnCenter = new JPanel(), BorderLayout.CENTER);

		pnCenter.add(pnInfo = new JPanel());
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));

		pnInfo.add(box = Box.createVerticalBox());
		pnInfo.setBorder(BorderFactory.createLineBorder(Color.black));

		box.add(Box.createVerticalStrut(10));
		box.add(box1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		box.add(box2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));

		pnInfo.setMaximumSize(new Dimension(1000, 200));

		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblNgayBD = new JLabel("Ngày bắt đầu:"));
		lblNgayBD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(13));
		box1.add(ngayBatDau = new JDateChooser());
		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblLoaiTK = new JLabel("Thống kê theo:"));
		lblLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(cmbLoaiTK = new JComboBox());
		cmbLoaiTK.addItem("Theo tháng");
		cmbLoaiTK.addItem("Theo thời gian");
		cmbLoaiTK.addItem("Theo nhân viên");
		box1.add(Box.createHorizontalStrut(20));

		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblNgayKT = new JLabel("Ngày kết thúc:"));
		lblNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(10));
		box2.add(ngayKetThuc = new JDateChooser());
		box2.add(Box.createHorizontalStrut(120));
		box2.add(btnThongKe = new JButton("Thống kê"));
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(20));
		box2.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(20));

		lblNgayBD.setPreferredSize(lblNgayKT.getPreferredSize());

		ngayBatDau.setPreferredSize(new Dimension(300, 25));
		cmbLoaiTK.setPreferredSize(new Dimension(350, 25));

		pnCenter.add(pnChart = new JPanel());
		pnChart.setBorder(BorderFactory.createLineBorder(Color.black));
		pnChart.setLayout(new BorderLayout(0, 0));

		btnThongKe.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setBackground(new Color(107, 208, 107));

		btnThongKe.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		cmbLoaiTK.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (cmbLoaiTK.getSelectedIndex() == 0) {
						ngayBatDau.setEnabled(false);
						ngayKetThuc.setEnabled(false);
					} else if (cmbLoaiTK.getSelectedIndex() == 1) {
						ngayBatDau.setEnabled(true);
						ngayKetThuc.setEnabled(true);
					} else if (cmbLoaiTK.getSelectedIndex() == 2) {
						ngayBatDau.setEnabled(false);
						ngayKetThuc.setEnabled(false);
					}
				}
			}
		});

		ngayBatDau.setEnabled(false);
		ngayKetThuc.setEnabled(false);

		// Chi tiet thong ke
		add(pnEast = new JPanel(), BorderLayout.EAST);
		pnEast.add(pnCenter = new JPanel(), BorderLayout.CENTER);
		GridLayout gl_pnCenter = new GridLayout();
		gl_pnCenter.setVgap(70);
		gl_pnCenter.setHgap(10);
		gl_pnCenter.setColumns(2);
		gl_pnCenter.setRows(7);
		pnCenter.setLayout(gl_pnCenter);
		pnEast.setBorder(BorderFactory.createLineBorder(Color.black));

		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 VND");

		lblTongTitle = new JLabel("Tổng doanh thu:");
		lblTongTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnCenter.add(lblTongTitle);

		BigDecimal bigDecimal = new BigDecimal(tongDanhThu);
		tongDanhThu = bigDecimal.doubleValue();

		lblTong = new JLabel(decimalFormat.format(tongDanhThu));
		lblTong.setHorizontalAlignment(SwingConstants.LEFT);
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCenter.add(lblTong);

		lblTongHoaDonTitle = new JLabel("Tổng số hóa đơn:");
		lblTongHoaDonTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnCenter.add(lblTongHoaDonTitle);

		lblTongHoaDon = new JLabel(tongHoaDon + "");
		lblTongHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCenter.add(lblTongHoaDon);

		lblDoanhThuPhongThuongTitle = new JLabel("Doanh thu phòng thường:");
		lblDoanhThuPhongThuongTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnCenter.add(lblDoanhThuPhongThuongTitle);

		lblDoanhThuPhongThuong = new JLabel(decimalFormat.format(doanhThuPhongThuong));
		lblDoanhThuPhongThuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoanhThuPhongThuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCenter.add(lblDoanhThuPhongThuong);

		lblDoanhThuPhongVipTitle = new JLabel("Doanh thu phòng Vip:");
		lblDoanhThuPhongVipTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnCenter.add(lblDoanhThuPhongVipTitle);

		lblDoanhThuPhongVip = new JLabel(decimalFormat.format(doanhThuPhongVip));
		lblDoanhThuPhongVip.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoanhThuPhongVip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCenter.add(lblDoanhThuPhongVip);

		lblTongTienDichVuTitle = new JLabel("Tổng tiền dịch vụ:");
		lblTongTienDichVuTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnCenter.add(lblTongTienDichVuTitle);

		lblTongTienDichVu = new JLabel(decimalFormat.format(tongTienDichVu));
		lblTongTienDichVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTienDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCenter.add(lblTongTienDichVu);

		lblTrungBinhTitle = new JLabel("Doanh thu trung bình:");
		lblTrungBinhTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnCenter.add(lblTrungBinhTitle);

		lblTrungBinh = new JLabel(decimalFormat.format(doanhThuTrungBinh));
		lblTrungBinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrungBinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnCenter.add(lblTrungBinh);

		pnCenter.setBorder(new EmptyBorder(30, 10, 0, 0));

		buildChartByThang();

	}

	private void getDataTheoThang() {
		listMonth = new ArrayList<>();
		listScore = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT MONTH(NgayThanhToan) AS Thang, SUM(tongTien) AS TongTien, COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon, MAX(Phong.maLoaiPhong) AS MaLoaiPhong, MAX(LoaiPhong.tenLoaiPhong) AS LoaiPhong, \r\n"
				+ "		MAX(ChiTietDatDichVuView.LichSuGiaDichVu_Gia) AS GiaDichVu, MAX(ChiTietDatDichVuView.ChiTietDatDichVu_SoLuong) AS SoLuongDichVu, (MAX(ChiTietDatDichVuView.LichSuGiaDichVu_Gia) * MAX(ChiTietDatDichVuView.ChiTietDatDichVu_SoLuong)) AS TongTienDichVu\r\n"
				+ "FROM HoaDon\r\n" + "	JOIN PhieuDatPhong ON HoaDon.maHoaDon = PhieuDatPhong.maHoaDon\r\n"
				+ "	JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong\r\n"
				+ "	JOIN LoaiPhong ON Phong.maLoaiPhong = LoaiPhong.maLoaiPhong\r\n"
				+ "	JOIN ChiTietDatDichVuView ON ChiTietDatDichVuView.ChiTietDatDichVu_MaPhieuDatPhong = PhieuDatPhong.maPhieuDatPhong\r\n"
				+ "GROUP BY MONTH(HoaDon.NgayThanhToan)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listMonth.add(resultSet.getInt("Thang"));
				listScore.add(resultSet.getDouble("TongTien"));

				tongDanhThu += resultSet.getDouble("TongTien");
				tongHoaDon += resultSet.getInt("SoLuongHoaDon");
				if (resultSet.getString("LoaiPhong").equals("Vip")) {
					doanhThuPhongVip += resultSet.getDouble("TongTien");
				}
				if (resultSet.getString("LoaiPhong").equals("Thường")) {
					doanhThuPhongThuong += resultSet.getDouble("TongTien");
				}
				tongTienDichVu += resultSet.getDouble("TongTienDichVu");
				doanhThuTrungBinh += tongDanhThu / tongHoaDon;
				updateChiTiet();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getMonthNameFromDAO();
	}

	private void buildChartByThang() {
		getDataTheoThang();
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			ArrayList<DefaultCategoryDataset> listDataset = new ArrayList<>();

			for (int i = 0; i < listMonthName.size(); i++) {
				dataset.setValue(listScore.get(i), "Vertical", listMonthName.get(i));
				listDataset.add(dataset);
			}

			JFreeChart chart = null;
			for (DefaultCategoryDataset curDateset : listDataset) {
				chart = ChartFactory.createBarChart("", "Tháng", "Doanh thu (VND)", dataset, PlotOrientation.VERTICAL,
						false, true, false);
			}

			CategoryPlot p = chart.getCategoryPlot();
			p.setRangeGridlinePaint(Color.black);
			ChartPanel chartPanel = new ChartPanel(chart);
			pnChart.add(chartPanel);
			pnChart.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildChartByThoiGian() throws Exception {
		ArrayList<String> listTime = getTimeFromDate();
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			ArrayList<DefaultCategoryDataset> listDataset = new ArrayList<>();

			for (int i = 0; i < listTime.size(); i++) {
				dataset.setValue(listScore.get(i), "Vertical", listTime.get(i));
				listDataset.add(dataset);
			}

			JFreeChart chart = null;
			for (DefaultCategoryDataset curDateset : listDataset) {
				chart = ChartFactory.createBarChart("", "Thời gian", "Doanh thu (VND)", dataset,
						PlotOrientation.VERTICAL, false, true, false);
			}

			CategoryPlot p = chart.getCategoryPlot();
			p.setRangeGridlinePaint(Color.black);
			ChartPanel chartPanel = new ChartPanel(chart);

			pnChart.add(chartPanel);
			pnChart.validate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildChartByNhanVien() {
		getDataByNhanVien();
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			ArrayList<DefaultCategoryDataset> listDataset = new ArrayList<>();

			for (int i = 0; i < listScore.size(); i++) {
				dataset.setValue(listScore.get(i), "Vertical", listNhanVien.get(i));
				listDataset.add(dataset);
			}

			JFreeChart chart = null;
			for (DefaultCategoryDataset curDateset : listDataset) {
				chart = ChartFactory.createBarChart("", "Nhân viên", "Doanh thu (VND)", dataset,
						PlotOrientation.VERTICAL, false, true, false);
			}

			CategoryPlot p = chart.getCategoryPlot();
			p.setRangeGridlinePaint(Color.black);
			ChartPanel chartPanel = new ChartPanel(chart);
			pnChart.add(chartPanel);
			pnChart.validate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getMonthNameFromDAO() {
		listMonthName = new ArrayList<>();
		for (Integer curMonth : listMonth) {
			Month month = Month.of(curMonth);
			String monthName = month.getDisplayName(TextStyle.SHORT, Locale.getDefault());
			listMonthName.add(monthName);
		}
	}

	private void getDataByThoiGian() {
		listDate = new ArrayList<>();
		listScore = new ArrayList<>();
		Connection connection = connectDB.getConnection();
//		String query = "SELECT NgayThanhToan, SUM(tongTien) AS TongTien FROM HoaDon GROUP BY NgayThanhToan";
		String query = "SELECT NgayThanhToan, SUM(tongTien) AS TongTien, COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon, MAX(Phong.maLoaiPhong) AS MaLoaiPhong, MAX(LoaiPhong.tenLoaiPhong) AS LoaiPhong, \r\n"
				+ "		MAX(ChiTietDatDichVuView.LichSuGiaDichVu_Gia) AS GiaDichVu, MAX(ChiTietDatDichVuView.ChiTietDatDichVu_SoLuong) AS SoLuongDichVu, (MAX(ChiTietDatDichVuView.LichSuGiaDichVu_Gia) * MAX(ChiTietDatDichVuView.ChiTietDatDichVu_SoLuong)) AS TongTienDichVu\r\n"
				+ "FROM HoaDon \r\n" + "	JOIN PhieuDatPhong ON HoaDon.maHoaDon = PhieuDatPhong.maHoaDon\r\n"
				+ "	JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong\r\n"
				+ "	JOIN LoaiPhong ON Phong.maLoaiPhong = LoaiPhong.maLoaiPhong\r\n"
				+ "	JOIN ChiTietDatDichVuView ON ChiTietDatDichVuView.ChiTietDatDichVu_MaPhieuDatPhong = PhieuDatPhong.maPhieuDatPhong\r\n"
				+ "GROUP BY NgayThanhToan";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listDate.add(resultSet.getDate("NgayThanhToan"));
				listScore.add(resultSet.getDouble("TongTien"));

				tongDanhThu += resultSet.getDouble("TongTien");
				tongHoaDon += resultSet.getInt("SoLuongHoaDon");
				if (resultSet.getString("LoaiPhong").equals("Vip")) {
					doanhThuPhongVip += resultSet.getDouble("TongTien");
				}
				if (resultSet.getString("LoaiPhong").equals("Thường")) {
					doanhThuPhongThuong += resultSet.getDouble("TongTien");
				}
				tongTienDichVu += resultSet.getDouble("TongTienDichVu");
				doanhThuTrungBinh += tongDanhThu / tongHoaDon;
				updateChiTiet();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> getTimeFromDate() {
		getDataByThoiGian();
		Date start = ngayBatDau.getDate();
		Date end = ngayKetThuc.getDate();

		try {
			getDateRange(start, end);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn thời gian");
		}
		ArrayList<String> newListDate = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

		for (Date date2 : listDate) {
			for (Date date : dateRange) {
				if (date.getTime() == date2.getTime()) {
					newListDate.add(dateFormat.format(date));
				}
			}
		}
		return newListDate;
	}

	private void getDateRange(Date dateStart, Date dateEnd) {
		dateRange = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateStart);

		while (calendar.getTime().before(dateEnd)) {
			dateRange.add(calendar.getTime());
			calendar.add(calendar.DATE, 1);
		}
	}

	private void getDataByNhanVien() {
		listNhanVien = new ArrayList<>();
		listScore = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT NhanVien.ten, SUM(tongTien) AS TongTien, COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon, MAX(Phong.maLoaiPhong) AS MaLoaiPhong, MAX(LoaiPhong.tenLoaiPhong) AS LoaiPhong, \r\n"
				+ "		MAX(ChiTietDatDichVuView.LichSuGiaDichVu_Gia) AS GiaDichVu, MAX(ChiTietDatDichVuView.ChiTietDatDichVu_SoLuong) AS SoLuongDichVu, (MAX(ChiTietDatDichVuView.LichSuGiaDichVu_Gia) * MAX(ChiTietDatDichVuView.ChiTietDatDichVu_SoLuong)) AS TongTienDichVu\r\n"
				+ "FROM HoaDon JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
				+ "	JOIN PhieuDatPhong ON HoaDon.maHoaDon = PhieuDatPhong.maHoaDon\r\n"
				+ "	JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong\r\n"
				+ "	JOIN LoaiPhong ON Phong.maLoaiPhong = LoaiPhong.maLoaiPhong\r\n"
				+ "	JOIN ChiTietDatDichVuView ON ChiTietDatDichVuView.ChiTietDatDichVu_MaPhieuDatPhong = PhieuDatPhong.maPhieuDatPhong\r\n"
				+ "GROUP BY NhanVien.ten";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listNhanVien.add(resultSet.getString("ten"));
				listScore.add(resultSet.getDouble("TongTien"));

				tongDanhThu += resultSet.getDouble("TongTien");
				tongHoaDon += resultSet.getInt("SoLuongHoaDon");
				if (resultSet.getString("LoaiPhong").equals("Vip")) {
					doanhThuPhongVip += resultSet.getDouble("TongTien");
				}
				if (resultSet.getString("LoaiPhong").equals("Thường")) {
					doanhThuPhongThuong += resultSet.getDouble("TongTien");
				}
				tongTienDichVu += resultSet.getDouble("TongTienDichVu");
				doanhThuTrungBinh += tongDanhThu / tongHoaDon;
				updateChiTiet();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void clearInput() {
		ngayBatDau.setCalendar(null);
		ngayKetThuc.setCalendar(null);
		cmbLoaiTK.setSelectedIndex(0);
	}

	private void xuLyThongKe() throws Exception {
		if (cmbLoaiTK.getSelectedIndex() == 0) {
			setDefaultData();
			buildChartByThang();
		} else if (cmbLoaiTK.getSelectedIndex() == 1) {
			setDefaultData();
			getTimeFromDate();
			buildChartByThoiGian();
		} else if (cmbLoaiTK.getSelectedIndex() == 2) {
			setDefaultData();
			buildChartByNhanVien();
		}
	}

	private void setDefaultData() {
		tongDanhThu = 0;
		tongHoaDon = 0;
		doanhThuPhongVip = 0;
		doanhThuPhongThuong = 0;
		tongTienDichVu = 0;
		doanhThuTrungBinh = 0;
	}

	private void updateChiTiet() {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 VND");
		lblTong.setText(decimalFormat.format(tongDanhThu));
		lblTongHoaDon.setText(this.tongHoaDon + "");
		lblDoanhThuPhongVip.setText(decimalFormat.format(doanhThuPhongVip));
		lblDoanhThuPhongThuong.setText(decimalFormat.format(doanhThuPhongThuong));
		lblTongTienDichVu.setText(decimalFormat.format(tongTienDichVu));
		lblTrungBinh.setText(decimalFormat.format(doanhThuTrungBinh));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			try {
				xuLyThongKe();
			} catch (Exception exception) {

			}
		} else if (o.equals(btnXoaTrang)) {
			clearInput();
			buildChartByThang();
		}
	}

//    public static void main(String[] args) {
//        new GD_ThongKe().setVisible(true);
//    }

}