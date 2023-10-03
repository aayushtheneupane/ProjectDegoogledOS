package com.android.dialer.strictmode.impl;

import android.os.StrictMode;

/* renamed from: com.android.dialer.strictmode.impl.-$$Lambda$SystemDialerStrictMode$_qvKNOJ5TTFJIagjswmPr9E2rfE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SystemDialerStrictMode$_qvKNOJ5TTFJIagjswmPr9E2rfE implements Runnable {
    public static final /* synthetic */ $$Lambda$SystemDialerStrictMode$_qvKNOJ5TTFJIagjswmPr9E2rfE INSTANCE = new $$Lambda$SystemDialerStrictMode$_qvKNOJ5TTFJIagjswmPr9E2rfE();

    private /* synthetic */ $$Lambda$SystemDialerStrictMode$_qvKNOJ5TTFJIagjswmPr9E2rfE() {
    }

    public final void run() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(SystemDialerStrictMode.THREAD_DEATH_PENALTY).detectAll().build());
    }
}
