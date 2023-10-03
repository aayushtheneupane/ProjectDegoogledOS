package com.android.dialer.calllogutils;

import android.content.Context;
import android.net.Uri;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.phonenumbercache.ContactInfo;

public class PhoneCallDetails {
    public String accountComponentName;
    public PhoneAccountHandle accountHandle;
    public String accountId;
    public ContactInfo cachedContactInfo;
    public CharSequence callDescription;
    public CharSequence callLocationAndDate;
    public int[] callTypes;
    public long contactUserType;
    public String countryIso;
    public long date;
    public String displayNumber;
    public long duration;
    public int features;
    public String geocode;
    public boolean isBlocked = false;
    public boolean isRead = true;
    public boolean isSpam = false;
    public CharSequence nameAlternative;
    public ContactDisplayPreferences.DisplayOrder nameDisplayOrder;
    public CharSequence namePrimary;
    public CharSequence number;
    public CharSequence numberLabel;
    public int numberPresentation;
    public int numberType;
    public Uri photoUri;
    public String postDialDigits;
    public int previousGroup;
    public ContactSource$Type sourceType;
    public String transcription;
    public int transcriptionState;
    public String viaNumber;
    public String voicemailUri;

    public PhoneCallDetails(CharSequence charSequence, int i, CharSequence charSequence2) {
        this.number = charSequence;
        this.numberPresentation = i;
        this.postDialDigits = charSequence2.toString();
    }

    public CharSequence getPreferredName() {
        if (this.nameDisplayOrder == ContactDisplayPreferences.DisplayOrder.PRIMARY || TextUtils.isEmpty(this.nameAlternative)) {
            return this.namePrimary;
        }
        return this.nameAlternative;
    }

    public void updateDisplayNumber(Context context, CharSequence charSequence, boolean z) {
        this.displayNumber = CallLogDates.getDisplayNumber(context, this.number, this.numberPresentation, charSequence, this.postDialDigits, z).toString();
    }
}
