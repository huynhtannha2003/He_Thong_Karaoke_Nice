package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.LichSuGiaPhong;
import entity.PhieuDatPhong;

public class PhieuDatPhongDAO {
	private ConnectDB connectDB;

	public PhieuDatPhongDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public boolean bookKaraokeRoom(String maKhachHang, String maNhanVien, String maPhong, Time thoiGianBatDau,
			Date ngayThanhToan) {
		Connection connection = connectDB.getConnection();
		String query = "{CALL BookKaraokeRoom(?, ?, ?, ?, ?)}";

		try (CallableStatement statement = connection.prepareCall(query)) {
			statement.setString(1, maKhachHang);
			statement.setString(2, maNhanVien);
			statement.setString(3, maPhong);
			statement.setTime(4, thoiGianBatDau);
			statement.setDate(5, ngayThanhToan);
			int affectedRows = statement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
				LichSuGiaPhong lichSuGiaPhong = new LichSuGiaPhong(resultSet);
				phieuDatPhong.getPhong().getLoaiPhong().setLichSuGiaPhongList(List.of(lichSuGiaPhong));
				phieuDatPhongList.add(phieuDatPhong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return phieuDatPhongList;
	}

	public boolean updatePaymentDetails(HoaDon hoaDon) {
		Connection connection = connectDB.getConnection();
		String query = "{CALL UpdatePaymentDetails(?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, hoaDon.getMaHoaDon());
            statement.setDouble(2, hoaDon.getTongTien());
            statement.setTime(3, hoaDon.getThoiDiemThanhToan());
            statement.setString(4, hoaDon.getPhieuDatPhongList().get(hoaDon.getPhieuDatPhongList().size() - 1).getMaPhieuDatPhong());
            statement.setTime(5, hoaDon.getPhieuDatPhongList().get(hoaDon.getPhieuDatPhongList().size() - 1).getThoiGianKetThuc());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeRoom(PhieuDatPhong phieuDatPhong) {
        Connection connection = connectDB.getConnection();
        String query = "{CALL ChangeKarokeRoom(?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, phieuDatPhong.getHoaDon().getMaHoaDon());
            statement.setString(2, phieuDatPhong.getPhong().getMaPhong());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
