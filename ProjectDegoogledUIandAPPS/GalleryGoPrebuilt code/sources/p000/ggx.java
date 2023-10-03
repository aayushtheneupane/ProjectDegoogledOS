package p000;

/* renamed from: ggx */
/* compiled from: PG */
public final class ggx extends gqb {
    /* renamed from: a */
    public final void mo6626a(ghl ghl, float f, float f2) {
        ghl.mo6681a(f2 * f, 180.0f, 90.0f);
        float f3 = (f2 + f2) * f;
        ghh ghh = new ghh(0.0f, 0.0f, f3, f3);
        ghh.f11374e = 180.0f;
        ghh.f11375f = 90.0f;
        ghl.f11383d.add(ghh);
        ghl.mo6683a((ghk) new ghf(ghh), 180.0f, 270.0f);
        float f4 = f3 + 0.0f;
        float f5 = 0.5f * f4;
        float f6 = f4 / 2.0f;
        ghl.f11381b = (((float) Math.cos(Math.toRadians(270.0d))) * f6) + f5;
        ghl.f11382c = f5 + (f6 * ((float) Math.sin(Math.toRadians(270.0d))));
    }
}
