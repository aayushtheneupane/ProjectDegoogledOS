package p000;

import android.content.Context;

/* renamed from: ib */
/* compiled from: PG */
public class C0224ib {

    /* renamed from: c */
    public int f13816c;

    /* renamed from: d */
    public C0223ia f13817d;

    /* renamed from: e */
    public final Context f13818e;

    /* renamed from: f */
    public boolean f13819f = false;

    /* renamed from: g */
    public boolean f13820g = false;

    /* renamed from: h */
    public boolean f13821h = true;

    /* renamed from: i */
    public boolean f13822i = false;

    public C0224ib(Context context) {
        this.f13818e = context.getApplicationContext();
    }

    /* renamed from: a */
    public void mo6169a(Object obj) {
        throw null;
    }

    /* renamed from: c */
    public void mo8242c() {
    }

    /* renamed from: b */
    public static final String m12595b(Object obj) {
        StringBuilder sb = new StringBuilder(64);
        if (obj == null) {
            sb.append("null");
        } else {
            sb.append(obj.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(obj)));
            sb.append("}");
        }
        return sb.toString();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" id=");
        sb.append(this.f13816c);
        sb.append("}");
        return sb.toString();
    }
}
