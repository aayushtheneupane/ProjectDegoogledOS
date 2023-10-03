package p000;

/* renamed from: hwv */
/* compiled from: PG */
public final class hwv {

    /* renamed from: a */
    public static final hwv f13551a = new hwv(0, -1, -1);

    /* renamed from: e */
    private static final long f13552e;

    /* renamed from: b */
    public final int f13553b;

    /* renamed from: c */
    public final int f13554c;

    /* renamed from: d */
    public final int f13555d;

    static {
        long j = 0;
        for (int i = 0; i < 7; i++) {
            j |= (((long) i) + 1) << ((int) (((long) (" #(+,-0".charAt(i) - ' ')) * 3));
        }
        f13552e = j;
    }

    /* renamed from: a */
    private static int m12339a(char c) {
        return ((int) ((f13552e >>> ((c - ' ') * 3)) & 7)) - 1;
    }

    /* renamed from: a */
    public final boolean mo8233a() {
        return this == f13551a;
    }

    /* renamed from: b */
    public final boolean mo8235b() {
        return (this.f13553b & 128) != 0;
    }

    public final int hashCode() {
        return (((this.f13553b * 31) + this.f13554c) * 31) + this.f13555d;
    }

    public hwv(int i, int i2, int i3) {
        this.f13553b = i;
        this.f13554c = i2;
        this.f13555d = i3;
    }

    /* renamed from: a */
    public final StringBuilder mo8232a(StringBuilder sb) {
        if (!mo8233a()) {
            int i = this.f13553b & -129;
            int i2 = 0;
            while (true) {
                int i3 = 1 << i2;
                if (i3 > i) {
                    break;
                }
                if ((i3 & i) != 0) {
                    sb.append(" #(+,-0".charAt(i2));
                }
                i2++;
            }
            int i4 = this.f13554c;
            if (i4 != -1) {
                sb.append(i4);
            }
            if (this.f13555d != -1) {
                sb.append('.');
                sb.append(this.f13555d);
            }
        }
        return sb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hwv) {
            hwv hwv = (hwv) obj;
            return hwv.f13553b == this.f13553b && hwv.f13554c == this.f13554c && hwv.f13555d == this.f13555d;
        }
    }

    /* renamed from: a */
    public static hwv m12342a(String str, int i, int i2, boolean z) {
        if (i == i2 && !z) {
            return f13551a;
        }
        int i3 = !z ? 0 : 128;
        while (i != i2) {
            int i4 = i + 1;
            char charAt = str.charAt(i);
            if (charAt >= ' ' && charAt <= '0') {
                int a = m12339a(charAt);
                if (a >= 0) {
                    int i5 = 1 << a;
                    if ((i3 & i5) == 0) {
                        i3 |= i5;
                        i = i4;
                    } else {
                        throw hyt.m12475a("repeated flag", str, i4 - 1);
                    }
                } else if (charAt == '.') {
                    return new hwv(i3, -1, m12340a(str, i4, i2));
                } else {
                    throw hyt.m12475a("invalid flag", str, i4 - 1);
                }
            } else {
                int i6 = i4 - 1;
                if (charAt <= '9') {
                    int i7 = charAt - '0';
                    while (i4 != i2) {
                        int i8 = i4 + 1;
                        char charAt2 = str.charAt(i4);
                        if (charAt2 == '.') {
                            return new hwv(i3, i7, m12340a(str, i8, i2));
                        }
                        char c = (char) (charAt2 - '0');
                        if (c < 10) {
                            i7 = (i7 * 10) + c;
                            if (i7 <= 999999) {
                                i4 = i8;
                            } else {
                                throw hyt.m12476a("width too large", str, i6, i2);
                            }
                        } else {
                            throw hyt.m12475a("invalid width character", str, i8 - 1);
                        }
                    }
                    return new hwv(i3, i7, -1);
                }
                throw hyt.m12475a("invalid flag", str, i6);
            }
        }
        return new hwv(i3, -1, -1);
    }

    /* renamed from: a */
    private static int m12340a(String str, int i, int i2) {
        if (i != i2) {
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                char charAt = (char) (str.charAt(i4) - '0');
                if (charAt < 10) {
                    i3 = (i3 * 10) + charAt;
                    if (i3 <= 999999) {
                        i4++;
                    } else {
                        throw hyt.m12476a("precision too large", str, i, i2);
                    }
                } else {
                    throw hyt.m12475a("invalid precision character", str, i4);
                }
            }
            if (i3 != 0 || i2 == i + 1) {
                return i3;
            }
            throw hyt.m12476a("invalid precision", str, i, i2);
        }
        throw hyt.m12475a("missing precision", str, i - 1);
    }

    /* renamed from: a */
    static int m12341a(String str, boolean z) {
        int i = 0;
        int i2 = !z ? 0 : 128;
        while (i < str.length()) {
            int a = m12339a(str.charAt(i));
            if (a >= 0) {
                i2 |= 1 << a;
                i++;
            } else {
                throw new IllegalArgumentException(str.length() == 0 ? new String("invalid flags: ") : "invalid flags: ".concat(str));
            }
        }
        return i2;
    }

    /* renamed from: a */
    public final boolean mo8234a(int i, boolean z) {
        int i2;
        if (mo8233a()) {
            return true;
        }
        int i3 = this.f13553b;
        if (((i ^ -1) & i3) != 0) {
            return false;
        }
        if (!z && this.f13555d != -1) {
            return false;
        }
        int i4 = this.f13554c;
        if ((i3 & 9) == 9 || (i2 = i3 & 96) == 96) {
            return false;
        }
        return i2 == 0 || i4 != -1;
    }
}
