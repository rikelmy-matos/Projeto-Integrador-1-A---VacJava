package javaVac;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
	
	static void createUserClass(String firstName, String lastName, String birthday, String gender, String password) throws IOException {
    	FileWriter createUser = new FileWriter("src/users/users.txt", true);
        String user = firstName + ";" + lastName + ";" + birthday + ";" + gender + ";" + password + ";Null\n";
        createUser.append(user);
        System.err.print("\nUsuário(a) " + firstName + " criado!\n");
        createUser.close();
	}
	
	static boolean loginUserClass(String firstName, String password) throws IOException {
		File file = new File("src/users/users.txt");
        if(file.exists()) {
        	Scanner dataUserLogin = new Scanner(file);
        	while (dataUserLogin.hasNextLine()) {
        		String stringUser = dataUserLogin.nextLine();
        		String[] dataUser = stringUser.split(";");
        		if ((firstName.equals(dataUser[0])) && (password.equals(dataUser[4]))) {
        			FileWriter control = new FileWriter("src/control/control.txt");
        			control.write(dataUser[0] + ";" + dataUser[1] + ";Null");
        			control.close();
        			dataUserLogin.close();
        			return true; 
                }
            }
        	dataUserLogin.close();
        }
        System.out.println("\nLogin inválido!");
        return false;
	}
}
