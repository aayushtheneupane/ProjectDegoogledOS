package com.android.dialer.historyitemactions;

public interface HistoryItemActionModule {
    int getDrawableId();

    int getStringId();

    boolean onClick();

    boolean tintDrawable() {
        return true;
    }
}
