package p000;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* renamed from: emv */
/* compiled from: PG */
public final class emv implements enb {

    /* renamed from: a */
    public final ene f8574a;

    /* renamed from: b */
    public final Lock f8575b;

    /* renamed from: c */
    public final Context f8576c;

    /* renamed from: d */
    public final ejx f8577d;

    /* renamed from: e */
    public ewc f8578e;

    /* renamed from: f */
    public boolean f8579f;

    /* renamed from: g */
    public boolean f8580g;

    /* renamed from: h */
    public eqg f8581h;

    /* renamed from: i */
    public boolean f8582i;

    /* renamed from: j */
    public boolean f8583j;

    /* renamed from: k */
    public final epk f8584k;

    /* renamed from: l */
    private ejq f8585l;

    /* renamed from: m */
    private int f8586m;

    /* renamed from: n */
    private int f8587n = 0;

    /* renamed from: o */
    private int f8588o;

    /* renamed from: p */
    private final Bundle f8589p = new Bundle();

    /* renamed from: q */
    private final Set f8590q = new HashSet();

    /* renamed from: r */
    private boolean f8591r;

    /* renamed from: s */
    private final Map f8592s;

    /* renamed from: t */
    private final ArrayList f8593t = new ArrayList();

    /* renamed from: u */
    private final eov f8594u;

    public emv(ene ene, epk epk, Map map, ejx ejx, eov eov, Lock lock, Context context, byte[] bArr, byte[] bArr2) {
        this.f8574a = ene;
        this.f8584k = epk;
        this.f8592s = map;
        this.f8577d = ejx;
        this.f8594u = eov;
        this.f8575b = lock;
        this.f8576c = context;
    }

