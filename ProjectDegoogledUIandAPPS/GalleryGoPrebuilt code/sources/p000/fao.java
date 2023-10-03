package p000;

import android.graphics.Bitmap;

/* renamed from: fao */
/* compiled from: PG */
public class fao implements fab {

    /* renamed from: a */
    public esg f9245a = new esg();

    /* renamed from: a */
    public fae mo5430a() {
        return new far(this.f9245a.mo5198a());
    }

    /* renamed from: a */
    public final void mo5434a(String str) {
        this.f9245a.f8909b = str;
    }

    /* renamed from: a */
    public final void mo5432a(ezw ezw) {
        fan fan = new fan(ezw);
        esg esg = this.f9245a;
        if ((!esg.f8910c.isEmpty() || !esg.f8913f.isEmpty()) && esg.f8916i) {
            throw new IllegalStateException("Can't mix pii-full psd and pii-free psd");
        }
        esg.f8916i = false;
        esg.f8919l = fan;
    }

    /* renamed from: b */
    public final void mo5436b(String str) {
        this.f9245a.f8912e = str;
    }

    /* renamed from: c */
    public final void mo5437c(String str) {
        this.f9245a.f8911d = str;
    }

    /* renamed from: b */
    public final void mo5435b() {
        this.f9245a.f8914g = true;
    }

    /* renamed from: a */
    public final void mo5431a(Bitmap bitmap) {
        esg esg = this.f9245a;
        if (!esg.f8914g || !ipt.f14632a.mo2652a().mo9059a()) {
            esg.f8908a = bitmap;
            return;
        }
        throw new IllegalStateException("Can't call setScreenshotBitmap after report is already certified pii free.");
    }

    /* renamed from: a */
    public final void mo5433a(fai fai) {
        this.f9245a.f8915h = ((fax) fai).f9249a;
    }
}
