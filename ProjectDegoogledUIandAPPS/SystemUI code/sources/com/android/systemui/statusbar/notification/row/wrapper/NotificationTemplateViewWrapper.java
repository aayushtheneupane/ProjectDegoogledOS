package com.android.systemui.statusbar.notification.row.wrapper;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.internal.util.ContrastColorUtil;
import com.android.internal.widget.NotificationActionListLayout;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.TransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;

public class NotificationTemplateViewWrapper extends NotificationHeaderViewWrapper {
    private NotificationActionListLayout mActions;
    protected View mActionsContainer;
    private ArraySet<PendingIntent> mCancelledPendingIntents = new ArraySet<>();
    private int mContentHeight;
    private final int mFullHeaderTranslation;
    private float mHeaderTranslation;
    private int mMinHeightHint;
    protected ImageView mPicture;
    private ProgressBar mProgressBar;
    private View mRemoteInputHistory;
    private ImageView mReplyAction;
    private TextView mText;
    private TextView mTitle;
    private Rect mTmpRect = new Rect();
    /* access modifiers changed from: private */
    public UiOffloadThread mUiOffloadThread;

    protected NotificationTemplateViewWrapper(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        super(context, view, expandableNotificationRow);
        this.mTransformationHelper.setCustomTransformation(new ViewTransformationHelper.CustomTransformation() {
            public boolean transformTo(TransformState transformState, TransformableView transformableView, float f) {
                if (!(transformableView instanceof HybridNotificationView)) {
                    return false;
                }
                TransformState currentState = transformableView.getCurrentState(1);
                CrossFadeHelper.fadeOut(transformState.getTransformedView(), f);
                if (currentState != null) {
                    transformState.transformViewVerticalTo(currentState, this, f);
                    currentState.recycle();
                }
                return true;
            }

            public boolean customTransformTarget(TransformState transformState, TransformState transformState2) {
                transformState.setTransformationEndY(getTransformationY(transformState, transformState2));
                return true;
            }

            public boolean transformFrom(TransformState transformState, TransformableView transformableView, float f) {
                if (!(transformableView instanceof HybridNotificationView)) {
                    return false;
                }
                TransformState currentState = transformableView.getCurrentState(1);
                CrossFadeHelper.fadeIn(transformState.getTransformedView(), f);
                if (currentState != null) {
                    transformState.transformViewVerticalFrom(currentState, this, f);
                    currentState.recycle();
                }
                return true;
            }

            public boolean initTransformation(TransformState transformState, TransformState transformState2) {
                transformState.setTransformationStartY(getTransformationY(transformState, transformState2));
                return true;
            }

            private float getTransformationY(TransformState transformState, TransformState transformState2) {
                return ((float) ((transformState2.getLaidOutLocationOnScreen()[1] + transformState2.getTransformedView().getHeight()) - transformState.getLaidOutLocationOnScreen()[1])) * 0.33f;
            }
        }, 2);
        this.mFullHeaderTranslation = context.getResources().getDimensionPixelSize(17105309) - context.getResources().getDimensionPixelSize(17105312);
    }

    private void resolveTemplateViews(StatusBarNotification statusBarNotification) {
        this.mPicture = (ImageView) this.mView.findViewById(16909298);
        ImageView imageView = this.mPicture;
        if (imageView != null) {
            imageView.setTag(ImageTransformState.ICON_TAG, statusBarNotification.getNotification().getLargeIcon());
        }
        this.mTitle = (TextView) this.mView.findViewById(16908310);
        this.mText = (TextView) this.mView.findViewById(16909433);
        View findViewById = this.mView.findViewById(16908301);
        if (findViewById instanceof ProgressBar) {
            this.mProgressBar = (ProgressBar) findViewById;
        } else {
            this.mProgressBar = null;
        }
        this.mActionsContainer = this.mView.findViewById(16908695);
        this.mActions = this.mView.findViewById(16908694);
        this.mReplyAction = (ImageView) this.mView.findViewById(16909288);
        this.mRemoteInputHistory = this.mView.findViewById(16909167);
        updatePendingIntentCancellations();
    }

