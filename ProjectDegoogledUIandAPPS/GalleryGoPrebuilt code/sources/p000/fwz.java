package p000;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: fwz */
/* compiled from: PG */
public class fwz extends C0147fh implements fvw {

    /* renamed from: a */
    private final fvv f10652a = new fvv();

    /* renamed from: g */
    public final /* bridge */ /* synthetic */ fwc mo6228g() {
        return this.f10652a;
    }

    /* renamed from: d */
    public void mo2667d(Bundle bundle) {
        this.f10652a.mo6221a(bundle);
        super.mo2667d(bundle);
    }

    /* renamed from: a */
    public void mo2665a(int i, int i2, Intent intent) {
        this.f10652a.mo6245a(i, i2, intent);
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        this.f10652a.mo6227f();
        super.mo2631a(activity);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.f10652a.mo6254k();
        super.onConfigurationChanged(configuration);
    }

    /* renamed from: N */
    public final boolean mo5633N() {
        return this.f10652a.mo6255l();
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        this.f10652a.mo6248d(bundle);
        super.mo165a(bundle);
    }

    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f10652a.mo6256m();
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    /* renamed from: a */
    public void mo2758a(Menu menu, MenuInflater menuInflater) {
        if (this.f10652a.mo6257n()) {
            mo5629J();
        }
    }

    /* renamed from: a */
    public View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f10652a.mo6222b(bundle);
        return null;
    }

    /* renamed from: x */
    public void mo1836x() {
        this.f10652a.mo6213b();
        super.mo1836x();
    }

    /* renamed from: f */
    public void mo212f() {
        this.f10652a.mo6223c();
        super.mo212f();
    }

    /* renamed from: c */
    public void mo1834c() {
        this.f10652a.mo6225d();
        super.mo1834c();
    }

    public final void onLowMemory() {
        this.f10652a.mo6253j();
        super.onLowMemory();
    }

    /* renamed from: a */
    public boolean mo2666a(MenuItem menuItem) {
        return this.f10652a.mo6258o();
    }

    /* renamed from: w */
    public void mo2669w() {
        this.f10652a.mo6212a();
        super.mo2669w();
    }

    /* renamed from: O */
    public final void mo5634O() {
        if (this.f10652a.mo6259p()) {
            mo5629J();
        }
    }

    /* renamed from: a */
    public void mo2705a(int i, String[] strArr, int[] iArr) {
        this.f10652a.mo6260q();
    }

    /* renamed from: v */
    public void mo2668v() {
        this.f10652a.mo6251h();
        super.mo2668v();
    }

    /* renamed from: e */
    public void mo168e(Bundle bundle) {
        this.f10652a.mo6249e(bundle);
    }

    /* renamed from: d */
    public void mo210d() {
        this.f10652a.mo6250g();
        super.mo210d();
    }

    /* renamed from: e */
    public void mo211e() {
        this.f10652a.mo6252i();
        super.mo211e();
    }

    /* renamed from: a */
    public void mo2632a(View view, Bundle bundle) {
        this.f10652a.mo6224c(bundle);
    }
}
