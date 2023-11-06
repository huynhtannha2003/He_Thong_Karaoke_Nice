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
import javax.swing.border.LineBorder;

public class GD_QuanLyKhachHang extends JFrame {

	private JPanel contentPane;
	private JTextField txtkMaKhachHang;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTable table;
	private JTextField txtMaTimKiem;
	private JTextField txtTenTimKiem;
	private JLabel lbTenKH;
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
	private JLabel lbMaKhachHang;
	private JTextField txtMaKhachHang;
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
	private JMenuItem MenuItemTrangChu;
	private JMenuItem MenuItemTaiKhoan;
	private JMenuItem MenuItemDangXuat;
	private JMenuItem MenuItemThoat;
	private Component verticalStrut_5;
	private Component horizontalStrut_2;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public GD_QuanLyKhachHang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1002, 699);
		setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		setJMenuBar(menuBar);

		MenuHeThong = new JMenu("Hệ thống");
		MenuHeThong.setFont(new Font("Dialog", Font.BOLD, 14));
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
		contentPane.setLayout(new BorderLayout(0, 5));

		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(new Color(97, 250, 254));
		contentPane.add(TitlePanel, BorderLayout.NORTH);

		JLabel lbTitle = new JLabel("Quản lý khách hàng");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		TitlePanel.add(lbTitle);

		JPanel ContentPanel = new JPanel();
		contentPane.add(ContentPanel);
		ContentPanel.setLayout(new BorderLayout(0, 10));

		JPanel PaneThongTin = new JPanel();
		PaneThongTin.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nh\u1EADp th\u00F4ng tin",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ContentPanel.add(PaneThongTin, BorderLayout.NORTH);
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
		btnThem.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/image/plus 1.png")));
		BoxThongTin3.add(btnThem);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		BoxThongTin3.add(horizontalStrut);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(107, 208, 107));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhat.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/image/pen (1) 1.png")));
		BoxThongTin3.add(btnCapNhat);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		BoxThongTin3.add(horizontalStrut_1);

		btnXoaTrangThongTin = new JButton("Xóa trắng");
		btnXoaTrangThongTin.setBackground(new Color(107, 208, 107));
		btnXoaTrangThongTin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaTrangThongTin.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/image/clear-format 1.png")));
		BoxThongTin3.add(btnXoaTrangThongTin);

		verticalStrut_5 = Box.createVerticalStrut(10);
		BoxVerticalThongTin.add(verticalStrut_5);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		PaneThongTin.add(horizontalStrut_2);

		JPanel ContentPane = new JPanel();
		ContentPanel.add(ContentPane, BorderLayout.CENTER);
		ContentPane.setLayout(new BorderLayout(0, 10));

		JPanel PaneTacVu = new JPanel();
		PaneTacVu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ch\u1ECDn t\u00E1c v\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

		lbTimKiemMa = new JLabel("Nhập mã cần tìm:");
		lbTimKiemMa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTimKiemMa.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxTacVu.add(lbTimKiemMa);

		lbTimKiemMa.setPreferredSize(new Dimension(130, 30));

		txtMaTimKiem = new JTextField();
		lbTimKiemMa.setLabelFor(txtMaTimKiem);
		BoxTacVu.add(txtMaTimKiem);

		Component horizontalStrut_8_1_1_1 = Box.createHorizontalStrut(20);
		BoxTacVu.add(horizontalStrut_8_1_1_1);

		lbTimKiemTen = new JLabel("Nhập tên cần tìm:");
		lbTimKiemTen.setPreferredSize(new Dimension(130, 30));
		lbTimKiemTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTimKiemTen.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxTacVu.add(lbTimKiemTen);

		txtTenTimKiem = new JTextField();
		lbTimKiemTen.setLabelFor(txtTenTimKiem);
		BoxTacVu.add(txtTenTimKiem);

		Component horizontalStrut_9_1_1_1 = Box.createHorizontalStrut(60);
		BoxTacVu.add(horizontalStrut_9_1_1_1);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(107, 208, 107));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/image/plus 1.png")));
		BoxTacVu.add(btnTimKiem);

		Component horizontalStrut_10_1_1_1 = Box.createHorizontalStrut(20);
		BoxTacVu.add(horizontalStrut_10_1_1_1);

		btnXoaTrangTacVu = new JButton("Xóa trắng");
		btnXoaTrangTacVu.setBackground(new Color(107, 208, 107));
		btnXoaTrangTacVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaTrangTacVu.setIcon(new ImageIcon(GD_QuanLyKhachHang.class.getResource("/image/clear-format 1.png")));
		BoxTacVu.add(btnXoaTrangTacVu);

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		BoxVerticalTacVu.add(verticalStrut_4);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		PaneTacVu.add(horizontalStrut_4);

		String row[] = { "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email" };
		modelTable = new DefaultTableModel(row, 0);
		table = new JTable(modelTable);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		ContentPane.add(scrollPane, BorderLayout.CENTER);

		String s[] = { "KH123", "Vũ Quốc Huy", "0334405617", "vuquochuy.01012003@gmail.com" };
		modelTable.addRow(s);

	}

}
