package p000;

import java.io.Writer;

/* renamed from: ks */
/* compiled from: PG */
public final class C0295ks extends Writer {

    /* renamed from: a */
    private final StringBuilder f15152a = new StringBuilder(128);

    public final void close() {
        m14542a();
    }

    public final void flush() {
        m14542a();
    }

    /* renamed from: a */
    private final void m14542a() {
        if (this.f15152a.length() > 0) {
            this.f15152a.toString();
            StringBuilder sb = this.f15152a;
            sb.delete(0, sb.length());
        }
    }

    public final void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c != 10) {
                this.f15152a.append(c);
            } else {
                m14542a();
            }
        }
    }
}
