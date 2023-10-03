package p000;

import java.text.Format;

/* renamed from: cu */
/* compiled from: PG */
final class C0079cu {

    /* renamed from: a */
    public final int f5650a;

    /* renamed from: b */
    public final String f5651b;

    /* renamed from: c */
    public final Number f5652c;

    /* renamed from: d */
    public final double f5653d;

    /* renamed from: e */
    public int f5654e;

    /* renamed from: f */
    public Format f5655f;

    /* renamed from: g */
    public String f5656g;

    /* renamed from: h */
    public boolean f5657h;

    public /* synthetic */ C0079cu(int i, String str, Number number, double d) {
        this.f5650a = i;
        this.f5651b = str;
        if (d == 0.0d) {
            this.f5652c = number;
        } else {
            this.f5652c = Double.valueOf(number.doubleValue() - d);
        }
        this.f5653d = d;
    }

    public final String toString() {
        throw new AssertionError("PluralSelectorContext being formatted, rather than its number");
    }
}
