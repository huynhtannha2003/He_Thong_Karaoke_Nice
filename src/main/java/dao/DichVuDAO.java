package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;

import entity.LoaiDichVu;
import enums.TrangThaiDichVu;

public class DichVuDAO {
	private ConnectDB connectDB;

	public DichVuDAO() {
			this.connectDB = ConnectDB.getInstance();
	}

	public List<DichVu> getAllDichVu() {
		List<DichVu> dichVuList = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM DichVuLichSuGiaByConditionTimeView";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DichVu dichVu = new DichVu(resultSet);
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	/*
	 * Thêm dịch vụ
	 * 
	 * @param dichVu : dịch vụ cần câp nhật
	 *
	 */
//	public boolean addDichVu(DichVu dichVu) {
//		Connection con = connectDB.getConnection();
//		String query = "insert into DichVu values(?,?,?,?,?,?)";
//		int n = 0;
//		PreparedStatement pre = null;
//		try {
//			pre = con.prepareStatement(query);
//			pre.setString(1, dichVu.getMaDichVu());
//			pre.setString(2, dichVu.getTenDichVu());
//			pre.setString(3, dichVu.get());
//			pre.setInt(4, dichVu.getSoLuong());
//			pre.setString(5, dichVu.getLoaiDichVu().getMaLoaiDichVu());
//			pre.setString(6, dichVu.getTrangThai().toString());
//			pre.setBytes(7, dichVu.getHinhAnh());
//			n = pre.executeUpdate();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				pre.close();
//			} catch (SQLException e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;
//	}
//
//	/*
//	 * Cập nhật theo mã dịch vụ
//	 * 
//	 */
//	public boolean updateDichVu(DichVu dichVu, boolean capNhatAnh) {
//		Connection con = connectDB.getConnection();
//		PreparedStatement pre = null;
//		int n = 0;
//		try {
//			// Cập nhật ảnh
//			if (capNhatAnh = true) {
//				pre = con.prepareStatement(
//						"update DichVu set tenDichVu = ?,gia = ?, soLuong = ?, maLoaiDichVu = ?,trangThai = ?, hinhAnh = ? where maDichVu = ?");
//				pre.setString(1, dichVu.getTenDichVu());
//				pre.setDouble(2, dichVu.getGia());
//				pre.setInt(3, dichVu.getSoLuong());
//				pre.setString(4, dichVu.getLoaiDichVu().getMaLoaiDichVu());
//				pre.setString(5, dichVu.getTrangThai().toString());
//				pre.setBytes(6, dichVu.getHinhAnh());
//				pre.setString(7, dichVu.getMaDichVu());
//				n = pre.executeUpdate();
//			} else {
//				// Không cập nhật ảnh
//				pre = con.prepareStatement(
//						"update DichVu set tenDichVu = ?,gia = ? ,soLuong = ?, maLoaiDichVu = ?,trangThai = ? where maDichVu = ?");
//				pre.setString(1, dichVu.getTenDichVu());
//				pre.setDouble(2, dichVu.getGia());
//				pre.setInt(3, dichVu.getSoLuong());
//				pre.setString(4, dichVu.getLoaiDichVu().getMaLoaiDichVu());
//				pre.setString(5, dichVu.getTrangThai().toString());
//				pre.setString(6, dichVu.getMaDichVu());
//				n = pre.executeUpdate();
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				pre.close();
//			} catch (SQLException e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;
//	}
//	/*
//	 * Tìm dịch vụ theo mã
//	 * 
//	 */
//	public List<DichVu> getDichVuTheoMa(String ma) {
//		Connection con = connectDB.getConnection();
//		List<DichVu> dichVuList = new ArrayList<>();
//		PreparedStatement pre = null;
//		String query = "select * from DichVu where maDichVu = ?";
//		try {
//			pre = con.prepareStatement(query);
//			pre.setString(1,ma);
//			ResultSet rs = pre.executeQuery();
//			while(rs.next()) {
//				DichVu dichVu = new DichVu(rs);
//				dichVuList.add(dichVu);
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				pre.close();
//			} catch (SQLException e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return dichVuList;
//	}
//	
//	/*
//	 * Tìm dịch vụ theo tên
//	 */
//	
//	public List<DichVu> getDSDichVuTheoTen(String ten) {
//		Connection con = connectDB.getConnection();
//		List<DichVu> dichVuList = new ArrayList<>();
//		try {
//			String query = "select * from DichVu where tenDichVu like N'%" + ten + "%'";
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(query);
//			while (rs.next()) {
//				DichVu dichVu = new DichVu(rs);
//				dichVuList.add(dichVu);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dichVuList;
//	}
//	
//	/*
//	 * Tìm dịch vụ theo 
//	 */
}
