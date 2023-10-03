package p000;

/* renamed from: hcs */
/* compiled from: PG */
final class hcs extends hdd {

    /* renamed from: a */
    public final ifz f12486a;

    /* renamed from: b */
    public final int f12487b;

    /* renamed from: c */
    public final String f12488c;

    public hcs(ifz ifz, int i, String str) {
        if (ifz != null) {
            this.f12486a = ifz;
            this.f12487b = i;
            this.f12488c = str;
            return;
        }
        throw new NullPointerException("Null event");
    }

    /* renamed from: a */
    public final ifz mo7282a() {
        return this.f12486a;
    }

    /* renamed from: b */
    public final int mo7283b() {
        return this.f12487b;
    }

    /* renamed from: c */
    public final String mo7284c() {
        return this.f12488c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r1 = r4.f12488c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 == r4) goto L_0x0036
            boolean r1 = r5 instanceof p000.hdd
            r2 = 0
            if (r1 == 0) goto L_0x0035
            hdd r5 = (p000.hdd) r5
            ifz r1 = r4.f12486a
            ifz r3 = r5.mo7282a()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0035
            int r1 = r4.f12487b
            int r3 = r5.mo7283b()
            if (r1 != r3) goto L_0x0035
            java.lang.String r1 = r4.f12488c
            if (r1 == 0) goto L_0x002d
            java.lang.String r5 = r5.mo7284c()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0033
            goto L_0x0034
        L_0x002d:
            java.lang.String r5 = r5.mo7284c()
            if (r5 == 0) goto L_0x0034
        L_0x0033:
            goto L_0x0035
        L_0x0034:
            return r0
        L_0x0035:
            return r2
        L_0x0036:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hcs.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        ifz ifz = this.f12486a;
        int i = ifz.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) ifz).mo8862a(ifz);
            ifz.f14173y = i;
        }
        int i2 = (((i ^ 1000003) * 1000003) ^ this.f12487b) * 1000003;
        String str = this.f12488c;
        return (str != null ? str.hashCode() : 0) ^ i2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12486a);
        int i = this.f12487b;
        String str = this.f12488c;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(str).length());
        sb.append("LogWrapper{event=");
        sb.append(valueOf);
        sb.append(", eventCode=");
        sb.append(i);
        sb.append(", accountString=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
