package p000;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.google.android.apps.photosgo.R;

/* renamed from: tt */
/* compiled from: PG */
public final class C0539tt extends SeekBar {

    /* renamed from: a */
    private final C0540tu f15955a;

    public C0539tt(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        C0540tu tuVar = new C0540tu(this);
        this.f15955a = tuVar;
        tuVar.mo10168a(attributeSet, (int) R.attr.seekBarStyle);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0540tu tuVar = this.f15955a;
        Drawable drawable = tuVar.f15957c;
        if (drawable != null && drawable.isStateful() && drawable.setState(tuVar.f15956b.getDrawableState())) {
            tuVar.f15956b.invalidateDrawable(drawable);
        }
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f15955a.f15957c;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        C0540tu tuVar = this.f15955a;
        if (tuVar.f15957c != null) {
            int max = tuVar.f15956b.getMax();
            int i = 1;
            if (max > 1) {
                int intrinsicWidth = tuVar.f15957c.getIntrinsicWidth();
                int intrinsicHeight = tuVar.f15957c.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth >> 1 : 1;
                if (intrinsicHeight >= 0) {
                    i = intrinsicHeight >> 1;
                }
                tuVar.f15957c.setBounds(-i2, -i, i2, i);
                float width = ((float) ((tuVar.f15956b.getWidth() - tuVar.f15956b.getPaddingLeft()) - tuVar.f15956b.getPaddingRight())) / ((float) max);
                int save = canvas.save();
                canvas.translate((float) tuVar.f15956b.getPaddingLeft(), (float) (tuVar.f15956b.getHeight() / 2));
                for (int i3 = 0; i3 <= max; i3++) {
                    tuVar.f15957c.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
