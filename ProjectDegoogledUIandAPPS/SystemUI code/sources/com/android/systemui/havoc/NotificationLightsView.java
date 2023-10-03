package com.android.systemui.havoc;

import android.animation.ValueAnimator;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.palette.graphics.Palette;
import com.android.settingslib.Utils;
import com.android.systemui.C1777R$id;

public class NotificationLightsView extends RelativeLayout {
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    /* access modifiers changed from: private */
    public ImageView mLeftViewFaded;
    /* access modifiers changed from: private */
    public ImageView mLeftViewSolid;
    private ValueAnimator mLightAnimator;
    /* access modifiers changed from: private */
    public ImageView mRightViewFaded;
    /* access modifiers changed from: private */
    public ImageView mRightViewSolid;

    public NotificationLightsView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NotificationLightsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NotificationLightsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public NotificationLightsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                NotificationLightsView.this.mLeftViewSolid.setScaleY(floatValue);
                NotificationLightsView.this.mLeftViewFaded.setScaleY(floatValue);
                NotificationLightsView.this.mRightViewSolid.setScaleY(floatValue);
                NotificationLightsView.this.mRightViewFaded.setScaleY(floatValue);
                float f = 1.0f;
                if (floatValue <= 0.3f) {
                    f = floatValue / 0.3f;
                } else if (floatValue >= 1.0f) {
                    f = 2.0f - floatValue;
                }
                NotificationLightsView.this.mLeftViewSolid.setAlpha(f);
                NotificationLightsView.this.mLeftViewFaded.setAlpha(f);
                NotificationLightsView.this.mRightViewSolid.setAlpha(f);
                NotificationLightsView.this.mRightViewFaded.setAlpha(f);
            }
        };
        this.mLightAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 2.0f});
    }

    public int getNotificationLightsColor() {
        Bitmap bitmap;
        int dominantColor;
        int defaultNotificationLightsColor = getDefaultNotificationLightsColor();
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_color", 0, -2);
        int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_custom_color", getDefaultNotificationLightsColor(), -2);
        if (intForUser == 1) {
            try {
                WallpaperManager instance = WallpaperManager.getInstance(this.mContext);
                if (instance.getWallpaperInfo() != null || (bitmap = ((BitmapDrawable) instance.getDrawable()).getBitmap()) == null || defaultNotificationLightsColor == (dominantColor = Palette.from(bitmap).generate().getDominantColor(defaultNotificationLightsColor))) {
                    return defaultNotificationLightsColor;
                }
                return dominantColor;
            } catch (Exception unused) {
                return defaultNotificationLightsColor;
            }
        } else if (intForUser == 2) {
            return Utils.getColorAccentDefaultColor(getContext());
        } else {
            if (intForUser == 3) {
                return intForUser2;
            }
            return -1;
        }
    }

    public int getDefaultNotificationLightsColor() {
        return getResources().getInteger(17694727);
    }

    public void endAnimation() {
        this.mLightAnimator.end();
        this.mLightAnimator.removeAllUpdateListeners();
    }

    public void animateNotificationWithColor(int i) {
        boolean z = false;
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_layout", 0, -2);
        if (this.mLeftViewSolid == null) {
            this.mLeftViewSolid = (ImageView) findViewById(C1777R$id.notification_animation_left_solid);
        }
        if (this.mLeftViewFaded == null) {
            this.mLeftViewFaded = (ImageView) findViewById(C1777R$id.notification_animation_left_faded);
        }
        if (this.mRightViewSolid == null) {
            this.mRightViewSolid = (ImageView) findViewById(C1777R$id.notification_animation_right_solid);
        }
        if (this.mRightViewFaded == null) {
            this.mRightViewFaded = (ImageView) findViewById(C1777R$id.notification_animation_right_faded);
        }
        this.mLeftViewSolid.setColorFilter(i);
        this.mLeftViewFaded.setColorFilter(i);
        int i2 = 8;
        this.mLeftViewSolid.setVisibility(intForUser == 0 ? 0 : 8);
        this.mLeftViewFaded.setVisibility(intForUser == 1 ? 0 : 8);
        this.mRightViewSolid.setColorFilter(i);
        this.mRightViewFaded.setColorFilter(i);
        this.mRightViewSolid.setVisibility(intForUser == 0 ? 0 : 8);
        ImageView imageView = this.mRightViewFaded;
        if (intForUser == 1) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
        if (!this.mLightAnimator.isRunning()) {
            int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_repeat_count", 0, -2);
            int i3 = 2;
            int intForUser3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_duration", 2, -2) * 1000;
            if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_repeat_direction", 0, -2) != 1) {
                z = true;
            }
            this.mLightAnimator.setDuration((long) intForUser3);
            ValueAnimator valueAnimator = this.mLightAnimator;
            if (z) {
                i3 = 1;
            }
            valueAnimator.setRepeatMode(i3);
            if (intForUser2 == 0) {
                this.mLightAnimator.setRepeatCount(-1);
            } else {
                this.mLightAnimator.setRepeatCount(intForUser2 - 1);
            }
            this.mLightAnimator.addUpdateListener(this.mAnimatorUpdateListener);
            this.mLightAnimator.start();
        }
    }
}
