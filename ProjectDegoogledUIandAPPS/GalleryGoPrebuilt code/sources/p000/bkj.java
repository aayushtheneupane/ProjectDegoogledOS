package p000;

import android.app.Activity;
import com.google.android.apps.photosgo.assassin.AssassinActivity;
import com.google.android.apps.photosgo.editor.ExternalEditorActivity;
import com.google.android.apps.photosgo.environment.BuildType;
import com.google.android.apps.photosgo.home.HomeActivity;
import com.google.android.apps.photosgo.oneup.ExternalOneUpActivity;
import com.google.android.apps.photosgo.picker.ExternalPickerActivity;
import com.google.android.apps.photosgo.settings.SettingsActivity;
import com.google.android.apps.photosgo.wallpaper.CropAndSetWallpaperActivity;
import com.google.android.apps.photosgo.wallpaper.SetWallpaperActivity;

/* renamed from: bkj */
/* compiled from: PG */
public final class bkj implements bll, blm, bwm, bwn, cqr, cqs, dlf, dlg, dsq, dsr, ead, eae, ebl, ebm, ehr, ehs, eid, eie, frr, ftq, hcj, hco, inx, iod {

    /* renamed from: a */
    public final Activity f3007a;

    /* renamed from: b */
    public final /* synthetic */ bjw f3008b;

    /* renamed from: c */
    private volatile Object f3009c;

    /* renamed from: d */
    private volatile iqk f3010d;

    /* renamed from: e */
    private volatile Object f3011e;

    /* renamed from: f */
    private volatile fwc f3012f;

    /* renamed from: g */
    private volatile iqk f3013g;

    /* renamed from: h */
    private volatile Object f3014h;

    /* renamed from: i */
    private volatile Object f3015i;

    /* renamed from: j */
    private volatile Object f3016j;

    /* renamed from: k */
    private volatile Object f3017k;

    /* renamed from: l */
    private volatile Object f3018l;

    /* renamed from: m */
    private volatile Object f3019m;

    /* renamed from: n */
    private volatile Object f3020n;

    /* renamed from: o */
    private volatile Object f3021o;

    /* renamed from: p */
    private volatile Object f3022p;

    public /* synthetic */ bkj(bjw bjw, Activity activity) {
        this.f3008b = bjw;
        this.f3009c = new iok();
        this.f3011e = new iok();
        this.f3014h = new iok();
        this.f3015i = new iok();
        this.f3016j = new iok();
        this.f3017k = new iok();
        this.f3018l = new iok();
        this.f3019m = new iok();
        this.f3020n = new iok();
        this.f3021o = new iok();
        this.f3022p = new iok();
        this.f3007a = activity;
    }

    /* renamed from: p */
    public final void mo2370p() {
    }

    /* renamed from: q */
    public final void mo2371q() {
    }

    /* renamed from: r */
    public final void mo2372r() {
    }

    /* renamed from: s */
    public final void mo2373s() {
    }

    /* renamed from: t */
    public final void mo2374t() {
    }

    /* renamed from: u */
    public final void mo2375u() {
    }

    /* renamed from: v */
    public final void mo2376v() {
    }

    /* renamed from: w */
    public final void mo2377w() {
    }

    /* renamed from: x */
    public final void mo2378x() {
    }

    /* renamed from: m */
    public final iob mo2367m() {
        return new bji(this);
    }

