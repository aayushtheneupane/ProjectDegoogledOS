package com.android.systemui;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settingslib.graph.CircleBatteryDrawable;
import com.android.settingslib.graph.FullCircleBatteryDrawable;
import com.android.settingslib.graph.ThemedBatteryDrawable;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.SysuiLifecycle;
import com.android.systemui.util.Utils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class BatteryMeterView extends LinearLayout implements BatteryController.BatteryStateChangeCallback, DarkIconDispatcher.DarkReceiver, ConfigurationController.ConfigurationListener {
    private BatteryController mBatteryController;
    private final ImageView mBatteryIconView;
    private TextView mBatteryPercentView;
    public int mBatteryStyle;
    private final ArrayList<BatteryMeterViewCallbacks> mCallbacks;
    private boolean mCharging;
    private final CircleBatteryDrawable mCircleDrawable;
    private DualToneHandler mDualToneHandler;
    private final FullCircleBatteryDrawable mFullCircleDrawable;
    private boolean mIsQsHeader;
    private int mLevel;
    private int mNonAdaptedBackgroundColor;
    private int mNonAdaptedForegroundColor;
    private int mNonAdaptedSingleToneColor;
    private final int mPercentageStyleId;
    /* access modifiers changed from: private */
    public SettingObserver mSettingObserver;
    public int mShowBatteryPercent;
    private int mShowPercentMode;
    private final String mSlotBattery;
    private int mTextColor;
    private final ThemedBatteryDrawable mThemedDrawable;
    private boolean mUseWallpaperTextColors;
    /* access modifiers changed from: private */
    public int mUser;
    private final CurrentUserTracker mUserTracker;

    public interface BatteryMeterViewCallbacks {
        void onHiddenBattery(boolean z) {
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public BatteryMeterView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowPercentMode = 0;
        this.mBatteryStyle = 0;
        this.mCallbacks = new ArrayList<>();
        setOrientation(0);
        setGravity(8388627);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BatteryMeterView, i, 0);
        int color = obtainStyledAttributes.getColor(R$styleable.BatteryMeterView_frameColor, context.getColor(C1774R$color.meter_background_color));
        this.mPercentageStyleId = obtainStyledAttributes.getResourceId(R$styleable.BatteryMeterView_textAppearance, 0);
        this.mThemedDrawable = new ThemedBatteryDrawable(context, color);
        this.mCircleDrawable = new CircleBatteryDrawable(context, color);
        this.mFullCircleDrawable = new FullCircleBatteryDrawable(context, color);
        obtainStyledAttributes.recycle();
        this.mSettingObserver = new SettingObserver(new Handler(context.getMainLooper()));
        addOnAttachStateChangeListener(new Utils.DisableStateTracker(0, 2));
        setupLayoutTransition();
        this.mSlotBattery = context.getString(17041145);
        this.mBatteryIconView = new ImageView(context);
        this.mBatteryIconView.setImageDrawable(this.mThemedDrawable);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(getResources().getDimensionPixelSize(C1775R$dimen.status_bar_battery_icon_width), getResources().getDimensionPixelSize(C1775R$dimen.status_bar_battery_icon_height));
        marginLayoutParams.setMargins(0, 0, 0, getResources().getDimensionPixelOffset(C1775R$dimen.battery_margin_bottom));
        addView(this.mBatteryIconView, marginLayoutParams);
        updateShowPercent();
        this.mDualToneHandler = new DualToneHandler(context);
        onDarkChanged(new Rect(), 0.0f, -1);
        this.mUserTracker = new CurrentUserTracker(this.mContext) {
            public void onUserSwitched(int i) {
                int unused = BatteryMeterView.this.mUser = i;
                BatteryMeterView.this.getContext().getContentResolver().unregisterContentObserver(BatteryMeterView.this.mSettingObserver);
                BatteryMeterView.this.updateShowPercent();
            }
        };
        setClipChildren(false);
        setClipToPadding(false);
        this.mSettingObserver.observe();
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).observe(SysuiLifecycle.viewAttachLifecycle(this), this);
    }

    private void setupLayoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setDuration(200);
        layoutTransition.setAnimator(2, ObjectAnimator.ofFloat((Object) null, "alpha", new float[]{0.0f, 1.0f}));
        layoutTransition.setInterpolator(2, Interpolators.ALPHA_IN);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object) null, "alpha", new float[]{1.0f, 0.0f});
        layoutTransition.setInterpolator(3, Interpolators.ALPHA_OUT);
        layoutTransition.setAnimator(3, ofFloat);
        setLayoutTransition(layoutTransition);
    }

    public void setForceShowPercent(boolean z) {
        setPercentShowMode(z ? 1 : 0);
    }

    public void setPercentShowMode(int i) {
        this.mShowPercentMode = i;
        updateShowPercent();
    }

    public void useWallpaperTextColor(boolean z) {
        if (z != this.mUseWallpaperTextColors) {
            this.mUseWallpaperTextColors = z;
            if (this.mUseWallpaperTextColors) {
                updateColors(com.android.settingslib.Utils.getColorAttrDefaultColor(this.mContext, C1772R$attr.wallpaperTextColor), com.android.settingslib.Utils.getColorAttrDefaultColor(this.mContext, C1772R$attr.wallpaperTextColorSecondary), com.android.settingslib.Utils.getColorAttrDefaultColor(this.mContext, C1772R$attr.wallpaperTextColor));
            } else {
                updateColors(this.mNonAdaptedForegroundColor, this.mNonAdaptedBackgroundColor, this.mNonAdaptedSingleToneColor);
            }
        }
    }

    public void setColorsFromContext(Context context) {
        if (context != null) {
            this.mDualToneHandler.setColorsFromContext(context);
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        updateSbBatteryStyle();
        updateSbShowBatteryPercent();
    }

    private void updateSbBatteryStyle() {
        this.mBatteryStyle = Settings.System.getInt(this.mContext.getContentResolver(), "status_bar_battery_style", 0);
        updateBatteryStyle();
        updateVisibility();
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            this.mCallbacks.get(i).onHiddenBattery(this.mBatteryStyle == 5);
        }
    }

    private void updateSbShowBatteryPercent() {
        int i = this.mBatteryStyle;
        if (i == 4) {
            this.mShowBatteryPercent = 2;
            updatePercentView();
        } else if (i != 5) {
            this.mShowBatteryPercent = Settings.System.getInt(this.mContext.getContentResolver(), "status_bar_show_battery_percent", 0);
            updatePercentView();
        } else {
            this.mShowBatteryPercent = 0;
            updatePercentView();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mBatteryController = (BatteryController) Dependency.get(BatteryController.class);
        this.mBatteryController.addCallback(this);
        this.mUser = ActivityManager.getCurrentUser();
        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor("battery_estimates_last_update_time"), false, this.mSettingObserver);
        updateShowPercent();
        updateSettings();
        this.mUserTracker.startTracking();
        this.mSettingObserver.observe();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mUserTracker.stopTracking();
        this.mBatteryController.removeCallback(this);
        getContext().getContentResolver().unregisterContentObserver(this.mSettingObserver);
    }

    public void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        if (this.mLevel != i) {
            this.mLevel = i;
            this.mThemedDrawable.setBatteryLevel(this.mLevel);
            this.mCircleDrawable.setBatteryLevel(this.mLevel);
            this.mFullCircleDrawable.setBatteryLevel(this.mLevel);
        }
        if (this.mCharging != z) {
            this.mCharging = z;
            this.mThemedDrawable.setCharging(this.mCharging);
            this.mCircleDrawable.setCharging(this.mCharging);
            this.mFullCircleDrawable.setCharging(this.mCharging);
            updateShowPercent();
            return;
        }
        updatePercentText();
    }

    public void onPowerSaveChanged(boolean z) {
        this.mThemedDrawable.setPowerSaveEnabled(z);
        this.mCircleDrawable.setPowerSaveEnabled(z);
        this.mFullCircleDrawable.setPowerSaveEnabled(z);
        updateShowPercent();
    }

    private TextView loadPercentView() {
        return (TextView) LayoutInflater.from(getContext()).inflate(C1779R$layout.battery_percentage_view, (ViewGroup) null);
    }

    public void updatePercentView() {
        updateShowPercent();
    }

    private void updatePercentText() {
        int i;
        BatteryController batteryController = this.mBatteryController;
        if (batteryController != null) {
            if (this.mBatteryPercentView == null) {
                Context context = getContext();
                if (this.mCharging) {
                    i = C1784R$string.accessibility_battery_level_charging;
                } else {
                    i = C1784R$string.accessibility_battery_level;
                }
                setContentDescription(context.getString(i, new Object[]{Integer.valueOf(this.mLevel)}));
            } else if (this.mShowPercentMode != 3 || this.mCharging) {
                setPercentTextAtCurrentLevel();
            } else {
                batteryController.getEstimatedTimeRemainingString(new BatteryController.EstimateFetchCompletion() {
                    public final void onBatteryRemainingEstimateRetrieved(String str) {
                        BatteryMeterView.this.lambda$updatePercentText$0$BatteryMeterView(str);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$updatePercentText$0$BatteryMeterView(String str) {
        if (str != null) {
            if (this.mBatteryPercentView != null) {
                batteryPercentViewSetText(str);
            }
            setContentDescription(getContext().getString(C1784R$string.accessibility_battery_level_with_estimate, new Object[]{Integer.valueOf(this.mLevel), str}));
            return;
        }
        setPercentTextAtCurrentLevel();
    }

    private void setPercentTextAtCurrentLevel() {
        String str;
        int i;
        if (this.mBatteryPercentView != null) {
            if (!this.mCharging || this.mBatteryStyle != 4) {
                str = "";
            } else {
                str = "⚡︎" + " ";
            }
            batteryPercentViewSetText(str + NumberFormat.getPercentInstance().format((double) (((float) this.mLevel) / 100.0f)));
            Context context = getContext();
            if (this.mCharging) {
                i = C1784R$string.accessibility_battery_level_charging;
            } else {
                i = C1784R$string.accessibility_battery_level;
            }
            setContentDescription(context.getString(i, new Object[]{Integer.valueOf(this.mLevel)}));
        }
    }

    private void removeBatteryPercentView() {
        TextView textView = this.mBatteryPercentView;
        if (textView != null) {
            removeView(textView);
            this.mBatteryPercentView = null;
        }
    }

    /* access modifiers changed from: private */
    public void updateShowPercent() {
        boolean z = true;
        boolean z2 = this.mBatteryPercentView != null;
        boolean z3 = this.mShowPercentMode == 0 && this.mShowBatteryPercent == 1;
        int i = this.mShowPercentMode;
        if (!(i == 3 || i == 1 || this.mShowBatteryPercent == 2)) {
            z = false;
        }
        if ((this.mIsQsHeader || this.mBatteryStyle != 5) && z && (!z3 || this.mCharging)) {
            this.mThemedDrawable.setShowPercent(false);
            this.mCircleDrawable.setShowPercent(false);
            this.mFullCircleDrawable.setShowPercent(false);
            if (!z2) {
                this.mBatteryPercentView = loadPercentView();
                int i2 = this.mPercentageStyleId;
                if (i2 != 0) {
                    this.mBatteryPercentView.setTextAppearance(i2);
                }
                int i3 = this.mTextColor;
                if (i3 != 0) {
                    this.mBatteryPercentView.setTextColor(i3);
                }
                addView(this.mBatteryPercentView, new ViewGroup.LayoutParams(-2, -1));
            }
            if (this.mBatteryStyle == 4) {
                this.mBatteryPercentView.setPaddingRelative(0, 0, 0, 0);
            } else {
                this.mBatteryPercentView.setPaddingRelative(getContext().getResources().getDimensionPixelSize(C1775R$dimen.battery_level_padding_start), 0, 0, 0);
            }
        } else {
            removeBatteryPercentView();
            this.mThemedDrawable.setShowPercent(z3);
            this.mCircleDrawable.setShowPercent(z3);
            this.mFullCircleDrawable.setShowPercent(z3);
        }
        updatePercentText();
    }

    public void setIsQsHeader(boolean z) {
        this.mIsQsHeader = z;
    }

    public void updateVisibility() {
        int i = this.mBatteryStyle;
        if (i == 4 || i == 5) {
            this.mBatteryIconView.setVisibility(8);
            this.mBatteryIconView.setImageDrawable((Drawable) null);
            return;
        }
        this.mBatteryIconView.setVisibility(0);
        scaleBatteryMeterViews();
    }

    private void batteryPercentViewSetText(CharSequence charSequence) {
        if (!this.mBatteryPercentView.getText().toString().equals(charSequence.toString())) {
            this.mBatteryPercentView.setText(charSequence);
        }
    }

    public void onDensityOrFontScaleChanged() {
        scaleBatteryMeterViews();
        updateSettings();
    }

    public void onOverlayChanged() {
        updateShowPercent();
        updateSettings();
    }

    private void scaleBatteryMeterViews() {
        int i;
        int i2;
        Resources resources = getContext().getResources();
        TypedValue typedValue = new TypedValue();
        resources.getValue(C1775R$dimen.status_bar_icon_scale_factor, typedValue, true);
        float f = typedValue.getFloat();
        int i3 = this.mBatteryStyle;
        if (i3 == 1 || i3 == 2 || i3 == 3) {
            i = resources.getDimensionPixelSize(C1775R$dimen.status_bar_battery_icon_circle_width);
        } else {
            i = resources.getDimensionPixelSize(C1775R$dimen.status_bar_battery_icon_height);
        }
        int i4 = this.mBatteryStyle;
        if (i4 == 1 || i4 == 2 || i4 == 3) {
            i2 = resources.getDimensionPixelSize(C1775R$dimen.status_bar_battery_icon_circle_width);
        } else {
            i2 = resources.getDimensionPixelSize(C1775R$dimen.status_bar_battery_icon_width);
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(C1775R$dimen.battery_margin_bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (((float) i2) * f), (int) (((float) i) * f));
        layoutParams.setMargins(0, 0, 0, dimensionPixelSize);
        this.mBatteryIconView.setLayoutParams(layoutParams);
    }

    public void updateBatteryStyle() {
        int i = this.mBatteryStyle;
        if (i == 0) {
            this.mBatteryIconView.setImageDrawable(this.mThemedDrawable);
        } else if (i == 3) {
            this.mBatteryIconView.setImageDrawable(this.mFullCircleDrawable);
        } else if (i != 4 && i != 5) {
            this.mCircleDrawable.setMeterStyle(i);
            this.mBatteryIconView.setImageDrawable(this.mCircleDrawable);
        }
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        if (!DarkIconDispatcher.isInArea(rect, this)) {
            f = 0.0f;
        }
        this.mNonAdaptedSingleToneColor = this.mDualToneHandler.getSingleColor(f);
        this.mNonAdaptedForegroundColor = this.mDualToneHandler.getFillColor(f);
        this.mNonAdaptedBackgroundColor = this.mDualToneHandler.getBackgroundColor(f);
        if (!this.mUseWallpaperTextColors) {
            updateColors(this.mNonAdaptedForegroundColor, this.mNonAdaptedBackgroundColor, this.mNonAdaptedSingleToneColor);
        }
    }

    private void updateColors(int i, int i2, int i3) {
        this.mThemedDrawable.setColors(i, i2, i3);
        this.mCircleDrawable.setColors(i, i2, i3);
        this.mFullCircleDrawable.setColors(i, i2, i3);
        this.mTextColor = i3;
        TextView textView = this.mBatteryPercentView;
        if (textView != null) {
            textView.setTextColor(i3);
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str;
        CharSequence charSequence = null;
        if (this.mThemedDrawable == null) {
            str = null;
        } else {
            str = this.mThemedDrawable.getPowerSaveEnabled() + "";
        }
        TextView textView = this.mBatteryPercentView;
        if (textView != null) {
            charSequence = textView.getText();
        }
        printWriter.println("  BatteryMeterView:");
        printWriter.println("    mThemedDrawable.getPowerSave: " + str);
        printWriter.println("    mBatteryPercentView.getText(): " + charSequence);
        printWriter.println("    mTextColor: #" + Integer.toHexString(this.mTextColor));
        printWriter.println("    mLevel: " + this.mLevel);
    }

    private final class SettingObserver extends ContentObserver {
        public SettingObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = BatteryMeterView.this.getContext().getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_battery_style"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_show_battery_percent"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            BatteryMeterView.this.updateShowPercent();
            BatteryMeterView.this.updateSettings();
        }
    }

    public void addCallback(BatteryMeterViewCallbacks batteryMeterViewCallbacks) {
        this.mCallbacks.add(batteryMeterViewCallbacks);
    }

    public void removeCallback(BatteryMeterViewCallbacks batteryMeterViewCallbacks) {
        this.mCallbacks.remove(batteryMeterViewCallbacks);
    }
}
