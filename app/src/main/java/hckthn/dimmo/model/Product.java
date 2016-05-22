package hckthn.dimmo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by WILSOND on 5/22/16.
 */
public class Product {
    public String name;
    public String image;
    public String description;
    public int price;
    public int kind;



    public static ArrayList<Product> getFoods(JSONArray foods) {
        ArrayList<Product> ps  = new ArrayList<>();
        try {
            for (int h = 0; h < foods.length(); h++) {
                JSONObject jo = foods.getJSONObject(h);
                Product p = new Product();
                p.name = jo.getString("name");
                ps.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ps;
    }
}
