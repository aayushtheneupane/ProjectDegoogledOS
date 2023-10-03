package p000;

import java.util.NoSuchElementException;

/* renamed from: ihl */
/* compiled from: PG */
final class ihl extends ihm {

    /* renamed from: a */
    private int f14189a = 0;

    /* renamed from: b */
    private final int f14190b = this.f14191c.mo8597a();

    /* renamed from: c */
    private final /* synthetic */ ihw f14191c;

    public ihl(ihw ihw) {
        this.f14191c = ihw;
    }

    public final boolean hasNext() {
        return this.f14189a < this.f14190b;
    }

    /* renamed from: a */
    public final byte mo8591a() {
        int i = this.f14189a;
        if (i < this.f14190b) {
            this.f14189a = i + 1;
            return this.f14191c.mo8599b(i);
        }
        throw new NoSuchElementException();
    }
}
