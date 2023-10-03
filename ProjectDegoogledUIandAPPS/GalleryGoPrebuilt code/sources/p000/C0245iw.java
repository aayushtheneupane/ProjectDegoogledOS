package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: iw */
/* compiled from: PG */
public final class C0245iw extends C0250ja {
    /* renamed from: a */
    public final Typeface mo9109a(Context context, C0231ii iiVar, Resources resources, int i) {
        int i2 = 0;
        FontFamily.Builder builder = null;
        for (C0232ij ijVar : iiVar.f14222a) {
            try {
                Font build = new Font.Builder(resources, ijVar.f14335f).setWeight(ijVar.f14331b).setSlant(ijVar.f14332c ? 1 : 0).setTtcIndex(ijVar.f14334e).setFontVariationSettings(ijVar.f14333d).build();
                if (builder != null) {
                    builder.addFont(build);
                } else {
                    builder = new FontFamily.Builder(build);
                }
            } catch (IOException e) {
            }
        }
        if (builder == null) {
            return null;
        }
        int i3 = (i & 1) == 0 ? 400 : 700;
        if ((i & 2) != 0) {
            i2 = 1;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i3, i2)).build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw r7;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Typeface mo9110a(android.content.Context r10, p000.C0271jv[] r11, int r12) {
        /*
            r9 = this;
            android.content.ContentResolver r10 = r10.getContentResolver()
            int r0 = r11.length
            r1 = 0
            r2 = 0
            r4 = r2
            r3 = 0
        L_0x0009:
            if (r3 >= r0) goto L_0x0056
            r5 = r11[r3]
            android.net.Uri r6 = r5.f15097a     // Catch:{ IOException -> 0x0051 }
            java.lang.String r7 = "r"
            android.os.ParcelFileDescriptor r6 = r10.openFileDescriptor(r6, r7, r2)     // Catch:{ IOException -> 0x0051 }
            if (r6 == 0) goto L_0x0044
            android.graphics.fonts.Font$Builder r7 = new android.graphics.fonts.Font$Builder     // Catch:{ all -> 0x0045 }
            r7.<init>(r6)     // Catch:{ all -> 0x0045 }
            int r8 = r5.f15099c     // Catch:{ all -> 0x0045 }
            android.graphics.fonts.Font$Builder r7 = r7.setWeight(r8)     // Catch:{ all -> 0x0045 }
            boolean r8 = r5.f15100d     // Catch:{ all -> 0x0045 }
            android.graphics.fonts.Font$Builder r7 = r7.setSlant(r8)     // Catch:{ all -> 0x0045 }
            int r5 = r5.f15098b     // Catch:{ all -> 0x0045 }
            android.graphics.fonts.Font$Builder r5 = r7.setTtcIndex(r5)     // Catch:{ all -> 0x0045 }
            android.graphics.fonts.Font r5 = r5.build()     // Catch:{ all -> 0x0045 }
            if (r4 == 0) goto L_0x0038
            r4.addFont(r5)     // Catch:{ all -> 0x0045 }
            goto L_0x003f
        L_0x0038:
            android.graphics.fonts.FontFamily$Builder r7 = new android.graphics.fonts.FontFamily$Builder     // Catch:{ all -> 0x0045 }
            r7.<init>(r5)     // Catch:{ all -> 0x0045 }
            r4 = r7
        L_0x003f:
            r6.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0053
        L_0x0043:
            r5 = move-exception
        L_0x0044:
            goto L_0x0053
        L_0x0045:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r7 = move-exception
            r6.close()     // Catch:{ all -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r6 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r5, (java.lang.Throwable) r6)     // Catch:{ IOException -> 0x0051 }
        L_0x0050:
            throw r7     // Catch:{ IOException -> 0x0051 }
        L_0x0051:
            r5 = move-exception
        L_0x0053:
            int r3 = r3 + 1
            goto L_0x0009
        L_0x0056:
            if (r4 == 0) goto L_0x0080
            r10 = r12 & 1
            if (r10 != 0) goto L_0x005f
            r10 = 400(0x190, float:5.6E-43)
            goto L_0x0061
        L_0x005f:
            r10 = 700(0x2bc, float:9.81E-43)
        L_0x0061:
            r11 = r12 & 2
            if (r11 == 0) goto L_0x0067
            r1 = 1
            goto L_0x0069
        L_0x0067:
        L_0x0069:
            android.graphics.fonts.FontStyle r11 = new android.graphics.fonts.FontStyle
            r11.<init>(r10, r1)
            android.graphics.Typeface$CustomFallbackBuilder r10 = new android.graphics.Typeface$CustomFallbackBuilder
            android.graphics.fonts.FontFamily r12 = r4.build()
            r10.<init>(r12)
            android.graphics.Typeface$CustomFallbackBuilder r10 = r10.setStyle(r11)
            android.graphics.Typeface r10 = r10.build()
            return r10
        L_0x0080:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0245iw.mo9110a(android.content.Context, jv[], int):android.graphics.Typeface");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Typeface mo9114a(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* renamed from: a */
    public final Typeface mo9111a(Context context, Resources resources, int i, String str, int i2) {
        int i3;
        try {
            FontFamily build = new FontFamily.Builder(new Font.Builder(resources, i).build()).build();
            int i4 = (i2 & 1) == 0 ? 400 : 700;
            if ((i2 & 2) != 0) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            return new Typeface.CustomFallbackBuilder(build).setStyle(new FontStyle(i4, i3)).build();
        } catch (IOException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0271jv mo9115a(C0271jv[] jvVarArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
