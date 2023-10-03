package p000;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

/* renamed from: ahv */
/* compiled from: PG */
public abstract class ahv {

    /* renamed from: a */
    private static final String f503a = iol.m14236b("WorkerFactory");

    /* renamed from: a */
    public abstract ListenableWorker mo501a(Context context, String str, WorkerParameters workerParameters);

    /* renamed from: b */
    public final ListenableWorker mo502b(Context context, String str, WorkerParameters workerParameters) {
        Class<? extends U> cls;
        ListenableWorker a = mo501a(context, str, workerParameters);
        if (a == null) {
            try {
                cls = Class.forName(str).asSubclass(ListenableWorker.class);
            } catch (ClassNotFoundException e) {
                iol.m14231a();
                String str2 = f503a;
                iol.m14234a(str2, "Class not found: " + str, new Throwable[0]);
                cls = null;
            }
            if (cls != null) {
                try {
                    a = (ListenableWorker) cls.getDeclaredConstructor(new Class[]{Context.class, WorkerParameters.class}).newInstance(new Object[]{context, workerParameters});
                } catch (Exception e2) {
                    iol.m14231a();
                    String str3 = f503a;
                    iol.m14234a(str3, "Could not instantiate " + str, e2);
                }
            }
        }
        if (a == null || !a.f1162c) {
            return a;
        }
        throw new IllegalStateException(String.format("WorkerFactory (%s) returned an instance of a ListenableWorker (%s) which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.", new Object[]{getClass().getName(), str}));
    }

    /* renamed from: a */
    public static ahv m511a() {
        return new ahu();
    }
}
