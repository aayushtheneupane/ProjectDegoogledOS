package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.foldermanagement.picker.PickFolderToAddItemsFragmentPeer$1;
import com.google.android.apps.photosgo.foldermanagement.picker.PickFolderToAddItemsFragmentPeer$2;

/* renamed from: cmv */
/* compiled from: PG */
public final class cmv implements cnq, cnc {

    /* renamed from: a */
    public final cmo f4700a;

    /* renamed from: b */
    public final cmn f4701b;

    /* renamed from: c */
    public final cnh f4702c;

    /* renamed from: d */
    public final blu f4703d;

    /* renamed from: e */
    public final ckk f4704e;

    /* renamed from: f */
    public final grx f4705f;

    /* renamed from: g */
    public final iij f4706g;

    /* renamed from: h */
    public final egp f4707h;

    /* renamed from: i */
    public final gry f4708i;

    /* renamed from: j */
    public final gry f4709j;

    /* renamed from: k */
    public View f4710k;

    /* renamed from: l */
    public boolean f4711l = false;

    public cmv(cmo cmo, cmn cmn, cnh cnh, blu blu, ckk ckk, grx grx, iij iij, egp egp) {
        this.f4700a = cmo;
        this.f4701b = cmn;
        this.f4702c = cnh;
        this.f4703d = blu;
        this.f4704e = ckk;
        this.f4705f = grx;
        this.f4706g = iij;
        this.f4707h = egp;
        this.f4708i = new PickFolderToAddItemsFragmentPeer$1(new cmp(this), new cmq(this));
        this.f4709j = new PickFolderToAddItemsFragmentPeer$2(new cmr(this), new cms(this));
        cmn.f4686a.mo64a(egp);
    }

    /* renamed from: e */
    public final void mo2812e() {
    }

    /* renamed from: i */
    public final void mo2816i() {
    }

    /* renamed from: c */
    public final void mo3264c() {
        eaj eaj = (eaj) this.f4701b.mo5659r().mo6418a("progress_dialog_tag");
        if (eaj != null) {
            eaj.mo6295S();
        }
    }

    /* renamed from: j */
    public final void mo2638j() {
        this.f4710k.findViewById(R.id.folder_picker_appbar).setVisibility(0);
    }

    /* renamed from: b */
    public final void mo2637b() {
        this.f4710k.findViewById(R.id.folder_picker_appbar).setVisibility(4);
    }

    /* renamed from: h */
    public final void mo2815h() {
        this.f4711l = false;
    }
}
