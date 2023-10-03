package com.android.dialer.activecalls.impl;

import com.android.dialer.activecalls.ActiveCallInfo;
import com.android.dialer.activecalls.ActiveCalls;
import com.android.dialer.common.Assert;
import com.google.common.collect.ImmutableList;

public class ActiveCallsImpl implements ActiveCalls {
    ImmutableList<ActiveCallInfo> activeCalls = ImmutableList.m74of();

    ActiveCallsImpl() {
    }

    public ImmutableList<ActiveCallInfo> getActiveCalls() {
        return this.activeCalls;
    }

    public void setActiveCalls(ImmutableList<ActiveCallInfo> immutableList) {
        Assert.isMainThread();
        Assert.isNotNull(immutableList);
        this.activeCalls = immutableList;
    }
}
