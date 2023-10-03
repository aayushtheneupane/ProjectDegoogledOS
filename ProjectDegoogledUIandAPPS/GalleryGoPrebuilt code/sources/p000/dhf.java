package p000;

import android.content.Context;
import android.content.pm.PackageManager;
import p003j$.util.Optional;

/* renamed from: dhf */
/* compiled from: PG */
public final class dhf implements dhl {

    /* renamed from: a */
    private final Optional f6537a = Optional.empty();

    /* renamed from: b */
    private final Optional f6538b = Optional.empty();

    /* renamed from: c */
    private final Context f6539c;

    /* renamed from: d */
    private final PackageManager f6540d;

    /* renamed from: e */
    private final cjr f6541e;

    /* renamed from: f */
    private Optional f6542f = Optional.empty();

    public dhf(Context context, PackageManager packageManager, cjr cjr) {
        this.f6539c = context;
        this.f6540d = packageManager;
        this.f6541e = cjr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0111  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.hto mo4133a() {
        /*
            r8 = this;
            j$.util.Optional r0 = r8.f6542f
            boolean r0 = r0.isPresent()
            if (r0 == 0) goto L_0x000a
            goto L_0x0127
        L_0x000a:
            htm r0 = p000.hto.m12130j()
            cjr r1 = r8.f6541e
            boolean r1 = r1.mo3175a()
            java.lang.String r2 = "58E1C4133F7441EC3D2C270270A14802DA47BA0E"
            r3 = 0
            r4 = 1
            if (r1 != 0) goto L_0x0037
            android.content.pm.PackageManager r1 = r8.f6540d
            android.content.Context r5 = r8.f6539c
            java.lang.String r5 = r5.getPackageName()
            hto r6 = p000.hto.m12120a((java.lang.Object) r2)
            boolean r1 = p000.C0637xj.m15911b((android.content.pm.PackageManager) r1, (java.lang.String) r5, (java.util.Set) r6)
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)
            r5[r3] = r6
            if (r1 != 0) goto L_0x0036
            r1 = 0
            goto L_0x0038
        L_0x0036:
        L_0x0037:
            r1 = 1
        L_0x0038:
            hsq r5 = p000.hsu.m12070g()
            htm r6 = p000.hto.m12130j()
            java.lang.String r7 = "BE8CB9F95BCB5BFB04045034E5182634A2FCA1FA"
            r6.mo7874b(r7)
            if (r1 == 0) goto L_0x004c
            java.lang.String r7 = "33192B4B6C9F1FAC05D19D9BAD680B621556365D"
            r6.mo7874b(r7)
        L_0x004c:
            hto r6 = r6.mo7993a()
            java.lang.String r7 = "com.gallery20.photoprovider"
            r5.mo7932a(r7, r6)
            htm r6 = p000.hto.m12130j()
            java.lang.String r7 = "737DB486FCE1F87CCC46237C31AE1C0B45AA8416"
            r6.mo7874b(r7)
            if (r1 == 0) goto L_0x0064
            r6.mo7874b(r2)
        L_0x0064:
            hto r2 = r6.mo7993a()
            java.lang.String r6 = "com.google.android.apps.cameralite.processingmedia.ProcessingMediaProvider"
            r5.mo7932a(r6, r2)
            htm r2 = p000.hto.m12130j()
            java.lang.String r6 = "5F95F1F30F897387769AE22A970327569C6D0B89"
            r2.mo7874b(r6)
            if (r1 == 0) goto L_0x007e
            java.lang.String r6 = "B79DF4A82E90B57EA76525AB7037AB238A42F5D3"
            r2.mo7874b(r6)
        L_0x007e:
            hto r2 = r2.mo7993a()
            java.lang.String r6 = "com.sprd.android.providers.SpecialTypesProvider"
            r5.mo7932a(r6, r2)
            if (r1 == 0) goto L_0x0095
            java.lang.String r1 = "04C500FCF5C208965AD21DE0E503ABC8118F1743"
            hto r1 = p000.hto.m12120a((java.lang.Object) r1)
            java.lang.String r2 = "com.google.android.apps.photos.api.sample.SpecialTypesProvider"
            r5.mo7932a(r2, r1)
        L_0x0095:
            hsu r1 = r5.mo7930a()
            hto r1 = r1.entrySet()
            hvr r1 = r1.iterator()
        L_0x00a1:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00cb
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            android.content.pm.PackageManager r5 = r8.f6540d
            java.lang.Object r6 = r2.getKey()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r2.getValue()
            java.util.Set r7 = (java.util.Set) r7
            boolean r5 = p000.C0637xj.m15903a((android.content.pm.PackageManager) r5, (java.lang.String) r6, (java.util.Set) r7)
            if (r5 == 0) goto L_0x00a1
            java.lang.Object r2 = r2.getKey()
            java.lang.String r2 = (java.lang.String) r2
            r0.mo7874b(r2)
            goto L_0x00a1
        L_0x00cb:
            j$.util.Optional r1 = r8.f6537a
            boolean r1 = r1.isPresent()
            if (r1 == 0) goto L_0x0102
            j$.util.Optional r1 = r8.f6538b
            boolean r1 = r1.isPresent()
            if (r1 == 0) goto L_0x0102
            android.content.pm.PackageManager r1 = r8.f6540d
            j$.util.Optional r2 = r8.f6537a
            java.lang.Object r2 = r2.get()
            java.lang.String r2 = (java.lang.String) r2
            j$.util.Optional r5 = r8.f6538b
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            hto r5 = p000.hto.m12120a((java.lang.Object) r5)
            boolean r1 = p000.C0637xj.m15903a((android.content.pm.PackageManager) r1, (java.lang.String) r2, (java.util.Set) r5)
            if (r1 == 0) goto L_0x0102
            j$.util.Optional r1 = r8.f6537a
            java.lang.Object r1 = r1.get()
            java.lang.String r1 = (java.lang.String) r1
            r0.mo7874b(r1)
        L_0x0102:
            java.lang.String r1 = "oem_trusted_authority"
            java.lang.String r2 = ""
            java.lang.String r1 = p000.fxj.m9816a(r1, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x011d
            r0.mo7874b(r1)
            java.lang.Object[] r2 = new java.lang.Object[r4]
            r2[r3] = r1
            java.lang.String r1 = "OemAuthorities: Always trusting %s via system property"
            p000.cwn.m5510a(r1, r2)
        L_0x011d:
            hto r0 = r0.mo7993a()
            j$.util.Optional r0 = p003j$.util.Optional.m16285of(r0)
            r8.f6542f = r0
        L_0x0127:
            j$.util.Optional r0 = r8.f6542f
            java.lang.Object r0 = r0.get()
            hto r0 = (p000.hto) r0
            r0.isEmpty()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dhf.mo4133a():hto");
    }
}
