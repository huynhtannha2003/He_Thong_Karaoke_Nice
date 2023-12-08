package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import dao.LoaiPhongDAO;
import dao.PhongDAO;
import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiLoaiPhong;
import enums.TrangThaiPhong;
import utils.PhongPanelClickListener;
import utils.RoomPanelUtil;

public class GD_QuanLyPhong extends JPanel implements ActionListener, PhongPanelClickListener {
    private JPanel pnNorth, pnCenter, pnSouth, pnInfo, pnFind, pnListRoom;
    private JLabel lblTitle, lblMaPhong, lblTenPhong, lblSoLuong, lblTrangThai, lblLoaiTimKiem, lblTuKhoaTim,
            lblLoaiPhong;
    private JTextField txtMaPhong, txtTenPhong, txtTuKhoaTim;
    private JSpinner spnSoLuong;
    private JButton btnThem, btnCapNhat, btnXoaTrang, btnTim;
    private Box box, box1, box2, box3;
    private JComboBox<String> cmbLoaiTimKiem, cmbLoaiPhong, cmbTrangThai;
    private JScrollPane scroll;

    private PhongDAO dao;
    private List<Phong> listPhong;
    private List<TrangThaiPhong> listTrangThai;

    public GD_QuanLyPhong() {
        createGUI();
    }

    private void createGUI() {
//		setTitle("Quản lý phòng");
        setSize(1000, 700);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setResizable(false);
//		setLocationRelativeTo(this);
        setLayout(new BorderLayout());

        add(pnNorth = new JPanel(), BorderLayout.NORTH);
        pnNorth.setBackground(new Color(97, 250, 204));
        pnNorth.add(lblTitle = new JLabel("Quản lý phòng"));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));

        add(pnCenter = new JPanel(), BorderLayout.CENTER);
