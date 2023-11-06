package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import utils.*;

public class GD_QuanLyNhanVien extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu MenuHeThong;
	private JMenuItem MenuItemTrangChu;
	private JMenuItem MenuItemTaiKhoan;
	private JMenuItem MenuItemDangXuat;
	private JMenuItem MenuItemThoat;
	private JMenu MenuDanhMuc;
	private JMenuItem MenuItemPhong;
	private JMenuItem MenuItemNhanVien;
	private JMenuItem MenuItemDichVu;
	private JMenuItem MenuItemKhachHang;
	private JMenuItem MenuItemKhuyenMai;
	private JMenu MenuXuLy;
	private JMenuItem MenuItemDatPhong;
	private JMenu MenuThongKe;
	private JMenuItem MenuItemTKDoanhThu;
	private JMenu MenuTroGiup;
	private JPanel PaneThongTinText;
	private Box BoxVerticalThongTin_1;
	private Box horizontalBox_4;
	private Box horizontalBox_5;
	private Box horizontalBox_6;
	private Box horizontalBox_7;
	private JPanel PaneThongTinImage;
	private JLabel lbMaNhanVien;
	private JTextField txtMaNhanVien;
	private JLabel lbSDT;
	private JTextField txtSDT;
	private JLabel lbTenNhanVien;
	private JTextField txtTenNhanVien;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JLabel lbDiaChi;
	private JTextField txtDiaChi;
	private JLabel lblTrangThai;
	private JComboBox cbTrangThai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component verticalStrut_4;
	private Component verticalStrut_5;
	private Component verticalStrut_6;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private JPanel PaneTVandDanhSach;
	private JPanel PaneTacVu_1;
	private Box verticalBox_1;
	private Box horizontalBox_1;
	private JLabel lbChonTuKhoa;
	private JComboBox cbTuKhoa;
	private JLabel lbTuKhoaTimKiem;
	private JTextField txtTuKhoaTimKiem;
	private JButton btnTimKiem;
	private Component horizontalStrut_7;
	private Component horizontalStrut_8;
	private Component horizontalStrut_9;
	private Component horizontalStrut_10;
	private Component verticalStrut_7;
	private Component verticalStrut_8;
	private JScrollPane PaneDanhSach;
	private JTable table;
	private DefaultTableModel modelTable;
	private JScrollPane scrollPane;
	private Box verticalBox;
	private Box horizontalBox;
	private JButton btnChonAnh;
	private JLabel lbImageNV;
	private Component verticalStrut_1;
	private Component verticalStrut_9;
	private Component verticalStrut;
	private Component horizontalStrut_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QuanLyNhanVien frame = new GD_QuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_QuanLyNhanVien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		setJMenuBar(menuBar);

		MenuHeThong = new JMenu("Hệ thống");
		MenuHeThong.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuHeThong);

		MenuItemTrangChu = new JMenuItem("Trang chủ");
		MenuHeThong.add(MenuItemTrangChu);

		MenuItemTaiKhoan = new JMenuItem("Tài khoản ");
		MenuHeThong.add(MenuItemTaiKhoan);

		JMenuItem MenuItemTroGiup = new JMenuItem("Trợ giúp");
		MenuHeThong.add(MenuItemTroGiup);

		MenuItemDangXuat = new JMenuItem("Đăng xuất");
		MenuHeThong.add(MenuItemDangXuat);

		MenuItemThoat = new JMenuItem("Thoát");
		MenuHeThong.add(MenuItemThoat);

		MenuDanhMuc = new JMenu("Danh mục");
		MenuDanhMuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuDanhMuc);

		MenuItemPhong = new JMenuItem("Phòng");
		MenuDanhMuc.add(MenuItemPhong);

		MenuItemNhanVien = new JMenuItem("Nhân viên");
		MenuDanhMuc.add(MenuItemNhanVien);

		MenuItemDichVu = new JMenuItem("Dịch vụ");
		MenuDanhMuc.add(MenuItemDichVu);

		MenuItemKhachHang = new JMenuItem("Khách hàng");
		MenuDanhMuc.add(MenuItemKhachHang);

		MenuItemKhuyenMai = new JMenuItem("Khuyến mãi");
		MenuDanhMuc.add(MenuItemKhuyenMai);

		MenuXuLy = new JMenu("Xử lý");
		MenuXuLy.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuXuLy);

		MenuItemDatPhong = new JMenuItem("Đặt phòng");
		MenuItemDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuXuLy.add(MenuItemDatPhong);

		MenuThongKe = new JMenu("Thống kê");
		MenuThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuThongKe);

		MenuItemTKDoanhThu = new JMenuItem("Thống kê doanh thu");
		MenuItemTKDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuThongKe.add(MenuItemTKDoanhThu);

		MenuTroGiup = new JMenu("Trợ giúp");
		MenuTroGiup.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuTroGiup);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(new Color(97, 250, 254));
		contentPane.add(TitlePanel, BorderLayout.NORTH);
		TitlePanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lbTitle = new JLabel("Quản lý nhân viên");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		TitlePanel.add(lbTitle);

		JPanel ContentPanel = new JPanel();
		contentPane.add(ContentPanel, BorderLayout.CENTER);
		ContentPanel.setLayout(new BorderLayout(0, 10));

		JPanel PaneThongTin = new JPanel();
		PaneThongTin.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nh\u1EADp th\u00F4ng tin",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ContentPanel.add(PaneThongTin, BorderLayout.NORTH);
		PaneThongTin.setLayout(new BorderLayout(0, 0));

		PaneThongTinText = new JPanel();
		PaneThongTin.add(PaneThongTinText, BorderLayout.CENTER);
		PaneThongTinText.setLayout(new BoxLayout(PaneThongTinText, BoxLayout.X_AXIS));

		horizontalStrut = Box.createHorizontalStrut(20);
		PaneThongTinText.add(horizontalStrut);

		BoxVerticalThongTin_1 = Box.createVerticalBox();
		PaneThongTinText.add(BoxVerticalThongTin_1);

		verticalStrut_2 = Box.createVerticalStrut(20);
		BoxVerticalThongTin_1.add(verticalStrut_2);

		horizontalBox_4 = Box.createHorizontalBox();
		BoxVerticalThongTin_1.add(horizontalBox_4);

		lbMaNhanVien = new JLabel("Mã nhân viên:");
		lbMaNhanVien.setPreferredSize(new Dimension(110, 30));
		lbMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(lbMaNhanVien);

		txtMaNhanVien = new JTextField();
		lbMaNhanVien.setLabelFor(txtMaNhanVien);
		horizontalBox_4.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_2);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setPreferredSize(new Dimension(100, 30));
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(lbSDT);

		txtSDT = new JTextField();
		lbSDT.setLabelFor(txtSDT);
		horizontalBox_4.add(txtSDT);
		txtSDT.setColumns(10);

		verticalStrut_5 = Box.createVerticalStrut(20);
		BoxVerticalThongTin_1.add(verticalStrut_5);

		horizontalBox_5 = Box.createHorizontalBox();
		BoxVerticalThongTin_1.add(horizontalBox_5);

		lbTenNhanVien = new JLabel("Tên nhân viên:");
		lbTenNhanVien.setPreferredSize(new Dimension(110, 30));
		lbTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_5.add(lbTenNhanVien);

		txtTenNhanVien = new JTextField();
		lbTenNhanVien.setLabelFor(txtTenNhanVien);
		horizontalBox_5.add(txtTenNhanVien);
		txtTenNhanVien.setColumns(10);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_3);

		lbEmail = new JLabel("Email:");
		lbEmail.setPreferredSize(new Dimension(100, 30));
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_5.add(lbEmail);

		txtEmail = new JTextField();
		lbEmail.setLabelFor(txtEmail);
		horizontalBox_5.add(txtEmail);
		txtEmail.setColumns(10);

		verticalStrut_6 = Box.createVerticalStrut(20);
		BoxVerticalThongTin_1.add(verticalStrut_6);

		horizontalBox_6 = Box.createHorizontalBox();
		BoxVerticalThongTin_1.add(horizontalBox_6);

		lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setPreferredSize(new Dimension(110, 30));
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_6.add(lbDiaChi);

		txtDiaChi = new JTextField();
		lbDiaChi.setLabelFor(txtDiaChi);
		horizontalBox_6.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_4);

		lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setPreferredSize(new Dimension(90, 30));
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_6.add(lblTrangThai);

		cbTrangThai = new JComboBox();
		cbTrangThai.setModel(new DefaultComboBoxModel());
		lblTrangThai.setLabelFor(cbTrangThai);
		horizontalBox_6.add(cbTrangThai);

		verticalStrut_4 = Box.createVerticalStrut(20);
		BoxVerticalThongTin_1.add(verticalStrut_4);

		horizontalBox_7 = Box.createHorizontalBox();
		BoxVerticalThongTin_1.add(horizontalBox_7);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(GD_QuanLyNhanVien.class.getResource("/image/icon/add_icon.png")));
		btnThem.setBackground(new Color(107, 208, 107));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_7.add(btnThem);

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_7.add(horizontalStrut_5);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon(GD_QuanLyNhanVien.class.getResource("/image/icon/update_icon.png")));
		btnCapNhat.setBackground(new Color(107, 208, 107));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_7.add(btnCapNhat);

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_7.add(horizontalStrut_6);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(GD_QuanLyNhanVien.class.getResource("/image/icon/clear_icon.png")));
		btnXoaTrang.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_7.add(btnXoaTrang);

		verticalStrut_3 = Box.createVerticalStrut(20);
		BoxVerticalThongTin_1.add(verticalStrut_3);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		PaneThongTinText.add(horizontalStrut_1);

		PaneThongTinImage = new JPanel();
		PaneThongTin.add(PaneThongTinImage, BorderLayout.EAST);
		PaneThongTinImage.setLayout(new BoxLayout(PaneThongTinImage, BoxLayout.X_AXIS));

		verticalBox = Box.createVerticalBox();
		PaneThongTinImage.add(verticalBox);

		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		lbImageNV = new JLabel("");
		lbImageNV.setIcon(new ImageIcon(GD_QuanLyNhanVien.class.getResource("/image/icon/user_icon.png")));
		lbImageNV.setMaximumSize(new Dimension(500, 200));
		lbImageNV.setHorizontalAlignment(SwingConstants.CENTER);
		lbImageNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbImageNV.setAlignmentX(0.5f);
		verticalBox.add(lbImageNV);

		verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setIcon(new ImageIcon(GD_QuanLyNhanVien.class.getResource("/image/icon/import_icon.png")));
		btnChonAnh.setBackground(new Color(107, 208, 107));
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(btnChonAnh);

		verticalStrut_9 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_9);

		horizontalStrut_11 = Box.createHorizontalStrut(20);
		PaneThongTinImage.add(horizontalStrut_11);

		PaneTVandDanhSach = new JPanel();
		ContentPanel.add(PaneTVandDanhSach, BorderLayout.CENTER);
		PaneTVandDanhSach.setLayout(new BorderLayout(0, 10));

		PaneTacVu_1 = new JPanel();
		PaneTacVu_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ch\u1ECDn t\u00E1c v\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PaneTVandDanhSach.add(PaneTacVu_1, BorderLayout.NORTH);
		PaneTacVu_1.setLayout(new BoxLayout(PaneTacVu_1, BoxLayout.X_AXIS));

		verticalBox_1 = Box.createVerticalBox();
		PaneTacVu_1.add(verticalBox_1);

		verticalStrut_7 = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut_7);

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_1);

		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_7);

		lbChonTuKhoa = new JLabel("Chọn từ khóa:");
		lbChonTuKhoa.setPreferredSize(new Dimension(115, 30));
		lbChonTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lbChonTuKhoa);

		cbTuKhoa = new JComboBox();
		cbTuKhoa.setModel(new DefaultComboBoxModel(new String[] { "Vô hiệu", "Hiệu lực" }));
		horizontalBox_1.add(cbTuKhoa);

		horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_9);

		lbTuKhoaTimKiem = new JLabel("Từ khóa cần tìm:");
		lbTuKhoaTimKiem.setPreferredSize(new Dimension(120, 30));
		lbTuKhoaTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lbTuKhoaTimKiem);

		txtTuKhoaTimKiem = new JTextField();
		txtTuKhoaTimKiem.setColumns(10);
		horizontalBox_1.add(txtTuKhoaTimKiem);

		horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_10);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon(GD_QuanLyNhanVien.class.getResource("/image/icon/search_icon.png")));
		btnTimKiem.setBackground(new Color(107, 208, 107));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(btnTimKiem);

		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_8);

		verticalStrut_8 = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut_8);

		String row[] = { "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Địa chỉ", "Trạng thái" };
		modelTable = new DefaultTableModel(row, 0);
		table = new JTable(modelTable);
		scrollPane = new JScrollPane(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		PaneTVandDanhSach.add(scrollPane, BorderLayout.CENTER);
		String ss[] = { "NV1", "Vũ Quốc Huy", "0334405617", "vuquochuy.01012003@gmail.com", "Khánh Hòa", "Còn làm" };
		modelTable.addRow(ss);

	}

}
