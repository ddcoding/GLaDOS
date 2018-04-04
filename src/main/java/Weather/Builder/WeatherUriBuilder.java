package Weather.Builder;


import Weather.ForecastMode;

/**
 * It's class for build URI, to send http GET request for weather.
 * It's using http://openweathermap.org site for getting currently and future weather.
 * I'm using API_KEY downloaded from this site.
 * This is free version(using only currently and 5 days forecast), everything is legit.
 *
 * @link http://openwaethermap.org
 * Cheers :)
 */
public class WeatherUriBuilder {
    private static final String BASE_URL_WEATHER = "api.openweathermap.org/data/2.5";

    private static final String API_KEY = "f8ac12b15cd8370cf93a5d9b0c368201";

    private String countryCode;

    private String city;

    private Integer amountFutureDays;

    /**
     * We need that mode to set, if we want today forecast weather or future.
     */
    private ForecastMode mode;

    public WeatherUriBuilder() {
    }

    public String getCity() {
        return city;
    }

    public WeatherUriBuilder city(String city) {
        this.city = city;
        return this;
    }

    public ForecastMode getMode() {
        return mode;
    }

    public WeatherUriBuilder mode(ForecastMode mode) {
        this.mode = mode;
        return this;
    }

    public WeatherUriBuilder amountFutureDays(int i) {
        this.amountFutureDays = i;
        return this;
    }

    public Integer getAmountFutureDays() {
        return this.amountFutureDays;
    }

    public String getCountryCode() {
        return countryCode;
    }

    /**
     * This method is optional, because if we dont enter it, API will complete it automatically.
     *
     * @param countryCode
     * @return WeatherUriBuilder
     */
    public WeatherUriBuilder countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String build() throws CannotBuildException {

        if (getCity() == null
                || getMode() == null
                || (getMode() == ForecastMode.FUTURE && getAmountFutureDays() == null))
            throw new CannotBuildException("Cannot buid URI, because you didnt set all required values!");

        return WeatherUriBuilder.BASE_URL_WEATHER +
                (getMode() == ForecastMode.CURRENTLY ? "/weather?q=" : "/forecast?q=") +
                getCity() +
                (getCountryCode() == null ? "" : "," + getCountryCode()) +
                "&appid=" + WeatherUriBuilder.API_KEY;
    }
}
