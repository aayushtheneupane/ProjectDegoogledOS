package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.p002v7.widget.CardView;

/* renamed from: android.support.v7.widget.CardViewApi21Impl */
class CardViewApi21Impl implements CardViewImpl {
    CardViewApi21Impl() {
    }

    private RoundRectDrawable getCardBackground(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawable) ((CardView.C01951) cardViewDelegate).getCardBackground();
    }

    public float getMinHeight(CardViewDelegate cardViewDelegate) {
        return getCardBackground(cardViewDelegate).getRadius() * 2.0f;
    }

    public float getMinWidth(CardViewDelegate cardViewDelegate) {
        return getCardBackground(cardViewDelegate).getRadius() * 2.0f;
    }

    public void initStatic() {
    }

    public void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        CardView.C01951 r1 = (CardView.C01951) cardViewDelegate;
        r1.setCardBackground(new RoundRectDrawable(colorStateList, f));
        CardView cardView = CardView.this;
        cardView.setClipToOutline(true);
        cardView.setElevation(f2);
        getCardBackground(r1).setPadding(f3, r1.getUseCompatPadding(), r1.getPreventCornerOverlap());
        if (!r1.getUseCompatPadding()) {
            r1.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float padding = getCardBackground(r1).getPadding();
        float radius = getCardBackground(r1).getRadius();
        int ceil = (int) Math.ceil((double) RoundRectDrawableWithShadow.calculateHorizontalPadding(padding, radius, r1.getPreventCornerOverlap()));
        int ceil2 = (int) Math.ceil((double) RoundRectDrawableWithShadow.calculateVerticalPadding(padding, radius, r1.getPreventCornerOverlap()));
        r1.setShadowPadding(ceil, ceil2, ceil, ceil2);
    }
}
