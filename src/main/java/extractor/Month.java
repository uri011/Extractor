package extractor;

import java.util.ArrayList;

/**
 * @author Uri
 */

public class Month {
    
    private final int year;    // Year of month
    
    private final int JA = 31;   // Days on January month
    private final int FB;        // Days on February month
    private final int MR = 31;   // Days on March month
    private final int AP = 30;   // Days on April month
    private final int MY = 31;   // Days on May month
    private final int JN = 30;   // Days on June month
    private final int JY = 31;   // Days on July month
    private final int AG = 31;   // Days on August month
    private final int SP = 30;   // Days on September month
    private final int OC = 31;   // Days on October month
    private final int NV = 30;   // Days on November month
    private final int DC = 31;   // Days on December month
    
    protected final ArrayList<String> ID;
    
    protected Month(int y) {
        
        this.year = y;
        
        if(this.leapYearCheck())
            this.FB = 29;
        else
            this.FB = 28;
        
        this.ID = new ArrayList<>();
        this.initializeIDs();
    }
    
    protected String getMID(int m) {
        return this.ID.get(m);
    }
    
    protected int getNumDays(String m) {
        
        switch(m) {
            case "JA":
                return this.JA;
            case "FB":
                return this.FB;
            case "MR":
                return this.MR;
            case "AP":
                return this.AP;
            case "MY":
                return this.MY;
            case "JN":
                return this.JN;
            case "JY":
                return this.JY;
            case "AG":
                return this.AG;
            case "SP":
                return this.SP;
            case "OC":
                return this.OC;
            case "NV":
                return this.NV;
            case "DC":
                return this.DC;
            default:
                return -1;
        }
    }
    
    private boolean leapYearCheck() {
        
        if(this.year % 4 == 0)
            if(this.year % 100 == 0)
                if(this.year % 400 == 0)
                    return true;
                else
                    return false;
            else
                return true;
        else
            return false;
    }
    
    private void initializeIDs() {
        
        this.ID.add("JA");
        this.ID.add("FB");
        this.ID.add("MR");
        this.ID.add("AP");
        this.ID.add("MY");
        this.ID.add("JN");
        this.ID.add("JY");
        this.ID.add("AG");
        this.ID.add("SP");
        this.ID.add("OC");
        this.ID.add("NV");
        this.ID.add("DC");
    }
}
