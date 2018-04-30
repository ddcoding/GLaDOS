package GoogleDestination;

import Structures.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GoogleDestImpl implements GoogleDest {

    private JSONObject googleDestObject;

    public GoogleDestImpl(JSONObject googleDestObject) {
        this.googleDestObject = googleDestObject;
    }

    @Override
    public ArrayList<Pair<String,String>> getDistanceDuration(){
        ArrayList<Pair<String,String>> distanceDurationList = new ArrayList<>();
        JSONArray rows = googleDestObject.getJSONArray("rows");
        for(Object obj : rows){
            JSONArray elements = ((JSONObject) obj).getJSONArray("elements");
            for(Object el: elements){
                JSONObject element = (JSONObject)((JSONObject)el);
                distanceDurationList.add(new Pair<String,String>((element.getJSONObject("distance")).getString("text"),element.getJSONObject("duration").getString("text")));
            }
        }
        return distanceDurationList;
    }

}
