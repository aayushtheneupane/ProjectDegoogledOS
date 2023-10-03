package com.android.vcard;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.contacts.ContactSaveService;
import com.android.vcard.VCardUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VCardEntry {
    private static final List<String> sEmptyList = Collections.unmodifiableList(new ArrayList(0));
    private static final Map<String, Integer> sImMap = new HashMap();
    private final Account mAccount;
    private List<AndroidCustomData> mAndroidCustomDataList;
    private AnniversaryData mAnniversary;
    private BirthdayData mBirthday;
    private List<VCardEntry> mChildren;
    private List<EmailData> mEmailList;
    private List<ImData> mImList;
    private final NameData mNameData;
    private List<NicknameData> mNicknameList;
    private List<NoteData> mNoteList;
    private List<OrganizationData> mOrganizationList;
    private List<PhoneData> mPhoneList;
    private List<PhotoData> mPhotoList;
    private List<PostalData> mPostalList;
    private List<SipData> mSipList;
    private boolean mStarred;
    private List<Pair<String, String>> mUnknownXData;
    private final int mVCardType;
    private List<WebsiteData> mWebsiteList;

    public interface EntryElement {
        void constructInsertOperation(List<ContentProviderOperation> list, int i);

        EntryLabel getEntryLabel();

        boolean isEmpty();
    }

    public interface EntryElementIterator {
        boolean onElement(EntryElement entryElement);

        void onElementGroupEnded();

        void onElementGroupStarted(EntryLabel entryLabel);

        void onIterationEnded();

        void onIterationStarted();
    }

    public enum EntryLabel {
        NAME,
        PHONE,
        EMAIL,
        POSTAL_ADDRESS,
        ORGANIZATION,
        IM,
        PHOTO,
        WEBSITE,
        SIP,
        NICKNAME,
        NOTE,
        BIRTHDAY,
        ANNIVERSARY,
        ANDROID_CUSTOM
    }

    static {
        sImMap.put("X-AIM", 0);
        sImMap.put("X-MSN", 1);
        sImMap.put("X-YAHOO", 2);
        sImMap.put("X-ICQ", 6);
        sImMap.put("X-JABBER", 7);
        sImMap.put("X-SKYPE-USERNAME", 3);
        sImMap.put("X-GOOGLE-TALK", 5);
        sImMap.put("X-GOOGLE TALK", 5);
    }

    public boolean getStarred() {
        return this.mStarred;
    }

    public static class NameData implements EntryElement {
        public String displayName;
        /* access modifiers changed from: private */
        public String mFamily;
        /* access modifiers changed from: private */
        public String mFormatted;
        /* access modifiers changed from: private */
        public String mGiven;
        /* access modifiers changed from: private */
        public String mMiddle;
        /* access modifiers changed from: private */
        public String mPhoneticFamily;
        /* access modifiers changed from: private */
        public String mPhoneticGiven;
        /* access modifiers changed from: private */
        public String mPhoneticMiddle;
        /* access modifiers changed from: private */
        public String mPrefix;
        /* access modifiers changed from: private */
        public String mSortString;
        /* access modifiers changed from: private */
        public String mSuffix;

        public boolean emptyStructuredName() {
            return TextUtils.isEmpty(this.mFamily) && TextUtils.isEmpty(this.mGiven) && TextUtils.isEmpty(this.mMiddle) && TextUtils.isEmpty(this.mPrefix) && TextUtils.isEmpty(this.mSuffix);
        }

        public boolean emptyPhoneticStructuredName() {
            return TextUtils.isEmpty(this.mPhoneticFamily) && TextUtils.isEmpty(this.mPhoneticGiven) && TextUtils.isEmpty(this.mPhoneticMiddle);
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/name");
            if (!TextUtils.isEmpty(this.mGiven)) {
                newInsert.withValue("data2", this.mGiven);
            }
            if (!TextUtils.isEmpty(this.mFamily)) {
                newInsert.withValue("data3", this.mFamily);
            }
            if (!TextUtils.isEmpty(this.mMiddle)) {
                newInsert.withValue("data5", this.mMiddle);
            }
            if (!TextUtils.isEmpty(this.mPrefix)) {
                newInsert.withValue("data4", this.mPrefix);
            }
            if (!TextUtils.isEmpty(this.mSuffix)) {
                newInsert.withValue("data6", this.mSuffix);
            }
            boolean z = false;
            if (!TextUtils.isEmpty(this.mPhoneticGiven)) {
                newInsert.withValue("data7", this.mPhoneticGiven);
                z = true;
            }
            if (!TextUtils.isEmpty(this.mPhoneticFamily)) {
                newInsert.withValue("data9", this.mPhoneticFamily);
                z = true;
            }
            if (!TextUtils.isEmpty(this.mPhoneticMiddle)) {
                newInsert.withValue("data8", this.mPhoneticMiddle);
                z = true;
            }
            if (!z) {
                newInsert.withValue("data7", this.mSortString);
            }
            newInsert.withValue("data1", this.displayName);
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mFamily) && TextUtils.isEmpty(this.mMiddle) && TextUtils.isEmpty(this.mGiven) && TextUtils.isEmpty(this.mPrefix) && TextUtils.isEmpty(this.mSuffix) && TextUtils.isEmpty(this.mFormatted) && TextUtils.isEmpty(this.mPhoneticFamily) && TextUtils.isEmpty(this.mPhoneticMiddle) && TextUtils.isEmpty(this.mPhoneticGiven) && TextUtils.isEmpty(this.mSortString);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NameData)) {
                return false;
            }
            NameData nameData = (NameData) obj;
            if (!TextUtils.equals(this.mFamily, nameData.mFamily) || !TextUtils.equals(this.mMiddle, nameData.mMiddle) || !TextUtils.equals(this.mGiven, nameData.mGiven) || !TextUtils.equals(this.mPrefix, nameData.mPrefix) || !TextUtils.equals(this.mSuffix, nameData.mSuffix) || !TextUtils.equals(this.mFormatted, nameData.mFormatted) || !TextUtils.equals(this.mPhoneticFamily, nameData.mPhoneticFamily) || !TextUtils.equals(this.mPhoneticMiddle, nameData.mPhoneticMiddle) || !TextUtils.equals(this.mPhoneticGiven, nameData.mPhoneticGiven) || !TextUtils.equals(this.mSortString, nameData.mSortString)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            String[] strArr = {this.mFamily, this.mMiddle, this.mGiven, this.mPrefix, this.mSuffix, this.mFormatted, this.mPhoneticFamily, this.mPhoneticMiddle, this.mPhoneticGiven, this.mSortString};
            int length = strArr.length;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                String str = strArr[i2];
                i = (i * 31) + (str != null ? str.hashCode() : 0);
            }
            return i;
        }

        public String toString() {
            return String.format("family: %s, given: %s, middle: %s, prefix: %s, suffix: %s", new Object[]{this.mFamily, this.mGiven, this.mMiddle, this.mPrefix, this.mSuffix});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.NAME;
        }
    }

    public static class PhoneData implements EntryElement {
        private boolean mIsPrimary;
        private final String mLabel;
        /* access modifiers changed from: private */
        public final String mNumber;
        private final int mType;

        public PhoneData(String str, int i, String str2, boolean z) {
            this.mNumber = str;
            this.mType = i;
            this.mLabel = str2;
            this.mIsPrimary = z;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/phone_v2");
            newInsert.withValue("data2", Integer.valueOf(this.mType));
            if (this.mType == 0) {
                newInsert.withValue("data3", this.mLabel);
            }
            newInsert.withValue("data1", this.mNumber);
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mNumber);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PhoneData)) {
                return false;
            }
            PhoneData phoneData = (PhoneData) obj;
            if (this.mType != phoneData.mType || !TextUtils.equals(this.mNumber, phoneData.mNumber) || !TextUtils.equals(this.mLabel, phoneData.mLabel) || this.mIsPrimary != phoneData.mIsPrimary) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.mType * 31;
            String str = this.mNumber;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mLabel;
            if (str2 != null) {
                i2 = str2.hashCode();
            }
            return ((hashCode + i2) * 31) + (this.mIsPrimary ? 1231 : 1237);
        }

        public String toString() {
            return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), this.mNumber, this.mLabel, Boolean.valueOf(this.mIsPrimary)});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.PHONE;
        }
    }

    public static class EmailData implements EntryElement {
        /* access modifiers changed from: private */
        public final String mAddress;
        private final boolean mIsPrimary;
        private final String mLabel;
        private final int mType;

        public EmailData(String str, int i, String str2, boolean z) {
            this.mType = i;
            this.mAddress = str;
            this.mLabel = str2;
            this.mIsPrimary = z;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/email_v2");
            newInsert.withValue("data2", Integer.valueOf(this.mType));
            if (this.mType == 0) {
                newInsert.withValue("data3", this.mLabel);
            }
            newInsert.withValue("data1", this.mAddress);
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAddress);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EmailData)) {
                return false;
            }
            EmailData emailData = (EmailData) obj;
            if (this.mType != emailData.mType || !TextUtils.equals(this.mAddress, emailData.mAddress) || !TextUtils.equals(this.mLabel, emailData.mLabel) || this.mIsPrimary != emailData.mIsPrimary) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.mType * 31;
            String str = this.mAddress;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mLabel;
            if (str2 != null) {
                i2 = str2.hashCode();
            }
            return ((hashCode + i2) * 31) + (this.mIsPrimary ? 1231 : 1237);
        }

        public String toString() {
            return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), this.mAddress, this.mLabel, Boolean.valueOf(this.mIsPrimary)});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.EMAIL;
        }
    }

    public static class PostalData implements EntryElement {
        private final String mCountry;
        private final String mExtendedAddress;
        private boolean mIsPrimary;
        private final String mLabel;
        private final String mLocalty;
        private final String mPobox;
        private final String mPostalCode;
        private final String mRegion;
        private final String mStreet;
        private final int mType;
        private int mVCardType;

        public PostalData(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, String str8, boolean z, int i2) {
            this.mType = i;
            this.mPobox = str;
            this.mExtendedAddress = str2;
            this.mStreet = str3;
            this.mLocalty = str4;
            this.mRegion = str5;
            this.mPostalCode = str6;
            this.mCountry = str7;
            this.mLabel = str8;
            this.mIsPrimary = z;
            this.mVCardType = i2;
        }

        public static PostalData constructPostalData(List<String> list, int i, String str, boolean z, int i2) {
            String[] strArr = new String[7];
            int size = list.size();
            if (size > 7) {
                size = 7;
            }
            int i3 = 0;
            for (String str2 : list) {
                strArr[i3] = str2;
                i3++;
                if (i3 >= size) {
                    break;
                }
            }
            while (i3 < 7) {
                strArr[i3] = null;
                i3++;
            }
            return new PostalData(strArr[0], strArr[1], strArr[2], strArr[3], strArr[4], strArr[5], strArr[6], i, str, z, i2);
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            String str;
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/postal-address_v2");
            newInsert.withValue("data2", Integer.valueOf(this.mType));
            if (this.mType == 0) {
                newInsert.withValue("data3", this.mLabel);
            }
            if (TextUtils.isEmpty(this.mStreet)) {
                if (TextUtils.isEmpty(this.mExtendedAddress)) {
                    str = null;
                } else {
                    str = this.mExtendedAddress;
                }
            } else if (TextUtils.isEmpty(this.mExtendedAddress)) {
                str = this.mStreet;
            } else {
                str = this.mStreet + " " + this.mExtendedAddress;
            }
            newInsert.withValue("data5", this.mPobox);
            newInsert.withValue("data4", str);
            newInsert.withValue("data7", this.mLocalty);
            newInsert.withValue("data8", this.mRegion);
            newInsert.withValue("data9", this.mPostalCode);
            newInsert.withValue("data10", this.mCountry);
            newInsert.withValue("data1", getFormattedAddress(this.mVCardType));
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
        }

        public String getFormattedAddress(int i) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            String[] strArr = {this.mPobox, this.mExtendedAddress, this.mStreet, this.mLocalty, this.mRegion, this.mPostalCode, this.mCountry};
            if (VCardConfig.isJapaneseDevice(i)) {
                for (int i2 = 6; i2 >= 0; i2--) {
                    String str = strArr[i2];
                    if (!TextUtils.isEmpty(str)) {
                        if (!z) {
                            sb.append(' ');
                        } else {
                            z = false;
                        }
                        sb.append(str);
                    }
                }
            } else {
                for (int i3 = 0; i3 < 7; i3++) {
                    String str2 = strArr[i3];
                    if (!TextUtils.isEmpty(str2)) {
                        if (!z) {
                            sb.append(' ');
                        } else {
                            z = false;
                        }
                        sb.append(str2);
                    }
                }
            }
            return sb.toString().trim();
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mPobox) && TextUtils.isEmpty(this.mExtendedAddress) && TextUtils.isEmpty(this.mStreet) && TextUtils.isEmpty(this.mLocalty) && TextUtils.isEmpty(this.mRegion) && TextUtils.isEmpty(this.mPostalCode) && TextUtils.isEmpty(this.mCountry);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PostalData)) {
                return false;
            }
            PostalData postalData = (PostalData) obj;
            int i = this.mType;
            if (i != postalData.mType || ((i == 0 && !TextUtils.equals(this.mLabel, postalData.mLabel)) || this.mIsPrimary != postalData.mIsPrimary || !TextUtils.equals(this.mPobox, postalData.mPobox) || !TextUtils.equals(this.mExtendedAddress, postalData.mExtendedAddress) || !TextUtils.equals(this.mStreet, postalData.mStreet) || !TextUtils.equals(this.mLocalty, postalData.mLocalty) || !TextUtils.equals(this.mRegion, postalData.mRegion) || !TextUtils.equals(this.mPostalCode, postalData.mPostalCode) || !TextUtils.equals(this.mCountry, postalData.mCountry))) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.mType * 31;
            String str = this.mLabel;
            int hashCode = ((i + (str != null ? str.hashCode() : 0)) * 31) + (this.mIsPrimary ? 1231 : 1237);
            String[] strArr = {this.mPobox, this.mExtendedAddress, this.mStreet, this.mLocalty, this.mRegion, this.mPostalCode, this.mCountry};
            int length = strArr.length;
            int i2 = hashCode;
            for (int i3 = 0; i3 < length; i3++) {
                String str2 = strArr[i3];
                i2 = (i2 * 31) + (str2 != null ? str2.hashCode() : 0);
            }
            return i2;
        }

        public String toString() {
            return String.format("type: %d, label: %s, isPrimary: %s, pobox: %s, extendedAddress: %s, street: %s, localty: %s, region: %s, postalCode %s, country: %s", new Object[]{Integer.valueOf(this.mType), this.mLabel, Boolean.valueOf(this.mIsPrimary), this.mPobox, this.mExtendedAddress, this.mStreet, this.mLocalty, this.mRegion, this.mPostalCode, this.mCountry});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.POSTAL_ADDRESS;
        }
    }

    public static class OrganizationData implements EntryElement {
        /* access modifiers changed from: private */
        public String mDepartmentName;
        /* access modifiers changed from: private */
        public boolean mIsPrimary;
        /* access modifiers changed from: private */
        public String mOrganizationName;
        private final String mPhoneticName;
        /* access modifiers changed from: private */
        public String mTitle;
        private final int mType;

        public OrganizationData(String str, String str2, String str3, String str4, int i, boolean z) {
            this.mType = i;
            this.mOrganizationName = str;
            this.mDepartmentName = str2;
            this.mTitle = str3;
            this.mPhoneticName = str4;
            this.mIsPrimary = z;
        }

        public String getFormattedString() {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.mOrganizationName)) {
                sb.append(this.mOrganizationName);
            }
            if (!TextUtils.isEmpty(this.mDepartmentName)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(this.mDepartmentName);
            }
            if (!TextUtils.isEmpty(this.mTitle)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(this.mTitle);
            }
            return sb.toString();
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/organization");
            newInsert.withValue("data2", Integer.valueOf(this.mType));
            String str = this.mOrganizationName;
            if (str != null) {
                newInsert.withValue("data1", str);
            }
            String str2 = this.mDepartmentName;
            if (str2 != null) {
                newInsert.withValue("data5", str2);
            }
            String str3 = this.mTitle;
            if (str3 != null) {
                newInsert.withValue("data4", str3);
            }
            String str4 = this.mPhoneticName;
            if (str4 != null) {
                newInsert.withValue("data8", str4);
            }
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mOrganizationName) && TextUtils.isEmpty(this.mDepartmentName) && TextUtils.isEmpty(this.mTitle) && TextUtils.isEmpty(this.mPhoneticName);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OrganizationData)) {
                return false;
            }
            OrganizationData organizationData = (OrganizationData) obj;
            if (this.mType != organizationData.mType || !TextUtils.equals(this.mOrganizationName, organizationData.mOrganizationName) || !TextUtils.equals(this.mDepartmentName, organizationData.mDepartmentName) || !TextUtils.equals(this.mTitle, organizationData.mTitle) || this.mIsPrimary != organizationData.mIsPrimary) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.mType * 31;
            String str = this.mOrganizationName;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mDepartmentName;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mTitle;
            if (str3 != null) {
                i2 = str3.hashCode();
            }
            return ((hashCode2 + i2) * 31) + (this.mIsPrimary ? 1231 : 1237);
        }

        public String toString() {
            return String.format("type: %d, organization: %s, department: %s, title: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), this.mOrganizationName, this.mDepartmentName, this.mTitle, Boolean.valueOf(this.mIsPrimary)});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.ORGANIZATION;
        }
    }

    public static class ImData implements EntryElement {
        private final String mAddress;
        private final String mCustomProtocol;
        private final boolean mIsPrimary;
        private final int mProtocol;
        private final int mType;

        public ImData(int i, String str, String str2, int i2, boolean z) {
            this.mProtocol = i;
            this.mCustomProtocol = str;
            this.mType = i2;
            this.mAddress = str2;
            this.mIsPrimary = z;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/im");
            newInsert.withValue("data2", Integer.valueOf(this.mType));
            newInsert.withValue("data5", Integer.valueOf(this.mProtocol));
            newInsert.withValue("data1", this.mAddress);
            if (this.mProtocol == -1) {
                newInsert.withValue("data6", this.mCustomProtocol);
            }
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAddress);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ImData)) {
                return false;
            }
            ImData imData = (ImData) obj;
            if (this.mType == imData.mType && this.mProtocol == imData.mProtocol && TextUtils.equals(this.mCustomProtocol, imData.mCustomProtocol) && TextUtils.equals(this.mAddress, imData.mAddress) && this.mIsPrimary == imData.mIsPrimary) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = ((this.mType * 31) + this.mProtocol) * 31;
            String str = this.mCustomProtocol;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mAddress;
            if (str2 != null) {
                i2 = str2.hashCode();
            }
            return ((hashCode + i2) * 31) + (this.mIsPrimary ? 1231 : 1237);
        }

        public String toString() {
            return String.format("type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), Integer.valueOf(this.mProtocol), this.mCustomProtocol, this.mAddress, Boolean.valueOf(this.mIsPrimary)});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.IM;
        }
    }

    public static class PhotoData implements EntryElement {
        private final byte[] mBytes;
        private final String mFormat;
        private Integer mHashCode = null;
        private final boolean mIsPrimary;

        public PhotoData(String str, byte[] bArr, boolean z) {
            this.mFormat = str;
            this.mBytes = bArr;
            this.mIsPrimary = z;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/photo");
            newInsert.withValue("data15", this.mBytes);
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            byte[] bArr = this.mBytes;
            return bArr == null || bArr.length == 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PhotoData)) {
                return false;
            }
            PhotoData photoData = (PhotoData) obj;
            if (!TextUtils.equals(this.mFormat, photoData.mFormat) || !Arrays.equals(this.mBytes, photoData.mBytes) || this.mIsPrimary != photoData.mIsPrimary) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            Integer num = this.mHashCode;
            if (num != null) {
                return num.intValue();
            }
            String str = this.mFormat;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            byte[] bArr = this.mBytes;
            if (bArr != null) {
                for (byte b : bArr) {
                    hashCode += b;
                }
            }
            int i = (hashCode * 31) + (this.mIsPrimary ? 1231 : 1237);
            this.mHashCode = Integer.valueOf(i);
            return i;
        }

        public String toString() {
            return String.format("format: %s: size: %d, isPrimary: %s", new Object[]{this.mFormat, Integer.valueOf(this.mBytes.length), Boolean.valueOf(this.mIsPrimary)});
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.PHOTO;
        }
    }

    public static class NicknameData implements EntryElement {
        private final String mNickname;

        public NicknameData(String str) {
            this.mNickname = str;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/nickname");
            newInsert.withValue("data2", 1);
            newInsert.withValue("data1", this.mNickname);
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mNickname);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof NicknameData)) {
                return false;
            }
            return TextUtils.equals(this.mNickname, ((NicknameData) obj).mNickname);
        }

        public int hashCode() {
            String str = this.mNickname;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "nickname: " + this.mNickname;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.NICKNAME;
        }
    }

    public static class NoteData implements EntryElement {
        public final String mNote;

        public NoteData(String str) {
            this.mNote = str;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/note");
            newInsert.withValue("data1", this.mNote);
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mNote);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NoteData)) {
                return false;
            }
            return TextUtils.equals(this.mNote, ((NoteData) obj).mNote);
        }

        public int hashCode() {
            String str = this.mNote;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "note: " + this.mNote;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.NOTE;
        }
    }

    public static class WebsiteData implements EntryElement {
        private final String mWebsite;

        public WebsiteData(String str) {
            this.mWebsite = str;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/website");
            newInsert.withValue("data1", this.mWebsite);
            newInsert.withValue("data2", 1);
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mWebsite);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WebsiteData)) {
                return false;
            }
            return TextUtils.equals(this.mWebsite, ((WebsiteData) obj).mWebsite);
        }

        public int hashCode() {
            String str = this.mWebsite;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "website: " + this.mWebsite;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.WEBSITE;
        }
    }

    public static class BirthdayData implements EntryElement {
        private final String mBirthday;

        public BirthdayData(String str) {
            this.mBirthday = str;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/contact_event");
            newInsert.withValue("data1", this.mBirthday);
            newInsert.withValue("data2", 3);
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mBirthday);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BirthdayData)) {
                return false;
            }
            return TextUtils.equals(this.mBirthday, ((BirthdayData) obj).mBirthday);
        }

        public int hashCode() {
            String str = this.mBirthday;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "birthday: " + this.mBirthday;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.BIRTHDAY;
        }
    }

    public static class AnniversaryData implements EntryElement {
        private final String mAnniversary;

        public AnniversaryData(String str) {
            this.mAnniversary = str;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/contact_event");
            newInsert.withValue("data1", this.mAnniversary);
            newInsert.withValue("data2", 1);
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAnniversary);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnniversaryData)) {
                return false;
            }
            return TextUtils.equals(this.mAnniversary, ((AnniversaryData) obj).mAnniversary);
        }

        public int hashCode() {
            String str = this.mAnniversary;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "anniversary: " + this.mAnniversary;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.ANNIVERSARY;
        }
    }

    public static class SipData implements EntryElement {
        private final String mAddress;
        private final boolean mIsPrimary;
        private final String mLabel;
        private final int mType;

        public SipData(String str, int i, String str2, boolean z) {
            if (str.startsWith("sip:")) {
                this.mAddress = str.substring(4);
            } else {
                this.mAddress = str;
            }
            this.mType = i;
            this.mLabel = str2;
            this.mIsPrimary = z;
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/sip_address");
            newInsert.withValue("data1", this.mAddress);
            newInsert.withValue("data2", Integer.valueOf(this.mType));
            if (this.mType == 0) {
                newInsert.withValue("data3", this.mLabel);
            }
            boolean z = this.mIsPrimary;
            if (z) {
                newInsert.withValue("is_primary", Boolean.valueOf(z));
            }
            list.add(newInsert.build());
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAddress);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SipData)) {
                return false;
            }
            SipData sipData = (SipData) obj;
            if (this.mType != sipData.mType || !TextUtils.equals(this.mLabel, sipData.mLabel) || !TextUtils.equals(this.mAddress, sipData.mAddress) || this.mIsPrimary != sipData.mIsPrimary) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.mType * 31;
            String str = this.mLabel;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mAddress;
            if (str2 != null) {
                i2 = str2.hashCode();
            }
            return ((hashCode + i2) * 31) + (this.mIsPrimary ? 1231 : 1237);
        }

        public String toString() {
            return "sip: " + this.mAddress;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.SIP;
        }
    }

    public static class AndroidCustomData implements EntryElement {
        private final List<String> mDataList;
        private final String mMimeType;

        public AndroidCustomData(String str, List<String> list) {
            this.mMimeType = str;
            this.mDataList = list;
        }

        public static AndroidCustomData constructAndroidCustomData(List<String> list) {
            List<String> list2;
            String str = null;
            if (list == null) {
                list2 = null;
            } else if (list.size() < 2) {
                str = list.get(0);
                list2 = null;
            } else {
                int i = 16;
                if (list.size() < 16) {
                    i = list.size();
                }
                str = list.get(0);
                list2 = list.subList(1, i);
            }
            return new AndroidCustomData(str, list2);
        }

        public void constructInsertOperation(List<ContentProviderOperation> list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", this.mMimeType);
            for (int i2 = 0; i2 < this.mDataList.size(); i2++) {
                String str = this.mDataList.get(i2);
                if (!TextUtils.isEmpty(str)) {
                    newInsert.withValue("data" + (i2 + 1), str);
                }
            }
            list.add(newInsert.build());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
            r0 = r1.mDataList;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isEmpty() {
            /*
                r1 = this;
                java.lang.String r0 = r1.mMimeType
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                if (r0 != 0) goto L_0x0015
                java.util.List<java.lang.String> r0 = r1.mDataList
                if (r0 == 0) goto L_0x0015
                int r0 = r0.size()
                if (r0 != 0) goto L_0x0013
                goto L_0x0015
            L_0x0013:
                r0 = 0
                goto L_0x0016
            L_0x0015:
                r0 = 1
            L_0x0016:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardEntry.AndroidCustomData.isEmpty():boolean");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AndroidCustomData)) {
                return false;
            }
            AndroidCustomData androidCustomData = (AndroidCustomData) obj;
            if (!TextUtils.equals(this.mMimeType, androidCustomData.mMimeType)) {
                return false;
            }
            List<String> list = this.mDataList;
            if (list != null) {
                int size = list.size();
                if (size != androidCustomData.mDataList.size()) {
                    return false;
                }
                for (int i = 0; i < size; i++) {
                    if (!TextUtils.equals(this.mDataList.get(i), androidCustomData.mDataList.get(i))) {
                        return false;
                    }
                }
                return true;
            } else if (androidCustomData.mDataList == null) {
                return true;
            } else {
                return false;
            }
        }

        public int hashCode() {
            String str = this.mMimeType;
            int hashCode = str != null ? str.hashCode() : 0;
            List<String> list = this.mDataList;
            if (list != null) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    hashCode = (hashCode * 31) + (next != null ? next.hashCode() : 0);
                }
            }
            return hashCode;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("android-custom: " + this.mMimeType + ", data: ");
            List<String> list = this.mDataList;
            sb.append(list == null ? "null" : Arrays.toString(list.toArray()));
            return sb.toString();
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.ANDROID_CUSTOM;
        }
    }

    public final void iterateAllData(EntryElementIterator entryElementIterator) {
        entryElementIterator.onIterationStarted();
        entryElementIterator.onElementGroupStarted(this.mNameData.getEntryLabel());
        entryElementIterator.onElement(this.mNameData);
        entryElementIterator.onElementGroupEnded();
        iterateOneList(this.mPhoneList, entryElementIterator);
        iterateOneList(this.mEmailList, entryElementIterator);
        iterateOneList(this.mPostalList, entryElementIterator);
        iterateOneList(this.mOrganizationList, entryElementIterator);
        iterateOneList(this.mImList, entryElementIterator);
        iterateOneList(this.mPhotoList, entryElementIterator);
        iterateOneList(this.mWebsiteList, entryElementIterator);
        iterateOneList(this.mSipList, entryElementIterator);
        iterateOneList(this.mNicknameList, entryElementIterator);
        iterateOneList(this.mNoteList, entryElementIterator);
        iterateOneList(this.mAndroidCustomDataList, entryElementIterator);
        BirthdayData birthdayData = this.mBirthday;
        if (birthdayData != null) {
            entryElementIterator.onElementGroupStarted(birthdayData.getEntryLabel());
            entryElementIterator.onElement(this.mBirthday);
            entryElementIterator.onElementGroupEnded();
        }
        AnniversaryData anniversaryData = this.mAnniversary;
        if (anniversaryData != null) {
            entryElementIterator.onElementGroupStarted(anniversaryData.getEntryLabel());
            entryElementIterator.onElement(this.mAnniversary);
            entryElementIterator.onElementGroupEnded();
        }
        entryElementIterator.onIterationEnded();
    }

    private void iterateOneList(List<? extends EntryElement> list, EntryElementIterator entryElementIterator) {
        if (list != null && list.size() > 0) {
            entryElementIterator.onElementGroupStarted(((EntryElement) list.get(0)).getEntryLabel());
            for (EntryElement onElement : list) {
                entryElementIterator.onElement(onElement);
            }
            entryElementIterator.onElementGroupEnded();
        }
    }

    private class IsIgnorableIterator implements EntryElementIterator {
        private boolean mEmpty;

        public void onElementGroupEnded() {
        }

        public void onElementGroupStarted(EntryLabel entryLabel) {
        }

        public void onIterationEnded() {
        }

        public void onIterationStarted() {
        }

        private IsIgnorableIterator() {
            this.mEmpty = true;
        }

        public boolean onElement(EntryElement entryElement) {
            if (entryElement.isEmpty()) {
                return true;
            }
            this.mEmpty = false;
            return false;
        }

        public boolean getResult() {
            return this.mEmpty;
        }
    }

    private class ToStringIterator implements EntryElementIterator {
        private StringBuilder mBuilder;
        private boolean mFirstElement;

        private ToStringIterator() {
        }

        public void onIterationStarted() {
            this.mBuilder = new StringBuilder();
            StringBuilder sb = this.mBuilder;
            sb.append("[[hash: " + VCardEntry.this.hashCode() + "\n");
        }

        public void onElementGroupStarted(EntryLabel entryLabel) {
            StringBuilder sb = this.mBuilder;
            sb.append(entryLabel.toString() + ": ");
            this.mFirstElement = true;
        }

        public boolean onElement(EntryElement entryElement) {
            if (!this.mFirstElement) {
                this.mBuilder.append(", ");
                this.mFirstElement = false;
            }
            StringBuilder sb = this.mBuilder;
            sb.append("[");
            sb.append(entryElement.toString());
            sb.append("]");
            return true;
        }

        public void onElementGroupEnded() {
            this.mBuilder.append("\n");
        }

        public void onIterationEnded() {
            this.mBuilder.append("]]\n");
        }

        public String toString() {
            return this.mBuilder.toString();
        }
    }

    private class InsertOperationConstrutor implements EntryElementIterator {
        private final int mBackReferenceIndex;
        private final List<ContentProviderOperation> mOperationList;

        public void onElementGroupEnded() {
        }

        public void onElementGroupStarted(EntryLabel entryLabel) {
        }

        public void onIterationEnded() {
        }

        public void onIterationStarted() {
        }

        public InsertOperationConstrutor(List<ContentProviderOperation> list, int i) {
            this.mOperationList = list;
            this.mBackReferenceIndex = i;
        }

        public boolean onElement(EntryElement entryElement) {
            if (entryElement.isEmpty()) {
                return true;
            }
            entryElement.constructInsertOperation(this.mOperationList, this.mBackReferenceIndex);
            return true;
        }
    }

    public String toString() {
        ToStringIterator toStringIterator = new ToStringIterator();
        iterateAllData(toStringIterator);
        return toStringIterator.toString();
    }

    public VCardEntry() {
        this(-1073741824);
    }

    public VCardEntry(int i) {
        this(i, (Account) null);
    }

    public VCardEntry(int i, Account account) {
        this.mStarred = false;
        this.mNameData = new NameData();
        this.mVCardType = i;
        this.mAccount = account;
    }

    private void addPhone(int i, String str, String str2, boolean z) {
        if (this.mPhoneList == null) {
            this.mPhoneList = new ArrayList();
        }
        StringBuilder sb = new StringBuilder();
        String trim = str.trim();
        if (i != 6 && !VCardConfig.refrainPhoneNumberFormatting(this.mVCardType)) {
            int length = trim.length();
            boolean z2 = false;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = trim.charAt(i2);
                if (charAt == 'p' || charAt == 'P') {
                    sb.append(',');
                } else if (charAt == 'w' || charAt == 'W') {
                    sb.append(';');
                } else {
                    if (PhoneNumberUtils.is12Key(charAt) || (i2 == 0 && charAt == '+')) {
                        sb.append(charAt);
                    }
                }
                z2 = true;
            }
            if (!z2) {
                trim = VCardUtils.PhoneNumberUtilsPort.formatNumber(sb.toString(), VCardUtils.getPhoneNumberFormat(this.mVCardType));
            } else {
                trim = sb.toString();
            }
        }
        this.mPhoneList.add(new PhoneData(trim, i, str2, z));
    }

    private void addSip(String str, int i, String str2, boolean z) {
        if (this.mSipList == null) {
            this.mSipList = new ArrayList();
        }
        this.mSipList.add(new SipData(str, i, str2, z));
    }

    private void addNickName(String str) {
        if (this.mNicknameList == null) {
            this.mNicknameList = new ArrayList();
        }
        this.mNicknameList.add(new NicknameData(str));
    }

    private void addEmail(int i, String str, String str2, boolean z) {
        if (this.mEmailList == null) {
            this.mEmailList = new ArrayList();
        }
        this.mEmailList.add(new EmailData(str, i, str2, z));
    }

    private void addPostal(int i, List<String> list, String str, boolean z) {
        if (this.mPostalList == null) {
            this.mPostalList = new ArrayList(0);
        }
        this.mPostalList.add(PostalData.constructPostalData(list, i, str, z, this.mVCardType));
    }

    private void addNewOrganization(String str, String str2, String str3, String str4, int i, boolean z) {
        if (this.mOrganizationList == null) {
            this.mOrganizationList = new ArrayList();
        }
        this.mOrganizationList.add(new OrganizationData(str, str2, str3, str4, i, z));
    }

    private String buildSinglePhoneticNameFromSortAsParam(Map<String, Collection<String>> map) {
        Collection collection = map.get("SORT-AS");
        if (collection == null || collection.size() == 0) {
            return null;
        }
        if (collection.size() > 1) {
            Log.w("vCard", "Incorrect multiple SORT_AS parameters detected: " + Arrays.toString(collection.toArray()));
        }
        List<String> constructListFromValue = VCardUtils.constructListFromValue((String) collection.iterator().next(), this.mVCardType);
        StringBuilder sb = new StringBuilder();
        for (String append : constructListFromValue) {
            sb.append(append);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleOrgValue(int r8, java.util.List<java.lang.String> r9, java.util.Map<java.lang.String, java.util.Collection<java.lang.String>> r10, boolean r11) {
        /*
            r7 = this;
            java.lang.String r4 = r7.buildSinglePhoneticNameFromSortAsParam(r10)
            if (r9 != 0) goto L_0x0008
            java.util.List<java.lang.String> r9 = sEmptyList
        L_0x0008:
            int r10 = r9.size()
            r0 = 0
            if (r10 == 0) goto L_0x0042
            r1 = 0
            r2 = 1
            if (r10 == r2) goto L_0x003b
            java.lang.Object r0 = r9.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r3 = 1
        L_0x001f:
            if (r3 >= r10) goto L_0x0034
            if (r3 <= r2) goto L_0x0028
            r5 = 32
            r1.append(r5)
        L_0x0028:
            java.lang.Object r5 = r9.get(r3)
            java.lang.String r5 = (java.lang.String) r5
            r1.append(r5)
            int r3 = r3 + 1
            goto L_0x001f
        L_0x0034:
            java.lang.String r9 = r1.toString()
            r2 = r9
            r1 = r0
            goto L_0x0046
        L_0x003b:
            java.lang.Object r9 = r9.get(r1)
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x0044
        L_0x0042:
            java.lang.String r9 = ""
        L_0x0044:
            r1 = r9
            r2 = r0
        L_0x0046:
            java.util.List<com.android.vcard.VCardEntry$OrganizationData> r9 = r7.mOrganizationList
            if (r9 != 0) goto L_0x0052
            r3 = 0
            r0 = r7
            r5 = r8
            r6 = r11
            r0.addNewOrganization(r1, r2, r3, r4, r5, r6)
            return
        L_0x0052:
            java.util.Iterator r9 = r9.iterator()
        L_0x0056:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0078
            java.lang.Object r10 = r9.next()
            com.android.vcard.VCardEntry$OrganizationData r10 = (com.android.vcard.VCardEntry.OrganizationData) r10
            java.lang.String r0 = r10.mOrganizationName
            if (r0 != 0) goto L_0x0056
            java.lang.String r0 = r10.mDepartmentName
            if (r0 != 0) goto L_0x0056
            java.lang.String unused = r10.mOrganizationName = r1
            java.lang.String unused = r10.mDepartmentName = r2
            boolean unused = r10.mIsPrimary = r11
            return
        L_0x0078:
            r3 = 0
            r0 = r7
            r5 = r8
            r6 = r11
            r0.addNewOrganization(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardEntry.handleOrgValue(int, java.util.List, java.util.Map, boolean):void");
    }

    private void handleTitleValue(String str) {
        List<OrganizationData> list = this.mOrganizationList;
        if (list == null) {
            addNewOrganization((String) null, (String) null, str, (String) null, 1, false);
            return;
        }
        for (OrganizationData next : list) {
            if (next.mTitle == null) {
                String unused = next.mTitle = str;
                return;
            }
        }
        addNewOrganization((String) null, (String) null, str, (String) null, 1, false);
    }

    private void addIm(int i, String str, String str2, int i2, boolean z) {
        if (this.mImList == null) {
            this.mImList = new ArrayList();
        }
        this.mImList.add(new ImData(i, str, str2, i2, z));
    }

    private void addNote(String str) {
        if (this.mNoteList == null) {
            this.mNoteList = new ArrayList(1);
        }
        this.mNoteList.add(new NoteData(str));
    }

    private void addPhotoBytes(String str, byte[] bArr, boolean z) {
        if (this.mPhotoList == null) {
            this.mPhotoList = new ArrayList(1);
        }
        this.mPhotoList.add(new PhotoData(str, bArr, z));
    }

    private void tryHandleSortAsName(Map<String, Collection<String>> map) {
        Collection collection;
        if ((!VCardConfig.isVersion30(this.mVCardType) || (TextUtils.isEmpty(this.mNameData.mPhoneticFamily) && TextUtils.isEmpty(this.mNameData.mPhoneticMiddle) && TextUtils.isEmpty(this.mNameData.mPhoneticGiven))) && (collection = map.get("SORT-AS")) != null && collection.size() != 0) {
            if (collection.size() > 1) {
                Log.w("vCard", "Incorrect multiple SORT_AS parameters detected: " + Arrays.toString(collection.toArray()));
            }
            List<String> constructListFromValue = VCardUtils.constructListFromValue((String) collection.iterator().next(), this.mVCardType);
            int size = constructListFromValue.size();
            if (size > 3) {
                size = 3;
            }
            if (size != 2) {
                if (size == 3) {
                    String unused = this.mNameData.mPhoneticMiddle = constructListFromValue.get(2);
                }
                String unused2 = this.mNameData.mPhoneticFamily = constructListFromValue.get(0);
            }
            String unused3 = this.mNameData.mPhoneticGiven = constructListFromValue.get(1);
            String unused4 = this.mNameData.mPhoneticFamily = constructListFromValue.get(0);
        }
    }

    private void handleNProperty(List<String> list, Map<String, Collection<String>> map) {
        int size;
        tryHandleSortAsName(map);
        if (list != null && (size = list.size()) >= 1) {
            if (size > 5) {
                size = 5;
            }
            if (size != 2) {
                if (size != 3) {
                    if (size != 4) {
                        if (size == 5) {
                            String unused = this.mNameData.mSuffix = list.get(4);
                        }
                        String unused2 = this.mNameData.mFamily = list.get(0);
                    }
                    String unused3 = this.mNameData.mPrefix = list.get(3);
                }
                String unused4 = this.mNameData.mMiddle = list.get(2);
            }
            String unused5 = this.mNameData.mGiven = list.get(1);
            String unused6 = this.mNameData.mFamily = list.get(0);
        }
    }

    private void handlePhoneticNameFromSound(List<String> list) {
        int size;
        boolean z;
        if (TextUtils.isEmpty(this.mNameData.mPhoneticFamily) && TextUtils.isEmpty(this.mNameData.mPhoneticMiddle) && TextUtils.isEmpty(this.mNameData.mPhoneticGiven) && list != null && (size = list.size()) >= 1) {
            if (size > 3) {
                size = 3;
            }
            if (list.get(0).length() > 0) {
                int i = 1;
                while (true) {
                    if (i >= size) {
                        z = true;
                        break;
                    } else if (list.get(i).length() > 0) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    String[] split = list.get(0).split(" ");
                    int length = split.length;
                    if (length == 3) {
                        String unused = this.mNameData.mPhoneticFamily = split[0];
                        String unused2 = this.mNameData.mPhoneticMiddle = split[1];
                        String unused3 = this.mNameData.mPhoneticGiven = split[2];
                        return;
                    } else if (length == 2) {
                        String unused4 = this.mNameData.mPhoneticFamily = split[0];
                        String unused5 = this.mNameData.mPhoneticGiven = split[1];
                        return;
                    } else {
                        String unused6 = this.mNameData.mPhoneticGiven = list.get(0);
                        return;
                    }
                }
            }
            if (size != 2) {
                if (size == 3) {
                    String unused7 = this.mNameData.mPhoneticMiddle = list.get(2);
                }
                String unused8 = this.mNameData.mPhoneticFamily = list.get(0);
            }
            String unused9 = this.mNameData.mPhoneticGiven = list.get(1);
            String unused10 = this.mNameData.mPhoneticFamily = list.get(0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:143:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x024c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addProperty(com.android.vcard.VCardProperty r18) {
        /*
            r17 = this;
            r6 = r17
            java.lang.String r0 = r18.getName()
            java.util.Map r1 = r18.getParameterMap()
            java.util.List r2 = r18.getValueList()
            byte[] r3 = r18.getByteValue()
            if (r2 == 0) goto L_0x001a
            int r4 = r2.size()
            if (r4 != 0) goto L_0x001d
        L_0x001a:
            if (r3 != 0) goto L_0x001d
            return
        L_0x001d:
            r4 = 0
            if (r2 == 0) goto L_0x0029
            java.lang.String r5 = r6.listToString(r2)
            java.lang.String r5 = r5.trim()
            goto L_0x002a
        L_0x0029:
            r5 = r4
        L_0x002a:
            java.lang.String r7 = "VERSION"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x0034
            goto L_0x0414
        L_0x0034:
            java.lang.String r7 = "FN"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x0043
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String unused = r0.mFormatted = r5
            goto L_0x0414
        L_0x0043:
            java.lang.String r7 = "NAME"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x005e
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String r0 = r0.mFormatted
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0414
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String unused = r0.mFormatted = r5
            goto L_0x0414
        L_0x005e:
            java.lang.String r7 = "N"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x006b
            r6.handleNProperty(r2, r1)
            goto L_0x0414
        L_0x006b:
            java.lang.String r7 = "SORT-STRING"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x007a
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String unused = r0.mSortString = r5
            goto L_0x0414
        L_0x007a:
            java.lang.String r7 = "NICKNAME"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L_0x0411
            java.lang.String r7 = "X-NICKNAME"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x008c
            goto L_0x0411
        L_0x008c:
            java.lang.String r7 = "SOUND"
            boolean r7 = r0.equals(r7)
            java.lang.String r8 = "TYPE"
            if (r7 == 0) goto L_0x00b1
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x0414
            java.lang.String r1 = "X-IRMC-N"
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0414
            int r0 = r6.mVCardType
            java.util.List r0 = com.android.vcard.VCardUtils.constructListFromValue(r5, r0)
            r6.handlePhoneticNameFromSound(r0)
            goto L_0x0414
        L_0x00b1:
            java.lang.String r7 = "ADR"
            boolean r7 = r0.equals(r7)
            java.lang.String r9 = "X-"
            java.lang.String r10 = "WORK"
            java.lang.String r11 = "HOME"
            r13 = 2
            java.lang.String r14 = "PREF"
            r12 = 1
            if (r7 == 0) goto L_0x0156
            java.util.Iterator r0 = r2.iterator()
        L_0x00c7:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00db
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00c7
            r0 = 0
            goto L_0x00dc
        L_0x00db:
            r0 = 1
        L_0x00dc:
            if (r0 == 0) goto L_0x00df
            return
        L_0x00df:
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x014b
            java.util.Iterator r0 = r0.iterator()
            r3 = r4
            r1 = -1
            r5 = 0
        L_0x00ee:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L_0x014e
            java.lang.Object r7 = r0.next()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = r7.toUpperCase()
            boolean r16 = r8.equals(r14)
            if (r16 == 0) goto L_0x0106
            r5 = 1
            goto L_0x00ee
        L_0x0106:
            boolean r16 = r8.equals(r11)
            if (r16 == 0) goto L_0x010f
            r3 = r4
            r1 = 1
            goto L_0x00ee
        L_0x010f:
            boolean r16 = r8.equals(r10)
            if (r16 != 0) goto L_0x0148
            java.lang.String r15 = "COMPANY"
            boolean r15 = r8.equalsIgnoreCase(r15)
            if (r15 == 0) goto L_0x011e
            goto L_0x0148
        L_0x011e:
            java.lang.String r15 = "PARCEL"
            boolean r15 = r8.equals(r15)
            if (r15 != 0) goto L_0x00ee
            java.lang.String r15 = "DOM"
            boolean r15 = r8.equals(r15)
            if (r15 != 0) goto L_0x00ee
            java.lang.String r15 = "INTL"
            boolean r15 = r8.equals(r15)
            if (r15 == 0) goto L_0x0137
            goto L_0x00ee
        L_0x0137:
            if (r1 >= 0) goto L_0x00ee
            boolean r1 = r8.startsWith(r9)
            if (r1 == 0) goto L_0x0145
            java.lang.String r1 = r7.substring(r13)
            r3 = r1
            goto L_0x0146
        L_0x0145:
            r3 = r7
        L_0x0146:
            r1 = 0
            goto L_0x00ee
        L_0x0148:
            r3 = r4
            r1 = 2
            goto L_0x00ee
        L_0x014b:
            r3 = r4
            r1 = -1
            r5 = 0
        L_0x014e:
            if (r1 >= 0) goto L_0x0151
            r1 = 1
        L_0x0151:
            r6.addPostal(r1, r2, r3, r5)
            goto L_0x0414
        L_0x0156:
            java.lang.String r7 = "EMAIL"
            boolean r7 = r0.equals(r7)
            r15 = 4
            if (r7 == 0) goto L_0x01ba
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x01b0
            java.util.Iterator r0 = r0.iterator()
            r1 = -1
            r2 = 0
        L_0x016d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x01b2
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r7 = r3.toUpperCase()
            boolean r8 = r7.equals(r14)
            if (r8 == 0) goto L_0x0185
            r2 = 1
            goto L_0x016d
        L_0x0185:
            boolean r8 = r7.equals(r11)
            if (r8 == 0) goto L_0x018d
            r1 = 1
            goto L_0x016d
        L_0x018d:
            boolean r8 = r7.equals(r10)
            if (r8 == 0) goto L_0x0195
            r1 = 2
            goto L_0x016d
        L_0x0195:
            java.lang.String r8 = "CELL"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x019f
            r1 = 4
            goto L_0x016d
        L_0x019f:
            if (r1 >= 0) goto L_0x016d
            boolean r1 = r7.startsWith(r9)
            if (r1 == 0) goto L_0x01ad
            java.lang.String r1 = r3.substring(r13)
            r4 = r1
            goto L_0x01ae
        L_0x01ad:
            r4 = r3
        L_0x01ae:
            r1 = 0
            goto L_0x016d
        L_0x01b0:
            r1 = -1
            r2 = 0
        L_0x01b2:
            if (r1 >= 0) goto L_0x01b5
            r1 = 3
        L_0x01b5:
            r6.addEmail(r1, r5, r4, r2)
            goto L_0x0414
        L_0x01ba:
            java.lang.String r7 = "ORG"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x01e9
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x01e3
            java.util.Iterator r0 = r0.iterator()
            r15 = 0
        L_0x01cf:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x01e4
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r3.equals(r14)
            if (r3 == 0) goto L_0x01cf
            r15 = 1
            goto L_0x01cf
        L_0x01e3:
            r15 = 0
        L_0x01e4:
            r6.handleOrgValue(r12, r2, r1, r15)
            goto L_0x0414
        L_0x01e9:
            java.lang.String r2 = "TITLE"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x01f6
            r6.handleTitleValue(r5)
            goto L_0x0414
        L_0x01f6:
            java.lang.String r2 = "ROLE"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0200
            goto L_0x0414
        L_0x0200:
            java.lang.String r2 = "PHOTO"
            boolean r2 = r0.equals(r2)
            java.lang.String r7 = "URL"
            if (r2 != 0) goto L_0x03d6
            java.lang.String r2 = "LOGO"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0214
            goto L_0x03d6
        L_0x0214:
            java.lang.String r2 = "TEL"
            boolean r2 = r0.equals(r2)
            java.lang.String r3 = "sip:"
            if (r2 == 0) goto L_0x027c
            int r0 = r6.mVCardType
            boolean r0 = com.android.vcard.VCardConfig.isVersion40(r0)
            if (r0 == 0) goto L_0x023d
            boolean r0 = r5.startsWith(r3)
            if (r0 == 0) goto L_0x022f
            r2 = r4
            r0 = 1
            goto L_0x023f
        L_0x022f:
            java.lang.String r0 = "tel:"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L_0x023d
            java.lang.String r0 = r5.substring(r15)
            r2 = r0
            goto L_0x023e
        L_0x023d:
            r2 = r5
        L_0x023e:
            r0 = 0
        L_0x023f:
            if (r0 == 0) goto L_0x024c
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            r6.handleSipCase(r5, r0)
            goto L_0x0414
        L_0x024c:
            int r0 = r5.length()
            if (r0 != 0) goto L_0x0253
            return
        L_0x0253:
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.Object r1 = com.android.vcard.VCardUtils.getPhoneTypeFromStrings(r0, r2)
            boolean r3 = r1 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0268
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r15 = r1.intValue()
            goto L_0x026d
        L_0x0268:
            java.lang.String r4 = r1.toString()
            r15 = 0
        L_0x026d:
            if (r0 == 0) goto L_0x0276
            boolean r0 = r0.contains(r14)
            if (r0 == 0) goto L_0x0276
            goto L_0x0277
        L_0x0276:
            r12 = 0
        L_0x0277:
            r6.addPhone(r15, r2, r4, r12)
            goto L_0x0414
        L_0x027c:
            java.lang.String r2 = "X-SKYPE-PSTNNUMBER"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x029a
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x0293
            boolean r0 = r0.contains(r14)
            if (r0 == 0) goto L_0x0293
            goto L_0x0294
        L_0x0293:
            r12 = 0
        L_0x0294:
            r0 = 7
            r6.addPhone(r0, r5, r4, r12)
            goto L_0x0414
        L_0x029a:
            java.util.Map<java.lang.String, java.lang.Integer> r2 = sImMap
            boolean r2 = r2.containsKey(r0)
            if (r2 == 0) goto L_0x02f9
            java.util.Map<java.lang.String, java.lang.Integer> r2 = sImMap
            java.lang.Object r0 = r2.get(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r2 = r0.intValue()
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x02e4
            java.util.Iterator r0 = r0.iterator()
            r1 = -1
            r16 = 0
        L_0x02bd:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x02e7
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = r3.equals(r14)
            if (r4 == 0) goto L_0x02d2
            r16 = 1
            goto L_0x02bd
        L_0x02d2:
            if (r1 >= 0) goto L_0x02bd
            boolean r4 = r3.equalsIgnoreCase(r11)
            if (r4 == 0) goto L_0x02dc
            r1 = 1
            goto L_0x02bd
        L_0x02dc:
            boolean r3 = r3.equalsIgnoreCase(r10)
            if (r3 == 0) goto L_0x02bd
            r1 = 2
            goto L_0x02bd
        L_0x02e4:
            r1 = -1
            r16 = 0
        L_0x02e7:
            if (r1 >= 0) goto L_0x02eb
            r4 = 1
            goto L_0x02ec
        L_0x02eb:
            r4 = r1
        L_0x02ec:
            r3 = 0
            r0 = r17
            r1 = r2
            r2 = r3
            r3 = r5
            r5 = r16
            r0.addIm(r1, r2, r3, r4, r5)
            goto L_0x0414
        L_0x02f9:
            java.lang.String r2 = "NOTE"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0306
            r6.addNote(r5)
            goto L_0x0414
        L_0x0306:
            boolean r2 = r0.equals(r7)
            if (r2 == 0) goto L_0x0323
            java.util.List<com.android.vcard.VCardEntry$WebsiteData> r0 = r6.mWebsiteList
            if (r0 != 0) goto L_0x0317
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r12)
            r6.mWebsiteList = r0
        L_0x0317:
            java.util.List<com.android.vcard.VCardEntry$WebsiteData> r0 = r6.mWebsiteList
            com.android.vcard.VCardEntry$WebsiteData r1 = new com.android.vcard.VCardEntry$WebsiteData
            r1.<init>(r5)
            r0.add(r1)
            goto L_0x0414
        L_0x0323:
            java.lang.String r2 = "BDAY"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0334
            com.android.vcard.VCardEntry$BirthdayData r0 = new com.android.vcard.VCardEntry$BirthdayData
            r0.<init>(r5)
            r6.mBirthday = r0
            goto L_0x0414
        L_0x0334:
            java.lang.String r2 = "ANNIVERSARY"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0345
            com.android.vcard.VCardEntry$AnniversaryData r0 = new com.android.vcard.VCardEntry$AnniversaryData
            r0.<init>(r5)
            r6.mAnniversary = r0
            goto L_0x0414
        L_0x0345:
            java.lang.String r2 = "X-PHONETIC-FIRST-NAME"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0354
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String unused = r0.mPhoneticGiven = r5
            goto L_0x0414
        L_0x0354:
            java.lang.String r2 = "X-PHONETIC-MIDDLE-NAME"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0363
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String unused = r0.mPhoneticMiddle = r5
            goto L_0x0414
        L_0x0363:
            java.lang.String r2 = "X-PHONETIC-LAST-NAME"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0372
            com.android.vcard.VCardEntry$NameData r0 = r6.mNameData
            java.lang.String unused = r0.mPhoneticFamily = r5
            goto L_0x0414
        L_0x0372:
            java.lang.String r2 = "IMPP"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x038b
            boolean r0 = r5.startsWith(r3)
            if (r0 == 0) goto L_0x0414
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            r6.handleSipCase(r5, r0)
            goto L_0x0414
        L_0x038b:
            java.lang.String r2 = "X-SIP"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x03a4
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x0414
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            r6.handleSipCase(r5, r0)
            goto L_0x0414
        L_0x03a4:
            java.lang.String r1 = "X-ANDROID-CUSTOM"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x03b6
            int r0 = r6.mVCardType
            java.util.List r0 = com.android.vcard.VCardUtils.constructListFromValue(r5, r0)
            r6.handleAndroidCustomProperty(r0)
            goto L_0x0414
        L_0x03b6:
            java.lang.String r1 = r0.toUpperCase()
            boolean r1 = r1.startsWith(r9)
            if (r1 == 0) goto L_0x0414
            java.util.List<android.util.Pair<java.lang.String, java.lang.String>> r1 = r6.mUnknownXData
            if (r1 != 0) goto L_0x03cb
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r6.mUnknownXData = r1
        L_0x03cb:
            java.util.List<android.util.Pair<java.lang.String, java.lang.String>> r1 = r6.mUnknownXData
            android.util.Pair r2 = new android.util.Pair
            r2.<init>(r0, r5)
            r1.add(r2)
            goto L_0x0414
        L_0x03d6:
            java.lang.String r0 = "VALUE"
            java.lang.Object r0 = r1.get(r0)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x03e7
            boolean r0 = r0.contains(r7)
            if (r0 == 0) goto L_0x03e7
            goto L_0x0414
        L_0x03e7:
            java.lang.Object r0 = r1.get(r8)
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x040c
            java.util.Iterator r0 = r0.iterator()
            r15 = 0
        L_0x03f4:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x040d
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r14.equals(r1)
            if (r2 == 0) goto L_0x0408
            r15 = 1
            goto L_0x03f4
        L_0x0408:
            if (r4 != 0) goto L_0x03f4
            r4 = r1
            goto L_0x03f4
        L_0x040c:
            r15 = 0
        L_0x040d:
            r6.addPhotoBytes(r4, r3, r15)
            goto L_0x0414
        L_0x0411:
            r6.addNickName(r5)
        L_0x0414:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardEntry.addProperty(com.android.vcard.VCardProperty):void");
    }

    private void handleSipCase(String str, Collection<String> collection) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("sip:")) {
                str = str.substring(4);
                if (str.length() == 0) {
                    return;
                }
            }
            int i = -1;
            String str2 = null;
            boolean z = false;
            if (collection != null) {
                boolean z2 = false;
                for (String next : collection) {
                    String upperCase = next.toUpperCase();
                    if (upperCase.equals("PREF")) {
                        z2 = true;
                    } else if (upperCase.equals("HOME")) {
                        i = 1;
                    } else if (upperCase.equals("WORK")) {
                        i = 2;
                    } else if (i < 0) {
                        if (upperCase.startsWith("X-")) {
                            str2 = next.substring(2);
                        } else {
                            str2 = next;
                        }
                        i = 0;
                    }
                }
                z = z2;
            }
            if (i < 0) {
                i = 3;
            }
            addSip(str, i, str2, z);
        }
    }

    public void addChild(VCardEntry vCardEntry) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList();
        }
        this.mChildren.add(vCardEntry);
    }

    private void handleAndroidCustomProperty(List<String> list) {
        if (this.mAndroidCustomDataList == null) {
            this.mAndroidCustomDataList = new ArrayList();
        }
        this.mAndroidCustomDataList.add(AndroidCustomData.constructAndroidCustomData(list));
    }

    private String constructDisplayName() {
        String str;
        if (!TextUtils.isEmpty(this.mNameData.mFormatted)) {
            str = this.mNameData.mFormatted;
        } else if (!this.mNameData.emptyStructuredName()) {
            str = VCardUtils.constructNameFromElements(this.mVCardType, this.mNameData.mFamily, this.mNameData.mMiddle, this.mNameData.mGiven, this.mNameData.mPrefix, this.mNameData.mSuffix);
        } else if (!this.mNameData.emptyPhoneticStructuredName()) {
            str = VCardUtils.constructNameFromElements(this.mVCardType, this.mNameData.mPhoneticFamily, this.mNameData.mPhoneticMiddle, this.mNameData.mPhoneticGiven);
        } else {
            List<EmailData> list = this.mEmailList;
            if (list == null || list.size() <= 0) {
                List<PhoneData> list2 = this.mPhoneList;
                if (list2 == null || list2.size() <= 0) {
                    List<PostalData> list3 = this.mPostalList;
                    if (list3 == null || list3.size() <= 0) {
                        List<OrganizationData> list4 = this.mOrganizationList;
                        str = (list4 == null || list4.size() <= 0) ? null : this.mOrganizationList.get(0).getFormattedString();
                    } else {
                        str = this.mPostalList.get(0).getFormattedAddress(this.mVCardType);
                    }
                } else {
                    str = this.mPhoneList.get(0).mNumber;
                }
            } else {
                str = this.mEmailList.get(0).mAddress;
            }
        }
        return str == null ? "" : str;
    }

    public void consolidateFields() {
        this.mNameData.displayName = constructDisplayName();
    }

    public boolean isIgnorable() {
        IsIgnorableIterator isIgnorableIterator = new IsIgnorableIterator();
        iterateAllData(isIgnorableIterator);
        return isIgnorableIterator.getResult();
    }

    public ArrayList<ContentProviderOperation> constructInsertOperations(ContentResolver contentResolver, ArrayList<ContentProviderOperation> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (isIgnorable()) {
            return arrayList;
        }
        int size = arrayList.size();
        ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
        Account account = this.mAccount;
        if (account != null) {
            newInsert.withValue("account_name", account.name);
            newInsert.withValue("account_type", this.mAccount.type);
        } else {
            newInsert.withValue("account_name", (Object) null);
            newInsert.withValue("account_type", (Object) null);
        }
        if (getStarred()) {
            newInsert.withValue(ContactSaveService.EXTRA_STARRED_FLAG, 1);
        }
        arrayList.add(newInsert.build());
        arrayList.size();
        iterateAllData(new InsertOperationConstrutor(arrayList, size));
        arrayList.size();
        return arrayList;
    }

    private String listToString(List<String> list) {
        int size = list.size();
        if (size <= 1) {
            return size == 1 ? list.get(0) : "";
        }
        StringBuilder sb = new StringBuilder();
        for (String append : list) {
            sb.append(append);
            if (size - 1 > 0) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public String getDisplayName() {
        NameData nameData = this.mNameData;
        if (nameData.displayName == null) {
            nameData.displayName = constructDisplayName();
        }
        return this.mNameData.displayName;
    }
}
