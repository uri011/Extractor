package extractor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import util.KStick;

/**
 * @author Uri
 */

// Server Data Extractor

public class ServerExtractor {
    
    private final String sourceURL;
    private String URL;
    
    private JsonArray jsonElements;
    private JsonParser jsonParser;
    
    private InputStream is;
    private BufferedReader br;
    
    private ArrayList<KStick> data;
    private String extractedData;
    
    public ServerExtractor(String source) {
        
        this.sourceURL = source;
        this.jsonParser = new JsonParser();
        this.data = new ArrayList<>();
    }
    
    // Extracts data from startTime to endTime
    // Returns a list of the extracted data
    public ArrayList<KStick> extract(String startTime, String limit) throws IOException {

        this.URL = this.sourceURL + "&startTime=" + startTime + "&limit=" + limit;
        
        this.is = new URL(this.URL).openStream();

        try {
            
            this.br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            this.extractedData = br.readLine();

            jsonElements = this.asJsonArray(extractedData);

            for (JsonElement element : jsonElements)
                this.data.add(new KStick(element.getAsJsonArray()));

        } finally {
            is.close();
            return this.data;
        }
    }
    
    public JsonArray asJsonArray(String data) {

        return (JsonArray) this.jsonParser.parse(data);
    }
}