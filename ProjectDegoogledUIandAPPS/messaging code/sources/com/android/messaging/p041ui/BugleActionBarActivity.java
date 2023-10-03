package com.android.messaging.p041ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.android.messaging.util.C1417V;
import com.android.messaging.util.C1418W;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.android.messaging.ui.BugleActionBarActivity */
public class BugleActionBarActivity extends AppCompatActivity implements C1417V {

    /* renamed from: Pb */
    private final Set f1601Pb = new HashSet();

    /* renamed from: Qb */
    private boolean f1602Qb;
    /* access modifiers changed from: private */

    /* renamed from: Rb */
    public Menu f1603Rb;

    /* renamed from: Sb */
    private int f1604Sb;
    /* access modifiers changed from: private */
    public C1383u mActionMode;
    private boolean mDestroyed;

    /* renamed from: G */
    public void mo6907G() {
        C1383u uVar = this.mActionMode;
        if (uVar != null) {
            uVar.finish();
            this.mActionMode = null;
            invalidateActionBar();
        }
    }

    /* renamed from: K */
    public ActionMode mo6908K() {
        return this.mActionMode;
    }

    /* renamed from: S */
    public boolean mo6909S() {
        return this.f1602Qb;
    }

    /* access modifiers changed from: protected */
    /* renamed from: _a */
    public ActionMode.Callback mo6910_a() {
        C1383u uVar = this.mActionMode;
        if (uVar == null) {
            return null;
        }
        return uVar.getCallback();
    }

    /* renamed from: ab */
    public boolean mo6912ab() {
        return this.mDestroyed;
    }

    /* renamed from: g */
    public void mo6914g(int i) {
        int i2 = getResources().getDisplayMetrics().heightPixels;
        if (i2 != this.f1604Sb) {
            this.f1604Sb = i2;
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onDisplayHeightChanged  screenHeight: " + i2 + " lastScreenHeight: " + this.f1604Sb + " Skipped, appears to be orientation change.");
            return;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.isShowing()) {
            i2 -= supportActionBar.getHeight();
        }
        int size = View.MeasureSpec.getSize(i);
        boolean z = this.f1602Qb;
        this.f1602Qb = i2 - size > 100;
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onDisplayHeightChanged imeWasOpen: " + z + " mImeOpen: " + this.f1602Qb + " screenHeight: " + i2 + " height: " + size);
        }
        if (z != this.f1602Qb) {
            for (C1418W c : this.f1601Pb) {
                c.mo7441c(this.f1602Qb);
            }
        }
    }

    public final void invalidateActionBar() {
        C1383u uVar = this.mActionMode;
        if (uVar != null) {
            uVar.updateActionBar(getSupportActionBar());
        } else {
            updateActionBar(getSupportActionBar());
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!C1486ya.m3856d(this)) {
            this.f1604Sb = getResources().getDisplayMetrics().heightPixels;
            if (Log.isLoggable("MessagingApp", 2)) {
                C1430e.m3628v("MessagingApp", getLocalClassName() + ".onCreate");
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.f1603Rb = menu;
        C1383u uVar = this.mActionMode;
        return uVar != null && uVar.getCallback().onCreateActionMode(this.mActionMode, menu);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mDestroyed = true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        C1383u uVar = this.mActionMode;
        if (uVar != null && uVar.getCallback().onActionItemClicked(this.mActionMode, menuItem)) {
            return true;
        }
        if (menuItem.getItemId() != 16908332 || this.mActionMode == null) {
            return super.onOptionsItemSelected(menuItem);
        }
        mo6907G();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onPause");
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        this.f1603Rb = menu;
        C1383u uVar = this.mActionMode;
        if (uVar == null || !uVar.getCallback().onPrepareActionMode(this.mActionMode, menu)) {
            return super.onPrepareOptionsMenu(menu);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onStop();
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onRestart");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onResume");
        }
        C1430e.m3614a((Context) this, (Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onStart");
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", getLocalClassName() + ".onStop");
        }
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        this.mActionMode = new C1383u(this, callback);
        supportInvalidateOptionsMenu();
        invalidateActionBar();
        return this.mActionMode;
    }

    /* access modifiers changed from: protected */
    public void updateActionBar(ActionBar actionBar) {
        actionBar.setHomeAsUpIndicator((Drawable) null);
    }

    /* renamed from: b */
    public void mo6913b(C1418W w) {
        this.f1601Pb.remove(w);
    }

    /* renamed from: a */
    public void mo6911a(C1418W w) {
        this.f1601Pb.add(w);
    }
}
