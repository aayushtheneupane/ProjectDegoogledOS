package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* renamed from: rx */
/* compiled from: PG */
public class C0489rx extends C0459qu implements Menu {

    /* renamed from: c */
    private final C0254je f15829c;

    public C0489rx(Context context, C0254je jeVar) {
        super(context);
        if (jeVar != null) {
            this.f15829c = jeVar;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public final MenuItem add(int i) {
        return mo9793a(this.f15829c.add(i));
    }

    public final MenuItem add(int i, int i2, int i3, int i4) {
        return mo9793a(this.f15829c.add(i, i2, i3, i4));
    }

    public final MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return mo9793a(this.f15829c.add(i, i2, i3, charSequence));
    }

    public final MenuItem add(CharSequence charSequence) {
        return mo9793a(this.f15829c.add(charSequence));
    }

    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr;
        MenuItem[] menuItemArr3 = menuItemArr2 != null ? new MenuItem[menuItemArr2.length] : null;
        int addIntentOptions = this.f15829c.addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr3);
        if (menuItemArr3 != null) {
            int length = menuItemArr3.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr2[i5] = mo9793a(menuItemArr3[i5]);
            }
        }
        return addIntentOptions;
    }

    public final SubMenu addSubMenu(int i) {
        return mo9794a(this.f15829c.addSubMenu(i));
    }

    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return mo9794a(this.f15829c.addSubMenu(i, i2, i3, i4));
    }

    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return mo9794a(this.f15829c.addSubMenu(i, i2, i3, charSequence));
    }

    public final SubMenu addSubMenu(CharSequence charSequence) {
        return mo9794a(this.f15829c.addSubMenu(charSequence));
    }

    public final void clear() {
        Map map = this.f15696a;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.f15697b;
        if (map2 != null) {
            map2.clear();
        }
        this.f15829c.clear();
    }

    public final void close() {
        this.f15829c.close();
    }

    public final MenuItem findItem(int i) {
        return mo9793a(this.f15829c.findItem(i));
    }

    public final MenuItem getItem(int i) {
        return mo9793a(this.f15829c.getItem(i));
    }

    public final boolean hasVisibleItems() {
        return this.f15829c.hasVisibleItems();
    }

    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return this.f15829c.isShortcutKey(i, keyEvent);
    }

    public final boolean performIdentifierAction(int i, int i2) {
        return this.f15829c.performIdentifierAction(i, i2);
    }

    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return this.f15829c.performShortcut(i, keyEvent, i2);
    }

    public final void removeGroup(int i) {
        Map map = this.f15696a;
        if (map != null) {
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getGroupId()) {
                    it.remove();
                }
            }
        }
        this.f15829c.removeGroup(i);
    }

    public final void removeItem(int i) {
        Map map = this.f15696a;
        if (map != null) {
            Iterator it = map.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (i == ((MenuItem) it.next()).getItemId()) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        this.f15829c.removeItem(i);
    }

    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        this.f15829c.setGroupCheckable(i, z, z2);
    }

    public final void setGroupEnabled(int i, boolean z) {
        this.f15829c.setGroupEnabled(i, z);
    }

    public final void setGroupVisible(int i, boolean z) {
        this.f15829c.setGroupVisible(i, z);
    }

    public final void setQwertyMode(boolean z) {
        this.f15829c.setQwertyMode(z);
    }

    public final int size() {
        return this.f15829c.size();
    }
}
