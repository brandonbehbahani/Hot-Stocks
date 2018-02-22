package hotstocks;

public class SimpleMovingAverage {
	
	private static double priceAverage(double[] prices, int timeLength, int daysAgo) {
		double priceAverage = 0.0;
		double priceTotal = 0.0;
		
		for (int i = daysAgo; i < (daysAgo + timeLength); i++) {
			priceTotal += prices[i];
		}
		
		priceAverage = priceTotal/timeLength;
		
		return priceAverage;

	}
	
	public static double[] calculate(double[] prices, int timeLength) {
		double[] sma = new double[500];
		
		for (int i = 0; i < sma.length; i++) {
			sma[i] = priceAverage(prices, timeLength, i);
		}
		
		return sma;
	}

}
