package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DichVuDAO;
import dao.LichSuGiaDichVuDAO;
import entity.DichVu;
import entity.LichSuGiaDichVu;
import entity.LoaiDichVu;
import enums.TrangThaiDichVu;
import utils.FormatCurrencyUtil;
import utils.ResizeImageUtil;

public class GD_QuanLyDichVu extends JPanel implements ActionListener, MouseListener {

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
    private JLabel lbImageStaff;
    private JComboBox cbType2;
    private List<DichVu> invoices;
    LocalDate current = LocalDate.now();
    private JComboBox cbTypeTwo;
    private JComboBox cbStatusTwo;
    private boolean kiemTraChonAnh;
    private String imageDichVu;
    private String filename;

    public GD_QuanLyDichVu() {
        dichVuDAO = new DichVuDAO();
        lichSuGiaGiaoDichDAO = new LichSuGiaDichVuDAO();
        initGUI();
    }

    private void initGUI() {
        setupFrame();

        addPanelNorth();

        addPanelCenter();

    }

    private void setupFrame() {
        setSize(1000, 700);
        setBackground(new Color(255, 255, 255));
    }

    private void addPanelNorth() {
        setLayout(new BorderLayout());
        pnNorth = new JPanel();
        pnNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnNorth.setBackground(new Color(97, 250, 204));
        add(pnNorth, BorderLayout.NORTH);
        pnNorth.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblTitle = new JLabel("Quản lý dịch vụ");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnNorth.add(lblTitle);

    }

    private void addPanelCenter() {
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        add(pnCenter);

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
        pnFirstForm.setBorder(new EmptyBorder(0, 0, 10, 0));

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
        txtPrice.setEnabled(false);
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

        btnUpdate = new JButton("Cập nhật");
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

        lbImageStaff = new JLabel("");
        verticalBox.add(lbImageStaff);

        horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        btnChoose = new JButton("Chọn ảnh");
        btnChoose.setBackground(new Color(107, 208, 107));
        btnChoose.setFont(new Font("Tahoma", Font.BOLD, 14));
        horizontalBox.add(btnChoose);

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
        cbSelectKey.setModel(new DefaultComboBoxModel<>(new String[]{"Mã dịch vụ", "Tên dịch vụ", "Giá", "Số lượng", "Tên loại dịch vụ", "Trạng thái"}));
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

        String[] headers = {"Mã dịch vụ", "Tên dịch vụ", "Giá", "Số lượng", "Tên loại dịch vụ", "Trạng thái"};
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
    }

    private void loadData() {
        modelTable.setRowCount(0);
        List<DichVu> list = dichVuDAO.getAllDichVu();
        for (DichVu s : list) {
            Object[] row = {s.getMaDichVu(), s.getTenDichVu(), FormatCurrencyUtil.formatCurrency(s.getGia()),
                    s.getSoLuong(), s.getLoaiDichVu().getTenLoaiDichVu(), s.getTrangThai().getName()};
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
            handleSearch();
        }

    }

    private void handleSearch() {
        int selectedOption = cbSelectKey.getSelectedIndex();
        invoices = getColumnName(selectedOption);
        loadData();
    }

    private void addObject() {
        DichVu dv = reverSPFromTextFile();
//        LichSuGiaDichVu lichSu = rever();
//        Object[] row = {dv.getMaDichVu(), dv.getTenDichVu(), 0,
//                dv.getSoLuong(), dv.getLoaiDichVu().getTenLoaiDichVu(), dv.getTrangThai().getName()};
//        modelTable.addRow(row);
//        dichVuDAO.addLichSuGiaGiaoDich(lichSu, dv);
        boolean in = dichVuDAO.addDichVu(dv);
        if(in == true){
             JOptionPane.showMessageDialog(this, "Thêm thành công");
             loadData();
        }else{
            JOptionPane.showMessageDialog(this,"Thêm không thành công");
        }
    }

    private DichVu reverSPFromTextFile() {
        TrangThaiDichVu trangThai = TrangThaiDichVu.values()[cbType.getSelectedIndex()];
        LoaiDichVu loaiDichVu = (LoaiDichVu) cbLoaiDichVu.getSelectedItem();
        List<LichSuGiaDichVu> lichSuGiaDichVuList = new ArrayList<>();
        lichSuGiaDichVuList.add(new LichSuGiaDichVu("", Date.valueOf(current), null, Time.valueOf("8:00:00"),
                Time.valueOf("23:00:00"), 0.0));
//        DichVu dichVu = new DichVu(txtID.getText(), txtName.getText(), Integer.parseInt(txtQuantity.getText()),
//                trangThai, loaiDichVu, lichSuGiaDichVuList);
        DichVu dichVu = new DichVu(txtID.getText(), txtName.getText(), Integer.parseInt(txtQuantity.getText()), filename, trangThai, loaiDichVu);
        return dichVu;
    }

    private LichSuGiaDichVu rever() {
        LocalDate current = LocalDate.now();
        LichSuGiaDichVu lichSu = new LichSuGiaDichVu("", Date.valueOf(current), (Date) null, Time.valueOf("8:00:00"),
                Time.valueOf("23:00:00"),  0.0);
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
            JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void selectImage() {
        JFileChooser fch = new JFileChooser("D:\\He_Thong_Karaoke_Nice\\src\\main\\resources\\image\\dichVu");
        fch.setDialogTitle("Mở file");
        fch.showOpenDialog(null);
        File fImageName = fch.getSelectedFile();
        filename = fImageName.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        lbImageStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbImageStaff.setText("");
        lbImageStaff.setIcon(imageIcon);
    }

    private List<DichVu> getColumnName(int selectedOption) {
        switch (selectedOption) {
            case 0:
                return dichVuDAO.getDichVuTheoMa(txtKey.getText());
            case 1:
                return dichVuDAO.getDSDichVuTheoTen(txtKey.getText());
            case 2:
                // tìm theo giá
            case 3:
                // Tìm theo số lượng
            case 4:
                // Tìm theo loại dịch vụ
                searchHorizontalBox.remove(txtKey);
                searchHorizontalBox.add(cbTypeTwo);
                cbTypeTwo.addItem(TrangThaiDichVu.VO_HIEU.getName());
                cbTypeTwo.addItem(TrangThaiDichVu.HIEU_LUC.getName());
                // return dichVuDAO;
            default:
                // Tìm theo trạng thái
        }

        return null;
    }

//    private void loadImage(String maDichVu) {
//        List<DichVu> list = dichVuDAO.getDichVuTheoMa(maDichVu);
//        for (DichVu dichVu : list) {
//            lbImageStaff = new JLabel(ResizeImageUtil.getResizedImage(dichVu.getHinhAnh(), 100, 100));
//            lbImageStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
//        }
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowSelect = table.getSelectedRow();
        txtID.setText(modelTable.getValueAt(rowSelect, 0).toString());
        txtName.setText(modelTable.getValueAt(rowSelect, 1).toString());
        NumberFormat df = NumberFormat.getCurrencyInstance();
        String text = modelTable.getValueAt(rowSelect, 2).toString();
        try {
            txtPrice.setText(df.parse(text).toString());
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        txtQuantity.setText(modelTable.getValueAt(rowSelect, 3).toString());
        cbLoaiDichVu.setSelectedItem(modelTable.getValueAt(rowSelect, 4));
        cbType.setSelectedItem(modelTable.getValueAt(rowSelect, 5));

//      loadImage(txtID.getText());
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

}