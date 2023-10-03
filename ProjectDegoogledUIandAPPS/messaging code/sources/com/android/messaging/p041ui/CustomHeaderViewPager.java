package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewpager.widget.C0616a;
import androidx.viewpager.widget.C0626k;
import androidx.viewpager.widget.ViewPager;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.CustomHeaderViewPager */
public class CustomHeaderViewPager extends LinearLayout {

    /* renamed from: lb */
    private ViewPager f1616lb = ((ViewPager) findViewById(R.id.pager));

    /* renamed from: nh */
    private final int f1617nh;
    /* access modifiers changed from: private */

    /* renamed from: oh */
    public ViewPagerTabs f1618oh = ((ViewPagerTabs) findViewById(R.id.tab_strip));

    public CustomHeaderViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.custom_header_view_pager, this, true);
        setOrientation(1);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843499, typedValue, true);
        this.f1617nh = context.getResources().getDimensionPixelSize(typedValue.resourceId);
    }

    public void setCurrentItem(int i) {
        this.f1616lb.setCurrentItem(i);
    }

    /* renamed from: v */
    public void mo6935v(int i) {
        ViewGroup.LayoutParams layoutParams = this.f1618oh.getLayoutParams();
        if (i == -1) {
            i = this.f1617nh;
        }
        layoutParams.height = i;
    }

    /* renamed from: a */
    public void mo6933a(C1051K[] kArr) {
        C1424b.m3594t(this.f1616lb);
        this.f1616lb.mo5330a((C0616a) new C1055M(kArr));
        this.f1618oh.mo7106a(this.f1616lb);
        this.f1616lb.mo5331a((C0626k) new C1053L(this));
    }
}
