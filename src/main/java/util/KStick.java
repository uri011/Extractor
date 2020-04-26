package util;

import com.google.gson.JsonArray;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Uri
 */

public class KStick implements Serializable {

    private final Long openTime;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;
    private final BigDecimal volume;
    private final BigDecimal quoteAssetVolume;
    private final Long numberOfTrades;
    private final BigDecimal takerBuyBaseAssetVolume;
    private final BigDecimal takerBuyQuoteAssetVolume;

    public KStick(JsonArray jsonArray) {

        this.openTime = (jsonArray.get(0).getAsLong());
        this.open = (jsonArray.get(1).getAsBigDecimal());
        this.high = (jsonArray.get(2).getAsBigDecimal());
        this.low = (jsonArray.get(3).getAsBigDecimal());
        this.close = (jsonArray.get(4).getAsBigDecimal());
        this.volume = (jsonArray.get(5).getAsBigDecimal());
        this.quoteAssetVolume = (jsonArray.get(7).getAsBigDecimal());
        this.numberOfTrades = (jsonArray.get(8).getAsLong());
        this.takerBuyBaseAssetVolume = (jsonArray.get(9).getAsBigDecimal());
        this.takerBuyQuoteAssetVolume = (jsonArray.get(10).getAsBigDecimal());
    }

    public Long getOpenTime() {
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
        return this.takerBuyBaseAssetVolume;
    }
    
    public BigDecimal getTakerBuyQuoteAssetVolume() {
        return this.takerBuyQuoteAssetVolume;
    }

    @Override
    public String toString() {

        String string = "[openTime:" + this.openTime.toString()
                + "/open:" + this.open.toPlainString()
                + "/high:" + this.high.toPlainString()
                + "/low:" + this.low.toPlainString()
                + "/close:" + this.close.toPlainString()
                + "/volume:" + this.volume.toPlainString()
                + "/qav:" + this.quoteAssetVolume.toPlainString()
                + "/nt: " + this.numberOfTrades.toString()
                + "/tbbav: " + this.takerBuyBaseAssetVolume.toPlainString()
                + "/tbqav: " + this.takerBuyQuoteAssetVolume.toPlainString() + "]";

        return string;
    }
}