package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import java.util.Arrays;

/* renamed from: eus */
/* compiled from: PG */
public final class eus extends eqv implements Comparable {
    public static final Parcelable.Creator CREATOR = new eut();

    /* renamed from: a */
    public final String f9068a;

    /* renamed from: b */
    public final long f9069b;

    /* renamed from: c */
    public final boolean f9070c;

    /* renamed from: d */
    public final double f9071d;

    /* renamed from: e */
    public final String f9072e;

    /* renamed from: f */
    public final byte[] f9073f;

    /* renamed from: g */
    public final int f9074g;

    /* renamed from: h */
    private final int f9075h;

    /* renamed from: a */
    private static int m8182a(int i, int i2) {
        if (i >= i2) {
            return i != i2 ? 1 : 0;
        }
        return -1;
    }

    public eus(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.f9068a = str;
        this.f9069b = j;
        this.f9070c = z;
        this.f9071d = d;
        this.f9072e = str2;
        this.f9073f = bArr;
        this.f9074g = i;
        this.f9075h = i2;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        eus eus = (eus) obj;
        int compareTo = this.f9068a.compareTo(eus.f9068a);
        if (compareTo != 0) {
            return compareTo;
        }
        int a = m8182a(this.f9074g, eus.f9074g);
        if (a != 0) {
            return a;
        }
        int i = this.f9074g;
        if (i != 1) {
            if (i == 2) {
                boolean z = this.f9070c;
                if (z != eus.f9070c) {
                    if (z) {
                        return 1;
                    }
                    return -1;
                }
            } else if (i == 3) {
                return Double.compare(this.f9071d, eus.f9071d);
            } else {
                if (i == 4) {
                    String str = this.f9072e;
                    String str2 = eus.f9072e;
                    if (str != str2) {
                        if (str != null) {
                            if (str2 != null) {
                                return str.compareTo(str2);
                            }
                        }
                        return -1;
                    }
                } else if (i == 5) {
                    byte[] bArr = this.f9073f;
                    byte[] bArr2 = eus.f9073f;
                    if (bArr != bArr2) {
                        if (bArr != null) {
                            if (bArr2 != null) {
                                for (int i2 = 0; i2 < Math.min(this.f9073f.length, eus.f9073f.length); i2++) {
                                    int i3 = this.f9073f[i2] - eus.f9073f[i2];
                                    if (i3 != 0) {
                                        return i3;
                                    }
                                }
                                return m8182a(this.f9073f.length, eus.f9073f.length);
                            }
                        }
                        return -1;
                    }
                } else {
                    StringBuilder sb = new StringBuilder(31);
                    sb.append("Invalid enum value: ");
                    sb.append(i);
                    throw new AssertionError(sb.toString());
                }
            }
            return 1;
        }
        long j = this.f9069b;
        long j2 = eus.f9069b;
        if (j >= j2) {
            if (j == j2) {
                return 0;
            }
            return 1;
        }
        return -1;
        return 0;
    }

    public final boolean equals(Object obj) {
        int i;
        if (!(obj instanceof eus)) {
            return false;
        }
        eus eus = (eus) obj;
        if (!fej.m8701a((Object) this.f9068a, (Object) eus.f9068a) || (i = this.f9074g) != eus.f9074g || this.f9075h != eus.f9075h) {
            return false;
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return fej.m8701a((Object) this.f9072e, (Object) eus.f9072e);
                    }
                    if (i == 5) {
                        return Arrays.equals(this.f9073f, eus.f9073f);
                    }
                    StringBuilder sb = new StringBuilder(31);
                    sb.append("Invalid enum value: ");
                    sb.append(i);
                    throw new AssertionError(sb.toString());
                } else if (this.f9071d != eus.f9071d) {
                    return false;
                } else {
                    return true;
                }
            } else if (this.f9070c != eus.f9070c) {
                return false;
            } else {
                return true;
            }
        } else if (this.f9069b != eus.f9069b) {
            return false;
        } else {
            return true;
        }
    }

    public final String toString() {
        return mo5279a(new StringBuilder());
    }

    /* renamed from: a */
    public final String mo5279a(StringBuilder sb) {
        sb.append("Flag(");
        sb.append(this.f9068a);
        sb.append(", ");
        int i = this.f9074g;
        if (i == 1) {
            sb.append(this.f9069b);
        } else if (i == 2) {
            sb.append(this.f9070c);
        } else if (i == 3) {
            sb.append(this.f9071d);
        } else if (i == 4) {
            sb.append("'");
            sb.append(this.f9072e);
            sb.append("'");
        } else if (i != 5) {
            String str = this.f9068a;
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 27);
            sb2.append("Invalid type: ");
            sb2.append(str);
            sb2.append(", ");
            sb2.append(i);
            throw new AssertionError(sb2.toString());
        } else if (this.f9073f != null) {
            sb.append("'");
            sb.append(Base64.encodeToString(this.f9073f, 3));
            sb.append("'");
        } else {
            sb.append("null");
        }
        sb.append(", ");
        sb.append(this.f9074g);
        sb.append(", ");
        sb.append(this.f9075h);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f9068a);
        abj.m94a(parcel, 3, this.f9069b);
        abj.m100a(parcel, 4, this.f9070c);
        double d = this.f9071d;
        abj.m93a(parcel, 5, 8);
        parcel.writeDouble(d);
        abj.m98a(parcel, 6, this.f9072e);
        abj.m101a(parcel, 7, this.f9073f);
        abj.m114b(parcel, 8, this.f9074g);
        abj.m114b(parcel, 9, this.f9075h);
        abj.m92a(parcel, a);
    }
}
