package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.C0616a;
import androidx.viewpager.widget.C0626k;
import androidx.viewpager.widget.ViewPager;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.ViewPagerTabs */
public class ViewPagerTabs extends HorizontalScrollView implements C0626k {
    private static final int[] ATTRS = {16842901, 16842903, 16842904, 16843660};

    /* renamed from: _e */
    ViewPager f1691_e;

    /* renamed from: af */
    final boolean f1692af;

    /* renamed from: bf */
    int f1693bf;

    /* renamed from: cf */
    int f1694cf;

    /* renamed from: kb */
    private C1060Oa f1695kb;
    final ColorStateList mTextColor;
    final int mTextSize;
    final int mTextStyle;

    public ViewPagerTabs(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* access modifiers changed from: private */
    /* renamed from: A */
    public int m2664A(int i) {
        return (!C1464na.m3756Wj() || C0967f.get().getApplicationContext().getResources().getConfiguration().getLayoutDirection() != 1) ? i : (this.f1695kb.getChildCount() - 1) - i;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
        int A = m2664A(i);
        int childCount = this.f1695kb.getChildCount();
        if (childCount != 0 && A >= 0 && A < childCount) {
            this.f1695kb.onPageScrolled(A, f, i2);
        }
    }

    public void onPageSelected(int i) {
        int A = m2664A(i);
        int childCount = this.f1695kb.getChildCount();
        if (childCount != 0 && A >= 0 && A < childCount) {
            int i2 = this.f1693bf;
            if (i2 >= 0 && i2 < childCount) {
                this.f1695kb.getChildAt(i2).setSelected(false);
            }
            View childAt = this.f1695kb.getChildAt(A);
            childAt.setSelected(true);
            smoothScrollTo(childAt.getLeft() - ((getWidth() - childAt.getWidth()) / 2), 0);
            this.f1693bf = A;
        }
    }

    public ViewPagerTabs(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: a */
    public void mo7106a(ViewPager viewPager) {
        this.f1691_e = viewPager;
        C0616a adapter = this.f1691_e.getAdapter();
        this.f1695kb.removeAllViews();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence pageTitle = adapter.getPageTitle(i);
            TextView textView = new TextView(getContext());
            textView.setText(pageTitle);
            textView.setBackgroundResource(R.drawable.contact_picker_tab_background_selector);
            textView.setGravity(17);
            textView.setOnClickListener(new C1064Qa(this, i));
            if (this.mTextStyle > 0) {
                textView.setTypeface(textView.getTypeface(), this.mTextStyle);
            }
            int i2 = this.mTextSize;
            if (i2 > 0) {
                textView.setTextSize(0, (float) i2);
            }
            ColorStateList colorStateList = this.mTextColor;
            if (colorStateList != null) {
                textView.setTextColor(colorStateList);
            }
            textView.setAllCaps(this.f1692af);
            int i3 = this.f1694cf;
            textView.setPadding(i3, 0, i3, 0);
            this.f1695kb.addView(textView, new LinearLayout.LayoutParams(-2, -1, 1.0f));
            if (i == 0) {
                this.f1693bf = 0;
                textView.setSelected(true);
            }
        }
    }

    public ViewPagerTabs(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1693bf = -1;
        setFillViewport(true);
        this.f1694cf = (int) (getResources().getDisplayMetrics().density * 10.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mTextStyle = obtainStyledAttributes.getInt(1, 0);
        this.mTextColor = obtainStyledAttributes.getColorStateList(2);
        this.f1692af = obtainStyledAttributes.getBoolean(3, false);
        this.f1695kb = new C1060Oa(context);
        addView(this.f1695kb, new FrameLayout.LayoutParams(-2, -1));
        obtainStyledAttributes.recycle();
        if (C1464na.m3758Yj()) {
            setOutlineProvider(new C1062Pa(this));
        }
    }
}
