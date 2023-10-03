package p000;

import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselItemView;
import p003j$.util.Optional;

/* renamed from: drt */
/* compiled from: PG */
public final class drt {

    /* renamed from: a */
    public final ImageView f7248a;

    /* renamed from: b */
    public final ImageView f7249b;

    /* renamed from: c */
    public final hdt f7250c;

    /* renamed from: d */
    public final cnx f7251d;

    /* renamed from: e */
    public Optional f7252e = Optional.empty();

    public drt(CarouselItemView carouselItemView, hos hos, hdt hdt, cnx cnx) {
        this.f7248a = (ImageView) carouselItemView.findViewById(R.id.carousel_item_dot);
        this.f7249b = (ImageView) carouselItemView.findViewById(R.id.carousel_item_image);
        this.f7250c = hdt;
        this.f7251d = cnx;
        carouselItemView.setOrientation(1);
        hos.mo7632a((View) carouselItemView, (View.OnClickListener) new drs(this));
    }
}
