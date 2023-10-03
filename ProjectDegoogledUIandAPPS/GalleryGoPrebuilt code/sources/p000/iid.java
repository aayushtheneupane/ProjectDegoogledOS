package p000;

import java.io.IOException;

/* renamed from: iid */
/* compiled from: PG */
public final class iid extends IOException {
    public static final long serialVersionUID = -6947486886997889499L;

    iid() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public iid(java.lang.String r3, java.lang.Throwable r4) {
        /*
            r2 = this;
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r0 = r3.length()
            java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
            if (r0 != 0) goto L_0x0012
            java.lang.String r3 = new java.lang.String
            r3.<init>(r1)
            goto L_0x0016
        L_0x0012:
            java.lang.String r3 = r1.concat(r3)
        L_0x0016:
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.iid.<init>(java.lang.String, java.lang.Throwable):void");
    }

    public iid(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
