package com.android.systemui.stackdivider;

import android.content.Context;
import android.os.Binder;
import android.view.View;
import android.view.WindowManager;

public class DividerWindowManager {
    private WindowManager.LayoutParams mLp;
    private View mView;
    private final WindowManager mWindowManager;

    public DividerWindowManager(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
    }

    public void add(View view, int i, int i2) {
        this.mLp = new WindowManager.LayoutParams(i, i2, 2034, 545521704, -3);
        this.mLp.token = new Binder();
        this.mLp.setTitle("DockedStackDivider");
        WindowManager.LayoutParams layoutParams = this.mLp;
        layoutParams.privateFlags |= 64;
        layoutParams.layoutInDisplayCutoutMode = 1;
        view.setSystemUiVisibility(1792);
        this.mWindowManager.addView(view, this.mLp);
        this.mView = view;
    }

    public void remove() {
        View view = this.mView;
        if (view != null) {
            this.mWindowManager.removeView(view);
        }
        this.mView = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSlippery(boolean r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            if (r6 == 0) goto L_0x0012
            android.view.WindowManager$LayoutParams r2 = r5.mLp
            int r3 = r2.flags
            r4 = r3 & r1
            if (r4 != 0) goto L_0x0012
            r6 = r3 | r1
            r2.flags = r6
            goto L_0x0023
        L_0x0012:
            if (r6 != 0) goto L_0x0022
            android.view.WindowManager$LayoutParams r6 = r5.mLp
            int r2 = r6.flags
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0022
            r1 = -536870913(0xffffffffdfffffff, float:-3.6893486E19)
            r1 = r1 & r2
            r6.flags = r1
            goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            if (r0 == 0) goto L_0x002e
            android.view.WindowManager r6 = r5.mWindowManager
            android.view.View r0 = r5.mView
            android.view.WindowManager$LayoutParams r5 = r5.mLp
            r6.updateViewLayout(r0, r5)
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.DividerWindowManager.setSlippery(boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTouchable(boolean r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != 0) goto L_0x0010
            android.view.WindowManager$LayoutParams r1 = r4.mLp
            int r2 = r1.flags
            r3 = r2 & 16
            if (r3 != 0) goto L_0x0010
            r5 = r2 | 16
            r1.flags = r5
            goto L_0x0020
        L_0x0010:
            if (r5 == 0) goto L_0x001f
            android.view.WindowManager$LayoutParams r5 = r4.mLp
            int r1 = r5.flags
            r2 = r1 & 16
            if (r2 == 0) goto L_0x001f
            r1 = r1 & -17
            r5.flags = r1
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            if (r0 == 0) goto L_0x002b
            android.view.WindowManager r5 = r4.mWindowManager
            android.view.View r0 = r4.mView
            android.view.WindowManager$LayoutParams r4 = r4.mLp
            r5.updateViewLayout(r0, r4)
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.DividerWindowManager.setTouchable(boolean):void");
    }
}
