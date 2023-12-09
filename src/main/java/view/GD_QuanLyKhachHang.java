package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDAO;
import entity.KhachHang;

import java.awt.FlowLayout;

public class GD_QuanLyKhachHang extends JPanel implements ActionListener {

    private JTextField txtkMaKhachHang;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTable table;
    private JTextField txtMaTimKiem;
    private JTextField txtTenTimKiem;
    private JLabel lbTenKH;
    private JLabel lbMaKhachHang;
    private JLabel lbSDT;
    private JButton btnThem;
    private JButton btnCapNhat;
    private JButton btnXoaTrangThongTin;
    private JLabel lbTimKiemMa;
    private JLabel lbTimKiemTen;
    private JButton btnTimKiem;
    private JButton btnXoaTrangTacVu;
    private JScrollPane scrollPane;
    private DefaultTableModel modelTable;
    private List<KhachHang> list = new ArrayList<KhachHang>();
    private KhachHangDAO daoKH;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GD_QuanLyKhachHang frame = new GD_QuanLyKhachHang();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GD_QuanLyKhachHang() {
        setSize(1000, 700);
        daoKH = new KhachHangDAO();
        setLayout(new BorderLayout(0, 5));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(new Color(97, 250, 204));
        add(TitlePanel, BorderLayout.NORTH);
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lbTitle = new JLabel("Quản lý khách hàng");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        TitlePanel.add(lbTitle);

//		setLayout(new BorderLayout(0, 10));

        JPanel PaneThongTin = new JPanel();
        PaneThongTin.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nh\u1EADp th\u00F4ng tin",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        add(PaneThongTin, BorderLayout.CENTER);
        PaneThongTin.setLayout(new BoxLayout(PaneThongTin, BoxLayout.X_AXIS));

        Box BoxVerticalThongTin = Box.createVerticalBox();
        PaneThongTin.add(BoxVerticalThongTin);

        Component verticalStrut = Box.createVerticalStrut(10);
        BoxVerticalThongTin.add(verticalStrut);

        Box BoxThongTin1 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin1);

        lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setPreferredSize(new Dimension(130, 30));

        lbMaKhachHang = new JLabel("Mã khách hàng:");
        lbMaKhachHang.setPreferredSize(lbTenKH.getPreferredSize());
        lbMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbMaKhachHang.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbMaKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        BoxThongTin1.add(lbMaKhachHang);

        txtkMaKhachHang = new JTextField();
        lbMaKhachHang.setLabelFor(txtkMaKhachHang);
        BoxThongTin1.add(txtkMaKhachHang);

        Component horizontalStrut_5 = Box.createHorizontalStrut(20);
        BoxThongTin1.add(horizontalStrut_5);

        lbSDT = new JLabel("Số điện thoại:");
        lbSDT.setPreferredSize(new Dimension(100, 30));
        lbSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
        BoxThongTin1.add(lbSDT);

        txtSDT = new JTextField();
        lbSDT.setLabelFor(txtSDT);
        BoxThongTin1.add(txtSDT);

        Component verticalStrut_1 = Box.createVerticalStrut(20);
        BoxVerticalThongTin.add(verticalStrut_1);

        Box BoxThongTin2 = Box.createHorizontalBox();
        BoxVerticalThongTin.add(BoxThongTin2);

        lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTenKH.setHorizontalAlignment(SwingConstants.CENTER);
        BoxThongTin2.add(lbTenKH);

        txtTenKH = new JTextField();
        lbTenKH.setLabelFor(txtTenKH);
        BoxThongTin2.add(txtTenKH);

        Component verticalStrut_2 = Box.createVerticalStrut(20);
        BoxVerticalThongTin.add(verticalStrut_2);

