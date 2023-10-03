package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: ja */
/* compiled from: PG */
public class C0250ja {

    /* renamed from: a */
    private final ConcurrentHashMap f15066a = new ConcurrentHashMap();

    /* renamed from: a */
    public Typeface mo9109a(Context context, C0231ii iiVar, Resources resources, int i) {
        C0232ij ijVar = (C0232ij) m14452a((Object[]) iiVar.f14222a, i, (C0248iz) new C0247iy());
        if (ijVar == null) {
            return null;
        }
        Typeface a = C0241is.m14371a(context, resources, ijVar.f14335f, ijVar.f14330a, i);
        long a2 = m14451a(a);
        if (a2 != 0) {
            this.f15066a.put(Long.valueOf(a2), iiVar);
        }
        return a;
    }

    /* renamed from: a */
    public Typeface mo9110a(Context context, C0271jv[] jvVarArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (jvVarArr.length > 0) {
            try {
                inputStream = context.getContentResolver().openInputStream(mo9115a(jvVarArr, i).f15097a);
                try {
                    Typeface a = mo9114a(context, inputStream);
                    C0257jh.m14478a((Closeable) inputStream);
                    return a;
                } catch (IOException e) {
                    C0257jh.m14478a((Closeable) inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    C0257jh.m14478a((Closeable) inputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                inputStream = null;
                C0257jh.m14478a((Closeable) inputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                C0257jh.m14478a((Closeable) inputStream2);
                throw th;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Typeface mo9114a(Context context, InputStream inputStream) {
        File a = C0257jh.m14470a(context);
        if (a == null) {
            return null;
        }
        try {
            if (C0257jh.m14482a(a, inputStream)) {
                Typeface createFromFile = Typeface.createFromFile(a.getPath());
                a.delete();
                return createFromFile;
            }
        } catch (RuntimeException e) {
        } catch (Throwable th) {
            a.delete();
            throw th;
        }
        a.delete();
        return null;
    }

    /* renamed from: a */
    public Typeface mo9111a(Context context, Resources resources, int i, String str, int i2) {
        File a = C0257jh.m14470a(context);
        if (a == null) {
            return null;
        }
        try {
            if (C0257jh.m14481a(a, resources, i)) {
                Typeface createFromFile = Typeface.createFromFile(a.getPath());
                a.delete();
                return createFromFile;
            }
        } catch (RuntimeException e) {
        } catch (Throwable th) {
            a.delete();
            throw th;
        }
        a.delete();
        return null;
    }

    /* renamed from: a */
    private static Object m14452a(Object[] objArr, int i, C0248iz izVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        Object obj = null;
        int i3 = Integer.MAX_VALUE;
        for (Object obj2 : objArr) {
            int abs = Math.abs(izVar.mo9117b(obj2) - i2);
            int i4 = abs + abs + (izVar.mo9116a(obj2) == z ? 0 : 1);
            if (obj == null || i3 > i4) {
                obj = obj2;
                i3 = i4;
            }
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0271jv mo9115a(C0271jv[] jvVarArr, int i) {
        return (C0271jv) m14452a((Object[]) jvVarArr, i, (C0248iz) new C0246ix());
    }

    /* renamed from: a */
    private static long m14451a(Typeface typeface) {
        if (typeface == null) {
            return 0;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (NoSuchFieldException e) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e);
            return 0;
        } catch (IllegalAccessException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0;
        }
    }
}
