package p000;

import java.util.regex.Pattern;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: fno */
/* compiled from: PG */
public final class fno {

    /* renamed from: a */
    private static final Pattern f10106a = Pattern.compile("^(\\*[a-z]+\\*).*");

    /* renamed from: b */
    private final ConcurrentHashMap f10107b = new ConcurrentHashMap();

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cf  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.iqv mo5982a(p000.fnn r14, p000.iqv r15) {
        /*
            r13 = this;
            iqq r0 = r15.f14715d
            if (r0 == 0) goto L_0x0005
            goto L_0x0007
        L_0x0005:
            iqq r0 = p000.iqq.f14681d
        L_0x0007:
            int r0 = r0.f14683a
            r1 = 2
            r0 = r0 & r1
            if (r0 == 0) goto L_0x011b
            iqq r0 = r15.f14715d
            if (r0 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            iqq r0 = p000.iqq.f14681d
        L_0x0014:
            r2 = 5
            java.lang.Object r3 = r0.mo8790b((int) r2)
            iir r3 = (p000.iir) r3
            r3.mo8503a((p000.iix) r0)
            java.lang.Object r0 = r15.mo8790b((int) r2)
            iir r0 = (p000.iir) r0
            r0.mo8503a((p000.iix) r15)
            iix r15 = r3.f14318b
            iqq r15 = (p000.iqq) r15
            java.lang.String r15 = r15.f14685c
            java.lang.Long r2 = p000.fje.m9029a(r15)
            java.lang.Object r2 = p000.ife.m12898e((java.lang.Object) r2)
            java.lang.Long r2 = (java.lang.Long) r2
            long r4 = r2.longValue()
            j$.util.concurrent.ConcurrentHashMap r2 = r13.f10107b
            java.lang.Long r6 = java.lang.Long.valueOf(r4)
            boolean r2 = r2.containsKey(r6)
            r7 = 0
            r8 = 1
            if (r2 != 0) goto L_0x00d4
            fnn r2 = p000.fnn.WAKELOCK
            int r2 = r14.ordinal()
            java.lang.String r9 = "HashingNameSanitizer"
            if (r2 == 0) goto L_0x0061
            if (r2 == r8) goto L_0x005c
            if (r2 == r1) goto L_0x0058
            goto L_0x00ad
        L_0x0058:
            java.lang.String r2 = "--"
            goto L_0x00ae
        L_0x005c:
            java.lang.String r2 = m9273a((java.lang.String) r15)
            goto L_0x00ae
        L_0x0061:
            java.util.regex.Pattern r2 = f10106a
            java.util.regex.Matcher r2 = r2.matcher(r15)
            boolean r10 = r2.matches()
            if (r10 == 0) goto L_0x00a2
            java.lang.String r10 = "*sync*/"
            boolean r11 = r15.startsWith(r10)
            if (r11 == 0) goto L_0x0093
            r2 = 7
            java.lang.String r2 = r15.substring(r2)
            java.lang.String r2 = m9273a((java.lang.String) r2)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r11 = r2.length()
            if (r11 != 0) goto L_0x008e
            java.lang.String r2 = new java.lang.String
            r2.<init>(r10)
            goto L_0x00ae
        L_0x008e:
            java.lang.String r2 = r10.concat(r2)
            goto L_0x00ae
        L_0x0093:
            java.lang.String r2 = r2.group(r8)
            java.lang.String[] r10 = new java.lang.String[r8]
            r10[r7] = r2
            java.lang.String r11 = "non-sync system task wakelock: %s"
            p000.flw.m9199b(r9, r11, r10)
            goto L_0x00ae
        L_0x00a2:
            java.lang.String[] r2 = new java.lang.String[r8]
            r2[r7] = r15
            java.lang.String r10 = "wakelock: %s"
            p000.flw.m9199b(r9, r10, r2)
        L_0x00ad:
            r2 = r15
        L_0x00ae:
            java.lang.Long r10 = p000.fje.m9029a(r2)
            r11 = 3
            java.io.Serializable[] r12 = new java.io.Serializable[r11]
            r12[r7] = r14
            r12[r8] = r2
            r12[r1] = r10
            java.lang.String r2 = "Sanitized Hash: [%s] %s -> %s"
            p000.flw.m9199b(r9, r2, r12)
            java.io.Serializable[] r2 = new java.io.Serializable[r11]
            r2[r7] = r14
            r2[r8] = r15
            r2[r1] = r6
            java.lang.String r14 = "Raw Hash: [%s] %s -> %s"
            p000.flw.m9193a(r9, r14, r2)
            if (r10 == 0) goto L_0x00d4
            j$.util.concurrent.ConcurrentHashMap r14 = r13.f10107b
            r14.putIfAbsent(r6, r10)
        L_0x00d4:
            boolean r14 = r3.f14319c
            if (r14 != 0) goto L_0x00d9
            goto L_0x00de
        L_0x00d9:
            r3.mo8751b()
            r3.f14319c = r7
        L_0x00de:
            iix r14 = r3.f14318b
            iqq r14 = (p000.iqq) r14
            int r15 = r14.f14683a
            r15 = r15 | r8
            r14.f14683a = r15
            r14.f14684b = r4
            r15 = r15 & -3
            r14.f14683a = r15
            iqq r15 = p000.iqq.f14681d
            java.lang.String r15 = r15.f14685c
            r14.f14685c = r15
            boolean r14 = r0.f14319c
            if (r14 != 0) goto L_0x00f8
            goto L_0x00fd
        L_0x00f8:
            r0.mo8751b()
            r0.f14319c = r7
        L_0x00fd:
            iix r14 = r0.f14318b
            iqv r14 = (p000.iqv) r14
            iix r15 = r3.mo8770g()
            iqq r15 = (p000.iqq) r15
            iqv r1 = p000.iqv.f14710e
            r15.getClass()
            r14.f14715d = r15
            int r15 = r14.f14712a
            r15 = r15 | 4
            r14.f14712a = r15
            iix r14 = r0.mo8770g()
            iqv r14 = (p000.iqv) r14
            return r14
        L_0x011b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fno.mo5982a(fnn, iqv):iqv");
    }

