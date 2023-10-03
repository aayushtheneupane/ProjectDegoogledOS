package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
    private static final String TAG = "PopupWindowCompatApi21";
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    private PopupWindowCompat() {
    }

    public static boolean getOverlapAnchor(PopupWindow popupWindow) {
        int i = Build.VERSION.SDK_INT;
        return popupWindow.getOverlapAnchor();
    }

    public static int getWindowLayoutType(PopupWindow popupWindow) {
        int i = Build.VERSION.SDK_INT;
        return popupWindow.getWindowLayoutType();
    }

    public static void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
        int i = Build.VERSION.SDK_INT;
        popupWindow.setOverlapAnchor(z);
    }

    public static void setWindowLayoutType(PopupWindow popupWindow, int i) {
        int i2 = Build.VERSION.SDK_INT;
        popupWindow.setWindowLayoutType(i);
    }

    public static void showAsDropDown(PopupWindow popupWindow, View view, int i, int i2, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        popupWindow.showAsDropDown(view, i, i2, i3);
    }
}
