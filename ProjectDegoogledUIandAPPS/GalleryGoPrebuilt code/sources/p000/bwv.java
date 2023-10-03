package p000;

import android.net.Uri;
import android.view.View;
import android.view.ViewStub;
import com.google.android.material.button.MaterialButton;
import p003j$.util.Optional;

/* renamed from: bwv */
/* compiled from: PG */
final class bwv implements gvc {

    /* renamed from: a */
    public final /* synthetic */ bwx f3784a;

    /* renamed from: b */
    private final /* synthetic */ ViewStub f3785b;

    /* renamed from: c */
    private final /* synthetic */ bul f3786c;

    public bwv(bwx bwx, ViewStub viewStub, bul bul) {
        this.f3784a = bwx;
        this.f3785b = viewStub;
        this.f3786c = bul;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5511a(th, "OemExternalEditorButtonMixin: Failed to load editor data.", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        Optional optional = (Optional) obj;
        if (optional.isPresent()) {
            dhq dhq = (dhq) optional.get();
            MaterialButton materialButton = (MaterialButton) this.f3785b.inflate();
            materialButton.setOnClickListener(this.f3784a.f3794f.mo7575a((View.OnClickListener) new bws(this, this.f3786c, dhq), "Edit"));
            bwx bwx = this.f3784a;
            Uri a = dhq.mo4137a();
            bwt bwt = new bwt(this, materialButton);
            bwu bwu = new bwu(materialButton);
            hlj a2 = hnb.m11765a("Loading preferred editor icon");
            try {
                bwx.f3790b.mo7308a(a).mo1421a(hnr.m11806a((ber) new bww(bwt, bwu)));
                if (a2 != null) {
                    a2.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
