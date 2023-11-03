package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThongKe extends JFrame {
	private JPanel pnNorth, pnCenter, pnSouth;
	private JLabel lblTitle, lblNgayBD, lblNgayKT, lblLoaiTK;

	public ThongKe() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Thống kê");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);

		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setBackground(new Color(97, 250, 204));
		pnNorth.add(lblTitle = new JLabel("THỐNG KÊ"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

		add(pnCenter = new JPanel(), BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new ThongKe().setVisible(true);
	}
}
