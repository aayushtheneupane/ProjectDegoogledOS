package p000;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

/* renamed from: aac */
/* compiled from: PG */
public class aac extends C0215ht implements C0681z, C0026ax, aeo, aah {

    /* renamed from: a */
    private final C0002ab f4a = new C0002ab(this);

    /* renamed from: b */
    private final aen f5b = aen.m284a((aeo) this);

    /* renamed from: c */
    private C0025aw f6c;

    /* renamed from: d */
    public final aag f7d = new aag(new C0706zy(this));

    public aac() {
        if (mo5ad() != null) {
            int i = Build.VERSION.SDK_INT;
            mo5ad().mo64a(new C0707zz(this));
            mo5ad().mo64a(new aaa(this));
            int i2 = Build.VERSION.SDK_INT;
            int i3 = Build.VERSION.SDK_INT;
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    /* renamed from: ad */
    public C0600w mo5ad() {
        return this.f4a;
    }

    /* renamed from: ai */
    public final aem mo6ai() {
        return this.f5b.f277a;
    }

    @Deprecated
    /* renamed from: k */
    public void mo7k() {
    }

    /* renamed from: aa */
    public final C0025aw mo4aa() {
        if (getApplication() != null) {
            if (this.f6c == null) {
                aab aab = (aab) getLastNonConfigurationInstance();
                if (aab != null) {
                    this.f6c = aab.f3a;
                }
                if (this.f6c == null) {
                    this.f6c = new C0025aw();
                }
            }
            return this.f6c;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public void onBackPressed() {
        this.f7d.mo15a();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f5b.mo250a(bundle);
        C0014an.m797a((Activity) this);
    }

    public final Object onRetainNonConfigurationInstance() {
        aab aab;
        mo7k();
        C0025aw awVar = this.f6c;
        if (awVar == null && (aab = (aab) getLastNonConfigurationInstance()) != null) {
            awVar = aab.f3a;
        }
        if (awVar == null) {
            return null;
        }
        aab aab2 = new aab();
        aab2.f3a = awVar;
        return aab2;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        C0600w ad = mo5ad();
        if (ad instanceof C0002ab) {
            ((C0002ab) ad).mo63a(C0573v.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f5b.mo251b(bundle);
    }
}
