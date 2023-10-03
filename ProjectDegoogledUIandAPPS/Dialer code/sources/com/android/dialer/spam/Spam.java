package com.android.dialer.spam;

public interface Spam {
    static boolean shouldShowAsSpam(boolean z, int i) {
        return z && i != 2;
    }
}
