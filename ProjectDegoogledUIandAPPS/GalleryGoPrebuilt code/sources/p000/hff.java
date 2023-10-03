package p000;

import android.content.Context;

/* renamed from: hff */
/* compiled from: PG */
public final class hff implements hbc {

    /* renamed from: a */
    private final Context f12646a;

    /* renamed from: b */
    private final fsz f12647b;

    /* renamed from: c */
    private final fta f12648c;

    public hff(Context context, fsz fsz, fta fta) {
        this.f12646a = context;
        this.f12647b = fsz;
        this.f12648c = fta;
    }

    /* renamed from: a */
    public final void mo7253a() {
        fsz fsz = this.f12647b;
        Context context = this.f12646a;
        fta fta = this.f12648c;
        Thread.setDefaultUncaughtExceptionHandler(new fsx(context, Thread.getDefaultUncaughtExceptionHandler(), fsz.f10559a, fta));
        Thread.currentThread().setUncaughtExceptionHandler(new fsy(context, Thread.currentThread().getUncaughtExceptionHandler(), fsz.f10559a, fta));
    }
}
