package androidx.viewpager.widget;

import android.view.View;
import java.util.Comparator;

/* renamed from: androidx.viewpager.widget.o */
class C0630o implements Comparator {
    C0630o() {
    }

    public int compare(Object obj, Object obj2) {
        C0623h hVar = (C0623h) ((View) obj).getLayoutParams();
        C0623h hVar2 = (C0623h) ((View) obj2).getLayoutParams();
        boolean z = hVar.isDecor;
        if (z != hVar2.isDecor) {
            return z ? 1 : -1;
        }
        return hVar.position - hVar2.position;
    }
}
