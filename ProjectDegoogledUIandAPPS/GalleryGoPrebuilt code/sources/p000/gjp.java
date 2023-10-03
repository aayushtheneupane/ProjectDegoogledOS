package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

/* renamed from: gjp */
/* compiled from: PG */
final class gjp extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ int f11498a;

    /* renamed from: b */
    private final /* synthetic */ TextView f11499b;

    /* renamed from: c */
    private final /* synthetic */ int f11500c;

    /* renamed from: d */
    private final /* synthetic */ TextView f11501d;

    /* renamed from: e */
    private final /* synthetic */ gjq f11502e;

    public gjp(gjq gjq, int i, TextView textView, int i2, TextView textView2) {
        this.f11502e = gjq;
        this.f11498a = i;
        this.f11499b = textView;
        this.f11500c = i2;
        this.f11501d = textView2;
    }

    public final void onAnimationEnd(Animator animator) {
        TextView textView;
        gjq gjq = this.f11502e;
        gjq.f11506d = this.f11498a;
        gjq.f11505c = null;
        TextView textView2 = this.f11499b;
        if (textView2 != null) {
            textView2.setVisibility(4);
            if (this.f11500c == 1 && (textView = this.f11502e.f11510h) != null) {
                textView.setText((CharSequence) null);
            }
        }
        TextView textView3 = this.f11501d;
        if (textView3 != null) {
            textView3.setTranslationY(0.0f);
            this.f11501d.setAlpha(1.0f);
        }
    }

    public final void onAnimationStart(Animator animator) {
        TextView textView = this.f11501d;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }
}
