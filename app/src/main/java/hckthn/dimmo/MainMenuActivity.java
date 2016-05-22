package hckthn.dimmo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hckthn.dimmo.model.Dimmo;
import hckthn.dimmo.model.PaymentHelper;

/**
 * Created by WILSOND on 5/21/16.
 */
public class MainMenuActivity extends AppCompatActivity {
    private ListView list;
    private ArrayList<Dimmo> dimmos;
    private DimmosAdapter dimmosAdapter;
    private ImageLoader imgLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainMenuActivity.this));
        setContentView(R.layout.main_menu_activity);

        imgLoader = ImageLoader.getInstance();

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

        return ps;
    }
    private void loadDimmos(){
        DimmosAdapter dimmosAdapter = new DimmosAdapter(this,dimmos);
        list.setAdapter(dimmosAdapter);
        dimmosAdapter.notifyDataSetChanged();
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
            ImageView imgView = (ImageView) row.findViewById(R.id.image);
            imgLoader.displayImage(p.image, imgView);
            //((TextView) row.findViewById(R.id.paws)).setText(String.valueOf(p.price));
            if (position % 3 == 0) {
                row.setBackgroundResource(R.color.colorMain1);
            }
            else if (position % 3 == 1) {
                row.setBackgroundResource(R.color.colorMain2);
            }
            else if (position % 3 == 2) {
                row.setBackgroundResource(R.color.colorMain3);
                ((TextView) row.findViewById(R.id.name)).setTextColor(getResources().getColor(R.color.white));
                ((TextView) row.findViewById(R.id.description)).setTextColor(getResources().getColor(R.color.white));
            }
            View btn = row.findViewById(R.id.buyBtn);
            btn.setTag(position);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = (int) v.getTag();
                    Dimmo dr = dimmos.get(p);
                    try {
                        PaymentHelper.dimmoProducts = dr.json.getJSONArray("products");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(MainMenuActivity.this, DimmoBuyActivity.class);
                    startActivity(intent);
                }
            });
            return row;
        }
    }
}
