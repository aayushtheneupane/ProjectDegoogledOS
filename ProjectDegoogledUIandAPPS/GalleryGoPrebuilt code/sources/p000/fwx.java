package p000;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.preference.PreferenceScreen;

/* renamed from: fwx */
/* compiled from: PG */
public abstract class fwx extends adk implements fvw {

    /* renamed from: ae */
    public final fvv f10650ae = new fvv();

    /* renamed from: g */
    public final /* bridge */ /* synthetic */ fwc mo6228g() {
        return this.f10650ae;
    }

    /* renamed from: d */
    public void mo2667d(Bundle bundle) {
        this.f10650ae.mo6221a(bundle);
        super.mo2667d(bundle);
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        this.f10650ae.mo6227f();
        super.mo2631a(activity);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        this.f10650ae.mo6254k();
        super.onConfigurationChanged(configuration);
    }

    /* renamed from: N */
    public final boolean mo5633N() {
        return this.f10650ae.mo6255l();
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        this.f10650ae.mo6248d(bundle);
        super.mo165a(bundle);
    }

    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f10650ae.mo6256m();
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    /* renamed from: a */
    public final void mo2758a(Menu menu, MenuInflater menuInflater) {
        if (this.f10650ae.mo6257n()) {
            mo5629J();
        }
    }

    /* renamed from: x */
    public void mo1836x() {
        this.f10650ae.mo6213b();
        super.mo1836x();
    }

    /* renamed from: f */
    public void mo212f() {
        this.f10650ae.mo6223c();
        super.mo212f();
    }

    /* renamed from: c */
    public void mo1834c() {
        this.f10650ae.mo6225d();
        super.mo1834c();
    }

    public final void onLowMemory() {
        this.f10650ae.mo6253j();
        super.onLowMemory();
    }

    /* renamed from: w */
    public void mo2669w() {
        this.f10650ae.mo6212a();
        super.mo2669w();
    }

    /* renamed from: O */
    public final void mo5634O() {
        if (this.f10650ae.mo6259p()) {
            mo5629J();
        }
    }

    /* renamed from: a */
    public final void mo2705a(int i, String[] strArr, int[] iArr) {
        this.f10650ae.mo6260q();
    }

    /* renamed from: v */
    public void mo2668v() {
        fsa.m9483a(mo5659r());
        this.f10650ae.mo6251h();
        super.mo2668v();
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        this.f10650ae.mo6249e(bundle);
        PreferenceScreen am = mo208am();
        if (am != null) {
            Bundle bundle2 = new Bundle();
            am.mo1172a(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    /* renamed from: d */
    public void mo210d() {
        fsa.m9483a(mo5659r());
        this.f10650ae.mo6250g();
        super.mo210d();
    }

    /* renamed from: e */
    public void mo211e() {
        this.f10650ae.mo6252i();
        super.mo211e();
    }
}
