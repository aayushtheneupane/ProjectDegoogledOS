package p000;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* renamed from: enl */
/* compiled from: PG */
public final class enl implements ekt, eku, elz {

    /* renamed from: a */
    public final Queue f8648a = new LinkedList();

    /* renamed from: b */
    public final ekm f8649b;

    /* renamed from: c */
    public final emg f8650c;

    /* renamed from: d */
    public final Map f8651d = new HashMap();

    /* renamed from: e */
    public final int f8652e;

    /* renamed from: f */
    public boolean f8653f;

    /* renamed from: g */
    public final List f8654g = new ArrayList();

    /* renamed from: h */
    public final /* synthetic */ enp f8655h;

    /* renamed from: i */
    private final ekl f8656i;

    /* renamed from: j */
    private final eln f8657j;

    /* renamed from: k */
    private final Set f8658k = new HashSet();

    /* renamed from: l */
    private final eoj f8659l;

    /* renamed from: m */
    private ejq f8660m = null;

    public enl(enp enp, ekr ekr) {
        this.f8655h = enp;
        ekm a = ekr.f8485b.mo4941b().mo4862a(ekr.f8484a, enp.f8684m.getLooper(), ekr.mo4944a().mo5130a(), (Object) null, this, this);
        this.f8649b = a;
        if (a instanceof equ) {
            equ equ = (equ) a;
            this.f8656i = null;
        } else {
            this.f8656i = a;
        }
        this.f8657j = ekr.f8486c;
        this.f8650c = new emg();
        this.f8652e = ekr.f8488e;
        if (this.f8649b.mo4934g()) {
            this.f8659l = new eoj(enp.f8678g, enp.f8684m, ekr.mo4944a().mo5130a());
        } else {
            this.f8659l = null;
        }
    }

