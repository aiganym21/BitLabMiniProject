import java.util.*;
import java.io.*;

public class PackageData implements Serializable{

	 private ArrayList<Student> studentList;
	 private Student student;
	 private String operationType;

	 public PackageData(){

	 }

	 public void setStudent(Student student){
	  	this.student = student;
	 }

	 public Student getStudent(){
	  	return this.student;
	 }

	 public void setStudentList(ArrayList<Student> students){
	  	this.studentList = students;
	 }

	 public ArrayList<Student> getStudentList(){
	  	return this.studentList;
	 }

	 public void setOperationType(String operationType){
	  	this.operationType = operationType;
	 }

	 public String getOperationType(){
	  	return this.operationType;
	 }

}