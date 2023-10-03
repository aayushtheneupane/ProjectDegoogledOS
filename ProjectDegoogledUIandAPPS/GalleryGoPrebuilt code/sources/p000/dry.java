package p000;

import android.view.LayoutInflater;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: dry */
/* compiled from: PG */
public final class dry {

    /* renamed from: a */
    public final C0647xt f7255a;

    /* renamed from: b */
    public final LayoutInflater f7256b;

    /* renamed from: c */
    public final gwd f7257c;

    /* renamed from: d */
    public List f7258d = new ArrayList();

    /* renamed from: e */
    public int f7259e = -1;

    /* renamed from: f */
    public int f7260f = -1;

    public dry(CarouselView carouselView, hbl hbl) {
        C0607wg wgVar = new C0607wg(0);
        this.f7255a = wgVar;
        carouselView.setLayoutManager(wgVar);
        this.f7256b = LayoutInflater.from(hbl);
        drx drx = new drx(this);
        gwb c = gwd.m10934c();
        c.mo7127a((gwe) drx);
        c.mo7128a(drw.f7253a);
        gwd a = c.mo7126a();
        this.f7257c = a;
        carouselView.setAdapter(a);
        C0641xn itemAnimator = carouselView.getItemAnimator();
        if (itemAnimator instanceof C0674yt) {
            ((C0674yt) itemAnimator).mo10713h();
        }
    }
}
