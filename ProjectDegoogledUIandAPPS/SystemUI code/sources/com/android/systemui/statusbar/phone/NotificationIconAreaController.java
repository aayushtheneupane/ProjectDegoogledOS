package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.collection.ArrayMap;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.util.ContrastColorUtil;
import com.android.settingslib.Utils;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import java.util.ArrayList;
import java.util.function.Function;

public class NotificationIconAreaController implements DarkIconDispatcher.DarkReceiver, StatusBarStateController.StateListener, NotificationWakeUpCoordinator.WakeUpListener {
    private boolean mAnimationsEnabled;
    private int mAodIconAppearTranslation;
    private int mAodIconTint;
    private NotificationIconContainer mAodIcons;
    private boolean mAodIconsVisible;
    private final KeyguardBypassController mBypassController;
    private NotificationIconContainer mCenteredIcon;
    protected View mCenteredIconArea;
    private int mCenteredIconTint = -1;
    private StatusBarIconView mCenteredIconView;
    private Context mContext;
    private final ContrastColorUtil mContrastColorUtil;
    private final DozeParameters mDozeParameters;
    private final NotificationEntryManager mEntryManager;
    private int mIconHPadding;
    private int mIconSize;
    private int mIconTint = -1;
    private final NotificationMediaManager mMediaManager;
    protected View mNotificationIconArea;
    private NotificationIconContainer mNotificationIcons;
    private ViewGroup mNotificationScrollLayout;
    private NotificationIconContainer mShelfIcons;
    private StatusBar mStatusBar;
    private final StatusBarStateController mStatusBarStateController;
    private final Rect mTintArea = new Rect();
    private final Runnable mUpdateStatusBarIcons = new Runnable() {
        public final void run() {
            NotificationIconAreaController.this.updateStatusBarIcons();
        }
    };
    private final NotificationWakeUpCoordinator mWakeUpCoordinator;

    public NotificationIconAreaController(Context context, StatusBar statusBar, StatusBarStateController statusBarStateController, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardBypassController keyguardBypassController, NotificationMediaManager notificationMediaManager) {
        this.mStatusBar = statusBar;
        this.mContrastColorUtil = ContrastColorUtil.getInstance(context);
        this.mContext = context;
        this.mEntryManager = (NotificationEntryManager) Dependency.get(NotificationEntryManager.class);
        this.mStatusBarStateController = statusBarStateController;
        this.mStatusBarStateController.addCallback(this);
        this.mMediaManager = notificationMediaManager;
        this.mDozeParameters = DozeParameters.getInstance(this.mContext);
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        notificationWakeUpCoordinator.addListener(this);
        this.mBypassController = keyguardBypassController;
        initializeNotificationAreaViews(context);
        reloadAodColor();
    }

