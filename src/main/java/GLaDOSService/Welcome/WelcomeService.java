package GLaDOSService.Welcome;

import GLaDOSService.CommandNotFoundException;
import GLaDOSService.DateTime.DateTimeService;
import GLaDOSService.DateTime.DateTimeType;
import GLaDOSService.GLaDOSService;
import GLaDOSService.Weather.WeatherService;
import GeoIP.CannotGetLocalizationException;
import GeoIP.GeoIPInt;
import GeoIP.GeoIPService;
import Player.Player;
import org.joda.time.DateTime;

public class WelcomeService {
    private DateTimeService dateTimeService;
    private WeatherService weatherService;
    private GeoIPInt geoIPService;
    public WelcomeService() {
        dateTimeService = new DateTimeService();
        weatherService = new WeatherService();
        geoIPService = new GeoIPService();
    }

    public void speakWelcomeMessage(){
        Player.getInstance().speak("Hej ! Mamy dzisiaj");
        dateTimeService.checkDateTime(DateTimeType.DATE,false);
        Player.getInstance().speak("jest godzina");
                dateTimeService.checkDateTime(DateTimeType.TIME,false);
                Player.getInstance().speak("Pogoda na dzisiaj to");
        try {
            weatherService.getWeather(geoIPService.getMyCityName(),"na dzisiaj",false);
            Player.getInstance().speak("Czy powtórzyć ?");
            if(GLaDOSService.getTranscriptFromRecord(2000).toLowerCase().equals("tak"))
                speakWelcomeMessage();
        } catch (CommandNotFoundException | CannotGetLocalizationException e) {
            e.printStackTrace();
            Player.getInstance().speak("Wystapil blad w trakcie pobierania aktualnej lokalizacji. Przepraszam, jest mi przykro");
        }
    }

    public void speakGoodbyeMessage(){
        Player.getInstance().speak("Dobranoc. Życzę kolorowych snów. Mam nadzieję, że jutrzejsza pogoda sprawi, że Twój sen będzie udany. Pogoda na jutro to");
        try {
            weatherService.getWeather(geoIPService.getMyCityName(),"na jutro",false);
        } catch (CommandNotFoundException | CannotGetLocalizationException e) {
            e.printStackTrace();
            Player.getInstance().speak("Wystapil blad w trakcie pobierania aktualnej lokalizacji. Przepraszam, jest mi przykro");
        }
    }
}
