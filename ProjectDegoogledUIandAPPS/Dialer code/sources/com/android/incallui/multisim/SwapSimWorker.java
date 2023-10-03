package com.android.incallui.multisim;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.preferredsim.suggestion.SimSuggestionComponent;
import com.android.dialer.preferredsim.suggestion.stub.StubSuggestionProvider;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallListener;
import com.android.incallui.incalluilock.InCallUiLock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SwapSimWorker implements DialerExecutor.Worker<Void, Void>, DialerCallListener, CallList.Listener {
    private final DialerCall call;
    private final CallList callList;
    private final Context context;
    private final CountDownLatch dialingLatch;
    private final CountDownLatch disconnectLatch;
    private final InCallUiLock inCallUiLock;
    private CountDownLatch latchForTest;
    private final String number;
    private final PhoneAccountHandle otherAccount;
    private final int timeoutMillis;

    public SwapSimWorker(Context context2, DialerCall dialerCall, CallList callList2, PhoneAccountHandle phoneAccountHandle, InCallUiLock inCallUiLock2) {
        this(context2, dialerCall, callList2, phoneAccountHandle, inCallUiLock2, 5000);
    }

    public /* synthetic */ void lambda$doInBackground$0$SwapSimWorker() {
        this.call.removeListener(this);
        this.callList.removeListener(this);
        this.inCallUiLock.release();
    }

    public void onCallListChange(CallList callList2) {
        if (callList2.getOutgoingCall() != null) {
            this.dialingLatch.countDown();
        }
    }

    public void onDialerCallChildNumberChange() {
    }

    public void onDialerCallDisconnect() {
        this.disconnectLatch.countDown();
    }

    public void onDialerCallLastForwardedNumberChange() {
    }

    public void onDialerCallSessionModificationStateChange() {
    }

    public void onDialerCallUpdate() {
    }

    public void onDialerCallUpgradeToVideo() {
    }

    public void onDisconnect(DialerCall dialerCall) {
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onHandoverToWifiFailure() {
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi() {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover() {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }

    /* access modifiers changed from: package-private */
    public void setLatchForTest(CountDownLatch countDownLatch) {
        this.latchForTest = countDownLatch;
    }

    SwapSimWorker(Context context2, DialerCall dialerCall, CallList callList2, PhoneAccountHandle phoneAccountHandle, InCallUiLock inCallUiLock2, int i) {
        this.disconnectLatch = new CountDownLatch(1);
        this.dialingLatch = new CountDownLatch(1);
        Assert.isMainThread();
        this.context = context2;
        this.call = dialerCall;
        this.callList = callList2;
        this.otherAccount = phoneAccountHandle;
        this.inCallUiLock = inCallUiLock2;
        this.timeoutMillis = i;
        this.number = dialerCall.getNumber();
        dialerCall.addListener(this);
        dialerCall.disconnect();
    }

    public Void doInBackground(Void voidR) {
        $$Lambda$SwapSimWorker$Bkaet0OtUR5eJwod7hVFDYl97g r7;
        try {
            ((StubSuggestionProvider) SimSuggestionComponent.get(this.context).getSuggestionProvider()).reportIncorrectSuggestion(this.context, this.number, this.otherAccount);
            if (!PermissionsUtil.hasPhonePermissions(this.context)) {
                LogUtil.m8e("SwapSimWorker.doInBackground", "missing phone permission", new Object[0]);
                r7 = new Runnable() {
                    public final void run() {
                        SwapSimWorker.this.lambda$doInBackground$0$SwapSimWorker();
                    }
                };
            } else if (!this.disconnectLatch.await((long) this.timeoutMillis, TimeUnit.MILLISECONDS)) {
                LogUtil.m8e("SwapSimWorker.doInBackground", "timeout waiting for call to disconnect", new Object[0]);
                r7 = new Runnable() {
                    public final void run() {
                        SwapSimWorker.this.lambda$doInBackground$0$SwapSimWorker();
                    }
                };
            } else {
                LogUtil.m9i("SwapSimWorker.doInBackground", "call disconnected, redialing", new Object[0]);
                Bundle bundle = new Bundle();
                bundle.putParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE", this.otherAccount);
                this.callList.addListener(this);
                ((TelecomManager) this.context.getSystemService(TelecomManager.class)).placeCall(Uri.fromParts("tel", this.number, (String) null), bundle);
                if (this.latchForTest != null) {
                    this.latchForTest.countDown();
                }
                if (!this.dialingLatch.await((long) this.timeoutMillis, TimeUnit.MILLISECONDS)) {
                    LogUtil.m8e("SwapSimWorker.doInBackground", "timeout waiting for call to dial", new Object[0]);
                }
                r7 = new Runnable() {
                    public final void run() {
                        SwapSimWorker.this.lambda$doInBackground$0$SwapSimWorker();
                    }
                };
            }
        } catch (InterruptedException e) {
            LogUtil.m7e("SwapSimWorker.doInBackground", "interrupted", (Throwable) e);
            Thread.currentThread().interrupt();
            r7 = new Runnable() {
                public final void run() {
                    SwapSimWorker.this.lambda$doInBackground$0$SwapSimWorker();
                }
            };
        } catch (Throwable th) {
            DialerExecutorModule.postOnUiThread(new Runnable() {
                public final void run() {
                    SwapSimWorker.this.lambda$doInBackground$0$SwapSimWorker();
                }
            });
            throw th;
        }
        DialerExecutorModule.postOnUiThread(r7);
        return null;
    }
}
