package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			pst.setString(2, ma);
			pst.setDate(3, ngayBatDau);
			pst.setDate(4, ngayBatDau);
			pst.setDate(5, ngayKetThuc);
			pst.setDate(6, ngayKetThuc);
			pst.setDate(7, ngayBatDau);
			pst.setDate(8, ngayKetThuc);
			pst.setDate(9, ngayBatDau);
			pst.setDate(10, ngayKetThuc);
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

	public List<NhanVien> TimKiemTheoMaNhanVien(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_MaNhanVien LIKE ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + giaTriTimKiem + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<NhanVien> TimKiemTheoTenNhanVien(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_Ten LIKE ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + giaTriTimKiem + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<NhanVien> TimKiemTheoChucVu(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_ChucVu LIKE ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + giaTriTimKiem + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<NhanVien> TimKiemTheoSDT(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_Sdt LIKE ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + giaTriTimKiem + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<NhanVien> TimKiemTheoEmail(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_Email LIKE ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + giaTriTimKiem + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<NhanVien> TimKiemTheoDiaChi(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_DiaChi LIKE ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + giaTriTimKiem + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<NhanVien> TimKiemTheoTrangThai(String giaTriTimKiem) throws SQLException {
		Connection con = connectDB.getConnection();
		int n = 0;
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			if (giaTriTimKiem.equalsIgnoreCase("vô hiệu")) {
				n = 0;
			} else if (giaTriTimKiem.equalsIgnoreCase("hiệu lực")) {
				n = 1;
			}
			String query = "SELECT * FROM NhanVienView WHERE NhanVien_TrangThai = ?";

			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, n);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
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