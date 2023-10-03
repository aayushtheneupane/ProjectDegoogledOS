package p000;

import android.os.SystemClock;
import java.util.Collections;
import java.util.List;

/* renamed from: fos */
/* compiled from: PG */
public final class fos {

    /* renamed from: a */
    public final String f10164a;

    /* renamed from: b */
    public final long f10165b;

    /* renamed from: c */
    public final long f10166c;

    /* renamed from: d */
    public final long f10167d;

    /* renamed from: e */
    public volatile List f10168e;

    /* renamed from: f */
    public final int f10169f = 1;

    /* renamed from: g */
    public final int f10170g;

    static {
        new fos("", SystemClock.elapsedRealtime(), -1, Thread.currentThread().getId(), 3);
    }

    private fos(String str, long j, long j2, long j3, int i) {
        this.f10164a = str;
        this.f10165b = j;
        this.f10166c = j2;
        this.f10167d = j3;
        this.f10170g = i;
        this.f10168e = Collections.emptyList();
    }

    /* renamed from: a */
    public static fos m9330a(fmt fmt, String str, long j, long j2, long j3, int i) {
        ife.m12898e((Object) fmt);
        return new fos(str, j, j2, j3, i);
    }
}
