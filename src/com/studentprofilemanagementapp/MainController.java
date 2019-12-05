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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {
	dbConfig dbconfig;
	ResultSet rs = null;
	int count=0;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField studentID;

	@FXML
	private CheckBox maleCheckBox;

	@FXML
	private CheckBox femaleCheckBox;

	@FXML
	private DatePicker birthDate;

	@FXML
	private Button clearFieldsButton;

	@FXML
	private Button addStudentButton;

	@FXML
	private TextArea resultConsole;

	@FXML
	private Button searchBtn;

	@FXML
	private Button updateBtn;

	@FXML
	private Button deleteBtn;

	@FXML
	private Button loadAllBtn;

	@FXML
	private TextField crudStudentId;

	@FXML
	private TextField crudNewLName;
	 @FXML
	private TextField crudNewFName;
	@FXML
	private TableView<StudentModel> showResultsTable;
	@FXML
	private TableColumn<StudentModel, String> colFirstName;

	@FXML
	private TableColumn<StudentModel, String> colLastName;

	@FXML
	private TableColumn<StudentModel, String> colStudentId;

	@FXML
	private TableColumn<StudentModel, String> colBirthDate;

	@FXML
	private TableColumn<StudentModel, String> colEmail;

	@FXML
	private TableColumn<StudentModel, String> colGender;
	// set up an observable list collection to dynamically populate it with sets of
	// database records in form of a studentModel object
	ObservableList<StudentModel> oblist = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		// on load all event, cell the cell values as in the studentModel class

		loadAllBtn.setOnAction(event -> {
		
			setCellProperties(0);

		});
		// handle event for when the add student button is clicked
		addStudentButton.setOnAction(event -> {
			try {
				createUser();
				resultConsole.setText("record added successfully");
			} catch (ClassNotFoundException | SQLException e) {
				resultConsole.setText("Error: " + e.getMessage());
				return;
			}

		});
		
		deleteBtn.setOnAction(event->{
			int studentId=Integer.parseInt(crudStudentId.getText());
			try {
				dbconfig.deleteRec(studentId);
				setCellProperties(studentId);
				resultConsole.setText("Record was deleted successfully");
			} catch (ClassNotFoundException | SQLException e) {
				resultConsole.setText("Error: " + e.getMessage());
				return;
			}
		});
		//when  an event occurs on the update button this block of code will be triggered to update the student record.
		updateBtn.setOnAction(event->{
			String Email = crudNewLName.getText().charAt(0) + "" +  crudNewFName.getText().toLowerCase() + "@saumag.edu";
			try {
				dbconfig.updateRecord(Integer.parseInt(crudStudentId.getText().trim()), crudNewFName.getText(), crudNewLName.getText(), Email);
				setCellProperties(Integer.parseInt(crudStudentId.getText().trim()));
				resultConsole.setText("Record was updated successfully");
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				resultConsole.setText("Error: " + e.getMessage());
				return;
			}
		});
		//search button event handling
		searchBtn.setOnAction(event->{
			
			setCellProperties(Integer.parseInt(crudStudentId.getText().trim()));
			resultConsole.setText(count+" Result(s) found");
			
		});
		//clear button event handling
		clearFieldsButton.setOnAction(event->{
			clearFields();
		});
		
	}
	
	private void setCellProperties(int studentId) {
		showResultsTable.getItems().clear();
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		dbconfig = new dbConfig();
		try {
			if(studentId==0) {
				rs = dbconfig.getAllStudents();
			}else {
				rs = dbconfig.getSingleRec(studentId);
			}
			
			// loop through the results adding data in form of models into the observable
			// list
			
			while (rs.next()) {
				oblist.add(
						new StudentModel(rs.getString("firstName"), rs.getString("lastName"), rs.getString("studentId"),
								rs.getString("gender"), rs.getString("emailAddress"), rs.getString("birthDate")));
				count ++;
			}
			// pass the observable list to showResults Table for display
			showResultsTable.setItems(oblist);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			resultConsole.setText("Error: " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			resultConsole.setText("Error: " + e.getMessage());
		}
	}

	// Method to add user to database. executed when add student button is clicked
	private void createUser() throws ClassNotFoundException, SQLException {
		dbconfig = new dbConfig();
		String fName = firstName.getText().trim();
		String lName = lastName.getText().trim();
		String Email = lName.charAt(0) + "" + fName.toLowerCase() + "@saumag.edu";
		String gender = "";
		String DoB = birthDate.getValue().toString();
		int sId = Integer.parseInt(studentID.getText());
		if (maleCheckBox.isSelected()) {
			gender = "m";
		} else if (femaleCheckBox.isSelected()) {
			gender = "f";
		} else {
			resultConsole.setText("Gender not selected");
		}

		dbconfig.addRecord(fName, lName, Email, gender, DoB, sId);

	}
	
	//function to clear all fields
	public void clearFields() {
	firstName.setText("");
	lastName.setText("");
	studentID.setText("");
	birthDate.setValue(null);
	crudNewFName.setText("");
	crudNewLName.setText("");
	crudStudentId.setText("");
	resultConsole.setText("");
	}
	
	
	
}
