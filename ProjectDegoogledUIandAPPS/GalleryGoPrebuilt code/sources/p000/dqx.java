package p000;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.android.apps.photosgo.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p003j$.util.Optional;

/* renamed from: dqx */
/* compiled from: PG */
public final class dqx implements C0438q {

    /* renamed from: a */
    public final Map f7132a = new HashMap();

    /* renamed from: b */
    private final dql f7133b;

    /* renamed from: c */
    private final Animation f7134c;

    /* renamed from: d */
    private final Animation f7135d;

    /* renamed from: e */
    private final C0147fh f7136e;

    /* renamed from: f */
    private Optional f7137f = Optional.empty();

    public dqx(Context context, dql dql, C0147fh fhVar) {
        this.f7133b = dql;
        this.f7136e = fhVar;
        this.f7134c = AnimationUtils.loadAnimation(context, R.anim.animation_fade_in);
        this.f7135d = AnimationUtils.loadAnimation(context, R.anim.animation_fade_out);
        this.f7134c.setAnimationListener(new dqu(this));
        this.f7135d.setAnimationListener(new dqv(this));
        fhVar.mo5ad().mo64a(this);
    }

    /* renamed from: a */
    public final void mo2556a() {
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
    }

    /* renamed from: a */
    public final void mo4354a(dqw dqw) {
        Map map = this.f7132a;
        dqw.getClass();
        map.put(dqw, new dqm(dqw));
    }

    /* renamed from: d */
    public final Optional mo4357d() {
        Window window;
        C0149fj m = this.f7136e.mo5653m();
        if (m == null || (window = m.getWindow()) == null) {
            return Optional.empty();
        }
        return Optional.m16285of(window.getDecorView());
    }

    /* renamed from: b */
    public final void mo4356b(dqw dqw) {
        if (this.f7132a.containsKey(dqw)) {
            Map map = this.f7132a;
            dqw.getClass();
            map.put(dqw, new dqn(dqw));
            m6488a(this.f7133b.f7121b, true, dqw.mo4262k());
        }
    }

    /* renamed from: a */
    public final void mo4355a(boolean z) {
        m6487a(z, false);
    }

    /* renamed from: b */
    public final void mo2558b() {
        dql dql = this.f7133b;
        dql.f7120a.remove(new dqq(this));
        for (dqw put : this.f7132a.keySet()) {
            this.f7132a.put(put, dqr.f7127a);
        }
    }

    /* renamed from: c */
    public final void mo2560c() {
        for (dqw a : this.f7132a.keySet()) {
            mo4354a(a);
        }
        dql dql = this.f7133b;
        dql.f7120a.add(new dqp(this));
        m6487a(this.f7133b.f7121b, true);
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
        this.f7137f = mo4357d().map(dqo.f7124a);
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
        this.f7137f.ifPresent(new dqs(this));
    }

    /* renamed from: a */
    private final void m6487a(boolean z, boolean z2) {
        Window window;
        boolean z3 = !z;
        C0149fj m = this.f7136e.mo5653m();
        if (!(m == null || (window = m.getWindow()) == null)) {
            View decorView = window.getDecorView();
            if (!z3) {
                if (Build.VERSION.SDK_INT >= 28) {
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.layoutInDisplayCutoutMode = 1;
                    window.setAttributes(attributes);
                }
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 3846);
            } else {
                decorView.setSystemUiVisibility((decorView.getSystemUiVisibility() & -2055) | 1792);
            }
        }
        for (iqk a : this.f7132a.values()) {
            m6488a(z, z2, (List) a.mo2097a());
        }
    }

    /* renamed from: a */
    private final void m6488a(boolean z, boolean z2, List list) {
        int i;
        Animation animation;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            if (!z2) {
                view.requestLayout();
                if (!z) {
                    animation = this.f7134c;
                } else {
                    animation = this.f7135d;
                }
                view.startAnimation(animation);
            } else {
                if (!z) {
                    i = 0;
                } else {
                    i = 8;
                }
                view.setVisibility(i);
            }
        }
    }
}
