package hckthn.dimmo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import hckthn.dimmo.model.PaymentHelper;

/**
 * Created by WILSOND on 5/22/16.
 */
public class CompletePaymentActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimmo_complete_payment_activity);
        /*
        ViewGroup food = (ViewGroup) findViewById(R.id.food);
        TextView nameFood = (TextView) food.findViewById(R.id.name);
        nameFood.setText(PaymentHelper.food.name);
        TextView priceFood = (TextView) food.findViewById(R.id.price);
        priceFood.setText(String.valueOf(PaymentHelper.food.price));
        */
        ViewGroup cookie = (ViewGroup) findViewById(R.id.cookie);
        TextView nameCookie = (TextView) cookie.findViewById(R.id.name);
        nameCookie.setText(PaymentHelper.cookie.name);
        TextView priceCookie = (TextView) cookie.findViewById(R.id.price);
        priceCookie.setText(String.valueOf(PaymentHelper.cookie.price));

        TextView nameWalk = (TextView) ((ViewGroup) findViewById(R.id.walk)).findViewById(R.id.name);
        nameWalk.setText(PaymentHelper.walk.name);
        TextView priceWalk = (TextView) ((ViewGroup) findViewById(R.id.walk)).findViewById(R.id.price);
        priceWalk.setText(String.valueOf(PaymentHelper.walk.price));

        TextView tTotal = (TextView) findViewById(R.id.total);
        tTotal.setText(String.valueOf(PaymentHelper.cookie.price + PaymentHelper.food.price + PaymentHelper.walk.price));

    }
}
