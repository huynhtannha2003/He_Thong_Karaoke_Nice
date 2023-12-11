package dao;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuDatPhong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private ConnectDB connectDB;
    private final PhieuDatPhongDAO phieuDatPhongDAO;
    private final ChiTietDatDichVuDAO chiTietDatDichVuDAO;

    public HoaDonDAO() {
        phieuDatPhongDAO = new PhieuDatPhongDAO();
        chiTietDatDichVuDAO = new ChiTietDatDichVuDAO();
        this.connectDB = ConnectDB.getInstance();
    }

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM HoaDonDetailsView";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon(resultSet);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDonList;
    }

    public List<HoaDon> getHoaDonPaged(int pageNumber, int rowsPerPage) {
        return executeGetHoaDonPage("{CALL GetHoaDonPaged(?, ?)}", null, pageNumber, rowsPerPage);
    }

    public List<HoaDon> getHoaDonPagedByMaHoaDon(String maHoaDon, int pageNumber, int rowsPerPage) {
        return executeGetHoaDonPage("{CALL GetHoaDonPagedByMaHoaDon(?, ?, ?)}", maHoaDon, pageNumber, rowsPerPage);
    }

    public List<HoaDon> getHoaDonPagedByTenKhachHang(String tenKhachHang, int pageNumber, int rowsPerPage) {
        return executeGetHoaDonPage("{CALL GetHoaDonPagedByTenKhachHangLike(?, ?, ?)}", tenKhachHang, pageNumber, rowsPerPage);
    }

    public List<HoaDon> getHoaDonPagedByDateRange(Date fromDay, Date toDay, int pageNumber, int rowsPerPage) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "{CALL GetHoaDonPagedByDateRange(?, ?, ?, ?)}";

        try (PreparedStatement preparedStatement = connection.prepareCall(query)) {
            preparedStatement.setDate(1, fromDay);
            preparedStatement.setDate(2, toDay);
            preparedStatement.setInt(3, pageNumber);
            preparedStatement.setInt(4, rowsPerPage);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon(resultSet);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDonList;
    }


    private List<HoaDon> executeGetHoaDonPage(String query, String parameter, int pageNumber, int rowsPerPage) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Connection connection = connectDB.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareCall(query)) {
            if (parameter != null) {
                preparedStatement.setString(1, parameter);
            }
            preparedStatement.setInt(parameter != null ? 2 : 1, pageNumber);
            preparedStatement.setInt(parameter != null ? 3 : 2, rowsPerPage);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon(resultSet);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDonList;
    }

    public HoaDon getHoaDonByMaPhong(String maPhong) {
        HoaDon hoaDon = null;
        Connection connection = connectDB.getConnection();
        String query = "{CALL GetNewHoaDonByMaPhong(?)}";

        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, maPhong);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                hoaDon = new HoaDon(resultSet);
                hoaDon.setNhanVien(new NhanVien(resultSet));
                List<PhieuDatPhong> phieuDatPhongList = phieuDatPhongDAO.getPhieuDatPhongByMaHoaDon(hoaDon.getMaHoaDon());
                phieuDatPhongList.forEach(hoaDon.getPhieuDatPhongList()::add);
                hoaDon.getPhieuDatPhongList().forEach(currentPhieuDatPhong ->
                        chiTietDatDichVuDAO.getChiTietDatDichVuByPhieuDatPhong(currentPhieuDatPhong.getMaPhieuDatPhong()).forEach(currentPhieuDatPhong.getChiTietDatDichVuList()::add)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }

    public HoaDon getHoaDonByMaHoaDon(String maHoaDon) {
        HoaDon hoaDon = null;
        Connection connection = connectDB.getConnection();
        String query = "{CALL GetHoaDonByMaHoaDon(?)}";

        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, maHoaDon);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                hoaDon = new HoaDon(resultSet);
                hoaDon.setNhanVien(new NhanVien(resultSet));
                List<PhieuDatPhong> phieuDatPhongList = phieuDatPhongDAO.getPhieuDatPhongByMaHoaDon(hoaDon.getMaHoaDon());
                phieuDatPhongList.forEach(hoaDon.getPhieuDatPhongList()::add);
                hoaDon.getPhieuDatPhongList().forEach(currentPhieuDatPhong ->
                        chiTietDatDichVuDAO.getChiTietDatDichVuByPhieuDatPhong(currentPhieuDatPhong.getMaPhieuDatPhong()).forEach(currentPhieuDatPhong.getChiTietDatDichVuList()::add)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }

    public List<HoaDon> getTodayPhieuDatPhongCho() {
        List<HoaDon> hoaDonList = new ArrayList<>();

        Connection connection = connectDB.getConnection();
        String query = "{CALL GetTodayPhieuDatPhongCho}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon(resultSet);

                List<PhieuDatPhong> phieuDatPhongList = new ArrayList<>();
                phieuDatPhongList.add(new PhieuDatPhong(resultSet));

                hoaDon.setPhieuDatPhongList(phieuDatPhongList);

                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDonList;
    }
}
