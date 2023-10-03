package p000;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.photo.OneUpPhotoView;
import com.google.android.apps.photosgo.oneup.photo.PhotoView;
import com.google.android.apps.photosgo.oneup.photo.ScalePhotoView;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselView;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: drg */
/* compiled from: PG */
public final class drg implements dqw, dlr {

    /* renamed from: a */
    public final OneUpPhotoView f7209a;

    /* renamed from: b */
    public PhotoView f7210b;

    /* renamed from: c */
    public final hdt f7211c;

    /* renamed from: d */
    public View f7212d;

    /* renamed from: e */
    public final ScalePhotoView f7213e;

    /* renamed from: f */
    public Optional f7214f = Optional.empty();

    /* renamed from: g */
    public Optional f7215g = Optional.empty();

    /* renamed from: h */
    public Optional f7216h = Optional.empty();

    /* renamed from: i */
    public Optional f7217i = Optional.empty();

    /* renamed from: j */
    public CarouselView f7218j;

    /* renamed from: k */
    public final cjr f7219k;

    /* renamed from: l */
    public final ViewGroup f7220l;

    /* renamed from: m */
    public final int f7221m;

    /* renamed from: n */
    public Optional f7222n = Optional.empty();

    /* renamed from: o */
    private final View f7223o;

    /* renamed from: p */
    private final hlz f7224p;

    /* renamed from: q */
    private final cny f7225q;

    /* renamed from: r */
    private final dql f7226r;

    /* renamed from: s */
    private final bee f7227s = new dra(this);

    /* renamed from: t */
    private final bee f7228t = new drb(this);

    /* renamed from: u */
    private final ism f7229u = new drc(this);

    /* renamed from: v */
    private final View.OnClickListener f7230v = new dqy(this);

    /* renamed from: w */
    private final bgj f7231w = new drd(this);

    /* renamed from: x */
    private final ber f7232x = new dre(this);

