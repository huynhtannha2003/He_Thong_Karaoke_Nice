package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.NhanVien;
import enums.TrangThaiNhanVien;
import view.GD_QuanLyNhanVien;

public class NhanVienDAO {
    private ConnectDB connectDB;

    public NhanVienDAO() {
        try {
            this.connectDB = ConnectDB.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> listNV = new ArrayList<NhanVien>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM NhanVienView";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NhanVien nv = new NhanVien(resultSet);
                listNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNV;
    }

    public boolean createNhanVien(NhanVien nv) {
        Connection connection = connectDB.getConnection();
        String query = "INSERT INTO NhanVien values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nv.getMaNhanVien());
            preparedStatement.setString(2, nv.getTen());
            preparedStatement.setString(3, nv.getChucVu());
            preparedStatement.setString(4, nv.getSdt());
            preparedStatement.setString(5, nv.getEmail());
            preparedStatement.setString(6, nv.getDiaChi());
            if (nv.getTrangThai() == TrangThaiNhanVien.HIEU_LUC) {
                preparedStatement.setInt(7, 1);
            } else {
                preparedStatement.setInt(7, 0);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateNhanVien(NhanVien nv, String maNV) {
        Connection connection = connectDB.getConnection();
        String query = "UPDATE NhanVien set maNhanVien = ?, ten = ?,chucVu = ?, sdt = ?, email = ?, diaChi = ?, trangThai = ? where maNhanVien = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nv.getMaNhanVien());
            preparedStatement.setString(2, nv.getTen());
            preparedStatement.setString(3, nv.getChucVu());
            preparedStatement.setString(4, nv.getSdt());
            preparedStatement.setString(5, nv.getEmail());
            preparedStatement.setString(6, nv.getDiaChi());
            preparedStatement.setString(8, maNV);
            if (nv.getTrangThai() == TrangThaiNhanVien.HIEU_LUC) {
                preparedStatement.setInt(7, 1);
            } else {
                preparedStatement.setInt(7, 0);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public NhanVien getNhanVienTheoMa(String maNV) throws SQLException {
        NhanVien nv = new NhanVien();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM NhanVien where maNhanVien = " + maNV);
            ResultSet rs = pstm.executeQuery();
            NhanVien nv1 = new NhanVien(rs);
            nv = nv1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }

    public List<NhanVien> getNhanVienTheoTen(String tenNV) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            PreparedStatement pstm = con
                    .prepareStatement("SELECT * FROM NhanVien where maNhanVien like N'%" + tenNV + "%");
            ResultSet rs = pstm.executeQuery();
            NhanVien nv1 = new NhanVien(rs);
            list.add(nv1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVien> getNhanVienTheoChucVu(String chucVu) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM NhanVien where maNhanVien = " + chucVu);
            ResultSet rs = pstm.executeQuery();
            NhanVien nv1 = new NhanVien(rs);
            list.add(nv1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVien> getNhanVienTheoSDT(String sdt) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            PreparedStatement pstm = con
                    .prepareStatement("SELECT * FROM NhanVien where maNhanVien like N'%" + sdt + "%");
            ResultSet rs = pstm.executeQuery();
            NhanVien nv1 = new NhanVien(rs);
            list.add(nv1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVien> getNhanVienTheoEmail(String tenNV) throws SQLException {
        Connection con = ConnectDB.getInstance().getConnection();
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            PreparedStatement pstm = con
                    .prepareStatement("SELECT * FROM NhanVien where maNhanVien like N'%" + tenNV + "%");
            ResultSet rs = pstm.executeQuery();
            NhanVien nv1 = new NhanVien(rs);
            list.add(nv1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVien> TimKiemTatCa(String giaTriTimKiem) throws SQLException {
        Connection con = connectDB.getConnection();
        ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
        try {
            int so = -1;
            if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
                so = Integer.parseInt(giaTriTimKiem);
            }
            String query = "SELECT * FROM NhanVienView WHERE NhanVien_MaNhanVien LIKE ? OR NhanVien_Ten LIKE ? OR NhanVien_ChucVu LIKE ? OR NhanVien_Sdt LIKE ? OR NhanVien_Email LIKE ? OR NhanVien_DiaChi LIKE ? OR NhanVien_TrangThai = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, "%" + giaTriTimKiem + "%");
            pst.setString(2, "%" + giaTriTimKiem + "%");
            pst.setString(3, "%" + giaTriTimKiem + "%");
            pst.setString(4, "%" + giaTriTimKiem + "%");
            pst.setString(5, "%" + giaTriTimKiem + "%");
            pst.setString(6, "%" + giaTriTimKiem + "%");
            pst.setInt(7, so);
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
}
