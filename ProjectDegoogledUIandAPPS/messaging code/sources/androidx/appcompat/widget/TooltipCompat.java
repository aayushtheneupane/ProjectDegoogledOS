package androidx.appcompat.widget;

import android.os.Build;
import android.view.View;

public class TooltipCompat {
    private TooltipCompat() {
    }

    public static void setTooltipText(View view, CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        view.setTooltipText(charSequence);
    }
}
