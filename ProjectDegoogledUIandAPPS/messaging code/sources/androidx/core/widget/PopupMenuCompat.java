package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupMenu;

public final class PopupMenuCompat {
    private PopupMenuCompat() {
    }

    public static View.OnTouchListener getDragToOpenListener(Object obj) {
        int i = Build.VERSION.SDK_INT;
        return ((PopupMenu) obj).getDragToOpenListener();
    }
}
