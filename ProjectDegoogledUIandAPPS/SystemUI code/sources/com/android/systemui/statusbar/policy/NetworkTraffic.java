package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import java.text.DecimalFormat;

public class NetworkTraffic extends TextView {
    /* access modifiers changed from: private */
    public long lastUpdateTime;
    private boolean mAttached;
    /* access modifiers changed from: private */
    public int mAutoHideThreshold;
    private ConnectivityManager mConnectivityManager;
    /* access modifiers changed from: private */
    public int mIndicatorMode;
    private final BroadcastReceiver mIntentReceiver;
    protected boolean mIsEnabled;
    protected int mLocation;
    /* access modifiers changed from: private */
    public int mRefreshInterval;
    /* access modifiers changed from: private */
    public Runnable mRunnable;
    /* access modifiers changed from: private */
    public boolean mScreenOn;
    protected int mTintColor;
    /* access modifiers changed from: private */
    public Handler mTrafficHandler;
    protected boolean mVisible;
    /* access modifiers changed from: private */
    public long totalRxBytes;
    /* access modifiers changed from: private */
    public long totalTxBytes;

    /* access modifiers changed from: protected */
    public boolean restoreViewQuickly() {
        return getConnectAvailable() && this.mAutoHideThreshold == 0;
    }

    /* access modifiers changed from: protected */
    public void makeVisible() {
        int i = 0;
        boolean z = true;
        if (this.mLocation != 1) {
            z = false;
        }
        if (!z) {
            i = 8;
        }
        setVisibility(i);
        this.mVisible = z;
    }

    public NetworkTraffic(Context context) {
        this(context, (AttributeSet) null);
    }

