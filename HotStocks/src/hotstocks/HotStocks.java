package hotstocks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


  //Created by Brandon Behbahani on 7/22/2017
 

public class HotStocks { 
    static ArrayList<HotStocks> stocks = new ArrayList<>();
    String stockName;
    public double[] price = new double[500];
    String errorCheck = " { ";
    double[] ema12 = new double[200];
    double[] ema26 = new double[200];
    double simpleMA50;
    double simpleMA100;
    double simpleMA200;
    double exponentialMA12;
    double exponentialMA26;
    
    
    void getInfo(String apiKey,String stockCode){
        BufferedReader br = null;
        String line = "";
        this.stockName = stockCode;
        
        try {
                stockCode = stockCode.toUpperCase();
                InputStream input1 = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockCode + "&outputsize=full&apikey=" + apiKey + "&datatype=csv").openStream();
                br = new BufferedReader(new InputStreamReader(input1, "UTF-8"));
                this.errorCheck  = br.readLine();

                if (!this.errorCheck.contains("{")){
                    for (int i = 0; i < 500; i++){
                        line = br.readLine();
                        String[] info = line.split(",");
                        if (line != null){this.price[i] = Double.parseDouble(info[4]);}  // converts strings to a double for the puposes of calculations               
                    }
                    //HotStocks.stocks.add(this);
                }
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("There was a connection error.");
            } catch (IOException e) {
                System.out.println("There was a connection error.");
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
    
    double timedEMACalc(int count, int daysAgo){ // This calculates the EMA for any amount of days upto 500
        double total = 0;
        double ema = 0;
        int multipliers = 0;
        int count2 = count;
        for (int i = daysAgo; i < count + daysAgo; i++){
            total += this.price[i] * count2;
        }
        for (int i = count; i > 0; i--){
            multipliers += i;
        }
        for (int i = daysAgo; i < count + daysAgo; i++){
            total += this.price[i] * count2;
            count2--;
        }
        ema = total/multipliers;
        return ema;
    }
    
    double simpleMovingAverageCalc(int count){
        double simpleMovingAverage = 0.0;
        double total = 0.0;
        if (count > 200){
            count = 200; 
        }
        for (int i = 0; i < count; i++){
            total += this.price[i];
        }
        simpleMovingAverage = total / count;
        return simpleMovingAverage;
    }

    double exponentialMovingAverage(int count){
        double total = 0.0;
        double exponentialMovingAverage = 0.0;
        int count2 = count;
        int multipliers = 0;
        for (int i = count; i > 0; i--){
            multipliers += i;
        }
        for (int i = 0; i < count; i++){
            total += this.price[i] * count2;
            count2--;
        }
        exponentialMovingAverage = total / multipliers;
        return exponentialMovingAverage;
    }
    
    void findManyEMA(){
        for (int  i = 0; i < 200; i++){
            ema12[i] = this.timedEMACalc(12, i);
        }
        for (int  i = 0; i < 200; i++){
            ema26[i] = this.timedEMACalc(26, i);
        }
    }
    
    void checkStock(){
        simpleMA50 = this.simpleMovingAverageCalc(50);
        simpleMA100 = this.simpleMovingAverageCalc(100);
        simpleMA200 = this.simpleMovingAverageCalc(200);
        exponentialMA12 = this.exponentialMovingAverage(12);
        exponentialMA26 = this.exponentialMovingAverage(26);
        System.out.println("");
        System.out.println("////////////////////STATISTICS/////////////////////");
        System.out.println("50 DAY SIMPLE MOVING AVERAGE:      " + simpleMA50);
        System.out.println("100 DAY SIMPLE MOVING AVERAGE:     " + simpleMA100);
        System.out.println("200 DAY SIMPLE MOVING AVERAGE:     " + simpleMA200);
        System.out.println("12 DAY EXPONENTIAL MOVING AVERAGE: " + exponentialMA12);
        System.out.println("26 DAY EXPONETIAL MOVING AVERAGE:  " + exponentialMA26);
        
        if (simpleMA50 > simpleMA200){
            System.out.println("");
            System.out.println("?????????????????????PREDICTIONS????????????????????");
            System.out.println(">>> Because the 50 day SMA is higher than the 200");
            System.out.println("day SMA it indicates that this stock is in an upward");
            System.out.println("trend. ");
            if (exponentialMA12 > exponentialMA26){
                System.out.println(">>> Also because the 12 day EMA is above the 26 day EMA");
                System.out.println("this mean this is a very strong stock pick.");
            } else {
                System.out.println(">>> Despite the upward trend, because the 12 day EMA is ");
                System.out.println("less than the 26 day EMA this may not be the best pick.");
            }
        } else {
            System.out.println("");
            System.out.println("??????????????????????PERDICTIONS????????????????????");
            System.out.println(">>> Because the 50 day SMA is lower than the 200");
            System.out.println("day SMA it indicates that the stock is in a downward");
            System.out.println("trend. ");
            if (exponentialMA12 > exponentialMA26){
                System.out.println(">>> Despite this because the 12 day EMA is higher ");
                System.out.println("the 26 day EMA, this trend may be reversing and might");
                System.out.println("be a good pick.");
            } else{
                System.out.println(">>> Also because the 12 day EMA is lower than the 26");
                System.out.println("this downward trend is continuing. This stock is a ");
                System.out.println("really bad pick.");
            }
        }
               
    }   
    
    static void showAllStocks(){  // this show a short sample of stats from every stock that the user has entered
        for (int i = 0; i < HotStocks.stocks.size(); i++){
                    System.out.println("");
                    System.out.println("////////////////////////////////////////////////////////////////");
                    System.out.println("Name: " + HotStocks.stocks.get(i).stockName);
                    System.out.println("");
                    System.out.println("Most recent Price: " + HotStocks.stocks.get(i).price[i]);
                    System.out.println("");  
                    System.out.println("50 day SMA: " + HotStocks.stocks.get(i).simpleMA50);
                    System.out.println("");
                    System.out.println("200 day SMA: " + HotStocks.stocks.get(i).simpleMA200);
        }
        System.out.println("These are the " + HotStocks.stocks.size() + " stocks that you entered. ");
        System.out.println("");
    }
    
    public static void main(String[] args) {
        String userChoice = "";
        String currentStock;
        Scanner scanner = new Scanner(System.in);
        final String apiKey = "MK6GJRU4RGFD8PU3" ;
        boolean keepGoing = true;
        while (keepGoing){
            HotStocks hs1 = new HotStocks();  
            System.out.println("/////////////////////////////////////////////");
            System.out.println("Hot Stocks!!!");
            System.out.println("Please enter the 4 letter stock code: ");
            currentStock = scanner.nextLine();
            if (currentStock.length() > 4){ currentStock = currentStock.substring(0, 4);}
            hs1.getInfo(apiKey, currentStock);
            if (hs1.errorCheck.contains("{")){                
                System.out.println(">>> This api request returned an error");
                System.out.println("please enter a different stock code.");
            } else {
                hs1.checkStock();
                HotStocks.stocks.add(hs1);
            }
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println("Type 'INFO' to show all previous stocks from this session. ");
            System.out.println("Type 'EXIT' to quit the program. ");
            System.out.println("Press ENTER to continue. ");
            userChoice = scanner.nextLine();
            userChoice = userChoice.toUpperCase();
            if (userChoice.equals("INFO")){
                HotStocks.showAllStocks();
                System.out.println("////////////////////////////////////////////////////////");
                System.out.println("Type 'INFO' to show all previous stocks from this session. ");
                System.out.println("Type 'EXIT' to quit the program. ");
                System.out.println("Press ENTER to continue. ");
                userChoice = scanner.nextLine();
                userChoice = userChoice.toUpperCase();
                if (userChoice.equals("EXIT")){
                    keepGoing = false;
                }
            }
               
                if (userChoice.equals("EXIT")){
                    keepGoing = false;
                }
            }
            
    }
}
