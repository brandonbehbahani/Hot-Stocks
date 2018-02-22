package hotstocks;

public class CsvParser {
	
	private static String[][] stockStrings = new String[800][9];
	private static double[][] stockNumbers = new double[750][8];
	
	public static void parseInfo(String[] info) {	
		for(int i = 0; i < 775; i++) {
			String[] data;
			data = info[i].split(",");
			for (int j = 0; j < 9; j++) {
				stockStrings[i][j] = data[j];
			}
		}
			 
	}
	
	public static String[][] getInfo() {
		return stockStrings;
	}

	public static void convertToNumbers() {
		for (int i = 0; i < 750; i++) {
			for (int j = 0; j < 8; j++) {
				stockNumbers[i][j] = Double.parseDouble(stockStrings[i + 1][j + 1]);
			}
		}
	}
	
	public static double [][] getNumbers(){
		return stockNumbers; 
	}
		
}
