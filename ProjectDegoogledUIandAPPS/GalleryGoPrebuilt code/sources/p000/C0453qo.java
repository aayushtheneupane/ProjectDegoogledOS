package p000;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;

/* renamed from: qo */
/* compiled from: PG */
public final class C0453qo {

    /* renamed from: a */
    public final ArrayList f15665a = new ArrayList();

    /* renamed from: b */
    public C0345mo f15666b;

    /* renamed from: c */
    public boolean f15667c;

    /* renamed from: d */
    private long f15668d = -1;

    /* renamed from: e */
    private Interpolator f15669e;

    /* renamed from: f */
    private final C0346mp f15670f = new C0452qn(this);

    /* renamed from: b */
    public final void mo9731b() {
        if (this.f15667c) {
            ArrayList arrayList = this.f15665a;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((C0344mn) arrayList.get(i)).mo9399a();
            }
            this.f15667c = false;
        }
    }

    /* renamed from: a */
    public final void mo9729a(C0344mn mnVar) {
        if (!this.f15667c) {
            this.f15665a.add(mnVar);
        }
    }

    /* renamed from: c */
    public final void mo9732c() {
        if (!this.f15667c) {
            this.f15668d = 250;
        }
    }

    /* renamed from: a */
    public final void mo9728a(Interpolator interpolator) {
        if (!this.f15667c) {
            this.f15669e = interpolator;
        }
    }

    /* renamed from: a */
    public final void mo9730a(C0345mo moVar) {
        if (!this.f15667c) {
            this.f15666b = moVar;
        }
    }

    /* renamed from: a */
    public final void mo9727a() {
        View view;
        if (!this.f15667c) {
            ArrayList arrayList = this.f15665a;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0344mn mnVar = (C0344mn) arrayList.get(i);
                long j = this.f15668d;
                if (j >= 0) {
                    mnVar.mo9401a(j);
                }
                Interpolator interpolator = this.f15669e;
                if (!(interpolator == null || (view = (View) mnVar.f15239a.get()) == null)) {
                    view.animate().setInterpolator(interpolator);
                }
                if (this.f15666b != null) {
                    mnVar.mo9402a((C0345mo) this.f15670f);
                }
                View view2 = (View) mnVar.f15239a.get();
                if (view2 != null) {
                    view2.animate().start();
                }
            }
            this.f15667c = true;
        }
    }
}
