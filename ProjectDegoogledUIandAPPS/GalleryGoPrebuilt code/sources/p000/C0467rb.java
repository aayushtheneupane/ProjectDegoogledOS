package p000;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;

/* renamed from: rb */
/* compiled from: PG */
public final class C0467rb extends BaseAdapter {

    /* renamed from: a */
    private int f15734a = -1;

    /* renamed from: b */
    private final /* synthetic */ C0468rc f15735b;

    public C0467rb(C0468rc rcVar) {
        this.f15735b = rcVar;
        m15216a();
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    /* renamed from: a */
    private final void m15216a() {
        C0472rg rgVar = this.f15735b.f15737b;
        C0475rj rjVar = rgVar.f15756h;
        if (rjVar != null) {
            ArrayList i = rgVar.mo9866i();
            int size = i.size();
            int i2 = 0;
            while (i2 < size) {
                if (((C0475rj) i.get(i2)) != rjVar) {
                    i2++;
                } else {
                    this.f15734a = i2;
                    return;
                }
            }
        }
        this.f15734a = -1;
    }

    public final int getCount() {
        int size = this.f15735b.f15737b.mo9866i().size();
        return this.f15734a >= 0 ? size - 1 : size;
    }

    /* renamed from: a */
    public final C0475rj getItem(int i) {
        ArrayList i2 = this.f15735b.f15737b.mo9866i();
        int i3 = this.f15734a;
        if (i3 >= 0 && i >= i3) {
            i++;
        }
        return (C0475rj) i2.get(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f15735b.f15736a.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
        }
        ((C0487rv) view).mo763a(getItem(i));
        return view;
    }

    public final void notifyDataSetChanged() {
        m15216a();
        super.notifyDataSetChanged();
    }
}
