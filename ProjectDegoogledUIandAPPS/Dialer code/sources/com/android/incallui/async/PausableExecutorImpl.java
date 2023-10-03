package com.android.incallui.async;

import java.util.concurrent.Executors;

public class PausableExecutorImpl implements PausableExecutor {
    public void execute(Runnable runnable) {
        Executors.newSingleThreadExecutor().execute(runnable);
    }

    public void milestone() {
    }
}
