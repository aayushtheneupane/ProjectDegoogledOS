package android.support.p016v4.media;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.p016v4.media.session.C0107q;
import android.support.p016v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v4.media.a */
class C0071a extends Handler {

    /* renamed from: Sd */
    private final WeakReference f86Sd;

    /* renamed from: Td */
    private WeakReference f87Td;

    C0071a(C0078h hVar) {
        this.f86Sd = new WeakReference(hVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo470b(Messenger messenger) {
        this.f87Td = new WeakReference(messenger);
    }

    public void handleMessage(Message message) {
        WeakReference weakReference = this.f87Td;
        if (weakReference != null && weakReference.get() != null && this.f86Sd.get() != null) {
            Bundle data = message.getData();
            C0107q.m130b(data);
            C0078h hVar = (C0078h) this.f86Sd.get();
            Messenger messenger = (Messenger) this.f87Td.get();
            try {
                int i = message.what;
                if (i == 1) {
                    Bundle bundle = data.getBundle("data_root_hints");
                    C0107q.m130b(bundle);
                    hVar.mo482a(messenger, data.getString("data_media_item_id"), (MediaSessionCompat$Token) data.getParcelable("data_media_session_token"), bundle);
                } else if (i == 2) {
                    hVar.mo481a(messenger);
                } else if (i != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle("data_options");
                    C0107q.m130b(bundle2);
                    Bundle bundle3 = data.getBundle("data_notify_children_changed_options");
                    C0107q.m130b(bundle3);
                    hVar.mo483a(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    hVar.mo481a(messenger);
                }
            }
        }
    }
}
