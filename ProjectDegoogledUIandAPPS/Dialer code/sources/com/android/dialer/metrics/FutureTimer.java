package com.android.dialer.metrics;

import android.os.SystemClock;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

public final class FutureTimer {
    static final long LONG_OPERATION_LOGCAT_THRESHOLD_MILLIS = 100;
    private final ListeningExecutorService lightweightExecutorService;
    /* access modifiers changed from: private */
    public final Metrics metrics;

    public FutureTimer(Metrics metrics2, ListeningExecutorService listeningExecutorService) {
        this.metrics = metrics2;
        this.lightweightExecutorService = listeningExecutorService;
    }

    public <T> void applyTiming(ListenableFuture<T> listenableFuture, String str) {
        applyTiming(listenableFuture, new Function(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final Object apply(Object obj) {
                return this.f$0;
            }
        }, 1);
    }

    public <T> void applyTiming(ListenableFuture<T> listenableFuture, String str, int i) {
        applyTiming(listenableFuture, new Function(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final Object apply(Object obj) {
                return this.f$0;
            }
        }, i);
    }

    public <T> void applyTiming(ListenableFuture<T> listenableFuture, Function<T, String> function) {
        applyTiming(listenableFuture, function, 1);
    }

    private <T> void applyTiming(ListenableFuture<T> listenableFuture, Function<T, String> function, int i) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final Integer startUnnamedTimer = ((StubMetrics) this.metrics).startUnnamedTimer();
        final Function<T, String> function2 = function;
        final int i2 = i;
        Futures.addCallback(listenableFuture, new FutureCallback<T>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(T t) {
                int i;
                String str = (String) function2.apply(t);
                if (startUnnamedTimer != null) {
                    ((StubMetrics) FutureTimer.this.metrics).stopUnnamedTimer(startUnnamedTimer.intValue(), str);
                }
                long elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
                if (elapsedRealtime > FutureTimer.LONG_OPERATION_LOGCAT_THRESHOLD_MILLIS) {
                    int i2 = i2;
                    if (i2 == 1) {
                        LogUtil.m10w("FutureTimer.onSuccess", "%s took more than %dms (took %dms)", str, Long.valueOf(FutureTimer.LONG_OPERATION_LOGCAT_THRESHOLD_MILLIS), Long.valueOf(elapsedRealtime));
                    } else if (i2 == 2) {
                        LogUtil.m10w("FutureTimer.onSuccess", "%s took more than %dms (took %dms and returned %s)", str, Long.valueOf(FutureTimer.LONG_OPERATION_LOGCAT_THRESHOLD_MILLIS), Long.valueOf(elapsedRealtime), LogUtil.sanitizePii(t));
                    } else {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("unknown logcat mode: ");
                        outline13.append(i2);
                        throw new UnsupportedOperationException(outline13.toString());
                    }
                } else if (LogUtil.isDebugEnabled() && (i = i2) != 1) {
                    if (i == 2) {
                        Object[] objArr = {str, Long.valueOf(elapsedRealtime), LogUtil.sanitizePii(t)};
                        return;
                    }
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("unknown logcat mode: ");
                    outline132.append(i2);
                    throw new UnsupportedOperationException(outline132.toString());
                }
            }
        }, this.lightweightExecutorService);
    }
}
