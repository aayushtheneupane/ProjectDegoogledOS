package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class SnackbarContentLayout extends LinearLayout implements gil {

    /* renamed from: a */
    public TextView f5227a;

    /* renamed from: b */
    public Button f5228b;

    /* renamed from: c */
    private int f5229c;

    /* renamed from: d */
    private int f5230d;

    public SnackbarContentLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gim.f11431a);
        this.f5229c = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        this.f5230d = obtainStyledAttributes.getDimensionPixelSize(7, -1);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void mo3673a() {
        this.f5227a.setAlpha(0.0f);
        this.f5227a.animate().alpha(1.0f).setDuration(180).setStartDelay(70).start();
        if (this.f5228b.getVisibility() == 0) {
            this.f5228b.setAlpha(0.0f);
            this.f5228b.animate().alpha(1.0f).setDuration(180).setStartDelay(70).start();
        }
    }

    /* renamed from: b */
    public final void mo3674b() {
        this.f5227a.setAlpha(1.0f);
        this.f5227a.animate().alpha(0.0f).setDuration(180).setStartDelay(0).start();
        if (this.f5228b.getVisibility() == 0) {
            this.f5228b.setAlpha(1.0f);
            this.f5228b.animate().alpha(0.0f).setDuration(180).setStartDelay(0).start();
        }
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f5227a = (TextView) findViewById(R.id.snackbar_text);
        this.f5228b = (Button) findViewById(R.id.snackbar_action);
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        if (this.f5229c > 0 && getMeasuredWidth() > (i3 = this.f5229c)) {
            i = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
            super.onMeasure(i, i2);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        int lineCount = this.f5227a.getLayout().getLineCount();
        if (lineCount <= 1 || this.f5230d <= 0 || this.f5228b.getMeasuredWidth() <= this.f5230d) {
            if (lineCount <= 1) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!m5109a(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!m5109a(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i, i2);
    }

    /* renamed from: a */
    private final boolean m5109a(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.f5227a.getPaddingTop() == i2 && this.f5227a.getPaddingBottom() == i3) {
            return z;
        }
        TextView textView = this.f5227a;
        if (C0340mj.m14727r(textView)) {
            C0340mj.m14690a(textView, C0340mj.m14716g(textView), i2, C0340mj.m14717h(textView), i3);
        } else {
            textView.setPadding(textView.getPaddingLeft(), i2, textView.getPaddingRight(), i3);
        }
        return true;
    }
}
