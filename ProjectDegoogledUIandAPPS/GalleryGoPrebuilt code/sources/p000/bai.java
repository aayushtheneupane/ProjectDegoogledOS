package p000;

import android.util.Log;
import java.io.File;

/* renamed from: bai */
/* compiled from: PG */
public final class bai {

    /* renamed from: a */
    public static volatile bai f1947a;

    /* renamed from: d */
    private static final File f1948d = new File("/proc/self/fd");

    /* renamed from: b */
    public final boolean f1949b;

    /* renamed from: c */
    public final int f1950c;

    /* renamed from: e */
    private final int f1951e;

    /* renamed from: f */
    private int f1952f;

    /* renamed from: g */
    private boolean f1953g = true;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public bai() {
        /*
            r4 = this;
            r4.<init>()
            r0 = 1
            r4.f1953g = r0
            java.lang.String r1 = android.os.Build.MODEL
            r2 = 0
            if (r1 == 0) goto L_0x0078
            java.lang.String r1 = android.os.Build.MODEL
            int r1 = r1.length()
            r3 = 7
            if (r1 < r3) goto L_0x0077
            java.lang.String r1 = android.os.Build.MODEL
            java.lang.String r1 = r1.substring(r2, r3)
            int r3 = r1.hashCode()
            switch(r3) {
                case -1398613787: goto L_0x005f;
                case -1398431166: goto L_0x0055;
                case -1398431161: goto L_0x004b;
                case -1398431073: goto L_0x0041;
                case -1398431068: goto L_0x0037;
                case -1398343746: goto L_0x002d;
                case -1398222624: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0069
        L_0x0022:
            java.lang.String r3 = "SM-N935"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 0
            goto L_0x006a
        L_0x002d:
            java.lang.String r3 = "SM-J720"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 1
            goto L_0x006a
        L_0x0037:
            java.lang.String r3 = "SM-G965"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 3
            goto L_0x006a
        L_0x0041:
            java.lang.String r3 = "SM-G960"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 2
            goto L_0x006a
        L_0x004b:
            java.lang.String r3 = "SM-G935"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 4
            goto L_0x006a
        L_0x0055:
            java.lang.String r3 = "SM-G930"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 5
            goto L_0x006a
        L_0x005f:
            java.lang.String r3 = "SM-A520"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            r1 = 6
            goto L_0x006a
        L_0x0069:
            r1 = -1
        L_0x006a:
            switch(r1) {
                case 0: goto L_0x006e;
                case 1: goto L_0x006e;
                case 2: goto L_0x006e;
                case 3: goto L_0x006e;
                case 4: goto L_0x006e;
                case 5: goto L_0x006e;
                case 6: goto L_0x006e;
                default: goto L_0x006d;
            }
        L_0x006d:
            goto L_0x0078
        L_0x006e:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            if (r1 != r3) goto L_0x0076
            r0 = 0
            goto L_0x0079
        L_0x0076:
            goto L_0x0078
        L_0x0077:
        L_0x0078:
        L_0x0079:
            r4.f1949b = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L_0x0088
            r0 = 20000(0x4e20, float:2.8026E-41)
            r4.f1951e = r0
            r4.f1950c = r2
            return
        L_0x0088:
            r0 = 700(0x2bc, float:9.81E-43)
            r4.f1951e = r0
            r0 = 128(0x80, float:1.794E-43)
            r4.f1950c = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bai.<init>():void");
    }

    /* renamed from: a */
    public final synchronized boolean mo1755a() {
        boolean z = true;
        int i = this.f1952f + 1;
        this.f1952f = i;
        if (i >= 50) {
            this.f1952f = 0;
            int length = f1948d.list().length;
            if (length >= this.f1951e) {
                z = false;
            }
            this.f1953g = z;
            if (!z && Log.isLoggable("Downsampler", 5)) {
                int i2 = this.f1951e;
                StringBuilder sb = new StringBuilder(126);
                sb.append("Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors ");
                sb.append(length);
                sb.append(", limit ");
                sb.append(i2);
                Log.w("Downsampler", sb.toString());
            }
        }
        return this.f1953g;
    }
}
