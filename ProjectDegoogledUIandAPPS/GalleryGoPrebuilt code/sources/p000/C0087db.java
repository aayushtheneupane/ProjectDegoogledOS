package p000;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Deprecated
/* renamed from: db */
/* compiled from: PG */
public final class C0087db extends Number implements Comparable {
    public static final long serialVersionUID = -4756200506571685661L;
    @Deprecated

    /* renamed from: a */
    public final double f6159a;
    @Deprecated

    /* renamed from: b */
    public final int f6160b;
    @Deprecated

    /* renamed from: c */
    public final int f6161c;
    @Deprecated

    /* renamed from: d */
    public final long f6162d;
    @Deprecated

    /* renamed from: e */
    public final long f6163e;
    @Deprecated

    /* renamed from: f */
    public final long f6164f;
    @Deprecated

    /* renamed from: g */
    private final boolean f6165g;

    @Deprecated
    public final double doubleValue() {
        return this.f6165g ? -this.f6159a : this.f6159a;
    }

    @Deprecated
    public final float floatValue() {
        return (float) this.f6159a;
    }

    @Deprecated
    public final int hashCode() {
        return (int) (this.f6162d + ((long) ((this.f6160b + ((int) (this.f6159a * 37.0d))) * 37)));
    }

    @Deprecated
    public final int intValue() {
        return (int) this.f6164f;
    }

    @Deprecated
    public final long longValue() {
        return this.f6164f;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0087db(double r11) {
        /*
            r10 = this;
            boolean r0 = java.lang.Double.isInfinite(r11)
            r1 = 0
            if (r0 != 0) goto L_0x0084
            boolean r0 = java.lang.Double.isNaN(r11)
            if (r0 != 0) goto L_0x0084
            r2 = 0
            int r0 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0015
            double r2 = -r11
            goto L_0x0016
        L_0x0015:
            r2 = r11
        L_0x0016:
            r4 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x0041
            r4 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r2 = r2 * r4
            long r2 = (long) r2
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r2 % r4
            r0 = 6
            r4 = 10
        L_0x002e:
            if (r0 <= 0) goto L_0x0084
            long r5 = (long) r4
            long r5 = r2 % r5
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x003f
            int r4 = r4 * 10
            int r0 = r0 + -1
            goto L_0x002e
        L_0x003f:
            r1 = r0
            goto L_0x0085
        L_0x0041:
            java.util.Locale r0 = java.util.Locale.ENGLISH
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r4[r1] = r2
            java.lang.String r2 = "%1.15e"
            java.lang.String r0 = java.lang.String.format(r0, r2, r4)
            r2 = 101(0x65, float:1.42E-43)
            int r2 = r0.lastIndexOf(r2)
            int r3 = r2 + 1
            char r4 = r0.charAt(r3)
            r5 = 43
            if (r4 != r5) goto L_0x0064
            int r3 = r3 + 1
        L_0x0064:
            int r4 = r2 + -2
            java.lang.String r3 = r0.substring(r3)
            int r3 = java.lang.Integer.parseInt(r3)
            int r4 = r4 - r3
            if (r4 < 0) goto L_0x0084
            int r2 = r2 + -1
            r1 = r4
        L_0x0074:
            if (r1 <= 0) goto L_0x0085
            char r3 = r0.charAt(r2)
            r4 = 48
            if (r3 != r4) goto L_0x0084
            int r1 = r1 + -1
            int r2 = r2 + -1
            goto L_0x0074
        L_0x0084:
        L_0x0085:
            r10.<init>(r11, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0087db.<init>(double):void");
    }

    @Deprecated
    private C0087db(double d, int i) {
        int i2;
        if (i != 0) {
            double d2 = d < 0.0d ? -d : d;
            int pow = (int) Math.pow(10.0d, (double) i);
            double d3 = (double) pow;
            Double.isNaN(d3);
            i2 = (int) (Math.round(d2 * d3) % ((long) pow));
        } else {
            i2 = 0;
        }
        long j = (long) i2;
        boolean z = d < 0.0d;
        this.f6165g = z;
        this.f6159a = z ? -d : d;
        this.f6160b = i;
        this.f6162d = j;
        this.f6164f = d <= 1.0E18d ? (long) d : 1000000000000000000L;
        if (j == 0) {
            this.f6163e = 0;
            this.f6161c = 0;
        } else {
            int i3 = i;
            while (j % 10 == 0) {
                j /= 10;
                i3--;
            }
            this.f6163e = j;
            this.f6161c = i3;
        }
        Math.pow(10.0d, (double) i);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0087db(java.lang.String r4) {
        /*
            r3 = this;
            double r0 = java.lang.Double.parseDouble(r4)
            java.lang.String r4 = r4.trim()
            r2 = 46
            int r2 = r4.indexOf(r2)
            int r2 = r2 + 1
            if (r2 == 0) goto L_0x0018
            int r4 = r4.length()
            int r4 = r4 - r2
            goto L_0x001a
        L_0x0018:
            r4 = 0
        L_0x001a:
            r3.<init>(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0087db.<init>(java.lang.String):void");
    }

    @Deprecated
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        C0087db dbVar = (C0087db) obj;
        long j = this.f6164f;
        long j2 = dbVar.f6164f;
        if (j != j2) {
            return j >= j2 ? 1 : -1;
        }
        double d = this.f6159a;
        double d2 = dbVar.f6159a;
        if (d != d2) {
            return d >= d2 ? 1 : -1;
        }
        int i = this.f6160b;
        int i2 = dbVar.f6160b;
        if (i != i2) {
            return i >= i2 ? 1 : -1;
        }
        long j3 = this.f6162d - dbVar.f6162d;
        if (j3 != 0) {
            return j3 >= 0 ? 1 : -1;
        }
        return 0;
    }

    @Deprecated
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0087db) {
            C0087db dbVar = (C0087db) obj;
            return this.f6159a == dbVar.f6159a && this.f6160b == dbVar.f6160b && this.f6162d == dbVar.f6162d;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new NotSerializableException();
    }

    @Deprecated
    public final String toString() {
        int i = this.f6160b;
        StringBuilder sb = new StringBuilder(14);
        sb.append("%.");
        sb.append(i);
        sb.append("f");
        return String.format(sb.toString(), new Object[]{Double.valueOf(this.f6159a)});
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        throw new NotSerializableException();
    }
}
