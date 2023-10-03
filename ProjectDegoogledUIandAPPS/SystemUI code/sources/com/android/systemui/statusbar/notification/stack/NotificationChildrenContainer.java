package com.android.systemui.statusbar.notification.stack;

import android.app.Notification;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1779R$layout;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.NotificationHeaderUtil;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridGroupManager;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import java.util.ArrayList;
import java.util.List;

public class NotificationChildrenContainer extends ViewGroup {
    private static final AnimationProperties ALPHA_FADE_IN;
    @VisibleForTesting
    static final int NUMBER_OF_CHILDREN_WHEN_CHILDREN_EXPANDED = 8;
    @VisibleForTesting
    static final int NUMBER_OF_CHILDREN_WHEN_COLLAPSED = 2;
    @VisibleForTesting
    static final int NUMBER_OF_CHILDREN_WHEN_SYSTEM_EXPANDED = 5;
    private int mActualHeight;
    private int mChildPadding;
    private final List<ExpandableNotificationRow> mChildren;
    private boolean mChildrenExpanded;
    private int mClipBottomAmount;
    private float mCollapsedBottompadding;
    private ExpandableNotificationRow mContainingNotification;
    private ViewGroup mCurrentHeader;
    private int mCurrentHeaderTranslation;
    private float mDividerAlpha;
    private int mDividerHeight;
    private final List<View> mDividers;
    private boolean mEnableShadowOnChildNotifications;
    private ViewState mGroupOverFlowState;
    private View.OnClickListener mHeaderClickListener;
    private int mHeaderHeight;
    private NotificationHeaderUtil mHeaderUtil;
    private ViewState mHeaderViewState;
    private float mHeaderVisibleAmount;
    private boolean mHideDividersDuringExpand;
    private final HybridGroupManager mHybridGroupManager;
    private boolean mIsLowPriority;
    private boolean mNeverAppliedGroupState;
    private NotificationHeaderView mNotificationHeader;
    private NotificationHeaderView mNotificationHeaderLowPriority;
    private int mNotificationHeaderMargin;
    private NotificationViewWrapper mNotificationHeaderWrapper;
    private NotificationViewWrapper mNotificationHeaderWrapperLowPriority;
    private int mNotificatonTopPadding;
    private TextView mOverflowNumber;
    private int mRealHeight;
    private boolean mShowDividersWhenExpanded;
    private int mTranslationForHeader;
    private boolean mUserLocked;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public void prepareExpansionChanged() {
    }

    static {
        C12341 r0 = new AnimationProperties() {
            private AnimationFilter mAnimationFilter;

            {
                AnimationFilter animationFilter = new AnimationFilter();
                animationFilter.animateAlpha();
                this.mAnimationFilter = animationFilter;
            }

            public AnimationFilter getAnimationFilter() {
                return this.mAnimationFilter;
            }
        };
        r0.setDuration(200);
        ALPHA_FADE_IN = r0;
    }

