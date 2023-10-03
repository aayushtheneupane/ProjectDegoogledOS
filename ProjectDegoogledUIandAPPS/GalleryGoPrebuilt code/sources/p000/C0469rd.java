package p000;

import android.support.p002v7.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

/* renamed from: rd */
/* compiled from: PG */
public final class C0469rd extends BaseAdapter {

    /* renamed from: a */
    public final C0472rg f15742a;

    /* renamed from: b */
    public boolean f15743b;

    /* renamed from: c */
    private int f15744c = -1;

    /* renamed from: d */
    private final boolean f15745d;

    /* renamed from: e */
    private final LayoutInflater f15746e;

    /* renamed from: f */
    private final int f15747f;

    public C0469rd(C0472rg rgVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.f15745d = z;
        this.f15746e = layoutInflater;
        this.f15742a = rgVar;
        this.f15747f = i;
        m15227a();
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    /* renamed from: a */
    private final void m15227a() {
        C0472rg rgVar = this.f15742a;
        C0475rj rjVar = rgVar.f15756h;
        if (rjVar != null) {
            ArrayList i = rgVar.mo9866i();
            int size = i.size();
            int i2 = 0;
            while (i2 < size) {
                if (((C0475rj) i.get(i2)) != rjVar) {
                    i2++;
                } else {
                    this.f15744c = i2;
                    return;
                }
            }
        }
        this.f15744c = -1;
    }

    public final int getCount() {
        ArrayList i = this.f15745d ? this.f15742a.mo9866i() : this.f15742a.mo9862g();
        if (this.f15744c < 0) {
            return i.size();
        }
        return i.size() - 1;
    }

    /* renamed from: a */
    public final C0475rj getItem(int i) {
        ArrayList g = !this.f15745d ? this.f15742a.mo9862g() : this.f15742a.mo9866i();
        int i2 = this.f15744c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (C0475rj) g.get(i);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (view == null) {
            view = this.f15746e.inflate(this.f15747f, viewGroup, false);
        }
        int i3 = getItem(i).f15781b;
        int i4 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        boolean z = this.f15742a.mo9852b() && i3 != (i4 >= 0 ? getItem(i4).f15781b : i3);
        ImageView imageView = listMenuItemView.f871b;
        if (imageView != null) {
            if (listMenuItemView.f873d || !z) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
        C0487rv rvVar = (C0487rv) view;
        if (this.f15743b) {
            listMenuItemView.f874e = true;
            listMenuItemView.f872c = true;
        }
        rvVar.mo763a(getItem(i));
        return view;
    }

    public final void notifyDataSetChanged() {
        m15227a();
        super.notifyDataSetChanged();
    }
}
