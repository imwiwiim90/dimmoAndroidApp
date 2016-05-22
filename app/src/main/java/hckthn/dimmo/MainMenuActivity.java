package hckthn.dimmo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import hckthn.dimmo.model.Dimmo;

/**
 * Created by WILSOND on 5/21/16.
 */
public class MainMenuActivity extends AppCompatActivity {
    private ListView list;
    private ArrayList<Dimmo> dimmos;
    private DimmosAdapter dimmosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        ApiManager.getDimmmos(this, new ApiManager.Load() {
            @Override
            public void onLoaded(JSONArray response) {
                Log.d("something", "eah");
                dimmos = Dimmo.mainDimmos(response);
                loadDimmos();
            }
        });

        // list
        ListView list = (ListView) findViewById(R.id.list);
        this.list = list;
        //DimmosAdapter dimmosAdapter = new DimmosAdapter(this,null);
        //list.setAdapter(publicationsAdapter);


    }
    private ArrayList<Dimmo> ge(){
        ArrayList<Dimmo> ps = new ArrayList<>();
        Dimmo p = new Dimmo();
        p.price = 10000;
        p.description = "You will wake up with either stab wounds\n" +
                "Or another persons blood all over you.\n" +
                "You scum! Kill yourself!!!\n";
        p.name = "The end is near";
        ps.add(p);
        ps.add(p);
        ps.add(p);
        ps.add(p);
        return ps;
    }
    private void loadDimmos(){
        DimmosAdapter dimmosAdapter = new DimmosAdapter(this,dimmos);
        list.setAdapter(dimmosAdapter);

        Log.d("676","loaded into list");
        Log.d("676",String.valueOf(dimmos.size()));
    }
    private class DimmosAdapter extends BaseAdapter {
        private ArrayList<Dimmo> dimmos;
        private Context context;

        public DimmosAdapter(Context context, ArrayList<Dimmo> ps){
            this.context= context;
            dimmos = ps;
        }

        @Override
        public int getCount() {
            return dimmos.size();
        }

        @Override
        public Object getItem(int position) {
            return dimmos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            Dimmo p = dimmos.get(position);
            if (convertView == null) {
                LayoutInflater ly  = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                row = ly.inflate(R.layout.list_item_dimmos,parent,false);
//                Typeface tfmain = Typeface.createFromAsset(getAssets(), "");
                //text.setTypeface(tfmain);
            } else {
                row = convertView;
            }
            ((TextView) row.findViewById(R.id.name)).setText(p.name);
            ((TextView) row.findViewById(R.id.description)).setText(p.description);
            //((TextView) row.findViewById(R.id.paws)).setText(String.valueOf(p.price));

            return row;
        }
    }
}
