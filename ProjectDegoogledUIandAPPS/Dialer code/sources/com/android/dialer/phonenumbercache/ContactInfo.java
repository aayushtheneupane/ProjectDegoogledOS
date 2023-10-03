package com.android.dialer.phonenumbercache;

import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.android.dialer.logging.ContactSource$Type;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class ContactInfo {
    public static final ContactInfo EMPTY = new ContactInfo();
    public int carrierPresence;
    public boolean contactExists;
    public String formattedNumber;
    public String geoDescription;
    public String label;
    public String lookupKey;
    public Uri lookupUri;
    public String name;
    public String nameAlternative;
    public String normalizedNumber;
    public String number;
    public String objectId;
    public long photoId;
    public Uri photoUri;
    public ContactSource$Type sourceType = ContactSource$Type.UNKNOWN_SOURCE_TYPE;
    public int type;
    public long userType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContactInfo.class != obj.getClass()) {
            return false;
        }
        ContactInfo contactInfo = (ContactInfo) obj;
        return R$style.areEqual(this.lookupUri, contactInfo.lookupUri) && TextUtils.equals(this.name, contactInfo.name) && TextUtils.equals(this.nameAlternative, contactInfo.nameAlternative) && this.type == contactInfo.type && TextUtils.equals(this.label, contactInfo.label) && TextUtils.equals(this.number, contactInfo.number) && TextUtils.equals(this.formattedNumber, contactInfo.formattedNumber) && TextUtils.equals(this.normalizedNumber, contactInfo.normalizedNumber) && this.photoId == contactInfo.photoId && R$style.areEqual(this.photoUri, contactInfo.photoUri) && TextUtils.equals(this.objectId, contactInfo.objectId) && this.userType == contactInfo.userType && this.carrierPresence == contactInfo.carrierPresence && TextUtils.equals(this.geoDescription, contactInfo.geoDescription);
    }

    public int hashCode() {
        Uri uri = this.lookupUri;
        int i = 0;
        int hashCode = ((uri == null ? 0 : uri.hashCode()) + 31) * 31;
        String str = this.name;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ContactInfo{lookupUri=");
        outline13.append(this.lookupUri);
        outline13.append(", name='");
        outline13.append(this.name);
        outline13.append('\'');
        outline13.append(", nameAlternative='");
        outline13.append(this.nameAlternative);
        outline13.append('\'');
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", label='");
        outline13.append(this.label);
        outline13.append('\'');
        outline13.append(", number='");
        outline13.append(this.number);
        outline13.append('\'');
        outline13.append(", formattedNumber='");
        outline13.append(this.formattedNumber);
        outline13.append('\'');
        outline13.append(", normalizedNumber='");
        outline13.append(this.normalizedNumber);
        outline13.append('\'');
        outline13.append(", photoId=");
        outline13.append(this.photoId);
        outline13.append(", photoUri=");
        outline13.append(this.photoUri);
        outline13.append(", objectId='");
        outline13.append(this.objectId);
        outline13.append('\'');
        outline13.append(", userType=");
        outline13.append(this.userType);
        outline13.append(", carrierPresence=");
        outline13.append(this.carrierPresence);
        outline13.append(", geoDescription=");
        outline13.append(this.geoDescription);
        outline13.append('}');
        return outline13.toString();
    }
}
