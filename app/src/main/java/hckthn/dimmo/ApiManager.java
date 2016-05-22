package hckthn.dimmo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WILSOND on 5/21/16.
 */
public class ApiManager {
    public static JSONObject dimmos;
    public static String baseurl = "http://192.168.1.14:9000/";

    public static void getDimmmos(Context context, final Load l){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseurl + "dimmos";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    l.onLoaded(new JSONArray(response));
                    Log.d("666",response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("666","error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
    public static void getProducts(Context context, final Load l) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseurl + "product/" + "1";
        Log.d("api","creating request");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("api", "loaded");
                    l.onLoaded(new JSONArray(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
    public static void getCookies(Context context, final Load l) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseurl + "product/" + "2";
        Log.d("api","creating request");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("api","loaded");
                    l.onLoaded(new JSONArray(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    public interface Load {
        void onLoaded(JSONArray response);
    }
}
