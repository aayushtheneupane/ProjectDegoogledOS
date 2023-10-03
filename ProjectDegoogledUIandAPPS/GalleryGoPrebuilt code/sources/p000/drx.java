package p000;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselItemView;
import p003j$.util.Optional;

/* renamed from: drx */
/* compiled from: PG */
final class drx extends gwe {

    /* renamed from: a */
    private final /* synthetic */ dry f7254a;

    public drx(dry dry) {
        this.f7254a = dry;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        apj apj;
        String str;
        Object obj2;
        drq drq = (drq) obj;
        drt a = ((CarouselItemView) view).mo2635n();
        a.f7252e = Optional.m16285of(drq);
        cxi a2 = drq.mo4372a();
        if (a2 != null) {
            if ((a2.f5909a & 4096) != 0) {
                apj = ((apj) a.f7250c.mo7307a().mo1419a((Object) cob.m4677a(Long.valueOf(a2.f5916h), a2.f5922n)).mo1854a((aqu) new bfa(Long.valueOf(a2.f5918j)))).mo1426b(a.f7251d.mo3293a());
            } else {
                apj a3 = a.f7250c.mo7307a();
                if (a2.f5912d) {
                    obj2 = ebh.m7086a(a2.f5910b);
                } else {
                    obj2 = a2.f5910b;
                }
                apj = ((apj) ((apj) ((apj) a3.mo1419a(obj2).mo1854a((aqu) new bfa(Long.valueOf(a2.f5918j)))).mo1860a(false)).mo1857a(atc.f1597a)).mo1426b(a.f7251d.mo3294b());
            }
            apj.mo1422a(a.f7249b);
            if (drq.mo4374c()) {
                a.f7248a.setAlpha(1.0f);
            } else {
                a.f7248a.setAlpha(0.0f);
            }
            String str2 = "";
            if (drq.mo4374c()) {
                str = a.f7249b.getResources().getString(R.string.carousel_item_burst_content_description_primary);
            } else {
                str = str2;
            }
            if (drq.mo4375d()) {
                str2 = a.f7249b.getResources().getString(R.string.carousel_item_burst_content_description_selected);
            }
            ImageView imageView = a.f7249b;
            imageView.setContentDescription(imageView.getResources().getString(R.string.carousel_item_burst_content_description, new Object[]{Integer.valueOf(drq.mo4373b() + 1), str, str2}));
            a.f7249b.setSelected(drq.mo4375d());
            return;
        }
        throw new IllegalStateException("Trying to load null media");
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return (CarouselItemView) this.f7254a.f7256b.inflate(R.layout.carousel_item_view, viewGroup, false);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        drt a = ((CarouselItemView) view).mo2635n();
        a.f7252e = Optional.empty();
        a.f7248a.setAlpha(0.0f);
        a.f7250c.mo7311a(a.f7249b);
    }
}
