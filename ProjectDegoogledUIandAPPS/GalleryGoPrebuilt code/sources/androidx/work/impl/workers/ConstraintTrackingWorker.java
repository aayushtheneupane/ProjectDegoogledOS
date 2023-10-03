package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import java.util.List;

/* compiled from: PG */
public class ConstraintTrackingWorker extends ListenableWorker implements ajk {

    /* renamed from: d */
    public static final String f1188d = iol.m14236b("ConstraintTrkngWrkr");

    /* renamed from: e */
    public WorkerParameters f1189e;

    /* renamed from: f */
    public final Object f1190f = new Object();

    /* renamed from: g */
    public volatile boolean f1191g = false;

    /* renamed from: h */
    public ListenableWorker f1192h;

    /* renamed from: i */
    public amx f1193i = amx.m782a();

    /* renamed from: a */
    public final void mo531a(List list) {
    }

    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.f1189e = workerParameters;
    }

    /* renamed from: b */
    public final void mo532b(List list) {
        iol.m14231a();
        String.format("Constraints changed for %s", new Object[]{list});
        synchronized (this.f1190f) {
            this.f1191g = true;
        }
    }

    /* renamed from: e */
    public final void mo1223e() {
        ListenableWorker listenableWorker = this.f1192h;
        if (listenableWorker != null) {
            listenableWorker.mo1222d();
        }
    }

    /* renamed from: g */
    public final void mo1244g() {
        this.f1193i.mo659b((Object) ihg.m13047b());
    }

    /* renamed from: h */
    public final void mo1245h() {
        this.f1193i.mo659b((Object) new ahi());
    }

    /* renamed from: c */
    public final ieh mo1221c() {
        mo1224f().execute(new anc(this));
        return this.f1193i;
    }
}
