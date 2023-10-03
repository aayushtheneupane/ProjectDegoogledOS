package p000;

/* renamed from: att */
/* compiled from: PG */
final class att implements Appendable {

    /* renamed from: a */
    private final Appendable f1666a;

    /* renamed from: b */
    private boolean f1667b = true;

    public att(Appendable appendable) {
        this.f1666a = appendable;
    }

    /* renamed from: a */
    private static final CharSequence m1650a(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence;
    }

    public final Appendable append(char c) {
        boolean z = false;
        if (this.f1667b) {
            this.f1667b = false;
            this.f1666a.append("  ");
        }
        if (c == 10) {
            z = true;
        }
        this.f1667b = z;
        this.f1666a.append(c);
        return this;
    }

    public final Appendable append(CharSequence charSequence) {
        CharSequence a = m1650a(charSequence);
        append(a, 0, a.length());
        return this;
    }

    public final Appendable append(CharSequence charSequence, int i, int i2) {
        CharSequence a = m1650a(charSequence);
        boolean z = false;
        if (this.f1667b) {
            this.f1667b = false;
            this.f1666a.append("  ");
        }
        if (a.length() > 0 && a.charAt(i2 - 1) == 10) {
            z = true;
        }
        this.f1667b = z;
        this.f1666a.append(a, i, i2);
        return this;
    }
}
