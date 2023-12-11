package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.LichSuGiaPhong;
import entity.PhieuDatPhong;
import entity.Phong;

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
        String query = "{CALL UpdatePaymentDetails(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, hoaDon.getMaHoaDon());
            statement.setDouble(2, hoaDon.getTongTien());
            statement.setTime(3, hoaDon.getThoiDiemThanhToan());
            statement.setString(4, hoaDon.getPhieuDatPhongList().get(hoaDon.getPhieuDatPhongList().size() - 1).getMaPhieuDatPhong());
            statement.setTime(5, hoaDon.getPhieuDatPhongList().get(hoaDon.getPhieuDatPhongList().size() - 1).getThoiGianKetThuc());
            statement.setString(6, hoaDon.getKhuyenMai().getMaKhuyenMai());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean bookRoomBefore(String maKhachHang, String maNhanVien, String maPhong, Time thoiGianBatDau,
                                  Date ngayThanhToan) {
        Connection connection = connectDB.getConnection();
        String query = "{CALL BookRoomBefore(?, ?, ?, ?, ?)}";

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

    public List<String[]> getDanhSachPhieu() {
        Connection con = connectDB.getConnection();
        List<String[]> list = new ArrayList<String[]>();
        String sql = "SELECT * FROM DanhSachPhieu_View";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                String maPhieu = rs.getString(1);
                String maHoaDon = rs.getString(2);
                String maPhong = rs.getString(3);
                String tenKH = rs.getString(4);
                String sdt = rs.getString(5);
                Time thoiGianBD = rs.getTime(6);
                String TimeBD = thoiGianBD.toString();
                String[] s = {i++ + "", maPhieu, maHoaDon, maPhong, tenKH, sdt, TimeBD};
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public void xoaPhieuDatPhongCho(String maHoaDon) {
        Connection con = connectDB.getConnection();
        String sql = "{ CALL xoaPhieuDatPhongCho(?) }";

        try (CallableStatement statement = con.prepareCall(sql)) {
            statement.setString(1, maHoaDon);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> timKiemPhieuDatPhong(String sdt) {
        List<String[]> list = new ArrayList<>();
        Connection con = connectDB.getConnection();
        String sql = "SELECT * FROM DanhSachPhieu_View WHERE sdt LIKE ?";
        int i = 1;
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + sdt + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String s[] = {i++ + "", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getTime(6).toString()};
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> getHoaDonBySDTAndTime(String soDienThoaiKhachHang) {
        Connection connection = connectDB.getConnection();
        List<HoaDon> hoaDonList = new ArrayList<>();
        String query = "{CALL GetHoaDonBySDTAndTime(?)}";
        try (PreparedStatement statement = connection.prepareCall(query)) {
            statement.setString(1, soDienThoaiKhachHang);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon(resultSet);

                List<PhieuDatPhong> phieuDatPhongList = new ArrayList<>();
                phieuDatPhongList.add(new PhieuDatPhong(resultSet));

                hoaDon.setPhieuDatPhongList(phieuDatPhongList);

                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

}
