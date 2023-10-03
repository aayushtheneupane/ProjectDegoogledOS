package p000;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: agh */
/* compiled from: PG */
final class agh extends afm {

    /* renamed from: a */
    private final /* synthetic */ ViewGroup f373a;

    /* renamed from: b */
    private final /* synthetic */ View f374b;

    /* renamed from: c */
    private final /* synthetic */ View f375c;

    /* renamed from: d */
    private final /* synthetic */ agk f376d;

    public agh(agk agk, ViewGroup viewGroup, View view, View view2) {
        this.f376d = agk;
        this.f373a = viewGroup;
        this.f374b = view;
        this.f375c = view2;
    }

    /* renamed from: a */
    public final void mo265a(afl afl) {
        this.f375c.setTag(R.id.save_overlay_view, (Object) null);
        afy.m413a(this.f373a).mo337b(this.f374b);
        afl.mo312b((afk) this);
    }

    /* renamed from: b */
    public final void mo266b() {
        afy.m413a(this.f373a).mo337b(this.f374b);
    }

    /* renamed from: c */
    public final void mo267c() {
        if (this.f374b.getParent() == null) {
            afy.m413a(this.f373a).mo336a(this.f374b);
        } else {
            this.f376d.mo322f();
        }
    }
}
