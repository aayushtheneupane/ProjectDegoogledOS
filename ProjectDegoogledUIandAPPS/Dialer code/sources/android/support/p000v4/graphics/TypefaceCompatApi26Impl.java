package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* renamed from: android.support.v4.graphics.TypefaceCompatApi26Impl */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Method method;
        Method method2;
        Method method3;
        Constructor<?> constructor;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(new Class[0]);
            method3 = obtainAddFontFromAssetManagerMethod(cls2);
            method2 = obtainAddFontFromBufferMethod(cls2);
            method = cls2.getMethod("freeze", new Class[0]);
            method4 = cls2.getMethod("abortCreation", new Class[0]);
            Class<?> cls3 = cls2;
            method5 = obtainCreateFromFamiliesWithDefaultMethod(cls2);
            cls = cls3;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unable to collect necessary methods for class ");
            outline13.append(e.getClass().getName());
            Log.e("TypefaceCompatApi26Impl", outline13.toString(), e);
            method5 = null;
            method4 = null;
            constructor = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        this.mFontFamily = cls;
        this.mFontFamilyCtor = constructor;
        this.mAddFontFromAssetManager = method3;
        this.mAddFontFromBuffer = method2;
        this.mFreeze = method;
        this.mAbortCreation = method4;
        this.mCreateFromFamiliesWithDefault = method5;
    }

    private void abortCreation(Object obj) {
        try {
            this.mAbortCreation.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean addFontFromAssetManager(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.mAddFontFromAssetManager.invoke(obj, new Object[]{context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean freeze(Object obj) {
        try {
            return ((Boolean) this.mFreeze.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    private Object newFamily() {
        try {
            return this.mFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public Typeface createFromFamiliesWithDefault(Object obj) {
        throw null;
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
            int i2 = (i & 1) == 0 ? 400 : 700;
            boolean z = (i & 2) != 0;
            FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = null;
            int i3 = Integer.MAX_VALUE;
            for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry2 : entries) {
                int abs = (Math.abs(fontFileResourceEntry2.getWeight() - i2) * 2) + (fontFileResourceEntry2.isItalic() == z ? 0 : 1);
                if (fontFileResourceEntry == null || i3 > abs) {
                    fontFileResourceEntry = fontFileResourceEntry2;
                    i3 = abs;
                }
            }
            if (fontFileResourceEntry == null) {
                return null;
            }
            return TypefaceCompat.createFromResourcesFontFile(context, resources, fontFileResourceEntry.getResourceId(), fontFileResourceEntry.getFileName(), i);
        }
        Object newFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry3 : fontFamilyFilesResourceEntry.getEntries()) {
            if (!addFontFromAssetManager(context, newFamily, fontFileResourceEntry3.getFileName(), fontFileResourceEntry3.getTtcIndex(), fontFileResourceEntry3.getWeight(), fontFileResourceEntry3.isItalic() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontFileResourceEntry3.getVariationSettings()))) {
                abortCreation(newFamily);
                return null;
            }
        }
        if (!freeze(newFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(newFamily);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0052, code lost:
        throw r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r12, android.os.CancellationSignal r13, android.support.p000v4.provider.FontsContractCompat.FontInfo[] r14, int r15) {
        /*
            r11 = this;
            int r0 = r14.length
            r1 = 1
            r2 = 0
            if (r0 >= r1) goto L_0x0006
            return r2
        L_0x0006:
            boolean r0 = r11.isFontFamilyPrivateAPIAvailable()
            if (r0 != 0) goto L_0x0054
            android.support.v4.provider.FontsContractCompat$FontInfo r11 = r11.findBestInfo(r14, r15)
            android.content.ContentResolver r12 = r12.getContentResolver()
            android.net.Uri r14 = r11.getUri()     // Catch:{ IOException -> 0x0053 }
            java.lang.String r15 = "r"
            android.os.ParcelFileDescriptor r12 = r12.openFileDescriptor(r14, r15, r13)     // Catch:{ IOException -> 0x0053 }
            if (r12 != 0) goto L_0x0026
            if (r12 == 0) goto L_0x0025
            r12.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0025:
            return r2
        L_0x0026:
            android.graphics.Typeface$Builder r13 = new android.graphics.Typeface$Builder     // Catch:{ all -> 0x0047 }
            java.io.FileDescriptor r14 = r12.getFileDescriptor()     // Catch:{ all -> 0x0047 }
            r13.<init>(r14)     // Catch:{ all -> 0x0047 }
            int r14 = r11.getWeight()     // Catch:{ all -> 0x0047 }
            android.graphics.Typeface$Builder r13 = r13.setWeight(r14)     // Catch:{ all -> 0x0047 }
            boolean r11 = r11.isItalic()     // Catch:{ all -> 0x0047 }
            android.graphics.Typeface$Builder r11 = r13.setItalic(r11)     // Catch:{ all -> 0x0047 }
            android.graphics.Typeface r11 = r11.build()     // Catch:{ all -> 0x0047 }
            r12.close()     // Catch:{ IOException -> 0x0053 }
            return r11
        L_0x0047:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r13 = move-exception
            r12.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r12 = move-exception
            r11.addSuppressed(r12)     // Catch:{ IOException -> 0x0053 }
        L_0x0052:
            throw r13     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            return r2
        L_0x0054:
            java.util.Map r12 = android.support.p000v4.provider.FontsContractCompat.prepareFontData(r12, r14, r13)
            java.lang.Object r13 = r11.newFamily()
            int r0 = r14.length
            r3 = 0
            r4 = r3
            r5 = r4
        L_0x0060:
            if (r4 >= r0) goto L_0x00b6
            r6 = r14[r4]
            android.net.Uri r7 = r6.getUri()
            java.lang.Object r7 = r12.get(r7)
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            if (r7 != 0) goto L_0x0071
            goto L_0x00ac
        L_0x0071:
            int r5 = r6.getTtcIndex()
            int r8 = r6.getWeight()
            boolean r6 = r6.isItalic()
            java.lang.reflect.Method r9 = r11.mAddFontFromBuffer     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r10 = 5
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r10[r3] = r7     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r10[r1] = r5     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r5 = 2
            r10[r5] = r2     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r5 = 3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r10[r5] = r7     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r5 = 4
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            r10[r5] = r6     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            java.lang.Object r5 = r9.invoke(r13, r10)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            boolean r5 = r5.booleanValue()     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x00af }
            if (r5 != 0) goto L_0x00ab
            r11.abortCreation(r13)
            return r2
        L_0x00ab:
            r5 = r1
        L_0x00ac:
            int r4 = r4 + 1
            goto L_0x0060
        L_0x00af:
            r11 = move-exception
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            r12.<init>(r11)
            throw r12
        L_0x00b6:
            if (r5 != 0) goto L_0x00bc
            r11.abortCreation(r13)
            return r2
        L_0x00bc:
            boolean r12 = r11.freeze(r13)
            if (r12 != 0) goto L_0x00c3
            return r2
        L_0x00c3:
            android.graphics.Typeface r11 = r11.createFromFamiliesWithDefault(r13)
            android.graphics.Typeface r11 = android.graphics.Typeface.create(r11, r15)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Typeface typeface = null;
        if (!isFontFamilyPrivateAPIAvailable()) {
            File tempFile = PathParser.getTempFile(context);
            if (tempFile != null) {
                try {
                    if (PathParser.copyToFile(tempFile, resources, i)) {
                        typeface = Typeface.createFromFile(tempFile.getPath());
                    }
                } catch (RuntimeException unused) {
                } catch (Throwable th) {
                    tempFile.delete();
                    throw th;
                }
                tempFile.delete();
            }
            return typeface;
        }
        Object newFamily = newFamily();
        if (!addFontFromAssetManager(context, newFamily, str, 0, -1, -1, (FontVariationAxis[]) null)) {
            abortCreation(newFamily);
            return null;
        } else if (!freeze(newFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(newFamily);
        }
    }

    /* access modifiers changed from: protected */
    public Method obtainAddFontFromAssetManagerMethod(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class});
    }

    /* access modifiers changed from: protected */
    public Method obtainAddFontFromBufferMethod(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", new Class[]{ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2});
    }

    /* access modifiers changed from: protected */
    public Method obtainCreateFromFamiliesWithDefaultMethod(Class cls) throws NoSuchMethodException {
        throw null;
    }
}
