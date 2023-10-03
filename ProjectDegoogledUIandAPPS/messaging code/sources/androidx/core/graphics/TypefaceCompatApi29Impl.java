package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import androidx.core.content.p022a.C0311d;
import androidx.core.content.p022a.C0312e;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.io.InputStream;

public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, C0311d dVar, Resources resources, int i) {
        C0312e[] entries = dVar.getEntries();
        int length = entries.length;
        int i2 = 0;
        FontFamily.Builder builder = null;
        int i3 = 0;
        while (true) {
            int i4 = 1;
            if (i3 >= length) {
                break;
            }
            C0312e eVar = entries[i3];
            try {
                Font.Builder weight = new Font.Builder(resources, eVar.getResourceId()).setWeight(eVar.getWeight());
                if (!eVar.isItalic()) {
                    i4 = 0;
                }
                Font build = weight.setSlant(i4).setTtcIndex(eVar.getTtcIndex()).setFontVariationSettings(eVar.getVariationSettings()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(build);
                } else {
                    builder.addFont(build);
                }
            } catch (IOException unused) {
            }
            i3++;
        }
        if (builder == null) {
            return null;
        }
        int i5 = (i & 1) != 0 ? 700 : 400;
        if ((i & 2) != 0) {
            i2 = 1;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle(i5, i2)).build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r6.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r10, android.os.CancellationSignal r11, androidx.core.provider.FontsContractCompat.FontInfo[] r12, int r13) {
        /*
            r9 = this;
            android.content.ContentResolver r9 = r10.getContentResolver()
            int r10 = r12.length
            r0 = 0
            r1 = 0
            r3 = r0
            r2 = r1
        L_0x0009:
            r4 = 1
            if (r2 >= r10) goto L_0x0061
            r5 = r12[r2]
            android.net.Uri r6 = r5.getUri()     // Catch:{ IOException -> 0x005e }
            java.lang.String r7 = "r"
            android.os.ParcelFileDescriptor r6 = r9.openFileDescriptor(r6, r7, r11)     // Catch:{ IOException -> 0x005e }
            if (r6 != 0) goto L_0x0020
            if (r6 == 0) goto L_0x005e
        L_0x001c:
            r6.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x005e
        L_0x0020:
            android.graphics.fonts.Font$Builder r7 = new android.graphics.fonts.Font$Builder     // Catch:{ all -> 0x0052 }
            r7.<init>(r6)     // Catch:{ all -> 0x0052 }
            int r8 = r5.getWeight()     // Catch:{ all -> 0x0052 }
            android.graphics.fonts.Font$Builder r7 = r7.setWeight(r8)     // Catch:{ all -> 0x0052 }
            boolean r8 = r5.isItalic()     // Catch:{ all -> 0x0052 }
            if (r8 == 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r4 = r1
        L_0x0035:
            android.graphics.fonts.Font$Builder r4 = r7.setSlant(r4)     // Catch:{ all -> 0x0052 }
            int r5 = r5.getTtcIndex()     // Catch:{ all -> 0x0052 }
            android.graphics.fonts.Font$Builder r4 = r4.setTtcIndex(r5)     // Catch:{ all -> 0x0052 }
            android.graphics.fonts.Font r4 = r4.build()     // Catch:{ all -> 0x0052 }
            if (r3 != 0) goto L_0x004e
            android.graphics.fonts.FontFamily$Builder r5 = new android.graphics.fonts.FontFamily$Builder     // Catch:{ all -> 0x0052 }
            r5.<init>(r4)     // Catch:{ all -> 0x0052 }
            r3 = r5
            goto L_0x001c
        L_0x004e:
            r3.addFont(r4)     // Catch:{ all -> 0x0052 }
            goto L_0x001c
        L_0x0052:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r5 = move-exception
            r6.close()     // Catch:{ all -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ IOException -> 0x005e }
        L_0x005d:
            throw r5     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            int r2 = r2 + 1
            goto L_0x0009
        L_0x0061:
            if (r3 != 0) goto L_0x0064
            return r0
        L_0x0064:
            android.graphics.fonts.FontStyle r9 = new android.graphics.fonts.FontStyle
            r10 = r13 & 1
            if (r10 == 0) goto L_0x006d
            r10 = 700(0x2bc, float:9.81E-43)
            goto L_0x006f
        L_0x006d:
            r10 = 400(0x190, float:5.6E-43)
        L_0x006f:
            r11 = r13 & 2
            if (r11 == 0) goto L_0x0074
            r1 = r4
        L_0x0074:
            r9.<init>(r10, r1)
            android.graphics.Typeface$CustomFallbackBuilder r10 = new android.graphics.Typeface$CustomFallbackBuilder
            android.graphics.fonts.FontFamily r11 = r3.build()
            r10.<init>(r11)
            android.graphics.Typeface$CustomFallbackBuilder r9 = r10.setStyle(r9)
            android.graphics.Typeface r9 = r9.build()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi29Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    /* access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        try {
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(new Font.Builder(resources, i).build()).build()).setStyle(new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0)).build();
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
