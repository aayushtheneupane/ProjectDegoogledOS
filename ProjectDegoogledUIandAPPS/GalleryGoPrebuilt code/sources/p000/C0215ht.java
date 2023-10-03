package p000;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/* renamed from: ht */
/* compiled from: PG */
public class C0215ht extends Activity implements C0681z, C0322ls {

    /* renamed from: a */
    private final C0002ab f13366a = new C0002ab(this);

    public C0215ht() {
        new C0309lf();
    }

    /* renamed from: ad */
    public C0600w mo5ad() {
        return this.f13366a;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C0323lt.m14636a(decorView, keyEvent)) {
            return C0323lt.m14637a(this, decorView, this, keyEvent);
        }
        return true;
    }

    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C0323lt.m14636a(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C0014an.m797a((Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        this.f13366a.mo63a(C0573v.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* renamed from: a */
    public final boolean mo7972a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
