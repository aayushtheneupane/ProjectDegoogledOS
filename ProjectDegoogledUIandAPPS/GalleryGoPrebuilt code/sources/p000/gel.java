package p000;

import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

/* renamed from: gel */
/* compiled from: PG */
final class gel extends C0652xy {

    /* renamed from: a */
    private final /* synthetic */ gey f11111a;

    /* renamed from: b */
    private final /* synthetic */ MaterialButton f11112b;

    /* renamed from: c */
    private final /* synthetic */ geq f11113c;

    public gel(geq geq, gey gey, MaterialButton materialButton) {
        this.f11113c = geq;
        this.f11111a = gey;
        this.f11112b = materialButton;
    }

    /* renamed from: a */
    public final void mo4639a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            CharSequence text = this.f11112b.getText();
            int i2 = Build.VERSION.SDK_INT;
            recyclerView.announceForAccessibility(text);
        }
    }

    /* renamed from: a */
    public final void mo4654a(RecyclerView recyclerView, int i, int i2) {
        int i3;
        if (i < 0) {
            i3 = this.f11113c.mo6518P().mo10481n();
        } else {
            i3 = this.f11113c.mo6518P().mo10482o();
        }
        this.f11113c.f11127c = this.f11111a.mo6543f(i3);
        this.f11112b.setText(this.f11111a.mo6543f(i3).f11130b);
    }
}
