package androidx.core.widget;

import android.os.Build;
import android.widget.ListView;

public final class ListViewCompat {
    private ListViewCompat() {
    }

    public static boolean canScrollList(ListView listView, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return listView.canScrollList(i);
    }

    public static void scrollListBy(ListView listView, int i) {
        int i2 = Build.VERSION.SDK_INT;
        listView.scrollListBy(i);
    }
}
