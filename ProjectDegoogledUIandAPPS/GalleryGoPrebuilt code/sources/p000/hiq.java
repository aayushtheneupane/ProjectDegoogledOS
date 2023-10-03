package p000;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: hiq */
/* compiled from: PG */
public final class hiq implements hjp, hhg {

    /* renamed from: l */
    private static final hvy f12808l = hvy.m12261a("com/google/apps/tiktok/sync/impl/SyncManager");

    /* renamed from: a */
    public final exm f12809a;

    /* renamed from: b */
    public final iek f12810b;

    /* renamed from: c */
    public final iel f12811c;

    /* renamed from: d */
    public final goo f12812d;

    /* renamed from: e */
    public final hiz f12813e;

    /* renamed from: f */
    public final Map f12814f;

    /* renamed from: g */
    public final ieh f12815g;

    /* renamed from: h */
    public final C0290kn f12816h = new C0290kn();

    /* renamed from: i */
    public final Map f12817i = new C0290kn();

    /* renamed from: j */
    public final Map f12818j = new C0290kn();

    /* renamed from: k */
    public final AtomicReference f12819k = new AtomicReference();

    /* renamed from: m */
    private final Context f12820m;

    /* renamed from: n */
    private final hpy f12821n;

    /* renamed from: o */
    private final hhs f12822o;

    /* renamed from: p */
    private final hjk f12823p;

