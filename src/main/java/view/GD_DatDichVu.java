package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.FlowLayout;

public class GD_DatDichVu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private JTextField textField_1;
	private JTable table_2;
	private JTextField txtTongTien;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_DatDichVu frame = new GD_DatDichVu();
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
	public GD_DatDichVu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1000, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(97, 250, 204));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Đặt dịch vụ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JPanel info_Pane = new JPanel();
		getContentPane().add(info_Pane, BorderLayout.CENTER);
		info_Pane.setLayout(new BorderLayout(0, 0));
		
		JPanel table_Pane = new JPanel();
		info_Pane.add(table_Pane, BorderLayout.WEST);
		table_Pane.setLayout(new GridLayout(0, 1, 0, 0));
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"STT", "T\u00EAn d\u1ECBch v\u1EE5", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		JScrollPane jp = new JScrollPane(table_2);
		table_Pane.add(jp);
		
		JPanel panel_4 = new JPanel();
		info_Pane.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_6.add(lblNewLabel_1);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(200);
		panel_6.add(horizontalStrut_5);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box verticalBox = Box.createVerticalBox();
		panel_7.add(verticalBox);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblLoiDchV = new JLabel("Loại dịch vụ:");
		lblLoiDchV.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblLoiDchV);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tất cả"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(comboBox);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_3);
		
		JLabel lblTen = new JLabel("Tên dịch vụ:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblTen);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_4);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		horizontalBox.add(textField_2);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(100);
		horizontalBox_1.add(horizontalStrut_6);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(btnSearch);
		
		JPanel panel_8 = new JPanel();
		info_Pane.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnThem);
		
		Component horizontalStrut = Box.createHorizontalStrut(30);
		panel_2.add(horizontalStrut);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnXoa);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		panel_2.add(horizontalStrut_1);
		
		JButton btnReset = new JButton("Làm mới");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnReset);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblTong = new JLabel("Tổng tiền:");
		lblTong.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblTong);
		
		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		JButton btnDat = new JButton("Đặt dịch vụ");
		btnDat.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(btnDat);

	}
}