    /* renamed from: o */
    public final hcq mo2369o() {
        Object obj;
        Object obj2 = this.f3022p;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f3022p;
                if (obj instanceof iok) {
                    hcq hcq = new hcq(this.f3007a, hvf.f13465a, hto.m12121a((Object) (C0654y) iol.m14229a((Object) new dep(this.f3008b.mo2283bz()), "Cannot return null from a non-@Nullable @Provides method"), (Object) new blh(this.f3007a, this.f3008b.mo2241bJ())));
                    this.f3022p = iog.m14219a(this.f3022p, hcq);
                    obj = hcq;
                }
            }
            obj2 = obj;
        }
        return (hcq) obj2;
    }

    /* renamed from: a */
    public final fwc mo2355a() {
        fwc fwc = this.f3012f;
        if (fwc != null) {
            return fwc;
        }
        Activity activity = this.f3007a;
        ife.m12878b(activity instanceof fvw, "Cannot inject lifecycle for an activity that doesn't have a lifecycle: %s", (Object) activity);
        fwc fwc2 = (fwc) iol.m14229a((Object) ((fvw) activity).mo6228g(), "Cannot return null from a non-@Nullable @Provides method");
        this.f3012f = fwc2;
        return fwc2;
    }

    /* renamed from: b */
    public final blk mo2356b() {
        Activity activity = this.f3007a;
        if (activity instanceof AssassinActivity) {
            return new blk((AssassinActivity) iol.m14229a((Object) (AssassinActivity) activity, "Cannot return null from a non-@Nullable @Provides method"), this.f3008b.mo2324cx(), this.f3008b.mo2241bJ(), this.f3008b.mo2245bN());
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 220);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.assassin.AssassinActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: i */
    public final ehq mo2363i() {
        Activity activity = this.f3007a;
        if (activity instanceof CropAndSetWallpaperActivity) {
            return new ehq((CropAndSetWallpaperActivity) iol.m14229a((Object) (CropAndSetWallpaperActivity) activity, "Cannot return null from a non-@Nullable @Provides method"), (cnp) m2969y());
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 232);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.wallpaper.CropAndSetWallpaperActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: c */
    public final bwh mo2357c() {
        Activity activity = this.f3007a;
        if (activity instanceof ExternalEditorActivity) {
            return new bwh((ExternalEditorActivity) iol.m14229a((Object) (ExternalEditorActivity) activity, "Cannot return null from a non-@Nullable @Provides method"), (cnp) m2969y(), this.f3008b.mo2245bN());
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 224);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.editor.ExternalEditorActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: e */
    public final dlc mo2359e() {
        Object obj;
        Activity activity = this.f3007a;
        if (activity instanceof ExternalOneUpActivity) {
            ExternalOneUpActivity externalOneUpActivity = (ExternalOneUpActivity) iol.m14229a((Object) (ExternalOneUpActivity) activity, "Cannot return null from a non-@Nullable @Provides method");
            cnp cnp = (cnp) m2969y();
            fee bN = this.f3008b.mo2245bN();
            Class bO = this.f3008b.mo2246bO();
            Object obj2 = this.f3011e;
            if (obj2 instanceof iok) {
                synchronized (obj2) {
                    obj = this.f3011e;
                    if (obj instanceof iok) {
                        dsg dsg = new dsg(this.f3007a);
                        this.f3011e = iog.m14219a(this.f3011e, dsg);
                        obj = dsg;
                    }
                }
                obj2 = obj;
            }
            return new dlc(externalOneUpActivity, cnp, bN, bO, (dsg) obj2, this.f3008b.mo2235bD());
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 222);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.oneup.ExternalOneUpActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: g */
    public final dzy mo2361g() {
        Activity activity = this.f3007a;
        if (activity instanceof ExternalPickerActivity) {
            return new dzy((ExternalPickerActivity) iol.m14229a((Object) (ExternalPickerActivity) activity, "Cannot return null from a non-@Nullable @Provides method"), (cnp) m2969y(), this.f3008b.mo2245bN());
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 224);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.picker.ExternalPickerActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: y */
    private final Object m2969y() {
        Object obj;
        Object obj2 = this.f3009c;
        if (!(obj2 instanceof iok)) {
            return obj2;
        }
        synchronized (obj2) {
            obj = this.f3009c;
            if (obj instanceof iok) {
                cnm cnm = new cnm(this.f3007a);
                this.f3009c = iog.m14219a(this.f3009c, cnm);
                obj = cnm;
            }
        }
        return obj;
    }

    /* renamed from: d */
    public final cqm mo2358d() {
        Object obj;
        Activity activity = this.f3007a;
        if (activity instanceof HomeActivity) {
            HomeActivity homeActivity = (HomeActivity) iol.m14229a((Object) (HomeActivity) activity, "Cannot return null from a non-@Nullable @Provides method");
            cnp cnp = (cnp) m2969y();
            fee bN = this.f3008b.mo2245bN();
            iqk iqk = this.f3010d;
            if (iqk == null) {
                iqk = new bjk(this, 0);
                this.f3010d = iqk;
            }
            inw a = iog.m14218a(iqk);
            bjw bjw = this.f3008b;
            Object obj2 = bjw.f2962v;
            if (obj2 instanceof iok) {
                synchronized (obj2) {
                    obj = bjw.f2962v;
                    if (obj instanceof iok) {
                        BuildType b = ceo.m4214b();
                        boolean d = ((gxb) bjw.mo2183aE().f14635a.mo2097a()).mo7170a("com.google.android.apps.photosgo 44").mo7172d();
                        cjq a2 = cjr.m4407a(b);
                        a2.f4512d = d;
                        a2.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                        a2.f4511c = new BuildType[]{BuildType.DEV};
                        obj = (cjr) iol.m14229a((Object) a2.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                        bjw.f2962v = iog.m14219a(bjw.f2962v, obj);
                    }
                }
                obj2 = obj;
            }
            return new cqm(homeActivity, cnp, bN, a, (cjr) obj2);
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 212);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.home.HomeActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: z */
    private final fwc m2970z() {
        fwc fwc;
        iqk iqk = this.f3013g;
        boolean z = true;
        if (iqk == null) {
            iqk = new bjk(this, 1);
            this.f3013g = iqk;
        }
        hvf hvf = hvf.f13465a;
        if (hvf.isEmpty()) {
            fwc = (fwc) iqk.mo2097a();
        } else {
            if (hvf.f13466b != 1) {
                z = false;
            }
            ife.m12876b(z, (Object) "More than one fragment lifecycle found");
            fwc = (fwc) hvf.iterator().next();
        }
        return (fwc) iol.m14229a((Object) fwc, "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: k */
    public final fro mo2365k() {
        Object obj;
        fro fro;
        Object obj2 = this.f3015i;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f3015i;
                if (obj instanceof iok) {
                    Activity activity = this.f3007a;
                    fwc z = m2970z();
                    if (activity instanceof fub) {
                        fub fub = (fub) activity;
                        if (fub.mo6185a().mo6184b(fro.class) != null) {
                            fro = (fro) fub.mo6185a().mo6183a(fro.class);
                            obj = (fro) iol.m14229a((Object) fro, "Cannot return null from a non-@Nullable @Provides method");
                            this.f3015i = iog.m14219a(this.f3015i, obj);
                        }
                    }
                    fro = new fro(activity, z);
                    obj = (fro) iol.m14229a((Object) fro, "Cannot return null from a non-@Nullable @Provides method");
                    this.f3015i = iog.m14219a(this.f3015i, obj);
                }
            }
            obj2 = obj;
        }
        return (fro) obj2;
    }

    /* renamed from: l */
    public final frw mo2366l() {
        Object obj;
        frw frw;
        Object obj2 = this.f3014h;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f3014h;
                if (obj instanceof iok) {
                    Activity activity = this.f3007a;
                    fwc z = m2970z();
                    if (activity instanceof fub) {
                        fub fub = (fub) activity;
                        if (fub.mo6185a().mo6184b(frw.class) != null) {
                            frw = (frw) fub.mo6185a().mo6183a(frw.class);
                            obj = (frw) iol.m14229a((Object) frw, "Cannot return null from a non-@Nullable @Provides method");
                            this.f3014h = iog.m14219a(this.f3014h, obj);
                        }
                    }
                    frw = new frw(z);
                    obj = (frw) iol.m14229a((Object) frw, "Cannot return null from a non-@Nullable @Provides method");
                    this.f3014h = iog.m14219a(this.f3014h, obj);
                }
            }
            obj2 = obj;
        }
        return (frw) obj2;
    }

    /* renamed from: n */
    public final hpy mo2368n() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4 = this.f3021o;
        if (obj4 instanceof iok) {
            synchronized (obj4) {
                obj = this.f3021o;
                if (obj instanceof iok) {
                    fwc a = mo2355a();
                    Object obj5 = this.f3020n;
                    if (obj5 instanceof iok) {
                        synchronized (obj5) {
                            obj2 = this.f3020n;
                            if (obj2 instanceof iok) {
                                bjw bjw = this.f3008b;
                                Object obj6 = bjw.f2964x;
                                if (obj6 instanceof iok) {
                                    synchronized (obj6) {
                                        obj3 = bjw.f2964x;
                                        if (obj3 instanceof iok) {
                                            bjw.mo2248bQ();
                                            obj3 = (gku) iol.m14229a((Object) new gxi(), "Cannot return null from a non-@Nullable @Provides method");
                                            bjw.f2964x = iog.m14219a(bjw.f2964x, obj3);
                                        }
                                    }
                                    obj6 = obj3;
                                }
                                hto.m12120a((Object) (gku) obj6);
                                obj2 = new hgh();
                                this.f3020n = iog.m14219a(this.f3020n, obj2);
                            }
                        }
                        obj5 = obj2;
                    }
                    hgh hgh = (hgh) obj5;
                    gkv gkv = new gkv(a, this.f3008b.mo2249bR(), hph.f13219a);
                    this.f3021o = iog.m14219a(this.f3021o, gkv);
                    obj = gkv;
                }
            }
            obj4 = obj;
        }
        gkv gkv2 = new gkw((gkv) obj4).f11558a;
        fxk.m9830b();
        return hpy.m11893b((gkn) iol.m14229a((Object) gkn.m10445a(gkv2.f11553a, gtf.f12011a), "Cannot return null from a non-@Nullable @Provides method"));
    }

    /* renamed from: j */
    public final eib mo2364j() {
        Activity activity = this.f3007a;
        if (activity instanceof SetWallpaperActivity) {
            return new eib((SetWallpaperActivity) iol.m14229a((Object) (SetWallpaperActivity) activity, "Cannot return null from a non-@Nullable @Provides method"), cjp.m4403a(ftz.m9622a(this.f3008b.f2702a), cep.m4216b()));
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 225);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.wallpaper.SetWallpaperActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: h */
    public final ebk mo2362h() {
        Activity activity = this.f3007a;
        if (activity instanceof SettingsActivity) {
            return new ebk((SettingsActivity) iol.m14229a((Object) (SettingsActivity) activity, "Cannot return null from a non-@Nullable @Provides method"), this.f3008b.mo2245bN());
        }
        String valueOf = String.valueOf(activity.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 220);
        sb.append("Attempt to inject a Activity wrapper of type com.google.android.apps.photosgo.settings.SettingsActivityPeer, but the wrapper available is of type: ");
        sb.append(valueOf);
        sb.append(". Does your peer's @Inject constructor reference the wrong wrapper class?");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: f */
    public final dsp mo2360f() {
        Object obj;
        Activity activity = this.f3007a;
        bjw bjw = this.f3008b;
        Object obj2 = bjw.f2963w;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = bjw.f2963w;
                if (obj instanceof iok) {
                    obj = new dsm();
                    bjw.f2963w = iog.m14219a(bjw.f2963w, obj);
                }
            }
            obj2 = obj;
        }
        return new dsp(activity, (dsm) obj2);
    }

    public bkj() {
    }
}
