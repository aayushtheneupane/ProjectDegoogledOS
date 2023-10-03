package android.support.p002v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

/* renamed from: android.support.v7.app.AlertController$RecycleListView */
/* compiled from: PG */
public class AlertController$RecycleListView extends ListView {

    /* renamed from: a */
    public final int f856a;

    /* renamed from: b */
    public final int f857b;

    public AlertController$RecycleListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15592t);
        this.f857b = obtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.f856a = obtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}
