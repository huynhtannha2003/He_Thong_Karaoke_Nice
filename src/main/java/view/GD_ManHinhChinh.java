package view;

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
		menuHeThong.add(menuItemTaiKhoan);

		menuItemTroGiup = new JMenuItem("Trợ giúp");
		menuItemTroGiup.setFont(new Font("Tahoma", Font.BOLD, 14));
		menuHeThong.add(menuItemTroGiup);

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

		lbBackGroundKaraoke = new JLabel("");
		lbBackGroundKaraoke.setIcon(new ImageIcon(GD_ManHinhChinh.class.getResource("/image/icon/ManHinhChinh.png")));
		lbBackGroundKaraoke.setBounds(0, 0, 984, 588);
		contentPane.add(lbBackGroundKaraoke);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == menuItemTrangChu) {

		} else if (source == menuItemTaiKhoan) {
			JOptionPane.showMessageDialog(null, "Tài Khoản Clicked");
		} else if (source == menuItemTroGiup) {
			JOptionPane.showMessageDialog(null, "Trợ Giúp Clicked");
		} else if (source == menuItemDangXuat) {
			JOptionPane.showMessageDialog(null, "Đăng Xuất Clicked");
		} else if (source == menuItemThoat) {
			JOptionPane.showMessageDialog(null, "Thoát Clicked");
		} else if (source == menuItemPhong) {
			JOptionPane.showMessageDialog(null, "Phòng Clicked");
		} else if (source == menuItemNhanVien) {
			JOptionPane.showMessageDialog(null, "Nhân Viên Clicked");
		} else if (source == menuItemHoaDon) {
		}
	}
}
