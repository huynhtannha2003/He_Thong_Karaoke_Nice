package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import utils.*;

public class GD_QuanLyKhuyenMai extends JPanel {

    private JTextField txtMaKhuyenMai;
    private JTextField txtTenKhuyenMai;
    private JTable table;
    private JTextField txtMaTimKiem;
    private JLabel lbPhanTram;
    private JLabel lbMaKhuyenMai;
    private JTextField txtMaKhachHang;
    private JLabel lbTenKhuyenMai;
    private JLabel lbTimKiemMaKM;
    private JLabel lbTimKiemTrangThai;
    private JButton btnTimKiem;
    private JScrollPane scrollPane;
    private DefaultTableModel modelTable;
    private Box horizontalBox;
    private Box horizontalBox_1;
    private Component verticalStrut_6;
    private JLabel lbNgayBatDau;
    private JDateChooser txtNgayBatDau;
    private JLabel lbTrangThai;
    private JComboBox cbTrangThai;
    private JLabel lbNgayKetThuc;
    private JDateChooser txtNgayKetThuc;
    private JLabel lbGioiHan;
    private JTextField txtGioiHan;
    private Box BoxThongTin3;
    private JButton btnThem;
    private JButton btnCapNhat;
    private JButton btnXoaTrangThongTin;
    private JComboBox cbPhanTram;
    private Box horizontalBox_2;
    private JLabel lbTimKiemNgayBatDau;
    private JDateChooser txtTimKiemNgayBatDau;
    private JLabel lbTimKiemNgayKetThuc;
    private JDateChooser txtTimKiemNgayKetThuc;
    private JComboBox cbTacVuTrangThai;
    private JButton btnXoaTrangTacVu;

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

        setLayout(new BorderLayout(0, 5));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(new Color(97, 250, 254));
        add(TitlePanel, BorderLayout.NORTH);

        JLabel lbTitle = new JLabel("Quản lý khuyến mãi");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        TitlePanel.add(lbTitle);

        JPanel ContentPanel = new JPanel();
        add(ContentPanel);
        ContentPanel.setLayout(new BorderLayout(0, 10));

