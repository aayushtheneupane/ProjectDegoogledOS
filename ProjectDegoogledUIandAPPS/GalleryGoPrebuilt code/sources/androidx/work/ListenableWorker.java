package androidx.work;

import android.content.Context;
import java.util.UUID;
import java.util.concurrent.Executor;

/* compiled from: PG */
public abstract class ListenableWorker {

    /* renamed from: a */
    public Context f1160a;

    /* renamed from: b */
    public WorkerParameters f1161b;

    /* renamed from: c */
    public boolean f1162c;

    /* renamed from: d */
    private volatile boolean f1163d;

    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        } else if (workerParameters != null) {
            this.f1160a = context;
            this.f1161b = workerParameters;
        } else {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
    }

    /* renamed from: a */
    public final UUID mo1219a() {
        return this.f1161b.f1165a;
    }

    /* renamed from: b */
    public final ahf mo1220b() {
        return this.f1161b.f1166b;
    }

    /* renamed from: c */
    public abstract ieh mo1221c();

    /* renamed from: e */
    public void mo1223e() {
    }

    /* renamed from: f */
    public final Executor mo1224f() {
        return this.f1161b.f1168d;
    }

    /* renamed from: d */
    public final void mo1222d() {
        this.f1163d = true;
        mo1223e();
    }
}