    public NotificationChildrenContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public NotificationChildrenContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NotificationChildrenContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public NotificationChildrenContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDividers = new ArrayList();
        this.mChildren = new ArrayList();
        this.mCurrentHeaderTranslation = 0;
        this.mHeaderVisibleAmount = 1.0f;
        this.mHybridGroupManager = new HybridGroupManager(getContext(), this);
        initDimens();
        setClipChildren(false);
    }

    private void initDimens() {
        Resources resources = getResources();
        this.mChildPadding = resources.getDimensionPixelSize(C1775R$dimen.notification_children_padding);
        this.mDividerHeight = resources.getDimensionPixelSize(C1775R$dimen.notification_children_container_divider_height);
        this.mDividerAlpha = resources.getFloat(C1775R$dimen.notification_divider_alpha);
        this.mNotificationHeaderMargin = resources.getDimensionPixelSize(C1775R$dimen.notification_children_container_margin_top);
        this.mNotificatonTopPadding = resources.getDimensionPixelSize(C1775R$dimen.notification_children_container_top_padding);
        this.mHeaderHeight = this.mNotificationHeaderMargin + this.mNotificatonTopPadding;
        this.mCollapsedBottompadding = (float) resources.getDimensionPixelSize(17105309);
        this.mEnableShadowOnChildNotifications = resources.getBoolean(C1773R$bool.config_enableShadowOnChildNotifications);
        this.mShowDividersWhenExpanded = resources.getBoolean(C1773R$bool.config_showDividersWhenGroupNotificationExpanded);
        this.mHideDividersDuringExpand = resources.getBoolean(C1773R$bool.config_hideDividersDuringExpand);
        this.mTranslationForHeader = resources.getDimensionPixelSize(17105309) - this.mNotificationHeaderMargin;
        this.mHybridGroupManager.initDimens();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int min = Math.min(this.mChildren.size(), 8);
        for (int i6 = 0; i6 < min; i6++) {
            View view = this.mChildren.get(i6);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            this.mDividers.get(i6).layout(0, 0, getWidth(), this.mDividerHeight);
        }
        if (this.mOverflowNumber != null) {
            boolean z2 = true;
            if (getLayoutDirection() != 1) {
                z2 = false;
            }
            if (z2) {
                i5 = 0;
            } else {
                i5 = getWidth() - this.mOverflowNumber.getMeasuredWidth();
            }
            TextView textView = this.mOverflowNumber;
            textView.layout(i5, 0, this.mOverflowNumber.getMeasuredWidth() + i5, textView.getMeasuredHeight());
        }
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.layout(0, 0, notificationHeaderView.getMeasuredWidth(), this.mNotificationHeader.getMeasuredHeight());
        }
        NotificationHeaderView notificationHeaderView2 = this.mNotificationHeaderLowPriority;
        if (notificationHeaderView2 != null) {
            notificationHeaderView2.layout(0, 0, notificationHeaderView2.getMeasuredWidth(), this.mNotificationHeaderLowPriority.getMeasuredHeight());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        TextView textView;
        int i4 = i;
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = mode == 1073741824;
        boolean z2 = mode == Integer.MIN_VALUE;
        int size = View.MeasureSpec.getSize(i2);
        if (z || z2) {
            i3 = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
        } else {
            i3 = i2;
        }
        int size2 = View.MeasureSpec.getSize(i);
        TextView textView2 = this.mOverflowNumber;
        if (textView2 != null) {
            textView2.measure(View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE), i3);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mDividerHeight, 1073741824);
        int i5 = this.mNotificationHeaderMargin + this.mNotificatonTopPadding;
        int min = Math.min(this.mChildren.size(), 8);
        int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
        int i6 = min > maxAllowedVisibleChildren ? maxAllowedVisibleChildren - 1 : -1;
        int i7 = i5;
        int i8 = 0;
        while (i8 < min) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i8);
            expandableNotificationRow.setSingleLineWidthIndention((!(i8 == i6) || (textView = this.mOverflowNumber) == null) ? 0 : textView.getMeasuredWidth());
            expandableNotificationRow.measure(i4, i3);
            this.mDividers.get(i8).measure(i4, makeMeasureSpec);
            if (expandableNotificationRow.getVisibility() != 8) {
                i7 += expandableNotificationRow.getMeasuredHeight() + this.mDividerHeight;
            }
            i8++;
        }
        this.mRealHeight = i7;
        if (mode != 0) {
            i7 = Math.min(i7, size);
        }
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.mHeaderHeight, 1073741824);
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.measure(i4, makeMeasureSpec2);
        }
        if (this.mNotificationHeaderLowPriority != null) {
            this.mNotificationHeaderLowPriority.measure(i4, View.MeasureSpec.makeMeasureSpec(this.mHeaderHeight, 1073741824));
        }
        setMeasuredDimension(size2, i7);
    }

    public boolean pointInView(float f, float f2, float f3) {
        float f4 = -f3;
        return f >= f4 && f2 >= f4 && f < ((float) (this.mRight - this.mLeft)) + f3 && f2 < ((float) this.mRealHeight) + f3;
    }

    public void addNotification(ExpandableNotificationRow expandableNotificationRow, int i) {
        if (i < 0) {
            i = this.mChildren.size();
        }
        this.mChildren.add(i, expandableNotificationRow);
        addView(expandableNotificationRow);
        expandableNotificationRow.setUserLocked(this.mUserLocked);
        View inflateDivider = inflateDivider();
        addView(inflateDivider);
        this.mDividers.add(i, inflateDivider);
        updateGroupOverflow();
        expandableNotificationRow.setContentTransformationAmount(0.0f, false);
        ExpandableViewState viewState = expandableNotificationRow.getViewState();
        if (viewState != null) {
            viewState.cancelAnimations(expandableNotificationRow);
            expandableNotificationRow.cancelAppearDrawing();
        }
    }

    public void removeNotification(ExpandableNotificationRow expandableNotificationRow) {
        int indexOf = this.mChildren.indexOf(expandableNotificationRow);
        this.mChildren.remove(expandableNotificationRow);
        removeView(expandableNotificationRow);
        final View remove = this.mDividers.remove(indexOf);
        removeView(remove);
        getOverlay().add(remove);
        CrossFadeHelper.fadeOut(remove, (Runnable) new Runnable() {
            public void run() {
                NotificationChildrenContainer.this.getOverlay().remove(remove);
            }
        });
        expandableNotificationRow.setSystemChildExpanded(false);
        expandableNotificationRow.setUserLocked(false);
        updateGroupOverflow();
        if (!expandableNotificationRow.isRemoved()) {
            this.mHeaderUtil.restoreNotificationHeader(expandableNotificationRow);
        }
    }

    public int getNotificationChildCount() {
        return this.mChildren.size();
    }

    public void recreateNotificationHeader(View.OnClickListener onClickListener) {
        this.mHeaderClickListener = onClickListener;
        Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(getContext(), this.mContainingNotification.getStatusBarNotification().getNotification());
        RemoteViews makeNotificationHeader = recoverBuilder.makeNotificationHeader();
        if (this.mNotificationHeader == null) {
            this.mNotificationHeader = makeNotificationHeader.apply(getContext(), this);
            this.mNotificationHeader.findViewById(16908900).setVisibility(0);
            this.mNotificationHeader.setOnClickListener(this.mHeaderClickListener);
            this.mNotificationHeaderWrapper = NotificationViewWrapper.wrap(getContext(), this.mNotificationHeader, this.mContainingNotification);
            addView(this.mNotificationHeader, 0);
            invalidate();
        } else {
            makeNotificationHeader.reapply(getContext(), this.mNotificationHeader);
        }
        this.mNotificationHeaderWrapper.onContentUpdated(this.mContainingNotification);
        recreateLowPriorityHeader(recoverBuilder);
        updateHeaderVisibility(false);
        updateChildrenHeaderAppearance();
    }

    private void recreateLowPriorityHeader(Notification.Builder builder) {
        StatusBarNotification statusBarNotification = this.mContainingNotification.getStatusBarNotification();
        if (this.mIsLowPriority) {
            if (builder == null) {
                builder = Notification.Builder.recoverBuilder(getContext(), statusBarNotification.getNotification());
            }
            RemoteViews makeLowPriorityContentView = builder.makeLowPriorityContentView(true);
            if (this.mNotificationHeaderLowPriority == null) {
                this.mNotificationHeaderLowPriority = makeLowPriorityContentView.apply(getContext(), this);
                this.mNotificationHeaderLowPriority.findViewById(16908900).setVisibility(0);
                this.mNotificationHeaderLowPriority.setOnClickListener(this.mHeaderClickListener);
                this.mNotificationHeaderWrapperLowPriority = NotificationViewWrapper.wrap(getContext(), this.mNotificationHeaderLowPriority, this.mContainingNotification);
                addView(this.mNotificationHeaderLowPriority, 0);
                invalidate();
            } else {
                makeLowPriorityContentView.reapply(getContext(), this.mNotificationHeaderLowPriority);
            }
            this.mNotificationHeaderWrapperLowPriority.onContentUpdated(this.mContainingNotification);
            resetHeaderVisibilityIfNeeded(this.mNotificationHeaderLowPriority, calculateDesiredHeader());
            return;
        }
        removeView(this.mNotificationHeaderLowPriority);
        this.mNotificationHeaderLowPriority = null;
        this.mNotificationHeaderWrapperLowPriority = null;
    }

    public void updateChildrenHeaderAppearance() {
        this.mHeaderUtil.updateChildrenHeaderAppearance();
    }

    public void updateGroupOverflow() {
        int size = this.mChildren.size();
        int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
        if (size > maxAllowedVisibleChildren) {
            this.mOverflowNumber = this.mHybridGroupManager.bindOverflowNumber(this.mOverflowNumber, size - maxAllowedVisibleChildren);
            if (this.mGroupOverFlowState == null) {
                this.mGroupOverFlowState = new ViewState();
                this.mNeverAppliedGroupState = true;
                return;
            }
            return;
        }
        TextView textView = this.mOverflowNumber;
        if (textView != null) {
            removeView(textView);
            if (isShown() && isAttachedToWindow()) {
                final TextView textView2 = this.mOverflowNumber;
                addTransientView(textView2, getTransientViewCount());
                CrossFadeHelper.fadeOut((View) textView2, (Runnable) new Runnable() {
                    public void run() {
                        NotificationChildrenContainer.this.removeTransientView(textView2);
                    }
                });
            }
            this.mOverflowNumber = null;
            this.mGroupOverFlowState = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateGroupOverflow();
    }

    private View inflateDivider() {
        return LayoutInflater.from(this.mContext).inflate(C1779R$layout.notification_children_divider, this, false);
    }

    public List<ExpandableNotificationRow> getNotificationChildren() {
        return this.mChildren;
    }

    public boolean applyChildOrder(List<ExpandableNotificationRow> list, VisualStabilityManager visualStabilityManager, VisualStabilityManager.Callback callback) {
        int i = 0;
        if (list == null) {
            return false;
        }
        boolean z = false;
        while (i < this.mChildren.size() && i < list.size()) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i);
            ExpandableNotificationRow expandableNotificationRow2 = list.get(i);
            if (expandableNotificationRow != expandableNotificationRow2) {
                if (visualStabilityManager.canReorderNotification(expandableNotificationRow2)) {
                    this.mChildren.remove(expandableNotificationRow2);
                    this.mChildren.add(i, expandableNotificationRow2);
                    z = true;
                } else {
                    visualStabilityManager.addReorderingAllowedCallback(callback);
                }
            }
            i++;
        }
        updateExpansionStates();
        return z;
    }

    private void updateExpansionStates() {
        if (!this.mChildrenExpanded && !this.mUserLocked) {
            int size = this.mChildren.size();
            for (int i = 0; i < size; i++) {
                ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i);
                boolean z = true;
                if (i != 0 || size != 1) {
                    z = false;
                }
                expandableNotificationRow.setSystemChildExpanded(z);
            }
        }
    }

    public int getIntrinsicHeight() {
        return getIntrinsicHeight((float) getMaxAllowedVisibleChildren());
    }

    private int getIntrinsicHeight(float f) {
        float f2;
        float f3;
        int i;
        if (showingAsLowPriority()) {
            return this.mNotificationHeaderLowPriority.getHeight();
        }
        int i2 = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation;
        int size = this.mChildren.size();
        float groupExpandFraction = this.mUserLocked ? getGroupExpandFraction() : 0.0f;
        boolean z = this.mChildrenExpanded;
        int i3 = i2;
        boolean z2 = true;
        int i4 = 0;
        for (int i5 = 0; i5 < size && ((float) i4) < f; i5++) {
            if (z2) {
                if (this.mUserLocked) {
                    i = (int) (((float) i3) + NotificationUtils.interpolate(0.0f, (float) (this.mNotificatonTopPadding + this.mDividerHeight), groupExpandFraction));
                } else {
                    i = i3 + (z ? this.mNotificatonTopPadding + this.mDividerHeight : 0);
                }
                z2 = false;
            } else if (this.mUserLocked) {
                i = (int) (((float) i3) + NotificationUtils.interpolate((float) this.mChildPadding, (float) this.mDividerHeight, groupExpandFraction));
            } else {
                i = i3 + (z ? this.mDividerHeight : this.mChildPadding);
            }
            i3 = i + this.mChildren.get(i5).getIntrinsicHeight();
            i4++;
        }
        if (this.mUserLocked) {
            f2 = (float) i3;
            f3 = NotificationUtils.interpolate(this.mCollapsedBottompadding, 0.0f, groupExpandFraction);
        } else if (z) {
            return i3;
        } else {
            f2 = (float) i3;
            f3 = this.mCollapsedBottompadding;
        }
        return (int) (f2 + f3);
    }

    /* JADX WARNING: type inference failed for: r4v8, types: [android.widget.TextView, android.view.View] */
    /* JADX WARNING: type inference failed for: r4v12, types: [android.widget.TextView] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0109 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateState(com.android.systemui.statusbar.notification.stack.ExpandableViewState r20, com.android.systemui.statusbar.notification.stack.AmbientState r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.util.List<com.android.systemui.statusbar.notification.row.ExpandableNotificationRow> r2 = r0.mChildren
            int r2 = r2.size()
            int r3 = r0.mNotificationHeaderMargin
            int r4 = r0.mCurrentHeaderTranslation
            int r3 = r3 + r4
            int r4 = r19.getMaxAllowedVisibleChildren()
            r5 = 1
            int r4 = r4 - r5
            int r6 = r4 + 1
            boolean r7 = r0.mUserLocked
            if (r7 == 0) goto L_0x0023
            boolean r7 = r19.showingAsLowPriority()
            if (r7 != 0) goto L_0x0023
            r7 = r5
            goto L_0x0024
        L_0x0023:
            r7 = 0
        L_0x0024:
            boolean r9 = r0.mUserLocked
            r10 = 0
            if (r9 == 0) goto L_0x0037
            float r6 = r19.getGroupExpandFraction()
            int r9 = r0.getMaxAllowedVisibleChildren(r5)
            r18 = r9
            r9 = r6
            r6 = r18
            goto L_0x0038
        L_0x0037:
            r9 = r10
        L_0x0038:
            boolean r11 = r0.mChildrenExpanded
            if (r11 == 0) goto L_0x0046
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r11 = r0.mContainingNotification
            boolean r11 = r11.isGroupExpansionChanging()
            if (r11 != 0) goto L_0x0046
            r11 = r5
            goto L_0x0047
        L_0x0046:
            r11 = 0
        L_0x0047:
            r13 = r3
            r12 = r5
            r3 = 0
            r14 = 0
        L_0x004b:
            if (r3 >= r2) goto L_0x0111
            java.util.List<com.android.systemui.statusbar.notification.row.ExpandableNotificationRow> r15 = r0.mChildren
            java.lang.Object r15 = r15.get(r3)
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r15 = (com.android.systemui.statusbar.notification.row.ExpandableNotificationRow) r15
            if (r12 != 0) goto L_0x0072
            if (r7 == 0) goto L_0x0067
            float r13 = (float) r13
            int r5 = r0.mChildPadding
            float r5 = (float) r5
            int r8 = r0.mDividerHeight
            float r8 = (float) r8
            float r5 = com.android.systemui.statusbar.notification.NotificationUtils.interpolate(r5, r8, r9)
            float r13 = r13 + r5
            int r5 = (int) r13
            goto L_0x0090
        L_0x0067:
            boolean r5 = r0.mChildrenExpanded
            if (r5 == 0) goto L_0x006e
            int r5 = r0.mDividerHeight
            goto L_0x0070
        L_0x006e:
            int r5 = r0.mChildPadding
        L_0x0070:
            int r5 = r5 + r13
            goto L_0x0090
        L_0x0072:
            if (r7 == 0) goto L_0x0082
            float r5 = (float) r13
            int r8 = r0.mNotificatonTopPadding
            int r12 = r0.mDividerHeight
            int r8 = r8 + r12
            float r8 = (float) r8
            float r8 = com.android.systemui.statusbar.notification.NotificationUtils.interpolate(r10, r8, r9)
            float r5 = r5 + r8
            int r5 = (int) r5
            goto L_0x008f
        L_0x0082:
            boolean r5 = r0.mChildrenExpanded
            if (r5 == 0) goto L_0x008c
            int r5 = r0.mNotificatonTopPadding
            int r8 = r0.mDividerHeight
            int r8 = r8 + r5
            goto L_0x008d
        L_0x008c:
            r8 = 0
        L_0x008d:
            int r13 = r13 + r8
            r5 = r13
        L_0x008f:
            r12 = 0
        L_0x0090:
            com.android.systemui.statusbar.notification.stack.ExpandableViewState r8 = r15.getViewState()
            int r13 = r15.getIntrinsicHeight()
            r8.height = r13
            int r10 = r5 + r14
            float r10 = (float) r10
            r8.yTranslation = r10
            r10 = 0
            r8.hidden = r10
            if (r11 == 0) goto L_0x00ab
            boolean r10 = r0.mEnableShadowOnChildNotifications
            if (r10 == 0) goto L_0x00ab
            float r10 = r1.zTranslation
            goto L_0x00ac
        L_0x00ab:
            r10 = 0
        L_0x00ac:
            r8.zTranslation = r10
            boolean r10 = r1.dimmed
            r8.dimmed = r10
            boolean r10 = r1.hideSensitive
            r8.hideSensitive = r10
            boolean r10 = r1.belowSpeedBump
            r8.belowSpeedBump = r10
            r10 = 0
            r8.clipTopAmount = r10
            r10 = 0
            r8.alpha = r10
            r10 = 1065353216(0x3f800000, float:1.0)
            if (r3 >= r6) goto L_0x00ce
            boolean r16 = r19.showingAsLowPriority()
            if (r16 == 0) goto L_0x00cb
            r10 = r9
        L_0x00cb:
            r8.alpha = r10
            goto L_0x00f2
        L_0x00ce:
            int r16 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r16 != 0) goto L_0x00f2
            if (r3 > r4) goto L_0x00f2
            int r10 = r0.mActualHeight
            float r10 = (float) r10
            r17 = r4
            float r4 = r8.yTranslation
            float r10 = r10 - r4
            int r4 = r8.height
            float r4 = (float) r4
            float r10 = r10 / r4
            r8.alpha = r10
            float r4 = r8.alpha
            r10 = 1065353216(0x3f800000, float:1.0)
            float r4 = java.lang.Math.min(r10, r4)
            r10 = 0
            float r4 = java.lang.Math.max(r10, r4)
            r8.alpha = r4
            goto L_0x00f4
        L_0x00f2:
            r17 = r4
        L_0x00f4:
            int r4 = r1.location
            r8.location = r4
            boolean r4 = r1.inShelf
            r8.inShelf = r4
            int r13 = r13 + r5
            boolean r4 = r15.isExpandAnimationRunning()
            if (r4 == 0) goto L_0x0109
            int r4 = r21.getExpandAnimationTopChange()
            int r4 = -r4
            r14 = r4
        L_0x0109:
            int r3 = r3 + 1
            r4 = r17
            r5 = 1
            r10 = 0
            goto L_0x004b
        L_0x0111:
            android.widget.TextView r3 = r0.mOverflowNumber
            if (r3 == 0) goto L_0x0174
            java.util.List<com.android.systemui.statusbar.notification.row.ExpandableNotificationRow> r3 = r0.mChildren
            r4 = 1
            int r5 = r0.getMaxAllowedVisibleChildren(r4)
            int r2 = java.lang.Math.min(r5, r2)
            int r2 = r2 - r4
            java.lang.Object r2 = r3.get(r2)
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r2 = (com.android.systemui.statusbar.notification.row.ExpandableNotificationRow) r2
            com.android.systemui.statusbar.notification.stack.ViewState r3 = r0.mGroupOverFlowState
            com.android.systemui.statusbar.notification.stack.ExpandableViewState r4 = r2.getViewState()
            r3.copyFrom(r4)
            boolean r3 = r0.mChildrenExpanded
            if (r3 != 0) goto L_0x0166
            com.android.systemui.statusbar.notification.row.HybridNotificationView r3 = r2.getSingleLineView()
            if (r3 == 0) goto L_0x0174
            android.widget.TextView r4 = r3.getTextView()
            int r5 = r4.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x014a
            android.widget.TextView r4 = r3.getTitleView()
        L_0x014a:
            int r5 = r4.getVisibility()
            if (r5 != r6) goto L_0x0151
            goto L_0x0152
        L_0x0151:
            r3 = r4
        L_0x0152:
            com.android.systemui.statusbar.notification.stack.ViewState r4 = r0.mGroupOverFlowState
            float r5 = r3.getAlpha()
            r4.alpha = r5
            com.android.systemui.statusbar.notification.stack.ViewState r4 = r0.mGroupOverFlowState
            float r5 = r4.yTranslation
            float r2 = com.android.systemui.statusbar.notification.NotificationUtils.getRelativeYOffset(r3, r2)
            float r5 = r5 + r2
            r4.yTranslation = r5
            goto L_0x0174
        L_0x0166:
            com.android.systemui.statusbar.notification.stack.ViewState r2 = r0.mGroupOverFlowState
            float r3 = r2.yTranslation
            int r4 = r0.mNotificationHeaderMargin
            float r4 = (float) r4
            float r3 = r3 + r4
            r2.yTranslation = r3
            r10 = 0
            r2.alpha = r10
            goto L_0x0175
        L_0x0174:
            r10 = 0
        L_0x0175:
            android.view.NotificationHeaderView r2 = r0.mNotificationHeader
            if (r2 == 0) goto L_0x01a1
            com.android.systemui.statusbar.notification.stack.ViewState r2 = r0.mHeaderViewState
            if (r2 != 0) goto L_0x0184
            com.android.systemui.statusbar.notification.stack.ViewState r2 = new com.android.systemui.statusbar.notification.stack.ViewState
            r2.<init>()
            r0.mHeaderViewState = r2
        L_0x0184:
            com.android.systemui.statusbar.notification.stack.ViewState r2 = r0.mHeaderViewState
            android.view.NotificationHeaderView r3 = r0.mNotificationHeader
            r2.initFrom(r3)
            com.android.systemui.statusbar.notification.stack.ViewState r2 = r0.mHeaderViewState
            if (r11 == 0) goto L_0x0191
            float r10 = r1.zTranslation
        L_0x0191:
            r2.zTranslation = r10
            com.android.systemui.statusbar.notification.stack.ViewState r1 = r0.mHeaderViewState
            int r2 = r0.mCurrentHeaderTranslation
            float r2 = (float) r2
            r1.yTranslation = r2
            float r0 = r0.mHeaderVisibleAmount
            r1.alpha = r0
            r0 = 0
            r1.hidden = r0
        L_0x01a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer.updateState(com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.AmbientState):void");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int getMaxAllowedVisibleChildren() {
        return getMaxAllowedVisibleChildren(false);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int getMaxAllowedVisibleChildren(boolean z) {
        if (!z && ((this.mChildrenExpanded || this.mContainingNotification.isUserLocked()) && !showingAsLowPriority())) {
            return 8;
        }
        if (this.mIsLowPriority) {
            return 5;
        }
        if (this.mContainingNotification.isOnKeyguard() || !this.mContainingNotification.isExpanded()) {
            return (!this.mContainingNotification.isHeadsUpState() || !this.mContainingNotification.canShowHeadsUp()) ? 2 : 5;
        }
        return 5;
    }

    public void applyState() {
        int size = this.mChildren.size();
        ViewState viewState = new ViewState();
        float groupExpandFraction = this.mUserLocked ? getGroupExpandFraction() : 0.0f;
        boolean z = (this.mUserLocked && !showingAsLowPriority()) || (this.mChildrenExpanded && this.mShowDividersWhenExpanded) || (this.mContainingNotification.isGroupExpansionChanging() && !this.mHideDividersDuringExpand);
        for (int i = 0; i < size; i++) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i);
            ExpandableViewState viewState2 = expandableNotificationRow.getViewState();
            viewState2.applyToView(expandableNotificationRow);
            View view = this.mDividers.get(i);
            viewState.initFrom(view);
            viewState.yTranslation = viewState2.yTranslation - ((float) this.mDividerHeight);
            float f = (!this.mChildrenExpanded || viewState2.alpha == 0.0f) ? 0.0f : this.mDividerAlpha;
            if (this.mUserLocked && !showingAsLowPriority()) {
                float f2 = viewState2.alpha;
                if (f2 != 0.0f) {
                    f = NotificationUtils.interpolate(0.0f, 0.5f, Math.min(f2, groupExpandFraction));
                }
            }
            viewState.hidden = !z;
            viewState.alpha = f;
            viewState.applyToView(view);
            expandableNotificationRow.setFakeShadowIntensity(0.0f, 0.0f, 0, 0);
        }
        ViewState viewState3 = this.mGroupOverFlowState;
        if (viewState3 != null) {
            viewState3.applyToView(this.mOverflowNumber);
            this.mNeverAppliedGroupState = false;
        }
        ViewState viewState4 = this.mHeaderViewState;
        if (viewState4 != null) {
            viewState4.applyToView(this.mNotificationHeader);
        }
        updateChildrenClipping();
    }

    private void updateChildrenClipping() {
        int i;
        boolean z;
        if (!this.mContainingNotification.hasExpandingChild()) {
            int size = this.mChildren.size();
            int actualHeight = this.mContainingNotification.getActualHeight() - this.mClipBottomAmount;
            for (int i2 = 0; i2 < size; i2++) {
                ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i2);
                if (expandableNotificationRow.getVisibility() != 8) {
                    float translationY = expandableNotificationRow.getTranslationY();
                    float actualHeight2 = ((float) expandableNotificationRow.getActualHeight()) + translationY;
                    float f = (float) actualHeight;
                    boolean z2 = true;
                    if (translationY > f) {
                        z = false;
                        i = 0;
                    } else {
                        i = actualHeight2 > f ? (int) (actualHeight2 - f) : 0;
                        z = true;
                    }
                    if (expandableNotificationRow.getVisibility() != 0) {
                        z2 = false;
                    }
                    if (z != z2) {
                        expandableNotificationRow.setVisibility(z ? 0 : 4);
                    }
                    expandableNotificationRow.setClipBottomAmount(i);
                }
            }
        }
    }

    public void startAnimationToState(AnimationProperties animationProperties) {
        int size = this.mChildren.size();
        ViewState viewState = new ViewState();
        float groupExpandFraction = getGroupExpandFraction();
        boolean z = (this.mUserLocked && !showingAsLowPriority()) || (this.mChildrenExpanded && this.mShowDividersWhenExpanded) || (this.mContainingNotification.isGroupExpansionChanging() && !this.mHideDividersDuringExpand);
        for (int i = size - 1; i >= 0; i--) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i);
            ExpandableViewState viewState2 = expandableNotificationRow.getViewState();
            viewState2.animateTo(expandableNotificationRow, animationProperties);
            View view = this.mDividers.get(i);
            viewState.initFrom(view);
            viewState.yTranslation = viewState2.yTranslation - ((float) this.mDividerHeight);
            float f = (!this.mChildrenExpanded || viewState2.alpha == 0.0f) ? 0.0f : 0.5f;
            if (this.mUserLocked && !showingAsLowPriority()) {
                float f2 = viewState2.alpha;
                if (f2 != 0.0f) {
                    f = NotificationUtils.interpolate(0.0f, 0.5f, Math.min(f2, groupExpandFraction));
                }
            }
            viewState.hidden = !z;
            viewState.alpha = f;
            viewState.animateTo(view, animationProperties);
            expandableNotificationRow.setFakeShadowIntensity(0.0f, 0.0f, 0, 0);
        }
        TextView textView = this.mOverflowNumber;
        if (textView != null) {
            if (this.mNeverAppliedGroupState) {
                ViewState viewState3 = this.mGroupOverFlowState;
                float f3 = viewState3.alpha;
                viewState3.alpha = 0.0f;
                viewState3.applyToView(textView);
                this.mGroupOverFlowState.alpha = f3;
                this.mNeverAppliedGroupState = false;
            }
            this.mGroupOverFlowState.animateTo(this.mOverflowNumber, animationProperties);
        }
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            this.mHeaderViewState.applyToView(notificationHeaderView);
        }
        updateChildrenClipping();
    }

    public ExpandableNotificationRow getViewAtPosition(float f) {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i);
            float translationY = expandableNotificationRow.getTranslationY();
            float clipTopAmount = ((float) expandableNotificationRow.getClipTopAmount()) + translationY;
            float actualHeight = translationY + ((float) expandableNotificationRow.getActualHeight());
            if (f >= clipTopAmount && f <= actualHeight) {
                return expandableNotificationRow;
            }
        }
        return null;
    }

    public void setChildrenExpanded(boolean z) {
        this.mChildrenExpanded = z;
        updateExpansionStates();
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.setExpanded(z);
        }
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            this.mChildren.get(i).setChildrenExpanded(z, false);
        }
        updateHeaderTouchability();
    }

    public void setContainingNotification(ExpandableNotificationRow expandableNotificationRow) {
        this.mContainingNotification = expandableNotificationRow;
        this.mHeaderUtil = new NotificationHeaderUtil(this.mContainingNotification);
    }

    public NotificationHeaderView getHeaderView() {
        return this.mNotificationHeader;
    }

    public NotificationHeaderView getLowPriorityHeaderView() {
        return this.mNotificationHeaderLowPriority;
    }

    @VisibleForTesting
    public ViewGroup getCurrentHeaderView() {
        return this.mCurrentHeader;
    }

    private void updateHeaderVisibility(boolean z) {
        NotificationHeaderView notificationHeaderView = this.mCurrentHeader;
        NotificationHeaderView calculateDesiredHeader = calculateDesiredHeader();
        if (notificationHeaderView != calculateDesiredHeader) {
            if (z) {
                if (calculateDesiredHeader == null || notificationHeaderView == null) {
                    z = false;
                } else {
                    notificationHeaderView.setVisibility(0);
                    calculateDesiredHeader.setVisibility(0);
                    NotificationViewWrapper wrapperForView = getWrapperForView(calculateDesiredHeader);
                    NotificationViewWrapper wrapperForView2 = getWrapperForView(notificationHeaderView);
                    wrapperForView.transformFrom(wrapperForView2);
                    wrapperForView2.transformTo((TransformableView) wrapperForView, (Runnable) new Runnable() {
                        public final void run() {
                            NotificationChildrenContainer.this.lambda$updateHeaderVisibility$0$NotificationChildrenContainer();
                        }
                    });
                    startChildAlphaAnimations(calculateDesiredHeader == this.mNotificationHeader);
                }
            }
            if (!z) {
                if (calculateDesiredHeader != null) {
                    getWrapperForView(calculateDesiredHeader).setVisible(true);
                    calculateDesiredHeader.setVisibility(0);
                }
                if (notificationHeaderView != null) {
                    NotificationViewWrapper wrapperForView3 = getWrapperForView(notificationHeaderView);
                    if (wrapperForView3 != null) {
                        wrapperForView3.setVisible(false);
                    }
                    notificationHeaderView.setVisibility(4);
                }
            }
            resetHeaderVisibilityIfNeeded(this.mNotificationHeader, calculateDesiredHeader);
            resetHeaderVisibilityIfNeeded(this.mNotificationHeaderLowPriority, calculateDesiredHeader);
            this.mCurrentHeader = calculateDesiredHeader;
        }
    }

    public /* synthetic */ void lambda$updateHeaderVisibility$0$NotificationChildrenContainer() {
        updateHeaderVisibility(false);
    }

    private void resetHeaderVisibilityIfNeeded(View view, View view2) {
        if (view != null) {
            if (!(view == this.mCurrentHeader || view == view2)) {
                getWrapperForView(view).setVisible(false);
                view.setVisibility(4);
            }
            if (view == view2 && view.getVisibility() != 0) {
                getWrapperForView(view).setVisible(true);
                view.setVisibility(0);
            }
        }
    }

    private ViewGroup calculateDesiredHeader() {
        if (showingAsLowPriority()) {
            return this.mNotificationHeaderLowPriority;
        }
        return this.mNotificationHeader;
    }

    private void startChildAlphaAnimations(boolean z) {
        float f = z ? 1.0f : 0.0f;
        float f2 = 1.0f - f;
        int size = this.mChildren.size();
        int i = 0;
        while (i < size && i < 5) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i);
            expandableNotificationRow.setAlpha(f2);
            ViewState viewState = new ViewState();
            viewState.initFrom(expandableNotificationRow);
            viewState.alpha = f;
            ALPHA_FADE_IN.setDelay((long) (i * 50));
            viewState.animateTo(expandableNotificationRow, ALPHA_FADE_IN);
            i++;
        }
    }

    private void updateHeaderTransformation() {
        if (this.mUserLocked && showingAsLowPriority()) {
            float groupExpandFraction = getGroupExpandFraction();
            this.mNotificationHeaderWrapper.transformFrom(this.mNotificationHeaderWrapperLowPriority, groupExpandFraction);
            this.mNotificationHeader.setVisibility(0);
            this.mNotificationHeaderWrapperLowPriority.transformTo((TransformableView) this.mNotificationHeaderWrapper, groupExpandFraction);
        }
    }

    private NotificationViewWrapper getWrapperForView(View view) {
        if (view == this.mNotificationHeader) {
            return this.mNotificationHeaderWrapper;
        }
        return this.mNotificationHeaderWrapperLowPriority;
    }

    public void updateHeaderForExpansion(boolean z) {
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView == null) {
            return;
        }
        if (z) {
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setColor(this.mContainingNotification.calculateBgColor());
            this.mNotificationHeader.setHeaderBackgroundDrawable(colorDrawable);
            return;
        }
        notificationHeaderView.setHeaderBackgroundDrawable((Drawable) null);
    }

    public int getMaxContentHeight() {
        int i;
        if (showingAsLowPriority()) {
            return getMinHeight(5, true);
        }
        int i2 = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation + this.mNotificatonTopPadding;
        int size = this.mChildren.size();
        int i3 = i2;
        int i4 = 0;
        for (int i5 = 0; i5 < size && i4 < 8; i5++) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i5);
            if (expandableNotificationRow.isExpanded(true)) {
                i = expandableNotificationRow.getMaxExpandHeight();
            } else {
                i = expandableNotificationRow.getShowingLayout().getMinHeight(true);
            }
            i3 = (int) (((float) i3) + ((float) i));
            i4++;
        }
        return i4 > 0 ? i3 + (i4 * this.mDividerHeight) : i3;
    }

    public void setActualHeight(int i) {
        int minHeight;
        if (this.mUserLocked) {
            this.mActualHeight = i;
            float groupExpandFraction = getGroupExpandFraction();
            boolean showingAsLowPriority = showingAsLowPriority();
            updateHeaderTransformation();
            int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
            int size = this.mChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i2);
                if (showingAsLowPriority) {
                    minHeight = expandableNotificationRow.getShowingLayout().getMinHeight(false);
                } else if (expandableNotificationRow.isExpanded(true)) {
                    minHeight = expandableNotificationRow.getMaxExpandHeight();
                } else {
                    minHeight = expandableNotificationRow.getShowingLayout().getMinHeight(true);
                }
                float f = (float) minHeight;
                if (i2 < maxAllowedVisibleChildren) {
                    expandableNotificationRow.setActualHeight((int) NotificationUtils.interpolate((float) expandableNotificationRow.getShowingLayout().getMinHeight(false), f, groupExpandFraction), false);
                } else {
                    expandableNotificationRow.setActualHeight((int) f, false);
                }
            }
        }
    }

    public float getGroupExpandFraction() {
        int i;
        if (showingAsLowPriority()) {
            i = getMaxContentHeight();
        } else {
            i = getVisibleChildrenExpandHeight();
        }
        int collapsedHeight = getCollapsedHeight();
        return Math.max(0.0f, Math.min(1.0f, ((float) (this.mActualHeight - collapsedHeight)) / ((float) (i - collapsedHeight))));
    }

    private int getVisibleChildrenExpandHeight() {
        int i;
        int i2 = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation + this.mNotificatonTopPadding + this.mDividerHeight;
        int size = this.mChildren.size();
        int maxAllowedVisibleChildren = getMaxAllowedVisibleChildren(true);
        int i3 = i2;
        int i4 = 0;
        for (int i5 = 0; i5 < size && i4 < maxAllowedVisibleChildren; i5++) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i5);
            if (expandableNotificationRow.isExpanded(true)) {
                i = expandableNotificationRow.getMaxExpandHeight();
            } else {
                i = expandableNotificationRow.getShowingLayout().getMinHeight(true);
            }
            i3 = (int) (((float) i3) + ((float) i));
            i4++;
        }
        return i3;
    }

    public int getMinHeight() {
        return getMinHeight(2, false);
    }

    public int getCollapsedHeight() {
        return getMinHeight(getMaxAllowedVisibleChildren(true), false);
    }

    public int getCollapsedHeightWithoutHeader() {
        return getMinHeight(getMaxAllowedVisibleChildren(true), false, 0);
    }

    private int getMinHeight(int i, boolean z) {
        return getMinHeight(i, z, this.mCurrentHeaderTranslation);
    }

    private int getMinHeight(int i, boolean z, int i2) {
        if (!z && showingAsLowPriority()) {
            return this.mNotificationHeaderLowPriority.getHeight();
        }
        int i3 = this.mNotificationHeaderMargin + i2;
        int size = this.mChildren.size();
        int i4 = i3;
        boolean z2 = true;
        int i5 = 0;
        for (int i6 = 0; i6 < size && i5 < i; i6++) {
            if (!z2) {
                i4 += this.mChildPadding;
            } else {
                z2 = false;
            }
            i4 += this.mChildren.get(i6).getSingleLineView().getHeight();
            i5++;
        }
        return (int) (((float) i4) + this.mCollapsedBottompadding);
    }

    public boolean showingAsLowPriority() {
        return this.mIsLowPriority && !this.mContainingNotification.isExpanded();
    }

    public void reInflateViews(View.OnClickListener onClickListener, StatusBarNotification statusBarNotification) {
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            removeView(notificationHeaderView);
            this.mNotificationHeader = null;
        }
        NotificationHeaderView notificationHeaderView2 = this.mNotificationHeaderLowPriority;
        if (notificationHeaderView2 != null) {
            removeView(notificationHeaderView2);
            this.mNotificationHeaderLowPriority = null;
        }
        recreateNotificationHeader(onClickListener);
        initDimens();
        for (int i = 0; i < this.mDividers.size(); i++) {
            View view = this.mDividers.get(i);
            int indexOfChild = indexOfChild(view);
            removeView(view);
            View inflateDivider = inflateDivider();
            addView(inflateDivider, indexOfChild);
            this.mDividers.set(i, inflateDivider);
        }
        removeView(this.mOverflowNumber);
        this.mOverflowNumber = null;
        this.mGroupOverFlowState = null;
        updateGroupOverflow();
    }

    public void setUserLocked(boolean z) {
        this.mUserLocked = z;
        if (!this.mUserLocked) {
            updateHeaderVisibility(false);
        }
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            this.mChildren.get(i).setUserLocked(z && !showingAsLowPriority());
        }
        updateHeaderTouchability();
    }

    private void updateHeaderTouchability() {
        NotificationHeaderView notificationHeaderView = this.mNotificationHeader;
        if (notificationHeaderView != null) {
            notificationHeaderView.setAcceptAllTouches(this.mChildrenExpanded || this.mUserLocked);
        }
    }

    public void onNotificationUpdated() {
        this.mHybridGroupManager.setOverflowNumberColor(this.mOverflowNumber, this.mContainingNotification.getNotificationColor());
    }

    public int getPositionInLinearLayout(View view) {
        int i = this.mNotificationHeaderMargin + this.mCurrentHeaderTranslation + this.mNotificatonTopPadding;
        for (int i2 = 0; i2 < this.mChildren.size(); i2++) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(i2);
            boolean z = expandableNotificationRow.getVisibility() != 8;
            if (z) {
                i += this.mDividerHeight;
            }
            if (expandableNotificationRow == view) {
                return i;
            }
            if (z) {
                i += expandableNotificationRow.getIntrinsicHeight();
            }
        }
        return 0;
    }

    public void setIconsVisible(boolean z) {
        NotificationHeaderView notificationHeader;
        NotificationHeaderView notificationHeader2;
        NotificationViewWrapper notificationViewWrapper = this.mNotificationHeaderWrapper;
        if (!(notificationViewWrapper == null || (notificationHeader2 = notificationViewWrapper.getNotificationHeader()) == null)) {
            notificationHeader2.getIcon().setForceHidden(!z);
        }
        NotificationViewWrapper notificationViewWrapper2 = this.mNotificationHeaderWrapperLowPriority;
        if (notificationViewWrapper2 != null && (notificationHeader = notificationViewWrapper2.getNotificationHeader()) != null) {
            notificationHeader.getIcon().setForceHidden(!z);
        }
    }

    public void setClipBottomAmount(int i) {
        this.mClipBottomAmount = i;
        updateChildrenClipping();
    }

    public void setIsLowPriority(boolean z) {
        this.mIsLowPriority = z;
        if (this.mContainingNotification != null) {
            recreateLowPriorityHeader((Notification.Builder) null);
            updateHeaderVisibility(false);
        }
        boolean z2 = this.mUserLocked;
        if (z2) {
            setUserLocked(z2);
        }
    }

    public NotificationHeaderView getVisibleHeader() {
        return showingAsLowPriority() ? this.mNotificationHeaderLowPriority : this.mNotificationHeader;
    }

    public void onExpansionChanged() {
        if (this.mIsLowPriority) {
            boolean z = this.mUserLocked;
            if (z) {
                setUserLocked(z);
            }
            updateHeaderVisibility(true);
        }
    }

    public float getIncreasedPaddingAmount() {
        if (showingAsLowPriority()) {
            return 0.0f;
        }
        return getGroupExpandFraction();
    }

    @VisibleForTesting
    public boolean isUserLocked() {
        return this.mUserLocked;
    }

    public void setCurrentBottomRoundness(float f) {
        boolean z = true;
        for (int size = this.mChildren.size() - 1; size >= 0; size--) {
            ExpandableNotificationRow expandableNotificationRow = this.mChildren.get(size);
            if (expandableNotificationRow.getVisibility() != 8) {
                expandableNotificationRow.setBottomRoundness(z ? f : 0.0f, isShown());
                z = false;
            }
        }
    }

    public void setHeaderVisibleAmount(float f) {
        this.mHeaderVisibleAmount = f;
        this.mCurrentHeaderTranslation = (int) ((1.0f - f) * ((float) this.mTranslationForHeader));
    }
}
