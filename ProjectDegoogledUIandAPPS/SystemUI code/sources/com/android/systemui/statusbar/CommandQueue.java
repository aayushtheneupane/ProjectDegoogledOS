package com.android.systemui.statusbar;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.util.SparseArray;
import android.view.inputmethod.InputMethodSystemProperty;
import com.android.internal.os.SomeArgs;
import com.android.internal.statusbar.IStatusBar;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.CallbackController;
import java.util.ArrayList;

public class CommandQueue extends IStatusBar.Stub implements CallbackController<Callbacks>, DisplayManager.DisplayListener {
    /* access modifiers changed from: private */
    public ArrayList<Callbacks> mCallbacks = new ArrayList<>();
    private SparseArray<Pair<Integer, Integer>> mDisplayDisabled = new SparseArray<>();
    /* access modifiers changed from: private */
    public Handler mHandler = new C1071H(Looper.getMainLooper());
    private int mLastUpdatedImeDisplayId = -1;
    private final Object mLock = new Object();

    public interface Callbacks {
        void addQsTile(ComponentName componentName) {
        }

        void animateCollapsePanels(int i, boolean z) {
        }

        void animateExpandNotificationsPanel() {
        }

        void animateExpandSettingsPanel(String str) {
        }

        void appTransitionCancelled(int i) {
        }

        void appTransitionFinished(int i) {
        }

        void appTransitionPending(int i, boolean z) {
        }

        void appTransitionStarting(int i, long j, long j2, boolean z) {
        }

        void cancelPreloadRecentApps() {
        }

        void clickTile(ComponentName componentName) {
        }

        void disable(int i, int i2, int i3, boolean z) {
        }

        void dismissKeyboardShortcutsMenu() {
        }

        void handleShowGlobalActionsMenu() {
        }

        void handleShowShutdownUi(boolean z, String str) {
        }

        void handleSystemKey(int i) {
        }

        void hideBiometricDialog() {
        }

        void hideInDisplayFingerprintView() {
        }

        void hideRecentApps(boolean z, boolean z2) {
        }

        void killForegroundApp() {
        }

        void leftInLandscapeChanged(boolean z) {
        }

        void onBiometricAuthenticated(boolean z, String str, boolean z2) {
        }

        void onBiometricError(String str) {
        }

        void onBiometricHelp(String str) {
        }

        void onCameraLaunchGestureDetected(int i) {
        }

        void onDisplayReady(int i) {
        }

        void onDisplayRemoved(int i) {
        }

        void onRecentsAnimationStateChanged(boolean z) {
        }

        void onRotationProposal(int i, boolean z) {
        }

        void preloadRecentApps() {
        }

        void remQsTile(ComponentName componentName) {
        }

        void removeIcon(String str) {
        }

        void screenPinningStateChanged(boolean z) {
        }

        void setIcon(String str, StatusBarIcon statusBarIcon) {
        }

        void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z) {
        }

        void setPartialScreenshot(boolean z) {
        }

        void setSystemUiVisibility(int i, int i2, int i3, int i4, int i5, Rect rect, Rect rect2, boolean z) {
        }

        void setTopAppHidesStatusBar(boolean z) {
        }

        void setWindowState(int i, int i2, int i3) {
        }

        void showAssistDisclosure() {
        }

