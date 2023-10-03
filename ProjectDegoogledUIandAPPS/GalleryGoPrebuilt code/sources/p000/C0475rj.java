package p000;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.widget.LinearLayout;

/* renamed from: rj */
/* compiled from: PG */
public final class C0475rj implements C0255jf {

    /* renamed from: A */
    private int f15777A = 16;

    /* renamed from: B */
    private View f15778B;

    /* renamed from: C */
    private MenuItem.OnActionExpandListener f15779C;

    /* renamed from: a */
    public final int f15780a;

    /* renamed from: b */
    public final int f15781b;

    /* renamed from: c */
    public final int f15782c;

    /* renamed from: d */
    public CharSequence f15783d;

    /* renamed from: e */
    public Intent f15784e;

    /* renamed from: f */
    public char f15785f;

    /* renamed from: g */
    public int f15786g = 4096;

    /* renamed from: h */
    public char f15787h;

    /* renamed from: i */
    public int f15788i = 4096;

    /* renamed from: j */
    public final C0472rg f15789j;

    /* renamed from: k */
    public C0495sc f15790k;

    /* renamed from: l */
    public CharSequence f15791l;

    /* renamed from: m */
    public CharSequence f15792m;

    /* renamed from: n */
    public int f15793n;

    /* renamed from: o */
    public C0317ln f15794o;

    /* renamed from: p */
    public boolean f15795p = false;

    /* renamed from: q */
    private final int f15796q;

    /* renamed from: r */
    private CharSequence f15797r;

    /* renamed from: s */
    private Drawable f15798s;

    /* renamed from: t */
    private int f15799t = 0;

    /* renamed from: u */
    private MenuItem.OnMenuItemClickListener f15800u;

    /* renamed from: v */
    private ColorStateList f15801v = null;

    /* renamed from: w */
    private PorterDuff.Mode f15802w = null;

    /* renamed from: x */
    private boolean f15803x = false;

    /* renamed from: y */
    private boolean f15804y = false;

    /* renamed from: z */
    private boolean f15805z = false;

    public C0475rj(C0472rg rgVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.f15789j = rgVar;
        this.f15780a = i2;
        this.f15781b = i;
        this.f15796q = i3;
        this.f15782c = i4;
        this.f15783d = charSequence;
        this.f15793n = i5;
    }

    /* renamed from: a */
    public final C0317ln mo9137a() {
        return this.f15794o;
    }

    /* renamed from: e */
    public final boolean mo9894e() {
        return (this.f15777A & 4) != 0;
    }

    /* renamed from: f */
    public final boolean mo9895f() {
        return (this.f15777A & 32) == 32;
    }

    /* renamed from: g */
    public final boolean mo9896g() {
        return (this.f15793n & 1) != 0;
    }

    public final int getAlphabeticModifiers() {
        return this.f15788i;
    }

    public final char getAlphabeticShortcut() {
        return this.f15787h;
    }

    public final CharSequence getContentDescription() {
        return this.f15791l;
    }

    public final int getGroupId() {
        return this.f15781b;
    }

    public final ColorStateList getIconTintList() {
        return this.f15801v;
    }

    public final PorterDuff.Mode getIconTintMode() {
        return this.f15802w;
    }

    public final Intent getIntent() {
        return this.f15784e;
    }

    @ViewDebug.CapturedViewProperty
    public final int getItemId() {
        return this.f15780a;
    }

    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public final int getNumericModifiers() {
        return this.f15786g;
    }

    public final char getNumericShortcut() {
        return this.f15785f;
    }

    public final int getOrder() {
        return this.f15796q;
    }

    public final SubMenu getSubMenu() {
        return this.f15790k;
    }

    @ViewDebug.CapturedViewProperty
    public final CharSequence getTitle() {
        return this.f15783d;
    }

    public final CharSequence getTooltipText() {
        return this.f15792m;
    }

    /* renamed from: h */
    public final boolean mo9909h() {
        return (this.f15793n & 2) == 2;
    }

    public final boolean hasSubMenu() {
        return this.f15790k != null;
    }

    public final boolean isActionViewExpanded() {
        return this.f15795p;
    }

    public final boolean isCheckable() {
        return (this.f15777A & 1) != 0;
    }

    public final boolean isChecked() {
        return (this.f15777A & 2) == 2;
    }

    public final boolean isEnabled() {
        return (this.f15777A & 16) != 0;
    }

