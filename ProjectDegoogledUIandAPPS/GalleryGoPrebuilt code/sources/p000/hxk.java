package p000;

/* renamed from: hxk */
/* compiled from: PG */
public final class hxk {

    /* renamed from: a */
    public final hys f13585a;

    /* renamed from: b */
    public final String f13586b;

    public hxk(hys hys, String str) {
        this.f13585a = (hys) ife.m12827a((Object) hys, "parser");
        this.f13586b = (String) ife.m12827a((Object) str, "message");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hxk) {
            hxk hxk = (hxk) obj;
            if (!this.f13585a.equals(hxk.f13585a) || !this.f13586b.equals(hxk.f13586b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f13585a.hashCode() ^ this.f13586b.hashCode();
    }
}
