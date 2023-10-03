package com.android.systemui.p006qs;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.StatsLog;
import android.view.ContextThemeWrapper;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.BatteryMeterView;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1785R$style;
import com.android.systemui.DualToneHandler;
import com.android.systemui.p006qs.QSDetail;
import com.android.systemui.p006qs.TouchAnimator;
import com.android.systemui.p006qs.customize.QSCustomizer;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.DateView;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.ZenModeController;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* renamed from: com.android.systemui.qs.QuickStatusBarHeader */
public class QuickStatusBarHeader extends RelativeLayout implements View.OnClickListener, NextAlarmController.NextAlarmChangeCallback, ZenModeController.Callback {
    private final ActivityStarter mActivityStarter;
    private final NextAlarmController mAlarmController;
    private boolean mBatteryInQS;
    private BatteryMeterView mBatteryMeterView;
    private BatteryMeterView mBatteryRemainingIcon;
    private QSCarrierGroup mCarrierGroup;
    private Clock mClockView;
    private DateView mDateView;
    private DualToneHandler mDualToneHandler;
    private boolean mExpanded;
    private boolean mForceHideQsStatusBar;
    private final Handler mHandler = new Handler();
    private boolean mHasTopCutout = false;
    private boolean mHeaderImageEnabled;
    protected QuickQSPanel mHeaderQsPanel;
    private TouchAnimator mHeaderTextContainerAlphaAnimator;
    private View mHeaderTextContainerView;
    protected QSTileHost mHost;
    private StatusBarIconController.TintedIconManager mIconManager;
    private boolean mLandscape;
    private boolean mListening;
    private AlarmManager.AlarmClockInfo mNextAlarm;
    private View mNextAlarmContainer;
    private ImageView mNextAlarmIcon;
    private TextView mNextAlarmTextView;
    private PrivacyItemController.Callback mPICCallback = new PrivacyItemController.Callback() {
        public void privacyChanged(List<PrivacyItem> list) {
            QuickStatusBarHeader.this.mPrivacyChip.setPrivacyList(list);
            QuickStatusBarHeader.this.setChipVisibility(!list.isEmpty());
        }
    };
    private boolean mPermissionsHubEnabled;
    /* access modifiers changed from: private */
    public OngoingPrivacyChip mPrivacyChip;
    private TouchAnimator mPrivacyChipAlphaAnimator;
    private boolean mPrivacyChipLogged = false;
    private PrivacyItemController mPrivacyItemController;
    private boolean mQsDisabled;
    private QSPanel mQsPanel;
    private View mQuickQsStatusIcons;
    private View mRingerContainer;
    /* access modifiers changed from: private */
    public int mRingerMode = 2;
    private ImageView mRingerModeIcon;
    private TextView mRingerModeTextView;
    private final BroadcastReceiver mRingerReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int unused = QuickStatusBarHeader.this.mRingerMode = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", -1);
            QuickStatusBarHeader.this.updateStatusText();
        }
    };
    private SettingsObserver mSettingsObserver = new SettingsObserver(this.mHandler);
    private Space mSpace;
    private final StatusBarIconController mStatusBarIconController;
    private TouchAnimator mStatusIconsAlphaAnimator;
    private View mStatusSeparator;
    private View mSystemIconsView;
    private final ZenModeController mZenController;

    public static float getColorIntensity(int i) {
        return i == -1 ? 0.0f : 1.0f;
    }

    public QuickStatusBarHeader(Context context, AttributeSet attributeSet, NextAlarmController nextAlarmController, ZenModeController zenModeController, StatusBarIconController statusBarIconController, ActivityStarter activityStarter, PrivacyItemController privacyItemController) {
        super(context, attributeSet);
        this.mAlarmController = nextAlarmController;
        this.mZenController = zenModeController;
        this.mStatusBarIconController = statusBarIconController;
        this.mActivityStarter = activityStarter;
        this.mPrivacyItemController = privacyItemController;
        this.mDualToneHandler = new DualToneHandler(new ContextThemeWrapper(context, C1785R$style.QSHeaderTheme));
        this.mSettingsObserver.observe();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mHeaderQsPanel = (QuickQSPanel) findViewById(C1777R$id.quick_qs_panel);
        this.mSystemIconsView = findViewById(C1777R$id.quick_status_bar_system_icons);
        this.mQuickQsStatusIcons = findViewById(C1777R$id.quick_qs_status_icons);
        this.mBatteryInQS = getResources().getBoolean(C1773R$bool.config_batteryInQSPanel);
        StatusIconContainer statusIconContainer = (StatusIconContainer) findViewById(C1777R$id.statusIcons);
        statusIconContainer.addIgnoredSlots(getIgnoredIconSlots());
        int i = 0;
        statusIconContainer.setShouldRestrictIcons(false);
        this.mIconManager = new StatusBarIconController.TintedIconManager(statusIconContainer);
        this.mHeaderTextContainerView = findViewById(C1777R$id.header_text_container);
        this.mStatusSeparator = findViewById(C1777R$id.status_separator);
        this.mNextAlarmIcon = (ImageView) findViewById(C1777R$id.next_alarm_icon);
        this.mNextAlarmTextView = (TextView) findViewById(C1777R$id.next_alarm_text);
        this.mNextAlarmContainer = findViewById(C1777R$id.alarm_container);
        this.mNextAlarmContainer.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                QuickStatusBarHeader.this.onClick(view);
            }
        });
        this.mRingerModeIcon = (ImageView) findViewById(C1777R$id.ringer_mode_icon);
        this.mRingerModeTextView = (TextView) findViewById(C1777R$id.ringer_mode_text);
        this.mRingerContainer = findViewById(C1777R$id.ringer_container);
        this.mRingerContainer.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                QuickStatusBarHeader.this.onClick(view);
            }
        });
        this.mPrivacyChip = (OngoingPrivacyChip) findViewById(C1777R$id.privacy_chip);
        this.mPrivacyChip.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                QuickStatusBarHeader.this.onClick(view);
            }
        });
        this.mCarrierGroup = (QSCarrierGroup) findViewById(C1777R$id.carrier_group);
        this.mForceHideQsStatusBar = this.mContext.getResources().getBoolean(C1773R$bool.qs_status_bar_hidden);
        updateResources();
        Rect rect = new Rect(0, 0, 0, 0);
        int singleColor = this.mDualToneHandler.getSingleColor(getColorIntensity(Utils.getColorAttrDefaultColor(getContext(), 16842800)));
        applyDarkness(C1777R$id.clock, rect, 0.0f, -1);
        this.mIconManager.setTint(singleColor);
        this.mNextAlarmIcon.setImageTintList(ColorStateList.valueOf(singleColor));
        this.mRingerModeIcon.setImageTintList(ColorStateList.valueOf(singleColor));
        this.mBatteryMeterView = (BatteryMeterView) findViewById(C1777R$id.battery);
        this.mBatteryMeterView.setForceShowPercent(true);
        this.mBatteryMeterView.setOnClickListener(this);
        this.mBatteryMeterView.setPercentShowMode(getBatteryPercentMode());
        this.mClockView = (Clock) findViewById(C1777R$id.clock);
        this.mClockView.setOnClickListener(this);
        this.mClockView.setQsHeader();
        this.mDateView = (DateView) findViewById(C1777R$id.date);
        this.mDateView.setOnClickListener(this);
        this.mSpace = (Space) findViewById(C1777R$id.space);
        this.mBatteryRemainingIcon = (BatteryMeterView) findViewById(C1777R$id.batteryRemainingIcon);
        this.mBatteryRemainingIcon.setIsQsHeader(true);
        this.mBatteryRemainingIcon.setPercentShowMode(getBatteryPercentMode());
        this.mBatteryRemainingIcon.setOnClickListener(this);
        this.mRingerModeTextView.setSelected(true);
        this.mNextAlarmTextView.setSelected(true);
        this.mBatteryMeterView.setVisibility(this.mBatteryInQS ? 8 : 0);
        BatteryMeterView batteryMeterView = this.mBatteryRemainingIcon;
        if (!this.mBatteryInQS) {
            i = 8;
        }
        batteryMeterView.setVisibility(i);
        updateSettings();
    }

    private List<String> getIgnoredIconSlots() {
        ArrayList arrayList = new ArrayList();
        if (this.mBatteryInQS) {
            arrayList.add(this.mContext.getResources().getString(17041147));
            arrayList.add(this.mContext.getResources().getString(17041160));
            if (this.mPermissionsHubEnabled) {
                arrayList.add(this.mContext.getResources().getString(17041158));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void updateStatusText() {
        boolean z = true;
        int i = 0;
        if (updateRingerStatus() || updateAlarmStatus()) {
            boolean z2 = this.mNextAlarmTextView.getVisibility() == 0;
            if (this.mRingerModeTextView.getVisibility() != 0) {
                z = false;
            }
            View view = this.mStatusSeparator;
            if (!z2 || !z) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    /* access modifiers changed from: private */
    public void setChipVisibility(boolean z) {
        if (!z || !this.mPermissionsHubEnabled || !this.mBatteryInQS) {
            this.mPrivacyChip.setVisibility(8);
            return;
        }
        this.mPrivacyChip.setVisibility(0);
        if (!this.mPrivacyChipLogged && this.mListening) {
            this.mPrivacyChipLogged = true;
            StatsLog.write(180, 1);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateRingerStatus() {
        /*
            r8 = this;
            android.widget.TextView r0 = r8.mRingerModeTextView
            int r0 = r0.getVisibility()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x000c
            r0 = r1
            goto L_0x000d
        L_0x000c:
            r0 = r2
        L_0x000d:
            android.widget.TextView r3 = r8.mRingerModeTextView
            java.lang.CharSequence r3 = r3.getText()
            com.android.systemui.statusbar.policy.ZenModeController r4 = r8.mZenController
            int r4 = r4.getZen()
            com.android.systemui.statusbar.policy.ZenModeController r5 = r8.mZenController
            android.app.NotificationManager$Policy r5 = r5.getConsolidatedPolicy()
            boolean r4 = android.service.notification.ZenModeConfig.isZenOverridingRinger(r4, r5)
            if (r4 != 0) goto L_0x004a
            int r4 = r8.mRingerMode
            if (r4 != r1) goto L_0x0039
            android.widget.ImageView r4 = r8.mRingerModeIcon
            int r5 = com.android.systemui.C1776R$drawable.ic_volume_ringer_vibrate
            r4.setImageResource(r5)
            android.widget.TextView r4 = r8.mRingerModeTextView
            int r5 = com.android.systemui.C1784R$string.qs_status_phone_vibrate
            r4.setText(r5)
        L_0x0037:
            r4 = r1
            goto L_0x004b
        L_0x0039:
            if (r4 != 0) goto L_0x004a
            android.widget.ImageView r4 = r8.mRingerModeIcon
            int r5 = com.android.systemui.C1776R$drawable.ic_volume_ringer_mute
            r4.setImageResource(r5)
            android.widget.TextView r4 = r8.mRingerModeTextView
            int r5 = com.android.systemui.C1784R$string.qs_status_phone_muted
            r4.setText(r5)
            goto L_0x0037
        L_0x004a:
            r4 = r2
        L_0x004b:
            android.widget.ImageView r5 = r8.mRingerModeIcon
            r6 = 8
            if (r4 == 0) goto L_0x0053
            r7 = r2
            goto L_0x0054
        L_0x0053:
            r7 = r6
        L_0x0054:
            r5.setVisibility(r7)
            android.widget.TextView r5 = r8.mRingerModeTextView
            if (r4 == 0) goto L_0x005d
            r7 = r2
            goto L_0x005e
        L_0x005d:
            r7 = r6
        L_0x005e:
            r5.setVisibility(r7)
            android.view.View r5 = r8.mRingerContainer
            if (r4 == 0) goto L_0x0066
            r6 = r2
        L_0x0066:
            r5.setVisibility(r6)
            if (r0 != r4) goto L_0x0079
            android.widget.TextView r8 = r8.mRingerModeTextView
            java.lang.CharSequence r8 = r8.getText()
            boolean r8 = java.util.Objects.equals(r3, r8)
            if (r8 != 0) goto L_0x0078
            goto L_0x0079
        L_0x0078:
            r1 = r2
        L_0x0079:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.p006qs.QuickStatusBarHeader.updateRingerStatus():boolean");
    }

    private boolean updateAlarmStatus() {
        boolean z;
        boolean z2 = this.mNextAlarmTextView.getVisibility() == 0;
        CharSequence text = this.mNextAlarmTextView.getText();
        AlarmManager.AlarmClockInfo alarmClockInfo = this.mNextAlarm;
        if (alarmClockInfo != null) {
            this.mNextAlarmTextView.setText(formatNextAlarm(alarmClockInfo));
            z = true;
        } else {
            z = false;
        }
        int i = 8;
        this.mNextAlarmIcon.setVisibility(z ? 0 : 8);
        this.mNextAlarmTextView.setVisibility(z ? 0 : 8);
        View view = this.mNextAlarmContainer;
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        if (z2 != z || !Objects.equals(text, this.mNextAlarmTextView.getText())) {
            return true;
        }
        return false;
    }

    private void applyDarkness(int i, Rect rect, float f, int i2) {
        View findViewById = findViewById(i);
        if (findViewById instanceof DarkIconDispatcher.DarkReceiver) {
            ((DarkIconDispatcher.DarkReceiver) findViewById).onDarkChanged(rect, f, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mLandscape = configuration.orientation == 2;
        updateResources();
        updateStatusbarProperties();
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        updateResources();
    }

    private void updateMinimumHeight() {
        setMinimumHeight(this.mContext.getResources().getDimensionPixelSize(17105434) + this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_quick_header_panel_height));
    }

    private void updateResources() {
        Resources resources = this.mContext.getResources();
        updateMinimumHeight();
        this.mHeaderTextContainerView.getLayoutParams().height = resources.getDimensionPixelSize(C1775R$dimen.qs_header_tooltip_height);
        View view = this.mHeaderTextContainerView;
        view.setLayoutParams(view.getLayoutParams());
        int dimensionPixelSize = resources.getDimensionPixelSize(17105395) + (this.mHeaderImageEnabled ? resources.getDimensionPixelSize(C1775R$dimen.qs_header_image_offset) : 0);
        this.mSystemIconsView.getLayoutParams().height = dimensionPixelSize;
        View view2 = this.mSystemIconsView;
        view2.setLayoutParams(view2.getLayoutParams());
        ((StatusIconContainer) findViewById(C1777R$id.statusIcons)).addIgnoredSlots(getIgnoredIconSlots());
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (this.mQsDisabled) {
            layoutParams.height = dimensionPixelSize;
        } else {
            int dimensionPixelSize2 = resources.getDimensionPixelSize(17105396);
            if (this.mHeaderImageEnabled) {
                dimensionPixelSize2 += resources.getDimensionPixelSize(C1775R$dimen.qs_header_image_offset);
            }
            layoutParams.height = Math.max(getMinimumHeight(), dimensionPixelSize2);
        }
        setLayoutParams(layoutParams);
        updateStatusIconAlphaAnimator();
        updateHeaderTextContainerAlphaAnimator();
        updatePrivacyChipAlphaAnimator();
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        boolean z = false;
        this.mHeaderImageEnabled = Settings.System.getIntForUser(getContext().getContentResolver(), "status_bar_custom_header", 0, -2) == 1;
        if (Settings.System.getIntForUser(getContext().getContentResolver(), "permissions_hub_enabled", 1, -2) == 1) {
            z = true;
        }
        this.mPermissionsHubEnabled = z;
        updateResources();
        updateStatusbarProperties();
    }

    private void updateStatusIconAlphaAnimator() {
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mQuickQsStatusIcons, "alpha", 1.0f, 0.0f, 0.0f);
        this.mStatusIconsAlphaAnimator = builder.build();
    }

    private void updateHeaderTextContainerAlphaAnimator() {
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mHeaderTextContainerView, "alpha", 0.0f, 0.0f, 1.0f);
        this.mHeaderTextContainerAlphaAnimator = builder.build();
    }

    private void updatePrivacyChipAlphaAnimator() {
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mPrivacyChip, "alpha", 1.0f, 0.0f, 1.0f);
        this.mPrivacyChipAlphaAnimator = builder.build();
    }

    private int getBatteryPercentMode() {
        return Settings.System.getIntForUser(getContext().getContentResolver(), "qs_show_battery_estimate", 1, -2) == 1 ? 3 : 1;
    }

    public void setBatteryPercentMode() {
        this.mBatteryMeterView.setPercentShowMode(getBatteryPercentMode());
        this.mBatteryRemainingIcon.setPercentShowMode(getBatteryPercentMode());
    }

    public void setExpanded(boolean z) {
        if (this.mExpanded != z) {
            this.mExpanded = z;
            this.mHeaderQsPanel.setExpanded(z);
            updateEverything();
        }
    }

    public void setExpansion(boolean z, float f, float f2) {
        float f3 = z ? 1.0f : f;
        TouchAnimator touchAnimator = this.mStatusIconsAlphaAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f3);
        }
        if (z) {
            this.mHeaderTextContainerView.setTranslationY(f2);
        } else {
            this.mHeaderTextContainerView.setTranslationY(0.0f);
        }
        TouchAnimator touchAnimator2 = this.mHeaderTextContainerAlphaAnimator;
        boolean z2 = false;
        if (touchAnimator2 != null) {
            touchAnimator2.setPosition(f3);
            if (f3 > 0.0f) {
                this.mHeaderTextContainerView.setVisibility(0);
            } else {
                this.mHeaderTextContainerView.setVisibility(4);
            }
        }
        if (this.mPrivacyChipAlphaAnimator != null) {
            OngoingPrivacyChip ongoingPrivacyChip = this.mPrivacyChip;
            if (((double) f) > 0.5d) {
                z2 = true;
            }
            ongoingPrivacyChip.setExpanded(z2);
            this.mPrivacyChipAlphaAnimator.setPosition(f3);
        }
    }

    public void disable(int i, int i2, boolean z) {
        boolean z2 = true;
        int i3 = 0;
        if ((i2 & 1) == 0) {
            z2 = false;
        }
        if (z2 != this.mQsDisabled) {
            this.mQsDisabled = z2;
            this.mHeaderQsPanel.setDisabledByPolicy(z2);
            this.mHeaderTextContainerView.setVisibility(this.mQsDisabled ? 8 : 0);
            View view = this.mQuickQsStatusIcons;
            if (this.mQsDisabled) {
                i3 = 8;
            }
            view.setVisibility(i3);
            updateResources();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mStatusBarIconController.addIconGroup(this.mIconManager);
        requestApplyInsets();
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        Pair<Integer, Integer> cornerCutoutMargins = PhoneStatusBarView.cornerCutoutMargins(displayCutout, getDisplay());
        boolean z = false;
        if (cornerCutoutMargins == null) {
            this.mSystemIconsView.setPaddingRelative(getResources().getDimensionPixelSize(C1775R$dimen.status_bar_icon_padding), getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_top), getResources().getDimensionPixelSize(C1775R$dimen.status_bar_icon_padding), 0);
        } else {
            this.mSystemIconsView.setPadding(((Integer) cornerCutoutMargins.first).intValue(), getResources().getDimensionPixelSize(C1775R$dimen.status_bar_padding_top), ((Integer) cornerCutoutMargins.second).intValue(), 0);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mSpace.getLayoutParams();
        if (displayCutout != null) {
            Rect boundingRectTop = displayCutout.getBoundingRectTop();
            if (boundingRectTop.isEmpty()) {
                this.mHasTopCutout = false;
                layoutParams.width = 0;
                this.mSpace.setVisibility(8);
            } else {
                this.mHasTopCutout = true;
                layoutParams.width = boundingRectTop.width();
                this.mSpace.setVisibility(0);
            }
        }
        this.mSpace.setLayoutParams(layoutParams);
        if (this.mPrivacyChip.getVisibility() == 0) {
            z = true;
        }
        setChipVisibility(z);
        return super.onApplyWindowInsets(windowInsets);
    }

    public void onDetachedFromWindow() {
        setListening(false);
        this.mStatusBarIconController.removeIconGroup(this.mIconManager);
        super.onDetachedFromWindow();
    }

    public void setListening(boolean z) {
        if (z != this.mListening) {
            this.mHeaderQsPanel.setListening(z);
            this.mListening = z;
            this.mCarrierGroup.setListening(this.mListening);
            if (z) {
                this.mZenController.addCallback(this);
                this.mAlarmController.addCallback(this);
                this.mContext.registerReceiver(this.mRingerReceiver, new IntentFilter("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"));
                this.mPrivacyItemController.addCallback(this.mPICCallback);
                return;
            }
            this.mZenController.removeCallback(this);
            this.mAlarmController.removeCallback(this);
            this.mPrivacyItemController.removeCallback(this.mPICCallback);
            this.mContext.unregisterReceiver(this.mRingerReceiver);
            this.mPrivacyChipLogged = false;
        }
    }

    public void onClick(View view) {
        if (view == this.mClockView || view == this.mNextAlarmTextView) {
            this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.intent.action.SHOW_ALARMS"), 0);
            return;
        }
        View view2 = this.mNextAlarmContainer;
        if (view != view2 || !view2.isVisibleToUser()) {
            OngoingPrivacyChip ongoingPrivacyChip = this.mPrivacyChip;
            if (view != ongoingPrivacyChip) {
                View view3 = this.mRingerContainer;
                if (view == view3 && view3.isVisibleToUser()) {
                    this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.SOUND_SETTINGS"), 0);
                } else if (view == this.mDateView) {
                    Uri.Builder buildUpon = CalendarContract.CONTENT_URI.buildUpon();
                    buildUpon.appendPath("time");
                    buildUpon.appendPath(Long.toString(System.currentTimeMillis()));
                    this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.intent.action.VIEW", buildUpon.build()), 0);
                } else if (view == this.mBatteryRemainingIcon || view == this.mBatteryMeterView) {
                    this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.intent.action.POWER_USAGE_SUMMARY"), 0);
                }
            } else if (ongoingPrivacyChip.getBuilder().getAppsAndTypes().size() != 0) {
                Handler handler = new Handler(Looper.getMainLooper());
                StatsLog.write(180, 2);
                handler.post(new Runnable() {
                    public final void run() {
                        QuickStatusBarHeader.this.lambda$onClick$0$QuickStatusBarHeader();
                    }
                });
            }
        } else if (this.mNextAlarm.getShowIntent() != null) {
            this.mActivityStarter.postStartActivityDismissingKeyguard(this.mNextAlarm.getShowIntent());
        } else {
            Log.d("QuickStatusBarHeader", "No PendingIntent for next alarm. Using default intent");
            this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.intent.action.SHOW_ALARMS"), 0);
        }
    }

    public /* synthetic */ void lambda$onClick$0$QuickStatusBarHeader() {
        this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.intent.action.REVIEW_ONGOING_PERMISSION_USAGE"), 0);
        this.mHost.collapsePanels();
    }

    public void onNextAlarmChanged(AlarmManager.AlarmClockInfo alarmClockInfo) {
        this.mNextAlarm = alarmClockInfo;
        updateStatusText();
    }

    public void onZenChanged(int i) {
        updateStatusText();
    }

    public void onConfigChanged(ZenModeConfig zenModeConfig) {
        updateStatusText();
    }

    public /* synthetic */ void lambda$updateEverything$1$QuickStatusBarHeader() {
        setClickable(!this.mExpanded);
    }

    public void updateEverything() {
        post(new Runnable() {
            public final void run() {
                QuickStatusBarHeader.this.lambda$updateEverything$1$QuickStatusBarHeader();
            }
        });
    }

    public void setQSPanel(QSPanel qSPanel) {
        this.mQsPanel = qSPanel;
        setupHost(qSPanel.getHost());
    }

    public void setupHost(QSTileHost qSTileHost) {
        this.mHost = qSTileHost;
        this.mHeaderQsPanel.setQSPanelAndHeader(this.mQsPanel, this);
        this.mHeaderQsPanel.setHost(qSTileHost, (QSCustomizer) null);
        Rect rect = new Rect(0, 0, 0, 0);
        float colorIntensity = getColorIntensity(Utils.getColorAttrDefaultColor(getContext(), 16842800));
        this.mBatteryRemainingIcon.onDarkChanged(rect, colorIntensity, this.mDualToneHandler.getSingleColor(colorIntensity));
        this.mBatteryMeterView.setColorsFromContext(this.mHost.getContext());
        this.mBatteryMeterView.onDarkChanged(new Rect(), 0.0f, -1);
    }

    public void setCallback(QSDetail.Callback callback) {
        this.mHeaderQsPanel.setCallback(callback);
    }

    private String formatNextAlarm(AlarmManager.AlarmClockInfo alarmClockInfo) {
        if (alarmClockInfo == null) {
            return "";
        }
        return DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), DateFormat.is24HourFormat(this.mContext, ActivityManager.getCurrentUser()) ? "EHm" : "Ehma"), alarmClockInfo.getTriggerTime()).toString();
    }

    public void setMargins(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == this.mSystemIconsView || childAt == this.mQuickQsStatusIcons || childAt == this.mHeaderQsPanel || childAt == this.mHeaderTextContainerView)) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) childAt.getLayoutParams();
                layoutParams.leftMargin = i;
                layoutParams.rightMargin = i;
            }
        }
    }

    /* renamed from: com.android.systemui.qs.QuickStatusBarHeader$SettingsObserver */
    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = QuickStatusBarHeader.this.getContext().getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_custom_header"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("permissions_hub_enabled"), false, this, -1);
        }

        public void onChange(boolean z) {
            QuickStatusBarHeader.this.updateSettings();
        }
    }

    private void updateStatusbarProperties() {
        boolean z = (this.mLandscape || this.mForceHideQsStatusBar) && !this.mHeaderImageEnabled;
        this.mBatteryMeterView.useWallpaperTextColor(z);
        this.mClockView.useWallpaperTextColor(z);
    }
}
