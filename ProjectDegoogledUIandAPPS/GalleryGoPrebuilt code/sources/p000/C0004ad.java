package p000;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* renamed from: ad */
/* compiled from: PG */
public class C0004ad extends Service implements C0681z {

    /* renamed from: a */
    private final C0016ap f197a = new C0016ap(this);

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f197a.f1311a;
    }

    public final IBinder onBind(Intent intent) {
        this.f197a.mo1401a(C0546u.ON_START);
        return null;
    }

    public void onCreate() {
        this.f197a.mo1401a(C0546u.ON_CREATE);
        super.onCreate();
    }

    public void onDestroy() {
        C0016ap apVar = this.f197a;
        apVar.mo1401a(C0546u.ON_STOP);
        apVar.mo1401a(C0546u.ON_DESTROY);
        super.onDestroy();
    }

    public final void onStart(Intent intent, int i) {
        this.f197a.mo1401a(C0546u.ON_START);
        super.onStart(intent, i);
    }
}
