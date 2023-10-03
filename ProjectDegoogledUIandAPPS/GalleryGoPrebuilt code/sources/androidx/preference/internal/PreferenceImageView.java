package androidx.preference.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* compiled from: PG */
public class PreferenceImageView extends ImageView {

    /* renamed from: a */
    private int f1158a;

    /* renamed from: b */
    private int f1159b;

    public PreferenceImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final int getMaxHeight() {
        return this.f1159b;
    }

    public final int getMaxWidth() {
        return this.f1158a;
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1158a = Integer.MAX_VALUE;
        this.f1159b = Integer.MAX_VALUE;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, adz.f259j, i, 0);
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(3, Integer.MAX_VALUE));
        setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(2, Integer.MAX_VALUE));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int size = View.MeasureSpec.getSize(i);
            int i3 = this.f1158a;
            if (i3 != Integer.MAX_VALUE && (i3 < size || mode == 0)) {
                i = View.MeasureSpec.makeMeasureSpec(i3, RecyclerView.UNDEFINED_DURATION);
            }
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            int size2 = View.MeasureSpec.getSize(i2);
            int i4 = this.f1159b;
            if (i4 != Integer.MAX_VALUE && (i4 < size2 || mode2 == 0)) {
                i2 = View.MeasureSpec.makeMeasureSpec(i4, RecyclerView.UNDEFINED_DURATION);
            }
        }
        super.onMeasure(i, i2);
    }

    public final void setMaxHeight(int i) {
        this.f1159b = i;
        super.setMaxHeight(i);
    }

    public final void setMaxWidth(int i) {
        this.f1158a = i;
        super.setMaxWidth(i);
    }
}
