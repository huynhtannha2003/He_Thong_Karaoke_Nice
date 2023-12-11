package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.KhachHangDAO;
import dao.PhieuDatPhongDAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GD_DatPhong extends JFrame implements ActionListener {

    private JPanel pnNorth;
    private JLabel lblTitle, lblPhoneNumber, lblCustomerName;
    private JTextField txtPhoneNumber, txtCustomerName;
    private ButtonGroup radioGroup;
    private JRadioButton radioCustomer, radioVisitingCustomer;
    private JButton btnApply, btnExit, btnCheck;
    private KhachHangDAO khachHangDAO;
    private List<Phong> phong;
    private PhieuDatPhongDAO phieuDatPhongDAO;
    private KhachHang currentKhachHang;
    private NhanVien nhanVien;
    private JTable table;

    public GD_DatPhong(List<Phong> selectedPhong, NhanVien currentNhanVien) {
        phong = selectedPhong;
        nhanVien = currentNhanVien;
        khachHangDAO = new KhachHangDAO();
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        createGUI();
    }

    private void createGUI() {
        setTitle("Đặt phòng");
        setSize(555, 385);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setResizable(false);

        getContentPane().add(pnNorth = new JPanel(), BorderLayout.NORTH);

        JPanel pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        getContentPane().add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);

        radioGroup = new ButtonGroup();

        radioCustomer = new JRadioButton("Khách hàng");
        radioGroup.add(radioCustomer);
        radioCustomer.setSelected(true);
        radioCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
        radioCustomer.setBackground(new Color(255, 255, 255));
        radioCustomer.setBounds(121, 19, 113, 21);
        pnCenter.add(radioCustomer);

        radioVisitingCustomer = new JRadioButton("Khách vãng lai");
        radioGroup.add(radioVisitingCustomer);
        radioVisitingCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
        radioVisitingCustomer.setBackground(Color.WHITE);
        radioVisitingCustomer.setBounds(275, 17, 134, 21);
        pnCenter.add(radioVisitingCustomer);

        lblPhoneNumber = new JLabel("SĐT khách hàng:");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPhoneNumber.setBounds(26, 69, 124, 13);
        pnCenter.add(lblPhoneNumber);

        lblCustomerName = new JLabel("Tên khách hàng");
        lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCustomerName.setBounds(26, 111, 124, 13);
        pnCenter.add(lblCustomerName);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setText("0345678912");
        txtPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtPhoneNumber.setColumns(10);
        txtPhoneNumber.setBounds(160, 66, 249, 19);
        pnCenter.add(txtPhoneNumber);

        txtCustomerName = new JTextField();
        txtCustomerName.setEditable(false);
        txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtCustomerName.setColumns(10);
        txtCustomerName.setBounds(160, 108, 249, 19);
        pnCenter.add(txtCustomerName);

        btnCheck = new JButton("Kiểm tra");
        btnCheck.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCheck.setBackground(new Color(107, 208, 107));
        btnCheck.setBounds(419, 65, 96, 21);
        pnCenter.add(btnCheck);

        btnApply = new JButton("Đặt phòng");
        btnApply.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnApply.setBackground(new Color(107, 208, 107));
        btnApply.setBounds(261, 271, 110, 21);
        pnCenter.add(btnApply);

        btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExit.setBackground(new Color(236, 73, 73));
        btnExit.setBounds(405, 271, 110, 21);
        pnCenter.add(btnExit);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 146, 521, 107);
        pnCenter.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã phòng", "Tên phòng", "Loại phòng", "Sức chứa"}, 0);
        table = new JTable(model);
        phong.forEach(phong -> {
            model.addRow(
                    new Object[]{phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong(), phong.getSucChua()});
        });

        scrollPane.setViewportView(table);
        pnNorth.setBackground(new Color(97, 250, 204));
        pnNorth.add(lblTitle = new JLabel("Đặt phòng"));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnApply.addActionListener(this);
        btnCheck.addActionListener(this);
        btnExit.addActionListener(this);
        radioCustomer.addActionListener(this);
        radioVisitingCustomer.addActionListener(this);
    }

    private void exitButton() {
        int key = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy không ?", "Thoát",
                JOptionPane.YES_NO_OPTION);
        if (key == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnExit)) {
            exitButton();
        } else if (o.equals(btnCheck)) {
            txtCustomerName.setText("");
            if (txtPhoneNumber.getText().length() < 10) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 số", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } else if (txtPhoneNumber.getText().length() > 0) {
                currentKhachHang = khachHangDAO.getCustomerByPhoneNumber(txtPhoneNumber.getText());
                if (currentKhachHang == null) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng", "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    txtCustomerName.setText(currentKhachHang.getTenKhachHang());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hãy nhập vào số điện thoại khách hàng cần tìm", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(radioCustomer)) {
            currentKhachHang = new KhachHang();
            txtPhoneNumber.setEditable(true);
            btnCheck.setEnabled(true);
        } else if (o.equals(radioVisitingCustomer)) {
            txtPhoneNumber.setEditable(false);
            currentKhachHang = new KhachHang("KH.000000.000", "Khách vãng lai", "0000000000");
            btnCheck.setEnabled(false);
            txtCustomerName.setText("");
            txtPhoneNumber.setText("");
        }
        if (o.equals(btnApply)) {
            if (txtCustomerName.getText().length() > 0 || radioVisitingCustomer.isSelected()) {
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String currentTimeString = currentTime.format(formatter);
                AtomicBoolean bookingResult = new AtomicBoolean(true);
                phong.forEach(phong -> {
                    bookingResult.set(phieuDatPhongDAO.bookKaraokeRoom(currentKhachHang.getMaKhachHang(),
                            nhanVien.getMaNhanVien(), phong.getMaPhong(), Time.valueOf(currentTimeString),
                            new Date(System.currentTimeMillis())));
                });

                if (bookingResult.get()) {
                    JOptionPane.showMessageDialog(this, "Đặt phòng thành công", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Đặt phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Hãy tìm khách hàng trước", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

    }
}