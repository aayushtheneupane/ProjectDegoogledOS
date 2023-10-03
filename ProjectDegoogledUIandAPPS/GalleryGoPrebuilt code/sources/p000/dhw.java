package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: dhw */
/* compiled from: PG */
final /* synthetic */ class dhw implements hpr {

    /* renamed from: a */
    private final long f6573a;

    public dhw(long j) {
        this.f6573a = j;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        long j = this.f6573a;
        String[] strArr = dib.f6588a;
        for (dhu dhu : (List) obj) {
            if (dhu.f6568b == j) {
                return Optional.m16285of(dhu);
            }
        }
        return Optional.empty();
    }
}
