package p000;

/* renamed from: ceb */
/* compiled from: PG */
final class ceb implements dtj {

    /* renamed from: a */
    private final /* synthetic */ cef f4154a;

    public /* synthetic */ ceb(cef cef) {
        this.f4154a = cef;
    }

    /* renamed from: b */
    public final void mo3045b() {
        this.f4154a.f4174i.mo3054a();
    }

    /* renamed from: a */
    public final void mo3043a() {
        if (this.f4154a.f4170e.getTranslationX() >= this.f4154a.f4172g.getX() || this.f4154a.f4170e.getTranslationX() <= this.f4154a.f4171f.getX()) {
            cef cef = this.f4154a;
            cef.mo3070b(cef.mo3073c());
        }
        this.f4154a.f4174i.mo3054a();
    }

    /* renamed from: a */
    public final void mo3044a(int i, int i2, boolean z) {
        Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z)};
        if (!z) {
            float width = (float) this.f4154a.f4168c.getWidth();
            this.f4154a.f4170e.setVisibility(0);
            float f = (((float) i) * width) / ((float) i2);
            if (this.f4154a.mo3072b()) {
                f = width - f;
            }
            Object[] objArr2 = {Float.valueOf(this.f4154a.f4170e.getTranslationX()), Float.valueOf(f)};
            this.f4154a.f4170e.setTranslationX(f);
        }
        if (this.f4154a.f4170e.getTranslationX() >= this.f4154a.f4172g.getX()) {
            Object[] objArr3 = {Float.valueOf(this.f4154a.f4170e.getTranslationX()), Float.valueOf(this.f4154a.f4172g.getX())};
            cef cef = this.f4154a;
            cef.f4170e.setTranslationX(cef.f4172g.getX());
            if (!this.f4154a.mo3072b()) {
                this.f4154a.mo3076e();
            }
        } else if (this.f4154a.f4170e.getTranslationX() <= this.f4154a.f4171f.getX()) {
            Object[] objArr4 = {Float.valueOf(this.f4154a.f4170e.getTranslationX()), Float.valueOf(this.f4154a.f4171f.getX())};
            cef cef2 = this.f4154a;
            cef2.f4170e.setTranslationX(cef2.f4171f.getX());
            if (this.f4154a.mo3072b()) {
                this.f4154a.mo3076e();
            }
        }
    }
}
