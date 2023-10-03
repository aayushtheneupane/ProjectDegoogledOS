package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.messaging.R;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.Oa */
public class C1060Oa extends LinearLayout {

    /* renamed from: th */
    private int f1660th;

    /* renamed from: uh */
    private final Paint f1661uh = new Paint();

    /* renamed from: vh */
    private int f1662vh;

    /* renamed from: wh */
    private float f1663wh;

    public C1060Oa(Context context) {
        super(context, (AttributeSet) null);
        Resources resources = context.getResources();
        this.f1660th = resources.getDimensionPixelSize(R.dimen.pager_tab_underline_selected);
        int color = resources.getColor(R.color.contact_picker_tab_underline);
        int color2 = resources.getColor(R.color.action_bar_background_color);
        this.f1661uh.setColor(color);
        setBackgroundColor(color2);
        setWillNotDraw(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(this.f1662vh);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            boolean z = false;
            int i = 1;
            boolean z2 = C1464na.m3756Wj() && getLayoutDirection() == 1;
            if (!z2 ? this.f1662vh < getChildCount() - 1 : this.f1662vh > 0) {
                z = true;
            }
            if (this.f1663wh > 0.0f && z) {
                int i2 = this.f1662vh;
                if (z2) {
                    i = -1;
                }
                View childAt2 = getChildAt(i2 + i);
                int left2 = childAt2.getLeft();
                int right2 = childAt2.getRight();
                float f = this.f1663wh;
                left = (int) (((1.0f - f) * ((float) left)) + (((float) left2) * f));
                right = (int) (((1.0f - f) * ((float) right)) + (((float) right2) * f));
            }
            int height = getHeight();
            canvas.drawRect((float) left, (float) (height - this.f1660th), (float) right, (float) height, this.f1661uh);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPageScrolled(int i, float f, int i2) {
        this.f1662vh = i;
        this.f1663wh = f;
        invalidate();
    }
}