    /* renamed from: a */
    public final iqv mo5983a(iqv iqv) {
        iqq iqq = iqv.f14715d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        if ((iqq.f14683a & 1) == 0) {
            return iqv;
        }
        iqq iqq2 = iqv.f14715d;
        if (iqq2 == null) {
            iqq2 = iqq.f14681d;
        }
        iir iir = (iir) iqq2.mo8790b(5);
        iir.mo8503a((iix) iqq2);
        iir iir2 = (iir) iqv.mo8790b(5);
        iir2.mo8503a((iix) iqv);
        long longValue = ((Long) ife.m12898e((Object) (Long) this.f10107b.get(Long.valueOf(((iqq) iir.f14318b).f14684b)))).longValue();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        iqq iqq3 = (iqq) iir.f14318b;
        iqq3.f14683a |= 1;
        iqq3.f14684b = longValue;
        if (iir2.f14319c) {
            iir2.mo8751b();
            iir2.f14319c = false;
        }
        iqv iqv2 = (iqv) iir2.f14318b;
        iqq iqq4 = (iqq) iir.mo8770g();
        iqv iqv3 = iqv.f14710e;
        iqq4.getClass();
        iqv2.f14715d = iqq4;
        iqv2.f14712a |= 4;
        return (iqv) iir2.mo8770g();
    }

    /* renamed from: a */
    private static String m9273a(String str) {
        String[] split = str.split("/");
        if (split != null && split.length == 3) {
            return split[0];
        }
        flw.m9199b("HashingNameSanitizer", "malformed sync name: %s", str);
        return "MALFORMED";
    }
}
