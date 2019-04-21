package main.webapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ConnectDB {

	public static void createNewDatabase(String fileName) {
		// TODO Auto-generated method stub
		String url = "jdbc:sqlite:C:/" + fileName;
		String query = "CREATE TABLE IF NOT EXISTS employees (" 
					+ "employeeName varchar(80) not null,"
					+ "employeeNumber int not null,"
					+ "state varchar(32) not null,"
					+ "zip int not null,"
					+ "age int not null,"
					+ "PRIMARY KEY (employeeNumber))";
		String selectQuery = "SELECT * from employees";
		
		try(Connection conn = DriverManager.getConnection(url);
				Statement sm = conn.createStatement();) {
			if(conn!= null){
				sm.execute(query);
				List<Employee> employeeList = ReadExcelFile.getEmployeeFromExcel(conn);
				ResultSet rs    = sm.executeQuery(selectQuery);
				while(rs.next()){
					System.out.println(rs.getString("employeeName") +  "\t" + 
							rs.getInt("employeeNumber") +  "\t" +
                            rs.getString("state") + "\t" +
                            rs.getInt("zip") + "\t" + 
                            rs.getInt("age"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
