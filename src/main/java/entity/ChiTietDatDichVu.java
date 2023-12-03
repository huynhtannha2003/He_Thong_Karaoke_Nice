package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietDatDichVu {
    private int soLuong;
    private PhieuDatPhong phieuDatPhong;
    private DichVu dichVu;

    public ChiTietDatDichVu() {
    }

    public ChiTietDatDichVu(int soLuong, PhieuDatPhong phieuDatPhong, DichVu dichVu) {
        this.soLuong = soLuong;
        this.phieuDatPhong = phieuDatPhong;
        this.dichVu = dichVu;
    }

    public ChiTietDatDichVu(ResultSet rs) throws SQLException {
        this.soLuong = rs.getInt("ChiTietDatDichVu_SoLuong");
        this.dichVu = new DichVu(rs);
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public PhieuDatPhong getPhieuDatPhong() {
        return phieuDatPhong;
    }

    public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        this.phieuDatPhong = phieuDatPhong;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    @Override
    public String toString() {
        return "ChiTietDatDichVu{" +
                "soLuong=" + soLuong +
                ", phieuDatPhong=" + phieuDatPhong +
                ", dichVu=" + dichVu +
                '}';
    }

    public double getDonGia() {
        int lichSuGiaDichVuLastIndex = this.getDichVu().getLichSuGiaDichVuList().size() - 1;
        return this.getDichVu().getLichSuGiaDichVuList().get(lichSuGiaDichVuLastIndex).getGia();
    }

    public double tinhTienDichVu() {
        return getDonGia() * soLuong;
    }
}