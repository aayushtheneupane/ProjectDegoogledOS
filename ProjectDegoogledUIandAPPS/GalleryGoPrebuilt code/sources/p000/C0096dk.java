package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: dk */
/* compiled from: PG */
public final class C0096dk {

    /* renamed from: a */
    public final int f6697a;

    /* renamed from: b */
    public final char f6698b;

    /* renamed from: c */
    public short f6699c;

    /* renamed from: d */
    public int f6700d;

    /* renamed from: e */
    public final int f6701e;

    public /* synthetic */ C0096dk(int i, int i2, int i3, int i4) {
        this.f6701e = i;
        this.f6697a = i2;
        this.f6698b = (char) i3;
        this.f6699c = (short) i4;
    }

    /* renamed from: a */
    public final int mo4169a() {
        return this.f6697a + this.f6698b;
    }

    public final int hashCode() {
        return (((((this.f6701e * 37) + this.f6697a) * 37) + this.f6698b) * 37) + this.f6699c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            C0096dk dkVar = (C0096dk) obj;
            return this.f6701e == dkVar.f6701e && this.f6697a == dkVar.f6697a && this.f6698b == dkVar.f6698b && this.f6699c == dkVar.f6699c && this.f6700d == dkVar.f6700d;
        }
    }

    /* renamed from: b */
    public final int mo4170b() {
        int i = this.f6701e;
        if (i == 6 || i == 7) {
            return C0097dl.f6756f[this.f6699c];
        }
        return 1;
    }

    public final String toString() {
        String str;
        String str2;
        int i = this.f6701e;
        if (i == 6 || i == 7) {
            int b = mo4170b();
            str = cun.m5450c(b);
            if (b == 0) {
                throw null;
            }
        } else {
            str = Integer.toString(this.f6699c);
        }
        switch (this.f6701e) {
            case 1:
                str2 = "MSG_START";
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                str2 = "MSG_LIMIT";
                break;
            case 3:
                str2 = "SKIP_SYNTAX";
                break;
            case 4:
                str2 = "INSERT_CHAR";
                break;
            case 5:
                str2 = "REPLACE_NUMBER";
                break;
            case 6:
                str2 = "ARG_START";
                break;
            case 7:
                str2 = "ARG_LIMIT";
                break;
            case 8:
                str2 = "ARG_NUMBER";
                break;
            case 9:
                str2 = "ARG_NAME";
                break;
            case 10:
                str2 = "ARG_TYPE";
                break;
            case 11:
                str2 = "ARG_STYLE";
                break;
            case 12:
                str2 = "ARG_SELECTOR";
                break;
            case 13:
                str2 = "ARG_INT";
                break;
            default:
                str2 = "ARG_DOUBLE";
                break;
        }
        int i2 = this.f6697a;
        StringBuilder sb = new StringBuilder(str2.length() + 14 + String.valueOf(str).length());
        sb.append(str2);
        sb.append("(");
        sb.append(str);
        sb.append(")@");
        sb.append(i2);
        return sb.toString();
    }
}
