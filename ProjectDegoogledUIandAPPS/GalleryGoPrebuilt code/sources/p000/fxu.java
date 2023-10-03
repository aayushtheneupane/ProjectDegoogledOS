package p000;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/* renamed from: fxu */
/* compiled from: PG */
public final class fxu extends fys {

    /* renamed from: a */
    private final fxx f10686a = new fxx();

    /* renamed from: b */
    private final Context f10687b;

    /* renamed from: c */
    private final Object f10688c = new Object();

    /* renamed from: d */
    private String f10689d;

    public /* synthetic */ fxu(fxt fxt) {
        this.f10687b = fxt.f10685a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final fyr mo6320a() {
        return this.f10686a;
    }

    /* renamed from: b */
    public final String mo6322b() {
        return "android";
    }

    /* renamed from: b */
    public final boolean mo6323b(Uri uri) {
        if (!m9852f(uri)) {
            return this.f10686a.mo6323b(mo6324c(uri));
        }
        m9851c();
        throw null;
    }

    /* renamed from: f */
    private final boolean m9852f(Uri uri) {
        return !TextUtils.isEmpty(uri.getAuthority()) && !this.f10687b.getPackageName().equals(uri.getAuthority());
    }

    /* renamed from: a */
    public final InputStream mo6321a(Uri uri) {
        if (!m9852f(uri)) {
            return this.f10686a.mo6321a(mo6324c(uri));
        }
        m9851c();
        throw null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final Uri mo6324c(Uri uri) {
        if (!m9852f(uri)) {
            File d = mo6325d(uri);
            fxw fxw = new fxw((byte[]) null);
            fxw.f10691a.path(d.getAbsolutePath());
            return fxw.f10691a.encodedFragment(fyl.m9877a((List) fxw.f10692b.mo7905a())).build();
        }
        throw new fyb("Operation across authorities is not allowed.");
    }

    /* renamed from: c */
    private static final void m9851c() {
        throw new fya("Android backend cannot perform remote operations without a remote backend");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.File mo6325d(android.net.Uri r11) {
        /*
            r10 = this;
            boolean r0 = r10.m9852f(r11)
            if (r0 != 0) goto L_0x017e
            android.content.Context r0 = r10.f10687b
            fxv r1 = new fxv
            r1.<init>(r0)
            java.lang.String r0 = r11.getScheme()
            java.lang.String r2 = "android"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0176
            java.util.List r0 = r11.getPathSegments()
            boolean r0 = r0.isEmpty()
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x0166
            java.lang.String r0 = r11.getQuery()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x015e
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List r4 = r11.getPathSegments()
            r0.<init>(r4)
            java.lang.Object r4 = r0.get(r2)
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r4.hashCode()
            r6 = 5
            r7 = 4
            r8 = 3
            r9 = 2
            switch(r5) {
                case -1820761141: goto L_0x007d;
                case 94416770: goto L_0x0073;
                case 97434231: goto L_0x0069;
                case 835260319: goto L_0x005f;
                case 988548496: goto L_0x0055;
                case 991565957: goto L_0x004a;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x0087
        L_0x004a:
            java.lang.String r5 = "directboot-files"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0049
            r4 = 0
            goto L_0x0088
        L_0x0055:
            java.lang.String r5 = "directboot-cache"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0049
            r4 = 1
            goto L_0x0088
        L_0x005f:
            java.lang.String r5 = "managed"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0049
            r4 = 4
            goto L_0x0088
        L_0x0069:
            java.lang.String r5 = "files"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0049
            r4 = 2
            goto L_0x0088
        L_0x0073:
            java.lang.String r5 = "cache"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0049
            r4 = 3
            goto L_0x0088
        L_0x007d:
            java.lang.String r5 = "external"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0049
            r4 = 5
            goto L_0x0088
        L_0x0087:
            r4 = -1
        L_0x0088:
            if (r4 == 0) goto L_0x0101
            if (r4 == r3) goto L_0x00f4
            if (r4 == r9) goto L_0x00ed
            if (r4 == r8) goto L_0x00e6
            if (r4 == r7) goto L_0x00ad
            if (r4 != r6) goto L_0x009d
            android.content.Context r11 = r1.f10690a
            r1 = 0
            java.io.File r11 = r11.getExternalFilesDir(r1)
            goto L_0x010d
        L_0x009d:
            fyb r0 = new fyb
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r1[r2] = r11
            java.lang.String r11 = "Path must start with a valid logical location: %s"
            java.lang.String r11 = java.lang.String.format(r11, r1)
            r0.<init>((java.lang.String) r11)
            throw r0
        L_0x00ad:
            android.content.Context r11 = r1.f10690a
            java.io.File r11 = p000.fym.m9878a((android.content.Context) r11)
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "managed"
            r1.<init>(r11, r2)
            int r11 = r0.size()
            if (r11 >= r8) goto L_0x00c1
        L_0x00c0:
            goto L_0x00d5
        L_0x00c1:
            java.lang.Object r11 = r0.get(r9)     // Catch:{ IllegalArgumentException -> 0x00df }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ IllegalArgumentException -> 0x00df }
            android.accounts.Account r11 = p000.fxs.m9850a(r11)     // Catch:{ IllegalArgumentException -> 0x00df }
            android.accounts.Account r2 = p000.fxs.f10684a
            boolean r11 = r2.equals(r11)
            if (r11 == 0) goto L_0x00d7
            goto L_0x00c0
        L_0x00d5:
            r11 = r1
            goto L_0x010d
        L_0x00d7:
            fyb r11 = new fyb
            java.lang.String r0 = "AccountManager cannot be null"
            r11.<init>((java.lang.String) r0)
            throw r11
        L_0x00df:
            r11 = move-exception
            fyb r0 = new fyb
            r0.<init>((java.lang.Throwable) r11)
            throw r0
        L_0x00e6:
            android.content.Context r11 = r1.f10690a
            java.io.File r11 = r11.getCacheDir()
            goto L_0x010d
        L_0x00ed:
            android.content.Context r11 = r1.f10690a
            java.io.File r11 = p000.fym.m9878a((android.content.Context) r11)
            goto L_0x010d
        L_0x00f4:
            int r11 = android.os.Build.VERSION.SDK_INT
            android.content.Context r11 = r1.f10690a
            android.content.Context r11 = r11.createDeviceProtectedStorageContext()
            java.io.File r11 = r11.getCacheDir()
            goto L_0x010d
        L_0x0101:
            int r11 = android.os.Build.VERSION.SDK_INT
            android.content.Context r11 = r1.f10690a
            android.content.Context r11 = r11.createDeviceProtectedStorageContext()
            java.io.File r11 = r11.getFilesDir()
        L_0x010d:
            java.io.File r1 = new java.io.File
            java.lang.String r2 = java.io.File.separator
            int r4 = r0.size()
            java.util.List r0 = r0.subList(r3, r4)
            java.lang.String r0 = android.text.TextUtils.join(r2, r0)
            r1.<init>(r11, r0)
            android.content.Context r11 = r10.f10687b
            boolean r11 = p000.exp.m8338b(r11)
            if (r11 != 0) goto L_0x015d
            java.lang.Object r11 = r10.f10688c
            monitor-enter(r11)
            java.lang.String r0 = r10.f10689d     // Catch:{ all -> 0x015a }
            if (r0 == 0) goto L_0x0130
            goto L_0x0144
        L_0x0130:
            android.content.Context r0 = r10.f10687b     // Catch:{ all -> 0x015a }
            android.content.Context r0 = r0.createDeviceProtectedStorageContext()     // Catch:{ all -> 0x015a }
            java.io.File r0 = p000.fym.m9878a((android.content.Context) r0)     // Catch:{ all -> 0x015a }
            java.io.File r0 = r0.getParentFile()     // Catch:{ all -> 0x015a }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x015a }
            r10.f10689d = r0     // Catch:{ all -> 0x015a }
        L_0x0144:
            java.lang.String r0 = r10.f10689d     // Catch:{ all -> 0x015a }
            monitor-exit(r11)     // Catch:{ all -> 0x015a }
            java.lang.String r11 = r1.getAbsolutePath()
            boolean r11 = r11.startsWith(r0)
            if (r11 == 0) goto L_0x0152
            goto L_0x015d
        L_0x0152:
            fya r11 = new fya
            java.lang.String r0 = "Cannot access credential-protected data from direct boot"
            r11.<init>(r0)
            throw r11
        L_0x015a:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x015a }
            throw r0
        L_0x015d:
            return r1
        L_0x015e:
            fyb r11 = new fyb
            java.lang.String r0 = "Did not expect uri to have query"
            r11.<init>((java.lang.String) r0)
            throw r11
        L_0x0166:
            fyb r0 = new fyb
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r1[r2] = r11
            java.lang.String r11 = "Path must start with a valid logical location: %s"
            java.lang.String r11 = java.lang.String.format(r11, r1)
            r0.<init>((java.lang.String) r11)
            throw r0
        L_0x0176:
            fyb r11 = new fyb
            java.lang.String r0 = "Scheme must be 'android'"
            r11.<init>((java.lang.String) r0)
            throw r11
        L_0x017e:
            java.io.IOException r11 = new java.io.IOException
            java.lang.String r0 = "operation is not permitted in other authorities."
            r11.<init>(r0)
            goto L_0x0187
        L_0x0186:
            throw r11
        L_0x0187:
            goto L_0x0186
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fxu.mo6325d(android.net.Uri):java.io.File");
    }
}