        JPanel PaneThongTin = new JPanel();
        PaneThongTin.setBorder(
                new TitledBorder(null, "Nhập thông tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        ContentPanel.add(PaneThongTin, BorderLayout.NORTH);
        PaneThongTin.setLayout(new BoxLayout(PaneThongTin, BoxLayout.X_AXIS));

        Box BoxVerticalThongTin = Box.createVerticalBox();
        PaneThongTin.add(BoxVerticalThongTin);

        Component verticalStrut = Box.createVerticalStrut(10);
        BoxVerticalThongTin.add(verticalStrut);

        Box BoxThongTin1 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin1);

        lbPhanTram = new JLabel("Phần trăm :");
        lbPhanTram.setPreferredSize(new Dimension(120, 30));

        BoxThongTin1.add(Box.createHorizontalStrut(20));

        lbMaKhuyenMai = new JLabel("Mã khuyến mãi:");
        lbMaKhuyenMai.setPreferredSize(new Dimension(120, 30));
        lbMaKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbMaKhuyenMai.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbMaKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
        BoxThongTin1.add(lbMaKhuyenMai);

        txtMaKhuyenMai = new JTextField();
        lbMaKhuyenMai.setLabelFor(txtMaKhuyenMai);
        BoxThongTin1.add(txtMaKhuyenMai);

        Component horizontalStrut_5 = Box.createHorizontalStrut(20);
        BoxThongTin1.add(horizontalStrut_5);

        lbTenKhuyenMai = new JLabel("Tên khuyến mãi:");
        lbTenKhuyenMai.setPreferredSize(lbPhanTram.getPreferredSize());
        lbTenKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin1.add(lbTenKhuyenMai);

        txtTenKhuyenMai = new JTextField();
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

        cbPhanTram = new JComboBox();
        lbPhanTram.setLabelFor(cbPhanTram);
        cbPhanTram.setModel(new DefaultComboBoxModel(new String[]{"30%", "70%", "100%"}));
        BoxThongTin2.add(cbPhanTram);

        BoxThongTin2.add(Box.createHorizontalStrut(20));

        lbGioiHan = new JLabel("Giới hạn:");
        lbGioiHan.setPreferredSize(new Dimension(70, 30));
        lbGioiHan.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin2.add(lbGioiHan);

        txtGioiHan = new JTextField();
        lbGioiHan.setLabelFor(txtGioiHan);
        BoxThongTin2.add(txtGioiHan);
        txtGioiHan.setColumns(10);

        Component verticalStrut_2 = Box.createVerticalStrut(20);
        BoxVerticalThongTin.add(verticalStrut_2);

        horizontalBox = Box.createHorizontalBox();
        BoxVerticalThongTin.add(horizontalBox);

        horizontalBox.add(Box.createHorizontalStrut(20));

        lbNgayBatDau = new JLabel("Ngày bắt đầu:");
        lbNgayBatDau.setPreferredSize(lbPhanTram.getPreferredSize());
        lbNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox.add(lbNgayBatDau);

        txtNgayBatDau = new JDateChooser();
        lbNgayBatDau.setLabelFor(txtNgayBatDau);
        txtNgayBatDau.setPreferredSize(new Dimension(0, 20));
        horizontalBox.add(txtNgayBatDau);

        horizontalBox.add(Box.createHorizontalStrut(20));

        lbTrangThai = new JLabel("Trạng thái:");
        lbTrangThai.setPreferredSize(new Dimension(90, 30));
        lbTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox.add(lbTrangThai);

        cbTrangThai = new JComboBox();
        lbTrangThai.setLabelFor(cbTrangThai);
        cbTrangThai.setModel(new DefaultComboBoxModel(new String[]{"Còn hiệu lực", "Hết hiệu lực"}));
        horizontalBox.add(cbTrangThai);

        verticalStrut_6 = Box.createVerticalStrut(20);
        BoxVerticalThongTin.add(verticalStrut_6);

        horizontalBox_1 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(horizontalBox_1);

        horizontalBox_1.add(Box.createHorizontalStrut(20));

        lbNgayKetThuc = new JLabel("Ngày kết thúc:");
        lbNgayKetThuc.setPreferredSize(lbPhanTram.getPreferredSize());
        lbNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_1.add(lbNgayKetThuc);

        txtNgayKetThuc = new JDateChooser();
        lbNgayKetThuc.setLabelFor(txtNgayKetThuc);
        horizontalBox_1.add(txtNgayKetThuc);

        BoxVerticalThongTin.add(Box.createVerticalStrut(20));

        BoxThongTin3 = Box.createHorizontalBox();
        BoxThongTin3.setAlignmentX(1.0f);
        BoxVerticalThongTin.add(BoxThongTin3);

        btnThem = new JButton("Thêm");
        btnThem.setIcon(new ImageIcon(getClass().getResource("/image/icon/add_icon.png")));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThem.setBackground(new Color(107, 208, 107));
        BoxThongTin3.add(btnThem);

        BoxThongTin3.add(Box.createHorizontalStrut(20));

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setIcon(new ImageIcon(getClass().getResource("/image/icon/update_icon.png")));
        btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCapNhat.setBackground(new Color(107, 208, 107));
        BoxThongTin3.add(btnCapNhat);

        BoxThongTin3.add(Box.createHorizontalStrut(20));

        btnXoaTrangThongTin = new JButton("Xóa trắng");
        btnXoaTrangThongTin.setIcon(new ImageIcon(getClass().getResource("/image/icon/clear_icon.png")));
        btnXoaTrangThongTin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrangThongTin.setBackground(new Color(107, 208, 107));
        BoxThongTin3.add(btnXoaTrangThongTin);

        BoxVerticalThongTin.add(Box.createVerticalStrut(10));

        JPanel ContentPane = new JPanel();
        ContentPanel.add(ContentPane, BorderLayout.CENTER);
        ContentPane.setLayout(new BorderLayout(0, 10));

        JPanel PaneTacVu = new JPanel();
        PaneTacVu.setBorder(new TitledBorder(null, "Ch\u1ECDn t\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,

                null, null));
        ContentPane.add(PaneTacVu, BorderLayout.NORTH);
        PaneTacVu.setLayout(new BoxLayout(PaneTacVu, BoxLayout.X_AXIS));

        Component horizontalStrut_3 = Box.createHorizontalStrut(20);
        PaneTacVu.add(horizontalStrut_3);

        Box BoxVerticalTacVu = Box.createVerticalBox();
        PaneTacVu.add(BoxVerticalTacVu);

        Component verticalStrut_3 = Box.createVerticalStrut(10);
        BoxVerticalTacVu.add(verticalStrut_3);

        Box BoxTacVu = Box.createHorizontalBox();
        BoxVerticalTacVu.add(BoxTacVu);

        lbTimKiemMaKM = new JLabel("Mã khuyến mãi:");
        lbTimKiemMaKM.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTimKiemMaKM.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxTacVu.add(lbTimKiemMaKM);

        lbTimKiemMaKM.setPreferredSize(new Dimension(120, 30));

        txtMaTimKiem = new JTextField();
        lbTimKiemMaKM.setLabelFor(txtMaTimKiem);
        BoxTacVu.add(txtMaTimKiem);

        Component horizontalStrut_8_1_1_1 = Box.createHorizontalStrut(20);
        BoxTacVu.add(horizontalStrut_8_1_1_1);

        lbTimKiemTrangThai = new JLabel("Trạng thái:");
        lbTimKiemTrangThai.setPreferredSize(new Dimension(90, 30));
        lbTimKiemTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTimKiemTrangThai.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxTacVu.add(lbTimKiemTrangThai);

        cbTacVuTrangThai = new JComboBox();
        lbTimKiemTrangThai.setLabelFor(cbTacVuTrangThai);
        cbTacVuTrangThai.setModel(new DefaultComboBoxModel(new String[]{"Còn hiệu lực", "Hết hiệu lực"}));
        BoxTacVu.add(cbTacVuTrangThai);

        Component horizontalStrut_9_1_1_1 = Box.createHorizontalStrut(60);
        BoxTacVu.add(horizontalStrut_9_1_1_1);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(new Dimension(127, 30));
        btnTimKiem.setBackground(new Color(107, 208, 107));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/image/icon/search_icon.png")));
        BoxTacVu.add(btnTimKiem);

        Component horizontalStrut_10_1_1_1 = Box.createHorizontalStrut(20);
        BoxTacVu.add(horizontalStrut_10_1_1_1);

        Component verticalStrut_4 = Box.createVerticalStrut(20);
        BoxVerticalTacVu.add(verticalStrut_4);

        horizontalBox_2 = Box.createHorizontalBox();
        BoxVerticalTacVu.add(horizontalBox_2);

        lbTimKiemNgayBatDau = new JLabel("Ngày bắt đầu:");
        lbTimKiemNgayBatDau.setPreferredSize(lbPhanTram.getPreferredSize());
        lbTimKiemNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_2.add(lbTimKiemNgayBatDau);

        txtTimKiemNgayBatDau = new JDateChooser();
        lbTimKiemNgayBatDau.setLabelFor(txtTimKiemNgayBatDau);
        horizontalBox_2.add(txtTimKiemNgayBatDau);

        horizontalBox_2.add(Box.createHorizontalStrut(20));

        lbTimKiemNgayKetThuc = new JLabel("Ngày kết thúc:");
        lbTimKiemNgayKetThuc.setPreferredSize(lbPhanTram.getPreferredSize());
        lbTimKiemNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox_2.add(lbTimKiemNgayKetThuc);

        txtTimKiemNgayKetThuc = new JDateChooser();
        lbTimKiemNgayKetThuc.setLabelFor(txtTimKiemNgayKetThuc);
        horizontalBox_2.add(txtTimKiemNgayKetThuc);

        horizontalBox_2.add(Box.createHorizontalStrut(20));

        btnXoaTrangTacVu = new JButton("Xóa trắng");
        btnXoaTrangTacVu.setIcon(new ImageIcon(getClass().getResource("/image/icon/clear_icon.png")));
        btnXoaTrangTacVu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrangTacVu.setBackground(new Color(107, 208, 107));
        horizontalBox_2.add(btnXoaTrangTacVu);

        horizontalBox_2.add(Box.createHorizontalStrut(20));

        BoxVerticalTacVu.add(Box.createVerticalStrut(10));

        Component horizontalStrut_4 = Box.createHorizontalStrut(20);
        PaneTacVu.add(horizontalStrut_4);

        String row[] = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email"};
        modelTable = new DefaultTableModel(row, 0);
        table = new JTable(modelTable);
        scrollPane = new JScrollPane(table);
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        ContentPane.add(scrollPane, BorderLayout.CENTER);

        String s[] = {"KH123", "Vũ Quốc Huy", "0334405617", "vuquochuy.01012003@gmail.com"};
        modelTable.addRow(s);

    }

}
