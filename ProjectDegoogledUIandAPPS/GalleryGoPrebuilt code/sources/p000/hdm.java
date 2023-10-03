package p000;

import android.content.Context;
import android.content.Intent;
import com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver_Receiver;
import java.util.Map;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: hdm */
/* compiled from: PG */
public final class hdm implements gth {

    /* renamed from: a */
    public final Context f12535a;

    /* renamed from: b */
    public final inw f12536b;

    /* renamed from: c */
    public final iqk f12537c;

    /* renamed from: d */
    public final inw f12538d;

    /* renamed from: e */
    public final ext f12539e;

    /* renamed from: f */
    public final hdh f12540f;

    /* renamed from: g */
    public final iek f12541g;

    /* renamed from: h */
    public final ConcurrentHashMap f12542h = new ConcurrentHashMap(16, 0.75f, 2);

    public hdm(Context context, int i, String str, inw inw, inw inw2, ext ext, hdh hdh, iek iek) {
        this.f12535a = context;
        this.f12536b = inw;
        this.f12538d = inw2;
        this.f12539e = ext;
        this.f12540f = hdh;
        this.f12541g = iek;
        this.f12537c = new hdi(context, i, str, inw2);
    }

    /* renamed from: a */
    public final void mo7034a() {
        for (Map.Entry entry : this.f12542h.entrySet()) {
            if (((ieh) entry.getKey()).cancel(true)) {
                hdd hdd = (hdd) entry.getValue();
                Intent intent = new Intent(this.f12535a, ClientLoggingReceiver_Receiver.class);
                intent.putExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.logData", hdd.mo7282a().mo8512ag());
                intent.putExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.eventCode", hdd.mo7283b());
                intent.putExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.account", hdd.mo7284c());
                intent.putExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.logSource", "CLIENT_LOGGING_PROD");
                this.f12535a.sendBroadcast(intent);
            }
        }
    }
}
