package androidx.media.session;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.p016v4.media.C0073c;
import android.support.p016v4.media.C0085o;
import android.support.p016v4.media.session.C0103m;
import android.util.Log;
import android.view.KeyEvent;

/* renamed from: androidx.media.session.a */
class C0513a extends C0073c {

    /* renamed from: ce */
    private final BroadcastReceiver.PendingResult f494ce;

    /* renamed from: de */
    private C0085o f495de;
    private final Context mContext;
    private final Intent mIntent;

    C0513a(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        this.mContext = context;
        this.mIntent = intent;
        this.f494ce = pendingResult;
    }

    private void finish() {
        this.f495de.disconnect();
        this.f494ce.finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4601a(C0085o oVar) {
        this.f495de = oVar;
    }

    public void onConnected() {
        try {
            new C0103m(this.mContext, this.f495de.getSessionToken()).dispatchMediaButtonEvent((KeyEvent) this.mIntent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
        } catch (RemoteException e) {
            Log.e("MediaButtonReceiver", "Failed to create a media controller", e);
        }
        finish();
    }

    public void onConnectionFailed() {
        finish();
    }

    public void onConnectionSuspended() {
        finish();
    }
}
