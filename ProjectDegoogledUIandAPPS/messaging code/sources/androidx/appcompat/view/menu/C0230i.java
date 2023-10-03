package androidx.appcompat.view.menu;

import android.view.MenuItem;
import androidx.appcompat.widget.MenuItemHoverListener;

/* renamed from: androidx.appcompat.view.menu.i */
class C0230i implements MenuItemHoverListener {
    final /* synthetic */ C0232k this$0;

    C0230i(C0232k kVar) {
        this.this$0 = kVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: androidx.appcompat.view.menu.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemHoverEnter(androidx.appcompat.view.menu.C0238q r6, android.view.MenuItem r7) {
        /*
            r5 = this;
            androidx.appcompat.view.menu.k r0 = r5.this$0
            android.os.Handler r0 = r0.f249un
            r1 = 0
            r0.removeCallbacksAndMessages(r1)
            androidx.appcompat.view.menu.k r0 = r5.this$0
            java.util.List r0 = r0.f251wn
            int r0 = r0.size()
            r2 = 0
        L_0x0011:
            r3 = -1
            if (r2 >= r0) goto L_0x0026
            androidx.appcompat.view.menu.k r4 = r5.this$0
            java.util.List r4 = r4.f251wn
            java.lang.Object r4 = r4.get(r2)
            androidx.appcompat.view.menu.j r4 = (androidx.appcompat.view.menu.C0231j) r4
            androidx.appcompat.view.menu.q r4 = r4.menu
            if (r6 != r4) goto L_0x0023
            goto L_0x0027
        L_0x0023:
            int r2 = r2 + 1
            goto L_0x0011
        L_0x0026:
            r2 = r3
        L_0x0027:
            if (r2 != r3) goto L_0x002a
            return
        L_0x002a:
            int r2 = r2 + 1
            androidx.appcompat.view.menu.k r0 = r5.this$0
            java.util.List r0 = r0.f251wn
            int r0 = r0.size()
            if (r2 >= r0) goto L_0x0041
            androidx.appcompat.view.menu.k r0 = r5.this$0
            java.util.List r0 = r0.f251wn
            java.lang.Object r0 = r0.get(r2)
            r1 = r0
            androidx.appcompat.view.menu.j r1 = (androidx.appcompat.view.menu.C0231j) r1
        L_0x0041:
            androidx.appcompat.view.menu.h r0 = new androidx.appcompat.view.menu.h
            r0.<init>(r5, r1, r7, r6)
            long r1 = android.os.SystemClock.uptimeMillis()
            r3 = 200(0xc8, double:9.9E-322)
            long r1 = r1 + r3
            androidx.appcompat.view.menu.k r5 = r5.this$0
            android.os.Handler r5 = r5.f249un
            r5.postAtTime(r0, r6, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.C0230i.onItemHoverEnter(androidx.appcompat.view.menu.q, android.view.MenuItem):void");
    }

    public void onItemHoverExit(C0238q qVar, MenuItem menuItem) {
        this.this$0.f249un.removeCallbacksAndMessages(qVar);
    }
}
