package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.service.notification.StatusBarNotification;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.DualToneHandler;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.phone.StatusBarSignalPolicy;

public class StatusBarMobileView extends FrameLayout implements DarkIconDispatcher.DarkReceiver, StatusIconDisplayable {
    private StatusBarIconView mDotView;
    private DualToneHandler mDualToneHandler;
    private ImageView mIn;
    private View mInoutContainer;
    private ImageView mMobile;
    private SignalDrawable mMobileDrawable;
    private LinearLayout mMobileGroup;
    private ImageView mMobileRoaming;
    private View mMobileRoamingSpace;
    private View mMobileSignalType;
    private ImageView mMobileType;
    private ImageView mMobileTypeSmall;
    private boolean mOldStyleType;
    private ImageView mOut;
    private String mSlot;
    private StatusBarSignalPolicy.MobileIconState mState;
    private int mVisibleState = -1;
    private ImageView mVolte;

    public static StatusBarMobileView fromContext(Context context, String str) {
        StatusBarMobileView statusBarMobileView = (StatusBarMobileView) LayoutInflater.from(context).inflate(C1779R$layout.status_bar_mobile_signal_group, (ViewGroup) null);
        statusBarMobileView.setSlot(str);
        statusBarMobileView.init();
        statusBarMobileView.setVisibleState(0);
        return statusBarMobileView;
    }

    public StatusBarMobileView(Context context) {
        super(context);
    }

    public StatusBarMobileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StatusBarMobileView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public StatusBarMobileView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void getDrawingRect(Rect rect) {
        super.getDrawingRect(rect);
        float translationX = getTranslationX();
        float translationY = getTranslationY();
        rect.left = (int) (((float) rect.left) + translationX);
        rect.right = (int) (((float) rect.right) + translationX);
        rect.top = (int) (((float) rect.top) + translationY);
        rect.bottom = (int) (((float) rect.bottom) + translationY);
    }

    private void init() {
        this.mDualToneHandler = new DualToneHandler(getContext());
        this.mMobileGroup = (LinearLayout) findViewById(C1777R$id.mobile_group);
        this.mMobile = (ImageView) findViewById(C1777R$id.mobile_signal);
        this.mMobileType = (ImageView) findViewById(C1777R$id.mobile_type);
        this.mMobileRoaming = (ImageView) findViewById(C1777R$id.mobile_roaming);
        this.mMobileRoamingSpace = findViewById(C1777R$id.mobile_roaming_space);
        this.mIn = (ImageView) findViewById(C1777R$id.mobile_in);
        this.mOut = (ImageView) findViewById(C1777R$id.mobile_out);
        this.mInoutContainer = findViewById(C1777R$id.inout_container);
        this.mVolte = (ImageView) findViewById(C1777R$id.mobile_volte);
        this.mMobileSignalType = findViewById(C1777R$id.mobile_signal_type);
        this.mMobileTypeSmall = (ImageView) findViewById(C1777R$id.mobile_type_small);
        this.mMobileDrawable = new SignalDrawable(getContext());
        this.mMobile.setImageDrawable(this.mMobileDrawable);
        initDotView();
    }

