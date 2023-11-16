package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.KhachHangDAO;
import dao.PhieuDatPhongDAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;

import javax.swing.JRadioButton;

public class GD_DatPhong extends JFrame implements ActionListener {

    private JPanel pnNorth;
    private JLabel lblTitle, lblRoomType, lblRoomQuantity, lblRoomPrice, lblPhoneNumber, lblCustomerName;
    private JTextField txtRoomName, txtRoomType, txtRoomQuantity, txtRoomPrice, txtPhoneNumber, txtCustomerName;
    private ButtonGroup radioGroup;
    private JRadioButton radioCustomer, radioVisitingCustomer;
    private JButton btnApply, btnExit, btnCheck;
    private KhachHangDAO khachHangDAO;
    private Phong phong;
    private PhieuDatPhongDAO phieuDatPhongDAO;
    private KhachHang currentKhachHang;
    private NhanVien nhanVien;
    public GD_DatPhong(Phong selectedPhong, NhanVien currentNhanVien) {
        phong = selectedPhong;
        nhanVien = currentNhanVien;
        khachHangDAO = new KhachHangDAO();
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        createGUI();
    }

    private void createGUI() {
        setTitle("Đặt phòng");
        setSize(555, 385);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setResizable(false);

        getContentPane().add(pnNorth = new JPanel(), BorderLayout.NORTH);

        JPanel pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        getContentPane().add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);

        JLabel lblRoomName = new JLabel("Tên phòng:");
        lblRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoomName.setBounds(26, 30, 85, 13);
        pnCenter.add(lblRoomName);

        txtRoomName = new JTextField();
        txtRoomName.setText(phong.getTenPhong());
        txtRoomName.setEditable(false);
        txtRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtRoomName.setBounds(121, 28, 134, 16);
        pnCenter.add(txtRoomName);
        txtRoomName.setColumns(10);

        lblRoomType = new JLabel("Loại phòng:");
        lblRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoomType.setBounds(26, 80, 85, 13);
        pnCenter.add(lblRoomType);

        txtRoomType = new JTextField();
        txtRoomType.setText(phong.getLoaiPhong().getTenLoaiPhong());
        txtRoomType.setEditable(false);
        txtRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtRoomType.setColumns(10);
        txtRoomType.setBounds(121, 77, 134, 19);
        pnCenter.add(txtRoomType);

        lblRoomQuantity = new JLabel("Sức chứa:");
        lblRoomQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoomQuantity.setBounds(297, 30, 74, 13);
        pnCenter.add(lblRoomQuantity);

        txtRoomQuantity = new JTextField();
        txtRoomQuantity.setText(phong.getSucChua() + "");
        txtRoomQuantity.setEditable(false);
        txtRoomQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtRoomQuantity.setColumns(10);
        txtRoomQuantity.setBounds(381, 27, 134, 19);
        pnCenter.add(txtRoomQuantity);

        lblRoomPrice = new JLabel("Giá tiền:");
        lblRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoomPrice.setBounds(297, 78, 74, 13);
        pnCenter.add(lblRoomPrice);

        txtRoomPrice = new JTextField();
        txtRoomPrice.setText(phong.getLoaiPhong().getLichSuGiaPhongList().get(0).getGia() + "");
        txtRoomPrice.setEditable(false);
        txtRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtRoomPrice.setColumns(10);
        txtRoomPrice.setBounds(381, 75, 134, 19);
        pnCenter.add(txtRoomPrice);

        radioGroup = new ButtonGroup();

        radioCustomer = new JRadioButton("Khách hàng");
        radioGroup.add(radioCustomer);
        radioCustomer.setSelected(true);
        radioCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
        radioCustomer.setBackground(new Color(255, 255, 255));
        radioCustomer.setBounds(121, 130, 113, 21);
        pnCenter.add(radioCustomer);

        radioVisitingCustomer = new JRadioButton("Khách vãng lai");
        radioGroup.add(radioVisitingCustomer);
        radioVisitingCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
        radioVisitingCustomer.setBackground(Color.WHITE);
        radioVisitingCustomer.setBounds(275, 128, 134, 21);
        pnCenter.add(radioVisitingCustomer);

        lblPhoneNumber = new JLabel("SĐT khách hàng:");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPhoneNumber.setBounds(26, 180, 124, 13);
        pnCenter.add(lblPhoneNumber);

        lblCustomerName = new JLabel("Tên khách hàng");
        lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCustomerName.setBounds(26, 230, 124, 13);
        pnCenter.add(lblCustomerName);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setText("0345678912");
        txtPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtPhoneNumber.setColumns(10);
        txtPhoneNumber.setBounds(160, 177, 249, 19);
        pnCenter.add(txtPhoneNumber);

        txtCustomerName = new JTextField();
        txtCustomerName.setEditable(false);
        txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtCustomerName.setColumns(10);
        txtCustomerName.setBounds(160, 227, 249, 19);
        pnCenter.add(txtCustomerName);

        btnCheck = new JButton("Kiểm tra");
        btnCheck.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCheck.setBackground(new Color(107, 208, 107));
        btnCheck.setBounds(419, 176, 96, 21);
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
        int key = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy không ?", "Thoát", JOptionPane.YES_NO_OPTION);
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
            if (txtPhoneNumber.getText().length() > 0) {
                currentKhachHang = khachHangDAO.getCustomerByPhoneNumber(txtPhoneNumber.getText());
                if (currentKhachHang == null) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                txtCustomerName.setText(currentKhachHang.getTenKhachHang());
            }
        } else if (o.equals(radioCustomer)) {
            txtPhoneNumber.setEditable(true);
        } else if (o.equals(radioVisitingCustomer)) {
            txtPhoneNumber.setEditable(false);
        } if (o.equals(btnApply)) {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String currentTimeString = currentTime.format(formatter);

            boolean bookingResult = phieuDatPhongDAO.bookKaraokeRoom(currentKhachHang.getMaKhachHang(), nhanVien.getMaNhanVien(), phong.getMaPhong(), currentTimeString, new Date(System.currentTimeMillis()));

            if (bookingResult) {
                JOptionPane.showMessageDialog(this, "Đặt phòng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Đặt phòng thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }

    }

    public static void main(String[] args) {
        new GD_DatPhong(new Phong(), new NhanVien()).setVisible(true);
    }
}
