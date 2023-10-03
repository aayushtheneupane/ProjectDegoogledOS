package com.android.systemui.p006qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.SystemClock;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.phone.StatusBar;

/* renamed from: com.android.systemui.qs.tiles.CaffeineTile */
public class CaffeineTile extends QSTileImpl<QSTile.BooleanState> {
    private static int[] DURATIONS = {StatusBar.FADE_KEYGUARD_DURATION, 600, 1800, -1};
    private CountDownTimer mCountdownTimer = null;
    private int mDuration;
    private final QSTile.Icon mIcon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_caffeine);
    public long mLastClickTime = -1;
    private final Receiver mReceiver = new Receiver();
    /* access modifiers changed from: private */
    public int mSecondsRemaining;
    /* access modifiers changed from: private */
    public final PowerManager.WakeLock mWakeLock = ((PowerManager) this.mContext.getSystemService("power")).newWakeLock(26, "CaffeineTile");

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public CaffeineTile(QSHost qSHost) {
        super(qSHost);
        this.mReceiver.init();
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    /* access modifiers changed from: protected */
    public void handleDestroy() {
        super.handleDestroy();
        stopCountDown();
        this.mReceiver.destroy();
        if (this.mWakeLock.isHeld()) {
            this.mWakeLock.release();
        }
    }

    public void handleClick() {
        if (this.mWakeLock.isHeld() && this.mLastClickTime != -1 && SystemClock.elapsedRealtime() - this.mLastClickTime < 5000) {
            this.mDuration++;
            int i = this.mDuration;
            int[] iArr = DURATIONS;
            if (i >= iArr.length) {
                this.mDuration = -1;
                stopCountDown();
                if (this.mWakeLock.isHeld()) {
                    this.mWakeLock.release();
                }
            } else {
                startCountDown((long) iArr[i]);
                if (!this.mWakeLock.isHeld()) {
                    this.mWakeLock.acquire();
                }
            }
        } else if (this.mWakeLock.isHeld()) {
            this.mWakeLock.release();
            stopCountDown();
        } else {
            this.mWakeLock.acquire();
            this.mDuration = 0;
            startCountDown((long) DURATIONS[this.mDuration]);
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        refreshState();
    }

    /* access modifiers changed from: protected */
    public void handleLongClick() {
        handleClick();
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_caffeine_label);
    }

    private void startCountDown(long j) {
        stopCountDown();
        this.mSecondsRemaining = (int) j;
        if (j != -1) {
            this.mCountdownTimer = new CountDownTimer(j * 1000, 1000) {
                public void onTick(long j) {
                    int unused = CaffeineTile.this.mSecondsRemaining = (int) (j / 1000);
                    CaffeineTile.this.refreshState();
                }

                public void onFinish() {
                    if (CaffeineTile.this.mWakeLock.isHeld()) {
                        CaffeineTile.this.mWakeLock.release();
                    }
                    CaffeineTile.this.refreshState();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void stopCountDown() {
        CountDownTimer countDownTimer = this.mCountdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mCountdownTimer = null;
        }
    }

    private String formatValueWithRemainingTime() {
        int i = this.mSecondsRemaining;
        if (i == -1) {
            return "∞";
        }
        return String.format("%02d:%02d", new Object[]{Integer.valueOf((i / 60) % 60), Integer.valueOf(this.mSecondsRemaining % 60)});
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (this.mWakeLock != null) {
            if (booleanState.slash == null) {
                booleanState.slash = new QSTile.SlashState();
            }
            booleanState.label = this.mContext.getString(C1784R$string.quick_settings_caffeine_label);
            booleanState.icon = this.mIcon;
            booleanState.value = this.mWakeLock.isHeld();
            if (booleanState.value) {
                booleanState.secondaryLabel = formatValueWithRemainingTime();
                booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_caffeine_on);
                booleanState.state = 2;
                return;
            }
            booleanState.secondaryLabel = this.mContext.getString(C1784R$string.quick_settings_caffeine_label_off);
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_caffeine_off);
            booleanState.state = 1;
        }
    }

    /* renamed from: com.android.systemui.qs.tiles.CaffeineTile$Receiver */
    private final class Receiver extends BroadcastReceiver {
        private Receiver() {
        }

        public void init() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            CaffeineTile.this.mContext.registerReceiver(this, intentFilter, (String) null, CaffeineTile.this.mHandler);
        }

        public void destroy() {
            CaffeineTile.this.mContext.unregisterReceiver(this);
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                CaffeineTile.this.stopCountDown();
                if (CaffeineTile.this.mWakeLock.isHeld()) {
                    CaffeineTile.this.mWakeLock.release();
                }
                CaffeineTile.this.refreshState();
            }
        }
    }
}