    /* access modifiers changed from: protected */
    public View inflateIconArea(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1779R$layout.notification_icon_area, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void initializeNotificationAreaViews(Context context) {
        reloadDimens(context);
        LayoutInflater from = LayoutInflater.from(context);
        this.mNotificationIconArea = inflateIconArea(from);
        this.mNotificationIcons = (NotificationIconContainer) this.mNotificationIconArea.findViewById(C1777R$id.notificationIcons);
        this.mNotificationScrollLayout = this.mStatusBar.getNotificationScrollLayout();
        this.mCenteredIconArea = from.inflate(C1779R$layout.center_icon_area, (ViewGroup) null);
        this.mCenteredIcon = (NotificationIconContainer) this.mCenteredIconArea.findViewById(C1777R$id.centeredIcon);
        initAodIcons();
    }

    public void initAodIcons() {
        boolean z = this.mAodIcons != null;
        if (z) {
            this.mAodIcons.setAnimationsEnabled(false);
            this.mAodIcons.removeAllViews();
        }
        this.mAodIcons = (NotificationIconContainer) this.mStatusBar.getStatusBarWindow().findViewById(C1777R$id.clock_notification_icon_container);
        this.mAodIcons.setOnLockScreen(true);
        updateAodIconsVisibility(false);
        updateAnimations();
        if (z) {
            updateAodNotificationIcons();
        }
    }

    public void setupShelf(NotificationShelf notificationShelf) {
        this.mShelfIcons = notificationShelf.getShelfIcons();
        notificationShelf.setCollapsedIcons(this.mNotificationIcons);
    }

    public void onDensityOrFontScaleChanged(Context context) {
        reloadDimens(context);
        FrameLayout.LayoutParams generateIconLayoutParams = generateIconLayoutParams();
        for (int i = 0; i < this.mNotificationIcons.getChildCount(); i++) {
            this.mNotificationIcons.getChildAt(i).setLayoutParams(generateIconLayoutParams);
        }
        for (int i2 = 0; i2 < this.mShelfIcons.getChildCount(); i2++) {
            this.mShelfIcons.getChildAt(i2).setLayoutParams(generateIconLayoutParams);
        }
        for (int i3 = 0; i3 < this.mCenteredIcon.getChildCount(); i3++) {
            this.mCenteredIcon.getChildAt(i3).setLayoutParams(generateIconLayoutParams);
        }
        for (int i4 = 0; i4 < this.mAodIcons.getChildCount(); i4++) {
            this.mAodIcons.getChildAt(i4).setLayoutParams(generateIconLayoutParams);
        }
    }

    private FrameLayout.LayoutParams generateIconLayoutParams() {
        return new FrameLayout.LayoutParams(this.mIconSize + (this.mIconHPadding * 2), getHeight());
    }

    private void reloadDimens(Context context) {
        Resources resources = context.getResources();
        this.mIconSize = resources.getDimensionPixelSize(17105437);
        this.mIconHPadding = resources.getDimensionPixelSize(C1775R$dimen.status_bar_icon_padding);
        this.mAodIconAppearTranslation = resources.getDimensionPixelSize(C1775R$dimen.shelf_appear_translation);
    }

    public View getNotificationInnerAreaView() {
        return this.mNotificationIconArea;
    }

    public View getCenteredNotificationAreaView() {
        return this.mCenteredIconArea;
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        if (rect == null) {
            this.mTintArea.setEmpty();
        } else {
            this.mTintArea.set(rect);
        }
        View view = this.mNotificationIconArea;
        if (view == null) {
            this.mIconTint = i;
        } else if (DarkIconDispatcher.isInArea(rect, view)) {
            this.mIconTint = i;
        }
        View view2 = this.mCenteredIconArea;
        if (view2 == null) {
            this.mCenteredIconTint = i;
        } else if (DarkIconDispatcher.isInArea(rect, view2)) {
            this.mCenteredIconTint = i;
        }
        applyNotificationIconsTint();
    }

    /* access modifiers changed from: protected */
    public int getHeight() {
        return this.mStatusBar.getStatusBarHeight();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r3 = r5.centeredIcon;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldShowNotificationIcon(com.android.systemui.statusbar.notification.collection.NotificationEntry r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, boolean r11, boolean r12) {
        /*
            r4 = this;
            com.android.systemui.statusbar.StatusBarIconView r0 = r4.mCenteredIconView
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0012
            com.android.systemui.statusbar.StatusBarIconView r3 = r5.centeredIcon
            if (r3 == 0) goto L_0x0012
            boolean r0 = java.util.Objects.equals(r3, r0)
            if (r0 == 0) goto L_0x0012
            r0 = r1
            goto L_0x0013
        L_0x0012:
            r0 = r2
        L_0x0013:
            if (r12 == 0) goto L_0x0016
            return r0
        L_0x0016:
            if (r10 == 0) goto L_0x0021
            if (r0 == 0) goto L_0x0021
            boolean r10 = r5.isRowHeadsUp()
            if (r10 != 0) goto L_0x0021
            return r2
        L_0x0021:
            com.android.systemui.statusbar.notification.NotificationEntryManager r10 = r4.mEntryManager
            com.android.systemui.statusbar.notification.collection.NotificationData r10 = r10.getNotificationData()
            java.lang.String r12 = r5.key
            boolean r10 = r10.isAmbient(r12)
            if (r10 == 0) goto L_0x0032
            if (r6 != 0) goto L_0x0032
            return r2
        L_0x0032:
            if (r9 == 0) goto L_0x0043
            java.lang.String r9 = r5.key
            com.android.systemui.statusbar.NotificationMediaManager r10 = r4.mMediaManager
            java.lang.String r10 = r10.getMediaNotificationKey()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0043
            return r2
        L_0x0043:
            boolean r9 = r5.isTopLevelChild()
            if (r9 != 0) goto L_0x004a
            return r2
        L_0x004a:
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r9 = r5.getRow()
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x0057
            return r2
        L_0x0057:
            boolean r9 = r5.isRowDismissed()
            if (r9 == 0) goto L_0x0060
            if (r7 == 0) goto L_0x0060
            return r2
        L_0x0060:
            if (r8 == 0) goto L_0x0069
            boolean r7 = r5.isLastMessageFromReply()
            if (r7 == 0) goto L_0x0069
            return r2
        L_0x0069:
            if (r6 != 0) goto L_0x0072
            boolean r6 = r5.shouldSuppressStatusBar()
            if (r6 == 0) goto L_0x0072
            return r2
        L_0x0072:
            if (r11 == 0) goto L_0x0089
            boolean r6 = r5.showingPulsing()
            if (r6 == 0) goto L_0x0089
            com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator r4 = r4.mWakeUpCoordinator
            boolean r4 = r4.getNotificationsFullyHidden()
            if (r4 == 0) goto L_0x0088
            boolean r4 = r5.isPulseSuppressed()
            if (r4 != 0) goto L_0x0089
        L_0x0088:
            return r2
        L_0x0089:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NotificationIconAreaController.shouldShowNotificationIcon(com.android.systemui.statusbar.notification.collection.NotificationEntry, boolean, boolean, boolean, boolean, boolean, boolean, boolean):boolean");
    }

    public void updateNotificationIcons() {
        updateStatusBarIcons();
        updateShelfIcons();
        updateCenterIcon();
        updateAodNotificationIcons();
        applyNotificationIconsTint();
    }

    private void updateShelfIcons() {
        updateIconsForLayout(C1280xeccd2fb2.INSTANCE, this.mShelfIcons, true, false, false, false, false, false, false);
    }

    public void updateStatusBarIcons() {
        updateIconsForLayout(C1283x339b1a97.INSTANCE, this.mNotificationIcons, false, true, true, false, true, false, false);
    }

    private void updateCenterIcon() {
        updateIconsForLayout(C1279x603b2dbf.INSTANCE, this.mCenteredIcon, false, false, false, false, false, false, true);
    }

    public void updateAodNotificationIcons() {
        updateIconsForLayout(C1281x43d84486.INSTANCE, this.mAodIcons, false, true, true, true, true, this.mBypassController.getBypassEnabled(), false);
    }

    private void updateIconsForLayout(Function<NotificationEntry, StatusBarIconView> function, NotificationIconContainer notificationIconContainer, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        NotificationIconContainer notificationIconContainer2 = notificationIconContainer;
        ArrayList arrayList = new ArrayList(this.mNotificationScrollLayout.getChildCount());
        for (int i = 0; i < this.mNotificationScrollLayout.getChildCount(); i++) {
            View childAt = this.mNotificationScrollLayout.getChildAt(i);
            if (childAt instanceof ExpandableNotificationRow) {
                NotificationEntry entry = ((ExpandableNotificationRow) childAt).getEntry();
                if (shouldShowNotificationIcon(entry, z, z2, z3, z4, z5, z6, z7)) {
                    StatusBarIconView apply = function.apply(entry);
                    if (apply != null) {
                        arrayList.add(apply);
                    }
                }
            }
            Function<NotificationEntry, StatusBarIconView> function2 = function;
        }
        ArrayMap arrayMap = new ArrayMap();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < notificationIconContainer.getChildCount(); i2++) {
            View childAt2 = notificationIconContainer2.getChildAt(i2);
            if ((childAt2 instanceof StatusBarIconView) && !arrayList.contains(childAt2)) {
                StatusBarIconView statusBarIconView = (StatusBarIconView) childAt2;
                String groupKey = statusBarIconView.getNotification().getGroupKey();
                int i3 = 0;
                boolean z8 = false;
                while (true) {
                    if (i3 >= arrayList.size()) {
                        break;
                    }
                    StatusBarIconView statusBarIconView2 = (StatusBarIconView) arrayList.get(i3);
                    if (statusBarIconView2.getSourceIcon().sameAs(statusBarIconView.getSourceIcon()) && statusBarIconView2.getNotification().getGroupKey().equals(groupKey)) {
                        if (z8) {
                            z8 = false;
                            break;
                        }
                        z8 = true;
                    }
                    i3++;
                }
                if (z8) {
                    ArrayList arrayList3 = (ArrayList) arrayMap.get(groupKey);
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                        arrayMap.put(groupKey, arrayList3);
                    }
                    arrayList3.add(statusBarIconView.getStatusBarIcon());
                }
                arrayList2.add(statusBarIconView);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (String str : arrayMap.keySet()) {
            if (((ArrayList) arrayMap.get(str)).size() != 1) {
                arrayList4.add(str);
            }
        }
        arrayMap.removeAll(arrayList4);
        notificationIconContainer2.setReplacingIcons(arrayMap);
        int size = arrayList2.size();
        for (int i4 = 0; i4 < size; i4++) {
            notificationIconContainer2.removeView((View) arrayList2.get(i4));
        }
        FrameLayout.LayoutParams generateIconLayoutParams = generateIconLayoutParams();
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            StatusBarIconView statusBarIconView3 = (StatusBarIconView) arrayList.get(i5);
            notificationIconContainer2.removeTransientView(statusBarIconView3);
            if (statusBarIconView3.getParent() == null) {
                if (z2) {
                    statusBarIconView3.setOnDismissListener(this.mUpdateStatusBarIcons);
                }
                notificationIconContainer2.addView(statusBarIconView3, i5, generateIconLayoutParams);
            }
        }
        notificationIconContainer2.setChangingViewPositions(true);
        int childCount = notificationIconContainer.getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt3 = notificationIconContainer2.getChildAt(i6);
            StatusBarIconView statusBarIconView4 = (StatusBarIconView) arrayList.get(i6);
            if (childAt3 != statusBarIconView4) {
                notificationIconContainer2.removeView(statusBarIconView4);
                notificationIconContainer2.addView(statusBarIconView4, i6);
            }
        }
        notificationIconContainer2.setChangingViewPositions(false);
        notificationIconContainer2.setReplacingIcons((ArrayMap<String, ArrayList<StatusBarIcon>>) null);
    }

    private void applyNotificationIconsTint() {
        for (int i = 0; i < this.mNotificationIcons.getChildCount(); i++) {
            StatusBarIconView statusBarIconView = (StatusBarIconView) this.mNotificationIcons.getChildAt(i);
            if (statusBarIconView.getWidth() != 0) {
                updateTintForIcon(statusBarIconView, this.mIconTint);
            } else {
                statusBarIconView.executeOnLayout(new Runnable(statusBarIconView) {
                    private final /* synthetic */ StatusBarIconView f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NotificationIconAreaController.this.mo15751x7468f248(this.f$1);
                    }
                });
            }
        }
        for (int i2 = 0; i2 < this.mCenteredIcon.getChildCount(); i2++) {
            StatusBarIconView statusBarIconView2 = (StatusBarIconView) this.mCenteredIcon.getChildAt(i2);
            if (statusBarIconView2.getWidth() != 0) {
                updateTintForIcon(statusBarIconView2, this.mCenteredIconTint);
            } else {
                statusBarIconView2.executeOnLayout(new Runnable(statusBarIconView2) {
                    private final /* synthetic */ StatusBarIconView f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NotificationIconAreaController.this.mo15752xfc993227(this.f$1);
                    }
                });
            }
        }
        updateAodIconColors();
    }

    /* renamed from: lambda$applyNotificationIconsTint$4$NotificationIconAreaController */
    public /* synthetic */ void mo15751x7468f248(StatusBarIconView statusBarIconView) {
        updateTintForIcon(statusBarIconView, this.mIconTint);
    }

    /* renamed from: lambda$applyNotificationIconsTint$5$NotificationIconAreaController */
    public /* synthetic */ void mo15752xfc993227(StatusBarIconView statusBarIconView) {
        updateTintForIcon(statusBarIconView, this.mCenteredIconTint);
    }

    private void updateTintForIcon(StatusBarIconView statusBarIconView, int i) {
        int i2 = 0;
        if (!Boolean.TRUE.equals(statusBarIconView.getTag(C1777R$id.icon_is_pre_L)) || NotificationUtils.isGrayscale(statusBarIconView, this.mContrastColorUtil)) {
            i2 = DarkIconDispatcher.getTint(this.mTintArea, statusBarIconView, i);
        }
        statusBarIconView.setStaticDrawableColor(i2);
        statusBarIconView.setDecorColor(i);
    }

    public void showIconIsolated(StatusBarIconView statusBarIconView, boolean z) {
        this.mNotificationIcons.showIconIsolated(statusBarIconView, z);
    }

    public void setIsolatedIconLocation(Rect rect, boolean z) {
        this.mNotificationIcons.setIsolatedIconLocation(rect, z);
    }

    public void onDozingChanged(boolean z) {
        this.mAodIcons.setDozing(z, this.mDozeParameters.getAlwaysOn() && !this.mDozeParameters.getDisplayNeedsBlanking(), 0);
    }

    public void setAnimationsEnabled(boolean z) {
        this.mAnimationsEnabled = z;
        updateAnimations();
    }

    public void onStateChanged(int i) {
        updateAodIconsVisibility(false);
        updateAnimations();
    }

    private void updateAnimations() {
        boolean z = true;
        boolean z2 = this.mStatusBarStateController.getState() == 0;
        this.mAodIcons.setAnimationsEnabled(this.mAnimationsEnabled && !z2);
        this.mCenteredIcon.setAnimationsEnabled(this.mAnimationsEnabled && z2);
        NotificationIconContainer notificationIconContainer = this.mNotificationIcons;
        if (!this.mAnimationsEnabled || !z2) {
            z = false;
        }
        notificationIconContainer.setAnimationsEnabled(z);
    }

    public void onThemeChanged() {
        reloadAodColor();
        updateAodIconColors();
    }

    public void appearAodIcons() {
        if (DozeParameters.getInstance(this.mContext).shouldControlScreenOff()) {
            this.mAodIcons.setTranslationY((float) (-this.mAodIconAppearTranslation));
            this.mAodIcons.setAlpha(0.0f);
            animateInAodIconTranslation();
            this.mAodIcons.animate().alpha(1.0f).setInterpolator(Interpolators.LINEAR).setDuration(200).start();
        }
    }

    private void animateInAodIconTranslation() {
        this.mAodIcons.animate().setInterpolator(Interpolators.DECELERATE_QUINT).translationY(0.0f).setDuration(200).start();
    }

    private void reloadAodColor() {
        this.mAodIconTint = Utils.getColorAttrDefaultColor(this.mContext, C1772R$attr.wallpaperTextColor);
    }

    private void updateAodIconColors() {
        for (int i = 0; i < this.mAodIcons.getChildCount(); i++) {
            StatusBarIconView statusBarIconView = (StatusBarIconView) this.mAodIcons.getChildAt(i);
            if (statusBarIconView.getWidth() != 0) {
                updateTintForIcon(statusBarIconView, this.mAodIconTint);
            } else {
                statusBarIconView.executeOnLayout(new Runnable(statusBarIconView) {
                    private final /* synthetic */ StatusBarIconView f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        NotificationIconAreaController.this.lambda$updateAodIconColors$6$NotificationIconAreaController(this.f$1);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$updateAodIconColors$6$NotificationIconAreaController(StatusBarIconView statusBarIconView) {
        updateTintForIcon(statusBarIconView, this.mAodIconTint);
    }

    public void onFullyHiddenChanged(boolean z) {
        boolean z2 = true;
        if (!this.mBypassController.getBypassEnabled()) {
            if (!this.mDozeParameters.getAlwaysOn() || this.mDozeParameters.getDisplayNeedsBlanking()) {
                z2 = false;
            }
            z2 &= z;
        }
        updateAodIconsVisibility(z2);
        updateAodNotificationIcons();
    }

    public void onPulseExpansionChanged(boolean z) {
        if (z) {
            updateAodIconsVisibility(true);
        }
    }

    private void updateAodIconsVisibility(boolean z) {
        boolean z2 = true;
        int i = 0;
        boolean z3 = this.mBypassController.getBypassEnabled() || this.mWakeUpCoordinator.getNotificationsFullyHidden();
        if (this.mStatusBarStateController.getState() != 1) {
            z3 = false;
        }
        if (z3 && this.mWakeUpCoordinator.isPulseExpanding()) {
            z3 = false;
        }
        if (this.mAodIconsVisible != z3) {
            this.mAodIconsVisible = z3;
            this.mAodIcons.animate().cancel();
            if (z) {
                if (this.mAodIcons.getVisibility() == 0) {
                    z2 = false;
                }
                if (!this.mAodIconsVisible) {
                    animateInAodIconTranslation();
                    CrossFadeHelper.fadeOut(this.mAodIcons);
                } else if (z2) {
                    this.mAodIcons.setVisibility(0);
                    this.mAodIcons.setAlpha(1.0f);
                    appearAodIcons();
                } else {
                    animateInAodIconTranslation();
                    CrossFadeHelper.fadeIn(this.mAodIcons);
                }
            } else {
                this.mAodIcons.setAlpha(1.0f);
                this.mAodIcons.setTranslationY(0.0f);
                NotificationIconContainer notificationIconContainer = this.mAodIcons;
                if (!z3) {
                    i = 4;
                }
                notificationIconContainer.setVisibility(i);
            }
        }
    }
}
