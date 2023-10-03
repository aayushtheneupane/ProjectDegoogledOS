package p000;

/* renamed from: hym */
/* compiled from: PG */
public abstract class hym {

    /* renamed from: a */
    public final int f13654a;

    /* renamed from: b */
    public final hwv f13655b;

    protected hym(hwv hwv, int i) {
        if (hwv == null) {
            throw new IllegalArgumentException("format options cannot be null");
        } else if (i >= 0) {
            this.f13654a = i;
            this.f13655b = hwv;
        } else {
            StringBuilder sb = new StringBuilder(26);
            sb.append("invalid index: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: a */
    public abstract void mo8271a(hyn hyn, Object obj);
}
