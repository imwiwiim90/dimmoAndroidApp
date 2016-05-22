package hckthn.dimmo.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import hckthn.dimmo.R;

/**
 * Created by WILSOND on 5/22/16.
 */
public class CustomCalendar extends LinearLayout {
    private ViewGroup vgSelected = null;
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
            ViewGroup vg = (ViewGroup) ly.inflate(R.layout.calendar_item,row,false);
            TextView tv = (TextView) vg.findViewById(R.id.number);
            tv.setText(String.valueOf(i));
            vg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vgSelected != null) vgSelected.findViewById(R.id.image).setVisibility(View.INVISIBLE);
                    vgSelected = (ViewGroup) v;
                    vgSelected.findViewById(R.id.image).setVisibility(View.VISIBLE);
                }
            });
            row.addView(vg);
        }
    }
    public int getSelected(){
        if (vgSelected!=null) return Integer.getInteger(String.valueOf(((TextView) vgSelected.findViewById(R.id.number)).getText()));
        return 0;
    }

}
