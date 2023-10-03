package p000;

import com.google.android.gms.common.api.Scope;

/* renamed from: ewb */
/* compiled from: PG */
public final class ewb {

    /* renamed from: a */
    public static final ekn f9123a = new ekn("SignIn.API", f9124b, f9125c, (byte[]) null, (byte[]) null);

    /* renamed from: b */
    public static final eov f9124b = new evy();

    /* renamed from: c */
    private static final eki f9125c = new eki((byte[]) null);

    /* renamed from: d */
    private static final eki f9126d = new eki((byte[]) null);

    /* renamed from: e */
    private static final eov f9127e = new evz();

    static {
        new Scope("profile");
        new Scope("email");
        new ekn("SignIn.INTERNAL_API", f9127e, f9126d, (byte[]) null, (byte[]) null);
    }
}
