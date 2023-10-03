package p000;

import java.util.List;

/* renamed from: hsl */
/* compiled from: PG */
final class hsl extends hso {

    /* renamed from: a */
    private final transient hso f13347a;

    public hsl(hso hso) {
        this.f13347a = hso;
    }

    /* renamed from: e */
    public final hso mo7910e() {
        return this.f13347a;
    }

    public final boolean contains(Object obj) {
        return this.f13347a.contains(obj);
    }

    public final Object get(int i) {
        ife.m12873b(i, size());
        return this.f13347a.get(m12022a(i));
    }

    public final int indexOf(Object obj) {
        int lastIndexOf = this.f13347a.lastIndexOf(obj);
        if (lastIndexOf < 0) {
            return -1;
        }
        return m12022a(lastIndexOf);
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return this.f13347a.mo7885h();
    }

    public final int lastIndexOf(Object obj) {
        int indexOf = this.f13347a.indexOf(obj);
        if (indexOf < 0) {
            return -1;
        }
        return m12022a(indexOf);
    }

    /* renamed from: a */
    private final int m12022a(int i) {
        return (size() - 1) - i;
    }

    /* renamed from: b */
    private final int m12023b(int i) {
        return size() - i;
    }

    public final int size() {
        return this.f13347a.size();
    }

    /* renamed from: a */
    public final hso mo7909a(int i, int i2) {
        ife.m12874b(i, i2, size());
        return this.f13347a.subList(m12023b(i2), m12023b(i)).mo7910e();
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
