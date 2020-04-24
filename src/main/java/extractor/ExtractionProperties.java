package extractor;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Uri
 */

// Properties of Data File

// Line 0 : Source Path
// Line 1 : Directory Path
// Properties File Structure
// ID
// N
// Start Object
// End Object

public class ExtractionProperties {
    
    private final int YID;  // Year of extraction
    private final int NH;   // Number of header lines
    private final int NP;   // Number of properties
    private final String extractionSource;
    private final String basePath;
    
    private String ID;
    private String filePath;
    
    private int N;
    
    private String startObject;
    private String endObject;
    
    private FileIO io;
    private String[] aux;
    
    public ExtractionProperties(int year, String path) throws FileNotFoundException, IOException {

        this.YID = year;
        this.NH = 2;
        this.NP = 3;
        this.io = new FileIO(path);
        this.aux = new String[this.NP];
        
        this.aux = this.io.getExtractionHeader(this.NH);
        
        this.extractionSource = this.aux[0];
        this.basePath = this.aux[1];
        
        this.ID = "null";
        this.filePath = this.basePath;
        this.N = 0;
        this.startObject = "null";
        this.endObject = "null";
    }
    
    public int getYID() {
        return this.YID;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public void updateID(String id) throws IOException {
        
        this.ID = id;
        this.filePath = this.basePath + "/" + id + ".txt";
        
        this.aux = this.io.getExtractionProperties(this.ID, this.NP);
        
        this.N = Integer.parseInt(this.aux[0]);
        this.startObject = this.aux[1];
        this.endObject = this.aux[2];
    }
    
    public String getSourcePath() {
        return this.extractionSource;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public void updateN(int n) throws IOException {
        
        this.N = n;
        this.io.setParameter(this.ID, Integer.toString(this.N), 0);
    }
    
    public int getN() {
        return this.N;
    }
    
    public void updateStartObject(String o) throws IOException {
        
        this.startObject = o;
        this.io.setParameter(this.ID, this.startObject, 1);
    }
    public String getStartObject() {
        return this.startObject;
    }
    
    public void updateEndObject(String o) throws IOException {
        
        this.endObject = o;
        this.io.setParameter(this.ID, this.endObject, 2);
    }
    
    public String getEndObject() {
        return this.endObject;
    }
}