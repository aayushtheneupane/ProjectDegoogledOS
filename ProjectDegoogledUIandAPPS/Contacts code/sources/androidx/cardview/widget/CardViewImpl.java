package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;

interface CardViewImpl {
    float getMinHeight(CardViewDelegate cardViewDelegate);

    float getMinWidth(CardViewDelegate cardViewDelegate);

    void initStatic();

    void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f, float f2, float f3);
}
