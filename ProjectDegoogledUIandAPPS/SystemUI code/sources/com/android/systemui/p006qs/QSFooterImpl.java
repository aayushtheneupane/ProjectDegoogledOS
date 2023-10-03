package com.android.systemui.p006qs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.UserManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.settingslib.Utils;
import com.android.settingslib.drawable.UserIconDrawable;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.TouchAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.MultiUserSwitch;
import com.android.systemui.statusbar.phone.SettingsButton;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.UserInfoController;

/* renamed from: com.android.systemui.qs.QSFooterImpl */
public class QSFooterImpl extends FrameLayout implements QSFooter, View.OnClickListener, View.OnLongClickListener, UserInfoController.OnUserInfoChangedListener {
    private View mActionsContainer;
    private final ActivityStarter mActivityStarter;
    private final DeviceProvisionedController mDeviceProvisionedController;
    private View mDragHandle;
    protected View mEdit;
    protected View mEditContainer;
    private View.OnClickListener mExpandClickListener;
    private boolean mExpanded;
    private float mExpansionAmount;
    protected TouchAnimator mFooterAnimator;
    private boolean mListening;
    private ImageView mMultiUserAvatar;
    protected MultiUserSwitch mMultiUserSwitch;
    private PageIndicator mPageIndicator;
    private boolean mQsDisabled;
    private QSPanel mQsPanel;
    private QuickQSPanel mQuickQSPanel;
    private View mRunningServicesButton;
    private SettingsButton mSettingsButton;
    private TouchAnimator mSettingsCogAnimator;
    protected View mSettingsContainer;
    private final UserInfoController mUserInfoController;
    protected Vibrator mVibrator;

    static /* synthetic */ void lambda$onClick$4() {
    }

    public QSFooterImpl(Context context, AttributeSet attributeSet, ActivityStarter activityStarter, UserInfoController userInfoController, DeviceProvisionedController deviceProvisionedController) {
        super(context, attributeSet);
        this.mActivityStarter = activityStarter;
        this.mUserInfoController = userInfoController;
        this.mDeviceProvisionedController = deviceProvisionedController;
    }

