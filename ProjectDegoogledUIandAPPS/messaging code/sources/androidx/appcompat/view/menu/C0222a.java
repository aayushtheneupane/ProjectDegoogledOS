package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.internal.p023a.C0329b;
import androidx.core.view.ActionProvider;

/* renamed from: androidx.appcompat.view.menu.a */
public class C0222a implements C0329b {

    /* renamed from: Am */
    private PorterDuff.Mode f224Am = null;

    /* renamed from: Bm */
    private boolean f225Bm = false;

    /* renamed from: Cm */
    private boolean f226Cm = false;
    private CharSequence mContentDescription;
    private Context mContext;
    private int mFlags = 16;
    private final int mGroup;
    private final int mId;
    private Intent mIntent;
    private CharSequence mTitle;
    private CharSequence mTooltipText;

    /* renamed from: sm */
    private final int f227sm;

    /* renamed from: tm */
    private CharSequence f228tm;

    /* renamed from: um */
    private char f229um;

    /* renamed from: vm */
    private int f230vm = NotificationCompat.FLAG_BUBBLE;

    /* renamed from: wm */
    private char f231wm;

    /* renamed from: xm */
    private int f232xm = NotificationCompat.FLAG_BUBBLE;

    /* renamed from: ym */
    private Drawable f233ym;

    /* renamed from: zm */
    private ColorStateList f234zm = null;

    public C0222a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.mContext = context;
        this.mId = i2;
        this.mGroup = i;
        this.f227sm = i4;
        this.mTitle = charSequence;
    }

    /* renamed from: yn */
    private void m208yn() {
        if (this.f233ym == null) {
            return;
        }
        if (this.f225Bm || this.f226Cm) {
            Drawable drawable = this.f233ym;
            int i = Build.VERSION.SDK_INT;
            this.f233ym = drawable;
            this.f233ym = this.f233ym.mutate();
            if (this.f225Bm) {
                Drawable drawable2 = this.f233ym;
                ColorStateList colorStateList = this.f234zm;
                int i2 = Build.VERSION.SDK_INT;
                drawable2.setTintList(colorStateList);
            }
            if (this.f226Cm) {
                Drawable drawable3 = this.f233ym;
                PorterDuff.Mode mode = this.f224Am;
                int i3 = Build.VERSION.SDK_INT;
                drawable3.setTintMode(mode);
            }
        }
    }

    /* renamed from: L */
    public ActionProvider mo1479L() {
        return null;
    }

    /* renamed from: a */
    public C0329b mo1480a(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.f232xm;
    }

    public char getAlphabeticShortcut() {
        return this.f231wm;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        return this.f233ym;
    }

    public ColorStateList getIconTintList() {
        return this.f234zm;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f224Am;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.f230vm;
    }

    public char getNumericShortcut() {
        return this.f229um;
    }

    public int getOrder() {
        return this.f227sm;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f228tm;
        return charSequence != null ? charSequence : this.mTitle;
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isChecked() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isVisible() {
        return (this.mFlags & 8) == 0;
    }

    public boolean requiresActionButton() {
        return true;
    }

    public boolean requiresOverflow() {
        return false;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char c) {
        this.f231wm = Character.toLowerCase(c);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.mFlags = z | (this.mFlags & true) ? 1 : 0;
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.mFlags = (z ? 2 : 0) | (this.mFlags & -3);
        return this;
    }

    public MenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.mFlags = (z ? 16 : 0) | (this.mFlags & -17);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f233ym = drawable;
        m208yn();
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f234zm = colorStateList;
        this.f225Bm = true;
        m208yn();
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f224Am = mode;
        this.f226Cm = true;
        m208yn();
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        this.f229um = c;
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f229um = c;
        this.f231wm = Character.toLowerCase(c2);
        return this;
    }

    public void setShowAsAction(int i) {
    }

    public MenuItem setShowAsActionFlags(int i) {
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f228tm = charSequence;
        return this;
    }

    public MenuItem setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.mFlags & 8;
        if (z) {
            i = 0;
        }
        this.mFlags = i2 | i;
        return this;
    }

    public MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.f231wm = Character.toLowerCase(c);
        this.f232xm = KeyEvent.normalizeMetaState(i);
        return this;
    }

    /* renamed from: setContentDescription  reason: collision with other method in class */
    public C0329b m4698setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i) {
        this.f229um = c;
        this.f230vm = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public MenuItem setTitle(int i) {
        this.mTitle = this.mContext.getResources().getString(i);
        return this;
    }

    /* renamed from: setTooltipText  reason: collision with other method in class */
    public C0329b m4699setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        return this;
    }

    public MenuItem setIcon(int i) {
        this.f233ym = ContextCompat.getDrawable(this.mContext, i);
        m208yn();
        return this;
    }

    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f229um = c;
        this.f230vm = KeyEvent.normalizeMetaState(i);
        this.f231wm = Character.toLowerCase(c2);
        this.f232xm = KeyEvent.normalizeMetaState(i2);
        return this;
    }
}
