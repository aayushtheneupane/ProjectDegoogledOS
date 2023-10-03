package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: hjo */
/* compiled from: PG */
public final class hjo {

    /* renamed from: a */
    public static final fuk f12864a = new fuk("tiktok_sync_debug", (byte[]) null);

    /* renamed from: a */
    public static long m11591a(TimeUnit timeUnit) {
        if (foj.m9314a(f12864a)) {
            return timeUnit.convert(5, TimeUnit.SECONDS);
        }
        return timeUnit.convert(15, TimeUnit.MINUTES);
    }
}
