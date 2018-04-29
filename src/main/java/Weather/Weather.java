package Weather;

import Translate.GoogleTranslate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class Weather implements WeatherIt{
    private JSONObject currentWeather ;

    public Weather(JSONObject currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Override
    public String getAdditionalInformation() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        Iterator<Object> it = ((JSONArray)currentWeather.get("weather")).iterator();
        JSONObject jsonObject = (JSONObject) it.next();
        stringBuilder.append(GoogleTranslate.translate("en", "pl", jsonObject.getString("description")));
        while (it.hasNext()) {
            jsonObject = (JSONObject) it.next();
            stringBuilder
                    .append(" oraz ")
                    .append(GoogleTranslate.translate("en", "pl", jsonObject.getString("description")));
        }
        return stringBuilder.toString();
    }

    @Override
    public String getTemperature(){
       return  String.format("%.2f",((JSONObject) currentWeather.get("main")).getDouble("temp") - 273.15);
    }
}
