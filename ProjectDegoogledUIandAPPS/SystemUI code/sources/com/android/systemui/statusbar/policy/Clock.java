package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.DemoMode;
import com.android.systemui.Dependency;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.R$styleable;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import libcore.icu.LocaleData;

public class Clock extends TextView implements DemoMode, CommandQueue.Callbacks, DarkIconDispatcher.DarkReceiver, ConfigurationController.ConfigurationListener {
    /* access modifiers changed from: private */
    public Handler autoHideHandler;
    private int mAmPmStyle;
    protected boolean mAttached;
    protected Calendar mCalendar;
    /* access modifiers changed from: private */
    public boolean mClockAutoHide;
    protected int mClockDateDisplay;
    protected int mClockDatePosition;
    protected int mClockDateStyle;
    protected SimpleDateFormat mClockFormat;
    protected String mClockFormatString;
    protected boolean mClockHideableByUser;
    protected int mClockStyle;
    private boolean mClockVisibleByPolicy;
    private boolean mClockVisibleByUser;
    private SimpleDateFormat mContentDescriptionFormat;
    /* access modifiers changed from: private */
    public int mCurrentUserId;
    private final CurrentUserTracker mCurrentUserTracker;
    private boolean mDemoMode;
    private Handler mHandler;
    private int mHideDuration;
    private final BroadcastReceiver mIntentReceiver;
    /* access modifiers changed from: private */
    public Locale mLocale;
    private int mNonAdaptedColor;
    protected boolean mQsHeader;
    private final BroadcastReceiver mScreenReceiver;
    /* access modifiers changed from: private */
    public final Runnable mSecondTick;
    /* access modifiers changed from: private */
    public Handler mSecondsHandler;
    private SettingsObserver mSettingsObserver;
    protected boolean mShowClock;
    private final boolean mShowDark;
    private int mShowDuration;
    private boolean mShowSeconds;
    private boolean mUseWallpaperTextColor;

