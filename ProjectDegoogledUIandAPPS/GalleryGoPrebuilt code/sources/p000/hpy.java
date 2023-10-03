package p000;

import java.io.Serializable;

/* renamed from: hpy */
/* compiled from: PG */
public abstract class hpy implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    public abstract Object mo7645a(Object obj);

    /* renamed from: a */
    public abstract boolean mo7646a();

    /* renamed from: b */
    public abstract Object mo7647b();

    /* renamed from: c */
    public abstract Object mo7648c();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    /* renamed from: c */
    public static hpy m11894c(Object obj) {
        return obj != null ? new hqc(obj) : hph.f13219a;
    }

    /* renamed from: b */
    public static hpy m11893b(Object obj) {
        return new hqc(ife.m12898e(obj));
    }
}
