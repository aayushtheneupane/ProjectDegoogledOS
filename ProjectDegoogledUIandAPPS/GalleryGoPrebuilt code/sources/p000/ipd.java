package p000;

/* renamed from: ipd */
/* compiled from: PG */
public final class ipd {

    /* renamed from: a */
    public static final hpr f14618a = new ipa();

    /* renamed from: b */
    public static final icf f14619b = new ipb();

    static {
        new ior(ife.m12820a((Object) hvb.f13454a));
    }

    /* renamed from: a */
    public static void m14277a(ioq ioq, boolean z) {
        if (ioq instanceof ioy) {
            ((ioy) ioq).mo9046a(z);
            return;
        }
        String valueOf = String.valueOf(ioq);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 44);
        sb.append("cancel called with non-CancellableProducer: ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [ioy, ioq, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.ioq m14275a(p000.ioq r2, p000.ioz r3) {
        /*
            boolean r0 = r2 instanceof p000.ioy
            if (r0 == 0) goto L_0x0009
            ioq r2 = r2.mo9045a((p000.ioz) r3)
            return r2
        L_0x0009:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r2)
            int r0 = r0.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r0 = r0 + 54
            r1.<init>(r0)
            java.lang.String r0 = "entryPointViewOf called with non-CancellableProducer: "
            r1.append(r0)
            r1.append(r2)
            java.lang.String r2 = r1.toString()
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ipd.m14275a(ioq, ioz):ioq");
    }

    /* renamed from: a */
    public static ioq m14274a(ioq ioq) {
        if (ioq instanceof ioy) {
            return ((ioy) ioq).mo9047ak();
        }
        String valueOf = String.valueOf(ioq);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 70);
        sb.append("nonCancellationPropagatingViewOf called with non-CancellableProducer: ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public static ioq m14276a(iqk iqk) {
        ife.m12898e((Object) iqk);
        return new ipc(iqk);
    }
}
