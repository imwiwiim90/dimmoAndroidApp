package hckthn.dimmo.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hckthn.dimmo.R;

/**
 * Created by WILSOND on 5/22/16.
 */
public class CustomDayPicker extends LinearLayout {
    private static ArrayList<String> days;
    private ArrayList<Boolean> booleans;
    static {
        days = new ArrayList<>();
        days.add("D");
        days.add("L");
        days.add("M");
        days.add("I");
        days.add("J");
        days.add("V");
        days.add("S");
    }
    private ViewGroup vgSelected = null;
    public CustomDayPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        booleans = new ArrayList<>();
        for (int y = 0; y < days.size(); y ++) booleans.add(false);
        this.setOrientation(HORIZONTAL);
        LayoutInflater ly = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int h = -1;
        for (String d: days) {
            h ++;
            final ViewGroup vg = (ViewGroup)ly.inflate(R.layout.calendar_item,this,false);
            TextView tv = (TextView) vg.findViewById(R.id.number);
            tv.setText(d);
            vg.setTag(h);
            vg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View img = v.findViewById(R.id.image);
                    int pos =(Integer) vg.getTag();
                    if (booleans.get(pos)) {
                        img.setVisibility(INVISIBLE);
                    } else {
                        img.setVisibility(VISIBLE);
                    }
                    booleans.set(pos,!booleans.get(pos));
                }
            });
            this.addView(vg);
        }
    }
    public int getSelected(){
        if (vgSelected!=null) return Integer.getInteger(String.valueOf(((TextView) vgSelected.findViewById(R.id.number)).getText()));
        return 0;
    }

}
