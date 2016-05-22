package hckthn.dimmo.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import hckthn.dimmo.R;

/**
 * Created by WILSOND on 5/22/16.
 */
public class CustomCalendar extends LinearLayout {
    public CustomCalendar(Context context,AttributeSet attrs) {
        super(context,attrs);
        this.setOrientation(VERTICAL);
        LayoutInflater ly = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout row = null;
        for (int i = 1; i < 30; i++) {
            if ((i-1)%7 == 0) {
                row = (LinearLayout) ly.inflate(R.layout.calendar_layout, this, false);
                this.addView(row);
            }
            TextView tv = (TextView) ly.inflate(R.layout.calendar_item,row,false);
            tv.setText(String.valueOf(i));
            row.addView(tv);
        }
    }


}