    private void updatePendingIntentCancellations() {
        NotificationActionListLayout notificationActionListLayout = this.mActions;
        if (notificationActionListLayout != null) {
            int childCount = notificationActionListLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                Button button = (Button) this.mActions.getChildAt(i);
                performOnPendingIntentCancellation(button, new Runnable(button) {
                    private final /* synthetic */ Button f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NotificationTemplateViewWrapper.this.mo14373xebb3ddd4(this.f$1);
                    }
                });
            }
        }
        ImageView imageView = this.mReplyAction;
        if (imageView != null) {
            imageView.setEnabled(true);
            performOnPendingIntentCancellation(this.mReplyAction, new Runnable() {
                public final void run() {
                    NotificationTemplateViewWrapper.this.mo14374x698b99d5();
                }
            });
        }
    }

    /* renamed from: lambda$updatePendingIntentCancellations$0$NotificationTemplateViewWrapper */
    public /* synthetic */ void mo14373xebb3ddd4(Button button) {
        if (button.isEnabled()) {
            button.setEnabled(false);
            ColorStateList textColors = button.getTextColors();
            int[] colors = textColors.getColors();
            int[] iArr = new int[colors.length];
            float f = this.mView.getResources().getFloat(17105298);
            for (int i = 0; i < colors.length; i++) {
                iArr[i] = blendColorWithBackground(colors[i], f);
            }
            button.setTextColor(new ColorStateList(textColors.getStates(), iArr));
        }
    }

    /* renamed from: lambda$updatePendingIntentCancellations$1$NotificationTemplateViewWrapper */
    public /* synthetic */ void mo14374x698b99d5() {
        ImageView imageView = this.mReplyAction;
        if (imageView != null && imageView.isEnabled()) {
            this.mReplyAction.setEnabled(false);
            Drawable mutate = this.mReplyAction.getDrawable().mutate();
            PorterDuffColorFilter porterDuffColorFilter = (PorterDuffColorFilter) mutate.getColorFilter();
            float f = this.mView.getResources().getFloat(17105298);
            if (porterDuffColorFilter != null) {
                mutate.mutate().setColorFilter(blendColorWithBackground(porterDuffColorFilter.getColor(), f), porterDuffColorFilter.getMode());
                return;
            }
            this.mReplyAction.setAlpha(f);
        }
    }

    private int blendColorWithBackground(int i, float f) {
        return ContrastColorUtil.compositeColors(Color.argb((int) (f * 255.0f), Color.red(i), Color.green(i), Color.blue(i)), resolveBackgroundColor());
    }

    private void performOnPendingIntentCancellation(View view, Runnable runnable) {
        final PendingIntent pendingIntent = (PendingIntent) view.getTag(16909220);
        if (pendingIntent != null) {
            if (this.mCancelledPendingIntents.contains(pendingIntent)) {
                runnable.run();
                return;
            }
            final C1199xcf088d79 r1 = new PendingIntent.CancelListener(pendingIntent, runnable) {
                private final /* synthetic */ PendingIntent f$1;
                private final /* synthetic */ Runnable f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onCancelled(PendingIntent pendingIntent) {
                    NotificationTemplateViewWrapper.this.mo14372x5715f5e7(this.f$1, this.f$2, pendingIntent);
                }
            };
            if (this.mUiOffloadThread == null) {
                this.mUiOffloadThread = (UiOffloadThread) Dependency.get(UiOffloadThread.class);
            }
            if (view.isAttachedToWindow()) {
                this.mUiOffloadThread.submit(new Runnable(pendingIntent, r1) {
                    private final /* synthetic */ PendingIntent f$0;
                    private final /* synthetic */ PendingIntent.CancelListener f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        this.f$0.registerCancelListener(this.f$1);
                    }
                });
            }
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View view) {
                    NotificationTemplateViewWrapper.this.mUiOffloadThread.submit(new Runnable(pendingIntent, r1) {
                        private final /* synthetic */ PendingIntent f$0;
                        private final /* synthetic */ PendingIntent.CancelListener f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final void run() {
                            this.f$0.registerCancelListener(this.f$1);
                        }
                    });
                }

                public void onViewDetachedFromWindow(View view) {
                    NotificationTemplateViewWrapper.this.mUiOffloadThread.submit(new Runnable(pendingIntent, r1) {
                        private final /* synthetic */ PendingIntent f$0;
                        private final /* synthetic */ PendingIntent.CancelListener f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final void run() {
                            this.f$0.unregisterCancelListener(this.f$1);
                        }
                    });
                }
            });
        }
    }

    /* renamed from: lambda$performOnPendingIntentCancellation$3$NotificationTemplateViewWrapper */
    public /* synthetic */ void mo14372x5715f5e7(PendingIntent pendingIntent, Runnable runnable, PendingIntent pendingIntent2) {
        this.mView.post(new Runnable(pendingIntent, runnable) {
            private final /* synthetic */ PendingIntent f$1;
            private final /* synthetic */ Runnable f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                NotificationTemplateViewWrapper.this.mo14371xd93e39e6(this.f$1, this.f$2);
            }
        });
    }

    /* renamed from: lambda$performOnPendingIntentCancellation$2$NotificationTemplateViewWrapper */
    public /* synthetic */ void mo14371xd93e39e6(PendingIntent pendingIntent, Runnable runnable) {
        this.mCancelledPendingIntents.add(pendingIntent);
        runnable.run();
    }

    public boolean disallowSingleClick(float f, float f2) {
        ImageView imageView = this.mReplyAction;
        if (imageView == null || imageView.getVisibility() != 0 || (!isOnView(this.mReplyAction, f, f2) && !isOnView(this.mPicture, f, f2))) {
            return super.disallowSingleClick(f, f2);
        }
        return true;
    }

    private boolean isOnView(View view, float f, float f2) {
        View view2 = (View) view.getParent();
        while (view2 != null && !(view2 instanceof ExpandableNotificationRow)) {
            view2.getHitRect(this.mTmpRect);
            Rect rect = this.mTmpRect;
            f -= (float) rect.left;
            f2 -= (float) rect.top;
            view2 = (View) view2.getParent();
        }
        view.getHitRect(this.mTmpRect);
        return this.mTmpRect.contains((int) f, (int) f2);
    }

    public void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        resolveTemplateViews(expandableNotificationRow.getStatusBarNotification());
        super.onContentUpdated(expandableNotificationRow);
        if (expandableNotificationRow.getHeaderVisibleAmount() != 1.0f) {
            setHeaderVisibleAmount(expandableNotificationRow.getHeaderVisibleAmount());
        }
    }

    /* access modifiers changed from: protected */
    public void updateTransformedTypes() {
        super.updateTransformedTypes();
        TextView textView = this.mTitle;
        if (textView != null) {
            this.mTransformationHelper.addTransformedView(1, textView);
        }
        TextView textView2 = this.mText;
        if (textView2 != null) {
            this.mTransformationHelper.addTransformedView(2, textView2);
        }
        ImageView imageView = this.mPicture;
        if (imageView != null) {
            this.mTransformationHelper.addTransformedView(3, imageView);
        }
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            this.mTransformationHelper.addTransformedView(4, progressBar);
        }
    }

    public void setContentHeight(int i, int i2) {
        super.setContentHeight(i, i2);
        this.mContentHeight = i;
        this.mMinHeightHint = i2;
        updateActionOffset();
    }

    public boolean shouldClipToRounding(boolean z, boolean z2) {
        View view;
        if (super.shouldClipToRounding(z, z2)) {
            return true;
        }
        if (!z2 || (view = this.mActionsContainer) == null || view.getVisibility() == 8) {
            return false;
        }
        return true;
    }

    private void updateActionOffset() {
        if (this.mActionsContainer != null) {
            this.mActionsContainer.setTranslationY((float) ((Math.max(this.mContentHeight, this.mMinHeightHint) - this.mView.getHeight()) - getHeaderTranslation(false)));
        }
    }

    public int getHeaderTranslation(boolean z) {
        return z ? this.mFullHeaderTranslation : (int) this.mHeaderTranslation;
    }

    public void setHeaderVisibleAmount(float f) {
        super.setHeaderVisibleAmount(f);
        this.mNotificationHeader.setAlpha(f);
        this.mHeaderTranslation = (1.0f - f) * ((float) this.mFullHeaderTranslation);
        this.mView.setTranslationY(this.mHeaderTranslation);
    }

    public int getExtraMeasureHeight() {
        NotificationActionListLayout notificationActionListLayout = this.mActions;
        int extraMeasureHeight = notificationActionListLayout != null ? notificationActionListLayout.getExtraMeasureHeight() : 0;
        View view = this.mRemoteInputHistory;
        if (!(view == null || view.getVisibility() == 8)) {
            extraMeasureHeight += this.mRow.getContext().getResources().getDimensionPixelSize(C1775R$dimen.remote_input_history_extra_height);
        }
        return extraMeasureHeight + super.getExtraMeasureHeight();
    }
}
