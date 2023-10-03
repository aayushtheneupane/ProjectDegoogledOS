package p000;

import android.view.View;
import com.google.android.apps.photosgo.category.CategoryListView;
import com.google.android.apps.photosgo.category.CategoryView;
import com.google.android.apps.photosgo.devicefolders.DeviceFoldersGridView;
import com.google.android.apps.photosgo.devicefolders.FolderView;
import com.google.android.apps.photosgo.devicefolders.NewFolderView;
import com.google.android.apps.photosgo.devicefolders.PromoView;
import com.google.android.apps.photosgo.editor.presets.PresetItemView;
import com.google.android.apps.photosgo.editor.presets.PresetSelectionView;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoThumbnailView;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView;
import com.google.android.apps.photosgo.environment.BuildType;
import com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserView;
import com.google.android.apps.photosgo.infosheet.InfoSheetListView;
import com.google.android.apps.photosgo.infosheet.InfoView;
import com.google.android.apps.photosgo.oneup.OneUpPagerView;
import com.google.android.apps.photosgo.oneup.photo.OneUpPhotoView;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselItemView;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselView;
import com.google.android.apps.photosgo.oneup.video.OneUpVideoView;
import com.google.android.apps.photosgo.oneup.video.VideoControlsView;
import com.google.android.apps.photosgo.peoplegrid.PeopleGridView;
import com.google.android.apps.photosgo.photogrid.DateHeaderView;
import com.google.android.apps.photosgo.photogrid.PhotoGridView;
import com.google.android.apps.photosgo.photogrid.PhotosPromoView;
import com.google.android.apps.photosgo.photogrid.SinglePhotoView;
import com.google.android.apps.photosgo.sharing.SharingAppGridView;
import com.google.android.apps.photosgo.sharing.SingleAppView;

/* renamed from: bkq */
/* compiled from: PG */
public final class bkq implements bnv, bnw, boo, bop, brv, brw, brz, bsa, bsf, bsg, bsi, bsj, bsn, bso, cbk, cbl, cbr, cbs, cdo, cdp, ceg, ceh, cma, cmb, csz, cta, ctc, ctd, dkn, dko, dlx, dly, dlz, dma, doh, doi, dri, drj, dru, drv, dsa, dsb, dsx, dsy, dts, dtt, duq, dur, dux, duy, dvu, dvv, dwb, dwc, dxh, dxi, dxm, dxn, dxv, dxw, ecu, ecv, edo, edp, hby, iod {

    /* renamed from: a */
    private final View f3067a;

    /* renamed from: b */
    private volatile Object f3068b;

    /* renamed from: c */
    private volatile cnx f3069c;

    /* renamed from: d */
    private volatile ctk f3070d;

    /* renamed from: e */
    private volatile bkt f3071e;

    /* renamed from: f */
    private volatile iqk f3072f;

    /* renamed from: g */
    private final /* synthetic */ bkl f3073g;

    public /* synthetic */ bkq(bkl bkl, View view) {
        this.f3073g = bkl;
        this.f3068b = new iok();
        this.f3067a = view;
    }

    /* renamed from: E */
    public final void mo2468E() {
    }

    /* renamed from: F */
    public final void mo2469F() {
    }

    /* renamed from: G */
    public final void mo2470G() {
    }

    /* renamed from: H */
    public final void mo2471H() {
    }

    /* renamed from: I */
    public final void mo2472I() {
    }

    /* renamed from: J */
    public final void mo2473J() {
    }

    /* renamed from: K */
    public final void mo2474K() {
    }

    /* renamed from: L */
    public final void mo2475L() {
    }

    /* renamed from: M */
    public final void mo2476M() {
    }

    /* renamed from: N */
    public final void mo2477N() {
    }

    /* renamed from: O */
    public final void mo2478O() {
    }

    /* renamed from: P */
    public final void mo2479P() {
    }

    /* renamed from: Q */
    public final void mo2480Q() {
    }

    /* renamed from: R */
    public final void mo2481R() {
    }

    /* renamed from: S */
    public final void mo2482S() {
    }

