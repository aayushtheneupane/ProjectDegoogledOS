package p000;

/* renamed from: bfn */
/* compiled from: PG */
public final class bfn {

    /* renamed from: a */
    private Class f2215a;

    /* renamed from: b */
    private Class f2216b;

    /* renamed from: c */
    private Class f2217c;

    public bfn() {
    }

    public bfn(Class cls, Class cls2, Class cls3) {
        mo1966a(cls, cls2, cls3);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bfn bfn = (bfn) obj;
        return this.f2215a.equals(bfn.f2215a) && this.f2216b.equals(bfn.f2216b) && bfp.m2432a((Object) this.f2217c, (Object) bfn.f2217c);
    }

    public final int hashCode() {
        int hashCode = ((this.f2215a.hashCode() * 31) + this.f2216b.hashCode()) * 31;
        Class cls = this.f2217c;
        return hashCode + (cls != null ? cls.hashCode() : 0);
    }

    /* renamed from: a */
    public final void mo1966a(Class cls, Class cls2, Class cls3) {
        this.f2215a = cls;
        this.f2216b = cls2;
        this.f2217c = cls3;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f2215a);
        String valueOf2 = String.valueOf(this.f2216b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30 + String.valueOf(valueOf2).length());
        sb.append("MultiClassKey{first=");
        sb.append(valueOf);
        sb.append(", second=");
        sb.append(valueOf2);
        sb.append('}');
        return sb.toString();
    }
}
