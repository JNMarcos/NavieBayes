package naiveBayes;

public class IfMeasure {
	
	public static double precision(int truePos, int falsePos){
		return (double)truePos/(truePos + falsePos);
		
	}
	
	public static double recall(int truePos, int falseNeg){
		return (double)truePos/(truePos + falseNeg);
		
	}
	
	
	public static double ifMeasure(int truePos, int falsePos, int falseNeg){
		return (double)2*((precision(truePos, falsePos))*recall(truePos, falseNeg))/(precision(truePos, falsePos)+recall(truePos,falseNeg));
		
	}
}
