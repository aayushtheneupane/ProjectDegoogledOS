package p000;

import android.app.Fragment;
import android.app.assist.AssistContent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.Collections;
import java.util.function.Consumer;

/* renamed from: fww */
/* compiled from: PG */
public class fww extends C0395ok implements fvw {

    /* renamed from: e */
    private int f10648e;

    /* renamed from: f */
    private final fvi f10649f = new fvi((byte[]) null);

    /* renamed from: g */
    public final /* bridge */ /* synthetic */ fwc mo6228g() {
        return this.f10649f;
    }

    /* renamed from: m */
    private final void mo2566m() {
        int i = this.f10648e;
        this.f10648e = i + 1;
        if (i == 0) {
            fvi fvi = this.f10649f;
            int i2 = fwv.f10647a;
            for (int i3 = 0; i3 < fvi.f10642e.size(); i3++) {
                fwt fwt = (fwt) fvi.f10642e.get(i3);
                if (fwt instanceof fvd) {
                    ((fvd) fwt).mo6210a();
                }
            }
        }
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof ful) {
                if (((ful) fwt).mo6190a()) {
                    return true;
                }
            }
        }
        if (!super.dispatchKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    /* renamed from: l */
    private final void mo3320l() {
        this.f10648e--;
    }

    public final void finish() {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fum) {
                ((fum) fwt).mo6191a();
            }
        }
        super.finish();
    }

    public final void onActivityReenter(int i, Intent intent) {
        fvi fvi = this.f10649f;
        int i2 = fwv.f10647a;
        for (int i3 = 0; i3 < fvi.f10642e.size(); i3++) {
            fwt fwt = (fwt) fvi.f10642e.get(i3);
            if (fwt instanceof fun) {
                ((fun) fwt).mo6192a();
            }
        }
        super.onActivityReenter(i, intent);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.f10649f.mo6245a(i, i2, intent);
    }

    /* renamed from: e */
    public final void mo5853e() {
        fvi fvi = this.f10649f;
        for (int i = 0; i < fvi.f10642e.size(); i++) {
            fwt fwt = (fwt) fvi.f10642e.get(i);
            if (fwt instanceof fxa) {
                ((fxa) fwt).mo6298a();
            }
        }
    }

    public final void onAttachedToWindow() {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        fvi.f10621d = fvi.mo6244a((fwb) new fvh());
        super.onAttachedToWindow();
    }

    public void onBackPressed() {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fup) {
                if (((fup) fwt).mo6194a()) {
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        this.f10649f.mo6254k();
        super.onConfigurationChanged(configuration);
    }

    public final boolean onContextItemSelected(MenuItem menuItem) {
        return this.f10649f.mo6255l() || super.onContextItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.f10649f.mo6248d(bundle);
        super.onCreate(bundle);
    }

    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f10649f.mo6256m();
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public final boolean onCreateOptionsMenu(Menu menu) {
        return this.f10649f.mo6257n() || super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.f10649f.mo6213b();
        super.onDestroy();
    }

    public final void onDetachedFromWindow() {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        fwb fwb = fvi.f10621d;
        if (fwb != null) {
            fvi.mo6247b(fwb);
            fvi.f10621d = null;
        }
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            fxk.m9821a((Object) fwt);
            if (fwt instanceof fuq) {
                ((fuq) fwt).mo6195a();
            }
        }
        super.onDetachedFromWindow();
    }

    public final /* synthetic */ void onGetDirectActions(CancellationSignal cancellationSignal, Consumer consumer) {
        p003j$.util.function.Consumer a = ifg.m12910a(consumer);
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        int i2 = 0;
        while (i2 < fvi.f10642e.size()) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (!(fwt instanceof fur)) {
                i2++;
            } else {
                ((fur) fwt).mo6196a();
                return;
            }
        }
        a.accept(Collections.emptyList());
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        fvi fvi = this.f10649f;
        int i2 = fwv.f10647a;
        for (int i3 = 0; i3 < fvi.f10642e.size(); i3++) {
            fwt fwt = (fwt) fvi.f10642e.get(i3);
            if (fwt instanceof fus) {
                if (((fus) fwt).mo6197a()) {
                    return true;
                }
            }
        }
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        return true;
    }

    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        fvi fvi = this.f10649f;
        int i2 = fwv.f10647a;
        for (int i3 = 0; i3 < fvi.f10642e.size(); i3++) {
            fwt fwt = (fwt) fvi.f10642e.get(i3);
            if (fwt instanceof fut) {
                if (((fut) fwt).mo6198a()) {
                    return true;
                }
            }
        }
        if (!super.onKeyUp(i, keyEvent)) {
            return false;
        }
        return true;
    }

    public final void onLowMemory() {
        this.f10649f.mo6253j();
        super.onLowMemory();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fuu) {
                ((fuu) fwt).mo6199a();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.f10649f.mo6258o() || super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.f10649f.mo6212a();
        super.onPause();
    }

    public final /* synthetic */ void onPerformDirectAction(String str, Bundle bundle, CancellationSignal cancellationSignal, Consumer consumer) {
        ifg.m12910a(consumer);
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        int i2 = 0;
        while (i2 < fvi.f10642e.size()) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (!(fwt instanceof fuv)) {
                i2++;
            } else {
                ((fuv) fwt).mo6200a();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        fvi.f10618a = fvi.mo6244a((fwb) new fve(fvi, bundle));
        super.onPostCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        fvi.f10620c = fvi.mo6244a((fwb) new fvg());
        super.onPostResume();
    }

    public final boolean onPrepareOptionsMenu(Menu menu) {
        return this.f10649f.mo6259p() || super.onPrepareOptionsMenu(menu);
    }

    public final void onProvideAssistContent(AssistContent assistContent) {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fuy) {
                ((fuy) fwt).mo6203a();
            }
        }
        super.onProvideAssistContent(assistContent);
    }

    public final void onProvideAssistData(Bundle bundle) {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fuz) {
                ((fuz) fwt).mo6204a();
            }
        }
        super.onProvideAssistData(bundle);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f10649f.mo6260q();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Bundle bundle) {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        fvi.f10619b = fvi.mo6244a((fwb) new fvf(fvi, bundle));
        super.onRestoreInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        fsa.m9483a(mo5851d());
        this.f10649f.mo6251h();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        this.f10649f.mo6249e(bundle);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        fsa.m9483a(mo5851d());
        this.f10649f.mo6250g();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.f10649f.mo6252i();
        super.onStop();
    }

    /* renamed from: a */
    public final void mo6273a(C0443qe qeVar) {
        fvi fvi = this.f10649f;
        if (qeVar != null) {
            for (int i = 0; i < fvi.f10642e.size(); i++) {
                fwt fwt = (fwt) fvi.f10642e.get(i);
                if (fwt instanceof fxb) {
                    ((fxb) fwt).mo6299a();
                }
            }
        }
    }

    /* renamed from: j */
    public final void mo6276j() {
        fvi fvi = this.f10649f;
        for (int i = 0; i < fvi.f10642e.size(); i++) {
            fwt fwt = (fwt) fvi.f10642e.get(i);
            if (fwt instanceof fxc) {
                ((fxc) fwt).mo6300a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onUserLeaveHint() {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fvb) {
                ((fvb) fwt).mo6208a();
            }
        }
        super.onUserLeaveHint();
    }

    public void onWindowFocusChanged(boolean z) {
        fvi fvi = this.f10649f;
        int i = fwv.f10647a;
        for (int i2 = 0; i2 < fvi.f10642e.size(); i2++) {
            fwt fwt = (fwt) fvi.f10642e.get(i2);
            if (fwt instanceof fvc) {
                ((fvc) fwt).mo6209a();
            }
        }
        super.onWindowFocusChanged(z);
    }

    public final void startActivity(Intent intent) {
        mo2566m();
        super.startActivity(intent);
        mo3320l();
    }

    public final void startActivity(Intent intent, Bundle bundle) {
        mo2566m();
        super.startActivity(intent, bundle);
        mo3320l();
    }

    public final void startActivityForResult(Intent intent, int i) {
        mo2566m();
        super.startActivityForResult(intent, i);
        mo3320l();
    }

    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        mo2566m();
        super.startActivityForResult(intent, i, bundle);
        mo3320l();
    }

    public final void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        mo2566m();
        super.startActivityFromFragment(fragment, intent, i, bundle);
        mo3320l();
    }
}
