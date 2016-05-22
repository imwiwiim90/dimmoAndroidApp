package hckthn.dimmo.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import hckthn.dimmo.R;

/**
 * Created by WILSOND on 5/22/16.
 */
public class CustomCalendar extends LinearLayout {
    public CustomCalendar(Context context) {
        super(context);
        LayoutInflater ly = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout l = null;
        for (int i = 0; i < 30; i++) {
            if (i%7 == 0) {
                // = (LinearLayout) ly.inflate(R.layout.calendar_layout, this, true);
            }
            TextView tv = (TextView) ly.inflate(R.layout.calendar_item,this,true);
        }
    }


}
