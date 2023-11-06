package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class GD_QLDichVu {

	JFrame frame;
	private JTable table;
	private JTextField txtMaDichVu;
	private JTextField txtSoLuong;
	private JTextField txtTenDichVu;
	private JTextField textField_1;
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QLDichVu window = new GD_QLDichVu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_QLDichVu() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
		
		addHeaderPanel();
        addInputPanel();
        addDataTablePanel();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setToolTipText("");
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setJMenuBar(menuBar);

		JMenu mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setFont(new Font("Tahoma", Font.BOLD, 15));
		mnHeThong.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnHeThong);
		
		JMenuItem mntmTrangChu = new JMenuItem("Trang chủ");
		mntmTrangChu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mnHeThong.add(mntmTrangChu);
		
		JMenuItem mntmTaikhoan = new JMenuItem("Tài khoản");
		mntmTaikhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mnHeThong.add(mntmTaikhoan);
		
		JMenuItem mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mnHeThong.add(mntmDangXuat);
		
		JMenuItem mntmThoat = new JMenuItem("Thoát");
		mntmThoat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mnHeThong.add(mntmThoat);
		
		JMenu mnDanhMuc = new JMenu("Danh Mục");
		mnDanhMuc.setHorizontalAlignment(SwingConstants.CENTER);
		mnDanhMuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnDanhMuc);
		
		JMenu mnXuLy = new JMenu("Xử lý");
		mnXuLy.setHorizontalAlignment(SwingConstants.CENTER);
		mnXuLy.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnXuLy);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnXuLy.add(mntmNewMenuItem);
		
		JMenu mnThongKe = new JMenu("Thống kê");
		mnThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		mnThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnThongKe);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnThongKe.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnThongKe.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnThongKe.add(mntmNewMenuItem_3);
		
		JMenu mnHelp = new JMenu("Trợ giúp");
		mnHelp.setHorizontalAlignment(SwingConstants.CENTER);
		mnHelp.setFont(new Font("Tahoma", Font.BOLD, 15));
		menuBar.add(mnHelp);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(97, 250, 204));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTieuDe = new JLabel("Quản lý dịch vụ");
		panel.add(lblTieuDe);
		lblTieuDe.setForeground(new Color(0, 0, 0));
		lblTieuDe.setBackground(new Color(97, 250, 204));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));

		JPanel contentPane = new JPanel();
		contentPane.setBorder(
				new TitledBorder(null, "Nh\u1EADp th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(contentPane, BorderLayout.CENTER);

		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Info_Pane = new JPanel();
		contentPane.add(Info_Pane, BorderLayout.NORTH);
		Info_Pane.setLayout(new BoxLayout(Info_Pane, BoxLayout.X_AXIS));

		Box verticalBox = Box.createVerticalBox();
		Info_Pane.add(verticalBox);
		
		verticalBox.add(Box.createVerticalStrut(40));

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		horizontalBox.add(Box.createHorizontalStrut(50));

		JLabel lblMaDichVu = new JLabel("Mã dịch vụ:");
		lblMaDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblMaDichVu);
		
		horizontalBox.add(Box.createHorizontalStrut(20));

		txtMaDichVu = new JTextField();
		txtMaDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaDichVu.setColumns(10);
		horizontalBox.add(txtMaDichVu);
		
		horizontalBox.add(Box.createHorizontalStrut(50));
		
		JLabel lblTenDichVu = new JLabel("Tên dịch vụ:");
		lblTenDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblTenDichVu);
		
		horizontalBox.add(Box.createHorizontalStrut(20));
		
		txtTenDichVu = new JTextField();
		txtTenDichVu.setColumns(10);
		txtTenDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(txtTenDichVu);
		
		horizontalBox.add(Box.createHorizontalStrut(50));
		
		verticalBox.add(Box.createVerticalStrut(40));

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		horizontalBox_1.add(Box.createHorizontalStrut(50));

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lblSoLuong);
		
		horizontalBox_1.add(Box.createHorizontalStrut(20));

		txtSoLuong = new JTextField();
		txtSoLuong.setPreferredSize(txtMaDichVu.getPreferredSize());
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_1.add(txtSoLuong);
		
		horizontalBox_1.add(Box.createHorizontalStrut(50));
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lblTrangThai);
		
		horizontalBox_1.add(Box.createHorizontalStrut(20));
		
		JComboBox cbxTrangThai = new JComboBox();
		cbxTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hiệu lực ", "Vô hiệu"}));
		cbxTrangThai.setMaximumRowCount(2);
		cbxTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_1.add(cbxTrangThai);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(160);
		horizontalBox_1.add(horizontalStrut_5);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_4);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(400);
		horizontalBox_4.add(horizontalStrut_13);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(107, 208, 107));
		btnThem.setAlignmentX(1.0f);
		btnThem.setAlignmentY(0.0f);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(107, 208, 107));
		btnSua.setAlignmentY(0.0f);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(50);
		horizontalBox_4.add(horizontalStrut_12);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnSua);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(50);
		horizontalBox_4.add(horizontalStrut_11);

		JButton btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBackground(new Color(107, 208, 107));
		btnXoaTrang.setAlignmentY(0.0f);
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnXoaTrang);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(100);
		Info_Pane.add(horizontalStrut_6);
		
		Box verticalBox_1 = Box.createVerticalBox();
		Info_Pane.add(verticalBox_1);
		
		JButton btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setBackground(new Color(107, 208, 107));
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		verticalBox_1.add(btnChonAnh);
		
		Info_Pane.add(Box.createHorizontalStrut(50));
		
		JPanel Pane = new JPanel();
		contentPane.add(Pane, BorderLayout.CENTER);
		Pane.setLayout(new BorderLayout(0, 0));
		
		JPanel search_Pane = new JPanel();
		search_Pane.setBorder(new TitledBorder(null, "Chọn tác vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Pane.add(search_Pane, BorderLayout.NORTH);
		search_Pane.setLayout(new BoxLayout(search_Pane, BoxLayout.X_AXIS));
		
		search_Pane.add(Box.createHorizontalStrut(50));
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.add(Box.createVerticalStrut(20));
		search_Pane.add(verticalBox_2);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_2);
		
		JLabel lblTimMa = new JLabel("Chọn từ khóa:");
		lblTimMa.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_2.add(lblTimMa);
		
		horizontalBox_2.add(Box.createHorizontalStrut(20));
		
		JComboBox cbxTimtrangthai = new JComboBox();
		cbxTimtrangthai.setModel(new DefaultComboBoxModel(new String[] {"Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Trạng thái"}));
		cbxTimtrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_2.add(cbxTimtrangthai);
		
		horizontalBox_2.add(Box.createHorizontalStrut(20));
		
		JLabel lblNewLabel_2 = new JLabel("Nhập từ khóa:");
		horizontalBox_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		horizontalBox_2.add(Box.createHorizontalStrut(20));
		
		textField_1 = new JTextField();
		horizontalBox_2.add(textField_1);
		textField_1.setColumns(10);
		
		horizontalBox_2.add(Box.createHorizontalStrut(50));
		
		verticalBox_2.add(Box.createVerticalStrut(20));
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(107, 208, 107));
		horizontalBox_2.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		search_Pane.add(Box.createHorizontalStrut(40));
		
		JPanel table_Pane = new JPanel();
		Pane.add(table_Pane, BorderLayout.CENTER);
		table_Pane.setLayout(new GridLayout(1, 0, 0, 0));
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 d\u1ECBch v\u1EE5", "T\u00EAn d\u1ECBch v\u1EE5", "S\u1ED1 l\u01B0\u1EE3ng", "Tr\u1EA1ng th\u00E1i"
			}
		));
		JScrollPane jp = new JScrollPane(table_1);
		jp.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		table_Pane.add(jp);
		
	}

	private void addDataTablePanel() {
		// TODO Auto-generated method stub
		
	}

	private void addInputPanel() {
		// TODO Auto-generated method stub
		
	}

	private void addHeaderPanel() {
		// TODO Auto-generated method stub
		
	}

}
