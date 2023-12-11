package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.*;
import enums.TrangThaiPhong;

public class PhongDAO {
    private ConnectDB connectDB;
    private final LoaiPhongDAO loaiPhongDAO;
    public PhongDAO() {
        this.connectDB = ConnectDB.getInstance();
        loaiPhongDAO = new LoaiPhongDAO();
    }

    public int addNewPhong(Phong phong) {
        int result = 0;
        Connection con = connectDB.getConnection();
        String query = "INSERT INTO Phong VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, phong.getMaPhong());
            preparedStatement.setString(2, phong.getTenPhong());
            preparedStatement.setInt(3, phong.getSucChua());
            preparedStatement.setString(4, phong.getLoaiPhong().getMaLoaiPhong());
            preparedStatement.setInt(5, phong.getTrangThai().getValue());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updatePhong(Phong phong) {
        int result = 0;
        Connection con = connectDB.getConnection();
        String query = "UPDATE Phong SET tenPhong = ?, sucChua = ?, maLoaiPhong = ?, trangThai = ? WHERE maPhong = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, phong.getTenPhong());
            preparedStatement.setInt(2, phong.getSucChua());
            preparedStatement.setString(3, phong.getLoaiPhong().getMaLoaiPhong());
            preparedStatement.setInt(4, phong.getTrangThai().getValue());
            preparedStatement.setString(5, phong.getMaPhong());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Phong> getAllPhong() {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM PhongView";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> getAllPhongTrong() {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM PhongTrongView";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> GetPhongByTenAndLoaiPhong(String tenPhong, LoaiPhong loaiPhong) {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String sql = "{call GetPhongByTenAndLoaiPhong(?, ?)}";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, tenPhong);
            callableStatement.setString(2, loaiPhong.getMaLoaiPhong());

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                LichSuGiaPhong lichSuGiaPhong = new LichSuGiaPhong(resultSet);
                phong.getLoaiPhong().setLichSuGiaPhongList(List.of(lichSuGiaPhong));
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> getPhongByTrangThai(int trangThai) {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM PhongView WHERE Phong_TrangThai = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trangThai);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> GetPhongByLoaiPhong(LoaiPhong loaiPhong) {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String sql = "SELECT * FROM PhongView WHERE Phong_MaLoaiPhong = ?";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, loaiPhong.getMaLoaiPhong());

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> getPhongByTen(String tenPhong) {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String sql = "SELECT * FROM PhongView WHERE Phong_TenPhong = ?";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, tenPhong);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> getPhongLoaiPhongLichSuaGiaByConditionTime() {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM PhongLoaiPhongLichSuaGiaByConditionTimeView";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                LichSuGiaPhong lichSuGiaPhong = new LichSuGiaPhong(resultSet);
                phong.getLoaiPhong().setLichSuGiaPhongList(List.of(lichSuGiaPhong));
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public List<Phong> getPhongByCondition(int trangThai, String maLoaiPhong, String tenPhong) {
        List<Phong> rooms = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String storedProcedure = "{call GetPhongByCondition(?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(storedProcedure)) {
            statement.setInt(1, trangThai);
            statement.setString(2, maLoaiPhong);
            statement.setString(3, tenPhong);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Phong room = new Phong(resultSet);
                LichSuGiaPhong lichSuGiaPhong = new LichSuGiaPhong(resultSet);
                room.getLoaiPhong().setLichSuGiaPhongList(List.of(lichSuGiaPhong));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public List<Phong> getNewHoaDonByTenKhachHang(String tenKhachHang) {
        List<Phong> phongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String sql = "{call GetNewHoaDonByTenKhachHang(?)}";

        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, tenKhachHang);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Phong phong = new Phong(resultSet);
                phongList.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phongList;
    }

    public boolean updateTrangThaiPhong(String maHoaDon, String maPhong) {
        int result = 0;
        Connection connection = connectDB.getConnection();
        String sql = "{CALL UpdateTrangThaiAndThoiGianBatDau(?, ?)}";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, maHoaDon);
            preparedStatement.setString(2, maPhong);
            result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
