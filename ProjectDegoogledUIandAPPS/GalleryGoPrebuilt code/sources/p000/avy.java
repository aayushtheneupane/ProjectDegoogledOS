package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: avy */
/* compiled from: PG */
public final class avy implements ThreadFactory {

    /* renamed from: a */
    public final boolean f1784a;

    /* renamed from: b */
    private final String f1785b;

    /* renamed from: c */
    private int f1786c;

    public avy(String str, boolean z) {
        this.f1785b = str;
        this.f1784a = z;
    }

    public final synchronized Thread newThread(Runnable runnable) {
        avx avx;
        String str = this.f1785b;
        int i = this.f1786c;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 25);
        sb.append("glide-");
        sb.append(str);
        sb.append("-thread-");
        sb.append(i);
        avx = new avx(this, runnable, sb.toString());
        this.f1786c++;
        return avx;
    }
}
