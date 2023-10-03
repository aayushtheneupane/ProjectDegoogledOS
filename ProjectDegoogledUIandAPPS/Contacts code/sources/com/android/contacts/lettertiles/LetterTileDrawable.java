package com.android.contacts.lettertiles;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.google.common.base.Preconditions;

public class LetterTileDrawable extends Drawable {
    private static Bitmap DEFAULT_BUSINESS_AVATAR;
    private static Bitmap DEFAULT_PERSON_AVATAR;
    private static Bitmap DEFAULT_VOICEMAIL_AVATAR;
    private static TypedArray sColors;
    private static int sDefaultColor;
    private static final char[] sFirstChar = new char[1];
    private static float sLetterToTileRatio;
    private static final Paint sPaint = new Paint();
    private static final Rect sRect = new Rect();
    private static int sTileFontColor;
    private final String TAG = LetterTileDrawable.class.getSimpleName();
    private int mColor;
    private int mContactType = 1;
    private boolean mIsCircle = false;
    private Character mLetter = null;
    private float mOffset = ContactPhotoManager.OFFSET_DEFAULT;
    private final Paint mPaint;
    private float mScale = 1.0f;

    private static boolean isEnglishLetter(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    public int getOpacity() {
        return -1;
    }

    public LetterTileDrawable(Resources resources) {
        if (sColors == null) {
            sColors = resources.obtainTypedArray(R.array.letter_tile_colors);
            sDefaultColor = resources.getColor(R.color.letter_tile_default_color);
            sTileFontColor = resources.getColor(R.color.letter_tile_font_color);
            sLetterToTileRatio = resources.getFraction(R.dimen.letter_to_tile_ratio, 1, 1);
            DEFAULT_PERSON_AVATAR = BitmapFactory.decodeResource(resources, R.drawable.ic_person_avatar);
            DEFAULT_BUSINESS_AVATAR = BitmapFactory.decodeResource(resources, R.drawable.ic_business_white_120dp);
            DEFAULT_VOICEMAIL_AVATAR = BitmapFactory.decodeResource(resources, R.drawable.ic_voicemail_avatar);
            sPaint.setTypeface(Typeface.create(resources.getString(R.string.letter_tile_letter_font_family), 0));
            sPaint.setTextAlign(Paint.Align.CENTER);
            sPaint.setAntiAlias(true);
        }
        this.mPaint = new Paint();
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setDither(true);
        this.mColor = sDefaultColor;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (isVisible() && !bounds.isEmpty()) {
            drawLetterTile(canvas);
        }
    }

    private void drawBitmap(Bitmap bitmap, int i, int i2, Canvas canvas) {
        Rect copyBounds = copyBounds();
        int min = (int) ((this.mScale * ((float) Math.min(copyBounds.width(), copyBounds.height()))) / 2.0f);
        copyBounds.set(copyBounds.centerX() - min, (int) (((float) (copyBounds.centerY() - min)) + (this.mOffset * ((float) copyBounds.height()))), copyBounds.centerX() + min, (int) (((float) (copyBounds.centerY() + min)) + (this.mOffset * ((float) copyBounds.height()))));
        sRect.set(0, 0, i, i2);
        sPaint.setTextAlign(Paint.Align.CENTER);
        sPaint.setAntiAlias(true);
        sPaint.setAlpha(138);
        canvas.drawBitmap(bitmap, sRect, copyBounds, sPaint);
    }

    private void drawLetterTile(Canvas canvas) {
        sPaint.setColor(this.mColor);
        sPaint.setAlpha(this.mPaint.getAlpha());
        Rect bounds = getBounds();
        int min = Math.min(bounds.width(), bounds.height());
        if (this.mIsCircle) {
            canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), (float) (min / 2), sPaint);
        } else {
            canvas.drawRect(bounds, sPaint);
        }
        Character ch = this.mLetter;
        if (ch != null) {
            sFirstChar[0] = ch.charValue();
            sPaint.setTextSize(this.mScale * sLetterToTileRatio * ((float) min));
            sPaint.getTextBounds(sFirstChar, 0, 1, sRect);
            sPaint.setTypeface(Typeface.create("sans-serif", 0));
            sPaint.setColor(sTileFontColor);
            sPaint.setAlpha(138);
            canvas.drawText(sFirstChar, 0, 1, (float) bounds.centerX(), (((float) bounds.centerY()) + (this.mOffset * ((float) bounds.height()))) - sRect.exactCenterY(), sPaint);
            return;
        }
        Bitmap bitmapForContactType = getBitmapForContactType(this.mContactType);
        drawBitmap(bitmapForContactType, bitmapForContactType.getWidth(), bitmapForContactType.getHeight(), canvas);
    }

    public int getColor() {
        return this.mColor;
    }

    private int pickColor(String str) {
        if (TextUtils.isEmpty(str) || this.mContactType == 3) {
            return sDefaultColor;
        }
        return sColors.getColor(Math.abs(str.hashCode()) % sColors.length(), sDefaultColor);
    }

    private static Bitmap getBitmapForContactType(int i) {
        if (i == 1) {
            return DEFAULT_PERSON_AVATAR;
        }
        if (i == 2) {
            return DEFAULT_BUSINESS_AVATAR;
        }
        if (i != 3) {
            return DEFAULT_PERSON_AVATAR;
        }
        return DEFAULT_VOICEMAIL_AVATAR;
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public LetterTileDrawable setScale(float f) {
        this.mScale = f;
        return this;
    }

    public LetterTileDrawable setOffset(float f) {
        Preconditions.checkArgument(f >= -0.5f && f <= 0.5f);
        this.mOffset = f;
        return this;
    }

    public LetterTileDrawable setLetterAndColorFromContactDetails(String str, String str2) {
        if (str == null || str.length() <= 0 || !isEnglishLetter(str.charAt(0))) {
            this.mLetter = null;
        } else {
            this.mLetter = Character.valueOf(Character.toUpperCase(str.charAt(0)));
        }
        this.mColor = pickColor(str2);
        return this;
    }

    public LetterTileDrawable setContactType(int i) {
        this.mContactType = i;
        return this;
    }

    public LetterTileDrawable setIsCircular(boolean z) {
        this.mIsCircle = z;
        return this;
    }

    public static float getAdaptiveIconScale() {
        return 1.0f / ((AdaptiveIconDrawable.getExtraInsetFraction() * 2.0f) + 1.0f);
    }
}