        Box BoxThongTin3 = Box.createHorizontalBox();
        BoxThongTin3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        BoxVerticalThongTin.add(BoxThongTin3);

        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(107, 208, 107));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThem.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\add_icon.png"));
        BoxThongTin3.add(btnThem);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        BoxThongTin3.add(horizontalStrut);

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBackground(new Color(107, 208, 107));
        btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCapNhat.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\update_icon.png"));
        BoxThongTin3.add(btnCapNhat);

        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        BoxThongTin3.add(horizontalStrut_1);

        btnXoaTrangThongTin = new JButton("Xóa trắng");
        btnXoaTrangThongTin.setBackground(new Color(107, 208, 107));
        btnXoaTrangThongTin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrangThongTin.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\clear_icon.png"));
        BoxThongTin3.add(btnXoaTrangThongTin);

        BoxVerticalThongTin.add(Box.createVerticalStrut(10));

        PaneThongTin.add(Box.createHorizontalStrut(20));

        JPanel pnCenter = new JPanel();
        add(pnCenter, BorderLayout.SOUTH);
        pnCenter.setLayout(new BorderLayout(0, 10));

        JPanel PaneTacVu = new JPanel();
        PaneTacVu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ch\u1ECDn t\u00E1c v\u1EE5",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnCenter.add(PaneTacVu, BorderLayout.NORTH);
        PaneTacVu.setLayout(new BoxLayout(PaneTacVu, BoxLayout.X_AXIS));

        PaneTacVu.add(Box.createHorizontalStrut(20));

        Box BoxVerticalTacVu = Box.createVerticalBox();
        PaneTacVu.add(BoxVerticalTacVu);

        BoxVerticalTacVu.add(Box.createHorizontalStrut(10));

        Box BoxTacVu = Box.createHorizontalBox();
        BoxVerticalTacVu.add(BoxTacVu);

        lbTimKiemMa = new JLabel("Nhập mã cần tìm:");
        lbTimKiemMa.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTimKiemMa.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxTacVu.add(lbTimKiemMa);

        lbTimKiemMa.setPreferredSize(new Dimension(130, 30));

        txtMaTimKiem = new JTextField();
        lbTimKiemMa.setLabelFor(txtMaTimKiem);
        BoxTacVu.add(txtMaTimKiem);

        BoxTacVu.add(Box.createHorizontalStrut(20));

        lbTimKiemTen = new JLabel("Nhập tên cần tìm:");
        lbTimKiemTen.setPreferredSize(new Dimension(130, 30));
        lbTimKiemTen.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbTimKiemTen.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxTacVu.add(lbTimKiemTen);

        txtTenTimKiem = new JTextField();
        lbTimKiemTen.setLabelFor(txtTenTimKiem);
        BoxTacVu.add(txtTenTimKiem);

        BoxTacVu.add(Box.createHorizontalStrut(60));

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(107, 208, 107));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnTimKiem.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\add_icon.png"));
        BoxTacVu.add(btnTimKiem);

        BoxTacVu.add(Box.createHorizontalStrut(20));

        btnXoaTrangTacVu = new JButton("Xóa trắng");
        btnXoaTrangTacVu.setBackground(new Color(107, 208, 107));
        btnXoaTrangTacVu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoaTrangTacVu.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\clear_icon.png"));
        BoxTacVu.add(btnXoaTrangTacVu);

        BoxVerticalTacVu.add(Box.createVerticalStrut(10));

        PaneTacVu.add(Box.createHorizontalStrut(20));

        String row[] = {"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại"};
        modelTable = new DefaultTableModel(row, 0);
        table = new JTable(modelTable);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnCenter.add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setPreferredSize(new Dimension(200, 350));

        initAction();
        loadData();
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
                txtkMaKhachHang.setText(table.getValueAt(row, 1).toString());
                txtSDT.setText(table.getValueAt(row, 3).toString());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            chucNangThem();
        } else if (o.equals(btnCapNhat)) {
            chucNangCapNhat();
        } else if (o.equals(btnXoaTrangTacVu)) {
            chucNangXoaTrangTacVu();
        } else if (o.equals(btnXoaTrangThongTin)) {
            chucNangXoaTrangThongTin();
        } else if (o.equals(btnTimKiem)) {
            chucNangTimKiem();
        }
    }

    public void chucNangXoaTrangThongTin() {
        txtTenKH.setText("");
        txtSDT.setText("");
    }

    public void chucNangXoaTrangTacVu() {
        txtTenTimKiem.setText("");
        txtMaTimKiem.setText("");
    }

    public void loadData() {
        list = daoKH.getAllKhachHang();
        modelTable.setRowCount(0);
        int i = 1;
        for (KhachHang KH : list) {
            String[] row = {i++ + "", KH.getMaKhachHang(), KH.getTenKhachHang(), KH.getSdt()};
            modelTable.addRow(row);
        }
    }

    public KhachHang revertSPFormKhachHang() {
        if (txtkMaKhachHang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập vào mã khách hàng", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (txtTenKH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập vào tên khách hàng", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập vào số điện thoại khách hàng", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        } else {
            return new KhachHang(txtkMaKhachHang.getText(), txtTenKH.getText(), txtSDT.getText());
        }
    }

    public void chucNangThem() {
        KhachHang khachHang = revertSPFormKhachHang();
        if (khachHang != null) {
            daoKH.createKhachHang(khachHang);
            loadData();
        }
    }

    public void chucNangCapNhat() {
        KhachHang kh = revertSPFormKhachHang();
        if (kh != null) {
            daoKH.updateKhachHang(kh, kh.getMaKhachHang());
            loadData();
        }
    }

    public void chucNangTimKiem() {
        list = daoKH.timKiem(txtMaTimKiem.getText(), txtTenTimKiem.getText());
        modelTable.setRowCount(0);
        int i = 1;
        for (KhachHang KH : list) {
            String[] row = {i++ + "", KH.getMaKhachHang(), KH.getTenKhachHang(), KH.getSdt()};
            modelTable.addRow(row);
        }
    }
}