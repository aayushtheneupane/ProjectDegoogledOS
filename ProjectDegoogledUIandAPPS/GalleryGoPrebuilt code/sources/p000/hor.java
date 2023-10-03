package p000;

import android.view.View;
import java.util.Set;

/* renamed from: hor */
/* compiled from: PG */
public final /* synthetic */ class hor implements View.OnLongClickListener {

    /* renamed from: a */
    private final hos f13166a;

    /* renamed from: b */
    private final View.OnLongClickListener f13167b;

    public hor(hos hos, View.OnLongClickListener onLongClickListener) {
        this.f13166a = hos;
        this.f13167b = onLongClickListener;
    }

    public final boolean onLongClick(View view) {
        hos hos = this.f13166a;
        View.OnLongClickListener onLongClickListener = this.f13167b;
        if (!hos.m11846a(view.getContext())) {
            return false;
        }
        hlp a = hos.f13168a.mo7579a(hos.m11845a("Long clicked", view), hnf.f13084a);
        try {
            boolean onLongClick = onLongClickListener.onLongClick(view);
            for (View.OnLongClickListener onLongClick2 : (Set) ((ioi) hos.f13170c).f14599a) {
                onLongClick2.onLongClick(view);
            }
            if (a == null) {
                return onLongClick;
            }
            a.close();
            return onLongClick;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
