package util;
        
import io.TxtFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Uri
 */

// Cryptocurrency Properties Structure:
// Crytpocurrency ID
// Source Path
// Data Source Start Date [ Format: "dd.MMM.yyyy HH:mm:ss" ]

// Data Properties Structure:
// File ID
// N
// Start Object
// End Object

public class ExtractionProperties {
    
    private final String ID;
    private final String dataDirectory;
    private final String dataSource;
    private final Date dataStartDate;
    
    private final String propertiesFileName = "Properties.txt";
    private final TxtFile cryptoPropertiesFile;
    private final TxtFile dataPropertiesFile;
    
    private String dataFileID;
    
    public ExtractionProperties(String id, String cryptoPropertiesPath) throws FileNotFoundException, IOException, ParseException {

        this.ID = id;
        this.dataDirectory = cryptoPropertiesPath;
        
        this.cryptoPropertiesFile = new TxtFile(this.dataDirectory + this.propertiesFileName);
        String[] aux = this.cryptoPropertiesFile.getCryptocurrencyProperties(this.ID);
        
        this.dataSource = aux[0];
        this.dataStartDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(aux[1]);
        
        this.dataPropertiesFile = new TxtFile(this.dataDirectory + this.ID + "/" + this.propertiesFileName);
    }
    
    public String getID() {
        return this.ID;
    }
    
    public String getDataDirectory() {
        return this.dataDirectory;
    }
    
    public String getDataSource() {
        return this.dataSource;
    }
    
    public Date getDataStartDate() {
        return this.dataStartDate;
    }
    
    public String getDataFileID() {
        return this.dataFileID;
    }
    
    public void updateDataFileID(String id) throws IOException {
        
        this.dataFileID = id;
    }
    
    public void updateN(int n) throws IOException {
        
        this.dataPropertiesFile.setParameter(this.dataFileID, Integer.toString(n), 0);
    }
    
    public void updateStartObject(String o) throws IOException {
        
        this.dataPropertiesFile.setParameter(this.dataFileID, o, 1);
    }
    
    public void updateEndObject(String o) throws IOException {
        
        this.dataPropertiesFile.setParameter(this.dataFileID, o, 2);
    }
}