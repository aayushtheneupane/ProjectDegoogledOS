package p000;

import android.util.Log;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: iol */
/* compiled from: PG */
public final class iol {

    /* renamed from: a */
    private static iol f14601a;

    public iol(byte[] bArr) {
    }

    /* renamed from: a */
    public static int m14224a(int i) {
        return i - 1;
    }

    /* renamed from: a */
    public static void m14234a(String str, String str2, Throwable... thArr) {
        if (thArr.length > 0) {
            Log.e(str, str2, thArr[0]);
        } else {
            Log.e(str, str2);
        }
    }

    public iol() {
    }

    /* renamed from: a */
    public static synchronized void m14231a() {
        synchronized (iol.class) {
            if (f14601a == null) {
                f14601a = new iol((byte[]) null);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m14232a(iol iol) {
        synchronized (iol.class) {
            f14601a = iol;
        }
    }

    /* renamed from: b */
    public static String m14236b(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(23);
        sb.append("WM-");
        if (length >= 20) {
            sb.append(str.substring(0, 20));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00de, code lost:
        if (r2.mo1286b() == false) goto L_0x0253;
     */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x023b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.ane m14225a(java.lang.String r14) {
        /*
            r0 = 5
            if (r14 == 0) goto L_0x0254
            int r1 = r14.length()
            if (r1 == 0) goto L_0x0254
            anu r1 = new anu
            r1.<init>()
            p000.ckx.m4482a((java.lang.Object) r14)
            anr r2 = new anr
            r2.<init>(r14)
            r14 = 0
            char r3 = r2.mo1283a(r14)
            r4 = 84
            r5 = 58
            r6 = 1
            if (r3 != r4) goto L_0x0024
        L_0x0022:
            r3 = 1
            goto L_0x0041
        L_0x0024:
            int r3 = r2.mo1284a()
            r7 = 2
            if (r3 < r7) goto L_0x0033
            char r3 = r2.mo1283a(r6)
            if (r3 == r5) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            goto L_0x0022
        L_0x0033:
            int r3 = r2.mo1284a()
            r8 = 3
            if (r3 < r8) goto L_0x0040
            char r3 = r2.mo1283a(r7)
            if (r3 == r5) goto L_0x0022
        L_0x0040:
            r3 = 0
        L_0x0041:
            r7 = 45
            if (r3 == 0) goto L_0x004d
            r1.mo1247a(r6)
            r1.mo1249b(r6)
            goto L_0x00e2
        L_0x004d:
            char r8 = r2.mo1283a(r14)
            if (r8 == r7) goto L_0x0055
            goto L_0x0058
        L_0x0055:
            r2.mo1288d()
        L_0x0058:
            java.lang.String r8 = "Invalid year in date string"
            r9 = 9999(0x270f, float:1.4012E-41)
            int r8 = r2.mo1285a(r8, r9)
            boolean r10 = r2.mo1286b()
            if (r10 == 0) goto L_0x0076
            char r10 = r2.mo1287c()
            if (r10 != r7) goto L_0x006e
            goto L_0x0076
        L_0x006e:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after year"
            r14.<init>(r1, r0)
            throw r14
        L_0x0076:
            char r10 = r2.mo1283a(r14)
            if (r10 == r7) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            int r8 = -r8
        L_0x007e:
            int r8 = java.lang.Math.abs(r8)
            int r8 = java.lang.Math.min(r8, r9)
            r1.f1212a = r8
            boolean r8 = r2.mo1286b()
            if (r8 == 0) goto L_0x0253
            r2.mo1288d()
            r8 = 12
            java.lang.String r9 = "Invalid month in date string"
            int r8 = r2.mo1285a(r9, r8)
            boolean r9 = r2.mo1286b()
            if (r9 == 0) goto L_0x00ae
            char r9 = r2.mo1287c()
            if (r9 != r7) goto L_0x00a6
            goto L_0x00ae
        L_0x00a6:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after month"
            r14.<init>(r1, r0)
            throw r14
        L_0x00ae:
            r1.mo1247a(r8)
            boolean r8 = r2.mo1286b()
            if (r8 == 0) goto L_0x0253
            r2.mo1288d()
            r8 = 31
            java.lang.String r9 = "Invalid day in date string"
            int r8 = r2.mo1285a(r9, r8)
            boolean r9 = r2.mo1286b()
            if (r9 == 0) goto L_0x00d7
            char r9 = r2.mo1287c()
            if (r9 != r4) goto L_0x00cf
            goto L_0x00d7
        L_0x00cf:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after day"
            r14.<init>(r1, r0)
            throw r14
        L_0x00d7:
            r1.mo1249b(r8)
            boolean r8 = r2.mo1286b()
            if (r8 != 0) goto L_0x00e2
            goto L_0x0253
        L_0x00e2:
            char r8 = r2.mo1287c()
            if (r8 != r4) goto L_0x00ec
            r2.mo1288d()
            goto L_0x00ee
        L_0x00ec:
            if (r3 == 0) goto L_0x024b
        L_0x00ee:
            java.lang.String r3 = "Invalid hour in date string"
            r4 = 23
            int r3 = r2.mo1285a(r3, r4)
            char r8 = r2.mo1287c()
            if (r8 != r5) goto L_0x0243
            int r3 = java.lang.Math.abs(r3)
            int r3 = java.lang.Math.min(r3, r4)
            r1.f1215d = r3
            r2.mo1288d()
            java.lang.String r3 = "Invalid minute in date string"
            r8 = 59
            int r3 = r2.mo1285a(r3, r8)
            boolean r9 = r2.mo1286b()
            r10 = 43
            r11 = 90
            if (r9 == 0) goto L_0x013d
            char r9 = r2.mo1287c()
            if (r9 == r5) goto L_0x013d
            char r9 = r2.mo1287c()
            if (r9 == r11) goto L_0x013d
            char r9 = r2.mo1287c()
            if (r9 == r10) goto L_0x013d
            char r9 = r2.mo1287c()
            if (r9 != r7) goto L_0x0135
            goto L_0x013d
        L_0x0135:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after minute"
            r14.<init>(r1, r0)
            throw r14
        L_0x013d:
            int r3 = java.lang.Math.abs(r3)
            int r3 = java.lang.Math.min(r3, r8)
            r1.f1216e = r3
            char r3 = r2.mo1287c()
            if (r3 != r5) goto L_0x01ce
            r2.mo1288d()
            java.lang.String r3 = "Invalid whole seconds in date string"
            int r3 = r2.mo1285a(r3, r8)
            boolean r9 = r2.mo1286b()
            r12 = 46
            if (r9 == 0) goto L_0x017f
            char r9 = r2.mo1287c()
            if (r9 == r12) goto L_0x017f
            char r9 = r2.mo1287c()
            if (r9 == r11) goto L_0x017f
            char r9 = r2.mo1287c()
            if (r9 == r10) goto L_0x017f
            char r9 = r2.mo1287c()
            if (r9 != r7) goto L_0x0177
            goto L_0x017f
        L_0x0177:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after whole seconds"
            r14.<init>(r1, r0)
            throw r14
        L_0x017f:
            int r3 = java.lang.Math.abs(r3)
            int r3 = java.lang.Math.min(r3, r8)
            r1.f1217f = r3
            char r3 = r2.mo1287c()
            if (r3 != r12) goto L_0x01ce
            r2.mo1288d()
            int r3 = r2.f1207a
            r9 = 999999999(0x3b9ac9ff, float:0.004723787)
            java.lang.String r12 = "Invalid fractional seconds in date string"
            int r9 = r2.mo1285a(r12, r9)
            char r12 = r2.mo1287c()
            if (r12 != r11) goto L_0x01a4
        L_0x01a3:
            goto L_0x01b9
        L_0x01a4:
            char r12 = r2.mo1287c()
            if (r12 == r10) goto L_0x01a3
            char r12 = r2.mo1287c()
            if (r12 != r7) goto L_0x01b1
            goto L_0x01a3
        L_0x01b1:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after fractional second"
            r14.<init>(r1, r0)
            throw r14
        L_0x01b9:
            int r12 = r2.f1207a
            int r12 = r12 - r3
        L_0x01bc:
            r3 = 9
            if (r12 <= r3) goto L_0x01c5
            int r9 = r9 / 10
            int r12 = r12 + -1
            goto L_0x01bc
        L_0x01c5:
            if (r12 >= r3) goto L_0x01cc
            int r9 = r9 * 10
            int r12 = r12 + 1
            goto L_0x01c5
        L_0x01cc:
            r1.f1219h = r9
        L_0x01ce:
            char r3 = r2.mo1287c()
            if (r3 != r11) goto L_0x01da
            r2.mo1288d()
        L_0x01d7:
            r3 = 0
            r4 = 0
            goto L_0x021e
        L_0x01da:
            boolean r3 = r2.mo1286b()
            if (r3 == 0) goto L_0x021c
            char r14 = r2.mo1287c()
            if (r14 == r10) goto L_0x01f6
            char r14 = r2.mo1287c()
            if (r14 != r7) goto L_0x01ee
            r14 = -1
            goto L_0x01f8
        L_0x01ee:
            ang r14 = new ang
            java.lang.String r1 = "Time zone must begin with 'Z', '+', or '-'"
            r14.<init>(r1, r0)
            throw r14
        L_0x01f6:
            r14 = 1
        L_0x01f8:
            r2.mo1288d()
            java.lang.String r3 = "Invalid time zone hour in date string"
            int r3 = r2.mo1285a(r3, r4)
            char r4 = r2.mo1287c()
            if (r4 != r5) goto L_0x0214
            r2.mo1288d()
            java.lang.String r4 = "Invalid time zone minute in date string"
            int r4 = r2.mo1285a(r4, r8)
            r13 = r3
            r3 = r14
            r14 = r13
            goto L_0x021e
        L_0x0214:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after time zone hour"
            r14.<init>(r1, r0)
            throw r14
        L_0x021c:
            goto L_0x01d7
        L_0x021e:
            java.util.SimpleTimeZone r5 = new java.util.SimpleTimeZone
            r6 = 3600000(0x36ee80, float:5.044674E-39)
            int r14 = r14 * r6
            r6 = 60000(0xea60, float:8.4078E-41)
            int r4 = r4 * r6
            int r14 = r14 + r4
            int r14 = r14 * r3
            java.lang.String r3 = ""
            r5.<init>(r14, r3)
            r1.f1218g = r5
            boolean r14 = r2.mo1286b()
            if (r14 != 0) goto L_0x023b
            goto L_0x0253
        L_0x023b:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, extra chars at end"
            r14.<init>(r1, r0)
            throw r14
        L_0x0243:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, after hour"
            r14.<init>(r1, r0)
            throw r14
        L_0x024b:
            ang r14 = new ang
            java.lang.String r1 = "Invalid date string, missing 'T' after date"
            r14.<init>(r1, r0)
            throw r14
        L_0x0253:
            return r1
        L_0x0254:
            ang r14 = new ang
            java.lang.String r1 = "Empty convert-string"
            r14.<init>(r1, r0)
            goto L_0x025d
        L_0x025c:
            throw r14
        L_0x025d:
            goto L_0x025c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.iol.m14225a(java.lang.String):ane");
    }

    /* renamed from: a */
    public static Object m14227a(bzy bzy, PipelineParams pipelineParams) {
        return bzy.mo2914a(pipelineParams);
    }

    /* renamed from: a */
    public static Runnable m14230a(Runnable runnable, imu imu, inc inc) {
        return new inf(imu, inc, runnable);
    }

    /* renamed from: a */
    public static imu m14226a(Class cls, imt imt, imr imr) {
        imr.mo9003b();
        imu imu = (imu) ine.f14553a.get();
        ife.m12845a(!imt.f14543b, (Object) "TaskDescription#forProducerTask should be used to construct producer task descriptions.");
        return new imu(imt, (Object) null, cls);
    }

    /* renamed from: b */
    public static Object m14235b(Object obj, Class cls) {
        if (obj instanceof iod) {
            return cls.cast(obj);
        }
        if (obj instanceof ioe) {
            return cls.cast(((ioe) obj).mo2453b());
        }
        throw new IllegalStateException(String.format("Given component holder %s does not implement %s or %s", new Object[]{obj.getClass(), iod.class, ioe.class}));
    }

    /* renamed from: a */
    public static void m14233a(Object obj, Class cls) {
        if (obj == null) {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }

    /* renamed from: a */
    public static Object m14228a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* renamed from: a */
    public static Object m14229a(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }
}
