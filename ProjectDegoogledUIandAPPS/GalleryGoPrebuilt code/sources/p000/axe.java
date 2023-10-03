package p000;

/* renamed from: axe */
/* compiled from: PG */
final class axe implements axc {

    /* renamed from: a */
    private final String f1818a;

    public axe(String str) {
        this.f1818a = str;
    }

    /* renamed from: a */
    public final String mo1706a() {
        return this.f1818a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof axe) {
            return this.f1818a.equals(((axe) obj).f1818a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1818a.hashCode();
    }

    public final String toString() {
        String str = this.f1818a;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 29);
        sb.append("StringHeaderFactory{value='");
        sb.append(str);
        sb.append("'}");
        return sb.toString();
    }
}
