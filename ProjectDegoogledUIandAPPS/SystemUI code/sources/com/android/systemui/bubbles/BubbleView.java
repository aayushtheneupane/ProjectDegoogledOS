package com.android.systemui.bubbles;

import android.animation.ValueAnimator;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.InsetDrawable;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.PathParser;
import android.widget.FrameLayout;
import com.android.internal.graphics.ColorUtils;
import com.android.launcher3.icons.ShadowGenerator;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.Interpolators;

public class BubbleView extends FrameLayout {
    private int mBadgeColor;
    private BadgedImageView mBadgedImageView;
    private Bubble mBubble;
    private BubbleIconFactory mBubbleIconFactory;
    private Context mContext;
    private int mIconInset;
    private boolean mSuppressDot;
    private Drawable mUserBadgedAppIcon;

    public BubbleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BubbleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BubbleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
        this.mIconInset = getResources().getDimensionPixelSize(C1775R$dimen.bubble_icon_inset);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBadgedImageView = (BadgedImageView) findViewById(C1777R$id.bubble_image);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setBubble(Bubble bubble) {
        this.mBubble = bubble;
    }

    public String getKey() {
        Bubble bubble = this.mBubble;
        if (bubble != null) {
            return bubble.getKey();
        }
        return null;
    }

    public void update(Bubble bubble) {
        this.mBubble = bubble;
        updateViews();
    }

    public void setBubbleIconFactory(BubbleIconFactory bubbleIconFactory) {
        this.mBubbleIconFactory = bubbleIconFactory;
    }

    public void setAppIcon(Drawable drawable) {
        this.mUserBadgedAppIcon = drawable;
    }

    /* access modifiers changed from: package-private */
    public void updateDotVisibility(boolean z) {
        updateDotVisibility(z, (Runnable) null);
    }

    /* access modifiers changed from: package-private */
    public void setSuppressDot(boolean z, boolean z2) {
        this.mSuppressDot = z;
        updateDotVisibility(z2);
    }

    /* access modifiers changed from: package-private */
    public void setDotPosition(boolean z, boolean z2) {
        if (!z2 || z == this.mBadgedImageView.getDotOnLeft() || !shouldShowDot()) {
            this.mBadgedImageView.setDotOnLeft(z);
        } else {
            animateDot(false, new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BubbleView.this.lambda$setDotPosition$0$BubbleView(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setDotPosition$0$BubbleView(boolean z) {
        this.mBadgedImageView.setDotOnLeft(z);
        animateDot(true, (Runnable) null);
    }

    /* access modifiers changed from: package-private */
    public float[] getDotCenter() {
        float[] dotCenter = this.mBadgedImageView.getDotCenter();
        return new float[]{dotCenter[0], dotCenter[1]};
    }

    /* access modifiers changed from: package-private */
    public boolean getDotPositionOnLeft() {
        return this.mBadgedImageView.getDotOnLeft();
    }

    private void updateDotVisibility(boolean z, Runnable runnable) {
        boolean shouldShowDot = shouldShowDot();
        if (z) {
            animateDot(shouldShowDot, runnable);
            return;
        }
        this.mBadgedImageView.setShowDot(shouldShowDot);
        this.mBadgedImageView.setDotScale(shouldShowDot ? 1.0f : 0.0f);
    }

    private void animateDot(boolean z, Runnable runnable) {
        if (this.mBadgedImageView.isShowingDot() != z) {
            this.mBadgedImageView.setShowDot(z);
            this.mBadgedImageView.clearAnimation();
            this.mBadgedImageView.animate().setDuration(200).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setUpdateListener(new ValueAnimator.AnimatorUpdateListener(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BubbleView.this.lambda$animateDot$1$BubbleView(this.f$1, valueAnimator);
                }
            }).withEndAction(new Runnable(z, runnable) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ Runnable f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    BubbleView.this.lambda$animateDot$2$BubbleView(this.f$1, this.f$2);
                }
            }).start();
        }
    }

    public /* synthetic */ void lambda$animateDot$1$BubbleView(boolean z, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (!z) {
            animatedFraction = 1.0f - animatedFraction;
        }
        this.mBadgedImageView.setDotScale(animatedFraction);
    }

    public /* synthetic */ void lambda$animateDot$2$BubbleView(boolean z, Runnable runnable) {
        this.mBadgedImageView.setDotScale(z ? 1.0f : 0.0f);
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateViews() {
        Bubble bubble = this.mBubble;
        if (bubble != null && this.mBubbleIconFactory != null) {
            Notification.BubbleMetadata bubbleMetadata = bubble.getEntry().getBubbleMetadata();
            Notification notification = this.mBubble.getEntry().notification.getNotification();
            Icon icon = bubbleMetadata.getIcon();
            boolean z = icon.getType() != 5;
            Drawable loadDrawable = icon.loadDrawable(this.mContext);
            if (z) {
                loadDrawable = buildIconWithTint(loadDrawable, notification.color);
            }
            Bitmap bitmap = this.mBubbleIconFactory.createBadgedIconBitmap(loadDrawable, (UserHandle) null, true).icon;
            BubbleIconFactory bubbleIconFactory = this.mBubbleIconFactory;
            Bitmap createIconBitmap = bubbleIconFactory.createIconBitmap(this.mUserBadgedAppIcon, 1.0f, bubbleIconFactory.getBadgeSize());
            Canvas canvas = new Canvas();
            ShadowGenerator shadowGenerator = new ShadowGenerator(this.mBubbleIconFactory.getBadgeSize());
            canvas.setBitmap(createIconBitmap);
            shadowGenerator.recreateIcon(Bitmap.createBitmap(createIconBitmap), canvas);
            this.mBubbleIconFactory.badgeWithDrawable(bitmap, (Drawable) new BitmapDrawable(this.mContext.getResources(), createIconBitmap));
            this.mBadgedImageView.setImageBitmap(bitmap);
            int determineDominateColor = determineDominateColor(loadDrawable, notification.color);
            this.mBadgeColor = determineDominateColor;
            this.mBadgedImageView.setDotColor(determineDominateColor);
            Path createPathFromPathData = PathParser.createPathFromPathData(getResources().getString(17039785));
            Matrix matrix = new Matrix();
            float scale = this.mBubbleIconFactory.getNormalizer().getScale(loadDrawable, (RectF) null, (Path) null, (boolean[]) null);
            matrix.setScale(scale, scale, 50.0f, 50.0f);
            createPathFromPathData.transform(matrix);
            this.mBadgedImageView.drawDot(createPathFromPathData);
            animateDot(shouldShowDot(), (Runnable) null);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldShowDot() {
        return this.mBubble.showBubbleDot() && !this.mSuppressDot;
    }

    /* access modifiers changed from: package-private */
    public int getBadgeColor() {
        return this.mBadgeColor;
    }

    private AdaptiveIconDrawable buildIconWithTint(Drawable drawable, int i) {
        checkTint(drawable, i);
        return new AdaptiveIconDrawable(new ColorDrawable(i), new InsetDrawable(drawable, this.mIconInset));
    }

    private Drawable checkTint(Drawable drawable, int i) {
        int alphaComponent = ColorUtils.setAlphaComponent(i, 255);
        if (alphaComponent == 0) {
            alphaComponent = -3355444;
        }
        drawable.setTint(-1);
        if (ColorUtils.calculateContrast(-1, alphaComponent) < 4.1d) {
            drawable.setTint(ColorUtils.setAlphaComponent(-16777216, 180));
        }
        return drawable;
    }

    private int determineDominateColor(Drawable drawable, int i) {
        return ColorUtils.blendARGB(i, -1, 0.54f);
    }
}
