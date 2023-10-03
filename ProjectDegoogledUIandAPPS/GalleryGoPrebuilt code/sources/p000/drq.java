package p000;

/* renamed from: drq */
/* compiled from: PG */
public abstract class drq {
    /* renamed from: a */
    public abstract cxi mo4372a();

    /* renamed from: b */
    public abstract int mo4373b();

    /* renamed from: c */
    public abstract boolean mo4374c();

    /* renamed from: d */
    public abstract boolean mo4375d();

    /* renamed from: e */
    public abstract drp mo4376e();

    public abstract int hashCode();

    /* renamed from: a */
    public static drq m6538a(cxi cxi, int i, boolean z, boolean z2) {
        drp drp = new drp((byte[]) null);
        if (cxi != null) {
            drp.f7242a = cxi;
            drp.f7243b = Integer.valueOf(i);
            drp.f7244c = Boolean.valueOf(z);
            drp.mo4381a(z2);
            return drp.mo4380a();
        }
        throw new NullPointerException("Null media");
    }
}
