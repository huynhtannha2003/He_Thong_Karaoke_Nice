package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;

public class GD_ChuyenPhong extends JDialog {
	private JTextField txtRoomName;
	private JTextField txtRoomType;

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
	}

	private void addPanelNorth() {
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(new Color(97, 250, 204));
		getContentPane().add(pnNorth, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Chuyển phòng");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnNorth.add(lblTitle);		
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.X_AXIS));
		
		pnCenter.add(Box.createHorizontalStrut(20));
		
		JPanel pnBox = new JPanel();
		pnBox.setBackground(new Color(255, 255, 255));
		pnCenter.add(pnBox);
		pnBox.setLayout(new BoxLayout(pnBox, BoxLayout.Y_AXIS));
		
		pnBox.add(Box.createVerticalStrut(20));
		
		Box formHorizontalBox = Box.createHorizontalBox();
		pnBox.add(formHorizontalBox);
		
		pnBox.add(Box.createVerticalStrut(20));
		
		JLabel lblRoomName = new JLabel("New label");
		formHorizontalBox.add(lblRoomName);
		
		txtRoomName = new JTextField();
		formHorizontalBox.add(txtRoomName);
		txtRoomName.setColumns(10);
		
		JLabel lblRoomType = new JLabel("New label");
		formHorizontalBox.add(lblRoomType);
		
		txtRoomType = new JTextField();
		formHorizontalBox.add(txtRoomType);
		txtRoomType.setColumns(10);
		
		Box horizontalBox = Box.createHorizontalBox();
		pnBox.add(horizontalBox);
		
		JButton btnFind = new JButton("Tìm kiếm");
		btnFind.setBackground(new Color(107, 208, 107));
		horizontalBox.add(btnFind);
		
		pnCenter.add(Box.createHorizontalStrut(20));
	}

	
}
