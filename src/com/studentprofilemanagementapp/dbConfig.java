package com.studentprofilemanagementapp;
/*
 * name:
 *    	 Viswas Vatte              
 * date:
 *     	November 28,2019
 * description:
 * this a simple student profile database management system.
 * it allows the user to:
 * 1) Create a new student profile record with fields as:
 * first name, last name email address, student id, gender and date of birth;
 * 2) Update students record( name)
 * 3) Search for a student from the database
 * 4) Delete a contact from the database
 * 5) list all records of students in the database on a tableView.
 * It's based on MySQL database and JavaFx graphical user interface.
 * 
 **/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConfig {
	//define database connection configuration variables
	 	protected String hostName="localhost";
	    protected String port="3306";
	    protected String dbName="students";
	    protected String dbUser="root";
	    protected String dbPass="";
	    Connection conn;

// this function returns connection to database or throws connection
	  //  related errors of connection fails
public Connection getConn() throws ClassNotFoundException, SQLException {
String connString="jdbc:mysql://"+hostName+":"+port+"/"+dbName;
Class.forName("com.mysql.jdbc.Driver");
conn= DriverManager.getConnection(connString,dbUser, dbPass);
    return conn;
}

//function to retrieve a list of all students
//in the students table
public ResultSet getAllStudents() 
		throws ClassNotFoundException, SQLException {
	String query= "SELECT * FROM students_table";
	PreparedStatement pst = getConn().prepareStatement(query);
	ResultSet resultset= pst.executeQuery();
	return resultset;
}

//function to create new record into database
public void addRecord(String firstName, String lastName, String Email, 
		String gender, String dateOfBirth, 
		int studentId) throws ClassNotFoundException, SQLException {
	String query= "INSERT INTO students_table(firstName, lastName,studentID,"
			+ " gender, birthDate, emailAddress) VALUES(?,?,?,?,?,?)";
	PreparedStatement preparedStatement= getConn().prepareStatement(query);
	preparedStatement.setString(1, firstName);
	preparedStatement.setString(2, lastName);
	preparedStatement.setInt(3, studentId);
	preparedStatement.setString(4, gender);
	preparedStatement.setString(5, dateOfBirth);
	preparedStatement.setString(6, Email);
	preparedStatement.execute();
}

//function to delete student record from database
public void deleteRec(int studentId) throws SQLException, ClassNotFoundException {
String query="DELETE FROM students_table WHERE studentID=?";
PreparedStatement ps= getConn().prepareStatement(query);
ps.setInt(1, studentId);
ps.execute();
}

//function to update student record
public void updateRecord(int studentId, String firstName, 
		String lastName, String Email)throws SQLException, ClassNotFoundException  {
	String query="UPDATE students_table SET firstName=?, lastName=?, emailAddress=?"
			+ " WHERE studentID=?";
	PreparedStatement ps= getConn().prepareStatement(query);
	ps.setString(1, firstName);
	ps.setString(2, lastName);
	ps.setString(3, Email);
	ps.setInt(4, studentId);
	ps.execute();	
}

//function to get single student record

public ResultSet getSingleRec(int studentId) 
		throws SQLException, ClassNotFoundException {
	String query= "SELECT * FROM students_table WHERE studentId=?";
	PreparedStatement pst = getConn().prepareStatement(query);
	pst.setInt(1, studentId);
	ResultSet resultset= pst.executeQuery();
	return resultset;	
}
}










