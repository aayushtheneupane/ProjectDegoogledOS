package p000;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.RectF;
import p003j$.util.Optional;

/* renamed from: cff */
/* compiled from: PG */
public abstract class cff {
    /* renamed from: a */
    public abstract Optional mo3091a();

    /* renamed from: b */
    public abstract RectF mo3092b();

    /* renamed from: c */
    public abstract long mo3093c();

    /* renamed from: d */
    public abstract int mo3094d();

    /* renamed from: e */
    public abstract int mo3095e();

    /* renamed from: f */
    public abstract byte[] mo3097f();

    /* renamed from: g */
    public abstract byte[] mo3098g();

    /* renamed from: h */
    public abstract Optional mo3099h();

    /* renamed from: i */
    public abstract Optional mo3101i();

    /* renamed from: j */
    public abstract boolean mo3102j();

    /* renamed from: k */
    public abstract boolean mo3103k();

    /* renamed from: l */
    public abstract boolean mo3104l();

    /* renamed from: m */
    public abstract cfe mo3105m();

    /* renamed from: p */
    public final ContentValues mo3129p() {
        ContentValues contentValues = new ContentValues(11);
        fxk.m9823a(contentValues, "a", mo3091a());
        RectF b = mo3092b();
        StringBuilder sb = new StringBuilder(32);
        sb.append(b.left);
        sb.append(' ');
        sb.append(b.top);
        sb.append(' ');
        sb.append(b.right);
        sb.append(' ');
        sb.append(b.bottom);
        contentValues.put("b", sb.toString());
        contentValues.put("c", Long.valueOf(mo3093c()));
        contentValues.put("d", Integer.valueOf(mo3094d()));
        contentValues.put("e", Integer.valueOf(mo3095e()));
        contentValues.put("f", mo3097f());
        if (mo3128n()) {
            contentValues.put("g", mo3098g());
        }
        fxk.m9837c(contentValues, "h", mo3099h());
        mo3101i().ifPresent(new bpw(contentValues, "i"));
        contentValues.put("j", Integer.valueOf(mo3103k() ? 1 : 0));
        contentValues.put("k", Integer.valueOf(mo3102j() ? 1 : 0));
        contentValues.put("l", Integer.valueOf(mo3104l() ? 1 : 0));
        return contentValues;
    }

    /* renamed from: a */
    public static cff m4255a(Cursor cursor) {
        cfe o = m4256o();
        o.f4245a = Optional.m16285of(Long.valueOf(fxk.m9829b(cursor, "a")));
        o.f4246b = m4254a(fxk.m9835c(cursor, "b"));
        o.mo3120a(fxk.m9829b(cursor, "c"));
        o.mo3124b(fxk.m9818a(cursor, "d"));
        o.mo3119a(fxk.m9818a(cursor, "e"));
        o.mo3123a(fxk.m9839d(cursor, "f"));
        o.mo3125b(fxk.m9840e(cursor, "j"));
        o.mo3122a(fxk.m9840e(cursor, "k"));
        o.mo3127c(fxk.m9840e(cursor, "l"));
        if (fxk.m9841f(cursor, "g")) {
            o.mo3126b(fxk.m9839d(cursor, "g"));
        }
        if (fxk.m9841f(cursor, "h")) {
            o.mo3121a(fxk.m9835c(cursor, "h"));
        }
        if (fxk.m9841f(cursor, "i")) {
            o.mo3118a(cursor.getDouble(cursor.getColumnIndexOrThrow("i")));
        }
        return o.mo3117a();
    }

    /* renamed from: n */
    public final boolean mo3128n() {
        return mo3098g().length > 0;
    }

    /* renamed from: o */
    public static cfe m4256o() {
        cfe cfe = new cfe((byte[]) null);
        cfe.mo3125b(false);
        cfe.mo3122a(false);
        cfe.mo3127c(false);
        cfe.mo3126b(new byte[0]);
        return cfe;
    }

    /* renamed from: a */
    private static RectF m4254a(String str) {
        try {
            int indexOf = str.indexOf(32);
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            int i2 = indexOf2 + 1;
            int indexOf3 = str.indexOf(32, i2);
            return new RectF(Float.parseFloat(str.substring(0, indexOf)), Float.parseFloat(str.substring(i, indexOf2)), Float.parseFloat(str.substring(i2, indexOf3)), Float.parseFloat(str.substring(indexOf3 + 1)));
        } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
            sb.append("[");
            sb.append(str);
            sb.append("] could not be parsed as a RectF.");
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }
}
