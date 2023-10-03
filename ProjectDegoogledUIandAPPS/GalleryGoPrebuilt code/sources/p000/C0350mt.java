package p000;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.util.Collections;
import java.util.IdentityHashMap;

/* renamed from: mt */
/* compiled from: PG */
public final class C0350mt {
    public C0350mt() {
        Collections.newSetFromMap(new IdentityHashMap());
    }

    /* renamed from: a */
    public static int m14757a(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        return cursor.getColumnIndexOrThrow("`" + str + "`");
    }

    /* renamed from: a */
    public static Intent m14759a(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        Intent parentActivityIntent = activity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        String b = m14770b(activity);
        if (b == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(activity, b);
        try {
            if (m14771b(activity, componentName) != null) {
                return new Intent().setComponent(componentName);
            }
            return Intent.makeMainActivity(componentName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + b + "' in manifest");
            return null;
        }
    }

    /* renamed from: a */
    public static Intent m14760a(Context context, ComponentName componentName) {
        String b = m14771b(context, componentName);
        if (b == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), b);
        if (m14771b(context, componentName2) != null) {
            return new Intent().setComponent(componentName2);
        }
        return Intent.makeMainActivity(componentName2);
    }

    /* renamed from: b */
    public static String m14770b(Activity activity) {
        try {
            return m14771b(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* renamed from: b */
    private static String m14771b(Context context, ComponentName componentName) {
        int i;
        String string;
        PackageManager packageManager = context.getPackageManager();
        int i2 = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT < 29) {
            int i3 = Build.VERSION.SDK_INT;
            i = 787072;
        } else {
            i = 269222528;
        }
        ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, i);
        int i4 = Build.VERSION.SDK_INT;
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        if (activityInfo.metaData == null || (string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    /* renamed from: a */
    public static void m14761a(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        layoutInflater.setFactory2(factory2);
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: b */
    public static int m14769b(ViewGroup.MarginLayoutParams marginLayoutParams) {
        int i = Build.VERSION.SDK_INT;
        return marginLayoutParams.getMarginEnd();
    }

    /* renamed from: a */
    public static int m14758a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        int i = Build.VERSION.SDK_INT;
        return marginLayoutParams.getMarginStart();
    }

    /* renamed from: a */
    public static boolean m14767a(ViewParent viewParent, View view, float f, float f2, boolean z) {
        int i = Build.VERSION.SDK_INT;
        try {
            return viewParent.onNestedFling(view, f, f2, z);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m14766a(ViewParent viewParent, View view, float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        try {
            return viewParent.onNestedPreFling(view, f, f2);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e);
            return false;
        }
    }

    /* renamed from: a */
    public static void m14764a(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
        if (viewParent instanceof C0326lw) {
            ((C0326lw) viewParent).mo707a(view, i, i2, iArr, i3);
        } else if (i3 == 0) {
            int i4 = Build.VERSION.SDK_INT;
            try {
                viewParent.onNestedPreScroll(view, i, i2, iArr);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e);
            }
        }
    }

    /* renamed from: a */
    public static void m14763a(ViewParent viewParent, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewParent viewParent2 = viewParent;
        if (viewParent2 instanceof C0327lx) {
            ((C0327lx) viewParent2).mo706a(view, i, i2, i3, i4, i5, iArr);
            return;
        }
        iArr[0] = iArr[0] + i3;
        iArr[1] = iArr[1] + i4;
        if (viewParent2 instanceof C0326lw) {
            ((C0326lw) viewParent2).mo705a(view, i, i2, i3, i4, i5);
        } else if (i5 == 0) {
            int i6 = Build.VERSION.SDK_INT;
            try {
                viewParent.onNestedScroll(view, i, i2, i3, i4);
            } catch (AbstractMethodError e) {
                AbstractMethodError abstractMethodError = e;
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", abstractMethodError);
            }
        }
    }

    /* renamed from: b */
    public static void m14772b(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof C0326lw) {
            ((C0326lw) viewParent).mo714b(view, view2, i, i2);
        } else if (i2 == 0) {
            int i3 = Build.VERSION.SDK_INT;
            try {
                viewParent.onNestedScrollAccepted(view, view2, i);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e);
            }
        }
    }

    /* renamed from: a */
    public static boolean m14768a(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof C0326lw) {
            return ((C0326lw) viewParent).mo709a(view, view2, i, i2);
        }
        if (i2 != 0) {
            return false;
        }
        int i3 = Build.VERSION.SDK_INT;
        try {
            return viewParent.onStartNestedScroll(view, view2, i);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e);
            return false;
        }
    }

    /* renamed from: a */
    public static void m14762a(ViewParent viewParent, View view, int i) {
        if (viewParent instanceof C0326lw) {
            ((C0326lw) viewParent).mo704a(view, i);
        } else if (i == 0) {
            int i2 = Build.VERSION.SDK_INT;
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e);
            }
        }
    }

    /* renamed from: a */
    public static void m14765a(AccessibilityEvent accessibilityEvent, int i) {
        int i2 = Build.VERSION.SDK_INT;
        accessibilityEvent.setContentChangeTypes(i);
    }
}
