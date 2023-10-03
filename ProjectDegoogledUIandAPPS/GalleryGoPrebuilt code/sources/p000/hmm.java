package p000;

import java.util.concurrent.Callable;

/* renamed from: hmm */
/* compiled from: PG */
final class hmm implements Callable {

    /* renamed from: a */
    private final /* synthetic */ hlp f13039a;

    /* renamed from: b */
    private final /* synthetic */ Callable f13040b;

    public hmm(hlp hlp, Callable callable) {
        this.f13039a = hlp;
        this.f13040b = callable;
    }

    public final Object call() {
        hlp b = hnb.m11776b(this.f13039a);
        try {
            return this.f13040b.call();
        } finally {
            hnb.m11776b(b);
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13040b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
