package com.google.android.material.picker;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import java.util.Calendar;

public abstract class MaterialCalendarView<S> extends LinearLayoutCompat {
    private final MonthInYearAdapter monthInYearAdapter;

    /* access modifiers changed from: protected */
    public abstract void drawSelection(AdapterView<?> adapterView);

    /* access modifiers changed from: protected */
    public abstract AdapterView.OnItemClickListener getOnItemClickListener();

    public MaterialCalendarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R$layout.mtrl_date_picker_calendar_days_header, this);
        from.inflate(R$layout.mtrl_date_picker_calendar_days, this);
        GridView gridView = (GridView) findViewById(R$id.date_picker_calendar_days_header);
        GridView gridView2 = (GridView) findViewById(R$id.date_picker_calendar_days);
        Calendar instance = Calendar.getInstance();
        MonthInYear create = MonthInYear.create(instance.get(1), instance.get(2));
        this.monthInYearAdapter = new MonthInYearAdapter(context, create);
        gridView.setAdapter(new DaysHeaderAdapter());
        gridView.setNumColumns(create.daysInWeek);
        gridView2.setAdapter(this.monthInYearAdapter);
        gridView2.setNumColumns(create.daysInWeek);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MaterialCalendarView.this.getOnItemClickListener().onItemClick(adapterView, view, i, j);
                MaterialCalendarView.this.drawSelection(adapterView);
                if (Build.VERSION.SDK_INT >= 16) {
                    MaterialCalendarView.this.callOnClick();
                } else {
                    MaterialCalendarView.this.performClick();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public final MonthInYearAdapter getMonthInYearAdapter() {
        return this.monthInYearAdapter;
    }
}
