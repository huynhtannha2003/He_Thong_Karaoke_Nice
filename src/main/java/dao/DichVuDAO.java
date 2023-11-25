package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;

import entity.LichSuGiaDichVu;
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
				LichSuGiaDichVu lichSuGiaDichVu = new LichSuGiaDichVu(resultSet);
				dichVu.setLichSuGiaDichVuList(List.of(lichSuGiaDichVu));
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	public List<DichVu> getDichVu() {
		List<DichVu> dichVuList = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM DichVuView";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DichVu dichVu = new DichVu(resultSet);
				LichSuGiaDichVu lichSuGiaDichVu = new LichSuGiaDichVu(resultSet);
				dichVu.setLichSuGiaDichVuList(List.of(lichSuGiaDichVu));
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	public List<LoaiDichVu> getLoaiDichVu() {
		List<LoaiDichVu> list = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM LoaiDichVuView";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LoaiDichVu loai = new LoaiDichVu(resultSet);
				list.add(loai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * Thêm dịch vụ
	 * 
	 * @param dichVu : dịch vụ cần câp nhật
	 *
	 */
	public boolean addDichVu(DichVu dichVu) {
		Connection con = connectDB.getConnection();
		String query = "insert into DichVu values(?,?,?,?,?)";
		int n = 0;
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(query);
			pre.setString(1, dichVu.getMaDichVu());
			pre.setString(2, dichVu.getTenDichVu());
			pre.setInt(3, dichVu.getSoLuong());
			pre.setString(4, dichVu.getLoaiDichVu().getMaLoaiDichVu());
			pre.setInt(5, dichVu.getTrangThai().getValue());
			n = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean addLichSuGiaGiaoDich(LichSuGiaDichVu lichSu, DichVu dv) {
		Connection con = connectDB.getConnection();
		String query = "insert into LichSuGiaDichVu values (?,?,?,?,?,?,?)";
		int n = 0;
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(query);
			pre.setString(1, lichSu.getMaLichSuGiaDichVu());
			pre.setDate(2, lichSu.getNgayBatDau());
			pre.setDate(3, lichSu.getNgayKetThuc());
			pre.setTime(4, lichSu.getThoiDiemBatDau());
			pre.setTime(5, lichSu.getThoiDiemKetThuc());
			pre.setDouble(6, lichSu.getGia());
			pre.setString(7, dv.getMaDichVu());

			n = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

// 
//	/*
//	 * Sửa
//	 * 
//	 */
	public boolean updateDichVu(DichVu dichVu, String ma) {
		Connection con = connectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {

			pre = con.prepareStatement(
					"update DichVu set tenDichVu = ?,soLuong = ?, maLoaiDichVu = ?,trangThai = ? where maDichVu = ?");
			pre.setString(1, dichVu.getTenDichVu());
			pre.setInt(2, dichVu.getSoLuong());
			pre.setString(3, dichVu.getLoaiDichVu().getMaLoaiDichVu());
			pre.setInt(4, dichVu.getTrangThai().getValue());
			pre.setString(5, ma);
			n = pre.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * Tìm dịch vụ theo mã
	 * 
	 */

	private void searchObject(String columnName, String inputValue) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "SELECT * FROM DichVuLichSuGiaByConditionTimeView WHERE " + columnName + " LIKE ?";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setString(1, "%" + inputValue + "%");
			ResultSet resultSet = preparedStatement.executeQuery(); 
				while (resultSet.next()) {
					DichVu dv = new DichVu(resultSet);
					dichVuList.add(dv);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<DichVu> getDichVuTheoMa(String ma) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "select * from DichVuLichSuGiaByConditionTimeView where DichVu_MaDichVu = ?";
		try {
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery();
			DichVu dichVu = new DichVu(rs);
			dichVuList.add(dichVu);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dichVuList;
	}

	/*
	 * Tìm dịch vụ theo tên
	 */

	public List<DichVu> getDSDichVuTheoTen(String ten) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "select * from DichVuLichSuGiaByConditionTimeView where DichVu_TenDichVu like ?";
		try {
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery(query);
			pre.setString(1, "%" + ten + "%");

			DichVu dichVu = new DichVu(rs);
			dichVuList.add(dichVu);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	/*
	 * Tìm dịch vụ theo giá
	 */

	public List<DichVu> getDSDichVuTheoGia(double gia) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "select * from DichVuLichSuGiaByConditionTimeView where LichSuGiaDichVu_Gia = ?";
		try {
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery(query);
			pre.setDouble(1, gia);
			while (rs.next()) {
				DichVu dichVu = new DichVu(rs);
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	/*
	 * Tìm dịch vụ theo số lượng
	 */

	public List<DichVu> getDSDichVuTheoSoLuong(int soLuong) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "select * from DichVuLichSuGiaByConditionTimeView where DichVu_SoLuong = ?";
		try {
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery(query);
			pre.setInt(1, soLuong);
			while (rs.next()) {
				DichVu dichVu = new DichVu(rs);
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	/*
	 * Tìm dịch vụ theo số lượng
	 */

	public List<DichVu> getDSDichVuTheoTenLoai(LoaiDichVu loai) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "select * from DichVuLichSuGiaByConditionTimeView where LoaiDichVu_TenLoaiDichVu like ?";
		try {
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery(query);
			pre.setString(1, loai.getTenLoaiDichVu());
			while (rs.next()) {
				DichVu dichVu = new DichVu(rs);
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

	/*
	 * Tìm dịch vụ theo trạng thái
	 */

	public List<DichVu> getDSDichVuTheoTrangThai(TrangThaiDichVu trangThai) {
		Connection con = connectDB.getConnection();
		List<DichVu> dichVuList = new ArrayList<>();
		String query = "select * from DichVuLichSuGiaByConditionTimeView where LoaiDichVu_TenLoaiDichVu like ?";
		try {
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery(query);
			pre.setInt(1, trangThai.getValue());
			while (rs.next()) {
				DichVu dichVu = new DichVu(rs);
				dichVuList.add(dichVu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dichVuList;
	}

}
