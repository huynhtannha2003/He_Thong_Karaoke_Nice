package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.PhieuDatPhongDAO;
import entity.PhieuDatPhong;
import entity.Phong;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ProcessHandle.Info;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;

public class GD_HuyDatPhongCho extends JPanel implements ActionListener {
	private DefaultTableModel model;
	private JButton btnXoaPhieu;
	private JButton btnXoaTatCa;
	private JButton btnThoat;
	private JTable table;
	private PhieuDatPhongDAO pdpDAO;
	private String maHoaDonSelected;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;

	public GD_HuyDatPhongCho() {
		setBackground(new Color(255, 255, 255));
		pdpDAO = new PhieuDatPhongDAO();
		setSize(1000, 700);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		String row[] = { "STT", "Mã phiếu", "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "Số điện thoại",
				"Thời gian bắt đầu" };
		model = new DefaultTableModel(row, 0);
		setLayout(new BorderLayout(0, 0));
		JPanel PaneButton = new JPanel();
		PaneButton.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) PaneButton.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(30);
		add(PaneButton, BorderLayout.SOUTH);
		btnXoaPhieu = new JButton("Xóa phiếu");
		btnXoaPhieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaPhieu.setBackground(new Color(107, 208, 107));
		PaneButton.add(btnXoaPhieu);

		btnXoaTatCa = new JButton("Xóa tất cả");
		btnXoaTatCa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaTatCa.setBackground(new Color(107, 208, 107));
		PaneButton.add(btnXoaTatCa);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThoat.setBackground(new Color(107, 208, 107));
		PaneButton.add(btnThoat);

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(97, 250, 204));
		add(pnTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Hủy đặt phòng chờ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTitle.add(lblTitle);

		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		add(pnCenter);
		pnCenter.setLayout(new BorderLayout(0, 0));
		JPanel PanelInfo = new JPanel();
		PanelInfo.setBackground(new Color(255, 255, 255));
		pnCenter.add(PanelInfo, BorderLayout.NORTH);
		PanelInfo.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin t\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelInfo.setLayout(new BoxLayout(PanelInfo, BoxLayout.X_AXIS));

		PanelInfo.add(Box.createHorizontalStrut(30));

		Box verticalBox = Box.createVerticalBox();
		PanelInfo.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox);

		JLabel lbSDTTimKiem = new JLabel("Số điện thoại:");
		lbSDTTimKiem.setPreferredSize(new Dimension(110, 30));
		lbSDTTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(lbSDTTimKiem);

		txtTimKiem = new JTextField("0345678912");
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(107, 208, 107));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBox.add(btnTimKiem);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(50);
		PanelInfo.add(horizontalStrut_1);
		table = new JTable();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		pnCenter.add(scrollPane);
		initAction();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaPhieu)) {
			if (maHoaDonSelected != null) {
				pdpDAO.xoaPhieuDatPhongCho(maHoaDonSelected);
				JOptionPane.showMessageDialog(null, "Hủy đặt phòng chờ thành công!");
			} else {
				JOptionPane.showMessageDialog(this, "Hãy chọn một phòng để hủy", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
			TimKiem();
		} else if (o.equals(btnXoaTatCa)) {
			xoaTatCaPhieu();
			TimKiem();
		} else if (o.equals(btnThoat)) {
			JOptionPane.getRootFrame().dispose();
		} else if (o.equals(btnTimKiem)) {
			TimKiem();
		}

	}

	public void initAction() {
		btnThoat.addActionListener(this);
		btnXoaPhieu.addActionListener(this);
		btnXoaTatCa.addActionListener(this);
		btnTimKiem.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					maHoaDonSelected = table.getValueAt(row, 2).toString();
				}
			}
		});
	}

	public void xoaTatCaPhieu() {
		List<String> pdpList = new ArrayList();
		int n = table.getRowCount();
		for (int i = 0; i < n; i++) {
			pdpDAO.xoaPhieuDatPhongCho(table.getValueAt(i, 2).toString());
		}
		JOptionPane.showMessageDialog(null, "Hủy đặt phòng chờ thành công!");
	}

	public void TimKiem() {
		model.setRowCount(0);
		List<String[]> list = pdpDAO.timKiemPhieuDatPhong(txtTimKiem.getText());
		for (String[] s : list) {
			model.addRow(s);
		}
	}
}