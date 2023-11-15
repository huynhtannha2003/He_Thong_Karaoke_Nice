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

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import entity.NhanVien;
import enums.TrangThaiNhanVien;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import utils.*;
import java.awt.event.MouseEvent;
import java.io.File;

public class GD_QuanLyNhanVien extends JFrame implements ActionListener {

	private List<NhanVien> list = null;
	private NhanVienDAO daoNV;
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
	private Box BoxVerticalThongTin;
	private Box BoxMaAndSDT;
	private Box BoxTenAndEmail;
	private Box horizontalBox_6;
	private Box horizontalBox_7;
	private JPanel PaneThongTinImage;
	private JTextField txtMaNhanVien;
	private JTextField txtSDT;
	private JTextField txtTenNhanVien;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JComboBox cbTrangThai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JPanel PaneTVandDanhSach;
	private JPanel PaneTacVu_1;
	private Box verticalBox_1;
	private Box horizontalBox_1;
	private JComboBox cbTuKhoa;
	private JTextField txtTuKhoaTimKiem;
	private JButton btnTimKiem;
	private JScrollPane PaneDanhSach;
	private JTable table;
	private DefaultTableModel modelTable;
	private JScrollPane scrollPane;
	private Box verticalBox;
	private Box horizontalBox;
	private JButton btnChonAnh;
	private JLabel lbImageNV;

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
	 * 
	 * @throws SQLException
	 */
	public GD_QuanLyNhanVien() throws SQLException {
		ConnectDB.getInstance().connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		daoNV = new NhanVienDAO();
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

		PaneThongTinText.add(Box.createHorizontalStrut(20));

		BoxVerticalThongTin = Box.createVerticalBox();
		PaneThongTinText.add(BoxVerticalThongTin);

		BoxVerticalThongTin.add(Box.createVerticalStrut(20));

		BoxMaAndSDT = Box.createHorizontalBox();
		BoxVerticalThongTin.add(BoxMaAndSDT);

		JLabel lbMaNhanVien = new JLabel("Mã nhân viên:");
		lbMaNhanVien.setPreferredSize(new Dimension(110, 30));
		lbMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoxMaAndSDT.add(lbMaNhanVien);

		txtMaNhanVien = new JTextField();
		lbMaNhanVien.setLabelFor(txtMaNhanVien);
		BoxMaAndSDT.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		BoxMaAndSDT.add(Box.createHorizontalStrut(20));

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setPreferredSize(new Dimension(100, 30));
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoxMaAndSDT.add(lbSDT);

		txtSDT = new JTextField();
		lbSDT.setLabelFor(txtSDT);
		BoxMaAndSDT.add(txtSDT);
		txtSDT.setColumns(10);

		BoxVerticalThongTin.add(Box.createVerticalStrut(20));

		BoxTenAndEmail = Box.createHorizontalBox();
		BoxVerticalThongTin.add(BoxTenAndEmail);

		JLabel lbTenNhanVien = new JLabel("Tên nhân viên:");
		lbTenNhanVien.setPreferredSize(new Dimension(110, 30));
		lbTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoxTenAndEmail.add(lbTenNhanVien);

		txtTenNhanVien = new JTextField();
		lbTenNhanVien.setLabelFor(txtTenNhanVien);
		BoxTenAndEmail.add(txtTenNhanVien);
		txtTenNhanVien.setColumns(10);

		BoxTenAndEmail.add(Box.createHorizontalStrut(20));

		JLabel lbEmail = new JLabel("Email:");
		lbEmail.setPreferredSize(new Dimension(100, 30));
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		BoxTenAndEmail.add(lbEmail);

		txtEmail = new JTextField();
		lbEmail.setLabelFor(txtEmail);
		BoxTenAndEmail.add(txtEmail);
		txtEmail.setColumns(10);

		BoxVerticalThongTin.add(Box.createVerticalStrut(20));

		horizontalBox_6 = Box.createHorizontalBox();
		BoxVerticalThongTin.add(horizontalBox_6);

		JLabel lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setPreferredSize(new Dimension(110, 30));
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_6.add(lbDiaChi);

		txtDiaChi = new JTextField();
		lbDiaChi.setLabelFor(txtDiaChi);
		horizontalBox_6.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		horizontalBox_6.add(Box.createHorizontalStrut(20));

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setPreferredSize(new Dimension(90, 30));
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_6.add(lblTrangThai);

		cbTrangThai = new JComboBox<TrangThaiNhanVien>();
		cbTrangThai.setModel(new DefaultComboBoxModel());
		lblTrangThai.setLabelFor(cbTrangThai);
		horizontalBox_6.add(cbTrangThai);

		BoxVerticalThongTin.add(Box.createVerticalStrut(20));

		horizontalBox_7 = Box.createHorizontalBox();
		BoxVerticalThongTin.add(horizontalBox_7);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("/image/icon/add_icon.png"));
		btnThem.setBackground(new Color(107, 208, 107));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_7.add(btnThem);

