package view;

import entity.NhanVien;
import enums.TrangThaiNhanVien;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GD_ManHinhChinh extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menuHeThong;
	private JMenu menuDanhMuc;
	private JMenuItem menuItemPhong;
	private JMenuItem menuItemNhanVien;
	private JMenuItem menuItemDichVu;
	private JMenuItem menuItemKhachHang;
	private JMenuItem menuItemKhuyenMai;
	private JMenu menuXuLy;
	private JMenuItem menuItemDatPhong;
	private JMenu menuThongKe;
	private JMenuItem menuItemTKDoanhThu;
	private JPanel contentPane;
	private JMenuItem menuItemThoat;
	private JMenuItem menuItemDangXuat;
	private JMenuItem menuItemTaiKhoan;
	private JMenuItem menuItemTrangChu;
	private JLabel lbBackGroundKaraoke;
	private JMenuItem menuItemTroGiup;
	private JMenuItem menuItemHoaDon;

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

	public GD_ManHinhChinh() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 14));
		setJMenuBar(menuBar);

		menuHeThong = new JMenu("Hệ thống");
		menuHeThong.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(menuHeThong);

		menuItemTrangChu = new JMenuItem("Trang chủ");
		menuItemTrangChu.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuHeThong.add(menuItemTrangChu);

		menuItemTaiKhoan = new JMenuItem("Tài khoản ");
		menuItemTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 14));
//        menuHeThong.add(menuItemTaiKhoan);

		menuItemTroGiup = new JMenuItem("Trợ giúp");
		menuItemTroGiup.setFont(new Font("Tahoma", Font.BOLD, 14));
//        menuHeThong.add(menuItemTroGiup);

		menuItemDangXuat = new JMenuItem("Đăng xuất");
		menuItemDangXuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuHeThong.add(menuItemDangXuat);

		menuItemThoat = new JMenuItem("Thoát");
		menuItemThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuHeThong.add(menuItemThoat);

		menuDanhMuc = new JMenu("Danh mục");
		menuDanhMuc.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(menuDanhMuc);

		menuItemPhong = new JMenuItem("Phòng");
		menuItemPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuDanhMuc.add(menuItemPhong);

		menuItemNhanVien = new JMenuItem("Nhân viên");
		menuItemNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuDanhMuc.add(menuItemNhanVien);

		menuItemDichVu = new JMenuItem("Dịch vụ");
		menuItemDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuDanhMuc.add(menuItemDichVu);

		menuItemKhachHang = new JMenuItem("Khách hàng");
		menuItemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuDanhMuc.add(menuItemKhachHang);

		menuItemKhuyenMai = new JMenuItem("Khuyến mãi");
		menuItemKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuDanhMuc.add(menuItemKhuyenMai);

		menuItemHoaDon = new JMenuItem("Hóa đơn");
		menuItemHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuDanhMuc.add(menuItemHoaDon);

		menuXuLy = new JMenu("Xử lý");
		menuXuLy.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(menuXuLy);

		menuItemDatPhong = new JMenuItem("Đặt phòng");
		menuItemDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuXuLy.add(menuItemDatPhong);

		menuThongKe = new JMenu("Thống kê");
		menuThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuBar.add(menuThongKe);

		menuItemTKDoanhThu = new JMenuItem("Thống kê doanh thu");
		menuItemTKDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuThongKe.add(menuItemTKDoanhThu);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		init();

		menuItemTrangChu.addActionListener(this);
		menuItemTaiKhoan.addActionListener(this);
		menuItemTroGiup.addActionListener(this);
		menuItemDangXuat.addActionListener(this);
		menuItemThoat.addActionListener(this);
		menuItemPhong.addActionListener(this);
		menuItemNhanVien.addActionListener(this);
		menuItemDichVu.addActionListener(this);
		menuItemKhachHang.addActionListener(this);
		menuItemKhuyenMai.addActionListener(this);
		menuItemDatPhong.addActionListener(this);
		menuItemTKDoanhThu.addActionListener(this);
		menuItemHoaDon.addActionListener(this);
	}

	private void init() {
		lbBackGroundKaraoke = new JLabel("");
		lbBackGroundKaraoke.setIcon(new ImageIcon(getClass().getResource("/image/icon/ManHinhChinh.png")));
		lbBackGroundKaraoke.setBounds(0, 0, 984, 588);
		contentPane.add(lbBackGroundKaraoke);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		JPanel simplePanel = null;
		if (source == menuItemTrangChu) {
			setContentPane(contentPane);
			return;
		} else if (source == menuItemTaiKhoan) {
			JOptionPane.showMessageDialog(null, "Tài Khoản Clicked");
		} else if (source == menuItemTroGiup) {
			JOptionPane.showMessageDialog(null, "Trợ Giúp Clicked");
		} else if (source == menuItemDangXuat) {
			GD_Login gdLogin = new GD_Login();
			gdLogin.setVisible(true);
			setVisible(false);
			return;
		} else if (source == menuItemThoat) {
			setVisible(false);
		} else if (source == menuItemPhong) {
			simplePanel = new GD_QuanLyPhong();
		} else if (source == menuItemNhanVien) {
			simplePanel = new GD_QuanLyNhanVien();
		} else if (source == menuItemHoaDon) {
			simplePanel = new GD_QuanLyHoaDon();
		} else if (source == menuItemKhachHang) {
			simplePanel = new GD_QuanLyKhachHang();
		} else if (source == menuItemKhuyenMai) {
			simplePanel = new GD_QuanLyKhuyenMai();
		} else if (source == menuItemDichVu) {
			simplePanel = new GD_QuanLyDichVu();
		} else if (source == menuThongKe) {

		} else if (source == menuItemDatPhong) {
			simplePanel = new GD_QuanLyDatPhong(
					new NhanVien("NV230001", "", "", "", "", "", TrangThaiNhanVien.HIEU_LUC, null));
		}
		setContentPane(simplePanel);
		validate();
		repaint();
	}
}
