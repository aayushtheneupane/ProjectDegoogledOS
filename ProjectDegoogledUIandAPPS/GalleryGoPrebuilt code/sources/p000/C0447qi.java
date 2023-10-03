package p000;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

/* renamed from: qi */
/* compiled from: PG */
public final class C0447qi implements C0442qd {

    /* renamed from: a */
    public final ActionMode.Callback f15615a;

    /* renamed from: b */
    public final Context f15616b;

    /* renamed from: c */
    private final ArrayList f15617c = new ArrayList();

    /* renamed from: d */
    private final C0309lf f15618d = new C0309lf();

    public C0447qi(Context context, ActionMode.Callback callback) {
        this.f15616b = context;
        this.f15615a = callback;
    }

    /* renamed from: a */
    public final void mo9576a(C0443qe qeVar) {
        throw null;
    }

    /* renamed from: a */
    public final boolean mo9577a(C0443qe qeVar, Menu menu) {
        throw null;
    }

    /* renamed from: a */
    public final boolean mo9578a(C0443qe qeVar, MenuItem menuItem) {
        throw null;
    }

    /* renamed from: b */
    public final void mo9579b(C0443qe qeVar, Menu menu) {
        throw null;
    }

    /* renamed from: b */
    public final ActionMode mo9702b(C0443qe qeVar) {
        int size = this.f15617c.size();
        for (int i = 0; i < size; i++) {
            C0448qj qjVar = (C0448qj) this.f15617c.get(i);
            if (qjVar != null && qjVar.f15619a == qeVar) {
                return qjVar;
            }
        }
        C0448qj qjVar2 = new C0448qj(this.f15616b, qeVar);
        this.f15617c.add(qjVar2);
        return qjVar2;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.view.Menu, java.lang.Object, je] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.Menu mo9701a(android.view.Menu r3) {
        /*
            r2 = this;
            lf r0 = r2.f15618d
            java.lang.Object r0 = r0.get(r3)
            android.view.Menu r0 = (android.view.Menu) r0
            if (r0 != 0) goto L_0x0016
            rx r0 = new rx
            android.content.Context r1 = r2.f15616b
            r0.<init>(r1, r3)
            lf r1 = r2.f15618d
            r1.put(r3, r0)
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0447qi.mo9701a(android.view.Menu):android.view.Menu");
    }
}
