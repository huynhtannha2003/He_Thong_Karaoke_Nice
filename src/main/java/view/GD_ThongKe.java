package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

public class GD_ThongKe extends JFrame implements ActionListener {
	private JPanel pnNorth, pnCenter, pnSouth, pnInfo, pnChart;
	private JLabel lblTitle, lblNgayBD, lblNgayKT, lblLoaiTK;
	private JDateChooser ngayBatDau, ngayKetThuc;
	private JComboBox<String> cmbLoaiTK;
	private JButton btnThongKe, btnXoaTrang;
	private Box box, box1, box2;
	private ArrayList<String> listMonth;
	private ArrayList<Integer> listScore;

	public GD_ThongKe() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Thống kê");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);

		getContentPane().add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("Thống kê"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));

		getContentPane().add(pnCenter = new JPanel(), BorderLayout.CENTER);

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
		box1.add(Box.createHorizontalStrut(10));
		box1.add(ngayBatDau = new JDateChooser());
		box1.add(Box.createHorizontalStrut(50));
		box1.add(lblLoaiTK = new JLabel("Thống kê theo:"));
		lblLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box1.add(Box.createHorizontalStrut(10));
		box1.add(cmbLoaiTK = new JComboBox());
		cmbLoaiTK.addItem("Tất cả");
		cmbLoaiTK.addItem("Theo ngày");
		cmbLoaiTK.addItem("Theo nhân viên");
		box1.add(Box.createHorizontalStrut(20));

		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblNgayKT = new JLabel("Ngày kết thúc:"));
		lblNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(10));
		box2.add(ngayKetThuc = new JDateChooser());
		box2.add(Box.createHorizontalStrut(270));
		box2.add(btnThongKe = new JButton("Thống kê"));
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(20));
		box2.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		box2.add(Box.createHorizontalStrut(50));

		lblNgayBD.setPreferredSize(lblNgayKT.getPreferredSize());

		ngayBatDau.setPreferredSize(new Dimension(300, 25));
		cmbLoaiTK.setPreferredSize(new Dimension(350, 25));

		pnCenter.add(pnChart = new JPanel());
		pnChart.setBorder(BorderFactory.createLineBorder(Color.black));
		pnChart.setLayout(new BorderLayout(0, 0));

		listScore = new ArrayList<>();
//		for (int i=0; i< 5; i++) {
//			listScore.add(10);
//		}
//		buildChart();

		btnThongKe.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setBackground(new Color(107, 208, 107));

		btnThongKe.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	private void buildChart() {
		try {
			getMonthFromDate();
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			ArrayList<DefaultCategoryDataset> listDataset = new ArrayList<>();

			for (int i = 0; i < listMonth.size(); i++) {
				dataset.setValue((i + 10) * 100 + (i / 5), "Vertical", listMonth.get(i));
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
			// TODO: handle exception
		}
	}

	private void getMonthFromDate() throws Exception {
		listMonth = new ArrayList<String>();
		if (ngayBatDau.getDate() != null) {
			if (ngayKetThuc.getDate() != null) {
				Date startDate = ngayBatDau.getDate();
				Date endDate = ngayKetThuc.getDate();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDate);
				int startMonth = calendar.get(Calendar.MONTH);
				calendar.setTime(endDate);
				int endMonth = calendar.get(Calendar.MONTH);

				SimpleDateFormat dateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);

				for (int i = startMonth; i <= endMonth; i++) {
					calendar.set(Calendar.MONTH, i);
					String month = dateFormat.format(calendar.getTime());
					listMonth.add(month);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc");
				ngayKetThuc.requestFocus();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu");
			ngayBatDau.requestFocus();
		}

	}

	private void clearInput() {
		ngayBatDau.setCalendar(null);
		ngayKetThuc.setCalendar(null);
		cmbLoaiTK.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThongKe)) {
			buildChart();
		} else if (o.equals(btnXoaTrang)) {
			clearInput();
		}
	}

	public static void main(String[] args) {
		new GD_ThongKe().setVisible(true);
	}

}
