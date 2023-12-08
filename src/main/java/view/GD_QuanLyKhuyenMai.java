package view;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.List;

import com.toedter.calendar.JDateChooser;
import dao.KhuyenMaiDAO;
import entity.KhuyenMai;

public class GD_QuanLyKhuyenMai extends JPanel implements ActionListener {

    private JTextField txtMaKhuyenMai, txtTenKhuyenMai, txtMaTimKiem, txtGioiHan;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel modelTable;
    private Box horizontalBox, horizontalBox_1, BoxThongTin3, horizontalBox_2;
    private JButton btnThem, btnCapNhat, btnXoaTrangThongTin, btnXoaTrangTacVu, btnTimKiem;
    private JDateChooser txtTimKiemNgayBatDau, txtTimKiemNgayKetThuc, txtNgayKetThuc, txtNgayBatDau;
    private List<KhuyenMai> list = new ArrayList<>();
    private KhuyenMaiDAO daoKM;
    private JLabel lbNgayKetThuc, lbThoiDiemBatDau, lblNewLabel;
    private JComboBox cbTacVuTrangThai, cbPhanTram, cbThoiDiemBatDau, cbThoiDiemKetThuc;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GD_QuanLyKhuyenMai frame = new GD_QuanLyKhuyenMai();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GD_QuanLyKhuyenMai() {
        setSize(1000, 700);
        daoKM = new KhuyenMaiDAO();
        setLayout(new BorderLayout(0, 0));


        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(new Color(97, 250, 204));
        add(TitlePanel, BorderLayout.NORTH);

        JLabel lbTitle = new JLabel("Quản lý khuyến mãi");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        TitlePanel.add(lbTitle);

        JPanel pnCenter = new JPanel();
        add(pnCenter);
        pnCenter.setLayout(new BorderLayout(0, 10));

        JPanel PaneThongTin = new JPanel();
        PaneThongTin.setBorder(
                new TitledBorder(null, "Nhập thông tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnCenter.add(PaneThongTin, BorderLayout.NORTH);
        PaneThongTin.setLayout(new BoxLayout(PaneThongTin, BoxLayout.X_AXIS));

        Box BoxVerticalThongTin = Box.createVerticalBox();
        PaneThongTin.add(BoxVerticalThongTin);

        Component verticalStrut = Box.createVerticalStrut(10);
        BoxVerticalThongTin.add(verticalStrut);

        Box BoxThongTin1 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin1);

        JLabel lbPhanTram = new JLabel("Phần trăm :");

        BoxThongTin1.add(Box.createHorizontalStrut(20));

        JLabel lbMaKhuyenMai = new JLabel("Mã khuyến mãi:");
        lbMaKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbMaKhuyenMai.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbMaKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
        BoxThongTin1.add(lbMaKhuyenMai);

        txtMaKhuyenMai = new JTextField();
        txtMaKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbMaKhuyenMai.setLabelFor(txtMaKhuyenMai);
        BoxThongTin1.add(txtMaKhuyenMai);

        Component horizontalStrut_5 = Box.createHorizontalStrut(20);
        BoxThongTin1.add(horizontalStrut_5);

        JLabel lbTenKhuyenMai = new JLabel("Tên khuyến mãi:");
        lbTenKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin1.add(lbTenKhuyenMai);

        txtTenKhuyenMai = new JTextField();
        txtTenKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbTenKhuyenMai.setLabelFor(txtTenKhuyenMai);
        BoxThongTin1.add(txtTenKhuyenMai);

        Component verticalStrut_1 = Box.createVerticalStrut(20);
        BoxVerticalThongTin.add(verticalStrut_1);

        Box BoxThongTin2 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin2);

        BoxThongTin2.add(Box.createHorizontalStrut(20));

        lbPhanTram.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbPhanTram.setHorizontalAlignment(SwingConstants.LEFT);
        BoxThongTin2.add(lbPhanTram);

        cbPhanTram = new JComboBox<Float>();
        cbPhanTram.setModel(new DefaultComboBoxModel(
                new String[]{"5.0", "10.0", "15.0", "20.0", "25.0", "30.0", "35.0", "40.0", "45.0", "50.0", "55.0",
                        "60.0", "65.0", "70.0", "75.0", "80.0", "85.0", "90.0", "95.0", "100.0"}));
        cbPhanTram.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbPhanTram.setLabelFor(cbPhanTram);
        BoxThongTin2.add(cbPhanTram);

        BoxThongTin2.add(Box.createHorizontalStrut(290));

        JLabel lbGioiHan = new JLabel("Giới hạn:");
        lbGioiHan.setPreferredSize(new Dimension(70, 30));
        lbGioiHan.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin2.add(lbGioiHan);

        txtGioiHan = new JTextField();
        txtGioiHan.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbGioiHan.setLabelFor(txtGioiHan);
        BoxThongTin2.add(txtGioiHan);
        txtGioiHan.setColumns(10);

        Component verticalStrut_2 = Box.createVerticalStrut(20);
        BoxVerticalThongTin.add(verticalStrut_2);

        horizontalBox = Box.createHorizontalBox();
        BoxVerticalThongTin.add(horizontalBox);

        horizontalBox.add(Box.createHorizontalStrut(20));

        JLabel lbNgayBatDau = new JLabel("Ngày bắt đầu:");
        lbNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox.add(lbNgayBatDau);

        txtNgayBatDau = new JDateChooser();
        txtNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtNgayBatDau.setDateFormatString("yyyy-MM-dd");
        lbNgayBatDau.setLabelFor(txtNgayBatDau);
        txtNgayBatDau.setPreferredSize(new Dimension(80, 20));
        horizontalBox.add(txtNgayBatDau);

        horizontalBox.add(Box.createHorizontalStrut(20));

        lbNgayKetThuc = new JLabel("Ngày kết thúc:");
        lbNgayKetThuc.setPreferredSize(new Dimension(120, 30));
        lbNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox.add(lbNgayKetThuc);

        txtNgayKetThuc = new JDateChooser();
        txtNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtNgayKetThuc.setDateFormatString("yyyy-MM-dd");
        horizontalBox.add(txtNgayKetThuc);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        horizontalBox_1 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(horizontalBox_1);

        horizontalBox_1.add(Box.createHorizontalStrut(20));

        lbThoiDiemBatDau = new JLabel("Thời điểm bắt đầu:");
        lbThoiDiemBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_1.add(lbThoiDiemBatDau);
        lbNgayBatDau.setPreferredSize(lbThoiDiemBatDau.getPreferredSize());
        lbPhanTram.setPreferredSize(lbThoiDiemBatDau.getPreferredSize());
        lbMaKhuyenMai.setPreferredSize(lbThoiDiemBatDau.getPreferredSize());

        cbThoiDiemBatDau = new JComboBox();
        cbThoiDiemBatDau.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbThoiDiemBatDau.setModel(new DefaultComboBoxModel(new String[]{"8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
        lbThoiDiemBatDau.setLabelFor(cbThoiDiemBatDau);
        horizontalBox_1.add(cbThoiDiemBatDau);

        horizontalBox_1.add(Box.createHorizontalStrut(20));

        lblNewLabel = new JLabel("Thời điểm kết thúc:");
        lblNewLabel.setPreferredSize(new Dimension(150, 30));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_1.add(lblNewLabel);

        cbThoiDiemKetThuc = new JComboBox();
        cbThoiDiemKetThuc.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbThoiDiemKetThuc.setModel(new DefaultComboBoxModel(new String[]{"8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
        lblNewLabel.setLabelFor(cbThoiDiemKetThuc);
        horizontalBox_1.add(cbThoiDiemKetThuc);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        BoxThongTin3 = Box.createHorizontalBox();
        BoxThongTin3.setAlignmentX(1.0f);
        BoxVerticalThongTin.add(BoxThongTin3);

        btnThem = new JButton("Thêm");
        btnThem.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\add_icon.png"));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThem.setBackground(new Color(107, 208, 107));
        BoxThongTin3.add(btnThem);

        BoxThongTin3.add(Box.createHorizontalStrut(20));

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\update_icon.png"));
        btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCapNhat.setBackground(new Color(107, 208, 107));
        BoxThongTin3.add(btnCapNhat);

        BoxThongTin3.add(Box.createHorizontalStrut(20));

        btnXoaTrangThongTin = new JButton("Xóa trắng");
        btnXoaTrangThongTin.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\clear_icon.png"));
        btnXoaTrangThongTin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrangThongTin.setBackground(new Color(107, 208, 107));
        BoxThongTin3.add(btnXoaTrangThongTin);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        PaneThongTin.add(Box.createHorizontalStrut(20));

        JPanel pnCenterInside = new JPanel();
        pnCenter.add(pnCenterInside, BorderLayout.CENTER);
        pnCenterInside.setLayout(new BorderLayout(0, 10));

        JPanel PaneTacVu = new JPanel();
        PaneTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,

                null, null));
        pnCenterInside.add(PaneTacVu, BorderLayout.NORTH);
        PaneTacVu.setLayout(new BoxLayout(PaneTacVu, BoxLayout.X_AXIS));

        Component horizontalStrut_3 = Box.createHorizontalStrut(20);
        PaneTacVu.add(horizontalStrut_3);

        Box BoxVerticalTacVu = Box.createVerticalBox();
        PaneTacVu.add(BoxVerticalTacVu);

        Component verticalStrut_3 = Box.createVerticalStrut(10);
        BoxVerticalTacVu.add(verticalStrut_3);

        Box BoxTacVu = Box.createHorizontalBox();
        BoxVerticalTacVu.add(BoxTacVu);

        JLabel lbTimKiemMaKM = new JLabel("Mã khuyến mãi:");
        lbTimKiemMaKM.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTimKiemMaKM.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxTacVu.add(lbTimKiemMaKM);

        lbTimKiemMaKM.setPreferredSize(new Dimension(120, 30));

        txtMaTimKiem = new JTextField();
        lbTimKiemMaKM.setLabelFor(txtMaTimKiem);
        BoxTacVu.add(txtMaTimKiem);

        Component horizontalStrut_8_1_1_1 = Box.createHorizontalStrut(20);
        BoxTacVu.add(horizontalStrut_8_1_1_1);

        JLabel lbTimKiemTrangThai = new JLabel("Trạng thái:");
        lbTimKiemTrangThai.setPreferredSize(new Dimension(90, 30));
        lbTimKiemTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTimKiemTrangThai.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxTacVu.add(lbTimKiemTrangThai);

        cbTacVuTrangThai = new JComboBox();
        lbTimKiemTrangThai.setLabelFor(cbTacVuTrangThai);
        cbTacVuTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hiệu lực", "Vô hiệu"}));
        BoxTacVu.add(cbTacVuTrangThai);

        Component horizontalStrut_9_1_1_1 = Box.createHorizontalStrut(60);
        BoxTacVu.add(horizontalStrut_9_1_1_1);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(new Dimension(127, 30));
        btnTimKiem.setBackground(new Color(107, 208, 107));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnTimKiem.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\search_icon.png"));
        BoxTacVu.add(btnTimKiem);

        Component horizontalStrut_10_1_1_1 = Box.createHorizontalStrut(20);
        BoxTacVu.add(horizontalStrut_10_1_1_1);

        Component verticalStrut_4 = Box.createVerticalStrut(20);
        BoxVerticalTacVu.add(verticalStrut_4);

        horizontalBox_2 = Box.createHorizontalBox();
        BoxVerticalTacVu.add(horizontalBox_2);

        JLabel lbTimKiemNgayBatDau = new JLabel("Ngày bắt đầu:");
        lbTimKiemNgayBatDau.setPreferredSize(lbPhanTram.getPreferredSize());
        lbTimKiemNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_2.add(lbTimKiemNgayBatDau);

        txtTimKiemNgayBatDau = new JDateChooser();
        lbTimKiemNgayBatDau.setLabelFor(txtTimKiemNgayBatDau);
        horizontalBox_2.add(txtTimKiemNgayBatDau);

        horizontalBox_2.add(Box.createHorizontalStrut(20));

        JLabel lbTimKiemNgayKetThuc = new JLabel("Ngày kết thúc:");
        lbTimKiemNgayKetThuc.setPreferredSize(lbPhanTram.getPreferredSize());
        lbTimKiemNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_2.add(lbTimKiemNgayKetThuc);

        txtTimKiemNgayKetThuc = new JDateChooser();
        lbTimKiemNgayKetThuc.setLabelFor(txtTimKiemNgayKetThuc);
        horizontalBox_2.add(txtTimKiemNgayKetThuc);

        horizontalBox_2.add(Box.createHorizontalStrut(20));

        btnXoaTrangTacVu = new JButton("Xóa trắng");
        btnXoaTrangTacVu.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\clear_icon.png"));
        btnXoaTrangTacVu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrangTacVu.setBackground(new Color(107, 208, 107));
        horizontalBox_2.add(btnXoaTrangTacVu);

        horizontalBox_2.add(Box.createHorizontalStrut(20));

        BoxVerticalTacVu.add(Box.createVerticalStrut(20));

        PaneTacVu.add(Box.createHorizontalStrut(20));

        String row[] = {"STT", "Mã khuyến mãi", "Tên khuyến mãi", "Phần trăm", "Giới hạn", "Ngày bắt đầu",
                "Ngày kết thúc", "Thời điểm bắt đầu", "Thời điểm kết thúc"};
        modelTable = new DefaultTableModel(row, 0);
        table = new JTable(modelTable);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        scrollPane = new JScrollPane(table);
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnCenterInside.add(scrollPane, BorderLayout.CENTER);
        loadData();
        initAction();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            chucNangThem();
        } else if (o.equals(btnXoaTrangThongTin)) {
            chucNangXoaTrangThongTin();
        } else if (o.equals(btnCapNhat)) {
            chucNangCapNhat();
        } else if (o.equals(btnXoaTrangTacVu)) {
            chucNangXoaTrangTacVu();
        } else if (o.equals(btnTimKiem)) {
            try {
                chucNangTimKiem();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (o.equals(btnXoaTrangTacVu)) {
            chucNangXoaTrangTacVu();
        }
    }

    public void initAction() {
        btnCapNhat.addActionListener(this);
        btnThem.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXoaTrangTacVu.addActionListener(this);
        btnXoaTrangThongTin.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtMaKhuyenMai.setText(table.getValueAt(row, 1).toString());
                txtTenKhuyenMai.setText(table.getValueAt(row, 2).toString());
                txtGioiHan.setText(table.getValueAt(row, 4).toString());
                cbPhanTram.setSelectedItem(table.getValueAt(row, 3).toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    java.util.Date ngayBatDau = dateFormat.parse(table.getValueAt(row, 5).toString());
                    txtNgayBatDau.setDate(new java.sql.Date(ngayBatDau.getTime()));
                    java.util.Date ngayKetThuc = dateFormat.parse(table.getValueAt(row, 6).toString());
                    txtNgayKetThuc.setDate(new java.sql.Date(ngayKetThuc.getTime()));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                String thoiDiemBatDau = table.getValueAt(row, 7).toString().charAt(1) + "";
                String thoiDiemKetThuc = table.getValueAt(row, 8).toString().substring(0, 2);
                cbThoiDiemBatDau.setSelectedItem(thoiDiemBatDau);
                cbThoiDiemKetThuc.setSelectedItem(thoiDiemKetThuc);
            }
        });
    }

    public KhuyenMai revertSPFormKhuyenMai() {
        try {
            java.util.Date ngayBatDau = txtNgayBatDau.getDate();
            java.sql.Date sqlNgayBatDau = new java.sql.Date(ngayBatDau.getTime());
            java.util.Date ngayKetThuc = txtNgayKetThuc.getDate();
            java.sql.Date sqlNgayKetThuc = new java.sql.Date(ngayBatDau.getTime());
            String maKhuyenMai = txtMaKhuyenMai.getText();
            String tenKhuyenMai = txtTenKhuyenMai.getText();
            double phanTram = Double.parseDouble(cbPhanTram.getSelectedItem().toString());
            double gioiHan = Double.parseDouble(txtGioiHan.getText());
            int HourBatDau = Integer.parseInt(cbThoiDiemBatDau.getSelectedItem().toString());
            int HourKetThuc = Integer.parseInt(cbThoiDiemKetThuc.getSelectedItem().toString());
            Time thoiDiemBatDau = new java.sql.Time(HourBatDau, 0, 0);
            Time thoiDiemKetThuc = new java.sql.Time(HourKetThuc, 0, 0);
            return new KhuyenMai(maKhuyenMai, tenKhuyenMai, phanTram, gioiHan, sqlNgayBatDau, sqlNgayKetThuc,
                    thoiDiemBatDau, thoiDiemKetThuc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadData() {
        list = daoKM.getAllKhuyenMai();
        int i = 1;
        modelTable.setRowCount(0);
        for (KhuyenMai km : list) {
            String[] row = {i++ + "", km.getMaKhuyenMai(), km.getTenKhuyenMai(), String.valueOf(km.getPhanTram()),
                    String.valueOf(km.getGioiHan()), km.getNgayBatDau().toString(), km.getNgayKetThuc().toString(),
                    km.getThoiDiemBatDau().toString(), km.getThoiDiemKetThuc().toString()};
            modelTable.addRow(row);
        }
    }

    public void chucNangThem() {
        daoKM.createKhuyenMai(revertSPFormKhuyenMai());
        int i = list.size();
        loadData();
    }

    public void chucNangXoaTrangThongTin() {
        txtTenKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtGioiHan.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);
        cbThoiDiemBatDau.setSelectedIndex(0);
        cbThoiDiemKetThuc.setSelectedIndex(0);
        cbPhanTram.setSelectedIndex(0);
    }

    private void chucNangTimKiem() throws SQLException {
        java.util.Date ngayBatDau = txtTimKiemNgayBatDau.getDate();
        java.sql.Date sqlNgayBatDau = new java.sql.Date(ngayBatDau.getTime());
        java.util.Date ngayKetThuc = txtTimKiemNgayKetThuc.getDate();
        java.sql.Date sqlNgayKetThuc = new java.sql.Date(ngayBatDau.getTime());
        list = daoKM.TimKiem(txtMaTimKiem.getText(), sqlNgayBatDau, sqlNgayKetThuc);
        loadData();
    }

    private void chucNangXoaTrangTacVu() {
        txtMaTimKiem.setText("");
        txtTimKiemNgayBatDau.setDate(null);
        txtTimKiemNgayKetThuc.setDate(null);
    }

    public void chucNangCapNhat() {
        KhuyenMai km = revertSPFormKhuyenMai();
        daoKM.updateKhuyenMai(km, km.getMaKhuyenMai());
        loadData();
    }

}
