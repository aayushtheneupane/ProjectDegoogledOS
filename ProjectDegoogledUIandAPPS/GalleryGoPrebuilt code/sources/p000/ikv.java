package p000;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: ikv */
/* compiled from: PG */
final class ikv implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final ArrayDeque f14411a;

    /* renamed from: b */
    private ihs f14412b;

    public /* synthetic */ ikv(ihw ihw) {
        if (ihw instanceof ikx) {
            ikx ikx = (ikx) ihw;
            ArrayDeque arrayDeque = new ArrayDeque(ikx.f14424g);
            this.f14411a = arrayDeque;
            arrayDeque.push(ikx);
            int[] iArr = ikx.f14420a;
            this.f14412b = m13872a(ikx.f14422e);
            return;
        }
        this.f14411a = null;
        this.f14412b = (ihs) ihw;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f14412b != null;
    }

    /* renamed from: a */
    private final ihs m13872a(ihw ihw) {
        while (ihw instanceof ikx) {
            ikx ikx = (ikx) ihw;
            this.f14411a.push(ikx);
            int[] iArr = ikx.f14420a;
            ihw = ikx.f14422e;
        }
        return (ihs) ihw;
    }

    /* renamed from: a */
    public final ihs next() {
        ihs ihs;
        ihs ihs2 = this.f14412b;
        if (ihs2 != null) {
            do {
                ArrayDeque arrayDeque = this.f14411a;
                ihs = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    this.f14412b = ihs;
                } else {
                    int[] iArr = ikx.f14420a;
                    ihs = m13872a(((ikx) this.f14411a.pop()).f14423f);
                }
            } while (ihs.mo8623i());
            this.f14412b = ihs;
            return ihs2;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
