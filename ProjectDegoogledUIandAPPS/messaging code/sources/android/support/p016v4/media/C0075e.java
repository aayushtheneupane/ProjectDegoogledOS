package android.support.p016v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.p016v4.media.session.C0094d;
import android.support.p016v4.media.session.C0095e;
import android.support.p016v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import androidx.core.app.BundleCompat;
import java.util.List;
import p000a.p005b.C0015b;

/* renamed from: android.support.v4.media.e */
class C0075e implements C0074d, C0078h {

    /* renamed from: ee */
    protected final MediaBrowser f90ee;

    /* renamed from: fe */
    protected final Bundle f91fe;

    /* renamed from: ge */
    private final C0015b f92ge = new C0015b();

    /* renamed from: he */
    protected C0080j f93he;

    /* renamed from: ie */
    protected Messenger f94ie;

    /* renamed from: je */
    private MediaSessionCompat$Token f95je;
    final Context mContext;
    protected final C0071a mHandler = new C0071a(this);

    C0075e(Context context, ComponentName componentName, C0073c cVar, Bundle bundle) {
        Bundle bundle2;
        this.mContext = context;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        this.f91fe = bundle2;
        this.f91fe.putInt("extra_client_version", 1);
        this.f91fe.putInt("extra_calling_pid", Process.myPid());
        cVar.f89be = this;
        this.f90ee = new MediaBrowser(context, componentName, cVar.f88ae, this.f91fe);
    }

    /* renamed from: a */
    public void mo481a(Messenger messenger) {
    }

    /* renamed from: a */
    public void mo482a(Messenger messenger, String str, MediaSessionCompat$Token mediaSessionCompat$Token, Bundle bundle) {
    }

    /* renamed from: a */
    public void mo483a(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
        if (this.f94ie == messenger) {
            C0081k kVar = (C0081k) this.f92ge.get(str);
            if (kVar != null) {
                C0084n a = kVar.mo491a(bundle);
                if (a == null) {
                    return;
                }
                if (bundle == null) {
                    if (list == null) {
                        a.onError(str);
                    } else {
                        a.onChildrenLoaded(str, list);
                    }
                } else if (list == null) {
                    a.onError(str, bundle);
                } else {
                    a.onChildrenLoaded(str, list, bundle);
                }
            } else if (C0085o.DEBUG) {
                Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
            }
        }
    }

    public void connect() {
        this.f90ee.connect();
    }

    public void disconnect() {
        Messenger messenger;
        C0080j jVar = this.f93he;
        if (!(jVar == null || (messenger = this.f94ie) == null)) {
            try {
                jVar.mo490c(messenger);
            } catch (RemoteException unused) {
                Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
            }
        }
        this.f90ee.disconnect();
    }

    public MediaSessionCompat$Token getSessionToken() {
        if (this.f95je == null) {
            this.f95je = MediaSessionCompat$Token.m98a(this.f90ee.getSessionToken(), (C0095e) null);
        }
        return this.f95je;
    }

    public void onConnected() {
        try {
            Bundle extras = this.f90ee.getExtras();
            if (extras != null) {
                extras.getInt("extra_service_version", 0);
                IBinder binder = BundleCompat.getBinder(extras, "extra_messenger");
                if (binder != null) {
                    this.f93he = new C0080j(binder, this.f91fe);
                    this.f94ie = new Messenger(this.mHandler);
                    this.mHandler.mo470b(this.f94ie);
                    try {
                        this.f93he.mo489a(this.mContext, this.f94ie);
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                C0095e asInterface = C0094d.asInterface(BundleCompat.getBinder(extras, "extra_session_binder"));
                if (asInterface != null) {
                    this.f95je = MediaSessionCompat$Token.m98a(this.f90ee.getSessionToken(), asInterface);
                }
            }
        } catch (IllegalStateException e) {
            Log.e("MediaBrowserCompat", "Unexpected IllegalStateException", e);
        }
    }

    public void onConnectionFailed() {
    }

    public void onConnectionSuspended() {
        this.f93he = null;
        this.f94ie = null;
        this.f95je = null;
        this.mHandler.mo470b((Messenger) null);
    }
}
