package android.support.p016v4.media;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;

/* renamed from: android.support.v4.media.j */
class C0080j {

    /* renamed from: fe */
    private Bundle f96fe;
    private Messenger mMessenger;

    public C0080j(IBinder iBinder, Bundle bundle) {
        this.mMessenger = new Messenger(iBinder);
        this.f96fe = bundle;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo489a(Context context, Messenger messenger) {
        Bundle bundle = new Bundle();
        bundle.putString("data_package_name", context.getPackageName());
        bundle.putInt("data_calling_pid", Process.myPid());
        bundle.putBundle("data_root_hints", this.f96fe);
        m93a(6, bundle, messenger);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo490c(Messenger messenger) {
        m93a(7, (Bundle) null, messenger);
    }

    /* renamed from: a */
    private void m93a(int i, Bundle bundle, Messenger messenger) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.arg1 = 1;
        obtain.setData(bundle);
        obtain.replyTo = messenger;
        this.mMessenger.send(obtain);
    }
}
