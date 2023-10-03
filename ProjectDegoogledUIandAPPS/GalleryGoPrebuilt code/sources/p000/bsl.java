package p000;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.PromoView;

/* renamed from: bsl */
/* compiled from: PG */
public final class bsl {

    /* renamed from: a */
    public final hos f3486a;

    /* renamed from: b */
    public final PromoView f3487b;

    /* renamed from: c */
    public final TextView f3488c;

    /* renamed from: d */
    public final TextView f3489d;

    /* renamed from: e */
    public final fee f3490e;

    /* renamed from: f */
    public Intent f3491f;

    /* renamed from: g */
    private final fdv f3492g;

    public bsl(PromoView promoView, hbl hbl, hos hos, fee fee, fdv fdv) {
        this.f3486a = hos;
        this.f3490e = fee;
        this.f3492g = fdv;
        LayoutInflater.from(hbl).inflate(R.layout.promo_contents, promoView);
        this.f3487b = promoView;
        this.f3488c = (TextView) promoView.findViewById(R.id.promo_text);
        this.f3489d = (TextView) promoView.findViewById(R.id.link_text);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo2719a(View view) {
        if (this.f3491f != null) {
            this.f3492g.mo5551a(fdu.m8653a(), view);
            try {
                view.getContext().startActivity(this.f3491f);
            } catch (ActivityNotFoundException e) {
                cwn.m5511a((Throwable) e, "PromoViewPeer: Unable to launch play store activity", new Object[0]);
            }
        }
    }
}
