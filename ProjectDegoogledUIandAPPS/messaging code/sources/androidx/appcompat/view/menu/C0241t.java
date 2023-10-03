package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.LinearLayout;
import androidx.appcompat.C0126R;
import androidx.appcompat.p018a.p019a.C0130a;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.p023a.C0329b;
import androidx.core.view.ActionProvider;

/* renamed from: androidx.appcompat.view.menu.t */
public final class C0241t implements C0329b {

    /* renamed from: Am */
    private PorterDuff.Mode f278Am = null;

    /* renamed from: Bm */
    private boolean f279Bm = false;

    /* renamed from: Cm */
    private boolean f280Cm = false;

    /* renamed from: Gm */
    private C0220M f281Gm;

    /* renamed from: gn */
    private final int f282gn;

    /* renamed from: hn */
    private int f283hn = 0;

    /* renamed from: jn */
    private Runnable f284jn;

    /* renamed from: kn */
    private MenuItem.OnMenuItemClickListener f285kn;

    /* renamed from: ln */
    private boolean f286ln = false;
    private CharSequence mContentDescription;
    private int mFlags = 16;
    private final int mGroup;
    private final int mId;
    private Intent mIntent;
    C0238q mMenu;
    private CharSequence mTitle;
    private CharSequence mTooltipText;

    /* renamed from: mn */
    private int f287mn = 0;

    /* renamed from: nn */
    private View f288nn;

    /* renamed from: pn */
    private ActionProvider f289pn;

    /* renamed from: qn */
    private MenuItem.OnActionExpandListener f290qn;

    /* renamed from: rn */
    private boolean f291rn = false;

    /* renamed from: sm */
    private final int f292sm;

    /* renamed from: sn */
    private ContextMenu.ContextMenuInfo f293sn;

    /* renamed from: tm */
    private CharSequence f294tm;

    /* renamed from: um */
    private char f295um;

    /* renamed from: vm */
    private int f296vm = NotificationCompat.FLAG_BUBBLE;

    /* renamed from: wm */
    private char f297wm;

    /* renamed from: xm */
    private int f298xm = NotificationCompat.FLAG_BUBBLE;

    /* renamed from: ym */
    private Drawable f299ym;

    /* renamed from: zm */
    private ColorStateList f300zm = null;

    C0241t(C0238q qVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.mMenu = qVar;
        this.mId = i2;
        this.mGroup = i;
        this.f282gn = i3;
        this.f292sm = i4;
        this.mTitle = charSequence;
        this.f287mn = i5;
    }

    /* renamed from: a */
    private static void m233a(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    /* renamed from: f */
    private Drawable m234f(Drawable drawable) {
        if (drawable != null && this.f286ln && (this.f279Bm || this.f280Cm)) {
            int i = Build.VERSION.SDK_INT;
            drawable = drawable.mutate();
            if (this.f279Bm) {
                ColorStateList colorStateList = this.f300zm;
                int i2 = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList);
            }
            if (this.f280Cm) {
                PorterDuff.Mode mode = this.f278Am;
                int i3 = Build.VERSION.SDK_INT;
                drawable.setTintMode(mode);
            }
            this.f286ln = false;
        }
        return drawable;
    }

    /* renamed from: L */
    public ActionProvider mo1479L() {
        return this.f289pn;
    }

    public boolean collapseActionView() {
        if ((this.f287mn & 8) == 0) {
            return false;
        }
        if (this.f288nn == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f290qn;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.mMenu.mo1452a(this);
        }
        return false;
    }

    public boolean expandActionView() {
        if (!hasCollapsibleActionView()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f290qn;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.mMenu.mo1453b(this);
        }
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        View view = this.f288nn;
        if (view != null) {
            return view;
        }
        ActionProvider actionProvider = this.f289pn;
        if (actionProvider == null) {
            return null;
        }
        this.f288nn = actionProvider.onCreateActionView(this);
        return this.f288nn;
    }

    public int getAlphabeticModifiers() {
        return this.f298xm;
    }

    public char getAlphabeticShortcut() {
        return this.f297wm;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        Drawable drawable = this.f299ym;
        if (drawable != null) {
            return m234f(drawable);
        }
        if (this.f283hn == 0) {
            return null;
        }
        Drawable drawable2 = C0130a.getDrawable(this.mMenu.getContext(), this.f283hn);
        this.f283hn = 0;
        this.f299ym = drawable2;
        return m234f(drawable2);
    }

