package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    private static final String TAG = "CompoundButtonCompat";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;

    private CompoundButtonCompat() {
    }

    public static Drawable getButtonDrawable(CompoundButton compoundButton) {
        int i = Build.VERSION.SDK_INT;
        return compoundButton.getButtonDrawable();
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundButton) {
        int i = Build.VERSION.SDK_INT;
        return compoundButton.getButtonTintList();
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton) {
        int i = Build.VERSION.SDK_INT;
        return compoundButton.getButtonTintMode();
    }

    public static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        compoundButton.setButtonTintList(colorStateList);
    }

    public static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        compoundButton.setButtonTintMode(mode);
    }
}
