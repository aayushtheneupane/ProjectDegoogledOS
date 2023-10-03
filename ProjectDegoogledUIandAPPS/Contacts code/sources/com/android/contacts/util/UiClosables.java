package com.android.contacts.util;

import android.widget.ListPopupWindow;

public class UiClosables {
    public static boolean closeQuietly(ListPopupWindow listPopupWindow) {
        if (listPopupWindow == null || !listPopupWindow.isShowing()) {
            return false;
        }
        listPopupWindow.dismiss();
        return true;
    }
}
