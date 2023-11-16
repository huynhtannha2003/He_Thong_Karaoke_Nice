package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import enums.TrangThaiNhanVien;

public class KhachHangDAO {
	private ConnectDB connectDB;

	public KhachHangDAO() {
		this.connectDB = ConnectDB.getInstance();
	}

	public List<KhachHang> getAllKhachHang() {
		List<KhachHang> listKH = new ArrayList<KhachHang>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM KhachHangView";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				KhachHang kh = new KhachHang(resultSet);
				listKH.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listKH;
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

	public boolean createKhachHang(KhachHang kh) {
		Connection connection = connectDB.getConnection();
		String query = "INSERT INTO KhachHang values(?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, kh.getMaKhachHang());
			preparedStatement.setString(2, kh.getTenKhachHang());
			preparedStatement.setString(3, kh.getSdt());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateKhachHang(KhachHang kh, String maKH) {
		Connection connection = connectDB.getConnection();
		String query = "UPDATE KhachHang set maKhachHang = ?, tenKhachHang = ?, sdt = ? where maKhachHang = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, kh.getMaKhachHang());
			preparedStatement.setString(2, kh.getTenKhachHang());
			preparedStatement.setString(3, kh.getSdt());
			preparedStatement.setString(4, kh.getMaKhachHang());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<KhachHang> timKiem(String maKhachHang, String tenKhachHang) {
		Connection con = connectDB.getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			String query = "SELECT * FROM KhachHangView WHERE KhachHang_MaKhachHang LIKE ? AND KhachHang_TenKhachHang LIKE ? ";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, "%" + maKhachHang + "%");
			pst.setString(2, "%" + tenKhachHang + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				KhachHang nv = new KhachHang(rs);
				ds.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
