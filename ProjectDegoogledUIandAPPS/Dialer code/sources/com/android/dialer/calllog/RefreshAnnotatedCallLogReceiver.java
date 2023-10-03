package com.android.dialer.calllog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.dialer.calllog.RefreshAnnotatedCallLogWorker;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.metrics.FutureTimer;
import com.android.dialer.metrics.MetricsComponent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

public final class RefreshAnnotatedCallLogReceiver extends BroadcastReceiver {
    private final FutureTimer futureTimer;
    /* access modifiers changed from: private */
    public final LoggingBindings logger;
    private Runnable refreshAnnotatedCallLogRunnable;
    private final RefreshAnnotatedCallLogWorker refreshAnnotatedCallLogWorker;

    private static class EventNameFromResultFunction implements Function<RefreshAnnotatedCallLogWorker.RefreshResult, String> {
        private final boolean checkDirty;

        /* synthetic */ EventNameFromResultFunction(boolean z, C04341 r2) {
            this.checkDirty = z;
        }

        public Object apply(Object obj) {
            RefreshAnnotatedCallLogWorker.RefreshResult refreshResult = (RefreshAnnotatedCallLogWorker.RefreshResult) obj;
            int ordinal = refreshResult.ordinal();
            if (ordinal == 0) {
                return "RefreshAnnotatedCallLogReceiver.NotDirty";
            }
            if (ordinal == 1) {
                return this.checkDirty ? "RefreshAnnotatedCallLogReceiver.NoChangesNeeded" : "RefreshAnnotatedCallLogReceiver.ForceRefreshNoChangesNeeded";
            }
            if (ordinal == 2) {
                return this.checkDirty ? "RefreshAnnotatedCallLogReceiver.ChangesNeeded" : "RefreshAnnotatedCallLogReceiver.ForceRefreshChangesNeeded";
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Unsupported result: ", refreshResult));
        }
    }

    public RefreshAnnotatedCallLogReceiver(Context context) {
        this.refreshAnnotatedCallLogWorker = CallLogComponent.get(context).getRefreshAnnotatedCallLogWorker();
        this.futureTimer = MetricsComponent.get(context).futureTimer();
        this.logger = Logger.get(context);
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("refresh_annotated_call_log");
        intentFilter.addAction("cancel_refreshing_annotated_call_log");
        return intentFilter;
    }

    public /* synthetic */ void lambda$refreshAnnotatedCallLog$0$RefreshAnnotatedCallLogReceiver(final boolean z) {
        ListenableFuture<RefreshAnnotatedCallLogWorker.RefreshResult> listenableFuture;
        if (z) {
            listenableFuture = this.refreshAnnotatedCallLogWorker.refreshWithDirtyCheck();
        } else {
            listenableFuture = this.refreshAnnotatedCallLogWorker.refreshWithoutDirtyCheck();
        }
        Futures.addCallback(listenableFuture, new FutureCallback<RefreshAnnotatedCallLogWorker.RefreshResult>() {
            public void onFailure(Throwable th) {
                DialerExecutorModule.getUiThreadHandler().post(new Runnable(th) {
                    private final /* synthetic */ Throwable f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void run() {
                        throw new RuntimeException(this.f$0);
                    }
                });
            }

            public void onSuccess(Object obj) {
                DialerImpression$Type dialerImpression$Type;
                RefreshAnnotatedCallLogWorker.RefreshResult refreshResult = (RefreshAnnotatedCallLogWorker.RefreshResult) obj;
                LoggingBindings access$100 = RefreshAnnotatedCallLogReceiver.this.logger;
                boolean z = z;
                int ordinal = refreshResult.ordinal();
                if (ordinal == 0) {
                    dialerImpression$Type = DialerImpression$Type.ANNOTATED_CALL_LOG_NOT_DIRTY;
                } else if (ordinal != 1) {
                    if (ordinal != 2) {
                        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Unsupported result: ", refreshResult));
                    } else if (z) {
                        dialerImpression$Type = DialerImpression$Type.ANNOTATED_CALL_LOG_CHANGES_NEEDED;
                    } else {
                        dialerImpression$Type = DialerImpression$Type.ANNOTATED_CALL_LOG_FORCE_REFRESH_CHANGES_NEEDED;
                    }
                } else if (z) {
                    dialerImpression$Type = DialerImpression$Type.ANNOTATED_CALL_LOG_NO_CHANGES_NEEDED;
                } else {
                    dialerImpression$Type = DialerImpression$Type.ANNOTATED_CALL_LOG_FORCE_REFRESH_NO_CHANGES_NEEDED;
                }
                ((LoggingBindingsStub) access$100).logImpression(dialerImpression$Type);
            }
        }, MoreExecutors.directExecutor());
        this.futureTimer.applyTiming(listenableFuture, new EventNameFromResultFunction(z, (C04341) null));
    }

    public void onReceive(Context context, Intent intent) {
        LogUtil.enterBlock("RefreshAnnotatedCallLogReceiver.onReceive");
        String action = intent.getAction();
        if ("refresh_annotated_call_log".equals(action)) {
            boolean booleanExtra = intent.getBooleanExtra("check_dirty", false);
            LogUtil.enterBlock("RefreshAnnotatedCallLogReceiver.refreshAnnotatedCallLog");
            DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.refreshAnnotatedCallLogRunnable);
            this.refreshAnnotatedCallLogRunnable = new Runnable(booleanExtra) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    RefreshAnnotatedCallLogReceiver.this.lambda$refreshAnnotatedCallLog$0$RefreshAnnotatedCallLogReceiver(this.f$1);
                }
            };
            DialerExecutorModule.getUiThreadHandler().postDelayed(this.refreshAnnotatedCallLogRunnable, 100);
        } else if ("cancel_refreshing_annotated_call_log".equals(action)) {
            LogUtil.enterBlock("RefreshAnnotatedCallLogReceiver.cancelRefreshingAnnotatedCallLog");
            DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.refreshAnnotatedCallLogRunnable);
        }
    }
}
