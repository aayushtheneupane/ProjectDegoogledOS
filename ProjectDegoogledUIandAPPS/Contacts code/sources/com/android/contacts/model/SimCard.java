package com.android.contacts.model;

import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SimCard {
    public static final int NO_SUBSCRIPTION_ID = -1;
    private static final String TAG = "SimCard";
    private final CharSequence mCarrierName;
    private List<SimContact> mContacts;
    private final String mCountryCode;
    private boolean mDismissed;
    private final CharSequence mDisplayName;
    private boolean mImported;
    private final String mPhoneNumber;
    private final String mSimId;
    private final int mSubscriptionId;

    public SimCard(SimCard simCard) {
        this.mDismissed = false;
        this.mImported = false;
        this.mSimId = simCard.mSimId;
        this.mSubscriptionId = simCard.mSubscriptionId;
        this.mCarrierName = simCard.mCarrierName;
        this.mDisplayName = simCard.mDisplayName;
        this.mPhoneNumber = simCard.mPhoneNumber;
        this.mCountryCode = simCard.mCountryCode;
        this.mDismissed = simCard.mDismissed;
        this.mImported = simCard.mImported;
        List<SimContact> list = simCard.mContacts;
        if (list != null) {
            this.mContacts = new ArrayList(list);
        }
    }

    public SimCard(String str, int i, CharSequence charSequence, CharSequence charSequence2, String str2, String str3) {
        this.mDismissed = false;
        this.mImported = false;
        this.mSimId = str;
        this.mSubscriptionId = i;
        this.mCarrierName = charSequence;
        this.mDisplayName = charSequence2;
        this.mPhoneNumber = str2;
        this.mCountryCode = str3 != null ? str3.toUpperCase(Locale.US) : null;
    }

    public SimCard(String str, CharSequence charSequence, CharSequence charSequence2, String str2, String str3) {
        this(str, -1, charSequence, charSequence2, str2, str3);
    }

    public String getSimId() {
        return this.mSimId;
    }

    public int getSubscriptionId() {
        return this.mSubscriptionId;
    }

    public boolean hasValidSubscriptionId() {
        return this.mSubscriptionId != -1;
    }

    public CharSequence getDisplayName() {
        return this.mDisplayName;
    }

    public String getPhone() {
        return this.mPhoneNumber;
    }

    public CharSequence getFormattedPhone() {
        String str = this.mPhoneNumber;
        if (str == null) {
            return null;
        }
        return PhoneNumberUtils.formatNumber(str, this.mCountryCode);
    }

    public boolean hasPhone() {
        return this.mPhoneNumber != null;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public boolean areContactsAvailable() {
        return this.mContacts != null;
    }

    public boolean hasContacts() {
        List<SimContact> list = this.mContacts;
        if (list != null) {
            return !list.isEmpty();
        }
        throw new IllegalStateException("Contacts not loaded.");
    }

    public int getContactCount() {
        List<SimContact> list = this.mContacts;
        if (list != null) {
            return list.size();
        }
        throw new IllegalStateException("Contacts not loaded.");
    }

    public boolean isDismissed() {
        return this.mDismissed;
    }

    public boolean isImported() {
        return this.mImported;
    }

    public boolean isImportable() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "isImportable: isDismissed? " + isDismissed() + " isImported? " + isImported() + " contacts=" + this.mContacts);
        }
        return !isDismissed() && !isImported() && hasContacts();
    }

    public List<SimContact> getContacts() {
        return this.mContacts;
    }

    public SimCard withImportAndDismissStates(boolean z, boolean z2) {
        SimCard simCard = new SimCard(this);
        simCard.mImported = z;
        simCard.mDismissed = z2;
        return simCard;
    }

    public SimCard withImportedState(boolean z) {
        return withImportAndDismissStates(z, this.mDismissed);
    }

    public SimCard withDismissedState(boolean z) {
        return withImportAndDismissStates(this.mImported, z);
    }

    public SimCard withContacts(List<SimContact> list) {
        SimCard simCard = new SimCard(this);
        simCard.mContacts = list;
        return simCard;
    }

    public SimCard withContacts(SimContact... simContactArr) {
        SimCard simCard = new SimCard(this);
        simCard.mContacts = Arrays.asList(simContactArr);
        return simCard;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SimCard.class != obj.getClass()) {
            return false;
        }
        SimCard simCard = (SimCard) obj;
        if (this.mSubscriptionId != simCard.mSubscriptionId || this.mDismissed != simCard.mDismissed || this.mImported != simCard.mImported || !Objects.equals(this.mSimId, simCard.mSimId) || !Objects.equals(this.mPhoneNumber, simCard.mPhoneNumber) || !Objects.equals(this.mCountryCode, simCard.mCountryCode)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((Objects.hash(new Object[]{this.mSimId, this.mPhoneNumber, this.mCountryCode}) * 31) + this.mSubscriptionId) * 31) + (this.mDismissed ? 1 : 0)) * 31) + (this.mImported ? 1 : 0);
    }

    public String toString() {
        return "SimCard{mSimId='" + this.mSimId + '\'' + ", mSubscriptionId=" + this.mSubscriptionId + ", mCarrierName=" + this.mCarrierName + ", mDisplayName=" + this.mDisplayName + ", mPhoneNumber='" + this.mPhoneNumber + '\'' + ", mCountryCode='" + this.mCountryCode + '\'' + ", mDismissed=" + this.mDismissed + ", mImported=" + this.mImported + ", mContacts=" + this.mContacts + '}';
    }

    public static SimCard create(SubscriptionInfo subscriptionInfo) {
        return new SimCard(subscriptionInfo.getIccId(), subscriptionInfo.getSubscriptionId(), subscriptionInfo.getCarrierName(), subscriptionInfo.getDisplayName(), subscriptionInfo.getNumber(), subscriptionInfo.getCountryIso());
    }

    public static SimCard create(TelephonyManager telephonyManager, String str) {
        if (telephonyManager.getSimState() != 5) {
            return new SimCard("", (CharSequence) null, str, "", (String) null);
        }
        return new SimCard(telephonyManager.getSimSerialNumber(), telephonyManager.getSimOperatorName(), str, telephonyManager.getLine1Number(), telephonyManager.getSimCountryIso());
    }
}
