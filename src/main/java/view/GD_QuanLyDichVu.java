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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import dao.LichSuGiaDichVuDAO;
import entity.DichVu;
import entity.LichSuGiaDichVu;
import entity.LoaiDichVu;
import enums.TrangThaiDichVu;
import enums.TrangThaiLoaiDichVu;

import javax.swing.border.EtchedBorder;

public class GD_QuanLyDichVu extends JFrame implements ActionListener, MouseListener {

	private JTextField txtID;
	private JTextField txtQuantity;
	private JTextField txtName;
	private JButton btnChoose;
	private JPanel pnNorth;
	private JPanel pnCenter;
	private Box formVerticalBox;
	private Box firstFormHorizontalBox;
	private JTextField txtKey;
	private JLabel lblTitle;
	private JPanel pnFirstForm;
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblType;
	private JPanel pnTable;
	private JButton btnSearch;
	private JPanel pnSearch;
	private DefaultTableModel modelTable;
	private JTable table;
	private JScrollPane scrollPane;
	private List<DichVu> ds;
	private JComboBox cbType;
	private JComboBox cbSelectKey;
	private DichVuDAO dichVuDAO;
	private LichSuGiaDichVuDAO lichSuGiaGiaoDichDAO;
	private Box fourFormHorizontalBox;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel lblLoaiDichVu;
	private JComboBox cbLoaiDichVu;
	private JLabel lblSelectKey;
	private Box searchHorizontalBox;
	private JComboBox cbType2;
	LocalDate current = LocalDate.now();

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
		dichVuDAO = new DichVuDAO();
		lichSuGiaGiaoDichDAO = new LichSuGiaDichVuDAO();
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
				"Thông tin dịch vụ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

		lblID = new JLabel("Mã dịch vụ:");
		lblID.setPreferredSize(new Dimension(150, 20));
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstFormHorizontalBox.add(lblID);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setColumns(10);
		firstFormHorizontalBox.add(txtID);

		firstFormHorizontalBox.add(Box.createHorizontalStrut(20));

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

		lblType = new JLabel("Trạng thái:");
		lblType.setPreferredSize(new Dimension(150, 20));
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		secondFormHorizontalBox.add(lblType);

		cbType = new JComboBox();
		cbType.setPreferredSize(new Dimension(245, 20));
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secondFormHorizontalBox.add(cbType);

		formVerticalBox.add(Box.createVerticalStrut(20));

		Box thirdFormHorizontalBox = Box.createHorizontalBox();
		thirdFormHorizontalBox.setBackground(new Color(255, 255, 255));
		formVerticalBox.add(thirdFormHorizontalBox);

