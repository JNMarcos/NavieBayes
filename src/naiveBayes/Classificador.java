package naiveBayes;

import java.util.ArrayList;

public class Classificador {
	static int contador1;
	static int contador2;
	static int contadorClassificao1;
	static int contadorClassificao2;
	static int acerto;
	
	public static void classificador(ArrayList<ArrayList<Integer>> data, int meioBase, int porcentagem){
		double[] densidade1 = new double[255];
		double[] densidade2 = new double[255];
		double[] media1 = Apredizagem.media1;
		double[] media2 = Apredizagem.media2; 
		double[] desvio1 = Apredizagem.desvio1;;
		double[] desvio2 = Apredizagem.desvio2;

		contadorClassificao1 = 0;
		contadorClassificao2 = 0;
		contador1 = 0;
		contador2 = 0;
	    acerto = 0;
	    
		double probabilidade1;
		double probabilidade2;
		
		for(int i=(meioBase*porcentagem)/100; i<meioBase; i++){
			probabilidade1 = 1f;
			probabilidade2 = 1f;
			
			if(data.get(i).get(256) == 0){
				contador1++;
			}else if(data.get(i).get(256) == 1){
				contador2++;
				
			}
			
			for(int j=0; j<255; j++){
				densidade1[j] = (1.0/Math.sqrt(2*Math.PI*desvio1[j])
							*Math.pow(Math.E,(-1)*Math.pow(data.get(i).get(j)-media1[j], 2)/2*Math.pow(desvio1[j], 2)));
			
				densidade2[j] =  (1.0/Math.sqrt(2*Math.PI*desvio2[j])
							*Math.pow(Math.E,(-1)*Math.pow(data.get(i).get(j)-media2[j], 2)/2*Math.pow(desvio2[j], 2)));
			}
			
			for(int a=0 ; a< 255; a++){
				if(densidade1[a] != 0.0){
				probabilidade1 = probabilidade1 + Math.log(densidade1[a]);
				}
			}
		    probabilidade1 = probabilidade1 * Apredizagem.probabilidadePriori1;
		    
			for(int a=0 ; a< 255; a++){
				if(densidade2[a] != 0.0){
				probabilidade2 = probabilidade2 + Math.log(densidade2[a]);
				}
			}
			
			probabilidade2 = probabilidade2 * Apredizagem.probabilidadePriori2;
			
			if(probabilidade1 > probabilidade2){
				contadorClassificao1++;
				if(data.get(i).get(256) == 0){
					acerto++;
				}
			}else{
				contadorClassificao2++;
				if(data.get(i).get(256) == 1){
					acerto++;
				}
				
			}
		}
		
		for(int i=(((data.size()-meioBase)*porcentagem)/100)+meioBase; i<data.size(); i++){
			probabilidade1 = 1f;
			probabilidade2 = 1f;
			
			if(data.get(i).get(256) == 0){
				contador1++;
			}else if(data.get(i).get(256) == 1){
				contador2++;
			}
			
			for(int j=0; j<255; j++){
				densidade1[j] = (1.0/Math.sqrt(2*Math.PI*desvio1[j])
							*Math.pow(Math.E,(-1)*Math.pow(data.get(i).get(j)-media1[j], 2)/2*Math.pow(desvio1[j], 2)));
			
				densidade2[j] =  (1.0/Math.sqrt(2*Math.PI*desvio2[j])
							*Math.pow(Math.E,(-1)*Math.pow(data.get(i).get(j)-media2[j], 2)/2*Math.pow(desvio2[j], 2)));
			}
			
			for(int a=0 ; a< 255; a++){
				if(densidade1[a] != 0.0){
				probabilidade1 = probabilidade1 + Math.log(densidade1[a]);
				}
			}
		    probabilidade1 = probabilidade1 * Apredizagem.probabilidadePriori1;
		    
			for(int a=0 ; a< 255; a++){
				if(densidade2[a] != 0.0){
				probabilidade2 = probabilidade2 + Math.log(densidade2[a]);
				}
			}
			
			probabilidade2 = probabilidade2 * Apredizagem.probabilidadePriori2;
			
			if(probabilidade1 > probabilidade2){
				contadorClassificao1++;
				if(data.get(i).get(256) == 0){
					acerto++;
				}
			}else{
				contadorClassificao2++;
				if(data.get(i).get(256) == 1){
					acerto++;
				}
				
			}
		}
		
		double taxaAcerto = ((double)acerto/(contador1+contador2))*100;
		
		System.out.printf("Classes A = %d\n",contadorClassificao1);
		System.out.printf("Classes B = %d\n",contadorClassificao2);
		System.out.printf("Real A= %d\n", contador1);
		System.out.printf("Real B = %d\n",contador2);
		System.out.printf("Taxa de Acerto = %.2f", taxaAcerto);
		
		}
}
