package p000;

/* renamed from: htc */
/* compiled from: PG */
final class htc extends hsf {

    /* renamed from: a */
    public final hsu f13369a;

    public htc(hsu hsu) {
        this.f13369a = hsu;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        throw null;
    }

    /* renamed from: g */
    public final hso mo7884g() {
        return new hta(this.f13369a.entrySet().mo7884g());
    }

    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        hvr a = iterator();
        while (a.hasNext()) {
            if (obj.equals(a.next())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final hvr iterator() {
        return new hsz(this);
    }

    public final int size() {
        return this.f13369a.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new htb(this.f13369a);
    }
}
