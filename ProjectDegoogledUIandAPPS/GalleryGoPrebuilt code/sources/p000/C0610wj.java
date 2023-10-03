package p000;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: wj */
/* compiled from: PG */
final class C0610wj implements AdapterView.OnItemSelectedListener {

    /* renamed from: a */
    private final /* synthetic */ C0616wp f16236a;

    public C0610wj(C0616wp wpVar) {
        this.f16236a = wpVar;
    }

    public final void onNothingSelected(AdapterView adapterView) {
    }

    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        C0582vi viVar;
        if (i != -1 && (viVar = this.f16236a.f16247e) != null) {
            viVar.f16091a = false;
        }
    }
}
