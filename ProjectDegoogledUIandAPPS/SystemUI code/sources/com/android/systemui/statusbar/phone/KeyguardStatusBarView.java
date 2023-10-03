package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.BatteryMeterView;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.p006qs.QSPanel;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcher;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class KeyguardStatusBarView extends RelativeLayout implements UserInfoController.OnUserInfoChangedListener, ConfigurationController.ConfigurationListener {
    private BatteryMeterView mBatteryView;
    /* access modifiers changed from: private */
    public TextView mCarrierLabel;
    private CustomSettingsObserver mCustomSettingsObserver = new CustomSettingsObserver(this.mHandler);
    private int mCutoutSideNudge = 0;
    private View mCutoutSpace;
    private final Rect mEmptyRect = new Rect(0, 0, 0, 0);
    private Handler mHandler = new Handler();
    private boolean mHideContents;
    private StatusBarIconController.TintedIconManager mIconManager;
    private boolean mImmerseMode;
    private KeyguardUserSwitcher mKeyguardUserSwitcher;
    private boolean mKeyguardUserSwitcherShowing;
    private int mLayoutState = 0;
    private ImageView mMultiUserAvatar;
    /* access modifiers changed from: private */
    public MultiUserSwitch mMultiUserSwitch;
    private ContentObserver mObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z, Uri uri) {
            KeyguardStatusBarView.this.showStatusBarCarrier();
            KeyguardStatusBarView.this.updateVisibilities();
        }
    };
    private boolean mShowCarrierLabel;
    private boolean mShowNotchView;
    /* access modifiers changed from: private */
    public ViewGroup mStatusIconArea;
    private StatusIconContainer mStatusIconContainer;
    private int mSystemIconsBaseMargin;
    /* access modifiers changed from: private */
    public View mSystemIconsContainer;
    private int mSystemIconsSwitcherHiddenExpandedMargin;
    private UserSwitcherController mUserSwitcherController;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public KeyguardStatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        showStatusBarCarrier();
    }

    /* access modifiers changed from: private */
    public void showStatusBarCarrier() {
        boolean z = true;
        boolean z2 = Settings.System.getIntForUser(getContext().getContentResolver(), "carrier_label_enabled", 1, -2) == 1;
        int intForUser = Settings.System.getIntForUser(getContext().getContentResolver(), "carrier_label_location", 0, -2);
        if (!z2 || intForUser == 1) {
            z = false;
        }
        this.mShowCarrierLabel = z;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mSystemIconsContainer = findViewById(C1777R$id.system_icons_container);
        this.mMultiUserSwitch = (MultiUserSwitch) findViewById(C1777R$id.multi_user_switch);
        this.mMultiUserAvatar = (ImageView) findViewById(C1777R$id.multi_user_avatar);
        this.mCarrierLabel = (TextView) findViewById(C1777R$id.keyguard_carrier_text);
        this.mBatteryView = (BatteryMeterView) this.mSystemIconsContainer.findViewById(C1777R$id.battery);
        this.mCutoutSpace = findViewById(C1777R$id.cutout_space_view);
        this.mStatusIconArea = (ViewGroup) findViewById(C1777R$id.status_icon_area);
        this.mStatusIconContainer = (StatusIconContainer) findViewById(C1777R$id.statusIcons);
        loadDimens();
        updateUserSwitcher();
        updateCutout();
        this.mCustomSettingsObserver.observe();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mMultiUserAvatar.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1775R$dimen.multi_user_avatar_keyguard_size);
        marginLayoutParams.height = dimensionPixelSize;
        marginLayoutParams.width = dimensionPixelSize;
        this.mMultiUserAvatar.setLayoutParams(marginLayoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mMultiUserSwitch.getLayoutParams();
        marginLayoutParams2.width = getResources().getDimensionPixelSize(C1775R$dimen.multi_user_switch_width_keyguard);
        marginLayoutParams2.setMarginEnd(getResources().getDimensionPixelSize(C1775R$dimen.multi_user_switch_keyguard_margin));
        this.mMultiUserSwitch.setLayoutParams(marginLayoutParams2);
        ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.mSystemIconsContainer.getLayoutParams();
        marginLayoutParams3.setMarginStart(getResources().getDimensionPixelSize(C1775R$dimen.system_icons_super_container_margin_start));
        this.mSystemIconsContainer.setLayoutParams(marginLayoutParams3);
        this.mCarrierLabel.setTextSize(0, (float) getResources().getDimensionPixelSize(17105462));
        ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) this.mCarrierLabel.getLayoutParams();
        marginLayoutParams4.setMarginStart(getResources().getDimensionPixelSize(C1775R$dimen.keyguard_carrier_text_margin));
        this.mCarrierLabel.setLayoutParams(marginLayoutParams4);
        updateCutout();
    }

    private void updateStatusBarHeight() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if (this.mImmerseMode) {
            marginLayoutParams.height = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_height);
        } else {
            marginLayoutParams.height = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_header_height_keyguard);
        }
        setLayoutParams(marginLayoutParams);
    }

    private void loadDimens() {
        Resources resources = getResources();
        this.mSystemIconsSwitcherHiddenExpandedMargin = resources.getDimensionPixelSize(C1775R$dimen.system_icons_switcher_hidden_expanded_margin);
        this.mSystemIconsBaseMargin = resources.getDimensionPixelSize(C1775R$dimen.system_icons_super_container_avatarless_margin_end);
        this.mCutoutSideNudge = getResources().getDimensionPixelSize(C1775R$dimen.display_cutout_margin_consumption);
    }

    /* access modifiers changed from: private */
    public void updateVisibilities() {
        if (this.mMultiUserSwitch.getParent() == this.mStatusIconArea || this.mKeyguardUserSwitcherShowing) {
            ViewParent parent = this.mMultiUserSwitch.getParent();
            ViewGroup viewGroup = this.mStatusIconArea;
            if (parent == viewGroup && this.mKeyguardUserSwitcherShowing) {
                viewGroup.removeView(this.mMultiUserSwitch);
            }
        } else {
            if (this.mMultiUserSwitch.getParent() != null) {
                getOverlay().remove(this.mMultiUserSwitch);
            }
            this.mStatusIconArea.addView(this.mMultiUserSwitch, 0);
        }
        int i = 4;
        if (this.mKeyguardUserSwitcher == null) {
            if (this.mMultiUserSwitch.isMultiUserEnabled()) {
                this.mMultiUserSwitch.setVisibility(this.mHideContents ? 4 : 0);
            } else {
                this.mMultiUserSwitch.setVisibility(8);
            }
        }
        TextView textView = this.mCarrierLabel;
        if (textView == null) {
            return;
        }
        if (this.mShowCarrierLabel) {
            if (!this.mHideContents) {
                i = 0;
            }
            textView.setVisibility(i);
            this.mCarrierLabel.setSelected(true);
            return;
        }
        textView.setVisibility(8);
        this.mCarrierLabel.setSelected(false);
    }

    private void updateSystemIconsLayoutParams() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mSystemIconsContainer.getLayoutParams();
        int i = this.mMultiUserSwitch.getVisibility() == 8 ? this.mSystemIconsBaseMargin : 0;
        if (this.mKeyguardUserSwitcherShowing) {
            i = this.mSystemIconsSwitcherHiddenExpandedMargin;
        }
        if (i != layoutParams.getMarginEnd()) {
            layoutParams.setMarginEnd(i);
            this.mSystemIconsContainer.setLayoutParams(layoutParams);
        }
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.mLayoutState = 0;
        if (updateLayoutConsideringCutout()) {
            requestLayout();
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    private boolean updateLayoutConsideringCutout() {
        DisplayCutout displayCutout = getRootWindowInsets().getDisplayCutout();
        Pair<Integer, Integer> cornerCutoutMargins = PhoneStatusBarView.cornerCutoutMargins(displayCutout, getDisplay());
        updateCornerCutoutPadding(cornerCutoutMargins);
        if (displayCutout == null || cornerCutoutMargins != null) {
            return updateLayoutParamsNoCutout();
        }
        return updateLayoutParamsForCutout(displayCutout);
    }

    private void updateCornerCutoutPadding(Pair<Integer, Integer> pair) {
        if (pair != null) {
            setPadding(Math.max(getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_start), ((Integer) pair.first).intValue()), 0, Math.max(getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_end), ((Integer) pair.second).intValue()), 0);
            return;
        }
        setPadding(0, 0, 0, 0);
    }

    private boolean updateLayoutParamsNoCutout() {
        if (this.mLayoutState == 2) {
            return false;
        }
        this.mLayoutState = 2;
        View view = this.mCutoutSpace;
        if (view != null) {
            view.setVisibility(8);
        }
        ((RelativeLayout.LayoutParams) this.mCarrierLabel.getLayoutParams()).addRule(16, C1777R$id.status_icon_area);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mStatusIconArea.getLayoutParams();
        layoutParams.removeRule(1);
        layoutParams.width = -2;
        ((LinearLayout.LayoutParams) this.mSystemIconsContainer.getLayoutParams()).setMarginStart(getResources().getDimensionPixelSize(C1775R$dimen.system_icons_super_container_margin_start));
        return true;
    }

    private boolean updateLayoutParamsForCutout(DisplayCutout displayCutout) {
        if (this.mLayoutState == 1) {
            return false;
        }
        this.mLayoutState = 1;
        if (this.mCutoutSpace == null) {
            updateLayoutParamsNoCutout();
        }
        Rect rect = new Rect();
        ScreenDecorations.DisplayCutoutView.boundsFromDirection(displayCutout, 48, rect);
        this.mShowNotchView = getResources().getBoolean(C1773R$bool.hide_view_behind_notch);
        if (!this.mShowNotchView) {
            this.mCutoutSpace.setVisibility(0);
        } else {
            this.mCutoutSpace.setVisibility(8);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCutoutSpace.getLayoutParams();
        int i = rect.left;
        int i2 = this.mCutoutSideNudge;
        rect.left = i + i2;
        rect.right -= i2;
        layoutParams.width = rect.width();
        layoutParams.height = rect.height();
        layoutParams.addRule(13);
        ((RelativeLayout.LayoutParams) this.mCarrierLabel.getLayoutParams()).addRule(16, C1777R$id.cutout_space_view);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mStatusIconArea.getLayoutParams();
        layoutParams2.addRule(1, C1777R$id.cutout_space_view);
        layoutParams2.width = -1;
        ((LinearLayout.LayoutParams) this.mSystemIconsContainer.getLayoutParams()).setMarginStart(0);
        return true;
    }

    private void updateUserSwitcher() {
        boolean z = this.mKeyguardUserSwitcher != null;
        this.mMultiUserSwitch.setClickable(z);
        this.mMultiUserSwitch.setFocusable(z);
        this.mMultiUserSwitch.setKeyguardMode(z);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        UserInfoController userInfoController = (UserInfoController) Dependency.get(UserInfoController.class);
        userInfoController.addCallback(this);
        this.mUserSwitcherController = (UserSwitcherController) Dependency.get(UserSwitcherController.class);
        this.mMultiUserSwitch.setUserSwitcherController(this.mUserSwitcherController);
        userInfoController.reloadUserInfo();
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        this.mIconManager = new StatusBarIconController.TintedIconManager((ViewGroup) findViewById(C1777R$id.statusIcons));
        ((StatusBarIconController) Dependency.get(StatusBarIconController.class)).addIconGroup(this.mIconManager);
        getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("carrier_label_enabled"), false, this.mObserver);
        getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("carrier_label_location"), false, this.mObserver);
        onThemeChanged();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((UserInfoController) Dependency.get(UserInfoController.class)).removeCallback(this);
        ((StatusBarIconController) Dependency.get(StatusBarIconController.class)).removeIconGroup(this.mIconManager);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).removeCallback(this);
    }

    public void onUserInfoChanged(String str, Drawable drawable, String str2) {
        this.mMultiUserAvatar.setImageDrawable(drawable);
    }

    public void setQSPanel(QSPanel qSPanel) {
        this.mMultiUserSwitch.setQsPanel(qSPanel);
    }

    public void setKeyguardUserSwitcher(KeyguardUserSwitcher keyguardUserSwitcher) {
        this.mKeyguardUserSwitcher = keyguardUserSwitcher;
        this.mMultiUserSwitch.setKeyguardUserSwitcher(keyguardUserSwitcher);
        updateUserSwitcher();
    }

    public void setKeyguardUserSwitcherShowing(boolean z, boolean z2) {
        this.mKeyguardUserSwitcherShowing = z;
        if (z2) {
            animateNextLayoutChange();
        }
        updateVisibilities();
        updateLayoutConsideringCutout();
        updateSystemIconsLayoutParams();
    }

    private void animateNextLayoutChange() {
        final int left = this.mSystemIconsContainer.getLeft();
        final boolean z = this.mMultiUserSwitch.getParent() == this.mStatusIconArea;
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                KeyguardStatusBarView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                boolean z = z && KeyguardStatusBarView.this.mMultiUserSwitch.getParent() != KeyguardStatusBarView.this.mStatusIconArea;
                KeyguardStatusBarView.this.mSystemIconsContainer.setX((float) left);
                KeyguardStatusBarView.this.mSystemIconsContainer.animate().translationX(0.0f).setDuration(400).setStartDelay(z ? 300 : 0).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).start();
                if (z) {
                    KeyguardStatusBarView.this.getOverlay().add(KeyguardStatusBarView.this.mMultiUserSwitch);
                    KeyguardStatusBarView.this.mMultiUserSwitch.animate().alpha(0.0f).setDuration(300).setStartDelay(0).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(new Runnable() {
                        public final void run() {
                            KeyguardStatusBarView.C13622.this.lambda$onPreDraw$0$KeyguardStatusBarView$2();
                        }
                    }).start();
                } else {
                    KeyguardStatusBarView.this.mMultiUserSwitch.setAlpha(0.0f);
                    KeyguardStatusBarView.this.mMultiUserSwitch.animate().alpha(1.0f).setDuration(300).setStartDelay(200).setInterpolator(Interpolators.ALPHA_IN);
                }
                return true;
            }

            public /* synthetic */ void lambda$onPreDraw$0$KeyguardStatusBarView$2() {
                KeyguardStatusBarView.this.mMultiUserSwitch.setAlpha(1.0f);
                KeyguardStatusBarView.this.getOverlay().remove(KeyguardStatusBarView.this.mMultiUserSwitch);
            }
        });
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i != 0) {
            this.mSystemIconsContainer.animate().cancel();
            this.mSystemIconsContainer.setTranslationX(0.0f);
            this.mMultiUserSwitch.animate().cancel();
            this.mMultiUserSwitch.setAlpha(1.0f);
            return;
        }
        updateVisibilities();
        updateSystemIconsLayoutParams();
    }

    public void onThemeChanged() {
        this.mBatteryView.setColorsFromContext(this.mContext);
        updateIconsAndTextColors();
        ((UserInfoControllerImpl) Dependency.get(UserInfoController.class)).onDensityOrFontScaleChanged();
    }

    public void onDensityOrFontScaleChanged() {
        loadDimens();
    }

    public void onOverlayChanged() {
        this.mCarrierLabel.setTextAppearance(Utils.getThemeAttr(this.mContext, 16842818));
        onThemeChanged();
        this.mBatteryView.updatePercentView();
    }

    private void updateIconsAndTextColors() {
        int i;
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(this.mContext, C1772R$attr.wallpaperTextColor);
        Context context = this.mContext;
        if (((double) Color.luminance(colorAttrDefaultColor)) < 0.5d) {
            i = C1774R$color.dark_mode_icon_color_single_tone;
        } else {
            i = C1774R$color.light_mode_icon_color_single_tone;
        }
        int colorStateListDefaultColor = Utils.getColorStateListDefaultColor(context, i);
        float f = colorAttrDefaultColor == -1 ? 0.0f : 1.0f;
        this.mCarrierLabel.setTextColor(colorStateListDefaultColor);
        StatusBarIconController.TintedIconManager tintedIconManager = this.mIconManager;
        if (tintedIconManager != null) {
            tintedIconManager.setTint(colorStateListDefaultColor);
        }
        applyDarkness(C1777R$id.battery, this.mEmptyRect, f, colorStateListDefaultColor);
        applyDarkness(C1777R$id.clock, this.mEmptyRect, f, colorStateListDefaultColor);
    }

    private void applyDarkness(int i, Rect rect, float f, int i2) {
        View findViewById = findViewById(i);
        if (findViewById instanceof DarkIconDispatcher.DarkReceiver) {
            ((DarkIconDispatcher.DarkReceiver) findViewById).onDarkChanged(rect, f, i2);
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("KeyguardStatusBarView:");
        printWriter.println("  mKeyguardUserSwitcherShowing: " + this.mKeyguardUserSwitcherShowing);
        printWriter.println("  mLayoutState: " + this.mLayoutState);
        BatteryMeterView batteryMeterView = this.mBatteryView;
        if (batteryMeterView != null) {
            batteryMeterView.dump(fileDescriptor, printWriter, strArr);
        }
    }

    private class CustomSettingsObserver extends ContentObserver {
        CustomSettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            KeyguardStatusBarView.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("display_cutout_mode"), false, this);
        }

        public void onChange(boolean z, Uri uri) {
            if (uri.equals(Settings.System.getUriFor("display_cutout_mode"))) {
                KeyguardStatusBarView.this.updateCutout();
            }
        }

        public void onChange(boolean z) {
            KeyguardStatusBarView.this.updateCutout();
        }
    }

    /* access modifiers changed from: private */
    public void updateCutout() {
        boolean z = false;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "display_cutout_mode", 0, -2) == 1) {
            z = true;
        }
        this.mImmerseMode = z;
        updateStatusBarHeight();
    }

    public void toggleContents(boolean z) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        if (Settings.System.getIntForUser(getContext().getContentResolver(), "lockscreen_show_status_bar", 1, -2) == 1) {
            z = false;
        }
        if (this.mHideContents != z) {
            this.mHideContents = z;
            ObjectAnimator objectAnimator3 = null;
            if (this.mHideContents) {
                if (this.mMultiUserSwitch.getVisibility() != 8) {
                    objectAnimator2 = ObjectAnimator.ofFloat(this.mMultiUserSwitch, "alpha", new float[]{1.0f, 0.0f});
                    objectAnimator2.setDuration(500);
                    objectAnimator2.setInterpolator(Interpolators.ALPHA_OUT);
                    objectAnimator2.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            KeyguardStatusBarView.this.mMultiUserSwitch.setVisibility(4);
                        }
                    });
                } else {
                    objectAnimator2 = null;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mSystemIconsContainer, "alpha", new float[]{1.0f, 0.0f});
                ofFloat.setDuration(500);
                ofFloat.setInterpolator(Interpolators.ALPHA_OUT);
                ofFloat.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        KeyguardStatusBarView.this.mSystemIconsContainer.setVisibility(4);
                    }
                });
                if (this.mCarrierLabel.getVisibility() != 8) {
                    objectAnimator3 = ObjectAnimator.ofFloat(this.mCarrierLabel, "alpha", new float[]{1.0f, 0.0f});
                    objectAnimator3.setDuration(500);
                    objectAnimator3.setInterpolator(Interpolators.ALPHA_OUT);
                    objectAnimator3.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            KeyguardStatusBarView.this.mCarrierLabel.setVisibility(4);
                        }
                    });
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{ofFloat});
                if (objectAnimator3 != null) {
                    animatorSet.playTogether(new Animator[]{objectAnimator3});
                }
                if (objectAnimator2 != null) {
                    animatorSet.playTogether(new Animator[]{objectAnimator2});
                }
                animatorSet.start();
                return;
            }
            if (this.mMultiUserSwitch.getVisibility() != 8) {
                this.mMultiUserSwitch.setAlpha(0.0f);
                this.mMultiUserSwitch.setVisibility(0);
                objectAnimator = ObjectAnimator.ofFloat(this.mMultiUserSwitch, "alpha", new float[]{0.0f, 1.0f});
                objectAnimator.setDuration(500);
                objectAnimator.setInterpolator(Interpolators.ALPHA_IN);
            } else {
                objectAnimator = null;
            }
            this.mSystemIconsContainer.setAlpha(0.0f);
            this.mSystemIconsContainer.setVisibility(0);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mSystemIconsContainer, "alpha", new float[]{0.0f, 1.0f});
            ofFloat2.setDuration(500);
            ofFloat2.setInterpolator(Interpolators.ALPHA_IN);
            if (this.mCarrierLabel.getVisibility() != 8) {
                this.mCarrierLabel.setAlpha(0.0f);
                this.mCarrierLabel.setVisibility(0);
                objectAnimator3 = ObjectAnimator.ofFloat(this.mCarrierLabel, "alpha", new float[]{0.0f, 1.0f});
                objectAnimator3.setDuration(500);
                objectAnimator3.setInterpolator(Interpolators.ALPHA_IN);
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(new Animator[]{ofFloat2});
            if (objectAnimator3 != null) {
                animatorSet2.playTogether(new Animator[]{objectAnimator3});
            }
            if (objectAnimator != null) {
                animatorSet2.playTogether(new Animator[]{objectAnimator});
            }
            animatorSet2.start();
        }
    }
}
