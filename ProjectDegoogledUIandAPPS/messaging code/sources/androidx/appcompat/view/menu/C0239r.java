package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.C0126R;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.PointerIconCompat;

/* renamed from: androidx.appcompat.view.menu.r */
class C0239r implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, C0211D {
    private AlertDialog mDialog;
    private C0238q mMenu;
    C0234m mPresenter;
    private C0211D mPresenterCallback;

    public C0239r(C0238q qVar) {
        this.mMenu = qVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.mMenu.performItemAction((C0241t) this.mPresenter.getAdapter().getItem(i), 0);
    }

    public void onCloseMenu(C0238q qVar, boolean z) {
        AlertDialog alertDialog;
        if ((z || qVar == this.mMenu) && (alertDialog = this.mDialog) != null) {
            alertDialog.dismiss();
        }
        C0211D d = this.mPresenterCallback;
        if (d != null) {
            d.onCloseMenu(qVar, z);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.mDialog.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.mDialog.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.mMenu.close(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.mMenu.performShortcut(i, keyEvent, 0);
    }

    public boolean onOpenSubMenu(C0238q qVar) {
        C0211D d = this.mPresenterCallback;
        if (d != null) {
            return d.onOpenSubMenu(qVar);
        }
        return false;
    }

    public void show(IBinder iBinder) {
        C0238q qVar = this.mMenu;
        AlertDialog.Builder builder = new AlertDialog.Builder(qVar.getContext());
        this.mPresenter = new C0234m(builder.getContext(), C0126R.layout.abc_list_menu_item_layout);
        this.mPresenter.setCallback(this);
        this.mMenu.mo1581a((C0212E) this.mPresenter);
        builder.setAdapter(this.mPresenter.getAdapter(), this);
        View view = qVar.mHeaderView;
        if (view != null) {
            builder.setCustomTitle(view);
        } else {
            builder.setIcon(qVar.mHeaderIcon).setTitle(qVar.mHeaderTitle);
        }
        builder.setOnKeyListener(this);
        this.mDialog = builder.create();
        this.mDialog.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
        attributes.type = PointerIconCompat.TYPE_HELP;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.mDialog.show();
    }
}
