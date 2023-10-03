package androidx.work;

import android.content.Context;

/* compiled from: PG */
public abstract class Worker extends ListenableWorker {

    /* renamed from: d */
    public amx f1164d;

    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    /* renamed from: g */
    public abstract ihg mo1225g();

    /* renamed from: c */
    public final ieh mo1221c() {
        this.f1164d = amx.m782a();
        mo1224f().execute(new aht(this));
        return this.f1164d;
    }
}
