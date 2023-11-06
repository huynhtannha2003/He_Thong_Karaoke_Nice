package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import utils.*;

public class GD_ManHinhChinh extends JFrame {

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
	private JPanel contentPane;
	private JMenuItem MenuItemThoat;
	private JMenuItem MenuItemDangXuat;
	private JMenuItem MenuItemTaiKhoan;
	private JMenuItem MenuItemTrangChu;
	private JLabel lbBackGroundKaraoke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_ManHinhChinh frame = new GD_ManHinhChinh();
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
	public GD_ManHinhChinh() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 650);

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
		contentPane.setLayout(null);

		lbBackGroundKaraoke = new JLabel("");
		lbBackGroundKaraoke.setIcon(new ImageIcon(GD_ManHinhChinh.class.getResource("/image/icon/ManHinhChinh.png")));
		lbBackGroundKaraoke.setBounds(0, 0, 984, 588);
		contentPane.add(lbBackGroundKaraoke);
	}

}
