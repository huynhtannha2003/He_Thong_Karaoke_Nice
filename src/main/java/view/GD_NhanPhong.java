package view;

import dao.KhachHangDAO;
import dao.LoaiPhongDAO;
import dao.PhieuDatPhongDAO;
import dao.PhongDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.PhieuDatPhong;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GD_NhanPhong extends JPanel implements ActionListener {
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
    private List<HoaDon> list;

    public GD_NhanPhong() {
        khachHangDAO = new KhachHangDAO();
        phongDAO = new PhongDAO();
        loaiPhongDAO = new LoaiPhongDAO();
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        initGUI();
    }

    private void setupFrame() {
        setSize(1000, 700);
        setBackground(new Color(255, 255, 255));
    }

    private void initGUI() {
        setupFrame();

        addPanelNorth();

        addPanelCenter();

        addPanelSouth();
    }

    private void addPanelNorth() {
        setLayout(new BorderLayout(0, 0));
        pnNorth = new JPanel();
        pnNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnNorth.setBackground(new Color(97, 250, 204));
        add(pnNorth, BorderLayout.NORTH);
        JLabel lblTitle = new JLabel("Nhận phòng chờ");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnNorth.add(lblTitle);

    }

    private void addPanelCenter() {
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        add(pnCenter, BorderLayout.CENTER);
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

        verticalForm.add(Box.createVerticalStrut(20));

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

        verticalForm.add(Box.createVerticalStrut(20));

        Box horizontalFormSecond = Box.createHorizontalBox();
        verticalForm.add(horizontalFormSecond);

        horizontalFormSecond.add(Box.createHorizontalStrut(20));

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

        horizontalFormSecond.add(Box.createHorizontalStrut(40));

        String[] headers = {"STT", "Mã phòng", "Tên phòng", "Thời gian nhận phòng"};
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
        add(pnSouth, BorderLayout.SOUTH);

        Box horizontalControl = Box.createHorizontalBox();
        pnSouth.add(horizontalControl);

        btnNhanMotPhong = new JButton("Nhận phòng");
        btnNhanMotPhong.setBackground(new Color(107, 208, 107));
        btnNhanMotPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalControl.add(btnNhanMotPhong);

        horizontalControl.add(Box.createHorizontalStrut(20));

        btnNhanNhieuPhong = new JButton("Nhận nhiều phòng");
        btnNhanNhieuPhong.setBackground(new Color(107, 208, 107));
        btnNhanNhieuPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalControl.add(btnNhanNhieuPhong);

        horizontalControl.add(Box.createHorizontalStrut(20));

        btnOut = new JButton("Thoát");
        btnOut.setBackground(new Color(107, 208, 107));
        btnOut.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalControl.add(btnOut);

        btnNhanMotPhong.addActionListener(this);
        btnNhanNhieuPhong.addActionListener(this);
        btnOut.addActionListener(this);
    }

    private void loadData() {
        list = phieuDatPhongDAO.getHoaDonBySDTAndTime(txtNumber.getText());
        int i = 0;
        modelTable.setRowCount(0);
        for (HoaDon hoaDon : list) {
            PhieuDatPhong s = hoaDon.getPhieuDatPhongList().get(0);
            Object[] row = {(++i), s.getPhong().getMaPhong(), s.getPhong().getTenPhong(), s.getThoiGianBatDau()};
            modelTable.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnSearch)) {
            if (!txtNumber.getText().isEmpty()) {
                khachHang = khachHangDAO.getCustomerByPhoneNumber(txtNumber.getText());
                if (khachHang == null) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    txtCustomer.setText(khachHang.getTenKhachHang());
                    loadData();
                }
            }
        }
        if (o.equals(btnNhanMotPhong)) {
            int selectRow = table.getSelectedRow();
            boolean invoice;
            if (selectRow != -1) {
                Object maPhongObject = table.getValueAt(selectRow, 1);
                String maPhong = String.valueOf(maPhongObject);
                HoaDon[] updateHoaDonContainer = {null};

                list.forEach(currentHoaDon -> {
                    if (currentHoaDon.getPhieuDatPhongList().get(0).getPhong().getMaPhong().equals(maPhong)) {
                        updateHoaDonContainer[0] = currentHoaDon;
                    }
                });

                HoaDon updateHoaDon = updateHoaDonContainer[0];

                invoice = phongDAO.updateTrangThaiPhong(updateHoaDon.getMaHoaDon(), maPhong);
                if (invoice) {
                    JOptionPane.showMessageDialog(this, "Nhận phòng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Nhận phòng thất bại", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }

                loadData();
            }else {
                JOptionPane.showMessageDialog(this, "Hãy chọn một phòng để nhận", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (o.equals(btnNhanNhieuPhong)) {
            int reply = JOptionPane.showConfirmDialog(this, "Bạn có muốn nhận toàn bộ phòng", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                list.forEach(invoice -> {
                    phongDAO.updateTrangThaiPhong(invoice.getMaHoaDon(), invoice.getPhieuDatPhongList().get(0).getPhong().getMaPhong());
                });
                JOptionPane.showMessageDialog(this, "Nhận phòng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            }
        }
        if (o.equals(btnOut))
            System.exit(0);
    }
}