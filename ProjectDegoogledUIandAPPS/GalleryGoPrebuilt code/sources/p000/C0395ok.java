package p000;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: ok */
/* compiled from: PG */
public class C0395ok extends C0149fj implements C0396ol, C0216hu {

    /* renamed from: e */
    private C0397om f15414e;

    /* renamed from: a */
    public void mo6273a(C0443qe qeVar) {
    }

    /* renamed from: j */
    public void mo6276j() {
    }

    public final void onContentChanged() {
    }

    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        mo9537i().mo9554b(view, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        mo9537i().mo9565k();
    }

    public final void closeOptionsMenu() {
        C0383nz f = mo9534f();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (f == null || !f.mo9497d()) {
            super.closeOptionsMenu();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        C0383nz f = mo9534f();
        if (keyCode == 82 && f != null && f.mo9490a(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public final View findViewById(int i) {
        return mo9537i().mo9553b(i);
    }

    /* renamed from: i */
    public final C0397om mo9537i() {
        if (this.f15414e == null) {
            this.f15414e = C0397om.m14919a((Activity) this, (C0396ol) this);
        }
        return this.f15414e;
    }

    public final MenuInflater getMenuInflater() {
        return mo9537i().mo9552b();
    }

    /* renamed from: f */
    public final C0383nz mo9534f() {
        return mo9537i().mo9546a();
    }

    /* renamed from: ah */
    public final Intent mo8020ah() {
        return C0350mt.m14759a((Activity) this);
    }

    public final void invalidateOptionsMenu() {
        mo9537i().mo9560f();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        mo9537i().mo9569o();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        C0397om i = mo9537i();
        i.mo9562h();
        i.mo9566l();
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        mo9537i().mo9561g();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2 = Build.VERSION.SDK_INT;
        return super.onKeyDown(i, keyEvent);
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        C0383nz f = mo9534f();
        if (menuItem.getItemId() != 16908332 || f == null || (f.mo9485a() & 4) == 0) {
            return false;
        }
        return mo3319h();
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        mo9537i().mo9567m();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        mo9537i().mo9558e();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        mo9537i().mo9568n();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        mo9537i().mo9555c();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        mo9537i().mo9557d();
    }

    /* renamed from: h */
    public boolean mo3319h() {
        Intent a = C0350mt.m14759a((Activity) this);
        if (a == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        if (shouldUpRecreateTask(a)) {
            C0217hv hvVar = new C0217hv(this);
            Intent ah = mo8020ah();
            if (ah == null) {
                ah = C0350mt.m14759a((Activity) this);
            }
            if (ah != null) {
                ComponentName component = ah.getComponent();
                if (component == null) {
                    component = ah.resolveActivity(hvVar.f13450b.getPackageManager());
                }
                int size = hvVar.f13449a.size();
                try {
                    for (Intent a2 = C0350mt.m14760a(hvVar.f13450b, component); a2 != null; a2 = C0350mt.m14760a(hvVar.f13450b, a2.getComponent())) {
                        hvVar.f13449a.add(size, a2);
                    }
                    hvVar.f13449a.add(ah);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e);
                }
            }
            if (!hvVar.f13449a.isEmpty()) {
                ArrayList arrayList = hvVar.f13449a;
                Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
                intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
                Context context = hvVar.f13450b;
                int i2 = Build.VERSION.SDK_INT;
                context.startActivities(intentArr, (Bundle) null);
                try {
                    int i3 = Build.VERSION.SDK_INT;
                    finishAffinity();
                    return true;
                } catch (IllegalStateException e2) {
                    finish();
                    return true;
                }
            } else {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
        } else {
            int i4 = Build.VERSION.SDK_INT;
            navigateUpTo(a);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        mo9537i().mo9551a(charSequence);
    }

    public final void openOptionsMenu() {
        C0383nz f = mo9534f();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (f == null || !f.mo9495c()) {
            super.openOptionsMenu();
        }
    }

    public final void setContentView(int i) {
        mo9537i().mo9556c(i);
    }

    public final void setContentView(View view) {
        mo9537i().mo9549a(view);
    }

    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        mo9537i().mo9550a(view, layoutParams);
    }

    /* renamed from: a */
    public final void mo9531a(Toolbar toolbar) {
        mo9537i().mo9548a(toolbar);
    }

    public final void setTheme(int i) {
        super.setTheme(i);
        mo9537i().mo9547a(i);
    }

    /* renamed from: c */
    public final void mo5850c() {
        mo9537i().mo9560f();
    }
}
