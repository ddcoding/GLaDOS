package GLaDOSService.Destination;

import Exceptions.CannotBuildException;
import GLaDOSService.GLaDOSService;
import GeoIP.CannotGetLocalizationException;
import GeoIP.GeoIPInt;
import GeoIP.GeoIPService;
import GoogleDestination.GoogleDestImpl;
import GoogleDestination.GoogleDestinationService;
import Player.Player;
import Structures.Pair;
import Translate.GoogleTranslate;

import java.io.IOException;

public class DestinationService {

    private GoogleDestinationService googleDestinationService;

    private GeoIPInt geoIPservice;

    public DestinationService() {
        googleDestinationService = new GoogleDestinationService();
        geoIPservice = new GeoIPService();
    }

    public void getDestinationInf(){
        Player.getInstance().speak("Z jakiego miasta chcesz zmierzyć odległość ?");
        String from = GLaDOSService.getTranscriptFromRecord(3000);
        if(from.toLowerCase().equals("z obecnego")) {
            try {
                from = geoIPservice.getMyCityName();
            } catch (CannotGetLocalizationException e) {
                e.printStackTrace();
            }
        }
        Player.getInstance().speak("Do jakiego miasta ?");
        String to = GLaDOSService.getTranscriptFromRecord(3000);
        String uri = "";
        try {
            uri = googleDestinationService.getGoogleDestinationUriBuilder()
                    .addEntry(from,to)
                    .build();
        } catch (CannotBuildException e) {
            e.printStackTrace();
        }
        googleDestinationService.setURI(uri);
        Pair<String,String> stringPair = googleDestinationService.getGoogleDestinationObject().getDistanceDuration().get(0);
        String odleglosc = stringPair.getFirst();
        String czas = null;
        try {
            czas = GoogleTranslate.translate("en","pl",stringPair.getSecond());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String phrase = "Odległość wynosi " + odleglosc + " dotrzesz tam w " + czas;
        Player.getInstance().speak(phrase);
        GLaDOSService.repeatPhrase(phrase);
    }
}
