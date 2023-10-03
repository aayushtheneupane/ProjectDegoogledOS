package p000;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;

/* renamed from: fwc */
/* compiled from: PG */
public class fwc {

    /* renamed from: c */
    private static final Bundle f10638c = new Bundle();

    /* renamed from: a */
    private final List f10639a = new ArrayList();

    /* renamed from: b */
    private final HashSet f10640b = new HashSet();

    /* renamed from: d */
    private fwb f10641d;

    /* renamed from: e */
    public final List f10642e = new ArrayList();

    /* renamed from: f */
    private fwb f10643f;

    /* renamed from: g */
    private fwb f10644g;

    /* renamed from: h */
    private fwb f10645h;

    /* renamed from: i */
    private Long f10646i = Long.valueOf(Thread.currentThread().getId());

    /* renamed from: a */
    public final fwb mo6244a(fwb fwb) {
        fxk.m9830b();
        this.f10646i = null;
        for (int i = 0; i < this.f10642e.size(); i++) {
            fwb.mo6211a((fwt) this.f10642e.get(i));
        }
        this.f10639a.add(fwb);
        return fwb;
    }

    /* renamed from: a */
    public final void mo6246a(fwt fwt) {
        String b = m9705b(fwt);
        if (b != null) {
            if (!this.f10640b.contains(b)) {
                this.f10640b.add(b);
            } else {
                throw new IllegalStateException(String.format("Duplicate observer tag: '%s'. Implement LifecycleObserverTag to provide unique tags.", new Object[]{b}));
            }
        }
        if (fxk.m9826a()) {
            this.f10646i = null;
        }
        Long l = this.f10646i;
        if (l == null) {
            fxk.m9830b();
        } else if (l.longValue() != Thread.currentThread().getId()) {
            String valueOf = String.valueOf(l);
            long id = Thread.currentThread().getId();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 70);
            sb.append("Lifecycle invoked from two different threads ");
            sb.append(valueOf);
            sb.append(" and ");
            sb.append(id);
            throw new ConcurrentModificationException(sb.toString());
        }
        fxk.m9821a((Object) fwt);
        this.f10642e.add(fwt);
        if (!this.f10639a.isEmpty()) {
            this.f10646i = null;
            fxk.m9830b();
        }
        for (int i = 0; i < this.f10639a.size(); i++) {
            ((fwb) this.f10639a.get(i)).mo6211a(fwt);
        }
    }

    /* renamed from: a */
    public final Bundle mo6243a(fwt fwt, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String b = m9705b(fwt);
        if (b == null) {
            return f10638c;
        }
        return bundle.getBundle(b);
    }

    /* renamed from: b */
    public static final String m9705b(fwt fwt) {
        if (!(fwt instanceof fwq)) {
            return null;
        }
        if (!(fwt instanceof fwu)) {
            return fwt.getClass().getName();
        }
        return ((fwu) fwt).mo6272a();
    }

    /* renamed from: a */
    public final void mo6245a(int i, int i2, Intent intent) {
        int i3 = fwv.f10647a;
        for (int i4 = 0; i4 < this.f10642e.size(); i4++) {
            fwt fwt = (fwt) this.f10642e.get(i4);
            if (fwt instanceof fwd) {
                ((fwd) fwt).mo6071a(i, i2, intent);
            }
        }
    }

    /* renamed from: k */
    public final void mo6254k() {
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwe) {
                ((fwe) fwt).mo6261a();
            }
        }
    }

    /* renamed from: l */
    public final boolean mo6255l() {
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwf) {
                if (((fwf) fwt).mo6262a()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: d */
    public final void mo6248d(Bundle bundle) {
        int i = fwv.f10647a;
        this.f10641d = mo6244a((fwb) new fvx(this, bundle));
    }

    /* renamed from: m */
    public final void mo6256m() {
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwh) {
                ((fwh) fwt).mo6263a();
            }
        }
    }

    /* renamed from: n */
    public final boolean mo6257n() {
        int i = fwv.f10647a;
        boolean z = false;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwi) {
                z |= ((fwi) fwt).mo6264a();
            }
        }
        return z;
    }

    /* renamed from: b */
    public void mo6213b() {
        int i = fwv.f10647a;
        fwb fwb = this.f10645h;
        if (fwb != null) {
            mo6247b(fwb);
            this.f10645h = null;
        }
        fwb fwb2 = this.f10641d;
        if (fwb2 != null) {
            mo6247b(fwb2);
            this.f10641d = null;
        }
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            fxk.m9821a((Object) fwt);
            if (fwt instanceof fwj) {
                ((fwj) fwt).mo6265a();
            }
        }
    }

    /* renamed from: j */
    public final void mo6253j() {
        int i = fwv.f10647a;
        for (fwt fwt : this.f10642e) {
            if (fwt instanceof fwk) {
                ((fwk) fwt).mo6266a();
            }
        }
    }

    /* renamed from: o */
    public final boolean mo6258o() {
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwl) {
                if (((fwl) fwt).mo6267a()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo6212a() {
        int i = fwv.f10647a;
        fwb fwb = this.f10644g;
        if (fwb != null) {
            mo6247b(fwb);
            this.f10644g = null;
        }
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            fxk.m9821a((Object) fwt);
            if (fwt instanceof fwm) {
                ((fwm) fwt).mo6070b();
            }
        }
    }

    /* renamed from: p */
    public final boolean mo6259p() {
        int i = fwv.f10647a;
        boolean z = false;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwn) {
                z |= ((fwn) fwt).mo6268a();
            }
        }
        return z;
    }

    /* renamed from: q */
    public final void mo6260q() {
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            if (fwt instanceof fwo) {
                ((fwo) fwt).mo6269a();
            }
        }
    }

    /* renamed from: h */
    public final void mo6251h() {
        int i = fwv.f10647a;
        this.f10644g = mo6244a((fwb) new fvz());
    }

    /* renamed from: e */
    public final void mo6249e(Bundle bundle) {
        int i = fwv.f10647a;
        this.f10645h = mo6244a((fwb) new fwa(bundle));
    }

    /* renamed from: g */
    public final void mo6250g() {
        int i = fwv.f10647a;
        this.f10643f = mo6244a((fwb) new fvy());
    }

    /* renamed from: i */
    public final void mo6252i() {
        int i = fwv.f10647a;
        fwb fwb = this.f10643f;
        if (fwb != null) {
            mo6247b(fwb);
            this.f10643f = null;
        }
        for (int i2 = 0; i2 < this.f10642e.size(); i2++) {
            fwt fwt = (fwt) this.f10642e.get(i2);
            fxk.m9821a((Object) fwt);
            if (fwt instanceof fws) {
                ((fws) fwt).mo6271d();
            }
        }
    }

    /* renamed from: b */
    public final void mo6247b(fwb fwb) {
        this.f10639a.remove(fwb);
    }
}
