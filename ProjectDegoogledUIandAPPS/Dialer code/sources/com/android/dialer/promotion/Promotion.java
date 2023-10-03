package com.android.dialer.promotion;

public interface Promotion {
    void dismiss();

    CharSequence getDetails();

    int getIconRes();

    CharSequence getTitle();

    int getType();

    boolean isEligibleToBeShown();

    void onViewed() {
    }
}
