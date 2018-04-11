package GLaDOSService.Weather;

import GLaDOSService.CommandNotFoundException;
import GLaDOSService.GLaDOSService;
import Player.Player;
import SoundRecord.RecordService;
import Translate.GoogleTranslate;
import Weather.Builder.CannotBuildException;
import Weather.ForecastMode;
import Weather.Service.CannotGetWeatherException;
import Weather.Service.WeatherRequestService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

public class WeatherService {

    private RecordService recordService;

    private Player player;

    private WeatherRequestService weatherRequestService;


    public WeatherService() {
        recordService = new RecordService();
        player = new Player();
        weatherRequestService = new WeatherRequestService();
    }

    public void sayWeather() {
        try {
            String miasto = GLaDOSService.getTranscriptFromRecord(3000);
            player.speak("Chcesz usłyszeć na dzisiaj, na jutro czy na pojutrze pogodę w miejscowości" + miasto);
            String forecast = GLaDOSService.getTranscriptFromRecord(3000).toLowerCase();
            player.speak("Pogoda " + forecast + " w miejscowości " + miasto + " to");
            getWeather(miasto, forecast);
        } catch (Exception e) {
            player.speak("Wystąpił błąd w trakcie sprawdzania pogody !");
            e.printStackTrace();
        }

    }

    private void getWeather(String city, String forecast) throws CommandNotFoundException {
        try {
            String uri = "";
            weatherRequestService
                    .getWeatherUriBuilder()
                    .city(city);
            switch (forecast) {
                case "na dzisiaj":
                    uri = weatherRequestService
                            .getWeatherUriBuilder()
                            .mode(ForecastMode.CURRENTLY)
                            .build();
                    break;
                case "na jutro":
                    uri = weatherRequestService
                            .getWeatherUriBuilder()
                            .mode(ForecastMode.FUTURE)
                            .amountFutureDays(1)
                            .build();
                    break;
                case "na pojutrze":
                    uri = weatherRequestService
                            .getWeatherUriBuilder()
                            .mode(ForecastMode.FUTURE)
                            .amountFutureDays(2)
                            .build();
                    break;
                default:
                    throw new CommandNotFoundException();
            }
            weatherRequestService.setURI(uri);
            JSONObject jsonObject = weatherRequestService.getWeather();
            Double degrees = ((JSONObject) jsonObject.get("main")).getDouble("temp");
            String status = additionalInformation((JSONArray) jsonObject.get("weather"));
            String result = String.format("%.2f", degrees - 273.15) + "stopni oraz " + status + ". Życzę miłego dnia.";
            player.speak(result);
            GLaDOSService.repeatPhrase(result);
        } catch (CannotBuildException | IOException | CannotGetWeatherException | ParseException e) {
            e.printStackTrace();
        }
    }

    private String additionalInformation(JSONArray weather) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        Iterator<Object> it = weather.iterator();
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
}
