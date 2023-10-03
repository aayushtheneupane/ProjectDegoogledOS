package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: sd */
/* compiled from: PG */
final class C0496sd extends C0489rx implements SubMenu {

    /* renamed from: c */
    private final C0256jg f15855c;

    public C0496sd(Context context, C0256jg jgVar) {
        super(context, jgVar);
        this.f15855c = jgVar;
    }

    public final void clearHeader() {
        this.f15855c.clearHeader();
    }

    public final MenuItem getItem() {
        return mo9793a(this.f15855c.getItem());
    }

    public final SubMenu setHeaderIcon(int i) {
        this.f15855c.setHeaderIcon(i);
        return this;
    }

    public final SubMenu setHeaderIcon(Drawable drawable) {
        this.f15855c.setHeaderIcon(drawable);
        return this;
    }

    public final SubMenu setHeaderTitle(int i) {
        this.f15855c.setHeaderTitle(i);
        return this;
    }

    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f15855c.setHeaderTitle(charSequence);
        return this;
    }

    public final SubMenu setHeaderView(View view) {
        this.f15855c.setHeaderView(view);
        return this;
    }

    public final SubMenu setIcon(int i) {
        this.f15855c.setIcon(i);
        return this;
    }

    public final SubMenu setIcon(Drawable drawable) {
        this.f15855c.setIcon(drawable);
        return this;
    }
}
