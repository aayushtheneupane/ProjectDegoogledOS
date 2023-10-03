package androidx.media;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.service.media.MediaBrowserService;
import android.support.p016v4.media.session.C0095e;
import android.support.p016v4.media.session.MediaSessionCompat$Token;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.q */
class C0510q implements C0507n {
    Messenger mMessenger;
    final /* synthetic */ MediaBrowserServiceCompat this$0;

    /* renamed from: xq */
    final List f491xq = new ArrayList();

    /* renamed from: yq */
    MediaBrowserService f492yq;

    C0510q(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.this$0 = mediaBrowserServiceCompat;
    }

    /* renamed from: a */
    public void mo4597a(String str, C0520z zVar) {
        C0508o oVar = new C0508o(this, str, zVar);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = this.this$0;
        C0506m mVar = mediaBrowserServiceCompat.f476Mb;
        mediaBrowserServiceCompat.mo4569a(str, (C0519y) oVar);
    }

    public IBinder onBind(Intent intent) {
        return this.f492yq.onBind(intent);
    }

    public C0504k onGetRoot(String str, int i, Bundle bundle) {
        int i2;
        Bundle bundle2;
        IBinder iBinder;
        if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
            i2 = -1;
            bundle2 = null;
        } else {
            bundle.remove("extra_client_version");
            this.mMessenger = new Messenger(this.this$0.mHandler);
            bundle2 = new Bundle();
            bundle2.putInt("extra_service_version", 2);
            IBinder binder = this.mMessenger.getBinder();
            int i3 = Build.VERSION.SDK_INT;
            bundle2.putBinder("extra_messenger", binder);
            MediaSessionCompat$Token mediaSessionCompat$Token = this.this$0.mSession;
            if (mediaSessionCompat$Token != null) {
                C0095e Ab = mediaSessionCompat$Token.mo526Ab();
                if (Ab == null) {
                    iBinder = null;
                } else {
                    iBinder = Ab.asBinder();
                }
                int i4 = Build.VERSION.SDK_INT;
                bundle2.putBinder("extra_session_binder", iBinder);
            } else {
                this.f491xq.add(bundle2);
            }
            int i5 = bundle.getInt("extra_calling_pid", -1);
            bundle.remove("extra_calling_pid");
            i2 = i5;
        }
        C0506m mVar = new C0506m(this.this$0, str, i2, i, bundle, (C0490K) null);
        C0504k onGetRoot = this.this$0.onGetRoot(str, i, bundle);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = this.this$0;
        if (onGetRoot == null) {
            return null;
        }
        if (this.mMessenger != null) {
            mediaBrowserServiceCompat.f477Nb.add(mVar);
        }
        if (bundle2 == null) {
            bundle2 = onGetRoot.getExtras();
        } else if (onGetRoot.getExtras() != null) {
            bundle2.putAll(onGetRoot.getExtras());
        }
        return new C0504k(onGetRoot.getRootId(), bundle2);
    }
}
