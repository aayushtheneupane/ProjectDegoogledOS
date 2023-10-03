package p000;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ajf */
/* compiled from: PG */
public final class ajf implements ahw {

    /* renamed from: a */
    public static final String f624a = iol.m14236b("SystemAlarmDispatcher");

    /* renamed from: b */
    public final Context f625b;

    /* renamed from: c */
    public final amz f626c;

    /* renamed from: d */
    public final amn f627d = new amn();

    /* renamed from: e */
    public final ahz f628e;

    /* renamed from: f */
    public final aip f629f;

    /* renamed from: g */
    public final aiw f630g = new aiw(this.f625b);

    /* renamed from: h */
    public final List f631h;

    /* renamed from: i */
    public Intent f632i;

    /* renamed from: j */
    public ajd f633j;

    /* renamed from: k */
    private final Handler f634k;

    public ajf(Context context) {
        this.f625b = context.getApplicationContext();
        aip a = aip.m549a(context);
        this.f629f = a;
        this.f628e = a.f557f;
        this.f626c = a.f555d;
        this.f628e.mo506a(this);
        this.f631h = new ArrayList();
        this.f632i = null;
        this.f634k = new Handler(Looper.getMainLooper());
    }

    /* renamed from: a */
    public final void mo543a(Intent intent, int i) {
        iol.m14231a();
        String.format("Adding command %s (%s)", new Object[]{intent, Integer.valueOf(i)});
        mo546c();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            iol.m14231a();
            Log.w(f624a, "Unknown command. Ignoring");
            return;
        }
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            mo546c();
            synchronized (this.f631h) {
                for (Intent action2 : this.f631h) {
                    if ("ACTION_CONSTRAINTS_CHANGED".equals(action2.getAction())) {
                        return;
                    }
                }
            }
        }
        intent.putExtra("KEY_START_ID", i);
        synchronized (this.f631h) {
            boolean z = !this.f631h.isEmpty();
            this.f631h.add(intent);
            if (!z) {
                mo545b();
            }
        }
    }

    /* renamed from: c */
    public final void mo546c() {
        if (this.f634k.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    /* renamed from: a */
    public final void mo542a() {
        iol.m14231a();
        this.f628e.mo508b(this);
        amn amn = this.f627d;
        if (!amn.f774a.isShutdown()) {
            amn.f774a.shutdownNow();
        }
        this.f633j = null;
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        mo544a(new ajc(this, aiw.m577a(this.f625b, str, z), 0));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo544a(Runnable runnable) {
        this.f634k.post(runnable);
    }

    /* renamed from: b */
    public final void mo545b() {
        mo546c();
        PowerManager.WakeLock a = ami.m762a(this.f625b, "ProcessCommand");
        try {
            a.acquire();
            this.f629f.f555d.mo668a(new ajb(this));
        } finally {
            a.release();
        }
    }
}
