package javaVac;

import java.io.File;
import java.io.IOException;

public class Config {
	
    // Metodo consiste em criar as pastas necessarios para que o programa rode normalmente
    static void createAllFolders() throws IOException {
    	File ControlFile = new File("src/control/");
    	File usersFile = new File("src/users/");
    	File usersVacFile = new File("src/usersvac/");
    	
    	if (!usersFile.exists()){
    		usersFile.mkdir();
    	}
    	if (!usersVacFile.exists()){
    		usersVacFile.mkdir();
    	}
    	if (!ControlFile.exists()){
    		ControlFile.mkdir();
    	}
    }
    
    // Metodo consiste em criar todos os arquivos necessarios para que o programa rode normalmente(Não inclui as pastas)
    static void createAllFiles() throws IOException {
        File usersFile = new File("src/users/users.txt");
        usersFile.createNewFile();

        File controlFile = new File("src/control/control.txt");
        controlFile.createNewFile();
    }
}
