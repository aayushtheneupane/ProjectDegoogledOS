package p000;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: ibz */
/* compiled from: PG */
abstract class ibz extends icd {

    /* renamed from: g */
    private static final Logger f13866g = Logger.getLogger(ibz.class.getName());

    /* renamed from: a */
    public hsf f13867a;

    /* renamed from: h */
    private final boolean f13868h;

    /* renamed from: i */
    private final boolean f13869i;

    /* renamed from: a */
    public abstract void mo8363a(int i, Object obj);

    /* renamed from: g */
    public abstract void mo8369g();

    public ibz(hsf hsf, boolean z, boolean z2) {
        super(hsf.size());
        this.f13867a = (hsf) ife.m12898e((Object) hsf);
        this.f13868h = z;
        this.f13869i = z2;
    }

    /* renamed from: a */
    private static boolean m12663a(Set set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    /* renamed from: a */
    public final void mo8367a(Set set) {
        ife.m12898e((Object) set);
        if (!isCancelled()) {
            m12663a(set, mo8349e());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        boolean z;
        hsf hsf = this.f13867a;
        mo8366a(iby.OUTPUT_FUTURE_DONE);
        boolean isCancelled = isCancelled();
        if (hsf != null) {
            z = true;
        } else {
            z = false;
        }
        if (isCancelled && z) {
            boolean d = mo8348d();
            hvr a = hsf.iterator();
            while (a.hasNext()) {
                ((Future) a.next()).cancel(d);
            }
        }
    }

    /* renamed from: a */
    public final void mo8364a(int i, Future future) {
        try {
            mo8363a(i, ife.m12871b(future));
        } catch (ExecutionException e) {
            m12664b(e.getCause());
        } catch (Throwable th) {
            m12664b(th);
        }
    }

    /* renamed from: a */
    public final void mo8365a(hsf hsf) {
        int a = icd.f13874f.mo8371a(this);
        int i = 0;
        ife.m12876b(a >= 0, (Object) "Less than 0 remaining futures");
        if (a == 0) {
            if (hsf != null) {
                hvr a2 = hsf.iterator();
                while (a2.hasNext()) {
                    Future future = (Future) a2.next();
                    if (!future.isCancelled()) {
                        mo8364a(i, future);
                    }
                    i++;
                }
            }
            this.seenExceptions = null;
            mo8369g();
            mo8366a(iby.ALL_INPUT_FUTURES_PROCESSED);
        }
    }

    /* renamed from: b */
    private final void m12664b(Throwable th) {
        ife.m12898e((Object) th);
        if (this.f13868h && !mo6952a(th)) {
            Set set = this.seenExceptions;
            if (set == null) {
                Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
                mo8367a(newSetFromMap);
                icd.f13874f.mo8372a(this, newSetFromMap);
                set = this.seenExceptions;
            }
            if (m12663a(set, th)) {
                m12665c(th);
                return;
            }
        }
        if (th instanceof Error) {
            m12665c(th);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo8368f() {
        hsf hsf;
        if (this.f13867a.isEmpty()) {
            mo8369g();
        } else if (this.f13868h) {
            hvr a = this.f13867a.iterator();
            int i = 0;
            while (a.hasNext()) {
                ieh ieh = (ieh) a.next();
                ieh.mo53a(new ibw(this, ieh, i), idh.f13918a);
                i++;
            }
        } else {
            if (this.f13869i) {
                hsf = this.f13867a;
            } else {
                hsf = null;
            }
            ibx ibx = new ibx(this, hsf);
            hvr a2 = this.f13867a.iterator();
            while (a2.hasNext()) {
                ((ieh) a2.next()).mo53a(ibx, idh.f13918a);
            }
        }
    }

    /* renamed from: c */
    private static void m12665c(Throwable th) {
        String str;
        if (!(th instanceof Error)) {
            str = "Got more than one input Future failure. Logging failures after the first";
        } else {
            str = "Input Future failed with Error";
        }
        f13866g.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", "log", str, th);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        hsf hsf = this.f13867a;
        if (hsf == null) {
            return super.mo6386a();
        }
        String valueOf = String.valueOf(hsf);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
        sb.append("futures=");
        sb.append(valueOf);
        return sb.toString();
    }

    /* renamed from: a */
    public void mo8366a(iby iby) {
        ife.m12898e((Object) iby);
        this.f13867a = null;
    }
}
