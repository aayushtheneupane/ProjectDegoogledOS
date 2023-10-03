package com.android.phone.common.dialpad;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.phone.common.R$styleable;
import java.util.Locale;

public class DialpadView extends LinearLayout {
    private final int[] mButtonIds;
    private ImageButton mDelete;
    private EditText mDigits;
    private TextView mIldCountry;
    private TextView mIldRate;
    private final boolean mIsLandscape;
    private final boolean mIsRtl;
    private View mOverflowMenuButton;
    private ViewGroup mRateContainer;
    private ColorStateList mRippleColor;
    private int mTranslateDistance;

    public boolean onHoverEvent(MotionEvent motionEvent) {
        return true;
    }

    public DialpadView(Context context) {
        this(context, (AttributeSet) null);
    }

    public DialpadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DialpadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mButtonIds = new int[]{R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.star, R.id.pound};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Dialpad);
        boolean z = false;
        this.mRippleColor = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        this.mTranslateDistance = getResources().getDimensionPixelSize(R.dimen.dialpad_key_button_translate_y);
        this.mIsLandscape = getResources().getConfiguration().orientation == 2;
        this.mIsRtl = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? true : z;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        setupKeypad();
        this.mDigits = (EditText) findViewById(R.id.digits);
        this.mDelete = (ImageButton) findViewById(R.id.deleteButton);
        this.mOverflowMenuButton = findViewById(R.id.dialpad_overflow);
        this.mRateContainer = (ViewGroup) findViewById(R.id.rate_container);
        this.mIldCountry = (TextView) this.mRateContainer.findViewById(R.id.ild_country);
        this.mIldRate = (TextView) this.mRateContainer.findViewById(R.id.ild_rate);
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            this.mDigits.setSelected(true);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: android.text.Spannable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupKeypad() {
        /*
            r14 = this;
            r0 = 12
            int[] r0 = new int[r0]
            r0 = {2131755282, 2131755283, 2131755284, 2131755285, 2131755286, 2131755287, 2131755288, 2131755289, 2131755290, 2131755291, 2131755294, 2131755292} // fill-array
            android.content.Context r1 = r14.getContext()
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r2 = r1.getConfiguration()
            java.util.Locale r2 = r2.locale
            java.lang.String r2 = r2.getLanguage()
            java.lang.String r3 = "fa"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x002c
            android.content.res.Configuration r2 = r1.getConfiguration()
            java.util.Locale r2 = r2.locale
            java.text.NumberFormat r2 = java.text.DecimalFormat.getInstance(r2)
            goto L_0x0032
        L_0x002c:
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.text.NumberFormat r2 = java.text.DecimalFormat.getInstance(r2)
        L_0x0032:
            r3 = 0
            r4 = 0
        L_0x0034:
            int[] r5 = r14.mButtonIds
            int r6 = r5.length
            if (r4 >= r6) goto L_0x00e9
            r5 = r5[r4]
            android.view.View r5 = r14.findViewById(r5)
            com.android.phone.common.dialpad.DialpadKeyButton r5 = (com.android.phone.common.dialpad.DialpadKeyButton) r5
            r6 = 2131296444(0x7f0900bc, float:1.8210805E38)
            android.view.View r6 = r5.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            r7 = 2131296443(0x7f0900bb, float:1.8210803E38)
            android.view.View r7 = r5.findViewById(r7)
            android.widget.TextView r7 = (android.widget.TextView) r7
            int[] r8 = r14.mButtonIds
            r9 = r8[r4]
            r10 = 2131296630(0x7f090176, float:1.8211182E38)
            if (r9 != r10) goto L_0x0065
            r8 = 2131755293(0x7f10011d, float:1.9141461E38)
            java.lang.String r8 = r1.getString(r8)
        L_0x0063:
            r10 = r8
            goto L_0x00ba
        L_0x0065:
            r8 = r8[r4]
            r9 = 2131296699(0x7f0901bb, float:1.8211322E38)
            if (r8 != r9) goto L_0x0074
            r8 = 2131755295(0x7f10011f, float:1.9141465E38)
            java.lang.String r8 = r1.getString(r8)
            goto L_0x0063
        L_0x0074:
            long r8 = (long) r4
            java.lang.String r8 = r2.format(r8)
            r9 = r0[r4]
            java.lang.String r9 = r1.getString(r9)
            android.text.Spannable$Factory r10 = android.text.Spannable.Factory.getInstance()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r8)
            java.lang.String r12 = ","
            r11.append(r12)
            r11.append(r9)
            java.lang.String r11 = r11.toString()
            android.text.Spannable r10 = r10.newSpannable(r11)
            android.text.style.TtsSpan$VerbatimBuilder r11 = new android.text.style.TtsSpan$VerbatimBuilder
            r11.<init>(r9)
            android.text.style.TtsSpan r11 = r11.build()
            int r12 = r8.length()
            int r12 = r12 + 1
            int r13 = r8.length()
            int r13 = r13 + 1
            int r9 = r9.length()
            int r13 = r13 + r9
            r9 = 33
            r10.setSpan(r11, r12, r13, r9)
        L_0x00ba:
            android.content.Context r9 = r14.getContext()
            r11 = 2131230819(0x7f080063, float:1.8077702E38)
            android.graphics.drawable.Drawable r9 = r14.getDrawableCompat(r9, r11)
            android.graphics.drawable.RippleDrawable r9 = (android.graphics.drawable.RippleDrawable) r9
            android.content.res.ColorStateList r11 = r14.mRippleColor
            if (r11 == 0) goto L_0x00ce
            r9.setColor(r11)
        L_0x00ce:
            r6.setText(r8)
            r6.setElegantTextHeight(r3)
            r5.setContentDescription(r10)
            r5.setBackground(r9)
            if (r7 == 0) goto L_0x00e5
            r5 = r0[r4]
            java.lang.String r5 = r1.getString(r5)
            r7.setText(r5)
        L_0x00e5:
            int r4 = r4 + 1
            goto L_0x0034
        L_0x00e9:
            r0 = 2131296612(0x7f090164, float:1.8211146E38)
            android.view.View r0 = r14.findViewById(r0)
            com.android.phone.common.dialpad.DialpadKeyButton r0 = (com.android.phone.common.dialpad.DialpadKeyButton) r0
            r2 = 2131755276(0x7f10010c, float:1.9141427E38)
            java.lang.CharSequence r2 = r1.getText(r2)
            r0.setLongHoverContentDescription(r2)
            r0 = 2131296774(0x7f090206, float:1.8211474E38)
            android.view.View r0 = r14.findViewById(r0)
            com.android.phone.common.dialpad.DialpadKeyButton r0 = (com.android.phone.common.dialpad.DialpadKeyButton) r0
            r2 = 2131755270(0x7f100106, float:1.9141415E38)
            java.lang.CharSequence r1 = r1.getText(r2)
            r0.setLongHoverContentDescription(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.common.dialpad.DialpadView.setupKeypad():void");
    }

    private Drawable getDrawableCompat(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }
}
