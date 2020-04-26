package extractor;

import io.Binary;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import util.ExtractionProperties;
import util.KStick;
import util.TimeTool;

/**
 * @author Uri
 */

// Extraction Control Unit

public class ExtractionProcess {
    
    private final ExtractionProperties properties;
    private final ServerExtractor serverExtractor;
    private final Date startDate;
    private final Date endDate;
    
    private String startTime;                   // Start Time Timestamp
    private String limit;                       // Extraction limit
    
    private Binary io;
    private ArrayList<KStick> extractedData;    // Extracted Data
    
    private int targetMonth;
    private int targetYear;
    private int countTotal;                           
    private int countExtracted;                     

    private String[] seo;
    private long aux;
    
    public ExtractionProcess(ExtractionProperties p, Date start, Date end) {
        
        this.properties = p;
        this.serverExtractor = new ServerExtractor(this.properties.getDataSource());
        
        if(this.properties.getDataStartDate().before(start))
            this.startDate = start;
        else
            this.startDate = this.properties.getDataStartDate();
        
        if(new Date(System.currentTimeMillis()).after(end))
            this.endDate = end;
        else
            this.endDate = new Date(System.currentTimeMillis());
        
        this.extractedData = new ArrayList<>();
        this.startTime = "new";
        this.limit = "null";
        this.seo = new String[2];
    }
    
    // Extracts and saves the data from the start month to the end month [ JA : 0 - DC : 11 ]
    public void startExtraction() throws IOException, FileNotFoundException, ClassNotFoundException {
        
        this.targetYear = this.startDate.getYear() + 1900;
        this.targetMonth = this.startDate.getMonth();
        
        while(this.targetYear < (this.endDate.getYear() + 1900)) {  
            while(this.targetMonth <= 11) {
                
                this.properties.updateDataFileID(this.properties.getID() + this.targetYear + "_" + TimeTool.getMonthID(this.targetMonth));
                
                this.io = new Binary(this.properties.getDataDirectory() + this.properties.getID() + "/" + this.properties.getDataFileID() + ".txt", 0);
                this.extract();
                this.targetMonth++;
            }
            this.targetMonth = 0;
            this.targetYear++;
        }
        
        while(this.targetMonth <= this.endDate.getMonth()) {
            
            this.properties.updateDataFileID(this.properties.getID() + this.targetYear + "_" + TimeTool.getMonthID(this.targetMonth));
                
            this.io = new Binary(this.properties.getDataDirectory() + this.properties.getID() + "/" + this.properties.getDataFileID() + ".txt", 0);
            this.extract();
            this.targetMonth++;
        }
    }
    
    // Extracts and saves the data of targeted month
    private void extract() throws IOException, FileNotFoundException, ClassNotFoundException {
        
        this.countExtracted = 0;
        
        if("new".equals(this.startTime))
            this.countTotal = (int) TimeUnit.MINUTES.convert(new Date(this.targetYear - 1900, this.targetMonth, TimeTool.getDaysOnMonth(this.targetYear, this.targetMonth), 23, 59, 00).getTime() - this.properties.getDataStartDate().getTime(), TimeUnit.MILLISECONDS);
        else
            this.countTotal = (TimeTool.getDaysOnMonth(this.targetYear, this.targetMonth) * 24 * 60); // Number of extractions to do [ Month minutes ]
        
        this.extractedData.clear();
        
        while(this.countExtracted < this.countTotal) {
            this.updateSTL();
            this.extractedData = this.serverExtractor.extract(this.startTime, this.limit);
            this.countExtracted = this.countExtracted + Integer.parseInt(this.limit);
            try{Thread.sleep(1100);}catch(InterruptedException e){ System.out.println(e);}
        }
        
        this.startTime = "null";
        
        this.seo = this.io.write(this.extractedData);
        this.properties.updateN(this.countExtracted);
        this.properties.updateStartObject(this.seo[0]);
        this.properties.updateEndObject(this.seo[1]);
        
        System.out.println(this.targetMonth + "." + this.targetYear + " completed with " + this.countTotal + " extractions");
    }
    
    // Updates start time and limit
    private void updateSTL() {
        
        if("new".equals(this.startTime)) {
            this.startTime = Long.toString(this.startDate.getTime());
            this.limit = "1000";
        } else if("null".equals(this.startTime)) {
            this.startTime = Long.toString(new Date(this.targetYear - 1900, this.targetMonth, 1, 1, 0, 0).getTime());
            this.limit = "1000";
        } else { 
            aux = Long.parseLong(this.startTime.substring(0, startTime.length() - 3));
            aux = aux + ((Integer.parseInt(limit)) * 60);
            this.startTime = Long.toString(aux) + "000";
            
            if((this.countExtracted + 1000) <= this.countTotal)
                this.limit = "1000";
            else
                this.limit = Integer.toString(this.countTotal - this.countExtracted);
        }
    }
}