package p000;

/* renamed from: hwn */
/* compiled from: PG */
public final class hwn {

    /* renamed from: a */
    public final String f13524a;

    /* renamed from: b */
    private final Class f13525b;

    private hwn(String str, Class cls) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("identifier must not be empty");
        } else if (!ife.m12852a(str.charAt(0))) {
            throw new IllegalArgumentException(str.length() == 0 ? new String("identifier must start with an ASCII letter: ") : "identifier must start with an ASCII letter: ".concat(str));
        } else {
            for (int i = 1; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!ife.m12852a(charAt) && ((charAt < '0' || charAt > '9') && charAt != '_')) {
                    throw new IllegalArgumentException(str.length() == 0 ? new String("identifier must contain only ASCII letters, digits or underscore: ") : "identifier must contain only ASCII letters, digits or underscore: ".concat(str));
                }
            }
            this.f13524a = str;
            this.f13525b = (Class) ife.m12827a((Object) cls, "class");
        }
    }

    /* renamed from: a */
    public final Object mo8230a(Object obj) {
        return this.f13525b.cast(obj);
    }

    /* renamed from: a */
    public static hwn m12331a(String str, Class cls) {
        return new hwn(str, cls);
    }

    public final String toString() {
        String name = getClass().getName();
        String str = this.f13524a;
        String name2 = this.f13525b.getName();
        int length = String.valueOf(name).length();
        StringBuilder sb = new StringBuilder(length + 3 + str.length() + String.valueOf(name2).length());
        sb.append(name);
        sb.append("/");
        sb.append(str);
        sb.append("[");
        sb.append(name2);
        sb.append("]");
        return sb.toString();
    }
}
