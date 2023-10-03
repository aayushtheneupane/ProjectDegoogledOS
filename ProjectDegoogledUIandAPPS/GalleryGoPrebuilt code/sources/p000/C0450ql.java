package p000;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;

/* renamed from: ql */
/* compiled from: PG */
final class C0450ql {

    /* renamed from: A */
    public C0317ln f15624A;

    /* renamed from: B */
    public CharSequence f15625B;

    /* renamed from: C */
    public CharSequence f15626C;

    /* renamed from: D */
    public ColorStateList f15627D = null;

    /* renamed from: E */
    public PorterDuff.Mode f15628E = null;

    /* renamed from: F */
    public final /* synthetic */ C0451qm f15629F;

    /* renamed from: a */
    public final Menu f15630a;

    /* renamed from: b */
    public int f15631b;

    /* renamed from: c */
    public int f15632c;

    /* renamed from: d */
    public int f15633d;

    /* renamed from: e */
    public int f15634e;

    /* renamed from: f */
    public boolean f15635f;

    /* renamed from: g */
    public boolean f15636g;

    /* renamed from: h */
    public boolean f15637h;

    /* renamed from: i */
    public int f15638i;

    /* renamed from: j */
    public int f15639j;

    /* renamed from: k */
    public CharSequence f15640k;

    /* renamed from: l */
    public CharSequence f15641l;

    /* renamed from: m */
    public int f15642m;

    /* renamed from: n */
    public char f15643n;

    /* renamed from: o */
    public int f15644o;

    /* renamed from: p */
    public char f15645p;

    /* renamed from: q */
    public int f15646q;

    /* renamed from: r */
    public int f15647r;

    /* renamed from: s */
    public boolean f15648s;

    /* renamed from: t */
    public boolean f15649t;

    /* renamed from: u */
    public boolean f15650u;

    /* renamed from: v */
    public int f15651v;

    /* renamed from: w */
    public int f15652w;

    /* renamed from: x */
    public String f15653x;

    /* renamed from: y */
    public String f15654y;

    /* renamed from: z */
    public String f15655z;

    public C0450ql(C0451qm qmVar, Menu menu) {
        this.f15629F = qmVar;
        this.f15630a = menu;
        mo9722a();
    }

    /* renamed from: b */
    public final SubMenu mo9724b() {
        this.f15637h = true;
        SubMenu addSubMenu = this.f15630a.addSubMenu(this.f15631b, this.f15638i, this.f15639j, this.f15640k);
        mo9723a(addSubMenu.getItem());
        return addSubMenu;
    }

    /* renamed from: a */
    public static final char m15156a(String str) {
        if (str != null) {
            return str.charAt(0);
        }
        return 0;
    }

    /* renamed from: a */
    public final Object mo9721a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.f15629F.f15659c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
            return null;
        }
    }

    /* renamed from: a */
    public final void mo9722a() {
        this.f15631b = 0;
        this.f15632c = 0;
        this.f15633d = 0;
        this.f15634e = 0;
        this.f15635f = true;
        this.f15636g = true;
    }

    /* renamed from: a */
    public final void mo9723a(MenuItem menuItem) {
        boolean z = false;
        menuItem.setChecked(this.f15648s).setVisible(this.f15649t).setEnabled(this.f15650u).setCheckable(this.f15647r > 0).setTitleCondensed(this.f15641l).setIcon(this.f15642m);
        int i = this.f15651v;
        if (i >= 0) {
            menuItem.setShowAsAction(i);
        }
        if (this.f15655z != null) {
            if (!this.f15629F.f15659c.isRestricted()) {
                C0451qm qmVar = this.f15629F;
                if (qmVar.f15660d == null) {
                    qmVar.f15660d = qmVar.mo9725a(qmVar.f15659c);
                }
                menuItem.setOnMenuItemClickListener(new C0449qk(qmVar.f15660d, this.f15655z));
            } else {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
        }
        boolean z2 = menuItem instanceof C0475rj;
        if (z2) {
            C0475rj rjVar = (C0475rj) menuItem;
        }
        if (this.f15647r >= 2) {
            if (z2) {
                ((C0475rj) menuItem).mo9887a(true);
            } else if (menuItem instanceof C0481rp) {
                C0481rp rpVar = (C0481rp) menuItem;
                try {
                    if (rpVar.f15815d == null) {
                        rpVar.f15815d = rpVar.f15814c.getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
                    }
                    rpVar.f15815d.invoke(rpVar.f15814c, new Object[]{true});
                } catch (Exception e) {
                    Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
                }
            }
        }
        String str = this.f15653x;
        if (str != null) {
            menuItem.setActionView((View) mo9721a(str, C0451qm.f15656a, this.f15629F.f15658b));
            z = true;
        }
        int i2 = this.f15652w;
        if (i2 > 0) {
            if (z) {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            } else {
                menuItem.setActionView(i2);
            }
        }
        C0317ln lnVar = this.f15624A;
        if (lnVar != null) {
            if (!(menuItem instanceof C0255jf)) {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            } else {
                ((C0255jf) menuItem).mo9139a(lnVar);
            }
        }
        CharSequence charSequence = this.f15625B;
        boolean z3 = menuItem instanceof C0255jf;
        if (z3) {
            ((C0255jf) menuItem).mo9138a(charSequence);
        } else {
            int i3 = Build.VERSION.SDK_INT;
            menuItem.setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f15626C;
        if (!z3) {
            int i4 = Build.VERSION.SDK_INT;
            menuItem.setTooltipText(charSequence2);
        } else {
            ((C0255jf) menuItem).mo9140b(charSequence2);
        }
        char c = this.f15643n;
        int i5 = this.f15644o;
        if (!z3) {
            int i6 = Build.VERSION.SDK_INT;
            menuItem.setAlphabeticShortcut(c, i5);
        } else {
            ((C0255jf) menuItem).setAlphabeticShortcut(c, i5);
        }
        char c2 = this.f15645p;
        int i7 = this.f15646q;
        if (!z3) {
            int i8 = Build.VERSION.SDK_INT;
            menuItem.setNumericShortcut(c2, i7);
        } else {
            ((C0255jf) menuItem).setNumericShortcut(c2, i7);
        }
        PorterDuff.Mode mode = this.f15628E;
        if (mode != null) {
            if (!z3) {
                int i9 = Build.VERSION.SDK_INT;
                menuItem.setIconTintMode(mode);
            } else {
                ((C0255jf) menuItem).setIconTintMode(mode);
            }
        }
        ColorStateList colorStateList = this.f15627D;
        if (colorStateList == null) {
            return;
        }
        if (z3) {
            ((C0255jf) menuItem).setIconTintList(colorStateList);
            return;
        }
        int i10 = Build.VERSION.SDK_INT;
        menuItem.setIconTintList(colorStateList);
    }
}
