package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;

public class Dao_NhanVien {
	private ConnectDB connectDB;

	public Dao_NhanVien() throws SQLException {
		ConnectDB.getInstance().connect();
	}

	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		Connection connection = connectDB.getConnection();
		String query = "Select * from [dbo].[NhanVien]";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NhanVien nv = new NhanVien(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
