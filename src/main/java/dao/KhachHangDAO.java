package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHangDAO {
	private ConnectDB connectDB;

	public KhachHangDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public KhachHang getCustomerByPhoneNumber(String phoneNumber) {
		Connection connection = connectDB.getConnection();
		CallableStatement callableStatement;
		try {
			callableStatement = connection.prepareCall("{call FindCustomerByPhoneNumber(?)}");
			callableStatement.setString(1, phoneNumber);

			ResultSet resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				KhachHang khachHang = new KhachHang(resultSet);
				return khachHang;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
