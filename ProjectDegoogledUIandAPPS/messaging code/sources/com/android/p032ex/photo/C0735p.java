package com.android.p032ex.photo;

import android.view.View;
import androidx.viewpager.widget.C0627l;

/* renamed from: com.android.ex.photo.p */
class C0735p implements C0627l {
    C0735p(PhotoViewPager photoViewPager) {
    }

    public void transformPage(View view, float f) {
        if (f < 0.0f || f >= 1.0f) {
            view.setTranslationX(0.0f);
            view.setAlpha(1.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return;
        }
        view.setTranslationX((-f) * ((float) view.getWidth()));
        view.setAlpha(Math.max(0.0f, 1.0f - f));
        float max = Math.max(0.0f, 1.0f - (f * 0.3f));
        view.setScaleX(max);
        view.setScaleY(max);
    }
}
