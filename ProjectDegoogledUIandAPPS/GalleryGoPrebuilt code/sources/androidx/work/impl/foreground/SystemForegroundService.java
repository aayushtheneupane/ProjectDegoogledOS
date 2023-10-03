package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.UUID;

/* compiled from: PG */
public class SystemForegroundService extends C0004ad implements akg {

    /* renamed from: a */
    private akh f1185a;

    /* renamed from: b */
    private Handler f1186b;

    /* renamed from: c */
    private boolean f1187c;

    static {
        iol.m14236b("SystemFgService");
    }

    /* renamed from: b */
    private final void m1136b() {
        this.f1186b = new Handler(Looper.getMainLooper());
        akh akh = new akh(getApplicationContext());
        this.f1185a = akh;
        if (akh.f682h != null) {
            iol.m14231a();
            iol.m14234a(akh.f675a, "A callback already exists.", new Throwable[0]);
            return;
        }
        akh.f682h = this;
    }

    /* renamed from: a */
    public final void mo572a(int i, int i2, Notification notification) {
        this.f1186b.post(new aki(this, i, notification, i2));
    }

    public final void onCreate() {
        super.onCreate();
        m1136b();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.f1185a.mo573a();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        akg akg;
        super.onStartCommand(intent, i, i2);
        if (this.f1187c) {
            iol.m14231a();
            this.f1185a.mo573a();
            m1136b();
            this.f1187c = false;
        }
        if (intent == null) {
            return 3;
        }
        akh akh = this.f1185a;
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            iol.m14231a();
            String.format("Started foreground service %s", new Object[]{intent});
            String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
            akh.f677c.mo668a(new akf(akh, akh.f676b.f554c, stringExtra));
            return 3;
        } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
            iol.m14231a();
            String.format("Stopping foreground service %s", new Object[]{intent});
            akg akg2 = akh.f682h;
            if (akg2 == null) {
                return 3;
            }
            akg2.mo571a();
            return 3;
        } else if ("ACTION_NOTIFY".equals(action)) {
            int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
            int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
            intent.getStringExtra("KEY_NOTIFICATION_TAG");
            Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
            if (notification == null || (akg = akh.f682h) == null) {
                return 3;
            }
            akg.mo572a(intExtra, intExtra2, notification);
            return 3;
        } else if (!"ACTION_CANCEL_WORK".equals(action)) {
            return 3;
        } else {
            iol.m14231a();
            String.format("Stopping foreground work for %s", new Object[]{intent});
            String stringExtra2 = intent.getStringExtra("KEY_WORKSPEC_ID");
            if (stringExtra2 == null || TextUtils.isEmpty(stringExtra2)) {
                return 3;
            }
            aip aip = akh.f676b;
            aip.f555d.mo668a(new alw(aip, UUID.fromString(stringExtra2)));
            return 3;
        }
    }

    /* renamed from: a */
    public final void mo571a() {
        this.f1187c = true;
        iol.m14231a();
        int i = Build.VERSION.SDK_INT;
        stopForeground(true);
        stopSelf();
    }
}
