package Weather.Service;

import Weather.Builder.WeatherUriBuilder;
import Weather.ForecastMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

public class WeatherRequestService {

    private String URI;

    private WeatherUriBuilder weatherUriBuilder;

    private final static String USER_AGENT = "Mozilla/5.0";

    public WeatherRequestService() {
        weatherUriBuilder = new WeatherUriBuilder();
    }

    public WeatherUriBuilder getWeatherUriBuilder() {
        return weatherUriBuilder;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public JSONObject getWeather() throws IOException, CannotGetWeatherException, ParseException {
        URL obj = new URL("http://" + getURI());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", WeatherRequestService.USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode >= 300) throw new CannotGetWeatherException();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        JSONObject result = new JSONObject(response.toString());
        if(getWeatherUriBuilder().getMode() == ForecastMode.FUTURE) result = getFutureWeatherObject(result);
        return result;
    }

    private JSONObject getFutureWeatherObject(JSONObject jsonObject) throws ParseException {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object aJsonArray : jsonArray) {
            JSONObject object = (JSONObject) aJsonArray;
            if (object.getString("dt_txt").equals(getFutureDate(getWeatherUriBuilder().getAmountFutureDays()) +  " " + "12:00:00"))
            return object;
        }
        return null;
    }

    private String getFutureDate(int amountDays) throws ParseException {
        String untildate= Instant.now().toString();//can take any date in current format
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar cal = Calendar.getInstance();
        cal.setTime( dateFormat.parse(untildate));
        cal.add( Calendar.DATE, amountDays );
        String convertedDate=dateFormat.format(cal.getTime());
        return convertedDate;
    }
}
