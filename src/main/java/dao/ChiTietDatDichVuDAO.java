package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietDatDichVu;
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

}
