package com.android.contacts.common.list;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;

public class ViewPagerTabs extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private static final int[] ATTRS = {16842901, 16842903, 16842904, 16843660};
    private static final ViewOutlineProvider VIEW_BOUNDS_OUTLINE_PROVIDER = new ViewOutlineProvider() {
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
        }
    };
    ViewPager mPager;
    int mPrevSelected;
    int mSidePadding;
    private int[] mTabIcons;
    private ViewPagerTabStrip mTabStrip;
    final boolean mTextAllCaps;
    final ColorStateList mTextColor;
    final int mTextSize;
    final int mTextStyle;
    private int[] mUnreadCounts;

    private class OnTabLongClickListener implements View.OnLongClickListener {
        final int mPosition;

        public OnTabLongClickListener(int i) {
            this.mPosition = i;
        }

        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            ViewPagerTabs.this.getLocationOnScreen(iArr);
            Context context = ViewPagerTabs.this.getContext();
            int width = ViewPagerTabs.this.getWidth();
            int height = ViewPagerTabs.this.getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, ViewPagerTabs.this.mPager.getAdapter().getPageTitle(this.mPosition), 0);
            makeText.setGravity(49, ((width / 2) + iArr[0]) - (i / 2), iArr[1] + height);
            makeText.show();
            return true;
        }
    }

    public ViewPagerTabs(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    private void addTab(CharSequence charSequence, final int i) {
        TextView textView;
        int[] iArr = this.mTabIcons;
        if (iArr == null || i >= iArr.length) {
            TextView textView2 = new TextView(getContext());
            textView2.setText(charSequence);
            textView2.setClickable(true);
            if (this.mTextStyle > 0) {
                textView2.setTypeface(textView2.getTypeface(), this.mTextStyle);
            }
            int i2 = this.mTextSize;
            if (i2 > 0) {
                textView2.setTextSize(0, (float) i2);
            }
            ColorStateList colorStateList = this.mTextColor;
            if (colorStateList != null) {
                textView2.setTextColor(colorStateList);
            }
            textView2.setAllCaps(this.mTextAllCaps);
            textView2.setGravity(17);
            textView = textView2;
        } else {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.unread_count_tab, (ViewGroup) null);
            View findViewById = inflate.findViewById(R.id.icon);
            findViewById.setBackgroundResource(this.mTabIcons[i]);
            findViewById.setContentDescription(charSequence);
            TextView textView3 = (TextView) inflate.findViewById(R.id.count);
            int[] iArr2 = this.mUnreadCounts;
            if (iArr2 == null || iArr2[i] <= 0) {
                textView3.setVisibility(4);
                findViewById.setContentDescription(getResources().getString(R.string.tab_title, new Object[]{charSequence}));
                textView = inflate;
            } else {
                textView3.setText(Integer.toString(iArr2[i]));
                textView3.setVisibility(0);
                findViewById.setContentDescription(getResources().getQuantityString(R.plurals.tab_title_with_unread_items, this.mUnreadCounts[i], new Object[]{charSequence.toString(), Integer.valueOf(this.mUnreadCounts[i])}));
                textView = inflate;
            }
        }
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ViewPagerTabs viewPagerTabs = ViewPagerTabs.this;
                viewPagerTabs.mPager.setCurrentItem(viewPagerTabs.getRtlPosition(i));
            }
        });
        textView.setOnLongClickListener(new OnTabLongClickListener(i));
        int i3 = this.mSidePadding;
        textView.setPadding(i3, 0, i3, 0);
        this.mTabStrip.addView(textView, i, new LinearLayout.LayoutParams(-2, -1, 1.0f));
        if (i == 0) {
            this.mPrevSelected = 0;
            textView.setSelected(true);
        }
    }

    /* access modifiers changed from: private */
    public int getRtlPosition(int i) {
        return getLayoutDirection() == 1 ? (this.mTabStrip.getChildCount() - 1) - i : i;
    }

    public void configureTabIcons(int[] iArr) {
        this.mTabIcons = iArr;
        this.mUnreadCounts = new int[iArr.length];
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
        int rtlPosition = getRtlPosition(i);
        int childCount = this.mTabStrip.getChildCount();
        if (childCount != 0 && rtlPosition >= 0 && rtlPosition < childCount) {
            this.mTabStrip.onPageScrolled(rtlPosition, f, i2);
        }
    }

    public void onPageSelected(int i) {
        int rtlPosition = getRtlPosition(i);
        int childCount = this.mTabStrip.getChildCount();
        if (childCount != 0 && rtlPosition >= 0 && rtlPosition < childCount) {
            int i2 = this.mPrevSelected;
            if (i2 >= 0 && i2 < childCount) {
                this.mTabStrip.getChildAt(i2).setSelected(false);
            }
            View childAt = this.mTabStrip.getChildAt(rtlPosition);
            childAt.setSelected(true);
            smoothScrollTo(childAt.getLeft() - ((getWidth() - childAt.getWidth()) / 2), 0);
            this.mPrevSelected = rtlPosition;
        }
    }

    public void removeTab(int i) {
        View childAt = this.mTabStrip.getChildAt(i);
        if (childAt != null) {
            this.mTabStrip.removeView(childAt);
        }
    }

    public void setUnreadCount(int i, int i2) {
        int[] iArr = this.mUnreadCounts;
        if (iArr != null && i2 < iArr.length) {
            iArr[i2] = i;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.mPager = viewPager;
        PagerAdapter adapter = this.mPager.getAdapter();
        this.mTabStrip.removeAllViews();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            addTab(adapter.getPageTitle(i), i);
        }
    }

    public void updateTab(int i) {
        removeTab(i);
        if (i < this.mPager.getAdapter().getCount()) {
            addTab(this.mPager.getAdapter().getPageTitle(i), i);
        }
    }

    public ViewPagerTabs(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewPagerTabs(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPrevSelected = -1;
        setFillViewport(true);
        this.mSidePadding = (int) (getResources().getDisplayMetrics().density * 10.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mTextStyle = obtainStyledAttributes.getInt(1, 0);
        this.mTextColor = obtainStyledAttributes.getColorStateList(2);
        this.mTextAllCaps = obtainStyledAttributes.getBoolean(3, false);
        this.mTabStrip = new ViewPagerTabStrip(context);
        addView(this.mTabStrip, new FrameLayout.LayoutParams(-2, -1));
        obtainStyledAttributes.recycle();
        setOutlineProvider(VIEW_BOUNDS_OUTLINE_PROVIDER);
    }
}
