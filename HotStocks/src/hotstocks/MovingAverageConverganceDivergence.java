package hotstocks;

public class MovingAverageConverganceDivergence {
	
	private static double[] combinedMovingAverages = new double[500];
	private static double[] twentySixDayExponentialMovingAverage = new double[500];
	private static double[] twelveDayExponentialMovingAverage = new double [500];
	private static double[] signalLine = new double[500];
		
	private static double[] calculateMovingAverages(double[] prices) {
			twentySixDayExponentialMovingAverage = ExponentialMovingAverage.calculate(prices, 26);
			twelveDayExponentialMovingAverage = ExponentialMovingAverage.calculate(prices, 12);
			
			for (int i = 0; i < 500; i++) {
				combinedMovingAverages[i] = twelveDayExponentialMovingAverage[i] - twentySixDayExponentialMovingAverage[i];
			}
			
			return combinedMovingAverages ;			
		}
	
	private static void calculateSignalLine(double[] prices) {
		signalLine = ExponentialMovingAverage.calculate(prices, 9);		
	}
	
	public static void calculate(double[] prices) {
		calculateMovingAverages(prices);
		calculateSignalLine(prices);
	}
	
	
	public static double[] getSignalLine() {
		return signalLine;
	}
	
	public static double[] getMovingAverages() {
		return combinedMovingAverages;
	}
	
	
}


