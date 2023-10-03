package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: clk */
/* compiled from: PG */
final /* synthetic */ class clk implements Consumer {

    /* renamed from: a */
    private final clr f4614a;

    public clk(clr clr) {
        this.f4614a = clr;
    }

    public final void accept(Object obj) {
        clr clr = this.f4614a;
        Throwable th = (Throwable) obj;
        if (th instanceof gsl) {
            clr.f4627c.mo3274e();
            return;
        }
        cwn.m5515b(th, "FolderCreationFragmentPeer: Error while creating folder.", new Object[0]);
        if (clr.f4643s != null) {
            if (C0643xp.m15943a(th)) {
                clr.f4643s.mo3687a((CharSequence) clr.f4625a.getString(R.string.low_storage_error));
            } else {
                clr.f4643s.mo3687a((CharSequence) clr.f4625a.getString(R.string.folder_creation_error_message));
            }
        }
        eaj eaj = (eaj) clr.f4626b.mo5659r().mo6418a("progress_dialog_tag");
        if (eaj != null) {
            eaj.mo6295S();
        }
        clr.mo3245a(true);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
