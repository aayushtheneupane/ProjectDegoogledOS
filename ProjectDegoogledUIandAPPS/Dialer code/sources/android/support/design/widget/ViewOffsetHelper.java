package android.support.design.widget;

import android.os.Build;
import android.support.p000v4.view.ViewCompat;
import android.view.View;

class ViewOffsetHelper {
    private int layoutLeft;
    private int layoutTop;
    private int offsetLeft;
    private int offsetTop;
    private final View view;

    public ViewOffsetHelper(View view2) {
        this.view = view2;
    }

    private void updateOffsets() {
        View view2 = this.view;
        ViewCompat.offsetTopAndBottom(view2, this.offsetTop - (view2.getTop() - this.layoutTop));
        View view3 = this.view;
        int i = Build.VERSION.SDK_INT;
        view3.offsetLeftAndRight(this.offsetLeft - (view3.getLeft() - this.layoutLeft));
    }

    public int getTopAndBottomOffset() {
        return this.offsetTop;
    }

    public void onViewLayout() {
        this.layoutTop = this.view.getTop();
        this.layoutLeft = this.view.getLeft();
        updateOffsets();
    }

    public boolean setLeftAndRightOffset(int i) {
        if (this.offsetLeft == i) {
            return false;
        }
        this.offsetLeft = i;
        updateOffsets();
        return true;
    }

    public boolean setTopAndBottomOffset(int i) {
        if (this.offsetTop == i) {
            return false;
        }
        this.offsetTop = i;
        updateOffsets();
        return true;
    }
}
