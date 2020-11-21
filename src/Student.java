import java.io.*;

public class Student implements Serializable{

	 private Long id;
	 private String name;
	 private String surname;
	 private int age;

	 public Student(Long id,String name, String surname, int age){

		  this.id = id;
		  this.name = name;
		  this.surname = surname;
		  this.age = age;

	 }

	 public Student(){

	 }

	 public void setId(Long id){
	  	this.id = id;
	 }

	 public Long getId(){
	  	return this.id;
	 }

	 public void setName(String name){
	  	this.name = name;
	 }

	 public String getName(){
	  	return this.name;
	 }

	 public void setSurname(String surname){
	  	this.surname = surname;
	 }

	 public String getSurname(){
	  	return this.surname;
	 }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", age=" + age +
				'}';
	}
}