package android.support.p002v7.widget;

import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;

/* renamed from: android.support.v7.widget.ScrollingTabContainerView */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    static {
        new DecelerateInterpolator();
    }
}
