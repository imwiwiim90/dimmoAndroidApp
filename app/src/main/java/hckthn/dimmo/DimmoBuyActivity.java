package hckthn.dimmo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

import hckthn.dimmo.model.Dimmo;
import hckthn.dimmo.model.PaymentHelper;
import hckthn.dimmo.model.Product;

/**
 * Created by WILSOND on 5/22/16.
 */
public class DimmoBuyActivity extends AppCompatActivity {
    private ListView productsList;
    private ArrayList<Product> products;
    private ListView cookiesList;
    private ArrayList<Product> cookies;
    private ListView walkList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimmo_buy_activity);

        TextView mFoodName = (TextView) findViewById(R.id.mainFood).findViewById(R.id.name);
        TextView mCookieName = (TextView) findViewById(R.id.mainCookie).findViewById(R.id.name);
        TextView mWalkName = (TextView) findViewById(R.id.mainWalk).findViewById(R.id.name);
        try {
            mFoodName.setText(PaymentHelper.dimmoProducts.getJSONObject(0).getString("name"));
            mCookieName.setText(PaymentHelper.dimmoProducts.getJSONObject(2).getString("name"));
            mWalkName.setText(PaymentHelper.dimmoProducts.getJSONObject(1).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        findViewById(R.id.mainFood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsList.setVisibility(View.VISIBLE);
            }
        });
        this.productsList = (ListView) findViewById(R.id.listProducts);
        ApiManager.getProducts(this, new ApiManager.Load() {
            @Override
            public void onLoaded(JSONArray response) {
                products = Product.getFoods(response);
                Log.d("66response", response.toString());
                loadProducts();
            }
        });
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup v = (ViewGroup) view;
                ViewGroup tv = (ViewGroup) findViewById(R.id.mainFood);
                productsList.setVisibility(View.GONE);
                ((TextView) tv.findViewById(R.id.name)).setText(((TextView) v.findViewById(R.id.name)).getText());
                PaymentHelper.food = (Product) productsList.getAdapter().getItem(position);
            }

        });

        findViewById(R.id.mainCookie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookiesList.setVisibility(View.VISIBLE);
            }
        });
        this.cookiesList = (ListView) findViewById(R.id.listCookies);
        ApiManager.getCookies(this, new ApiManager.Load() {
            @Override
            public void onLoaded(JSONArray response) {
                cookies = Product.getFoods(response);
                loadCookies();
            }
        });
        cookiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("itemClick", "clicked");
                ViewGroup v = (ViewGroup) view;
                ViewGroup tv = (ViewGroup) findViewById(R.id.mainCookie);
                cookiesList.setVisibility(View.GONE);
                ((TextView) tv.findViewById(R.id.name)).setText(((TextView) v.findViewById(R.id.name)).getText());
                PaymentHelper.cookie = (Product) cookiesList.getAdapter().getItem(position);

            }
        });
        findViewById(R.id.mainWalk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                walkList.setVisibility(View.VISIBLE);
            }
        });
        walkList = (ListView) findViewById(R.id.listWalks);
        ArrayList<Product> walks = new ArrayList<>();
        ApiManager.getWalks(this, new ApiManager.Load() {
            @Override
            public void onLoaded(JSONArray response) {
                walkList.setAdapter(new ProductAdapter(DimmoBuyActivity.this, Product.getFoods(response)));
            }
        });
        //walkList.setAdapter(adapter);
        walkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup v = (ViewGroup) view;
                ViewGroup tv = (ViewGroup) findViewById(R.id.mainWalk);
                walkList.setVisibility(View.GONE);
                ((TextView) tv.findViewById(R.id.name)).setText(((TextView) v.findViewById(R.id.name)).getText());
                PaymentHelper.walk = (Product) walkList.getAdapter().getItem(position);
            }
        });

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAct();
            }
        });
    }
    public void nextAct(){
        Intent intent = new Intent(this, CompletePaymentActivity.class);
        startActivity(intent);
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
