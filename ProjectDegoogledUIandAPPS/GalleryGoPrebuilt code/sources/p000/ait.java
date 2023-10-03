package p000;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* renamed from: ait */
/* compiled from: PG */
public final class ait implements Runnable {

    /* renamed from: a */
    public static final String f573a = iol.m14236b("WorkerWrapper");

    /* renamed from: b */
    public alg f574b;

    /* renamed from: c */
    public ListenableWorker f575c;

    /* renamed from: d */
    public ieh f576d = null;

    /* renamed from: e */
    public volatile boolean f577e;

    /* renamed from: f */
    public final amx f578f = amx.m782a();

    /* renamed from: g */
    public ihg f579g = ihg.m13047b();

    /* renamed from: h */
    private final Context f580h;

    /* renamed from: i */
    private final String f581i;

    /* renamed from: j */
    private final List f582j;

    /* renamed from: k */
    private final agz f583k;

    /* renamed from: l */
    private final amz f584l;

    /* renamed from: m */
    private final WorkDatabase f585m;

    /* renamed from: n */
    private final alh f586n;

    /* renamed from: o */
    private final akk f587o;

    /* renamed from: p */
    private final alt f588p;

    /* renamed from: q */
    private List f589q;

    /* renamed from: r */
    private String f590r;

    /* renamed from: s */
    private final ckx f591s;

