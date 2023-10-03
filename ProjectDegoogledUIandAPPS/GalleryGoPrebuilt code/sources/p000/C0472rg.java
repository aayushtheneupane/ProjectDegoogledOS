package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: rg */
/* compiled from: PG */
public class C0472rg implements C0254je {

    /* renamed from: j */
    private static final int[] f15748j = {1, 4, 5, 3, 2, 0};

    /* renamed from: a */
    public final Context f15749a;

    /* renamed from: b */
    public C0470re f15750b;

    /* renamed from: c */
    public final ArrayList f15751c;

    /* renamed from: d */
    public final ArrayList f15752d;

    /* renamed from: e */
    public CharSequence f15753e;

    /* renamed from: f */
    public Drawable f15754f;

    /* renamed from: g */
    public View f15755g;

    /* renamed from: h */
    public C0475rj f15756h;

    /* renamed from: i */
    public boolean f15757i;

    /* renamed from: k */
    private final Resources f15758k;

    /* renamed from: l */
    private boolean f15759l;

    /* renamed from: m */
    private final boolean f15760m;

    /* renamed from: n */
    private final ArrayList f15761n;

    /* renamed from: o */
    private boolean f15762o;

    /* renamed from: p */
    private final ArrayList f15763p;

    /* renamed from: q */
    private boolean f15764q;

    /* renamed from: r */
    private int f15765r = 0;

    /* renamed from: s */
    private boolean f15766s = false;

    /* renamed from: t */
    private boolean f15767t = false;

    /* renamed from: u */
    private boolean f15768u = false;

    /* renamed from: v */
    private boolean f15769v = false;

    /* renamed from: w */
    private final ArrayList f15770w = new ArrayList();

    /* renamed from: x */
    private final CopyOnWriteArrayList f15771x = new CopyOnWriteArrayList();

    /* renamed from: y */
    private boolean f15772y = false;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo9829a() {
        return "android:menu:actionviewstates";
    }

    /* renamed from: b */
    public boolean mo9852b() {
        return this.f15772y;
    }

    /* renamed from: c */
    public boolean mo9854c() {
        return this.f15759l;
    }

    /* renamed from: d */
    public boolean mo9858d() {
        return this.f15760m;
    }

    /* renamed from: j */
    public C0472rg mo9868j() {
        return this;
    }

    public C0472rg(Context context) {
        boolean z = false;
        this.f15749a = context;
        this.f15758k = context.getResources();
        this.f15751c = new ArrayList();
        this.f15761n = new ArrayList();
        this.f15762o = true;
        this.f15752d = new ArrayList();
        this.f15763p = new ArrayList();
        this.f15764q = true;
        if (this.f15758k.getConfiguration().keyboard != 1 && C0341mk.m14737a(ViewConfiguration.get(this.f15749a), this.f15749a)) {
            z = true;
        }
        this.f15760m = z;
    }

    public final MenuItem add(int i) {
        return m15232a(0, 0, 0, this.f15758k.getString(i));
    }

    public final MenuItem add(int i, int i2, int i3, int i4) {
        return m15232a(i, i2, i3, this.f15758k.getString(i4));
    }

