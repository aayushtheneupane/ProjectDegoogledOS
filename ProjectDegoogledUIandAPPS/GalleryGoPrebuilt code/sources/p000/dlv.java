package p000;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.photo.PhotoView;
import com.google.android.apps.photosgo.oneup.photo.ScalePhotoView;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselView;
import com.google.android.apps.photosgo.oneup.video.OneUpVideoView;
import com.google.android.apps.photosgo.oneup.video.VideoControlsView;
import java.util.ArrayList;
import p003j$.util.Optional;

/* renamed from: dlv */
/* compiled from: PG */
final class dlv extends gwe {

    /* renamed from: a */
    private final /* synthetic */ hbl f6804a;

    public dlv(hbl hbl) {
        this.f6804a = hbl;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        CarouselView carouselView;
        dls dls = (dls) obj;
        dlw a = ((dln) view).mo2635n();
        a.f6811g = Optional.m16285of(dls);
        a.f6805a.setContentDescription(a.f6807c.mo2529a(dls.f6796a, (String) dls.f6798c.map(dlt.f6802a).orElse((Object) null)));
        dll dll = a.f6808d;
        cxi cxi = dls.f6796a;
        Optional optional = dls.f6798c;
        int i = 0;
        boolean booleanValue = ((Boolean) optional.map(dli.f6776a).orElse(false)).booleanValue();
        Object[] objArr = {Boolean.valueOf(booleanValue), optional.map(dlj.f6777a)};
        if (!booleanValue) {
            ImageButton imageButton = dll.f6786f;
            if (imageButton != null) {
                imageButton.setVisibility(8);
            }
        } else {
            if (dll.f6786f == null) {
                dll.f6786f = (ImageButton) dll.f6785e.inflate();
            }
            fea fea = (fea) dll.f6784d.f9364c.mo5563a(84027).mo5513a(ffh.f9451a);
            String str = dll.f6787g;
            if (str != null && !str.equals(cxi.f5910b)) {
                fee.m8692a(dll.f6786f);
            }
            fea.mo5562b(dll.f6786f);
            dll.f6787g = cxi.f5910b;
            dik dik = (dik) optional.get();
            dll.f6786f.setVisibility(0);
            dll.f6786f.setContentDescription(dll.f6783c.getString(R.string.oneup_launch_button_a11y, new Object[]{dik.f6611c}));
            dll.f6786f.setOnClickListener(new dlk(dll, dik, cxi));
            dll.f6781a.mo7310a(dik.f6613e).mo1426b(dll.f6782b.mo3300d()).mo1422a((ImageView) dll.f6786f);
        }
        doj doj = a.f6809e;
        boolean z = (dls.f6796a.f5909a & 1048576) != 0;
        new Object[1][0] = Boolean.valueOf(z);
        if (!z) {
            View view2 = doj.f6940b;
            if (view2 != null) {
                view2.setVisibility(8);
            }
        } else {
            if (doj.f6940b == null) {
                doj.f6940b = doj.f6939a.inflate();
            }
            doj.f6940b.setVisibility(0);
        }
        if ((dls.f6796a.f5909a & 1048576) != 0 || cxh.IMAGE.equals(dls.mo4229a()) || (cxh.VIDEO.equals(dls.mo4229a()) && ((Boolean) dls.f6798c.map(dlu.f6803a).orElse(false)).booleanValue())) {
            OneUpVideoView oneUpVideoView = a.f6810f;
            if (oneUpVideoView != null) {
                oneUpVideoView.setVisibility(8);
            }
            a.f6806b.setVisibility(0);
            drg a2 = a.f6806b.mo2635n();
            a2.f7216h = Optional.m16285of(dls);
            Object[] objArr2 = {Integer.valueOf(a2.hashCode()), dls.f6796a.f5910b, Boolean.valueOf(a2.f7214f.isPresent())};
            if (!a2.f7214f.isPresent() || !cyc.m5649b(dls.f6796a, (cxi) a2.f7214f.get())) {
                a2.f7214f = Optional.m16285of(dls.f6796a);
                a2.f7215g = Optional.m16285of(dls.f6796a);
                dls.mo4231a((cxi) a2.f7215g.get());
                a2.mo4365f();
                if (a2.f7219k.mo3175a() && a2.f7214f.isPresent() && flw.m9194a((cxi) a2.f7214f.get())) {
                    if (a2.f7218j == null) {
                        ViewStub viewStub = (ViewStub) a2.f7209a.findViewById(R.id.carousel_view_stub);
                        if (viewStub == null) {
                            carouselView = (CarouselView) a2.f7209a.findViewById(R.id.carousel_view);
                        } else {
                            carouselView = (CarouselView) viewStub.inflate();
                        }
                        a2.f7218j = carouselView;
                    }
                    a2.f7218j.setVisibility(0);
                    dry a3 = a2.f7218j.mo2635n();
                    cxi cxi2 = (cxi) a2.f7214f.get();
                    ije ije = cxi2.f5929u;
                    a3.f7258d = new ArrayList();
                    for (int i2 = 0; i2 < ije.size(); i2++) {
                        if (((cxi) ije.get(i2)).f5911c == cxi2.f5911c) {
                            a3.f7259e = i2;
                            a3.f7260f = i2;
                            a3.f7258d.add(drq.m6538a((cxi) ije.get(i2), i2, true, true));
                        } else {
                            a3.f7258d.add(drq.m6538a((cxi) ije.get(i2), i2, false, false));
                        }
                    }
                    a3.f7257c.mo7129a(a3.f7258d);
                    a3.f7255a.mo10470d(a3.f7259e);
                }
            } else {
                a2.f7214f = Optional.m16285of(dls.f6796a);
                a2.f7215g = Optional.m16285of(dls.f6796a);
                dls.mo4231a((cxi) a2.f7215g.get());
            }
            dls.mo4232a((dlr) a.f6806b.mo2635n());
        } else if (cxh.VIDEO.equals(dls.mo4229a())) {
            a.f6806b.setVisibility(8);
            if (a.f6810f == null) {
                a.f6810f = (OneUpVideoView) ((ViewStub) a.f6805a.findViewById(R.id.video_view_stub)).inflate();
            }
            OneUpVideoView oneUpVideoView2 = a.f6810f;
            oneUpVideoView2.setVisibility(0);
            dsw a4 = oneUpVideoView2.mo2635n();
            Object[] objArr3 = {Integer.valueOf(a4.hashCode()), dls.f6796a.f5910b, Boolean.valueOf(a4.f7299e.isPresent())};
            if (!a4.f7299e.isPresent() || !cyc.m5649b(dls.f6796a, (cxi) a4.f7299e.get())) {
                a4.f7299e = Optional.m16285of(dls.f6796a);
                if (a4.f7296b.mo4409d()) {
                    a4.f7296b.mo4418m();
                }
                VideoControlsView videoControlsView = a4.f7295a;
                if (a4.f7297c.f7121b) {
                    i = 8;
                }
                videoControlsView.setVisibility(i);
                a4.f7296b.mo4404a((cxi) a4.f7299e.get());
            } else {
                a4.f7299e = Optional.m16285of(dls.f6796a);
            }
            dls.mo4232a((dlr) oneUpVideoView2.mo2635n());
        } else {
            throw new IllegalArgumentException("MediaViewPeer doesn't support UNKNOWN_MEDIA_TYPE.");
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        dln dln = new dln(this.f6804a);
        dln.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return dln;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        OneUpVideoView oneUpVideoView;
        dlw a = ((dln) view).mo2635n();
        if (a.f6811g.isPresent()) {
            dls dls = (dls) a.f6811g.get();
            dls.f6800e = Optional.empty();
            cxh cxh = cxh.UNKNOWN_MEDIA_TYPE;
            int ordinal = dls.mo4229a().ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    drg a2 = a.f6806b.mo2635n();
                    PhotoView photoView = a2.f7210b;
                    if (photoView != null) {
                        photoView.setVisibility(8);
                        a2.f7211c.mo7311a((ImageView) a2.f7210b);
                    }
                    if (a2.f7222n.isPresent()) {
                        a2.f7211c.mo7312a((ber) a2.f7222n.get());
                        a2.f7222n = Optional.empty();
                    }
                    a2.mo4368i();
                    CarouselView carouselView = a2.f7218j;
                    if (carouselView != null) {
                        carouselView.setVisibility(8);
                    }
                    ScalePhotoView scalePhotoView = a2.f7213e;
                    scalePhotoView.mo2001a(true);
                    scalePhotoView.f2295F = null;
                    scalePhotoView.f2296G = null;
                    a2.f7214f = Optional.empty();
                    a2.f7215g = Optional.empty();
                    a2.f7216h = Optional.empty();
                    a2.f7217i = Optional.empty();
                } else if (ordinal == 2 && (oneUpVideoView = a.f6810f) != null) {
                    dsw a3 = oneUpVideoView.mo2635n();
                    a3.f7299e = Optional.empty();
                    a3.f7296b.mo4418m();
                    View view2 = a3.f7300f;
                    if (view2 != null) {
                        view2.setVisibility(8);
                    }
                    a3.f7295a.setVisibility(8);
                    a3.f7298d = false;
                }
                a.f6811g = Optional.empty();
                return;
            }
            throw new IllegalStateException("MediaViewPeer does not support UNKNOWN_MEDIA_TYPE");
        }
    }
}
