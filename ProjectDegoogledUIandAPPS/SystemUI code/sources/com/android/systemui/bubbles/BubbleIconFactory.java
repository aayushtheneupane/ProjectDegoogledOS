package com.android.systemui.bubbles;

import android.content.Context;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.R$dimen;
import com.android.systemui.C1775R$dimen;

public class BubbleIconFactory extends BaseIconFactory {
    protected BubbleIconFactory(Context context) {
        super(context, context.getResources().getConfiguration().densityDpi, context.getResources().getDimensionPixelSize(C1775R$dimen.individual_bubble_size));
    }

    public int getBadgeSize() {
        return this.mContext.getResources().getDimensionPixelSize(R$dimen.profile_badge_size);
    }
}
