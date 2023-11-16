package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;
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

	public boolean bookKaraokeRoom(String maKhachHang, String maNhanVien, String maPhong, String thoiGianBatDau) {
		Connection connection = connectDB.getConnection();
		String query = "{CALL BookKaraokeRoom(?, ?, ?, ?)}";

		try (CallableStatement statement = connection.prepareCall(query)) {
			statement.setString(1, maKhachHang);
			statement.setString(2, maNhanVien);
			statement.setString(3, maPhong);
			statement.setString(4, thoiGianBatDau);
			int affectedRows = statement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean bookKaraokeRoomBefore(String maKhachHang, String maNhanVien, String maPhong, Time thoiGianBatDau,
			HoaDon hd) {
		Connection connection = connectDB.getConnection();
		String query = "{CALL BookKaraokeRoom(?, ?, ?, ?)}";

		try (CallableStatement statement = connection.prepareCall(query)) {
			statement.setString(1, maKhachHang);
			statement.setString(2, maNhanVien);
			statement.setString(3, maPhong);
			statement.setTime(4, thoiGianBatDau);
			statement.setDate(5, hd.getNgayThanhToan());
			int affectedRows = statement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
