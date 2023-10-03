package p000;

import android.view.View;
import java.util.Set;

/* renamed from: fee */
/* compiled from: PG */
public final class fee {

    /* renamed from: a */
    public final Set f9362a;

    /* renamed from: b */
    public final fds f9363b;

    /* renamed from: c */
    public final feb f9364c;

    /* renamed from: d */
    private final fdo f9365d = new fed(this);

    public fee(Set set, fds fds, ffa ffa) {
        ife.m12898e((Object) ffa);
        this.f9362a = set;
        this.f9363b = fds;
        this.f9364c = new feb(this, fds);
    }

    @Deprecated
    /* renamed from: a */
    public final fdi mo5564a(View view, int i) {
        return new fdi(i, new fec(this, view), mo5565a(), ffa.f9433a);
    }

    /* renamed from: a */
    public static final void m8692a(View view) {
        fxk.m9830b();
        fdr a = fdz.m8660a(view, ffa.f9433a);
        ife.m12898e((Object) a);
        a.f9326c.mo5532h();
        ife.m12896d(!a.f9326c.mo5530f());
        a.f9326c = null;
    }

    /* renamed from: a */
    public final fdo mo5565a() {
        if (!this.f9362a.isEmpty()) {
            return this.f9365d;
        }
        return null;
    }
}
