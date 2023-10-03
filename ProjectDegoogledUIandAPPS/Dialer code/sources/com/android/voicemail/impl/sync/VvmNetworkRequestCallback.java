package com.android.voicemail.impl.sync;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import com.android.dialer.common.Assert;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;

@TargetApi(26)
public abstract class VvmNetworkRequestCallback extends ConnectivityManager.NetworkCallback {
    private final OmtpVvmCarrierConfigHelper carrierConfigHelper;
    private ConnectivityManager connectivityManager;
    protected Context context;
    protected NetworkRequest networkRequest;
    protected PhoneAccountHandle phoneAccount;
    private boolean released = false;
    private boolean requestSent = false;
    /* access modifiers changed from: private */
    public boolean resultReceived = false;
    private final VoicemailStatus$Editor status;

    public VvmNetworkRequestCallback(Context context2, PhoneAccountHandle phoneAccountHandle, VoicemailStatus$Editor voicemailStatus$Editor) {
        this.context = context2;
        this.phoneAccount = phoneAccountHandle;
        this.status = voicemailStatus$Editor;
        this.carrierConfigHelper = new OmtpVvmCarrierConfigHelper(context2, this.phoneAccount);
        this.networkRequest = createNetworkRequest();
    }

    private NetworkRequest createNetworkRequest() {
        NetworkRequest.Builder addCapability = new NetworkRequest.Builder().addCapability(12);
        TelephonyManager createForPhoneAccountHandle = ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(this.phoneAccount);
        Assert.isNotNull(createForPhoneAccountHandle);
        if (this.carrierConfigHelper.isCellularDataRequired()) {
            addCapability.addTransportType(0).setNetworkSpecifier(createForPhoneAccountHandle.getNetworkSpecifier());
        }
        return addCapability.build();
    }

    public VoicemailStatus$Editor getVoicemailStatusEditor() {
        return this.status;
    }

    public void onAvailable(Network network) {
        super.onAvailable(network);
        this.resultReceived = true;
    }

    public void onFailed(String str) {
        VvmLog.m45i("VvmNetworkRequest", "onFailed: " + str);
        if (this.carrierConfigHelper.isCellularDataRequired()) {
            this.carrierConfigHelper.handleEvent(this.status, OmtpEvents.DATA_NO_CONNECTION_CELLULAR_REQUIRED);
        } else {
            this.carrierConfigHelper.handleEvent(this.status, OmtpEvents.DATA_NO_CONNECTION);
        }
        releaseNetwork();
    }

    public void onLost(Network network) {
        VvmLog.m45i("VvmNetworkRequest", "onLost");
        this.resultReceived = true;
        onFailed("lost");
    }

    public void onUnavailable() {
        VvmLog.m45i("VvmNetworkRequest", "onUnavailable");
        this.resultReceived = true;
        onFailed("timeout");
    }

    public void releaseNetwork() {
        VvmLog.m45i("VvmNetworkRequest", "releaseNetwork");
        if (!this.released) {
            if (this.connectivityManager == null) {
                this.connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
            }
            this.connectivityManager.unregisterNetworkCallback(this);
            this.released = true;
            return;
        }
        VvmLog.m46w("VvmNetworkRequest", "already released");
    }

    public void requestNetwork() {
        if (this.requestSent) {
            VvmLog.m43e("VvmNetworkRequest", "requestNetwork() called twice");
            return;
        }
        this.requestSent = true;
        if (this.connectivityManager == null) {
            this.connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        }
        this.connectivityManager.requestNetwork(this.networkRequest, this);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                if (!VvmNetworkRequestCallback.this.resultReceived) {
                    VvmNetworkRequestCallback.this.onFailed("timeout");
                }
            }
        }, 60000);
    }

    public VvmNetworkRequestCallback(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, PhoneAccountHandle phoneAccountHandle, VoicemailStatus$Editor voicemailStatus$Editor) {
        this.context = omtpVvmCarrierConfigHelper.getContext();
        this.phoneAccount = phoneAccountHandle;
        this.status = voicemailStatus$Editor;
        this.carrierConfigHelper = omtpVvmCarrierConfigHelper;
        this.networkRequest = createNetworkRequest();
    }
}
