package extractor;

import com.google.gson.JsonArray;
import java.io.Serializable;

import java.math.BigDecimal;

/**
 * @author Uri
 */

public class KStick implements Serializable {

    // PROBAR SI TORNAR A POSAR BIG DECIMAL PERMET SERIABILITZAR
    public Long openTime;
    public String open;
    public String high;
    public String low;
    public String close;
    public String volume;
    public String quoteAssetVolume;
    public Long numberOfTrades;
    public String takerBuyBaseAssetVolume;
    public String takerBuyQuoteAssetVolume;

    public KStick(JsonArray jsonArray) {

        this.openTime = (jsonArray.get(0).getAsLong());
        this.open = (jsonArray.get(1).getAsString());
        this.high = (jsonArray.get(2).getAsString());
        this.low = (jsonArray.get(3).getAsString());
        this.close = (jsonArray.get(4).getAsString());
        this.volume = (jsonArray.get(5).getAsString());
        this.quoteAssetVolume = (jsonArray.get(7).getAsString());
        this.numberOfTrades = (jsonArray.get(8).getAsLong());
        this.takerBuyBaseAssetVolume = (jsonArray.get(9).getAsString());
        this.takerBuyQuoteAssetVolume = (jsonArray.get(10).getAsString());
    }

    /*public Long getOpenTime() {
        return this.openTime;
    }
    
    public BigDecimal getOpen() {
        return this.open;
    }
    
    public BigDecimal getHigh() {
        return this.high;
    }
    
    public BigDecimal getLow() {
        return this.low;
    }
    
    public BigDecimal getClose() {
        return this.close;
    }
    
    public BigDecimal getVolume() {
        return this.volume;
    }
    
    public BigDecimal getQuoteAssetVolume() {
        return this.quoteAssetVolume;
    }
    
    public Long getNumberOfTrades() {
        return this.numberOfTrades;
    }
    
    public BigDecimal getTakerBuyBaseAssetVolume() {
        return (BigDecimal) this.takerBuyBaseAssetVolume;
    }
    
    public BigDecimal getTakerBuyQuoteAssetVolume() {
        return this.takerBuyQuoteAssetVolume;
    }
    
    /*public String getParameter(String param) {

        switch (param) {
            case "openTime":
                return this.openTime.toString();
            case "open":
                return this.open.toString();
            case "high":
                return this.high.toString();
            case "low":
                return this.low.toString();
            case "close":
                return this.close.toString();
            case "volume":
                return this.volume.toString();
            case "quoteAssetVolume":
                return this.quoteAssetVolume.toString();
            case "numberOfTrades":
                return this.numberOfTrades.toString();
            case "takerBuyBaseAssetVolume":
                return this.takerBuyBaseAssetVolume.toString();
            case "takerBuyQuoteAssetVolume":
                return this.takerBuyQuoteAssetVolume.toString();
            default:
                return "Parameter Not Found";
        }
    }*/

    @Override
    public String toString() {

        String string = "[ openTime: " + this.openTime.toString()
                + "/ open: " + this.open
                + "/ high: " + this.high
                + "/ low: " + this.low
                + "/ close: " + this.close
                + "/ volume: " + this.volume
                + "/ quoteAssetVolume: " + this.quoteAssetVolume
                + "/ numberOfTrades: " + this.numberOfTrades.toString()
                + "/ takerBuyBaseAssetVolume: " + this.takerBuyBaseAssetVolume
                + "/ takerBuyQuoteAssetVolume: " + this.takerBuyQuoteAssetVolume + " ]";

        return string;
    }
}