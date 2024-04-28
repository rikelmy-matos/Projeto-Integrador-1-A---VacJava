package javaVac;

import java.io.*;
import java.util.Scanner;

public class Main {
	
	// Scanner principal, todos as inputs do usuário vão ser recebidas por este objeto
	static Scanner scan = new Scanner(System.in); 
	
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
        	Scanner dataUserLogin = new Scanner(file);
        	System.out.print("Informe o seu usuário (Primeiro nome): ");
        	String userLogin = getString();

        	System.out.print("Informe a sua senha: ");
        	String userPassword = getString();
        	
        	while (dataUserLogin.hasNextLine()) {
        		String stringUser = dataUserLogin.nextLine();
        		String[] dataUser = stringUser.split(";");
        		if ((userLogin.equals(dataUser[0])) && (userPassword.equals(dataUser[4]))) {
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
    
    // Metodo consiste em criar novo usuário, ele pede algumas informações, e adiciona no arquivo de users.txt(necessario para login)
    static void createUser() throws IOException{
    	
        System.out.println("---------------------------------------------------");
        System.out.print("Informe o primeiro nome do usuário: ");
        String firstName = getString();;

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
        System.out.println("---------------------------------------------------");
        User user = new User(firstName, lastName, birthday, gender, password);
        user.createUserClass();
        }
    
    // Metodo consiste em adicionar Vacina, dentro do arquivo do usuário, o programa sabe onde colocar a informação, por conta do arquivo de controle(control.txt)-> apos login
    static void userAddVac() throws IOException {
        File file = new File("src/control/control.txt");
        if (file.exists()) {
            Scanner scan = new Scanner(file); 
            if (scan.hasNextLine()) {
            	String stringUser = scan.nextLine();
                String[] nameUser = stringUser.split(";");
                String archiveVacUser = "src/usersvac/" + nameUser[0] + "_" + nameUser[1] + ".txt";
                
                try (FileWriter archive = new FileWriter(archiveVacUser, true)) {
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
                    String informacoesVacina = nomeVacina + ";" + dataVacina + ";" + loteVacina + ";" + doseVacina + ";" + localVacina + ";" + "Null\n";
                    archive.write(informacoesVacina);
                    System.err.print("\nNova vacina salva: " + nomeVacina + " | No usuário(a): " + nameUser[0] + "_" + nameUser[1] + "\n");
 
                }
            }
            else {
            	System.out.println("Arquivo de controle vazio.");
            	}
            scan.close();
            }
        }
    
    // Metodo consiste em ler todas as Vacinas dentro do arquivo do usuário, o programa sabe onde colocar a informação, por conta do arquivo de controle(control.txt)-> apos login
    static void userReadVac() throws IOException {
        File file = new File("src/control/control.txt");
        Scanner scan = new Scanner(file);
        String arquivoUsuario = scan.nextLine();
        String[] dadosArquivo = arquivoUsuario.split(";");
        String arquivoAdicionarVac = "src\\usersvac\\" + dadosArquivo[0] + "_" + dadosArquivo[1] + ".txt";
        
        File fileUser = new File(arquivoAdicionarVac);
        if (fileUser.exists()) {
        	BufferedReader leitor = new BufferedReader(new FileReader(arquivoAdicionarVac));
            String vacinaLinha;
            String[] dadosVacina;

            System.err.println("\nSegue dados de todas as vacinas tomadas: \n");
            System.err.println("---------------------------------------------------\n");

            while ((vacinaLinha = leitor.readLine()) != null) {
                dadosVacina = vacinaLinha.split(";");
                System.out.print("Nome da Vacina: ");
                System.out.println(dadosVacina[0]);

                System.out.print("Data da Vacina: ");
                System.out.println(dadosVacina[1]);

                System.out.print("Lote da Vacina: ");
                System.out.println(dadosVacina[2]);

                System.out.print("Dose da Vacina: ");
                System.out.println(dadosVacina[3]);

                System.out.print("Local da Vacina: ");
                System.out.println(dadosVacina[4]);

                System.err.println("\n---------------------------------------------------\n");
            }
            leitor.close();	
    	}
        scan.close();
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
        		userReadVac();            	
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