		horizontalBox_7.add(Box.createHorizontalStrut(20));

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setIcon(new ImageIcon("/image/icon/update_icon.png"));
		btnCapNhat.setBackground(new Color(107, 208, 107));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_7.add(btnCapNhat);

		horizontalBox_7.add(Box.createHorizontalStrut(20));

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon("/image/icon/clear_icon.png"));
		btnXoaTrang.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_7.add(btnXoaTrang);

		BoxVerticalThongTin.add(Box.createVerticalStrut(20));

		PaneThongTinText.add(Box.createHorizontalStrut(20));

		PaneThongTinImage = new JPanel();
		PaneThongTin.add(PaneThongTinImage, BorderLayout.EAST);
		PaneThongTinImage.setLayout(new BoxLayout(PaneThongTinImage, BoxLayout.X_AXIS));

		verticalBox = Box.createVerticalBox();
		PaneThongTinImage.add(verticalBox);

		verticalBox.add(Box.createVerticalStrut(20));

		JLabel lbImageNV = new JLabel();
		lbImageNV.setIcon(new ImageIcon("/image/icon/user_icon.png"));
		lbImageNV.setMaximumSize(new Dimension(500, 200));
		lbImageNV.setHorizontalAlignment(SwingConstants.CENTER);
		lbImageNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbImageNV.setAlignmentX(0.5f);
		verticalBox.add(lbImageNV);

		verticalBox.add(Box.createVerticalStrut(20));

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser Path = new JFileChooser();
				Path.show();
			}
		});
		btnChonAnh.setIcon(new ImageIcon("/image/icon/import_icon.png"));
		btnChonAnh.setBackground(new Color(107, 208, 107));
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(btnChonAnh);

		verticalBox.add(Box.createVerticalStrut(20));

		PaneThongTinImage.add(Box.createHorizontalStrut(20));

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

		verticalBox_1.add(Box.createVerticalStrut(20));

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_1);

		horizontalBox_1.add(Box.createHorizontalStrut(20));

		JLabel lbChonTuKhoa = new JLabel("Chọn từ khóa:");
		lbChonTuKhoa.setPreferredSize(new Dimension(115, 30));
		lbChonTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lbChonTuKhoa);

		cbTuKhoa = new JComboBox();
		cbTuKhoa.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Mã nhân viên", "Tên nhân viên", "Chức vụ",
				"Số điện thoại", "Email", "Địa chỉ", "Trạng thái" }));
		horizontalBox_1.add(cbTuKhoa);

		horizontalBox_1.add(Box.createHorizontalStrut(20));

		JLabel lbTuKhoaTimKiem = new JLabel("Từ khóa cần tìm:");
		lbTuKhoaTimKiem.setPreferredSize(new Dimension(120, 30));
		lbTuKhoaTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lbTuKhoaTimKiem);

		txtTuKhoaTimKiem = new JTextField();
		txtTuKhoaTimKiem.setColumns(10);
		horizontalBox_1.add(txtTuKhoaTimKiem);

		horizontalBox_1.add(Box.createHorizontalStrut(20));

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon("/image/icon/search_icon.png"));
		btnTimKiem.setBackground(new Color(107, 208, 107));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(btnTimKiem);

		horizontalBox_1.add(Box.createHorizontalStrut(20));

		verticalBox_1.add(Box.createVerticalStrut(20));

		String row[] = { "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Số điện thoại", "Email", "Địa chỉ",
				"Trạng thái" };
		modelTable = new DefaultTableModel(row, 0);
		table = new JTable(modelTable);
		scrollPane = new JScrollPane(table);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		PaneTVandDanhSach.add(scrollPane, BorderLayout.CENTER);
		loadData();
		loadcomboBoxTrangThai();
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnChonAnh.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaNhanVien.setText(table.getValueAt(row, 0).toString());
				txtTenNhanVien.setText(table.getValueAt(row, 1).toString());
				txtSDT.setText(table.getValueAt(row, 3).toString());
				txtEmail.setText(table.getValueAt(row, 4).toString());
				txtDiaChi.setText(table.getValueAt(row, 5).toString());
				String ttnv = table.getValueAt(row, 6).toString();
				if (ttnv.trim().equals(TrangThaiNhanVien.VO_HIEU.toString())) {
					cbTrangThai.setSelectedItem(TrangThaiNhanVien.VO_HIEU);
				} else {
					cbTrangThai.setSelectedItem(TrangThaiNhanVien.HIEU_LUC);
				}
			}
		});
		// txtMaNhanVien.setEditable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnThem)) {
			chucNangThem();
		} else if (o.equals(btnCapNhat)) {
			chucNangCapNhat();
		} else if (o.equals(btnTimKiem)) {
			chucNangTimKiem();
		} else if (o.equals(btnChonAnh)) {
			NutChonAnh();
		}
	}

	public void loadData() {
		list = daoNV.getAllNhanVien();
		modelTable.setRowCount(0);
		for (NhanVien nhanVien : list) {
			String[] s = { nhanVien.getMaNhanVien(), nhanVien.getTen(), nhanVien.getChucVu(), nhanVien.getSdt(),
					nhanVien.getEmail(), nhanVien.getDiaChi(), nhanVien.getTrangThai().toString() };
			modelTable.addRow(s);
		}
	}

	public void loadDataFind() throws SQLException {
		String tuKhoa = txtTuKhoaTimKiem.getText();
		String s = cbTuKhoa.getSelectedItem().toString();
		if (s.equals("Tất cả")) {
			list = daoNV.TimKiemTatCa(tuKhoa);
		} else if (s.equals("Mã nhân viên")) {
			list = daoNV.TimKiemTheoMaNhanVien(tuKhoa);
		} else if (s.equals("Tên nhân viên")) {
			list = daoNV.TimKiemTheoTenNhanVien(tuKhoa);
		} else if (s.equals("Chức vụ")) {
			list = daoNV.TimKiemTheoChucVu(tuKhoa);
		} else if (s.equals("Số điện thoại")) {
			list = daoNV.TimKiemTheoSDT(tuKhoa);
		} else if (s.equals("Email")) {
			list = daoNV.TimKiemTheoEmail(tuKhoa);
		} else if (s.equals("Địa chỉ")) {
			list = daoNV.TimKiemTheoDiaChi(tuKhoa);
		} else if (s.equals("Trạng thái")) {
			list = daoNV.TimKiemTheoTrangThai(tuKhoa);
		}
		modelTable.setRowCount(0);
		for (NhanVien nhanVien : list) {
			String[] s1 = { nhanVien.getMaNhanVien(), nhanVien.getTen(), nhanVien.getChucVu(), nhanVien.getSdt(),
					nhanVien.getEmail(), nhanVien.getDiaChi(), nhanVien.getTrangThai().toString() };
			modelTable.addRow(s1);
		}
	}

	public void xoaTrang() {
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtTenNhanVien.setText("");
		txtTuKhoaTimKiem.setText("");
	}

	public void chucNangThem() {
		NhanVien nhanVien = createNhanVien();
		String[] s = { nhanVien.getMaNhanVien(), nhanVien.getTen(), nhanVien.getChucVu(), nhanVien.getSdt(),
				nhanVien.getEmail(), nhanVien.getDiaChi(), nhanVien.getTrangThai().toString() };
		modelTable.addRow(s);
		daoNV.createNhanVien(nhanVien);
	}

	public void chucNangCapNhat() {
		NhanVien nv = createNhanVien();
		daoNV.updateNhanVien(nv, nv.getMaNhanVien());
		loadData();
	}

	public void chucNangTimKiem() {
		try {
			loadDataFind();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public NhanVien createNhanVien() {
		return new NhanVien(txtMaNhanVien.getText(), txtTenNhanVien.getText(), "Nhân viên", txtSDT.getText(),
				txtEmail.getText(), txtDiaChi.getText(), (TrangThaiNhanVien) cbTrangThai.getSelectedItem());
	}

	public void loadcomboBoxTrangThai() {
		cbTrangThai.addItem(TrangThaiNhanVien.HIEU_LUC);
		cbTrangThai.addItem(TrangThaiNhanVien.VO_HIEU);
	}

	public void NutChonAnh() {
		JFileChooser f = new JFileChooser();
		f.setDialogTitle("Mở file");
		f.showOpenDialog(null);
		File fileAnh = f.getSelectedFile();
		String strAnh = fileAnh.getAbsolutePath();
	}
}