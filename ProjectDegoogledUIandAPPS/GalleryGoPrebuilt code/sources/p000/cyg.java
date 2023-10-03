package p000;

import android.content.ContentValues;
import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: cyg */
/* compiled from: PG */
public abstract class cyg {
    /* renamed from: A */
    public abstract byte[] mo3894A();

    /* renamed from: B */
    public abstract byte[] mo3895B();

    /* renamed from: C */
    public abstract Optional mo3896C();

    /* renamed from: D */
    public abstract Optional mo3897D();

    /* renamed from: E */
    public abstract Optional mo3898E();

    /* renamed from: F */
    public abstract int mo3899F();

    /* renamed from: G */
    public abstract Optional mo3900G();

    /* renamed from: H */
    public abstract boolean mo3901H();

    /* renamed from: I */
    public abstract boolean mo3902I();

    /* renamed from: J */
    public abstract int mo3903J();

    /* renamed from: K */
    public abstract boolean mo3904K();

    /* renamed from: L */
    public abstract boolean mo3905L();

    /* renamed from: M */
    public abstract cyf mo3906M();

    /* renamed from: a */
    public abstract Optional mo3907a();

    /* renamed from: b */
    public abstract long mo3908b();

    /* renamed from: c */
    public abstract int mo3909c();

    /* renamed from: d */
    public abstract String mo3910d();

    /* renamed from: e */
    public abstract int mo3911e();

    /* renamed from: f */
    public abstract int mo3913f();

    /* renamed from: g */
    public abstract int mo3914g();

    /* renamed from: h */
    public abstract long mo3915h();

    /* renamed from: i */
    public abstract Optional mo3917i();

    /* renamed from: j */
    public abstract long mo3918j();

    /* renamed from: k */
    public abstract long mo3919k();

    /* renamed from: l */
    public abstract long mo3920l();

    /* renamed from: m */
    public abstract String mo3921m();

    /* renamed from: n */
    public abstract String mo3922n();

    /* renamed from: o */
    public abstract String mo3923o();

    /* renamed from: p */
    public abstract Optional mo3924p();

    /* renamed from: q */
    public abstract Optional mo3925q();

    /* renamed from: r */
    public abstract int mo3926r();

    /* renamed from: s */
    public abstract Optional mo3927s();

    /* renamed from: t */
    public abstract int mo3928t();

    /* renamed from: u */
    public abstract boolean mo3930u();

    /* renamed from: v */
    public abstract boolean mo3931v();

    /* renamed from: w */
    public abstract boolean mo3932w();

    /* renamed from: x */
    public abstract boolean mo3933x();

    /* renamed from: y */
    public abstract boolean mo3934y();

    /* renamed from: z */
    public abstract boolean mo3935z();

    /* renamed from: O */
    public final Uri mo3991O() {
        return cyc.m5647b(cyc.m5643a(mo3909c()), mo3908b());
    }

    /* renamed from: S */
    public final ContentValues mo3994S() {
        ContentValues contentValues = new ContentValues(23);
        fxk.m9823a(contentValues, "a", mo3907a());
        contentValues.put("b", Long.valueOf(mo3908b()));
        contentValues.put("c", Integer.valueOf(mo3909c()));
        contentValues.put("d", mo3910d());
        contentValues.put("e", Integer.valueOf(mo3911e()));
        contentValues.put("f", Integer.valueOf(mo3913f()));
        contentValues.put("ah", Integer.valueOf(mo3914g()));
        contentValues.put("g", Long.valueOf(mo3915h()));
        fxk.m9823a(contentValues, "h", mo3917i());
        contentValues.put("i", Long.valueOf(mo3918j()));
        contentValues.put("j", Long.valueOf(mo3919k()));
        contentValues.put("k", Long.valueOf(mo3920l()));
        contentValues.put("l", mo3921m());
        contentValues.put("m", mo3922n());
        contentValues.put("n", mo3923o());
        fxk.m9837c(contentValues, "ai", mo3924p());
        contentValues.put("aj", Integer.valueOf(mo3928t()));
        fxk.m9837c(contentValues, "ak", mo3896C());
        fxk.m9831b(contentValues, "ao", mo3925q());
        contentValues.put("ap", Integer.valueOf(mo3926r()));
        fxk.m9823a(contentValues, "aq", mo3927s());
        contentValues.put("at", (String) mo3897D().orElse((Object) null));
        fxk.m9823a(contentValues, "al", mo3898E());
        contentValues.put("am", Integer.valueOf(mo3899F()));
        fxk.m9831b(contentValues, "an", mo3900G());
        if (mo3904K()) {
            contentValues.put("aa", 0);
            contentValues.put("ab", 0);
            contentValues.put("ac", 0);
            contentValues.put("af", 0);
            contentValues.put("ae", 0);
            contentValues.put("ad", 0);
            contentValues.putNull("v");
            contentValues.putNull("ag");
            contentValues.put("y", 0);
            contentValues.put("w", 0);
            contentValues.put("x", 0);
            contentValues.putNull("o");
        } else {
            contentValues.put("aa", Integer.valueOf(mo3930u() ? 1 : 0));
            contentValues.put("ab", Integer.valueOf(mo3931v() ? 1 : 0));
            contentValues.put("ac", Integer.valueOf(mo3932w() ? 1 : 0));
            contentValues.put("af", Integer.valueOf(mo3933x() ? 1 : 0));
            contentValues.put("ae", Integer.valueOf(mo3934y() ? 1 : 0));
            contentValues.put("ad", Integer.valueOf(mo3935z() ? 1 : 0));
            if (mo3990N()) {
                contentValues.put("v", mo3894A());
            }
            if (mo3895B().length > 0) {
                contentValues.put("ag", mo3895B());
            }
            contentValues.put("y", Integer.valueOf(mo3901H() ? 1 : 0));
            contentValues.put("w", Integer.valueOf(mo3902I() ? 1 : 0));
            contentValues.put("x", Integer.valueOf(mo3903J()));
        }
        return contentValues;
    }

    /* renamed from: N */
    public final boolean mo3990N() {
        return mo3894A().length > 0;
    }

    /* renamed from: R */
    public static cyf m5687R() {
        cyf cyf = new cyf((byte[]) null);
        cyf.mo3978e(false);
        cyf.mo3989j(false);
        cyf.mo3983g(0);
        cyf.mo3970c(false);
        cyf.mo3974d(false);
        cyf.mo3985g(false);
        cyf.mo3988i(false);
        cyf.mo3987h(false);
        cyf.mo3982f(false);
        cyf.mo3964b(new byte[0]);
        cyf.mo3959a(new byte[0]);
        cyf.mo3975e(-1);
        cyf.mo3963b(false);
        cyf.mo3979f(0);
        cyf.mo3958a(false);
        cyf.mo3967c(0);
        return cyf;
    }

    /* renamed from: Q */
    public final int mo3993Q() {
        return cyc.m5646b(mo3913f(), mo3911e(), mo3914g());
    }

    /* renamed from: P */
    public final int mo3992P() {
        return cyc.m5640a(mo3913f(), mo3911e(), mo3914g());
    }
}
