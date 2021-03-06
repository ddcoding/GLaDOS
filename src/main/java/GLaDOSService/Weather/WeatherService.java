package GLaDOSService.Weather;

import GLaDOSService.CommandNotFoundException;
import GLaDOSService.GLaDOSService;
import GeoIP.GeoIPInt;
import GeoIP.GeoIPService;
import Player.Player;
import Exceptions.CannotBuildException;
import Weather.ForecastMode;
import Weather.Service.CannotGetWeatherException;
import Weather.Service.WeatherResponseService;
import Weather.WeatherIt;

import java.io.IOException;
import java.text.ParseException;

public class WeatherService {


    private WeatherResponseService weatherResponseService;

    private GeoIPInt geoIPService;


    public WeatherService() {
        weatherResponseService = new WeatherResponseService();
        geoIPService = new GeoIPService();
    }

    public void sayWeather() {
        try {
            Player.getInstance().speak("Z jakiego miasta chcesz usłyszeć pogodę?");
            String miasto = GLaDOSService.getTranscriptFromRecord(3000);
            if(miasto.toLowerCase().equals("z obecnego")) miasto = geoIPService.getMyCityName();
            Player.getInstance().speak("Chcesz usłyszeć na dzisiaj, na jutro czy na pojutrze pogodę w miejscowości" + miasto);
            String forecast = GLaDOSService.getTranscriptFromRecord(3000).toLowerCase();
            Player.getInstance().speak("Pogoda " + forecast + " w miejscowości " + miasto + " to");
            getWeather(miasto, forecast, true);
        } catch (Exception e) {
            Player.getInstance().speak("Wystąpił błąd w trakcie sprawdzania pogody !");
            e.printStackTrace();
        }

    }

    public void getWeather(String city, String forecast, boolean repeat) throws CommandNotFoundException {
        try {
            String uri = "";
            weatherResponseService
                    .getWeatherUriBuilder()
                    .city(city);
            switch (forecast) {
                case "na dzisiaj":
                    uri = weatherResponseService
                            .getWeatherUriBuilder()
                            .mode(ForecastMode.CURRENTLY)
                            .build();
                    break;
                case "na jutro":
                    uri = weatherResponseService
                            .getWeatherUriBuilder()
                            .mode(ForecastMode.FUTURE)
                            .amountFutureDays(1)
                            .build();
                    break;
                case "na pojutrze":
                    uri = weatherResponseService
                            .getWeatherUriBuilder()
                            .mode(ForecastMode.FUTURE)
                            .amountFutureDays(2)
                            .build();
                    break;
                default:
                    throw new CommandNotFoundException();
            }
            weatherResponseService.setURI(uri);
            WeatherIt weather = weatherResponseService.getWeather();
            String result = weather.getTemperature() + "stopni oraz " + weather.getAdditionalInformation() + ". Życzę miłego dnia.";
            Player.getInstance().speak(result);
            if(repeat)
            GLaDOSService.repeatPhrase(result);
        } catch (CannotBuildException | IOException | CannotGetWeatherException | ParseException e) {
            e.printStackTrace();
        }
    }

}
