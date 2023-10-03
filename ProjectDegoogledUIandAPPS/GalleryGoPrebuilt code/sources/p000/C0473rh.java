package p000;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

/* renamed from: rh */
/* compiled from: PG */
final class C0473rh implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, C0485rt {

    /* renamed from: a */
    public final C0472rg f15773a;

    /* renamed from: b */
    public C0394oj f15774b;

    /* renamed from: c */
    public C0468rc f15775c;

    public C0473rh(C0472rg rgVar) {
        this.f15773a = rgVar;
    }

    /* renamed from: a */
    public final boolean mo9575a(C0472rg rgVar) {
        return false;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f15773a.mo9836a((MenuItem) ((C0467rb) this.f15775c.mo9821c()).getItem(i), 0);
    }

    /* renamed from: a */
    public final void mo9574a(C0472rg rgVar, boolean z) {
        C0394oj ojVar;
        if ((z || rgVar == this.f15773a) && (ojVar = this.f15774b) != null) {
            ojVar.dismiss();
        }
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.f15775c.mo9786a(this.f15773a, true);
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f15774b.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f15774b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f15773a.mo9835a(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f15773a.performShortcut(i, keyEvent, 0);
    }
}
