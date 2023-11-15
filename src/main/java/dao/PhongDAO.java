package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.LichSuGiaPhong;
import entity.LoaiPhong;
import entity.Phong;

public class PhongDAO {
	private ConnectDB connectDB;

	public PhongDAO() {
		this.connectDB = ConnectDB.getInstance();
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
}
