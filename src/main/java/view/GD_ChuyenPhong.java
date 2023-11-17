package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.RoomPanelUtil;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;

public class GD_ChuyenPhong extends JDialog {
	private JTextField txtRoomName;
	private JPanel pnCenter;
	private ArrayList rooms;

	public static void main(String[] args) {
		try {
			GD_ChuyenPhong dialog = new GD_ChuyenPhong();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GD_ChuyenPhong() {
		getContentPane().setLayout(new BorderLayout());
		setSize(784, 600);
		setLocationRelativeTo(null);

		addPanelNorth();

		addPanelCenter();
	}

	private void addPanelCenter() {
		pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		addFormPanel();

		addRoomsPanel();

		JPanel pnRoomChange = new JPanel();
		pnRoomChange.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnRoomChange, BorderLayout.SOUTH);
		pnRoomChange.setLayout(new BoxLayout(pnRoomChange, BoxLayout.Y_AXIS));

		pnRoomChange.add(Box.createVerticalStrut(20));

		Box horizontalBoxRoomChange = Box.createHorizontalBox();
		pnRoomChange.add(horizontalBoxRoomChange);

		horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

		JPanel pnCurrentRoom = new JPanel();
		pnCurrentRoom.setBackground(new Color(255, 255, 255));
		horizontalBoxRoomChange.add(pnCurrentRoom);
		pnCurrentRoom.setBorder(BorderFactory.createLineBorder(Color.black));
		pnCurrentRoom.setLayout(new BoxLayout(pnCurrentRoom, BoxLayout.Y_AXIS));

		pnCurrentRoom.add(Box.createVerticalStrut(20));

		JLabel lblCurrentRoomName = new JLabel("Tên phòng");
		lblCurrentRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentRoomName.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnCurrentRoom.add(lblCurrentRoomName);

		pnCurrentRoom.add(Box.createVerticalStrut(20));

		JLabel lblCurrentRoomPrice = new JLabel("Giá phòng:");
		lblCurrentRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentRoomPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnCurrentRoom.add(lblCurrentRoomPrice);

		pnCurrentRoom.add(Box.createVerticalStrut(20));

		JLabel lblCurrentRoomType = new JLabel("Loại phòng:");
		lblCurrentRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentRoomType.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnCurrentRoom.add(lblCurrentRoomType);

		pnCurrentRoom.add(Box.createVerticalStrut(20));

		horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

		JPanel pnFollowRoom = new JPanel();
		pnFollowRoom.setBackground(new Color(255, 255, 255));
		horizontalBoxRoomChange.add(pnFollowRoom);
		pnFollowRoom.setBorder(BorderFactory.createLineBorder(Color.black));
		pnFollowRoom.setLayout(new BoxLayout(pnFollowRoom, BoxLayout.Y_AXIS));

		pnFollowRoom.add(Box.createVerticalStrut(20));

		JLabel lblFollowRoomName = new JLabel("Tên phòng:");
		lblFollowRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFollowRoomName.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnFollowRoom.add(lblFollowRoomName);

		pnFollowRoom.add(Box.createVerticalStrut(20));

		JLabel lblFollowRoomPrice = new JLabel("Giá phòng");
		lblFollowRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFollowRoomPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnFollowRoom.add(lblFollowRoomPrice);

		pnFollowRoom.add(Box.createVerticalStrut(20));

		JLabel lblFollowRoomType = new JLabel("Loại phòng");
		lblFollowRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFollowRoomType.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnFollowRoom.add(lblFollowRoomType);

		pnFollowRoom.add(Box.createVerticalStrut(20));

		horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

		JButton btnApply = new JButton("Xác nhận");
		btnApply.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnApply.setBackground(new Color(107, 208, 107));
		horizontalBoxRoomChange.add(btnApply);
		
		horizontalBoxRoomChange.add(Box.createHorizontalStrut(20));

		pnRoomChange.add(Box.createVerticalStrut(20));
	}

	private void initData() {
		rooms = new ArrayList<>();
		LoaiPhong loaiPhong = new LoaiPhong("001", "Thường", TrangThaiLoaiPhong.HIEU_LUC);
		for (int i = 0; i < 18; i++) {
			rooms.add(new Phong("00" + (i + 1), loaiPhong, "00" + (i + 1), 5, TrangThaiPhong.PHONG_TRONG));
		}
	}

	private void addRoomsPanel() {
		JPanel pnRoomScrollPane = new JPanel();
		pnRoomScrollPane.setBackground(new Color(255, 255, 255));

		JScrollPane scrollPane = new JScrollPane(pnRoomScrollPane);
		pnRoomScrollPane.setLayout(new GridLayout(0, 4, 0, 0));

		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setUnitIncrement(16);

		JPanel pnRooms = new JPanel();
		pnRooms.setBackground(new Color(255, 255, 255));
		pnRooms.setLayout(new BoxLayout(pnRooms, BoxLayout.X_AXIS));

		pnCenter.add(pnRooms, BorderLayout.CENTER);

		Box scrollPaneBox = Box.createVerticalBox();
		scrollPaneBox.add(scrollPane);

		pnRooms.add(Box.createHorizontalStrut(20));

		pnRooms.add(scrollPaneBox);

		scrollPaneBox.add(Box.createVerticalStrut(20));

		pnRooms.add(Box.createHorizontalStrut(20));

		initData();

		List<JPanel> roomPanels = RoomPanelUtil.createPhongPanels(rooms);
//		roomPanels.forEach(pnRoomScrollPane::add);
	}

	private void addFormPanel() {
		JPanel pnForm = new JPanel();
		pnForm.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnForm, BorderLayout.NORTH);
		pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));

		pnForm.add(Box.createVerticalStrut(20));

		Box horizontalBoxForm = Box.createHorizontalBox();
		pnForm.add(horizontalBoxForm);

		horizontalBoxForm.add(Box.createHorizontalStrut(20));

		JLabel lblRoomName = new JLabel("Tên phòng:");
		lblRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBoxForm.add(lblRoomName);

		horizontalBoxForm.add(Box.createHorizontalStrut(20));

		txtRoomName = new JTextField();
		txtRoomName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBoxForm.add(txtRoomName);
		txtRoomName.setColumns(10);

		horizontalBoxForm.add(Box.createHorizontalStrut(20));

		JLabel lblRoomType = new JLabel("Loại phòng:");
		lblRoomType.setFont(new Font("Tahoma", Font.BOLD, 14));
		horizontalBoxForm.add(lblRoomType);

		horizontalBoxForm.add(Box.createHorizontalStrut(20));

		JComboBox cbTypeRoom = new JComboBox();
		cbTypeRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbTypeRoom.setModel(new DefaultComboBoxModel(new String[] { "Tất cả" }));
		horizontalBoxForm.add(cbTypeRoom);

		horizontalBoxForm.add(Box.createHorizontalStrut(20));

		pnForm.add(Box.createVerticalStrut(20));

		JPanel pnButton = new JPanel();
		pnButton.setBackground(new Color(255, 255, 255));
		pnButton.setLayout(new BorderLayout(0, 0));

		JButton btnFind = new JButton("Tìm kiếm");
		btnFind.setBackground(new Color(107, 208, 107));
		pnButton.add(btnFind, BorderLayout.EAST);

		Box horizontalBoxButton = Box.createHorizontalBox();
		pnForm.add(horizontalBoxButton);
		horizontalBoxButton.add(pnButton);

		horizontalBoxButton.add(Box.createHorizontalStrut(20));

		pnForm.add(Box.createVerticalStrut(20));
	}

	private void addPanelNorth() {
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(new Color(97, 250, 204));
		getContentPane().add(pnNorth, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Chuyển phòng");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnNorth.add(lblTitle);

	}

}
