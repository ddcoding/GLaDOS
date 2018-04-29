package GeoIP;

import Request.CannotGetResponseException;
import Request.RequestService;
import org.json.JSONObject;

import java.io.IOException;

public class GeoIPService implements GeoIPInt{
    private final static String URL = "ip-api.com/json";

    private RequestService requestService;

    public GeoIPService() {
        requestService = new RequestService();
    }

    @Override
    public JSONObject getMyLocalization() throws CannotGetLocalizationException {
        try {
            return requestService.getJSONfromURL(GeoIPService.URL);
        } catch (IOException | CannotGetResponseException e) {
            e.printStackTrace();
            throw new CannotGetLocalizationException();
        }
    }
    @Override
    public JSONObject getLocalizationFromIP(String ip) throws CannotGetLocalizationException {
        try {
            return requestService.getJSONfromURL(GeoIPService.URL + "/" + ip);
        } catch (IOException | CannotGetResponseException e) {
            e.printStackTrace();
            throw new CannotGetLocalizationException();
        }
    }
    @Override
    public String getMyIPAddress() throws CannotGetLocalizationException {
        return getMyLocalization().getString("query");
    }
    @Override
    public String getMyCityName() throws CannotGetLocalizationException {
        return getMyLocalization().getString("city");
    }
}
