package p000;

import java.io.File;
import java.util.List;

/* renamed from: foi */
/* compiled from: PG */
public final class foi {

    /* renamed from: a */
    private final File f10144a;

    /* renamed from: b */
    private final List f10145b;

    /* renamed from: c */
    private final int f10146c;

    /* renamed from: d */
    private final List f10147d;

    public /* synthetic */ foi(File file, List list, int i, hso hso) {
        this.f10144a = file;
        this.f10146c = i;
        this.f10147d = list;
        this.f10145b = hso;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x013c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long mo5999a(p000.foh r17) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            irx r0 = p000.irx.f14941e
            iir r3 = r0.mo8793g()
            java.lang.String r0 = r2.f10141a
            boolean r4 = r3.f14319c
            r5 = 0
            if (r4 != 0) goto L_0x0012
            goto L_0x0017
        L_0x0012:
            r3.mo8751b()
            r3.f14319c = r5
        L_0x0017:
            iix r4 = r3.f14318b
            irx r4 = (p000.irx) r4
            r0.getClass()
            int r6 = r4.f14943a
            r7 = 1
            r6 = r6 | r7
            r4.f14943a = r6
            r4.f14944b = r0
            r8 = 0
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            foi r4 = r2.f10143c     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            java.io.File r4 = r4.f10144a     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            java.lang.String r6 = r2.f10141a     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            r0.<init>(r4, r6)     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            java.io.File[] r0 = r0.listFiles()     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            int r4 = r2.f10142b     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            int r6 = r1.f10146c     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            if (r4 >= r6) goto L_0x011c
            java.util.List r4 = r1.f10147d     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            int r4 = r4.size()     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            r6 = 512(0x200, float:7.175E-43)
            if (r4 < r6) goto L_0x0049
            goto L_0x011c
        L_0x0049:
            int r4 = r0.length     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
            r10 = 0
        L_0x004b:
            if (r10 >= r4) goto L_0x0120
            r11 = r0[r10]     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            boolean r12 = p000.frz.m9476a((java.io.File) r11)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r12 != 0) goto L_0x0111
            boolean r12 = r11.isFile()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r12 != 0) goto L_0x0071
            boolean r12 = r11.isDirectory()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r12 == 0) goto L_0x0111
            foh r12 = new foh     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.lang.String r11 = r11.getName()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r12.<init>(r1, r2, r11)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            long r11 = r1.mo5999a(r12)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            long r8 = r8 + r11
            goto L_0x0112
        L_0x0071:
            java.lang.String r12 = r11.getName()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r13 = r2.f10142b     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r13 == 0) goto L_0x00a1
            java.lang.String r13 = r2.f10141a     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.lang.String r14 = java.lang.String.valueOf(r13)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r14 = r14.length()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.lang.String r15 = java.lang.String.valueOf(r12)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r15 = r15.length()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r14 = r14 + r7
            int r14 = r14 + r15
            r5.<init>(r14)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r5.append(r13)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r13 = 47
            r5.append(r13)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r5.append(r12)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.lang.String r12 = r5.toString()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
        L_0x00a1:
            java.util.List r5 = r1.f10145b     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
        L_0x00a7:
            boolean r13 = r5.hasNext()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r13 == 0) goto L_0x010a
            java.lang.Object r13 = r5.next()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.util.regex.Pattern r13 = (java.util.regex.Pattern) r13     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.util.regex.Matcher r13 = r13.matcher(r12)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            boolean r13 = r13.matches()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r13 == 0) goto L_0x00a7
            java.util.List r5 = r1.f10147d     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r5 = r5.size()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r5 >= r6) goto L_0x010a
            irx r5 = p000.irx.f14941e     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            iir r5 = r5.mo8793g()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            boolean r13 = r5.f14319c     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r13 != 0) goto L_0x00d0
            goto L_0x00d6
        L_0x00d0:
            r5.mo8751b()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r13 = 0
            r5.f14319c = r13     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
        L_0x00d6:
            iix r13 = r5.f14318b     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            irx r13 = (p000.irx) r13     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r12.getClass()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r14 = r13.f14943a     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r14 = r14 | r7
            r13.f14943a = r14     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r13.f14944b = r12     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            long r12 = r11.length()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            boolean r14 = r5.f14319c     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            if (r14 != 0) goto L_0x00ed
            goto L_0x00f3
        L_0x00ed:
            r5.mo8751b()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r14 = 0
            r5.f14319c = r14     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
        L_0x00f3:
            iix r14 = r5.f14318b     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            irx r14 = (p000.irx) r14     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            int r15 = r14.f14943a     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r15 = r15 | 2
            r14.f14943a = r15     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r14.f14946d = r12     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            iix r5 = r5.mo8770g()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            irx r5 = (p000.irx) r5     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            java.util.List r12 = r1.f10147d     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            r12.add(r5)     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
        L_0x010a:
            long r11 = r11.length()     // Catch:{ IOException -> 0x011a, SecurityException -> 0x0118 }
            long r8 = r8 + r11
            goto L_0x0112
        L_0x0111:
        L_0x0112:
            int r10 = r10 + 1
            r5 = 0
            goto L_0x004b
        L_0x0118:
            r0 = move-exception
            goto L_0x0126
        L_0x011a:
            r0 = move-exception
            goto L_0x0126
        L_0x011c:
            long r8 = p000.frz.m9473a((java.io.File[]) r0)     // Catch:{ IOException -> 0x0125, SecurityException -> 0x0123 }
        L_0x0120:
            goto L_0x0137
        L_0x0123:
            r0 = move-exception
            goto L_0x0126
        L_0x0125:
            r0 = move-exception
        L_0x0126:
            java.lang.String[] r4 = new java.lang.String[r7]
            java.lang.String r2 = r2.f10141a
            r5 = 0
            r4[r5] = r2
            java.lang.String r2 = "DirStatsCapture"
            java.lang.String r5 = "exception while collecting DirStats for dir %s"
            p000.flw.m9192a((java.lang.String) r2, (java.lang.String) r5, (java.lang.Throwable) r0, (java.lang.Object[]) r4)
        L_0x0137:
            boolean r0 = r3.f14319c
            if (r0 != 0) goto L_0x013c
            goto L_0x0142
        L_0x013c:
            r3.mo8751b()
            r2 = 0
            r3.f14319c = r2
        L_0x0142:
            iix r0 = r3.f14318b
            irx r0 = (p000.irx) r0
            int r2 = r0.f14943a
            r2 = r2 | 2
            r0.f14943a = r2
            r0.f14946d = r8
            java.util.List r0 = r1.f10147d
            iix r2 = r3.mo8770g()
            irx r2 = (p000.irx) r2
            r0.add(r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.foi.mo5999a(foh):long");
    }
}
