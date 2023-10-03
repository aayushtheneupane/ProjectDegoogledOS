package com.android.dialer.strictmode;

public abstract class StrictModeComponent {

    public interface HasComponent {
    }

    public abstract DialerStrictMode getDialerStrictMode();
}
