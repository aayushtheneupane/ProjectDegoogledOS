package p000;

import android.app.Notification;
import android.os.Build;
import androidx.work.impl.foreground.SystemForegroundService;

/* renamed from: aki */
/* compiled from: PG */
public final class aki implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ int f684a;

    /* renamed from: b */
    private final /* synthetic */ Notification f685b;

    /* renamed from: c */
    private final /* synthetic */ int f686c;

    /* renamed from: d */
    private final /* synthetic */ SystemForegroundService f687d;

    public aki(SystemForegroundService systemForegroundService, int i, Notification notification, int i2) {
        this.f687d = systemForegroundService;
        this.f684a = i;
        this.f685b = notification;
        this.f686c = i2;
    }

    public final void run() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.f687d.startForeground(this.f684a, this.f685b, this.f686c);
        } else {
            this.f687d.startForeground(this.f684a, this.f685b);
        }
    }
}