    /* renamed from: c */
    private static final String m7808c(int i) {
        return i != 0 ? "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    /* renamed from: b */
    public final void mo5015b() {
    }

    /* renamed from: a */
    public final void mo5010a() {
        this.f8574a.f8634g.clear();
        this.f8579f = false;
        this.f8585l = null;
        this.f8587n = 0;
        this.f8591r = true;
        this.f8580g = false;
        this.f8582i = false;
        HashMap hashMap = new HashMap();
        for (ekn ekn : this.f8592s.keySet()) {
            ekm ekm = (ekm) this.f8574a.f8633f.get(ekn.mo4940a());
            boolean booleanValue = ((Boolean) this.f8592s.get(ekn)).booleanValue();
            if (ekm.mo4934g()) {
                this.f8579f = true;
                if (booleanValue) {
                    this.f8590q.add(ekn.mo4940a());
                } else {
                    this.f8591r = false;
                }
            }
            hashMap.put(ekm, new emm(this, ekn, booleanValue));
        }
        if (this.f8579f) {
            this.f8584k.f8782g = Integer.valueOf(System.identityHashCode(this.f8574a.f8639l));
            emt emt = new emt(this);
            eov eov = this.f8594u;
            Context context = this.f8576c;
            Looper looper = this.f8574a.f8639l.f8607g;
            epk epk = this.f8584k;
            this.f8578e = (ewc) eov.mo4862a(context, looper, epk, epk.f8781f, emt, emt);
        }
        this.f8588o = ((C0309lf) this.f8574a.f8633f).f15193b;
        this.f8593t.add(enf.f8642a.submit(new emp(this, hashMap)));
    }

    /* renamed from: h */
    private final void m7810h() {
        ArrayList arrayList = this.f8593t;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Future) arrayList.get(i)).cancel(true);
        }
        this.f8593t.clear();
    }

    /* renamed from: f */
    public final void mo5028f() {
        this.f8579f = false;
        this.f8574a.f8639l.f8611k = Collections.emptySet();
        for (eki eki : this.f8590q) {
            if (!this.f8574a.f8634g.containsKey(eki)) {
                this.f8574a.f8634g.put(eki, new ejq(17, (PendingIntent) null));
            }
        }
    }

    /* renamed from: b */
    public final boolean mo5025b(int i) {
        if (this.f8587n == i) {
            return true;
        }
        Log.w("GACConnecting", this.f8574a.f8639l.mo5034g());
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(valueOf);
        Log.w("GACConnecting", sb.toString());
        int i2 = this.f8588o;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i2);
        Log.w("GACConnecting", sb2.toString());
        String c = m7808c(this.f8587n);
        String c2 = m7808c(i);
        StringBuilder sb3 = new StringBuilder(c.length() + 70 + c2.length());
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(c);
        sb3.append(" but received callback for step ");
        sb3.append(c2);
        Log.e("GACConnecting", sb3.toString(), new Exception());
        mo5023b(new ejq(8, (PendingIntent) null));
        return false;
    }

    /* renamed from: c */
    public final void mo5016c() {
        m7810h();
        m7807a(true);
        this.f8574a.mo5037d();
    }

    /* renamed from: a */
    private final void m7807a(boolean z) {
        ewc ewc = this.f8578e;
        if (ewc != null) {
            if (ewc.mo4932e() && z) {
                this.f8578e.mo5333m();
            }
            this.f8578e.mo4931d();
            this.f8581h = null;
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: g */
    private final void m7809g() {
        ene ene = this.f8574a;
        ene.f8628a.lock();
        try {
            ene.f8639l.mo5033f();
            ene.f8637j = new emk(ene);
            ene.f8637j.mo5010a();
            ene.f8629b.signalAll();
            ene.f8628a.unlock();
            enf.f8642a.execute(new eml(this));
            ewc ewc = this.f8578e;
            if (ewc != null) {
                if (this.f8582i) {
                    ewc.mo5331a(this.f8581h, this.f8583j);
                }
                m7807a(false);
            }
            for (eki eki : this.f8574a.f8634g.keySet()) {
                ((ekm) this.f8574a.f8633f.get(eki)).mo4931d();
            }
            this.f8574a.f8640m.mo4996a(!this.f8589p.isEmpty() ? this.f8589p : null);
        } catch (Throwable th) {
            ene.f8628a.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    public final elq mo5009a(elq elq) {
        this.f8574a.f8639l.f8608h.add(elq);
        return elq;
    }

    /* renamed from: b */
    public final elq mo5014b(elq elq) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    /* renamed from: b */
    public final void mo5023b(ejq ejq) {
        m7810h();
        m7807a(!ejq.mo4894a());
        this.f8574a.mo5037d();
        this.f8574a.f8640m.mo4997a(ejq);
    }

    /* renamed from: d */
    public final boolean mo5026d() {
        int i = this.f8588o - 1;
        this.f8588o = i;
        if (i > 0) {
            return false;
        }
        if (i < 0) {
            Log.w("GACConnecting", this.f8574a.f8639l.mo5034g());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            mo5023b(new ejq(8, (PendingIntent) null));
            return false;
        }
        ejq ejq = this.f8585l;
        if (ejq == null) {
            return true;
        }
        this.f8574a.f8638k = this.f8586m;
        mo5023b(ejq);
        return false;
    }

    /* renamed from: a */
    public final void mo5012a(Bundle bundle) {
        if (mo5025b(1)) {
            if (bundle != null) {
                this.f8589p.putAll(bundle);
            }
            if (mo5026d()) {
                m7809g();
            }
        }
    }

    /* renamed from: a */
    public final void mo5013a(ejq ejq, ekn ekn, boolean z) {
        if (mo5025b(1)) {
            mo5024b(ejq, ekn, z);
            if (mo5026d()) {
                m7809g();
            }
        }
    }

    /* renamed from: a */
    public final void mo5011a(int i) {
        mo5023b(new ejq(8, (PendingIntent) null));
    }

    /* renamed from: b */
    public final void mo5024b(ejq ejq, ekn ekn, boolean z) {
        if ((!z || ejq.mo4894a() || this.f8577d.mo4917a((Context) null, ejq.f8443b, (String) null) != null) && this.f8585l == null) {
            this.f8585l = ejq;
            this.f8586m = Integer.MAX_VALUE;
        }
        this.f8574a.f8634g.put(ekn.mo4940a(), ejq);
    }

    /* renamed from: a */
    public final boolean mo5022a(ejq ejq) {
        return this.f8591r && !ejq.mo4894a();
    }

    /* renamed from: e */
    public final void mo5027e() {
        if (this.f8588o != 0) {
            return;
        }
        if (!this.f8579f || this.f8580g) {
            ArrayList arrayList = new ArrayList();
            this.f8587n = 1;
            Map map = this.f8574a.f8633f;
            this.f8588o = ((C0309lf) map).f15193b;
            for (eki eki : map.keySet()) {
                if (!this.f8574a.f8634g.containsKey(eki)) {
                    arrayList.add((ekm) this.f8574a.f8633f.get(eki));
                } else if (mo5026d()) {
                    m7809g();
                }
            }
            if (!arrayList.isEmpty()) {
                this.f8593t.add(enf.f8642a.submit(new emq(this, arrayList)));
            }
        }
    }
}
