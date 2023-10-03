package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import com.google.android.apps.photosgo.R;

/* renamed from: ts */
/* compiled from: PG */
public final class C0538ts extends RatingBar {

    /* renamed from: a */
    private final C0536tq f15954a;

    public C0538ts(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ratingBarStyle);
        C0536tq tqVar = new C0536tq(this);
        this.f15954a = tqVar;
        tqVar.mo10168a(attributeSet, (int) R.attr.ratingBarStyle);
    }

    /* access modifiers changed from: protected */
    public final synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap bitmap = this.f15954a.f15949a;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }
}
