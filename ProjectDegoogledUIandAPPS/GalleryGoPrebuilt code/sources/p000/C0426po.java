package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.ActionMenuView;
import android.support.p002v7.widget.Toolbar;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;

/* renamed from: po */
/* compiled from: PG */
final class C0426po extends C0383nz {

    /* renamed from: a */
    public final C0566ut f15519a;

    /* renamed from: b */
    public boolean f15520b;

    /* renamed from: c */
    public final Window.Callback f15521c;

    /* renamed from: d */
    private boolean f15522d;

    /* renamed from: e */
    private boolean f15523e;

    /* renamed from: f */
    private final ArrayList f15524f = new ArrayList();

    /* renamed from: g */
    private final Runnable f15525g = new C0421pj(this);

    /* renamed from: h */
    private final C0690zi f15526h = new C0422pk(this);

    public C0426po(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        this.f15519a = new C0695zn(toolbar, false);
        C0425pn pnVar = new C0425pn(this, callback);
        this.f15521c = pnVar;
        C0566ut utVar = this.f15519a;
        ((C0695zn) utVar).f16455d = pnVar;
        toolbar.f1030q = this.f15526h;
        utVar.mo10328a(charSequence);
    }

    /* renamed from: a */
    public final int mo9485a() {
        return ((C0695zn) this.f15519a).f16453b;
    }

    /* renamed from: c */
    public final void mo9494c(boolean z) {
    }

    /* renamed from: d */
    public final void mo9496d(boolean z) {
    }

    /* renamed from: h */
    public final void mo9502h() {
    }

    /* renamed from: d */
    public final boolean mo9497d() {
        return this.f15519a.mo10340k();
    }

    /* renamed from: f */
    public final boolean mo9500f() {
        if (!this.f15519a.mo10332c()) {
            return false;
        }
        this.f15519a.mo10333d();
        return true;
    }

    /* renamed from: e */
    public final void mo9498e(boolean z) {
        if (z != this.f15523e) {
            this.f15523e = z;
            int size = this.f15524f.size();
            for (int i = 0; i < size; i++) {
                ((C0382ny) this.f15524f.get(i)).mo9484a();
            }
        }
    }

    /* renamed from: k */
    public final Menu mo9639k() {
        if (!this.f15522d) {
            C0566ut utVar = this.f15519a;
            C0423pl plVar = new C0423pl(this);
            C0424pm pmVar = new C0424pm(this);
            Toolbar toolbar = ((C0695zn) utVar).f16452a;
            toolbar.f1033t = plVar;
            toolbar.f1034u = pmVar;
            ActionMenuView actionMenuView = toolbar.f1014a;
            if (actionMenuView != null) {
                actionMenuView.mo847a(plVar, pmVar);
            }
            this.f15522d = true;
        }
        return ((C0695zn) this.f15519a).f16452a.mo1099f();
    }

    /* renamed from: b */
    public final Context mo9491b() {
        return this.f15519a.mo10329b();
    }

    /* renamed from: e */
    public final boolean mo9499e() {
        ((C0695zn) this.f15519a).f16452a.removeCallbacks(this.f15525g);
        C0340mj.m14695a((View) ((C0695zn) this.f15519a).f16452a, this.f15525g);
        return true;
    }

    /* renamed from: g */
    public final void mo9501g() {
        ((C0695zn) this.f15519a).f16452a.removeCallbacks(this.f15525g);
    }

    /* renamed from: a */
    public final boolean mo9489a(int i, KeyEvent keyEvent) {
        int i2;
        Menu k = mo9639k();
        if (k == null) {
            return false;
        }
        if (keyEvent == null) {
            i2 = -1;
        } else {
            i2 = keyEvent.getDeviceId();
        }
        boolean z = true;
        if (KeyCharacterMap.load(i2).getKeyboardType() == 1) {
            z = false;
        }
        k.setQwertyMode(z);
        return k.performShortcut(i, keyEvent, 0);
    }

    /* renamed from: a */
    public final boolean mo9490a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            mo9495c();
        }
        return true;
    }

    /* renamed from: c */
    public final boolean mo9495c() {
        return this.f15519a.mo10339j();
    }

    /* renamed from: a */
    public final void mo9488a(boolean z) {
        m15042a(4, 4);
    }

    /* renamed from: a */
    private final void m15042a(int i, int i2) {
        C0566ut utVar = this.f15519a;
        utVar.mo10324a((i & i2) | ((i2 ^ -1) & ((C0695zn) utVar).f16453b));
    }

    /* renamed from: i */
    public final void mo9503i() {
        m15042a(2, 2);
    }

    /* renamed from: b */
    public final void mo9493b(boolean z) {
        m15042a(!z ? 0 : 8, 8);
    }

    /* renamed from: j */
    public final void mo9504j() {
        this.f15519a.mo10325a((Drawable) null);
    }

    /* renamed from: a */
    public final void mo9487a(CharSequence charSequence) {
        this.f15519a.mo10331b(charSequence);
    }

    /* renamed from: b */
    public final void mo9492b(CharSequence charSequence) {
        this.f15519a.mo10328a(charSequence);
    }
}
