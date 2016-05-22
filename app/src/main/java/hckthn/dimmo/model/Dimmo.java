package hckthn.dimmo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by WILSOND on 5/21/16.
 */
public class Dimmo {
    public String name = "";
    public String description = "";
    public String image = "";
    public int price = 0;

    public static ArrayList<Dimmo> mainDimmos(JSONArray ja) {
        ArrayList<Dimmo> dimmos = new ArrayList<>();
        try {
            //JSONArray ja = new JSONArray(js.toString());
            for (int g = 0; g < ja.length() ; g++ ){
                JSONObject jo = ja.getJSONObject(g);
                Dimmo d = new Dimmo();
                d.name = jo.getString("name");
                d.description = jo.getString("description");
                dimmos.add(d);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dimmos;
    }
}
