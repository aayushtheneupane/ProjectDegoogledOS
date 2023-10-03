package com.android.systemui.biometrics;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowManager;
import com.android.internal.os.SomeArgs;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUI;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.CommandQueue;

public class BiometricDialogImpl extends SystemUI implements CommandQueue.Callbacks {
    private Callback mCallback = new Callback();
    private BiometricDialogView mCurrentDialog;
    private SomeArgs mCurrentDialogArgs;
    /* access modifiers changed from: private */
    public boolean mDialogShowing;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    BiometricDialogImpl.this.handleShowDialog((SomeArgs) message.obj, false, (Bundle) null);
                    return;
                case 2:
                    SomeArgs someArgs = (SomeArgs) message.obj;
                    BiometricDialogImpl.this.handleBiometricAuthenticated(((Boolean) someArgs.arg1).booleanValue(), (String) someArgs.arg2, ((Boolean) someArgs.arg3).booleanValue());
                    someArgs.recycle();
                    return;
                case 3:
                    SomeArgs someArgs2 = (SomeArgs) message.obj;
                    BiometricDialogImpl.this.handleBiometricHelp((String) someArgs2.arg1);
                    someArgs2.recycle();
                    return;
                case 4:
                    BiometricDialogImpl.this.handleBiometricError((String) message.obj);
                    return;
                case 5:
                    BiometricDialogImpl.this.handleHideDialog(((Boolean) message.obj).booleanValue());
                    return;
                case 6:
                    BiometricDialogImpl.this.handleButtonNegative();
                    return;
                case 7:
                    BiometricDialogImpl.this.handleUserCanceled();
                    return;
                case 8:
                    BiometricDialogImpl.this.handleButtonPositive();
                    return;
                case 9:
                    BiometricDialogImpl.this.handleTryAgainPressed();
                    return;
                default:
                    Log.w("BiometricDialogImpl", "Unknown message: " + message.what);
                    return;
            }
        }
    };
    private IBiometricServiceReceiverInternal mReceiver;
    private boolean mTryAgainPressed;
    private WakefulnessLifecycle mWakefulnessLifecycle;
    final WakefulnessLifecycle.Observer mWakefulnessObserver = new WakefulnessLifecycle.Observer() {
        public void onStartedGoingToSleep() {
            if (BiometricDialogImpl.this.mDialogShowing) {
                Log.d("BiometricDialogImpl", "User canceled due to screen off");
                BiometricDialogImpl.this.mHandler.obtainMessage(7).sendToTarget();
            }
        }
    };
    private WindowManager mWindowManager;

    private class Callback implements DialogViewCallback {
        private Callback() {
        }

        public void onUserCanceled() {
            BiometricDialogImpl.this.mHandler.obtainMessage(7).sendToTarget();
        }

        public void onErrorShown() {
            BiometricDialogImpl.this.mHandler.sendMessageDelayed(BiometricDialogImpl.this.mHandler.obtainMessage(5, false), 2000);
        }

        public void onNegativePressed() {
            BiometricDialogImpl.this.mHandler.obtainMessage(6).sendToTarget();
        }

        public void onPositivePressed() {
            BiometricDialogImpl.this.mHandler.obtainMessage(8).sendToTarget();
        }

        public void onTryAgainPressed() {
            BiometricDialogImpl.this.mHandler.obtainMessage(9).sendToTarget();
        }
    }

    public void start() {
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager.hasSystemFeature("android.hardware.fingerprint") || packageManager.hasSystemFeature("android.hardware.biometrics.face") || packageManager.hasSystemFeature("android.hardware.biometrics.iris")) {
            ((CommandQueue) getComponent(CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
            this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
            this.mWakefulnessLifecycle = (WakefulnessLifecycle) Dependency.get(WakefulnessLifecycle.class);
            this.mWakefulnessLifecycle.addObserver(this.mWakefulnessObserver);
        }
    }

    public void showBiometricDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2) {
        Log.d("BiometricDialogImpl", "showBiometricDialog, type: " + i + ", requireConfirmation: " + z);
        this.mHandler.removeMessages(4);
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(5);
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = bundle;
        obtain.arg2 = iBiometricServiceReceiverInternal;
        obtain.argi1 = i;
        obtain.arg3 = Boolean.valueOf(z);
        obtain.argi2 = i2;
        this.mHandler.obtainMessage(1, obtain).sendToTarget();
    }

    public void onBiometricAuthenticated(boolean z, String str, boolean z2) {
        Log.d("BiometricDialogImpl", "onBiometricAuthenticated: " + z + " reason: " + str);
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = Boolean.valueOf(z);
        obtain.arg2 = str;
        obtain.arg3 = Boolean.valueOf(z2);
        this.mHandler.obtainMessage(2, obtain).sendToTarget();
    }

    public void onBiometricHelp(String str) {
        Log.d("BiometricDialogImpl", "onBiometricHelp: " + str);
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = str;
        this.mHandler.obtainMessage(3, obtain).sendToTarget();
    }

    public void onBiometricError(String str) {
        Log.d("BiometricDialogImpl", "onBiometricError: " + str);
        this.mHandler.obtainMessage(4, str).sendToTarget();
    }

    public void hideBiometricDialog() {
        Log.d("BiometricDialogImpl", "hideBiometricDialog");
        this.mHandler.obtainMessage(5, false).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleShowDialog(SomeArgs someArgs, boolean z, Bundle bundle) {
        this.mCurrentDialogArgs = someArgs;
        int i = someArgs.argi1;
        BiometricDialogView biometricDialogView = this.mCurrentDialog;
        boolean z2 = (i & 1) != 0;
        boolean z3 = (i & 4) != 0;
        if (!this.mTryAgainPressed) {
            if (z3 && z2) {
                biometricDialogView = new FingerprintAndFaceDialogView(this.mContext, this.mCallback);
            } else if (z2) {
                biometricDialogView = new FingerprintDialogView(this.mContext, this.mCallback);
            } else if (z3) {
                biometricDialogView = new FaceDialogView(this.mContext, this.mCallback);
            } else {
                Log.e("BiometricDialogImpl", "Unsupported type: " + i);
                return;
            }
        }
        Log.d("BiometricDialogImpl", "handleShowDialog,  savedState: " + bundle + " mCurrentDialog: " + this.mCurrentDialog + " newDialog: " + biometricDialogView + " type: " + i);
        if (bundle != null) {
            biometricDialogView.restoreState(bundle);
        } else {
            BiometricDialogView biometricDialogView2 = this.mCurrentDialog;
            if (biometricDialogView2 != null && this.mDialogShowing && !this.mTryAgainPressed) {
                biometricDialogView2.forceRemove();
                this.mDialogShowing = false;
            }
        }
        biometricDialogView.setFaceAndFingerprint(z3, z2);
        this.mReceiver = (IBiometricServiceReceiverInternal) someArgs.arg2;
        biometricDialogView.setBundle((Bundle) someArgs.arg1);
        biometricDialogView.setRequireConfirmation(((Boolean) someArgs.arg3).booleanValue());
        biometricDialogView.setUserId(someArgs.argi2);
        biometricDialogView.setSkipIntro(z);
        this.mCurrentDialog = biometricDialogView;
        if (!this.mTryAgainPressed && !this.mDialogShowing) {
            WindowManager windowManager = this.mWindowManager;
            BiometricDialogView biometricDialogView3 = this.mCurrentDialog;
            windowManager.addView(biometricDialogView3, biometricDialogView3.getLayoutParams());
        }
        this.mDialogShowing = true;
        this.mTryAgainPressed = false;
    }

    /* access modifiers changed from: private */
    public void handleBiometricAuthenticated(boolean z, String str, boolean z2) {
        Log.d("BiometricDialogImpl", "handleBiometricAuthenticated: " + z + " requireConfirmation: " + z2);
        if (z) {
            this.mCurrentDialog.announceForAccessibility(this.mContext.getResources().getText(this.mCurrentDialog.getAuthenticatedAccessibilityResourceId()));
            if (z2) {
                this.mCurrentDialog.updateState(3);
                return;
            }
            this.mCurrentDialog.updateState(4);
            this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    BiometricDialogImpl.this.lambda$handleBiometricAuthenticated$0$BiometricDialogImpl();
                }
            }, (long) this.mCurrentDialog.getDelayAfterAuthenticatedDurationMs());
            return;
        }
        this.mCurrentDialog.onAuthenticationFailed(str);
    }

    public /* synthetic */ void lambda$handleBiometricAuthenticated$0$BiometricDialogImpl() {
        handleHideDialog(false);
    }

    /* access modifiers changed from: private */
    public void handleBiometricHelp(String str) {
        Log.d("BiometricDialogImpl", "handleBiometricHelp: " + str);
        this.mCurrentDialog.onHelpReceived(str);
    }

    /* access modifiers changed from: private */
    public void handleBiometricError(String str) {
        Log.d("BiometricDialogImpl", "handleBiometricError: " + str);
        if (!this.mDialogShowing) {
            Log.d("BiometricDialogImpl", "Dialog already dismissed");
        } else {
            this.mCurrentDialog.onErrorReceived(str);
        }
    }

    /* access modifiers changed from: private */
    public void handleHideDialog(boolean z) {
        Log.d("BiometricDialogImpl", "handleHideDialog, userCanceled: " + z);
        if (!this.mDialogShowing) {
            Log.w("BiometricDialogImpl", "Dialog already dismissed, userCanceled: " + z);
            return;
        }
        if (z) {
            try {
                this.mReceiver.onDialogDismissed(3);
            } catch (RemoteException e) {
                Log.e("BiometricDialogImpl", "RemoteException when hiding dialog", e);
            }
        }
        this.mReceiver = null;
        this.mDialogShowing = false;
        this.mTryAgainPressed = false;
        this.mCurrentDialog.startDismiss();
    }

    /* access modifiers changed from: private */
    public void handleButtonNegative() {
        IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal = this.mReceiver;
        if (iBiometricServiceReceiverInternal == null) {
            Log.e("BiometricDialogImpl", "Receiver is null");
            return;
        }
        try {
            iBiometricServiceReceiverInternal.onDialogDismissed(2);
        } catch (RemoteException e) {
            Log.e("BiometricDialogImpl", "Remote exception when handling negative button", e);
        }
        handleHideDialog(false);
    }

    /* access modifiers changed from: private */
    public void handleButtonPositive() {
        IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal = this.mReceiver;
        if (iBiometricServiceReceiverInternal == null) {
            Log.e("BiometricDialogImpl", "Receiver is null");
            return;
        }
        try {
            iBiometricServiceReceiverInternal.onDialogDismissed(1);
        } catch (RemoteException e) {
            Log.e("BiometricDialogImpl", "Remote exception when handling positive button", e);
        }
        handleHideDialog(false);
    }

    /* access modifiers changed from: private */
    public void handleUserCanceled() {
        handleHideDialog(true);
    }

    /* access modifiers changed from: private */
    public void handleTryAgainPressed() {
        try {
            this.mTryAgainPressed = true;
            this.mReceiver.onTryAgainPressed();
        } catch (RemoteException e) {
            Log.e("BiometricDialogImpl", "RemoteException when handling try again", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = this.mDialogShowing;
        Bundle bundle = new Bundle();
        BiometricDialogView biometricDialogView = this.mCurrentDialog;
        if (biometricDialogView != null) {
            biometricDialogView.onSaveState(bundle);
        }
        if (this.mDialogShowing) {
            this.mCurrentDialog.forceRemove();
            this.mDialogShowing = false;
            this.mTryAgainPressed = false;
        }
        if (z) {
            handleShowDialog(this.mCurrentDialogArgs, true, bundle);
        }
    }
}
