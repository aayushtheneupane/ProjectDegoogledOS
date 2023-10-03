package p000;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Method;

/* renamed from: rp */
/* compiled from: PG */
public final class C0481rp extends C0459qu implements MenuItem {

    /* renamed from: c */
    public final C0255jf f15814c;

    /* renamed from: d */
    public Method f15815d;

    public C0481rp(Context context, C0255jf jfVar) {
        super(context);
        if (jfVar != null) {
            this.f15814c = jfVar;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public final boolean collapseActionView() {
        return this.f15814c.collapseActionView();
    }

    public final boolean expandActionView() {
        return this.f15814c.expandActionView();
    }

    public final ActionProvider getActionProvider() {
        C0317ln a = this.f15814c.mo9137a();
        if (a instanceof C0476rk) {
            return ((C0476rk) a).f15806b;
        }
        return null;
    }

    public final View getActionView() {
        View actionView = this.f15814c.getActionView();
        return actionView instanceof C0478rm ? (View) ((C0478rm) actionView).f15809a : actionView;
    }

    public final int getAlphabeticModifiers() {
        return this.f15814c.getAlphabeticModifiers();
    }

    public final char getAlphabeticShortcut() {
        return this.f15814c.getAlphabeticShortcut();
    }

    public final CharSequence getContentDescription() {
        return this.f15814c.getContentDescription();
    }

    public final int getGroupId() {
        return this.f15814c.getGroupId();
    }

    public final Drawable getIcon() {
        return this.f15814c.getIcon();
    }

    public final ColorStateList getIconTintList() {
        return this.f15814c.getIconTintList();
    }

    public final PorterDuff.Mode getIconTintMode() {
        return this.f15814c.getIconTintMode();
    }

    public final Intent getIntent() {
        return this.f15814c.getIntent();
    }

    public final int getItemId() {
        return this.f15814c.getItemId();
    }

    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f15814c.getMenuInfo();
    }

    public final int getNumericModifiers() {
        return this.f15814c.getNumericModifiers();
    }

    public final char getNumericShortcut() {
        return this.f15814c.getNumericShortcut();
    }

    public final int getOrder() {
        return this.f15814c.getOrder();
    }

    public final SubMenu getSubMenu() {
        return mo9794a(this.f15814c.getSubMenu());
    }

    public final CharSequence getTitle() {
        return this.f15814c.getTitle();
    }

    public final CharSequence getTitleCondensed() {
        return this.f15814c.getTitleCondensed();
    }

    public final CharSequence getTooltipText() {
        return this.f15814c.getTooltipText();
    }

    public final boolean hasSubMenu() {
        return this.f15814c.hasSubMenu();
    }

    public final boolean isActionViewExpanded() {
        return this.f15814c.isActionViewExpanded();
    }

    public final boolean isCheckable() {
        return this.f15814c.isCheckable();
    }

    public final boolean isChecked() {
        return this.f15814c.isChecked();
    }

    public final boolean isEnabled() {
        return this.f15814c.isEnabled();
    }

    public final boolean isVisible() {
        return this.f15814c.isVisible();
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        int i = Build.VERSION.SDK_INT;
        C0477rl rlVar = new C0477rl(this, actionProvider);
        C0255jf jfVar = this.f15814c;
        if (actionProvider == null) {
            rlVar = null;
        }
        jfVar.mo9139a((C0317ln) rlVar);
        return this;
    }

    public final MenuItem setActionView(int i) {
        this.f15814c.setActionView(i);
        View actionView = this.f15814c.getActionView();
        if (actionView instanceof CollapsibleActionView) {
            this.f15814c.setActionView((View) new C0478rm(actionView));
        }
        return this;
    }

    public final MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new C0478rm(view);
        }
        this.f15814c.setActionView(view);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c) {
        this.f15814c.setAlphabeticShortcut(c);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c, int i) {
        this.f15814c.setAlphabeticShortcut(c, i);
        return this;
    }

    public final MenuItem setCheckable(boolean z) {
        this.f15814c.setCheckable(z);
        return this;
    }

    public final MenuItem setChecked(boolean z) {
        this.f15814c.setChecked(z);
        return this;
    }

    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f15814c.mo9138a(charSequence);
        return this;
    }

    public final MenuItem setEnabled(boolean z) {
        this.f15814c.setEnabled(z);
        return this;
    }

    public final MenuItem setIcon(int i) {
        this.f15814c.setIcon(i);
        return this;
    }

    public final MenuItem setIcon(Drawable drawable) {
        this.f15814c.setIcon(drawable);
        return this;
    }

    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f15814c.setIconTintList(colorStateList);
        return this;
    }

    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f15814c.setIconTintMode(mode);
        return this;
    }

    public final MenuItem setIntent(Intent intent) {
        this.f15814c.setIntent(intent);
        return this;
    }

    public final MenuItem setNumericShortcut(char c) {
        this.f15814c.setNumericShortcut(c);
        return this;
    }

    public final MenuItem setNumericShortcut(char c, int i) {
        this.f15814c.setNumericShortcut(c, i);
        return this;
    }

    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f15814c.setOnActionExpandListener(onActionExpandListener != null ? new C0479rn(this, onActionExpandListener) : null);
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f15814c.setOnMenuItemClickListener(onMenuItemClickListener != null ? new C0480ro(this, onMenuItemClickListener) : null);
        return this;
    }

    public final MenuItem setShortcut(char c, char c2) {
        this.f15814c.setShortcut(c, c2);
        return this;
    }

    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f15814c.setShortcut(c, c2, i, i2);
        return this;
    }

    public final void setShowAsAction(int i) {
        this.f15814c.setShowAsAction(i);
    }

    public final MenuItem setShowAsActionFlags(int i) {
        this.f15814c.setShowAsActionFlags(i);
        return this;
    }

    public final MenuItem setTitle(int i) {
        this.f15814c.setTitle(i);
        return this;
    }

    public final MenuItem setTitle(CharSequence charSequence) {
        this.f15814c.setTitle(charSequence);
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f15814c.setTitleCondensed(charSequence);
        return this;
    }

    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.f15814c.mo9140b(charSequence);
        return this;
    }

    public final MenuItem setVisible(boolean z) {
        return this.f15814c.setVisible(z);
    }
}
