package com.google.android.material.picker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$attr;
import java.util.Calendar;

public class MaterialDateRangePickerView extends MaterialCalendarView<Pair<Calendar, Calendar>> {
    private static final int DEF_STYLE_ATTR = R$attr.materialDateRangePickerStyle;
    private static final ColorDrawable emptyColor = new ColorDrawable(0);
    private static final ColorDrawable endColor = new ColorDrawable(-16711936);
    private static final ColorDrawable rangeColor = new ColorDrawable(-256);
    private static final ColorDrawable startColor = new ColorDrawable(-65536);
    private final AdapterView.OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public int selectedEndPosition;
    /* access modifiers changed from: private */
    public int selectedStartPosition;

    public MaterialDateRangePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, DEF_STYLE_ATTR);
    }

    public MaterialDateRangePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectedStartPosition = -1;
        this.selectedEndPosition = -1;
        this.onItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (MaterialDateRangePickerView.this.getMonthInYearAdapter().withinMonth(i)) {
                    if (MaterialDateRangePickerView.this.selectedStartPosition < 0) {
                        int unused = MaterialDateRangePickerView.this.selectedStartPosition = i;
                    } else if (MaterialDateRangePickerView.this.selectedEndPosition >= 0 || i <= MaterialDateRangePickerView.this.selectedStartPosition) {
                        int unused2 = MaterialDateRangePickerView.this.selectedEndPosition = -1;
                        int unused3 = MaterialDateRangePickerView.this.selectedStartPosition = i;
                    } else {
                        int unused4 = MaterialDateRangePickerView.this.selectedEndPosition = i;
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    /* access modifiers changed from: protected */
    public void drawSelection(AdapterView<?> adapterView) {
        for (int i = 0; i < adapterView.getCount(); i++) {
            ColorDrawable colorDrawable = emptyColor;
            int i2 = this.selectedStartPosition;
            if (i == i2) {
                colorDrawable = startColor;
            } else {
                int i3 = this.selectedEndPosition;
                if (i == i3) {
                    colorDrawable = endColor;
                } else if (i > i2 && i < i3) {
                    colorDrawable = rangeColor;
                }
            }
            ViewCompat.setBackground(adapterView.getChildAt(i), colorDrawable);
        }
    }
}