    public NetworkTraffic(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkTraffic(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRefreshInterval = 1;
        this.mIndicatorMode = 0;
        this.mScreenOn = true;
        this.mVisible = true;
        this.mTrafficHandler = new Handler() {
            public void handleMessage(Message message) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - NetworkTraffic.this.lastUpdateTime;
                if (((double) elapsedRealtime) < ((double) (NetworkTraffic.this.mRefreshInterval * 1000)) * 0.95d) {
                    if (message.what == 1) {
                        if (elapsedRealtime < 1) {
                            elapsedRealtime = Long.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                }
                long j = elapsedRealtime;
                long unused = NetworkTraffic.this.lastUpdateTime = SystemClock.elapsedRealtime();
                long totalRxBytes = TrafficStats.getTotalRxBytes();
                long totalTxBytes = TrafficStats.getTotalTxBytes();
                long access$200 = totalRxBytes - NetworkTraffic.this.totalRxBytes;
                long access$300 = totalTxBytes - NetworkTraffic.this.totalTxBytes;
                if (shouldHide(access$200, access$300, j)) {
                    NetworkTraffic.this.setText("");
                    NetworkTraffic.this.setVisibility(8);
                    NetworkTraffic.this.mVisible = false;
                } else if (shouldShowUpload(access$200, access$300, j)) {
                    CharSequence formatOutput = formatOutput(j, access$300, "/S");
                    if (formatOutput != NetworkTraffic.this.getText()) {
                        NetworkTraffic.this.setText(formatOutput);
                    }
                    NetworkTraffic.this.makeVisible();
                } else {
                    CharSequence formatOutput2 = formatOutput(j, access$200, "/S");
                    if (formatOutput2 != NetworkTraffic.this.getText()) {
                        NetworkTraffic.this.setText(formatOutput2);
                    }
                    NetworkTraffic.this.makeVisible();
                }
                long unused2 = NetworkTraffic.this.totalRxBytes = totalRxBytes;
                long unused3 = NetworkTraffic.this.totalTxBytes = totalTxBytes;
                NetworkTraffic.this.clearHandlerCallbacks();
                NetworkTraffic.this.mTrafficHandler.postDelayed(NetworkTraffic.this.mRunnable, (long) (NetworkTraffic.this.mRefreshInterval * 1000));
            }

            private CharSequence formatOutput(long j, long j2, String str) {
                return formatDecimal((long) (((float) j2) / (((float) j) / 1000.0f)));
            }

            private CharSequence formatDecimal(long j) {
                String str;
                String str2 = "KB";
                if (j >= 1073741824) {
                    str = new DecimalFormat("0.00").format((double) (((float) j) / 1.07374182E9f));
                    str2 = "GB";
                } else {
                    if (j >= 104857600) {
                        str = new DecimalFormat("000").format((double) (((float) j) / 1048576.0f));
                    } else if (j >= 10485760) {
                        str = new DecimalFormat("00.0").format((double) (((float) j) / 1048576.0f));
                    } else if (j >= 1048576) {
                        str = new DecimalFormat("0.00").format((double) (((float) j) / 1048576.0f));
                    } else if (j >= 102400) {
                        str = new DecimalFormat("000").format((double) (((float) j) / 1024.0f));
                    } else if (j >= 10240) {
                        str = new DecimalFormat("00.0").format((double) (((float) j) / 1024.0f));
                    } else {
                        str = new DecimalFormat("0.00").format((double) (((float) j) / 1024.0f));
                    }
                    str2 = "MB";
                }
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(NetworkTraffic.this.getSpeedRelativeSizeSpan(), 0, str.length(), 18);
                SpannableString spannableString2 = new SpannableString(str2 + "/S");
                RelativeSizeSpan unitRelativeSizeSpan = NetworkTraffic.this.getUnitRelativeSizeSpan();
                spannableString2.setSpan(unitRelativeSizeSpan, 0, (str2 + "/S").length(), 18);
                return TextUtils.concat(new CharSequence[]{spannableString, "\n", spannableString2});
            }

            private boolean shouldHide(long j, long j2, long j3) {
                float f = ((float) j3) / 1000.0f;
                return !NetworkTraffic.this.getConnectAvailable() || (((long) (((float) j) / f)) / 1024 < ((long) NetworkTraffic.this.mAutoHideThreshold) && ((long) (((float) j2) / f)) / 1024 < ((long) NetworkTraffic.this.mAutoHideThreshold));
            }

            private boolean shouldShowUpload(long j, long j2, long j3) {
                float f = ((float) j3) / 1000.0f;
                return NetworkTraffic.this.mIndicatorMode == 0 ? ((long) (((float) j2) / f)) / 1024 > ((long) (((float) j) / f)) / 1024 : NetworkTraffic.this.mIndicatorMode == 2;
            }
        };
        this.mRunnable = new Runnable() {
            public void run() {
                NetworkTraffic.this.mTrafficHandler.sendEmptyMessage(0);
            }
        };
        this.mIntentReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null) {
                    if (action.equals("android.net.conn.CONNECTIVITY_CHANGE") && NetworkTraffic.this.mScreenOn) {
                        NetworkTraffic.this.update();
                    } else if (action.equals("android.intent.action.SCREEN_ON")) {
                        boolean unused = NetworkTraffic.this.mScreenOn = true;
                        NetworkTraffic.this.update();
                    } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                        boolean unused2 = NetworkTraffic.this.mScreenOn = false;
                        NetworkTraffic.this.clearHandlerCallbacks();
                    }
                }
            }
        };
        this.mTintColor = getResources().getColor(17170443);
        setMode();
        Handler handler = new Handler();
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        new SettingsObserver(handler).observe();
        update();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            this.mContext.registerReceiver(this.mIntentReceiver, intentFilter, (String) null, getHandler());
        }
        update();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            this.mContext.unregisterReceiver(this.mIntentReceiver);
            this.mAttached = false;
        }
    }

    /* access modifiers changed from: protected */
    public RelativeSizeSpan getSpeedRelativeSizeSpan() {
        return new RelativeSizeSpan(0.7f);
    }

    /* access modifiers changed from: protected */
    public RelativeSizeSpan getUnitRelativeSizeSpan() {
        return new RelativeSizeSpan(0.65f);
    }

    class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = NetworkTraffic.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("network_traffic_state"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("network_traffic_location"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("network_traffic_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("network_traffic_autohide_threshold"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("network_traffic_refresh_interval"), false, this, -1);
        }

        public void onChange(boolean z) {
            NetworkTraffic.this.setMode();
            NetworkTraffic.this.update();
        }
    }

    /* access modifiers changed from: private */
    public boolean getConnectAvailable() {
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        return (connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null) != null;
    }

    /* access modifiers changed from: protected */
    public void update() {
        if (!this.mIsEnabled) {
            clearHandlerCallbacks();
            setVisibility(8);
            this.mVisible = false;
        } else if (this.mAttached) {
            this.totalRxBytes = TrafficStats.getTotalRxBytes();
            this.lastUpdateTime = SystemClock.elapsedRealtime();
            this.mTrafficHandler.sendEmptyMessage(1);
        }
    }

    /* access modifiers changed from: protected */
    public void setMode() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mIsEnabled = Settings.System.getIntForUser(contentResolver, "network_traffic_state", 0, -2) == 1;
        this.mLocation = Settings.System.getIntForUser(contentResolver, "network_traffic_location", 0, -2);
        this.mIndicatorMode = Settings.System.getIntForUser(contentResolver, "network_traffic_mode", 0, -2);
        this.mAutoHideThreshold = Settings.System.getIntForUser(contentResolver, "network_traffic_autohide_threshold", 0, -2);
        this.mRefreshInterval = Settings.System.getIntForUser(contentResolver, "network_traffic_refresh_interval", 1, -2);
        setGravity(17);
        setMaxLines(2);
        setSpacingAndFonts();
        updateTrafficDrawable();
        setVisibility(8);
        this.mVisible = false;
    }

    /* access modifiers changed from: private */
    public void clearHandlerCallbacks() {
        this.mTrafficHandler.removeCallbacks(this.mRunnable);
        this.mTrafficHandler.removeMessages(0);
        this.mTrafficHandler.removeMessages(1);
    }

    /* access modifiers changed from: protected */
    public void updateTrafficDrawable() {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        setTextColor(this.mTintColor);
    }

    /* access modifiers changed from: protected */
    public void setSpacingAndFonts() {
        setTypeface(Typeface.create(getResources().getString(17039781), 1));
        setLineSpacing(0.75f, 0.75f);
    }
}
