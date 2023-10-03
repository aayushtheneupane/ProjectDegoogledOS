package p000;

import android.content.Context;
import android.support.p002v7.view.menu.ExpandedMenuView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;

/* renamed from: rc */
/* compiled from: PG */
public final class C0468rc implements AdapterView.OnItemClickListener, C0486ru {

    /* renamed from: a */
    public LayoutInflater f15736a;

    /* renamed from: b */
    public C0472rg f15737b;

    /* renamed from: c */
    public ExpandedMenuView f15738c;

    /* renamed from: d */
    public C0485rt f15739d;

    /* renamed from: e */
    public C0467rb f15740e;

    /* renamed from: f */
    private Context f15741f;

    public C0468rc(Context context) {
        this.f15741f = context;
        this.f15736a = LayoutInflater.from(context);
    }

    /* renamed from: a */
    public final void mo9787a(C0485rt rtVar) {
        throw null;
    }

    /* renamed from: a */
    public final boolean mo9788a() {
        return false;
    }

    /* renamed from: a */
    public final boolean mo9789a(C0475rj rjVar) {
        return false;
    }

    /* renamed from: b */
    public final boolean mo9792b(C0475rj rjVar) {
        return false;
    }

    /* renamed from: c */
    public final ListAdapter mo9821c() {
        if (this.f15740e == null) {
            this.f15740e = new C0467rb(this);
        }
        return this.f15740e;
    }

    /* renamed from: a */
    public final void mo9785a(Context context, C0472rg rgVar) {
        if (this.f15741f != null) {
            this.f15741f = context;
            if (this.f15736a == null) {
                this.f15736a = LayoutInflater.from(context);
            }
        }
        this.f15737b = rgVar;
        C0467rb rbVar = this.f15740e;
        if (rbVar != null) {
            rbVar.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public final void mo9786a(C0472rg rgVar, boolean z) {
        C0485rt rtVar = this.f15739d;
        if (rtVar != null) {
            rtVar.mo9574a(rgVar, z);
        }
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f15737b.mo9837a((MenuItem) this.f15740e.getItem(i), (C0486ru) this, 0);
    }

    /* renamed from: a */
    public final boolean mo9790a(C0495sc scVar) {
        if (!scVar.hasVisibleItems()) {
            return false;
        }
        C0473rh rhVar = new C0473rh(scVar);
        C0472rg rgVar = rhVar.f15773a;
        C0393oi oiVar = new C0393oi(rgVar.f15749a);
        rhVar.f15775c = new C0468rc(oiVar.mo9518a());
        C0468rc rcVar = rhVar.f15775c;
        rcVar.f15739d = rhVar;
        rhVar.f15773a.mo9833a((C0486ru) rcVar);
        ListAdapter c = rhVar.f15775c.mo9821c();
        C0389oe oeVar = oiVar.f15411a;
        oeVar.f15363p = c;
        oeVar.f15364q = rhVar;
        View view = rgVar.f15755g;
        if (view != null) {
            oeVar.f15353f = view;
        } else {
            oiVar.mo9521a(rgVar.f15754f);
            oiVar.mo9525b(rgVar.f15753e);
        }
        oiVar.f15411a.f15361n = rhVar;
        rhVar.f15774b = oiVar.mo6550b();
        rhVar.f15774b.setOnDismissListener(rhVar);
        WindowManager.LayoutParams attributes = rhVar.f15774b.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        rhVar.f15774b.show();
        C0485rt rtVar = this.f15739d;
        if (rtVar == null) {
            return true;
        }
        rtVar.mo9575a(scVar);
        return true;
    }

    /* renamed from: b */
    public final void mo9791b() {
        C0467rb rbVar = this.f15740e;
        if (rbVar != null) {
            rbVar.notifyDataSetChanged();
        }
    }
}
