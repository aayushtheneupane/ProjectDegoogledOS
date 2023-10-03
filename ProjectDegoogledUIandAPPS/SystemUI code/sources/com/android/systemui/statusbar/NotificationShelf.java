package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.NotificationIconContainer;

public class NotificationShelf extends ActivatableNotificationView implements View.OnLayoutChangeListener, StatusBarStateController.StateListener {
    private static final boolean ICON_ANMATIONS_WHILE_SCROLLING = SystemProperties.getBoolean("debug.icon_scroll_animations", true);
    /* access modifiers changed from: private */
    public static final int TAG_CONTINUOUS_CLIPPING = C1777R$id.continuous_clipping_tag;
    private static final boolean USE_ANIMATIONS_WHEN_OPENING = SystemProperties.getBoolean("debug.icon_opening_animations", true);
    private AmbientState mAmbientState;
    /* access modifiers changed from: private */
    public boolean mAnimationsEnabled = true;
    private final KeyguardBypassController mBypassController;
    private Rect mClipRect = new Rect();
    private NotificationIconContainer mCollapsedIcons;
    private int mCutoutHeight;
    private float mFirstElementRoundness;
    private int mGapHeight;
    private boolean mHasItemsInStableShelf;
    private float mHiddenShelfIconSize;
    private boolean mHideBackground;
    private NotificationStackScrollLayout mHostLayout;
    private int mIconAppearTopPadding;
    private int mIconSize;
    private boolean mInteractive;
    private int mMaxLayoutHeight;
    private float mMaxShelfEnd;
    private boolean mNoAnimationsInThisFrame;
    private int mNotGoneIndex;
    private float mOpenedAmount;
    private int mPaddingBetweenElements;
    private int mRelativeOffset;
    private int mScrollFastThreshold;
    /* access modifiers changed from: private */
    public NotificationIconContainer mShelfIcons;
    /* access modifiers changed from: private */
    public boolean mShowNotificationShelf;
    private int mStatusBarHeight;
    private int mStatusBarPaddingStart;
    private int mStatusBarState;
    private int[] mTmp = new int[2];

