package util;

import java.util.Date;

/**
 * @author Uri
 */

public final class TimeTool {
    
    private static final int JA = 0;   // January index
    private static final int FB = 1;   // February index
    private static final int MR = 2;   // March index
    private static final int AP = 3;   // April index
    private static final int MY = 4;   // May index
    private static final int JN = 5;   // June index
    private static final int JY = 6;   // July index
    private static final int AG = 7;   // August index
    private static final int SP = 8;   // September index
    private static final int OC = 9;   // October index
    private static final int NV = 10;  // November index
    private static final int DC = 11;  // December index
    
    private static final int N_JA = 31;   // Days on January month
    private static final int N_MR = 31;   // Days on March month
    private static final int N_AP = 30;   // Days on April month
    private static final int N_MY = 31;   // Days on May month
    private static final int N_JN = 30;   // Days on June month
    private static final int N_JY = 31;   // Days on July month
    private static final int N_AG = 31;   // Days on August month
    private static final int N_SP = 30;   // Days on September month
    private static final int N_OC = 31;   // Days on October month
    private static final int N_NV = 30;   // Days on November month
    private static final int N_DC = 31;   // Days on December month
    
    private static final String ID_JA = "01";    // January ID
    private static final String ID_FB = "02";    // February ID
    private static final String ID_MR = "03";    // March ID
    private static final String ID_AP = "04";    // April ID
    private static final String ID_MY = "05";    // May ID
    private static final String ID_JN = "06";    // June ID
    private static final String ID_JY = "07";    // July ID
    private static final String ID_AG = "08";    // August ID
    private static final String ID_SP = "09";    // September ID
    private static final String ID_OC = "10";    // October ID
    private static final String ID_NV = "11";    // November ID
    private static final String ID_DC = "12";    // December ID
    
    // Returns distance in miliseconds
    public long datesDistance(Date dateX, Date dateY) {
        
        if(dateX.before(dateY))
            return dateY.getTime() - dateX.getTime();
        else
            return dateX.getTime() - dateY.getTime();
    }
    
    public static int getDaysOnMonth(int year, int month) {
        
        switch(month) {
            case JA:
                return N_JA;
            case FB:
                if(isLeapYear(year))
                    return 29;
                else
                    return 28;
            case MR:
                return N_MR;
            case AP:
                return N_AP;
            case MY:
                return N_MY;
            case JN:
                return N_JN;
            case JY:
                return N_JY;
            case AG:
                return N_AG;
            case SP:
                return N_SP;
            case OC:
                return N_OC;
            case NV:
                return N_NV;
            case DC:
                return N_DC;
            default:
                return -1;
        }     
    }
    
    public static String getMonthID(int month) {
        
        switch(month) {
            case JA:
                return ID_JA;
            case FB:
                return ID_FB;
            case MR:
                return ID_MR;
            case AP:
                return ID_AP;
            case MY:
                return ID_MY;
            case JN:
                return ID_JN;
            case JY:
                return ID_JY;
            case AG:
                return ID_AG;
            case SP:
                return ID_SP;
            case OC:
                return ID_OC;
            case NV:
                return ID_NV;
            case DC:
                return ID_DC;
            default:
                return "null";
        }
    }
    
    private static boolean isLeapYear(int year) {
        
        if(year % 4 == 0)
            if(year % 100 == 0)
                if(year % 400 == 0)
                    return true;
                else
                    return false;
            else
                return true;
        else
            return false;
    }
}