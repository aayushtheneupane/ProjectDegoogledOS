package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fej */
/* compiled from: PG */
public final class fej {
    /* renamed from: a */
    public static boolean m8701a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public fej() {
    }

    /* renamed from: a */
    public static fdp m8698a() {
        iih iih = feh.f9370a;
        iir g = feg.f9366c.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        feg feg = (feg) g.f14318b;
        "".getClass();
        feg.f9368a |= 1;
        feg.f9369b = "";
        return fdp.m8624a(iih, (feg) g.mo8770g());
    }

    /* JADX WARNING: type inference failed for: r0v16, types: [ikf] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m8700a(p000.fdr r9, java.util.List r10) {
        /*
            r0 = r9
        L_0x0001:
            r1 = 0
            r2 = 1
            if (r9 == 0) goto L_0x0057
            ffa r3 = p000.ffa.f9433a
            fdx r3 = r9.mo5534a((p000.ffa) r3)
            if (r3 != 0) goto L_0x000e
            goto L_0x0022
        L_0x000e:
            int r0 = r3.f9340a
            r0 = r0 & r2
            if (r0 != 0) goto L_0x0016
            r0 = 0
            goto L_0x0018
        L_0x0016:
            r0 = 1
        L_0x0018:
            java.lang.String r4 = "Instrumented view has no VE ID."
            p000.ife.m12876b((boolean) r0, (java.lang.Object) r4)
            r10.add(r3)
            r0 = r9
        L_0x0022:
            ffa r4 = p000.ffa.f9433a
            fdq r4 = r9.mo5544e(r4)
            fdr r4 = r4.mo5524b()
            if (r4 != 0) goto L_0x0055
            ffa r4 = p000.ffa.f9433a
            fdq r4 = r9.mo5544e(r4)
            boolean r4 = r4.mo5523a()
            if (r4 != 0) goto L_0x004e
            iih r4 = p000.ffz.f9495a
            java.util.Map r5 = p000.iix.f14324A
            r3.mo8786b(r4)
            iim r3 = r3.f14321j
            iiw r4 = r4.f14244d
            boolean r3 = r3.mo8726a((p000.iil) r4)
            if (r3 != 0) goto L_0x004d
            r3 = 0
            goto L_0x004f
        L_0x004d:
        L_0x004e:
            r3 = 1
        L_0x004f:
            java.lang.String r4 = "Activity's content root (android.R.id.content) must be annotated with a VE. CVE root was: %s"
            p000.ife.m12878b((boolean) r3, (java.lang.String) r4, (java.lang.Object) r9)
            goto L_0x0058
        L_0x0055:
            r9 = r4
            goto L_0x0001
        L_0x0057:
        L_0x0058:
            int r3 = r10.size()
            int r3 = r3 + -1
            ffa r4 = p000.ffa.f9433a
            android.view.View r0 = p000.fdz.m8659a((p000.fdr) r0, (p000.ffa) r4)
            if (r0 == 0) goto L_0x00f5
            ffa r4 = p000.ffa.f9433a
            fdx r9 = r9.mo5534a((p000.ffa) r4)
            android.content.Context r0 = r0.getContext()
        L_0x0070:
            r4 = 0
            if (r0 == 0) goto L_0x0086
            boolean r5 = r0 instanceof android.app.Activity
            if (r5 != 0) goto L_0x0083
            boolean r5 = r0 instanceof android.content.ContextWrapper
            if (r5 != 0) goto L_0x007c
            goto L_0x0086
        L_0x007c:
            android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
            android.content.Context r0 = r0.getBaseContext()
            goto L_0x0070
        L_0x0083:
            android.app.Activity r0 = (android.app.Activity) r0
            goto L_0x0087
        L_0x0086:
            r0 = r4
        L_0x0087:
            if (r0 == 0) goto L_0x00f5
            iij r5 = p000.iij.m13502b()
            ffa r6 = p000.ffa.f9433a
            p000.ife.m12898e((java.lang.Object) r6)
            android.content.Intent r0 = r0.getIntent()
            java.lang.String r6 = "$GIL$PropagatedState"
            boolean r7 = r0.hasExtra(r6)
            if (r7 == 0) goto L_0x00ae
            android.os.Parcelable r0 = r0.getParcelableExtra(r6)
            imh r0 = (p000.imh) r0
            fdx r4 = p000.fdx.f9338e
            ikf r0 = r0.mo8995a(r4, r5)
            r4 = r0
            fdx r4 = (p000.fdx) r4
            goto L_0x00b0
        L_0x00ae:
        L_0x00b0:
            if (r4 == 0) goto L_0x00f5
            ijc r0 = r4.f9342c
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x00f5
        L_0x00ba:
            if (r1 >= r0) goto L_0x00dc
            ijc r6 = r4.f9342c
            int r6 = r6.mo8800c(r1)
            fdx r7 = p000.fdx.f9338e
            iih r7 = r5.mo8715a(r7, r6)
            r9.mo8786b(r7)
            iim r8 = r9.f14321j
            iiw r7 = r7.f14244d
            boolean r7 = r8.mo8726a((p000.iil) r7)
            r7 = r7 ^ r2
            java.lang.String r8 = "Propagated VE State conflicts with explicit state: extension=%s"
            p000.ife.m12846a((boolean) r7, (java.lang.String) r8, (int) r6)
            int r1 = r1 + 1
            goto L_0x00ba
        L_0x00dc:
            r0 = 5
            java.lang.Object r0 = r9.mo8790b((int) r0)
            iir r0 = (p000.iir) r0
            r0.mo8503a((p000.iix) r9)
            iit r0 = (p000.iit) r0
            r0.mo8503a((p000.iix) r4)
            iix r9 = r0.mo8770g()
            fdx r9 = (p000.fdx) r9
            r10.set(r3, r9)
            return
        L_0x00f5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fej.m8700a(fdr, java.util.List):void");
    }

    /* renamed from: a */
    public static List m8699a(fdr fdr) {
        ArrayList arrayList = new ArrayList();
        m8700a(fdr, (List) arrayList);
        return arrayList;
    }

    public fej(byte[] bArr) {
    }

    public fej(char[] cArr) {
    }
}
