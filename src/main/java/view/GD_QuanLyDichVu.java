package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

import dao.DichVuDAO;
import entity.DichVu;

import javax.swing.border.EtchedBorder;

public class GD_QuanLyDichVu extends JFrame {

	private JTextField txtID;
	private JTextField txtQuantity;
	private JTextField txtName;
	private JButton btnChoose;
	private JPanel pnNorth;
	private JPanel pnCenter;
	private Box formVerticalBox;
	private Box firstFormHorizontalBox;
	private JTextField textField;
	private JLabel lblTitle;
	private JPanel pnFirstForm;
	private JLabel lblID;
	private JLabel lblName;
	private JPanel pnTable;
	private JPanel pnSearch;
	private DefaultTableModel modelTable;
	private JTable table;
	private JScrollPane scrollPane;
	private List<DichVu> ds;
	private DichVuDAO dichVuList = new DichVuDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_QuanLyDichVu frame = new GD_QuanLyDichVu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GD_QuanLyDichVu() throws IOException {
		initGUI();
	}

	private void initGUI() throws IOException {
		setupFrame();

		addMenuBar();

		addPanelNorth();

		addPanelCenter();

	}

	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setBackground(new Color(255, 255, 255));
	}

	private void addPanelNorth() {
		pnNorth = new JPanel();
		pnNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnNorth.setBackground(new Color(97, 250, 204));
		getContentPane().add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblTitle = new JLabel("Quản lý dịch vụ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnNorth.add(lblTitle);

	}

	private void addPanelCenter() {
		pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnCenter, BorderLayout.CENTER);

		addForm();
	}

	private void addForm() {
		pnCenter.setLayout(new BorderLayout(0, 0));
		pnFirstForm = new JPanel();
		pnFirstForm.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnFirstForm.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnFirstForm, BorderLayout.NORTH);
		pnFirstForm.setLayout(new BorderLayout(0, 0));

		JPanel pnInfo = new JPanel();
		pnInfo.setBackground(new Color(255, 255, 255));
		pnFirstForm.add(pnInfo, BorderLayout.CENTER);
		pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.X_AXIS));
		Box verticalBox;

		pnInfo.add(Box.createHorizontalStrut(20));

		formVerticalBox = Box.createVerticalBox();
		formVerticalBox.setBackground(new Color(255, 255, 255));
		pnInfo.add(formVerticalBox);

		formVerticalBox.add(Box.createVerticalStrut(20));

		Box horizontalBox;
		firstFormHorizontalBox = Box.createHorizontalBox();
		firstFormHorizontalBox.setBackground(new Color(255, 255, 255));
		formVerticalBox.add(firstFormHorizontalBox);

		JLabel lblID = new JLabel("Mã dịch vụ:");
		lblID.setPreferredSize(new Dimension(150, 20));
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstFormHorizontalBox.add(lblID);

		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setColumns(10);
		firstFormHorizontalBox.add(txtID);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		firstFormHorizontalBox.add(horizontalStrut);

		lblName = new JLabel("Tên dịch vụ:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setPreferredSize(new Dimension(150, 20));
		firstFormHorizontalBox.add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstFormHorizontalBox.add(txtName);

		formVerticalBox.add(Box.createVerticalStrut(20));

		Box secondFormHorizontalBox = Box.createHorizontalBox();
		secondFormHorizontalBox.setBackground(new Color(255, 255, 255));
		formVerticalBox.add(secondFormHorizontalBox);

		JLabel lblQuantity = new JLabel("Số lượng:");
		lblQuantity.setPreferredSize(new Dimension(150, 20));
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		secondFormHorizontalBox.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secondFormHorizontalBox.add(txtQuantity);

		secondFormHorizontalBox.add(Box.createHorizontalStrut(20));

		JLabel lblType = new JLabel("Trạng thái:");
		lblType.setPreferredSize(new Dimension(150, 20));
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		secondFormHorizontalBox.add(lblType);

		JComboBox cbType = new JComboBox();
		cbType.setPreferredSize(new Dimension(247, 20));
		cbType.setMaximumRowCount(2);
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secondFormHorizontalBox.add(cbType);

		formVerticalBox.add(Box.createVerticalStrut(20));

		Box thirdFormHorizontalBox = Box.createHorizontalBox();
		thirdFormHorizontalBox.setBackground(new Color(255, 255, 255));
		formVerticalBox.add(thirdFormHorizontalBox);

		JButton btnAdd = new JButton("Thêm");
		btnAdd.setBackground(new Color(107, 208, 107));
		btnAdd.setAlignmentX(1.0f);
		btnAdd.setAlignmentY(0.0f);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		thirdFormHorizontalBox.add(btnAdd);

		JButton btnUpdate = new JButton("Sửa");
		btnUpdate.setBackground(new Color(107, 208, 107));
		btnUpdate.setAlignmentY(0.0f);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(50));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		thirdFormHorizontalBox.add(btnUpdate);

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(50));

		JButton btnDetele = new JButton("Xóa Trắng");
		btnDetele.setBackground(new Color(107, 208, 107));
		btnDetele.setAlignmentY(0.0f);
		btnDetele.setFont(new Font("Tahoma", Font.BOLD, 14));
		thirdFormHorizontalBox.add(btnDetele);

		pnInfo.add(Box.createHorizontalStrut(20));

		JPanel pnImageInfo = new JPanel();
		pnImageInfo.setBackground(new Color(255, 255, 255));
		pnFirstForm.add(pnImageInfo, BorderLayout.EAST);
		pnImageInfo.setLayout(new BoxLayout(pnImageInfo, BoxLayout.X_AXIS));

		verticalBox = Box.createVerticalBox();
		pnImageInfo.add(verticalBox);

		verticalBox.add(Box.createVerticalStrut(20));

		JLabel lbImageStaff = new JLabel("");
		lbImageStaff.setMaximumSize(new Dimension(500, 200));
		lbImageStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lbImageStaff.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbImageStaff.setAlignmentX(0.5f);
		verticalBox.add(lbImageStaff);

		verticalBox.add(Box.createVerticalStrut(20));

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		btnChoose = new JButton("Chọn ảnh");
		btnChoose.setBackground(new Color(107, 208, 107));
		btnChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(btnChoose);

		verticalBox.add(Box.createVerticalStrut(20));

		pnImageInfo.add(Box.createHorizontalStrut(20));

		JPanel pnSecondForm = new JPanel();
		pnSecondForm.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnSecondForm, BorderLayout.CENTER);
		pnSecondForm.setLayout(new BorderLayout(0, 0));

		pnSearch = new JPanel();
		pnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnSearch.setBorder(new TitledBorder(null, "Tác vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearch.setBackground(new Color(255, 255, 255));
		pnSecondForm.add(pnSearch, BorderLayout.NORTH);
		pnSearch.setLayout(new BoxLayout(pnSearch, BoxLayout.X_AXIS));

		Box centerForm = Box.createVerticalBox();
		pnSearch.add(centerForm);

		centerForm.add(Box.createVerticalStrut(20));

		Box searchHorizontalBox = Box.createHorizontalBox();
		centerForm.add(searchHorizontalBox);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		JLabel lblSelectKey = new JLabel("Chọn từ khóa");
		lblSelectKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchHorizontalBox.add(lblSelectKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		JComboBox cbSelectKey = new JComboBox();
		cbSelectKey.setPreferredSize(new Dimension(200, 22));
		cbSelectKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchHorizontalBox.add(cbSelectKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		JLabel lblInputKey = new JLabel("Nhâp từ khóa:");
		lblInputKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchHorizontalBox.add(lblInputKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(5, 20));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		searchHorizontalBox.add(textField);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBackground(new Color(107, 208, 107));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchHorizontalBox.add(btnSearch);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		centerForm.add(Box.createVerticalStrut(20));

		String[] headers = { "STT", "Mã dịch vụ", "Tên dịch vụ", "Giá", "Số lượng", "Mã loại dịch vụ", "Trạng thái" };
		modelTable = new DefaultTableModel(headers, 0);
		table = new JTable(modelTable);
		table.setBackground(new Color(255, 255, 255));
		table.setPreferredSize(new Dimension(460, 0));
		table.setMinimumSize(new Dimension(90, 0));
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setFont(new Font("Tahoma", Font.BOLD, 24));
		table.setDefaultEditor(Object.class, null);
		pnSecondForm.add(scrollPane, BorderLayout.CENTER);

		loadData();

//		btnAdd.addActionListener(this);

	}

	public void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuHeThong = new JMenu("Hệ thống");
		menuBar.add(menuHeThong);

		JMenu menuDanhMuc = new JMenu("Danh mục");
		menuBar.add(menuDanhMuc);

		JMenu menuXuLy = new JMenu("Xử lý");
		menuBar.add(menuXuLy);

		JMenu menuThongKe = new JMenu("Thống kê");
		menuBar.add(menuThongKe);

		JMenu menuTroGiup = new JMenu("Trợ giúp");
		menuBar.add(menuTroGiup);
	}

	private void loadData() {
		try {
			List<DichVu> list = dichVuList.getAllDichVu();
			int i = 0;
			modelTable.setRowCount(0);
			for (DichVu s : list) {
				String[] row = { (i++ + 1) + "", s.getMaDichVu(), s.getTenDichVu(), s.getGia().getGia() + "",
						s.getSoLuong() + "", s.getLoaiDichVu().getMaLoaiDichVu(), s.getTrangThai() + "", };
				System.out.println(row[1] +' '+row[2]);
				modelTable.addRow(row);
			}
			modelTable.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		table.setModel(modelTable);
	}
}
