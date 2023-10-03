package com.android.systemui.doze;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.TimeUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.doze.DozeMachine;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DozeLog {
    private static final boolean DEBUG = Log.isLoggable("DozeLog", 3);
    static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    private static final int SIZE = (Build.IS_DEBUGGABLE ? 400 : 50);
    private static int sCount;
    private static SummaryStats sEmergencyCallStats;
    private static final KeyguardUpdateMonitorCallback sKeyguardCallback = new KeyguardUpdateMonitorCallback() {
        public void onEmergencyCallAction() {
            DozeLog.traceEmergencyCall();
        }

        public void onKeyguardBouncerChanged(boolean z) {
            DozeLog.traceKeyguardBouncerChanged(z);
        }

        public void onStartedWakingUp() {
            DozeLog.traceScreenOn();
        }

        public void onFinishedGoingToSleep(int i) {
            DozeLog.traceScreenOff(i);
        }

        public void onKeyguardVisibilityChanged(boolean z) {
            DozeLog.traceKeyguard(z);
        }
    };
    private static String[] sMessages;
    private static SummaryStats sNotificationPulseStats;
    private static SummaryStats sPickupPulseNearVibrationStats;
    private static SummaryStats sPickupPulseNotNearVibrationStats;
    private static int sPosition;
    private static SummaryStats[][] sProxStats;
    private static boolean sPulsing;
    private static boolean sRegisterKeyguardCallback = true;
    private static SummaryStats sScreenOnNotPulsingStats;
    private static SummaryStats sScreenOnPulsingStats;
    /* access modifiers changed from: private */
    public static long sSince;
    private static long[] sTimes;

    public static void tracePickupWakeUp(Context context, boolean z) {
        SummaryStats summaryStats;
        init(context);
        log("pickupWakeUp withinVibrationThreshold=" + z);
        if (z) {
            summaryStats = sPickupPulseNearVibrationStats;
        } else {
            summaryStats = sPickupPulseNotNearVibrationStats;
        }
        summaryStats.append();
    }

    public static void tracePulseStart(int i) {
        sPulsing = true;
        log("pulseStart reason=" + reasonToString(i));
    }

    public static void tracePulseFinish() {
        sPulsing = false;
        log("pulseFinish");
    }

    public static void traceNotificationPulse(Context context) {
        init(context);
        log("notificationPulse");
        sNotificationPulseStats.append();
    }

    private static void init(Context context) {
        synchronized (DozeLog.class) {
            if (sMessages == null) {
                sTimes = new long[SIZE];
                sMessages = new String[SIZE];
                sSince = System.currentTimeMillis();
                sPickupPulseNearVibrationStats = new SummaryStats();
                sPickupPulseNotNearVibrationStats = new SummaryStats();
                sNotificationPulseStats = new SummaryStats();
                sScreenOnPulsingStats = new SummaryStats();
                sScreenOnNotPulsingStats = new SummaryStats();
                sEmergencyCallStats = new SummaryStats();
                sProxStats = (SummaryStats[][]) Array.newInstance(SummaryStats.class, new int[]{11, 2});
                for (int i = 0; i < 11; i++) {
                    sProxStats[i][0] = new SummaryStats();
                    sProxStats[i][1] = new SummaryStats();
                }
                log("init");
                if (sRegisterKeyguardCallback) {
                    KeyguardUpdateMonitor.getInstance(context).registerCallback(sKeyguardCallback);
                }
            }
        }
    }

    public static void traceDozing(Context context, boolean z) {
        sPulsing = false;
        init(context);
        log("dozing " + z);
    }

    public static void traceFling(boolean z, boolean z2, boolean z3, boolean z4) {
        log("fling expand=" + z + " aboveThreshold=" + z2 + " thresholdNeeded=" + z3 + " screenOnFromTouch=" + z4);
    }

    public static void traceEmergencyCall() {
        log("emergencyCall");
        sEmergencyCallStats.append();
    }

    public static void traceKeyguardBouncerChanged(boolean z) {
        log("bouncer " + z);
    }

    public static void traceScreenOn() {
        log("screenOn pulsing=" + sPulsing);
        (sPulsing ? sScreenOnPulsingStats : sScreenOnNotPulsingStats).append();
        sPulsing = false;
    }

    public static void traceScreenOff(int i) {
        log("screenOff why=" + i);
    }

    public static void traceMissedTick(String str) {
        log("missedTick by=" + str);
    }

    public static void traceTimeTickScheduled(long j, long j2) {
        log("timeTickScheduled at=" + FORMAT.format(new Date(j)) + " triggerAt=" + FORMAT.format(new Date(j2)));
    }

    public static void traceKeyguard(boolean z) {
        log("keyguard " + z);
        if (!z) {
            sPulsing = false;
        }
    }

    public static void traceState(DozeMachine.State state) {
        log("state " + state);
    }

    public static void traceWakeDisplay(boolean z) {
        log("wakeDisplay " + z);
    }

    public static void traceProximityResult(Context context, boolean z, long j, int i) {
        init(context);
        log("proximityResult reason=" + reasonToString(i) + " near=" + z + " millis=" + j);
        sProxStats[i][z ^ true].append();
    }

    public static String reasonToString(int i) {
        switch (i) {
            case 0:
                return "intent";
            case 1:
                return "notification";
            case 2:
                return "sigmotion";
            case 3:
                return "pickup";
            case 4:
                return "doubletap";
            case 5:
                return "longpress";
            case 6:
                return "docking";
            case 7:
                return "wakeup";
            case 8:
                return "wakelockscreen";
            case 9:
                return "tap";
            case 10:
                return "flashlight";
            default:
                throw new IllegalArgumentException("bad reason: " + i);
        }
    }

    public static void dump(PrintWriter printWriter) {
        synchronized (DozeLog.class) {
            if (sMessages != null) {
                printWriter.println("  Doze log:");
                int i = ((sPosition - sCount) + SIZE) % SIZE;
                for (int i2 = 0; i2 < sCount; i2++) {
                    int i3 = (i + i2) % SIZE;
                    printWriter.print("    ");
                    printWriter.print(FORMAT.format(new Date(sTimes[i3])));
                    printWriter.print(' ');
                    printWriter.println(sMessages[i3]);
                }
                printWriter.print("  Doze summary stats (for ");
                TimeUtils.formatDuration(System.currentTimeMillis() - sSince, printWriter);
                printWriter.println("):");
                sPickupPulseNearVibrationStats.dump(printWriter, "Pickup pulse (near vibration)");
                sPickupPulseNotNearVibrationStats.dump(printWriter, "Pickup pulse (not near vibration)");
                sNotificationPulseStats.dump(printWriter, "Notification pulse");
                sScreenOnPulsingStats.dump(printWriter, "Screen on (pulsing)");
                sScreenOnNotPulsingStats.dump(printWriter, "Screen on (not pulsing)");
                sEmergencyCallStats.dump(printWriter, "Emergency call");
                for (int i4 = 0; i4 < 11; i4++) {
                    String reasonToString = reasonToString(i4);
                    sProxStats[i4][0].dump(printWriter, "Proximity near (" + reasonToString + ")");
                    sProxStats[i4][1].dump(printWriter, "Proximity far (" + reasonToString + ")");
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (DEBUG == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        android.util.Log.d("DozeLog", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void log(java.lang.String r5) {
        /*
            java.lang.Class<com.android.systemui.doze.DozeLog> r0 = com.android.systemui.doze.DozeLog.class
            monitor-enter(r0)
            java.lang.String[] r1 = sMessages     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            return
        L_0x0009:
            long[] r1 = sTimes     // Catch:{ all -> 0x0039 }
            int r2 = sPosition     // Catch:{ all -> 0x0039 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0039 }
            r1[r2] = r3     // Catch:{ all -> 0x0039 }
            java.lang.String[] r1 = sMessages     // Catch:{ all -> 0x0039 }
            int r2 = sPosition     // Catch:{ all -> 0x0039 }
            r1[r2] = r5     // Catch:{ all -> 0x0039 }
            int r1 = sPosition     // Catch:{ all -> 0x0039 }
            int r1 = r1 + 1
            int r2 = SIZE     // Catch:{ all -> 0x0039 }
            int r1 = r1 % r2
            sPosition = r1     // Catch:{ all -> 0x0039 }
            int r1 = sCount     // Catch:{ all -> 0x0039 }
            int r1 = r1 + 1
            int r2 = SIZE     // Catch:{ all -> 0x0039 }
            int r1 = java.lang.Math.min(r1, r2)     // Catch:{ all -> 0x0039 }
            sCount = r1     // Catch:{ all -> 0x0039 }
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0038
            java.lang.String r0 = "DozeLog"
            android.util.Log.d(r0, r5)
        L_0x0038:
            return
        L_0x0039:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeLog.log(java.lang.String):void");
    }

    public static void tracePulseDropped(Context context, boolean z, DozeMachine.State state, boolean z2) {
        init(context);
        log("pulseDropped pulsePending=" + z + " state=" + state + " blocked=" + z2);
    }

    public static void tracePulseDropped(Context context, String str) {
        init(context);
        log("pulseDropped why=" + str);
    }

    public static void tracePulseTouchDisabledByProx(Context context, boolean z) {
        init(context);
        log("pulseTouchDisabledByProx " + z);
    }

    public static void traceSensor(Context context, int i) {
        init(context);
        log("sensor type=" + reasonToString(i));
    }

    private static class SummaryStats {
        private int mCount;

        private SummaryStats() {
        }

        public void append() {
            this.mCount++;
        }

        public void dump(PrintWriter printWriter, String str) {
            if (this.mCount != 0) {
                printWriter.print("    ");
                printWriter.print(str);
                printWriter.print(": n=");
                printWriter.print(this.mCount);
                printWriter.print(" (");
                printWriter.print((((double) this.mCount) / ((double) (System.currentTimeMillis() - DozeLog.sSince))) * 1000.0d * 60.0d * 60.0d);
                printWriter.print("/hr)");
                printWriter.println();
            }
        }
    }
}
