package hckthn.dimmo.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import hckthn.dimmo.R;

/**
 * Created by WILSOND on 5/21/16.
 */
public class PrettyTextView extends TextView {

    public PrettyTextView(Context context, AttributeSet attrs) {
        super(context,attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PrettyTextView, 0, 0);
        try {
            String fontDir = a.getString(R.styleable.PrettyTextView_font);
            Typeface tfmain = Typeface.createFromAsset(context.getAssets(), fontDir);
            this.setTypeface(tfmain);
        } finally {
            a.recycle();
        }
    }
}
