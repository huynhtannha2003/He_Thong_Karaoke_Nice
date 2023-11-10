package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDonDAO {
	private ConnectDB connectDB;

    public HoaDonDAO() throws SQLException {
        this.connectDB = ConnectDB.getInstance();
    }
    
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Connection connection = connectDB.getConnection();
        String query = "SELECT * FROM HoaDonDetailsView";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HoaDon hoaDon = new HoaDon(resultSet);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoaDonList;
    }
}
