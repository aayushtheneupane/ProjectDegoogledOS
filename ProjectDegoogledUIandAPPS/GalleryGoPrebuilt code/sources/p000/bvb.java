package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: bvb */
/* compiled from: PG */
final /* synthetic */ class bvb implements View.OnClickListener {

    /* renamed from: a */
    private final bvv f3667a;

    public bvb(bvv bvv) {
        this.f3667a = bvv;
    }

    public final void onClick(View view) {
        bvv bvv = this.f3667a;
        if (bvv.f3690C) {
            bvv.mo2809b(bvv.f3720b.f3637h);
            int i = (bvv.f3692E + 1) & 3;
            bvv.f3692E = i;
            bvv.f3711X.announceForAccessibility(C0643xp.m15940a(bvv.f3743y, R.string.editor_rotate_accessibility_announcement_icu, "rotation_position", Integer.valueOf(i * 90)));
            bvv.f3726h.mo2884a();
            bxq bxq = bvv.f3726h;
            bzy bzy = byu.f3917c;
            double d = (double) bvv.f3692E;
            Double.isNaN(d);
            bxq.mo2887a(bzy, (Object) Float.valueOf((float) (-((d * 3.141592653589793d) / 2.0d))));
            bxo bxo = bvv.f3728j;
            bxo.f3860a = bvv.f3687a;
            bxo.mo2883a();
        }
    }
}
