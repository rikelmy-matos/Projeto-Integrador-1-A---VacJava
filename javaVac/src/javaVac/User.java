package javaVac;

import java.io.FileWriter;
import java.io.IOException;

public class User {
	
	String firstName;
	String lastName;
	String birthday;
	String gender;
	String password;
	
	User(String firstName, String lastName, String birthday, String gender, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.password = password;
	}
	
	void createUserClass() throws IOException {
    	FileWriter createUser = new FileWriter("src/users/users.txt", true);
        String user = this.firstName + ";" + this.lastName + ";" + this.birthday + ";" + this.gender + ";" + this.password + ";Null\n";
        createUser.append(user);
        System.err.print("\nUsuário(a) " + firstName + " criado!\n");
        createUser.close();
	}
}
