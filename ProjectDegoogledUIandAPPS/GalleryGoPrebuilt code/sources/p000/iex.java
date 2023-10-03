package p000;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: iex */
/* compiled from: PG */
public final class iex {

    /* renamed from: a */
    public ThreadFactory f13979a = null;

    /* renamed from: b */
    private String f13980b = null;

    /* renamed from: c */
    private Boolean f13981c = null;

    /* renamed from: a */
    public static ThreadFactory m12778a(iex iex) {
        AtomicLong atomicLong;
        String str = iex.f13980b;
        Boolean bool = iex.f13981c;
        ThreadFactory threadFactory = iex.f13979a;
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }
        if (str != null) {
            atomicLong = new AtomicLong(0);
        } else {
            atomicLong = null;
        }
        return new iew(threadFactory, str, atomicLong, bool);
    }

    /* renamed from: a */
    public static String m12777a(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    /* renamed from: a */
    public final void mo8475a() {
        this.f13981c = true;
    }

    /* renamed from: a */
    public final void mo8476a(String str) {
        m12777a(str, 0);
        this.f13980b = str;
    }
}
