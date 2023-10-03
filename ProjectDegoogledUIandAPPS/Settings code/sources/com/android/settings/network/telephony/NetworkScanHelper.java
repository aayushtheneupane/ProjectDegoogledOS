package com.android.settings.network.telephony;

import android.telephony.CellInfo;
import android.telephony.NetworkScan;
import android.telephony.NetworkScanRequest;
import android.telephony.RadioAccessSpecifier;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyScanManager;
import android.util.Log;
import com.android.internal.telephony.CellNetworkScanResult;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class NetworkScanHelper {
    private static final NetworkScanRequest NETWORK_SCAN_REQUEST = new NetworkScanRequest(0, new RadioAccessSpecifier[]{new RadioAccessSpecifier(1, (int[]) null, (int[]) null), new RadioAccessSpecifier(3, (int[]) null, (int[]) null), new RadioAccessSpecifier(2, (int[]) null, (int[]) null)}, 5, 300, true, 3, (ArrayList) null);
    private final Executor mExecutor;
    private final TelephonyScanManager.NetworkScanCallback mInternalNetworkScanCallback = new NetworkScanCallbackImpl();
    private final NetworkScanCallback mNetworkScanCallback;
    private ListenableFuture<List<CellInfo>> mNetworkScanFuture;
    private NetworkScan mNetworkScanRequester;
    private final TelephonyManager mTelephonyManager;

    public interface NetworkScanCallback {
        void onComplete();

        void onError(int i);

        void onResults(List<CellInfo> list);
    }

    /* access modifiers changed from: private */
    public static int convertToScanErrorCode(int i) {
        return i != 2 ? 1 : 10000;
    }

    public NetworkScanHelper(TelephonyManager telephonyManager, NetworkScanCallback networkScanCallback, Executor executor) {
        this.mTelephonyManager = telephonyManager;
        this.mNetworkScanCallback = networkScanCallback;
        this.mExecutor = executor;
    }

    public void startNetworkScan(int i) {
        if (i == 1) {
            this.mNetworkScanFuture = SettableFuture.create();
            Futures.addCallback(this.mNetworkScanFuture, new FutureCallback<List<CellInfo>>() {
                public void onSuccess(List<CellInfo> list) {
                    NetworkScanHelper.this.onResults(list);
                    NetworkScanHelper.this.onComplete();
                }

                public void onFailure(Throwable th) {
                    NetworkScanHelper.this.onError(Integer.parseInt(th.getMessage()));
                }
            });
            this.mExecutor.execute(new NetworkScanSyncTask(this.mTelephonyManager, (SettableFuture) this.mNetworkScanFuture));
        } else if (i == 2) {
            this.mNetworkScanRequester = this.mTelephonyManager.requestNetworkScan(NETWORK_SCAN_REQUEST, this.mExecutor, this.mInternalNetworkScanCallback);
        }
    }

    public void stopNetworkQuery() {
        NetworkScan networkScan = this.mNetworkScanRequester;
        if (networkScan != null) {
            networkScan.stopScan();
            this.mNetworkScanFuture = null;
        }
        ListenableFuture<List<CellInfo>> listenableFuture = this.mNetworkScanFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(true);
            this.mNetworkScanFuture = null;
        }
    }

    /* access modifiers changed from: private */
    public void onResults(List<CellInfo> list) {
        this.mNetworkScanCallback.onResults(list);
    }

    /* access modifiers changed from: private */
    public void onComplete() {
        this.mNetworkScanCallback.onComplete();
    }

    /* access modifiers changed from: private */
    public void onError(int i) {
        this.mNetworkScanCallback.onError(i);
    }

    private final class NetworkScanCallbackImpl extends TelephonyScanManager.NetworkScanCallback {
        private NetworkScanCallbackImpl() {
        }

        public void onResults(List<CellInfo> list) {
            Log.d("NetworkScanHelper", "Async scan onResults() results = " + CellInfoUtil.cellInfoListToString(list));
            NetworkScanHelper.this.onResults(list);
        }

        public void onComplete() {
            Log.d("NetworkScanHelper", "async scan onComplete()");
            NetworkScanHelper.this.onComplete();
        }

        public void onError(int i) {
            Log.d("NetworkScanHelper", "async scan onError() errorCode = " + i);
            NetworkScanHelper.this.onError(i);
        }
    }

    private static final class NetworkScanSyncTask implements Runnable {
        private final SettableFuture<List<CellInfo>> mCallback;
        private final TelephonyManager mTelephonyManager;

        NetworkScanSyncTask(TelephonyManager telephonyManager, SettableFuture<List<CellInfo>> settableFuture) {
            this.mTelephonyManager = telephonyManager;
            this.mCallback = settableFuture;
        }

        public void run() {
            CellNetworkScanResult availableNetworks = this.mTelephonyManager.getAvailableNetworks();
            if (availableNetworks.getStatus() == 1) {
                List list = (List) availableNetworks.getOperators().stream().map(C0968x8d05bedc.INSTANCE).collect(Collectors.toList());
                Log.d("NetworkScanHelper", "Sync network scan completed, cellInfos = " + CellInfoUtil.cellInfoListToString(list));
                this.mCallback.set(list);
                return;
            }
            Throwable th = new Throwable(Integer.toString(NetworkScanHelper.convertToScanErrorCode(availableNetworks.getStatus())));
            this.mCallback.setException(th);
            Log.d("NetworkScanHelper", "Sync network scan error, ex = " + th);
        }
    }
}
