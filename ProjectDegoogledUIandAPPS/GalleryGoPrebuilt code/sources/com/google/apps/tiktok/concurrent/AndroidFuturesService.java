package com.google.apps.tiktok.concurrent;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;

/* compiled from: PG */
public class AndroidFuturesService extends Service {

    /* renamed from: a */
    private ieh f5305a = ife.m12820a((Object) null);

    public final IBinder onBind(Intent intent) {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (Map.Entry value : ((goq) iol.m14235b(getApplicationContext(), goq.class)).mo2325cy().f11762d.entrySet()) {
            printWriter.println((String) value.getValue());
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        ieh ieh;
        if ((i & 2) == 0 && intent != null) {
            gor cy = ((goq) iol.m14235b(getApplicationContext(), goq.class)).mo2325cy();
            ife.m12849a(intent.hasExtra("EXTRA_FUTURE_INDEX"), "Intent missing extra %s", (Object) intent);
            ife.m12849a(intent.hasExtra("EXTRA_PROCESS_UUID"), "Intent missing extra %s", (Object) intent);
            ife.m12849a(intent.hasExtra("EXTRA_PROCESS_UUID2"), "Intent missing extra %s", (Object) intent);
            long longExtra = intent.getLongExtra("EXTRA_PROCESS_UUID", -1);
            long longExtra2 = intent.getLongExtra("EXTRA_PROCESS_UUID2", -1);
            if (cy.f11765g.getMostSignificantBits() == longExtra && cy.f11765g.getLeastSignificantBits() == longExtra2) {
                int intExtra = intent.getIntExtra("EXTRA_FUTURE_INDEX", -1);
                synchronized (cy.f11761c) {
                    ieh = (iev) ife.m12898e((Object) (iev) cy.f11763e.get(intExtra));
                    if (ieh != gor.f11760b) {
                        cy.f11764f.put(intExtra, ieh);
                    }
                    cy.f11763e.remove(intExtra);
                }
            } else {
                ((hvv) ((hvv) gor.f11759a.mo8180b()).mo8201a("com/google/apps/tiktok/concurrent/AndroidFuturesServiceCounter", "onStartCommand", 218, "AndroidFuturesServiceCounter.java")).mo8205a("Stopping service immediately, intent delivered from previous process. Old PID was %d but current PID is %d", intent.getIntExtra("EXTRA_PROCESS_PID", -1), Process.myPid());
                ieh = ife.m12820a((Object) null);
            }
            this.f5305a = ieh;
        }
        this.f5305a.mo53a(new gop(this, i2), idh.f13918a);
        return 2;
    }
}
