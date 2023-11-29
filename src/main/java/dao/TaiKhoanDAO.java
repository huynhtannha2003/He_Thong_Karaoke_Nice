package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoanDAO {
	private ConnectDB connectDB;

	public TaiKhoanDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public List<TaiKhoan> getAllTaiKhoan() {
		List<TaiKhoan> list = new ArrayList<>();
		Connection con = connectDB.getConnection();
		String query = "SELECT * FROM TaiKhoanView";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TaiKhoan taiKhoan = new TaiKhoan(resultSet);
				list.add(taiKhoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public TaiKhoan getTaiKhoan(String tenDangNhap, String matKhau) {
		TaiKhoan taiKhoan = null;
		Connection con = connectDB.getConnection();
		String query = "SELECT * FROM TaiKhoanView WHERE TaiKhoan_TenTaiKhoan = ? and TaiKhoan_MatKhau = ?";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setString(1, tenDangNhap);
			preparedStatement.setString(2, matKhau);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				taiKhoan = new TaiKhoan(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taiKhoan;
	}
}
