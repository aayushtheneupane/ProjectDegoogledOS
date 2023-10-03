package com.android.dialer.calllog.datasources;

import com.google.common.util.concurrent.ListenableFuture;

public interface CallLogDataSource {
    ListenableFuture<Void> clearData();

    ListenableFuture<Void> fill(CallLogMutations callLogMutations);

    String getLoggingName();

    ListenableFuture<Boolean> isDirty();

    ListenableFuture<Void> onSuccessfulFill();

    void registerContentObservers();

    void unregisterContentObservers();
}
