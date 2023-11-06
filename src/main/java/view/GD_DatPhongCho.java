package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.BoxLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

public class GD_DatPhongCho extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtSDT;
	private JTextField txtKH;
	private JTextField txtTenPhong;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_DatPhongCho frame = new GD_DatPhongCho();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_DatPhongCho() {
		setBackground(new Color(255, 255, 255));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		JPanel panelTieuDe = new JPanel();
		panelTieuDe.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTieuDe.setBackground(new Color(97, 250, 204));
		getContentPane().add(panelTieuDe, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("Đặt phòng chờ ");
		lblTieuDe.setBackground(new Color(97, 250, 204));
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTieuDe.add(lblTieuDe);
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel info_Pane = new JPanel();
		info_Pane.setBackground(new Color(255, 255, 255));
		contentPane.add(info_Pane, BorderLayout.NORTH);
		info_Pane.setLayout(new BoxLayout(info_Pane, BoxLayout.X_AXIS));
		
		info_Pane.add(Box.createHorizontalStrut(30));
		
		Box verticalBox = Box.createVerticalBox();
		info_Pane.add(verticalBox);
		
		verticalBox.add(Box.createVerticalStrut(10));
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblSDT = new JLabel("Số điện thoại khách hàng:");
		lblSDT.setHorizontalAlignment(SwingConstants.CENTER);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lblSDT);
		
		horizontalBox.add(Box.createHorizontalStrut(20));
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(txtSDT);
		txtSDT.setColumns(10);
		
		horizontalBox.add(Box.createHorizontalStrut(30));
		
		JButton btnCheck = new JButton("Kiểm tra");
		btnCheck.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(btnCheck);
		
		horizontalBox.add(Box.createHorizontalStrut(30));
		
		verticalBox.add(Box.createVerticalStrut(20));
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JLabel lblKH = new JLabel("Khách hàng:");
		lblKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_1.add(lblKH);
		
		horizontalBox_1.add(Box.createHorizontalStrut(111));
		
		txtKH = new JTextField();
		txtKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_1.add(txtKH);
		txtKH.setColumns(10);
		
		horizontalBox_1.add(Box.createHorizontalStrut(150));
		
		verticalBox.add(Box.createVerticalStrut(20));
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_2.add(lblTenPhong);
		
		horizontalBox_2.add(Box.createHorizontalStrut(50));
		
		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_2.add(txtTenPhong);
		txtTenPhong.setColumns(10);
		
		horizontalBox_2.add(Box.createHorizontalStrut(80));
		
		JLabel lblLoaiPhong = new JLabel("Loại phòng:");
		lblLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_2.add(lblLoaiPhong);
		
		horizontalBox_2.add(Box.createHorizontalStrut(80));
		
		JComboBox cbxLoaiPhong = new JComboBox();
		cbxLoaiPhong.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Vip", "Thường"}));
		cbxLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_2.add(cbxLoaiPhong);
		
		horizontalBox_2.add(Box.createHorizontalStrut(100));
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_2.add(btnSearch);
		
		horizontalBox_2.add(Box.createHorizontalStrut(30));
		
		JPanel panel_Table = new JPanel();
		panel_Table.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_Table);
		
		JPanel panel_Bottom = new JPanel();
		panel_Bottom.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_Bottom, BorderLayout.SOUTH);
		panel_Bottom.setLayout(new BorderLayout(0, 0));
		
		Box verticalBox_1 = Box.createVerticalBox();
		panel_Bottom.add(verticalBox_1);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_3);
		
		horizontalBox_3.add(Box.createHorizontalStrut(30));
		
		JLabel lblNhanPhong = new JLabel("Nhận phòng:");
		lblNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_3.add(lblNhanPhong);
		
		horizontalBox_3.add(Box.createHorizontalStrut(30));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Hôm nay");
		rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_3.add(rdbtnNewRadioButton);
		
		horizontalBox_3.add(Box.createHorizontalStrut(30));
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Ngày khác");
		rdbtnNewRadioButton_1.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_3.add(rdbtnNewRadioButton_1);
		
		horizontalBox_3.add(Box.createHorizontalStrut(300));
		
		JDateChooser dateChooser = new JDateChooser();
		horizontalBox_3.add(dateChooser);
		
		JPanel panel = new JPanel();
		horizontalBox_3.add(panel);
		
		horizontalBox_3.add(Box.createHorizontalStrut(50));
		
		verticalBox_1.add(Box.createVerticalStrut(30));
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_4);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(30);
		horizontalBox_4.add(horizontalStrut_11);
		
		JLabel lblTime = new JLabel("Thời gian nhận phòng:");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(lblTime);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(50);
		horizontalBox_4.add(horizontalStrut_16);
		
		JComboBox cbxHours = new JComboBox();
		cbxHours.setModel(new DefaultComboBoxModel(new String[] {"1 ", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cbxHours.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_4.add(cbxHours);
		
		JLabel lblNewLabel = new JLabel("Giờ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_4.add(lblNewLabel);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(50);
		horizontalBox_4.add(horizontalStrut_17);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_4.add(comboBox_1);
		
		JLabel lblMin = new JLabel("Phút");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox_4.add(lblMin);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(50);
		horizontalBox_4.add(horizontalStrut_20);
		
		JButton btnXacNhan = new JButton("Xác nhận\r\n");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnXacNhan);
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(30);
		horizontalBox_4.add(horizontalStrut_19);
		
		JButton btnIn = new JButton("In phiếu đặt\r\n");
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox_4.add(btnIn);
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(30);
		horizontalBox_4.add(horizontalStrut_18);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		verticalBox_1.add(panel_1);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_4);
		
		
	}
}
