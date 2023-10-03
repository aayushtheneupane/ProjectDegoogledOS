package com.android.settingslib.display;

import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.WindowManagerGlobal;
import com.android.settingslib.R$string;

public class DisplayDensityUtils {
    private static final int[] SUMMARIES_LARGER = {R$string.screen_zoom_summary_large, R$string.screen_zoom_summary_very_large, R$string.screen_zoom_summary_extremely_large};
    private static final int[] SUMMARIES_SMALLER = {R$string.screen_zoom_summary_small, R$string.screen_zoom_summary_smaller, R$string.screen_zoom_summary_smallest};
    private static final int SUMMARY_CUSTOM = R$string.screen_zoom_summary_custom;
    public static final int SUMMARY_DEFAULT = R$string.screen_zoom_summary_default;
    private final int mCurrentIndex;
    private final int mDefaultDensity;
    private final String[] mEntries;
    private final int[] mValues;

    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DisplayDensityUtils(android.content.Context r18) {
        /*
            r17 = this;
            r0 = r17
            r17.<init>()
            r1 = 0
            int r2 = getDefaultDisplayDensity(r1)
            r3 = -1
            if (r2 > 0) goto L_0x0017
            r2 = 0
            r0.mEntries = r2
            r0.mValues = r2
            r0.mDefaultDensity = r1
            r0.mCurrentIndex = r3
            return
        L_0x0017:
            android.content.res.Resources r4 = r18.getResources()
            android.util.DisplayMetrics r5 = new android.util.DisplayMetrics
            r5.<init>()
            android.view.Display r6 = r18.getDisplay()
            r6.getRealMetrics(r5)
            int r6 = r5.densityDpi
            int r7 = r5.widthPixels
            int r5 = r5.heightPixels
            int r5 = java.lang.Math.min(r7, r5)
            int r5 = r5 * 160
            int r5 = r5 / 320
            r7 = 1069547520(0x3fc00000, float:1.5)
            float r5 = (float) r5
            float r8 = (float) r2
            float r5 = r5 / r8
            float r5 = java.lang.Math.min(r7, r5)
            r7 = 1065353216(0x3f800000, float:1.0)
            float r5 = r5 - r7
            r9 = 1035489772(0x3db851ec, float:0.09)
            float r9 = r5 / r9
            int[] r10 = SUMMARIES_LARGER
            int r10 = r10.length
            float r10 = (float) r10
            r11 = 0
            float r9 = android.util.MathUtils.constrain(r9, r11, r10)
            int r9 = (int) r9
            r10 = 1079334229(0x40555555, float:3.3333333)
            int[] r12 = SUMMARIES_SMALLER
            int r12 = r12.length
            float r12 = (float) r12
            float r10 = android.util.MathUtils.constrain(r10, r11, r12)
            int r10 = (int) r10
            int r11 = r10 + 1
            int r11 = r11 + r9
            java.lang.String[] r11 = new java.lang.String[r11]
            int r12 = r11.length
            int[] r12 = new int[r12]
            r13 = 1
            if (r10 <= 0) goto L_0x0091
            r14 = 1050253722(0x3e99999a, float:0.3)
            float r15 = (float) r10
            float r14 = r14 / r15
            int r10 = r10 - r13
            r15 = r3
            r3 = r1
        L_0x006f:
            if (r10 < 0) goto L_0x0093
            int r1 = r10 + 1
            float r1 = (float) r1
            float r1 = r1 * r14
            float r1 = r7 - r1
            float r1 = r1 * r8
            int r1 = (int) r1
            r1 = r1 & -2
            if (r6 != r1) goto L_0x007e
            r15 = r3
        L_0x007e:
            int[] r16 = SUMMARIES_SMALLER
            r13 = r16[r10]
            java.lang.String r13 = r4.getString(r13)
            r11[r3] = r13
            r12[r3] = r1
            int r3 = r3 + 1
            int r10 = r10 + -1
            r1 = 0
            r13 = 1
            goto L_0x006f
        L_0x0091:
            r15 = r3
            r3 = 0
        L_0x0093:
            if (r6 != r2) goto L_0x0096
            r15 = r3
        L_0x0096:
            r12[r3] = r2
            int r1 = SUMMARY_DEFAULT
            java.lang.String r1 = r4.getString(r1)
            r11[r3] = r1
            int r3 = r3 + 1
            if (r9 <= 0) goto L_0x00c5
            float r1 = (float) r9
            float r5 = r5 / r1
            r1 = 0
        L_0x00a7:
            if (r1 >= r9) goto L_0x00c5
            int r10 = r1 + 1
            float r13 = (float) r10
            float r13 = r13 * r5
            float r13 = r13 + r7
            float r13 = r13 * r8
            int r13 = (int) r13
            r13 = r13 & -2
            if (r6 != r13) goto L_0x00b5
            r15 = r3
        L_0x00b5:
            r12[r3] = r13
            int[] r13 = SUMMARIES_LARGER
            r1 = r13[r1]
            java.lang.String r1 = r4.getString(r1)
            r11[r3] = r1
            int r3 = r3 + 1
            r1 = r10
            goto L_0x00a7
        L_0x00c5:
            if (r15 < 0) goto L_0x00c8
            goto L_0x00ea
        L_0x00c8:
            int r1 = r12.length
            r5 = 1
            int r1 = r1 + r5
            int[] r12 = java.util.Arrays.copyOf(r12, r1)
            r12[r3] = r6
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r11, r1)
            r11 = r1
            java.lang.String[] r11 = (java.lang.String[]) r11
            int r1 = SUMMARY_CUSTOM
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7 = 0
            r5[r7] = r6
            java.lang.String r1 = r4.getString(r1, r5)
            r11[r3] = r1
            r15 = r3
        L_0x00ea:
            r0.mDefaultDensity = r2
            r0.mCurrentIndex = r15
            r0.mEntries = r11
            r0.mValues = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.display.DisplayDensityUtils.<init>(android.content.Context):void");
    }

    public String[] getEntries() {
        return this.mEntries;
    }

    public int[] getValues() {
        return this.mValues;
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    public int getDefaultDensity() {
        return this.mDefaultDensity;
    }

    private static int getDefaultDisplayDensity(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().getInitialDisplayDensity(i);
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public static void clearForcedDisplayDensity(int i) {
        AsyncTask.execute(new Runnable(i, UserHandle.myUserId()) {
            private final /* synthetic */ int f$0;
            private final /* synthetic */ int f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                DisplayDensityUtils.lambda$clearForcedDisplayDensity$0(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$clearForcedDisplayDensity$0(int i, int i2) {
        try {
            WindowManagerGlobal.getWindowManagerService().clearForcedDisplayDensityForUser(i, i2);
        } catch (RemoteException unused) {
            Log.w("DisplayDensityUtils", "Unable to clear forced display density setting");
        }
    }

    public static void setForcedDisplayDensity(int i, int i2) {
        AsyncTask.execute(new Runnable(i, i2, UserHandle.myUserId()) {
            private final /* synthetic */ int f$0;
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                DisplayDensityUtils.lambda$setForcedDisplayDensity$1(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$setForcedDisplayDensity$1(int i, int i2, int i3) {
        try {
            WindowManagerGlobal.getWindowManagerService().setForcedDisplayDensityForUser(i, i2, i3);
        } catch (RemoteException unused) {
            Log.w("DisplayDensityUtils", "Unable to save forced display density setting");
        }
    }
}
