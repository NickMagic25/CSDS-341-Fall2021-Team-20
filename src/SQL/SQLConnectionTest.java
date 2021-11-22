package SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLConnectionTest {
	public static void main(String[] args) {
		System.out.println("Performing setup");
		String username = "team20";
		String password = "ArnOld2021$";
		String cnnString = "jdbc:sqlserver://arnold-tracker.database.windows.net:1433;database=arnold-tracker;user=team20@arnold-tracker;password=ArnOld2021$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		SQLConnectionTest azure = new SQLConnectionTest();
		System.out.println("connecting");
		String sql = "Select * From [Has] Where [User_ID] = 1";
		azure.SelectAzureSQL(username, password, sql, cnnString);
	}
	
	private void SelectAzureSQL(String userName, String userPassword, String sql, String cnnStr) {
		System.out.println("Selecting data");
		ResultSet resultSet = null;
		try(Connection cnn = DriverManager.getConnection(cnnStr); Statement statement = cnn.createStatement();){
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
}