    /* renamed from: a */
    public static void m15267a(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    /* renamed from: a */
    private final Drawable m15265a(Drawable drawable) {
        if (drawable != null && this.f15805z && (this.f15803x || this.f15804y)) {
            int i = Build.VERSION.SDK_INT;
            drawable = drawable.mutate();
            if (this.f15803x) {
                C0257jh.m14475a(drawable, this.f15801v);
            }
            if (this.f15804y) {
                C0257jh.m14476a(drawable, this.f15802w);
            }
            this.f15805z = false;
        }
        return drawable;
    }

    public final boolean collapseActionView() {
        if ((this.f15793n & 8) == 0) {
            return false;
        }
        if (this.f15778B == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f15779C;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionCollapse(this)) {
            return false;
        }
        return this.f15789j.mo9853b(this);
    }

    public final boolean expandActionView() {
        if (!mo9911i()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f15779C;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionExpand(this)) {
            return false;
        }
        return this.f15789j.mo9839a(this);
    }

    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public final View getActionView() {
        View view = this.f15778B;
        if (view != null) {
            return view;
        }
        C0317ln lnVar = this.f15794o;
        if (lnVar == null) {
            return null;
        }
        View a = lnVar.mo9363a((MenuItem) this);
        this.f15778B = a;
        return a;
    }

    public final Drawable getIcon() {
        Drawable drawable = this.f15798s;
        if (drawable != null) {
            return m15265a(drawable);
        }
        int i = this.f15799t;
        if (i == 0) {
            return null;
        }
        Drawable b = C0436py.m15105b(this.f15789j.f15749a, i);
        this.f15799t = 0;
        this.f15798s = b;
        return m15265a(b);
    }

    /* renamed from: c */
    public final char mo9890c() {
        return this.f15789j.mo9854c() ? this.f15787h : this.f15785f;
    }

    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f15797r;
        if (charSequence == null) {
            charSequence = this.f15783d;
        }
        int i = Build.VERSION.SDK_INT;
        return charSequence;
    }

    /* renamed from: a */
    public final CharSequence mo9885a(C0487rv rvVar) {
        return (rvVar != null && rvVar.mo764b()) ? getTitleCondensed() : this.f15783d;
    }

