package com.android.systemui.volume;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Region;
import android.media.AppTrackData;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.provider.Settings;
import android.text.InputFilter;
import android.util.Log;
import android.util.Slog;
import android.util.SparseBooleanArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.settingslib.Utils;
import com.android.settingslib.volume.Util;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.C1785R$style;
import com.android.systemui.Dependency;
import com.android.systemui.Prefs;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.phone.ExpandableIndicator;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.volume.CaptionsToggleImageButton;
import com.android.systemui.volume.VolumeDialogImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VolumeDialogImpl implements VolumeDialog, ConfigurationController.ConfigurationListener {
    /* access modifiers changed from: private */
    public static final String TAG = Util.logTag(VolumeDialogImpl.class);
    private final Accessibility mAccessibility = new Accessibility();
    private final AccessibilityManagerWrapper mAccessibilityMgr;
    private int mActiveStream;
    private final ActivityManager mActivityManager;
    private int mAllyStream;
    private final ColorFilter mAppIconMuteColorFilter;
    private final List<VolumeRow> mAppRows = new ArrayList();
    private boolean mAutomute = true;
    /* access modifiers changed from: private */
    public boolean mConfigChanged = false;
    private ConfigurableTexts mConfigurableTexts;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final VolumeDialogController mController;
    private final VolumeDialogController.Callbacks mControllerCallbackH = new VolumeDialogController.Callbacks() {
        public void onShowRequested(int i) {
            VolumeDialogImpl.this.showH(i);
        }

        public void onDismissRequested(int i) {
            VolumeDialogImpl.this.dismissH(i);
        }

        public void onScreenOff() {
            VolumeDialogImpl.this.dismissH(4);
        }

        public void onStateChanged(VolumeDialogController.State state) {
            VolumeDialogImpl.this.onStateChangedH(state);
        }

        public void onLayoutDirectionChanged(int i) {
            VolumeDialogImpl.this.mDialogView.setLayoutDirection(i);
        }

        public void onConfigurationChanged() {
            if (VolumeDialogImpl.this.mDialog.isShown()) {
                VolumeDialogImpl.this.mWindowManager.removeViewImmediate(VolumeDialogImpl.this.mDialog);
            }
            boolean unused = VolumeDialogImpl.this.mConfigChanged = true;
        }

        public void onShowVibrateHint() {
            if (VolumeDialogImpl.this.mSilentMode) {
                VolumeDialogImpl.this.mController.setRingerMode(0, false);
            }
        }

        public void onShowSilentHint() {
            if (VolumeDialogImpl.this.mSilentMode) {
                VolumeDialogImpl.this.mController.setRingerMode(2, false);
            }
        }

        public void onShowSafetyWarning(int i) {
            VolumeDialogImpl.this.showSafetyWarningH(i);
        }

        public void onAccessibilityModeChanged(Boolean bool) {
            boolean unused = VolumeDialogImpl.this.mShowA11yStream = bool == null ? false : bool.booleanValue();
            VolumeRow access$3900 = VolumeDialogImpl.this.getActiveRow();
            if (VolumeDialogImpl.this.mShowA11yStream || 10 != access$3900.stream) {
                VolumeDialogImpl.this.updateRowsH(access$3900);
            } else {
                VolumeDialogImpl.this.dismissH(7);
            }
        }

        public void onCaptionComponentStateChanged(Boolean bool, Boolean bool2) {
            VolumeDialogImpl.this.updateODICaptionsH(bool.booleanValue(), bool2.booleanValue());
        }
    };
    private final DeviceProvisionedController mDeviceProvisionedController;
    /* access modifiers changed from: private */
    public View mDialog;
    private ViewGroup mDialogMainView;
    /* access modifiers changed from: private */
    public ViewGroup mDialogRowsView;
    /* access modifiers changed from: private */
    public ViewGroup mDialogView;
    private final SparseBooleanArray mDynamic = new SparseBooleanArray();
    private ExpandableIndicator mExpandRows;
    private View mExpandRowsView;
    private boolean mExpanded;
    /* access modifiers changed from: private */
    public final C1651H mHandler = new C1651H();
    private boolean mHasAlertSlider;
    private boolean mHasSeenODICaptionsTooltip;
    private boolean mHovering = false;
    private final ViewTreeObserver.OnComputeInternalInsetsListener mInsetsListener = new ViewTreeObserver.OnComputeInternalInsetsListener() {
        public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
            VolumeDialogImpl.this.lambda$new$2$VolumeDialogImpl(internalInsetsInfo);
        }
    };
    private final KeyguardManager mKeyguard;
    private ImageButton mMediaOutputIcon;
    private View mMediaOutputView;
    private boolean mMusicHidden;
    private CaptionsToggleImageButton mODICaptionsIcon;
    private View mODICaptionsTooltipView = null;
    private ViewStub mODICaptionsTooltipViewStub;
    private ViewGroup mODICaptionsView;
    private int mPrevActiveStream;
    private ViewGroup mRinger;
    private ImageButton mRingerIcon;
    private final List<VolumeRow> mRows = new ArrayList();
    /* access modifiers changed from: private */
    public SafetyWarningDialog mSafetyWarning;
    /* access modifiers changed from: private */
    public final Object mSafetyWarningLock = new Object();
    /* access modifiers changed from: private */
    public boolean mShowA11yStream;
    private boolean mShowActiveStreamOnly;
    private boolean mShowing;
    /* access modifiers changed from: private */
    public boolean mSilentMode = true;
    /* access modifiers changed from: private */
    public VolumeDialogController.State mState;
    private boolean mVolumePanelOnLeft;
    /* access modifiers changed from: private */
    public WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private FrameLayout mZenIcon;

    public VolumeDialogImpl(Context context) {
        this.mContext = new ContextThemeWrapper(context, C1785R$style.qs_theme);
        this.mController = (VolumeDialogController) Dependency.get(VolumeDialogController.class);
        this.mKeyguard = (KeyguardManager) this.mContext.getSystemService("keyguard");
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        this.mAccessibilityMgr = (AccessibilityManagerWrapper) Dependency.get(AccessibilityManagerWrapper.class);
        this.mDeviceProvisionedController = (DeviceProvisionedController) Dependency.get(DeviceProvisionedController.class);
        this.mShowActiveStreamOnly = showActiveStreamOnly();
        this.mHasSeenODICaptionsTooltip = Prefs.getBoolean(context, "HasSeenODICaptionsTooltip", false);
        this.mVolumePanelOnLeft = this.mContext.getResources().getBoolean(C1773R$bool.config_audioPanelOnLeftSide);
        this.mHasAlertSlider = this.mContext.getResources().getBoolean(17891479);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        this.mAppIconMuteColorFilter = new ColorMatrixColorFilter(colorMatrix);
    }

    public void onUiModeChanged() {
        this.mContext.getTheme().applyStyle(this.mContext.getThemeResId(), true);
    }

    public void init(int i, VolumeDialog.Callback callback) {
        initDialog();
        this.mAccessibility.init();
        this.mController.addCallback(this.mControllerCallbackH, this.mHandler);
        this.mController.getState();
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
    }

    public void destroy() {
        this.mController.removeCallback(this.mControllerCallbackH);
        this.mHandler.removeCallbacksAndMessages((Object) null);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).removeCallback(this);
    }

    private void initDialog() {
        ViewStub viewStub;
        int i = this.mVolumePanelOnLeft ? 3 : 5;
        this.mConfigurableTexts = new ConfigurableTexts(this.mContext);
        this.mHovering = false;
        this.mShowing = false;
        this.mExpanded = false;
        this.mWindowParams = new WindowManager.LayoutParams();
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.flags &= -3;
        layoutParams.flags &= -65537;
        layoutParams.flags |= 17563944;
        layoutParams.type = 2020;
        layoutParams.format = -3;
        layoutParams.windowAnimations = -1;
        this.mDialog = LayoutInflater.from(this.mContext).inflate(C1779R$layout.volume_dialog, (ViewGroup) null, false);
        this.mDialog.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return VolumeDialogImpl.this.lambda$initDialog$0$VolumeDialogImpl(view, motionEvent);
            }
        });
        this.mDialogView = (ViewGroup) this.mDialog.findViewById(C1777R$id.volume_dialog);
        this.mDialogView.setAlpha(0.0f);
        this.mDialogView.setLayoutDirection(this.mVolumePanelOnLeft ^ true ? 1 : 0);
        this.mDialogView.setOnHoverListener(new View.OnHoverListener() {
            public final boolean onHover(View view, MotionEvent motionEvent) {
                return VolumeDialogImpl.this.lambda$initDialog$1$VolumeDialogImpl(view, motionEvent);
            }
        });
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mDialogView.getLayoutParams();
        layoutParams2.gravity = 16;
        this.mDialogView.setLayoutParams(layoutParams2);
        this.mDialogMainView = (ViewGroup) this.mDialog.findViewById(C1777R$id.main);
        ViewGroup viewGroup = this.mDialogMainView;
        if (viewGroup != null) {
            setLayoutGravity(viewGroup.getLayoutParams(), i);
        }
        this.mDialogRowsView = (ViewGroup) this.mDialog.findViewById(C1777R$id.volume_dialog_rows);
        this.mRinger = (ViewGroup) this.mDialog.findViewById(C1777R$id.ringer);
        ViewGroup viewGroup2 = this.mRinger;
        if (viewGroup2 != null) {
            this.mRingerIcon = (ImageButton) viewGroup2.findViewById(C1777R$id.ringer_icon);
            this.mZenIcon = (FrameLayout) this.mRinger.findViewById(C1777R$id.dnd_icon);
            setLayoutGravity(this.mRinger.getLayoutParams(), i);
        }
        this.mODICaptionsView = (ViewGroup) this.mDialog.findViewById(C1777R$id.odi_captions);
        ViewGroup viewGroup3 = this.mODICaptionsView;
        if (viewGroup3 != null) {
            this.mODICaptionsIcon = (CaptionsToggleImageButton) viewGroup3.findViewById(C1777R$id.odi_captions_icon);
            setLayoutGravity(this.mODICaptionsView.getLayoutParams(), i);
        }
        this.mODICaptionsTooltipViewStub = (ViewStub) this.mDialog.findViewById(C1777R$id.odi_captions_tooltip_stub);
        if (this.mHasSeenODICaptionsTooltip && (viewStub = this.mODICaptionsTooltipViewStub) != null) {
            this.mDialogView.removeView(viewStub);
            this.mODICaptionsTooltipViewStub = null;
        }
        if (this.mHasAlertSlider) {
            this.mRinger.setVisibility(8);
        }
        this.mMediaOutputView = this.mDialog.findViewById(C1777R$id.media_output_container);
        this.mMediaOutputIcon = (ImageButton) this.mDialog.findViewById(C1777R$id.media_output);
        ImageButton imageButton = this.mMediaOutputIcon;
        if (imageButton != null) {
            setLayoutGravity(imageButton.getLayoutParams(), i);
        }
        this.mExpandRowsView = this.mDialog.findViewById(C1777R$id.expandable_indicator_container);
        this.mExpandRows = (ExpandableIndicator) this.mDialog.findViewById(C1777R$id.expandable_indicator);
        ExpandableIndicator expandableIndicator = this.mExpandRows;
        if (expandableIndicator != null) {
            setLayoutGravity(expandableIndicator.getLayoutParams(), i);
            this.mExpandRows.setRotation(this.mVolumePanelOnLeft ? -90.0f : 90.0f);
        }
        if (this.mRows.isEmpty()) {
            if (!AudioSystem.isSingleVolume(this.mContext)) {
                int i2 = C1776R$drawable.ic_volume_accessibility;
                addRow(10, i2, i2, true, false);
            }
            addRow(3, C1776R$drawable.ic_volume_media, C1776R$drawable.ic_volume_media_mute, true, true);
            if (!AudioSystem.isSingleVolume(this.mContext)) {
                if (Util.isVoiceCapable(this.mContext)) {
                    addRow(2, C1776R$drawable.ic_volume_ringer, C1776R$drawable.ic_volume_ringer_mute, true, false);
                } else {
                    addRow(2, C1776R$drawable.ic_volume_notification, C1776R$drawable.ic_volume_notification_mute, true, false);
                }
                addRow(4, C1776R$drawable.ic_volume_alarm, C1776R$drawable.ic_volume_alarm_mute, true, false);
                addRow(0, 17302778, 17302778, false, false);
                int i3 = C1776R$drawable.ic_volume_bt_sco;
                addRow(6, i3, i3, false, false);
                addRow(1, C1776R$drawable.ic_volume_system, C1776R$drawable.ic_volume_system_mute, false, false);
            }
        } else {
            addExistingRows();
        }
        updateRowsH(getActiveRow());
        initRingerH();
        initSettingsH();
        initODICaptionsH();
        this.mAllyStream = -1;
        this.mMusicHidden = false;
    }

    public /* synthetic */ boolean lambda$initDialog$0$VolumeDialogImpl(View view, MotionEvent motionEvent) {
        if (!this.mShowing) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0 && action != 4) {
            return false;
        }
        dismissH(1);
        return true;
    }

    public /* synthetic */ boolean lambda$initDialog$1$VolumeDialogImpl(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        this.mHovering = actionMasked == 9 || actionMasked == 7;
        rescheduleTimeoutH();
        return true;
    }

    public /* synthetic */ void lambda$new$2$VolumeDialogImpl(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.touchableRegion.setEmpty();
        internalInsetsInfo.setTouchableInsets(3);
        View findViewById = this.mDialog.findViewById(C1777R$id.main);
        int[] iArr = new int[2];
        findViewById.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.mDialogView.getLocationOnScreen(iArr2);
        internalInsetsInfo.touchableRegion.set(new Region(iArr[0], iArr2[1], iArr[0] + findViewById.getWidth(), iArr2[1] + this.mDialogView.getHeight()));
    }

    private void setLayoutGravity(Object obj, int i) {
        if (obj instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) obj).gravity = i;
        } else if (obj instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) obj).gravity = i;
        }
    }

    private float getAnimatorX() {
        float width = ((float) this.mDialogView.getWidth()) / 2.0f;
        return this.mVolumePanelOnLeft ? -width : width;
    }

    private int getAlphaAttr(int i) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{i});
        float f = obtainStyledAttributes.getFloat(0, 0.0f);
        obtainStyledAttributes.recycle();
        return (int) (f * 255.0f);
    }

    private boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public void setStreamImportant(int i, boolean z) {
        this.mHandler.obtainMessage(5, i, z ? 1 : 0).sendToTarget();
    }

    public void setAutomute(boolean z) {
        if (this.mAutomute != z) {
            this.mAutomute = z;
            this.mHandler.sendEmptyMessage(4);
        }
    }

    public void setSilentMode(boolean z) {
        if (this.mSilentMode != z) {
            this.mSilentMode = z;
            this.mHandler.sendEmptyMessage(4);
        }
    }

    private void addRow(int i, int i2, int i3, boolean z, boolean z2) {
        addRow(i, i2, i3, z, z2, false);
    }

    private void addRow(int i, int i2, int i3, boolean z, boolean z2, boolean z3) {
        if (C1625D.BUG) {
            String str = TAG;
            Slog.d(str, "Adding row for stream " + i);
        }
        VolumeRow volumeRow = new VolumeRow();
        initRow(volumeRow, i, i2, i3, z, z2);
        this.mDialogRowsView.addView(volumeRow.view);
        this.mRows.add(volumeRow);
    }

    private void addAppRow(AppTrackData appTrackData) {
        VolumeRow volumeRow = new VolumeRow();
        initAppRow(volumeRow, appTrackData);
        this.mDialogRowsView.addView(volumeRow.view);
        this.mAppRows.add(volumeRow);
    }

    @SuppressLint({"InflateParams"})
    private void initAppRow(VolumeRow volumeRow, AppTrackData appTrackData) {
        ColorFilter colorFilter = null;
        View unused = volumeRow.view = LayoutInflater.from(this.mContext).inflate(C1779R$layout.volume_dialog_row, (ViewGroup) null);
        String unused2 = volumeRow.packageName = appTrackData.getPackageName();
        boolean unused3 = volumeRow.isAppVolumeRow = true;
        volumeRow.view.setTag(volumeRow);
        SeekBar unused4 = volumeRow.slider = (SeekBar) volumeRow.view.findViewById(C1777R$id.volume_row_slider);
        volumeRow.slider.setOnSeekBarChangeListener(new VolumeSeekBarChangeListener(volumeRow));
        boolean unused5 = volumeRow.appMuted = appTrackData.isMuted();
        volumeRow.slider.setProgress((int) (appTrackData.getVolume() * 100.0f));
        FrameLayout unused6 = volumeRow.dndIcon = (FrameLayout) volumeRow.view.findViewById(C1777R$id.dnd_icon);
        volumeRow.dndIcon.setVisibility(8);
        ImageButton unused7 = volumeRow.icon = (ImageButton) volumeRow.view.findViewById(C1777R$id.volume_row_app_icon);
        volumeRow.icon.setVisibility(0);
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            volumeRow.icon.setImageDrawable(packageManager.getApplicationIcon(volumeRow.packageName));
        } catch (PackageManager.NameNotFoundException e) {
            volumeRow.icon.setImageDrawable(packageManager.getDefaultActivityIcon());
            String str = TAG;
            Log.e(str, "Failed to get icon of " + volumeRow.packageName, e);
        }
        ImageButton access$900 = volumeRow.icon;
        if (volumeRow.appMuted) {
            colorFilter = this.mAppIconMuteColorFilter;
        }
        access$900.setColorFilter(colorFilter);
        volumeRow.icon.setOnClickListener(new View.OnClickListener(volumeRow) {
            private final /* synthetic */ VolumeDialogImpl.VolumeRow f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                VolumeDialogImpl.this.lambda$initAppRow$3$VolumeDialogImpl(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initAppRow$3$VolumeDialogImpl(VolumeRow volumeRow, View view) {
        rescheduleTimeoutH();
        AudioManager audioManager = this.mController.getAudioManager();
        boolean unused = volumeRow.appMuted = !volumeRow.appMuted;
        audioManager.setAppMute(volumeRow.packageName, volumeRow.appMuted);
        volumeRow.icon.setColorFilter(volumeRow.appMuted ? this.mAppIconMuteColorFilter : null);
    }

    private void addExistingRows() {
        int size = this.mRows.size();
        for (int i = 0; i < size; i++) {
            VolumeRow volumeRow = this.mRows.get(i);
            initRow(volumeRow, volumeRow.stream, volumeRow.iconRes, volumeRow.iconMuteRes, volumeRow.important, volumeRow.defaultStream);
            this.mDialogRowsView.addView(volumeRow.view);
            updateVolumeRowH(volumeRow);
        }
    }

    /* access modifiers changed from: private */
    public VolumeRow getActiveRow() {
        for (VolumeRow next : this.mRows) {
            if (next.stream == this.mActiveStream) {
                return next;
            }
        }
        for (VolumeRow next2 : this.mRows) {
            if (next2.stream == 3) {
                return next2;
            }
        }
        return this.mRows.get(0);
    }

    private VolumeRow findRow(int i) {
        for (VolumeRow next : this.mRows) {
            if (next.stream == i) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static int getImpliedLevel(SeekBar seekBar, int i) {
        int max = seekBar.getMax();
        int i2 = max / 100;
        int i3 = i2 - 1;
        if (i == 0) {
            return 0;
        }
        return i == max ? i2 : ((int) ((((float) i) / ((float) max)) * ((float) i3))) + 1;
    }

    @SuppressLint({"InflateParams"})
    private void initRow(VolumeRow volumeRow, int i, int i2, int i3, boolean z, boolean z2) {
        int unused = volumeRow.stream = i;
        int unused2 = volumeRow.iconRes = i2;
        int unused3 = volumeRow.iconMuteRes = i3;
        boolean unused4 = volumeRow.important = z;
        boolean unused5 = volumeRow.defaultStream = z2;
        View unused6 = volumeRow.view = LayoutInflater.from(this.mContext).inflate(C1779R$layout.volume_dialog_row, (ViewGroup) null);
        volumeRow.view.setId(volumeRow.stream);
        volumeRow.view.setTag(volumeRow);
        TextView unused7 = volumeRow.header = (TextView) volumeRow.view.findViewById(C1777R$id.volume_row_header);
        volumeRow.header.setId(volumeRow.stream * 20);
        if (i == 10) {
            volumeRow.header.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});
        }
        FrameLayout unused8 = volumeRow.dndIcon = (FrameLayout) volumeRow.view.findViewById(C1777R$id.dnd_icon);
        SeekBar unused9 = volumeRow.slider = (SeekBar) volumeRow.view.findViewById(C1777R$id.volume_row_slider);
        volumeRow.slider.setOnSeekBarChangeListener(new VolumeSeekBarChangeListener(volumeRow));
        ObjectAnimator unused10 = volumeRow.anim = null;
        ImageButton unused11 = volumeRow.icon = (ImageButton) volumeRow.view.findViewById(C1777R$id.volume_row_icon);
        volumeRow.icon.setImageResource(i2);
        volumeRow.icon.setVisibility(0);
        if (volumeRow.stream != 10) {
            volumeRow.icon.setOnClickListener(new View.OnClickListener(volumeRow, i) {
                private final /* synthetic */ VolumeDialogImpl.VolumeRow f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    VolumeDialogImpl.this.lambda$initRow$4$VolumeDialogImpl(this.f$1, this.f$2, view);
                }
            });
        } else {
            volumeRow.icon.setImportantForAccessibility(2);
        }
    }

    public /* synthetic */ void lambda$initRow$4$VolumeDialogImpl(VolumeRow volumeRow, int i, View view) {
        rescheduleTimeoutH();
        boolean z = false;
        Events.writeEvent(this.mContext, 7, Integer.valueOf(volumeRow.stream), Integer.valueOf(volumeRow.iconState));
        this.mController.setActiveStream(volumeRow.stream);
        if (volumeRow.f78ss.level == volumeRow.f78ss.levelMin) {
            z = true;
        }
        this.mController.setStreamVolume(i, z ? volumeRow.lastAudibleLevel : volumeRow.f78ss.levelMin);
        long unused = volumeRow.userAttempt = 0;
    }

    private boolean isNotificationVolumeLinked() {
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_link_notification", 1) == 1) {
            return true;
        }
        return false;
    }

    private static boolean isBluetoothA2dpConnected() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled() && defaultAdapter.getProfileConnectionState(2) == 2;
    }

    private void setVisOrGone(int i, boolean z) {
        if (z || i != this.mAllyStream) {
            Util.setVisOrGone(findRow(i).view, z);
        }
    }

    /* access modifiers changed from: private */
    public void updateExpandedRows(boolean z) {
        if (!z) {
            this.mController.setActiveStream(this.mAllyStream);
        }
        if (this.mMusicHidden) {
            setVisOrGone(3, z);
        }
        setVisOrGone(2, z);
        setVisOrGone(4, z);
        if (!isNotificationVolumeLinked()) {
            setVisOrGone(5, z);
        }
        if (z) {
            updateAppRows();
        }
    }

    private void updateAppRows() {
        for (int size = this.mAppRows.size() - 1; size >= 0; size--) {
            removeAppRow(this.mAppRows.get(size));
        }
        for (AppTrackData appTrackData : this.mController.getAudioManager().listAppTrackDatas()) {
            if (appTrackData.isActive()) {
                addAppRow(appTrackData);
            }
        }
    }

    private void animateExpandedRowsChange(final boolean z) {
        int i;
        TimeInterpolator timeInterpolator;
        int i2 = this.mDialogRowsView.getLayoutParams().width;
        if (z) {
            updateExpandedRows(z);
            this.mDialogRowsView.measure(-2, -2);
            i = this.mDialogRowsView.getMeasuredWidth();
        } else {
            i = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.volume_dialog_panel_width);
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                VolumeDialogImpl.this.mDialogRowsView.getLayoutParams().width = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                VolumeDialogImpl.this.mDialogRowsView.requestLayout();
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                boolean z = z;
                if (!z) {
                    VolumeDialogImpl.this.updateExpandedRows(z);
                }
            }
        });
        if (z) {
            timeInterpolator = new SystemUIInterpolators$LogDecelerateInterpolator();
        } else {
            timeInterpolator = new SystemUIInterpolators$LogAccelerateInterpolator();
        }
        ofInt.setInterpolator(timeInterpolator);
        ofInt.setDuration(80);
        ofInt.start();
    }

    public void updateMediaOutputH() {
        View view = this.mMediaOutputView;
        if (view != null) {
            view.setVisibility((!this.mDeviceProvisionedController.isCurrentUserSetup() || this.mActivityManager.getLockTaskModeState() != 0 || !isBluetoothA2dpConnected() || !this.mExpanded) ? 8 : 0);
        }
        ImageButton imageButton = this.mMediaOutputIcon;
        if (imageButton != null) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    VolumeDialogImpl.this.lambda$updateMediaOutputH$5$VolumeDialogImpl(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateMediaOutputH$5$VolumeDialogImpl(View view) {
        rescheduleTimeoutH();
        Events.writeEvent(this.mContext, 8, new Object[0]);
        Intent intent = new Intent("com.android.settings.panel.action.MEDIA_OUTPUT");
        dismissH(5);
        ((ActivityStarter) Dependency.get(ActivityStarter.class)).startActivity(intent, true);
    }

    public void initSettingsH() {
        updateMediaOutputH();
        if (this.mAllyStream == -1) {
            this.mAllyStream = this.mActiveStream;
        }
        View view = this.mExpandRowsView;
        if (view != null) {
            view.setVisibility((!this.mDeviceProvisionedController.isCurrentUserSetup() || this.mActivityManager.getLockTaskModeState() != 0) ? 8 : 0);
        }
        ExpandableIndicator expandableIndicator = this.mExpandRows;
        if (expandableIndicator != null) {
            expandableIndicator.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    return VolumeDialogImpl.this.lambda$initSettingsH$6$VolumeDialogImpl(view);
                }
            });
            this.mExpandRows.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    VolumeDialogImpl.this.lambda$initSettingsH$7$VolumeDialogImpl(view);
                }
            });
        }
    }

    public /* synthetic */ boolean lambda$initSettingsH$6$VolumeDialogImpl(View view) {
        Events.writeEvent(this.mContext, 8, new Object[0]);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.Settings$SoundSettingsActivity");
        dismissH(5);
        ((ActivityStarter) Dependency.get(ActivityStarter.class)).startActivity(intent, true);
        return true;
    }

    public /* synthetic */ void lambda$initSettingsH$7$VolumeDialogImpl(View view) {
        rescheduleTimeoutH();
        animateExpandedRowsChange(!this.mExpanded);
        this.mExpandRows.setExpanded(!this.mExpanded);
        this.mExpanded = !this.mExpanded;
        updateMediaOutputH();
    }

    public void initRingerH() {
        ImageButton imageButton = this.mRingerIcon;
        if (imageButton != null) {
            imageButton.setAccessibilityLiveRegion(1);
            this.mRingerIcon.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    VolumeDialogImpl.this.lambda$initRingerH$8$VolumeDialogImpl(view);
                }
            });
        }
        updateRingerH();
    }

    public /* synthetic */ void lambda$initRingerH$8$VolumeDialogImpl(View view) {
        rescheduleTimeoutH();
        Prefs.putBoolean(this.mContext, "TouchedRingerToggle", true);
        int i = 2;
        VolumeDialogController.StreamState streamState = this.mState.states.get(2);
        if (streamState != null) {
            boolean hasVibrator = this.mController.hasVibrator();
            int i2 = this.mState.ringerModeInternal;
            if (i2 == 2) {
                if (hasVibrator) {
                    i = 1;
                    Events.writeEvent(this.mContext, 18, Integer.valueOf(i));
                    incrementManualToggleCount();
                    updateRingerH();
                    provideTouchFeedbackH(i);
                    this.mController.setRingerMode(i, false);
                    maybeShowToastH(i);
                }
            } else if (i2 != 1) {
                if (streamState.level == 0) {
                    this.mController.setStreamVolume(2, 1);
                }
                Events.writeEvent(this.mContext, 18, Integer.valueOf(i));
                incrementManualToggleCount();
                updateRingerH();
                provideTouchFeedbackH(i);
                this.mController.setRingerMode(i, false);
                maybeShowToastH(i);
            }
            i = 0;
            Events.writeEvent(this.mContext, 18, Integer.valueOf(i));
            incrementManualToggleCount();
            updateRingerH();
            provideTouchFeedbackH(i);
            this.mController.setRingerMode(i, false);
            maybeShowToastH(i);
        }
    }

    private void initODICaptionsH() {
        CaptionsToggleImageButton captionsToggleImageButton = this.mODICaptionsIcon;
        if (captionsToggleImageButton != null) {
            captionsToggleImageButton.setOnConfirmedTapListener(new CaptionsToggleImageButton.ConfirmedTapListener() {
                public final void onConfirmedTap() {
                    VolumeDialogImpl.this.lambda$initODICaptionsH$9$VolumeDialogImpl();
                }
            }, this.mHandler);
        }
        this.mController.getCaptionsComponentState(false);
    }

    public /* synthetic */ void lambda$initODICaptionsH$9$VolumeDialogImpl() {
        onCaptionIconClicked();
        Events.writeEvent(this.mContext, 21, new Object[0]);
    }

    private void checkODICaptionsTooltip(boolean z) {
        if (!this.mHasSeenODICaptionsTooltip && !z && this.mODICaptionsTooltipViewStub != null) {
            this.mController.getCaptionsComponentState(true);
        } else if (this.mHasSeenODICaptionsTooltip && z && this.mODICaptionsTooltipView != null) {
            hideCaptionsTooltip();
        }
    }

    /* access modifiers changed from: protected */
    public void showCaptionsTooltip() {
        ViewStub viewStub;
        if (!this.mHasSeenODICaptionsTooltip && (viewStub = this.mODICaptionsTooltipViewStub) != null) {
            this.mODICaptionsTooltipView = viewStub.inflate();
            this.mODICaptionsTooltipView.findViewById(C1777R$id.dismiss).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    VolumeDialogImpl.this.lambda$showCaptionsTooltip$10$VolumeDialogImpl(view);
                }
            });
            this.mODICaptionsTooltipViewStub = null;
        }
        View view = this.mODICaptionsTooltipView;
        if (view != null) {
            view.setAlpha(0.0f);
            this.mODICaptionsTooltipView.animate().alpha(1.0f).setStartDelay(300).withEndAction(new Runnable() {
                public final void run() {
                    VolumeDialogImpl.this.lambda$showCaptionsTooltip$11$VolumeDialogImpl();
                }
            }).start();
        }
    }

    public /* synthetic */ void lambda$showCaptionsTooltip$10$VolumeDialogImpl(View view) {
        rescheduleTimeoutH();
        hideCaptionsTooltip();
        Events.writeEvent(this.mContext, 22, new Object[0]);
    }

    public /* synthetic */ void lambda$showCaptionsTooltip$11$VolumeDialogImpl() {
        if (C1625D.BUG) {
            Log.d(TAG, "tool:checkODICaptionsTooltip() putBoolean true");
        }
        Prefs.putBoolean(this.mContext, "HasSeenODICaptionsTooltip", true);
        this.mHasSeenODICaptionsTooltip = true;
        CaptionsToggleImageButton captionsToggleImageButton = this.mODICaptionsIcon;
        if (captionsToggleImageButton != null) {
            captionsToggleImageButton.postOnAnimation(getSinglePressFor(captionsToggleImageButton));
        }
    }

    private void hideCaptionsTooltip() {
        View view = this.mODICaptionsTooltipView;
        if (view != null && view.getVisibility() == 0) {
            this.mODICaptionsTooltipView.animate().cancel();
            this.mODICaptionsTooltipView.setAlpha(1.0f);
            this.mODICaptionsTooltipView.animate().alpha(0.0f).setStartDelay(0).setDuration(250).withEndAction(new Runnable() {
                public final void run() {
                    VolumeDialogImpl.this.lambda$hideCaptionsTooltip$12$VolumeDialogImpl();
                }
            }).start();
        }
    }

    public /* synthetic */ void lambda$hideCaptionsTooltip$12$VolumeDialogImpl() {
        this.mODICaptionsTooltipView.setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void tryToRemoveCaptionsTooltip() {
        if (this.mHasSeenODICaptionsTooltip && this.mODICaptionsTooltipView != null) {
            ((ViewGroup) this.mDialog.findViewById(C1777R$id.volume_dialog_container)).removeView(this.mODICaptionsTooltipView);
            this.mODICaptionsTooltipView = null;
        }
    }

    /* access modifiers changed from: private */
    public void updateODICaptionsH(boolean z, boolean z2) {
        ViewGroup viewGroup = this.mODICaptionsView;
        if (viewGroup != null) {
            viewGroup.setVisibility(z ? 0 : 8);
        }
        if (z) {
            updateCaptionsIcon();
            if (z2) {
                showCaptionsTooltip();
            }
        }
    }

    private void updateCaptionsIcon() {
        boolean areCaptionsEnabled = this.mController.areCaptionsEnabled();
        if (this.mODICaptionsIcon.getCaptionsEnabled() != areCaptionsEnabled) {
            this.mHandler.post(this.mODICaptionsIcon.setCaptionsEnabled(areCaptionsEnabled));
        }
        boolean isCaptionStreamOptedOut = this.mController.isCaptionStreamOptedOut();
        if (this.mODICaptionsIcon.getOptedOut() != isCaptionStreamOptedOut) {
            this.mHandler.post(new Runnable(isCaptionStreamOptedOut) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    VolumeDialogImpl.this.lambda$updateCaptionsIcon$13$VolumeDialogImpl(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateCaptionsIcon$13$VolumeDialogImpl(boolean z) {
        this.mODICaptionsIcon.setOptedOut(z);
    }

    private void onCaptionIconClicked() {
        this.mController.setCaptionsEnabled(!this.mController.areCaptionsEnabled());
        updateCaptionsIcon();
    }

    private void incrementManualToggleCount() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Settings.Secure.putInt(contentResolver, "manual_ringer_toggle_count", Settings.Secure.getInt(contentResolver, "manual_ringer_toggle_count", 0) + 1);
    }

    private void provideTouchFeedbackH(int i) {
        VibrationEffect vibrationEffect;
        if (i == 0) {
            vibrationEffect = VibrationEffect.get(0);
        } else if (i != 2) {
            vibrationEffect = VibrationEffect.get(1);
        } else {
            this.mController.scheduleTouchFeedback();
            vibrationEffect = null;
        }
        if (vibrationEffect != null) {
            this.mController.vibrate(vibrationEffect);
        }
    }

    private void maybeShowToastH(int i) {
        int i2 = Prefs.getInt(this.mContext, "RingerGuidanceCount", 0);
        if (i2 <= 12) {
            String str = null;
            if (i == 0) {
                str = this.mContext.getString(17041283);
            } else if (i != 2) {
                str = this.mContext.getString(17041285);
            } else {
                VolumeDialogController.StreamState streamState = this.mState.states.get(2);
                if (streamState != null) {
                    str = this.mContext.getString(C1784R$string.volume_dialog_ringer_guidance_ring, new Object[]{Utils.formatPercentage((long) streamState.level, (long) streamState.levelMax)});
                }
            }
            Toast.makeText(this.mContext, str, 0).show();
            Prefs.putInt(this.mContext, "RingerGuidanceCount", i2 + 1);
        }
    }

    /* access modifiers changed from: private */
    public void showH(int i) {
        if (C1625D.BUG) {
            String str = TAG;
            Log.d(str, "showH r=" + Events.SHOW_REASONS[i]);
        }
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        rescheduleTimeoutH();
        if (this.mConfigChanged) {
            initDialog();
            this.mConfigurableTexts.update();
            this.mConfigChanged = false;
        }
        initSettingsH();
        this.mDialog.getViewTreeObserver().addOnComputeInternalInsetsListener(this.mInsetsListener);
        if (!this.mShowing && !this.mDialog.isShown()) {
            if (!isLandscape()) {
                this.mDialogView.setTranslationX(((float) ((this.mVolumePanelOnLeft ? -1 : 1) * this.mDialogView.getWidth())) / 2.0f);
            }
            this.mDialogView.setAlpha(0.0f);
            this.mDialogView.animate().alpha(1.0f).translationX(0.0f).setDuration(300).setInterpolator(new SystemUIInterpolators$LogDecelerateInterpolator()).withStartAction(new Runnable() {
                public final void run() {
                    VolumeDialogImpl.this.lambda$showH$14$VolumeDialogImpl();
                }
            }).withEndAction(new Runnable() {
                public final void run() {
                    VolumeDialogImpl.this.lambda$showH$15$VolumeDialogImpl();
                }
            }).start();
        }
        Events.writeEvent(this.mContext, 0, Integer.valueOf(i), Boolean.valueOf(this.mKeyguard.isKeyguardLocked()));
        this.mController.notifyVisible(true);
        this.mController.getCaptionsComponentState(false);
        checkODICaptionsTooltip(false);
    }

    public /* synthetic */ void lambda$showH$14$VolumeDialogImpl() {
        if (!this.mDialog.isShown()) {
            this.mWindowManager.addView(this.mDialog, this.mWindowParams);
        }
    }

    public /* synthetic */ void lambda$showH$15$VolumeDialogImpl() {
        ImageButton imageButton;
        if (!Prefs.getBoolean(this.mContext, "TouchedRingerToggle", false) && (imageButton = this.mRingerIcon) != null) {
            imageButton.postOnAnimationDelayed(getSinglePressFor(imageButton), 1500);
        }
        this.mShowing = true;
    }

    /* access modifiers changed from: protected */
    public void rescheduleTimeoutH() {
        this.mHandler.removeMessages(2);
        int computeTimeoutH = computeTimeoutH();
        C1651H h = this.mHandler;
        h.sendMessageDelayed(h.obtainMessage(2, 3, 0), (long) computeTimeoutH);
        if (C1625D.BUG) {
            String str = TAG;
            Log.d(str, "rescheduleTimeout " + computeTimeoutH + " " + Debug.getCaller());
        }
        this.mController.userActivity();
    }

    private int computeTimeoutH() {
        if (this.mHovering) {
            return this.mAccessibilityMgr.getRecommendedTimeoutMillis(16000, 4);
        }
        if (this.mSafetyWarning != null) {
            return this.mAccessibilityMgr.getRecommendedTimeoutMillis(5000, 6);
        }
        if (this.mHasSeenODICaptionsTooltip || this.mODICaptionsTooltipView == null) {
            return this.mAccessibilityMgr.getRecommendedTimeoutMillis(3000, 4);
        }
        return this.mAccessibilityMgr.getRecommendedTimeoutMillis(5000, 6);
    }

    /* access modifiers changed from: protected */
    public void dismissH(int i) {
        if (this.mShowing) {
            if (C1625D.BUG) {
                String str = TAG;
                Log.d(str, "mDialog.dismiss() reason: " + Events.DISMISS_REASONS[i] + " from: " + Debug.getCaller());
            }
            if (this.mShowing) {
                this.mHandler.removeMessages(2);
                this.mHandler.removeMessages(1);
                this.mDialogView.animate().cancel();
                if (this.mShowing) {
                    this.mShowing = false;
                    Events.writeEvent(this.mContext, 1, Integer.valueOf(i));
                }
                this.mDialogView.setTranslationX(0.0f);
                this.mDialogView.setAlpha(1.0f);
                ViewPropertyAnimator withEndAction = this.mDialogView.animate().alpha(0.0f).setDuration(250).setInterpolator(new SystemUIInterpolators$LogAccelerateInterpolator()).withEndAction(new Runnable() {
                    public final void run() {
                        VolumeDialogImpl.this.lambda$dismissH$17$VolumeDialogImpl();
                    }
                });
                withEndAction.translationX(getAnimatorX());
                withEndAction.start();
                checkODICaptionsTooltip(true);
                synchronized (this.mSafetyWarningLock) {
                    if (this.mSafetyWarning != null) {
                        if (C1625D.BUG) {
                            Log.d(TAG, "SafetyWarning dismissed");
                        }
                        this.mSafetyWarning.dismiss();
                    }
                }
            }
        }
    }

    public /* synthetic */ void lambda$dismissH$17$VolumeDialogImpl() {
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                VolumeDialogImpl.this.lambda$dismissH$16$VolumeDialogImpl();
            }
        }, 50);
    }

    public /* synthetic */ void lambda$dismissH$16$VolumeDialogImpl() {
        if (this.mDialog.isShown()) {
            this.mWindowManager.removeViewImmediate(this.mDialog);
        }
        this.mExpanded = false;
        this.mDialogRowsView.getLayoutParams().width = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.volume_dialog_panel_width);
        updateExpandedRows(this.mExpanded);
        this.mExpandRows.setExpanded(this.mExpanded);
        this.mAllyStream = -1;
        this.mMusicHidden = false;
        tryToRemoveCaptionsTooltip();
        this.mController.notifyVisible(false);
    }

    private boolean showActiveStreamOnly() {
        return this.mContext.getPackageManager().hasSystemFeature("android.software.leanback") || this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.television");
    }

    private boolean shouldBeVisibleH(VolumeRow volumeRow, VolumeRow volumeRow2) {
        boolean z = volumeRow.stream == volumeRow2.stream;
        if (volumeRow.stream == 3 && volumeRow2.stream != 3 && !this.mExpanded) {
            this.mMusicHidden = true;
            return false;
        } else if (z) {
            return true;
        } else {
            if (this.mShowActiveStreamOnly) {
                return false;
            }
            if (volumeRow.stream == 10) {
                return this.mShowA11yStream;
            }
            if (volumeRow2.stream == 10 && volumeRow.stream == this.mPrevActiveStream) {
                return true;
            }
            if (!volumeRow.defaultStream) {
                return false;
            }
            if (volumeRow2.stream == 2 || volumeRow2.stream == 5 || volumeRow2.stream == 4 || volumeRow2.stream == 0 || volumeRow2.stream == 10 || this.mDynamic.get(volumeRow2.stream)) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void updateRowsH(VolumeRow volumeRow) {
        if (C1625D.BUG) {
            Log.d(TAG, "updateRowsH");
        }
        if (!this.mShowing) {
            trimObsoleteH();
        }
        Iterator<VolumeRow> it = this.mRows.iterator();
        while (it.hasNext()) {
            VolumeRow next = it.next();
            boolean z = next == volumeRow;
            boolean shouldBeVisibleH = shouldBeVisibleH(next, volumeRow);
            if (!this.mExpanded) {
                Util.setVisOrGone(next.view, shouldBeVisibleH);
            }
            if (next.view.isShown()) {
                updateVolumeRowTintH(next, z);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateRingerH() {
        VolumeDialogController.StreamState streamState;
        VolumeDialogController.State state = this.mState;
        if (state != null && (streamState = state.states.get(2)) != null) {
            VolumeDialogController.State state2 = this.mState;
            int i = state2.zenMode;
            boolean z = false;
            boolean z2 = i == 3 || i == 2 || (i == 1 && state2.disallowRinger);
            enableRingerViewsH(!z2);
            int i2 = this.mState.ringerModeInternal;
            if (i2 == 0) {
                this.mRingerIcon.setImageResource(C1776R$drawable.ic_volume_ringer_mute);
                this.mRingerIcon.setTag(2);
                addAccessibilityDescription(this.mRingerIcon, 0, this.mContext.getString(C1784R$string.volume_ringer_hint_unmute));
            } else if (i2 != 1) {
                if ((this.mAutomute && streamState.level == 0) || streamState.muted) {
                    z = true;
                }
                if (z2 || !z) {
                    this.mRingerIcon.setImageResource(C1776R$drawable.ic_volume_ringer);
                    if (this.mController.hasVibrator()) {
                        addAccessibilityDescription(this.mRingerIcon, 2, this.mContext.getString(C1784R$string.volume_ringer_hint_vibrate));
                    } else {
                        addAccessibilityDescription(this.mRingerIcon, 2, this.mContext.getString(C1784R$string.volume_ringer_hint_mute));
                    }
                    this.mRingerIcon.setTag(1);
                    return;
                }
                this.mRingerIcon.setImageResource(C1776R$drawable.ic_volume_ringer_mute);
                addAccessibilityDescription(this.mRingerIcon, 2, this.mContext.getString(C1784R$string.volume_ringer_hint_unmute));
                this.mRingerIcon.setTag(2);
            } else {
                this.mRingerIcon.setImageResource(C1776R$drawable.ic_volume_ringer_vibrate);
                addAccessibilityDescription(this.mRingerIcon, 1, this.mContext.getString(C1784R$string.volume_ringer_hint_mute));
                this.mRingerIcon.setTag(3);
            }
        }
    }

    private void addAccessibilityDescription(View view, int i, final String str) {
        int i2;
        if (i == 0) {
            i2 = C1784R$string.volume_ringer_status_silent;
        } else if (i != 1) {
            i2 = C1784R$string.volume_ringer_status_normal;
        } else {
            i2 = C1784R$string.volume_ringer_status_vibrate;
        }
        view.setContentDescription(this.mContext.getString(i2));
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, str));
            }
        });
    }

    private void enableVolumeRowViewsH(VolumeRow volumeRow, boolean z) {
        volumeRow.dndIcon.setVisibility(z ^ true ? 0 : 8);
    }

    private void enableRingerViewsH(boolean z) {
        ImageButton imageButton = this.mRingerIcon;
        if (imageButton != null) {
            imageButton.setEnabled(z);
        }
        FrameLayout frameLayout = this.mZenIcon;
        if (frameLayout != null) {
            frameLayout.setVisibility(z ? 8 : 0);
        }
    }

    private void trimObsoleteH() {
        if (C1625D.BUG) {
            Log.d(TAG, "trimObsoleteH");
        }
        for (int size = this.mRows.size() - 1; size >= 0; size--) {
            VolumeRow volumeRow = this.mRows.get(size);
            if (volumeRow.f78ss != null && volumeRow.f78ss.dynamic && !this.mDynamic.get(volumeRow.stream)) {
                removeRow(volumeRow);
            }
        }
    }

    private void removeRow(VolumeRow volumeRow) {
        this.mRows.remove(volumeRow);
        this.mDialogRowsView.removeView(volumeRow.view);
    }

    private void removeAppRow(VolumeRow volumeRow) {
        this.mAppRows.remove(volumeRow);
        this.mDialogRowsView.removeView(volumeRow.view);
    }

    /* access modifiers changed from: protected */
    public void onStateChangedH(VolumeDialogController.State state) {
        int i;
        if (C1625D.BUG) {
            String str = TAG;
            Log.d(str, "onStateChangedH() state: " + state.toString());
        }
        VolumeDialogController.State state2 = this.mState;
        if (!(state2 == null || state == null || state2.ringerModeInternal == (i = state.ringerModeInternal) || i != 1)) {
            this.mController.vibrate(VibrationEffect.get(5));
        }
        this.mState = state;
        this.mDynamic.clear();
        for (int i2 = 0; i2 < state.states.size(); i2++) {
            int keyAt = state.states.keyAt(i2);
            if (state.states.valueAt(i2).dynamic) {
                this.mDynamic.put(keyAt, true);
                if (findRow(keyAt) == null) {
                    addRow(keyAt, C1776R$drawable.ic_volume_remote, C1776R$drawable.ic_volume_remote_mute, true, false, true);
                }
            }
        }
        if (Util.isVoiceCapable(this.mContext)) {
            updateNotificationRowH();
        }
        int i3 = this.mActiveStream;
        int i4 = state.activeStream;
        if (i3 != i4) {
            this.mPrevActiveStream = i3;
            this.mActiveStream = i4;
            updateRowsH(getActiveRow());
            if (this.mShowing) {
                rescheduleTimeoutH();
            }
        }
        for (VolumeRow updateVolumeRowH : this.mRows) {
            updateVolumeRowH(updateVolumeRowH);
        }
        updateRingerH();
    }

    /* access modifiers changed from: package-private */
    public CharSequence composeWindowTitle() {
        return this.mContext.getString(C1784R$string.volume_dialog_title, new Object[]{getStreamLabelH(getActiveRow().f78ss)});
    }

    private void updateNotificationRowH() {
        VolumeRow findRow = findRow(5);
        if (findRow != null && this.mState.linkedNotification) {
            removeRow(findRow);
        } else if (findRow == null && !this.mState.linkedNotification) {
            addRow(5, C1776R$drawable.ic_volume_notification, C1776R$drawable.ic_volume_notification_mute, true, false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00db, code lost:
        if (r12 == false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00e9, code lost:
        if (r12 == false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00f4, code lost:
        if (r0.mState.disallowAlarms != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00fc, code lost:
        if (r0.mState.disallowMedia != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0104, code lost:
        if (r0.mState.disallowRinger != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x010c, code lost:
        if (r0.mState.disallowSystem != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x010f, code lost:
        if (r14 != false) goto L_0x0113;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x02c8 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateVolumeRowH(com.android.systemui.volume.VolumeDialogImpl.VolumeRow r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            boolean r2 = com.android.systemui.volume.C1625D.BUG
            if (r2 == 0) goto L_0x0022
            java.lang.String r2 = TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "updateVolumeRowH s="
            r3.append(r4)
            int r4 = r18.stream
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r2, r3)
        L_0x0022:
            com.android.systemui.plugins.VolumeDialogController$State r2 = r0.mState
            if (r2 != 0) goto L_0x0027
            return
        L_0x0027:
            android.util.SparseArray<com.android.systemui.plugins.VolumeDialogController$StreamState> r2 = r2.states
            int r3 = r18.stream
            java.lang.Object r2 = r2.get(r3)
            com.android.systemui.plugins.VolumeDialogController$StreamState r2 = (com.android.systemui.plugins.VolumeDialogController.StreamState) r2
            if (r2 != 0) goto L_0x0036
            return
        L_0x0036:
            com.android.systemui.plugins.VolumeDialogController.StreamState unused = r1.f78ss = r2
            int r3 = r2.level
            int r4 = r2.levelMin
            if (r3 <= r4) goto L_0x0042
            int unused = r1.lastAudibleLevel = r3
        L_0x0042:
            int r3 = r2.level
            int r4 = r18.requestedLevel
            if (r3 != r4) goto L_0x004e
            r3 = -1
            int unused = r1.requestedLevel = r3
        L_0x004e:
            int r3 = r18.stream
            r4 = 10
            r6 = 1
            if (r3 != r4) goto L_0x0059
            r3 = r6
            goto L_0x005a
        L_0x0059:
            r3 = 0
        L_0x005a:
            int r4 = r18.stream
            r7 = 2
            if (r4 != r7) goto L_0x0063
            r4 = r6
            goto L_0x0064
        L_0x0063:
            r4 = 0
        L_0x0064:
            int r8 = r18.stream
            if (r8 != r6) goto L_0x006c
            r8 = r6
            goto L_0x006d
        L_0x006c:
            r8 = 0
        L_0x006d:
            int r9 = r18.stream
            r10 = 4
            if (r9 != r10) goto L_0x0076
            r9 = r6
            goto L_0x0077
        L_0x0076:
            r9 = 0
        L_0x0077:
            int r10 = r18.stream
            r11 = 3
            if (r10 != r11) goto L_0x0080
            r10 = r6
            goto L_0x0081
        L_0x0080:
            r10 = 0
        L_0x0081:
            int r12 = r18.stream
            r13 = 5
            if (r12 != r13) goto L_0x008a
            r12 = r6
            goto L_0x008b
        L_0x008a:
            r12 = 0
        L_0x008b:
            com.android.systemui.plugins.VolumeDialogController$StreamState r13 = r18.f78ss
            int r13 = r13.level
            com.android.systemui.plugins.VolumeDialogController$StreamState r14 = r18.f78ss
            int r14 = r14.levelMin
            if (r13 != r14) goto L_0x009b
            r13 = r6
            goto L_0x009c
        L_0x009b:
            r13 = 0
        L_0x009c:
            com.android.systemui.plugins.VolumeDialogController$State r14 = r0.mState
            int r14 = r14.ringerModeInternal
            if (r14 != r6) goto L_0x00a4
            r14 = r6
            goto L_0x00a5
        L_0x00a4:
            r14 = 0
        L_0x00a5:
            if (r4 == 0) goto L_0x00ab
            if (r14 == 0) goto L_0x00ab
            r15 = r6
            goto L_0x00ac
        L_0x00ab:
            r15 = 0
        L_0x00ac:
            if (r4 == 0) goto L_0x00b6
            com.android.systemui.plugins.VolumeDialogController$State r5 = r0.mState
            int r5 = r5.ringerModeInternal
            if (r5 != 0) goto L_0x00b6
            r5 = r6
            goto L_0x00b7
        L_0x00b6:
            r5 = 0
        L_0x00b7:
            com.android.systemui.plugins.VolumeDialogController$State r7 = r0.mState
            int r7 = r7.zenMode
            if (r7 != r6) goto L_0x00bf
            r7 = r6
            goto L_0x00c0
        L_0x00bf:
            r7 = 0
        L_0x00c0:
            com.android.systemui.plugins.VolumeDialogController$State r6 = r0.mState
            int r6 = r6.zenMode
            if (r6 != r11) goto L_0x00c8
            r6 = 1
            goto L_0x00c9
        L_0x00c8:
            r6 = 0
        L_0x00c9:
            com.android.systemui.plugins.VolumeDialogController$State r11 = r0.mState
            int r11 = r11.zenMode
            r16 = r3
            r3 = 2
            if (r11 != r3) goto L_0x00d4
            r11 = 1
            goto L_0x00d5
        L_0x00d4:
            r11 = 0
        L_0x00d5:
            if (r6 == 0) goto L_0x00df
            if (r4 != 0) goto L_0x00dd
            if (r8 != 0) goto L_0x00dd
            if (r12 == 0) goto L_0x0112
        L_0x00dd:
            r12 = 1
            goto L_0x0113
        L_0x00df:
            if (r11 == 0) goto L_0x00ec
            if (r4 != 0) goto L_0x00dd
            if (r8 != 0) goto L_0x00dd
            if (r9 != 0) goto L_0x00dd
            if (r10 != 0) goto L_0x00dd
            if (r12 == 0) goto L_0x0112
            goto L_0x00dd
        L_0x00ec:
            if (r7 == 0) goto L_0x010f
            if (r9 == 0) goto L_0x00f6
            com.android.systemui.plugins.VolumeDialogController$State r6 = r0.mState
            boolean r6 = r6.disallowAlarms
            if (r6 != 0) goto L_0x00dd
        L_0x00f6:
            if (r10 == 0) goto L_0x00fe
            com.android.systemui.plugins.VolumeDialogController$State r6 = r0.mState
            boolean r6 = r6.disallowMedia
            if (r6 != 0) goto L_0x00dd
        L_0x00fe:
            if (r4 == 0) goto L_0x0106
            com.android.systemui.plugins.VolumeDialogController$State r6 = r0.mState
            boolean r6 = r6.disallowRinger
            if (r6 != 0) goto L_0x00dd
        L_0x0106:
            if (r8 == 0) goto L_0x0112
            com.android.systemui.plugins.VolumeDialogController$State r6 = r0.mState
            boolean r6 = r6.disallowSystem
            if (r6 == 0) goto L_0x0112
            goto L_0x00dd
        L_0x010f:
            if (r14 == 0) goto L_0x0112
            goto L_0x0113
        L_0x0112:
            r12 = 0
        L_0x0113:
            int r6 = r2.levelMax
            int r6 = r6 * 100
            android.widget.SeekBar r7 = r18.slider
            int r7 = r7.getMax()
            if (r6 == r7) goto L_0x0123
            r7 = 1
            goto L_0x0124
        L_0x0123:
            r7 = 0
        L_0x0124:
            if (r7 == 0) goto L_0x012d
            android.widget.SeekBar r8 = r18.slider
            r8.setMax(r6)
        L_0x012d:
            int r6 = r2.levelMin
            int r6 = r6 * 100
            android.widget.SeekBar r8 = r18.slider
            int r8 = r8.getMin()
            if (r6 == r8) goto L_0x0142
            android.widget.SeekBar r8 = r18.slider
            r8.setMin(r6)
        L_0x0142:
            android.widget.TextView r6 = r18.header
            java.lang.String r8 = r0.getStreamLabelH(r2)
            com.android.settingslib.volume.Util.setText(r6, r8)
            android.widget.SeekBar r6 = r18.slider
            android.widget.TextView r8 = r18.header
            java.lang.CharSequence r8 = r8.getText()
            r6.setContentDescription(r8)
            com.android.systemui.volume.ConfigurableTexts r6 = r0.mConfigurableTexts
            android.widget.TextView r8 = r18.header
            int r9 = r2.name
            r6.add(r8, r9)
            boolean r6 = r0.mAutomute
            if (r6 != 0) goto L_0x016f
            boolean r6 = r2.muteSupported
            if (r6 == 0) goto L_0x0173
        L_0x016f:
            if (r12 != 0) goto L_0x0173
            r6 = 1
            goto L_0x0174
        L_0x0173:
            r6 = 0
        L_0x0174:
            android.widget.ImageButton r8 = r18.icon
            r8.setEnabled(r6)
            android.widget.ImageButton r8 = r18.icon
            if (r6 == 0) goto L_0x0184
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0186
        L_0x0184:
            r9 = 1056964608(0x3f000000, float:0.5)
        L_0x0186:
            r8.setAlpha(r9)
            if (r15 == 0) goto L_0x018e
            int r5 = com.android.systemui.C1776R$drawable.ic_volume_ringer_vibrate
            goto L_0x01be
        L_0x018e:
            if (r5 != 0) goto L_0x01ba
            if (r12 == 0) goto L_0x0193
            goto L_0x01ba
        L_0x0193:
            boolean r5 = r2.routedToBluetooth
            if (r5 == 0) goto L_0x01a1
            boolean r5 = r2.muted
            if (r5 == 0) goto L_0x019e
            int r5 = com.android.systemui.C1776R$drawable.ic_volume_media_bt_mute
            goto L_0x01be
        L_0x019e:
            int r5 = com.android.systemui.C1776R$drawable.ic_volume_media_bt
            goto L_0x01be
        L_0x01a1:
            boolean r5 = r0.mAutomute
            if (r5 == 0) goto L_0x01ae
            int r5 = r2.level
            if (r5 != 0) goto L_0x01ae
            int r5 = r18.iconMuteRes
            goto L_0x01be
        L_0x01ae:
            if (r13 == 0) goto L_0x01b5
            int r5 = r18.iconMuteRes
            goto L_0x01be
        L_0x01b5:
            int r5 = r18.iconRes
            goto L_0x01be
        L_0x01ba:
            int r5 = r18.iconMuteRes
        L_0x01be:
            android.widget.ImageButton r8 = r18.icon
            r8.setImageResource(r5)
            int r8 = com.android.systemui.C1776R$drawable.ic_volume_ringer_vibrate
            if (r5 != r8) goto L_0x01cb
            r3 = 3
            goto L_0x01e4
        L_0x01cb:
            int r8 = com.android.systemui.C1776R$drawable.ic_volume_media_bt_mute
            if (r5 == r8) goto L_0x01e4
            int r8 = r18.iconMuteRes
            if (r5 != r8) goto L_0x01d6
            goto L_0x01e4
        L_0x01d6:
            int r3 = com.android.systemui.C1776R$drawable.ic_volume_media_bt
            if (r5 == r3) goto L_0x01e3
            int r3 = r18.iconRes
            if (r5 != r3) goto L_0x01e1
            goto L_0x01e3
        L_0x01e1:
            r3 = 0
            goto L_0x01e4
        L_0x01e3:
            r3 = 1
        L_0x01e4:
            int unused = r1.iconState = r3
            if (r6 == 0) goto L_0x02a8
            if (r4 == 0) goto L_0x0251
            if (r15 == 0) goto L_0x0207
            android.widget.ImageButton r3 = r18.icon
            android.content.Context r5 = r0.mContext
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_unmute
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r9 = 0
            r8[r9] = r2
            java.lang.String r2 = r5.getString(r6, r8)
            r3.setContentDescription(r2)
            goto L_0x025e
        L_0x0207:
            com.android.systemui.plugins.VolumeDialogController r3 = r0.mController
            boolean r3 = r3.hasVibrator()
            if (r3 == 0) goto L_0x0230
            android.widget.ImageButton r3 = r18.icon
            android.content.Context r5 = r0.mContext
            boolean r6 = r0.mShowA11yStream
            if (r6 == 0) goto L_0x021c
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_vibrate_a11y
            goto L_0x021e
        L_0x021c:
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_vibrate
        L_0x021e:
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r9 = 0
            r8[r9] = r2
            java.lang.String r2 = r5.getString(r6, r8)
            r3.setContentDescription(r2)
            goto L_0x025e
        L_0x0230:
            android.widget.ImageButton r3 = r18.icon
            android.content.Context r5 = r0.mContext
            boolean r6 = r0.mShowA11yStream
            if (r6 == 0) goto L_0x023d
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_mute_a11y
            goto L_0x023f
        L_0x023d:
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_mute
        L_0x023f:
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r9 = 0
            r8[r9] = r2
            java.lang.String r2 = r5.getString(r6, r8)
            r3.setContentDescription(r2)
            goto L_0x025e
        L_0x0251:
            if (r16 == 0) goto L_0x0260
            android.widget.ImageButton r3 = r18.icon
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r3.setContentDescription(r2)
        L_0x025e:
            r9 = 0
            goto L_0x02b4
        L_0x0260:
            boolean r3 = r2.muted
            if (r3 != 0) goto L_0x028e
            boolean r3 = r0.mAutomute
            if (r3 == 0) goto L_0x026d
            int r3 = r2.level
            if (r3 != 0) goto L_0x026d
            goto L_0x028e
        L_0x026d:
            android.widget.ImageButton r3 = r18.icon
            android.content.Context r5 = r0.mContext
            boolean r6 = r0.mShowA11yStream
            if (r6 == 0) goto L_0x027a
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_mute_a11y
            goto L_0x027c
        L_0x027a:
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_mute
        L_0x027c:
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r9 = 0
            r8[r9] = r2
            java.lang.String r2 = r5.getString(r6, r8)
            r3.setContentDescription(r2)
            goto L_0x02b4
        L_0x028e:
            r8 = 1
            r9 = 0
            android.widget.ImageButton r3 = r18.icon
            android.content.Context r5 = r0.mContext
            int r6 = com.android.systemui.C1784R$string.volume_stream_content_description_unmute
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r8[r9] = r2
            java.lang.String r2 = r5.getString(r6, r8)
            r3.setContentDescription(r2)
            goto L_0x02b4
        L_0x02a8:
            r9 = 0
            android.widget.ImageButton r3 = r18.icon
            java.lang.String r2 = r0.getStreamLabelH(r2)
            r3.setContentDescription(r2)
        L_0x02b4:
            if (r12 == 0) goto L_0x02b9
            boolean unused = r1.tracking = r9
        L_0x02b9:
            r2 = r12 ^ 1
            r0.enableVolumeRowViewsH(r1, r2)
            r2 = r12 ^ 1
            com.android.systemui.plugins.VolumeDialogController$StreamState r3 = r18.f78ss
            boolean r3 = r3.muted
            if (r3 == 0) goto L_0x02ce
            if (r4 != 0) goto L_0x02ce
            if (r12 != 0) goto L_0x02ce
            r5 = r9
            goto L_0x02d4
        L_0x02ce:
            com.android.systemui.plugins.VolumeDialogController$StreamState r3 = r18.f78ss
            int r5 = r3.level
        L_0x02d4:
            r0.updateVolumeRowSliderH(r1, r2, r5, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.VolumeDialogImpl.updateVolumeRowH(com.android.systemui.volume.VolumeDialogImpl$VolumeRow):void");
    }

    private void updateVolumeRowTintH(VolumeRow volumeRow, boolean z) {
        ColorStateList colorStateList;
        int i;
        if (z) {
            volumeRow.slider.requestFocus();
        }
        boolean z2 = z && volumeRow.slider.isEnabled();
        if (z2) {
            colorStateList = Utils.getColorAccent(this.mContext);
        } else {
            colorStateList = Utils.getColorAttr(this.mContext, 16842800);
        }
        if (z2) {
            i = Color.alpha(colorStateList.getDefaultColor());
        } else {
            i = getAlphaAttr(16844115);
        }
        if (colorStateList != volumeRow.cachedTint || !this.mExpanded) {
            volumeRow.slider.setProgressTintList(colorStateList);
            volumeRow.slider.setThumbTintList(colorStateList);
            volumeRow.slider.setProgressBackgroundTintList(colorStateList);
            volumeRow.slider.setAlpha(((float) i) / 255.0f);
            volumeRow.icon.setImageTintList(colorStateList);
            volumeRow.icon.setImageAlpha(i);
            ColorStateList unused = volumeRow.cachedTint = colorStateList;
        }
    }

    private void updateVolumeRowSliderH(VolumeRow volumeRow, boolean z, int i, boolean z2) {
        volumeRow.slider.setEnabled(z);
        updateVolumeRowTintH(volumeRow, volumeRow.stream == this.mActiveStream);
        if (!volumeRow.tracking) {
            int progress = volumeRow.slider.getProgress();
            int impliedLevel = getImpliedLevel(volumeRow.slider, progress);
            boolean z3 = volumeRow.view.getVisibility() == 0;
            boolean z4 = SystemClock.uptimeMillis() - volumeRow.userAttempt < 1000;
            this.mHandler.removeMessages(3, volumeRow);
            if (this.mShowing && z3 && z4) {
                if (C1625D.BUG) {
                    Log.d(TAG, "inGracePeriod");
                }
                C1651H h = this.mHandler;
                h.sendMessageAtTime(h.obtainMessage(3, volumeRow), volumeRow.userAttempt + 1000);
            } else if (i != impliedLevel || !this.mShowing || !z3) {
                int i2 = i * 100;
                if (progress == i2 && !z2) {
                    return;
                }
                if (!this.mShowing || !z3) {
                    if (volumeRow.anim != null) {
                        volumeRow.anim.cancel();
                    }
                    volumeRow.slider.setProgress(i2, true);
                } else if (volumeRow.anim == null || !volumeRow.anim.isRunning() || volumeRow.animTargetProgress != i2) {
                    if (volumeRow.anim == null) {
                        ObjectAnimator unused = volumeRow.anim = ObjectAnimator.ofInt(volumeRow.slider, "progress", new int[]{progress, i2});
                        volumeRow.anim.setInterpolator(new DecelerateInterpolator());
                    } else {
                        volumeRow.anim.cancel();
                        volumeRow.anim.setIntValues(new int[]{progress, i2});
                    }
                    int unused2 = volumeRow.animTargetProgress = i2;
                    volumeRow.anim.setDuration(80);
                    volumeRow.anim.start();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void recheckH(VolumeRow volumeRow) {
        if (volumeRow == null) {
            if (C1625D.BUG) {
                Log.d(TAG, "recheckH ALL");
            }
            trimObsoleteH();
            for (VolumeRow updateVolumeRowH : this.mRows) {
                updateVolumeRowH(updateVolumeRowH);
            }
            return;
        }
        if (C1625D.BUG) {
            String str = TAG;
            Log.d(str, "recheckH " + volumeRow.stream);
        }
        updateVolumeRowH(volumeRow);
    }

    /* access modifiers changed from: private */
    public void setStreamImportantH(int i, boolean z) {
        for (VolumeRow next : this.mRows) {
            if (next.stream == i) {
                boolean unused = next.important = z;
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        recheckH((com.android.systemui.volume.VolumeDialogImpl.VolumeRow) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showSafetyWarningH(int r4) {
        /*
            r3 = this;
            r4 = r4 & 1025(0x401, float:1.436E-42)
            if (r4 != 0) goto L_0x0008
            boolean r4 = r3.mShowing
            if (r4 == 0) goto L_0x002a
        L_0x0008:
            java.lang.Object r4 = r3.mSafetyWarningLock
            monitor-enter(r4)
            com.android.systemui.volume.SafetyWarningDialog r0 = r3.mSafetyWarning     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0011
            monitor-exit(r4)     // Catch:{ all -> 0x002e }
            return
        L_0x0011:
            com.android.systemui.volume.VolumeDialogImpl$4 r0 = new com.android.systemui.volume.VolumeDialogImpl$4     // Catch:{ all -> 0x002e }
            android.content.Context r1 = r3.mContext     // Catch:{ all -> 0x002e }
            com.android.systemui.plugins.VolumeDialogController r2 = r3.mController     // Catch:{ all -> 0x002e }
            android.media.AudioManager r2 = r2.getAudioManager()     // Catch:{ all -> 0x002e }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x002e }
            r3.mSafetyWarning = r0     // Catch:{ all -> 0x002e }
            com.android.systemui.volume.SafetyWarningDialog r0 = r3.mSafetyWarning     // Catch:{ all -> 0x002e }
            r0.show()     // Catch:{ all -> 0x002e }
            monitor-exit(r4)     // Catch:{ all -> 0x002e }
            r4 = 0
            r3.recheckH(r4)
        L_0x002a:
            r3.rescheduleTimeoutH()
            return
        L_0x002e:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x002e }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.VolumeDialogImpl.showSafetyWarningH(int):void");
    }

    private String getStreamLabelH(VolumeDialogController.StreamState streamState) {
        if (streamState == null) {
            return "";
        }
        String str = streamState.remoteLabel;
        if (str != null) {
            return str;
        }
        try {
            return this.mContext.getResources().getString(streamState.name);
        } catch (Resources.NotFoundException unused) {
            String str2 = TAG;
            Slog.e(str2, "Can't find translation for stream " + streamState);
            return "";
        }
    }

    private Runnable getSinglePressFor(ImageButton imageButton) {
        return new Runnable(imageButton) {
            private final /* synthetic */ ImageButton f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VolumeDialogImpl.this.lambda$getSinglePressFor$18$VolumeDialogImpl(this.f$1);
            }
        };
    }

    public /* synthetic */ void lambda$getSinglePressFor$18$VolumeDialogImpl(ImageButton imageButton) {
        if (imageButton != null) {
            imageButton.setPressed(true);
            imageButton.postOnAnimationDelayed(getSingleUnpressFor(imageButton), 200);
        }
    }

    private Runnable getSingleUnpressFor(ImageButton imageButton) {
        return new Runnable(imageButton) {
            private final /* synthetic */ ImageButton f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                VolumeDialogImpl.lambda$getSingleUnpressFor$19(this.f$0);
            }
        };
    }

    static /* synthetic */ void lambda$getSingleUnpressFor$19(ImageButton imageButton) {
        if (imageButton != null) {
            imageButton.setPressed(false);
        }
    }

    /* renamed from: com.android.systemui.volume.VolumeDialogImpl$H */
    private final class C1651H extends Handler {
        public C1651H() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    VolumeDialogImpl.this.showH(message.arg1);
                    return;
                case 2:
                    VolumeDialogImpl.this.dismissH(message.arg1);
                    return;
                case 3:
                    VolumeDialogImpl.this.recheckH((VolumeRow) message.obj);
                    return;
                case 4:
                    VolumeDialogImpl.this.recheckH((VolumeRow) null);
                    return;
                case 5:
                    VolumeDialogImpl.this.setStreamImportantH(message.arg1, message.arg2 != 0);
                    return;
                case 6:
                    VolumeDialogImpl.this.rescheduleTimeoutH();
                    return;
                case 7:
                    VolumeDialogImpl volumeDialogImpl = VolumeDialogImpl.this;
                    volumeDialogImpl.onStateChangedH(volumeDialogImpl.mState);
                    return;
                default:
                    return;
            }
        }
    }

    private final class VolumeSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private final VolumeRow mRow;

        private VolumeSeekBarChangeListener(VolumeRow volumeRow) {
            this.mRow = volumeRow;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int i2;
            VolumeDialogImpl.this.rescheduleTimeoutH();
            if (C1625D.BUG) {
                String access$4400 = VolumeDialogImpl.TAG;
                Log.d(access$4400, AudioSystem.streamToString(this.mRow.stream) + " onProgressChanged " + i + " fromUser=" + z);
            }
            if (z) {
                if (this.mRow.isAppVolumeRow) {
                    VolumeDialogImpl.this.mController.getAudioManager().setAppVolume(this.mRow.packageName, ((float) i) * 0.01f);
                } else if (this.mRow.f78ss != null) {
                    if (this.mRow.f78ss.levelMin > 0 && i < (i2 = this.mRow.f78ss.levelMin * 100)) {
                        seekBar.setProgress(i2);
                        i = i2;
                    }
                    int access$4500 = VolumeDialogImpl.getImpliedLevel(seekBar, i);
                    if (this.mRow.f78ss.level != access$4500 || (this.mRow.f78ss.muted && access$4500 > 0)) {
                        long unused = this.mRow.userAttempt = SystemClock.uptimeMillis();
                        if (this.mRow.requestedLevel != access$4500) {
                            VolumeDialogImpl.this.mController.setActiveStream(this.mRow.stream);
                            VolumeDialogImpl.this.mController.setStreamVolume(this.mRow.stream, access$4500);
                            int unused2 = this.mRow.requestedLevel = access$4500;
                            Events.writeEvent(VolumeDialogImpl.this.mContext, 9, Integer.valueOf(this.mRow.stream), Integer.valueOf(access$4500));
                        }
                    }
                }
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            boolean unused = this.mRow.tracking = true;
            if (!this.mRow.isAppVolumeRow) {
                if (C1625D.BUG) {
                    String access$4400 = VolumeDialogImpl.TAG;
                    Log.d(access$4400, "onStartTrackingTouch " + this.mRow.stream);
                }
                VolumeDialogImpl.this.mController.setActiveStream(this.mRow.stream);
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (C1625D.BUG) {
                String access$4400 = VolumeDialogImpl.TAG;
                Log.d(access$4400, "onStopTrackingTouch " + this.mRow.stream);
            }
            boolean unused = this.mRow.tracking = false;
            if (!this.mRow.isAppVolumeRow) {
                long unused2 = this.mRow.userAttempt = SystemClock.uptimeMillis();
                int access$4500 = VolumeDialogImpl.getImpliedLevel(seekBar, seekBar.getProgress());
                Events.writeEvent(VolumeDialogImpl.this.mContext, 16, Integer.valueOf(this.mRow.stream), Integer.valueOf(access$4500));
                if (this.mRow.f78ss.level != access$4500) {
                    VolumeDialogImpl.this.mHandler.sendMessageDelayed(VolumeDialogImpl.this.mHandler.obtainMessage(3, this.mRow), 1000);
                }
            }
        }
    }

    private final class Accessibility extends View.AccessibilityDelegate {
        private Accessibility() {
        }

        public void init() {
            VolumeDialogImpl.this.mDialogView.setAccessibilityDelegate(this);
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.getText().add(VolumeDialogImpl.this.composeWindowTitle());
            return true;
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            VolumeDialogImpl.this.rescheduleTimeoutH();
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    private static class VolumeRow {
        /* access modifiers changed from: private */
        public ObjectAnimator anim;
        /* access modifiers changed from: private */
        public int animTargetProgress;
        /* access modifiers changed from: private */
        public boolean appMuted;
        /* access modifiers changed from: private */
        public ColorStateList cachedTint;
        /* access modifiers changed from: private */
        public boolean defaultStream;
        /* access modifiers changed from: private */
        public FrameLayout dndIcon;
        /* access modifiers changed from: private */
        public TextView header;
        /* access modifiers changed from: private */
        public ImageButton icon;
        /* access modifiers changed from: private */
        public int iconMuteRes;
        /* access modifiers changed from: private */
        public int iconRes;
        /* access modifiers changed from: private */
        public int iconState;
        /* access modifiers changed from: private */
        public boolean important;
        /* access modifiers changed from: private */
        public boolean isAppVolumeRow;
        /* access modifiers changed from: private */
        public int lastAudibleLevel;
        /* access modifiers changed from: private */
        public String packageName;
        /* access modifiers changed from: private */
        public int requestedLevel;
        /* access modifiers changed from: private */
        public SeekBar slider;
        /* access modifiers changed from: private */

        /* renamed from: ss */
        public VolumeDialogController.StreamState f78ss;
        /* access modifiers changed from: private */
        public int stream;
        /* access modifiers changed from: private */
        public boolean tracking;
        /* access modifiers changed from: private */
        public long userAttempt;
        /* access modifiers changed from: private */
        public View view;

        private VolumeRow() {
            this.requestedLevel = -1;
            this.lastAudibleLevel = 2;
            this.isAppVolumeRow = false;
        }
    }
}
