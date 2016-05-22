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

        TextView nameFood = (TextView) ((ViewGroup)findViewById(R.id.food)).findViewById(R.id.name);
        nameFood.setText(PaymentHelper.food.name);

    }
}
