package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.NhanVien;

public class KhuyenMaiDAO {
	private ConnectDB connectDB;

	public KhuyenMaiDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public List<KhuyenMai> getAllKhuyenMai() {
		List<KhuyenMai> listKM = new ArrayList<KhuyenMai>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM KhuyenMaiView";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				KhuyenMai km = new KhuyenMai(resultSet);
				listKM.add(km);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listKM;
	}

	public boolean createKhuyenMai(KhuyenMai km) {
		Connection connection = connectDB.getConnection();
		String query = "INSERT INTO KhuyenMai values(?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, km.getMaKhuyenMai());
			preparedStatement.setString(2, km.getMaKhuyenMai());
			preparedStatement.setDouble(3, km.getPhanTram());
			preparedStatement.setDouble(4, km.getGioiHan());
			preparedStatement.setDate(5, km.getNgayBatDau());
			preparedStatement.setDate(6, km.getNgayKetThuc());
			preparedStatement.setTime(7, km.getThoiDiemBatDau());
			preparedStatement.setTime(8, km.getThoiDiemKetThuc());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateKhuyenMai(KhuyenMai km, String maKhuyenMai) {
		Connection connection = connectDB.getConnection();
		String query = "UPDATE KhuyenMai set maKhuyenMai = ?, tenKhuyenMai = ?,phanTram = ?, gioiHan = ?, ngayBatDau = ?, ngayKetThuc = ?, thoiDiemBatDau = ?, thoiDiemKetThuc = ? where maKhuyenMai = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, km.getMaKhuyenMai());
			preparedStatement.setString(2, km.getTenKhuyenMai());
			preparedStatement.setDouble(3, km.getPhanTram());
			preparedStatement.setDouble(4, km.getGioiHan());
			preparedStatement.setDate(5, km.getNgayBatDau());
			preparedStatement.setDate(6, km.getNgayKetThuc());
			preparedStatement.setTime(7, km.getThoiDiemBatDau());
			preparedStatement.setTime(8, km.getThoiDiemKetThuc());
			preparedStatement.setString(9, maKhuyenMai);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<KhuyenMai> TimKiem(String ma, Date ngayBatDau, Date ngayKetThuc) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<KhuyenMai> ds = new ArrayList<KhuyenMai>();
		try {
			String query = "SELECT * FROM KhuyenMaiView WHERE (KhuyenMai_MaKhuyenMai LIKE ? OR ? IS NULL) AND (KhuyenMai_NgayBatDau = ? OR ? IS NULL) AND (KhuyenMai_NgayKetThuc = ? OR ? IS NULL) AND ( (KhuyenMai_NgayBatDau >= ? AND KhuyenMai_NgayKetThuc <= ?) OR (? IS NULL AND ? IS NULL))";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + ma + "%");
			pst.setDate(2, ngayBatDau);
			pst.setDate(3, ngayKetThuc);
			pst.setDate(4, ngayBatDau);
			pst.setDate(5, ngayKetThuc);
//			pst.setDate(6, ngayKetThuc);
//			pst.setDate(7, ngayBatDau);
//			pst.setDate(8, ngayKetThuc);
//			pst.setDate(9, ngayBatDau);
//			pst.setDate(10, ngayKetThuc);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				KhuyenMai nv = new KhuyenMai(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public KhuyenMai getKhuyenMaiTheoMa(String maKhuyenMai) {
		Connection con = connectDB.getConnection();
		KhuyenMai km = null;
		try {
			String query = "SELECT * FROM KhuyenMaiView where KhuyenMai_MaKhuyenMai = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, maKhuyenMai);
			ResultSet rs = pst.executeQuery();
			KhuyenMai kn = new KhuyenMai(rs);
			km = kn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return km;
	}

	public KhuyenMai getKhuyenMaiByTen(String khuyenMaiName) {
		Connection connection = connectDB.getConnection();
		KhuyenMai khuyenMai = null;

		try {
			String query = "SELECT * FROM KhuyenMaiView WHERE KhuyenMai_TenKhuyenMai = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, khuyenMaiName);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				khuyenMai = new KhuyenMai(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return khuyenMai;
	}

}