    /* renamed from: c */
    private final void m7871c(ejq ejq) {
        Iterator it = this.f8658k.iterator();
        if (it.hasNext()) {
            eki eki = (eki) it.next();
            if (C0652xy.m16068a((Object) ejq, (Object) ejq.f8442a)) {
                this.f8649b.mo4937j();
            }
            throw null;
        }
        this.f8658k.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e4  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean m7870b(p000.ell r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof p000.elg
            r1 = 1
            if (r0 != 0) goto L_0x0009
            r14.m7872c((p000.ell) r15)
            return r1
        L_0x0009:
            r0 = r15
            elg r0 = (p000.elg) r0
            ejt[] r2 = r0.mo4960a(r14)
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L_0x0065
            int r5 = r2.length
            if (r5 == 0) goto L_0x0064
            ekm r5 = r14.f8649b
            ejt[] r5 = r5.mo4935h()
            if (r5 == 0) goto L_0x0020
            goto L_0x0023
        L_0x0020:
            ejt[] r5 = new p000.ejt[r3]
        L_0x0023:
            int r6 = r5.length
            kn r7 = new kn
            r7.<init>((int) r6)
            r8 = 0
        L_0x002a:
            if (r8 >= r6) goto L_0x003e
            r9 = r5[r8]
            java.lang.String r10 = r9.f8449a
            long r11 = r9.mo4904a()
            java.lang.Long r9 = java.lang.Long.valueOf(r11)
            r7.put(r10, r9)
            int r8 = r8 + 1
            goto L_0x002a
        L_0x003e:
            int r5 = r2.length
            r6 = 0
        L_0x0040:
            if (r6 >= r5) goto L_0x0065
            r8 = r2[r6]
            java.lang.String r9 = r8.f8449a
            boolean r9 = r7.containsKey(r9)
            if (r9 == 0) goto L_0x0063
            java.lang.String r9 = r8.f8449a
            java.lang.Object r9 = r7.get(r9)
            java.lang.Long r9 = (java.lang.Long) r9
            long r9 = r9.longValue()
            long r11 = r8.mo4904a()
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 < 0) goto L_0x0063
            int r6 = r6 + 1
            goto L_0x0040
        L_0x0063:
            goto L_0x0066
        L_0x0064:
        L_0x0065:
            r8 = r4
        L_0x0066:
            if (r8 == 0) goto L_0x00e4
            boolean r15 = r0.mo4961b(r14)
            if (r15 != 0) goto L_0x0077
            elf r15 = new elf
            r15.<init>(r8)
            r0.mo4964a((java.lang.Exception) r15)
            goto L_0x00e3
        L_0x0077:
            enm r15 = new enm
            eln r0 = r14.f8657j
            r15.<init>(r0, r8)
            java.util.List r0 = r14.f8654g
            int r0 = r0.indexOf(r15)
            r1 = 15
            if (r0 < 0) goto L_0x00a9
            java.util.List r15 = r14.f8654g
            java.lang.Object r15 = r15.get(r0)
            enm r15 = (p000.enm) r15
            enp r0 = r14.f8655h
            com.google.android.gms.common.api.Status r2 = p000.enp.f8671a
            android.os.Handler r0 = r0.f8684m
            r0.removeMessages(r1, r15)
            enp r0 = r14.f8655h
            android.os.Handler r0 = r0.f8684m
            android.os.Message r15 = android.os.Message.obtain(r0, r1, r15)
            enp r1 = r14.f8655h
            long r1 = r1.f8675c
            r0.sendMessageDelayed(r15, r1)
            goto L_0x00e3
        L_0x00a9:
            java.util.List r0 = r14.f8654g
            r0.add(r15)
            enp r0 = r14.f8655h
            com.google.android.gms.common.api.Status r2 = p000.enp.f8671a
            android.os.Handler r0 = r0.f8684m
            android.os.Message r1 = android.os.Message.obtain(r0, r1, r15)
            enp r2 = r14.f8655h
            long r5 = r2.f8675c
            r0.sendMessageDelayed(r1, r5)
            enp r0 = r14.f8655h
            android.os.Handler r0 = r0.f8684m
            r1 = 16
            android.os.Message r15 = android.os.Message.obtain(r0, r1, r15)
            enp r1 = r14.f8655h
            long r1 = r1.f8676d
            r0.sendMessageDelayed(r15, r1)
            ejq r15 = new ejq
            r0 = 2
            r15.<init>(r0, r4)
            boolean r0 = r14.m7869b((p000.ejq) r15)
            if (r0 != 0) goto L_0x00e3
            enp r0 = r14.f8655h
            int r1 = r14.f8652e
            r0.mo5061a(r15, r1)
        L_0x00e3:
            return r3
        L_0x00e4:
            r14.m7872c((p000.ell) r15)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.enl.m7870b(ell):boolean");
    }

    /* renamed from: e */
    public final void mo5047e() {
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        abj.m90a(enp.f8684m);
        this.f8660m = null;
    }

    /* renamed from: h */
    public final void mo5050h() {
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        abj.m90a(enp.f8684m);
        if (!this.f8649b.mo4932e() && !this.f8649b.mo4933f()) {
            try {
                enp enp2 = this.f8655h;
                int a = enp2.f8679h.mo5158a(enp2.f8678g, this.f8649b);
                if (a == 0) {
                    eno eno = new eno(this.f8655h, this.f8649b, this.f8657j);
                    if (this.f8649b.mo4934g()) {
                        eoj eoj = this.f8659l;
                        ewc ewc = eoj.f8711e;
                        if (ewc != null) {
                            ewc.mo4931d();
                        }
                        eoj.f8710d.f8782g = Integer.valueOf(System.identityHashCode(eoj));
                        eov eov = eoj.f8713g;
                        Context context = eoj.f8707a;
                        Looper looper = eoj.f8708b.getLooper();
                        epk epk = eoj.f8710d;
                        eoj.f8711e = (ewc) eov.mo4862a(context, looper, epk, epk.f8781f, eoj, eoj);
                        eoj.f8712f = eno;
                        Set set = eoj.f8709c;
                        if (set != null && !set.isEmpty()) {
                            eoj.f8711e.mo5334n();
                        } else {
                            eoj.f8708b.post(new eog(eoj));
                        }
                    }
                    try {
                        this.f8649b.mo4927a((epc) eno);
                    } catch (SecurityException e) {
                        m7868a(new ejq(10), e);
                    }
                } else {
                    mo4994a(new ejq(a, (PendingIntent) null));
                }
            } catch (IllegalStateException e2) {
                m7868a(new ejq(10), e2);
            }
        }
    }

