package com.android.dialer.dialpadview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import java.util.Locale;

public class DialpadView extends LinearLayout {
    /* access modifiers changed from: private */
    public static final int[] BUTTON_IDS = {R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.star, R.id.pound};
    private final AttributeSet attributeSet;
    private ImageButton delete;
    private EditText digits;
    private TextView digitsHint;
    /* access modifiers changed from: private */
    public boolean isLandscapeMode;
    private final boolean isRtl;
    private final OnPreDrawListenerForKeyLayoutAdjust onPreDrawListenerForKeyLayoutAdjust;
    private View overflowMenuButton;
    private final String[] primaryLettersMapping;
    private ViewGroup rateContainer;
    private final ColorStateList rippleColor;
    private final String[] secondaryLettersMapping;
    private final int translateDistance;

    private class OnPreDrawListenerForKeyLayoutAdjust implements ViewTreeObserver.OnPreDrawListener {
        /* synthetic */ OnPreDrawListenerForKeyLayoutAdjust(C04781 r2) {
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x009d A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x009e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onPreDraw() {
            /*
                r11 = this;
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                boolean r0 = r0.isLandscapeMode
                r1 = 9
                r2 = 2131296504(0x7f0900f8, float:1.8210927E38)
                r3 = 0
                r4 = 1
                if (r0 == 0) goto L_0x0056
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                boolean r0 = r0.isLandscapeMode
                com.android.dialer.common.Assert.checkState(r0)
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                int[] r5 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                r5 = r5[r3]
                android.view.View r0 = r0.findViewById(r5)
                com.android.dialer.dialpadview.DialpadKeyButton r0 = (com.android.dialer.dialpadview.DialpadKeyButton) r0
                android.view.View r0 = r0.findViewById(r2)
                android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
                int r0 = r0.getWidth()
                r5 = r4
            L_0x0031:
                int[] r6 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                int r6 = r6.length
                if (r5 >= r6) goto L_0x009a
                com.android.dialer.dialpadview.DialpadView r6 = com.android.dialer.dialpadview.DialpadView.this
                int[] r7 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                r7 = r7[r5]
                android.view.View r6 = r6.findViewById(r7)
                com.android.dialer.dialpadview.DialpadKeyButton r6 = (com.android.dialer.dialpadview.DialpadKeyButton) r6
                android.view.View r6 = r6.findViewById(r2)
                android.widget.LinearLayout r6 = (android.widget.LinearLayout) r6
                int r6 = r6.getWidth()
                if (r0 == r6) goto L_0x0053
                goto L_0x0095
            L_0x0053:
                int r5 = r5 + 1
                goto L_0x0031
            L_0x0056:
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                boolean r0 = r0.isLandscapeMode
                r0 = r0 ^ r4
                com.android.dialer.common.Assert.checkState(r0)
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                int[] r5 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                r5 = r5[r3]
                android.view.View r0 = r0.findViewById(r5)
                com.android.dialer.dialpadview.DialpadKeyButton r0 = (com.android.dialer.dialpadview.DialpadKeyButton) r0
                android.view.View r0 = r0.findViewById(r2)
                android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
                int r0 = r0.getHeight()
                r5 = r4
            L_0x0079:
                if (r5 > r1) goto L_0x009a
                com.android.dialer.dialpadview.DialpadView r6 = com.android.dialer.dialpadview.DialpadView.this
                int[] r7 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                r7 = r7[r5]
                android.view.View r6 = r6.findViewById(r7)
                com.android.dialer.dialpadview.DialpadKeyButton r6 = (com.android.dialer.dialpadview.DialpadKeyButton) r6
                android.view.View r6 = r6.findViewById(r2)
                android.widget.LinearLayout r6 = (android.widget.LinearLayout) r6
                int r6 = r6.getHeight()
                if (r0 == r6) goto L_0x0097
            L_0x0095:
                r0 = r4
                goto L_0x009b
            L_0x0097:
                int r5 = r5 + 1
                goto L_0x0079
            L_0x009a:
                r0 = r3
            L_0x009b:
                if (r0 != 0) goto L_0x009e
                return r4
            L_0x009e:
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                boolean r0 = r0.isLandscapeMode
                if (r0 == 0) goto L_0x0104
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                boolean r0 = r0.isLandscapeMode
                com.android.dialer.common.Assert.checkState(r0)
                int[] r0 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                int r1 = r0.length
                r4 = r3
                r5 = r4
            L_0x00b6:
                if (r4 >= r1) goto L_0x00d3
                r6 = r0[r4]
                com.android.dialer.dialpadview.DialpadView r7 = com.android.dialer.dialpadview.DialpadView.this
                android.view.View r6 = r7.findViewById(r6)
                com.android.dialer.dialpadview.DialpadKeyButton r6 = (com.android.dialer.dialpadview.DialpadKeyButton) r6
                android.view.View r6 = r6.findViewById(r2)
                android.widget.LinearLayout r6 = (android.widget.LinearLayout) r6
                int r6 = r6.getWidth()
                int r5 = java.lang.Math.max(r5, r6)
                int r4 = r4 + 1
                goto L_0x00b6
            L_0x00d3:
                int[] r0 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                int r1 = r0.length
                r4 = r3
            L_0x00d9:
                if (r4 >= r1) goto L_0x0178
                r6 = r0[r4]
                com.android.dialer.dialpadview.DialpadView r7 = com.android.dialer.dialpadview.DialpadView.this
                android.view.View r6 = r7.findViewById(r6)
                com.android.dialer.dialpadview.DialpadKeyButton r6 = (com.android.dialer.dialpadview.DialpadKeyButton) r6
                android.view.View r6 = r6.findViewById(r2)
                android.widget.LinearLayout r6 = (android.widget.LinearLayout) r6
                r7 = 2131296502(0x7f0900f6, float:1.8210922E38)
                android.view.View r7 = r6.findViewById(r7)
                android.widget.LinearLayout$LayoutParams r8 = new android.widget.LinearLayout$LayoutParams
                int r6 = r6.getWidth()
                int r6 = r5 - r6
                r9 = -1
                r8.<init>(r6, r9)
                r7.setLayoutParams(r8)
                int r4 = r4 + 1
                goto L_0x00d9
            L_0x0104:
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                boolean r0 = r0.isLandscapeMode
                r0 = r0 ^ r4
                com.android.dialer.common.Assert.checkState(r0)
                r0 = r3
                r4 = r0
            L_0x0110:
                if (r0 > r1) goto L_0x0131
                com.android.dialer.dialpadview.DialpadView r5 = com.android.dialer.dialpadview.DialpadView.this
                int[] r6 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                r6 = r6[r0]
                android.view.View r5 = r5.findViewById(r6)
                com.android.dialer.dialpadview.DialpadKeyButton r5 = (com.android.dialer.dialpadview.DialpadKeyButton) r5
                android.view.View r5 = r5.findViewById(r2)
                android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
                int r5 = r5.getHeight()
                int r4 = java.lang.Math.max(r4, r5)
                int r0 = r0 + 1
                goto L_0x0110
            L_0x0131:
                r0 = r3
            L_0x0132:
                if (r0 > r1) goto L_0x0178
                com.android.dialer.dialpadview.DialpadView r5 = com.android.dialer.dialpadview.DialpadView.this
                int[] r6 = com.android.dialer.dialpadview.DialpadView.BUTTON_IDS
                r6 = r6[r0]
                android.view.View r5 = r5.findViewById(r6)
                com.android.dialer.dialpadview.DialpadKeyButton r5 = (com.android.dialer.dialpadview.DialpadKeyButton) r5
                android.view.View r5 = r5.findViewById(r2)
                android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
                r6 = 2131296506(0x7f0900fa, float:1.821093E38)
                android.view.View r6 = r5.findViewById(r6)
                com.android.dialer.dialpadview.DialpadTextView r6 = (com.android.dialer.dialpadview.DialpadTextView) r6
                android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
                android.view.ViewGroup$MarginLayoutParams r7 = (android.view.ViewGroup.MarginLayoutParams) r7
                r8 = 2131296503(0x7f0900f7, float:1.8210925E38)
                android.view.View r5 = r5.findViewById(r8)
                android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
                android.widget.LinearLayout$LayoutParams r8 = new android.widget.LinearLayout$LayoutParams
                r9 = -2
                int r6 = r6.getHeight()
                int r6 = r4 - r6
                int r10 = r7.topMargin
                int r6 = r6 - r10
                int r7 = r7.bottomMargin
                int r6 = r6 - r7
                r8.<init>(r9, r6)
                r5.setLayoutParams(r8)
                int r0 = r0 + 1
                goto L_0x0132
            L_0x0178:
                com.android.dialer.dialpadview.DialpadView r0 = com.android.dialer.dialpadview.DialpadView.this
                android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
                r0.removeOnPreDrawListener(r11)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.dialpadview.DialpadView.OnPreDrawListenerForKeyLayoutAdjust.onPreDraw():boolean");
        }
    }

    public DialpadView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0071, code lost:
        if (r4 != com.android.dialer.R.id.star) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0098, code lost:
        if (r4 != com.android.dialer.R.id.pound) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d2, code lost:
        if (r4 != com.android.dialer.R.id.pound) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d5, code lost:
        com.android.dialer.common.LogUtil.m8e("DialpadView", "Attempted to get animation delay for invalid key button id.", new java.lang.Object[0]);
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0121, code lost:
        if (r5 != com.android.dialer.R.id.pound) goto L_0x0170;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0145, code lost:
        if (r5 != com.android.dialer.R.id.pound) goto L_0x0170;
     */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x014f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void animateShow() {
        /*
            r22 = this;
            r0 = r22
            com.android.dialer.dialpadview.DialpadView$1 r1 = new com.android.dialer.dialpadview.DialpadView$1
            r1.<init>(r0)
            r3 = 0
        L_0x0008:
            int[] r4 = BUTTON_IDS
            int r5 = r4.length
            if (r3 >= r5) goto L_0x01d9
            r4 = r4[r3]
            boolean r5 = r0.isLandscapeMode
            r9 = 2131296568(0x7f090138, float:1.8211056E38)
            r11 = 2131296925(0x7f09029d, float:1.821178E38)
            r13 = 2131296946(0x7f0902b2, float:1.8211823E38)
            r14 = 2131296727(0x7f0901d7, float:1.8211379E38)
            r15 = 2131296878(0x7f09026e, float:1.8211685E38)
            r6 = 2131296718(0x7f0901ce, float:1.821136E38)
            r7 = 2131296561(0x7f090131, float:1.8211042E38)
            r8 = 2131296762(0x7f0901fa, float:1.821145E38)
            r16 = 363(0x16b, float:5.09E-43)
            r17 = 231(0xe7, float:3.24E-43)
            r18 = 198(0xc6, float:2.77E-43)
            r19 = 330(0x14a, float:4.62E-43)
            r20 = 297(0x129, float:4.16E-43)
            r10 = 2131297021(0x7f0902fd, float:1.8211975E38)
            r12 = 2131296532(0x7f090114, float:1.8210983E38)
            r2 = 2131296871(0x7f090267, float:1.821167E38)
            r21 = 264(0x108, float:3.7E-43)
            if (r5 == 0) goto L_0x009b
            boolean r5 = r0.isRtl
            if (r5 == 0) goto L_0x0075
            if (r4 != r11) goto L_0x0048
            goto L_0x009d
        L_0x0048:
            if (r4 != r15) goto L_0x004c
            goto L_0x00a2
        L_0x004c:
            if (r4 != r6) goto L_0x0050
            goto L_0x00a7
        L_0x0050:
            if (r4 != r8) goto L_0x0054
            goto L_0x00ac
        L_0x0054:
            if (r4 != r13) goto L_0x0058
            goto L_0x00b1
        L_0x0058:
            if (r4 != r7) goto L_0x005c
            goto L_0x00b6
        L_0x005c:
            if (r4 != r12) goto L_0x0060
            goto L_0x00bb
        L_0x0060:
            if (r4 != r10) goto L_0x0064
            goto L_0x00c0
        L_0x0064:
            if (r4 != r14) goto L_0x0068
            goto L_0x00c5
        L_0x0068:
            if (r4 != r9) goto L_0x006c
            goto L_0x00cd
        L_0x006c:
            r5 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r4 == r2) goto L_0x00e1
            if (r4 != r5) goto L_0x00d5
            goto L_0x00e1
        L_0x0075:
            r5 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r4 != r14) goto L_0x007b
            goto L_0x009d
        L_0x007b:
            if (r4 != r9) goto L_0x007e
            goto L_0x00a2
        L_0x007e:
            if (r4 != r2) goto L_0x0081
            goto L_0x00a7
        L_0x0081:
            if (r4 != r5) goto L_0x0084
            goto L_0x00ac
        L_0x0084:
            if (r4 != r13) goto L_0x0087
            goto L_0x00b1
        L_0x0087:
            if (r4 != r7) goto L_0x008a
            goto L_0x00b6
        L_0x008a:
            if (r4 != r12) goto L_0x008d
            goto L_0x00bb
        L_0x008d:
            if (r4 != r10) goto L_0x0090
            goto L_0x00c0
        L_0x0090:
            if (r4 != r11) goto L_0x0093
            goto L_0x00c5
        L_0x0093:
            if (r4 != r15) goto L_0x0096
            goto L_0x00cd
        L_0x0096:
            if (r4 == r6) goto L_0x00e1
            if (r4 != r8) goto L_0x00d5
            goto L_0x00e1
        L_0x009b:
            if (r4 != r14) goto L_0x00a0
        L_0x009d:
            r4 = 33
            goto L_0x00e3
        L_0x00a0:
            if (r4 != r13) goto L_0x00a5
        L_0x00a2:
            r4 = 66
            goto L_0x00e3
        L_0x00a5:
            if (r4 != r11) goto L_0x00aa
        L_0x00a7:
            r4 = 99
            goto L_0x00e3
        L_0x00aa:
            if (r4 != r9) goto L_0x00af
        L_0x00ac:
            r4 = 132(0x84, float:1.85E-43)
            goto L_0x00e3
        L_0x00af:
            if (r4 != r7) goto L_0x00b4
        L_0x00b1:
            r4 = 165(0xa5, float:2.31E-43)
            goto L_0x00e3
        L_0x00b4:
            if (r4 != r15) goto L_0x00b9
        L_0x00b6:
            r4 = r18
            goto L_0x00e3
        L_0x00b9:
            if (r4 != r2) goto L_0x00be
        L_0x00bb:
            r4 = r17
            goto L_0x00e3
        L_0x00be:
            if (r4 != r12) goto L_0x00c3
        L_0x00c0:
            r4 = r21
            goto L_0x00e3
        L_0x00c3:
            if (r4 != r6) goto L_0x00c8
        L_0x00c5:
            r4 = r20
            goto L_0x00e3
        L_0x00c8:
            r5 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r4 != r5) goto L_0x00d0
        L_0x00cd:
            r4 = r19
            goto L_0x00e3
        L_0x00d0:
            if (r4 == r10) goto L_0x00e1
            if (r4 != r8) goto L_0x00d5
            goto L_0x00e1
        L_0x00d5:
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.String r4 = "DialpadView"
            java.lang.String r9 = "Attempted to get animation delay for invalid key button id."
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r4, (java.lang.String) r9, (java.lang.Object[]) r5)
            r4 = 0
            goto L_0x00e3
        L_0x00e1:
            r4 = r16
        L_0x00e3:
            double r4 = (double) r4
            r13 = 4604119971053405471(0x3fe51eb851eb851f, double:0.66)
            double r4 = r4 * r13
            int r4 = (int) r4
            int[] r5 = BUTTON_IDS
            r5 = r5[r3]
            boolean r9 = r0.isLandscapeMode
            r11 = 2131296568(0x7f090138, float:1.8211056E38)
            r13 = 2131296925(0x7f09029d, float:1.821178E38)
            r14 = 2131296946(0x7f0902b2, float:1.8211823E38)
            r8 = 2131296727(0x7f0901d7, float:1.8211379E38)
            if (r9 == 0) goto L_0x014f
            boolean r9 = r0.isRtl
            if (r9 == 0) goto L_0x0127
            if (r5 == r8) goto L_0x0124
            if (r5 == r11) goto L_0x0124
            if (r5 == r2) goto L_0x0124
            r2 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r5 != r2) goto L_0x010f
            goto L_0x0124
        L_0x010f:
            if (r5 == r14) goto L_0x0148
            if (r5 == r7) goto L_0x0148
            if (r5 == r12) goto L_0x0148
            if (r5 != r10) goto L_0x0118
            goto L_0x0148
        L_0x0118:
            if (r5 == r13) goto L_0x014b
            if (r5 == r15) goto L_0x014b
            if (r5 == r6) goto L_0x014b
            r2 = 2131296762(0x7f0901fa, float:1.821145E38)
            if (r5 != r2) goto L_0x0170
            goto L_0x014b
        L_0x0124:
            r5 = r21
            goto L_0x014d
        L_0x0127:
            if (r5 == r8) goto L_0x014b
            if (r5 == r11) goto L_0x014b
            if (r5 == r2) goto L_0x014b
            r2 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r5 != r2) goto L_0x0133
            goto L_0x014b
        L_0x0133:
            if (r5 == r14) goto L_0x0148
            if (r5 == r7) goto L_0x0148
            if (r5 == r12) goto L_0x0148
            if (r5 != r10) goto L_0x013c
            goto L_0x0148
        L_0x013c:
            if (r5 == r13) goto L_0x0124
            if (r5 == r15) goto L_0x0124
            if (r5 == r6) goto L_0x0124
            r2 = 2131296762(0x7f0901fa, float:1.821145E38)
            if (r5 != r2) goto L_0x0170
            goto L_0x0124
        L_0x0148:
            r5 = r20
            goto L_0x014d
        L_0x014b:
            r5 = r19
        L_0x014d:
            r2 = 0
            goto L_0x0187
        L_0x014f:
            if (r5 == r8) goto L_0x0184
            if (r5 == r14) goto L_0x0184
            if (r5 == r13) goto L_0x0184
            if (r5 == r11) goto L_0x0184
            if (r5 == r7) goto L_0x0184
            if (r5 != r15) goto L_0x015c
            goto L_0x0184
        L_0x015c:
            if (r5 == r2) goto L_0x0180
            if (r5 == r12) goto L_0x0180
            if (r5 != r6) goto L_0x0163
            goto L_0x0180
        L_0x0163:
            r2 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r5 == r2) goto L_0x017c
            if (r5 == r10) goto L_0x017c
            r2 = 2131296762(0x7f0901fa, float:1.821145E38)
            if (r5 != r2) goto L_0x0170
            goto L_0x017c
        L_0x0170:
            r2 = 0
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "DialpadView"
            java.lang.String r7 = "Attempted to get animation duration for invalid key button id."
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r6, (java.lang.String) r7, (java.lang.Object[]) r5)
            r5 = r2
            goto L_0x0187
        L_0x017c:
            r2 = 0
            r5 = r21
            goto L_0x0187
        L_0x0180:
            r2 = 0
            r5 = r20
            goto L_0x0187
        L_0x0184:
            r2 = 0
            r5 = r19
        L_0x0187:
            double r5 = (double) r5
            r7 = 4605380978949069210(0x3fe999999999999a, double:0.8)
            double r5 = r5 * r7
            int r5 = (int) r5
            int[] r6 = BUTTON_IDS
            r6 = r6[r3]
            android.view.View r6 = r0.findViewById(r6)
            com.android.dialer.dialpadview.DialpadKeyButton r6 = (com.android.dialer.dialpadview.DialpadKeyButton) r6
            android.view.ViewPropertyAnimator r7 = r6.animate()
            boolean r8 = r0.isLandscapeMode
            if (r8 == 0) goto L_0x01b4
            boolean r8 = r0.isRtl
            if (r8 == 0) goto L_0x01a7
            r8 = -1
            goto L_0x01a8
        L_0x01a7:
            r8 = 1
        L_0x01a8:
            int r9 = r0.translateDistance
            int r8 = r8 * r9
            float r8 = (float) r8
            r6.setTranslationX(r8)
            r6 = 0
            r7.translationX(r6)
            goto L_0x01be
        L_0x01b4:
            int r8 = r0.translateDistance
            float r8 = (float) r8
            r6.setTranslationY(r8)
            r6 = 0
            r7.translationY(r6)
        L_0x01be:
            android.view.animation.Interpolator r6 = com.android.dialer.animation.AnimUtils.EASE_OUT_EASE_IN
            android.view.ViewPropertyAnimator r6 = r7.setInterpolator(r6)
            long r7 = (long) r4
            android.view.ViewPropertyAnimator r4 = r6.setStartDelay(r7)
            long r5 = (long) r5
            android.view.ViewPropertyAnimator r4 = r4.setDuration(r5)
            android.view.ViewPropertyAnimator r4 = r4.setListener(r1)
            r4.start()
            int r3 = r3 + 1
            goto L_0x0008
        L_0x01d9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.dialpadview.DialpadView.animateShow():void");
    }

    public ImageButton getDeleteButton() {
        return this.delete;
    }

    public EditText getDigits() {
        return this.digits;
    }

    public TextView getDigitsHint() {
        return this.digitsHint;
    }

    public View getOverflowMenuButton() {
        return this.overflowMenuButton;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnPreDrawListener(this.onPreDrawListenerForKeyLayoutAdjust);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: android.text.Spannable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFinishInflate() {
        /*
            r13 = this;
            super.onFinishInflate()
            android.content.res.Resources r0 = r13.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.orientation
            r1 = 0
            r2 = 1
            r3 = 2
            if (r0 != r3) goto L_0x0014
            r0 = r2
            goto L_0x0015
        L_0x0014:
            r0 = r1
        L_0x0015:
            r13.isLandscapeMode = r0
            android.content.Context r0 = r13.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.content.Context r3 = r13.getContext()
            java.util.Locale r3 = android.support.p002v7.appcompat.R$style.getLocale(r3)
            java.lang.String r4 = r3.getISO3Language()
            java.lang.String r5 = "fas"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0038
            java.text.NumberFormat r3 = java.text.DecimalFormat.getInstance(r3)
            goto L_0x003e
        L_0x0038:
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.text.NumberFormat r3 = java.text.DecimalFormat.getInstance(r3)
        L_0x003e:
            r4 = r1
        L_0x003f:
            int[] r5 = BUTTON_IDS
            int r6 = r5.length
            r7 = 2131297021(0x7f0902fd, float:1.8211975E38)
            if (r4 >= r6) goto L_0x013b
            r5 = r5[r4]
            android.view.View r5 = r13.findViewById(r5)
            com.android.dialer.dialpadview.DialpadKeyButton r5 = (com.android.dialer.dialpadview.DialpadKeyButton) r5
            r6 = 2131296506(0x7f0900fa, float:1.821093E38)
            android.view.View r6 = r5.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            int[] r8 = BUTTON_IDS
            r9 = r8[r4]
            r10 = 2131296762(0x7f0901fa, float:1.821145E38)
            if (r9 != r10) goto L_0x0069
            r7 = 2131820926(0x7f11017e, float:1.927458E38)
            java.lang.String r7 = r0.getString(r7)
            goto L_0x0081
        L_0x0069:
            r9 = r8[r4]
            r10 = 2131296898(0x7f090282, float:1.8211726E38)
            if (r9 != r10) goto L_0x0078
            r7 = 2131820927(0x7f11017f, float:1.9274583E38)
            java.lang.String r7 = r0.getString(r7)
            goto L_0x0081
        L_0x0078:
            r8 = r8[r4]
            if (r8 != r7) goto L_0x0083
            long r7 = (long) r4
            java.lang.String r7 = r3.format(r7)
        L_0x0081:
            r9 = r7
            goto L_0x00c5
        L_0x0083:
            long r7 = (long) r4
            java.lang.String r7 = r3.format(r7)
            java.lang.String[] r8 = r13.primaryLettersMapping
            r8 = r8[r4]
            android.text.Spannable$Factory r9 = android.text.Spannable.Factory.getInstance()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r7)
            java.lang.String r11 = ","
            r10.append(r11)
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            android.text.Spannable r9 = r9.newSpannable(r10)
            android.text.style.TtsSpan$VerbatimBuilder r10 = new android.text.style.TtsSpan$VerbatimBuilder
            r10.<init>(r8)
            android.text.style.TtsSpan r10 = r10.build()
            int r11 = r7.length()
            int r11 = r11 + r2
            int r12 = r7.length()
            int r12 = r12 + r2
            int r8 = r8.length()
            int r8 = r8 + r12
            r12 = 33
            r9.setSpan(r10, r11, r8, r12)
        L_0x00c5:
            android.content.Context r8 = r13.getContext()
            r10 = 2131230831(0x7f08006f, float:1.8077726E38)
            android.graphics.drawable.Drawable r8 = r8.getDrawable(r10)
            android.graphics.drawable.RippleDrawable r8 = (android.graphics.drawable.RippleDrawable) r8
            android.content.res.ColorStateList r10 = r13.rippleColor
            if (r10 == 0) goto L_0x00d9
            r8.setColor(r10)
        L_0x00d9:
            r6.setText(r7)
            r6.setElegantTextHeight(r1)
            r5.setContentDescription(r9)
            r5.setBackground(r8)
            r6 = 2131296505(0x7f0900f9, float:1.8210929E38)
            android.view.View r6 = r5.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            r7 = 2131296507(0x7f0900fb, float:1.8210933E38)
            android.view.View r5 = r5.findViewById(r7)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r6 == 0) goto L_0x0100
            java.lang.String[] r7 = r13.primaryLettersMapping
            r7 = r7[r4]
            r6.setText(r7)
        L_0x0100:
            if (r6 == 0) goto L_0x0137
            if (r5 == 0) goto L_0x0137
            java.lang.String[] r7 = r13.secondaryLettersMapping
            if (r7 != 0) goto L_0x010e
            r6 = 8
            r5.setVisibility(r6)
            goto L_0x0137
        L_0x010e:
            r5.setVisibility(r1)
            java.lang.String[] r7 = r13.secondaryLettersMapping
            r7 = r7[r4]
            r5.setText(r7)
            android.content.Context r7 = r13.getContext()
            android.content.res.Resources$Theme r7 = r7.getTheme()
            android.util.AttributeSet r8 = r13.attributeSet
            int[] r9 = com.android.dialer.dialpadview.R$styleable.Dialpad
            android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r9, r1, r1)
            r8 = 3
            int r8 = r7.getDimensionPixelSize(r8, r1)
            r7.recycle()
            float r7 = (float) r8
            r6.setTextSize(r1, r7)
            r5.setTextSize(r1, r7)
        L_0x0137:
            int r4 = r4 + 1
            goto L_0x003f
        L_0x013b:
            r1 = 2131296727(0x7f0901d7, float:1.8211379E38)
            android.view.View r1 = r13.findViewById(r1)
            com.android.dialer.dialpadview.DialpadKeyButton r1 = (com.android.dialer.dialpadview.DialpadKeyButton) r1
            r3 = 2131820891(0x7f11015b, float:1.927451E38)
            java.lang.CharSequence r3 = r0.getText(r3)
            r1.setLongHoverContentDescription(r3)
            android.view.View r1 = r13.findViewById(r7)
            com.android.dialer.dialpadview.DialpadKeyButton r1 = (com.android.dialer.dialpadview.DialpadKeyButton) r1
            r3 = 2131820864(0x7f110140, float:1.9274455E38)
            java.lang.CharSequence r0 = r0.getText(r3)
            r1.setLongHoverContentDescription(r0)
            r0 = 2131296515(0x7f090103, float:1.8210949E38)
            android.view.View r0 = r13.findViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            r13.digits = r0
            r0 = 2131296517(0x7f090105, float:1.8210953E38)
            android.view.View r0 = r13.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r13.digitsHint = r0
            r0 = 2131296478(0x7f0900de, float:1.8210874E38)
            android.view.View r0 = r13.findViewById(r0)
            android.widget.ImageButton r0 = (android.widget.ImageButton) r0
            r13.delete = r0
            r0 = 2131296509(0x7f0900fd, float:1.8210937E38)
            android.view.View r0 = r13.findViewById(r0)
            r13.overflowMenuButton = r0
            r0 = 2131296781(0x7f09020d, float:1.8211488E38)
            android.view.View r0 = r13.findViewById(r0)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            r13.rateContainer = r0
            android.view.ViewGroup r0 = r13.rateContainer
            r1 = 2131296594(0x7f090152, float:1.821111E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            android.view.ViewGroup r0 = r13.rateContainer
            r1 = 2131296595(0x7f090153, float:1.8211111E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            android.content.Context r0 = r13.getContext()
            java.lang.String r1 = "accessibility"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.accessibility.AccessibilityManager r0 = (android.view.accessibility.AccessibilityManager) r0
            boolean r0 = r0.isEnabled()
            if (r0 == 0) goto L_0x01c0
            android.widget.EditText r0 = r13.digits
            r0.setSelected(r2)
        L_0x01c0:
            android.view.ViewTreeObserver r0 = r13.getViewTreeObserver()
            com.android.dialer.dialpadview.DialpadView$OnPreDrawListenerForKeyLayoutAdjust r1 = r13.onPreDrawListenerForKeyLayoutAdjust
            r0.removeOnPreDrawListener(r1)
            android.view.ViewTreeObserver r0 = r13.getViewTreeObserver()
            com.android.dialer.dialpadview.DialpadView$OnPreDrawListenerForKeyLayoutAdjust r13 = r13.onPreDrawListenerForKeyLayoutAdjust
            r0.addOnPreDrawListener(r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.dialpadview.DialpadView.onFinishInflate():void");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        return true;
    }

    public void setCanDigitsBeEdited(boolean z) {
        findViewById(R.id.deleteButton).setVisibility(z ? 0 : 4);
        findViewById(R.id.dialpad_overflow).setVisibility(z ? 0 : 8);
        EditText editText = (EditText) findViewById(R.id.digits);
        editText.setClickable(z);
        editText.setLongClickable(z);
        editText.setFocusableInTouchMode(z);
        editText.setCursorVisible(false);
    }

    public DialpadView(Context context, AttributeSet attributeSet2) {
        this(context, attributeSet2, 0);
    }

    public DialpadView(Context context, AttributeSet attributeSet2, int i) {
        super(context, attributeSet2, i);
        this.attributeSet = attributeSet2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet2, R$styleable.Dialpad);
        this.rippleColor = obtainStyledAttributes.getColorStateList(2);
        obtainStyledAttributes.recycle();
        this.translateDistance = getResources().getDimensionPixelSize(R.dimen.dialpad_key_button_translate_y);
        this.isRtl = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1 ? false : true;
        this.primaryLettersMapping = DialpadCharMappings.getDefaultKeyToCharsMap();
        this.secondaryLettersMapping = DialpadCharMappings.getKeyToCharsMap(context);
        this.onPreDrawListenerForKeyLayoutAdjust = new OnPreDrawListenerForKeyLayoutAdjust((C04781) null);
    }
}
