package view;

import com.toedter.calendar.JDateChooser;
import dao.HoaDonDAO;
import entity.ChiTietDatDichVu;
import entity.HoaDon;
import entity.PhieuDatPhong;
import utils.FormatCurrencyUtil;
import utils.PdfExportUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GD_QuanLyHoaDon extends JPanel implements ActionListener {

    private JTextField txtMaHoaDon, txtTenKhachHang;
    private JLabel lbNgayBatDau, lbMaHoaDon, lbTenKhachHang, lbNgayKetThuc, lblCurrentPageNumber;
    private JButton btnTimKiem, btnXuatHoaDon, btnXoaTrang, btnNext, btnPrevious;
    private JDateChooser txtNgayKetThuc, txtNgayBatDau;
    private JScrollPane scrollPaneInvoice, scrollPaneInvoiceServiceDetail, scrollPaneInvoiceDetail;
    private JPanel pnCenter, pnDetail;
    private JTable tableInvoice, tableInvoiceDetail, tableInvoiceServiceDetail;
    private HoaDonDAO hoaDonDAO;
    private int currentPageNumber;
    private DefaultTableModel modelInvoice, modelInvoiceDetail, modelInvoiceServiceDetail;
    private List<HoaDon> invoices;
    private HoaDon currentInvoice;
    private static final int ROWS_PER_PAGE = 6;
    private Box horizontalBox;
    private JLabel lblSelect;
    private JComboBox<String> cbSelect;

    public GD_QuanLyHoaDon() {
        setSize(1000, 700);

        hoaDonDAO = new HoaDonDAO();
        currentPageNumber = 1;
        createGUI();
    }

    public void initData() {
        invoices = hoaDonDAO.getHoaDonPaged(currentPageNumber, ROWS_PER_PAGE);
        loadInvoiceData(invoices);
    }

    private void loadInvoiceData(List<HoaDon> invoices) {
        modelInvoice.setRowCount(0);
        for (HoaDon invoice : invoices) {
            Object[] rowData = {invoice.getMaHoaDon(), invoice.getKhachHang().getTenKhachHang(),
                    invoice.getKhachHang().getSdt(), invoice.getNgayThanhToan(), invoice.getNhanVien().getMaNhanVien(),
                    invoice.getNhanVien().getTen(), invoice.getKhuyenMai().getTenKhuyenMai(), FormatCurrencyUtil.formatCurrency(invoice.getTongTien())};

            modelInvoice.addRow(rowData);
        }
    }

    private void loadInvoiceDetailData(HoaDon invoice) {
        modelInvoiceServiceDetail.setRowCount(0);
        modelInvoiceDetail.setRowCount(0);
        currentInvoice = hoaDonDAO.getHoaDonByMaHoaDon(invoice.getMaHoaDon());
        for (PhieuDatPhong invoiceDetail : currentInvoice.getPhieuDatPhongList()) {
            Object[] rowData = {invoiceDetail.getMaPhieuDatPhong(), invoiceDetail.getPhong().getTenPhong(),
                    invoiceDetail.getPhong().getLoaiPhong(), invoiceDetail.getPhong().getSucChua(),
                    invoiceDetail.getThoiGianBatDau(), invoiceDetail.getThoiGianKetThuc()};

            modelInvoiceDetail.addRow(rowData);
        }
    }

    private void loadInvoiceServiceDetail(List<ChiTietDatDichVu> chiTietDatDichVuList) {
        modelInvoiceServiceDetail.setRowCount(0);
        for (ChiTietDatDichVu chiTietDatDichVu : chiTietDatDichVuList) {
            Object[] rowData = {chiTietDatDichVu.getDichVu().getMaDichVu(),
                    chiTietDatDichVu.getDichVu().getTenDichVu(), chiTietDatDichVu.getSoLuong(),
                    chiTietDatDichVu.getDichVu().getLoaiDichVu().getTenLoaiDichVu(),
                    FormatCurrencyUtil.formatCurrency(chiTietDatDichVu.getDichVu().getLichSuGiaDichVuList().get(0).getGia())};
            modelInvoiceServiceDetail.addRow(rowData);
        }
    }

    private void createGUI() {
        setLayout(new BorderLayout());

        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(new Color(97, 250, 204));
        add(TitlePanel, BorderLayout.NORTH);

        JLabel lbTitle = new JLabel("Quản lý hóa đơn");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        TitlePanel.add(lbTitle);

        JPanel ContentPanel = new JPanel();
        ContentPanel.setBackground(new Color(255, 255, 255));
        add(ContentPanel);
        ContentPanel.setLayout(new BorderLayout(0, 20));

        JPanel PaneThongTin = new JPanel();
        PaneThongTin.setBackground(new Color(255, 255, 255));
        PaneThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
        ContentPanel.add(PaneThongTin, BorderLayout.NORTH);
        PaneThongTin.setLayout(new BoxLayout(PaneThongTin, BoxLayout.X_AXIS));

        PaneThongTin.add(Box.createHorizontalStrut(20));

        Box BoxVerticalThongTin = Box.createVerticalBox();
        PaneThongTin.add(BoxVerticalThongTin);
        BoxVerticalThongTin.add(Box.createVerticalStrut(10));
        horizontalBox = Box.createHorizontalBox();
        BoxVerticalThongTin.add(horizontalBox);

        lblSelect = new JLabel("Tìm kiếm theo");
        lblSelect.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox.add(lblSelect);

        horizontalBox.add(Box.createHorizontalStrut(20));

        cbSelect = new JComboBox<>();
        cbSelect.setFont(new Font("Tahoma", Font.BOLD, 14));
        cbSelect.setModel(
                new DefaultComboBoxModel<>(new String[]{"Tất cả", "Mã hóa đơn", "Tên khách hàng", "Khoảng thời gian"}));
        horizontalBox.add(cbSelect);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        Box BoxThongTin1 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin1);

        lbNgayBatDau = new JLabel("Từ ngày:");
        lbNgayBatDau.setPreferredSize(new Dimension(100, 30));

        lbMaHoaDon = new JLabel("Mã hóa đơn:");
        lbMaHoaDon.setPreferredSize(new Dimension(100, 30));
        lbMaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbMaHoaDon.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbMaHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
        BoxThongTin1.add(lbMaHoaDon);

        BoxThongTin1.add(Box.createHorizontalStrut(20));

        txtMaHoaDon = new JTextField();
        txtMaHoaDon.setEnabled(false);
        lbMaHoaDon.setLabelFor(txtMaHoaDon);
        BoxThongTin1.add(txtMaHoaDon);

        BoxThongTin1.add(Box.createHorizontalStrut(20));

        lbTenKhachHang = new JLabel("Tên khách hàng:");
        lbTenKhachHang.setPreferredSize(new Dimension(130, 30));
        lbTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin1.add(lbTenKhachHang);

        BoxThongTin1.add(Box.createHorizontalStrut(20));

        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setEnabled(false);
        lbTenKhachHang.setLabelFor(txtTenKhachHang);
        BoxThongTin1.add(txtTenKhachHang);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        Box BoxThongTin2 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin2);

        lbNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbNgayBatDau.setHorizontalAlignment(SwingConstants.LEFT);
        BoxThongTin2.add(lbNgayBatDau);

        BoxThongTin2.add(Box.createHorizontalStrut(20));

        txtNgayBatDau = new JDateChooser();
        txtNgayBatDau.setEnabled(false);
        lbNgayBatDau.setLabelFor(txtNgayBatDau);
        BoxThongTin2.add(txtNgayBatDau);

        BoxThongTin2.add(Box.createHorizontalStrut(20));

        lbNgayKetThuc = new JLabel("Đến ngày:");
        lbNgayKetThuc.setPreferredSize(new Dimension(130, 30));
        lbNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin2.add(lbNgayKetThuc);

        BoxThongTin2.add(Box.createHorizontalStrut(20));

        txtNgayKetThuc = new JDateChooser();
        txtNgayKetThuc.setEnabled(false);
        lbNgayKetThuc.setLabelFor(txtNgayKetThuc);
        BoxThongTin2.add(txtNgayKetThuc);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        Box BoxThongTin3 = Box.createHorizontalBox();
        BoxThongTin3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        BoxVerticalThongTin.add(BoxThongTin3);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(107, 208, 107));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnTimKiem.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\search_icon.png"));
        BoxThongTin3.add(btnTimKiem);

        BoxThongTin3.add(Box.createHorizontalStrut(20));

        btnXuatHoaDon = new JButton("Xuất hóa đơn");
        btnXuatHoaDon.setBackground(new Color(107, 208, 107));
        btnXuatHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXuatHoaDon.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\print_icon.png"));
        BoxThongTin3.add(btnXuatHoaDon);

        BoxThongTin3.add(Box.createHorizontalStrut(20));

        btnXoaTrang = new JButton("Xóa trắng");
        btnXoaTrang.setBackground(new Color(107, 208, 107));
        btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrang.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\clear_icon.png"));
        BoxThongTin3.add(btnXoaTrang);

        BoxVerticalThongTin.add(Box.createVerticalStrut(10));

        PaneThongTin.add(Box.createHorizontalStrut(20));

        String[] Invoicerow = {"Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Ngày thanh toán", "Mã nhân viên",
                "Tên nhân viên", "Khuyến mãi", "Tổng tiền"};
        modelInvoice = new DefaultTableModel(Invoicerow, 0);
        tableInvoice = new JTable(modelInvoice);
        scrollPaneInvoice = new JScrollPane(tableInvoice);
        scrollPaneInvoice.setBounds(10, 20, 958, 140);
        scrollPaneInvoice.setBorder(BorderFactory.createTitledBorder("Danh sách Hóa đơn"));
        tableInvoice.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 255));
        centerPanel.setBounds(0, -22, 978, 168);
        centerPanel.setLayout(null);
        centerPanel.add(scrollPaneInvoice);

        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        ContentPanel.add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        pnCenter.add(centerPanel);

        pnDetail = new JPanel();
        pnDetail.setBackground(new Color(255, 255, 255));
        pnDetail.setBounds(0, 185, 978, 202);
        pnCenter.add(pnDetail);

        String[] InvoiceDetailRow = {"Mã phiếu đặt phòng", "Tên Phòng", "Loại Phòng", "Sức chứa", "Từ lúc",
                "Đến lúc"};
        modelInvoiceDetail = new DefaultTableModel(InvoiceDetailRow, 0);
        tableInvoiceDetail = new JTable(modelInvoiceDetail);
        tableInvoiceDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tableInvoiceDetail.getColumnModel().getColumn(3).setResizable(false);
        pnDetail.setLayout(null);

        scrollPaneInvoiceDetail = new JScrollPane(tableInvoiceDetail);
        scrollPaneInvoiceDetail.setBounds(10, 10, 462, 182);
        scrollPaneInvoiceDetail.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu đặt phòng"));
        pnDetail.add(scrollPaneInvoiceDetail);

        String invoiceServiceDetailRow[] = {"Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Loại dịch vụ", "Giá"};
        modelInvoiceServiceDetail = new DefaultTableModel(invoiceServiceDetailRow, 0);
        tableInvoiceServiceDetail = new JTable(modelInvoiceServiceDetail);

        tableInvoiceServiceDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));

        scrollPaneInvoiceServiceDetail = new JScrollPane(tableInvoiceServiceDetail);
        scrollPaneInvoiceServiceDetail.setBounds(501, 10, 467, 182);
        scrollPaneInvoiceServiceDetail.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
        pnDetail.add(scrollPaneInvoiceServiceDetail);

        btnPrevious = new JButton("<");
        btnPrevious.setBackground(new Color(107, 208, 107));
        btnPrevious.setBounds(420, 154, 45, 21);
        pnCenter.add(btnPrevious);

        btnNext = new JButton(">");
        btnNext.setBackground(new Color(107, 208, 107));
        btnNext.setBounds(508, 156, 45, 21);
        pnCenter.add(btnNext);

        lblCurrentPageNumber = new JLabel("1");
        lblCurrentPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurrentPageNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCurrentPageNumber.setBounds(475, 156, 24, 19);
        pnCenter.add(lblCurrentPageNumber);
        initData();
        btnNext.addActionListener(this);
        btnPrevious.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnXuatHoaDon.addActionListener(this);
        cbSelect.addActionListener(this);
        addAction();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnNext || source == btnPrevious) {
            handlePageNavigation(source);
        } else if (source == cbSelect) {
            handleComboBoxSelection();
        } else if (source == btnTimKiem) {
            handleSearch();
        } else if (source == btnXuatHoaDon) {
            boolean success = PdfExportUtil.exportInvoiceToPdf(currentInvoice);

            if (success) {
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "In hóa đơn thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleSearch() {
        int selectedOption = cbSelect.getSelectedIndex();
        invoices = getInvoicesForPage(1, selectedOption);
        lblCurrentPageNumber.setText("1");
        loadInvoiceData(invoices);
    }

    private void handlePageNavigation(Object source) {
        int targetPageNumber = (source == btnNext) ? currentPageNumber + 1 : currentPageNumber - 1;
        int selectedOption = cbSelect.getSelectedIndex();

        invoices = getInvoicesForPage(targetPageNumber, selectedOption);
        updateAndLoadInvoices(targetPageNumber);
    }

    private List<HoaDon> getInvoicesForPage(int targetPageNumber, int selectedOption) {
        switch (selectedOption) {
            case 0:
                return hoaDonDAO.getHoaDonPaged(targetPageNumber, ROWS_PER_PAGE);
            case 1:
                return hoaDonDAO.getHoaDonPagedByMaHoaDon(txtMaHoaDon.getText(), targetPageNumber, ROWS_PER_PAGE);
            case 2:
                return hoaDonDAO.getHoaDonPagedByTenKhachHang(txtTenKhachHang.getText(), targetPageNumber, ROWS_PER_PAGE);
            case 3:
                return hoaDonDAO.getHoaDonPagedByDateRange(
                        new Date(txtNgayBatDau.getDate().getTime()),
                        new Date(txtNgayKetThuc.getDate().getTime()),
                        targetPageNumber,
                        ROWS_PER_PAGE
                );
            default:
                return new ArrayList<>();
        }
    }

    private void updateAndLoadInvoices(int targetPageNumber) {
        if (!invoices.isEmpty()) {
            currentPageNumber = targetPageNumber;
            lblCurrentPageNumber.setText(String.valueOf(currentPageNumber));
            loadInvoiceData(invoices);
        }
    }

    private void enableComponents(boolean[] enableFlags) {
        txtMaHoaDon.setEnabled(enableFlags[0]);
        txtTenKhachHang.setEnabled(enableFlags[1]);
        txtNgayBatDau.setEnabled(enableFlags[2]);
        txtNgayKetThuc.setEnabled(enableFlags[3]);
    }

    private void handleComboBoxSelection() {
        int selectedOption = cbSelect.getSelectedIndex();
        boolean[] enableFlags = {false, false, false, false};

        switch (selectedOption) {
            case 1:
                enableFlags[0] = true;
                break;
            case 2:
                enableFlags[1] = true;
                break;
            case 3:
                enableFlags[2] = enableFlags[3] = true;
                break;
        }
        enableComponents(enableFlags);
    }


    private void addAction() {
        tableInvoice.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableInvoice.getSelectedRow();
                if (selectedRow != -1) {
                    currentInvoice = invoices.get(selectedRow);
                    loadInvoiceDetailData(currentInvoice);
                }
            }
        });

        tableInvoiceDetail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectRow = tableInvoiceDetail.getSelectedRow();
                if (selectRow != -1) {
                    List<ChiTietDatDichVu> chiTietDatDichVuList = currentInvoice.getPhieuDatPhongList().get(selectRow).getChiTietDatDichVuList();
                    loadInvoiceServiceDetail(chiTietDatDichVuList);
                }
            }
        });
    }
}