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
		String query = "{CALL GetChiTietDatDichVuByPhieuDatPhong(?)}";

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
		String query = "INSERT INTO ChiTietDatDichVu VALUES (?, ?,?)";

		try (CallableStatement statement = connection.prepareCall(query)) {
			for (DichVu dichVu : selectedDichVuList) {
				statement.setString(1, maPhieuDatPhong);
				statement.setString(2, dichVu.getMaDichVu());
				statement.setInt(3, dichVu.getSoLuong());

				statement.addBatch();
			}

			int[] updateCounts = statement.executeBatch();

			for (int updateCount : updateCounts) {
				if (updateCount <= 0) {
					return false;
				}
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
