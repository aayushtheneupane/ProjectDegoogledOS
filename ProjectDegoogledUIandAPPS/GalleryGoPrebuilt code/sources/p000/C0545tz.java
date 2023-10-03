package p000;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: tz */
/* compiled from: PG */
final class C0545tz implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private final /* synthetic */ C0549uc f15971a;

    public C0545tz(C0549uc ucVar) {
        this.f15971a = ucVar;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f15971a.f15978d.setSelection(i);
        if (this.f15971a.f15978d.getOnItemClickListener() != null) {
            C0549uc ucVar = this.f15971a;
            ucVar.f15978d.performItemClick(view, i, ucVar.f15976b.getItemId(i));
        }
        this.f15971a.mo9810d();
    }
}
