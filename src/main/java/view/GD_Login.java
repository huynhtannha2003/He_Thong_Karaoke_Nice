package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import dao.TaiKhoanDAO;
import entity.TaiKhoan;
import utils.*;

public class GD_Login extends JFrame {

    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private TaiKhoanDAO dao;
    private TaiKhoan taiKhoanLogin;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GD_Login frame = new GD_Login();
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
    public GD_Login() {
        dao = new TaiKhoanDAO();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        JPanel MainPane = new JPanel();
        MainPane.setBackground(new Color(192, 192, 192));
        MainPane.setSize(700, 700);
        MainPane.setBounds(0, 0, 684, 661);
        MainPane.setLayout(null);
        add(MainPane);

        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setForeground(new Color(255, 255, 255));
        btnDangNhap.setBackground(new Color(31, 141, 242));
        btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GD_ManHinhChinh(taiKhoanLogin).setVisible(true);
//                System.out.println(taiKhoanLogin);
                setVisible(false);
            }
        });

        JLabel lbShowPassword = new JLabel("");
        lbShowPassword.setIcon(new ImageIcon(GD_Login.class.getResource("/image/icon/hide_icon.png")));
        lbShowPassword.setBounds(235, 397, 29, 19);
        MainPane.add(lbShowPassword);

        txtMatKhau = new JPasswordField("Tam123456@");
        txtMatKhau.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtMatKhau.setBounds(69, 384, 200, 40);
        MainPane.add(txtMatKhau);
        btnDangNhap.setBounds(108, 482, 126, 40);
        MainPane.add(btnDangNhap);

        JLabel lbQuenMatKhau = new JLabel("Quên mật khẩu");
        lbQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbQuenMatKhau.setForeground(new Color(255, 255, 255));
        lbQuenMatKhau.setBounds(155, 440, 114, 14);
        MainPane.add(lbQuenMatKhau);

        JLabel lbMatKhau = new JLabel("Mật khẩu");
        lbMatKhau.setForeground(new Color(255, 255, 255));
        lbMatKhau.setBounds(69, 364, 61, 14);
        MainPane.add(lbMatKhau);

        txtTenDangNhap = new JTextField("laminhtam");
        txtTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtTenDangNhap.setBounds(69, 313, 200, 40);
        MainPane.add(txtTenDangNhap);
        txtTenDangNhap.setColumns(10);

        JLabel lbTenDangNhap = new JLabel("Tên đăng nhập");
        lbTenDangNhap.setForeground(new Color(255, 255, 255));
        lbTenDangNhap.setBounds(69, 293, 84, 14);
        MainPane.add(lbTenDangNhap);

        JLabel txtTitle = new JLabel("Đăng nhập");
        txtTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        txtTitle.setBackground(new Color(255, 255, 128));
        txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
        txtTitle.setForeground(new Color(255, 255, 255));
        txtTitle.setBounds(98, 238, 126, 30);
        MainPane.add(txtTitle);

        JLabel lbBackGroundKaraoke = new JLabel("");
        lbBackGroundKaraoke.setLabelFor(MainPane);
        lbBackGroundKaraoke.setIcon(new ImageIcon(GD_Login.class.getResource("/image/icon/login_image.png")));
        lbBackGroundKaraoke.setBounds(0, 0, 684, 661);
        lbBackGroundKaraoke.setPreferredSize(new Dimension(684, 661));
        MainPane.add(lbBackGroundKaraoke);

        taiKhoanLogin = getLoginAccount();
    }

    public TaiKhoan getLoginAccount() {
        String pwd = new String(txtMatKhau.getPassword());
        return dao.getTaiKhoan(txtTenDangNhap.getText(), pwd);
    }
}