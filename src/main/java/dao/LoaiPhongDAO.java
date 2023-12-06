package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LichSuGiaPhong;
import entity.LoaiPhong;

public class LoaiPhongDAO {
	private ConnectDB connectDB;

	public LoaiPhongDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public List<LoaiPhong> getAllLoaiPhong() {
		List<LoaiPhong> loaiPhongList = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM LoaiPhongView";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LoaiPhong loaiPhong = new LoaiPhong(resultSet);

				loaiPhongList.add(loaiPhong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loaiPhongList;
	}

	public LoaiPhong getLoaiPhongByTen(String tenLoaiPhong) {
		LoaiPhong loaiPhong = null;
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM LoaiPhongView WHERE LoaiPhong_TenLoaiPhong = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, tenLoaiPhong);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				loaiPhong = new LoaiPhong(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loaiPhong;
	}

	public List<LichSuGiaPhong> getLichSuGiaPhongByMaLoaiPhong(String maLoaiPhong) {
		List<LichSuGiaPhong> lichSuLloaiPhong = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM LichSuGiaPhongView WHERE LoaiPhong_MaLoaiPhong = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, maLoaiPhong);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				lichSuLloaiPhong.add(new LichSuGiaPhong(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lichSuLloaiPhong;
	}
}
