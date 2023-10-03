package com.android.systemui.statusbar.notification.row.wrapper;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;

public abstract class NotificationViewWrapper implements TransformableView {
    protected int mBackgroundColor = 0;
    protected final ExpandableNotificationRow mRow;
    protected final View mView;

    public boolean disallowSingleClick(float f, float f2) {
        return false;
    }

    public TransformState getCurrentState(int i) {
        return null;
    }

    public int getExtraMeasureHeight() {
        return 0;
    }

    public int getHeaderTranslation(boolean z) {
        return 0;
    }

    public int getMinLayoutHeight() {
        return 0;
    }

    public NotificationHeaderView getNotificationHeader() {
        return null;
    }

    public boolean isDimmable() {
        return true;
    }

    public void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
    }

    public void setContentHeight(int i, int i2) {
    }

    public void setHeaderVisibleAmount(float f) {
    }

    public void setIsChildInGroup(boolean z) {
    }

    public void setLegacy(boolean z) {
    }

    public void setRemoteInputVisible(boolean z) {
    }

    public void setRemoved() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldClearBackgroundOnReapply() {
        return true;
    }

    public boolean shouldClipToRounding(boolean z, boolean z2) {
        return false;
    }

    public void updateExpandability(boolean z, View.OnClickListener onClickListener) {
    }

    public static NotificationViewWrapper wrap(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        if (view.getId() == 16909410) {
            if ("bigPicture".equals(view.getTag())) {
                return new NotificationBigPictureTemplateViewWrapper(context, view, expandableNotificationRow);
            }
            if ("bigText".equals(view.getTag())) {
                return new NotificationBigTextTemplateViewWrapper(context, view, expandableNotificationRow);
            }
            if ("media".equals(view.getTag()) || "bigMediaNarrow".equals(view.getTag())) {
                return new NotificationMediaTemplateViewWrapper(context, view, expandableNotificationRow);
            }
            if ("messaging".equals(view.getTag())) {
                return new NotificationMessagingTemplateViewWrapper(context, view, expandableNotificationRow);
            }
            if (Notification.DecoratedCustomViewStyle.class.equals(expandableNotificationRow.getEntry().notification.getNotification().getNotificationStyle())) {
                return new NotificationDecoratedCustomViewWrapper(context, view, expandableNotificationRow);
            }
            return new NotificationTemplateViewWrapper(context, view, expandableNotificationRow);
        } else if (view instanceof NotificationHeaderView) {
            return new NotificationHeaderViewWrapper(context, view, expandableNotificationRow);
        } else {
            return new NotificationCustomViewWrapper(context, view, expandableNotificationRow);
        }
    }

    protected NotificationViewWrapper(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        this.mView = view;
        this.mRow = expandableNotificationRow;
        onReinflated();
    }

    public void onReinflated() {
        if (shouldClearBackgroundOnReapply()) {
            this.mBackgroundColor = 0;
        }
        int backgroundColor = getBackgroundColor(this.mView);
        if (backgroundColor != 0) {
            this.mBackgroundColor = backgroundColor;
            this.mView.setBackground(new ColorDrawable(0));
        }
    }

    /* access modifiers changed from: protected */
    public boolean needsInversion(int i, View view) {
        if (view == null) {
            return false;
        }
        if (!((this.mView.getResources().getConfiguration().uiMode & 48) == 32) || this.mRow.getEntry().targetSdk >= 29) {
            return false;
        }
        int backgroundColor = getBackgroundColor(view);
        if (backgroundColor != 0) {
            i = backgroundColor;
        }
        if (i == 0) {
            i = resolveBackgroundColor();
        }
        float[] fArr = {0.0f, 0.0f, 0.0f};
        ColorUtils.colorToHSL(i, fArr);
        if (fArr[1] != 0.0f) {
            return false;
        }
        if (fArr[1] == 0.0f && ((double) fArr[2]) > 0.5d) {
            return true;
        }
        if (view instanceof ViewGroup) {
            return childrenNeedInversion(i, (ViewGroup) view);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean childrenNeedInversion(int i, ViewGroup viewGroup) {
        if (viewGroup == null) {
            return false;
        }
        int backgroundColor = getBackgroundColor(viewGroup);
        if (Color.alpha(backgroundColor) != 255) {
            backgroundColor = ColorUtils.setAlphaComponent(ContrastColorUtil.compositeColors(backgroundColor, i), 255);
        }
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof TextView) {
                if (ColorUtils.calculateContrast(((TextView) childAt).getCurrentTextColor(), backgroundColor) < 3.0d) {
                    return true;
                }
            } else if ((childAt instanceof ViewGroup) && childrenNeedInversion(backgroundColor, (ViewGroup) childAt)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundColor(View view) {
        if (view == null) {
            return 0;
        }
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            return ((ColorDrawable) background).getColor();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void invertViewLuminosity(View view) {
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix.setRGB2YUV();
        colorMatrix2.set(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        colorMatrix.postConcat(colorMatrix2);
        colorMatrix2.setYUV2RGB();
        colorMatrix.postConcat(colorMatrix2);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        view.setLayerType(2, paint);
    }

    public void transformTo(TransformableView transformableView, Runnable runnable) {
        CrossFadeHelper.fadeOut(this.mView, runnable);
    }

    public void transformTo(TransformableView transformableView, float f) {
        CrossFadeHelper.fadeOut(this.mView, f);
    }

    public void transformFrom(TransformableView transformableView) {
        CrossFadeHelper.fadeIn(this.mView);
    }

    public void transformFrom(TransformableView transformableView, float f) {
        CrossFadeHelper.fadeIn(this.mView, f);
    }

    public void setVisible(boolean z) {
        this.mView.animate().cancel();
        this.mView.setVisibility(z ? 0 : 4);
    }

    public int getCustomBackgroundColor() {
        if (this.mRow.isSummaryWithChildren()) {
            return 0;
        }
        return this.mBackgroundColor;
    }

    /* access modifiers changed from: protected */
    public int resolveBackgroundColor() {
        int customBackgroundColor = getCustomBackgroundColor();
        if (customBackgroundColor != 0) {
            return customBackgroundColor;
        }
        return this.mView.getContext().getColor(17170886);
    }
}
