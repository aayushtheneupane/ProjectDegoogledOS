package p000;

import java.util.Set;

/* renamed from: icc */
/* compiled from: PG */
final class icc extends ica {
    private icc() {
    }

    public /* synthetic */ icc(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo8372a(icd icd, Set set) {
        synchronized (icd) {
            if (icd.seenExceptions == null) {
                icd.seenExceptions = set;
            }
        }
    }

    /* renamed from: a */
    public final int mo8371a(icd icd) {
        int i;
        synchronized (icd) {
            i = icd.remaining - 1;
            icd.remaining = i;
        }
        return i;
    }
}
