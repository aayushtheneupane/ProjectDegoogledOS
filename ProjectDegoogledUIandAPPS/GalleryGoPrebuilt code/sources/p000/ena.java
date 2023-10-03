package p000;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* renamed from: ena */
/* compiled from: PG */
public final class ena extends ekv implements ent {

    /* renamed from: t */
    public static /* synthetic */ int f8601t;

    /* renamed from: b */
    public final Lock f8602b;

    /* renamed from: c */
    public final epx f8603c;

    /* renamed from: d */
    public enu f8604d = null;

    /* renamed from: e */
    public final int f8605e;

    /* renamed from: f */
    public final Context f8606f;

    /* renamed from: g */
    public final Looper f8607g;

    /* renamed from: h */
    public final Queue f8608h = new LinkedList();

    /* renamed from: i */
    public final ejw f8609i;

    /* renamed from: j */
    public final Map f8610j;

    /* renamed from: k */
    public Set f8611k = new HashSet();

    /* renamed from: l */
    public final epk f8612l;

    /* renamed from: m */
    public final Map f8613m;

    /* renamed from: n */
    public final eoa f8614n = new eoa();

    /* renamed from: o */
    public final ArrayList f8615o;

    /* renamed from: p */
    public Integer f8616p = null;

    /* renamed from: q */
    public Set f8617q = null;

    /* renamed from: r */
    public final eot f8618r;

    /* renamed from: s */
    public final eov f8619s;

    /* renamed from: u */
    private volatile boolean f8620u;

    /* renamed from: v */
    private long f8621v = 120000;

    /* renamed from: w */
    private long f8622w = 5000;

    /* renamed from: x */
    private final emy f8623x;

    /* renamed from: y */
    private ens f8624y;

    /* renamed from: z */
    private final epw f8625z = new emx(this);

