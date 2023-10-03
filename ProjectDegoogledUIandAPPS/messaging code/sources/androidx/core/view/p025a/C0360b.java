package androidx.core.view.p025a;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;

/* renamed from: androidx.core.view.a.b */
public class C0360b {

    /* renamed from: fo */
    private final Class f327fo;
    final Object mAction;
    private final int mId;

    static {
        new C0360b((Object) null, 1, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 2, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 4, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 8, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 16, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 32, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 64, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 128, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 256, (CharSequence) null, C0367i.class);
        new C0360b((Object) null, NotificationCompat.FLAG_GROUP_SUMMARY, (CharSequence) null, C0367i.class);
        new C0360b((Object) null, 1024, (CharSequence) null, C0368j.class);
        new C0360b((Object) null, 2048, (CharSequence) null, C0368j.class);
        new C0360b((Object) null, NotificationCompat.FLAG_BUBBLE, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 8192, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 16384, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 32768, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 65536, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 131072, (CharSequence) null, C0372n.class);
        new C0360b((Object) null, 262144, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 524288, (CharSequence) null, (Class) null);
        new C0360b((Object) null, 1048576, (CharSequence) null, (Class) null);
        new C0360b((Object) null, InputDeviceCompat.SOURCE_TOUCH_NAVIGATION, (CharSequence) null, C0373o.class);
        int i = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, 16908342, (CharSequence) null, (Class) null);
        int i2 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, 16908343, (CharSequence) null, C0370l.class);
        int i3 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, 16908344, (CharSequence) null, (Class) null);
        int i4 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, 16908345, (CharSequence) null, (Class) null);
        int i5 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, 16908346, (CharSequence) null, (Class) null);
        int i6 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, 16908347, (CharSequence) null, (Class) null);
        new C0360b(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, 16908358, (CharSequence) null, (Class) null);
        new C0360b(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, 16908359, (CharSequence) null, (Class) null);
        new C0360b(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, 16908360, (CharSequence) null, (Class) null);
        new C0360b(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, 16908361, (CharSequence) null, (Class) null);
        int i7 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, 16908348, (CharSequence) null, (Class) null);
        int i8 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, 16908349, (CharSequence) null, C0371m.class);
        int i9 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, 16908354, (CharSequence) null, C0369k.class);
        int i10 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP, 16908356, (CharSequence) null, (Class) null);
        int i11 = Build.VERSION.SDK_INT;
        new C0360b(AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP, 16908357, (CharSequence) null, (Class) null);
    }

    public C0360b(int i, CharSequence charSequence, C0374p pVar) {
        this((Object) null, i, charSequence, (Class) null);
    }

    /* renamed from: a */
    public C0360b mo3850a(CharSequence charSequence, C0374p pVar) {
        return new C0360b((Object) null, this.mId, charSequence, this.f327fo);
    }

    public int getId() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
    }

    C0360b(Object obj, int i, CharSequence charSequence, Class cls) {
        this.mId = i;
        int i2 = Build.VERSION.SDK_INT;
        if (obj == null) {
            this.mAction = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
        } else {
            this.mAction = obj;
        }
        this.f327fo = cls;
    }
}
