package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
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
}
