package com.android.dialer.app.contactinfo;

import android.text.TextUtils;
import com.android.dialer.phonenumbercache.ContactInfo;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public final class ContactInfoRequest implements Comparable<ContactInfoRequest> {
    private static final AtomicLong NEXT_SEQUENCE_NUMBER = new AtomicLong(0);
    public final ContactInfo callLogInfo;
    public final String countryIso;
    public final String number;
    private final long sequenceNumber = NEXT_SEQUENCE_NUMBER.getAndIncrement();
    public final int type;

    public ContactInfoRequest(String str, String str2, ContactInfo contactInfo, int i) {
        this.number = str;
        this.countryIso = str2;
        this.callLogInfo = contactInfo;
        this.type = i;
    }

    public int compareTo(Object obj) {
        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) obj;
        if (isLocalRequest() && !contactInfoRequest.isLocalRequest()) {
            return -1;
        }
        if ((isLocalRequest() || !contactInfoRequest.isLocalRequest()) && this.sequenceNumber < contactInfoRequest.sequenceNumber) {
            return -1;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ContactInfoRequest)) {
            return false;
        }
        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) obj;
        return TextUtils.equals(this.number, contactInfoRequest.number) && TextUtils.equals(this.countryIso, contactInfoRequest.countryIso) && Objects.equals(this.callLogInfo, contactInfoRequest.callLogInfo) && this.type == contactInfoRequest.type;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.sequenceNumber), this.number, this.countryIso, this.callLogInfo, Integer.valueOf(this.type)});
    }

    public boolean isLocalRequest() {
        int i = this.type;
        return i == 0 || i == 1;
    }
}
