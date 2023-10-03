package p000;

import android.content.Intent;

/* renamed from: hcv */
/* compiled from: PG */
final /* synthetic */ class hcv implements ice {

    /* renamed from: a */
    private final hde f12492a;

    /* renamed from: b */
    private final Intent f12493b;

    public hcv(hde hde, Intent intent) {
        this.f12492a = hde;
        this.f12493b = intent;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hde hde = this.f12492a;
        Intent intent = this.f12493b;
        return hde.m11232a(intent.getByteArrayExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.logData"), intent.getIntExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.eventCode", -1), intent.getStringExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.account"), hde.f12518a, hde.f12520c, hde.f12519b, hde.f12521d, intent.getStringExtra("com.google.apps.tiktok.logging.backends.clientlogging.ClientLoggingReceiver.logSource"));
    }
}
