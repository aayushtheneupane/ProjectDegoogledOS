package p000;

import android.view.ActionMode;
import android.view.Menu;
import com.google.android.apps.photosgo.R;

/* renamed from: cpr */
/* compiled from: PG */
final class cpr extends cpw {

    /* renamed from: a */
    private C0147fh f5384a;

    /* renamed from: b */
    private cqh f5385b;

    /* renamed from: c */
    private cnh f5386c;

    public cpr(C0147fh fhVar, cqh cqh, hlz hlz, cpi cpi, cnh cnh) {
        super(fhVar, cqh, hlz, cpi);
        this.f5384a = fhVar;
        this.f5385b = cqh;
        this.f5386c = cnh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo3737a(ActionMode actionMode, int i) {
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo3736a() {
        C0573v a = ((C0147fh) ife.m12898e((Object) this.f5384a)).mo5ad().mo61a();
        if (a == C0573v.RESUMED || a == C0573v.STARTED) {
            ((cnh) ife.m12898e((Object) this.f5386c)).mo3274e();
        }
        this.f5384a = null;
        this.f5385b = null;
        this.f5386c = null;
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        ife.m12898e((Object) this.f5385b);
        if (!this.f5385b.f5420a.isEmpty()) {
            actionMode.setTitle(String.valueOf(this.f5385b.f5420a.size()));
            return true;
        }
        actionMode.setTitle(R.string.select_items_text);
        return true;
    }
}
