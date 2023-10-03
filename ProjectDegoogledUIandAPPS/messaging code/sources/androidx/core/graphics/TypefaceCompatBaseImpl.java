package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import androidx.core.content.p022a.C0311d;
import androidx.core.content.p022a.C0312e;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

class TypefaceCompatBaseImpl {
    private static final int INVALID_KEY = 0;
    private static final String TAG = "TypefaceCompatBaseImpl";
    private ConcurrentHashMap mFontFamilies = new ConcurrentHashMap();

    interface StyleExtractor {
        int getWeight(Object obj);

        boolean isItalic(Object obj);
    }

    TypefaceCompatBaseImpl() {
    }

    private void addFontFamily(Typeface typeface, C0311d dVar) {
        long uniqueKey = getUniqueKey(typeface);
        if (uniqueKey != 0) {
            this.mFontFamilies.put(Long.valueOf(uniqueKey), dVar);
        }
    }

    private C0312e findBestEntry(C0311d dVar, int i) {
        return (C0312e) findBestFont(dVar.getEntries(), i, new StyleExtractor() {
            public int getWeight(C0312e eVar) {
                return eVar.getWeight();
            }

            public boolean isItalic(C0312e eVar) {
                return eVar.isItalic();
            }
        });
    }

    private static Object findBestFont(Object[] objArr, int i, StyleExtractor styleExtractor) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        int i3 = Integer.MAX_VALUE;
        Object obj = null;
        for (Object obj2 : objArr) {
            int abs = (Math.abs(styleExtractor.getWeight(obj2) - i2) * 2) + (styleExtractor.isItalic(obj2) == z ? 0 : 1);
            if (obj == null || i3 > abs) {
                obj = obj2;
                i3 = abs;
            }
        }
        return obj;
    }

    private static long getUniqueKey(Typeface typeface) {
        if (typeface == null) {
            return 0;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "Could not retrieve font from family.", e);
            return 0;
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Could not retrieve font from family.", e2);
            return 0;
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, C0311d dVar, Resources resources, int i) {
        C0312e findBestEntry = findBestEntry(dVar, i);
        if (findBestEntry == null) {
            return null;
        }
        Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, findBestEntry.getResourceId(), findBestEntry.getFileName(), i);
        addFontFamily(createFromResourcesFontFile, dVar);
        return createFromResourcesFontFile;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026 A[SYNTHETIC, Splitter:B:17:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002d A[SYNTHETIC, Splitter:B:25:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r3, android.os.CancellationSignal r4, androidx.core.provider.FontsContractCompat.FontInfo[] r5, int r6) {
        /*
            r2 = this;
            int r4 = r5.length
            r0 = 0
            r1 = 1
            if (r4 >= r1) goto L_0x0006
            return r0
        L_0x0006:
            androidx.core.provider.FontsContractCompat$FontInfo r4 = r2.findBestInfo(r5, r6)
            android.content.ContentResolver r5 = r3.getContentResolver()     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            android.net.Uri r4 = r4.getUri()     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            java.io.InputStream r4 = r5.openInputStream(r4)     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            android.graphics.Typeface r2 = r2.createFromInputStream(r3, r4)     // Catch:{ IOException -> 0x002b, all -> 0x0020 }
            if (r4 == 0) goto L_0x001f
            r4.close()     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            return r2
        L_0x0020:
            r2 = move-exception
            r0 = r4
            goto L_0x0024
        L_0x0023:
            r2 = move-exception
        L_0x0024:
            if (r0 == 0) goto L_0x0029
            r0.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            throw r2
        L_0x002a:
            r4 = r0
        L_0x002b:
            if (r4 == 0) goto L_0x0030
            r4.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatBaseImpl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    /* access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!TypefaceCompatUtil.copyToFile(tempFile, inputStream)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(tempFile.getPath());
            tempFile.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!TypefaceCompatUtil.copyToFile(tempFile, resources, i)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(tempFile.getPath());
            tempFile.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    /* access modifiers changed from: protected */
    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        return (FontsContractCompat.FontInfo) findBestFont(fontInfoArr, i, new StyleExtractor() {
            public int getWeight(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.getWeight();
            }

            public boolean isItalic(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.isItalic();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public C0311d getFontFamily(Typeface typeface) {
        long uniqueKey = getUniqueKey(typeface);
        if (uniqueKey == 0) {
            return null;
        }
        return (C0311d) this.mFontFamilies.get(Long.valueOf(uniqueKey));
    }
}
