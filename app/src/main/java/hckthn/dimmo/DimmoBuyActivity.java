package hckthn.dimmo;

import android.content.Context;
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

import java.util.ArrayList;

import hckthn.dimmo.model.Dimmo;
import hckthn.dimmo.model.Product;

/**
 * Created by WILSOND on 5/22/16.
 */
public class DimmoBuyActivity extends AppCompatActivity {
    private ListView productsList;
    private ArrayList<Product> products;
    private ListView cookiesList;
    private ArrayList<Product> cookies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimmo_buy_activity);

        this.productsList = (ListView) findViewById(R.id.listProducts);
        ApiManager.getProducts(this, new ApiManager.Load() {
            @Override
            public void onLoaded(JSONArray response) {
                products = Product.getFoods(response);
                loadProducts();
            }
        });
        this.cookiesList = (ListView) findViewById(R.id.listCookies);
        ApiManager.getProducts(this, new ApiManager.Load() {
            @Override
            public void onLoaded(JSONArray response) {
                cookies = Product.getFoods(response);
                loadCookies();
            }
        });


    }
    public void loadCookies(){
        ProductAdapter adapter = new ProductAdapter(this,cookies);
        cookiesList.setAdapter(adapter);
    }
    public void loadProducts(){
        Log.d("loaded yeji", String.valueOf(products.size()));
            ProductAdapter adapter = new ProductAdapter(this,products);
        productsList.setAdapter(adapter);
    }


    private class ProductAdapter extends BaseAdapter {
        private ArrayList<Product> products;
        private Context context;

        public ProductAdapter(Context context, ArrayList<Product> products) {
            this.products = products;
            this.context = context;

        }
        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            Product p = products.get(position);
            if (convertView == null) {
                LayoutInflater ly  = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                row = ly.inflate(R.layout.dropdown_item,parent,false);
//                Typeface tfmain = Typeface.createFromAsset(getAssets(), "");
                //text.setTypeface(tfmain);
            } else {
                row = convertView;
            }
            ((TextView) row.findViewById(R.id.name)).setText(p.name);
           // ((TextView) row.findViewById(R.id.description)).setText(p.description);
            //((TextView) row.findViewById(R.id.paws)).setText(String.valueOf(p.price));

            return row;
        }
    }
}
