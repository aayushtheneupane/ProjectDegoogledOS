package p000;

import java.util.Arrays;
import java.util.Set;

/* renamed from: htk */
/* compiled from: PG */
public abstract class htk extends htl implements huo {

    /* renamed from: a */
    private transient hso f13382a;

    /* renamed from: b */
    private transient hto f13383b;

    @Deprecated
    /* renamed from: a */
    public final int mo7770a(Object obj, int i) {
        throw null;
    }

    /* renamed from: a */
    public abstract hun mo7987a(int i);

    @Deprecated
    /* renamed from: b */
    public final int mo7772b(Object obj, int i) {
        throw null;
    }

    @Deprecated
    /* renamed from: c */
    public final boolean mo7775c(Object obj, int i) {
        throw null;
    }

    /* renamed from: e */
    public /* bridge */ /* synthetic */ Set mo7794e() {
        throw null;
    }

    /* renamed from: i */
    public abstract hto mo7990i();

    /* access modifiers changed from: package-private */
    public abstract Object writeReplace();

    /* renamed from: g */
    public final hso mo7884g() {
        hso hso = this.f13382a;
        if (hso != null) {
            return hso;
        }
        hso b = !isEmpty() ? hso.m12044b(toArray()) : hso.m12047f();
        this.f13382a = b;
        return b;
    }

    /* renamed from: k */
    public static hth m12102k() {
        return new hth();
    }

    public final boolean contains(Object obj) {
        return mo7769a(obj) > 0;
    }

    /* renamed from: a */
    public final int mo7875a(Object[] objArr, int i) {
        hvr a = mo7796f().iterator();
        while (a.hasNext()) {
            hun hun = (hun) a.next();
            Arrays.fill(objArr, i, hun.mo8080b() + i, hun.mo8079a());
            i += hun.mo8080b();
        }
        return i;
    }

    /* renamed from: j */
    public final hto mo7796f() {
        hto hto = this.f13383b;
        if (hto == null) {
            if (!isEmpty()) {
                hto = new hti(this);
            } else {
                hto = hvf.f13465a;
            }
            this.f13383b = hto;
        }
        return hto;
    }

    public final boolean equals(Object obj) {
        return ife.m12853a((huo) this, obj);
    }

    public final int hashCode() {
        return ife.m12809a((Set) mo7796f());
    }

    /* renamed from: a */
    public final hvr iterator() {
        return new htg(mo7796f().iterator());
    }

    public final String toString() {
        return mo7796f().toString();
    }
}
