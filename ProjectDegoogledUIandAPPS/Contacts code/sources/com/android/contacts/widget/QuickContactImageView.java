package com.android.contacts.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.contacts.R;
import com.android.contacts.lettertiles.LetterTileDrawable;

public class QuickContactImageView extends ImageView {
    private BitmapDrawable mBitmapDrawable;
    private boolean mIsBusiness;
    private Drawable mOriginalDrawable;
    private int mTintColor;

    public QuickContactImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public QuickContactImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QuickContactImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTint(int i) {
        BitmapDrawable bitmapDrawable = this.mBitmapDrawable;
        if (bitmapDrawable == null || bitmapDrawable.getBitmap() == null || this.mBitmapDrawable.getBitmap().hasAlpha()) {
            setBackgroundColor(i);
        } else {
            setBackground((Drawable) null);
        }
        this.mTintColor = i;
        postInvalidate();
    }

    public boolean isBasedOffLetterTile() {
        return this.mOriginalDrawable instanceof LetterTileDrawable;
    }

    public void setIsBusiness(boolean z) {
        this.mIsBusiness = z;
    }

    public void setImageDrawable(Drawable drawable) {
        BitmapDrawable bitmapDrawable;
        if (drawable == null || (drawable instanceof BitmapDrawable)) {
            bitmapDrawable = (BitmapDrawable) drawable;
        } else if (!(drawable instanceof LetterTileDrawable)) {
            throw new IllegalArgumentException("Does not support this type of drawable");
        } else if (!this.mIsBusiness) {
            bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.person_white_540dp);
        } else {
            bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.generic_business_white_540dp);
        }
        this.mOriginalDrawable = drawable;
        this.mBitmapDrawable = bitmapDrawable;
        setTint(this.mTintColor);
        super.setImageDrawable(bitmapDrawable);
    }

    public Drawable getDrawable() {
        return this.mOriginalDrawable;
    }
}
