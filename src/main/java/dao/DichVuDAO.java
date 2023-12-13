package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.HoaDon;
import entity.LichSuGiaDichVu;
import entity.LoaiDichVu;
import enums.TrangThaiDichVu;

public class DichVuDAO {
    private ConnectDB connectDB;

    public DichVuDAO() {
        this.connectDB = ConnectDB.getInstance();
    }

    public List<DichVu> getAllDichVu() {
        List<DichVu> dichVuList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM DichVuLichSuGiaByConditionTimeView";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DichVu dichVu = new DichVu(resultSet);
                LichSuGiaDichVu lichSuGiaDichVu = new LichSuGiaDichVu(resultSet);
                dichVu.setLichSuGiaDichVuList(List.of(lichSuGiaDichVu));
                dichVuList.add(dichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dichVuList;
    }

    public List<DichVu> getDichVu() {
        List<DichVu> dichVuList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM DichVuView";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DichVu dichVu = new DichVu(resultSet);
                LichSuGiaDichVu lichSuGiaDichVu = new LichSuGiaDichVu(resultSet);
                dichVu.setLichSuGiaDichVuList(List.of(lichSuGiaDichVu));
                dichVuList.add(dichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dichVuList;
    }

    public List<LoaiDichVu> getLoaiDichVu() {
        List<LoaiDichVu> list = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM LoaiDichVuView";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LoaiDichVu loai = new LoaiDichVu(resultSet);
                list.add(loai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addDichVu(DichVu dichVu) {
        Connection con = connectDB.getConnection();
        String query = "insert into DichVu values(?,?,?,?,?,?)";
        int n = 0;
        try (PreparedStatement pre = con.prepareStatement(query)) {
            pre.setString(1, dichVu.getMaDichVu());
            pre.setString(2, dichVu.getTenDichVu());
            pre.setInt(3, dichVu.getSoLuong());
            pre.setString(4, dichVu.getLoaiDichVu().getMaLoaiDichVu());
            pre.setInt(5, dichVu.getTrangThai().getValue());
            pre.setString(6,dichVu.getHinhAnh());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean addLichSuGiaGiaoDich(LichSuGiaDichVu lichSu, DichVu dv) {
        Connection con = connectDB.getConnection();
        String query = "insert into LichSuGiaDichVu values (?,?,?,?,?,?,?)";
        int n = 0;
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement(query);
            pre.setString(1, lichSu.getMaLichSuGiaDichVu());
            pre.setDate(2, lichSu.getNgayBatDau());
            pre.setDate(3, lichSu.getNgayKetThuc());
            pre.setTime(4, lichSu.getThoiDiemBatDau());
            pre.setTime(5, lichSu.getThoiDiemKetThuc());
            pre.setDouble(6, lichSu.getGia());
            pre.setString(7, dv.getMaDichVu());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean updateDichVu(DichVu dichVu, String ma) {
        Connection con = connectDB.getConnection();
        PreparedStatement pre = null;
        int n = 0;
        try {

            pre = con.prepareStatement(
                    "update DichVu set tenDichVu = ?,soLuong = ?, maLoaiDichVu = ?,trangThai = ? where maDichVu = ?");
            pre.setString(1, dichVu.getTenDichVu());
            pre.setInt(2, dichVu.getSoLuong());
            pre.setString(3, dichVu.getLoaiDichVu().getMaLoaiDichVu());
            pre.setInt(4, dichVu.getTrangThai().getValue());
            pre.setString(5, ma);
            n = pre.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return n > 0;
    }

    private List<DichVu> executeGetDichVuPage(String query, String parameter) {
        List<DichVu> dichVuList = new ArrayList<>();
        Connection connection = connectDB.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareCall(query)) {
            if (parameter != null) {
                preparedStatement.setString(1, parameter);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DichVu dichVu = new DichVu(resultSet);
                dichVuList.add(dichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dichVuList;
    }

    public List<DichVu> getDichVuTheoMa(String ma) {
        return executeGetDichVuPage("{CALL GetDichVuByMaDichVu(?)}", ma);
    }

    public List<DichVu> getDSDichVuTheoTen(String ten) {
        return executeGetDichVuPage(
                "{CALL GetDichVuByTenDichVu(?)})", ten);
    }

    public List<DichVu> getDSDichVuTheoGia(float Gia) {
        List<LichSuGiaDichVu> list = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "{CALL GetDichVuByGia(?)}";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setFloat(1, Gia);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DichVu> getDSTheoLoai(String loai) {
        List<DichVu> dichVuList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "select * from DichVuLichSuGiaByConditionTimeView where LoaiDichVu_TenLoaiDichVu = ?";
        try (PreparedStatement preparedStatement = connection.prepareCall(query)) {
            preparedStatement.setString(1, loai);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DichVu dichVu = new DichVu(resultSet);
                dichVuList.add(dichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dichVuList;
    }

    /*
     * Tìm dịch vụ theo trạng thái
     */

    public List<DichVu> getDSDichVuTheoTrangThai(TrangThaiDichVu trangThai) {
        Connection con = connectDB.getConnection();
        List<DichVu> dichVuList = new ArrayList<>();
        String query = "select * from DichVuLichSuGiaByConditionTimeView where LoaiDichVu_TenLoaiDichVu = ?";
        try {
            PreparedStatement pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery(query);
            pre.setInt(1, trangThai.getValue());
            while (rs.next()) {
                DichVu dichVu = new DichVu(rs);
                dichVuList.add(dichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dichVuList;
    }

}