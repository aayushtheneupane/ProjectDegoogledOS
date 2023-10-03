package p000;

import android.view.MenuItem;

/* renamed from: hnv */
/* compiled from: PG */
final /* synthetic */ class hnv implements C0690zi {

    /* renamed from: a */
    private final hnw f13129a;

    /* renamed from: b */
    private final String f13130b;

    /* renamed from: c */
    private final C0690zi f13131c;

    public hnv(hnw hnw, String str, C0690zi ziVar) {
        this.f13129a = hnw;
        this.f13130b = str;
        this.f13131c = ziVar;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        hnw hnw = this.f13129a;
        String str = this.f13130b;
        C0690zi ziVar = this.f13131c;
        hlp a = hnw.f13132a.mo7577a(str);
        try {
            boolean a2 = ziVar.mo2636a(menuItem);
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
