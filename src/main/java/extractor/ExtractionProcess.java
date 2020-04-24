package extractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Uri
 */

// Extraction Control Unit

public class ExtractionProcess {
    
    private final ExtractionProperties properties;
    private final Month month;
    
    private ServerExtractor se;
    private BinaryIO io;
    private ArrayList<KStick> extractedData;    // Extracted Data
    private String startTime;                   // Start Time Timestamp
    private String limit;                       // Extraction limit
    private String[] seo;
    private int extN;                           // Total Number of Extractions
    private int extC;                           // Current Number of Extractions
    private int mthC;                           // Current Extraction Month
    private long aux;
    
    public ExtractionProcess(ExtractionProperties p) {
        
        this.properties = p;
        this.month = new Month(this.properties.getYID());
        
        this.extractedData = new ArrayList<>();
        this.startTime = "null";
        this.limit = "null";
        this.seo = new String[2];
    }
    
    // Extracts and saves the data from the start month to the end month [ JA : 0 - DC : 11 ]
    public void startExtraction(int start, int end) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        this.mthC = start;
        
        while(this.mthC <= end) {
            this.properties.updateID(this.month.getMID(this.mthC) + Integer.toString(this.properties.getYID()));
            this.se = new ServerExtractor(this.properties.getSourcePath());
            this.io = new BinaryIO(this.properties.getFilePath(), this.properties.getN());
            this.extractMonth(this.month.getMID(this.mthC));
            this.mthC++;
        }
    }
    
    // Extracts and saves the data from month
    private void extractMonth(String m) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        this.extC = 0;
        //this.extN = (this.month.getNumDays(m) * 24 * 60); // Number of extractions to do [ Month minutes ]
        this.extN = 1002;
        
        this.extractedData.clear();
        
        while(this.extC < this.extN) {
            this.updateSTL();
            this.extractedData = se.extract(this.startTime, this.limit);
            this.extC = this.extC + Integer.parseInt(this.limit);
            System.out.println(this.extC + " extractions of " + this.extN);
            //try{Thread.sleep(50000);}catch(InterruptedException e){ System.out.println(e);}
        }
        
        this.startTime = "null";
        this.seo = this.io.write(this.extractedData);
        this.properties.updateN(this.extC);
        this.properties.updateStartObject(this.seo[0]);
        this.properties.updateEndObject(this.seo[1]);
        
        
        // IMPRIMIR TOTES LES DADES GUARDADES DEL MES, NOMES PER COMPROVAR BON FUNCIONAMENT
        ArrayList<KStick> k = new ArrayList<>();
        k = io.readFile();
        int i = 0;
        for( KStick x : k) {
            System.out.print(i + " : ");
            System.out.println(x.toString());
            i++;
        }
    }
    
    // Updates start time and limit
    private void updateSTL() {
        
        if(this.startTime == "null") {
            // First day of month 00:00:00 GMT
            this.startTime = Long.toString(new Date(this.properties.getYID() - 1900, this.mthC, 1, 1, 0, 0).getTime());
            this.limit = "1000";
            
        } else { 
            
            aux = Long.parseLong(this.startTime.substring(0, startTime.length() - 3));
            aux = aux + ((Integer.parseInt(limit)) * 60);
            this.startTime = Long.toString(aux) + "000";
            
            if((this.extC + 1000) <= this.extN)
                this.limit = "1000";
            else
                this.limit = Integer.toString(this.extN - this.extC);
        }
    }
}