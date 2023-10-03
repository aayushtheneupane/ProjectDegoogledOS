package com.android.contacts.common.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.dialer.R;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;

public class ViewPagerTabStrip extends LinearLayout {
    private int mIndexForSelection;
    private final Paint mSelectedUnderlinePaint = new Paint();
    private int mSelectedUnderlineThickness;
    private float mSelectionOffset;

    public ViewPagerTabStrip(Context context) {
        super(context, (AttributeSet) null);
        this.mSelectedUnderlineThickness = context.getResources().getDimensionPixelSize(R.dimen.tab_selected_underline_height);
        int colorAccent = ((AospThemeImpl) ThemeComponent.get(context).theme()).getColorAccent();
        int colorPrimary = ((AospThemeImpl) ThemeComponent.get(context).theme()).getColorPrimary();
        this.mSelectedUnderlinePaint.setColor(colorAccent);
        setBackgroundColor(colorPrimary);
        setWillNotDraw(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        View childAt;
        if (getChildCount() > 0 && (childAt = getChildAt(this.mIndexForSelection)) != null) {
            int left = childAt.getLeft();
            int right = childAt.getRight();
            boolean z = false;
            int i = 1;
            boolean z2 = getLayoutDirection() == 1;
            int i2 = this.mIndexForSelection;
            if (!z2 ? i2 < getChildCount() - 1 : i2 > 0) {
                z = true;
            }
            if (this.mSelectionOffset > 0.0f && z) {
                int i3 = this.mIndexForSelection;
                if (z2) {
                    i = -1;
                }
                View childAt2 = getChildAt(i3 + i);
                int left2 = childAt2.getLeft();
                int right2 = childAt2.getRight();
                float f = this.mSelectionOffset;
                left = (int) (((1.0f - f) * ((float) left)) + (((float) left2) * f));
                right = (int) (((1.0f - f) * ((float) right)) + (((float) right2) * f));
            }
            int height = getHeight();
            canvas.drawRect((float) left, (float) (height - this.mSelectedUnderlineThickness), (float) right, (float) height, this.mSelectedUnderlinePaint);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPageScrolled(int i, float f, int i2) {
        this.mIndexForSelection = i;
        this.mSelectionOffset = f;
        invalidate();
    }
}
