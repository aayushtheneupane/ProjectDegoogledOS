package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView;
import com.google.android.apps.photosgo.oneup.photo.PhotoView;
import p003j$.util.Optional;

/* renamed from: ccm */
/* compiled from: PG */
public final class ccm extends ccg implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f4061Z;

    /* renamed from: b */
    private cdh f4062b;

    /* renamed from: c */
    private Context f4063c;

    /* renamed from: d */
    private final C0002ab f4064d = new C0002ab(this);

    @Deprecated
    public ccm() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f4064d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f4063c == null) {
            this.f4063c = new hcf((Context) this.f4049a);
        }
        return this.f4063c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo3030Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: a */
    public static ccm m4069a(cbx cbx) {
        ccm ccm = new ccm();
        ftr.m9611b(ccm);
        ftr.m9610a((C0147fh) ccm);
        hcl.m11210a(ccm, cbx);
        return ccm;
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f4049a != null) {
            return mo2628P();
        }
        return null;
    }

    /* renamed from: a */
    public final void mo2631a(Activity activity) {
        hlq c = hnb.m11779c();
        try {
            super.mo2631a(activity);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo1832a(Context context) {
        hlq c = hnb.m11779c();
        try {
            if (!this.f4061Z) {
                super.mo1832a(context);
                if (this.f4062b == null) {
                    this.f4062b = ((cdk) mo2453b()).mo2442t();
                    this.f9583V.mo64a(new hbu(this.f4064d));
                }
                if (c != null) {
                    c.close();
                    return;
                }
                return;
            }
            throw new IllegalStateException("A Fragment cannot be attached more than once. Instead, create a new Fragment instance.");
        } catch (ClassCastException e) {
            throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
        } catch (Throwable th) {
            if (c != null) {
                try {
                    c.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        dtl dtl;
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            cdh R = mo2635n();
            R.f4111j.mo7113a(R.f4107f, guy.DONT_CARE, R.f4117p);
            R.f4103b.mo5629J();
            View inflate = layoutInflater.inflate(R.layout.video_editor_fragment, viewGroup, false);
            View findViewById = inflate.findViewById(R.id.editor_appbar);
            Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.editor_top_toolbar);
            if (!R.f4104c.f4035d) {
                R.f4110i.mo2574a(toolbar, findViewById, (int) R.menu.top_bar_menu);
            } else {
                R.f4110i.mo2577b(toolbar, findViewById, R.menu.top_bar_menu);
            }
            toolbar.mo1096d((int) R.drawable.editor_top_bar_close_button);
            toolbar.mo1092c((int) R.string.photosgo_videotrimming_cancel_trimming_video);
            MenuItem findItem = toolbar.mo1099f().findItem(R.id.editor_top_bar_save);
            findItem.getActionView().setOnClickListener(new cdb(R.f4106e.mo7620a(new cda(R), "Menu item selected"), findItem));
            VideoView videoView = (VideoView) inflate.findViewById(R.id.video_view);
            dtl dtl2 = R.f4115n;
            if (dtl2 != null) {
                dtl2.mo4408b((dtj) R.f4113l);
            }
            R.f4115n = R.f4109h.mo4423a(videoView, (dsw) null, (PhotoView) null, (hdt) null);
            R.f4115n.mo4405a((dtj) R.f4113l);
            videoView.setOnErrorListener(cdc.f4097a);
            VideoTrimView videoTrimView = (VideoTrimView) inflate.findViewById(R.id.video_trim_view);
            cef a = videoTrimView.mo2635n();
            dtl dtl3 = (dtl) ife.m12898e((Object) R.f4115n);
            dtl dtl4 = a.f4178m;
            if (dtl4 != null) {
                dtl4.mo4408b(a.f4173h);
            }
            a.f4178m = dtl3;
            dtl3.mo4405a(a.f4173h);
            a.f4169d.mo2635n().f4129i = R.f4112k;
            R.f4110i.mo2575a((View) videoTrimView);
            ((View) ife.m12898e((Object) inflate.findViewById(R.id.play_button))).setOnClickListener(R.f4105d.mo7575a((View.OnClickListener) new cdd(R), "Clicked Play"));
            ((View) ife.m12898e((Object) inflate.findViewById(R.id.pause_button))).setOnClickListener(R.f4105d.mo7575a((View.OnClickListener) new cde(R), "Clicked Pause"));
            ((fea) R.f4114m.f9364c.mo5563a(74318).mo5513a(ffh.f9451a)).mo5560a(inflate);
            if (bundle != null) {
                if (bundle.containsKey("video_begin_trim")) {
                    a.f4179n = Optional.m16285of(Integer.valueOf(bundle.getInt("video_begin_trim")));
                }
                if (bundle.containsKey("video_end_trim")) {
                    a.f4180o = Optional.m16285of(Integer.valueOf(bundle.getInt("video_end_trim")));
                }
                if (bundle.containsKey("video_time_elapsed_ms") && (dtl = R.f4115n) != null && dtl.mo4413h()) {
                    R.f4115n.mo4403a(bundle.getInt("video_time_elapsed_ms"));
                }
            }
            if (inflate != null) {
                if (c != null) {
                    c.close();
                }
                return inflate;
            }
            throw new NullPointerException("Fragment cannot use Event annotations with null view!");
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f4061Z = true;
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final LayoutInflater mo2633b(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            LayoutInflater from = LayoutInflater.from(new hcf(LayoutInflater.from(new fts(mo5627H(), (C0147fh) this, true))));
            if (c != null) {
                c.close();
            }
            return from;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        cdh R = mo2635n();
        cef d = R.mo3049d();
        bundle.putInt("video_begin_trim", d.mo3073c());
        bundle.putInt("video_end_trim", d.mo3075d());
        dtl dtl = R.f4115n;
        if (dtl != null && dtl.mo4413h()) {
            bundle.putInt("video_time_elapsed_ms", R.f4115n.mo4421p());
        }
    }

    /* renamed from: a */
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            cdh R = mo2635n();
            ihg.m13038a((C0147fh) this, cen.class, (hol) new cdi(R));
            ihg.m13038a((C0147fh) this, cdq.class, (hol) new cdj(R));
            mo7267b(view, bundle);
            cdh R2 = mo2635n();
            dtl dtl = R2.f4115n;
            if (dtl == null || !dtl.mo4415j()) {
                R2.f4113l.mo3045b();
            } else {
                R2.f4113l.mo3043a();
            }
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final cdh mo2635n() {
        cdh cdh = this.f4062b;
        if (cdh == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4061Z) {
            return cdh;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