    public ait(ais ais) {
        this.f580h = ais.f566a;
        this.f584l = ais.f567b;
        this.f581i = ais.f570e;
        this.f582j = ais.f571f;
        this.f591s = ais.f572g;
        this.f575c = null;
        this.f583k = ais.f568c;
        WorkDatabase workDatabase = ais.f569d;
        this.f585m = workDatabase;
        this.f586n = workDatabase.mo1226j();
        this.f587o = this.f585m.mo1227k();
        this.f588p = this.f585m.mo1228l();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo528a() {
        boolean z = false;
        if (!mo529b()) {
            this.f585m.mo2845e();
            try {
                int f = this.f586n.mo617f(this.f581i);
                this.f585m.mo1231o().mo591a(this.f581i);
                if (f == 0) {
                    m557a(false);
                    z = true;
                } else if (f == 2) {
                    ihg ihg = this.f579g;
                    if (ihg instanceof ahj) {
                        iol.m14231a();
                        String.format("Worker result SUCCESS for %s", new Object[]{this.f590r});
                        if (!this.f574b.mo595a()) {
                            this.f585m.mo2845e();
                            this.f586n.mo603a(3, this.f581i);
                            this.f586n.mo607a(this.f581i, ((ahj) this.f579g).f492a);
                            long currentTimeMillis = System.currentTimeMillis();
                            for (String str : this.f587o.mo577b(this.f581i)) {
                                if (this.f586n.mo617f(str) == 5 && this.f587o.mo576a(str)) {
                                    iol.m14231a();
                                    String.format("Setting status to enqueued for %s", new Object[]{str});
                                    this.f586n.mo603a(1, str);
                                    this.f586n.mo606a(str, currentTimeMillis);
                                }
                            }
                            this.f585m.mo2847g();
                            this.f585m.mo2846f();
                            m557a(false);
                        } else {
                            m561f();
                        }
                    } else if (!(ihg instanceof ahi)) {
                        iol.m14231a();
                        String.format("Worker result FAILURE for %s", new Object[]{this.f590r});
                        if (!this.f574b.mo595a()) {
                            m559d();
                        } else {
                            m561f();
                        }
                    } else {
                        iol.m14231a();
                        String.format("Worker result RETRY for %s", new Object[]{this.f590r});
                        m560e();
                    }
                    z = gbz.m9998e(this.f586n.mo617f(this.f581i));
                } else if (!gbz.m9998e(f)) {
                    m560e();
                }
                this.f585m.mo2847g();
                this.f585m.mo2846f();
            } catch (Throwable th) {
                this.f585m.mo2846f();
                throw th;
            }
        }
        List<aia> list = this.f582j;
        if (list != null) {
            if (z) {
                for (aia a : list) {
                    a.mo515a(this.f581i);
                }
            }
            aib.m533a(this.f585m, this.f582j);
        }
    }

    /* renamed from: e */
    private final void m560e() {
        this.f585m.mo2845e();
        try {
            this.f586n.mo603a(1, this.f581i);
            this.f586n.mo606a(this.f581i, System.currentTimeMillis());
            this.f586n.mo610b(this.f581i, -1);
            this.f585m.mo2847g();
        } finally {
            this.f585m.mo2846f();
            m557a(true);
        }
    }

    /* renamed from: f */
    private final void m561f() {
        this.f585m.mo2845e();
        try {
            this.f586n.mo606a(this.f581i, System.currentTimeMillis());
            this.f586n.mo603a(1, this.f581i);
            this.f586n.mo619h(this.f581i);
            this.f586n.mo610b(this.f581i, -1);
            this.f585m.mo2847g();
        } finally {
            this.f585m.mo2846f();
            m557a(false);
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    private final void m557a(boolean z) {
        this.f585m.mo2845e();
        try {
            if (this.f585m.mo1226j().mo602a().isEmpty()) {
                amc.m760a(this.f580h, RescheduleReceiver.class, false);
            }
            this.f585m.mo2847g();
            this.f585m.mo2846f();
            this.f578f.mo659b((Object) Boolean.valueOf(z));
        } catch (Throwable th) {
            this.f585m.mo2846f();
            throw th;
        }
    }

    /* renamed from: c */
    private final void m558c() {
        int f = this.f586n.mo617f(this.f581i);
        if (f == 2) {
            iol.m14231a();
            String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", new Object[]{this.f581i});
            m557a(true);
            return;
        }
        iol.m14231a();
        String.format("Status for %s is %s; not doing any work", new Object[]{this.f581i, gbz.m9996c(f)});
        m557a(false);
    }

    /* JADX INFO: finally extract failed */
    public final void run() {
        ahf ahf;
        WorkDatabase workDatabase;
        List<String> a = this.f588p.mo620a(this.f581i);
        this.f589q = a;
        StringBuilder sb = new StringBuilder("Work [ id=");
        sb.append(this.f581i);
        sb.append(", tags={ ");
        boolean z = true;
        boolean z2 = true;
        for (String str : a) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append(str);
            z2 = false;
        }
        sb.append(" } ]");
        this.f590r = sb.toString();
        if (!mo529b()) {
            this.f585m.mo2845e();
            try {
                alg b = this.f586n.mo608b(this.f581i);
                this.f574b = b;
                if (b == null) {
                    iol.m14231a();
                    iol.m14234a(f573a, String.format("Didn't find WorkSpec for id %s", new Object[]{this.f581i}), new Throwable[0]);
                    m557a(false);
                    workDatabase = this.f585m;
                } else if (b.f728q != 1) {
                    m558c();
                    this.f585m.mo2847g();
                    iol.m14231a();
                    String.format("%s is not in ENQUEUED state. Nothing more to do.", new Object[]{this.f574b.f714c});
                    workDatabase = this.f585m;
                } else {
                    if (b.mo595a() || this.f574b.mo596b()) {
                        long currentTimeMillis = System.currentTimeMillis();
                        alg alg = this.f574b;
                        if (alg.f724m != 0) {
                            if (currentTimeMillis < alg.mo597c()) {
                                iol.m14231a();
                                String.format("Delaying execution for %s because it is being executed before schedule.", new Object[]{this.f574b.f714c});
                                m557a(true);
                                workDatabase = this.f585m;
                            }
                        }
                    }
                    this.f585m.mo2847g();
                    this.f585m.mo2846f();
                    if (this.f574b.mo595a()) {
                        ahf = this.f574b.f716e;
                    } else {
                        ahg a2 = ahg.m491a(this.f574b.f715d);
                        if (a2 != null) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(this.f574b.f716e);
                            arrayList.addAll(this.f586n.mo613d(this.f581i));
                            ahf = a2.mo474a((List) arrayList);
                        } else {
                            iol.m14231a();
                            iol.m14234a(f573a, String.format("Could not create Input Merger %s", new Object[]{this.f574b.f715d}), new Throwable[0]);
                            m559d();
                            return;
                        }
                    }
                    UUID fromString = UUID.fromString(this.f581i);
                    List list = this.f589q;
                    int i = this.f574b.f722k;
                    agz agz = this.f583k;
                    Executor executor = agz.f463a;
                    ahv ahv = agz.f465c;
                    int i2 = amj.f770a;
                    WorkerParameters workerParameters = new WorkerParameters(fromString, ahf, list, executor, ahv);
                    if (this.f575c == null) {
                        this.f575c = this.f583k.f465c.mo502b(this.f580h, this.f574b.f714c, workerParameters);
                    }
                    ListenableWorker listenableWorker = this.f575c;
                    if (listenableWorker == null) {
                        iol.m14231a();
                        iol.m14234a(f573a, String.format("Could not create Worker %s", new Object[]{this.f574b.f714c}), new Throwable[0]);
                        m559d();
                        return;
                    } else if (listenableWorker.f1162c) {
                        iol.m14231a();
                        iol.m14234a(f573a, String.format("Received an already-used Worker %s; WorkerFactory should return new instances", new Object[]{this.f574b.f714c}), new Throwable[0]);
                        m559d();
                        return;
                    } else {
                        listenableWorker.f1162c = true;
                        this.f585m.mo2845e();
                        try {
                            if (this.f586n.mo617f(this.f581i) == 1) {
                                this.f586n.mo603a(2, this.f581i);
                                this.f586n.mo618g(this.f581i);
                            } else {
                                z = false;
                            }
                            this.f585m.mo2847g();
                            if (!z) {
                                m558c();
                                return;
                            } else if (!mo529b()) {
                                amx a3 = amx.m782a();
                                ((anb) this.f584l).f811c.execute(new aiq(this, a3));
                                a3.mo53a(new air(this, a3, this.f590r), ((anb) this.f584l).f809a);
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            this.f585m.mo2846f();
                        }
                    }
                }
                workDatabase.mo2846f();
            } catch (Throwable th) {
                this.f585m.mo2846f();
                throw th;
            }
        }
    }

    /* renamed from: d */
    private final void m559d() {
        this.f585m.mo2845e();
        try {
            String str = this.f581i;
            LinkedList linkedList = new LinkedList();
            linkedList.add(str);
            while (!linkedList.isEmpty()) {
                String str2 = (String) linkedList.remove();
                if (this.f586n.mo617f(str2) != 6) {
                    this.f586n.mo603a(4, str2);
                }
                linkedList.addAll(this.f587o.mo577b(str2));
            }
            this.f586n.mo607a(this.f581i, ((ahh) this.f579g).f491a);
            this.f585m.mo2847g();
        } finally {
            this.f585m.mo2846f();
            m557a(false);
        }
    }

    /* renamed from: b */
    public final boolean mo529b() {
        if (!this.f577e) {
            return false;
        }
        iol.m14231a();
        String.format("Work interrupted for %s", new Object[]{this.f590r});
        int f = this.f586n.mo617f(this.f581i);
        if (f == 0) {
            m557a(false);
        } else {
            m557a(!gbz.m9998e(f));
        }
        return true;
    }
}
