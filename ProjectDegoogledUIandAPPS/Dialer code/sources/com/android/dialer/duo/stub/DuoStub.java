package com.android.dialer.duo.stub;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.DuoListener;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

public class DuoStub implements Duo {
    public Optional<Intent> getCallIntent(String str) {
        Assert.isMainThread();
        Assert.isNotNull(str);
        return Optional.absent();
    }

    public int getIncomingCallTypeText() {
        return -1;
    }

    public int getLogo() {
        return -1;
    }

    public boolean isDuoAccount(PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isDuoAccount(String str) {
        return false;
    }

    public boolean isEnabled(Context context) {
        return false;
    }

    public boolean isInstalled(Context context) {
        return false;
    }

    public boolean isReachable(Context context, String str) {
        Assert.isMainThread();
        Assert.isNotNull(context);
        return false;
    }

    public void registerListener(DuoListener duoListener) {
        Assert.isMainThread();
        Assert.isNotNull(duoListener);
    }

    public void reloadReachability(Context context) {
    }

    public void requestUpgrade(Context context, Call call) {
        Assert.isMainThread();
        Assert.isNotNull(call);
    }

    public Optional<Boolean> supportsUpgrade(Context context, String str, PhoneAccountHandle phoneAccountHandle) {
        Assert.isMainThread();
        Assert.isNotNull(context);
        return Optional.m67of(false);
    }

    public void unregisterListener(DuoListener duoListener) {
        Assert.isMainThread();
        Assert.isNotNull(duoListener);
    }

    public ListenableFuture<ImmutableMap<String, Duo.ReachabilityData>> updateReachability(Context context, List<String> list) {
        Assert.isMainThread();
        Assert.isNotNull(context);
        Assert.isNotNull(list);
        return Futures.immediateFuture(ImmutableMap.m82of());
    }
}
