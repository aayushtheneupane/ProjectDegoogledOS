package p000;

import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: ln */
/* compiled from: PG */
public abstract class C0317ln {

    /* renamed from: a */
    public C0316lm f15206a;

    /* renamed from: a */
    public abstract View mo9362a();

    /* renamed from: a */
    public void mo9364a(SubMenu subMenu) {
    }

    /* renamed from: b */
    public boolean mo9366b() {
        return false;
    }

    /* renamed from: c */
    public boolean mo9367c() {
        return true;
    }

    /* renamed from: d */
    public boolean mo9368d() {
        throw null;
    }

    /* renamed from: e */
    public boolean mo9369e() {
        throw null;
    }

    /* renamed from: a */
    public View mo9363a(MenuItem menuItem) {
        return mo9362a();
    }

    /* renamed from: a */
    public void mo9365a(C0316lm lmVar) {
        if (this.f15206a != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f15206a = lmVar;
    }
}
