package android.support.p016v4.media.session;

import android.os.Handler;
import android.os.ResultReceiver;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver */
class C0090x50fd9e4a extends ResultReceiver {

    /* renamed from: Wd */
    private WeakReference f102Wd;

    C0090x50fd9e4a(C0101k kVar) {
        super((Handler) null);
        this.f102Wd = new WeakReference(kVar);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:5|6|7|8|9|(1:11)(2:12|(1:14)(2:15|16))|17|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceiveResult(int r4, android.os.Bundle r5) {
        /*
            r3 = this;
            java.lang.ref.WeakReference r3 = r3.f102Wd
            java.lang.Object r3 = r3.get()
            android.support.v4.media.session.k r3 = (android.support.p016v4.media.session.C0101k) r3
            if (r3 == 0) goto L_0x005a
            if (r5 != 0) goto L_0x000d
            goto L_0x005a
        L_0x000d:
            java.lang.Object r4 = r3.mLock
            monitor-enter(r4)
            android.support.v4.media.session.MediaSessionCompat$Token r0 = r3.f117xe     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = "android.support.v4.media.session.EXTRA_BINDER"
            android.os.IBinder r1 = androidx.core.app.BundleCompat.getBinder(r5, r1)     // Catch:{ all -> 0x0057 }
            android.support.v4.media.session.e r1 = android.support.p016v4.media.session.C0094d.asInterface(r1)     // Catch:{ all -> 0x0057 }
            r0.mo527a((android.support.p016v4.media.session.C0095e) r1)     // Catch:{ all -> 0x0057 }
            android.support.v4.media.session.MediaSessionCompat$Token r0 = r3.f117xe     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = "android.support.v4.media.session.SESSION_TOKEN2"
            r2 = 0
            android.os.Parcelable r5 = r5.getParcelable(r1)     // Catch:{ RuntimeException -> 0x004f }
            android.os.Bundle r5 = (android.os.Bundle) r5     // Catch:{ RuntimeException -> 0x004f }
            if (r5 != 0) goto L_0x002d
            goto L_0x004f
        L_0x002d:
            java.lang.Class<androidx.versionedparcelable.b> r1 = androidx.versionedparcelable.C0612b.class
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ RuntimeException -> 0x004f }
            r5.setClassLoader(r1)     // Catch:{ RuntimeException -> 0x004f }
            java.lang.String r1 = "a"
            android.os.Parcelable r5 = r5.getParcelable(r1)     // Catch:{ RuntimeException -> 0x004f }
            boolean r1 = r5 instanceof androidx.versionedparcelable.ParcelImpl     // Catch:{ RuntimeException -> 0x004f }
            if (r1 == 0) goto L_0x0047
            androidx.versionedparcelable.ParcelImpl r5 = (androidx.versionedparcelable.ParcelImpl) r5     // Catch:{ RuntimeException -> 0x004f }
            androidx.versionedparcelable.e r2 = r5.mo5292nd()     // Catch:{ RuntimeException -> 0x004f }
            goto L_0x004f
        L_0x0047:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ RuntimeException -> 0x004f }
            java.lang.String r1 = "Invalid parcel"
            r5.<init>(r1)     // Catch:{ RuntimeException -> 0x004f }
            throw r5     // Catch:{ RuntimeException -> 0x004f }
        L_0x004f:
            r0.mo528a((androidx.versionedparcelable.C0615e) r2)     // Catch:{ all -> 0x0057 }
            r3.mo577zb()     // Catch:{ all -> 0x0057 }
            monitor-exit(r4)     // Catch:{ all -> 0x0057 }
            return
        L_0x0057:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0057 }
            throw r3
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p016v4.media.session.C0090x50fd9e4a.onReceiveResult(int, android.os.Bundle):void");
    }
}
