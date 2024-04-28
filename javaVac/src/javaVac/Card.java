package javaVac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Card {
	
	String nomeVacina;
	String dataVacina;
	String loteVacina;
	String doseVacina;
	String localVacina;
	
	Card(String nomeVacina, String dataVacina, String loteVacina, String doseVacina, String localVacina){
		this.nomeVacina = nomeVacina;
		this.dataVacina = dataVacina;
		this.loteVacina = loteVacina;
		this.doseVacina = doseVacina;
		this.localVacina = localVacina;
	}
	
    // Metodo consiste em adicionar Vacina, dentro do arquivo do usuário, o programa sabe onde colocar a informação, por conta do arquivo de controle(control.txt)-> apos login
	static void userAddVacClass(String nomeVacina, String dataVacina, String loteVacina, String doseVacina, String localVacina) throws IOException{
		File file = new File("src/control/control.txt");
        if (file.exists()) {
            Scanner scan = new Scanner(file); 
            if (scan.hasNextLine()) {
            	String stringUser = scan.nextLine();
                String[] nameUser = stringUser.split(";");
                String archiveVacUser = "src/usersvac/" + nameUser[0] + "_" + nameUser[1] + ".txt";
                
                try (FileWriter archive = new FileWriter(archiveVacUser, true)) {
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
	static void userReadVacClass() throws IOException {
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

}