    public boolean hasNoContentHeight() {
        return true;
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public NotificationShelf(Context context, AttributeSet attributeSet, KeyguardBypassController keyguardBypassController) {
        super(context, attributeSet);
        this.mBypassController = keyguardBypassController;
    }

    @VisibleForTesting
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mShelfIcons = (NotificationIconContainer) findViewById(C1777R$id.content);
        this.mShelfIcons.setClipChildren(false);
        this.mShelfIcons.setClipToPadding(false);
        setClipToActualHeight(false);
        setClipChildren(false);
        setClipToPadding(false);
        this.mShelfIcons.setIsStaticLayout(false);
        setBottomRoundness(1.0f, false);
        initDimens();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((SysuiStatusBarStateController) Dependency.get(StatusBarStateController.class)).addCallback(this, 3);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).removeCallback(this);
    }

    public void bind(AmbientState ambientState, NotificationStackScrollLayout notificationStackScrollLayout) {
        this.mAmbientState = ambientState;
        this.mHostLayout = notificationStackScrollLayout;
    }

    private void initDimens() {
        Resources resources = getResources();
        this.mIconAppearTopPadding = resources.getDimensionPixelSize(C1775R$dimen.notification_icon_appear_padding);
        this.mStatusBarHeight = resources.getDimensionPixelOffset(C1775R$dimen.status_bar_height);
        this.mStatusBarPaddingStart = resources.getDimensionPixelOffset(C1775R$dimen.status_bar_padding_start);
        this.mPaddingBetweenElements = resources.getDimensionPixelSize(C1775R$dimen.notification_divider_height);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = resources.getDimensionPixelOffset(C1775R$dimen.notification_shelf_height);
        setLayoutParams(layoutParams);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(C1775R$dimen.shelf_icon_container_padding);
        this.mShelfIcons.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.mScrollFastThreshold = resources.getDimensionPixelOffset(C1775R$dimen.scroll_fast_threshold);
        this.mShowNotificationShelf = resources.getBoolean(C1773R$bool.config_showNotificationShelf);
        this.mIconSize = resources.getDimensionPixelSize(17105437);
        this.mHiddenShelfIconSize = (float) resources.getDimensionPixelOffset(C1775R$dimen.hidden_shelf_icon_size);
        this.mGapHeight = resources.getDimensionPixelSize(C1775R$dimen.qs_notification_padding);
        if (!this.mShowNotificationShelf) {
            setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initDimens();
    }

    /* access modifiers changed from: protected */
    public View getContentView() {
        return this.mShelfIcons;
    }

    public NotificationIconContainer getShelfIcons() {
        return this.mShelfIcons;
    }

    public ExpandableViewState createExpandableViewState() {
        return new ShelfState();
    }

    public void updateState(AmbientState ambientState) {
        ActivatableNotificationView lastVisibleBackgroundChild = ambientState.getLastVisibleBackgroundChild();
        ShelfState shelfState = (ShelfState) getViewState();
        boolean z = true;
        if (!this.mShowNotificationShelf || lastVisibleBackgroundChild == null) {
            shelfState.hidden = true;
            shelfState.location = 64;
            boolean unused = shelfState.hasItemsInStableShelf = false;
            return;
        }
        float innerHeight = ((float) ambientState.getInnerHeight()) + ambientState.getTopPadding() + ambientState.getStackTranslation();
        ExpandableViewState viewState = lastVisibleBackgroundChild.getViewState();
        float f = viewState.yTranslation + ((float) viewState.height);
        shelfState.copyFrom(viewState);
        shelfState.height = getIntrinsicHeight();
        shelfState.yTranslation = Math.max(Math.min(f, innerHeight) - ((float) shelfState.height), getFullyClosedTranslation());
        shelfState.zTranslation = (float) ambientState.getBaseZHeight();
        float unused2 = shelfState.openedAmount = Math.min(1.0f, (shelfState.yTranslation - getFullyClosedTranslation()) / ((float) ((getIntrinsicHeight() * 2) + this.mCutoutHeight)));
        shelfState.clipTopAmount = 0;
        shelfState.alpha = 1.0f;
        shelfState.belowSpeedBump = this.mAmbientState.getSpeedBumpIndex() == 0;
        shelfState.hideSensitive = false;
        shelfState.xTranslation = getTranslationX();
        int i = this.mNotGoneIndex;
        if (i != -1) {
            shelfState.notGoneIndex = Math.min(shelfState.notGoneIndex, i);
        }
        boolean unused3 = shelfState.hasItemsInStableShelf = viewState.inShelf;
        if (this.mAmbientState.isShadeExpanded() && !this.mAmbientState.isQsCustomizerShowing()) {
            z = false;
        }
        shelfState.hidden = z;
        float unused4 = shelfState.maxShelfEnd = innerHeight;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0206: MOVE  (r3v5 com.android.systemui.statusbar.notification.row.ActivatableNotificationView) = 
          (r22v1 com.android.systemui.statusbar.notification.row.ActivatableNotificationView)
        
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01e5  */
    public void updateAppearance() {
        /*
            r33 = this;
            r7 = r33
            boolean r0 = r7.mShowNotificationShelf
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            com.android.systemui.statusbar.phone.NotificationIconContainer r0 = r7.mShelfIcons
            r0.resetViewStates()
            float r8 = r33.getTranslationY()
            com.android.systemui.statusbar.notification.stack.AmbientState r0 = r7.mAmbientState
            com.android.systemui.statusbar.notification.row.ActivatableNotificationView r9 = r0.getLastVisibleBackgroundChild()
            r10 = -1
            r7.mNotGoneIndex = r10
            int r0 = r7.mMaxLayoutHeight
            int r1 = r33.getIntrinsicHeight()
            int r1 = r1 * 2
            int r0 = r0 - r1
            float r0 = (float) r0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r11 = 1065353216(0x3f800000, float:1.0)
            r12 = 0
            if (r1 < 0) goto L_0x0038
            float r0 = r8 - r0
            int r1 = r33.getIntrinsicHeight()
            float r1 = (float) r1
            float r0 = r0 / r1
            float r0 = java.lang.Math.min(r11, r0)
            r13 = r0
            goto L_0x0039
        L_0x0038:
            r13 = r12
        L_0x0039:
            boolean r0 = r7.mHideBackground
            if (r0 == 0) goto L_0x004c
            com.android.systemui.statusbar.notification.stack.ExpandableViewState r0 = r33.getViewState()
            com.android.systemui.statusbar.NotificationShelf$ShelfState r0 = (com.android.systemui.statusbar.NotificationShelf.ShelfState) r0
            boolean r0 = r0.hasItemsInStableShelf
            if (r0 != 0) goto L_0x004c
            r16 = 1
            goto L_0x004e
        L_0x004c:
            r16 = 0
        L_0x004e:
            com.android.systemui.statusbar.notification.stack.AmbientState r0 = r7.mAmbientState
            float r0 = r0.getCurrentScrollVelocity()
            int r1 = r7.mScrollFastThreshold
            float r1 = (float) r1
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0078
            com.android.systemui.statusbar.notification.stack.AmbientState r1 = r7.mAmbientState
            boolean r1 = r1.isExpansionChanging()
            if (r1 == 0) goto L_0x0075
            com.android.systemui.statusbar.notification.stack.AmbientState r1 = r7.mAmbientState
            float r1 = r1.getExpandingVelocity()
            float r1 = java.lang.Math.abs(r1)
            int r2 = r7.mScrollFastThreshold
            float r2 = (float) r2
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0075
            goto L_0x0078
        L_0x0075:
            r17 = 0
            goto L_0x007a
        L_0x0078:
            r17 = 1
        L_0x007a:
            int r0 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r0 <= 0) goto L_0x0081
            r18 = 1
            goto L_0x0083
        L_0x0081:
            r18 = 0
        L_0x0083:
            com.android.systemui.statusbar.notification.stack.AmbientState r0 = r7.mAmbientState
            boolean r0 = r0.isExpansionChanging()
            if (r0 == 0) goto L_0x0096
            com.android.systemui.statusbar.notification.stack.AmbientState r0 = r7.mAmbientState
            boolean r0 = r0.isPanelTracking()
            if (r0 != 0) goto L_0x0096
            r19 = 1
            goto L_0x0098
        L_0x0096:
            r19 = 0
        L_0x0098:
            com.android.systemui.statusbar.notification.stack.AmbientState r0 = r7.mAmbientState
            int r6 = r0.getBaseZHeight()
            r0 = 0
            r22 = r0
            r3 = r12
            r11 = r3
            r20 = r11
            r0 = 0
            r1 = 0
            r2 = 0
            r4 = 0
            r5 = 0
            r14 = 0
            r21 = 0
        L_0x00ad:
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r12 = r7.mHostLayout
            int r12 = r12.getChildCount()
            r15 = 8
            if (r5 >= r12) goto L_0x0270
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r12 = r7.mHostLayout
            android.view.View r12 = r12.getChildAt(r5)
            com.android.systemui.statusbar.notification.row.ExpandableView r12 = (com.android.systemui.statusbar.notification.row.ExpandableView) r12
            boolean r10 = r12 instanceof com.android.systemui.statusbar.notification.row.ActivatableNotificationView
            if (r10 == 0) goto L_0x024c
            int r10 = r12.getVisibility()
            if (r10 == r15) goto L_0x024c
            if (r12 != r7) goto L_0x00cd
            goto L_0x024c
        L_0x00cd:
            r10 = r12
            com.android.systemui.statusbar.notification.row.ActivatableNotificationView r10 = (com.android.systemui.statusbar.notification.row.ActivatableNotificationView) r10
            float r15 = com.android.systemui.statusbar.notification.stack.ViewState.getFinalTranslationZ(r10)
            r23 = r1
            float r1 = (float) r6
            int r1 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x00e4
            boolean r1 = r10.isPinned()
            if (r1 == 0) goto L_0x00e2
            goto L_0x00e4
        L_0x00e2:
            r15 = 0
            goto L_0x00e5
        L_0x00e4:
            r15 = 1
        L_0x00e5:
            if (r12 != r9) goto L_0x00ea
            r24 = 1
            goto L_0x00ec
        L_0x00ea:
            r24 = 0
        L_0x00ec:
            float r25 = r10.getTranslationY()
            if (r24 == 0) goto L_0x00f8
            boolean r1 = r12.isInShelf()
            if (r1 == 0) goto L_0x0126
        L_0x00f8:
            if (r15 != 0) goto L_0x0126
            if (r16 == 0) goto L_0x00fd
            goto L_0x0126
        L_0x00fd:
            int r1 = r7.mPaddingBetweenElements
            float r1 = (float) r1
            float r1 = r8 - r1
            float r12 = r1 - r25
            boolean r26 = r10.isBelowSpeedBump()
            if (r26 != 0) goto L_0x0121
            r26 = r1
            int r1 = r33.getNotificationMergeSize()
            float r1 = (float) r1
            int r1 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0123
            int r1 = r33.getNotificationMergeSize()
            float r1 = (float) r1
            float r1 = r25 + r1
            float r1 = java.lang.Math.min(r8, r1)
            goto L_0x012c
        L_0x0121:
            r26 = r1
        L_0x0123:
            r1 = r26
            goto L_0x012c
        L_0x0126:
            int r1 = r33.getIntrinsicHeight()
            float r1 = (float) r1
            float r1 = r1 + r8
        L_0x012c:
            int r1 = r7.updateNotificationClipHeight(r10, r1, r2)
            int r12 = java.lang.Math.max(r1, r0)
            boolean r0 = r10 instanceof com.android.systemui.statusbar.notification.row.ExpandableNotificationRow
            if (r0 == 0) goto L_0x01ed
            r1 = r10
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r1 = (com.android.systemui.statusbar.notification.row.ExpandableNotificationRow) r1
            r0 = r33
            r26 = r1
            r32 = r23
            r23 = r9
            r9 = r32
            r27 = r12
            r12 = r2
            r2 = r13
            r28 = r13
            r13 = r3
            r3 = r18
            r29 = r13
            r13 = r4
            r4 = r17
            r30 = r5
            r5 = r19
            r31 = r6
            r6 = r24
            float r0 = r0.updateIconAppearance(r1, r2, r3, r4, r5, r6)
            float r20 = r20 + r0
            int r1 = r10.getBackgroundColorWithoutTint()
            int r2 = (r25 > r8 ? 1 : (r25 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x0177
            int r2 = r7.mNotGoneIndex
            r3 = -1
            if (r2 != r3) goto L_0x0178
            r7.mNotGoneIndex = r12
            r7.setTintColor(r9)
            r7.setOverrideTintColor(r14, r11)
            goto L_0x017e
        L_0x0177:
            r3 = -1
        L_0x0178:
            int r2 = r7.mNotGoneIndex
            if (r2 != r3) goto L_0x017e
            r11 = r0
            r14 = r9
        L_0x017e:
            com.android.systemui.statusbar.notification.stack.AmbientState r2 = r7.mAmbientState
            boolean r2 = r2.isShadeExpanded()
            if (r2 == 0) goto L_0x0198
            com.android.systemui.statusbar.notification.stack.AmbientState r2 = r7.mAmbientState
            boolean r2 = r2.isOnKeyguard()
            if (r2 == 0) goto L_0x0196
            com.android.systemui.statusbar.phone.KeyguardBypassController r2 = r7.mBypassController
            boolean r2 = r2.getBypassEnabled()
            if (r2 != 0) goto L_0x0198
        L_0x0196:
            r2 = 1
            goto L_0x0199
        L_0x0198:
            r2 = 0
        L_0x0199:
            if (r24 == 0) goto L_0x01aa
            if (r2 == 0) goto L_0x01aa
            if (r21 != 0) goto L_0x01a1
            r2 = r1
            goto L_0x01a3
        L_0x01a1:
            r2 = r21
        L_0x01a3:
            r10.setOverrideTintColor(r2, r0)
            r21 = r2
            r2 = 0
            goto L_0x01b1
        L_0x01aa:
            r0 = 0
            r2 = 0
            r10.setOverrideTintColor(r2, r0)
            r21 = r1
        L_0x01b1:
            if (r12 != 0) goto L_0x01b9
            if (r15 != 0) goto L_0x01b6
            goto L_0x01b9
        L_0x01b6:
            r0 = r26
            goto L_0x01be
        L_0x01b9:
            r0 = r26
            r0.setAboveShelf(r2)
        L_0x01be:
            if (r12 != 0) goto L_0x01e5
            com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r0.getEntry()
            com.android.systemui.statusbar.StatusBarIconView r0 = r0.expandedIcon
            com.android.systemui.statusbar.phone.NotificationIconContainer$IconState r0 = r7.getIconState(r0)
            if (r0 == 0) goto L_0x01e5
            float r0 = r0.clampedAppearAmount
            r2 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x01e5
            float r0 = r10.getTranslationY()
            float r2 = r33.getTranslationY()
            float r0 = r0 - r2
            int r0 = (int) r0
            float r2 = r10.getCurrentTopRoundness()
            r4 = r0
            r3 = r2
            goto L_0x01e8
        L_0x01e5:
            r4 = r13
            r3 = r29
        L_0x01e8:
            int r2 = r12 + 1
            r29 = r3
            goto L_0x0200
        L_0x01ed:
            r29 = r3
            r30 = r5
            r31 = r6
            r27 = r12
            r28 = r13
            r12 = r2
            r13 = r4
            r32 = r23
            r23 = r9
            r9 = r32
            r1 = r9
        L_0x0200:
            boolean r0 = r10.isFirstInSection()
            if (r0 == 0) goto L_0x0243
            r3 = r22
            if (r3 == 0) goto L_0x0243
            boolean r0 = r3.isLastInSection()
            if (r0 == 0) goto L_0x0243
            float r0 = r10.getTranslationY()
            float r5 = r33.getTranslationY()
            float r0 = r0 - r5
            float r5 = r33.getTranslationY()
            float r6 = r3.getTranslationY()
            int r9 = r3.getActualHeight()
            float r9 = (float) r9
            float r6 = r6 + r9
            float r5 = r5 - r6
            r6 = 0
            int r9 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r9 <= 0) goto L_0x0241
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r4 = r7.mGapHeight
            float r4 = (float) r4
            float r5 = r5 / r4
            double r4 = (double) r5
            double r4 = java.lang.Math.min(r12, r4)
            float r4 = (float) r4
            r5 = 0
            r3.setBottomRoundness(r4, r5)
            int r0 = (int) r0
            r29 = r4
            goto L_0x0246
        L_0x0241:
            r5 = 0
            goto L_0x0245
        L_0x0243:
            r5 = 0
            r6 = 0
        L_0x0245:
            r0 = r4
        L_0x0246:
            r4 = r0
            r22 = r10
            r0 = r27
            goto L_0x0262
        L_0x024c:
            r12 = r2
            r29 = r3
            r30 = r5
            r31 = r6
            r23 = r9
            r28 = r13
            r3 = r22
            r5 = 0
            r6 = 0
            r9 = r1
            r13 = r4
            r22 = r3
            r1 = r9
            r2 = r12
            r4 = r13
        L_0x0262:
            r3 = r29
            int r9 = r30 + 1
            r5 = r9
            r9 = r23
            r13 = r28
            r6 = r31
            r10 = -1
            goto L_0x00ad
        L_0x0270:
            r12 = r2
            r29 = r3
            r13 = r4
            r5 = 0
            r33.clipTransientViews()
            r7.setClipTopAmount(r0)
            com.android.systemui.statusbar.notification.stack.ExpandableViewState r1 = r33.getViewState()
            boolean r1 = r1.hidden
            if (r1 != 0) goto L_0x028c
            int r1 = r33.getIntrinsicHeight()
            if (r0 < r1) goto L_0x028a
            goto L_0x028c
        L_0x028a:
            r0 = r5
            goto L_0x028d
        L_0x028c:
            r0 = 1
        L_0x028d:
            boolean r1 = r7.mShowNotificationShelf
            if (r1 == 0) goto L_0x0299
            if (r0 == 0) goto L_0x0295
            r0 = 4
            goto L_0x0296
        L_0x0295:
            r0 = r5
        L_0x0296:
            r7.setVisibility(r0)
        L_0x0299:
            r7.setBackgroundTop(r13)
            r3 = r29
            r7.setFirstElementRoundness(r3)
            com.android.systemui.statusbar.phone.NotificationIconContainer r0 = r7.mShelfIcons
            com.android.systemui.statusbar.notification.stack.AmbientState r1 = r7.mAmbientState
            int r1 = r1.getSpeedBumpIndex()
            r0.setSpeedBumpIndex(r1)
            com.android.systemui.statusbar.phone.NotificationIconContainer r0 = r7.mShelfIcons
            r0.calculateIconTranslations()
            com.android.systemui.statusbar.phone.NotificationIconContainer r0 = r7.mShelfIcons
            r0.applyIconStates()
            r0 = r5
        L_0x02b7:
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r1 = r7.mHostLayout
            int r1 = r1.getChildCount()
            if (r0 >= r1) goto L_0x02db
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r1 = r7.mHostLayout
            android.view.View r1 = r1.getChildAt(r0)
            boolean r2 = r1 instanceof com.android.systemui.statusbar.notification.row.ExpandableNotificationRow
            if (r2 == 0) goto L_0x02d8
            int r2 = r1.getVisibility()
            if (r2 != r15) goto L_0x02d0
            goto L_0x02d8
        L_0x02d0:
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r1 = (com.android.systemui.statusbar.notification.row.ExpandableNotificationRow) r1
            r7.updateIconClipAmount(r1)
            r7.updateContinuousClipping(r1)
        L_0x02d8:
            int r0 = r0 + 1
            goto L_0x02b7
        L_0x02db:
            r0 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r20 > r0 ? 1 : (r20 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x02e3
            r0 = 1
            goto L_0x02e4
        L_0x02e3:
            r0 = r5
        L_0x02e4:
            if (r0 != 0) goto L_0x02e8
            if (r16 == 0) goto L_0x02e9
        L_0x02e8:
            r5 = 1
        L_0x02e9:
            r7.setHideBackground(r5)
            int r0 = r7.mNotGoneIndex
            r1 = -1
            if (r0 != r1) goto L_0x02f3
            r7.mNotGoneIndex = r12
        L_0x02f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationShelf.updateAppearance():void");
    }

    private void clipTransientViews() {
        for (int i = 0; i < this.mHostLayout.getTransientViewCount(); i++) {
            View transientView = this.mHostLayout.getTransientView(i);
            if (transientView instanceof ExpandableNotificationRow) {
                updateNotificationClipHeight((ExpandableNotificationRow) transientView, getTranslationY(), -1);
            } else {
                Log.e("NotificationShelf", "NotificationShelf.clipTransientViews(): Trying to clip non-row transient view");
            }
        }
    }

    private void setFirstElementRoundness(float f) {
        if (this.mFirstElementRoundness != f) {
            this.mFirstElementRoundness = f;
            setTopRoundness(f, false);
        }
    }

    /* access modifiers changed from: private */
    public void updateIconClipAmount(ExpandableNotificationRow expandableNotificationRow) {
        float translationY = expandableNotificationRow.getTranslationY();
        if (getClipTopAmount() != 0) {
            translationY = Math.max(translationY, getTranslationY() + ((float) getClipTopAmount()));
        }
        StatusBarIconView statusBarIconView = expandableNotificationRow.getEntry().expandedIcon;
        float translationY2 = getTranslationY() + ((float) statusBarIconView.getTop()) + statusBarIconView.getTranslationY();
        if (translationY2 >= translationY || this.mAmbientState.isFullyHidden()) {
            statusBarIconView.setClipBounds((Rect) null);
            return;
        }
        int i = (int) (translationY - translationY2);
        statusBarIconView.setClipBounds(new Rect(0, i, statusBarIconView.getWidth(), Math.max(i, statusBarIconView.getHeight())));
    }

    private void updateContinuousClipping(final ExpandableNotificationRow expandableNotificationRow) {
        final StatusBarIconView statusBarIconView = expandableNotificationRow.getEntry().expandedIcon;
        boolean z = true;
        boolean z2 = ViewState.isAnimatingY(statusBarIconView) && !this.mAmbientState.isDozing();
        if (statusBarIconView.getTag(TAG_CONTINUOUS_CLIPPING) == null) {
            z = false;
        }
        if (z2 && !z) {
            final ViewTreeObserver viewTreeObserver = statusBarIconView.getViewTreeObserver();
            final C11131 r2 = new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    if (!ViewState.isAnimatingY(statusBarIconView)) {
                        if (viewTreeObserver.isAlive()) {
                            viewTreeObserver.removeOnPreDrawListener(this);
                        }
                        statusBarIconView.setTag(NotificationShelf.TAG_CONTINUOUS_CLIPPING, (Object) null);
                        return true;
                    }
                    NotificationShelf.this.updateIconClipAmount(expandableNotificationRow);
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(r2);
            statusBarIconView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View view) {
                }

                public void onViewDetachedFromWindow(View view) {
                    if (view == statusBarIconView) {
                        if (viewTreeObserver.isAlive()) {
                            viewTreeObserver.removeOnPreDrawListener(r2);
                        }
                        statusBarIconView.setTag(NotificationShelf.TAG_CONTINUOUS_CLIPPING, (Object) null);
                    }
                }
            });
            statusBarIconView.setTag(TAG_CONTINUOUS_CLIPPING, r2);
        }
    }

    private int updateNotificationClipHeight(ActivatableNotificationView activatableNotificationView, float f, int i) {
        float translationY = activatableNotificationView.getTranslationY() + ((float) activatableNotificationView.getActualHeight());
        boolean z = true;
        boolean z2 = (activatableNotificationView.isPinned() || activatableNotificationView.isHeadsUpAnimatingAway()) && !this.mAmbientState.isDozingAndNotPulsing((ExpandableView) activatableNotificationView);
        if (!this.mAmbientState.isPulseExpanding()) {
            z = activatableNotificationView.showingPulsing();
        } else if (i != 0) {
            z = false;
        }
        if (translationY <= f || z || (!this.mAmbientState.isShadeExpanded() && z2)) {
            activatableNotificationView.setClipBottomAmount(0);
        } else {
            int i2 = (int) (translationY - f);
            if (z2) {
                i2 = Math.min(activatableNotificationView.getIntrinsicHeight() - activatableNotificationView.getCollapsedHeight(), i2);
            }
            activatableNotificationView.setClipBottomAmount(i2);
        }
        if (z) {
            return (int) (translationY - getTranslationY());
        }
        return 0;
    }

    public void setFakeShadowIntensity(float f, float f2, int i, int i2) {
        if (!this.mHasItemsInStableShelf) {
            f = 0.0f;
        }
        super.setFakeShadowIntensity(f, f2, i, i2);
    }

    private float updateIconAppearance(ExpandableNotificationRow expandableNotificationRow, float f, boolean z, boolean z2, boolean z3, boolean z4) {
        float f2;
        float f3 = f;
        NotificationIconContainer.IconState iconState = getIconState(expandableNotificationRow.getEntry().expandedIcon);
        if (iconState == null) {
            return 0.0f;
        }
        float translationY = expandableNotificationRow.getTranslationY();
        int actualHeight = expandableNotificationRow.getActualHeight() + this.mPaddingBetweenElements;
        float f4 = 1.0f;
        float min = Math.min(((float) getIntrinsicHeight()) * 1.5f * NotificationUtils.interpolate(1.0f, 1.5f, f3), (float) actualHeight);
        if (z4) {
            actualHeight = Math.min(actualHeight, expandableNotificationRow.getMinHeight() - getIntrinsicHeight());
            min = Math.min(min, (float) (expandableNotificationRow.getMinHeight() - getIntrinsicHeight()));
        }
        float f5 = ((float) actualHeight) + translationY;
        boolean z5 = true;
        if (!z3 || this.mAmbientState.getScrollY() != 0 || this.mAmbientState.isOnKeyguard() || iconState.isLastExpandIcon) {
            ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
        } else {
            float intrinsicPadding = (float) (this.mAmbientState.getIntrinsicPadding() + this.mHostLayout.getPositionInLinearLayout(expandableNotificationRow));
            float intrinsicHeight = (float) (this.mMaxLayoutHeight - getIntrinsicHeight());
            if (intrinsicPadding < intrinsicHeight && ((float) expandableNotificationRow.getIntrinsicHeight()) + intrinsicPadding >= intrinsicHeight && expandableNotificationRow.getTranslationY() < intrinsicPadding) {
                iconState.isLastExpandIcon = true;
                iconState.customTransformHeight = Integer.MIN_VALUE;
                if (!(((float) (this.mMaxLayoutHeight - getIntrinsicHeight())) - intrinsicPadding < ((float) getIntrinsicHeight()))) {
                    iconState.customTransformHeight = (int) (((float) (this.mMaxLayoutHeight - getIntrinsicHeight())) - intrinsicPadding);
                }
            }
        }
        float translationY2 = getTranslationY();
        if (iconState.hasCustomTransformHeight()) {
            actualHeight = iconState.customTransformHeight;
            min = (float) actualHeight;
        }
        if (f5 < translationY2 || ((this.mAmbientState.isUnlockHintRunning() && !expandableNotificationRow.isInShelf()) || (!this.mAmbientState.isShadeExpanded() && (expandableNotificationRow.isPinned() || expandableNotificationRow.isHeadsUpAnimatingAway())))) {
            f4 = 0.0f;
        } else if (translationY < translationY2) {
            float f6 = translationY2 - translationY;
            float min2 = Math.min(1.0f, f6 / ((float) actualHeight));
            f4 = 1.0f - Math.min(1.0f, f6 / min);
            f2 = 1.0f - NotificationUtils.interpolate(Interpolators.ACCELERATE_DECELERATE.getInterpolation(min2), min2, f3);
            z5 = false;
            if (z5 && !z3 && iconState.isLastExpandIcon) {
                iconState.isLastExpandIcon = false;
                iconState.customTransformHeight = Integer.MIN_VALUE;
            }
            updateIconPositioning(expandableNotificationRow, f4, f2, min, z, z2, z3, z4);
            return f2;
        }
        f2 = f4;
        iconState.isLastExpandIcon = false;
        iconState.customTransformHeight = Integer.MIN_VALUE;
        updateIconPositioning(expandableNotificationRow, f4, f2, min, z, z2, z3, z4);
        return f2;
    }

    private void updateIconPositioning(ExpandableNotificationRow expandableNotificationRow, float f, float f2, float f3, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5 = z4;
        StatusBarIconView statusBarIconView = expandableNotificationRow.getEntry().expandedIcon;
        NotificationIconContainer.IconState iconState = getIconState(statusBarIconView);
        if (iconState != null) {
            boolean z6 = false;
            boolean z7 = iconState.isLastExpandIcon && !iconState.hasCustomTransformHeight();
            float f4 = 1.0f;
            float f5 = 0.0f;
            float f6 = f > 0.5f ? 1.0f : 0.0f;
            if (f6 == f2) {
                iconState.noAnimations = (z2 || z3) && !z7;
                iconState.useFullTransitionAmount = iconState.noAnimations || (!ICON_ANMATIONS_WHILE_SCROLLING && f2 == 0.0f && z);
                iconState.useLinearTransitionAmount = !ICON_ANMATIONS_WHILE_SCROLLING && f2 == 0.0f && !this.mAmbientState.isExpansionChanging();
                iconState.translateContent = (((float) this.mMaxLayoutHeight) - getTranslationY()) - ((float) getIntrinsicHeight()) > 0.0f;
            }
            if (!z7 && (z2 || (z3 && iconState.useFullTransitionAmount && !ViewState.isAnimatingY(statusBarIconView)))) {
                iconState.cancelAnimations(statusBarIconView);
                iconState.useFullTransitionAmount = true;
                iconState.noAnimations = true;
            }
            if (iconState.hasCustomTransformHeight()) {
                iconState.useFullTransitionAmount = true;
            }
            if (iconState.isLastExpandIcon) {
                iconState.translateContent = false;
            }
            if (!this.mAmbientState.isHiddenAtAll() || expandableNotificationRow.isInShelf()) {
                if (z5 || !USE_ANIMATIONS_WHEN_OPENING || iconState.useFullTransitionAmount || iconState.useLinearTransitionAmount) {
                    f4 = f;
                } else {
                    iconState.needsCannedAnimation = iconState.clampedAppearAmount != f6 && !this.mNoAnimationsInThisFrame;
                    f4 = f6;
                }
            } else if (!this.mAmbientState.isFullyHidden()) {
                f4 = 0.0f;
            }
            iconState.iconAppearAmount = (!USE_ANIMATIONS_WHEN_OPENING || iconState.useFullTransitionAmount) ? f2 : f4;
            iconState.clampedAppearAmount = f6;
            if (expandableNotificationRow.isAboveShelf() || expandableNotificationRow.showingPulsing() || (!z5 && !iconState.translateContent)) {
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
            } else {
                ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow;
                f5 = f;
            }
            expandableNotificationRow.setContentTransformationAmount(f5, z5);
            if (f6 != f4) {
                z6 = true;
            }
            setIconTransformationAmount(expandableNotificationRow, f4, f3, z6, z4);
        }
    }

    private void setIconTransformationAmount(ExpandableNotificationRow expandableNotificationRow, float f, float f2, boolean z, boolean z2) {
        int i;
        float f3;
        float f4;
        StatusBarIconView statusBarIconView = expandableNotificationRow.getEntry().expandedIcon;
        NotificationIconContainer.IconState iconState = getIconState(statusBarIconView);
        View notificationIcon = expandableNotificationRow.getNotificationIcon();
        float translationY = expandableNotificationRow.getTranslationY() + expandableNotificationRow.getContentTranslation();
        boolean z3 = expandableNotificationRow.isInShelf() && !expandableNotificationRow.isTransformingIntoShelf();
        if (z && !z3) {
            translationY = getTranslationY() - f2;
        }
        if (notificationIcon != null) {
            i = expandableNotificationRow.getRelativeTopPadding(notificationIcon);
            f3 = (float) notificationIcon.getHeight();
        } else {
            i = this.mIconAppearTopPadding;
            f3 = 0.0f;
        }
        float f5 = translationY + ((float) i);
        float translationY2 = getTranslationY() + ((float) statusBarIconView.getTop());
        float f6 = this.mAmbientState.isFullyHidden() ? this.mHiddenShelfIconSize : (float) this.mIconSize;
        float interpolate = NotificationUtils.interpolate(f5 - (translationY2 + ((((float) statusBarIconView.getHeight()) - (statusBarIconView.getIconScale() * f6)) / 2.0f)), 0.0f, f);
        float iconScale = f6 * statusBarIconView.getIconScale();
        boolean z4 = !expandableNotificationRow.isShowingIcon();
        if (z4) {
            f3 = iconScale / 2.0f;
            f4 = f;
        } else {
            f4 = 1.0f;
        }
        float interpolate2 = NotificationUtils.interpolate(f3, iconScale, f);
        if (iconState != null) {
            iconState.scaleX = interpolate2 / iconScale;
            iconState.scaleY = iconState.scaleX;
            iconState.hidden = f == 0.0f && !iconState.isAnimating(statusBarIconView);
            if (expandableNotificationRow.isDrawingAppearAnimation() && !expandableNotificationRow.isInShelf()) {
                iconState.hidden = true;
                iconState.iconAppearAmount = 0.0f;
            }
            iconState.alpha = f4;
            iconState.yTranslation = interpolate;
            if (z3) {
                iconState.iconAppearAmount = 1.0f;
                iconState.alpha = 1.0f;
                iconState.scaleX = 1.0f;
                iconState.scaleY = 1.0f;
                iconState.hidden = false;
            }
            if (expandableNotificationRow.isAboveShelf() || expandableNotificationRow.showingPulsing() || (!expandableNotificationRow.isInShelf() && ((z2 && expandableNotificationRow.areGutsExposed()) || expandableNotificationRow.getTranslationZ() > ((float) this.mAmbientState.getBaseZHeight())))) {
                iconState.hidden = true;
            }
            int contrastedStaticDrawableColor = statusBarIconView.getContrastedStaticDrawableColor(getBackgroundColorWithoutTint());
            if (!z4 && contrastedStaticDrawableColor != 0) {
                contrastedStaticDrawableColor = NotificationUtils.interpolateColors(expandableNotificationRow.getVisibleNotificationHeader().getOriginalIconColor(), contrastedStaticDrawableColor, iconState.iconAppearAmount);
            }
            iconState.iconColor = contrastedStaticDrawableColor;
        }
    }

    private NotificationIconContainer.IconState getIconState(StatusBarIconView statusBarIconView) {
        return this.mShelfIcons.getIconState(statusBarIconView);
    }

    private float getFullyClosedTranslation() {
        return (float) ((-(getIntrinsicHeight() - this.mStatusBarHeight)) / 2);
    }

    public int getNotificationMergeSize() {
        return getIntrinsicHeight();
    }

    private void setHideBackground(boolean z) {
        if (this.mHideBackground != z) {
            this.mHideBackground = z;
            updateBackground();
            updateOutline();
        }
    }

    /* access modifiers changed from: protected */
    public boolean needsOutline() {
        return !this.mHideBackground && super.needsOutline();
    }

    /* access modifiers changed from: protected */
    public boolean shouldHideBackground() {
        return super.shouldHideBackground() || this.mHideBackground;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateRelativeOffset();
        int i5 = getResources().getDisplayMetrics().heightPixels;
        this.mClipRect.set(0, -i5, getWidth(), i5);
        this.mShelfIcons.setClipBounds(this.mClipRect);
    }

    private void updateRelativeOffset() {
        this.mCollapsedIcons.getLocationOnScreen(this.mTmp);
        int[] iArr = this.mTmp;
        this.mRelativeOffset = iArr[0];
        getLocationOnScreen(iArr);
        this.mRelativeOffset -= this.mTmp[0];
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        this.mCutoutHeight = (displayCutout == null || displayCutout.getSafeInsetTop() < 0) ? 0 : displayCutout.getSafeInsetTop();
        return onApplyWindowInsets;
    }

    /* access modifiers changed from: private */
    public void setOpenedAmount(float f) {
        int i;
        this.mNoAnimationsInThisFrame = f == 1.0f && this.mOpenedAmount == 0.0f;
        this.mOpenedAmount = f;
        if (!this.mAmbientState.isPanelFullWidth() || this.mAmbientState.isDozing()) {
            f = 1.0f;
        }
        int i2 = this.mRelativeOffset;
        if (isLayoutRtl()) {
            i2 = (getWidth() - i2) - this.mCollapsedIcons.getWidth();
        }
        this.mShelfIcons.setActualLayoutWidth((int) NotificationUtils.interpolate((float) (this.mCollapsedIcons.getFinalTranslationX() + i2), (float) this.mShelfIcons.getWidth(), Interpolators.FAST_OUT_SLOW_IN_REVERSE.getInterpolation(f)));
        boolean hasOverflow = this.mCollapsedIcons.hasOverflow();
        int paddingEnd = this.mCollapsedIcons.getPaddingEnd();
        if (!hasOverflow) {
            i = this.mCollapsedIcons.getNoOverflowExtraPadding();
        } else {
            i = this.mCollapsedIcons.getPartialOverflowExtraPadding();
        }
        this.mShelfIcons.setActualPaddingEnd(NotificationUtils.interpolate((float) (paddingEnd - i), (float) this.mShelfIcons.getPaddingEnd(), f));
        this.mShelfIcons.setActualPaddingStart(NotificationUtils.interpolate((float) i2, (float) this.mShelfIcons.getPaddingStart(), f));
        this.mShelfIcons.setOpenedAmount(f);
    }

    public void setMaxLayoutHeight(int i) {
        this.mMaxLayoutHeight = i;
    }

    public int getNotGoneIndex() {
        return this.mNotGoneIndex;
    }

    /* access modifiers changed from: private */
    public void setHasItemsInStableShelf(boolean z) {
        if (this.mHasItemsInStableShelf != z) {
            this.mHasItemsInStableShelf = z;
            updateInteractiveness();
        }
    }

    public void setCollapsedIcons(NotificationIconContainer notificationIconContainer) {
        this.mCollapsedIcons = notificationIconContainer;
        this.mCollapsedIcons.addOnLayoutChangeListener(this);
    }

    public void onStateChanged(int i) {
        this.mStatusBarState = i;
        updateInteractiveness();
    }

    private void updateInteractiveness() {
        int i = 1;
        this.mInteractive = this.mStatusBarState == 1 && this.mHasItemsInStableShelf;
        setClickable(this.mInteractive);
        setFocusable(this.mInteractive);
        if (!this.mInteractive) {
            i = 4;
        }
        setImportantForAccessibility(i);
    }

    /* access modifiers changed from: protected */
    public boolean isInteractive() {
        return this.mInteractive;
    }

    public void setMaxShelfEnd(float f) {
        this.mMaxShelfEnd = f;
    }

    public void setAnimationsEnabled(boolean z) {
        this.mAnimationsEnabled = z;
        if (!z) {
            this.mShelfIcons.setAnimationsEnabled(false);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.mInteractive) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, getContext().getString(C1784R$string.accessibility_overflow_action)));
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        updateRelativeOffset();
    }

    public void onUiModeChanged() {
        updateBackgroundColors();
    }

    private class ShelfState extends ExpandableViewState {
        /* access modifiers changed from: private */
        public boolean hasItemsInStableShelf;
        /* access modifiers changed from: private */
        public float maxShelfEnd;
        /* access modifiers changed from: private */
        public float openedAmount;

        private ShelfState() {
        }

        public void applyToView(View view) {
            if (NotificationShelf.this.mShowNotificationShelf) {
                super.applyToView(view);
                NotificationShelf.this.setMaxShelfEnd(this.maxShelfEnd);
                NotificationShelf.this.setOpenedAmount(this.openedAmount);
                NotificationShelf.this.updateAppearance();
                NotificationShelf.this.setHasItemsInStableShelf(this.hasItemsInStableShelf);
                NotificationShelf.this.mShelfIcons.setAnimationsEnabled(NotificationShelf.this.mAnimationsEnabled);
            }
        }

        public void animateTo(View view, AnimationProperties animationProperties) {
            if (NotificationShelf.this.mShowNotificationShelf) {
                super.animateTo(view, animationProperties);
                NotificationShelf.this.setMaxShelfEnd(this.maxShelfEnd);
                NotificationShelf.this.setOpenedAmount(this.openedAmount);
                NotificationShelf.this.updateAppearance();
                NotificationShelf.this.setHasItemsInStableShelf(this.hasItemsInStableShelf);
                NotificationShelf.this.mShelfIcons.setAnimationsEnabled(NotificationShelf.this.mAnimationsEnabled);
            }
        }
    }
}
