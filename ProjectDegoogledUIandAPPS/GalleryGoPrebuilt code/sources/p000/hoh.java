package p000;

import android.content.Context;
import android.content.DialogInterface;

/* renamed from: hoh */
/* compiled from: PG */
public class hoh {

    /* renamed from: a */
    public int f13150a = -1;

    /* renamed from: b */
    public int f13151b = -1;

    /* renamed from: c */
    private final C0445qg f13152c;

    /* renamed from: d */
    private final hos f13153d;

    /* renamed from: e */
    private final hod f13154e;

    public hoh(Context context, hos hos, hpy hpy) {
        this.f13152c = new C0445qg(context, context.getTheme());
        this.f13153d = hos;
        this.f13154e = (hod) hpy.mo7645a(new hoe());
    }

    /* renamed from: b */
    public final C0394oj mo7627b() {
        C0393oi a = this.f13154e.mo7626a(this.f13152c);
        int i = this.f13150a;
        if (i != -1) {
            a.mo9519a(i);
        }
        int i2 = this.f13151b;
        if (i2 != -1) {
            a.mo9520a(i2, (DialogInterface.OnClickListener) new hoq(this.f13153d, new hog()));
        }
        return a.mo6550b();
    }
}
