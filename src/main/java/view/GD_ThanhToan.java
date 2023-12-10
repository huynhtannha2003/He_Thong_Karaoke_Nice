package view;

import dao.KhuyenMaiDAO;
import dao.PhieuDatPhongDAO;
import entity.*;
import utils.FormatCurrencyUtil;
import utils.PdfExportUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class GD_ThanhToan extends JFrame implements ActionListener {
    private JPanel pnNorth, pnCenter, pnSouth, pnTable, pnSouthLeft, pnSouthRight, pnInfo, pnInfoLeft, pnInfoRight, pnSouthThongTin, pnSouthButton;
    private JLabel lblTitle, lblPhoneNumTitle, lblCheckInTitle, lblCusNameTitle, lblCheckOutTitle, lblEmpNameTitle, lblTotalTimeTitle, lblTotalTitle, lblPhoneNum, lblCusName, lblEmpName, lblCheckIn, lblCheckOut, lblTotalTime, lblCouponID, lblTotal, lblReceive, lblRefund, lblTotalAfterDiscountTitle, lblTotalAfterDiscount;
    private Box box, box1, box2, box3;
    private JTable table, tableDetail;
    private DefaultTableModel model, modelDetail;
    private JScrollPane scroll, scrollDetail;
    private JTextField txtCouponID, txtReceive, txtRefund;
    private JCheckBox checkBoxPrintInvoice;
    private JButton btnAdd, btnPayment;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private List<HoaDon> hoaDonList;
    private final KhuyenMaiDAO khuyenMaiDAO;
    private final PhieuDatPhongDAO phieuDatPhongDao;

    public GD_ThanhToan(List<HoaDon> hoaDon, NhanVien currentNhanVien) {
        hoaDonList = hoaDon;
        hoaDonList.forEach(currentHoaDon -> currentHoaDon.getPhieuDatPhongList().get(currentHoaDon.getPhieuDatPhongList().size() - 1).setThoiGianKetThuc(new Time(System.currentTimeMillis())));
        khuyenMaiDAO = new KhuyenMaiDAO();
        phieuDatPhongDao = new PhieuDatPhongDAO();
        nhanVien = currentNhanVien;
        khachHang = hoaDon.get(0).getKhachHang();
        createGUI();
    }

    private void createGUI() {
        setTitle("Thanh toán");
        setSize(800, 650);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        lblCheckIn = new JLabel(formatFromDate(hoaDonList.get(0).getPhieuDatPhongList().get(0).getThoiGianBatDau()));
        lblCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnInfoRight.add(lblCheckIn);
        lblCheckIn.setBorder(new EmptyBorder(topPadding, 0, botPadding, rightPadding));

        lblCheckOutTitle = new JLabel("Ngày trả phòng:");
        lblCheckOutTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblCheckOutTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnInfoRight.add(lblCheckOutTitle);

        lblCheckOut = new JLabel(formatToDate(new Time(System.currentTimeMillis())));
        lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnInfoRight.add(lblCheckOut);

        lblTotalTimeTitle = new JLabel("Tổng thời gian:");
        lblTotalTimeTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotalTimeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnInfoRight.add(lblTotalTimeTitle);

        lblTotalTime = new JLabel("");
        Duration totalTime = calculateTotalTime(
                hoaDonList.get(0).getPhieuDatPhongList().get(0).getThoiGianBatDau(),
                new Time(System.currentTimeMillis())
        );
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
        txtReceive.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateRefundField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateRefundField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

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
        pnSouthThongTin.add(lblTotalTitle = new JLabel("Tổng cộng:"));
        lblTotalTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTotalTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        pnSouthThongTin.add(lblTotal = new JLabel(""));
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));

        pnSouthThongTin.add(lblTotalAfterDiscountTitle = new JLabel("Tổng tiến thanh toán:"));
        lblTotalAfterDiscountTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTotalAfterDiscountTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        pnSouthThongTin.add(lblTotalAfterDiscount = new JLabel(""));
        lblTotalAfterDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));

        pnSouthButton = new JPanel();
        pnSouthRight.add(pnSouthButton);
        pnSouthButton.setLayout(new BoxLayout(pnSouthButton, BoxLayout.X_AXIS));
        pnSouthButton.add(Box.createHorizontalStrut(100));

        checkBoxPrintInvoice = new JCheckBox("In hóa đơn");
        checkBoxPrintInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        checkBoxPrintInvoice.setSelected(true);
        pnSouthButton.add(checkBoxPrintInvoice);
        checkBoxPrintInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));

        pnSouthButton.add(Box.createHorizontalStrut(10));
        pnSouthButton.add(btnPayment = new JButton("Thanh toán"));
        btnPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));

        btnPayment.setBackground(new Color(107, 208, 107));


        btnAdd.addActionListener(this);
        btnPayment.addActionListener(this);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = hoaDonList.stream()
                .mapToDouble(HoaDon::tinhTongTien)
                .sum();
        lblTotal.setText(FormatCurrencyUtil.formatCurrency(totalPrice));
        lblTotalAfterDiscount.setText(FormatCurrencyUtil.formatCurrency(totalPrice));
    }

    private Duration calculateTotalTime(Time checkInTime, Time checkOutTime) {
        return hoaDonList.get(0).tinhGio(checkInTime, checkOutTime);
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    private String formatFromDate(Time time) {
        Date currentDate = hoaDonList.get(0).getNgayThanhToan();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

        String formattedDate = sdfDate.format(currentDate);
        String formattedTime = sdfTime.format(time);

        return formattedDate + " - " + formattedTime;
    }

    private String formatToDate(Time time) {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

        String formattedDate = sdfDate.format(currentDate);
        String formattedTime = sdfTime.format(time);

        return formattedDate + " - " + formattedTime;
    }

    private void createTable() {
        pnTable.setLayout(null);
        String[] row = {"Mã phiếu đặt phòng", "Tên Phòng", "Loại Phòng", "Sức chứa", "Từ lúc", "Đến lúc", "Giá", "Thành tiền"};

        model = new DefaultTableModel(row, 0);
        table = new JTable(model);
        scroll = new JScrollPane(table);

        scroll.setBounds(6, 23, 774, 103);
        pnTable.add(scroll);

        String[] modelDetailRow = {"Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Loại dịch vụ", "Giá", "Thành tiền"};

        modelDetail = new DefaultTableModel(modelDetailRow, 0);
        tableDetail = new JTable(modelDetail);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableDetail.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tableDetail.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
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
        hoaDonList.forEach(hoaDon -> {
            List<PhieuDatPhong> invoiceDetails = hoaDon.getPhieuDatPhongList();
            for (PhieuDatPhong invoiceDetail : invoiceDetails) {
                Object[] rowData = {
                        invoiceDetail.getMaPhieuDatPhong(),
                        invoiceDetail.getPhong().getTenPhong(),
                        invoiceDetail.getPhong().getLoaiPhong(),
                        invoiceDetail.getPhong().getSucChua(),
                        invoiceDetail.getThoiGianBatDau(),
                        invoiceDetail.getThoiGianKetThuc(),
                        FormatCurrencyUtil.formatCurrency(invoiceDetail.getPhong().getLoaiPhong().getGia()),
                        FormatCurrencyUtil.formatCurrency(invoiceDetail.tinhTongTienPhong())};

                model.addRow(rowData);
            }
        });
    }

    private void loadInvoiceServiceDetail() {
        modelDetail.setRowCount(0);
        List<PhieuDatPhong> tempHoaDon = hoaDonList.stream()
                .flatMap(hoaDon -> hoaDon.getPhieuDatPhongList().stream())
                .collect(Collectors.toList());
        List<ChiTietDatDichVu> chiTietDatDichVuList = tempHoaDon.get(table.getSelectedRow()).getChiTietDatDichVuList();
        for (ChiTietDatDichVu chiTietDatDichVu : chiTietDatDichVuList) {
            if(chiTietDatDichVu.getSoLuong() != 0){
                Object[] rowData = {
                        chiTietDatDichVu.getDichVu().getMaDichVu(),
                        chiTietDatDichVu.getDichVu().getTenDichVu(),
                        chiTietDatDichVu.getSoLuong(),
                        chiTietDatDichVu.getDichVu().getLoaiDichVu().getTenLoaiDichVu(),
                        FormatCurrencyUtil.formatCurrency(chiTietDatDichVu.getDichVu().getGia()),
                        FormatCurrencyUtil.formatCurrency(chiTietDatDichVu.getDichVu().getGia() * chiTietDatDichVu.getSoLuong())};
                modelDetail.addRow(rowData);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnPayment) {
            AtomicBoolean updatePaymentDetailsSuccess = new AtomicBoolean(true);

            hoaDonList.forEach(hoaDon -> {
                double discountAmount = calculateDiscountAmount(hoaDon.getKhuyenMai(), calculateTotalPrice());
                double discountedTotalPrice = calculateDiscountedTotalPrice(calculateTotalPrice(), discountAmount);

                hoaDon.setTongTien(discountedTotalPrice);
                hoaDon.setThoiDiemThanhToan(new Time(System.currentTimeMillis()));

                hoaDon.getPhieuDatPhongList().get(hoaDon.getPhieuDatPhongList().size() - 1).setThoiGianKetThuc(new Time(System.currentTimeMillis())); // Set the end time

                if (!phieuDatPhongDao.updatePaymentDetails(hoaDon)) {
                    updatePaymentDetailsSuccess.set(false);
                }
            });
            if (updatePaymentDetailsSuccess.get()) {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            if (checkBoxPrintInvoice.isSelected()) {
                HoaDon firstHoaDon = hoaDonList.get(0);
                HoaDon newHoaDon = new HoaDon(
                        firstHoaDon.getMaHoaDon(),
                        firstHoaDon.getTongTien(),
                        firstHoaDon.getNgayThanhToan(),
                        firstHoaDon.getThoiDiemThanhToan(),
                        firstHoaDon.getKhachHang(),
                        nhanVien,
                        firstHoaDon.getKhuyenMai());
                newHoaDon.setPhieuDatPhongList(hoaDonList.stream()
                        .flatMap(hoaDon -> hoaDon.getPhieuDatPhongList().stream())
                        .collect(Collectors.toList()));
                boolean success = PdfExportUtil.exportInvoiceToPdf(newHoaDon);

                if (success) {
                    JOptionPane.showMessageDialog(this, "In hóa đơn thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "In hóa đơn thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
            dispose();
        } else if (source == btnAdd) {
            String khuyenMaiName = txtCouponID.getText();

            KhuyenMai khuyenMai = khuyenMaiDAO.getKhuyenMaiByTen(khuyenMaiName);

            if (khuyenMai != null) {
                double totalPrice = calculateTotalPrice();
                double discountAmount = calculateDiscountAmount(khuyenMai, totalPrice);

                JOptionPane.showMessageDialog(this,
                        "Đã tìm thấy khuyến mãi: " + khuyenMai.getTenKhuyenMai() +
                                "\nPhần trăm giảm giá: " + khuyenMai.getPhanTram() + "%" +
                                "\nSố tiền được giảm: " + String.format("%.2f", discountAmount) + " VND");

                double discountedTotalPrice = calculateDiscountedTotalPrice(totalPrice, discountAmount);
                lblTotalAfterDiscount.setText(FormatCurrencyUtil.formatCurrency(discountedTotalPrice));
                hoaDonList.forEach(hoaDon -> hoaDon.setKhuyenMai(khuyenMai));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Không tìm thấy khuyến",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private double calculateDiscountedTotalPrice(double totalPrice, double discountAmount) {
        return totalPrice - discountAmount;
    }

    private double calculateDiscountAmount(KhuyenMai khuyenMai, double totalPrice) {
        double discountAmount = totalPrice * (khuyenMai.getPhanTram() / 100);
        return Math.min(discountAmount, khuyenMai.getGioiHan());
    }

    private double calculateTotalPrice() {
        return hoaDonList.stream().mapToDouble(HoaDon::tinhTongTien).sum();
    }

    private void updateRefundField() {
        try {
            double receiveAmount = Double.parseDouble(txtReceive.getText());
            double totalPrice = calculateTotalPrice();

            if (receiveAmount >= totalPrice) {
                double refundAmount = receiveAmount - totalPrice;
                txtRefund.setText(FormatCurrencyUtil.formatCurrency(refundAmount));
                lblTotalAfterDiscount.setText(FormatCurrencyUtil.formatCurrency(totalPrice));
            } else {
                txtRefund.setText("");
            }
        } catch (NumberFormatException ex) {
            txtRefund.setText("");
        }
    }

}
