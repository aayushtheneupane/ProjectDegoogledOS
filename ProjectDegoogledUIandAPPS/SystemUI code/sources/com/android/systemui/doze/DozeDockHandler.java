package com.android.systemui.doze;

import android.content.Context;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Handler;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.DozeMachine;
import java.io.PrintWriter;

public class DozeDockHandler implements DozeMachine.Part {
    private static final boolean DEBUG = DozeService.DEBUG;
    private final AmbientDisplayConfiguration mConfig;
    private final DockEventListener mDockEventListener = new DockEventListener(this, (C07201) null);
    /* access modifiers changed from: private */
    public final DockManager mDockManager;
    private int mDockState = 0;
    private final DozeHost mDozeHost;
    private final Handler mHandler;
    private final DozeMachine mMachine;
    private boolean mPulsePending;

    public DozeDockHandler(Context context, DozeMachine dozeMachine, DozeHost dozeHost, AmbientDisplayConfiguration ambientDisplayConfiguration, Handler handler, DockManager dockManager) {
        this.mMachine = dozeMachine;
        this.mDozeHost = dozeHost;
        this.mConfig = ambientDisplayConfiguration;
        this.mHandler = handler;
        this.mDockManager = dockManager;
    }

    /* renamed from: com.android.systemui.doze.DozeDockHandler$1 */
    static /* synthetic */ class C07201 {
        static final /* synthetic */ int[] $SwitchMap$com$android$systemui$doze$DozeMachine$State = new int[DozeMachine.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
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
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE_AOD     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.DOZE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$android$systemui$doze$DozeMachine$State     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.systemui.doze.DozeMachine$State r1 = com.android.systemui.doze.DozeMachine.State.FINISH     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeDockHandler.C07201.<clinit>():void");
        }
    }

    public void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        int i = C07201.$SwitchMap$com$android$systemui$doze$DozeMachine$State[state2.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        this.mDockEventListener.unregister();
                        return;
                    }
                    return;
                }
            } else if (this.mDockState == 2) {
                this.mMachine.requestState(DozeMachine.State.DOZE);
                return;
            }
            if (this.mDockState == 1 && !this.mPulsePending) {
                this.mPulsePending = true;
                this.mHandler.post(new Runnable(state2) {
                    private final /* synthetic */ DozeMachine.State f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DozeDockHandler.this.lambda$transitionTo$0$DozeDockHandler(this.f$1);
                    }
                });
                return;
            }
            return;
        }
        this.mDockEventListener.register();
    }

    /* access modifiers changed from: private */
    /* renamed from: requestPulse */
    public void lambda$transitionTo$0$DozeDockHandler(DozeMachine.State state) {
        if (!this.mDozeHost.isPulsingBlocked() && state.canPulse()) {
            this.mMachine.requestPulse(6);
        }
        this.mPulsePending = false;
    }

    private boolean isDocked() {
        int i = this.mDockState;
        return i == 1 || i == 2;
    }

    public void dump(PrintWriter printWriter) {
        printWriter.print(" DozeDockTriggers docking=");
        printWriter.println(isDocked());
    }

    private class DockEventListener implements DockManager.DockEventListener {
        private boolean mRegistered;

        private DockEventListener() {
        }

        /* synthetic */ DockEventListener(DozeDockHandler dozeDockHandler, C07201 r2) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void register() {
            if (!this.mRegistered) {
                if (DozeDockHandler.this.mDockManager != null) {
                    DozeDockHandler.this.mDockManager.addListener(this);
                }
                this.mRegistered = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void unregister() {
            if (this.mRegistered) {
                if (DozeDockHandler.this.mDockManager != null) {
                    DozeDockHandler.this.mDockManager.removeListener(this);
                }
                this.mRegistered = false;
            }
        }
    }
}
