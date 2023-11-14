package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Scrollbar;

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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.DichVu;
import entity.LoaiDichVu;
import enums.TrangThaiDichVu;
import enums.TrangThaiLoaiDichVu;
import utils.RoomPanelUtil;

import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class GD_DatDichVu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtServiceName;
	private JTable tableOrderedServices;
	private JTextField textField;
	private List<DichVu> listDichVu = new ArrayList<>();
	
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

	public GD_DatDichVu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(97, 255, 204));
		getContentPane().add(pnTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Đặt dịch vụ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTitle.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblRoomName = new JLabel("Phòng 003");
		lblRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoomName.setBounds(10, 10, 96, 25);
		panel.add(lblRoomName);
		
		JLabel lblServiceType = new JLabel("Loại dịch vụ");
		lblServiceType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblServiceType.setBounds(369, 10, 96, 25);
		panel.add(lblServiceType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(475, 12, 85, 25);
		panel.add(comboBox);
		
		JLabel lblServiceName = new JLabel("Tên dịch vụ:");
		lblServiceName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblServiceName.setBounds(570, 10, 96, 25);
		panel.add(lblServiceName);
		
		txtServiceName = new JTextField();
		txtServiceName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtServiceName.setBounds(664, 10, 162, 25);
		panel.add(txtServiceName);
		txtServiceName.setColumns(10);
		
		JButton btnFind = new JButton("Tìm kiếm");
		btnFind.setBackground(new Color(107, 208, 107));
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFind.setBounds(858, 10, 117, 25);
		panel.add(btnFind);
		
		tableOrderedServices = new JTable();
		tableOrderedServices.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Tên dịch vụ", "Số lượng", "Thành tiền"
			}
		));
		JScrollPane spOrderedServices = new JScrollPane(tableOrderedServices);
		spOrderedServices.setBounds(10, 58, 327, 479);
		panel.add(spOrderedServices);

//		initData();
		
		JPanel services = new JPanel();
		JScrollPane spServices = new JScrollPane(services);
		JScrollBar verticalScrollbar = spServices.getVerticalScrollBar();
		verticalScrollbar.setUnitIncrement(16);
		services.setLayout(new GridLayout(0, 3, 0, 0));
		spServices.setBounds(369, 58, 606, 479);
		panel.add(spServices);
		
		List<JPanel> dichVuPanel = RoomPanelUtil.createDichVuPanels(listDichVu);
		dichVuPanel.forEach(services::add);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setBackground(new Color(107, 208, 107));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(10, 571, 94, 25);
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBackground(new Color(221, 78, 78));
		btnDelete.setBounds(123, 571, 94, 25);
		panel.add(btnDelete);
		
		JButton btnClearAll = new JButton("Xóa tất cả");
		btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClearAll.setBackground(new Color(57, 130, 240));
		btnClearAll.setBounds(243, 571, 94, 25);
		panel.add(btnClearAll);
		
		JLabel lblTotalPrice = new JLabel("Tổng tiền:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalPrice.setBounds(511, 573, 82, 25);
		panel.add(lblTotalPrice);
		
		textField = new JTextField();
		textField.setBounds(620, 572, 185, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnApply = new JButton("Đặt dịch vụ");
		btnApply.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnApply.setBackground(new Color(107, 208, 107));
		btnApply.setBounds(855, 570, 120, 25);
		panel.add(btnApply);
	}
//	
//	public void initData() {
//		LoaiDichVu loaiDichVu = new LoaiDichVu("001", "Loại Dịch Vụ 1", TrangThaiLoaiDichVu.HIEU_LUC);
//	    for (int i = 0; i < 10; i++) {
//	        listDichVu.add(new DichVu("00" + (i + 1), "Dịch Vụ " + (i + 1), 5, TrangThaiDichVu.HIEU_LUC, loaiDichVu, 1));
//	    }
//	}
}