    public QSFooterImpl(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (ActivityStarter) Dependency.get(ActivityStarter.class), (UserInfoController) Dependency.get(UserInfoController.class), (DeviceProvisionedController) Dependency.get(DeviceProvisionedController.class));
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mEdit = findViewById(16908291);
        this.mEdit.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                QSFooterImpl.this.lambda$onFinishInflate$1$QSFooterImpl(view);
            }
        });
        this.mPageIndicator = (PageIndicator) findViewById(C1777R$id.footer_page_indicator);
        this.mSettingsButton = (SettingsButton) findViewById(C1777R$id.settings_button);
        this.mSettingsContainer = findViewById(C1777R$id.settings_button_container);
        this.mSettingsButton.setOnClickListener(this);
        this.mSettingsButton.setOnLongClickListener(this);
        this.mRunningServicesButton = findViewById(C1777R$id.running_services_button);
        this.mRunningServicesButton.setOnClickListener(this);
        this.mMultiUserSwitch = (MultiUserSwitch) findViewById(C1777R$id.multi_user_switch);
        this.mMultiUserAvatar = (ImageView) this.mMultiUserSwitch.findViewById(C1777R$id.multi_user_avatar);
        this.mDragHandle = findViewById(C1777R$id.qs_drag_handle_view);
        this.mActionsContainer = findViewById(C1777R$id.qs_footer_actions_container);
        this.mEditContainer = findViewById(C1777R$id.qs_footer_actions_edit_container);
        this.mVibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        ((RippleDrawable) this.mSettingsButton.getBackground()).setForceSoftware(true);
        ((RippleDrawable) this.mRunningServicesButton.getBackground()).setForceSoftware(true);
        updateResources();
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                QSFooterImpl.this.lambda$onFinishInflate$2$QSFooterImpl(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        setImportantForAccessibility(1);
        updateEverything();
    }

    public /* synthetic */ void lambda$onFinishInflate$1$QSFooterImpl(View view) {
        this.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable(view) {
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                QSFooterImpl.this.lambda$onFinishInflate$0$QSFooterImpl(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onFinishInflate$0$QSFooterImpl(View view) {
        this.mQsPanel.showEdit(view);
    }

    public /* synthetic */ void lambda$onFinishInflate$2$QSFooterImpl(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        updateAnimator(i3 - i);
    }

    private void updateAnimator(int i) {
        int numQuickTiles = this.mQuickQSPanel.getNumQuickTiles();
        int dimensionPixelSize = (i - ((this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_quick_tile_size) - this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_quick_tile_padding)) * numQuickTiles)) / (numQuickTiles - 1);
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(C1775R$dimen.default_gear_space);
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        View view = this.mSettingsContainer;
        float[] fArr = new float[2];
        int i2 = dimensionPixelSize - dimensionPixelOffset;
        if (!isLayoutRtl()) {
            i2 = -i2;
        }
        fArr[0] = (float) i2;
        fArr[1] = 0.0f;
        builder.addFloat(view, "translationX", fArr);
        builder.addFloat(this.mSettingsButton, "rotation", -120.0f, 0.0f);
        this.mSettingsCogAnimator = builder.build();
        setExpansion(this.mExpansionAmount);
    }

    public void vibrateheader() {
        Vibrator vibrator = this.mVibrator;
        if (vibrator != null && vibrator.hasVibrator()) {
            this.mVibrator.vibrate(VibrationEffect.get(5));
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResources();
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        updateResources();
    }

    private void updateResources() {
        updateFooterAnimator();
    }

    private void updateFooterAnimator() {
        this.mFooterAnimator = createFooterAnimator();
    }

    private TouchAnimator createFooterAnimator() {
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mActionsContainer, "alpha", 0.0f, 1.0f);
        builder.addFloat(this.mMultiUserAvatar, "alpha", 0.0f, 1.0f);
        builder.addFloat(this.mEditContainer, "alpha", 0.0f, 1.0f);
        builder.addFloat(this.mDragHandle, "alpha", 1.0f, 0.0f, 0.0f);
        builder.addFloat(this.mPageIndicator, "alpha", 0.0f, 1.0f);
        builder.setStartDelay(0.15f);
        return builder.build();
    }

    public void setKeyguardShowing(boolean z) {
        setExpansion(this.mExpansionAmount);
    }

    public void setExpandClickListener(View.OnClickListener onClickListener) {
        this.mExpandClickListener = onClickListener;
    }

    public void setExpanded(boolean z) {
        if (this.mExpanded != z) {
            this.mExpanded = z;
            updateEverything();
        }
    }

    public void setExpansion(float f) {
        this.mExpansionAmount = f;
        TouchAnimator touchAnimator = this.mSettingsCogAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f);
        }
        TouchAnimator touchAnimator2 = this.mFooterAnimator;
        if (touchAnimator2 != null) {
            touchAnimator2.setPosition(f);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        setListening(false);
        super.onDetachedFromWindow();
    }

    public void setListening(boolean z) {
        if (z != this.mListening) {
            this.mListening = z;
            updateListeners();
        }
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        View.OnClickListener onClickListener;
        if (i != 262144 || (onClickListener = this.mExpandClickListener) == null) {
            return super.performAccessibilityAction(i, bundle);
        }
        onClickListener.onClick((View) null);
        return true;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
    }

    public void disable(int i, int i2, boolean z) {
        boolean z2 = true;
        if ((i2 & 1) == 0) {
            z2 = false;
        }
        if (z2 != this.mQsDisabled) {
            this.mQsDisabled = z2;
            updateEverything();
        }
    }

    public void updateEverything() {
        post(new Runnable() {
            public final void run() {
                QSFooterImpl.this.lambda$updateEverything$3$QSFooterImpl();
            }
        });
    }

    public /* synthetic */ void lambda$updateEverything$3$QSFooterImpl() {
        updateVisibilities();
        updateClickabilities();
        setClickable(false);
    }

    private void updateClickabilities() {
        MultiUserSwitch multiUserSwitch = this.mMultiUserSwitch;
        boolean z = true;
        multiUserSwitch.setClickable(multiUserSwitch.getVisibility() == 0);
        View view = this.mEdit;
        view.setClickable(view.getVisibility() == 0);
        SettingsButton settingsButton = this.mSettingsButton;
        if (settingsButton.getVisibility() != 0) {
            z = false;
        }
        settingsButton.setClickable(z);
    }

    private void updateVisibilities() {
        boolean isDeviceInDemoMode = UserManager.isDeviceInDemoMode(this.mContext);
        int i = 8;
        this.mSettingsContainer.setVisibility((!isSettingsEnabled() || this.mQsDisabled) ? 8 : 0);
        int i2 = 4;
        this.mSettingsButton.setVisibility(isSettingsEnabled() ? (isDeviceInDemoMode || !this.mExpanded) ? 4 : 0 : 8);
        this.mRunningServicesButton.setVisibility(isServicesEnabled() ? (isDeviceInDemoMode || !this.mExpanded) ? 4 : 0 : 8);
        this.mMultiUserSwitch.setVisibility(isUserEnabled() ? showUserSwitcher() ? 0 : 4 : 8);
        View view = this.mEditContainer;
        if (!isDeviceInDemoMode && this.mExpanded) {
            i2 = 0;
        }
        view.setVisibility(i2);
        View view2 = this.mEdit;
        if (isEditEnabled()) {
            i = 0;
        }
        view2.setVisibility(i);
    }

    private boolean showUserSwitcher() {
        return this.mExpanded && this.mMultiUserSwitch.isMultiUserEnabled();
    }

    private void updateListeners() {
        if (this.mListening) {
            this.mUserInfoController.addCallback(this);
        } else {
            this.mUserInfoController.removeCallback(this);
        }
    }

    public void setQSPanel(QSPanel qSPanel, QuickQSPanel quickQSPanel) {
        this.mQsPanel = qSPanel;
        this.mQuickQSPanel = quickQSPanel;
        if (this.mQsPanel != null) {
            this.mMultiUserSwitch.setQsPanel(qSPanel);
            this.mQsPanel.setFooterPageIndicator(this.mPageIndicator);
        }
    }

    public boolean isSettingsEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "qs_footer_show_settings", 1) == 1;
    }

    public boolean isServicesEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "qs_footer_show_services", 0) == 1;
    }

    public boolean isEditEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "qs_footer_show_edit", 1) == 1;
    }

    public boolean isUserEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "qs_footer_show_user", 1) == 1;
    }

    public void onClick(View view) {
        if (this.mExpanded) {
            int i = 406;
            if (view == this.mSettingsButton) {
                if (!this.mDeviceProvisionedController.isCurrentUserSetup()) {
                    this.mActivityStarter.postQSRunnableDismissingKeyguard($$Lambda$QSFooterImpl$ORlOcuwnOcEc1bdhJcTagEFJfI4.INSTANCE);
                    return;
                }
                Context context = this.mContext;
                if (!this.mExpanded) {
                    i = 490;
                }
                MetricsLogger.action(context, i);
                startSettingsActivity();
            } else if (view == this.mRunningServicesButton) {
                Context context2 = this.mContext;
                if (!this.mExpanded) {
                    i = 490;
                }
                MetricsLogger.action(context2, i);
                startRunningServicesActivity();
            }
        }
    }

    public boolean onLongClick(View view) {
        if (view != this.mSettingsButton) {
            return false;
        }
        startConfigCenterActivity();
        vibrateheader();
        return false;
    }

    private void startRunningServicesActivity() {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$DevRunningServicesActivity");
        this.mActivityStarter.startActivity(intent, true);
    }

    private void startConfigCenterActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.Settings$ConfigCenterActivity");
        this.mActivityStarter.startActivity(intent, true);
    }

    private void startSettingsActivity() {
        this.mActivityStarter.startActivity(new Intent("android.settings.SETTINGS"), true);
    }

    public void onUserInfoChanged(String str, Drawable drawable, String str2) {
        if (drawable != null && UserManager.get(this.mContext).isGuestUser(KeyguardUpdateMonitor.getCurrentUser()) && !(drawable instanceof UserIconDrawable)) {
            drawable = drawable.getConstantState().newDrawable(this.mContext.getResources()).mutate();
            drawable.setColorFilter(Utils.getColorAttrDefaultColor(this.mContext, 16842800), PorterDuff.Mode.SRC_IN);
        }
        this.mMultiUserAvatar.setImageDrawable(drawable);
    }
}