    /* renamed from: T */
    public final void mo2483T() {
    }

    /* renamed from: U */
    public final void mo2484U() {
    }

    /* renamed from: V */
    public final void mo2485V() {
    }

    /* renamed from: W */
    public final void mo2486W() {
    }

    /* renamed from: X */
    public final void mo2487X() {
    }

    /* renamed from: Y */
    public final void mo2488Y() {
    }

    /* renamed from: Z */
    public final void mo2489Z() {
    }

    /* renamed from: aa */
    public final void mo2491aa() {
    }

    /* renamed from: ab */
    public final void mo2492ab() {
    }

    /* renamed from: ac */
    public final void mo2493ac() {
    }

    /* renamed from: ad */
    public final void mo2494ad() {
    }

    /* renamed from: ae */
    public final void mo2495ae() {
    }

    /* renamed from: af */
    public final void mo2496af() {
    }

    /* renamed from: ag */
    public final void mo2497ag() {
    }

    /* renamed from: ah */
    public final void mo2498ah() {
    }

    /* renamed from: ai */
    public final void mo2499ai() {
    }

    /* renamed from: aj */
    public final void mo2500aj() {
    }

    /* renamed from: s */
    public final drt mo2521s() {
        View view = this.f3067a;
        if (view instanceof CarouselItemView) {
            return new drt((CarouselItemView) iol.m14229a((Object) (CarouselItemView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.f3032b.f3006c.mo2459e(), mo2490a(), m3112ao());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 228);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.photo.carousel.CarouselItemViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: t */
    public final dry mo2522t() {
        View view = this.f3067a;
        if (view instanceof CarouselView) {
            return new dry((CarouselView) iol.m14229a((Object) (CarouselView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 224);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.photo.carousel.CarouselViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: b */
    public final bnu mo2504b() {
        hbl c = this.f3073g.mo2425c();
        View view = this.f3067a;
        if (view instanceof CategoryListView) {
            return new bnu(c, (CategoryListView) iol.m14229a((Object) (CategoryListView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2431i(), this.f3073g.mo2434l());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 216);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.category.CategoryListViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: c */
    public final bon mo2505c() {
        hbl c = this.f3073g.mo2425c();
        View view = this.f3067a;
        if (view instanceof CategoryView) {
            return new bon(c, (CategoryView) iol.m14229a((Object) (CategoryView) view, "Cannot return null from a non-@Nullable @Provides method"), mo2490a(), this.f3073g.f3032b.f3006c.f3063d.mo2180aB(), this.f3073g.mo2431i(), this.f3073g.f3032b.f3006c.mo2459e(), this.f3073g.f3032b.f3006c.f3063d.mo2245bN(), this.f3073g.f3032b.f3006c.f3063d.mo2250bS());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 212);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.category.CategoryViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: ap */
    private final bkt m3113ap() {
        bkt bkt = this.f3071e;
        if (bkt != null) {
            return bkt;
        }
        bki bki = this.f3073g.f3032b;
        bkt bkt2 = new bkt(bki.f3004a, bki.f3006c.f3063d.mo2270bm());
        this.f3071e = bkt2;
        return bkt2;
    }

    /* renamed from: y */
    public final dvt mo2527y() {
        View view = this.f3067a;
        if (view instanceof DateHeaderView) {
            return new dvt((DateHeaderView) iol.m14229a((Object) (DateHeaderView) view, "Cannot return null from a non-@Nullable @Provides method"));
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 215);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.photogrid.DateHeaderViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: d */
    public final bru mo2506d() {
        View view = this.f3067a;
        if (view instanceof DeviceFoldersGridView) {
            bkl bkl = this.f3073g;
            return new bru((DeviceFoldersGridView) iol.m14229a((Object) (DeviceFoldersGridView) view, "Cannot return null from a non-@Nullable @Provides method"), bkl.f3031a, bkl.mo2425c(), m3111an());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 226);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.devicefolders.DeviceFoldersGridViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: e */
    public final bry mo2507e() {
        View view = this.f3067a;
        if (view instanceof FolderView) {
            return new bry((FolderView) iol.m14229a((Object) (FolderView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), mo2490a(), m3112ao(), this.f3073g.f3032b.f3006c.mo2459e());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 215);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.devicefolders.FolderViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: f */
    public final bse mo2508f() {
        View view = this.f3067a;
        if (view instanceof bsc) {
            return new bse((bsc) iol.m14229a((Object) (bsc) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 224);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.devicefolders.FoldersGridItemViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: ao */
    private final cnx m3112ao() {
        cnx cnx = this.f3069c;
        if (cnx != null) {
            return cnx;
        }
        cnx cnx2 = new cnx(this.f3073g.f3032b.f3006c.f3063d.mo2180aB(), (coa) this.f3073g.f3032b.f3006c.f3063d.mo2179aA(), this.f3073g.f3032b.f3004a);
        this.f3069c = cnx2;
        return cnx2;
    }

    /* renamed from: an */
    private final cps m3111an() {
        return new cps(ftz.m9622a(this.f3073g.f3032b.f3006c.f3063d.f2702a));
    }

    /* renamed from: z */
    public final dwa mo2528z() {
        View view = this.f3067a;
        if (view instanceof dvy) {
            return new dwa((dvy) iol.m14229a((Object) (dvy) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 213);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.photogrid.GridItemViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: m */
    public final csy mo2515m() {
        hbl c = this.f3073g.mo2425c();
        View view = this.f3067a;
        if (view instanceof InfoSheetListView) {
            InfoSheetListView infoSheetListView = (InfoSheetListView) iol.m14229a((Object) (InfoSheetListView) view, "Cannot return null from a non-@Nullable @Provides method");
            ctk ctk = this.f3070d;
            if (ctk == null) {
                bki bki = this.f3073g.f3032b;
                ctk ctk2 = new ctk(bki.f3004a, bki.f3006c.f3063d.mo2204aZ());
                this.f3070d = ctk2;
                ctk = ctk2;
            }
            return new csy(c, infoSheetListView, ctk);
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 218);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.infosheet.InfoSheetListViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: n */
    public final ctb mo2516n() {
        hbl c = this.f3073g.mo2425c();
        View view = this.f3067a;
        if (view instanceof InfoView) {
            return new ctb(c, (InfoView) iol.m14229a((Object) (InfoView) view, "Cannot return null from a non-@Nullable @Provides method"));
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 209);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.infosheet.InfoViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: p */
    public final dlw mo2518p() {
        View view = this.f3067a;
        if (view instanceof dln) {
            dln dln = (dln) iol.m14229a((Object) (dln) view, "Cannot return null from a non-@Nullable @Provides method");
            hbl c = this.f3073g.mo2425c();
            bkt ap = m3113ap();
            iqk iqk = this.f3072f;
            if (iqk == null) {
                iqk = new bjp(this);
                this.f3072f = iqk;
            }
            return new dlw(dln, c, ap, new dlm(iqk, this.f3073g.f3032b.f3006c.f3063d.mo2302cb(), this.f3073g.mo2428f(), this.f3073g.f3032b.f3006c.f3063d.mo2304cd()));
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 206);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.MediaViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: am */
    public final gbz mo2503am() {
        View view = this.f3067a;
        if (view instanceof NewFolderView) {
            return new gbz((NewFolderView) iol.m14229a((Object) (NewFolderView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.mo2459e());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 218);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.devicefolders.NewFolderViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: al */
    public final dvg mo2502al() {
        return new dvg();
    }

    /* renamed from: o */
    public final dkm mo2517o() {
        View view = this.f3067a;
        if (view instanceof dkk) {
            return new dkm((dkk) iol.m14229a((Object) (dkk) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.mo2459e(), this.f3073g.f3032b.f3006c.f3063d.mo2306cf(), this.f3073g.f3032b.f3006c.f3063d.mo2307cg(), this.f3073g.f3032b.f3006c.f3063d.mo2312cl(), this.f3073g.f3032b.f3006c.f3063d.mo2245bN(), this.f3073g.f3032b.f3006c.f3063d.mo2250bS());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 216);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.onboarding.OnboardingViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: q */
    public final dog mo2519q() {
        View view = this.f3067a;
        if (view instanceof OneUpPagerView) {
            return new dog((OneUpPagerView) iol.m14229a((Object) (OneUpPagerView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.f3063d.mo2270bm());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 211);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.OneUpPagerViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: r */
    public final drg mo2520r() {
        View view = this.f3067a;
        if (view instanceof OneUpPhotoView) {
            return new drg((OneUpPhotoView) iol.m14229a((Object) (OneUpPhotoView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), mo2490a(), this.f3073g.f3032b.f3006c.mo2455a(), this.f3073g.f3032b.f3006c.f3063d.mo2180aB(), this.f3073g.mo2427e(), this.f3073g.mo2426d(), this.f3073g.f3032b.f3006c.f3063d.mo2270bm());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 217);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.photo.OneUpPhotoViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: u */
    public final dsw mo2523u() {
        View view = this.f3067a;
        if (view instanceof OneUpVideoView) {
            return new dsw((OneUpVideoView) iol.m14229a((Object) (OneUpVideoView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.mo2405a(), mo2490a(), this.f3073g.f3032b.f3006c.mo2455a(), this.f3073g.mo2427e(), this.f3073g.mo2426d());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 217);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.video.OneUpVideoViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: w */
    public final dup mo2525w() {
        View view = this.f3067a;
        if (view instanceof dum) {
            return new dup((dum) iol.m14229a((Object) (dum) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), mo2490a(), m3112ao(), this.f3073g.f3032b.f3006c.mo2459e(), this.f3073g.mo2431i());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 220);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.peoplegrid.PeopleGridItemViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: x */
    public final duw mo2526x() {
        View view = this.f3067a;
        if (view instanceof PeopleGridView) {
            hbl c = this.f3073g.mo2425c();
            cps an = m3111an();
            this.f3073g.mo2430h();
            return new duw((PeopleGridView) iol.m14229a((Object) (PeopleGridView) view, "Cannot return null from a non-@Nullable @Provides method"), c, an);
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 216);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.peoplegrid.PeopleGridViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: A */
    public final dxg mo2464A() {
        Object obj;
        C0147fh fhVar = this.f3073g.f3031a;
        View view = this.f3067a;
        if (view instanceof PhotoGridView) {
            PhotoGridView photoGridView = (PhotoGridView) iol.m14229a((Object) (PhotoGridView) view, "Cannot return null from a non-@Nullable @Provides method");
            hbl c = this.f3073g.mo2425c();
            cps an = m3111an();
            ebf k = this.f3073g.mo2433k();
            bjw bjw = this.f3073g.f3032b.f3006c.f3063d;
            Object obj2 = bjw.f2698W;
            if (obj2 instanceof iok) {
                synchronized (obj2) {
                    obj = bjw.f2698W;
                    if (obj instanceof iok) {
                        BuildType b = ceo.m4214b();
                        boolean d = ((gxb) bjw.mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 47").mo7172d();
                        cjq a = cjr.m4407a(b);
                        a.f4512d = d;
                        a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                        a.f4511c = new BuildType[0];
                        obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                        bjw.f2698W = iog.m14219a(bjw.f2698W, obj);
                    }
                }
                obj2 = obj;
            }
            return new dxg(fhVar, photoGridView, c, an, k, (cjr) obj2);
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 214);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.photogrid.PhotoGridViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: B */
    public final dxk mo2465B() {
        View view = this.f3067a;
        if (view instanceof PhotosPromoView) {
            return new dxk((PhotosPromoView) iol.m14229a((Object) (PhotosPromoView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.f3063d.mo2245bN(), this.f3073g.f3032b.f3006c.f3063d.mo2250bS());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 216);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.photogrid.PhotosPromoViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: h */
    public final cbi mo2510h() {
        View view = this.f3067a;
        if (view instanceof PresetItemView) {
            return new cbi((PresetItemView) iol.m14229a((Object) (PresetItemView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 220);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.editor.presets.PresetItemViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: i */
    public final cbp mo2511i() {
        View view = this.f3067a;
        if (view instanceof PresetSelectionView) {
            return new cbp((PresetSelectionView) iol.m14229a((Object) (PresetSelectionView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 225);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.editor.presets.PresetSelectionViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: g */
    public final bsl mo2509g() {
        View view = this.f3067a;
        if (view instanceof PromoView) {
            return new bsl((PromoView) iol.m14229a((Object) (PromoView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.mo2459e(), this.f3073g.f3032b.f3006c.f3063d.mo2245bN(), this.f3073g.f3032b.f3006c.f3063d.mo2250bS());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 214);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.devicefolders.PromoViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: C */
    public final ect mo2466C() {
        View view = this.f3067a;
        if (view instanceof SharingAppGridView) {
            return new ect((SharingAppGridView) iol.m14229a((Object) (SharingAppGridView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 217);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.sharing.SharingAppGridViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: D */
    public final edn mo2467D() {
        View view = this.f3067a;
        if (view instanceof SingleAppView) {
            return new edn((SingleAppView) iol.m14229a((Object) (SingleAppView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.f3032b.f3006c.mo2459e(), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.f3063d.mo2245bN(), this.f3073g.f3032b.f3006c.f3063d.mo2250bS());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 212);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.sharing.SingleAppViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: ak */
    public final dxu mo2501ak() {
        hbl c = this.f3073g.mo2425c();
        View view = this.f3067a;
        if (view instanceof SinglePhotoView) {
            return new dxu(c, (SinglePhotoView) iol.m14229a((Object) (SinglePhotoView) view, "Cannot return null from a non-@Nullable @Provides method"), mo2490a(), this.f3073g.mo2431i(), this.f3073g.mo2432j(), this.f3073g.f3032b.f3006c.mo2459e(), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.f3063d.mo2180aB(), m3112ao(), this.f3073g.mo2433k(), m3113ap(), this.f3073g.f3032b.f3006c.f3063d.mo2270bm(), this.f3073g.f3032b.f3006c.f3063d.mo2204aZ());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 216);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.photogrid.SinglePhotoViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: l */
    public final clz mo2514l() {
        View view = this.f3067a;
        if (view instanceof SingleVolumeChooserView) {
            return new clz((SingleVolumeChooserView) iol.m14229a((Object) (SingleVolumeChooserView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2424b(), this.f3073g.f3032b.f3006c.mo2455a());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 240);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: v */
    public final dtr mo2524v() {
        View view = this.f3067a;
        if (view instanceof VideoControlsView) {
            return new dtr((VideoControlsView) iol.m14229a((Object) (VideoControlsView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c(), this.f3073g.f3032b.f3006c.mo2455a(), this.f3073g.mo2426d());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 220);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.oneup.video.VideoControlsViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: j */
    public final cdn mo2512j() {
        hbl c = this.f3073g.mo2425c();
        View view = this.f3067a;
        if (view instanceof VideoThumbnailView) {
            return new cdn(c, (VideoThumbnailView) iol.m14229a((Object) (VideoThumbnailView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.f3032b.f3006c.mo2455a());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 239);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoThumbnailViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: k */
    public final cef mo2513k() {
        View view = this.f3067a;
        if (view instanceof VideoTrimView) {
            return new cef((VideoTrimView) iol.m14229a((Object) (VideoTrimView) view, "Cannot return null from a non-@Nullable @Provides method"), this.f3073g.mo2425c());
        }
        String valueOf = String.valueOf(view.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 234);
        sb.append("Attempt to inject a View wrapper of type com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimViewPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: a */
    public final hdt mo2490a() {
        Object obj;
        Object obj2 = this.f3068b;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f3068b;
                if (obj instanceof iok) {
                    obj = (hdt) iol.m14229a((Object) this.f3073g.mo2429g(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f3068b = iog.m14219a(this.f3068b, obj);
                }
            }
            obj2 = obj;
        }
        return (hdt) obj2;
    }

    public bkq() {
    }
}
