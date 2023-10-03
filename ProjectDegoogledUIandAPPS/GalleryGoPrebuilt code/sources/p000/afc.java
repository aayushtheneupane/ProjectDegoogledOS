package p000;

import android.view.View;
import java.util.ArrayList;

/* renamed from: afc */
/* compiled from: PG */
final class afc implements afk {

    /* renamed from: a */
    private final /* synthetic */ View f300a;

    /* renamed from: b */
    private final /* synthetic */ ArrayList f301b;

    public afc(View view, ArrayList arrayList) {
        this.f300a = view;
        this.f301b = arrayList;
    }

    /* renamed from: a */
    public final void mo264a() {
    }

    /* renamed from: b */
    public final void mo266b() {
    }

    /* renamed from: c */
    public final void mo267c() {
    }

    /* renamed from: a */
    public final void mo265a(afl afl) {
        afl.mo312b((afk) this);
        this.f300a.setVisibility(8);
        int size = this.f301b.size();
        for (int i = 0; i < size; i++) {
            ((View) this.f301b.get(i)).setVisibility(0);
        }
    }

    /* renamed from: b */
    public final void mo278b(afl afl) {
        afl.mo312b((afk) this);
        afl.mo302a((afk) this);
    }
}
