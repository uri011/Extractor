package extractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Uri
 */

public class FileIO {
    
    private File file;
    
    private FileWriter fw;
    private BufferedWriter bw;
    
    private FileReader fr;
    private BufferedReader br;
    
    private StringBuffer sbuff;
    private String aux;
    
    public FileIO(String filePath) {

        this.file = new File(filePath);
    }
    
    public void writeFile(StringBuffer buff) throws IOException {
        
        this.fw = new FileWriter(this.file);
        this.bw = new BufferedWriter(this.fw);
        
        this.bw.write(buff.toString());
        
        this.bw.close();
        this.fw.close();
    }
    
    public StringBuffer readFile() throws FileNotFoundException, IOException {
        
        this.fr = new FileReader(this.file);
        this.br = new BufferedReader(this.fr);
        
        this.sbuff = new StringBuffer();
        
        while((this.aux = br.readLine()) != null) {
            this.sbuff.append(this.aux);
            this.sbuff.append("\n");
        }  
        
        this.br.close();
        this.fr.close(); 
        return this.sbuff;
    }
    
    public String[] getExtractionHeader(int nh) throws IOException {
        
        this.fr = new FileReader(this.file);
        this.br = new BufferedReader(this.fr);
        
        String[] h = new String[nh];
        
        for(int i = 0; i < nh; i++)
            h[i] = this.br.readLine();
        
        this.br.close();
        this.fr.close();
        return h;
    }
    
    public String[] getExtractionProperties(String ID, int np) throws FileNotFoundException, IOException {
        
        this.fr = new FileReader(this.file);
        this.br = new BufferedReader(this.fr);
        
        String[] p = new String[np];
        
        while(!((this.aux = br.readLine()).equals(ID)));
         
        for(int i = 0; i < np; i++)
            p[i] = this.br.readLine();
        
        this.br.close();
        this.fr.close();
        return p;
    }
    
    public void setParameter(String ID, String param, int lineOffset) throws FileNotFoundException, IOException {
        
        this.fr = new FileReader(this.file);
        this.br = new BufferedReader(this.fr);
        
        this.sbuff = new StringBuffer();
        
        while((this.aux = br.readLine()) != null) {
            
            if(this.aux.equals(ID)) {
                this.sbuff.append(this.aux);
                this.sbuff.append("\n");
                
                while(lineOffset > 0) {
                    this.aux = br.readLine();
                    this.sbuff.append(this.aux);
                    this.sbuff.append("\n");
                    lineOffset--;
                }
                
                this.aux = br.readLine();
                this.sbuff.append(param);
                this.sbuff.append("\n");
            
            } else {
                this.sbuff.append(this.aux);
                this.sbuff.append("\n");
            }
        }  
        
        this.br.close();
        this.fr.close(); 
        
        this.writeFile(this.sbuff);
    }
    
    public void setFile(String filePath) {
        this.file = new File(filePath);
    }
}