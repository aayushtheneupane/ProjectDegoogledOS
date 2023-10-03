package p000;

import android.app.Activity;

/* renamed from: het */
/* compiled from: PG */
public final class het {

    /* renamed from: a */
    public static final hvy f12608a = hvy.m12261a("com/google/apps/tiktok/monitoring/feedback/HelpAndFeedbackStarter");

    /* renamed from: b */
    public final Activity f12609b;

    /* renamed from: c */
    public final grx f12610c;

    /* renamed from: d */
    public final fbb f12611d;

    /* renamed from: e */
    public final fah f12612e;

    /* renamed from: f */
    public final hpy f12613f;

    /* renamed from: g */
    public final iqk f12614g;

    /* renamed from: h */
    public final iqk f12615h;

    /* renamed from: i */
    public final iqk f12616i;

    /* renamed from: j */
    public final gry f12617j = new hes(this);

    /* renamed from: k */
    private final C0438q f12618k = new her(this);

    public het(Activity activity, C0147fh fhVar, grx grx, hpy hpy, iqk iqk, iqk iqk2, fbb fbb, fah fah, iqk iqk3) {
        boolean z;
        this.f12609b = activity;
        this.f12610c = grx;
        if (((gkn) ((hqc) hpy).f13250a).mo6807a() == -1) {
            this.f12613f = hph.f13219a;
        } else {
            this.f12613f = hpy;
        }
        this.f12614g = iqk;
        this.f12615h = iqk2;
        this.f12611d = fbb;
        this.f12612e = fah;
        this.f12616i = iqk3;
        if (fhVar.mo5ad().mo61a() == C0573v.INITIALIZED) {
            z = true;
        } else {
            z = false;
        }
        ife.m12896d(z);
        fhVar.mo5ad().mo64a(this.f12618k);
    }
}
