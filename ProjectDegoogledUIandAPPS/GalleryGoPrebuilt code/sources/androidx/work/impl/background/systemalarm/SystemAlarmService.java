package androidx.work.impl.background.systemalarm;

import android.content.Intent;

/* compiled from: PG */
public class SystemAlarmService extends C0004ad implements ajd {

    /* renamed from: a */
    private ajf f1180a;

    /* renamed from: b */
    private boolean f1181b;

    static {
        iol.m14236b("SystemAlarmService");
    }

    /* renamed from: b */
    private final void m1132b() {
        ajf ajf = new ajf(this);
        this.f1180a = ajf;
        if (ajf.f633j != null) {
            iol.m14231a();
            iol.m14234a(ajf.f624a, "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
            return;
        }
        ajf.f633j = this;
    }

    /* renamed from: a */
    public final void mo540a() {
        this.f1181b = true;
        iol.m14231a();
        ami.m763a();
        stopSelf();
    }

    public final void onCreate() {
        super.onCreate();
        m1132b();
        this.f1181b = false;
    }

    public final void onDestroy() {
        super.onDestroy();
        this.f1181b = true;
        this.f1180a.mo542a();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.f1181b) {
            iol.m14231a();
            this.f1180a.mo542a();
            m1132b();
            this.f1181b = false;
        }
        if (intent == null) {
            return 3;
        }
        this.f1180a.mo543a(intent, i2);
        return 3;
    }
}
