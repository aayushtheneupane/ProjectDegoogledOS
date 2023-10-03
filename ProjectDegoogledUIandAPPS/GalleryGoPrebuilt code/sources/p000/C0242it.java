package p000;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: it */
/* compiled from: PG */
class C0242it extends C0250ja {

    /* renamed from: a */
    private static Class f15052a;

    /* renamed from: b */
    private static Constructor f15053b;

    /* renamed from: c */
    private static Method f15054c;

    /* renamed from: d */
    private static Method f15055d;

    /* renamed from: e */
    private static boolean f15056e = false;

    /* renamed from: a */
    public Typeface mo9109a(Context context, C0231ii iiVar, Resources resources, int i) {
        Object b = m14421b();
        C0232ij[] ijVarArr = iiVar.f14222a;
        int length = ijVarArr.length;
        int i2 = 0;
        while (i2 < length) {
            C0232ij ijVar = ijVarArr[i2];
            File a = C0257jh.m14470a(context);
            if (a == null) {
                return null;
            }
            try {
                if (C0257jh.m14481a(a, resources, ijVar.f14335f)) {
                    String path = a.getPath();
                    int i3 = ijVar.f14331b;
                    boolean z = ijVar.f14332c;
                    m14420a();
                    if (((Boolean) f15054c.invoke(b, new Object[]{path, Integer.valueOf(i3), Boolean.valueOf(z)})).booleanValue()) {
                        a.delete();
                        i2++;
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e2) {
            } catch (Throwable th) {
                a.delete();
                throw th;
            }
            a.delete();
            return null;
        }
        m14420a();
        try {
            Object newInstance = Array.newInstance(f15052a, 1);
            Array.set(newInstance, 0, b);
            return (Typeface) f15055d.invoke((Object) null, new Object[]{newInstance});
        } catch (IllegalAccessException | InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0046, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        p000.ifn.m12935a(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0056, code lost:
        throw r5;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface mo9110a(android.content.Context r3, p000.C0271jv[] r4, int r5) {
        /*
            r2 = this;
            int r0 = r4.length
            r1 = 0
            if (r0 <= 0) goto L_0x0059
            jv r4 = r2.mo9115a((p000.C0271jv[]) r4, (int) r5)
            android.content.ContentResolver r5 = r3.getContentResolver()
            android.net.Uri r4 = r4.f15097a     // Catch:{ IOException -> 0x0058 }
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r4 = r5.openFileDescriptor(r4, r0, r1)     // Catch:{ IOException -> 0x0058 }
            if (r4 == 0) goto L_0x0057
            java.io.File r5 = m14419a(r4)     // Catch:{ all -> 0x004b }
            if (r5 != 0) goto L_0x001d
            goto L_0x002b
        L_0x001d:
            boolean r0 = r5.canRead()     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x002b
            android.graphics.Typeface r3 = android.graphics.Typeface.createFromFile(r5)     // Catch:{ all -> 0x004b }
            r4.close()     // Catch:{ IOException -> 0x0058 }
            return r3
        L_0x002b:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ all -> 0x004b }
            java.io.FileDescriptor r0 = r4.getFileDescriptor()     // Catch:{ all -> 0x004b }
            r5.<init>(r0)     // Catch:{ all -> 0x004b }
            android.graphics.Typeface r3 = super.mo9114a((android.content.Context) r3, (java.io.InputStream) r5)     // Catch:{ all -> 0x003f }
            r5.close()     // Catch:{ all -> 0x004b }
            r4.close()     // Catch:{ IOException -> 0x0058 }
            return r3
        L_0x003f:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r0 = move-exception
            r5.close()     // Catch:{ all -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r5 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r3, (java.lang.Throwable) r5)     // Catch:{ all -> 0x004b }
        L_0x004a:
            throw r0     // Catch:{ all -> 0x004b }
        L_0x004b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004d }
        L_0x004d:
            r5 = move-exception
            r4.close()     // Catch:{ all -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r4 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r3, (java.lang.Throwable) r4)     // Catch:{ IOException -> 0x0058 }
        L_0x0056:
            throw r5     // Catch:{ IOException -> 0x0058 }
        L_0x0057:
            return r1
        L_0x0058:
            r3 = move-exception
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0242it.mo9110a(android.content.Context, jv[], int):android.graphics.Typeface");
    }

    /* renamed from: a */
    private static final File m14419a(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m14420a() {
        Method method;
        Class<?> cls;
        Method method2;
        if (!f15056e) {
            f15056e = true;
            Constructor<?> constructor = null;
            try {
                cls = Class.forName("android.graphics.FontFamily");
                Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
                method = cls.getMethod("addFontWeightStyle", new Class[]{String.class, Integer.TYPE, Boolean.TYPE});
                method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(cls, 1).getClass()});
                constructor = constructor2;
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
                method2 = null;
                cls = null;
                method = null;
            }
            f15053b = constructor;
            f15052a = cls;
            f15054c = method;
            f15055d = method2;
        }
    }

    /* renamed from: b */
    private static Object m14421b() {
        m14420a();
        try {
            return f15053b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