    /* renamed from: a */
    public final void mo5043a(ell ell) {
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        abj.m90a(enp.f8684m);
        if (!this.f8649b.mo4932e()) {
            this.f8648a.add(ell);
            ejq ejq = this.f8660m;
            if (ejq == null || !ejq.mo4894a()) {
                mo5050h();
            } else {
                mo4994a(this.f8660m);
            }
        } else if (!m7870b(ell)) {
            this.f8648a.add(ell);
        } else {
            mo5049g();
        }
    }

    /* renamed from: a */
    public final void mo5042a(Status status) {
        enp enp = this.f8655h;
        Status status2 = enp.f8671a;
        abj.m90a(enp.f8684m);
        for (ell a : this.f8648a) {
            a.mo4962a(status);
        }
        this.f8648a.clear();
    }

    /* renamed from: c */
    public final void mo5045c() {
        ArrayList arrayList = new ArrayList(this.f8648a);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            ell ell = (ell) arrayList.get(i);
            if (this.f8649b.mo4932e()) {
                if (m7870b(ell)) {
                    this.f8648a.remove(ell);
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public final void mo4993a(Bundle bundle) {
        Looper myLooper = Looper.myLooper();
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        if (myLooper != enp.f8684m.getLooper()) {
            this.f8655h.f8684m.post(new enh(this));
        } else {
            mo5041a();
        }
    }

    /* renamed from: a */
    public final void mo5041a() {
        mo5047e();
        m7871c(ejq.f8442a);
        mo5048f();
        Iterator it = this.f8651d.values().iterator();
        if (!it.hasNext()) {
            mo5045c();
            mo5049g();
            return;
        }
        eod eod = (eod) it.next();
        throw null;
    }

    /* renamed from: a */
    public final void mo4994a(ejq ejq) {
        m7868a(ejq, (Exception) null);
    }

    /* renamed from: a */
    private final void m7868a(ejq ejq, Exception exc) {
        ewc ewc;
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        abj.m90a(enp.f8684m);
        eoj eoj = this.f8659l;
        if (!(eoj == null || (ewc = eoj.f8711e) == null)) {
            ewc.mo4931d();
        }
        mo5047e();
        this.f8655h.f8679h.mo5159a();
        m7871c(ejq);
        if (ejq.f8443b == 4) {
            mo5042a(enp.f8672b);
        } else if (this.f8648a.isEmpty()) {
            this.f8660m = ejq;
        } else if (exc != null) {
            abj.m90a(this.f8655h.f8684m);
            for (ell a : this.f8648a) {
                a.mo4964a(exc);
            }
            this.f8648a.clear();
        } else if (!m7869b(ejq) && !this.f8655h.mo5061a(ejq, this.f8652e)) {
            if (ejq.f8443b == 18) {
                this.f8653f = true;
            }
            if (!this.f8653f) {
                String str = this.f8657j.f8511a.f8476a;
                String valueOf = String.valueOf(ejq);
                StringBuilder sb = new StringBuilder(str.length() + 63 + String.valueOf(valueOf).length());
                sb.append("API: ");
                sb.append(str);
                sb.append(" is not available on this device. Connection failed with: ");
                sb.append(valueOf);
                mo5042a(new Status(17, sb.toString()));
                return;
            }
            Handler handler = this.f8655h.f8684m;
            handler.sendMessageDelayed(Message.obtain(handler, 9, this.f8657j), this.f8655h.f8675c);
        }
    }

    /* renamed from: a */
    public final void mo4992a(int i) {
        Looper myLooper = Looper.myLooper();
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        if (myLooper != enp.f8684m.getLooper()) {
            this.f8655h.f8684m.post(new eni(this));
        } else {
            mo5044b();
        }
    }

    /* renamed from: b */
    public final void mo5044b() {
        mo5047e();
        this.f8653f = true;
        this.f8650c.mo5006a(true, eot.f8727a);
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        Handler handler = enp.f8684m;
        handler.sendMessageDelayed(Message.obtain(handler, 9, this.f8657j), this.f8655h.f8675c);
        Handler handler2 = this.f8655h.f8684m;
        handler2.sendMessageDelayed(Message.obtain(handler2, 11, this.f8657j), this.f8655h.f8676d);
        this.f8655h.f8679h.mo5159a();
        Iterator it = this.f8651d.values().iterator();
        if (it.hasNext()) {
            eod eod = (eod) it.next();
            throw null;
        }
    }

    /* renamed from: i */
    public final boolean mo5051i() {
        return this.f8649b.mo4934g();
    }

    /* renamed from: g */
    public final void mo5049g() {
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        enp.f8684m.removeMessages(12, this.f8657j);
        Handler handler = this.f8655h.f8684m;
        handler.sendMessageDelayed(handler.obtainMessage(12, this.f8657j), this.f8655h.f8677e);
    }

    /* renamed from: c */
    private final void m7872c(ell ell) {
        ell.mo4963a(this.f8650c, mo5051i());
        try {
            ell.mo4965c(this);
        } catch (DeadObjectException e) {
            mo4992a(1);
            this.f8649b.mo4931d();
        } catch (Throwable th) {
            throw new IllegalStateException(String.format("Error in GoogleApi implementation for client %s.", new Object[]{this.f8656i.getClass().getName()}), th);
        }
    }

    /* renamed from: d */
    public final void mo5046d() {
        enp enp = this.f8655h;
        Status status = enp.f8671a;
        abj.m90a(enp.f8684m);
        mo5042a(enp.f8671a);
        this.f8650c.mo5006a(false, enp.f8671a);
        for (enz elk : (enz[]) this.f8651d.keySet().toArray(new enz[this.f8651d.size()])) {
            mo5043a((ell) new elk(elk, new exe()));
        }
        m7871c(new ejq(4));
        if (this.f8649b.mo4932e()) {
            this.f8649b.mo4928a((eph) new enk(this));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        return false;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean m7869b(p000.ejq r5) {
        /*
            r4 = this;
            java.lang.Object r0 = p000.enp.f8673f
            monitor-enter(r0)
            enp r1 = r4.f8655h     // Catch:{ all -> 0x0037 }
            emh r2 = r1.f8682k     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0034
            java.util.Set r1 = r1.f8683l     // Catch:{ all -> 0x0037 }
            eln r2 = r4.f8657j     // Catch:{ all -> 0x0037 }
            boolean r1 = r1.contains(r2)     // Catch:{ all -> 0x0037 }
            if (r1 == 0) goto L_0x0034
            enp r1 = r4.f8655h     // Catch:{ all -> 0x0037 }
            emh r1 = r1.f8682k     // Catch:{ all -> 0x0037 }
            int r2 = r4.f8652e     // Catch:{ all -> 0x0037 }
            elr r3 = new elr     // Catch:{ all -> 0x0037 }
            r3.<init>(r5, r2)     // Catch:{ all -> 0x0037 }
            java.util.concurrent.atomic.AtomicReference r5 = r1.f8527b     // Catch:{ all -> 0x0037 }
            r2 = 0
            boolean r5 = r5.compareAndSet(r2, r3)     // Catch:{ all -> 0x0037 }
            if (r5 == 0) goto L_0x0031
            android.os.Handler r5 = r1.f8528c     // Catch:{ all -> 0x0037 }
            elt r2 = new elt     // Catch:{ all -> 0x0037 }
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0037 }
            r5.post(r2)     // Catch:{ all -> 0x0037 }
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            r5 = 1
            return r5
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            r5 = 0
            return r5
        L_0x0037:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.enl.m7869b(ejq):boolean");
    }

    /* renamed from: f */
    public final void mo5048f() {
        if (this.f8653f) {
            enp enp = this.f8655h;
            Status status = enp.f8671a;
            enp.f8684m.removeMessages(11, this.f8657j);
            this.f8655h.f8684m.removeMessages(9, this.f8657j);
            this.f8653f = false;
        }
    }
}
