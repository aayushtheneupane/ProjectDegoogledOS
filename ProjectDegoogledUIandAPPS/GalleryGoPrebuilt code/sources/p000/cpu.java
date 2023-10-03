package p000;

import android.view.ActionMode;

/* renamed from: cpu */
/* compiled from: PG */
final /* synthetic */ class cpu implements Runnable {

    /* renamed from: a */
    private final ActionMode f5388a;

    /* renamed from: b */
    private final hto f5389b;

    /* renamed from: c */
    private final C0147fh f5390c;

    public cpu(ActionMode actionMode, hto hto, C0147fh fhVar) {
        this.f5388a = actionMode;
        this.f5389b = hto;
        this.f5390c = fhVar;
    }

    public final void run() {
        ActionMode actionMode = this.f5388a;
        hto hto = this.f5389b;
        C0147fh fhVar = this.f5390c;
        actionMode.finish();
        ihg.m13041a((hoi) dxo.m6865a(true, hto), fhVar);
    }
}
