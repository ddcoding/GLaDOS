package GoogleDestination;

import GoogleDestination.Builder.GoogleDestinationUriBuilder;
import Player.Player;
import Request.CannotGetResponseException;
import Request.RequestService;
import org.json.JSONObject;

import java.io.IOException;

public class GoogleDestinationService {

    private GoogleDestinationUriBuilder googleDestinationUriBuilder;

    private String URI;

    private RequestService requestService;

    public GoogleDestinationService() {
        googleDestinationUriBuilder = new GoogleDestinationUriBuilder();
        requestService = new RequestService();
    }

    public GoogleDestinationUriBuilder getGoogleDestinationUriBuilder() {
        return googleDestinationUriBuilder;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public GoogleDest getGoogleDestinationObject(){
        try {
            return new GoogleDestImpl(requestService.getJSONfromURL(getURI(),true));
        } catch (IOException | CannotGetResponseException e) {
            e.printStackTrace();
            Player.getInstance().speak("Wystąpił błąd w trakcie pobierania informacji o odległości.");
        }
        return null;
    }
}
