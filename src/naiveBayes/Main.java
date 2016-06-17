package naiveBayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {  
	
	public static void main(String[] args) throws IOException{
		String entradas = null;
		FileReader arquivoEntradaImage = new FileReader("aprendizagem.txt");                 //Ler Arquivo
		ArrayList<ArrayList<Integer>> lista = new ArrayList();                           //Matriz com os Dados 
		String[] vetorStr;		
		int meioBase;
		int porcentagem = 0;
		
		boolean condicao = true;
		while(condicao){
			System.out.println("Porcentagem da base a ser usado na aprendizagem (0% a 99%):");
			Scanner scanner = new Scanner(System.in);
			porcentagem = scanner.nextInt();
			if(porcentagem>0 && porcentagem<100){
				condicao=false;
			}else{
				System.out.println("Numero inválido.");
			}
		}
		BufferedReader br = new BufferedReader(arquivoEntradaImage);
		
		meioBase = 0;
		while(br.ready()){                                             			
			ArrayList<Integer> vetorInt = new ArrayList();
			entradas = br.readLine();                                  
			vetorStr = entradas.split(",");                            
	        
			for(int i=0; i<=256; i++){
				if(i==256){                                            
					if(vetorStr[i].contains("cifar_8")){
						vetorInt.add(0);
						meioBase++;
					}else if(vetorStr[i].contains("cifar_3")){
						vetorInt.add(1);
					}
				}else{
				vetorInt.add(Integer.parseInt(vetorStr[i]));
				}
			}			
			lista.add(vetorInt);
		
		}
		
		Apredizagem.media(lista, meioBase, porcentagem);
		
		Classificador.classificador(lista, meioBase, porcentagem);
		
		
	}
}