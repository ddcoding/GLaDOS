package GoogleDestination.Builder;

import Exceptions.CannotBuildException;

import java.util.ArrayList;

/**
 * http://developers.google.com/maps/documentation/distance-matrix/
 * It used for this class API GOOGLE DESTINATION MATRIX
 */
public class GoogleDestinationUriBuilder {
    private static final String API_KEY = "AIzaSyADeLCBCAu1N_HCbYlloXv2H381pfKpFKY";

    private static final String BASE_URL_DESTINATION = "maps.googleapis.com/maps/api/distancematrix/json?";

    private ArrayList<String> origins;

    private ArrayList<String> destinations;

    public GoogleDestinationUriBuilder() {
        origins = new ArrayList<>();
        destinations = new ArrayList<>();
    }

    public GoogleDestinationUriBuilder addEntry(String from, String to){
        this.origins.add(from);
        this.destinations.add(to);
        return this;
    }

    public String build() throws CannotBuildException {
        if(this.origins.isEmpty() || this.destinations.isEmpty()) throw new CannotBuildException("Cannot build URI, because you didnt set all required values!");
        StringBuilder uri = new StringBuilder(GoogleDestinationUriBuilder.BASE_URL_DESTINATION + "origins=");
        for(int i = 0 ; i < this.origins.size() ; i++){
            if(i!=0) uri.append(",");
            uri.append(this.origins.get(i));
        }
        uri.append("&destinations=");
        for(int i = 0 ; i < this.destinations.size() ; i++){
            if(i!=0) uri.append(",");
            uri.append(this.destinations.get(i));
        }
        uri.append("&key=" + GoogleDestinationUriBuilder.API_KEY);
        return uri.toString();
    }

}
