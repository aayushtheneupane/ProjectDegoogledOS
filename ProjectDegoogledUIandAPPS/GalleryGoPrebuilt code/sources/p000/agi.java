package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: agi */
/* compiled from: PG */
final class agi extends AnimatorListenerAdapter implements afk {

    /* renamed from: a */
    private final View f377a;

    /* renamed from: b */
    private final int f378b;

    /* renamed from: c */
    private final ViewGroup f379c;

    /* renamed from: d */
    private final boolean f380d;

    /* renamed from: e */
    private boolean f381e;

    /* renamed from: f */
    private boolean f382f = false;

    public agi(View view, int i) {
        this.f377a = view;
        this.f378b = i;
        this.f379c = (ViewGroup) view.getParent();
        this.f380d = true;
        m440a(true);
    }

    /* renamed from: a */
    public final void mo264a() {
    }

    /* renamed from: b */
    public final void mo278b(afl afl) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationStart(Animator animator) {
    }

    /* renamed from: d */
    private final void m441d() {
        if (!this.f382f) {
            agb.m422a(this.f377a, this.f378b);
            ViewGroup viewGroup = this.f379c;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        m440a(false);
    }

    public final void onAnimationCancel(Animator animator) {
        this.f382f = true;
    }

    public final void onAnimationEnd(Animator animator) {
        m441d();
    }

    public final void onAnimationPause(Animator animator) {
        if (!this.f382f) {
            agb.m422a(this.f377a, this.f378b);
        }
    }

    public final void onAnimationResume(Animator animator) {
        if (!this.f382f) {
            agb.m422a(this.f377a, 0);
        }
    }

    /* renamed from: a */
    public final void mo265a(afl afl) {
        m441d();
        afl.mo312b((afk) this);
    }

    /* renamed from: b */
    public final void mo266b() {
        m440a(false);
    }

    /* renamed from: c */
    public final void mo267c() {
        m440a(true);
    }

    /* renamed from: a */
    private final void m440a(boolean z) {
        ViewGroup viewGroup;
        if (this.f380d && this.f381e != z && (viewGroup = this.f379c) != null) {
            this.f381e = z;
            afy.m414a(viewGroup, z);
        }
    }
}
