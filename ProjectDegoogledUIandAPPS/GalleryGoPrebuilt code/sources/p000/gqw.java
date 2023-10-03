package p000;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.apps.tiktok.concurrent.InternalForegroundService;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: gqw */
/* compiled from: PG */
public final class gqw {

    /* renamed from: a */
    public final Runnable f11855a;

    /* renamed from: b */
    public final Executor f11856b;

    /* renamed from: c */
    public final got f11857c;

    /* renamed from: d */
    public final Object f11858d = new Object();

    /* renamed from: e */
    public final Map f11859e = new IdentityHashMap(10);

    /* renamed from: f */
    public InternalForegroundService f11860f;

    /* renamed from: g */
    public gqv f11861g;

    /* renamed from: h */
    public int f11862h;

    /* renamed from: i */
    public gqy f11863i;

    /* renamed from: j */
    private final Context f11864j;

    public gqw(Context context, got got, Executor executor) {
        this.f11864j = context;
        this.f11857c = got;
        this.f11856b = ife.m12837a(executor);
        this.f11861g = gqv.STOPPED;
        this.f11855a = new gqs(this, executor);
    }

    /* renamed from: b */
    public final gqy mo6942b(gqy gqy) {
        ife.m12876b(!this.f11859e.isEmpty(), (Object) "Can't select a best notification if thare are none");
        for (gqy gqy2 : this.f11859e.values()) {
            if (gqy == null || gqy.f11866b < gqy2.f11866b) {
                gqy = gqy2;
            }
        }
        return gqy;
    }

    /* renamed from: a */
    public final void mo6940a(Notification notification) {
        ife.m12896d(this.f11861g == gqv.STOPPED);
        Intent intent = new Intent(this.f11864j, InternalForegroundService.class);
        intent.putExtra("fallback_notification", notification);
        this.f11861g = gqv.STARTING;
        int i = Build.VERSION.SDK_INT;
        this.f11864j.startForegroundService(intent);
    }

    /* renamed from: a */
    public final void mo6939a() {
        ife.m12878b(this.f11861g == gqv.STARTED, "Destroyed in wrong state %s", (Object) this.f11861g);
        this.f11861g = gqv.STOPPED;
        this.f11860f.stopForeground(true);
        this.f11863i = null;
        this.f11860f.stopSelf(this.f11862h);
        this.f11860f = null;
    }

    /* renamed from: a */
    public final void mo6941a(gqy gqy) {
        gqy gqy2 = this.f11863i;
        gqy b = mo6942b(gqy);
        this.f11863i = b;
        if (gqy2 != b) {
            this.f11860f.startForeground(174344743, b.f11865a);
        }
    }
}
