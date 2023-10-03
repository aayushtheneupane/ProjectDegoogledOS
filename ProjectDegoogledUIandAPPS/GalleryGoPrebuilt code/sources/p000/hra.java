package p000;

import java.util.ListIterator;

/* renamed from: hra */
/* compiled from: PG */
final class hra extends hqy implements ListIterator, p003j$.util.ListIterator {

    /* renamed from: b */
    private final /* synthetic */ hrb f13292b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hra(hrb hrb) {
        super(hrb);
        this.f13292b = hrb;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hra(hrb hrb, int i) {
        super(hrb, hrb.mo7751d().listIterator(i));
        this.f13292b = hrb;
    }

    public final void add(Object obj) {
        boolean isEmpty = this.f13292b.isEmpty();
        m11940b().add(obj);
        hrc.m11942a(this.f13292b.f13293e);
        if (isEmpty) {
            this.f13292b.mo7728c();
        }
    }

    /* renamed from: b */
    private final ListIterator m11940b() {
        mo7720a();
        return (ListIterator) this.f13281a;
    }

    public final boolean hasPrevious() {
        return m11940b().hasPrevious();
    }

    public final int nextIndex() {
        return m11940b().nextIndex();
    }

    public final Object previous() {
        return m11940b().previous();
    }

    public final int previousIndex() {
        return m11940b().previousIndex();
    }

    public final void set(Object obj) {
        m11940b().set(obj);
    }
}
