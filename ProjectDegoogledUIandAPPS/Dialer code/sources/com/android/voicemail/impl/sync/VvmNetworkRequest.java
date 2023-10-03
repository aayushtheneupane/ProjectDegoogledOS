package com.android.voicemail.impl.sync;

import android.annotation.TargetApi;
import android.net.Network;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;
import java.io.Closeable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@TargetApi(26)
public class VvmNetworkRequest {

    private static class FutureNetworkRequestCallback extends VvmNetworkRequestCallback {
        private final CompletableFuture<NetworkWrapper> future = new CompletableFuture<>();

        public FutureNetworkRequestCallback(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, PhoneAccountHandle phoneAccountHandle, VoicemailStatus$Editor voicemailStatus$Editor) {
            super(omtpVvmCarrierConfigHelper, phoneAccountHandle, voicemailStatus$Editor);
        }

        public Future<NetworkWrapper> getFuture() {
            return this.future;
        }

        public void onAvailable(Network network) {
            super.onAvailable(network);
            this.future.complete(new NetworkWrapper(network, this, (C07841) null));
        }

        public void onFailed(String str) {
            super.onFailed(str);
            this.future.complete((Object) null);
        }
    }

    public static class NetworkWrapper implements Closeable {
        private final VvmNetworkRequestCallback callback;
        private final Network network;

        /* synthetic */ NetworkWrapper(Network network2, VvmNetworkRequestCallback vvmNetworkRequestCallback, C07841 r3) {
            this.network = network2;
            this.callback = vvmNetworkRequestCallback;
        }

        public void close() {
            this.callback.releaseNetwork();
        }

        public Network get() {
            return this.network;
        }
    }

    public static class RequestFailedException extends Exception {
        /* synthetic */ RequestFailedException(Throwable th, C07841 r2) {
            super(th);
        }
    }

    public static NetworkWrapper getNetwork(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, PhoneAccountHandle phoneAccountHandle, VoicemailStatus$Editor voicemailStatus$Editor) throws RequestFailedException {
        FutureNetworkRequestCallback futureNetworkRequestCallback = new FutureNetworkRequestCallback(omtpVvmCarrierConfigHelper, phoneAccountHandle, voicemailStatus$Editor);
        futureNetworkRequestCallback.requestNetwork();
        try {
            return futureNetworkRequestCallback.getFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            futureNetworkRequestCallback.releaseNetwork();
            VvmLog.m44e("VvmNetworkRequest", "can't get future network", e);
            throw new RequestFailedException(e, (C07841) null);
        }
    }
}
