package view;

import dao.ChiTietDatDichVuDAO;
import dao.DichVuDAO;
import entity.DichVu;
import entity.HoaDon;
import entity.LoaiDichVu;
import utils.DichVuPanelClickListener;
import utils.RoomPanelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class GD_DatDichVu extends JFrame implements DichVuPanelClickListener, ActionListener {

    private static final long serialVersionUID = 1L;
    private final DefaultTableModel model;
    private final JButton btnFind, btnAdd, btnDelete, btnClearAll, btnApply;
    private JTextField txtServiceName;
    private JTable tableOrderedServices;
    private JTextField textField;
    private JComboBox cmbLoaiDV;
    private List<DichVu> listDichVu = new ArrayList<>();
    private final DichVuDAO dichVuDAO;
    private List<DichVu> selectedDichVuList = new ArrayList<>();
    private final ChiTietDatDichVuDAO chiTietDatDichVuDAO;
    private final HoaDon hoaDon;

    public GD_DatDichVu(HoaDon selectedHoaDon, String tenPhong) {
        hoaDon = selectedHoaDon;
        dichVuDAO = new DichVuDAO();
        chiTietDatDichVuDAO = new ChiTietDatDichVuDAO();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JPanel pnTitle = new JPanel();
        pnTitle.setBackground(new Color(97, 255, 204));
        getContentPane().add(pnTitle, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Đặt dịch vụ");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTitle.add(lblTitle);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblRoomName = new JLabel("Phòng: " + tenPhong);
        lblRoomName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoomName.setBounds(10, 10, 96, 25);
        panel.add(lblRoomName);

        JLabel lblServiceType = new JLabel("Loại dịch vụ");
        lblServiceType.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblServiceType.setBounds(369, 10, 96, 25);
        panel.add(lblServiceType);

        cmbLoaiDV = new JComboBox();
        cmbLoaiDV.setBounds(475, 12, 85, 25);
        panel.add(cmbLoaiDV);
        cmbLoaiDV.addItem("Tất cả");

        List<LoaiDichVu> listLoaiDV = dichVuDAO.getLoaiDichVu();
        for (LoaiDichVu loaiDichVu : listLoaiDV) {
            cmbLoaiDV.addItem(loaiDichVu.getTenLoaiDichVu());
        }

        cmbLoaiDV.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cmbLoaiDV.getSelectedIndex() == 0) {

                    } else if (cmbLoaiDV.getSelectedIndex() == 1) {

                    }
                }
            }
        });

        JLabel lblServiceName = new JLabel("Tên dịch vụ:");
        lblServiceName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblServiceName.setBounds(570, 10, 96, 25);
        panel.add(lblServiceName);

        txtServiceName = new JTextField();
        txtServiceName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtServiceName.setBounds(664, 10, 162, 25);
        panel.add(txtServiceName);
        txtServiceName.setColumns(10);

        btnFind = new JButton("Tìm kiếm");
        btnFind.setBackground(new Color(107, 208, 107));
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setBounds(858, 10, 117, 25);
        panel.add(btnFind);

        String row[] = {"STT", "Tên dịch vụ", "Số lượng", "Thành tiền"};
        model = new DefaultTableModel(row, 0);
        tableOrderedServices = new JTable(model);
        JScrollPane spOrderedServices = new JScrollPane(tableOrderedServices);
        spOrderedServices.setBounds(10, 58, 327, 479);
        panel.add(spOrderedServices);

        initData();

        JPanel services = new JPanel();
        JScrollPane spServices = new JScrollPane(services);
        JScrollBar verticalScrollbar = spServices.getVerticalScrollBar();
        verticalScrollbar.setUnitIncrement(16);
        services.setLayout(new GridLayout(0, 3, 0, 0));
        spServices.setBounds(369, 58, 606, 479);
        panel.add(spServices);

        List<JPanel> dichVuPanel = RoomPanelUtil.createDichVuPanels(listDichVu, this);
        dichVuPanel.forEach(services::add);

        btnAdd = new JButton("Thay đổi");
        btnAdd.setBackground(new Color(107, 208, 107));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdd.setBounds(10, 571, 94, 25);
        panel.add(btnAdd);

        btnDelete = new JButton("Xóa");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDelete.setBackground(new Color(221, 78, 78));
        btnDelete.setBounds(123, 571, 94, 25);
        panel.add(btnDelete);

        btnClearAll = new JButton("Xóa tất cả");
        btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnClearAll.setBackground(new Color(57, 130, 240));
        btnClearAll.setBounds(243, 571, 94, 25);
        panel.add(btnClearAll);

        JLabel lblTotalPrice = new JLabel("Tổng tiền:");
        lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTotalPrice.setBounds(511, 573, 82, 25);
        panel.add(lblTotalPrice);

        textField = new JTextField();
        textField.setBounds(620, 572, 185, 25);
        panel.add(textField);
        textField.setColumns(10);

        btnApply = new JButton("Đặt dịch vụ");
        btnApply.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnApply.setBackground(new Color(107, 208, 107));
        btnApply.setBounds(855, 570, 120, 25);
        panel.add(btnApply);

        btnApply.addActionListener(this);
        btnAdd.addActionListener(this);
        btnFind.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClearAll.addActionListener(this);
    }

    public void initData() {
        listDichVu = dichVuDAO.getAllDichVu();
    }

    @Override
    public void onDichVuPanelClicked(DichVu dichVu) {
        String quantityStr = JOptionPane.showInputDialog(this, "Nhập số lượng dịch vụ muốn đặt:", "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);

        if (quantityStr == null) {
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên dương.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (quantity > dichVu.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số lượng đặt vượt quá số lượng hiện tại của sản phẩm.\nSố lượng hiện tại: " + dichVu.getSoLuong(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            dichVu.setSoLuong(quantity);
            DichVu newDichVu = dichVu;
            newDichVu.setSoLuong(quantity);
            selectedDichVuList.add(newDichVu);

            updateOrderedServicesTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên dương.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOrderedServicesTable() {
        model.setRowCount(0);

        int stt = 1;
        for (DichVu dichVu : selectedDichVuList) {
            Object[] rowData = {stt++, dichVu.getTenDichVu(), dichVu.getSoLuong(), dichVu.getLichSuGiaDichVuList().get(0).getGia()};
            model.addRow(rowData);
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = 0.0;
        for (DichVu dichVu : selectedDichVuList) {
            totalPrice += dichVu.getLichSuGiaDichVuList().get(0).getGia();
        }
        textField.setText(String.valueOf(totalPrice));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnAdd) {
            int selectedRow = tableOrderedServices.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dịch vụ từ danh sách đã đặt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String quantityStr = JOptionPane.showInputDialog(this, "Nhập số lượng dịch vụ muốn thêm:", "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);

            if (quantityStr == null) {
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityStr);

                if (quantity < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên không âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (quantity == 0) {
                    selectedDichVuList.remove(selectedRow);
                } else {
                    DichVu selectedDichVu = selectedDichVuList.get(selectedRow);
                    if (quantity > selectedDichVu.getSoLuong()) {
                        JOptionPane.showMessageDialog(this, "Số lượng đặt vượt quá số lượng hiện tại của sản phẩm.\nSố lượng hiện tại: " + selectedDichVu.getSoLuong(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    selectedDichVu.setSoLuong(quantity);
                }

                updateOrderedServicesTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên không âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == btnDelete) {
            int selectedRow = tableOrderedServices.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dịch vụ từ danh sách đã đặt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            selectedDichVuList.remove(selectedRow);
            updateOrderedServicesTable();
        } else if (source == btnClearAll) {
            clearAllData();
        } else if (source == btnApply) {
            if (insertChiTietDatDichVu()) {
                JOptionPane.showMessageDialog(this, "Dịch vụ đã được đặt thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi đặt dịch vụ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean insertChiTietDatDichVu() {
        return chiTietDatDichVuDAO.insertChiTietDatDichVu(hoaDon.getPhieuDatPhongList().get(hoaDon.getPhieuDatPhongList().size() - 1).getMaPhieuDatPhong(), selectedDichVuList);
    }

    private void clearAllData() {
        selectedDichVuList.clear();
        updateOrderedServicesTable();
    }
}