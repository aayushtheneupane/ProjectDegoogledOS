package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.p016v4.media.session.MediaSessionCompat$Token;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.K */
class C0490K {
    final Messenger mCallbacks;

    C0490K(Messenger messenger) {
        this.mCallbacks = messenger;
    }

    /* renamed from: b */
    private void m509b(int i, Bundle bundle) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.arg1 = 2;
        obtain.setData(bundle);
        this.mCallbacks.send(obtain);
    }

    /* renamed from: a */
    public void mo4555a(String str, MediaSessionCompat$Token mediaSessionCompat$Token, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("extra_service_version", 2);
        Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", str);
        bundle2.putParcelable("data_media_session_token", mediaSessionCompat$Token);
        bundle2.putBundle("data_root_hints", bundle);
        m509b(1, bundle2);
    }

    public IBinder asBinder() {
        return this.mCallbacks.getBinder();
    }

    public void onConnectFailed() {
        m509b(2, (Bundle) null);
    }

    /* renamed from: a */
    public void mo4556a(String str, List list, Bundle bundle, Bundle bundle2) {
        Bundle bundle3 = new Bundle();
        bundle3.putString("data_media_item_id", str);
        bundle3.putBundle("data_options", bundle);
        bundle3.putBundle("data_notify_children_changed_options", bundle2);
        if (list != null) {
            bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList(list));
        }
        m509b(3, bundle3);
    }
}