    public final MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return m15232a(i, i2, i3, charSequence);
    }

    public final MenuItem add(CharSequence charSequence) {
        return m15232a(0, 0, 0, charSequence);
    }

    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.f15749a.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i5 = 0; i5 < size; i5++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i5);
            Intent intent2 = new Intent(resolveInfo.specificIndex >= 0 ? intentArr[resolveInfo.specificIndex] : intent);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem a = m15232a(i, i2, i3, resolveInfo.loadLabel(packageManager));
            a.setIcon(resolveInfo.loadIcon(packageManager));
            ((C0475rj) a).f15784e = intent2;
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = a;
            }
        }
        return size;
    }

    /* renamed from: a */
    private final MenuItem m15232a(int i, int i2, int i3, CharSequence charSequence) {
        int i4;
        int i5 = i3 >> 16;
        if (i5 >= 0) {
            int[] iArr = f15748j;
            if (i5 < iArr.length) {
                char c = (iArr[i5] << 16) | ((char) i3);
                C0475rj rjVar = new C0475rj(this, i, i2, i3, c, charSequence, this.f15765r);
                ArrayList arrayList = this.f15751c;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        i4 = 0;
                        break;
                    } else if (((C0475rj) arrayList.get(size)).f15782c <= c) {
                        i4 = size + 1;
                        break;
                    }
                }
                arrayList.add(i4, rjVar);
                mo9851b(true);
                return rjVar;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    /* renamed from: a */
    public final void mo9833a(C0486ru ruVar) {
        mo9834a(ruVar, this.f15749a);
    }

    /* renamed from: a */
    public final void mo9834a(C0486ru ruVar, Context context) {
        this.f15771x.add(new WeakReference(ruVar));
        ruVar.mo9785a(context, this);
        this.f15764q = true;
    }

    public final SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, (CharSequence) this.f15758k.getString(i));
    }

    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, (CharSequence) this.f15758k.getString(i4));
    }

    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        C0475rj rjVar = (C0475rj) m15232a(i, i2, i3, charSequence);
        C0495sc scVar = new C0495sc(this.f15749a, this, rjVar);
        rjVar.mo9886a(scVar);
        return scVar;
    }

    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void clear() {
        C0475rj rjVar = this.f15756h;
        if (rjVar != null) {
            mo9853b(rjVar);
        }
        this.f15751c.clear();
        mo9851b(true);
    }

    public final void clearHeader() {
        this.f15754f = null;
        this.f15753e = null;
        this.f15755g = null;
        mo9851b(false);
    }

    public final void close() {
        mo9835a(true);
    }

    /* renamed from: a */
    public final void mo9835a(boolean z) {
        if (!this.f15769v) {
            this.f15769v = true;
            Iterator it = this.f15771x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0486ru ruVar = (C0486ru) weakReference.get();
                if (ruVar == null) {
                    this.f15771x.remove(weakReference);
                } else {
                    ruVar.mo9786a(this, z);
                }
            }
            this.f15769v = false;
        }
    }

    /* renamed from: b */
    public boolean mo9853b(C0475rj rjVar) {
        boolean z = false;
        if (!this.f15771x.isEmpty() && this.f15756h == rjVar) {
            mo9859e();
            Iterator it = this.f15771x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0486ru ruVar = (C0486ru) weakReference.get();
                if (ruVar == null) {
                    this.f15771x.remove(weakReference);
                } else {
                    z = ruVar.mo9789a(rjVar);
                    if (z) {
                        break;
                    }
                }
            }
            mo9860f();
            if (z) {
                this.f15756h = null;
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean mo9838a(C0472rg rgVar, MenuItem menuItem) {
        C0470re reVar = this.f15750b;
        return reVar != null && reVar.mo9607a(rgVar, menuItem);
    }

    /* renamed from: a */
    public boolean mo9839a(C0475rj rjVar) {
        boolean z = false;
        if (!this.f15771x.isEmpty()) {
            mo9859e();
            Iterator it = this.f15771x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0486ru ruVar = (C0486ru) weakReference.get();
                if (ruVar == null) {
                    this.f15771x.remove(weakReference);
                } else {
                    z = ruVar.mo9792b(rjVar);
                    if (z) {
                        break;
                    }
                }
            }
            mo9860f();
            if (z) {
                this.f15756h = rjVar;
            }
        }
        return z;
    }

    public final MenuItem findItem(int i) {
        MenuItem findItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            C0475rj rjVar = (C0475rj) this.f15751c.get(i2);
            if (rjVar.f15780a == i) {
                return rjVar;
            }
            if (rjVar.hasSubMenu() && (findItem = rjVar.f15790k.findItem(i)) != null) {
                return findItem;
            }
        }
        return null;
    }

    /* renamed from: a */
    private final C0475rj m15233a(int i, KeyEvent keyEvent) {
        char c;
        ArrayList arrayList = this.f15770w;
        arrayList.clear();
        m15235a((List) arrayList, i, keyEvent);
        if (!arrayList.isEmpty()) {
            int metaState = keyEvent.getMetaState();
            KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
            keyEvent.getKeyData(keyData);
            int size = arrayList.size();
            if (size == 1) {
                return (C0475rj) arrayList.get(0);
            }
            boolean c2 = mo9854c();
            for (int i2 = 0; i2 < size; i2++) {
                C0475rj rjVar = (C0475rj) arrayList.get(i2);
                if (!c2) {
                    c = rjVar.f15785f;
                } else {
                    c = rjVar.f15787h;
                }
                if ((c == keyData.meta[0] && (metaState & 2) == 0) || ((c == keyData.meta[2] && (metaState & 2) != 0) || (c2 && c == 8 && i == 67))) {
                    return rjVar;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private final void m15235a(List list, int i, KeyEvent keyEvent) {
        char c;
        int i2;
        boolean c2 = mo9854c();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f15751c.size();
            for (int i3 = 0; i3 < size; i3++) {
                C0475rj rjVar = (C0475rj) this.f15751c.get(i3);
                if (rjVar.hasSubMenu()) {
                    rjVar.f15790k.m15235a(list, i, keyEvent);
                }
                if (!c2) {
                    c = rjVar.f15785f;
                } else {
                    c = rjVar.f15787h;
                }
                if (!c2) {
                    i2 = rjVar.f15786g;
                } else {
                    i2 = rjVar.f15788i;
                }
                if ((modifiers & 69647) == (i2 & 69647) && c != 0 && ((c == keyData.meta[0] || c == keyData.meta[2] || (c2 && c == 8 && i == 67)) && rjVar.isEnabled())) {
                    list.add(rjVar);
                }
            }
        }
    }

    /* renamed from: h */
    public final void mo9864h() {
        ArrayList g = mo9862g();
        if (this.f15764q) {
            Iterator it = this.f15771x.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0486ru ruVar = (C0486ru) weakReference.get();
                if (ruVar == null) {
                    this.f15771x.remove(weakReference);
                } else {
                    z |= ruVar.mo9788a();
                }
            }
            if (!z) {
                this.f15752d.clear();
                this.f15763p.clear();
                this.f15763p.addAll(mo9862g());
            } else {
                this.f15752d.clear();
                this.f15763p.clear();
                int size = g.size();
                for (int i = 0; i < size; i++) {
                    C0475rj rjVar = (C0475rj) g.get(i);
                    if (rjVar.mo9895f()) {
                        this.f15752d.add(rjVar);
                    } else {
                        this.f15763p.add(rjVar);
                    }
                }
            }
            this.f15764q = false;
        }
    }

    public final MenuItem getItem(int i) {
        return (MenuItem) this.f15751c.get(i);
    }

    /* renamed from: i */
    public final ArrayList mo9866i() {
        mo9864h();
        return this.f15763p;
    }

    /* renamed from: g */
    public final ArrayList mo9862g() {
        if (this.f15762o) {
            this.f15761n.clear();
            int size = this.f15751c.size();
            for (int i = 0; i < size; i++) {
                C0475rj rjVar = (C0475rj) this.f15751c.get(i);
                if (rjVar.isVisible()) {
                    this.f15761n.add(rjVar);
                }
            }
            this.f15762o = false;
            this.f15764q = true;
        }
        return this.f15761n;
    }

    public final boolean hasVisibleItems() {
        if (this.f15757i) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((C0475rj) this.f15751c.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return m15233a(i, keyEvent) != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public final void mo9869k() {
        this.f15764q = true;
        mo9851b(true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public final void mo9870l() {
        this.f15762o = true;
        mo9851b(true);
    }

    /* renamed from: b */
    public final void mo9851b(boolean z) {
        if (this.f15766s) {
            this.f15767t = true;
            if (z) {
                this.f15768u = true;
                return;
            }
            return;
        }
        if (z) {
            this.f15762o = true;
            this.f15764q = true;
        }
        if (!this.f15771x.isEmpty()) {
            mo9859e();
            Iterator it = this.f15771x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0486ru ruVar = (C0486ru) weakReference.get();
                if (ruVar == null) {
                    this.f15771x.remove(weakReference);
                } else {
                    ruVar.mo9791b();
                }
            }
            mo9860f();
        }
    }

    public final boolean performIdentifierAction(int i, int i2) {
        return mo9836a(findItem(i), i2);
    }

    /* renamed from: a */
    public final boolean mo9836a(MenuItem menuItem, int i) {
        return mo9837a(menuItem, (C0486ru) null, i);
    }

    /* renamed from: a */
    public final boolean mo9837a(MenuItem menuItem, C0486ru ruVar, int i) {
        boolean z;
        C0475rj rjVar = (C0475rj) menuItem;
        if (rjVar == null || !rjVar.isEnabled()) {
            return false;
        }
        boolean b = rjVar.mo9888b();
        C0317ln lnVar = rjVar.f15794o;
        boolean z2 = lnVar != null && lnVar.mo9369e();
        if (rjVar.mo9911i()) {
            b |= rjVar.expandActionView();
            if (b) {
                mo9835a(true);
                return true;
            }
        } else if (rjVar.hasSubMenu() || z2) {
            if ((i & 4) == 0) {
                mo9835a(false);
            }
            if (!rjVar.hasSubMenu()) {
                rjVar.mo9886a(new C0495sc(this.f15749a, this, rjVar));
            }
            C0495sc scVar = rjVar.f15790k;
            if (z2) {
                lnVar.mo9364a((SubMenu) scVar);
            }
            if (this.f15771x.isEmpty()) {
                z = false;
            } else {
                z = ruVar != null ? ruVar.mo9790a(scVar) : false;
                Iterator it = this.f15771x.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    C0486ru ruVar2 = (C0486ru) weakReference.get();
                    if (ruVar2 == null) {
                        this.f15771x.remove(weakReference);
                    } else if (!z) {
                        z = ruVar2.mo9790a(scVar);
                    }
                }
            }
            b |= z;
            if (!b) {
                mo9835a(true);
                return false;
            }
        } else if ((i & 1) == 0) {
            mo9835a(true);
            return b;
        }
        return b;
    }

    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        boolean z;
        C0475rj a = m15233a(i, keyEvent);
        if (a != null) {
            z = mo9836a((MenuItem) a, i2);
        } else {
            z = false;
        }
        if ((i2 & 2) != 0) {
            mo9835a(true);
        }
        return z;
    }

    public final void removeGroup(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                if (((C0475rj) this.f15751c.get(i2)).f15781b == i) {
                    break;
                }
                i2++;
            } else {
                i2 = -1;
                break;
            }
        }
        if (i2 >= 0) {
            int size2 = this.f15751c.size() - i2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size2 || ((C0475rj) this.f15751c.get(i2)).f15781b != i) {
                    mo9851b(true);
                } else {
                    m15234a(i2, false);
                    i3 = i4;
                }
            }
            mo9851b(true);
        }
    }

    public final void removeItem(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                if (((C0475rj) this.f15751c.get(i2)).f15780a == i) {
                    break;
                }
                i2++;
            } else {
                i2 = -1;
                break;
            }
        }
        m15234a(i2, true);
    }

    /* renamed from: a */
    private final void m15234a(int i, boolean z) {
        if (i >= 0 && i < this.f15751c.size()) {
            this.f15751c.remove(i);
            if (z) {
                mo9851b(true);
            }
        }
    }

    /* renamed from: b */
    public final void mo9850b(C0486ru ruVar) {
        Iterator it = this.f15771x.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            C0486ru ruVar2 = (C0486ru) weakReference.get();
            if (ruVar2 == null || ruVar2 == ruVar) {
                this.f15771x.remove(weakReference);
            }
        }
    }

    /* renamed from: b */
    public final void mo9849b(Bundle bundle) {
        MenuItem findItem;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(mo9829a());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((C0495sc) item.getSubMenu()).mo9849b(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 > 0 && (findItem = findItem(i2)) != null) {
            findItem.expandActionView();
        }
    }

    /* renamed from: a */
    public final void mo9831a(Bundle bundle) {
        int size = size();
        SparseArray sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((C0495sc) item.getSubMenu()).mo9831a(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(mo9829a(), sparseArray);
        }
    }

    /* renamed from: a */
    public void mo9832a(C0470re reVar) {
        this.f15750b = reVar;
    }

    /* renamed from: m */
    public final void mo9871m() {
        this.f15765r = 1;
    }

    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f15751c.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0475rj rjVar = (C0475rj) this.f15751c.get(i2);
            if (rjVar.f15781b == i) {
                rjVar.mo9887a(z2);
                rjVar.setCheckable(z);
            }
        }
    }

    public void setGroupDividerEnabled(boolean z) {
        this.f15772y = z;
    }

    public final void setGroupEnabled(int i, boolean z) {
        int size = this.f15751c.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0475rj rjVar = (C0475rj) this.f15751c.get(i2);
            if (rjVar.f15781b == i) {
                rjVar.setEnabled(z);
            }
        }
    }

    public final void setGroupVisible(int i, boolean z) {
        int size = this.f15751c.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            C0475rj rjVar = (C0475rj) this.f15751c.get(i2);
            if (rjVar.f15781b == i && rjVar.mo9889b(z)) {
                z2 = true;
            }
        }
        if (z2) {
            mo9851b(true);
        }
    }

    /* renamed from: a */
    public final void mo9830a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resources = this.f15758k;
        if (view != null) {
            this.f15755g = view;
            this.f15753e = null;
            this.f15754f = null;
        } else {
            if (i > 0) {
                this.f15753e = resources.getText(i);
            } else if (charSequence != null) {
                this.f15753e = charSequence;
            }
            if (i2 > 0) {
                this.f15754f = C0071co.m4660a(this.f15749a, i2);
            } else if (drawable != null) {
                this.f15754f = drawable;
            }
            this.f15755g = null;
        }
        mo9851b(false);
    }

    public void setQwertyMode(boolean z) {
        this.f15759l = z;
        mo9851b(false);
    }

    public final int size() {
        return this.f15751c.size();
    }

    /* renamed from: f */
    public final void mo9860f() {
        this.f15766s = false;
        if (this.f15767t) {
            this.f15767t = false;
            mo9851b(this.f15768u);
        }
    }

    /* renamed from: e */
    public final void mo9859e() {
        if (!this.f15766s) {
            this.f15766s = true;
            this.f15767t = false;
            this.f15768u = false;
        }
    }
}