//		pnCenter.setLayout(new GridLayout(3, 1));
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
//		pnCenter.setPreferredSize(new Dimension(500, 500));

        pnCenter.add(pnInfo = new JPanel());
        pnCenter.add(pnFind = new JPanel());

        pnInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        pnInfo.setLayout(new BoxLayout(pnInfo, BoxLayout.Y_AXIS));

        pnInfo.add(box = Box.createVerticalBox());
        box.add(Box.createVerticalStrut(10));
        box.add(box1 = Box.createHorizontalBox());
        box.add(box.createVerticalStrut(10));
        box.add(box2 = Box.createHorizontalBox());
        box.add(box.createVerticalStrut(10));
        box.add(box3 = Box.createHorizontalBox());
        box.add(Box.createVerticalStrut(10));

        box1.add(Box.createHorizontalStrut(20));
        box1.add(lblMaPhong = new JLabel("Mã phòng:"));
        lblMaPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        box1.add(Box.createHorizontalStrut(15));
        box1.add(txtMaPhong = new JTextField());
        txtMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box1.add(Box.createHorizontalStrut(25));
        box1.add(lblTenPhong = new JLabel("Tên phòng:"));
        lblTenPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        box1.add(Box.createHorizontalStrut(20));
        box1.add(txtTenPhong = new JTextField());
        txtTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box1.add(Box.createHorizontalStrut(20));

        box2.add(Box.createHorizontalStrut(20));
        box2.add(lblSoLuong = new JLabel("Sức chứa:"));
        lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
        box2.add(Box.createHorizontalStrut(25));
        box2.add(spnSoLuong = new JSpinner());
        spnSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box2.add(Box.createHorizontalStrut(20));
        box2.add(lblTrangThai = new JLabel("Trạng thái:"));
        lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
        box2.add(Box.createHorizontalStrut(23));
        box2.add(cmbTrangThai = new JComboBox<String>());
        cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box2.add(Box.createHorizontalStrut(20));
        cmbTrangThai.setPreferredSize(new Dimension(360, 25));

        box3.add(Box.createHorizontalStrut(20));
        box3.add(lblLoaiPhong = new JLabel("Loại phòng:"));
        lblLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
        box3.add(Box.createHorizontalStrut(13));
        box3.add(cmbLoaiPhong = new JComboBox<>());
        cmbLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));

        box3.add(Box.createHorizontalStrut(210));
        box3.add(btnThem = new JButton("Thêm"));
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box3.add(Box.createHorizontalStrut(10));
        box3.add(btnCapNhat = new JButton("Cập nhật"));
        btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box3.add(Box.createHorizontalStrut(10));
        box3.add(btnXoaTrang = new JButton("Xóa trắng"));
        btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box3.add(Box.createHorizontalStrut(20));

        spnSoLuong.setPreferredSize(new Dimension(360, 20));
        lblMaPhong.setPreferredSize(new Dimension(79, 17));

        pnFind.setBorder(BorderFactory.createLineBorder(Color.black));
        pnFind.setLayout(new BoxLayout(pnFind, BoxLayout.Y_AXIS));
        pnFind.add(box = Box.createVerticalBox());

        box.add(Box.createVerticalStrut(10));
        box.add(box1 = Box.createHorizontalBox());
        box.add(Box.createVerticalStrut(10));

        box1.add(Box.createHorizontalStrut(20));
        box1.add(lblLoaiTimKiem = new JLabel("Tìm kiếm theo:"));
        lblLoaiTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box1.add(Box.createHorizontalStrut(10));
        box1.add(cmbLoaiTimKiem = new JComboBox<String>());
        cmbLoaiTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbLoaiTimKiem.setPreferredSize(new Dimension(300, 20));
        box1.add(Box.createHorizontalStrut(20));
        box1.add(lblTuKhoaTim = new JLabel("Nhập vào từ khóa:"));
        lblTuKhoaTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box1.add(Box.createHorizontalStrut(10));
        box1.add(txtTuKhoaTim = new JTextField());
        txtTuKhoaTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box1.add(Box.createHorizontalStrut(10));
        box1.add(btnTim = new JButton("Tìm kiếm"));
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box1.add(Box.createHorizontalStrut(20));

        cmbLoaiTimKiem.addItem("Chọn loại tìm kiếm");
        cmbLoaiTimKiem.addItem("Tìm phòng trống");
        cmbLoaiTimKiem.addItem("Tìm phòng đang sử dụng");
        cmbLoaiTimKiem.addItem("Tìm phòng chờ");
        cmbLoaiTimKiem.addItem("Tìm phòng đang bảo trì");
        cmbLoaiTimKiem.addItem("Phong thường");
        cmbLoaiTimKiem.addItem("Phòng vip");
        cmbLoaiTimKiem.addItem("Tìm kiếm theo tên phòng");

        initRoomData();
        createListRoom();

        btnThem.setBackground(new Color(107, 208, 107));
        btnCapNhat.setBackground(new Color(107, 208, 107));
        btnTim.setBackground(new Color(107, 208, 107));
        btnXoaTrang.setBackground(new Color(107, 208, 107));

        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoaTrang.addActionListener(this);

        loadTrangThaiPhong();
        loadLoaiPhong();

        btnCapNhat.setEnabled(false);

        txtTuKhoaTim.setEnabled(false);
        cmbLoaiTimKiem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cmbLoaiTimKiem.getSelectedIndex() == 0)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 1)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 2)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 3)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 4)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 5)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 6)
                        txtTuKhoaTim.setEnabled(false);
                    else if (cmbLoaiTimKiem.getSelectedIndex() == 7) {
                        txtTuKhoaTim.setEnabled(true);
                        txtTuKhoaTim.requestFocus();
                    }
                }
            }
        });
    }

    private void loadTrangThaiPhong() {
        listTrangThai = Arrays.asList(TrangThaiPhong.values());
        for (TrangThaiPhong trangThaiPhong : listTrangThai) {
            cmbTrangThai.addItem(trangThaiPhong.getTypePhong());
        }
    }

    private void loadLoaiPhong() {
        LoaiPhongDAO dao = new LoaiPhongDAO();
        List<LoaiPhong> listLoaiPhong = dao.getAllLoaiPhong();
        for (LoaiPhong loaiPhong : listLoaiPhong) {
            cmbLoaiPhong.addItem(loaiPhong.getTenLoaiPhong());
        }
    }

    private void initRoomData() {
        listPhong = new ArrayList<>();
        dao = new PhongDAO();
        listPhong = getAllRoom();
    }

    private List<Phong> getAllRoom() {
        return dao.getAllPhong();
    }

    private void createListRoom() {
        pnListRoom = new JPanel();
        pnListRoom.setBackground(new Color(255, 255, 255));
        scroll = new JScrollPane(pnListRoom);
        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(15);
        pnCenter.add(scroll);
        scroll.setPreferredSize(new Dimension(500, 500));

        pnListRoom.setLayout(new GridLayout(0, 6, 0, 0));
        pnListRoom.setBorder(BorderFactory.createLineBorder(Color.black));

        loadRoom(listPhong);
    }

    private void loadRoom(List<Phong> rooms) {
        pnListRoom.removeAll();
        List<JPanel> phongPanel = RoomPanelUtil.createPhongPanels(rooms, this);
        phongPanel.forEach(pnListRoom::add);

        pnListRoom.revalidate();
        pnListRoom.repaint();
    }

    private void clearInput() {
        txtMaPhong.setText("");
        txtTenPhong.setText("");
        spnSoLuong.setValue(0);
        cmbLoaiPhong.setSelectedIndex(0);
        cmbTrangThai.setSelectedIndex(0);
        btnCapNhat.setEnabled(false);
        cmbLoaiTimKiem.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new GD_QuanLyPhong().setVisible(true);
    }

    @Override
    public void onPhongPanelClicked(List<Phong> listPhong) {
        Phong phong = listPhong.get(0);
        txtMaPhong.setText(phong.getMaPhong());
        txtTenPhong.setText(phong.getTenPhong());
        spnSoLuong.setValue(phong.getSucChua());
        cmbLoaiPhong.setSelectedItem(phong.getLoaiPhong().getTenLoaiPhong());
        cmbTrangThai.setSelectedIndex(phong.getTrangThai().getValue());
        btnCapNhat.setEnabled(true);
    }

    private Phong getInput() {
        if (txtMaPhong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập vào mã phòng", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (txtTenPhong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập vào tên phòng", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        } else if ((int) spnSoLuong.getValue() < 1) {
            JOptionPane.showMessageDialog(this, "Sức chứa không được thấp hơn 1", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        LoaiPhongDAO dao = new LoaiPhongDAO();
        String maPhong = txtMaPhong.getText();
        String tenPhong = txtTenPhong.getText();
        int sucChua = (int) spnSoLuong.getValue();
        LoaiPhong loaiPhong = dao.getLoaiPhongByTen(cmbLoaiPhong.getSelectedItem().toString());
        TrangThaiPhong trangThai = TrangThaiPhong.values()[cmbTrangThai.getSelectedIndex()];

        return new Phong(maPhong, loaiPhong, tenPhong, sucChua, trangThai);
    }

    private void capNhatPhong(Phong phong) {
        if (dao.updatePhong(phong) != 0) {
            List<Phong> rooms = getAllRoom();
            loadRoom(rooms);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } else
            JOptionPane.showMessageDialog(this, "Cập nhật không thành công!");
    }

    private void themPhong(Phong phong) {
        if (dao.addNewPhong(phong) != 0) {
            List<Phong> rooms = getAllRoom();
            loadRoom(rooms);
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } else
            JOptionPane.showMessageDialog(this, "Thêm thất bại thành công!");
    }

    private void timKiemPhong() {
        LoaiPhongDAO daoLoaiPhong = new LoaiPhongDAO();
        if (cmbLoaiTimKiem.getSelectedIndex() == 0) {
            List<Phong> rooms = getAllRoom();
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 1) {
            List<Phong> rooms = dao.getPhongByTrangThai(0);
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 2) {
            List<Phong> rooms = dao.getPhongByTrangThai(1);
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 3) {
            List<Phong> rooms = dao.getPhongByTrangThai(2);
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 4) {
            List<Phong> rooms = dao.getPhongByTrangThai(3);
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 5) {
            List<Phong> rooms = dao.GetPhongByLoaiPhong(daoLoaiPhong.getLoaiPhongByTen("Phòng thường"));
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 6) {
            List<Phong> rooms = dao.GetPhongByLoaiPhong(daoLoaiPhong.getLoaiPhongByTen("Phòng vip"));
            loadRoom(rooms);
        } else if (cmbLoaiTimKiem.getSelectedIndex() == 7) {
            if (!(txtTuKhoaTim.getText().equalsIgnoreCase(""))) {
                List<Phong> rooms = dao.getPhongByTen(txtTuKhoaTim.getText());
                loadRoom(rooms);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên phòng!");
                txtTuKhoaTim.requestFocus();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnXoaTrang)) {
            clearInput();
        } else if (o.equals(btnCapNhat)) {
            capNhatPhong(getInput());
        } else if (o.equals(btnThem)) {
            themPhong(getInput());
        } else if (o.equals(btnTim)) {
            timKiemPhong();
        }
    }
}
