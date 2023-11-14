package dao;

import connectDB.ConnectDB;
import entity.LichSuGiaDichVu;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LichSuGiaDichVuDAO {
    private ConnectDB connectDB;

    public LichSuGiaDichVuDAO() {
        this.connectDB = ConnectDB.getInstance();
    }
    public List<LichSuGiaDichVu> getLichSuGiaDichVuByMaDichVu(String maDichVu) {
        List<LichSuGiaDichVu> lichSuGiaDichVuList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "{CALL GetLichSuGiaDichVuByMaDichVu(?)}";

        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, maDichVu);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LichSuGiaDichVu lichSuGiaDichVu = new LichSuGiaDichVu(resultSet);
                lichSuGiaDichVuList.add(lichSuGiaDichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lichSuGiaDichVuList;
    }

}
