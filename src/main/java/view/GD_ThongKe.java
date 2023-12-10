package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import entity.NhanVien;

public class GD_ThongKe extends JPanel implements ActionListener {
    private JPanel pnNorth, pnCenter, pnSouth, pnInfo, pnChart;
    private JLabel lblTitle, lblNgayBD, lblNgayKT, lblLoaiTK;
    private JDateChooser ngayBatDau, ngayKetThuc;
    private JComboBox<String> cmbLoaiTK;
    private JButton btnThongKe, btnXoaTrang;
    private Box box, box1, box2;
    private JScrollPane scroll;

    private ArrayList<String> listMonthName;
    private ArrayList<Double> listScore;
    private ArrayList<Integer> listMonth;
    private ArrayList<Date> listDate;
    private ArrayList<Date> dateRange;
    private ArrayList<String> listNhanVien;

    private ConnectDB connectDB;

    public GD_ThongKe() {
        connectDB = ConnectDB.getInstance();
        createGUI();
    }

    private void createGUI() {
//		setTitle("Thống kê");
        setSize(1000, 700);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(this);
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
        box1.add(Box.createHorizontalStrut(10));
        box1.add(ngayBatDau = new JDateChooser());
        box1.add(Box.createHorizontalStrut(50));
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

        buildChartByThang();

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
    }

    private void getDataTheoThang() {
        listMonth = new ArrayList<>();
        listScore = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT MONTH(NgayThanhToan) AS Thang, SUM(tongTien) AS TongTien \r\n"
                + "FROM HoaDon GROUP BY MONTH(HoaDon.NgayThanhToan)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listMonth.add(resultSet.getInt("Thang"));
                listScore.add(resultSet.getDouble("TongTien"));
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
        ArrayList<String> listTime = new ArrayList<>();
        listTime = getTimeFromDate();
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

            try {
                CategoryPlot p = chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.black);
                ChartPanel chartPanel = new ChartPanel(chart);

                pnChart.add(chartPanel);
                pnChart.validate();
            } catch (Exception exception) {

            }

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
//		System.out.println(listMonthName);
    }

    private void getDataByThoiGian() {
        listDate = new ArrayList<>();
        listScore = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT NgayThanhToan, SUM(tongTien) AS TongTien FROM HoaDon GROUP BY NgayThanhToan";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listDate.add(resultSet.getDate("NgayThanhToan"));
                listScore.add(resultSet.getDouble("TongTien"));
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
//		System.out.println(newListDate);
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
        String query = "SELECT HoaDon.maNhanVien, NhanVien.ten, SUM(tongTien) AS TongTien FROM HoaDon JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien GROUP BY HoaDon.maNhanVien, NhanVien.ten";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listNhanVien.add(resultSet.getString("ten"));
                listScore.add(resultSet.getDouble("TongTien"));
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
            buildChartByThang();
        } else if (cmbLoaiTK.getSelectedIndex() == 1) {
            getTimeFromDate();
            buildChartByThoiGian();
        } else if (cmbLoaiTK.getSelectedIndex() == 2) {
            buildChartByNhanVien();
        }
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