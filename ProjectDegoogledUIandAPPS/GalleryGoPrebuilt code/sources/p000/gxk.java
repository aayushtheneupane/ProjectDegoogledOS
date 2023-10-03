package p000;

import android.app.Application;
import java.lang.reflect.Field;

/* renamed from: gxk */
/* compiled from: PG */
final /* synthetic */ class gxk implements Runnable {

    /* renamed from: a */
    private final Application f12250a;

    /* renamed from: b */
    private final gxn f12251b;

    public gxk(Application application, gxn gxn) {
        this.f12250a = application;
        this.f12251b = gxn;
    }

    public final void run() {
        Application application = this.f12250a;
        gxn gxn = this.f12251b;
        Field field = gxo.f12261a;
        application.unregisterActivityLifecycleCallbacks(gxn);
    }
}
