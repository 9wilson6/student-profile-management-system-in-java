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
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,750,500);
			primaryStage.setTitle("Student Proﬁle Database Management App");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
