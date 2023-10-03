package p000;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bwt */
/* compiled from: PG */
final /* synthetic */ class bwt implements Consumer {

    /* renamed from: a */
    private final bwv f3781a;

    /* renamed from: b */
    private final MaterialButton f3782b;

    public bwt(bwv bwv, MaterialButton materialButton) {
        this.f3781a = bwv;
        this.f3782b = materialButton;
    }

    public final void accept(Object obj) {
        bwv bwv = this.f3781a;
        MaterialButton materialButton = this.f3782b;
        materialButton.mo3618a((Drawable) obj);
        materialButton.setVisibility(0);
        ((fea) bwv.f3784a.f3793e.f9364c.mo5563a(84026).mo5513a(ffh.f9451a)).mo5560a((View) materialButton);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
