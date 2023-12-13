package view;

import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GD_ManHinhChinh extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenuItem menuItemNhanPhong, menuItemHuyDatPhong, menuItemPhong, menuItemNhanVien, menuItemDichVu, menuItemKhachHang, menuItemKhuyenMai, menuItemDatPhong, menuItemTKDoanhThu, menuItemThoat, menuItemDangXuat, menuItemTaiKhoan, menuItemTrangChu, menuItemTroGiup, menuItemHoaDon;
    private JMenu menuHeThong, menuDanhMuc, menuXuLy, menuThongKe;
    private JPanel contentPane;
    private JLabel lbBackGroundKaraoke;
    private TaiKhoan taiKhoanLogin;

    public GD_ManHinhChinh(TaiKhoan taiKhoanLogin) {
        this.taiKhoanLogin = taiKhoanLogin;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menuBar.setFont(new Font("Tahoma", Font.BOLD, 14));
        setJMenuBar(menuBar);

        menuHeThong = new JMenu("Hệ thống");
        menuHeThong.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(menuHeThong);

        menuItemTrangChu = new JMenuItem("Trang chủ");
        menuItemTrangChu.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuHeThong.add(menuItemTrangChu);

        menuItemTaiKhoan = new JMenuItem("Tài khoản ");
        menuItemTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 14));
//        menuHeThong.add(menuItemTaiKhoan);

        menuItemTroGiup = new JMenuItem("Trợ giúp");
        menuItemTroGiup.setFont(new Font("Tahoma", Font.BOLD, 14));
//        menuHeThong.add(menuItemTroGiup);

        menuItemDangXuat = new JMenuItem("Đăng xuất");
        menuItemDangXuat.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuHeThong.add(menuItemDangXuat);

        menuItemThoat = new JMenuItem("Thoát");
        menuItemThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuHeThong.add(menuItemThoat);

        menuDanhMuc = new JMenu("Danh mục");
        menuDanhMuc.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(menuDanhMuc);

        menuItemPhong = new JMenuItem("Phòng");
        menuItemPhong.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuItemNhanVien = new JMenuItem("Nhân viên");
        menuItemNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuItemDichVu = new JMenuItem("Dịch vụ");
        menuItemDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuItemKhachHang = new JMenuItem("Khách hàng");
        menuItemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuItemKhuyenMai = new JMenuItem("Khuyến mãi");
        menuItemKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuItemHoaDon = new JMenuItem("Hóa đơn");
        menuItemHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuXuLy = new JMenu("Xử lý");
        menuXuLy.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(menuXuLy);

        menuItemDatPhong = new JMenuItem("Đặt phòng");
        menuItemDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuXuLy.add(menuItemDatPhong);

        menuItemNhanPhong = new JMenuItem("Nhận phòng");
        menuItemNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuXuLy.add(menuItemNhanPhong);

        menuItemHuyDatPhong = new JMenuItem("Đặt phòng");
        menuItemHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuXuLy.add(menuItemHuyDatPhong);

        menuThongKe = new JMenu("Thống kê");
        menuThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));

        menuItemTKDoanhThu = new JMenuItem("Thống kê doanh thu");
        menuItemTKDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuThongKe.add(menuItemTKDoanhThu);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        init();

        menuItemTrangChu.addActionListener(this);
        menuItemTaiKhoan.addActionListener(this);
        menuItemTroGiup.addActionListener(this);
        menuItemDangXuat.addActionListener(this);
        menuItemThoat.addActionListener(this);
        menuItemPhong.addActionListener(this);
        menuItemNhanVien.addActionListener(this);
        menuItemDichVu.addActionListener(this);
        menuItemKhachHang.addActionListener(this);
        menuItemKhuyenMai.addActionListener(this);
        menuItemDatPhong.addActionListener(this);
        menuItemTKDoanhThu.addActionListener(this);
        menuItemHoaDon.addActionListener(this);
        menuItemNhanPhong.addActionListener(this);
        menuItemHuyDatPhong.addActionListener(this);
        if(!taiKhoanLogin.getNhanVien().getChucVu().equals("Nhân viên")){
            menuDanhMuc.add(menuItemPhong);
            menuDanhMuc.add(menuItemNhanVien);
            menuDanhMuc.add(menuItemDichVu);
            menuDanhMuc.add(menuItemKhuyenMai);
            menuDanhMuc.add(menuItemHoaDon);
            menuDanhMuc.add(menuItemKhachHang);
            menuBar.add(menuThongKe);
        }else{
            menuDanhMuc.add(menuItemKhachHang);
        }
    }

    private void init() {
        lbBackGroundKaraoke = new JLabel("");
        lbBackGroundKaraoke.setIcon(new ImageIcon("src\\main\\resources\\image\\icon\\ManHinhChinh.jpeg"));
        lbBackGroundKaraoke.setBounds(0, 0, 1000, 700);
        add(lbBackGroundKaraoke);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JPanel simplePanel = null;
        if (source == menuItemTrangChu) {
            setContentPane(contentPane);
            return;
        } else if (source == menuItemTaiKhoan) {
            JOptionPane.showMessageDialog(null, "Tài Khoản Clicked");
        } else if (source == menuItemTroGiup) {
            JOptionPane.showMessageDialog(null, "Trợ Giúp Clicked");
        } else if (source == menuItemDangXuat) {
            GD_Login gdLogin = new GD_Login();
            gdLogin.setVisible(true);
            setVisible(false);
            return;
        } else if (source == menuItemThoat) {
            setVisible(false);
        } else if (source == menuItemPhong) {
            simplePanel = new GD_QuanLyPhong();
        } else if (source == menuItemNhanVien) {
            simplePanel = new GD_QuanLyNhanVien();
        } else if (source == menuItemHoaDon) {
            simplePanel = new GD_QuanLyHoaDon();
        } else if (source == menuItemKhachHang) {
            simplePanel = new GD_QuanLyKhachHang();
        } else if (source == menuItemKhuyenMai) {
            simplePanel = new GD_QuanLyKhuyenMai();
        } else if (source == menuItemDichVu) {
            simplePanel = new GD_QuanLyDichVu();
        } else if (source == menuItemTKDoanhThu) {
            simplePanel = new GD_ThongKe();
        } else if (source == menuItemDatPhong) {
            simplePanel = new GD_QuanLyDatPhong(this.taiKhoanLogin.getNhanVien());
        }else if(source == menuItemNhanPhong){
            simplePanel = new GD_NhanPhong();
        }else if(source == menuItemHuyDatPhong){
            simplePanel = new GD_HuyDatPhongCho();
        }
        setContentPane(simplePanel);
        validate();
        repaint();
    }
}