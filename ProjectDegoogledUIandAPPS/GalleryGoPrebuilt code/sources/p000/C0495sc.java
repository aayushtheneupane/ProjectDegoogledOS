package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: sc */
/* compiled from: PG */
public final class C0495sc extends C0472rg implements SubMenu {

    /* renamed from: j */
    public final C0472rg f15853j;

    /* renamed from: k */
    public final C0475rj f15854k;

    public C0495sc(Context context, C0472rg rgVar, C0475rj rjVar) {
        super(context);
        this.f15853j = rgVar;
        this.f15854k = rjVar;
    }

    public final MenuItem getItem() {
        return this.f15854k;
    }

    /* renamed from: b */
    public final boolean mo9853b(C0475rj rjVar) {
        return this.f15853j.mo9853b(rjVar);
    }

    /* renamed from: a */
    public final boolean mo9838a(C0472rg rgVar, MenuItem menuItem) {
        return super.mo9838a(rgVar, menuItem) || this.f15853j.mo9838a(rgVar, menuItem);
    }

    /* renamed from: a */
    public final boolean mo9839a(C0475rj rjVar) {
        return this.f15853j.mo9839a(rjVar);
    }

    /* renamed from: a */
    public final String mo9829a() {
        int i = this.f15854k.f15780a;
        if (i == 0) {
            return null;
        }
        return "android:menu:actionviewstates:" + i;
    }

    /* renamed from: j */
    public final C0472rg mo9868j() {
        return this.f15853j.mo9868j();
    }

    /* renamed from: b */
    public final boolean mo9852b() {
        return this.f15853j.mo9852b();
    }

    /* renamed from: c */
    public final boolean mo9854c() {
        return this.f15853j.mo9854c();
    }

    /* renamed from: d */
    public final boolean mo9858d() {
        return this.f15853j.mo9858d();
    }

    /* renamed from: a */
    public final void mo9832a(C0470re reVar) {
        this.f15853j.mo9832a(reVar);
    }

    public final void setGroupDividerEnabled(boolean z) {
        this.f15853j.setGroupDividerEnabled(z);
    }

    public final SubMenu setHeaderIcon(int i) {
        super.mo9830a(0, (CharSequence) null, i, (Drawable) null, (View) null);
        return this;
    }

    public final SubMenu setHeaderIcon(Drawable drawable) {
        super.mo9830a(0, (CharSequence) null, 0, drawable, (View) null);
        return this;
    }

    public final SubMenu setHeaderTitle(int i) {
        super.mo9830a(i, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        super.mo9830a(0, charSequence, 0, (Drawable) null, (View) null);
        return this;
    }

    public final SubMenu setHeaderView(View view) {
        super.mo9830a(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public final SubMenu setIcon(int i) {
        this.f15854k.setIcon(i);
        return this;
    }

    public final SubMenu setIcon(Drawable drawable) {
        this.f15854k.setIcon(drawable);
        return this;
    }

    public final void setQwertyMode(boolean z) {
        this.f15853j.setQwertyMode(z);
    }
}
