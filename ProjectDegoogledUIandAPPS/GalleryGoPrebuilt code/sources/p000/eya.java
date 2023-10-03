package p000;

@Deprecated
/* renamed from: eya */
/* compiled from: PG */
public final class eya extends exv {
    public eya(eja eja, eiz eiz) {
        super(eja, eiz);
    }

    public eya(eja eja, byte[] bArr) {
        super(eja, bArr);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ exr mo5376a(int i) {
        this.f9193a.mo4865a(i);
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo5378a(String str) {
        this.f9193a.f8388e = str;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ void mo5379b(String str) {
        eix eix = this.f9193a;
        eja eja = eix.f8384a;
        ekn ekn = eja.f8395a;
        if (!eja.f8405h) {
            eix.f8387d = str;
            return;
        }
        throw new IllegalStateException("setUploadAccountName forbidden on anonymous logger");
    }
}