        void showBiometricDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2) {
        }

        void showInDisplayFingerprintView() {
        }

        void showPictureInPictureMenu() {
        }

        void showPinningEnterExitToast(boolean z) {
        }

        void showPinningEscapeToast() {
        }

        void showRecentApps(boolean z) {
        }

        void showScreenPinningRequest(int i) {
        }

        void showWirelessChargingAnimation(int i) {
        }

        void startAssist(Bundle bundle) {
        }

        void toggleCameraFlash() {
        }

        void toggleKeyboardShortcutsMenu(int i) {
        }

        void togglePanel() {
        }

        void toggleRecentApps() {
        }

        void toggleSettingsPanel() {
        }

        void toggleSplitScreen() {
        }
    }

    public void onDisplayAdded(int i) {
    }

    public void onDisplayChanged(int i) {
    }

    public void topAppWindowChanged(int i, boolean z) {
    }

    public CommandQueue(Context context) {
        ((DisplayManager) context.getSystemService(DisplayManager.class)).registerDisplayListener(this, this.mHandler);
        setDisabled(0, 0, 0);
    }

    public void onDisplayRemoved(int i) {
        synchronized (this.mLock) {
            this.mDisplayDisabled.remove(i);
        }
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onDisplayRemoved(i);
        }
    }

    public boolean panelsEnabled() {
        int disabled1 = getDisabled1(0);
        int disabled2 = getDisabled2(0);
        if ((disabled1 & 65536) == 0 && (disabled2 & 4) == 0 && !StatusBar.ONLY_CORE_APPS) {
            return true;
        }
        return false;
    }

    public void addCallback(Callbacks callbacks) {
        this.mCallbacks.add(callbacks);
        for (int i = 0; i < this.mDisplayDisabled.size(); i++) {
            int keyAt = this.mDisplayDisabled.keyAt(i);
            callbacks.disable(keyAt, getDisabled1(keyAt), getDisabled2(keyAt), false);
        }
    }

    public void removeCallback(Callbacks callbacks) {
        this.mCallbacks.remove(callbacks);
    }

    public void setIcon(String str, StatusBarIcon statusBarIcon) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(65536, 1, 0, new Pair(str, statusBarIcon)).sendToTarget();
        }
    }

    public void removeIcon(String str) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(65536, 2, 0, str).sendToTarget();
        }
    }

    public void disable(int i, int i2, int i3, boolean z) {
        synchronized (this.mLock) {
            setDisabled(i, i2, i3);
            this.mHandler.removeMessages(131072);
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = i3;
            obtain.argi4 = z ? 1 : 0;
            Message obtainMessage = this.mHandler.obtainMessage(131072, obtain);
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                this.mHandler.handleMessage(obtainMessage);
                obtainMessage.recycle();
            } else {
                obtainMessage.sendToTarget();
            }
        }
    }

    public void disable(int i, int i2, int i3) {
        disable(i, i2, i3, true);
    }

    public void recomputeDisableFlags(int i, boolean z) {
        disable(i, getDisabled1(i), getDisabled2(i), z);
    }

    private void setDisabled(int i, int i2, int i3) {
        this.mDisplayDisabled.put(i, new Pair(Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    private int getDisabled1(int i) {
        return ((Integer) getDisabled(i).first).intValue();
    }

    private int getDisabled2(int i) {
        return ((Integer) getDisabled(i).second).intValue();
    }

    private Pair<Integer, Integer> getDisabled(int i) {
        Pair<Integer, Integer> pair = this.mDisplayDisabled.get(i);
        if (pair != null) {
            return pair;
        }
        Pair<Integer, Integer> pair2 = new Pair<>(0, 0);
        this.mDisplayDisabled.put(i, pair2);
        return pair2;
    }

    public void animateExpandNotificationsPanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(196608);
            this.mHandler.sendEmptyMessage(196608);
        }
    }

    public void animateCollapsePanels() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(262144);
            this.mHandler.obtainMessage(262144, 0, 0).sendToTarget();
        }
    }

    public void animateCollapsePanels(int i, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(262144);
            this.mHandler.obtainMessage(262144, i, z ? 1 : 0).sendToTarget();
        }
    }

    public void togglePanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2293760);
            this.mHandler.obtainMessage(2293760, 0, 0).sendToTarget();
        }
    }

    public void toggleSettingsPanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3407872);
            this.mHandler.obtainMessage(3407872, 0, 0).sendToTarget();
        }
    }

    public void animateExpandSettingsPanel(String str) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(327680);
            this.mHandler.obtainMessage(327680, str).sendToTarget();
        }
    }

    public void setSystemUiVisibility(int i, int i2, int i3, int i4, int i5, Rect rect, Rect rect2, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = i3;
            obtain.argi4 = i4;
            obtain.argi5 = i5;
            obtain.argi6 = z ? 1 : 0;
            obtain.arg1 = rect;
            obtain.arg2 = rect2;
            this.mHandler.obtainMessage(393216, obtain).sendToTarget();
        }
    }

    public void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(524288);
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = i3;
            obtain.argi4 = z ? 1 : 0;
            obtain.arg1 = iBinder;
            this.mHandler.obtainMessage(524288, obtain).sendToTarget();
        }
    }

    public void showRecentApps(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(851968);
            this.mHandler.obtainMessage(851968, z ? 1 : 0, 0, (Object) null).sendToTarget();
        }
    }

    public void hideRecentApps(boolean z, boolean z2) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(917504);
            this.mHandler.obtainMessage(917504, z ? 1 : 0, z2 ? 1 : 0, (Object) null).sendToTarget();
        }
    }

    public void toggleSplitScreen() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1966080);
            this.mHandler.obtainMessage(1966080, 0, 0, (Object) null).sendToTarget();
        }
    }

    public void toggleRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(589824);
            Message obtainMessage = this.mHandler.obtainMessage(589824, 0, 0, (Object) null);
            obtainMessage.setAsynchronous(true);
            obtainMessage.sendToTarget();
        }
    }

    public void preloadRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(655360);
            this.mHandler.obtainMessage(655360, 0, 0, (Object) null).sendToTarget();
        }
    }

    public void cancelPreloadRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(720896);
            this.mHandler.obtainMessage(720896, 0, 0, (Object) null).sendToTarget();
        }
    }

    public void dismissKeyboardShortcutsMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2097152);
            this.mHandler.obtainMessage(2097152).sendToTarget();
        }
    }

    public void toggleKeyboardShortcutsMenu(int i) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1638400);
            this.mHandler.obtainMessage(1638400, i, 0).sendToTarget();
        }
    }

    public void showPictureInPictureMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1703936);
            this.mHandler.obtainMessage(1703936).sendToTarget();
        }
    }

    public void setWindowState(int i, int i2, int i3) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(786432, i, i2, Integer.valueOf(i3)).sendToTarget();
        }
    }

    public void showScreenPinningRequest(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1179648, i, 0, (Object) null).sendToTarget();
        }
    }

    public void appTransitionPending(int i) {
        appTransitionPending(i, false);
    }

    public void appTransitionPending(int i, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1245184, i, z ? 1 : 0).sendToTarget();
        }
    }

    public void appTransitionCancelled(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1310720, i, 0).sendToTarget();
        }
    }

    public void appTransitionStarting(int i, long j, long j2) {
        appTransitionStarting(i, j, j2, false);
    }

    public void appTransitionStarting(int i, long j, long j2, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = z ? 1 : 0;
            obtain.arg1 = Long.valueOf(j);
            obtain.arg2 = Long.valueOf(j2);
            this.mHandler.obtainMessage(1376256, obtain).sendToTarget();
        }
    }

    public void appTransitionFinished(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2031616, i, 0).sendToTarget();
        }
    }

    public void showAssistDisclosure() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1441792);
            this.mHandler.obtainMessage(1441792).sendToTarget();
        }
    }

    public void startAssist(Bundle bundle) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1507328);
            this.mHandler.obtainMessage(1507328, bundle).sendToTarget();
        }
    }

    public void onCameraLaunchGestureDetected(int i) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1572864);
            this.mHandler.obtainMessage(1572864, i, 0).sendToTarget();
        }
    }

    public void addQsTile(ComponentName componentName) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1769472, componentName).sendToTarget();
        }
    }

    public void remQsTile(ComponentName componentName) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1835008, componentName).sendToTarget();
        }
    }

    public void clickQsTile(ComponentName componentName) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1900544, componentName).sendToTarget();
        }
    }

    public void handleSystemKey(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2162688, i, 0).sendToTarget();
        }
    }

    public void showPinningEnterExitToast(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2949120, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public void showPinningEscapeToast() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3014656).sendToTarget();
        }
    }

    public void showGlobalActionsMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2228224);
            this.mHandler.obtainMessage(2228224).sendToTarget();
        }
    }

    public void setTopAppHidesStatusBar(boolean z) {
        this.mHandler.removeMessages(2424832);
        this.mHandler.obtainMessage(2424832, z ? 1 : 0, 0).sendToTarget();
    }

    public void showShutdownUi(boolean z, String str) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2359296);
            this.mHandler.obtainMessage(2359296, z ? 1 : 0, 0, str).sendToTarget();
        }
    }

    public void showWirelessChargingAnimation(int i) {
        this.mHandler.removeMessages(2883584);
        this.mHandler.obtainMessage(2883584, i, 0).sendToTarget();
    }

    public void onProposedRotationChanged(int i, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2490368);
            this.mHandler.obtainMessage(2490368, i, z ? 1 : 0, (Object) null).sendToTarget();
        }
    }

    public void showBiometricDialog(Bundle bundle, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, int i, boolean z, int i2) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = bundle;
            obtain.arg2 = iBiometricServiceReceiverInternal;
            obtain.argi1 = i;
            obtain.arg3 = Boolean.valueOf(z);
            obtain.argi2 = i2;
            this.mHandler.obtainMessage(2555904, obtain).sendToTarget();
        }
    }

    public void onBiometricAuthenticated(boolean z, String str, boolean z2) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = Boolean.valueOf(z);
            obtain.arg2 = str;
            obtain.arg3 = Boolean.valueOf(z2);
            this.mHandler.obtainMessage(2621440, obtain).sendToTarget();
        }
    }

    public void onBiometricHelp(String str) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2686976, str).sendToTarget();
        }
    }

    public void onBiometricError(String str) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2752512, str).sendToTarget();
        }
    }

    public void hideBiometricDialog() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2818048).sendToTarget();
        }
    }

    public void showInDisplayFingerprintView() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3145728).sendToTarget();
        }
    }

    public void hideInDisplayFingerprintView() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3211264).sendToTarget();
        }
    }

    public void toggleCameraFlash() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3276800);
            this.mHandler.sendEmptyMessage(3276800);
        }
    }

    public void onDisplayReady(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(458752, i, 0).sendToTarget();
        }
    }

    public void onRecentsAnimationStateChanged(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3080192, z ? 1 : 0, 0).sendToTarget();
        }
    }

    /* access modifiers changed from: private */
    public void handleShowImeButton(int i, IBinder iBinder, int i2, int i3, boolean z) {
        int i4;
        if (i != -1) {
            if (!(InputMethodSystemProperty.MULTI_CLIENT_IME_ENABLED || (i4 = this.mLastUpdatedImeDisplayId) == i || i4 == -1)) {
                sendImeInvisibleStatusForPrevNavBar();
            }
            for (int i5 = 0; i5 < this.mCallbacks.size(); i5++) {
                this.mCallbacks.get(i5).setImeWindowStatus(i, iBinder, i2, i3, z);
            }
            this.mLastUpdatedImeDisplayId = i;
        }
    }

    private void sendImeInvisibleStatusForPrevNavBar() {
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            this.mCallbacks.get(i).setImeWindowStatus(this.mLastUpdatedImeDisplayId, (IBinder) null, 4, 0, false);
        }
    }

    public void killForegroundApp() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3473408);
            this.mHandler.sendEmptyMessage(3473408);
        }
    }

    public void setPartialScreenshot(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3342336);
            this.mHandler.obtainMessage(3342336, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public void leftInLandscapeChanged(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3604480);
            this.mHandler.obtainMessage(3604480, z ? 1 : 0, 0, (Object) null).sendToTarget();
        }
    }

    public void screenPinningStateChanged(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3538944);
            this.mHandler.obtainMessage(3538944, z ? 1 : 0, 0, (Object) null).sendToTarget();
        }
    }

    /* renamed from: com.android.systemui.statusbar.CommandQueue$H */
    private final class C1071H extends Handler {
        private C1071H(Looper looper) {
            super(looper);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:100:0x033a, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:101:0x033c, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).handleShowGlobalActionsMenu();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:103:0x0358, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x035a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).handleSystemKey(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x0378, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x037a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).dismissKeyboardShortcutsMenu();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:109:0x0396, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x0398, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).appTransitionFinished(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x03b6, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x03b8, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).toggleSplitScreen();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x03d4, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x03d6, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).clickTile((android.content.ComponentName) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x03f6, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x03f8, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).remQsTile((android.content.ComponentName) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x0418, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:122:0x041a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).addQsTile((android.content.ComponentName) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x043a, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:125:0x043c, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showPictureInPictureMenu();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:0x0458, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x045a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).toggleKeyboardShortcutsMenu(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:130:0x0478, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:131:0x047a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).onCameraLaunchGestureDetected(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x0498, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x049a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).startAssist((android.os.Bundle) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x04ba, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:137:0x04bc, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showAssistDisclosure();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x0515, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x0517, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).appTransitionCancelled(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x055d, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:159:0x055f, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showScreenPinningRequest(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x05d0, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x05d2, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).setWindowState(r14.arg1, r14.arg2, ((java.lang.Integer) r14.obj).intValue());
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x05fa, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x05fc, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).cancelPreloadRecentApps();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0618, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x061a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).preloadRecentApps();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x0636, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x0638, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).toggleRecentApps();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:198:0x0671, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:199:0x0673, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).onDisplayReady(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0062, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).killForegroundApp();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:210:0x06d5, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:211:0x06d7, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).animateExpandSettingsPanel((java.lang.String) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:221:0x071f, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:222:0x0721, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).animateExpandNotificationsPanel();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0080, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0082, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).toggleSettingsPanel();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x009e, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a0, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).setPartialScreenshot(((java.lang.Boolean) r14.obj).booleanValue());
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c4, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c6, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).toggleCameraFlash();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e2, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:328:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:329:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e4, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).hideInDisplayFingerprintView();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:330:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:331:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:332:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:333:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:335:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:336:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:337:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:338:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:339:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:340:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:344:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:345:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:346:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:347:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:348:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:349:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0100, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:350:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:351:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:352:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:353:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:354:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:355:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:356:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:357:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:359:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0102, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showInDisplayFingerprintView();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:361:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:364:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:365:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:366:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:367:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:368:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:369:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:371:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0144, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0146, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showPinningEscapeToast();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0162, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0164, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showPinningEnterExitToast(((java.lang.Boolean) r14.obj).booleanValue());
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0188, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x018a, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).showWirelessChargingAnimation(r14.arg1);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x01a8, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x01aa, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).hideBiometricDialog();
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x01c6, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x01c8, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).onBiometricError((java.lang.String) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x01e8, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x01ea, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).onBiometricHelp((java.lang.String) r14.obj);
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x031c, code lost:
            if (r1 >= com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).size()) goto L_0x07b7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x031e, code lost:
            ((com.android.systemui.statusbar.CommandQueue.Callbacks) com.android.systemui.statusbar.CommandQueue.access$100(r13.this$0).get(r1)).togglePanel();
            r1 = r1 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r14) {
            /*
                r13 = this;
                int r0 = r14.what
                r1 = -65536(0xffffffffffff0000, float:NaN)
                r0 = r0 & r1
                r1 = 0
                r2 = 1
                switch(r0) {
                    case 65536: goto L_0x0763;
                    case 131072: goto L_0x0733;
                    case 196608: goto L_0x0715;
                    case 262144: goto L_0x06ed;
                    case 327680: goto L_0x06cb;
                    case 393216: goto L_0x0687;
                    case 458752: goto L_0x0667;
                    case 524288: goto L_0x064a;
                    case 589824: goto L_0x062c;
                    case 655360: goto L_0x060e;
                    case 720896: goto L_0x05f0;
                    case 786432: goto L_0x05c6;
                    case 851968: goto L_0x05a0;
                    case 917504: goto L_0x0573;
                    case 1179648: goto L_0x0553;
                    case 1245184: goto L_0x052b;
                    case 1310720: goto L_0x050b;
                    case 1376256: goto L_0x04ce;
                    case 1441792: goto L_0x04b0;
                    case 1507328: goto L_0x048e;
                    case 1572864: goto L_0x046e;
                    case 1638400: goto L_0x044e;
                    case 1703936: goto L_0x0430;
                    case 1769472: goto L_0x040e;
                    case 1835008: goto L_0x03ec;
                    case 1900544: goto L_0x03ca;
                    case 1966080: goto L_0x03ac;
                    case 2031616: goto L_0x038c;
                    case 2097152: goto L_0x036e;
                    case 2162688: goto L_0x034e;
                    case 2228224: goto L_0x0330;
                    case 2293760: goto L_0x0312;
                    case 2359296: goto L_0x02e8;
                    case 2424832: goto L_0x02c2;
                    case 2490368: goto L_0x029a;
                    case 2555904: goto L_0x023b;
                    case 2621440: goto L_0x0200;
                    case 2686976: goto L_0x01de;
                    case 2752512: goto L_0x01bc;
                    case 2818048: goto L_0x019e;
                    case 2883584: goto L_0x017e;
                    case 2949120: goto L_0x0158;
                    case 3014656: goto L_0x013a;
                    case 3080192: goto L_0x0114;
                    case 3145728: goto L_0x00f6;
                    case 3211264: goto L_0x00d8;
                    case 3276800: goto L_0x00ba;
                    case 3342336: goto L_0x0094;
                    case 3407872: goto L_0x0076;
                    case 3473408: goto L_0x0058;
                    case 3538944: goto L_0x0032;
                    case 3604480: goto L_0x000c;
                    default: goto L_0x000a;
                }
            L_0x000a:
                goto L_0x07b7
            L_0x000c:
                r0 = r1
            L_0x000d:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 == 0) goto L_0x002b
                r4 = r2
                goto L_0x002c
            L_0x002b:
                r4 = r1
            L_0x002c:
                r3.leftInLandscapeChanged(r4)
                int r0 = r0 + 1
                goto L_0x000d
            L_0x0032:
                r0 = r1
            L_0x0033:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 == 0) goto L_0x0051
                r4 = r2
                goto L_0x0052
            L_0x0051:
                r4 = r1
            L_0x0052:
                r3.screenPinningStateChanged(r4)
                int r0 = r0 + 1
                goto L_0x0033
            L_0x0058:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.killForegroundApp()
                int r1 = r1 + 1
                goto L_0x0058
            L_0x0076:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.toggleSettingsPanel()
                int r1 = r1 + 1
                goto L_0x0076
            L_0x0094:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                r0.setPartialScreenshot(r2)
                int r1 = r1 + 1
                goto L_0x0094
            L_0x00ba:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.toggleCameraFlash()
                int r1 = r1 + 1
                goto L_0x00ba
            L_0x00d8:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.hideInDisplayFingerprintView()
                int r1 = r1 + 1
                goto L_0x00d8
            L_0x00f6:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.showInDisplayFingerprintView()
                int r1 = r1 + 1
                goto L_0x00f6
            L_0x0114:
                r0 = r1
            L_0x0115:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 <= 0) goto L_0x0133
                r4 = r2
                goto L_0x0134
            L_0x0133:
                r4 = r1
            L_0x0134:
                r3.onRecentsAnimationStateChanged(r4)
                int r0 = r0 + 1
                goto L_0x0115
            L_0x013a:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.showPinningEscapeToast()
                int r1 = r1 + 1
                goto L_0x013a
            L_0x0158:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                r0.showPinningEnterExitToast(r2)
                int r1 = r1 + 1
                goto L_0x0158
            L_0x017e:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.showWirelessChargingAnimation(r2)
                int r1 = r1 + 1
                goto L_0x017e
            L_0x019e:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.hideBiometricDialog()
                int r1 = r1 + 1
                goto L_0x019e
            L_0x01bc:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                java.lang.String r2 = (java.lang.String) r2
                r0.onBiometricError(r2)
                int r1 = r1 + 1
                goto L_0x01bc
            L_0x01de:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                java.lang.String r2 = (java.lang.String) r2
                r0.onBiometricHelp(r2)
                int r1 = r1 + 1
                goto L_0x01de
            L_0x0200:
                java.lang.Object r14 = r14.obj
                com.android.internal.os.SomeArgs r14 = (com.android.internal.os.SomeArgs) r14
            L_0x0204:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x0236
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.arg1
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                java.lang.Object r3 = r14.arg2
                java.lang.String r3 = (java.lang.String) r3
                java.lang.Object r4 = r14.arg3
                java.lang.Boolean r4 = (java.lang.Boolean) r4
                boolean r4 = r4.booleanValue()
                r0.onBiometricAuthenticated(r2, r3, r4)
                int r1 = r1 + 1
                goto L_0x0204
            L_0x0236:
                r14.recycle()
                goto L_0x07b7
            L_0x023b:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                android.os.Handler r0 = r0.mHandler
                r2 = 2752512(0x2a0000, float:3.857091E-39)
                r0.removeMessages(r2)
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                android.os.Handler r0 = r0.mHandler
                r2 = 2686976(0x290000, float:3.765255E-39)
                r0.removeMessages(r2)
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                android.os.Handler r0 = r0.mHandler
                r2 = 2621440(0x280000, float:3.67342E-39)
                r0.removeMessages(r2)
                java.lang.Object r14 = r14.obj
                com.android.internal.os.SomeArgs r14 = (com.android.internal.os.SomeArgs) r14
            L_0x0260:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x0295
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                r2 = r0
                com.android.systemui.statusbar.CommandQueue$Callbacks r2 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r2
                java.lang.Object r0 = r14.arg1
                r3 = r0
                android.os.Bundle r3 = (android.os.Bundle) r3
                java.lang.Object r0 = r14.arg2
                r4 = r0
                android.hardware.biometrics.IBiometricServiceReceiverInternal r4 = (android.hardware.biometrics.IBiometricServiceReceiverInternal) r4
                int r5 = r14.argi1
                java.lang.Object r0 = r14.arg3
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                int r7 = r14.argi2
                r2.showBiometricDialog(r3, r4, r5, r6, r7)
                int r1 = r1 + 1
                goto L_0x0260
            L_0x0295:
                r14.recycle()
                goto L_0x07b7
            L_0x029a:
                r0 = r1
            L_0x029b:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                int r5 = r14.arg2
                if (r5 == 0) goto L_0x02bb
                r5 = r2
                goto L_0x02bc
            L_0x02bb:
                r5 = r1
            L_0x02bc:
                r3.onRotationProposal(r4, r5)
                int r0 = r0 + 1
                goto L_0x029b
            L_0x02c2:
                r0 = r1
            L_0x02c3:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 == 0) goto L_0x02e1
                r4 = r2
                goto L_0x02e2
            L_0x02e1:
                r4 = r1
            L_0x02e2:
                r3.setTopAppHidesStatusBar(r4)
                int r0 = r0 + 1
                goto L_0x02c3
            L_0x02e8:
                r0 = r1
            L_0x02e9:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 == 0) goto L_0x0307
                r4 = r2
                goto L_0x0308
            L_0x0307:
                r4 = r1
            L_0x0308:
                java.lang.Object r5 = r14.obj
                java.lang.String r5 = (java.lang.String) r5
                r3.handleShowShutdownUi(r4, r5)
                int r0 = r0 + 1
                goto L_0x02e9
            L_0x0312:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.togglePanel()
                int r1 = r1 + 1
                goto L_0x0312
            L_0x0330:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.handleShowGlobalActionsMenu()
                int r1 = r1 + 1
                goto L_0x0330
            L_0x034e:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.handleSystemKey(r2)
                int r1 = r1 + 1
                goto L_0x034e
            L_0x036e:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.dismissKeyboardShortcutsMenu()
                int r1 = r1 + 1
                goto L_0x036e
            L_0x038c:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.appTransitionFinished(r2)
                int r1 = r1 + 1
                goto L_0x038c
            L_0x03ac:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.toggleSplitScreen()
                int r1 = r1 + 1
                goto L_0x03ac
            L_0x03ca:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                android.content.ComponentName r2 = (android.content.ComponentName) r2
                r0.clickTile(r2)
                int r1 = r1 + 1
                goto L_0x03ca
            L_0x03ec:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                android.content.ComponentName r2 = (android.content.ComponentName) r2
                r0.remQsTile(r2)
                int r1 = r1 + 1
                goto L_0x03ec
            L_0x040e:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                android.content.ComponentName r2 = (android.content.ComponentName) r2
                r0.addQsTile(r2)
                int r1 = r1 + 1
                goto L_0x040e
            L_0x0430:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.showPictureInPictureMenu()
                int r1 = r1 + 1
                goto L_0x0430
            L_0x044e:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.toggleKeyboardShortcutsMenu(r2)
                int r1 = r1 + 1
                goto L_0x044e
            L_0x046e:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.onCameraLaunchGestureDetected(r2)
                int r1 = r1 + 1
                goto L_0x046e
            L_0x048e:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                android.os.Bundle r2 = (android.os.Bundle) r2
                r0.startAssist(r2)
                int r1 = r1 + 1
                goto L_0x048e
            L_0x04b0:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.showAssistDisclosure()
                int r1 = r1 + 1
                goto L_0x04b0
            L_0x04ce:
                java.lang.Object r14 = r14.obj
                com.android.internal.os.SomeArgs r14 = (com.android.internal.os.SomeArgs) r14
                r0 = r1
            L_0x04d3:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                r4 = r3
                com.android.systemui.statusbar.CommandQueue$Callbacks r4 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r4
                int r5 = r14.argi1
                java.lang.Object r3 = r14.arg1
                java.lang.Long r3 = (java.lang.Long) r3
                long r6 = r3.longValue()
                java.lang.Object r3 = r14.arg2
                java.lang.Long r3 = (java.lang.Long) r3
                long r8 = r3.longValue()
                int r3 = r14.argi2
                if (r3 == 0) goto L_0x0504
                r10 = r2
                goto L_0x0505
            L_0x0504:
                r10 = r1
            L_0x0505:
                r4.appTransitionStarting(r5, r6, r8, r10)
                int r0 = r0 + 1
                goto L_0x04d3
            L_0x050b:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.appTransitionCancelled(r2)
                int r1 = r1 + 1
                goto L_0x050b
            L_0x052b:
                r0 = r1
            L_0x052c:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                int r5 = r14.arg2
                if (r5 == 0) goto L_0x054c
                r5 = r2
                goto L_0x054d
            L_0x054c:
                r5 = r1
            L_0x054d:
                r3.appTransitionPending(r4, r5)
                int r0 = r0 + 1
                goto L_0x052c
            L_0x0553:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.showScreenPinningRequest(r2)
                int r1 = r1 + 1
                goto L_0x0553
            L_0x0573:
                r0 = r1
            L_0x0574:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 == 0) goto L_0x0592
                r4 = r2
                goto L_0x0593
            L_0x0592:
                r4 = r1
            L_0x0593:
                int r5 = r14.arg2
                if (r5 == 0) goto L_0x0599
                r5 = r2
                goto L_0x059a
            L_0x0599:
                r5 = r1
            L_0x059a:
                r3.hideRecentApps(r4, r5)
                int r0 = r0 + 1
                goto L_0x0574
            L_0x05a0:
                r0 = r1
            L_0x05a1:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                if (r4 == 0) goto L_0x05bf
                r4 = r2
                goto L_0x05c0
            L_0x05bf:
                r4 = r1
            L_0x05c0:
                r3.showRecentApps(r4)
                int r0 = r0 + 1
                goto L_0x05a1
            L_0x05c6:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                int r3 = r14.arg2
                java.lang.Object r4 = r14.obj
                java.lang.Integer r4 = (java.lang.Integer) r4
                int r4 = r4.intValue()
                r0.setWindowState(r2, r3, r4)
                int r1 = r1 + 1
                goto L_0x05c6
            L_0x05f0:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.cancelPreloadRecentApps()
                int r1 = r1 + 1
                goto L_0x05f0
            L_0x060e:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.preloadRecentApps()
                int r1 = r1 + 1
                goto L_0x060e
            L_0x062c:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.toggleRecentApps()
                int r1 = r1 + 1
                goto L_0x062c
            L_0x064a:
                java.lang.Object r14 = r14.obj
                com.android.internal.os.SomeArgs r14 = (com.android.internal.os.SomeArgs) r14
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                int r4 = r14.argi1
                java.lang.Object r13 = r14.arg1
                r5 = r13
                android.os.IBinder r5 = (android.os.IBinder) r5
                int r6 = r14.argi2
                int r7 = r14.argi3
                int r13 = r14.argi4
                if (r13 == 0) goto L_0x0661
                r8 = r2
                goto L_0x0662
            L_0x0661:
                r8 = r1
            L_0x0662:
                r3.handleShowImeButton(r4, r5, r6, r7, r8)
                goto L_0x07b7
            L_0x0667:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                int r2 = r14.arg1
                r0.onDisplayReady(r2)
                int r1 = r1 + 1
                goto L_0x0667
            L_0x0687:
                java.lang.Object r14 = r14.obj
                com.android.internal.os.SomeArgs r14 = (com.android.internal.os.SomeArgs) r14
                r0 = r1
            L_0x068c:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x06c6
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                r4 = r3
                com.android.systemui.statusbar.CommandQueue$Callbacks r4 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r4
                int r5 = r14.argi1
                int r6 = r14.argi2
                int r7 = r14.argi3
                int r8 = r14.argi4
                int r9 = r14.argi5
                java.lang.Object r3 = r14.arg1
                r10 = r3
                android.graphics.Rect r10 = (android.graphics.Rect) r10
                java.lang.Object r3 = r14.arg2
                r11 = r3
                android.graphics.Rect r11 = (android.graphics.Rect) r11
                int r3 = r14.argi6
                if (r3 != r2) goto L_0x06bf
                r12 = r2
                goto L_0x06c0
            L_0x06bf:
                r12 = r1
            L_0x06c0:
                r4.setSystemUiVisibility(r5, r6, r7, r8, r9, r10, r11, r12)
                int r0 = r0 + 1
                goto L_0x068c
            L_0x06c6:
                r14.recycle()
                goto L_0x07b7
            L_0x06cb:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                java.lang.String r2 = (java.lang.String) r2
                r0.animateExpandSettingsPanel(r2)
                int r1 = r1 + 1
                goto L_0x06cb
            L_0x06ed:
                r0 = r1
            L_0x06ee:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.arg1
                int r5 = r14.arg2
                if (r5 == 0) goto L_0x070e
                r5 = r2
                goto L_0x070f
            L_0x070e:
                r5 = r1
            L_0x070f:
                r3.animateCollapsePanels(r4, r5)
                int r0 = r0 + 1
                goto L_0x06ee
            L_0x0715:
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                int r14 = r14.size()
                if (r1 >= r14) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r14 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r14 = r14.mCallbacks
                java.lang.Object r14 = r14.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r14 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r14
                r14.animateExpandNotificationsPanel()
                int r1 = r1 + 1
                goto L_0x0715
            L_0x0733:
                java.lang.Object r14 = r14.obj
                com.android.internal.os.SomeArgs r14 = (com.android.internal.os.SomeArgs) r14
                r0 = r1
            L_0x0738:
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                int r3 = r3.size()
                if (r0 >= r3) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r3 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r3 = r3.mCallbacks
                java.lang.Object r3 = r3.get(r0)
                com.android.systemui.statusbar.CommandQueue$Callbacks r3 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r3
                int r4 = r14.argi1
                int r5 = r14.argi2
                int r6 = r14.argi3
                int r7 = r14.argi4
                if (r7 == 0) goto L_0x075c
                r7 = r2
                goto L_0x075d
            L_0x075c:
                r7 = r1
            L_0x075d:
                r3.disable(r4, r5, r6, r7)
                int r0 = r0 + 1
                goto L_0x0738
            L_0x0763:
                int r0 = r14.arg1
                if (r0 == r2) goto L_0x078d
                r2 = 2
                if (r0 == r2) goto L_0x076b
                goto L_0x07b7
            L_0x076b:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.obj
                java.lang.String r2 = (java.lang.String) r2
                r0.removeIcon(r2)
                int r1 = r1 + 1
                goto L_0x076b
            L_0x078d:
                java.lang.Object r14 = r14.obj
                android.util.Pair r14 = (android.util.Pair) r14
            L_0x0791:
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                int r0 = r0.size()
                if (r1 >= r0) goto L_0x07b7
                com.android.systemui.statusbar.CommandQueue r0 = com.android.systemui.statusbar.CommandQueue.this
                java.util.ArrayList r0 = r0.mCallbacks
                java.lang.Object r0 = r0.get(r1)
                com.android.systemui.statusbar.CommandQueue$Callbacks r0 = (com.android.systemui.statusbar.CommandQueue.Callbacks) r0
                java.lang.Object r2 = r14.first
                java.lang.String r2 = (java.lang.String) r2
                java.lang.Object r3 = r14.second
                com.android.internal.statusbar.StatusBarIcon r3 = (com.android.internal.statusbar.StatusBarIcon) r3
                r0.setIcon(r2, r3)
                int r1 = r1 + 1
                goto L_0x0791
            L_0x07b7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.CommandQueue.C1071H.handleMessage(android.os.Message):void");
        }
    }

    public static class CommandQueueStart extends SystemUI {
        public void start() {
            putComponent(CommandQueue.class, new CommandQueue(this.mContext));
        }
    }
}
