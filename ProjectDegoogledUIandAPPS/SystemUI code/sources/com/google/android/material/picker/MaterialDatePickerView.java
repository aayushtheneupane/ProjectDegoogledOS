package com.google.android.material.picker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$attr;
import java.util.Calendar;

public class MaterialDatePickerView extends MaterialCalendarView<Calendar> {
    private static final int DEF_STYLE_ATTR = R$attr.materialDatePickerStyle;
    private static final ColorDrawable emptyColor = new ColorDrawable(0);
    private static final ColorDrawable selectedColor = new ColorDrawable(-65536);
    private final AdapterView.OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public int selectedPosition;

    public MaterialDatePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, DEF_STYLE_ATTR);
    }

    public MaterialDatePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectedPosition = -1;
        this.onItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (MaterialDatePickerView.this.getMonthInYearAdapter().withinMonth(i)) {
                    int unused = MaterialDatePickerView.this.selectedPosition = i;
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
        int i = 0;
        while (i < adapterView.getCount()) {
            ViewCompat.setBackground(adapterView.getChildAt(i), i == this.selectedPosition ? selectedColor : emptyColor);
            i++;
        }
    }
}
