package com.android.systemui.statusbar.notification.row.wrapper;

import android.app.Notification;
import android.content.Context;
import android.util.ArraySet;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.widget.NotificationExpandButton;
import com.android.systemui.C1773R$bool;
import com.android.systemui.Interpolators;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.CustomInterpolatorTransformation;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import java.util.Stack;

public class NotificationHeaderViewWrapper extends NotificationViewWrapper {
    /* access modifiers changed from: private */
    public static final Interpolator LOW_PRIORITY_HEADER_CLOSE = new PathInterpolator(0.4f, 0.0f, 0.7f, 1.0f);
    protected int mColor;
    private NotificationExpandButton mExpandButton;
    private TextView mHeaderText;
    private ImageView mIcon;
    /* access modifiers changed from: private */
    public boolean mIsLowPriority;
    protected NotificationHeaderView mNotificationHeader;
    private boolean mShowExpandButtonAtEnd;
    /* access modifiers changed from: private */
    public boolean mTransformLowPriorityTitle;
    protected final ViewTransformationHelper mTransformationHelper;
    private ImageView mWorkProfileImage;

    protected NotificationHeaderViewWrapper(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        super(context, view, expandableNotificationRow);
        this.mShowExpandButtonAtEnd = context.getResources().getBoolean(C1773R$bool.config_showNotificationExpandButtonAtEnd) || NotificationUtils.useNewInterruptionModel(context);
        this.mTransformationHelper = new ViewTransformationHelper();
        this.mTransformationHelper.setCustomTransformation(new CustomInterpolatorTransformation(1) {
            public Interpolator getCustomInterpolator(int i, boolean z) {
                boolean z2 = NotificationHeaderViewWrapper.this.mView instanceof NotificationHeaderView;
                if (i != 16) {
                    return null;
                }
                if ((!z2 || z) && (z2 || !z)) {
                    return NotificationHeaderViewWrapper.LOW_PRIORITY_HEADER_CLOSE;
                }
                return Interpolators.LINEAR_OUT_SLOW_IN;
            }

            /* access modifiers changed from: protected */
            public boolean hasCustomTransformation() {
                return NotificationHeaderViewWrapper.this.mIsLowPriority && NotificationHeaderViewWrapper.this.mTransformLowPriorityTitle;
            }
        }, 1);
        resolveHeaderViews();
        addAppOpsOnClickListener(expandableNotificationRow);
    }

    /* access modifiers changed from: protected */
    public void resolveHeaderViews() {
        this.mIcon = (ImageView) this.mView.findViewById(16908294);
        this.mHeaderText = (TextView) this.mView.findViewById(16908978);
        this.mExpandButton = this.mView.findViewById(16908900);
        this.mWorkProfileImage = (ImageView) this.mView.findViewById(16909261);
        this.mNotificationHeader = this.mView.findViewById(16909165);
        this.mNotificationHeader.setShowExpandButtonAtEnd(this.mShowExpandButtonAtEnd);
        this.mColor = this.mNotificationHeader.getOriginalIconColor();
    }

    private void addAppOpsOnClickListener(ExpandableNotificationRow expandableNotificationRow) {
        this.mNotificationHeader.setAppOpsOnClickListener(expandableNotificationRow.getAppOpsOnClickListener());
    }

    public void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        super.onContentUpdated(expandableNotificationRow);
        this.mIsLowPriority = expandableNotificationRow.isLowPriority();
        this.mTransformLowPriorityTitle = !expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.isSummaryWithChildren();
        ArraySet<View> allTransformingViews = this.mTransformationHelper.getAllTransformingViews();
        resolveHeaderViews();
        updateTransformedTypes();
        addRemainingTransformTypes();
        updateCropToPaddingForImageViews();
        Notification notification = expandableNotificationRow.getStatusBarNotification().getNotification();
        this.mIcon.setTag(ImageTransformState.ICON_TAG, notification.getSmallIcon());
        this.mWorkProfileImage.setTag(ImageTransformState.ICON_TAG, notification.getSmallIcon());
        ArraySet<View> allTransformingViews2 = this.mTransformationHelper.getAllTransformingViews();
        for (int i = 0; i < allTransformingViews.size(); i++) {
            View valueAt = allTransformingViews.valueAt(i);
            if (!allTransformingViews2.contains(valueAt)) {
                this.mTransformationHelper.resetTransformedView(valueAt);
            }
        }
    }

    private void addRemainingTransformTypes() {
        this.mTransformationHelper.addRemainingTransformTypes(this.mView);
    }

    private void updateCropToPaddingForImageViews() {
        Stack stack = new Stack();
        stack.push(this.mView);
        while (!stack.isEmpty()) {
            View view = (View) stack.pop();
            if (view instanceof ImageView) {
                ((ImageView) view).setCropToPadding(true);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    stack.push(viewGroup.getChildAt(i));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateTransformedTypes() {
        this.mTransformationHelper.reset();
        this.mTransformationHelper.addTransformedView(0, this.mIcon);
        if (this.mIsLowPriority) {
            this.mTransformationHelper.addTransformedView(1, this.mHeaderText);
        }
    }

    public void updateExpandability(boolean z, View.OnClickListener onClickListener) {
        this.mExpandButton.setVisibility(z ? 0 : 8);
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (!z) {
            onClickListener = null;
        }
        notificationHeaderView.setOnClickListener(onClickListener);
    }

    public NotificationHeaderView getNotificationHeader() {
        return this.mNotificationHeader;
    }

    public TransformState getCurrentState(int i) {
        return this.mTransformationHelper.getCurrentState(i);
    }

    public void transformTo(TransformableView transformableView, Runnable runnable) {
        this.mTransformationHelper.transformTo(transformableView, runnable);
    }

    public void transformTo(TransformableView transformableView, float f) {
        this.mTransformationHelper.transformTo(transformableView, f);
    }

    public void transformFrom(TransformableView transformableView) {
        this.mTransformationHelper.transformFrom(transformableView);
    }

    public void transformFrom(TransformableView transformableView, float f) {
        this.mTransformationHelper.transformFrom(transformableView, f);
    }

    public void setIsChildInGroup(boolean z) {
        super.setIsChildInGroup(z);
        this.mTransformLowPriorityTitle = !z;
    }

    public void setVisible(boolean z) {
        super.setVisible(z);
        this.mTransformationHelper.setVisible(z);
    }
}
