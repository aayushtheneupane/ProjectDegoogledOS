package p000;

import android.app.Activity;
import android.content.DialogInterface;
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

/* renamed from: fwy */
/* compiled from: PG */
public class fwy extends C0140fa implements fvw {

    /* renamed from: Z */
    private final fvv f10651Z = new fvv();

    /* renamed from: g */
    public final /* bridge */ /* synthetic */ fwc mo6228g() {
        return this.f10651Z;
    }

    /* renamed from: S */
    public final void mo6295S() {
        this.f10651Z.mo6226e();
        mo5428a(false, false);
    }

    /* renamed from: d */
    public void mo2667d(Bundle bundle) {
        this.f10651Z.mo6221a(bundle);
        super.mo2667d(bundle);
    }

    /* renamed from: a */
    public void mo2665a(int i, int i2, Intent intent) {
        this.f10651Z.mo6245a(i, i2, intent);
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        this.f10651Z.mo6227f();
        super.mo2631a(activity);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        this.f10651Z.mo6254k();
        super.onConfigurationChanged(configuration);
    }

    /* renamed from: N */
    public final boolean mo5633N() {
        return this.f10651Z.mo6255l();
    }

    /* renamed from: a */
    public void mo165a(Bundle bundle) {
        this.f10651Z.mo6248d(bundle);
        super.mo165a(bundle);
    }

    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f10651Z.mo6256m();
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    /* renamed from: a */
    public final void mo2758a(Menu menu, MenuInflater menuInflater) {
        if (this.f10651Z.mo6257n()) {
            mo5629J();
        }
    }

    /* renamed from: a */
    public View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f10651Z.mo6222b(bundle);
        return null;
    }

    /* renamed from: x */
    public void mo1836x() {
        this.f10651Z.mo6213b();
        super.mo1836x();
    }

    /* renamed from: f */
    public void mo212f() {
        this.f10651Z.mo6223c();
        super.mo212f();
    }

    /* renamed from: c */
    public void mo1834c() {
        this.f10651Z.mo6225d();
        super.mo1834c();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f10651Z.mo6226e();
        super.onDismiss(dialogInterface);
    }

    public final void onLowMemory() {
        this.f10651Z.mo6253j();
        super.onLowMemory();
    }

    /* renamed from: a */
    public boolean mo2666a(MenuItem menuItem) {
        return this.f10651Z.mo6258o();
    }

    /* renamed from: w */
    public void mo2669w() {
        this.f10651Z.mo6212a();
        super.mo2669w();
    }

    /* renamed from: O */
    public final void mo5634O() {
        if (this.f10651Z.mo6259p()) {
            mo5629J();
        }
    }

    /* renamed from: a */
    public final void mo2705a(int i, String[] strArr, int[] iArr) {
        this.f10651Z.mo6260q();
    }

    /* renamed from: v */
    public void mo2668v() {
        fsa.m9483a(mo5659r());
        this.f10651Z.mo6251h();
        super.mo2668v();
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        this.f10651Z.mo6249e(bundle);
        super.mo168e(bundle);
    }

    /* renamed from: d */
    public void mo210d() {
        fsa.m9483a(mo5659r());
        this.f10651Z.mo6250g();
        super.mo210d();
    }

    /* renamed from: e */
    public void mo211e() {
        this.f10651Z.mo6252i();
        super.mo211e();
    }

    /* renamed from: a */
    public void mo2632a(View view, Bundle bundle) {
        this.f10651Z.mo6224c(bundle);
    }
}
