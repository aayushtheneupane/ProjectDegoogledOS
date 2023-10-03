package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.content.p022a.C0310c;
import androidx.core.content.p022a.C0311d;
import androidx.core.content.p022a.C0313f;
import androidx.core.content.p022a.C0318k;
import androidx.core.provider.FontsContractCompat;
import p000a.p005b.C0020g;

@SuppressLint({"NewApi"})
public class TypefaceCompat {
    private static final C0020g sTypefaceCache = new C0020g(16);
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
        } else {
            sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        }
    }

    private TypefaceCompat() {
    }

    public static Typeface create(Context context, Typeface typeface, int i) {
        if (context != null) {
            int i2 = Build.VERSION.SDK_INT;
            return Typeface.create(typeface, i);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fontInfoArr, i);
    }

    public static Typeface createFromResourcesFamilyXml(Context context, C0310c cVar, Resources resources, int i, int i2, C0318k kVar, Handler handler, boolean z) {
        Typeface typeface;
        if (cVar instanceof C0313f) {
            C0313f fVar = (C0313f) cVar;
            boolean z2 = false;
            if (!z ? kVar == null : fVar.mo3404tc() == 0) {
                z2 = true;
            }
            typeface = FontsContractCompat.getFontSync(context, fVar.getRequest(), kVar, handler, z2, z ? fVar.getTimeout() : -1, i2, z);
        } else {
            typeface = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (C0311d) cVar, resources, i2);
            if (kVar != null) {
                if (typeface != null) {
                    kVar.callbackSuccessAsync(typeface, handler);
                } else {
                    kVar.callbackFailAsync(-3, handler);
                }
            }
        }
        if (typeface != null) {
            sTypefaceCache.put(createResourceUid(resources, i, i2), typeface);
        }
        return typeface;
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Typeface createFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, str, i2);
        if (createFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources, i, i2), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    private static String createResourceUid(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface findFromCache(Resources resources, int i, int i2) {
        return (Typeface) sTypefaceCache.get(createResourceUid(resources, i, i2));
    }

    private static Typeface getBestFontFromFamily(Context context, Typeface typeface, int i) {
        C0311d fontFamily = sTypefaceCompatImpl.getFontFamily(typeface);
        if (fontFamily == null) {
            return null;
        }
        return sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, fontFamily, context.getResources(), i);
    }
}
