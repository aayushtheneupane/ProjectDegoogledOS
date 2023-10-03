package com.android.dialer.common.concurrent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public final class DialerUiTaskFragment<InputT, OutputT> extends Fragment {
    private DialerExecutor.FailureListener failureListener;
    private Executor parallelExecutor;
    private ScheduledFuture<?> scheduledFuture;
    private ScheduledExecutorService serialExecutor;
    private DialerExecutor.SuccessListener<OutputT> successListener;
    private DialerExecutor.Worker<InputT, OutputT> worker;

    static <InputT, OutputT> DialerUiTaskFragment<InputT, OutputT> create(FragmentManager fragmentManager, String str, DialerExecutor.Worker<InputT, OutputT> worker2, DialerExecutor.SuccessListener<OutputT> successListener2, DialerExecutor.FailureListener failureListener2, ScheduledExecutorService scheduledExecutorService, Executor executor) {
        Assert.isMainThread();
        DialerUiTaskFragment<InputT, OutputT> dialerUiTaskFragment = (DialerUiTaskFragment) fragmentManager.findFragmentByTag(str);
        if (dialerUiTaskFragment == null) {
            LogUtil.m9i("DialerUiTaskFragment.create", GeneratedOutlineSupport.outline8("creating new DialerUiTaskFragment for ", str), new Object[0]);
            dialerUiTaskFragment = new DialerUiTaskFragment<>();
            fragmentManager.beginTransaction().add(dialerUiTaskFragment, str).commit();
        }
        dialerUiTaskFragment.worker = worker2;
        dialerUiTaskFragment.successListener = successListener2;
        dialerUiTaskFragment.failureListener = failureListener2;
        Assert.isNotNull(scheduledExecutorService);
        dialerUiTaskFragment.serialExecutor = scheduledExecutorService;
        Assert.isNotNull(executor);
        dialerUiTaskFragment.parallelExecutor = executor;
        return dialerUiTaskFragment;
    }

    /* access modifiers changed from: private */
    /* renamed from: runTask */
    public void lambda$executeSerial$0$DialerUiTaskFragment(InputT inputt) {
        try {
            OutputT doInBackground = this.worker.doInBackground(inputt);
            if (this.successListener == null) {
                LogUtil.m9i("DialerUiTaskFragment.runTask", "task succeeded but UI is dead", new Object[0]);
            } else {
                DialerExecutorModule.postOnUiThread(new Runnable(doInBackground) {
                    private final /* synthetic */ Object f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DialerUiTaskFragment.this.lambda$runTask$4$DialerUiTaskFragment(this.f$1);
                    }
                });
            }
        } catch (Throwable th) {
            LogUtil.m7e("DialerUiTaskFragment.runTask", "task failed", th);
            if (this.failureListener == null) {
                LogUtil.m9i("DialerUiTaskFragment.runTask", "task failed but UI is dead", new Object[0]);
            } else {
                DialerExecutorModule.postOnUiThread(new Runnable(th) {
                    private final /* synthetic */ Throwable f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DialerUiTaskFragment.this.lambda$runTask$5$DialerUiTaskFragment(this.f$1);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void executeParallel(InputT inputt) {
        this.parallelExecutor.execute(new Runnable(inputt) {
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DialerUiTaskFragment.this.lambda$executeParallel$2$DialerUiTaskFragment(this.f$1);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void executeSerial(InputT inputt) {
        this.serialExecutor.execute(new Runnable(inputt) {
            private final /* synthetic */ Object f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DialerUiTaskFragment.this.lambda$executeSerial$0$DialerUiTaskFragment(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$runTask$4$DialerUiTaskFragment(Object obj) {
        DialerExecutor.SuccessListener<OutputT> successListener2 = this.successListener;
        if (successListener2 == null) {
            LogUtil.m9i("DialerUiTaskFragment.runTask", "task succeeded but UI died after success runnable posted", new Object[0]);
        } else {
            successListener2.onSuccess(obj);
        }
    }

    public /* synthetic */ void lambda$runTask$5$DialerUiTaskFragment(Throwable th) {
        DialerExecutor.FailureListener failureListener2 = this.failureListener;
        if (failureListener2 == null) {
            LogUtil.m9i("DialerUiTaskFragment.runTask", "task failed but UI died after failure runnable posted", new Object[0]);
        } else {
            failureListener2.onFailure(th);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onDetach() {
        super.onDetach();
        LogUtil.enterBlock("DialerUiTaskFragment.onDetach");
        this.successListener = null;
        this.failureListener = null;
        ScheduledFuture<?> scheduledFuture2 = this.scheduledFuture;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
            this.scheduledFuture = null;
        }
    }
}