		lblPrice = new JLabel("Giá:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setPreferredSize(new Dimension(150, 20));
		thirdFormHorizontalBox.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		thirdFormHorizontalBox.add(txtPrice);
		txtPrice.setColumns(10);

		thirdFormHorizontalBox.add(Box.createHorizontalStrut(20));

		lblLoaiDichVu = new JLabel("Loại dịch vụ:");
		lblLoaiDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoaiDichVu.setPreferredSize(new Dimension(150, 20));
		thirdFormHorizontalBox.add(lblLoaiDichVu);

		cbLoaiDichVu = new JComboBox();
		cbLoaiDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbLoaiDichVu.setPreferredSize(new Dimension(245, 20));
		thirdFormHorizontalBox.add(cbLoaiDichVu);

		formVerticalBox.add(Box.createVerticalStrut(20));

		fourFormHorizontalBox = Box.createHorizontalBox();
		formVerticalBox.add(fourFormHorizontalBox);

		btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBackground(new Color(107, 208, 107));
		fourFormHorizontalBox.add(btnAdd);

		fourFormHorizontalBox.add(Box.createHorizontalStrut(50));

		btnUpdate = new JButton("Sửa");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBackground(new Color(107, 208, 107));
		fourFormHorizontalBox.add(btnUpdate);

		fourFormHorizontalBox.add(Box.createHorizontalStrut(50));

		btnDelete = new JButton("Xóa Trắng");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBackground(new Color(107, 208, 107));

		fourFormHorizontalBox.add(btnDelete);

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

		searchHorizontalBox = Box.createHorizontalBox();
		centerForm.add(searchHorizontalBox);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		lblSelectKey = new JLabel("Chọn từ khóa");
		lblSelectKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchHorizontalBox.add(lblSelectKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		cbSelectKey = new JComboBox();
		cbSelectKey.setPreferredSize(new Dimension(200, 22));
		cbSelectKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchHorizontalBox.add(cbSelectKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		JLabel lblInputKey = new JLabel("Nhâp từ khóa:");
		lblInputKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchHorizontalBox.add(lblInputKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		txtKey = new JTextField();
		txtKey.setPreferredSize(new Dimension(5, 20));
		txtKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKey.setColumns(10);
		searchHorizontalBox.add(txtKey);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBackground(new Color(107, 208, 107));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchHorizontalBox.add(btnSearch);

		searchHorizontalBox.add(Box.createHorizontalStrut(20));

		centerForm.add(Box.createVerticalStrut(20));

		String[] headers = { "STT", "Mã dịch vụ", "Tên dịch vụ", "Giá", "Số lượng", "Tên loại dịch vụ", "Trạng thái" };
		modelTable = new DefaultTableModel(headers, 0);
		table = new JTable(modelTable);
		table.setBackground(new Color(255, 255, 255));
		table.setMinimumSize(new Dimension(90, 0));
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnSecondForm.add(scrollPane, BorderLayout.CENTER);
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnChoose.addActionListener(this);
		table.addMouseListener(this);

		loadData();
		updateComboBoxStatus();
		updateComboBoxType();
		updateComboBoxKey();
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
//		DecimalFormat df = new DecimalFormat("#,##0đ");
		List<DichVu> list = dichVuDAO.getAllDichVu();
		int i = 0;
		modelTable.setRowCount(0);
		for (DichVu s : list) {
			Object[] row = { (++i), s.getMaDichVu(), s.getTenDichVu(), s.getLichSuGiaDichVuList().get(0).getGia(),
					s.getSoLuong(), s.getLoaiDichVu().getTenLoaiDichVu(), s.getTrangThai().getName() };
			modelTable.addRow(row);
		}

	}

	private void updateComboBoxStatus() {
		cbType.addItem(TrangThaiDichVu.VO_HIEU.getName());
		cbType.addItem(TrangThaiDichVu.HIEU_LUC.getName());
	}

	private void updateComboBoxType() {
		List<LoaiDichVu> list = dichVuDAO.getLoaiDichVu();
		for (LoaiDichVu dichVu : list) {
			cbLoaiDichVu.addItem(dichVu);
		}
	}

	private void updateComboBoxKey() {
		String[] s = { "Mã dịch vụ", "Tên dịch vụ", "Giá", "Số lượng", "Tên loại dịch vụ", "Trạng thái" };
		for (String string : s) {
			cbSelectKey.addItem(string);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			addObject();
		}
		if (o.equals(btnDelete)) {
			delete();
		}
		if (o.equals(btnUpdate)) {
			updateObject();
		}
		if (o.equals(btnChoose)) {
			selectImage();
		}
		if (o.equals(btnSearch)) {
//			searchObject();
		}

	}

	private void addObject() {
		DichVu dv = reverSPFromTextFile();
		LichSuGiaDichVu lichSu = rever();
		int i = modelTable.getColumnCount() + 1;
		Object[] row = { i, dv.getMaDichVu(), dv.getTenDichVu(), dv.getLichSuGiaDichVuList().get(0).getGia(),
				dv.getSoLuong(), dv.getLoaiDichVu().getTenLoaiDichVu(), dv.getTrangThai().getName() };
		modelTable.addRow(row);
		dichVuDAO.addDichVu(dv);
		dichVuDAO.addLichSuGiaGiaoDich(lichSu, dv);
		System.out.println("1");
		loadData();
	}

	private DichVu reverSPFromTextFile() {
		TrangThaiDichVu trangThai = TrangThaiDichVu.values()[cbType.getSelectedIndex()];
		LoaiDichVu loaiDichVu = (LoaiDichVu) cbLoaiDichVu.getSelectedItem();
		List<LichSuGiaDichVu> lichSuGiaDichVuList = new ArrayList<>();
		lichSuGiaDichVuList.add(new LichSuGiaDichVu("", Date.valueOf(current), null, Time.valueOf("8:00:00"),
				Time.valueOf("23:00:00"), Double.parseDouble(txtPrice.getText())));
		DichVu dichVu = new DichVu(txtID.getText(), txtName.getText(), Integer.parseInt(txtQuantity.getText()),
				trangThai, loaiDichVu, lichSuGiaDichVuList);
		return dichVu;
	}

	private LichSuGiaDichVu rever() {
		LocalDate current = LocalDate.now();
		LichSuGiaDichVu lichSu = new LichSuGiaDichVu("", Date.valueOf(current), null, Time.valueOf("8:00:00"),
				Time.valueOf("23:00:00"), Double.parseDouble(txtPrice.getText()));
		return lichSu;
	}

	private void delete() {
		txtID.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
	}

	private void updateObject() {
		int hangDuocChon = table.getSelectedRow();
		if (hangDuocChon > -1) {
			DichVu dv = reverSPFromTextFile();
			dichVuDAO.updateDichVu(dv, dv.getMaDichVu());
			loadData();
		}
	}

	private void selectImage() {
		JFileChooser f = new JFileChooser();
		f.setDialogTitle("Mở file");
		f.showOpenDialog(null);
		File fileAnh = f.getSelectedFile();
		String strAnh = fileAnh.getAbsolutePath();
	}

//	private void searchObject() {
//		try {
////			loadDataFind();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
////	public void loadDataFind() throws SQLException {
//		List<DichVu> listDichVu = null;
//		String tuKhoa = txtKey.getText();
//		String cbKey = cbSelectKey.getSelectedItem().toString();
//		if (cbKey.equals("Mã dịch vụ")) {
//			listDichVu = dichVuDAO.getDichVuTheoMa(tuKhoa);
//		}
////		if (s.equals("Tên dịch vụ")) {
////			listDichVu = dichVuDAO.getDSDichVuTheoTen(tuKhoa);
////		}
////		if (s.equals("Giá")) {
////			listDichVu = dichVuDAO.getDSDichVuTheoGia(tuKhoa);
////		}
////		if (s.equals("Số lượng")) {
////			listDichVu = dichVuDAO.getDSDichVuTheoSoLuong(tuKhoa);
////		}
////		if (s.equals("Tên loại dịch vụ")) {
////			listDichVu = dichVuDAO.getDSDichVuTheoTenLoai(tuKhoa);
////		}
////		if (s.equals("Trạng thái")) {
////			searchHorizontalBox.remove(txtKey);
////			searchHorizontalBox.add(cbType);
////		}
////			listDichVu = dichVuDAO.getDSDichVuTheoTrangThai(tuKhoa);
////		}
//		modelTable.setRowCount(0);
//		int i = modelTable.getRowCount() + 1;
//		for (DichVu s : listDichVu) {
//			Object[] row = { i, s.getMaDichVu(), s.getTenDichVu(), s.getLichSuGiaDichVuList().get(0).getGia(),
//					s.getSoLuong(), s.getLoaiDichVu().getTenLoaiDichVu(), s.getTrangThai().getName() };
//			modelTable.addRow(row);
//		}
//	}
	private String getColumnName(String selectedKey) {
		switch (selectedKey) {
		case "Mã dịch vụ":
			return "maDichVu";
		case "Tên dịch vụ":
			return "tenDichVu";
		case "Giá":
			return "gia";
		case "Số lượng":
			return "soLuong";
		case "Tên loại dịch vụ":
			return "tenLoaiDichVu";
		case "Trạng thái":
			return "trangThai";
		default:
			return null; // Return null if the key is not recognized
		}
	}

//	private void search() {
//		String selectedKey = cbSelectKey.getSelectedItem().toString();
//		String inputValue = txtKey.getText().toString();
//		if (selectedKey.isEmpty() || inputValue.isEmpty()) {
//			return;
//		}
//
//		String columnName = getColumnName(selectedKey);
//		try {
//			// gọi hàm dao ra 
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int rowSelect = table.getSelectedRow();
		txtID.setText(modelTable.getValueAt(rowSelect, 1).toString());
		txtName.setText(modelTable.getValueAt(rowSelect, 2).toString());
		txtPrice.setText(modelTable.getValueAt(rowSelect, 3).toString());
		txtQuantity.setText(modelTable.getValueAt(rowSelect, 4).toString());
		cbLoaiDichVu.setSelectedItem(modelTable.getValueAt(rowSelect, 5).toString());
		cbType.setSelectedItem(modelTable.getValueAt(rowSelect, 6).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

//	private int taoMaDichVu() {
//		List<DichVu> dv = dichVuDAO.getDichVu();
//		int i = 0;
//		while (i < dv.size()) {
//			if(Integer.parseInt(dv.get(i).))
//		}
//	}
//	
//	public static String tạoMaLichSuGiaDichVu() {
//		int sequenceNumber;
//        // Lấy ngày hiện tại
//        Date currentDate = new Date();
//
//        // Định dạng ngày tháng năm
//        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
//
//        // Tạo chuỗi DDMMYY từ ngày hiện tại
//        String datePart = dateFormat.format(currentDate);
//
//        // Tạo chuỗi số thứ tự XXX
//        String sequencePart = String.format("%03d", sequenceNumber);
//
//        // Tăng số thứ tự để sử dụng cho lần sau
//        sequenceNumber++;
//
//        // Tạo mã phát sinh
//        String generatedCode = "GDV." + datePart + "." + sequencePart;
//
//        return generatedCode;
//    }
}
