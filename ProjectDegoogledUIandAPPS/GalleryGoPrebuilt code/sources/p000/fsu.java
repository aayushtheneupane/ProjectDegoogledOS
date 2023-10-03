package p000;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: fsu */
/* compiled from: PG */
final /* synthetic */ class fsu implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private final fsw f10548a;

    public fsu(fsw fsw) {
        this.f10548a = fsw;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        fsw fsw = this.f10548a;
        fsr fsr = (fsr) adapterView.getItemAtPosition(i);
        fsv fsv = fsw.f10550b;
        if (fsv != null) {
            fsv.mo3552a(fsr);
        }
    }
}
