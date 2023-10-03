package p000;

/* renamed from: iih */
/* compiled from: PG */
public final class iih {

    /* renamed from: a */
    public final ikf f14241a;

    /* renamed from: b */
    public final Object f14242b;

    /* renamed from: c */
    public final ikf f14243c;

    /* renamed from: d */
    public final iiw f14244d;

    public iih() {
    }

    /* renamed from: a */
    public final int mo8710a() {
        return this.f14244d.f14322a;
    }

    /* renamed from: b */
    public final imb mo8712b() {
        return this.f14244d.f14323b;
    }

    public iih(ikf ikf, Object obj, ikf ikf2, iiw iiw) {
        if (ikf == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        } else if (iiw.f14323b == imb.MESSAGE && ikf2 == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        } else {
            this.f14241a = ikf;
            this.f14242b = obj;
            this.f14243c = ikf2;
            this.f14244d = iiw;
        }
    }

    /* renamed from: a */
    public final Object mo8711a(Object obj) {
        if (this.f14244d.mo8719c() != imc.ENUM) {
            return obj;
        }
        ((Integer) obj).intValue();
        throw null;
    }
}
