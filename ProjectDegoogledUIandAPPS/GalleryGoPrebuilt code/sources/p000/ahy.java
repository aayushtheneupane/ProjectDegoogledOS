package p000;

import java.util.concurrent.ExecutionException;

/* renamed from: ahy */
/* compiled from: PG */
final class ahy implements Runnable {

    /* renamed from: a */
    private final ahw f506a;

    /* renamed from: b */
    private final String f507b;

    /* renamed from: c */
    private final ieh f508c;

    public ahy(ahw ahw, String str, ieh ieh) {
        this.f506a = ahw;
        this.f507b = str;
        this.f508c = ieh;
    }

    public final void run() {
        boolean z;
        try {
            z = ((Boolean) this.f508c.get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            z = true;
        }
        this.f506a.mo503a(this.f507b, z);
    }
}
