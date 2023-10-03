package com.android.dialer.common.concurrent;

public interface DialerExecutor<InputT> {

    public interface Builder<InputT, OutputT> {
        DialerExecutor<InputT> build();

        Builder<InputT, OutputT> onFailure(FailureListener failureListener);

        Builder<InputT, OutputT> onSuccess(SuccessListener<OutputT> successListener);
    }

    public interface FailureListener {
        void onFailure(Throwable th);
    }

    public interface SuccessListener<OutputT> {
        void onSuccess(OutputT outputt);
    }

    public interface Worker<InputT, OutputT> {
        OutputT doInBackground(InputT inputt) throws Throwable;
    }

    void executeParallel(InputT inputt);

    void executeSerial(InputT inputt);
}
