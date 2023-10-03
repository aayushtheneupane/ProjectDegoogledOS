package p000;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

/* renamed from: bfp */
/* compiled from: PG */
public final class bfp {

    /* renamed from: a */
    public static final char[] f2219a = "0123456789abcdef".toCharArray();

    /* renamed from: b */
    public static final char[] f2220b = new char[64];

    /* renamed from: b */
    public static int m2434b(int i, int i2) {
        return (i2 * 31) + i;
    }

    /* renamed from: c */
    private static boolean m2438c(int i) {
        return i > 0 || i == Integer.MIN_VALUE;
    }

    /* renamed from: b */
    public static void m2435b() {
        if (!m2439d()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        }
    }

    /* renamed from: a */
    public static void m2430a() {
        if (!m2437c()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /* renamed from: b */
    public static boolean m2436b(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        if (obj instanceof axj) {
            return ((axj) obj).mo1713a();
        }
        return obj.equals(obj2);
    }

    /* renamed from: a */
    public static boolean m2432a(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }

    /* renamed from: a */
    public static Queue m2429a(int i) {
        return new ArrayDeque(i);
    }

    /* renamed from: a */
    public static int m2425a(int i, int i2, Bitmap.Config config) {
        int i3 = i * i2;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int i4 = bfo.f2218a[config.ordinal()];
        int i5 = 4;
        if (i4 == 1) {
            i5 = 1;
        } else if (i4 == 2 || i4 == 3) {
            i5 = 2;
        } else if (i4 == 4) {
            i5 = 8;
        }
        return i3 * i5;
    }

    /* renamed from: a */
    public static int m2426a(Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            int i = Build.VERSION.SDK_INT;
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException e) {
                return bitmap.getHeight() * bitmap.getRowBytes();
            }
        } else {
            String valueOf = String.valueOf(bitmap);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            String valueOf2 = String.valueOf(bitmap.getConfig());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 66 + String.valueOf(valueOf2).length());
            sb.append("Cannot obtain size for recycled Bitmap: ");
            sb.append(valueOf);
            sb.append("[");
            sb.append(width);
            sb.append("x");
            sb.append(height);
            sb.append("] ");
            sb.append(valueOf2);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* renamed from: a */
    public static List m2428a(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Object next : collection) {
            if (next != null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static int m2424a(float f) {
        return m2434b(Float.floatToIntBits(f), 17);
    }

    /* renamed from: b */
    public static int m2433b(int i) {
        return m2434b(i, 17);
    }

    /* renamed from: a */
    public static int m2427a(Object obj, int i) {
        return m2434b(obj != null ? obj.hashCode() : 0, i);
    }

    /* renamed from: d */
    public static boolean m2439d() {
        return !m2437c();
    }

    /* renamed from: c */
    public static boolean m2437c() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: a */
    public static boolean m2431a(int i, int i2) {
        return m2438c(i) && m2438c(i2);
    }
}
