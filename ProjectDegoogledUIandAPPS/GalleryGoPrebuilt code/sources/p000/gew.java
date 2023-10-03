package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import java.util.Iterator;

/* renamed from: gew */
/* compiled from: PG */
final class gew implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private final /* synthetic */ MaterialCalendarGridView f11140a;

    /* renamed from: b */
    private final /* synthetic */ gey f11141b;

    public gew(gey gey, MaterialCalendarGridView materialCalendarGridView) {
        this.f11141b = gey;
        this.f11140a = materialCalendarGridView;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        gev a = this.f11140a.getAdapter();
        if (i >= a.mo6533a() && i <= a.mo6535b()) {
            gep gep = this.f11141b.f11144c;
            this.f11140a.getAdapter().getItem(i).longValue();
            gei gei = (gei) gep;
            if (gei.f11108a.f11126b.f11084d.mo6496a()) {
                gei.f11108a.f11120a.mo6506c();
                Iterator it = gei.f11108a.f11153ad.iterator();
                while (it.hasNext()) {
                    gei.f11108a.f11120a.mo6507d();
                    ((gez) it.next()).mo6544a();
                }
                gei.f11108a.f11121aa.getAdapter().mo10540d();
                RecyclerView recyclerView = gei.f11108a.f11119Z;
                if (recyclerView != null) {
                    recyclerView.getAdapter().mo10540d();
                }
            }
        }
    }
}
