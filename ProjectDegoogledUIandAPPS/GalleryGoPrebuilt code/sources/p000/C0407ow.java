package p000;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.p002v7.widget.ActionBarContextView;
import android.support.p002v7.widget.ViewStubCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import com.google.android.apps.photosgo.R;
import java.util.List;

/* renamed from: ow */
/* compiled from: PG */
final class C0407ow extends C0454qp {

    /* renamed from: b */
    private final /* synthetic */ C0416pe f15428b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0407ow(C0416pe peVar, Window.Callback callback) {
        super(callback);
        this.f15428b = peVar;
    }

    public final void onContentChanged() {
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f15428b.mo9604a(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (!super.dispatchKeyShortcutEvent(keyEvent)) {
            C0416pe peVar = this.f15428b;
            int keyCode = keyEvent.getKeyCode();
            C0383nz a = peVar.mo9546a();
            if (a == null || !a.mo9489a(keyCode, keyEvent)) {
                C0414pc pcVar = peVar.f15500s;
                if (pcVar == null || !peVar.mo9605a(pcVar, keyEvent.getKeyCode(), keyEvent)) {
                    if (peVar.f15500s == null) {
                        C0414pc g = peVar.mo9610g(0);
                        peVar.mo9606a(g, keyEvent);
                        boolean a2 = peVar.mo9605a(g, keyEvent.getKeyCode(), keyEvent);
                        g.f15448k = false;
                        if (!a2) {
                            return false;
                        }
                    }
                    return false;
                }
                C0414pc pcVar2 = peVar.f15500s;
                if (pcVar2 != null) {
                    pcVar2.f15449l = true;
                }
            }
        }
        return true;
    }

    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof C0472rg)) {
            return super.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    public final boolean onMenuOpened(int i, Menu menu) {
        C0383nz a;
        super.onMenuOpened(i, menu);
        C0416pe peVar = this.f15428b;
        if (i == 108 && (a = peVar.mo9546a()) != null) {
            a.mo9498e(true);
        }
        return true;
    }

    public final void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
        C0416pe peVar = this.f15428b;
        if (i == 108) {
            C0383nz a = peVar.mo9546a();
            if (a != null) {
                a.mo9498e(false);
            }
        } else if (i == 0) {
            C0414pc g = peVar.mo9610g(0);
            if (g.f15450m) {
                peVar.mo9602a(g, false);
            }
        }
    }

    public final boolean onPreparePanel(int i, View view, Menu menu) {
        C0472rg rgVar;
        if (menu instanceof C0472rg) {
            rgVar = (C0472rg) menu;
        } else {
            rgVar = null;
        }
        if (i == 0 && rgVar == null) {
            return false;
        }
        if (rgVar != null) {
            rgVar.f15757i = true;
        }
        boolean onPreparePanel = super.onPreparePanel(i, view, menu);
        if (rgVar != null) {
            rgVar.f15757i = false;
        }
        return onPreparePanel;
    }

    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        C0472rg rgVar;
        C0414pc g = this.f15428b.mo9610g(0);
        if (g == null || (rgVar = g.f15445h) == null) {
            super.onProvideKeyboardShortcuts(list, menu, i);
        } else {
            super.onProvideKeyboardShortcuts(list, rgVar, i);
        }
    }

    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        return null;
    }

    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        C0396ol olVar;
        Context context;
        C0396ol olVar2;
        C0416pe peVar = this.f15428b;
        if (!peVar.f15494m || i != 0) {
            return super.onWindowStartingActionMode(callback, i);
        }
        C0447qi qiVar = new C0447qi(peVar.f15485d, callback);
        C0416pe peVar2 = this.f15428b;
        C0443qe qeVar = peVar2.f15489h;
        if (qeVar != null) {
            qeVar.mo9650c();
        }
        C0406ov ovVar = new C0406ov(peVar2, qiVar);
        C0383nz a = peVar2.mo9546a();
        if (a != null) {
            peVar2.f15489h = a.mo9486a((C0442qd) ovVar);
            if (!(peVar2.f15489h == null || (olVar2 = peVar2.f15487f) == null)) {
                olVar2.mo6276j();
            }
        }
        C0443qe qeVar2 = peVar2.f15489h;
        if (qeVar2 == null) {
            peVar2.mo9616s();
            C0443qe qeVar3 = peVar2.f15489h;
            if (qeVar3 != null) {
                qeVar3.mo9650c();
            }
            if (peVar2.f15490i == null) {
                if (peVar2.f15499r) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = peVar2.f15485d.getTheme();
                    theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme newTheme = peVar2.f15485d.getResources().newTheme();
                        newTheme.setTo(theme);
                        newTheme.applyStyle(typedValue.resourceId, true);
                        context = new C0445qg(peVar2.f15485d, 0);
                        context.getTheme().setTo(newTheme);
                    } else {
                        context = peVar2.f15485d;
                    }
                    peVar2.f15490i = new ActionBarContextView(context);
                    peVar2.f15491j = new PopupWindow(context, (AttributeSet) null, R.attr.actionModePopupWindowStyle);
                    dcm.m5899a(peVar2.f15491j, 2);
                    peVar2.f15491j.setContentView(peVar2.f15490i);
                    peVar2.f15491j.setWidth(-1);
                    context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                    peVar2.f15490i.f15862d = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
                    peVar2.f15491j.setHeight(-2);
                    peVar2.f15492k = new C0402or(peVar2);
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) peVar2.f15495n.findViewById(R.id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.f1040a = LayoutInflater.from(peVar2.mo9614q());
                        peVar2.f15490i = (ActionBarContextView) viewStubCompat.mo1114a();
                    }
                }
            }
            if (peVar2.f15490i != null) {
                peVar2.mo9616s();
                peVar2.f15490i.mo794a();
                C0446qh qhVar = new C0446qh(peVar2.f15490i.getContext(), peVar2.f15490i, ovVar);
                if (ovVar.mo9577a((C0443qe) qhVar, (Menu) qhVar.f15609a)) {
                    qhVar.mo9651d();
                    peVar2.f15490i.mo798a((C0443qe) qhVar);
                    peVar2.f15489h = qhVar;
                    if (peVar2.mo9615r()) {
                        peVar2.f15490i.setAlpha(0.0f);
                        C0344mn k = C0340mj.m14720k(peVar2.f15490i);
                        k.mo9400a(1.0f);
                        peVar2.f15493l = k;
                        peVar2.f15493l.mo9402a((C0345mo) new C0403os(peVar2));
                    } else {
                        peVar2.f15490i.setAlpha(1.0f);
                        peVar2.f15490i.setVisibility(0);
                        peVar2.f15490i.sendAccessibilityEvent(32);
                        if (peVar2.f15490i.getParent() instanceof View) {
                            C0340mj.m14724o((View) peVar2.f15490i.getParent());
                        }
                    }
                    if (peVar2.f15491j != null) {
                        peVar2.f15486e.getDecorView().post(peVar2.f15492k);
                    }
                } else {
                    peVar2.f15489h = null;
                }
            }
            if (!(peVar2.f15489h == null || (olVar = peVar2.f15487f) == null)) {
                olVar.mo6276j();
            }
            qeVar2 = peVar2.f15489h;
        }
        if (qeVar2 != null) {
            return qiVar.mo9702b(qeVar2);
        }
        return null;
    }
}
