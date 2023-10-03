package p000;

/* renamed from: avv */
/* compiled from: PG */
public final class avv {

    /* renamed from: a */
    private final bfl f1777a = new bfl(1000);

    /* renamed from: b */
    private final C0306lc f1778b = bfx.m2449a(10, new avt());

    /* renamed from: a */
    public final String mo1675a(aqu aqu) {
        String str;
        synchronized (this.f1777a) {
            str = (String) this.f1777a.mo1957b(aqu);
        }
        if (str == null) {
            avu avu = (avu) cns.m4632a((Object) (avu) this.f1778b.mo1971a());
            try {
                aqu.mo1494a(avu.f1775a);
                byte[] digest = avu.f1775a.digest();
                synchronized (bfp.f2220b) {
                    char[] cArr = bfp.f2220b;
                    for (int i = 0; i < digest.length; i++) {
                        byte b = digest[i] & 255;
                        int i2 = i + i;
                        cArr[i2] = bfp.f2219a[b >>> 4];
                        cArr[i2 + 1] = bfp.f2219a[b & 15];
                    }
                    str = new String(cArr);
                }
                this.f1778b.mo1972a(avu);
            } catch (Throwable th) {
                this.f1778b.mo1972a(avu);
                throw th;
            }
        }
        synchronized (this.f1777a) {
            this.f1777a.mo1958b(aqu, str);
        }
        return str;
    }
}
