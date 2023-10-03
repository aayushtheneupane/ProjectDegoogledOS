package p000;

import android.content.DialogInterface;
import android.view.View;
import java.util.Set;

/* renamed from: hlz */
/* compiled from: PG */
public final class hlz {

    /* renamed from: a */
    public final exm f13016a;

    /* renamed from: b */
    public final hme f13017b;

    /* renamed from: c */
    public final hln f13018c;

    /* renamed from: d */
    public final hms f13019d;

    public hlz(exm exm, hme hme, Set set, hms hms) {
        this.f13016a = exm;
        this.f13017b = hme;
        this.f13018c = hln.m11702a(set);
        this.f13019d = hms;
    }

    /* renamed from: a */
    public final hlp mo7578a(String str, hln hln, hnf hnf) {
        ife.m12898e((Object) hnf);
        return this.f13017b.mo7580a(str, hln.m11701a(this.f13018c, hln), this.f13016a.mo5370a(), this.f13016a.mo5372c(), this.f13019d);
    }

    /* renamed from: a */
    public final hlp mo7579a(String str, hnf hnf) {
        return mo7578a(str, hlm.f12987a, hnf);
    }

    /* renamed from: a */
    public final hlp mo7577a(String str) {
        return this.f13017b.mo7580a(str, this.f13018c, this.f13016a.mo5370a(), this.f13016a.mo5372c(), this.f13019d);
    }

    /* renamed from: a */
    public final View.OnClickListener mo7575a(View.OnClickListener onClickListener, String str) {
        return new hlu(this, str, onClickListener);
    }

    /* renamed from: a */
    public final DialogInterface.OnClickListener mo7574a(DialogInterface.OnClickListener onClickListener, String str) {
        return new hlt(this, str, onClickListener);
    }

    /* renamed from: a */
    public final View.OnTouchListener mo7576a(View.OnTouchListener onTouchListener, String str) {
        return new hlv(this, onTouchListener, str);
    }
}
