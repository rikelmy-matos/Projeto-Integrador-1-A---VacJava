package javaVac;

import java.io.*;
import java.util.Scanner;

public class Main {
	
	// Scanner principal, todos as inputs do usuário vão ser recebidas por este objeto
	static Scanner scan = new Scanner(System.in); 
	// variavel loggedin -> validará o login
	static boolean loggedin;
	
	// main -> Onde tudo roda
    public static void main(String[] args) throws IOException{
    	// vai criar todos as pastas necessarias
    	Config.createAllFolders();
    	// Vai criar todos os arquivos necessarios
    	Config.createAllFiles();
    	// Menu Principal(pode retornar true ao Logar)
    	boolean loggedIn = menuMain();
    	// Menu Secundaario(somente quando retornou true ao logar no primeiro menu)
    	menuUser(loggedIn);
    }
    
    // Metodo de pegar a String do usuário, este metodo evita a repetição de codigo, utilizando Scanner
    public static String getString() {
        String option = scan.nextLine();
        return option;
    }
    
    // Metodo consiste em verificar se existe um usuário salvo dentro do arquivo users.txt, se sim, vai adicionar o usuário dentro do arquivo de controle(control.txt)
    static boolean loginUser() throws IOException {
    
        File file = new File("src/users/users.txt");
        if(file.exists()) {
        	// login and password
        	System.out.print("Informe o seu usuário (Primeiro nome): ");
        	String userLogin = getString();
        	System.out.print("Informe a sua senha: ");
        	String userPassword = getString();
        	
        	// User class loginUserClass method
        	loggedin = User.loginUserClass(userLogin, userPassword);
        	return loggedin;
        }
		return false;
    }
    
    // Metodo consiste em criar novo usuário, ele pede algumas informações, e adiciona no arquivo de users.txt(necessario para login)
    static void createUser() throws IOException{
        System.out.print("Informe o primeiro nome do usuário: ");
        String firstName = getString();
        System.out.print("Informe o último nome do usuário: ");
        String lastName = getString();
        System.out.println("Informe a data de nascimento __/__/____: ");
        System.out.print("Dia: ");
        String day = getString();
        System.out.print("Mês: ");
        String month = getString();
        System.out.print("Ano: ");
        String year = getString();
        String birthday = day + "/" + month + "/" + year;
        System.out.print("Informe o sexo (1 - M || 2 - F): ");
        String gender = getString();
        
        switch (gender) {
        case "1":
        	gender = "Male";
            break;
        case "2":
            gender = "Female";
            break;
        default:
            gender = "Undefined";
            break;
        }
        
        System.out.print("Informe a senha do usuário: ");
        String password = getString();
   
        // User class createUserClass method
        User.createUserClass(firstName, lastName, birthday, gender, password);
        }
    
    // Metodo consiste em adicionar Vacina, dentro do arquivo do usuário, o programa sabe onde colocar a informação, por conta do arquivo de controle(control.txt)-> apos login
    static void userAddVac() throws IOException {     
    	System.out.print("Informe o nome da vacina: ");
        String nomeVacina = getString();
        System.out.println("Informe a data da vacina (__/__/____)");
        System.out.print("Dia: ");
        String diaVacina = getString();
        System.out.print("Mês: ");
        String mesVacina = getString();
        System.out.print("Ano: ");
        String anoVacina = getString();
        String dataVacina = diaVacina + "/" + mesVacina + "/" + anoVacina;
        System.out.print("Informe o lote da vacina: ");
        String loteVacina = getString();
        System.out.print("Informe a dose da vacina: ");
        String doseVacina = getString();
        System.out.print("Informe o nome do prestador(nome da unidade): ");
        String localVacina = getString();
        
        // Card class userAddVacClass method
        Card.userAddVacClass(nomeVacina, dataVacina, loteVacina, doseVacina, localVacina);
    }

    // Metodo do menu principal(O primeiro Menu que vai aparecer) -> Criar usuário e Logar...   
    static boolean menuMain() throws IOException {
        boolean loggedIn = false;
        
        while(true){
        	
        	System.out.println("\n### MENU PRINCIPAL ###");
            System.out.println("1. Fazer Login");
            System.out.println("2. Criar Novo Usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
        	
        	String choice = getString();
        	switch (choice) {
            	case "1":
                	loggedIn = loginUser();
                	return loggedIn;
            	case "2":
                	createUser();
                	break;
            	case "3":
            		System.out.println("\nSaindo do programa...\n");
                	return loggedIn;
            	default:
            		System.err.println("\nOpção inválida. Tente novamente.\n");
        		}
        	}
        }
    
    // Metodo do menu Secundario(O Segundo Menu que vai aparecer) -> Adicionar Vacina e Ver Vacinas...(Usuário) 
    static void menuUser(boolean loggedIn) throws IOException {
    	while(loggedIn) {
    		System.out.println("\n### Menu de Usuário ###");
            System.out.println("1. Ver todas as vacinas tomadas");
            System.out.println("2. Adicionar nova vacina");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            
            String option = getString();
            switch (option) {
        	case "1":
        		Card.userReadVacClass();            	
        		break;
        	case "2":
        		userAddVac();
            	break;
        	case "3":
        		System.out.println("\nSaindo do programa...\n");
            	return;
        	default:
        		System.err.println("\nOpção inválida. Tente novamente.\n");
    		}
    	}
    }
 }