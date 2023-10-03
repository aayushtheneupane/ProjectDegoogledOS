package p000;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.photogrid.PhotosPromoView;

/* renamed from: dxk */
/* compiled from: PG */
public final class dxk {

    /* renamed from: a */
    public final TextView f7569a;

    /* renamed from: b */
    public final TextView f7570b;

    /* renamed from: c */
    public final TextView f7571c;

    /* renamed from: d */
    public final fee f7572d;

    /* renamed from: e */
    public Intent f7573e;

    /* renamed from: f */
    private final fdv f7574f;

    /* renamed from: g */
    private final View f7575g;

    public dxk(PhotosPromoView photosPromoView, hbl hbl, fee fee, fdv fdv) {
        this.f7572d = fee;
        this.f7574f = fdv;
        this.f7575g = photosPromoView;
        LayoutInflater.from(hbl).inflate(R.layout.photogrid_promo_contents, photosPromoView);
        this.f7569a = (TextView) photosPromoView.findViewById(R.id.promo_header_text);
        this.f7570b = (TextView) photosPromoView.findViewById(R.id.promo_subheader_text);
        this.f7571c = (TextView) photosPromoView.findViewById(R.id.link_text);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4541a(View view) {
        if (this.f7573e != null) {
            this.f7574f.mo5551a(fdu.m8653a(), view);
            Context context = view.getContext();
            try {
                context.startActivity(this.f7573e);
            } catch (ActivityNotFoundException e) {
                cwn.m5511a((Throwable) e, "PhotosPromoViewPeer: Unable to launch photos activity", new Object[0]);
                ihg.m13039a((hoi) dwz.m6850a(context.getResources().getString(R.string.photos_launch_fail)), this.f7575g);
            }
        }
    }
}