    public ColorStateList getIconTintList() {
        return this.f300zm;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f278Am;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f293sn;
    }

    public int getNumericModifiers() {
        return this.f296vm;
    }

    public char getNumericShortcut() {
        return this.f295um;
    }

    public int getOrder() {
        return this.f282gn;
    }

    public int getOrdering() {
        return this.f292sm;
    }

    /* access modifiers changed from: package-private */
    public char getShortcut() {
        return this.mMenu.isQwertyMode() ? this.f297wm : this.f295um;
    }

    /* access modifiers changed from: package-private */
    public String getShortcutLabel() {
        char shortcut = getShortcut();
        if (shortcut == 0) {
            return "";
        }
        Resources resources = this.mMenu.getContext().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.mMenu.getContext()).hasPermanentMenuKey()) {
            sb.append(resources.getString(C0126R.string.abc_prepend_shortcut_label));
        }
        int i = this.mMenu.isQwertyMode() ? this.f298xm : this.f296vm;
        m233a(sb, i, 65536, resources.getString(C0126R.string.abc_menu_meta_shortcut_label));
        m233a(sb, i, NotificationCompat.FLAG_BUBBLE, resources.getString(C0126R.string.abc_menu_ctrl_shortcut_label));
        m233a(sb, i, 2, resources.getString(C0126R.string.abc_menu_alt_shortcut_label));
        m233a(sb, i, 1, resources.getString(C0126R.string.abc_menu_shift_shortcut_label));
        m233a(sb, i, 4, resources.getString(C0126R.string.abc_menu_sym_shortcut_label));
        m233a(sb, i, 8, resources.getString(C0126R.string.abc_menu_function_shortcut_label));
        if (shortcut == 8) {
            sb.append(resources.getString(C0126R.string.abc_menu_delete_shortcut_label));
        } else if (shortcut == 10) {
            sb.append(resources.getString(C0126R.string.abc_menu_enter_shortcut_label));
        } else if (shortcut != ' ') {
            sb.append(shortcut);
        } else {
            sb.append(resources.getString(C0126R.string.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    public SubMenu getSubMenu() {
        return this.f281Gm;
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f294tm;
        if (charSequence == null) {
            charSequence = this.mTitle;
        }
        int i = Build.VERSION.SDK_INT;
        return charSequence;
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasCollapsibleActionView() {
        ActionProvider actionProvider;
        if ((this.f287mn & 8) == 0) {
            return false;
        }
        if (this.f288nn == null && (actionProvider = this.f289pn) != null) {
            this.f288nn = actionProvider.onCreateActionView(this);
        }
        if (this.f288nn != null) {
            return true;
        }
        return false;
    }

    public boolean hasSubMenu() {
        return this.f281Gm != null;
    }

    public boolean invoke() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f285kn;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        C0238q qVar = this.mMenu;
        if (qVar.mo1451a(qVar, (MenuItem) this)) {
            return true;
        }
        Runnable runnable = this.f284jn;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        ActionProvider actionProvider = this.f289pn;
        if (actionProvider == null || !actionProvider.onPerformDefaultAction()) {
            return false;
        }
        return true;
    }

    public boolean isActionButton() {
        return (this.mFlags & 32) == 32;
    }

    public boolean isActionViewExpanded() {
        return this.f291rn;
    }

    public boolean isCheckable() {
        return (this.mFlags & 1) == 1;
    }

    public boolean isChecked() {
        return (this.mFlags & 2) == 2;
    }

    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isExclusiveCheckable() {
        return (this.mFlags & 4) != 0;
    }

    public boolean isVisible() {
        ActionProvider actionProvider = this.f289pn;
        if (actionProvider == null || !actionProvider.overridesItemVisibility()) {
            if ((this.mFlags & 8) == 0) {
                return true;
            }
            return false;
        } else if ((this.mFlags & 8) != 0 || !this.f289pn.isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean requestsActionButton() {
        return (this.f287mn & 1) == 1;
    }

    public boolean requiresActionButton() {
        return (this.f287mn & 2) == 2;
    }

    public boolean requiresOverflow() {
        return !requiresActionButton() && !requestsActionButton();
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public void setActionViewExpanded(boolean z) {
        this.f291rn = z;
        this.mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c) {
        if (this.f297wm == c) {
            return this;
        }
        this.f297wm = Character.toLowerCase(c);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        int i = this.mFlags;
        this.mFlags = z | (i & true) ? 1 : 0;
        if (i != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean z) {
        if ((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
        } else {
            setCheckedInt(z);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setCheckedInt(boolean z) {
        int i = this.mFlags;
        this.mFlags = (z ? 2 : 0) | (i & -3);
        if (i != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }

    public MenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.mFlags |= 16;
        } else {
            this.mFlags &= -17;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean z) {
        this.mFlags = (z ? 4 : 0) | (this.mFlags & -5);
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f283hn = 0;
        this.f299ym = drawable;
        this.f286ln = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f300zm = colorStateList;
        this.f279Bm = true;
        this.f286ln = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f278Am = mode;
        this.f280Cm = true;
        this.f286ln = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean z) {
        if (z) {
            this.mFlags |= 32;
        } else {
            this.mFlags &= -33;
        }
    }

    /* access modifiers changed from: package-private */
    public void setMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f293sn = contextMenuInfo;
    }

    public MenuItem setNumericShortcut(char c) {
        if (this.f295um == c) {
            return this;
        }
        this.f295um = c;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f290qn = onActionExpandListener;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f285kn = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f295um = c;
        this.f297wm = Character.toLowerCase(c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.f287mn = i;
            this.mMenu.mo1594c(this);
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public MenuItem setShowAsActionFlags(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.f287mn = i;
            this.mMenu.mo1594c(this);
            return this;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        C0220M m = this.f281Gm;
        if (m != null) {
            m.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f294tm = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        if (setVisibleInt(z)) {
            this.mMenu.mo1600d(this);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean setVisibleInt(boolean z) {
        int i = this.mFlags;
        this.mFlags = (z ? 0 : 8) | (i & -9);
        if (i != this.mFlags) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && getShortcut() != 0;
    }

    public boolean showsTextAsAction() {
        return (this.f287mn & 4) == 4;
    }

    public String toString() {
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    /* renamed from: a */
    public void mo1644a(C0220M m) {
        this.f281Gm = m;
        m.setHeaderTitle(this.mTitle);
    }

    public C0329b setActionView(View view) {
        int i;
        this.f288nn = view;
        this.f289pn = null;
        if (view != null && view.getId() == -1 && (i = this.mId) > 0) {
            view.setId(i);
        }
        this.mMenu.mo1594c(this);
        return this;
    }

    /* renamed from: setContentDescription  reason: collision with other method in class */
    public C0329b m4700setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    /* renamed from: setTooltipText  reason: collision with other method in class */
    public C0329b m4701setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.f297wm == c && this.f298xm == i) {
            return this;
        }
        this.f297wm = Character.toLowerCase(c);
        this.f298xm = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setNumericShortcut(char c, int i) {
        if (this.f295um == c && this.f296vm == i) {
            return this;
        }
        this.f295um = c;
        this.f296vm = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f295um = c;
        this.f296vm = KeyEvent.normalizeMetaState(i);
        this.f297wm = Character.toLowerCase(c2);
        this.f298xm = KeyEvent.normalizeMetaState(i2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public CharSequence mo1643a(C0213F f) {
        if (f == null || !f.prefersCondensedTitle()) {
            return this.mTitle;
        }
        CharSequence charSequence = this.f294tm;
        if (charSequence == null) {
            charSequence = this.mTitle;
        }
        int i = Build.VERSION.SDK_INT;
        return charSequence;
    }

    public MenuItem setIcon(int i) {
        this.f299ym = null;
        this.f283hn = i;
        this.f286ln = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setTitle(int i) {
        String string = this.mMenu.getContext().getString(i);
        this.mTitle = string;
        this.mMenu.onItemsChanged(false);
        C0220M m = this.f281Gm;
        if (m != null) {
            m.setHeaderTitle((CharSequence) string);
        }
        return this;
    }

    public MenuItem setActionView(int i) {
        Context context = this.mMenu.getContext();
        setActionView(LayoutInflater.from(context).inflate(i, new LinearLayout(context), false));
        return this;
    }

    /* renamed from: a */
    public C0329b mo1480a(ActionProvider actionProvider) {
        ActionProvider actionProvider2 = this.f289pn;
        if (actionProvider2 != null) {
            actionProvider2.reset();
        }
        this.f288nn = null;
        this.f289pn = actionProvider;
        this.mMenu.onItemsChanged(true);
        ActionProvider actionProvider3 = this.f289pn;
        if (actionProvider3 != null) {
            actionProvider3.setVisibilityListener(new C0240s(this));
        }
        return this;
    }
}
