package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;

/* renamed from: android.support.v7.widget.CardViewImpl */
interface CardViewImpl {
    float getMinHeight(CardViewDelegate cardViewDelegate);

    float getMinWidth(CardViewDelegate cardViewDelegate);

    void initStatic();

    void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f, float f2, float f3);
}
