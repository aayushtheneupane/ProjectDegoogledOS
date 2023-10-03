package p000;

import java.util.Map;

/* renamed from: fft */
/* compiled from: PG */
public final class fft {

    /* renamed from: a */
    private final Map f9489a;

    /* renamed from: b */
    private final iij f9490b = iij.m13502b();

    public fft(Map map) {
        this.f9489a = map;
    }

    /* renamed from: a */
    public final ffs mo5577a(int i) {
        iqk iqk = (iqk) this.f9489a.get(Integer.valueOf(i));
        if (iqk != null) {
            return (ffs) iqk.mo2097a();
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: ieh} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5578a(p000.iiu r7, java.util.List r8, java.util.Map r9, java.util.List r10) {
        /*
            r6 = this;
            int r0 = r8.size()
            r1 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x0069
            java.lang.Object r2 = r8.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r3 = r2.intValue()
            ffs r3 = r6.mo5577a(r3)
            if (r3 == 0) goto L_0x0066
            r4 = 6
            java.lang.Object r4 = r7.mo8790b((int) r4)
            iix r4 = (p000.iix) r4
            iiu r4 = (p000.iiu) r4
            int r2 = r2.intValue()
            iij r5 = r6.f9490b
            iih r2 = r5.mo8715a(r4, r2)
            java.util.Map r4 = p000.iix.f14324A
            r7.mo8786b(r2)
            iim r4 = r7.f14321j
            iiw r5 = r2.f14244d
            java.lang.Object r4 = r4.mo8728b((p000.iil) r5)
            if (r4 != 0) goto L_0x003c
            java.lang.Object r2 = r2.f14242b
            goto L_0x0040
        L_0x003c:
            java.lang.Object r2 = r2.mo8711a(r4)
        L_0x0040:
            ikf r2 = (p000.ikf) r2
            java.lang.Object r4 = r9.get(r2)
            ieh r4 = (p000.ieh) r4
            if (r4 != 0) goto L_0x0059
            ieh r3 = r3.mo3874a(r2)
            java.lang.Object r3 = p000.ife.m12898e((java.lang.Object) r3)
            r4 = r3
            ieh r4 = (p000.ieh) r4
            r9.put(r2, r4)
            goto L_0x005a
        L_0x0059:
        L_0x005a:
            ieh r2 = p000.ffs.f9488b
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0063
            goto L_0x0066
        L_0x0063:
            r10.add(r4)
        L_0x0066:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fft.mo5578a(iiu, java.util.List, java.util.Map, java.util.List):void");
    }
}
