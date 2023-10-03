package com.android.dialer.common.concurrent;

import com.google.common.util.concurrent.FutureCallback;

public final class DefaultFutureCallback<T> implements FutureCallback<T> {
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

    public void onSuccess(T t) {
    }
}
