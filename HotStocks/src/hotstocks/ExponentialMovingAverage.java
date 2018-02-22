package hotstocks;

public class ExponentialMovingAverage {
		
	private static double priceAverage(double[] prices, int timeLength, int daysAgo) {
		double priceAverage = 0.0;
		double priceTotal = 0.0;
		int multiplier = 0;
		int countDown = timeLength; 
		
		for (int i = 0; i < timeLength; i++) {
			multiplier += timeLength;
		}
		
		for (int i = daysAgo; i < daysAgo + timeLength; i++) {
			priceTotal += (prices[i * countDown]);
			countDown--;
		}
		
		priceAverage = priceTotal / multiplier; 
		
		return priceAverage;
	}
	
	public static double[] calculate(double[]prices, int timeLength) {
		double[] ema = new double[500];
		
		for (int i = 0; i < ema.length; i++) {
			ema[i] = priceAverage(prices, timeLength, i);
		}
		
		return ema;
	}
	

}
