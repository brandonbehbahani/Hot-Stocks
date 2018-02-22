package hotstocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetStockInfo {
	static String apiKey = "MK6GJRU4RGFD8PU3";
	private static String[] info = new String[800];
	
	public static void connectToNetwork(String stockCode) {
	
		try {
			BufferedReader br = null;
			InputStream input1 = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + 
					stockCode + "&outputsize=full&apikey=" + apiKey + "&datatype=csv").openStream();
			br = new BufferedReader(new InputStreamReader(input1, "UTF-8"));
			for (int i = 0; i < 780; i++) {
				info[i] = br.readLine();
			}
		} catch (MalformedURLException e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		} 
		
	}
	
	public static String[] getInfo() {
		return info;
	}

}
