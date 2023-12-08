package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDatDichVu;
import entity.DichVu;
import entity.LichSuGiaDichVu;

public class ChiTietDatDichVuDAO {
	private ConnectDB connectDB;

	public ChiTietDatDichVuDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public List<ChiTietDatDichVu> getChiTietDatDichVuByPhieuDatPhong(String maPhieuDatPhong) {
		List<ChiTietDatDichVu> chiTietDatDichVuList = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "{CALL GetChiTietDatDichVuByPhieuDatPhongNew(?)}";

		try (CallableStatement statement = connection.prepareCall(query)) {
			statement.setString(1, maPhieuDatPhong);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ChiTietDatDichVu chiTietDatDichVu = new ChiTietDatDichVu(resultSet);
				LichSuGiaDichVu lichSuGiaDichVu = new LichSuGiaDichVu(resultSet);
				chiTietDatDichVu.getDichVu().setLichSuGiaDichVuList(List.of(lichSuGiaDichVu));
				chiTietDatDichVuList.add(chiTietDatDichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chiTietDatDichVuList;
	}

	public boolean insertChiTietDatDichVu(String maPhieuDatPhong, List<DichVu> selectedDichVuList) {
		Connection connection = connectDB.getConnection();
		String query = "{call InsertOrUpdateChiTietDatDichVu (?, ?, ?)}";

		try (CallableStatement statement = connection.prepareCall(query)) {
			// Start a transaction
			connection.setAutoCommit(false);

			for (DichVu dichVu : selectedDichVuList) {
				statement.setString(1, maPhieuDatPhong);
				statement.setString(2, dichVu.getMaDichVu());
				statement.setInt(3, dichVu.getSoLuong());

				statement.addBatch();
			}

			int[] updateCounts = statement.executeBatch();

			// Commit the transaction if all updates are successful
			connection.commit();

			for (int updateCount : updateCounts) {
				if (updateCount <= 0) {
					return false;
				}
			}

			return true;
		} catch (SQLException e) {
			// Rollback the transaction in case of an exception
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}

			e.printStackTrace();
			return false;
		} finally {
			// Ensure to set AutoCommit back to true after processing
			try {
				if (connection != null) {
					connection.setAutoCommit(true);
				}
			} catch (SQLException autoCommitException) {
				autoCommitException.printStackTrace();
			}
		}
	}


}
