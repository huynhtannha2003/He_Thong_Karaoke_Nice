package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DichVu;

public class QLDichVu_DAO {
	private ConnectDB connectDB;
	
	public QLDichVu_DAO() throws SQLException{
		// TODO Auto-generated constructor stub
		this.connectDB = ConnectDB.getInstance(); 
	}
	
	public List<DichVu> getAllDichVu(){
		List<DichVu> dichVuList = new ArrayList<>();
		Connection connection = connectDB.getConnection();
		String query = "SELECT * FROM DichVu";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet  = preparedStatement.executeQuery();
			while(resultSet.next()) {
				DichVu dichVu = new DichVu(resultSet);
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dichVuList;
	}
}
