package p000;

import java.io.IOException;

/* renamed from: cca */
/* compiled from: PG */
public final class cca extends Exception {

    /* renamed from: a */
    public final int f4040a;

    private cca(Throwable th, int i) {
        super(th);
        this.f4040a = i;
    }

    /* renamed from: a */
    public static cca m4027a(Throwable th) {
        return new cca(th, 1);
    }

    /* renamed from: a */
    public static cca m4026a(InterruptedException interruptedException) {
        return new cca(interruptedException, 3);
    }

    /* renamed from: a */
    public static cca m4025a(IOException iOException) {
        return new cca(iOException, 2);
    }
}
