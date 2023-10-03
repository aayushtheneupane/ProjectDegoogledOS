package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: nx */
/* compiled from: PG */
public class C0381nx extends ViewGroup.MarginLayoutParams {

    /* renamed from: a */
    public int f15336a = 8388627;

    public C0381nx() {
        super(-2, -2);
    }

    public C0381nx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15574b);
        this.f15336a = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
    }

    public C0381nx(C0381nx nxVar) {
        super(nxVar);
        this.f15336a = nxVar.f15336a;
    }

    public C0381nx(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
