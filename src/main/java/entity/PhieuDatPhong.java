package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PhieuDatPhong {
    private String maPhieuDatPhong;
    private Time thoiGianBatDau;
    private Time thoiGianKetThuc;
    private HoaDon hoaDon;
    private Phong phong;
    List<ChiTietDatDichVu> chiTietDatDichVuList;

    public PhieuDatPhong() {
    }

    public PhieuDatPhong(String maPhieuDatPhong, Time thoiGianBatDau, Time thoiGianKetThuc, HoaDon hoaDon,
                         Phong phong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.hoaDon = hoaDon;
        this.phong = phong;
        chiTietDatDichVuList = new ArrayList<>();
    }

    public PhieuDatPhong(ResultSet rs) throws SQLException {
        this.maPhieuDatPhong = rs.getString("PhieuDatPhong_MaPhieuDatPhong");
        this.thoiGianBatDau = rs.getTime("PhieuDatPhong_ThoiGianBatDau");
        this.thoiGianKetThuc = rs.getTime("PhieuDatPhong_ThoiGianKetThuc");
        this.phong = new Phong(rs);
        chiTietDatDichVuList = new ArrayList<>();
    }

    public String getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public Time getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Time thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Time getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Time thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public List<ChiTietDatDichVu> getChiTietDatDichVuList() {
        return chiTietDatDichVuList;
    }

    public void setChiTietDatDichVuList(List<ChiTietDatDichVu> chiTietDatDichVuList) {
        this.chiTietDatDichVuList = chiTietDatDichVuList;
    }

    public double tinhTongTienDichVu() {
        final double[] totalPrice = {0.0};

        chiTietDatDichVuList.forEach(chiTietDatDichVu -> {
            totalPrice[0] += chiTietDatDichVu.getDichVu().getLichSuGiaDichVuList().get(0).getGia() *
                    chiTietDatDichVu.getSoLuong();
        });

        return totalPrice[0];
    }

    public double tinhTongTienPhong() {
        double totalPrice = 0.0;

        double hourlyRate = this.getPhong().getLoaiPhong().getLichSuGiaPhongList().get(0).getGia();

        Duration totalTime;
        totalTime = tinhGio(this.getThoiGianBatDau(), this.getThoiGianKetThuc());

        double totalHours = Math.ceil(totalTime.toMinutes() / 60.0);
        if (totalHours == 0) {
            totalHours = 1;
        }
        totalPrice += hourlyRate * totalHours;

        return totalPrice;
    }

    private Duration tinhGio(Time checkInTime, Time checkOutTime) {
        LocalTime checkInLocalTime = checkInTime.toLocalTime();
        LocalTime checkOutLocalTime = checkOutTime.toLocalTime();
        return Duration.between(checkInLocalTime, checkOutLocalTime);
    }
}