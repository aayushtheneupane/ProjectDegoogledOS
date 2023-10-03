package p000;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.io.Closeable;
import java.util.Arrays;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: fxk */
/* compiled from: PG */
public final class fxk {

    /* renamed from: a */
    private static Thread f10666a;

    /* renamed from: b */
    private static volatile Handler f10667b;

    public fxk(byte[] bArr) {
    }

    /* renamed from: a */
    public static int m9817a(int i, int i2) {
        return (i2 * 31) + i;
    }

    /* renamed from: a */
    public static /* synthetic */ String m9822a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "METERED" : "NOT_ROAMING" : "UNMETERED" : "CONNECTED" : "NOT_REQUIRED";
    }

    public fxk(char[] cArr) {
    }

    public fxk() {
    }

    /* renamed from: a */
    public static ieh m9820a(ieh ieh, Closeable closeable, Executor executor) {
        ieh.mo53a(hmq.m11748a((Runnable) new bpj(ieh)), executor);
        return gpc.m10578a(ieh, closeable).mo6899b();
    }

    /* renamed from: b */
    public static void m9831b(ContentValues contentValues, String str, Optional optional) {
        optional.ifPresent(new bpv(contentValues, str));
    }

    /* renamed from: a */
    public static void m9823a(ContentValues contentValues, String str, Optional optional) {
        optional.ifPresent(new bpu(contentValues, str));
    }

    /* renamed from: c */
    public static void m9837c(ContentValues contentValues, String str, Optional optional) {
        optional.ifPresent(new bpx(contentValues, str));
    }

    /* renamed from: e */
    public static boolean m9840e(Cursor cursor, String str) {
        return m9818a(cursor, str) == 1;
    }

    /* renamed from: d */
    public static byte[] m9839d(Cursor cursor, String str) {
        return cursor.getBlob(cursor.getColumnIndexOrThrow(str));
    }

    /* renamed from: a */
    public static int m9818a(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(str));
    }

    /* renamed from: b */
    public static long m9829b(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(str));
    }

    /* renamed from: c */
    public static String m9835c(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndexOrThrow(str));
    }

    /* renamed from: f */
    public static boolean m9841f(Cursor cursor, String str) {
        return !cursor.isNull(cursor.getColumnIndexOrThrow(str));
    }

    /* renamed from: a */
    public static boolean m9827a(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* renamed from: b */
    public static boolean m9833b(Uri uri) {
        return m9827a(uri) && uri.toString().contains("/video/");
    }

    /* renamed from: b */
    public static boolean m9834b(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? obj == obj2 : Arrays.equals((float[]) obj, (float[]) obj2);
    }

    /* renamed from: a */
    public static boolean m9828a(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }

    /* renamed from: a */
    public static int m9819a(Object obj, int i) {
        return m9817a(obj != null ? obj.hashCode() : 0, i);
    }

    /* renamed from: a */
    public static Object m9821a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* renamed from: c */
    public static void m9836c() {
        if (m9826a()) {
            throw new RuntimeException("Must be called on a background thread");
        }
    }

    /* renamed from: b */
    public static void m9830b() {
        if (!m9826a()) {
            throw new RuntimeException("Must be called on the UI thread");
        }
    }

    /* renamed from: d */
    public static Handler m9838d() {
        if (f10667b == null) {
            f10667b = new Handler(Looper.getMainLooper());
        }
        return f10667b;
    }

    /* renamed from: a */
    public static boolean m9826a() {
        if (f10666a == null) {
            f10666a = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == f10666a;
    }

    /* renamed from: a */
    public static void m9825a(Runnable runnable, long j) {
        m9838d().postDelayed(runnable, j);
    }

    /* renamed from: a */
    public static void m9824a(Runnable runnable) {
        m9838d().post(runnable);
    }

    /* renamed from: b */
    public static void m9832b(Runnable runnable) {
        m9838d().removeCallbacks(runnable);
    }
}
