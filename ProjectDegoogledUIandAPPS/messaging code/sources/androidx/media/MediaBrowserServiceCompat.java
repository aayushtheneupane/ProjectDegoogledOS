package androidx.media;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.p016v4.media.session.C0107q;
import android.support.p016v4.media.session.MediaSessionCompat$Token;
import android.support.p016v4.p017os.ResultReceiver;
import android.util.Log;
import androidx.core.util.Pair;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p000a.p005b.C0015b;
import p026b.p027a.p030b.p031a.C0632a;

public abstract class MediaBrowserServiceCompat extends Service {
    static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);

    /* renamed from: Mb */
    final C0506m f476Mb = new C0506m(this, "android.media.session.MediaController", -1, -1, (Bundle) null, (C0490K) null);

    /* renamed from: Nb */
    final ArrayList f477Nb = new ArrayList();

    /* renamed from: Ob */
    final C0015b f478Ob = new C0015b();
    final C0491L mHandler = new C0491L(this);
    private C0507n mImpl;
    MediaSessionCompat$Token mSession;

    /* renamed from: a */
    public void mo4563a(String str, Bundle bundle) {
    }

    /* renamed from: a */
    public abstract void mo4569a(String str, C0519y yVar);

    /* renamed from: a */
    public void mo4570a(String str, C0519y yVar, Bundle bundle) {
        yVar.setFlags(1);
        mo4569a(str, yVar);
    }

    /* renamed from: b */
    public void mo4575b(String str, C0519y yVar) {
        yVar.setFlags(2);
        yVar.sendResult((Object) null);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        this.mImpl = new C0518x(this);
        this.mImpl.onCreate();
    }

    public abstract C0504k onGetRoot(String str, int i, Bundle bundle);

    /* renamed from: t */
    public void mo4580t(String str) {
    }

    /* renamed from: a */
    public void mo4565a(String str, Bundle bundle, C0519y yVar) {
        yVar.mo4605e((Bundle) null);
    }

    /* renamed from: b */
    public void mo4574b(String str, Bundle bundle, C0519y yVar) {
        yVar.setFlags(4);
        yVar.sendResult((Object) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo4571a(String str, int i) {
        if (str == null) {
            return false;
        }
        for (String equals : getPackageManager().getPackagesForUid(i)) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4573b(String str, Bundle bundle, C0506m mVar, ResultReceiver resultReceiver) {
        C0502i iVar = new C0502i(this, str, resultReceiver);
        mo4574b(str, bundle, iVar);
        if (!iVar.isDone()) {
            throw new IllegalStateException(C0632a.m1025k("onSearch must call detach() or sendResult() before returning for query=", str));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4567a(String str, C0506m mVar, IBinder iBinder, Bundle bundle) {
        List<Pair> list = (List) mVar.subscriptions.get(str);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (Pair pair : list) {
            if (iBinder == pair.first && C0107q.areSameOptions(bundle, (Bundle) pair.second)) {
                return;
            }
        }
        list.add(new Pair(iBinder, bundle));
        mVar.subscriptions.put(str, list);
        mo4566a(str, mVar, bundle, (Bundle) null);
        mo4563a(str, bundle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo4572a(String str, C0506m mVar, IBinder iBinder) {
        boolean z = true;
        boolean z2 = false;
        if (iBinder == null) {
            try {
                if (mVar.subscriptions.remove(str) == null) {
                    z = false;
                }
                return z;
            } finally {
                mo4580t(str);
            }
        } else {
            List list = (List) mVar.subscriptions.get(str);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == ((Pair) it.next()).first) {
                        it.remove();
                        z2 = true;
                    }
                }
                if (list.size() == 0) {
                    mVar.subscriptions.remove(str);
                }
            }
            mo4580t(str);
            return z2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4566a(String str, C0506m mVar, Bundle bundle, Bundle bundle2) {
        C0500g gVar = new C0500g(this, str, mVar, str, bundle, bundle2);
        if (bundle == null) {
            mo4569a(str, (C0519y) gVar);
        } else {
            mo4570a(str, (C0519y) gVar, bundle);
        }
        if (!gVar.isDone()) {
            StringBuilder Pa = C0632a.m1011Pa("onLoadChildren must call detach() or sendResult() before returning for package=");
            Pa.append(mVar.pkg);
            Pa.append(" id=");
            Pa.append(str);
            throw new IllegalStateException(Pa.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public List mo4562a(List list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i = bundle.getInt("android.media.browse.extra.PAGE", -1);
        int i2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if (i == -1 && i2 == -1) {
            return list;
        }
        int i3 = i2 * i;
        int i4 = i3 + i2;
        if (i < 0 || i2 < 1 || i3 >= list.size()) {
            return Collections.emptyList();
        }
        if (i4 > list.size()) {
            i4 = list.size();
        }
        return list.subList(i3, i4);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4568a(String str, C0506m mVar, ResultReceiver resultReceiver) {
        C0501h hVar = new C0501h(this, str, resultReceiver);
        mo4575b(str, hVar);
        if (!hVar.isDone()) {
            throw new IllegalStateException(C0632a.m1025k("onLoadItem must call detach() or sendResult() before returning for id=", str));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4564a(String str, Bundle bundle, C0506m mVar, ResultReceiver resultReceiver) {
        C0503j jVar = new C0503j(this, str, resultReceiver);
        mo4565a(str, bundle, (C0519y) jVar);
        if (!jVar.isDone()) {
            throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
        }
    }
}
