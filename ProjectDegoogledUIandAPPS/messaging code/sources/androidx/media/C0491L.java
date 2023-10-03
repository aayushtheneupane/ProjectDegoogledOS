package androidx.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.p016v4.media.C0085o;
import android.support.p016v4.media.session.C0107q;
import android.support.p016v4.p017os.ResultReceiver;
import android.util.Log;
import androidx.core.app.BundleCompat;

/* renamed from: androidx.media.L */
final class C0491L extends Handler {

    /* renamed from: Ud */
    private final C0489J f475Ud = new C0489J(this.this$0);
    final /* synthetic */ MediaBrowserServiceCompat this$0;

    C0491L(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.this$0 = mediaBrowserServiceCompat;
    }

    /* renamed from: b */
    public void mo4559b(Runnable runnable) {
        if (Thread.currentThread() == getLooper().getThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public void handleMessage(Message message) {
        Message message2 = message;
        Bundle data = message.getData();
        switch (message2.what) {
            case 1:
                Bundle bundle = data.getBundle("data_root_hints");
                C0107q.m130b(bundle);
                C0489J j = this.f475Ud;
                String string = data.getString("data_package_name");
                int i = data.getInt("data_calling_pid");
                int i2 = data.getInt("data_calling_uid");
                C0490K k = new C0490K(message2.replyTo);
                if (j.this$0.mo4571a(string, i2)) {
                    j.this$0.mHandler.mo4559b(new C0480A(j, k, string, i, i2, bundle));
                    return;
                }
                throw new IllegalArgumentException("Package/uid mismatch: uid=" + i2 + " package=" + string);
            case 2:
                C0489J j2 = this.f475Ud;
                j2.this$0.mHandler.mo4559b(new C0481B(j2, new C0490K(message2.replyTo)));
                return;
            case 3:
                Bundle bundle2 = data.getBundle("data_options");
                C0107q.m130b(bundle2);
                C0489J j3 = this.f475Ud;
                String string2 = data.getString("data_media_item_id");
                IBinder binder = BundleCompat.getBinder(data, "data_callback_token");
                j3.this$0.mHandler.mo4559b(new C0482C(j3, new C0490K(message2.replyTo), string2, binder, bundle2));
                return;
            case 4:
                C0489J j4 = this.f475Ud;
                String string3 = data.getString("data_media_item_id");
                IBinder binder2 = BundleCompat.getBinder(data, "data_callback_token");
                j4.this$0.mHandler.mo4559b(new C0483D(j4, new C0490K(message2.replyTo), string3, binder2));
                return;
            case 5:
                this.f475Ud.mo4553a(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new C0490K(message2.replyTo));
                return;
            case 6:
                Bundle bundle3 = data.getBundle("data_root_hints");
                C0107q.m130b(bundle3);
                C0489J j5 = this.f475Ud;
                C0490K k2 = new C0490K(message2.replyTo);
                String string4 = data.getString("data_package_name");
                int i3 = data.getInt("data_calling_pid");
                j5.this$0.mHandler.mo4559b(new C0485F(j5, k2, data.getInt("data_calling_uid"), string4, i3, bundle3));
                return;
            case 7:
                C0489J j6 = this.f475Ud;
                j6.this$0.mHandler.mo4559b(new C0486G(j6, new C0490K(message2.replyTo)));
                return;
            case 8:
                Bundle bundle4 = data.getBundle("data_search_extras");
                C0107q.m130b(bundle4);
                this.f475Ud.mo4552a(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new C0490K(message2.replyTo));
                return;
            case 9:
                Bundle bundle5 = data.getBundle("data_custom_action_extras");
                C0107q.m130b(bundle5);
                this.f475Ud.mo4554b(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new C0490K(message2.replyTo));
                return;
            default:
                Log.w("MBServiceCompat", "Unhandled message: " + message2 + "\n  Service version: " + 2 + "\n  Client version: " + message2.arg1);
                return;
        }
    }

    public boolean sendMessageAtTime(Message message, long j) {
        Bundle data = message.getData();
        data.setClassLoader(C0085o.class.getClassLoader());
        data.putInt("data_calling_uid", Binder.getCallingUid());
        int callingPid = Binder.getCallingPid();
        if (callingPid > 0) {
            data.putInt("data_calling_pid", callingPid);
        } else if (!data.containsKey("data_calling_pid")) {
            data.putInt("data_calling_pid", -1);
        }
        return super.sendMessageAtTime(message, j);
    }
}
