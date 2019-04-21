package main.webapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFile {
	private static final String FILE_PATH = "C:/Users/barge/Documents/GitHub/core_dataset.xlsx";
	
	static List<Employee> getEmployeeFromExcel(Connection conn) throws SQLException {
        List<Employee> empList = new ArrayList<Employee>();
        String sqlQuery = "INSERT INTO employees (employeeName, employeeNumber, state, zip, age)"
				+ "VALUES"
				+ "(";
        FileInputStream fis = null;
        Statement stmt = conn.createStatement();
        try {
            fis = new FileInputStream(FILE_PATH);
 
            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);
 
            int numberOfSheets = workbook.getNumberOfSheets();            
            int countsheet = 0;
            while(countsheet < numberOfSheets) {
            	Sheet sheet = workbook.getSheetAt(countsheet);
            	Iterator rowIter = sheet.iterator();
            	while(rowIter.hasNext()) {
            		Employee emp = new Employee();
            		Row row = (Row) rowIter.next();
            		
            		Iterator cellIter = row.cellIterator();
            		
            		while(cellIter.hasNext()){
            			Cell cell = (Cell) cellIter.next();
            			
            			switch (cell.getCellTypeEnum()) {
	            	        case STRING:
	            	        	if(cell.getColumnIndex() == 0) {
	            	        		emp.setEmpName(cell.getRichStringCellValue().getString());
	            	        		sqlQuery = sqlQuery + "'"+emp.getEmpName() + "'"+ ", ";
	            	        	} else if(cell.getColumnIndex() == 2){
	            	        		emp.setEmpState(cell.getRichStringCellValue().getString());
	            	        		sqlQuery = sqlQuery + "'"+ emp.getEmpState() + "'"+ ", ";
	            	        	} 
	            	            break;
	            	        case NUMERIC:
	            	        	if(cell.getColumnIndex() == 3) {
	            	        		emp.setEmpZip(cell.getNumericCellValue());
	            	        		sqlQuery = sqlQuery + Double.toString(emp.getEmpZip()) + ", ";
	            	        	} else if(cell.getColumnIndex() == 1) {
	            	        		emp.setEmpNumber(cell.getNumericCellValue());
	            	        		sqlQuery = sqlQuery + Double.toString(emp.getEmpNumber()) + ", ";
	            	        	} else if(cell.getColumnIndex() == 5) {
	            	        		emp.setEmpAge(cell.getNumericCellValue());
	            	        		sqlQuery = sqlQuery + Double.toString(emp.getEmpAge()) + ") ";
	            	        	}
	            	            break;
	            	        default:
	            	            System.out.print("");
            			}
            		}
            		empList.add(emp);
            		if(emp != null){
            			if(!sqlQuery.equalsIgnoreCase("INSERT INTO employees (employeeName, employeeNumber, state, zip, age)VALUES(")){
                			stmt.executeUpdate(sqlQuery);
                    		sqlQuery = "INSERT INTO employees (employeeName, employeeNumber, state, zip, age)"
                    				+ "VALUES"
                    				+ "(";
            			}
            		}
            	}
            	countsheet = countsheet + 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empList;
    }
}
