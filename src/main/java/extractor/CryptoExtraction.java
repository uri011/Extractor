package extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import util.ExtractionProperties;
import util.TimeTool;


/**
 * @author Uri
 */

// https://api.binance.com/api/v1/klines?symbol=BTCUSDT&interval=1m&startTime=1577833200000&limit=1000

public class CryptoExtraction {
    
    public static final String BTCUSDT = "BTCUSDT";    // Thursday, 17 August 2017 4:00:00 GMT
    public static final String BTCUSDC = "BTCUSDC";    // Saturday, 15 December 2018 3:11:00 GMT
    
    public static final String ETHUSDT = "ETHUSDT";    // Thursday, 17 August 2017 4:00:00 GMT
    public static final String ETHUSDC = "ETHUSDC";    // Saturday, 15 December 2018 3:21:00 GMT
    
    public static final String XRPUSDT = "XRPUSDT";    // Friday, 4 May 2018 8:11:00 GMT
    public static final String XRPUSDC = "XRPUSDC";    // Saturday, 15 December 2018 3:04:00 GMT
    
    public static final String LTCUSDT = "LTCUSDT";    // Wednesday, 13 December 2017 3:32:00 GMT
    public static final String LTCUSDC = "LTCUSDC";    // Thursday, 24 January 2019 10:00:00 GMT
    
    public static final String NEOUSDT = "NEOUSDT";    // Monday, 20 November 2017 3:42:00 GMT
    public static final String NEOUSDC = "NEOUSDC";    // Wednesday, 27 March 2019 4:00:00 GMT
    
    public static final String folderPath = "/Users/Uri/NetBeansProjects/DataBot/src/";
    public static final String fileName = "/DataProperties.txt";

    public static void main(String[] args) throws MalformedURLException, IOException, Exception {

        /*writeCryptoProperties();
        
        writeDataProperties(BTCUSDT,2017,7);
        writeDataProperties(BTCUSDC,2018,11);
        
        writeDataProperties(ETHUSDT,2017,7);
        writeDataProperties(ETHUSDC,2018,11);
        
        writeDataProperties(XRPUSDT,2018,4);
        writeDataProperties(XRPUSDC,2018,11);
        
        writeDataProperties(LTCUSDT,2017,11);
        writeDataProperties(LTCUSDC,2019,0);
        
        writeDataProperties(NEOUSDT,2017,10);
        writeDataProperties(NEOUSDC,2019,2);*/

        extractCryptos();
    }
    
    public static void extractCryptos() throws IOException, FileNotFoundException, ParseException, ClassNotFoundException {
        
        ExtractionProperties properties;
        ExtractionProcess process;
        
        Date startDate = new Date(2017 - 1900, 0, 1, 1, 0, 0);
        Date endDate = new Date(2020 - 1900, 2, 31, 23, 59, 0);
                
        ArrayList<String> cryptos = new ArrayList<>();
        
        cryptos.add(BTCUSDT);
        cryptos.add(BTCUSDC);
        cryptos.add(ETHUSDT);
        cryptos.add(ETHUSDC);
        cryptos.add(XRPUSDT);
        cryptos.add(XRPUSDC);
        cryptos.add(LTCUSDT);
        cryptos.add(LTCUSDC);
        cryptos.add(NEOUSDT);
        cryptos.add(NEOUSDC);
            
        for(int i = 0; i < cryptos.size(); i++) {
            
            // "/Users/carlosrodriguezperise/Desktop/CryptosData/"
            properties = new ExtractionProperties(cryptos.get(i), "/Users/carlosrodriguezperise/Desktop/CryptoMarketData/");
            process = new ExtractionProcess(properties, startDate, endDate);
            process.startExtraction();
        }
    }
    
    public static void writeDataProperties(String symbol, int startYear, int startMonth) throws IOException {
        
	File fout = new File("/Users/carlosrodriguezperise/Desktop/CryptoMarketData/" + symbol + "/Properties.txt");
	FileOutputStream fos = new FileOutputStream(fout);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
 
	
        while(startYear <= 2020) {
            while(startMonth <= 11) {
                bw.write(symbol + Integer.toString(startYear) + "_" + TimeTool.getMonthID(startMonth));
                bw.newLine();
                bw.write("0");
                bw.newLine();
                bw.write("null");
                bw.newLine();
                bw.write("null");
                bw.newLine();
                bw.newLine();
                startMonth++;
            }
            startMonth = 0;
            startYear++;
        }
	bw.close();
    }

    public static void writeCryptoProperties() throws IOException {
        
	File fout = new File("/Users/carlosrodriguezperise/Desktop/CryptoMarketData/Properties.txt");
	FileOutputStream fos = new FileOutputStream(fout);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
 
        bw.write("BTCUSDT");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=BTCUSDT&interval=1m");
	bw.newLine();
        bw.write("2017.08.17 05:00:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("BTCUSDC");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=BTCUSDC&interval=1m");
	bw.newLine();
        bw.write("2018.12.15 04:11:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("ETHUSDT");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=ETHUSDT&interval=1m");
	bw.newLine();
        bw.write("2017.08.17 05:00:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("ETHUSDC");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=ETHUSDC&interval=1m");
	bw.newLine();
        bw.write("2018.12.15 04:21:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("XRPUSDT");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=XRPUSDT&interval=1m");
	bw.newLine();
        bw.write("2018.05.04 09:11:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("XRPUSDC");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=XRPUSDC&interval=1m");
	bw.newLine();
        bw.write("2018.12.15 04:04:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("LTCUSDT");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=LTCUSDT&interval=1m");
	bw.newLine();
        bw.write("2017.12.13 04:32:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("LTCUSDC");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=LTCUSDC&interval=1m");
	bw.newLine();
        bw.write("2019.01.24 11:00:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("NEOUSDT");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=NEOUSDT&interval=1m");
	bw.newLine();
        bw.write("2017.11.20 04:32:00");
        bw.newLine();
        bw.newLine();
        
        bw.write("NEOUSDC");
	bw.newLine();
	bw.write("https://api.binance.com/api/v1/klines?symbol=NEOUSDC&interval=1m");
	bw.newLine();
        bw.write("2019.03.27 05:00:00");
        
	bw.close();
    }
}