package naiveBayes;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Apredizagem {
	static double[] media1 = new double[255];  
	static double[] media2 = new double[255];
	static double[] desvio1 = new double[255];
	static double[] desvio2 = new double[255];
	static double contador1;
	static double contador2;
	static double probabilidadePriori1;
	static double probabilidadePriori2;
	
	public static void media(ArrayList<ArrayList<Integer>> data, int meioBase, int porcentagem){
		
		double[] soma1 = new double[255];
		double[] soma2 = new double[255];

 		contador1 = 0;
 		contador2 = 0;
		
		for(int i=0; i<255; i++){
			soma1[i] = 0;
			soma2[i] = 0;
		}
		
		for(int i=0; i<data.size(); i++){
			if(data.get(i).get(256) == 0){
				contador1++;			
			}else if(data.get(i).get(256) == 1){
				contador2++;
			}
			
			for(int j=0; j<255; j++){
				if(data.get(i).get(256) == 0 && i<(meioBase*porcentagem)/100){
					soma1[j] += data.get(i).get(j);		
				}else if(data.get(i).get(256) == 1 && i<(data.size()*porcentagem)/100){
					soma2[j] += data.get(i).get(j);
				}
			}
		}
		for(int i=0; i<255; i++){
			media1[i] = soma1[i]/contador1;
		}
		
		for(int i=0; i<255; i++){
			media2[i] = soma2[i]/contador2;
		}
		
		desvio(data, meioBase, porcentagem);
	}
	
	public static void desvio(ArrayList<ArrayList<Integer>> data, int meioBase, int porcentagem){
		double[] somatorio1 = new double[255];
		double[] somatorio2 = new double[255];
		
		for(int i=0; i<255; i++){
			somatorio1[i] = 0;
			somatorio2[i] = 0;
		}
		
		for(int i=0; i<data.size(); i++){
			for(int j=0; j<255; j++){
				if(data.get(i).get(256) == 0 && i<(meioBase*porcentagem)/100){
					somatorio1[j] +=  Math.pow((data.get(i).get(j) - media1[j]), 2);
				}else if(data.get(i).get(256) == 1 && i<(data.size()*porcentagem)/100){
					somatorio2[j] +=  Math.pow((data.get(i).get(j) - media2[j]), 2);
				}
			}
		}

		for(int i=0; i< 255; i++){
			desvio1[i] =  Math.pow(((1/(contador1-1f))*somatorio1[i]), 0.5);	
		}
		
		for(int i=0; i< 255; i++){
			desvio2[i] =  Math.pow(((1/(contador2-1f))*somatorio2[i]), 0.5);
		}
		
		probabilidade(data);
	}	
	
	public static void probabilidade(ArrayList<ArrayList<Integer>> data){
		probabilidadePriori1 = contador1 /(contador1 + contador2);
		probabilidadePriori2 = contador2 /(contador1 + contador2);
	}
}
