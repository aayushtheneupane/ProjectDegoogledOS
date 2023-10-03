package p000;

import android.app.Application;
import java.lang.reflect.Field;

/* renamed from: gxl */
/* compiled from: PG */
final /* synthetic */ class gxl implements Runnable {

    /* renamed from: a */
    private final Application f12252a;

    /* renamed from: b */
    private final gxn f12253b;

    public gxl(Application application, gxn gxn) {
        this.f12252a = application;
        this.f12253b = gxn;
    }

    public final void run() {
        Application application = this.f12252a;
        gxn gxn = this.f12253b;
        Field field = gxo.f12261a;
        application.unregisterActivityLifecycleCallbacks(gxn);
    }
}