    public drg(OneUpPhotoView oneUpPhotoView, hbl hbl, hdt hdt, hlz hlz, cny cny, dqx dqx, dql dql, cjr cjr) {
        this.f7209a = oneUpPhotoView;
        this.f7226r = dql;
        this.f7219k = cjr;
        LayoutInflater.from(hbl).inflate(R.layout.oneup_photo_view_contents, oneUpPhotoView, true);
        this.f7211c = hdt;
        this.f7224p = hlz;
        this.f7225q = cny;
        ScalePhotoView scalePhotoView = (ScalePhotoView) oneUpPhotoView.findViewById(R.id.photo_view_scale_view);
        this.f7213e = scalePhotoView;
        scalePhotoView.setOnClickListener(this.f7230v);
        ScalePhotoView scalePhotoView2 = this.f7213e;
        if (bgo.f2288b.contains(2)) {
            scalePhotoView2.f2333i = 2;
            ScalePhotoView scalePhotoView3 = this.f7213e;
            scalePhotoView3.f2293D = this.f7231w;
            ife.m12845a(true, (Object) "zoom factor must be > 1");
            scalePhotoView3.f4888I = Optional.m16285of(Float.valueOf(2.0f));
            ScalePhotoView scalePhotoView4 = this.f7213e;
            ife.m12845a(true, (Object) "zoom factor must be > 1");
            scalePhotoView4.f4889J = Optional.m16285of(Float.valueOf(4.0f));
            this.f7220l = (ViewGroup) oneUpPhotoView.findViewById(R.id.carousel);
            this.f7221m = cjr.mo3175a() ? oneUpPhotoView.getResources().getDimensionPixelOffset(R.dimen.design_bottom_navigation_height) : 0;
            this.f7223o = oneUpPhotoView.findViewById(R.id.oneup_bottom_gradient);
            dqx.mo4354a((dqw) this);
            dqx.mo4356b((dqw) this);
            return;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("Invalid zoom style: 2");
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public final void mo4221a(int i) {
    }

    /* renamed from: a */
    public final void mo4222a(Bundle bundle) {
    }

    /* renamed from: a */
    public final void mo4223a(boolean z) {
    }

    /* renamed from: b */
    public final void mo4225b() {
    }

    /* renamed from: c */
    public final void mo4226c() {
    }

    /* renamed from: d */
    public final Bundle mo4227d() {
        return null;
    }

    /* renamed from: a */
    public final boolean mo4224a() {
        PhotoView photoView = this.f7210b;
        if (photoView != null && photoView.getVisibility() == 0) {
            return m6510a(1.0f, this.f7210b.f15001a.mo9096d());
        }
        return m6510a(this.f7213e.mo2007e(), this.f7213e.f2334j);
    }

    /* renamed from: k */
    public final /* bridge */ /* synthetic */ List mo4262k() {
        return hso.m12033a((Object) this.f7223o);
    }

    /* renamed from: i */
    public final void mo4368i() {
        View view = this.f7212d;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* renamed from: j */
    public final void mo4369j() {
        PhotoView photoView = this.f7210b;
        if (photoView != null && photoView.getVisibility() != 8) {
            new Object[1][0] = Integer.valueOf(hashCode());
            this.f7210b.setVisibility(8);
        }
    }

    /* renamed from: a */
    private static boolean m6510a(float f, float f2) {
        return f > 0.0f && Math.abs(f - f2) <= f * 0.02f;
    }

    /* renamed from: g */
    public final void mo4366g() {
        if (this.f7212d == null) {
            this.f7212d = ((ViewStub) this.f7209a.findViewById(R.id.photo_error_view_stub)).inflate();
        }
    }

    /* renamed from: e */
    public final void mo4228e() {
        mo4365f();
    }

    /* renamed from: f */
    public final void mo4365f() {
        drf drf;
        Object obj;
        cxi cxi = (cxi) this.f7215g.get();
        int i = cxi.f5909a;
        if (!((i & 1048576) == 0 || (i & 2) == 0)) {
            this.f7217i = Optional.m16285of(new Uri.Builder().scheme("content").authority(cxi.f5930v).appendPath("processing").appendPath(String.valueOf(cxi.f5911c)).build());
        }
        mo4368i();
        Optional optional = this.f7217i;
        int i2 = cxi.f5909a;
        if ((i2 & 1048576) == 0 || (i2 & 2) == 0 || !optional.isPresent()) {
            if (cxi.f5912d) {
                obj = ebh.m7086a(cxi.f5910b);
            } else {
                obj = cxi.f5910b;
            }
            drf = new drf(obj, new bfa(Long.valueOf(cxi.f5918j)), Uri.parse(cxi.f5910b));
        } else {
            drf = new drf(optional.get(), new bfa(Long.valueOf(cxi.f5911c)), (Uri) optional.get());
        }
        Optional optional2 = this.f7217i;
        drf drf2 = null;
        if (optional2.isPresent() && (cxi.f5909a & 1048576) == 0) {
            drf2 = new drf(optional2.get(), new bfa(Long.valueOf(cxi.f5911c)), (Uri) optional2.get());
        }
        Object[] objArr = {drf, drf2};
        String str = ((cxi) this.f7215g.get()).f5914f;
        if ((!"image/jpeg".equals(str) && !"image/png".equals(str)) || (cxi.f5909a & 1048576) != 0) {
            new Object[1][0] = Integer.valueOf(hashCode());
            this.f7213e.setVisibility(8);
            if (this.f7210b == null) {
                ViewStub viewStub = (ViewStub) this.f7209a.findViewById(R.id.photo_view_stub);
                PhotoView photoView = (PhotoView) (viewStub == null ? this.f7209a.findViewById(R.id.photo_view) : viewStub.inflate());
                this.f7210b = photoView;
                photoView.mo3403a(1.0f, 2.0f, 4.0f);
                this.f7210b.mo3404a(this.f7229u);
                this.f7210b.mo3405a(true);
            }
            this.f7210b.setVisibility(0);
            apj a = ((apj) this.f7211c.mo7309a(drf.f7206a).mo1854a((aqu) drf.f7207b)).mo1426b(this.f7225q.mo3299c()).mo1418a(this.f7227s);
            if (drf2 != null) {
                a = a.mo1423b(((apj) this.f7211c.mo7309a(drf2.f7206a).mo1854a((aqu) drf2.f7207b)).mo1426b(this.f7225q.mo3299c()));
            }
            a.mo1422a((ImageView) this.f7210b);
            return;
        }
        this.f7213e.setVisibility(0);
        int i3 = cxi.f5909a;
        if ((i3 & 8192) == 0 || (i3 & 16384) == 0 || (i3 & 32768) == 0 || cxi.f5923o <= 0 || cxi.f5924p <= 0) {
            new Object[1][0] = Integer.valueOf(hashCode());
            this.f7213e.mo1993a(-1);
            this.f7213e.mo1998a(bfz.m2453a(drf.f7208c));
            return;
        }
        new Object[1][0] = Integer.valueOf(hashCode());
        Optional optional3 = this.f7222n;
        hdt hdt = this.f7211c;
        hdt.getClass();
        optional3.ifPresent(new dqz(hdt));
        apj a2 = ((apj) ((apj) this.f7211c.mo7307a().mo1419a(drf.f7206a).mo1854a((aqu) drf.f7207b)).mo1426b(this.f7225q.mo3299c()).mo1856a((ard) new bar(-cxi.f5925q))).mo1418a(this.f7228t);
        if (drf2 != null) {
            a2 = a2.mo1423b(((apj) this.f7211c.mo7307a().mo1419a(drf2.f7206a).mo1854a((aqu) drf2.f7207b)).mo1426b(this.f7225q.mo3299c()));
        }
        this.f7222n = Optional.m16285of(a2.mo1421a(hnr.m11806a(this.f7232x)));
    }

    /* renamed from: h */
    public final void mo4367h() {
        hlp a = this.f7224p.mo7577a("onOneUpViewPhotoTap");
        try {
            this.f7226r.mo4341a();
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
