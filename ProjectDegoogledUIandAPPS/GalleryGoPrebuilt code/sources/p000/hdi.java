package p000;

import android.content.Context;

/* renamed from: hdi */
/* compiled from: PG */
final class hdi implements iqk {

    /* renamed from: a */
    private ift f12524a = null;

    /* renamed from: b */
    private final /* synthetic */ Context f12525b;

    /* renamed from: c */
    private final /* synthetic */ int f12526c;

    /* renamed from: d */
    private final /* synthetic */ String f12527d;

    /* renamed from: e */
    private final /* synthetic */ inw f12528e;

    public hdi(Context context, int i, String str, inw inw) {
        this.f12525b = context;
        this.f12526c = i;
        this.f12527d = str;
        this.f12528e = inw;
    }

    /* renamed from: b */
    public final ift mo2097a() {
        if (this.f12524a == null) {
            this.f12524a = new ift(this.f12525b.getPackageName(), this.f12526c, this.f12527d, ((ifr) this.f12528e.mo9034a()).mo3860a());
        }
        return this.f12524a;
    }
}
