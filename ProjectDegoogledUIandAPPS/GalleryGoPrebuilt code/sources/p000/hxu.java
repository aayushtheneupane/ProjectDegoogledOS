package p000;

import android.os.Build;
import android.util.Log;
import dalvik.system.VMStack;

/* renamed from: hxu */
/* compiled from: PG */
public final class hxu extends hxg {

    /* renamed from: e */
    public static final boolean f13594e = hxt.m12418a();

    /* renamed from: f */
    public static final boolean f13595f;

    /* renamed from: g */
    public static final hxf f13596g = new hxs();

    static {
        boolean z = true;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        }
        f13595f = z;
        Log.class.getName();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public hxf mo8247b() {
        return f13596g;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public String mo8251h() {
        return "platform: Android";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public hxa mo8246b(String str) {
        if (hxx.f13600b.get() != null) {
            return ((hxp) hxx.f13600b.get()).mo7281a(str);
        }
        hxx hxx = new hxx(str.replace('$', '.'));
        hxv.f13597a.offer(hxx);
        if (hxx.f13600b.get() == null) {
            return hxx;
        }
        hxx.m12427b();
        return hxx;
    }

    /* renamed from: l */
    static Class m12421l() {
        return VMStack.getStackClass2();
    }

    /* renamed from: j */
    static boolean m12419j() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", new Class[0]);
            return hxt.class.getName().equals(m12420k());
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: k */
    static String m12420k() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable th) {
            return null;
        }
    }
}
