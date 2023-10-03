package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.core.internal.p023a.C0329b;

public final class MenuItemCompat {
    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;
    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    @Deprecated
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem menuItem);

        boolean onMenuItemActionExpand(MenuItem menuItem);
    }

    private MenuItemCompat() {
    }

    @Deprecated
    public static boolean collapseActionView(MenuItem menuItem) {
        return menuItem.collapseActionView();
    }

    @Deprecated
    public static boolean expandActionView(MenuItem menuItem) {
        return menuItem.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).mo1479L();
        }
        Log.w(TAG, "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @Deprecated
    public static View getActionView(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static int getAlphabeticModifiers(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).getAlphabeticModifiers();
        }
        int i = Build.VERSION.SDK_INT;
        return menuItem.getAlphabeticModifiers();
    }

    public static CharSequence getContentDescription(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).getContentDescription();
        }
        int i = Build.VERSION.SDK_INT;
        return menuItem.getContentDescription();
    }

    public static ColorStateList getIconTintList(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).getIconTintList();
        }
        int i = Build.VERSION.SDK_INT;
        return menuItem.getIconTintList();
    }

    public static PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).getIconTintMode();
        }
        int i = Build.VERSION.SDK_INT;
        return menuItem.getIconTintMode();
    }

    public static int getNumericModifiers(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).getNumericModifiers();
        }
        int i = Build.VERSION.SDK_INT;
        return menuItem.getNumericModifiers();
    }

    public static CharSequence getTooltipText(MenuItem menuItem) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).getTooltipText();
        }
        int i = Build.VERSION.SDK_INT;
        return menuItem.getTooltipText();
    }

    @Deprecated
    public static boolean isActionViewExpanded(MenuItem menuItem) {
        return menuItem.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof C0329b) {
            return ((C0329b) menuItem).mo1480a(actionProvider);
        }
        Log.w(TAG, "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static void setAlphabeticShortcut(MenuItem menuItem, char c, int i) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setAlphabeticShortcut(c, i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        menuItem.setAlphabeticShortcut(c, i);
    }

    public static void setContentDescription(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setContentDescription(charSequence);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        menuItem.setContentDescription(charSequence);
    }

    public static void setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setIconTintList(colorStateList);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        menuItem.setIconTintList(colorStateList);
    }

    public static void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setIconTintMode(mode);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        menuItem.setIconTintMode(mode);
    }

    public static void setNumericShortcut(MenuItem menuItem, char c, int i) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setNumericShortcut(c, i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        menuItem.setNumericShortcut(c, i);
    }

    @Deprecated
    public static MenuItem setOnActionExpandListener(MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
        return menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return OnActionExpandListener.this.onMenuItemActionCollapse(menuItem);
            }

            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return OnActionExpandListener.this.onMenuItemActionExpand(menuItem);
            }
        });
    }

    public static void setShortcut(MenuItem menuItem, char c, char c2, int i, int i2) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setShortcut(c, c2, i, i2);
            return;
        }
        int i3 = Build.VERSION.SDK_INT;
        menuItem.setShortcut(c, c2, i, i2);
    }

    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }

    public static void setTooltipText(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof C0329b) {
            ((C0329b) menuItem).setTooltipText(charSequence);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        menuItem.setTooltipText(charSequence);
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem, int i) {
        return menuItem.setActionView(i);
    }
}
