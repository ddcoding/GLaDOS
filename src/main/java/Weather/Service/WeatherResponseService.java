package Weather.Service;

import Request.CannotGetResponseException;
import Request.RequestService;
import Weather.Builder.WeatherUriBuilder;
import Weather.ForecastMode;
import Weather.Weather;
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

public class WeatherResponseService {

    private String URI;

    private WeatherUriBuilder weatherUriBuilder;

    private RequestService requestService;

    public WeatherResponseService() {
        weatherUriBuilder = new WeatherUriBuilder();
        requestService = new RequestService();
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



    public Weather getWeather() throws IOException, CannotGetWeatherException, ParseException {
        JSONObject result = null;
        try {
            result = requestService.getJSONfromURL(getURI());
        } catch (CannotGetResponseException e) {
            throw new CannotGetWeatherException();
        }
        if(getWeatherUriBuilder().getMode() == ForecastMode.FUTURE) result = getFutureWeatherObject(result);
        return new Weather(result);
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