    public ena(Context context, Lock lock, Looper looper, epk epk, ejw ejw, eov eov, Map map, List list, List list2, Map map2, ArrayList arrayList, byte[] bArr, byte[] bArr2) {
        this.f8606f = context;
        this.f8602b = lock;
        this.f8603c = new epx(looper, this.f8625z);
        this.f8607g = looper;
        this.f8623x = new emy(this, looper);
        this.f8609i = ejw;
        this.f8605e = -1;
        this.f8613m = map;
        this.f8610j = map2;
        this.f8615o = arrayList;
        this.f8618r = new eot();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.f8603c.mo5140a((ekt) list.get(i));
        }
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.f8603c.mo5141a((eku) list2.get(i2));
        }
        this.f8612l = epk;
        this.f8619s = eov;
    }

    /* renamed from: b */
    public static String m7837b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    /* renamed from: b */
    public final Looper mo4949b() {
        return this.f8607g;
    }

    /* renamed from: d */
    public final void mo5031d() {
        this.f8603c.f8811e = true;
        this.f8604d.mo4999a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final String mo5034g() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.append("").append("mContext=").println(this.f8606f);
        printWriter.append("").append("mResuming=").print(this.f8620u);
        printWriter.append(" mWorkQueue.size()=").print(this.f8608h.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.f8618r.f8729c.size());
        enu enu = this.f8604d;
        if (enu != null) {
            enu.mo5001a("", printWriter);
        }
        return stringWriter.toString();
    }

    /* renamed from: a */
    public final elq mo4948a(elq elq) {
        String str;
        Lock lock;
        abj.m117b(true, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.f8610j.containsKey(elq.f8519b);
        ekn ekn = elq.f8518a;
        if (ekn == null) {
            str = "the API";
        } else {
            str = ekn.f8476a;
        }
        StringBuilder sb = new StringBuilder(str.length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(str);
        sb.append(" required for this call.");
        abj.m117b(containsKey, (Object) sb.toString());
        this.f8602b.lock();
        try {
            enu enu = this.f8604d;
            if (enu == null) {
                this.f8608h.add(elq);
                lock = this.f8602b;
            } else {
                elq = enu.mo4998a(elq);
                lock = this.f8602b;
            }
            lock.unlock();
            return elq;
        } catch (Throwable th) {
            this.f8602b.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    public final void mo4997a(ejq ejq) {
        if (!ekh.m7673b(this.f8606f, ejq.f8443b)) {
            mo5033f();
        }
        if (!this.f8620u) {
            epx epx = this.f8603c;
            abj.m91a(epx.f8814h, "onConnectionFailure must only be called on the Handler thread");
            epx.f8814h.removeMessages(1);
            synchronized (epx.f8815i) {
                ArrayList arrayList = new ArrayList(epx.f8810d);
                int i = epx.f8812f.get();
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    eku eku = (eku) it.next();
                    if (epx.f8811e && epx.f8812f.get() == i) {
                        if (epx.f8810d.contains(eku)) {
                            eku.mo4994a(ejq);
                        }
                    }
                }
            }
            this.f8603c.mo5139a();
        }
    }

    /* renamed from: a */
    public final void mo4996a(Bundle bundle) {
        String str;
        Lock lock;
        while (true) {
            boolean z = true;
            if (!this.f8608h.isEmpty()) {
                elq elq = (elq) this.f8608h.remove();
                eki eki = elq.f8519b;
                abj.m117b(true, (Object) "This task can not be executed (it's probably a Batch or malformed)");
                boolean containsKey = this.f8610j.containsKey(elq.f8519b);
                ekn ekn = elq.f8518a;
                if (ekn == null) {
                    str = "the API";
                } else {
                    str = ekn.f8476a;
                }
                StringBuilder sb = new StringBuilder(str.length() + 65);
                sb.append("GoogleApiClient is not configured to use ");
                sb.append(str);
                sb.append(" required for this call.");
                abj.m117b(containsKey, (Object) sb.toString());
                this.f8602b.lock();
                try {
                    if (this.f8604d != null) {
                        if (!this.f8620u) {
                            this.f8604d.mo5002b(elq);
                            lock = this.f8602b;
                        } else {
                            this.f8608h.add(elq);
                            while (!this.f8608h.isEmpty()) {
                                elq elq2 = (elq) this.f8608h.remove();
                                this.f8618r.mo5093a(elq2);
                                elq2.mo4980b(Status.f4974c);
                            }
                            lock = this.f8602b;
                        }
                        lock.unlock();
                    } else {
                        throw new IllegalStateException("GoogleApiClient is not connected yet.");
                    }
                } catch (Throwable th) {
                    this.f8602b.unlock();
                    throw th;
                }
            } else {
                epx epx = this.f8603c;
                abj.m91a(epx.f8814h, "onConnectionSuccess must only be called on the Handler thread");
                synchronized (epx.f8815i) {
                    abj.m107a(!epx.f8813g);
                    epx.f8814h.removeMessages(1);
                    epx.f8813g = true;
                    if (epx.f8809c.size() != 0) {
                        z = false;
                    }
                    abj.m107a(z);
                    ArrayList arrayList = new ArrayList(epx.f8808b);
                    int i = epx.f8812f.get();
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ekt ekt = (ekt) it.next();
                        if (epx.f8811e) {
                            if (!epx.f8807a.mo5029e() || epx.f8812f.get() != i) {
                                break;
                            } else if (!epx.f8809c.contains(ekt)) {
                                ekt.mo4993a(bundle);
                            }
                        } else {
                            break;
                        }
                    }
                    epx.f8809c.clear();
                    epx.f8813g = false;
                }
                return;
            }
        }
    }

    /* renamed from: a */
    public final void mo4995a(int i) {
        if (i == 1 && !this.f8620u) {
            this.f8620u = true;
            if (this.f8624y == null) {
                try {
                    this.f8624y = this.f8609i.mo4912a(this.f8606f.getApplicationContext(), (enr) new emz(this));
                } catch (SecurityException e) {
                }
            }
            emy emy = this.f8623x;
            emy.sendMessageDelayed(emy.obtainMessage(1), this.f8621v);
            emy emy2 = this.f8623x;
            emy2.sendMessageDelayed(emy2.obtainMessage(2), this.f8622w);
        }
        for (BasePendingResult c : (BasePendingResult[]) this.f8618r.f8729c.toArray(eot.f8728b)) {
            c.mo3513c(eot.f8727a);
        }
        epx epx = this.f8603c;
        abj.m91a(epx.f8814h, "onUnintentionalDisconnection must only be called on the Handler thread");
        epx.f8814h.removeMessages(1);
        synchronized (epx.f8815i) {
            epx.f8813g = true;
            ArrayList arrayList = new ArrayList(epx.f8808b);
            int i2 = epx.f8812f.get();
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ekt ekt = (ekt) it.next();
                if (epx.f8811e) {
                    if (epx.f8812f.get() != i2) {
                        break;
                    } else if (epx.f8808b.contains(ekt)) {
                        ekt.mo4992a(i);
                    }
                } else {
                    break;
                }
            }
            epx.f8809c.clear();
            epx.f8813g = false;
        }
        this.f8603c.mo5139a();
        if (i == 2) {
            mo5031d();
        }
    }

    /* renamed from: c */
    public final boolean mo4950c() {
        enu enu = this.f8604d;
        return enu != null && enu.mo5004c();
    }

    /* renamed from: e */
    public final void mo5032e() {
        this.f8602b.lock();
        try {
            if (this.f8620u) {
                mo5031d();
            }
        } finally {
            this.f8602b.unlock();
        }
    }

    /* renamed from: a */
    public static int m7836a(Iterable iterable) {
        Iterator it = iterable.iterator();
        boolean z = false;
        while (it.hasNext()) {
            ekm ekm = (ekm) it.next();
            if (ekm.mo4934g()) {
                z = true;
            }
            ekm.mo4938k();
        }
        if (!z) {
            return 3;
        }
        return 1;
    }

    /* renamed from: f */
    public final boolean mo5033f() {
        boolean z = false;
        if (this.f8620u) {
            this.f8620u = false;
            this.f8623x.removeMessages(2);
            z = true;
            this.f8623x.removeMessages(1);
            ens ens = this.f8624y;
            if (ens != null) {
                ens.mo5065a();
                this.f8624y = null;
            }
        }
        return z;
    }
}
