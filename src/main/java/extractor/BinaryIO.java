package extractor;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Uri
 */

// Input/Output for Binary Files

public class BinaryIO {
    
    private File file;
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    private int N;

    private ArrayList<KStick> kdata;
    private KStick aux;
    private int i;
    
    public BinaryIO(String filePath, int n) {
        
        this.file = new File(filePath);
        this.N = n;
        
        this.kdata = new ArrayList<>();
    }
    
    // Writes the given array list to the binary file
    // Returns start and end object string
    public String[] write(ArrayList<KStick> data) throws IOException {
        
        this.fileOut = new FileOutputStream(this.file); 
        this.out = new ObjectOutputStream(this.fileOut); 
              
        for( KStick k : data)
            out.writeObject(k); 

        
        this.out.close(); 
        this.fileOut.close();
        
        this.N = this.N + data.size();

        String[] s = new String[2];
        s[0] = data.get(0).toString();                  // Start object string
        s[1] = data.get(data.size() - 1).toString();    // End object string
        return s;
    }
    
    // Returns data from the start target line to the end target line on binary file [ First line index : 0 ]
    public ArrayList<KStick> read(int start, int end) throws FileNotFoundException, IOException {
        
        if( start >= 0 && end < this.N) {
            this.kdata.clear();
        
            this.fileIn = new FileInputStream(this.file); 
            this.in = new ObjectInputStream(this.fileIn);
            
            //if(((this.N/2) - start) > (end - (this.N/2))) // Read file from start to end
            this.i = 0;
            try {
                try {
                    while(start > i) {    // Ignore data lines before start target
                        this.in.readObject();
                        this.i++;
                    }
                    // start target equals i
                    while(start >= i) {
                        this.aux = (KStick) this.in.readObject();
                        this.kdata.add(this.aux);
                        this.i++;
                    }
                } catch(EOFException e) { System.out.println(" -- EOF -- "); } 
            } finally {
                this.in.close(); 
                this.fileIn.close();
                return this.kdata;
            }
        } else {
            System.out.println("Invalid targets");
            return null;
        }
    }
    
    // Reads all binary file data and returns an array list of the data
    public ArrayList<KStick> readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        this.kdata.clear();

        this.fileIn = new FileInputStream(this.file); 
        this.in = new ObjectInputStream(this.fileIn);
        
        try {
            try {
                while(true) {
                    this.aux = (KStick) this.in.readObject();
                    this.kdata.add(this.aux);
                }
            } catch(EOFException e) { System.out.println(" -- EOF -- "); } 
        } finally {
            this.in.close(); 
            this.fileIn.close();
            return this.kdata;
        }
    }
}