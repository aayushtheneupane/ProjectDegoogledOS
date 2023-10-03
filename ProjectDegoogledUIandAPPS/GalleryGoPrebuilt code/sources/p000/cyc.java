package p000;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

/* renamed from: cyc */
/* compiled from: PG */
public final class cyc {

    /* renamed from: a */
    public static final hqk f5983a = ife.m12811a(cyb.f5982a);

    /* renamed from: b */
    public static final String f5984b = Build.MODEL;

    /* renamed from: a */
    public static int m5640a(int i, int i2, int i3) {
        return i3 % 180 != 0 ? i2 : i;
    }

    /* renamed from: a */
    public static cxh m5643a(int i) {
        return i != 1 ? i != 3 ? cxh.UNKNOWN_MEDIA_TYPE : cxh.VIDEO : cxh.IMAGE;
    }

    /* renamed from: a */
    public static boolean m5645a(cxi cxi, cxi cxi2) {
        return ((cxi.f5909a & 2) == 0 || (cxi2.f5909a & 2) == 0 || cxi.f5911c != cxi2.f5911c) ? false : true;
    }

    /* renamed from: b */
    public static int m5646b(int i, int i2, int i3) {
        return i3 % 180 != 0 ? i : i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        if (r6.f5911c == r7.f5911c) goto L_0x0021;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m5649b(p000.cxi r6, p000.cxi r7) {
        /*
            int r0 = r6.f5909a
            r0 = r0 & 2
            r1 = 0
            if (r0 == 0) goto L_0x0008
            goto L_0x0019
        L_0x0008:
            int r0 = r7.f5909a
            r0 = r0 & 2
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = r6.f5910b
            java.lang.String r2 = r7.f5910b
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0021
            return r1
        L_0x0019:
            long r2 = r6.f5911c
            long r4 = r7.f5911c
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0047
        L_0x0021:
            java.lang.String r0 = r6.f5930v
            java.lang.String r2 = r7.f5930v
            boolean r0 = p000.ife.m12891c((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x0047
            int r0 = r6.f5909a
            r2 = 1048576(0x100000, float:1.469368E-39)
            r0 = r0 & r2
            if (r0 == 0) goto L_0x0037
            int r0 = r7.f5909a
            r0 = r0 & r2
            if (r0 != 0) goto L_0x0045
        L_0x0037:
            boolean r0 = r6.f5912d
            boolean r2 = r7.f5912d
            if (r0 != r2) goto L_0x0047
            long r2 = r6.f5918j
            long r6 = r7.f5918j
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x0047
        L_0x0045:
            r6 = 1
            return r6
        L_0x0047:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cyc.m5649b(cxi, cxi):boolean");
    }

    /* renamed from: a */
    public static Uri m5642a(Uri uri) {
        String uri2 = uri.toString();
        if (TextUtils.isEmpty(uri2)) {
            return uri;
        }
        if (uri2.startsWith("content://media/external/images/media/")) {
            return Uri.parse(uri2.replace("content://media/external/images/media/", "content://media/external/file/"));
        }
        return uri2.startsWith("content://media/external/video/media/") ? Uri.parse(uri2.replace("content://media/external/video/media/", "content://media/external/file/")) : uri;
    }

    /* renamed from: a */
    public static long m5641a(cxi cxi) {
        return ((((cxi.f5911c + 31) * 31) + cxi.f5918j) * 31) + ((long) cxi.f5929u.size());
    }

    /* renamed from: b */
    public static cyd m5648b(cxi cxi) {
        iir g = cyd.f5985i.mo8793g();
        String str = cxi.f5910b;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cyd cyd = (cyd) g.f14318b;
        str.getClass();
        int i = cyd.f5987a | 1;
        cyd.f5987a = i;
        cyd.f5988b = str;
        long j = cxi.f5911c;
        int i2 = i | 2;
        cyd.f5987a = i2;
        cyd.f5989c = j;
        long j2 = cxi.f5916h;
        int i3 = i2 | 4;
        cyd.f5987a = i3;
        cyd.f5990d = j2;
        boolean z = cxi.f5912d;
        cyd.f5987a = i3 | 8;
        cyd.f5991e = z;
        cxh a = cxh.m5592a(cxi.f5913e);
        if (a == null) {
            a = cxh.UNKNOWN_MEDIA_TYPE;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cyd cyd2 = (cyd) g.f14318b;
        cyd2.f5992f = a.f5906d;
        int i4 = cyd2.f5987a | 16;
        cyd2.f5987a = i4;
        String str2 = cxi.f5914f;
        str2.getClass();
        cyd2.f5987a = i4 | 32;
        cyd2.f5993g = str2;
        ehf ehf = cxi.f5917i;
        if (ehf == null) {
            ehf = ehf.f8283d;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cyd cyd3 = (cyd) g.f14318b;
        ehf.getClass();
        cyd3.f5994h = ehf;
        cyd3.f5987a |= 64;
        return (cyd) g.mo8770g();
    }

    /* renamed from: a */
    public static String m5644a(cxh cxh, long j) {
        String str;
        cxh cxh2 = cxh.UNKNOWN_MEDIA_TYPE;
        int ordinal = cxh.ordinal();
        if (ordinal != 0) {
            str = ordinal != 1 ? ordinal != 2 ? "content://media/external/" : "content://media/external/video/media/" : "content://media/external/images/media/";
        } else {
            str = "content://media/external/file/";
        }
        StringBuilder sb = new StringBuilder(str.length() + 20);
        sb.append(str);
        sb.append(j);
        return sb.toString();
    }

    /* renamed from: b */
    public static Uri m5647b(cxh cxh, long j) {
        return Uri.parse(m5644a(cxh, j));
    }
}
