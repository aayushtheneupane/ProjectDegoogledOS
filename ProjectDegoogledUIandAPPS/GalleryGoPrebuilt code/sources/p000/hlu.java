package p000;

import android.view.View;

/* renamed from: hlu */
/* compiled from: PG */
final /* synthetic */ class hlu implements View.OnClickListener {

    /* renamed from: a */
    private final hlz f13002a;

    /* renamed from: b */
    private final String f13003b;

    /* renamed from: c */
    private final View.OnClickListener f13004c;

    public hlu(hlz hlz, String str, View.OnClickListener onClickListener) {
        this.f13002a = hlz;
        this.f13003b = str;
        this.f13004c = onClickListener;
    }

    public final void onClick(View view) {
        hlz hlz = this.f13002a;
        String str = this.f13003b;
        View.OnClickListener onClickListener = this.f13004c;
        hlp a = hlz.mo7577a(str);
        try {
            onClickListener.onClick(view);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
