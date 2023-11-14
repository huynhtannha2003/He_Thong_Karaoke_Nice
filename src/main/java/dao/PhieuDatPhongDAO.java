package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.PhieuDatPhong;

public class PhieuDatPhongDAO {
	private ConnectDB connectDB;

	public PhieuDatPhongDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public List<PhieuDatPhong> getPhieuDatPhongByMaHoaDon(String maHoaDon) {
        List<PhieuDatPhong> phieuDatPhongList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "{CALL GetPhieuDatPhongByMaHoaDon(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, maHoaDon);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PhieuDatPhong phieuDatPhong = new PhieuDatPhong(resultSet);
                phieuDatPhongList.add(phieuDatPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phieuDatPhongList;
    }
}
