package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.content.ContextCompat;
import androidx.core.internal.p023a.C0328a;
import androidx.core.view.ActionProvider;
import androidx.core.view.ViewConfigurationCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: androidx.appcompat.view.menu.q */
public class C0238q implements C0328a {

    /* renamed from: dn */
    private static final int[] f258dn = {1, 4, 5, 3, 2, 0};

    /* renamed from: Lm */
    private boolean f259Lm;

    /* renamed from: Mm */
    private boolean f260Mm;

    /* renamed from: Nm */
    private ArrayList f261Nm;

    /* renamed from: Om */
    private boolean f262Om;

    /* renamed from: Pm */
    private ArrayList f263Pm;

    /* renamed from: Qm */
    private ArrayList f264Qm;

    /* renamed from: Rm */
    private boolean f265Rm;

    /* renamed from: Sm */
    private int f266Sm = 0;

    /* renamed from: Tm */
    private ContextMenu.ContextMenuInfo f267Tm;

    /* renamed from: Um */
    private boolean f268Um = false;

    /* renamed from: Vm */
    private boolean f269Vm = false;

    /* renamed from: Wm */
    private boolean f270Wm = false;

    /* renamed from: Xm */
    private boolean f271Xm = false;

    /* renamed from: Ym */
    private boolean f272Ym = false;

    /* renamed from: Zm */
    private ArrayList f273Zm = new ArrayList();

    /* renamed from: _m */
    private CopyOnWriteArrayList f274_m = new CopyOnWriteArrayList();

    /* renamed from: an */
    private C0241t f275an;

    /* renamed from: bn */
    private boolean f276bn = false;

    /* renamed from: cn */
    private boolean f277cn;
    private C0236o mCallback;
    private final Context mContext;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private ArrayList mItems;
    private final Resources mResources;

