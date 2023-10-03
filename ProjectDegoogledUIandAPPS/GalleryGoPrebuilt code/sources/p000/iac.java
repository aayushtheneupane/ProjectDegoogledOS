package p000;

import java.util.concurrent.CancellationException;

/* renamed from: iac */
/* compiled from: PG */
final class iac extends iag {

    /* renamed from: a */
    private final CancellationException f13712a;

    private iac(CancellationException cancellationException) {
        super((byte[]) null);
        this.f13712a = cancellationException;
    }

    /* renamed from: a */
    public final boolean mo8319a() {
        return false;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Throwable mo8320b() {
        return this.f13712a;
    }

    /* renamed from: a */
    public static final iac m12563a(CancellationException cancellationException) {
        return new iac(cancellationException);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13712a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
        sb.append("CancelledTaskResult[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