    public hiq(exm exm, Context context, iek iek, iel iel, goo goo, hpy hpy, hiz hiz, Set set, Set set2, Map map, iqk iqk, hpy hpy2) {
        this.f12809a = exm;
        this.f12820m = context;
        this.f12810b = iek;
        this.f12811c = iel;
        this.f12812d = goo;
        this.f12821n = hpy;
        this.f12813e = hiz;
        this.f12814f = map;
        ife.m12876b(set2.isEmpty(), (Object) "SyncletBindings cannot be bound outside of account scope without @ApplicationSynclet.");
        this.f12815g = hiz.mo7485a();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hhe hhe = (hhe) it.next();
            C0290kn knVar = this.f12816h;
            hha a = hhe.mo7424a();
            iir g = hka.f12906d.mo8793g();
            hjz hjz = a.f12725a;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            hka hka = (hka) g.f14318b;
            hjz.getClass();
            hka.f12909b = hjz;
            hka.f12908a |= 1;
            knVar.put(new hjh((hka) g.mo8770g()), hhe);
        }
        hjk hjk = (hjk) ((hqc) hpy2).f13250a;
        this.f12823p = hjk;
        this.f12822o = hjk == null ? (hhs) iqk.mo2097a() : null;
    }

    /* renamed from: a */
    public final void mo7473a(Set set) {
        synchronized (this.f12816h) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                gkn gkn = (gkn) it.next();
                for (hhe hhe : ((hja) ggq.m10279a(this.f12820m, hja.class, gkn)).mo2463i()) {
                    hha a = hhe.mo7424a();
                    int a2 = gkn.mo6807a();
                    iir g = hka.f12906d.mo8793g();
                    hjz hjz = a.f12725a;
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    hka hka = (hka) g.f14318b;
                    hjz.getClass();
                    hka.f12909b = hjz;
                    int i = hka.f12908a | 1;
                    hka.f12908a = i;
                    hka.f12908a = i | 2;
                    hka.f12910c = a2;
                    this.f12816h.put(new hjh((hka) g.mo8770g()), hhe);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo7472a(Collection collection) {
        synchronized (this.f12817i) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                ieh ieh = (ieh) this.f12817i.get((hjh) it.next());
                if (ieh != null) {
                    ieh.cancel(true);
                }
            }
        }
    }

    /* renamed from: c */
    public final ieh mo7477c() {
        return ibv.m12657a(((glp) ((hqc) this.f12821n).f13250a).mo6833a(), hmq.m11742a(hic.f12782a), (Executor) this.f12810b);
    }

    /* renamed from: d */
    static final /* synthetic */ void m11547d(ieh ieh) {
        try {
            ife.m12871b((Future) ieh);
        } catch (CancellationException | ExecutionException e) {
            if (e.getCause() instanceof TimeoutException) {
                ((hvv) ((hvv) ((hvv) f12808l.mo8180b()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$onAccountsChanged$9", 513, "SyncManager.java")).mo8203a("Timeout updating accounts in sync. Some accounts may not sync correctly.");
            } else {
                ((hvv) ((hvv) ((hvv) f12808l.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$onAccountsChanged$9", 517, "SyncManager.java")).mo8203a("Updating sync accounts failed. Some accounts may not sync correctly.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ ieh mo7469a(iev iev, hjh hjh) {
        boolean z = false;
        try {
            ife.m12871b((Future) iev);
            z = true;
        } catch (ExecutionException e) {
            if (e.getCause() instanceof TimeoutException) {
                ((hvv) ((hvv) ((hvv) f12808l.mo8180b()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$runSyncs$2", 285, "SyncManager.java")).mo8206a("Sync cancelled from timeout and will be retried later: %s", (Object) hjh.f12850b);
            }
        } catch (CancellationException e2) {
        }
        long a = this.f12809a.mo5370a();
        return gqb.m10618a(this.f12813e.mo7486a(hjh, a, z), hmq.m11749a((Callable) new hii(a)), (Executor) this.f12810b);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ void mo7471a(hjh hjh, ieh ieh) {
        synchronized (this.f12817i) {
            this.f12817i.remove(hjh);
            try {
                this.f12818j.put(hjh, (Long) ife.m12871b((Future) ieh));
            } catch (CancellationException e) {
            } catch (ExecutionException e2) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ ieh mo7467a(ieh ieh, Long l) {
        C0290kn knVar;
        ieh ieh2;
        Set emptySet = Collections.emptySet();
        try {
            emptySet = (Set) ife.m12871b((Future) ieh);
        } catch (CancellationException | ExecutionException e) {
            ((hvv) ((hvv) ((hvv) f12808l.mo8180b()).mo8202a(e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$scheduleNextSync$13", 579, "SyncManager.java")).mo8203a("Unable to determine attempted syncs. They will not be used to schedule the next sync.");
        }
        synchronized (this.f12816h) {
            knVar = new C0290kn((C0309lf) this.f12816h);
        }
        long longValue = l.longValue();
        hjk hjk = this.f12823p;
        if (hjk != null) {
            ieh2 = hjk.mo7497a(emptySet, longValue, knVar);
        } else {
            ieh2 = this.f12822o.mo7456a(emptySet, longValue, knVar);
        }
        return ibv.m12658a(ieh2, hmq.m11744a((icf) new hif(this, knVar)), (Executor) idh.f13918a);
    }

    /* renamed from: c */
    static final /* synthetic */ void m11545c(ieh ieh) {
        try {
            ife.m12871b((Future) ieh);
        } catch (ExecutionException e) {
            ((hvv) ((hvv) ((hvv) f12808l.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$scheduleNextSync$15", 617, "SyncManager.java")).mo8203a("Error scheduling next sync wakeup");
        } catch (CancellationException e2) {
            ((hvv) ((hvv) ((hvv) f12808l.mo8178a()).mo8202a((Throwable) e2)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$scheduleNextSync$15", 619, "SyncManager.java")).mo8203a("The sync scheduling future was cancelled. This should never happen.");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* synthetic */ ieh mo7468a(ieh ieh, Map map) {
        boolean z;
        Throwable th;
        hln hln;
        hhe hhe;
        try {
            z = ((Boolean) ife.m12871b((Future) ieh)).booleanValue();
            th = null;
        } catch (CancellationException | ExecutionException e) {
            th = e;
            z = false;
        }
        if (!z) {
            ((hvv) ((hvv) ((hvv) f12808l.mo8180b()).mo8202a(th)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManager", "lambda$sync$6", 387, "SyncManager.java")).mo8203a("Failed preparing sync datastore for sync. Aborting sync attempt.");
            long a = this.f12809a.mo5370a();
            ArrayList arrayList = new ArrayList(map.size());
            for (hjh a2 : map.keySet()) {
                arrayList.add(this.f12813e.mo7486a(a2, a, false));
            }
            return gqb.m10618a(ife.m12819a((Iterable) arrayList), hmq.m11749a((Callable) new hig(this, map)), (Executor) this.f12810b);
        }
        ife.m12896d(m11546d().isDone());
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            hjh hjh = (hjh) entry.getKey();
            iev iev = (iev) entry.getValue();
            StringBuilder sb = new StringBuilder("Synclet: ");
            sb.append(hjh.f12850b.f12725a.f12901b);
            if (hjh.mo7492a()) {
                sb.append(" ");
                sb.append(((gkp) hjh.f12851c).f11548a);
            }
            if (hjh.mo7492a()) {
                hln = gko.m10448a(hln.m11704b(), hjh.f12851c, gtf.f12011a).mo7552a();
            } else {
                hln = hlm.f12987a;
            }
            hlj a3 = hnb.m11767a(sb.toString(), hnf.f13084a, hln);
            try {
                ieh a4 = a3.mo7548a(gqb.m10617a((ieh) iev, hmq.m11743a((ice) new hie(this, iev, hjh)), (Executor) this.f12810b));
                a4.mo53a(hmq.m11748a((Runnable) new hij(this, hjh, a4)), this.f12810b);
                synchronized (this.f12816h) {
                    hhe = (hhe) this.f12816h.get(hjh);
                }
                if (hhe != null) {
                    iev.mo6946a(ife.m12818a(((hhb) ife.m12898e((Object) (hhb) hhe.mo7426c().mo2097a())).mo6873a(), hhe.mo7425b().mo7414b(), TimeUnit.MILLISECONDS, (ScheduledExecutorService) this.f12811c));
                } else {
                    iev.cancel(true);
                }
                arrayList2.add(a4);
                a3.close();
            } catch (Throwable th2) {
                try {
                    a3.close();
                } catch (Throwable th3) {
                    ifn.m12935a(th2, th3);
                }
                throw th2;
            }
        }
        return ife.m12895d((Iterable) arrayList2);
    }

    /* renamed from: a */
    public final void mo7470a(hhf hhf) {
        hhf.mo7444b(this);
    }

    /* renamed from: a */
    public final ieh mo7465a() {
        return mo7466a(ife.m12820a((Object) Collections.emptySet()));
    }

    /* renamed from: b */
    public final ieh mo7474b() {
        long a = this.f12809a.mo5370a();
        hiz hiz = this.f12813e;
        return gqb.m10617a(hiz.f12841c.mo5933a((Callable) new hiy(hiz, a)), hmq.m11743a((ice) new hhw(this)), (Executor) this.f12810b);
    }

    /* renamed from: a */
    public final ieh mo7466a(ieh ieh) {
        ieh a = ife.m12817a(ibv.m12658a(this.f12815g, hmq.m11744a((icf) new hhy(this, ieh)), (Executor) this.f12810b));
        this.f12812d.mo6881a(a).mo53a(new hhz(a), this.f12810b);
        return ieh;
    }

    /* renamed from: a */
    public final ieh mo7445a(hgz hgz) {
        if (((hhf) ((iqk) this.f12814f.get(hgz)).mo2097a()).mo7443a()) {
            return mo7474b();
        }
        return ibv.m12657a(m11546d(), hmq.m11742a((hpr) new hhx(this, hgz)), (Executor) idh.f13918a);
    }

    /* renamed from: b */
    public final void mo7476b(hhf hhf) {
        hhf.mo7442a((hhg) this);
    }

    /* renamed from: d */
    private final ieh m11546d() {
        iev f = iev.m12774f();
        if (this.f12819k.compareAndSet((Object) null, f)) {
            f.mo6946a(ibv.m12657a(mo7477c(), hmq.m11742a((hpr) new hib(this)), (Executor) this.f12810b));
        }
        return ife.m12817a((ieh) this.f12819k.get());
    }

    /* renamed from: b */
    public final ieh mo7475b(ieh ieh) {
        return ibv.m12658a(m11546d(), (icf) new hia(ieh), (Executor) idh.f13918a);
    }
}
