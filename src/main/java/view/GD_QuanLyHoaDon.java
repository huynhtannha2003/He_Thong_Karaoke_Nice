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
import javax.swing.border.LineBorder;
import utils.*;

public class GD_QuanLyHoaDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaHoaDon;
	private JTextField txtTenKhachHang;
	private JLabel lbNgayBatDau;
	private JMenuBar menuBar;
	private JMenu MenuHeThong;
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
	private JLabel lbMaHoaDon;
	private JTextField txtMaKhachHang;
	private JLabel lbTenKhachHang;
	private JButton btnTimKiem;
	private JButton btnXuatHoaDon;
	private JButton btnXoaTrang;
	private DefaultTableModel modelTable;
	private JLabel lbNgayKetThuc;
	private JDateChooser txtNgayKetThuc;
	private JDateChooser txtNgayBatDau;
	private Component horizontalStrut_2;
	private Component horizontalStrut_6;
	private JScrollPane scrollPane;
	private JTable table;
	private Component verticalStrut_3;
	private Component horizontalStrut_3;
	private JMenuItem MenuItemDangXuat;
	private JMenuItem MenuItemThoat;
	private JMenuItem MenuItemTrangChu;
	private JMenuItem MenuItemTaiKhoan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QuanLyHoaDon frame = new GD_QuanLyHoaDon();
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
	public GD_QuanLyHoaDon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1002, 699);
		setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		setJMenuBar(menuBar);

		MenuHeThong = new JMenu("Hệ thống");
		MenuHeThong.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuHeThong);

		MenuItemTrangChu = new JMenuItem("Trang chủ");
		MenuItemTrangChu.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuHeThong.add(MenuItemTrangChu);

		MenuItemTaiKhoan = new JMenuItem("Tài khoản ");
		MenuItemTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuHeThong.add(MenuItemTaiKhoan);

		JMenuItem MenuItemTroGiup = new JMenuItem("Trợ giúp");
		MenuItemTroGiup.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuHeThong.add(MenuItemTroGiup);

		MenuItemDangXuat = new JMenuItem("Đăng xuất");
		MenuItemDangXuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuHeThong.add(MenuItemDangXuat);

		MenuItemThoat = new JMenuItem("Thoát");
		MenuItemThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuHeThong.add(MenuItemThoat);

		MenuDanhMuc = new JMenu("Danh mục");
		MenuDanhMuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(MenuDanhMuc);

		MenuItemPhong = new JMenuItem("Phòng");
		MenuItemPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuDanhMuc.add(MenuItemPhong);

		MenuItemNhanVien = new JMenuItem("Nhân viên");
		MenuItemNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuDanhMuc.add(MenuItemNhanVien);

		MenuItemDichVu = new JMenuItem("Dịch vụ");
		MenuItemDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuDanhMuc.add(MenuItemDichVu);

		MenuItemKhachHang = new JMenuItem("Khách hàng");
		MenuItemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		MenuDanhMuc.add(MenuItemKhachHang);

		MenuItemKhuyenMai = new JMenuItem("Khuyến mãi");
		MenuItemKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(new Color(97, 250, 254));
		contentPane.add(TitlePanel, BorderLayout.NORTH);

		JLabel lbTitle = new JLabel("Quản lý hóa đơn");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		TitlePanel.add(lbTitle);

		JPanel ContentPanel = new JPanel();
		contentPane.add(ContentPanel);
		ContentPanel.setLayout(new BorderLayout(0, 20));

		JPanel PaneThongTin = new JPanel();
		PaneThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContentPanel.add(PaneThongTin, BorderLayout.NORTH);
		PaneThongTin.setLayout(new BoxLayout(PaneThongTin, BoxLayout.X_AXIS));

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		PaneThongTin.add(horizontalStrut_6);

		Box BoxVerticalThongTin = Box.createVerticalBox();
		PaneThongTin.add(BoxVerticalThongTin);

		Component verticalStrut = Box.createVerticalStrut(20);
		BoxVerticalThongTin.add(verticalStrut);

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

		txtMaHoaDon = new JTextField();
		lbMaHoaDon.setLabelFor(txtMaHoaDon);
		BoxThongTin1.add(txtMaHoaDon);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		BoxThongTin1.add(horizontalStrut_5);

		lbTenKhachHang = new JLabel("Tên khách hàng:");
		lbTenKhachHang.setPreferredSize(new Dimension(130, 30));
		lbTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoxThongTin1.add(lbTenKhachHang);

		txtTenKhachHang = new JTextField();
		lbTenKhachHang.setLabelFor(txtTenKhachHang);
		BoxThongTin1.add(txtTenKhachHang);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		BoxVerticalThongTin.add(verticalStrut_1);

		Box BoxThongTin2 = Box.createHorizontalBox();
		BoxVerticalThongTin.add(BoxThongTin2);

		lbNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbNgayBatDau.setHorizontalAlignment(SwingConstants.LEFT);
		BoxThongTin2.add(lbNgayBatDau);

		txtNgayBatDau = new JDateChooser();
		lbNgayBatDau.setLabelFor(txtNgayBatDau);
		BoxThongTin2.add(txtNgayBatDau);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		BoxThongTin2.add(horizontalStrut_2);

		lbNgayKetThuc = new JLabel("Đến ngày:");
		lbNgayKetThuc.setPreferredSize(new Dimension(130, 30));
		lbNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoxThongTin2.add(lbNgayKetThuc);

		txtNgayKetThuc = new JDateChooser();
		lbNgayKetThuc.setLabelFor(txtNgayKetThuc);
		BoxThongTin2.add(txtNgayKetThuc);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		BoxVerticalThongTin.add(verticalStrut_2);

		Box BoxThongTin3 = Box.createHorizontalBox();
		BoxThongTin3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		BoxVerticalThongTin.add(BoxThongTin3);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(107, 208, 107));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setIcon(new ImageIcon(GD_QuanLyHoaDon.class.getResource("/image/icon/search_icon.png")));
		BoxThongTin3.add(btnTimKiem);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		BoxThongTin3.add(horizontalStrut);

		btnXuatHoaDon = new JButton("Xuất hóa đơn");
		btnXuatHoaDon.setBackground(new Color(107, 208, 107));
		btnXuatHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXuatHoaDon.setIcon(new ImageIcon(GD_QuanLyHoaDon.class.getResource("/image/icon/print_icon.png")));
		BoxThongTin3.add(btnXuatHoaDon);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		BoxThongTin3.add(horizontalStrut_1);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaTrang.setIcon(new ImageIcon(GD_QuanLyHoaDon.class.getResource("/image/icon/clear_icon.png")));
		BoxThongTin3.add(btnXoaTrang);

		verticalStrut_3 = Box.createVerticalStrut(20);
		BoxVerticalThongTin.add(verticalStrut_3);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		PaneThongTin.add(horizontalStrut_3);

		String row[] = { "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Email" };
		modelTable = new DefaultTableModel(row, 0);
		table = new JTable(modelTable);
		scrollPane = new JScrollPane(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		ContentPanel.add(scrollPane, BorderLayout.CENTER);

		String s[] = { "HD001", "Vũ Quốc Huy", "0334405617", "vuquochuy.01012003@gmail.com" };
		modelTable.addRow(s);

	}

}
