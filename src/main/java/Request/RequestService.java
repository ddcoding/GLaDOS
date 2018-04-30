package Request;

import Weather.ForecastMode;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestService {
    private final static String USER_AGENT = "Mozilla/5.0";

    public JSONObject getJSONfromURL(String URI, boolean secured) throws IOException, CannotGetResponseException {
        String prefix = secured ? "https://" : "http://";
        URL obj = new URL(prefix + URI);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", RequestService.USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode >= 300) throw new CannotGetResponseException();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

}
