package p000;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

/* renamed from: emc */
/* compiled from: PG */
public final class emc implements enu {

    /* renamed from: a */
    public final ene f8537a;

    /* renamed from: b */
    public final ene f8538b;

    /* renamed from: c */
    public Bundle f8539c;

    /* renamed from: d */
    public ejq f8540d = null;

    /* renamed from: e */
    public ejq f8541e = null;

    /* renamed from: f */
    public boolean f8542f = false;

    /* renamed from: g */
    public final Lock f8543g;

    /* renamed from: h */
    private final ena f8544h;

    /* renamed from: i */
    private final Map f8545i;

    /* renamed from: j */
    private final Set f8546j = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: k */
    private int f8547k = 0;

    /* renamed from: f */
    private final boolean m7767f() {
        ejq ejq = this.f8541e;
        return ejq != null && ejq.f8443b == 4;
    }

    public emc(Context context, ena ena, Lock lock, Looper looper, ejx ejx, Map map, Map map2, epk epk, eov eov, ArrayList arrayList, ArrayList arrayList2, Map map3, Map map4, byte[] bArr, byte[] bArr2) {
        this.f8544h = ena;
        this.f8543g = lock;
        Context context2 = context;
        Lock lock2 = lock;
        Looper looper2 = looper;
        ejx ejx2 = ejx;
        ene ene = r1;
        ene ene2 = new ene(context2, this.f8544h, lock2, looper2, ejx2, map2, (epk) null, map4, (eov) null, arrayList2, new ema(this), (byte[]) null, (byte[]) null);
        this.f8537a = ene;
        this.f8538b = new ene(context2, this.f8544h, lock2, looper2, ejx2, map, epk, map3, eov, arrayList, new emb(this), (byte[]) null, (byte[]) null);
        C0290kn knVar = new C0290kn();
        for (eki put : map2.keySet()) {
            knVar.put(put, this.f8537a);
        }
        for (eki put2 : map.keySet()) {
            knVar.put(put2, this.f8538b);
        }
        this.f8545i = Collections.unmodifiableMap(knVar);
    }

    /* renamed from: a */
    private final void m7763a(ejq ejq) {
        int i = this.f8547k;
        if (i != 1) {
            if (i != 2) {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                this.f8547k = 0;
            }
            this.f8544h.mo4997a(ejq);
        }
        m7766e();
        this.f8547k = 0;
    }

    /* renamed from: e */
    private final void m7766e() {
        for (eof a : this.f8546j) {
            a.mo5084a();
        }
        this.f8546j.clear();
    }

    /* renamed from: a */
    public final void mo4999a() {
        this.f8547k = 2;
        this.f8542f = false;
        this.f8541e = null;
        this.f8540d = null;
        this.f8537a.mo4999a();
        this.f8538b.mo4999a();
    }

    /* renamed from: b */
    public final void mo5003b() {
        this.f8541e = null;
        this.f8540d = null;
        this.f8547k = 0;
        this.f8537a.mo5003b();
        this.f8538b.mo5003b();
        m7766e();
    }

    /* renamed from: a */
    public final void mo5001a(String str, PrintWriter printWriter) {
        printWriter.append(str).append("authClient").println(":");
        this.f8538b.mo5001a(str.concat("  "), printWriter);
        printWriter.append(str).append("anonClient").println(":");
        this.f8537a.mo5001a(str.concat("  "), printWriter);
    }

    /* renamed from: a */
    public final elq mo4998a(elq elq) {
        if (!m7765c(elq)) {
            return this.f8537a.mo4998a(elq);
        }
        if (!m7767f()) {
            return this.f8538b.mo4998a(elq);
        }
        elq.mo4980b(new Status(4, (String) null, (byte[]) null));
        return elq;
    }

    /* renamed from: b */
    public final elq mo5002b(elq elq) {
        if (!m7765c(elq)) {
            return this.f8537a.mo5002b(elq);
        }
        if (!m7767f()) {
            return this.f8538b.mo5002b(elq);
        }
        elq.mo4980b(new Status(4, (String) null, (byte[]) null));
        return elq;
    }

    /* renamed from: a */
    public final void mo5000a(int i) {
        this.f8544h.mo4995a(i);
        this.f8541e = null;
        this.f8540d = null;
    }

    /* renamed from: c */
    private final boolean m7765c(elq elq) {
        eki eki = elq.f8519b;
        abj.m117b(this.f8545i.containsKey(eki), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return ((ene) this.f8545i.get(eki)).equals(this.f8538b);
    }

    /* renamed from: c */
    public final boolean mo5004c() {
        this.f8543g.lock();
        try {
            boolean z = false;
            if (this.f8537a.mo5004c() && (this.f8538b.mo5004c() || m7767f() || this.f8547k == 1)) {
                z = true;
            }
            return z;
        } finally {
            this.f8543g.unlock();
        }
    }

    /* renamed from: b */
    private static boolean m7764b(ejq ejq) {
        return ejq != null && ejq.mo4895b();
    }

    /* renamed from: d */
    public final void mo5005d() {
        ejq ejq;
        if (m7764b(this.f8540d)) {
            if (!m7764b(this.f8541e) && !m7767f()) {
                ejq ejq2 = this.f8541e;
                if (ejq2 == null) {
                    return;
                }
                if (this.f8547k != 1) {
                    m7763a(ejq2);
                    this.f8537a.mo5003b();
                    return;
                }
                m7766e();
                return;
            }
            int i = this.f8547k;
            if (i != 1) {
                if (i != 2) {
                    Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                    this.f8547k = 0;
                }
                this.f8544h.mo4996a(this.f8539c);
            }
            m7766e();
            this.f8547k = 0;
        } else if (this.f8540d == null || !m7764b(this.f8541e)) {
            ejq ejq3 = this.f8540d;
            if (ejq3 != null && (ejq = this.f8541e) != null) {
                if (this.f8538b.f8638k < this.f8537a.f8638k) {
                    ejq3 = ejq;
                }
                m7763a(ejq3);
            }
        } else {
            this.f8538b.mo5003b();
            m7763a(this.f8540d);
        }
    }
}
