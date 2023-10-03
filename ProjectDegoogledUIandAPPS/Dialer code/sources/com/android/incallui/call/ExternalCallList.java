package com.android.incallui.call;

import android.os.Handler;
import android.os.Looper;
import android.telecom.Call;
import android.util.ArraySet;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ExternalCallList {
    private final Set<ExternalCallListener> externalCallListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private final Set<Call> externalCalls = new ArraySet();
    private final Call.Callback telecomCallCallback = new Call.Callback() {
        public void onDetailsChanged(Call call, Call.Details details) {
            ExternalCallList.this.notifyExternalCallUpdated(call);
        }
    };

    public interface ExternalCallListener {
        void onExternalCallAdded(Call call);

        void onExternalCallPulled(Call call);

        void onExternalCallRemoved(Call call);

        void onExternalCallUpdated(Call call);
    }

    /* access modifiers changed from: private */
    public void notifyExternalCallUpdated(Call call) {
        if (!call.getDetails().hasProperty(64)) {
            onCallRemoved(call);
            for (ExternalCallListener onExternalCallPulled : this.externalCallListeners) {
                onExternalCallPulled.onExternalCallPulled(call);
            }
            return;
        }
        for (ExternalCallListener onExternalCallUpdated : this.externalCallListeners) {
            onExternalCallUpdated.onExternalCallUpdated(call);
        }
    }

    public void addExternalCallListener(ExternalCallListener externalCallListener) {
        this.externalCallListeners.add(externalCallListener);
    }

    public boolean isCallTracked(Call call) {
        return this.externalCalls.contains(call);
    }

    public void onCallAdded(Call call) {
        Assert.checkArgument(call.getDetails().hasProperty(64));
        this.externalCalls.add(call);
        call.registerCallback(this.telecomCallCallback, new Handler(Looper.getMainLooper()));
        for (ExternalCallListener onExternalCallAdded : this.externalCallListeners) {
            onExternalCallAdded.onExternalCallAdded(call);
        }
    }

    public void onCallRemoved(Call call) {
        if (!this.externalCalls.contains(call)) {
            LogUtil.m9i("ExternalCallList.onCallRemoved", "attempted to remove unregistered call", new Object[0]);
            return;
        }
        this.externalCalls.remove(call);
        call.unregisterCallback(this.telecomCallCallback);
        for (ExternalCallListener onExternalCallRemoved : this.externalCallListeners) {
            onExternalCallRemoved.onExternalCallRemoved(call);
        }
    }

    public void removeExternalCallListener(ExternalCallListener externalCallListener) {
        if (!this.externalCallListeners.contains(externalCallListener)) {
            LogUtil.m9i("ExternalCallList.removeExternalCallListener", "attempt to remove unregistered listener.", new Object[0]);
        }
        this.externalCallListeners.remove(externalCallListener);
    }
}
