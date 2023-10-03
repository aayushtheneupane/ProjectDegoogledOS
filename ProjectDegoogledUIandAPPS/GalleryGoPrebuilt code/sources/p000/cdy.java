package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: cdy */
/* compiled from: PG */
public final class cdy implements gry {

    /* renamed from: a */
    private final C0147fh f4140a;

    /* renamed from: b */
    private final cnh f4141b;

    /* renamed from: c */
    private final boolean f4142c;

    public cdy(C0147fh fhVar, cbx cbx, cnh cnh) {
        this.f4140a = fhVar;
        this.f4141b = cnh;
        this.f4142c = cbx.f4035d;
    }

    /* renamed from: a */
    public final void mo2649a(Object obj) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        Void voidR = (Void) obj;
        cwn.m5515b(th, "VideoTrimMixin: Unable to trim video.", new Object[0]);
        m4156a();
        boolean z = th instanceof cca;
        int i = R.string.unable_to_trim_video;
        if (!z) {
            if (C0643xp.m15943a(th)) {
                i = R.string.low_storage_error;
            }
        } else if (((cca) th).f4040a == 1) {
            i = R.string.cant_edit_bad_format;
        }
        View view = this.f4140a.f9573L;
        if (view != null) {
            gin.m10373a(view, i, -1).mo6715c();
        }
        if (this.f4142c) {
            ihg.m13041a((hoi) cce.m4042b(), this.f4140a);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        Void voidR = (Void) obj;
        imh imh = (imh) obj2;
        View view = this.f4140a.f9573L;
        if (view != null) {
            gin.m10373a(view, (int) R.string.trimmed_video_successfully, -1).mo6715c();
        }
        m4156a();
        cbw cbw = new cbw(Optional.m16285of((cxi) imh.mo8995a(cxi.f5908x, iij.m13502b())));
        if (this.f4142c) {
            ihg.m13041a((hoi) cbw, this.f4140a);
        } else {
            this.f4141b.mo3270a(cbw);
        }
    }

    /* renamed from: a */
    private final void m4156a() {
        C0147fh a = cdz.m4160a(this.f4140a);
        if (a != null) {
            C0182gn a2 = this.f4140a.mo5659r().mo6419a();
            a2.mo5243a(a);
            a2.mo5244a();
        }
    }
}
