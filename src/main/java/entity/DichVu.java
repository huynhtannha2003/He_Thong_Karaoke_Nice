package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import enums.TrangThaiDichVu;

public class DichVu {
    private String maDichVu;
    private String tenDichVu;
    private int soLuong;
    private String hinhAnh;
    private TrangThaiDichVu trangThai;
    private LoaiDichVu loaiDichVu;
    private List<LichSuGiaDichVu> lichSuGiaDichVuList;

    public DichVu() {
    }

    public DichVu(String maDichVu, String tenDichVu, int soLuong, String hinhAnh, TrangThaiDichVu trangThai, LoaiDichVu loaiDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
        this.loaiDichVu = loaiDichVu;
    }

    public DichVu(String maDichVu, String tenDichVu, int soLuong, TrangThaiDichVu trangThai, LoaiDichVu loaiDichVu, List<LichSuGiaDichVu> lichSuGiaDichVuList) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.loaiDichVu = loaiDichVu;
        this.lichSuGiaDichVuList = new ArrayList<LichSuGiaDichVu>();
    }

    public DichVu(ResultSet rs) throws SQLException {
        this.maDichVu = rs.getString("DichVu_MaDichVu");
        this.tenDichVu = rs.getString("DichVu_TenDichVu");
        this.soLuong = rs.getInt("DichVu_SoLuong");
        this.trangThai = TrangThaiDichVu.values()[rs.getInt("DichVu_TrangThai")];
        this.hinhAnh = rs.getString("DichVu_HinhAnh");
        this.loaiDichVu = new LoaiDichVu(rs);
        this.lichSuGiaDichVuList = new ArrayList<LichSuGiaDichVu>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DichVu dichVu = (DichVu) o;
        return Objects.equals(maDichVu, dichVu.maDichVu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDichVu);
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public TrangThaiDichVu getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiDichVu trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiDichVu getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public List<LichSuGiaDichVu> getLichSuGiaDichVuList() {
        return lichSuGiaDichVuList;
    }

    public void setLichSuGiaDichVuList(List<LichSuGiaDichVu> lichSuGiaDichVuList) {
        this.lichSuGiaDichVuList = lichSuGiaDichVuList;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Double getGia() {
        int lichSuGiaDichVuLastIndex = lichSuGiaDichVuList.size() - 1;
        return this.getLichSuGiaDichVuList().get(lichSuGiaDichVuLastIndex).getGia();
    }

    @Override
    public String toString() {
        return "DichVu{" +
                "maDichVu='" + maDichVu + '\'' +
                ", tenDichVu='" + tenDichVu + '\'' +
                ", soLuong=" + soLuong +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", trangThai=" + trangThai +
                ", loaiDichVu=" + loaiDichVu +
                ", lichSuGiaDichVuList=" + lichSuGiaDichVuList +
                '}';
    }
}