    public C0238q(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mItems = new ArrayList();
        this.f261Nm = new ArrayList();
        boolean z = true;
        this.f262Om = true;
        this.f263Pm = new ArrayList();
        this.f264Qm = new ArrayList();
        this.f265Rm = true;
        this.f260Mm = (this.mResources.getConfiguration().keyboard == 1 || !ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(this.mContext), this.mContext)) ? false : z;
    }

    /* renamed from: e */
    private void m220e(int i, boolean z) {
        if (i >= 0 && i < this.mItems.size()) {
            this.mItems.remove(i);
            if (z) {
                onItemsChanged(true);
            }
        }
    }

    /* renamed from: a */
    public void mo1581a(C0212E e) {
        mo1582a(e, this.mContext);
    }

    public MenuItem add(CharSequence charSequence) {
        return mo1580a(0, 0, 0, charSequence);
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            int size2 = size();
            int i6 = 0;
            while (true) {
                if (i6 >= size2) {
                    i6 = -1;
                    break;
                } else if (((C0241t) this.mItems.get(i6)).getGroupId() == i) {
                    break;
                } else {
                    i6++;
                }
            }
            if (i6 >= 0) {
                int size3 = this.mItems.size() - i6;
                int i7 = 0;
                while (true) {
                    int i8 = i7 + 1;
                    if (i7 >= size3 || ((C0241t) this.mItems.get(i6)).getGroupId() != i) {
                        onItemsChanged(true);
                    } else {
                        m220e(i6, false);
                        i7 = i8;
                    }
                }
                onItemsChanged(true);
            }
        }
        for (int i9 = 0; i9 < size; i9++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i9);
            int i10 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i10 < 0 ? intent : intentArr[i10]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = mo1580a(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    /* renamed from: b */
    public void mo1593b(C0212E e) {
        Iterator it = this.f274_m.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            C0212E e2 = (C0212E) weakReference.get();
            if (e2 == null || e2 == e) {
                this.f274_m.remove(weakReference);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo1594c(C0241t tVar) {
        this.f265Rm = true;
        onItemsChanged(true);
    }

    public void changeMenuMode() {
        C0236o oVar = this.mCallback;
        if (oVar != null) {
            oVar.onMenuModeChange(this);
        }
    }

    public void clear() {
        C0241t tVar = this.f275an;
        if (tVar != null) {
            mo1452a(tVar);
        }
        this.mItems.clear();
        onItemsChanged(true);
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        onItemsChanged(false);
    }

    public final void close(boolean z) {
        if (!this.f272Ym) {
            this.f272Ym = true;
            Iterator it = this.f274_m.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0212E e = (C0212E) weakReference.get();
                if (e == null) {
                    this.f274_m.remove(weakReference);
                } else {
                    e.onCloseMenu(this, z);
                }
            }
            this.f272Ym = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1600d(C0241t tVar) {
        this.f262Om = true;
        onItemsChanged(true);
    }

    public MenuItem findItem(int i) {
        MenuItem findItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            C0241t tVar = (C0241t) this.mItems.get(i2);
            if (tVar.getItemId() == i) {
                return tVar;
            }
            if (tVar.hasSubMenu() && (findItem = tVar.getSubMenu().findItem(i)) != null) {
                return findItem;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public C0241t findItemWithShortcutForKey(int i, KeyEvent keyEvent) {
        char c;
        ArrayList arrayList = this.f273Zm;
        arrayList.clear();
        findItemsWithShortcutForKey(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (C0241t) arrayList.get(0);
        }
        boolean isQwertyMode = isQwertyMode();
        for (int i2 = 0; i2 < size; i2++) {
            C0241t tVar = (C0241t) arrayList.get(i2);
            if (isQwertyMode) {
                c = tVar.getAlphabeticShortcut();
            } else {
                c = tVar.getNumericShortcut();
            }
            if ((c == keyData.meta[0] && (metaState & 2) == 0) || ((c == keyData.meta[2] && (metaState & 2) != 0) || (isQwertyMode && c == 8 && i == 67))) {
                return tVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void findItemsWithShortcutForKey(List list, int i, KeyEvent keyEvent) {
        boolean isQwertyMode = isQwertyMode();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.mItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0241t tVar = (C0241t) this.mItems.get(i2);
                if (tVar.hasSubMenu()) {
                    ((C0238q) tVar.getSubMenu()).findItemsWithShortcutForKey(list, i, keyEvent);
                }
                char alphabeticShortcut = isQwertyMode ? tVar.getAlphabeticShortcut() : tVar.getNumericShortcut();
                if (((modifiers & 69647) == ((isQwertyMode ? tVar.getAlphabeticModifiers() : tVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (isQwertyMode && alphabeticShortcut == 8 && i == 67)) && tVar.isEnabled()) {
                        list.add(tVar);
                    }
                }
            }
        }
    }

    public void flagActionItems() {
        ArrayList visibleItems = getVisibleItems();
        if (this.f265Rm) {
            Iterator it = this.f274_m.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0212E e = (C0212E) weakReference.get();
                if (e == null) {
                    this.f274_m.remove(weakReference);
                } else {
                    z |= e.flagActionItems();
                }
            }
            if (z) {
                this.f263Pm.clear();
                this.f264Qm.clear();
                int size = visibleItems.size();
                for (int i = 0; i < size; i++) {
                    C0241t tVar = (C0241t) visibleItems.get(i);
                    if (tVar.isActionButton()) {
                        this.f263Pm.add(tVar);
                    } else {
                        this.f264Qm.add(tVar);
                    }
                }
            } else {
                this.f263Pm.clear();
                this.f264Qm.clear();
                this.f264Qm.addAll(getVisibleItems());
            }
            this.f265Rm = false;
        }
    }

    public ArrayList getActionItems() {
        flagActionItems();
        return this.f263Pm;
    }

    /* access modifiers changed from: protected */
    public String getActionViewStatesKey() {
        return "android:menu:actionviewstates";
    }

    public Context getContext() {
        return this.mContext;
    }

    public C0241t getExpandedItem() {
        return this.f275an;
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.mItems.get(i);
    }

    public ArrayList getNonActionItems() {
        flagActionItems();
        return this.f264Qm;
    }

    /* access modifiers changed from: package-private */
    public boolean getOptionalIconsVisible() {
        return this.f271Xm;
    }

    public C0238q getRootMenu() {
        return this;
    }

    public ArrayList getVisibleItems() {
        if (!this.f262Om) {
            return this.f261Nm;
        }
        this.f261Nm.clear();
        int size = this.mItems.size();
        for (int i = 0; i < size; i++) {
            C0241t tVar = (C0241t) this.mItems.get(i);
            if (tVar.isVisible()) {
                this.f261Nm.add(tVar);
            }
        }
        this.f262Om = false;
        this.f265Rm = true;
        return this.f261Nm;
    }

    public boolean hasVisibleItems() {
        if (this.f277cn) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((C0241t) this.mItems.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public boolean isGroupDividerEnabled() {
        return this.f276bn;
    }

    /* access modifiers changed from: package-private */
    public boolean isQwertyMode() {
        return this.f259Lm;
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return findItemWithShortcutForKey(i, keyEvent) != null;
    }

    public boolean isShortcutsVisible() {
        return this.f260Mm;
    }

    public void onItemsChanged(boolean z) {
        if (!this.f268Um) {
            if (z) {
                this.f262Om = true;
                this.f265Rm = true;
            }
            if (!this.f274_m.isEmpty()) {
                stopDispatchingItemsChanged();
                Iterator it = this.f274_m.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    C0212E e = (C0212E) weakReference.get();
                    if (e == null) {
                        this.f274_m.remove(weakReference);
                    } else {
                        e.updateMenuView(z);
                    }
                }
                startDispatchingItemsChanged();
                return;
            }
            return;
        }
        this.f269Vm = true;
        if (z) {
            this.f270Wm = true;
        }
    }

    public boolean performIdentifierAction(int i, int i2) {
        return performItemAction(findItem(i), i2);
    }

    public boolean performItemAction(MenuItem menuItem, int i) {
        return mo1583a(menuItem, (C0212E) null, i);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        C0241t findItemWithShortcutForKey = findItemWithShortcutForKey(i, keyEvent);
        boolean a = findItemWithShortcutForKey != null ? mo1583a(findItemWithShortcutForKey, (C0212E) null, i2) : false;
        if ((i2 & 2) != 0) {
            close(true);
        }
        return a;
    }

    public void removeGroup(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (((C0241t) this.mItems.get(i2)).getGroupId() == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= 0) {
            int size2 = this.mItems.size() - i2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size2 || ((C0241t) this.mItems.get(i2)).getGroupId() != i) {
                    onItemsChanged(true);
                } else {
                    m220e(i2, false);
                    i3 = i4;
                }
            }
            onItemsChanged(true);
        }
    }

    public void removeItem(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (((C0241t) this.mItems.get(i2)).getItemId() == i) {
                break;
            } else {
                i2++;
            }
        }
        m220e(i2, true);
    }

    public void restoreActionViewStates(Bundle bundle) {
        MenuItem findItem;
        if (bundle != null) {
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(getActionViewStatesKey());
            int size = size();
            for (int i = 0; i < size; i++) {
                MenuItem item = getItem(i);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((C0220M) item.getSubMenu()).restoreActionViewStates(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0 && (findItem = findItem(i2)) != null) {
                findItem.expandActionView();
            }
        }
    }

    public void restorePresenterStates(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null && !this.f274_m.isEmpty()) {
            Iterator it = this.f274_m.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0212E e = (C0212E) weakReference.get();
                if (e == null) {
                    this.f274_m.remove(weakReference);
                } else {
                    int id = e.getId();
                    if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                        e.onRestoreInstanceState(parcelable);
                    }
                }
            }
        }
    }

    public void saveActionViewStates(Bundle bundle) {
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
                ((C0220M) item.getSubMenu()).saveActionViewStates(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(getActionViewStatesKey(), sparseArray);
        }
    }

    public void savePresenterStates(Bundle bundle) {
        Parcelable onSaveInstanceState;
        if (!this.f274_m.isEmpty()) {
            SparseArray sparseArray = new SparseArray();
            Iterator it = this.f274_m.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0212E e = (C0212E) weakReference.get();
                if (e == null) {
                    this.f274_m.remove(weakReference);
                } else {
                    int id = e.getId();
                    if (id > 0 && (onSaveInstanceState = e.onSaveInstanceState()) != null) {
                        sparseArray.put(id, onSaveInstanceState);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
        }
    }

    public C0238q setDefaultShowAsAction(int i) {
        this.f266Sm = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setExclusiveItemChecked(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.mItems.size();
        stopDispatchingItemsChanged();
        for (int i = 0; i < size; i++) {
            C0241t tVar = (C0241t) this.mItems.get(i);
            if (tVar.getGroupId() == groupId && tVar.isExclusiveCheckable() && tVar.isCheckable()) {
                tVar.setCheckedInt(tVar == menuItem);
            }
        }
        startDispatchingItemsChanged();
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.mItems.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0241t tVar = (C0241t) this.mItems.get(i2);
            if (tVar.getGroupId() == i) {
                tVar.setExclusiveCheckable(z2);
                tVar.setCheckable(z);
            }
        }
    }

    public void setGroupDividerEnabled(boolean z) {
        this.f276bn = z;
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.mItems.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0241t tVar = (C0241t) this.mItems.get(i2);
            if (tVar.getGroupId() == i) {
                tVar.setEnabled(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.mItems.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            C0241t tVar = (C0241t) this.mItems.get(i2);
            if (tVar.getGroupId() == i && tVar.setVisibleInt(z)) {
                z2 = true;
            }
        }
        if (z2) {
            onItemsChanged(true);
        }
    }

    /* access modifiers changed from: protected */
    public C0238q setHeaderIconInt(Drawable drawable) {
        m219a(0, (CharSequence) null, 0, drawable, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public C0238q setHeaderTitleInt(CharSequence charSequence) {
        m219a(0, charSequence, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public C0238q setHeaderViewInt(View view) {
        m219a(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public void setQwertyMode(boolean z) {
        this.f259Lm = z;
        onItemsChanged(false);
    }

    public int size() {
        return this.mItems.size();
    }

    public void startDispatchingItemsChanged() {
        this.f268Um = false;
        if (this.f269Vm) {
            this.f269Vm = false;
            onItemsChanged(this.f270Wm);
        }
    }

    public void stopDispatchingItemsChanged() {
        if (!this.f268Um) {
            this.f268Um = true;
            this.f269Vm = false;
            this.f270Wm = false;
        }
    }

    /* renamed from: z */
    public void mo1637z(boolean z) {
        this.f277cn = z;
    }

    /* renamed from: a */
    public void mo1582a(C0212E e, Context context) {
        this.f274_m.add(new WeakReference(e));
        e.initForMenu(context, this);
        this.f265Rm = true;
    }

    public MenuItem add(int i) {
        return mo1580a(0, 0, 0, this.mResources.getString(i));
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, (CharSequence) this.mResources.getString(i));
    }

    /* access modifiers changed from: protected */
    public C0238q setHeaderIconInt(int i) {
        m219a(0, (CharSequence) null, i, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public C0238q setHeaderTitleInt(int i) {
        m219a(i, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return mo1580a(i, i2, i3, charSequence);
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        C0241t tVar = (C0241t) mo1580a(i, i2, i3, charSequence);
        C0220M m = new C0220M(this.mContext, this, tVar);
        tVar.mo1644a(m);
        return m;
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return mo1580a(i, i2, i3, this.mResources.getString(i4));
    }

    /* renamed from: b */
    public boolean mo1453b(C0241t tVar) {
        boolean z = false;
        if (this.f274_m.isEmpty()) {
            return false;
        }
        stopDispatchingItemsChanged();
        Iterator it = this.f274_m.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            C0212E e = (C0212E) weakReference.get();
            if (e == null) {
                this.f274_m.remove(weakReference);
            } else {
                z = e.expandItemActionView(this, tVar);
                if (z) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (z) {
            this.f275an = tVar;
        }
        return z;
    }

    /* renamed from: a */
    public void mo1450a(C0236o oVar) {
        this.mCallback = oVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public MenuItem mo1580a(int i, int i2, int i3, CharSequence charSequence) {
        int i4;
        int i5 = (-65536 & i3) >> 16;
        if (i5 >= 0) {
            int[] iArr = f258dn;
            if (i5 < iArr.length) {
                int i6 = (iArr[i5] << 16) | (65535 & i3);
                C0241t tVar = new C0241t(this, i, i2, i3, i6, charSequence, this.f266Sm);
                ContextMenu.ContextMenuInfo contextMenuInfo = this.f267Tm;
                if (contextMenuInfo != null) {
                    tVar.setMenuInfo(contextMenuInfo);
                }
                ArrayList arrayList = this.mItems;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        if (((C0241t) arrayList.get(size)).getOrdering() <= i6) {
                            i4 = size + 1;
                            break;
                        }
                    } else {
                        i4 = 0;
                        break;
                    }
                }
                arrayList.add(i4, tVar);
                onItemsChanged(true);
                return tVar;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, (CharSequence) this.mResources.getString(i4));
    }

    public void close() {
        close(true);
    }

    /* renamed from: a */
    private void m219a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resources = this.mResources;
        if (view != null) {
            this.mHeaderView = view;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        } else {
            if (i > 0) {
                this.mHeaderTitle = resources.getText(i);
            } else if (charSequence != null) {
                this.mHeaderTitle = charSequence;
            }
            if (i2 > 0) {
                this.mHeaderIcon = ContextCompat.getDrawable(this.mContext, i2);
            } else if (drawable != null) {
                this.mHeaderIcon = drawable;
            }
            this.mHeaderView = null;
        }
        onItemsChanged(false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1451a(C0238q qVar, MenuItem menuItem) {
        C0236o oVar = this.mCallback;
        return oVar != null && oVar.onMenuItemSelected(qVar, menuItem);
    }

    /* renamed from: a */
    public boolean mo1583a(MenuItem menuItem, C0212E e, int i) {
        C0241t tVar = (C0241t) menuItem;
        boolean z = false;
        if (tVar == null || !tVar.isEnabled()) {
            return false;
        }
        boolean invoke = tVar.invoke();
        ActionProvider L = tVar.mo1479L();
        boolean z2 = L != null && L.hasSubMenu();
        if (tVar.hasCollapsibleActionView()) {
            invoke |= tVar.expandActionView();
            if (invoke) {
                close(true);
            }
        } else if (tVar.hasSubMenu() || z2) {
            if ((i & 4) == 0) {
                close(false);
            }
            if (!tVar.hasSubMenu()) {
                tVar.mo1644a(new C0220M(this.mContext, this, tVar));
            }
            C0220M m = (C0220M) tVar.getSubMenu();
            if (z2) {
                L.onPrepareSubMenu(m);
            }
            if (!this.f274_m.isEmpty()) {
                if (e != null) {
                    z = e.onSubMenuSelected(m);
                }
                Iterator it = this.f274_m.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    C0212E e2 = (C0212E) weakReference.get();
                    if (e2 == null) {
                        this.f274_m.remove(weakReference);
                    } else if (!z) {
                        z = e2.onSubMenuSelected(m);
                    }
                }
            }
            invoke |= z;
            if (!invoke) {
                close(true);
            }
        } else if ((i & 1) == 0) {
            close(true);
        }
        return invoke;
    }

    /* renamed from: a */
    public boolean mo1452a(C0241t tVar) {
        boolean z = false;
        if (!this.f274_m.isEmpty() && this.f275an == tVar) {
            stopDispatchingItemsChanged();
            Iterator it = this.f274_m.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                C0212E e = (C0212E) weakReference.get();
                if (e == null) {
                    this.f274_m.remove(weakReference);
                } else {
                    z = e.collapseItemActionView(this, tVar);
                    if (z) {
                        break;
                    }
                }
            }
            startDispatchingItemsChanged();
            if (z) {
                this.f275an = null;
            }
        }
        return z;
    }
}