    protected class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = Clock.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_clock"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_style"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_clock_seconds"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_am_pm_style"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_date_display"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_date_style"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_date_format"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("statusbar_clock_date_position"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_clock_auto_hide"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_clock_auto_hide_hduration"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_clock_auto_hide_sduration"), false, this, -1);
            Clock.this.updateSettings();
        }

        public void onChange(boolean z) {
            Clock.this.updateSettings();
        }
    }

    public Clock(Context context) {
        this(context, (AttributeSet) null);
    }

    public Clock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    public Clock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClockVisibleByPolicy = true;
        this.mClockVisibleByUser = true;
        this.mClockHideableByUser = true;
        this.autoHideHandler = new Handler();
        this.mClockDateDisplay = 0;
        this.mClockDateStyle = 0;
        this.mClockStyle = 0;
        this.mShowClock = true;
        this.mHideDuration = 60;
        this.mShowDuration = 5;
        this.mHandler = new Handler();
        this.mIntentReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Handler handler = Clock.this.getHandler();
                if (handler == null) {
                    Log.e("StatusBarClock", "Received intent, but handler is null - still attached to window? Window token: " + Clock.this.getWindowToken());
                    return;
                }
                if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                    handler.post(new Runnable(intent.getStringExtra("time-zone")) {
                        private final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            Clock.C15162.this.lambda$onReceive$0$Clock$2(this.f$1);
                        }
                    });
                } else if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                    handler.post(new Runnable(Clock.this.getResources().getConfiguration().locale) {
                        private final /* synthetic */ Locale f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            Clock.C15162.this.lambda$onReceive$1$Clock$2(this.f$1);
                        }
                    });
                }
                handler.post(new Runnable() {
                    public final void run() {
                        Clock.C15162.this.lambda$onReceive$2$Clock$2();
                    }
                });
                if (Clock.this.mClockAutoHide) {
                    Clock.this.autoHideHandler.post(new Runnable() {
                        public final void run() {
                            Clock.C15162.this.lambda$onReceive$3$Clock$2();
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$onReceive$0$Clock$2(String str) {
                Clock.this.mCalendar = Calendar.getInstance(TimeZone.getTimeZone(str));
                Clock clock = Clock.this;
                SimpleDateFormat simpleDateFormat = clock.mClockFormat;
                if (simpleDateFormat != null) {
                    simpleDateFormat.setTimeZone(clock.mCalendar.getTimeZone());
                }
            }

            public /* synthetic */ void lambda$onReceive$1$Clock$2(Locale locale) {
                if (!locale.equals(Clock.this.mLocale)) {
                    Locale unused = Clock.this.mLocale = locale;
                }
                Clock.this.updateSettings();
            }

            public /* synthetic */ void lambda$onReceive$2$Clock$2() {
                Clock.this.updateClock();
            }

            public /* synthetic */ void lambda$onReceive$3$Clock$2() {
                Clock.this.lambda$autoHideClock$1$Clock();
            }
        };
        this.mScreenReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    if (Clock.this.mSecondsHandler != null) {
                        Clock.this.mSecondsHandler.removeCallbacks(Clock.this.mSecondTick);
                    }
                } else if ("android.intent.action.SCREEN_ON".equals(action) && Clock.this.mSecondsHandler != null) {
                    Clock.this.mSecondsHandler.postAtTime(Clock.this.mSecondTick, ((SystemClock.uptimeMillis() / 1000) * 1000) + 1000);
                }
            }
        };
        this.mSecondTick = new Runnable() {
            public void run() {
                Clock clock = Clock.this;
                if (clock.mCalendar != null) {
                    clock.updateClock();
                }
                Clock.this.mSecondsHandler.postAtTime(this, ((SystemClock.uptimeMillis() / 1000) * 1000) + 1000);
            }
        };
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.Clock, 0, 0);
        try {
            this.mAmPmStyle = obtainStyledAttributes.getInt(R$styleable.Clock_amPmStyle, 0);
            this.mShowDark = obtainStyledAttributes.getBoolean(R$styleable.Clock_showDark, true);
            this.mNonAdaptedColor = getCurrentTextColor();
            obtainStyledAttributes.recycle();
            this.mCurrentUserTracker = new CurrentUserTracker(context) {
                public void onUserSwitched(int i) {
                    int unused = Clock.this.mCurrentUserId = i;
                }
            };
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("clock_super_parcelable", super.onSaveInstanceState());
        bundle.putInt("current_user_id", this.mCurrentUserId);
        bundle.putBoolean("visible_by_policy", this.mClockVisibleByPolicy);
        bundle.putBoolean("visible_by_user", this.mClockVisibleByUser);
        bundle.putBoolean("show_seconds", this.mShowSeconds);
        bundle.putInt("visibility", getVisibility());
        bundle.putBoolean("qsheader", this.mQsHeader);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("clock_super_parcelable"));
        if (bundle.containsKey("current_user_id")) {
            this.mCurrentUserId = bundle.getInt("current_user_id");
        }
        this.mClockVisibleByPolicy = bundle.getBoolean("visible_by_policy", true);
        this.mClockVisibleByUser = bundle.getBoolean("visible_by_user", true);
        this.mShowSeconds = bundle.getBoolean("show_seconds", false);
        if (bundle.containsKey("visibility")) {
            super.setVisibility(bundle.getInt("visibility"));
        }
        this.mQsHeader = bundle.getBoolean("qsheader", false);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
            intentFilter.addAction("android.intent.action.USER_SWITCHED");
            getContext().registerReceiverAsUser(this.mIntentReceiver, UserHandle.ALL, intentFilter, (String) null, (Handler) Dependency.get(Dependency.TIME_TICK_HANDLER));
            ((CommandQueue) SysUiServiceProvider.getComponent(getContext(), CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
            if (this.mShowDark) {
                ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
            }
            this.mCurrentUserTracker.startTracking();
            this.mCurrentUserId = this.mCurrentUserTracker.getCurrentUserId();
        }
        this.mCalendar = Calendar.getInstance(TimeZone.getDefault());
        if (this.mSettingsObserver == null) {
            this.mSettingsObserver = new SettingsObserver(new Handler());
        }
        this.mSettingsObserver.observe();
        updateSettings();
        updateShowSeconds();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            getContext().unregisterReceiver(this.mIntentReceiver);
            getContext().getContentResolver().unregisterContentObserver(this.mSettingsObserver);
            this.mAttached = false;
            ((CommandQueue) SysUiServiceProvider.getComponent(getContext(), CommandQueue.class)).removeCallback((CommandQueue.Callbacks) this);
            if (this.mShowDark) {
                ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
            }
            this.mCurrentUserTracker.stopTracking();
        }
    }

    public void setVisibility(int i) {
        if (i != 0 || shouldBeVisible()) {
            super.setVisibility(i);
        }
    }

    public void setClockVisibilityByPolicy(boolean z) {
        this.mClockVisibleByPolicy = z;
        lambda$autoHideClock$1$Clock();
    }

    private boolean shouldBeVisible() {
        return this.mClockVisibleByPolicy && this.mClockVisibleByUser;
    }

    /* access modifiers changed from: protected */
    /* renamed from: updateClockVisibility */
    public void lambda$autoHideClock$1$Clock() {
        int i = 0;
        boolean z = (this.mClockStyle == 0 || this.mQsHeader) && this.mShowClock && this.mClockVisibleByPolicy && this.mClockVisibleByUser;
        if (!z) {
            i = 8;
        }
        try {
            this.autoHideHandler.removeCallbacksAndMessages((Object) null);
        } catch (NullPointerException unused) {
        }
        super.setVisibility(i);
        if (!this.mQsHeader && this.mClockAutoHide && z) {
            this.autoHideHandler.postDelayed(new Runnable() {
                public final void run() {
                    Clock.this.lambda$updateClockVisibility$0$Clock();
                }
            }, (long) (this.mShowDuration * 1000));
        }
    }

    public boolean isClockVisible() {
        return this.mClockVisibleByPolicy && this.mClockVisibleByUser;
    }

    /* access modifiers changed from: private */
    /* renamed from: autoHideClock */
    public void lambda$updateClockVisibility$0$Clock() {
        setVisibility(8);
        this.autoHideHandler.postDelayed(new Runnable() {
            public final void run() {
                Clock.this.lambda$autoHideClock$1$Clock();
            }
        }, (long) (this.mHideDuration * 1000));
    }

    /* access modifiers changed from: package-private */
    public final void updateClock() {
        if (!this.mDemoMode) {
            this.mCalendar.setTimeInMillis(System.currentTimeMillis());
            setText(getSmallTime());
            setContentDescription(this.mContentDescriptionFormat.format(this.mCalendar.getTime()));
        }
    }

    public void disable(int i, int i2, int i3, boolean z) {
        if (i == getDisplay().getDisplayId()) {
            boolean z2 = (8388608 & i2) == 0;
            if (z2 != this.mClockVisibleByPolicy) {
                setClockVisibilityByPolicy(z2);
            }
        }
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        this.mNonAdaptedColor = DarkIconDispatcher.getTint(rect, this, i);
        if (!this.mUseWallpaperTextColor) {
            setTextColor(this.mNonAdaptedColor);
        }
    }

    public void onDensityOrFontScaleChanged() {
        FontSizeUtils.updateFontSize(this, C1775R$dimen.status_bar_clock_size);
        setPaddingRelative(this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_clock_starting_padding), 0, this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.status_bar_clock_end_padding), 0);
    }

    public void useWallpaperTextColor(boolean z) {
        if (z != this.mUseWallpaperTextColor) {
            this.mUseWallpaperTextColor = z;
            if (this.mUseWallpaperTextColor) {
                setTextColor(Utils.getColorAttr(this.mContext, C1772R$attr.wallpaperTextColor));
            } else {
                setTextColor(this.mNonAdaptedColor);
            }
        }
    }

    private void updateShowSeconds() {
        if (this.mShowSeconds) {
            if (this.mSecondsHandler == null && getDisplay() != null) {
                this.mSecondsHandler = new Handler();
                if (getDisplay().getState() == 2) {
                    this.mSecondsHandler.postAtTime(this.mSecondTick, ((SystemClock.uptimeMillis() / 1000) * 1000) + 1000);
                }
                IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                this.mContext.registerReceiver(this.mScreenReceiver, intentFilter);
            }
        } else if (this.mSecondsHandler != null) {
            this.mContext.unregisterReceiver(this.mScreenReceiver);
            this.mSecondsHandler.removeCallbacks(this.mSecondTick);
            this.mSecondsHandler = null;
            updateClock();
        }
    }

    private final CharSequence getSmallTime() {
        String str;
        SimpleDateFormat simpleDateFormat;
        String str2;
        String str3;
        Context context = getContext();
        boolean is24HourFormat = DateFormat.is24HourFormat(context, this.mCurrentUserId);
        LocaleData localeData = LocaleData.get(context.getResources().getConfiguration().locale);
        if (this.mShowSeconds) {
            str = is24HourFormat ? localeData.timeFormat_Hms : localeData.timeFormat_hms;
        } else {
            str = is24HourFormat ? localeData.timeFormat_Hm : localeData.timeFormat_hm;
        }
        if (!str.equals(this.mClockFormatString)) {
            this.mContentDescriptionFormat = new SimpleDateFormat(str);
            if (this.mAmPmStyle != 2) {
                int i = 0;
                boolean z = false;
                while (true) {
                    if (i >= str.length()) {
                        i = -1;
                        break;
                    }
                    char charAt = str.charAt(i);
                    if (charAt == '\'') {
                        z = !z;
                    }
                    if (!z && charAt == 'a') {
                        break;
                    }
                    i++;
                }
                if (i >= 0) {
                    int i2 = i;
                    while (i2 > 0 && Character.isWhitespace(str.charAt(i2 - 1))) {
                        i2--;
                    }
                    str = str.substring(0, i2) + 61184 + str.substring(i2, i) + "a" + 61185 + str.substring(i + 1);
                }
            }
            simpleDateFormat = new SimpleDateFormat(str);
            this.mClockFormat = simpleDateFormat;
            this.mClockFormatString = str;
        } else {
            simpleDateFormat = this.mClockFormat;
        }
        CharSequence charSequence = null;
        String format = simpleDateFormat.format(this.mCalendar.getTime());
        if (this.mQsHeader || this.mClockDateDisplay == 0) {
            str2 = format;
        } else {
            Date date = new Date();
            String string = Settings.System.getString(getContext().getContentResolver(), "statusbar_clock_date_format");
            if (string == null || string.isEmpty()) {
                charSequence = DateFormat.format("EEE", date);
            } else {
                charSequence = DateFormat.format(string, date);
            }
            int i3 = this.mClockDateStyle;
            if (i3 == 1) {
                str3 = charSequence.toString().toLowerCase();
            } else if (i3 == 2) {
                str3 = charSequence.toString().toUpperCase();
            } else {
                str3 = charSequence.toString();
            }
            if (this.mClockDatePosition == 0) {
                str2 = str3 + " " + format;
            } else {
                str2 = format + " " + str3;
            }
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        if (!(this.mClockDateDisplay == 2 || charSequence == null)) {
            int length = charSequence.length();
            int length2 = this.mClockDatePosition == 1 ? format.length() + 1 : 0;
            int i4 = this.mClockDateDisplay;
            if (i4 == 0) {
                spannableStringBuilder.delete(0, length);
            } else if (i4 == 1) {
                spannableStringBuilder.setSpan(new RelativeSizeSpan(0.7f), length2, length + length2, 34);
            }
        }
        if (this.mAmPmStyle != 2) {
            int indexOf = str2.indexOf(61184);
            int indexOf2 = str2.indexOf(61185);
            if (indexOf >= 0 && indexOf2 > indexOf) {
                int i5 = this.mAmPmStyle;
                if (i5 == 0) {
                    spannableStringBuilder.delete(indexOf, indexOf2 + 1);
                } else {
                    if (i5 == 1) {
                        spannableStringBuilder.setSpan(new RelativeSizeSpan(0.7f), indexOf, indexOf2, 34);
                    }
                    spannableStringBuilder.delete(indexOf2, indexOf2 + 1);
                    spannableStringBuilder.delete(indexOf, indexOf + 1);
                }
            }
        }
        return spannableStringBuilder;
    }

    /* access modifiers changed from: protected */
    public void updateSettings() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z = true;
        this.mShowClock = Settings.System.getIntForUser(contentResolver, "status_bar_clock", 1, -2) == 1;
        if (this.mQsHeader) {
            this.mShowClock = true;
        }
        this.mShowSeconds = Settings.System.getIntForUser(contentResolver, "status_bar_clock_seconds", 0, -2) == 1;
        if (!this.mShowClock) {
            this.mClockStyle = 1;
        } else {
            this.mClockStyle = Settings.System.getIntForUser(contentResolver, "statusbar_clock_style", 0, -2);
        }
        boolean is24HourFormat = DateFormat.is24HourFormat(this.mContext);
        int intForUser = Settings.System.getIntForUser(contentResolver, "statusbar_clock_am_pm_style", 0, -2);
        if (is24HourFormat) {
            intForUser = 0;
        }
        this.mAmPmStyle = intForUser;
        this.mClockFormatString = "";
        this.mClockDateDisplay = Settings.System.getIntForUser(contentResolver, "statusbar_clock_date_display", 0, -2);
        this.mClockDateStyle = Settings.System.getIntForUser(contentResolver, "statusbar_clock_date_style", 0, -2);
        this.mClockDatePosition = Settings.System.getIntForUser(contentResolver, "statusbar_clock_date_position", 0, -2);
        if (Settings.System.getIntForUser(contentResolver, "status_bar_clock_auto_hide", 0, -2) != 1) {
            z = false;
        }
        this.mClockAutoHide = z;
        this.mHideDuration = Settings.System.getIntForUser(contentResolver, "status_bar_clock_auto_hide_hduration", 60, -2);
        this.mShowDuration = Settings.System.getIntForUser(contentResolver, "status_bar_clock_auto_hide_sduration", 5, -2);
        if (this.mAttached) {
            lambda$autoHideClock$1$Clock();
            updateClock();
            updateShowSeconds();
        }
    }

    public void setQsHeader() {
        this.mQsHeader = true;
    }

    public void dispatchDemoCommand(String str, Bundle bundle) {
        if (!this.mDemoMode && str.equals("enter")) {
            this.mDemoMode = true;
        } else if (this.mDemoMode && str.equals("exit")) {
            this.mDemoMode = false;
            updateClock();
        } else if (this.mDemoMode && str.equals("clock")) {
            String string = bundle.getString("millis");
            String string2 = bundle.getString("hhmm");
            if (string != null) {
                this.mCalendar.setTimeInMillis(Long.parseLong(string));
            } else if (string2 != null && string2.length() == 4) {
                int parseInt = Integer.parseInt(string2.substring(0, 2));
                int parseInt2 = Integer.parseInt(string2.substring(2));
                if (DateFormat.is24HourFormat(getContext(), this.mCurrentUserId)) {
                    this.mCalendar.set(11, parseInt);
                } else {
                    this.mCalendar.set(10, parseInt);
                }
                this.mCalendar.set(12, parseInt2);
            }
            setText(getSmallTime());
            setContentDescription(this.mContentDescriptionFormat.format(this.mCalendar.getTime()));
        }
    }
}
