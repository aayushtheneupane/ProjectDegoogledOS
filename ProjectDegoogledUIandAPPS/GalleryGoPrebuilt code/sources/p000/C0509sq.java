package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: sq */
/* compiled from: PG */
final class C0509sq extends C0533tn implements C0513su {

    /* renamed from: a */
    public final /* synthetic */ C0512st f15876a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0509sq(C0512st stVar, Context context) {
        super(context, (AttributeSet) null, R.attr.actionOverflowButtonStyle);
        this.f15876a = stVar;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        C0637xj.m15898a((View) this, getContentDescription());
        setOnTouchListener(new C0508sp(this, this));
    }

    /* renamed from: d */
    public final boolean mo766d() {
        return false;
    }

    /* renamed from: e */
    public final boolean mo767e() {
        return false;
    }

    public final boolean performClick() {
        if (!super.performClick()) {
            playSoundEffect(0);
            this.f15876a.mo10070c();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (!(drawable == null || background == null)) {
            int width = getWidth();
            int height = getHeight();
            int max = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            C0257jh.m14474a(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
        }
        return frame;
    }
}
