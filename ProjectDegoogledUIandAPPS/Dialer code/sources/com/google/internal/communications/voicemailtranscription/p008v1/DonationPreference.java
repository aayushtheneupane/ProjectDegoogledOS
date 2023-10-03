package com.google.internal.communications.voicemailtranscription.p008v1;

import com.google.protobuf.Internal;

/* renamed from: com.google.internal.communications.voicemailtranscription.v1.DonationPreference */
public enum DonationPreference implements Internal.EnumLite {
    USER_PREFERENCE_UNSPECIFIED(0),
    DO_NOT_DONATE(1),
    DONATE(2);
    
    private final int value;

    private DonationPreference(int i) {
        this.value = i;
    }

    public static DonationPreference forNumber(int i) {
        if (i == 0) {
            return USER_PREFERENCE_UNSPECIFIED;
        }
        if (i == 1) {
            return DO_NOT_DONATE;
        }
        if (i != 2) {
            return null;
        }
        return DONATE;
    }

    public final int getNumber() {
        return this.value;
    }
}
