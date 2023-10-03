package com.android.dialer.main.impl.bottomnav;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class BottomNavItem extends LinearLayout {
    private ImageView image;
    private TextView notificationBadge;
    private TextView text;

    public BottomNavItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.image = (ImageView) findViewById(R.id.bottom_nav_item_image);
        this.text = (TextView) findViewById(R.id.bottom_nav_item_text);
        this.notificationBadge = (TextView) findViewById(R.id.notification_badge);
    }

    /* access modifiers changed from: package-private */
    public void setNotificationCount(int i) {
        int i2;
        Assert.checkArgument(i >= 0, GeneratedOutlineSupport.outline5("Invalid count: ", i), new Object[0]);
        if (i == 0) {
            this.notificationBadge.setVisibility(4);
            return;
        }
        String format = String.format(Integer.toString(i), new Object[0]);
        boolean z = ((SharedPrefConfigProvider) ConfigProviderComponent.get(getContext()).getConfigProvider()).getBoolean("use_99_plus", false);
        boolean z2 = !z;
        if (z && i > 99) {
            format = getContext().getString(R.string.bottom_nav_count_99_plus);
        } else if (z2 && i > 9) {
            format = getContext().getString(R.string.bottom_nav_count_9_plus);
        }
        this.notificationBadge.setVisibility(0);
        this.notificationBadge.setText(format);
        if (format.length() == 1) {
            i2 = getContext().getResources().getDimensionPixelSize(R.dimen.badge_margin_length_1);
        } else if (format.length() == 2) {
            i2 = getContext().getResources().getDimensionPixelSize(R.dimen.badge_margin_length_2);
        } else {
            i2 = getContext().getResources().getDimensionPixelSize(R.dimen.badge_margin_length_3);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.image.getLayoutParams();
        layoutParams.setMarginStart(i2);
        layoutParams.setMarginEnd(i2);
        this.image.setLayoutParams(layoutParams);
    }

    public void setSelected(boolean z) {
        int i;
        super.setSelected(z);
        if (z) {
            i = ((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorPrimary();
        } else {
            i = ((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getTextColorSecondary();
        }
        this.image.setImageTintList(ColorStateList.valueOf(i));
        this.text.setTextColor(i);
    }

    /* access modifiers changed from: package-private */
    public void setup(int i, int i2) {
        this.text.setText(i);
        this.image.setImageResource(i2);
    }
}
