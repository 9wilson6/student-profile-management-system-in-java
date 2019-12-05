package com.studentprofilemanagementapp;
//create a student model class
//generate setters and getters to help set and get student model properties
public class StudentModel {
String firstName, lastName, studentId,gender, emailAddress,birthDate;

public StudentModel(String firstName, String lastName, String studentId, String gender, String emailAddress,
		String birthDate) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.studentId = studentId;
	this.gender = gender;
	this.emailAddress = emailAddress;
	this.birthDate = birthDate;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getStudentId() {
	return studentId;
}

public void setStudentId(String studentId) {
	this.studentId = studentId;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getEmailAddress() {
	return emailAddress;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public String getBirthDate() {
	return birthDate;
}

public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
}

}
