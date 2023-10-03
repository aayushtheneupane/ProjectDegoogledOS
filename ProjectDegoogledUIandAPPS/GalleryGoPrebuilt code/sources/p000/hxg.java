package p000;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* renamed from: hxg */
/* compiled from: PG */
public abstract class hxg {

    /* renamed from: a */
    public static String f13573a = "hxu";

    /* renamed from: b */
    public static String f13574b = "com.google.common.flogger.backend.google.GooglePlatform";

    /* renamed from: c */
    public static String f13575c = "hye";

    /* renamed from: d */
    public static final String[] f13576d = {"hxu", "com.google.common.flogger.backend.google.GooglePlatform", "hye"};

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract hxa mo8246b(String str);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract hxf mo8247b();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo8248b(String str, Level level, boolean z) {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public hxj mo8249d() {
        return hxj.f13581a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public abstract String mo8251h();

    /* renamed from: a */
    public static hxa m12378a(String str) {
        return hxe.f13572a.mo8246b(str);
    }

    /* renamed from: a */
    public static hxf m12379a() {
        return hxe.f13572a.mo8247b();
    }

    /* renamed from: g */
    public static String m12383g() {
        return hxe.f13572a.mo8251h();
    }

    /* renamed from: e */
    public static long m12382e() {
        return hxe.f13572a.mo8250f();
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public long mo8250f() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }

    /* renamed from: c */
    public static hxj m12381c() {
        return hxe.f13572a.mo8249d();
    }

    /* renamed from: a */
    public static boolean m12380a(String str, Level level, boolean z) {
        return hxe.f13572a.mo8248b(str, level, z);
    }
}
