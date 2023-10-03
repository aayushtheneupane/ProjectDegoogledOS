package p000;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: qq */
/* compiled from: PG */
public final class C0455qq implements C0255jf {

    /* renamed from: a */
    private CharSequence f15672a;

    /* renamed from: b */
    private CharSequence f15673b;

    /* renamed from: c */
    private Intent f15674c;

    /* renamed from: d */
    private char f15675d;

    /* renamed from: e */
    private int f15676e = 4096;

    /* renamed from: f */
    private char f15677f;

    /* renamed from: g */
    private int f15678g = 4096;

    /* renamed from: h */
    private Drawable f15679h;

    /* renamed from: i */
    private final Context f15680i;

    /* renamed from: j */
    private CharSequence f15681j;

    /* renamed from: k */
    private CharSequence f15682k;

    /* renamed from: l */
    private ColorStateList f15683l = null;

    /* renamed from: m */
    private PorterDuff.Mode f15684m = null;

    /* renamed from: n */
    private boolean f15685n = false;

    /* renamed from: o */
    private boolean f15686o = false;

    /* renamed from: p */
    private int f15687p = 16;

    public C0455qq(Context context, CharSequence charSequence) {
        this.f15680i = context;
        this.f15672a = charSequence;
    }

    /* renamed from: a */
    public final C0317ln mo9137a() {
        return null;
    }

    public final boolean collapseActionView() {
        return false;
    }

    public final boolean expandActionView() {
        return false;
    }

    public final View getActionView() {
        return null;
    }

    public final int getAlphabeticModifiers() {
        return this.f15678g;
    }

    public final char getAlphabeticShortcut() {
        return this.f15677f;
    }

    public final CharSequence getContentDescription() {
        return this.f15681j;
    }

    public final int getGroupId() {
        return 0;
    }

    public final Drawable getIcon() {
        return this.f15679h;
    }

    public final ColorStateList getIconTintList() {
        return this.f15683l;
    }

    public final PorterDuff.Mode getIconTintMode() {
        return this.f15684m;
    }

    public final Intent getIntent() {
        return this.f15674c;
    }

    public final int getItemId() {
        return 16908332;
    }

    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public final int getNumericModifiers() {
        return this.f15676e;
    }

    public final char getNumericShortcut() {
        return this.f15675d;
    }

    public final int getOrder() {
        return 0;
    }

    public final SubMenu getSubMenu() {
        return null;
    }

    public final CharSequence getTitle() {
        return this.f15672a;
    }

    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f15673b;
        return charSequence == null ? this.f15672a : charSequence;
    }

    public final CharSequence getTooltipText() {
        return this.f15682k;
    }

    public final boolean hasSubMenu() {
        return false;
    }

    public final boolean isActionViewExpanded() {
        return false;
    }

    public final boolean isCheckable() {
        return (this.f15687p & 1) != 0;
    }

    public final boolean isChecked() {
        return (this.f15687p & 2) != 0;
    }

    public final boolean isEnabled() {
        return (this.f15687p & 16) != 0;
    }

    public final boolean isVisible() {
        return (this.f15687p & 8) == 0;
    }

    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    public final void setShowAsAction(int i) {
    }

    public final /* bridge */ /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        return this;
    }

    /* renamed from: b */
    private final void m15171b() {
        Drawable drawable = this.f15679h;
        if (drawable == null) {
            return;
        }
        if (this.f15685n || this.f15686o) {
            int i = Build.VERSION.SDK_INT;
            this.f15679h = drawable;
            Drawable mutate = drawable.mutate();
            this.f15679h = mutate;
            if (this.f15685n) {
                C0257jh.m14475a(mutate, this.f15683l);
            }
            if (this.f15686o) {
                C0257jh.m14476a(this.f15679h, this.f15684m);
            }
        }
    }

    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setAlphabeticShortcut(char c) {
        this.f15677f = Character.toLowerCase(c);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c, int i) {
        this.f15677f = Character.toLowerCase(c);
        this.f15678g = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public final MenuItem setCheckable(boolean z) {
        this.f15687p = z | (this.f15687p & true) ? 1 : 0;
        return this;
    }

    public final MenuItem setChecked(boolean z) {
        this.f15687p = (!z ? 0 : 2) | (this.f15687p & -3);
        return this;
    }

    /* renamed from: a */
    public final void mo9138a(CharSequence charSequence) {
        this.f15681j = charSequence;
    }

    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        this.f15681j = charSequence;
        return this;
    }

    public final MenuItem setEnabled(boolean z) {
        this.f15687p = (!z ? 0 : 16) | (this.f15687p & -17);
        return this;
    }

    public final MenuItem setIcon(int i) {
        this.f15679h = C0071co.m4660a(this.f15680i, i);
        m15171b();
        return this;
    }

    public final MenuItem setIcon(Drawable drawable) {
        this.f15679h = drawable;
        m15171b();
        return this;
    }

    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f15683l = colorStateList;
        this.f15685n = true;
        m15171b();
        return this;
    }

    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f15684m = mode;
        this.f15686o = true;
        m15171b();
        return this;
    }

    public final MenuItem setIntent(Intent intent) {
        this.f15674c = intent;
        return this;
    }

    public final MenuItem setNumericShortcut(char c) {
        this.f15675d = c;
        return this;
    }

    public final MenuItem setNumericShortcut(char c, int i) {
        this.f15675d = c;
        this.f15676e = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setShortcut(char c, char c2) {
        this.f15675d = c;
        this.f15677f = Character.toLowerCase(c2);
        return this;
    }

    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f15675d = c;
        this.f15676e = KeyEvent.normalizeMetaState(i);
        this.f15677f = Character.toLowerCase(c2);
        this.f15678g = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    /* renamed from: a */
    public final void mo9139a(C0317ln lnVar) {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setTitle(int i) {
        this.f15672a = this.f15680i.getResources().getString(i);
        return this;
    }

    public final MenuItem setTitle(CharSequence charSequence) {
        this.f15672a = charSequence;
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f15673b = charSequence;
        return this;
    }

    /* renamed from: b */
    public final void mo9140b(CharSequence charSequence) {
        this.f15682k = charSequence;
    }

    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        this.f15682k = charSequence;
        return this;
    }

    public final MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.f15687p & 8;
        if (z) {
            i = 0;
        }
        this.f15687p = i2 | i;
        return this;
    }
}
