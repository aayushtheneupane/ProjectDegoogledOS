package com.android.dialer.common.concurrent;

import android.app.FragmentManager;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class DefaultDialerExecutorFactory implements DialerExecutorFactory {
    private final ScheduledExecutorService nonUiSerialExecutor;
    private final ExecutorService nonUiThreadPool;
    private final ScheduledExecutorService uiSerialExecutor;
    private final ExecutorService uiThreadPool;

    private static abstract class BaseTaskBuilder<InputT, OutputT> implements DialerExecutor.Builder<InputT, OutputT> {
        /* access modifiers changed from: private */
        public DialerExecutor.FailureListener failureListener = C0447x72bf270a.INSTANCE;
        final Executor parallelExecutor;
        final ScheduledExecutorService serialExecutorService;
        /* access modifiers changed from: private */
        public DialerExecutor.SuccessListener<OutputT> successListener = C0448xaad836a3.INSTANCE;
        /* access modifiers changed from: private */
        public final DialerExecutor.Worker<InputT, OutputT> worker;

        BaseTaskBuilder(DialerExecutor.Worker<InputT, OutputT> worker2, ScheduledExecutorService scheduledExecutorService, Executor executor) {
            this.worker = worker2;
            this.serialExecutorService = scheduledExecutorService;
            this.parallelExecutor = executor;
        }

        public DialerExecutor.Builder<InputT, OutputT> onFailure(DialerExecutor.FailureListener failureListener2) {
            Assert.isNotNull(failureListener2);
            this.failureListener = failureListener2;
            return this;
        }

        public DialerExecutor.Builder<InputT, OutputT> onSuccess(DialerExecutor.SuccessListener<OutputT> successListener2) {
            Assert.isNotNull(successListener2);
            this.successListener = successListener2;
            return this;
        }
    }

    private static class NonUiDialerExecutor<InputT, OutputT> implements DialerExecutor<InputT> {
        private final DialerExecutor.FailureListener failureListener;
        private final Executor parallelExecutor;
        private final ScheduledExecutorService serialExecutorService;
        private final DialerExecutor.SuccessListener<OutputT> successListener;
        private final DialerExecutor.Worker<InputT, OutputT> worker;

        NonUiDialerExecutor(DialerExecutor.Worker<InputT, OutputT> worker2, DialerExecutor.SuccessListener<OutputT> successListener2, DialerExecutor.FailureListener failureListener2, ScheduledExecutorService scheduledExecutorService, Executor executor) {
            this.worker = worker2;
            this.successListener = successListener2;
            this.failureListener = failureListener2;
            this.serialExecutorService = scheduledExecutorService;
            this.parallelExecutor = executor;
        }

        /* access modifiers changed from: private */
        /* renamed from: run */
        public void mo5824xdac27492(InputT inputt) {
            try {
                DialerExecutorModule.postOnUiThread(new Runnable(this.worker.doInBackground(inputt)) {
                    private final /* synthetic */ Object f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DefaultDialerExecutorFactory.NonUiDialerExecutor.this.lambda$run$5$DefaultDialerExecutorFactory$NonUiDialerExecutor(this.f$1);
                    }
                });
            } catch (Throwable th) {
                DialerExecutorModule.postOnUiThread(new Runnable(th) {
                    private final /* synthetic */ Throwable f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        DefaultDialerExecutorFactory.NonUiDialerExecutor.this.lambda$run$4$DefaultDialerExecutorFactory$NonUiDialerExecutor(this.f$1);
                    }
                });
            }
        }

        public void executeParallel(InputT inputt) {
            this.parallelExecutor.execute(new Runnable(inputt) {
                private final /* synthetic */ Object f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    DefaultDialerExecutorFactory.NonUiDialerExecutor.this.mo5823x252253dd(this.f$1);
                }
            });
        }

        public void executeSerial(InputT inputt) {
            this.serialExecutorService.execute(new Runnable(inputt) {
                private final /* synthetic */ Object f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    DefaultDialerExecutorFactory.NonUiDialerExecutor.this.mo5824xdac27492(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$run$4$DefaultDialerExecutorFactory$NonUiDialerExecutor(Throwable th) {
            this.failureListener.onFailure(th);
        }

        public /* synthetic */ void lambda$run$5$DefaultDialerExecutorFactory$NonUiDialerExecutor(Object obj) {
            this.successListener.onSuccess(obj);
        }
    }

    public static class NonUiTaskBuilder<InputT, OutputT> extends BaseTaskBuilder<InputT, OutputT> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NonUiTaskBuilder(DialerExecutor.Worker<InputT, OutputT> worker, ScheduledExecutorService scheduledExecutorService, Executor executor) {
            super(worker, scheduledExecutorService, executor);
            Assert.isNotNull(scheduledExecutorService);
            Assert.isNotNull(executor);
        }

        public DialerExecutor<InputT> build() {
            return new NonUiDialerExecutor(this.worker, this.successListener, this.failureListener, this.serialExecutorService, this.parallelExecutor);
        }

        public /* bridge */ /* synthetic */ DialerExecutor.Builder onFailure(DialerExecutor.FailureListener failureListener) {
            super.onFailure(failureListener);
            return this;
        }

        public /* bridge */ /* synthetic */ DialerExecutor.Builder onSuccess(DialerExecutor.SuccessListener successListener) {
            super.onSuccess(successListener);
            return this;
        }
    }

    private static class UiDialerExecutor<InputT, OutputT> implements DialerExecutor<InputT> {
        private final DialerUiTaskFragment<InputT, OutputT> dialerUiTaskFragment;

        UiDialerExecutor(DialerUiTaskFragment<InputT, OutputT> dialerUiTaskFragment2) {
            this.dialerUiTaskFragment = dialerUiTaskFragment2;
        }

        public void executeParallel(InputT inputt) {
            this.dialerUiTaskFragment.executeParallel(inputt);
        }

        public void executeSerial(InputT inputt) {
            this.dialerUiTaskFragment.executeSerial(inputt);
        }
    }

    public static class UiTaskBuilder<InputT, OutputT> extends BaseTaskBuilder<InputT, OutputT> {
        private DialerUiTaskFragment<InputT, OutputT> dialerUiTaskFragment;
        private final FragmentManager fragmentManager;

        /* renamed from: id */
        private final String f17id;

        public UiTaskBuilder(FragmentManager fragmentManager2, String str, DialerExecutor.Worker<InputT, OutputT> worker, ScheduledExecutorService scheduledExecutorService, Executor executor) {
            super(worker, scheduledExecutorService, executor);
            this.fragmentManager = fragmentManager2;
            this.f17id = str;
        }

        public DialerExecutor<InputT> build() {
            this.dialerUiTaskFragment = DialerUiTaskFragment.create(this.fragmentManager, this.f17id, this.worker, this.successListener, this.failureListener, this.serialExecutorService, this.parallelExecutor);
            return new UiDialerExecutor(this.dialerUiTaskFragment);
        }

        public /* bridge */ /* synthetic */ DialerExecutor.Builder onFailure(DialerExecutor.FailureListener failureListener) {
            super.onFailure(failureListener);
            return this;
        }

        public /* bridge */ /* synthetic */ DialerExecutor.Builder onSuccess(DialerExecutor.SuccessListener successListener) {
            super.onSuccess(successListener);
            return this;
        }
    }

    DefaultDialerExecutorFactory(ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, ExecutorService executorService2, ScheduledExecutorService scheduledExecutorService2) {
        this.nonUiThreadPool = executorService;
        this.nonUiSerialExecutor = scheduledExecutorService;
        this.uiThreadPool = executorService2;
        this.uiSerialExecutor = scheduledExecutorService2;
    }

    public <InputT, OutputT> DialerExecutor.Builder<InputT, OutputT> createNonUiTaskBuilder(DialerExecutor.Worker<InputT, OutputT> worker) {
        Assert.isNotNull(worker);
        return new NonUiTaskBuilder(worker, this.nonUiSerialExecutor, this.nonUiThreadPool);
    }

    public <InputT, OutputT> DialerExecutor.Builder<InputT, OutputT> createUiTaskBuilder(FragmentManager fragmentManager, String str, DialerExecutor.Worker<InputT, OutputT> worker) {
        Assert.isNotNull(fragmentManager);
        Assert.isNotNull(str);
        Assert.isNotNull(worker);
        return new UiTaskBuilder(fragmentManager, str, worker, this.uiSerialExecutor, this.uiThreadPool);
    }
}
