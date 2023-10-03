package com.android.systemui.doze;

import android.app.AlarmManager;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.display.AmbientDisplayConfiguration;
import android.metrics.LogMaker;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.Preconditions;
import com.android.systemui.Dependency;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.DozeHost;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.DozeSensors;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.util.Assert;
import com.android.systemui.util.ProximitySensor;
import com.android.systemui.util.wakelock.WakeLock;
import java.io.PrintWriter;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class DozeTriggers implements DozeMachine.Part {
    private static final boolean DEBUG = DozeService.DEBUG;
    private static boolean sWakeDisplaySensorState = true;
    private final boolean mAllowPulseTriggers;
    private final TriggerReceiver mBroadcastReceiver = new TriggerReceiver();
    private final AmbientDisplayConfiguration mConfig;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final DockEventListener mDockEventListener = new DockEventListener();
    private final DockManager mDockManager;
    /* access modifiers changed from: private */
    public final DozeHost mDozeHost;
    private final DozeParameters mDozeParameters;
    /* access modifiers changed from: private */
    public final DozeSensors mDozeSensors;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private DozeHost.Callback mHostCallback = new DozeHost.Callback() {
        public void onNotificationAlerted(Runnable runnable) {
            DozeTriggers.this.onNotification(runnable);
        }

        public void onPowerSaveChanged(boolean z) {
            if (DozeTriggers.this.mDozeHost.isPowerSaveActive()) {
                DozeTriggers.this.mMachine.requestState(DozeMachine.State.DOZE);
            }
        }

        public void wakeUpFromDoubleTap(int i) {
            DozeTriggers.this.gentleWakeUp(i);
        }

        public void toggleFlashlightProximityCheck() {
            DozeTriggers.this.tryToggleFlashlight();
        }
    };
    /* access modifiers changed from: private */
    public final DozeMachine mMachine;
    private final MetricsLogger mMetricsLogger = ((MetricsLogger) Dependency.get(MetricsLogger.class));
    private long mNotificationPulseTime;
    private boolean mPulsePending;
    /* access modifiers changed from: private */
    public final SensorManager mSensorManager;
    private final UiModeManager mUiModeManager;
    /* access modifiers changed from: private */
    public final WakeLock mWakeLock;

    public DozeTriggers(Context context, DozeMachine dozeMachine, DozeHost dozeHost, AlarmManager alarmManager, AmbientDisplayConfiguration ambientDisplayConfiguration, DozeParameters dozeParameters, SensorManager sensorManager, Handler handler, WakeLock wakeLock, boolean z, DockManager dockManager) {
        this.mContext = context;
        this.mMachine = dozeMachine;
        this.mDozeHost = dozeHost;
        AmbientDisplayConfiguration ambientDisplayConfiguration2 = ambientDisplayConfiguration;
        this.mConfig = ambientDisplayConfiguration2;
        DozeParameters dozeParameters2 = dozeParameters;
        this.mDozeParameters = dozeParameters2;
        this.mSensorManager = sensorManager;
        this.mHandler = handler;
        WakeLock wakeLock2 = wakeLock;
        this.mWakeLock = wakeLock2;
        this.mAllowPulseTriggers = z;
        this.mDozeSensors = new DozeSensors(context, alarmManager, this.mSensorManager, dozeParameters2, ambientDisplayConfiguration2, wakeLock2, new DozeSensors.Callback() {
            public final void onSensorPulse(int i, float f, float f2, float[] fArr) {
                DozeTriggers.this.onSensor(i, f, f2, fArr);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                DozeTriggers.this.onProximityFar(((Boolean) obj).booleanValue());
            }
        }, dozeParameters.getPolicy());
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService(UiModeManager.class);
        this.mDockManager = dockManager;
    }

    /* access modifiers changed from: private */
    public void onNotification(Runnable runnable) {
        if (DozeMachine.DEBUG) {
            Log.d("DozeTriggers", "requestNotificationPulse");
        }
        if (!sWakeDisplaySensorState) {
            Log.d("DozeTriggers", "Wake display false. Pulse denied.");
            runIfNotNull(runnable);
            DozeLog.tracePulseDropped(this.mContext, "wakeDisplaySensor");
            return;
        }
        this.mNotificationPulseTime = SystemClock.elapsedRealtime();
        if (!this.mConfig.pulseOnNotificationEnabled(-2)) {
            runIfNotNull(runnable);
            DozeLog.tracePulseDropped(this.mContext, "pulseOnNotificationsDisabled");
            return;
        }
        requestPulse(1, false, runnable);
        DozeLog.traceNotificationPulse(this.mContext);
    }

    private static void runIfNotNull(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    private void proximityCheckThenCall(IntConsumer intConsumer, boolean z, int i) {
        Boolean isProximityCurrentlyFar = this.mDozeSensors.isProximityCurrentlyFar();
        if (z) {
            intConsumer.accept(3);
        } else if (isProximityCurrentlyFar != null) {
            intConsumer.accept(isProximityCurrentlyFar.booleanValue() ? 2 : 1);
        } else {
            final long uptimeMillis = SystemClock.uptimeMillis();
            final int i2 = i;
            final IntConsumer intConsumer2 = intConsumer;
            new ProximityCheck() {
                public void onProximityResult(int i) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    Context access$300 = DozeTriggers.this.mContext;
                    boolean z = true;
                    if (i != 1) {
                        z = false;
                    }
                    DozeLog.traceProximityResult(access$300, z, uptimeMillis - uptimeMillis, i2);
                    intConsumer2.accept(i);
                }
            }.check();
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void onSensor(int i, float f, float f2, float[] fArr) {
        boolean z = false;
        boolean z2 = i == 4;
        boolean z3 = i == 9;
        boolean z4 = i == 3;
        boolean z5 = i == 5;
        boolean z6 = i == 7;
        boolean z7 = i == 8;
        boolean z8 = (fArr == null || fArr.length <= 0 || fArr[0] == 0.0f) ? false : true;
        DozeMachine.State state = null;
        if (z6) {
            if (!this.mMachine.isExecutingTransition()) {
                state = this.mMachine.getState();
            }
            onWakeScreen(z8, state);
        } else if (z5) {
            requestPulse(i, true, (Runnable) null);
        } else if (!z7) {
            proximityCheckThenCall(new IntConsumer(z2, z3, f, f2, i, z4) {
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ boolean f$2;
                private final /* synthetic */ float f$3;
                private final /* synthetic */ float f$4;
                private final /* synthetic */ int f$5;
                private final /* synthetic */ boolean f$6;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                    this.f$5 = r6;
                    this.f$6 = r7;
                }

                public final void accept(int i) {
                    DozeTriggers.this.lambda$onSensor$0$DozeTriggers(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, i);
                }
            }, true, i);
        } else if (z8) {
            requestPulse(i, true, (Runnable) null);
        }
        if (z4) {
            if (SystemClock.elapsedRealtime() - this.mNotificationPulseTime < ((long) this.mDozeParameters.getPickupVibrationThreshold())) {
                z = true;
            }
            DozeLog.tracePickupWakeUp(this.mContext, z);
        }
    }

    public /* synthetic */ void lambda$onSensor$0$DozeTriggers(boolean z, boolean z2, float f, float f2, int i, boolean z3, int i2) {
        if (i2 != 1) {
            if (z || z2) {
                if (f != -1.0f && f2 != -1.0f) {
                    this.mDozeHost.onSlpiTap(f, f2, i);
                }
            } else if (z3) {
                gentleWakeUp(i);
            } else {
                this.mDozeHost.extendPulse(i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void gentleWakeUp(int i) {
        this.mMetricsLogger.write(new LogMaker(223).setType(6).setSubtype(i));
        if (this.mDozeParameters.getDisplayNeedsBlanking()) {
            this.mDozeHost.setAodDimmingScrim(1.0f);
        }
        this.mMachine.wakeUp();
    }

    /* access modifiers changed from: private */
    public void onProximityFar(boolean z) {
        if (this.mMachine.isExecutingTransition()) {
            Log.w("DozeTriggers", "onProximityFar called during transition. Ignoring sensor response.");
            return;
        }
        boolean z2 = !z;
        DozeMachine.State state = this.mMachine.getState();
        boolean z3 = false;
        boolean z4 = state == DozeMachine.State.DOZE_AOD_PAUSED;
        boolean z5 = state == DozeMachine.State.DOZE_AOD_PAUSING;
        if (state == DozeMachine.State.DOZE_AOD) {
            z3 = true;
        }
        if (state == DozeMachine.State.DOZE_PULSING || state == DozeMachine.State.DOZE_PULSING_BRIGHT) {
            if (DEBUG) {
                Log.i("DozeTriggers", "Prox changed, ignore touch = " + z2);
            }
            this.mDozeHost.onIgnoreTouchWhilePulsing(z2);
        }
        if (z && (z4 || z5)) {
            if (DEBUG) {
                Log.i("DozeTriggers", "Prox FAR, unpausing AOD");
            }
            this.mMachine.requestState(DozeMachine.State.DOZE_AOD);
        } else if (z2 && z3) {
            if (DEBUG) {
                Log.i("DozeTriggers", "Prox NEAR, pausing AOD");
            }
            this.mMachine.requestState(DozeMachine.State.DOZE_AOD_PAUSING);
        }
    }

    private void onWakeScreen(boolean z, DozeMachine.State state) {
        DozeLog.traceWakeDisplay(z);
        sWakeDisplaySensorState = z;
        boolean z2 = true;
        if (z) {
            proximityCheckThenCall(new IntConsumer(state) {
                private final /* synthetic */ DozeMachine.State f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(int i) {
                    DozeTriggers.this.lambda$onWakeScreen$1$DozeTriggers(this.f$1, i);
                }
            }, true, 7);
            return;
        }
        boolean z3 = state == DozeMachine.State.DOZE_AOD_PAUSED;
        if (state != DozeMachine.State.DOZE_AOD_PAUSING) {
            z2 = false;
        }
        if (!z2 && !z3) {
            this.mMachine.requestState(DozeMachine.State.DOZE);
            this.mMetricsLogger.write(new LogMaker(223).setType(2).setSubtype(7));
        }
    }

    public /* synthetic */ void lambda$onWakeScreen$1$DozeTriggers(DozeMachine.State state, int i) {
        if (i != 1 && state == DozeMachine.State.DOZE) {
            this.mMachine.requestState(DozeMachine.State.DOZE_AOD);
            this.mMetricsLogger.write(new LogMaker(223).setType(1).setSubtype(7));
        }
    }

    public void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        boolean z = Settings.System.getInt(this.mContext.getContentResolver(), "screen_off_fod", 0) != 0;
        switch (C07293.$SwitchMap$com$android$systemui$doze$DozeMachine$State[state2.ordinal()]) {
            case 1:
                this.mBroadcastReceiver.register(this.mContext);
                this.mDozeHost.addCallback(this.mHostCallback);
                DockManager dockManager = this.mDockManager;
                if (dockManager != null) {
                    dockManager.addListener(this.mDockEventListener);
                }
                this.mDozeSensors.requestTemporaryDisable();
                checkTriggersAtInit();
                return;
            case 2:
            case 3:
                this.mDozeSensors.setProxListening(state2 != DozeMachine.State.DOZE);
                this.mDozeSensors.setListening(true);
                this.mDozeSensors.setPaused(false);
                if (state2 == DozeMachine.State.DOZE_AOD && !sWakeDisplaySensorState) {
                    onWakeScreen(false, state2);
                }
                if (z) {
                    this.mDozeSensors.setProxListening(false);
                    this.mDozeSensors.setListening(false);
                    return;
                }
                return;
            case 4:
            case 5:
                this.mDozeSensors.setProxListening(true);
                this.mDozeSensors.setPaused(true);
                return;
            case 6:
            case 7:
                this.mDozeSensors.setTouchscreenSensorsListening(false);
                this.mDozeSensors.setProxListening(true);
                this.mDozeSensors.setPaused(false);
                return;
            case 8:
                this.mDozeSensors.requestTemporaryDisable();
                this.mDozeSensors.updateListening();
                return;
            case 9:
                this.mBroadcastReceiver.unregister(this.mContext);
                this.mDozeHost.removeCallback(this.mHostCallback);
                DockManager dockManager2 = this.mDockManager;
                if (dockManager2 != null) {
                    dockManager2.removeListener(this.mDockEventListener);
                }
                this.mDozeSensors.setListening(false);
                this.mDozeSensors.setProxListening(false);
                return;
            default:
                return;
        }
    }

    /* renamed from: com.android.systemui.doze.DozeTriggers$3 */
    static /* synthetic */ class C07293 {
        static final /* synthetic */ int[] $SwitchMap$com$android$systemui$doze$DozeMachine$State = new int[DozeMachine.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.systemui.doze.DozeMachine$State[] r0 = com.android.systemui.doze.DozeMachine.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$android$systemui$doze$DozeMachine$State = r0
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_AOD     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_AOD_PAUSED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_AOD_PAUSING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_PULSING     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_PULSING_BRIGHT     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_PULSE_DONE     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x006e }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.FINISH     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeTriggers.C07293.<clinit>():void");
        }
    }

    private void checkTriggersAtInit() {
        if (this.mUiModeManager.getCurrentModeType() == 3 || this.mDozeHost.isBlockingDoze() || !this.mDozeHost.isProvisioned()) {
            this.mMachine.requestState(DozeMachine.State.FINISH);
        }
    }

    /* access modifiers changed from: private */
    public void tryToggleFlashlight() {
        proximityCheckThenCall(new IntConsumer() {
            public final void accept(int i) {
                DozeTriggers.this.lambda$tryToggleFlashlight$2$DozeTriggers(i);
            }
        }, false, 10);
    }

    public /* synthetic */ void lambda$tryToggleFlashlight$2$DozeTriggers(int i) {
        if (i != 1) {
            this.mDozeHost.performToggleFlashlight();
        }
    }

    /* access modifiers changed from: private */
    public void requestPulse(int i, boolean z, Runnable runnable) {
        Assert.isMainThread();
        this.mDozeHost.extendPulse(i);
        if (this.mMachine.getState() == DozeMachine.State.DOZE_PULSING && i == 8) {
            this.mMachine.requestState(DozeMachine.State.DOZE_PULSING_BRIGHT);
        } else if (this.mPulsePending || !this.mAllowPulseTriggers || !canPulse()) {
            if (this.mAllowPulseTriggers) {
                DozeLog.tracePulseDropped(this.mContext, this.mPulsePending, this.mMachine.getState(), this.mDozeHost.isPulsingBlocked());
            }
            runIfNotNull(runnable);
        } else {
            boolean z2 = true;
            this.mPulsePending = true;
            $$Lambda$DozeTriggers$jH5ImBNnHtdzWoidDyzTmLESO_A r1 = new IntConsumer(runnable, i) {
                private final /* synthetic */ Runnable f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void accept(int i) {
                    DozeTriggers.this.lambda$requestPulse$3$DozeTriggers(this.f$1, this.f$2, i);
                }
            };
            if (this.mDozeParameters.getProxCheckBeforePulse() && !z) {
                z2 = false;
            }
            proximityCheckThenCall(r1, z2, i);
            this.mMetricsLogger.write(new LogMaker(223).setType(6).setSubtype(i));
        }
    }

    public /* synthetic */ void lambda$requestPulse$3$DozeTriggers(Runnable runnable, int i, int i2) {
        if (i2 == 1) {
            DozeLog.tracePulseDropped(this.mContext, "inPocket");
            this.mPulsePending = false;
            runIfNotNull(runnable);
            return;
        }
        continuePulseRequest(i);
    }

    private boolean canPulse() {
        return this.mMachine.getState() == DozeMachine.State.DOZE || this.mMachine.getState() == DozeMachine.State.DOZE_AOD;
    }

    private void continuePulseRequest(int i) {
        this.mPulsePending = false;
        if (this.mDozeHost.isPulsingBlocked() || !canPulse()) {
            DozeLog.tracePulseDropped(this.mContext, this.mPulsePending, this.mMachine.getState(), this.mDozeHost.isPulsingBlocked());
            return;
        }
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "pulse_trigger_reason", i, -2);
        this.mMachine.requestPulse(i);
    }

    public void dump(PrintWriter printWriter) {
        printWriter.print(" notificationPulseTime=");
        printWriter.println(Formatter.formatShortElapsedTime(this.mContext, this.mNotificationPulseTime));
        printWriter.print(" pulsePending=");
        printWriter.println(this.mPulsePending);
        printWriter.println("DozeSensors:");
        this.mDozeSensors.dump(printWriter);
    }

    private abstract class ProximityCheck implements SensorEventListener, Runnable {
        private boolean mFinished;
        private float mMaxRange;
        private boolean mRegistered;
        private float mSensorThreshold;
        private boolean mUsingBrightnessSensor;

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        /* access modifiers changed from: protected */
        public abstract void onProximityResult(int i);

        private ProximityCheck() {
        }

        public void check() {
            Preconditions.checkState(!this.mFinished && !this.mRegistered);
            Sensor findCustomProxSensor = ProximitySensor.findCustomProxSensor(DozeTriggers.this.mContext, DozeTriggers.this.mSensorManager);
            this.mUsingBrightnessSensor = findCustomProxSensor != null;
            if (this.mUsingBrightnessSensor) {
                this.mSensorThreshold = ProximitySensor.getBrightnessSensorThreshold(DozeTriggers.this.mContext.getResources());
            } else {
                findCustomProxSensor = DozeTriggers.this.mSensorManager.getDefaultSensor(8);
            }
            Sensor sensor = findCustomProxSensor;
            if (sensor == null) {
                if (DozeMachine.DEBUG) {
                    Log.d("DozeTriggers", "ProxCheck: No sensor found");
                }
                finishWithResult(0);
                return;
            }
            DozeTriggers.this.mDozeSensors.setDisableSensorsInterferingWithProximity(true);
            this.mMaxRange = sensor.getMaximumRange();
            DozeTriggers.this.mSensorManager.registerListener(this, sensor, 3, 0, DozeTriggers.this.mHandler);
            DozeTriggers.this.mHandler.postDelayed(this, 500);
            DozeTriggers.this.mWakeLock.acquire("DozeTriggers");
            this.mRegistered = true;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            boolean z = false;
            if (sensorEvent.values.length == 0) {
                if (DozeMachine.DEBUG) {
                    Log.d("DozeTriggers", "ProxCheck: Event has no values!");
                }
                finishWithResult(0);
                return;
            }
            if (DozeMachine.DEBUG) {
                Log.d("DozeTriggers", "ProxCheck: Event: value=" + sensorEvent.values[0] + " max=" + this.mMaxRange);
            }
            int i = 1;
            if (!this.mUsingBrightnessSensor ? sensorEvent.values[0] < this.mMaxRange : sensorEvent.values[0] <= this.mSensorThreshold) {
                z = true;
            }
            if (!z) {
                i = 2;
            }
            finishWithResult(i);
        }

        public void run() {
            if (DozeMachine.DEBUG) {
                Log.d("DozeTriggers", "ProxCheck: No event received before timeout");
            }
            finishWithResult(0);
        }

        private void finishWithResult(int i) {
            if (!this.mFinished) {
                boolean z = this.mRegistered;
                if (z) {
                    DozeTriggers.this.mHandler.removeCallbacks(this);
                    DozeTriggers.this.mSensorManager.unregisterListener(this);
                    DozeTriggers.this.mDozeSensors.setDisableSensorsInterferingWithProximity(false);
                    this.mRegistered = false;
                }
                onProximityResult(i);
                if (z) {
                    DozeTriggers.this.mWakeLock.release("DozeTriggers");
                }
                this.mFinished = true;
            }
        }
    }

    private class TriggerReceiver extends BroadcastReceiver {
        private boolean mRegistered;

        private TriggerReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.android.systemui.doze.pulse".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("NoProximityCheck", 0);
                DozeTriggers dozeTriggers = DozeTriggers.this;
                boolean z = true;
                if (intExtra != 1) {
                    z = false;
                }
                dozeTriggers.requestPulse(0, z, (Runnable) null);
            }
            if (UiModeManager.ACTION_ENTER_CAR_MODE.equals(intent.getAction())) {
                DozeTriggers.this.mMachine.requestState(DozeMachine.State.FINISH);
            }
            if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                DozeTriggers.this.mDozeSensors.onUserSwitched();
            }
        }

        public void register(Context context) {
            if (!this.mRegistered) {
                IntentFilter intentFilter = new IntentFilter("com.android.systemui.doze.pulse");
                intentFilter.addAction(UiModeManager.ACTION_ENTER_CAR_MODE);
                intentFilter.addAction("android.intent.action.USER_SWITCHED");
                context.registerReceiver(this, intentFilter);
                this.mRegistered = true;
            }
        }

        public void unregister(Context context) {
            if (this.mRegistered) {
                context.unregisterReceiver(this);
                this.mRegistered = false;
            }
        }
    }

    private class DockEventListener implements DockManager.DockEventListener {
        private DockEventListener() {
        }
    }
}
