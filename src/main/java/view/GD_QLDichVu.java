package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.TextArea;
import javax.swing.JMenuItem;
import java.awt.Insets;

public class GD_QLDichVu {

	JFrame frame;
	private JTable table;
	private JTextField txtMaDichVu;
	private JTextField txtSoLuong;
	private JTextField txtTenDichVu;
	private JTextField textField_1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public GD_QLDichVu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1321, 696);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
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
		
		Component verticalStrut = Box.createVerticalStrut(40);
		verticalBox.add(verticalStrut);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut);

		JLabel lblMaDichVu = new JLabel("Mã dịch vụ:");
		lblMaDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblMaDichVu);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		txtMaDichVu = new JTextField();
		txtMaDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaDichVu.setColumns(10);
		horizontalBox.add(txtMaDichVu);
		
		horizontalBox.add(Box.createHorizontalStrut(50));
		
		JLabel lblTenDichVu = new JLabel("Tên dịch vụ:");
		lblTenDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblTenDichVu);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(30);
		horizontalBox.add(horizontalStrut_4);
		
		txtTenDichVu = new JTextField();
		txtTenDichVu.setColumns(10);
		txtTenDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(txtTenDichVu);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_8);
		
		Component verticalStrut_3 = Box.createVerticalStrut(40);
		verticalBox.add(verticalStrut_3);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(50);
		horizontalBox_2.add(horizontalStrut_1);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_2.add(lblSoLuong);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		horizontalBox_2.add(horizontalStrut_3);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_2.add(txtSoLuong);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(50);
		horizontalBox_2.add(horizontalStrut_9);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_2.add(lblTrangThai);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(37);
		horizontalBox_2.add(horizontalStrut_10);
		
		JComboBox cbxTrangThai = new JComboBox();
		cbxTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hiệu lực ", "Vô hiệu"}));
		cbxTrangThai.setMaximumRowCount(2);
		cbxTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_2.add(cbxTrangThai);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(310);
		horizontalBox_2.add(horizontalStrut_5);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_4);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(400);
		horizontalBox_4.add(horizontalStrut_13);

		JButton btnThem = new JButton("Thêm");
		btnThem.setAlignmentX(1.0f);
		btnThem.setAlignmentY(0.0f);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnThem);

		JButton btnSua = new JButton("Sửa");
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
		btnXoaTrang.setAlignmentY(0.0f);
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnXoaTrang);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(100);
		Info_Pane.add(horizontalStrut_6);
		
		Box verticalBox_1 = Box.createVerticalBox();
		Info_Pane.add(verticalBox_1);
		
		JButton btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		verticalBox_1.add(btnChonAnh);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(50);
		Info_Pane.add(horizontalStrut_7);
		
		JPanel Pane = new JPanel();
		contentPane.add(Pane, BorderLayout.CENTER);
		Pane.setLayout(new BorderLayout(0, 0));
		
		JPanel search_Pane = new JPanel();
		search_Pane.setBorder(new TitledBorder(null, "Chọn tác vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Pane.add(search_Pane, BorderLayout.NORTH);
		search_Pane.setLayout(new BoxLayout(search_Pane, BoxLayout.X_AXIS));
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(50);
		search_Pane.add(horizontalStrut_14);
		
		Box verticalBox_2 = Box.createVerticalBox();
		search_Pane.add(verticalBox_2);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_1);
		
		JLabel lblTimMa = new JLabel("Chọn từ khóa:");
		lblTimMa.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lblTimMa);
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_18);
		
		JComboBox cbxTimtrangthai = new JComboBox();
		cbxTimtrangthai.setModel(new DefaultComboBoxModel(new String[] {"Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Trạng thái"}));
		cbxTimtrangthai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_1.add(cbxTimtrangthai);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_20);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập từ khóa:");
		horizontalBox_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_19);
		
		textField_1 = new JTextField();
		horizontalBox_1.add(textField_1);
		textField_1.setColumns(10);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(50);
		horizontalBox_1.add(horizontalStrut_16);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox_2.add(verticalStrut_2);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		verticalBox_2.add(horizontalBox_3);
		
		Component horizontalStrut_21 = Box.createHorizontalStrut(700);
		horizontalStrut_21.setEnabled(false);
		horizontalBox_3.add(horizontalStrut_21);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		horizontalBox_3.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(40);
		horizontalBox_3.add(horizontalStrut_17);
		
		JButton btnXoaTrang_1 = new JButton("Xóa Trắng");
		btnXoaTrang_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_3.add(btnXoaTrang_1);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(40);
		search_Pane.add(horizontalStrut_15);
		
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

}
