package p000;

import android.view.View;
import java.util.Set;

/* renamed from: hop */
/* compiled from: PG */
final /* synthetic */ class hop implements View.OnClickListener {

    /* renamed from: a */
    private final hos f13160a;

    /* renamed from: b */
    private final hpr f13161b;

    /* renamed from: c */
    private final View.OnClickListener f13162c;

    /* renamed from: d */
    private final View f13163d;

    public hop(hos hos, hpr hpr, View.OnClickListener onClickListener, View view) {
        this.f13160a = hos;
        this.f13161b = hpr;
        this.f13162c = onClickListener;
        this.f13163d = view;
    }

    public final void onClick(View view) {
        hos hos = this.f13160a;
        hpr hpr = this.f13161b;
        View.OnClickListener onClickListener = this.f13162c;
        View view2 = this.f13163d;
        if (hos.m11846a(view.getContext())) {
            hlp a = hos.f13168a.mo7578a(hos.m11845a("Clicked", view), (hln) hpr.mo1484a(view), hnf.f13084a);
            try {
                onClickListener.onClick(view);
                for (View.OnClickListener onClick : (Set) ((ioi) hos.f13169b).f14599a) {
                    onClick.onClick(view2);
                }
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
