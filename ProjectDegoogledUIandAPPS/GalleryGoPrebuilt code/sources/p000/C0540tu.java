package p000;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* renamed from: tu */
/* compiled from: PG */
final class C0540tu extends C0536tq {

    /* renamed from: b */
    public final SeekBar f15956b;

    /* renamed from: c */
    public Drawable f15957c;

    /* renamed from: d */
    private ColorStateList f15958d = null;

    /* renamed from: e */
    private PorterDuff.Mode f15959e = null;

    /* renamed from: f */
    private boolean f15960f = false;

    /* renamed from: g */
    private boolean f15961g = false;

    public C0540tu(SeekBar seekBar) {
        super(seekBar);
        this.f15956b = seekBar;
    }

    /* renamed from: a */
    private final void m15451a() {
        Drawable drawable = this.f15957c;
        if (drawable == null) {
            return;
        }
        if (this.f15960f || this.f15961g) {
            Drawable mutate = drawable.mutate();
            int i = Build.VERSION.SDK_INT;
            this.f15957c = mutate;
            if (this.f15960f) {
                C0257jh.m14475a(mutate, this.f15958d);
            }
            if (this.f15961g) {
                C0257jh.m14476a(this.f15957c, this.f15959e);
            }
            if (this.f15957c.isStateful()) {
                this.f15957c.setState(this.f15956b.getDrawableState());
            }
        }
    }

    /* renamed from: a */
    public final void mo10168a(AttributeSet attributeSet, int i) {
        super.mo10168a(attributeSet, i);
        C0684zc a = C0684zc.m16192a(this.f15956b.getContext(), attributeSet, C0435px.f15579g, i, 0);
        Drawable b = a.mo10727b(0);
        if (b != null) {
            this.f15956b.setThumb(b);
        }
        Drawable a2 = a.mo10723a(1);
        Drawable drawable = this.f15957c;
        if (drawable != null) {
            drawable.setCallback((Drawable.Callback) null);
        }
        this.f15957c = a2;
        if (a2 != null) {
            a2.setCallback(this.f15956b);
            C0257jh.m14486b(a2, C0340mj.m14714f(this.f15956b));
            if (a2.isStateful()) {
                a2.setState(this.f15956b.getDrawableState());
            }
            m15451a();
        }
        this.f15956b.invalidate();
        if (a.mo10735f(3)) {
            this.f15959e = C0579vf.m15603a(a.mo10722a(3, -1), this.f15959e);
            this.f15961g = true;
        }
        if (a.mo10735f(2)) {
            this.f15958d = a.mo10733e(2);
            this.f15960f = true;
        }
        a.mo10724a();
        m15451a();
    }
}
