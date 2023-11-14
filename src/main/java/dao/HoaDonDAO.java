package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDonDAO {
	private ConnectDB connectDB;

	public HoaDonDAO() {
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

}