    private void initDotView() {
        this.mDotView = new StatusBarIconView(this.mContext, this.mSlot, (StatusBarNotification) null);
        this.mDotView.setVisibleState(1);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_icon_size);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        layoutParams.gravity = 8388627;
        addView(this.mDotView, layoutParams);
    }

    public void applyMobileState(StatusBarSignalPolicy.MobileIconState mobileIconState, boolean z) {
        boolean z2 = true;
        if (mobileIconState == null) {
            if (getVisibility() == 8) {
                z2 = false;
            }
            setVisibility(8);
            this.mState = null;
        } else {
            StatusBarSignalPolicy.MobileIconState mobileIconState2 = this.mState;
            if (mobileIconState2 == null) {
                this.mState = mobileIconState.copy();
                this.mOldStyleType = z;
                initViewState();
            } else if (!mobileIconState2.equals(mobileIconState) || this.mOldStyleType != z) {
                z2 = updateState(mobileIconState.copy(), z);
            } else {
                z2 = false;
            }
        }
        if (z2) {
            requestLayout();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initViewState() {
        /*
            r5 = this;
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r5.mState
            java.lang.String r0 = r0.contentDescription
            r5.setContentDescription(r0)
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r5.mState
            boolean r0 = r0.visible
            r1 = 8
            r2 = 0
            if (r0 != 0) goto L_0x0016
            android.widget.LinearLayout r0 = r5.mMobileGroup
            r0.setVisibility(r1)
            goto L_0x001b
        L_0x0016:
            android.widget.LinearLayout r0 = r5.mMobileGroup
            r0.setVisibility(r2)
        L_0x001b:
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r5.mState
            int r0 = r0.strengthId
            if (r0 <= 0) goto L_0x0030
            android.widget.ImageView r0 = r5.mMobile
            r0.setVisibility(r2)
            com.android.settingslib.graph.SignalDrawable r0 = r5.mMobileDrawable
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r5.mState
            int r3 = r3.strengthId
            r0.setLevel(r3)
            goto L_0x0035
        L_0x0030:
            android.widget.ImageView r0 = r5.mMobile
            r0.setVisibility(r1)
        L_0x0035:
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r5.mState
            int r3 = r0.typeId
            if (r3 <= 0) goto L_0x0048
            boolean r3 = r5.mOldStyleType
            if (r3 == 0) goto L_0x0044
            r5.showOldStyle(r0)
            r0 = 1
            goto L_0x004c
        L_0x0044:
            r5.showNewStyle(r0)
            goto L_0x004b
        L_0x0048:
            r5.hideIndicators()
        L_0x004b:
            r0 = r2
        L_0x004c:
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r5.mState
            boolean r3 = r3.roaming
            if (r3 == 0) goto L_0x0055
            r5.showRoaming()
        L_0x0055:
            android.widget.ImageView r3 = r5.mMobileRoaming
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r4 = r5.mState
            boolean r4 = r4.roaming
            if (r4 == 0) goto L_0x005f
            r4 = r2
            goto L_0x0060
        L_0x005f:
            r4 = r1
        L_0x0060:
            r3.setVisibility(r4)
            android.view.View r3 = r5.mMobileRoamingSpace
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r4 = r5.mState
            boolean r4 = r4.roaming
            if (r4 != 0) goto L_0x0070
            if (r0 == 0) goto L_0x006e
            goto L_0x0070
        L_0x006e:
            r0 = r1
            goto L_0x0071
        L_0x0070:
            r0 = r2
        L_0x0071:
            r3.setVisibility(r0)
            android.widget.ImageView r0 = r5.mIn
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r5.mState
            boolean r3 = r3.activityIn
            if (r3 == 0) goto L_0x007e
            r3 = r2
            goto L_0x007f
        L_0x007e:
            r3 = r1
        L_0x007f:
            r0.setVisibility(r3)
            android.widget.ImageView r0 = r5.mOut
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r5.mState
            boolean r3 = r3.activityOut
            if (r3 == 0) goto L_0x008c
            r3 = r2
            goto L_0x008d
        L_0x008c:
            r3 = r1
        L_0x008d:
            r0.setVisibility(r3)
            android.view.View r0 = r5.mInoutContainer
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r5.mState
            boolean r3 = r3.activityIn
            if (r3 != 0) goto L_0x00a1
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r5.mState
            boolean r3 = r3.activityOut
            if (r3 == 0) goto L_0x009f
            goto L_0x00a1
        L_0x009f:
            r3 = r1
            goto L_0x00a2
        L_0x00a1:
            r3 = r2
        L_0x00a2:
            r0.setVisibility(r3)
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r5.mState
            int r0 = r0.volteId
            if (r0 <= 0) goto L_0x00b6
            android.widget.ImageView r1 = r5.mVolte
            r1.setImageResource(r0)
            android.widget.ImageView r5 = r5.mVolte
            r5.setVisibility(r2)
            goto L_0x00bb
        L_0x00b6:
            android.widget.ImageView r5 = r5.mVolte
            r5.setVisibility(r1)
        L_0x00bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.StatusBarMobileView.initViewState():void");
    }

    private void setMobileSignalWidth(boolean z) {
        ViewGroup.LayoutParams layoutParams = this.mMobileSignalType.getLayoutParams();
        if (z) {
            layoutParams.width = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_mobile_signal_width);
        } else {
            layoutParams.width = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_mobile_signal_with_type_width);
            this.mMobileTypeSmall.setPadding(this.mMobileTypeSmall.getWidth() < this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_mobile_type_padding_limit) ? this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_mobile_type_padding) : 0, 0, 0, 0);
        }
        this.mMobileSignalType.setLayoutParams(layoutParams);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateState(com.android.systemui.statusbar.phone.StatusBarSignalPolicy.MobileIconState r8, boolean r9) {
        /*
            r7 = this;
            java.lang.String r0 = r8.contentDescription
            r7.setContentDescription(r0)
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r7.mState
            boolean r0 = r0.visible
            boolean r1 = r8.visible
            r2 = 1
            r3 = 8
            r4 = 0
            if (r0 == r1) goto L_0x001f
            android.widget.LinearLayout r0 = r7.mMobileGroup
            boolean r1 = r8.visible
            if (r1 == 0) goto L_0x0019
            r1 = r4
            goto L_0x001a
        L_0x0019:
            r1 = r3
        L_0x001a:
            r0.setVisibility(r1)
            r0 = r2
            goto L_0x0020
        L_0x001f:
            r0 = r4
        L_0x0020:
            int r1 = r8.strengthId
            if (r1 <= 0) goto L_0x002f
            com.android.settingslib.graph.SignalDrawable r5 = r7.mMobileDrawable
            r5.setLevel(r1)
            android.widget.ImageView r1 = r7.mMobile
            r1.setVisibility(r4)
            goto L_0x0034
        L_0x002f:
            android.widget.ImageView r1 = r7.mMobile
            r1.setVisibility(r3)
        L_0x0034:
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r1 = r7.mState
            int r1 = r1.typeId
            int r5 = r8.typeId
            if (r1 != r5) goto L_0x0044
            boolean r1 = r7.mOldStyleType
            if (r1 == r9) goto L_0x0041
            goto L_0x0044
        L_0x0041:
            r1 = r0
            r0 = r4
            goto L_0x0067
        L_0x0044:
            int r1 = r8.typeId
            if (r1 == 0) goto L_0x0051
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r1 = r7.mState
            int r1 = r1.typeId
            if (r1 != 0) goto L_0x004f
            goto L_0x0051
        L_0x004f:
            r1 = r4
            goto L_0x0052
        L_0x0051:
            r1 = r2
        L_0x0052:
            r0 = r0 | r1
            int r1 = r8.typeId
            if (r1 == 0) goto L_0x0063
            if (r9 == 0) goto L_0x005f
            r7.showOldStyle(r8)
            r1 = r0
            r0 = r2
            goto L_0x0067
        L_0x005f:
            r7.showNewStyle(r8)
            goto L_0x0041
        L_0x0063:
            r7.hideIndicators()
            goto L_0x0041
        L_0x0067:
            boolean r5 = r8.roaming
            if (r5 == 0) goto L_0x006e
            r7.showRoaming()
        L_0x006e:
            android.widget.ImageView r5 = r7.mMobileRoaming
            boolean r6 = r8.roaming
            if (r6 == 0) goto L_0x0076
            r6 = r4
            goto L_0x0077
        L_0x0076:
            r6 = r3
        L_0x0077:
            r5.setVisibility(r6)
            android.view.View r5 = r7.mMobileRoamingSpace
            if (r0 != 0) goto L_0x0085
            boolean r0 = r8.roaming
            if (r0 == 0) goto L_0x0083
            goto L_0x0085
        L_0x0083:
            r0 = r3
            goto L_0x0086
        L_0x0085:
            r0 = r4
        L_0x0086:
            r5.setVisibility(r0)
            android.widget.ImageView r0 = r7.mIn
            boolean r5 = r8.activityIn
            if (r5 == 0) goto L_0x0091
            r5 = r4
            goto L_0x0092
        L_0x0091:
            r5 = r3
        L_0x0092:
            r0.setVisibility(r5)
            android.widget.ImageView r0 = r7.mOut
            boolean r5 = r8.activityOut
            if (r5 == 0) goto L_0x009d
            r5 = r4
            goto L_0x009e
        L_0x009d:
            r5 = r3
        L_0x009e:
            r0.setVisibility(r5)
            android.view.View r0 = r7.mInoutContainer
            boolean r5 = r8.activityIn
            if (r5 != 0) goto L_0x00ae
            boolean r5 = r8.activityOut
            if (r5 == 0) goto L_0x00ac
            goto L_0x00ae
        L_0x00ac:
            r5 = r3
            goto L_0x00af
        L_0x00ae:
            r5 = r4
        L_0x00af:
            r0.setVisibility(r5)
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r0 = r7.mState
            int r0 = r0.volteId
            int r5 = r8.volteId
            if (r0 == r5) goto L_0x00cc
            if (r5 == 0) goto L_0x00c7
            android.widget.ImageView r0 = r7.mVolte
            r0.setImageResource(r5)
            android.widget.ImageView r0 = r7.mVolte
            r0.setVisibility(r4)
            goto L_0x00cc
        L_0x00c7:
            android.widget.ImageView r0 = r7.mVolte
            r0.setVisibility(r3)
        L_0x00cc:
            boolean r0 = r8.roaming
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r7.mState
            boolean r3 = r3.roaming
            if (r0 != r3) goto L_0x00ea
            boolean r0 = r8.activityIn
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r7.mState
            boolean r3 = r3.activityIn
            if (r0 != r3) goto L_0x00ea
            boolean r0 = r8.activityOut
            com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState r3 = r7.mState
            boolean r3 = r3.activityOut
            if (r0 != r3) goto L_0x00ea
            boolean r0 = r7.mOldStyleType
            if (r0 == r9) goto L_0x00e9
            goto L_0x00ea
        L_0x00e9:
            r2 = r4
        L_0x00ea:
            r0 = r1 | r2
            r7.mState = r8
            r7.mOldStyleType = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.StatusBarMobileView.updateState(com.android.systemui.statusbar.phone.StatusBarSignalPolicy$MobileIconState, boolean):boolean");
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        if (!DarkIconDispatcher.isInArea(rect, this)) {
            f = 0.0f;
        }
        this.mMobileDrawable.setTintList(ColorStateList.valueOf(this.mDualToneHandler.getSingleColor(f)));
        ColorStateList valueOf = ColorStateList.valueOf(DarkIconDispatcher.getTint(rect, this, i));
        this.mIn.setImageTintList(valueOf);
        this.mOut.setImageTintList(valueOf);
        this.mMobileType.setImageTintList(valueOf);
        this.mMobileTypeSmall.setImageTintList(valueOf);
        this.mMobileRoaming.setImageTintList(valueOf);
        this.mVolte.setImageTintList(valueOf);
        this.mDotView.setDecorColor(i);
        this.mDotView.setIconColor(i, false);
    }

    public String getSlot() {
        return this.mSlot;
    }

    public void setSlot(String str) {
        this.mSlot = str;
    }

    public void setStaticDrawableColor(int i) {
        ColorStateList valueOf = ColorStateList.valueOf(i);
        this.mMobileDrawable.setTintList(ColorStateList.valueOf(this.mDualToneHandler.getSingleColor(i == -1 ? 0.0f : 1.0f)));
        this.mIn.setImageTintList(valueOf);
        this.mOut.setImageTintList(valueOf);
        this.mMobileType.setImageTintList(valueOf);
        this.mMobileRoaming.setImageTintList(valueOf);
        this.mVolte.setImageTintList(valueOf);
        this.mMobileTypeSmall.setImageTintList(valueOf);
        this.mDotView.setDecorColor(i);
    }

    public void setDecorColor(int i) {
        this.mDotView.setDecorColor(i);
    }

    public boolean isIconVisible() {
        return this.mState.visible;
    }

    public void setVisibleState(int i, boolean z) {
        if (i != this.mVisibleState) {
            this.mVisibleState = i;
            if (i == 0) {
                this.mMobileGroup.setVisibility(0);
                this.mDotView.setVisibility(8);
            } else if (i != 1) {
                this.mMobileGroup.setVisibility(4);
                this.mDotView.setVisibility(4);
            } else {
                this.mMobileGroup.setVisibility(4);
                this.mDotView.setVisibility(0);
            }
        }
    }

    public int getVisibleState() {
        return this.mVisibleState;
    }

    @VisibleForTesting
    public StatusBarSignalPolicy.MobileIconState getState() {
        return this.mState;
    }

    public String toString() {
        return "StatusBarMobileView(slot=" + this.mSlot + " state=" + this.mState + ")";
    }

    private void showOldStyle(StatusBarSignalPolicy.MobileIconState mobileIconState) {
        this.mMobileType.setVisibility(8);
        this.mMobileTypeSmall.setContentDescription(mobileIconState.typeContentDescription);
        this.mMobileTypeSmall.setImageResource(mobileIconState.typeId);
        this.mMobileTypeSmall.setVisibility(0);
        setMobileSignalWidth(false);
    }

    private void showNewStyle(StatusBarSignalPolicy.MobileIconState mobileIconState) {
        this.mMobileType.setVisibility(0);
        this.mMobileType.setContentDescription(mobileIconState.typeContentDescription);
        this.mMobileType.setImageResource(mobileIconState.typeId);
        this.mMobileTypeSmall.setVisibility(8);
        setMobileSignalWidth(true);
    }

    private void showRoaming() {
        this.mMobileTypeSmall.setVisibility(8);
        setMobileSignalWidth(true);
    }

    private void hideIndicators() {
        this.mMobileType.setVisibility(8);
        this.mMobileTypeSmall.setVisibility(8);
        setMobileSignalWidth(true);
    }
}