    /* renamed from: i */
    public final boolean mo9911i() {
        C0317ln lnVar;
        if ((this.f15793n & 8) != 0) {
            if (this.f15778B == null && (lnVar = this.f15794o) != null) {
                this.f15778B = lnVar.mo9363a((MenuItem) this);
            }
            if (this.f15778B != null) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public final boolean mo9888b() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f15800u;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        C0472rg rgVar = this.f15789j;
        if (rgVar.mo9838a(rgVar, (MenuItem) this)) {
            return true;
        }
        Intent intent = this.f15784e;
        if (intent != null) {
            try {
                this.f15789j.f15749a.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        C0317ln lnVar = this.f15794o;
        if (lnVar == null || !lnVar.mo9368d()) {
            return false;
        }
        return true;
    }

    public final boolean isVisible() {
        C0317ln lnVar = this.f15794o;
        return (lnVar != null && lnVar.mo9366b()) ? (this.f15777A & 8) == 0 && this.f15794o.mo9367c() : (this.f15777A & 8) == 0;
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public final /* bridge */ /* synthetic */ MenuItem setActionView(int i) {
        Context context = this.f15789j.f15749a;
        m15266a(LayoutInflater.from(context).inflate(i, new LinearLayout(context), false));
        return this;
    }

    /* renamed from: a */
    private final void m15266a(View view) {
        int i;
        this.f15778B = view;
        this.f15794o = null;
        if (view != null && view.getId() == -1 && (i = this.f15780a) > 0) {
            view.setId(i);
        }
        this.f15789j.mo9869k();
    }

    public final /* bridge */ /* synthetic */ MenuItem setActionView(View view) {
        m15266a(view);
        return this;
    }

    /* renamed from: d */
    public final void mo9892d(boolean z) {
        this.f15795p = z;
        this.f15789j.mo9851b(false);
    }

    public final MenuItem setAlphabeticShortcut(char c) {
        if (this.f15787h != c) {
            this.f15787h = Character.toLowerCase(c);
            this.f15789j.mo9851b(false);
        }
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.f15787h == c && this.f15788i == i) {
            return this;
        }
        this.f15787h = Character.toLowerCase(c);
        this.f15788i = KeyEvent.normalizeMetaState(i);
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setCheckable(boolean z) {
        int i = this.f15777A;
        boolean z2 = z | (i & true);
        this.f15777A = z2 ? 1 : 0;
        if (i != z2) {
            this.f15789j.mo9851b(false);
        }
        return this;
    }

    public final MenuItem setChecked(boolean z) {
        if ((this.f15777A & 4) != 0) {
            C0472rg rgVar = this.f15789j;
            int i = this.f15781b;
            int size = rgVar.f15751c.size();
            rgVar.mo9859e();
            for (int i2 = 0; i2 < size; i2++) {
                C0475rj rjVar = (C0475rj) rgVar.f15751c.get(i2);
                if (rjVar.f15781b == i && rjVar.mo9894e() && rjVar.isCheckable()) {
                    rjVar.m15268e(rjVar == this);
                }
            }
            rgVar.mo9860f();
        } else {
            m15268e(z);
        }
        return this;
    }

    /* renamed from: e */
    private final void m15268e(boolean z) {
        int i = this.f15777A;
        int i2 = (!z ? 0 : 2) | (i & -3);
        this.f15777A = i2;
        if (i != i2) {
            this.f15789j.mo9851b(false);
        }
    }

    /* renamed from: a */
    public final void mo9138a(CharSequence charSequence) {
        this.f15791l = charSequence;
        this.f15789j.mo9851b(false);
    }

    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        mo9138a(charSequence);
        return this;
    }

    public final MenuItem setEnabled(boolean z) {
        if (z) {
            this.f15777A |= 16;
        } else {
            this.f15777A &= -17;
        }
        this.f15789j.mo9851b(false);
        return this;
    }

    /* renamed from: a */
    public final void mo9887a(boolean z) {
        this.f15777A = (!z ? 0 : 4) | (this.f15777A & -5);
    }

    public final MenuItem setIcon(int i) {
        this.f15798s = null;
        this.f15799t = i;
        this.f15805z = true;
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setIcon(Drawable drawable) {
        this.f15799t = 0;
        this.f15798s = drawable;
        this.f15805z = true;
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f15801v = colorStateList;
        this.f15803x = true;
        this.f15805z = true;
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f15802w = mode;
        this.f15804y = true;
        this.f15805z = true;
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setIntent(Intent intent) {
        this.f15784e = intent;
        return this;
    }

    /* renamed from: c */
    public final void mo9891c(boolean z) {
        if (z) {
            this.f15777A |= 32;
        } else {
            this.f15777A &= -33;
        }
    }

    public final MenuItem setNumericShortcut(char c) {
        if (this.f15785f != c) {
            this.f15785f = c;
            this.f15789j.mo9851b(false);
        }
        return this;
    }

    public final MenuItem setNumericShortcut(char c, int i) {
        if (this.f15785f == c && this.f15786g == i) {
            return this;
        }
        this.f15785f = c;
        this.f15786g = KeyEvent.normalizeMetaState(i);
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f15779C = onActionExpandListener;
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f15800u = onMenuItemClickListener;
        return this;
    }

    public final MenuItem setShortcut(char c, char c2) {
        this.f15785f = c;
        this.f15787h = Character.toLowerCase(c2);
        this.f15789j.mo9851b(false);
        return this;
    }

    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.f15785f = c;
        this.f15786g = KeyEvent.normalizeMetaState(i);
        this.f15787h = Character.toLowerCase(c2);
        this.f15788i = KeyEvent.normalizeMetaState(i2);
        this.f15789j.mo9851b(false);
        return this;
    }

    public final void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.f15793n = i;
            this.f15789j.mo9869k();
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public final /* bridge */ /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    /* renamed from: a */
    public final void mo9886a(C0495sc scVar) {
        this.f15790k = scVar;
        scVar.setHeaderTitle(this.f15783d);
    }

    /* renamed from: a */
    public final void mo9139a(C0317ln lnVar) {
        C0317ln lnVar2 = this.f15794o;
        if (lnVar2 != null) {
            lnVar2.f15206a = null;
        }
        this.f15778B = null;
        this.f15794o = lnVar;
        this.f15789j.mo9851b(true);
        C0317ln lnVar3 = this.f15794o;
        if (lnVar3 != null) {
            lnVar3.mo9365a((C0316lm) new C0474ri(this));
        }
    }

    public final MenuItem setTitle(int i) {
        setTitle((CharSequence) this.f15789j.f15749a.getString(i));
        return this;
    }

    public final MenuItem setTitle(CharSequence charSequence) {
        this.f15783d = charSequence;
        this.f15789j.mo9851b(false);
        C0495sc scVar = this.f15790k;
        if (scVar != null) {
            scVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f15797r = charSequence;
        this.f15789j.mo9851b(false);
        return this;
    }

    /* renamed from: b */
    public final void mo9140b(CharSequence charSequence) {
        this.f15792m = charSequence;
        this.f15789j.mo9851b(false);
    }

    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        mo9140b(charSequence);
        return this;
    }

    public final MenuItem setVisible(boolean z) {
        if (mo9889b(z)) {
            this.f15789j.mo9870l();
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo9889b(boolean z) {
        int i = this.f15777A;
        int i2 = (!z ? 8 : 0) | (i & -9);
        this.f15777A = i2;
        return i != i2;
    }

    /* renamed from: d */
    public final boolean mo9893d() {
        return this.f15789j.mo9858d() && mo9890c() != 0;
    }

    public final String toString() {
        CharSequence charSequence = this.f15783d;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
}
