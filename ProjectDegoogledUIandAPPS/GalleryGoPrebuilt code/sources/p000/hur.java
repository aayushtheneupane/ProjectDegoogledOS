package p000;

import java.util.Iterator;

/* renamed from: hur */
/* compiled from: PG */
final class hur extends hvm {

    /* renamed from: a */
    private final /* synthetic */ hrj f13422a;

    public hur(hrj hrj) {
        this.f13422a = hrj;
    }

    public final Iterator iterator() {
        return this.f13422a.mo7773b();
    }

    public final int size() {
        return this.f13422a.mo7774c();
    }

    public hur() {
    }

    public final void clear() {
        this.f13422a.clear();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof hun) {
            hun hun = (hun) obj;
            if (hun.mo8080b() <= 0 || this.f13422a.mo7769a(hun.mo8079a()) != hun.mo8080b()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof hun)) {
            return false;
        }
        hun hun = (hun) obj;
        Object a = hun.mo8079a();
        int b = hun.mo8080b();
        if (b != 0) {
            return this.f13422a.mo7775c(a, b);
        }
        return false;
    }
}
