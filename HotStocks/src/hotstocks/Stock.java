package hotstocks;

public class Stock {
	
	// variable of the stock objects
	
	private double[] closingPrices = new double[750];
	private double[] openingPrices = new double[750];
	private double[] highestPrices = new double[750];
	private double[] lowestPrices = new double[750];
	private double[] adjustedClosingPrices = new double[750];
	private double[] volumes = new double[750];
	private double[] splitCoefficients = new double[750];
	
	//class constructor
	
	public Stock(
			double[] closingPrices, double[] openingPrices, double[] highestPrices, double[] lowestPrices,  
			double[] adjustedClosingPrices, double[] volumes, double[] splitCoefficients
			)
	{
		this.closingPrices = closingPrices;
		this.openingPrices = openingPrices;
		this.highestPrices = highestPrices;
		this.lowestPrices = lowestPrices;
		this.adjustedClosingPrices = adjustedClosingPrices;
		this.volumes = volumes;
		this.splitCoefficients = splitCoefficients;		
	}
	
	// getters for the stock objects
	
	public double[] getClosingPrices() {
		return this.closingPrices;
	}
	
	public double[] getOpeningPrices() {
		return this.openingPrices; 
	}
	
	public double[] getHighestPrices() {
		return this.highestPrices;
	}
	
	public double[] getlowestPrices() {
		return this.lowestPrices;
	}
	
	public double[] getAdjustedClosingPrices() {
		return this.adjustedClosingPrices;
	}
	
	public double[] getVolumes() {
		return this.volumes;
	}
	
	public double[] getSplitCoefficients() {
		return this.splitCoefficients;
	}
	
	
	
	
}
