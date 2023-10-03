package p000;

/* renamed from: anr */
/* compiled from: PG */
public final class anr {

    /* renamed from: a */
    public int f1207a = 0;

    /* renamed from: b */
    private final String f1208b;

    public anr(String str) {
        this.f1208b = str;
    }

    /* renamed from: c */
    public final char mo1287c() {
        if (this.f1207a < this.f1208b.length()) {
            return this.f1208b.charAt(this.f1207a);
        }
        return 0;
    }

    /* renamed from: a */
    public final char mo1283a(int i) {
        if (i < this.f1208b.length()) {
            return this.f1208b.charAt(i);
        }
        return 0;
    }

    /* renamed from: a */
    public final int mo1285a(String str, int i) {
        char a = mo1283a(this.f1207a);
        int i2 = 0;
        boolean z = false;
        while (a >= '0' && a <= '9') {
            i2 = (i2 * 10) + (a - '0');
            int i3 = this.f1207a + 1;
            this.f1207a = i3;
            a = mo1283a(i3);
            z = true;
        }
        if (!z) {
            throw new ang(str, 5);
        } else if (i2 > i) {
            return i;
        } else {
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }
    }

    /* renamed from: b */
    public final boolean mo1286b() {
        return this.f1207a < this.f1208b.length();
    }

    /* renamed from: a */
    public final int mo1284a() {
        return this.f1208b.length();
    }

    /* renamed from: d */
    public final void mo1288d() {
        this.f1207a++;
    }
}
