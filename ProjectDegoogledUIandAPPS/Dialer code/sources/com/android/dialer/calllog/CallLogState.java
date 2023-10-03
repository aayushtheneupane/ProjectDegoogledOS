package com.android.dialer.calllog;

import android.content.SharedPreferences;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public final class CallLogState {
    private final ListeningExecutorService backgroundExecutor;
    private final SharedPreferences sharedPreferences;

    public CallLogState(SharedPreferences sharedPreferences2, ListeningExecutorService listeningExecutorService) {
        this.sharedPreferences = sharedPreferences2;
        this.backgroundExecutor = listeningExecutorService;
    }

    public void clearData() {
        this.sharedPreferences.edit().remove("annotated_call_log_built").apply();
    }

    public ListenableFuture<Boolean> isBuilt() {
        return this.backgroundExecutor.submit(new Callable() {
            public final Object call() {
                return CallLogState.this.lambda$isBuilt$0$CallLogState();
            }
        });
    }

    public /* synthetic */ Boolean lambda$isBuilt$0$CallLogState() throws Exception {
        return Boolean.valueOf(this.sharedPreferences.getBoolean("annotated_call_log_built", false));
    }

    public void markBuilt() {
        this.sharedPreferences.edit().putBoolean("annotated_call_log_built", true).apply();
    }
}
