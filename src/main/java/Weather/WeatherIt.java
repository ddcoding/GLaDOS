package Weather;

import java.io.IOException;

public interface WeatherIt {
    String getAdditionalInformation() throws IOException;

    public String getTemperature();
}
