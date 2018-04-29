package GeoIP;

import org.json.JSONObject;

public interface GeoIPInt {
    JSONObject getMyLocalization() throws CannotGetLocalizationException;

    JSONObject getLocalizationFromIP(String ip) throws CannotGetLocalizationException;

    String getMyIPAddress() throws CannotGetLocalizationException;

    String getMyCityName() throws CannotGetLocalizationException;
}
