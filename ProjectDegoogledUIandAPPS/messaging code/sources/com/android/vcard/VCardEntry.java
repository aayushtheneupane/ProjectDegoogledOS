package com.android.vcard;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.vcard.VCardConstants;
import com.android.vcard.VCardUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p026b.p027a.p030b.p031a.C0632a;

public class VCardEntry {
    private static final int DEFAULT_ORGANIZATION_TYPE = 1;
    private static final String LOG_TAG = "vCard";
    private static final List sEmptyList = Collections.unmodifiableList(new ArrayList(0));
    private static final Map sImMap = new HashMap();
    private final Account mAccount;
    private List mAndroidCustomDataList;
    private AnniversaryData mAnniversary;
    private BirthdayData mBirthday;
    private List mChildren;
    private List mEmailList;
    private List mImList;
    private final NameData mNameData;
    private List mNicknameList;
    private List mNoteList;
    private List mOrganizationList;
    private List mPhoneList;
    private List mPhotoList;
    private List mPostalList;
    private List mSipList;
    private boolean mStarred;
    private List mUnknownXData;
    private final int mVCardType;
    private List mWebsiteList;

    public class AndroidCustomData implements EntryElement {
        private final List mDataList;
        private final String mMimeType;

        public AndroidCustomData(String str, List list) {
            this.mMimeType = str;
            this.mDataList = list;
        }

        public static AndroidCustomData constructAndroidCustomData(List list) {
            List list2;
            String str = null;
            if (list == null) {
                list2 = null;
            } else if (list.size() < 2) {
                str = (String) list.get(0);
                list2 = null;
            } else {
                int i = 16;
                if (list.size() < 16) {
                    i = list.size();
                }
                str = (String) list.get(0);
                list2 = list.subList(1, i);
            }
            return new AndroidCustomData(str, list2);
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", this.mMimeType);
            for (int i2 = 0; i2 < this.mDataList.size(); i2++) {
                String str = (String) this.mDataList.get(i2);
                if (!TextUtils.isEmpty(str)) {
                    StringBuilder Pa = C0632a.m1011Pa("data");
                    Pa.append(i2 + 1);
                    newInsert.withValue(Pa.toString(), str);
                }
            }
            list.add(newInsert.build());
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
            List list = this.mDataList;
            if (list != null) {
                int size = list.size();
                if (size != androidCustomData.mDataList.size()) {
                    return false;
                }
                for (int i = 0; i < size; i++) {
                    if (!TextUtils.equals((CharSequence) this.mDataList.get(i), (CharSequence) androidCustomData.mDataList.get(i))) {
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

        public List getDataList() {
            return this.mDataList;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.ANDROID_CUSTOM;
        }

        public String getMimeType() {
            return this.mMimeType;
        }

        public int hashCode() {
            String str = this.mMimeType;
            int hashCode = str != null ? str.hashCode() : 0;
            List<String> list = this.mDataList;
            if (list != null) {
                for (String str2 : list) {
                    hashCode = (hashCode * 31) + (str2 != null ? str2.hashCode() : 0);
                }
            }
            return hashCode;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
            r1 = r1.mDataList;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isEmpty() {
            /*
                r1 = this;
                java.lang.String r0 = r1.mMimeType
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                if (r0 != 0) goto L_0x0015
                java.util.List r1 = r1.mDataList
                if (r1 == 0) goto L_0x0015
                int r1 = r1.size()
                if (r1 != 0) goto L_0x0013
                goto L_0x0015
            L_0x0013:
                r1 = 0
                goto L_0x0016
            L_0x0015:
                r1 = 1
            L_0x0016:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardEntry.AndroidCustomData.isEmpty():boolean");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            StringBuilder Pa = C0632a.m1011Pa("android-custom: ");
            Pa.append(this.mMimeType);
            Pa.append(", data: ");
            sb.append(Pa.toString());
            List list = this.mDataList;
            sb.append(list == null ? "null" : Arrays.toString(list.toArray()));
            return sb.toString();
        }
    }

    public class AnniversaryData implements EntryElement {
        private final String mAnniversary;

        public AnniversaryData(String str) {
            this.mAnniversary = str;
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/contact_event");
            newInsert.withValue("data1", this.mAnniversary);
            newInsert.withValue("data2", 1);
            list.add(newInsert.build());
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

        public String getAnniversary() {
            return this.mAnniversary;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.ANNIVERSARY;
        }

        public int hashCode() {
            String str = this.mAnniversary;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAnniversary);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("anniversary: ");
            Pa.append(this.mAnniversary);
            return Pa.toString();
        }
    }

    public class BirthdayData implements EntryElement {
        /* access modifiers changed from: private */
        public final String mBirthday;

        public BirthdayData(String str) {
            this.mBirthday = str;
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/contact_event");
            newInsert.withValue("data1", this.mBirthday);
            newInsert.withValue("data2", 3);
            list.add(newInsert.build());
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

        public String getBirthday() {
            return this.mBirthday;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.BIRTHDAY;
        }

        public int hashCode() {
            String str = this.mBirthday;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mBirthday);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("birthday: ");
            Pa.append(this.mBirthday);
            return Pa.toString();
        }
    }

    public class EmailData implements EntryElement {
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

        public void constructInsertOperation(List list, int i) {
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

        public String getAddress() {
            return this.mAddress;
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.EMAIL;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public int getType() {
            return this.mType;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAddress);
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public String toString() {
            return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), this.mAddress, this.mLabel, Boolean.valueOf(this.mIsPrimary)});
        }
    }

    public interface EntryElement {
        void constructInsertOperation(List list, int i);

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

    public class ImData implements EntryElement {
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

        public void constructInsertOperation(List list, int i) {
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

        public String getAddress() {
            return this.mAddress;
        }

        public String getCustomProtocol() {
            return this.mCustomProtocol;
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.IM;
        }

        public int getProtocol() {
            return this.mProtocol;
        }

        public int getType() {
            return this.mType;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAddress);
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public String toString() {
            return String.format("type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), Integer.valueOf(this.mProtocol), this.mCustomProtocol, this.mAddress, Boolean.valueOf(this.mIsPrimary)});
        }
    }

    public class NameData implements EntryElement {
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

        public void constructInsertOperation(List list, int i) {
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

        public boolean emptyPhoneticStructuredName() {
            return TextUtils.isEmpty(this.mPhoneticFamily) && TextUtils.isEmpty(this.mPhoneticGiven) && TextUtils.isEmpty(this.mPhoneticMiddle);
        }

        public boolean emptyStructuredName() {
            return TextUtils.isEmpty(this.mFamily) && TextUtils.isEmpty(this.mGiven) && TextUtils.isEmpty(this.mMiddle) && TextUtils.isEmpty(this.mPrefix) && TextUtils.isEmpty(this.mSuffix);
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

        public final EntryLabel getEntryLabel() {
            return EntryLabel.NAME;
        }

        public String getFamily() {
            return this.mFamily;
        }

        public String getFormatted() {
            return this.mFormatted;
        }

        public String getGiven() {
            return this.mGiven;
        }

        public String getMiddle() {
            return this.mMiddle;
        }

        public String getPrefix() {
            return this.mPrefix;
        }

        public String getSortString() {
            return this.mSortString;
        }

        public String getSuffix() {
            return this.mSuffix;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mFamily) && TextUtils.isEmpty(this.mMiddle) && TextUtils.isEmpty(this.mGiven) && TextUtils.isEmpty(this.mPrefix) && TextUtils.isEmpty(this.mSuffix) && TextUtils.isEmpty(this.mFormatted) && TextUtils.isEmpty(this.mPhoneticFamily) && TextUtils.isEmpty(this.mPhoneticMiddle) && TextUtils.isEmpty(this.mPhoneticGiven) && TextUtils.isEmpty(this.mSortString);
        }

        public void setFamily(String str) {
            this.mFamily = str;
        }

        public void setGiven(String str) {
            this.mGiven = str;
        }

        public void setMiddle(String str) {
            this.mMiddle = str;
        }

        public void setPrefix(String str) {
            this.mPrefix = str;
        }

        public void setSuffix(String str) {
            this.mSuffix = str;
        }

        public String toString() {
            return String.format("family: %s, given: %s, middle: %s, prefix: %s, suffix: %s", new Object[]{this.mFamily, this.mGiven, this.mMiddle, this.mPrefix, this.mSuffix});
        }
    }

    public class NicknameData implements EntryElement {
        private final String mNickname;

        public NicknameData(String str) {
            this.mNickname = str;
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/nickname");
            newInsert.withValue("data2", 1);
            newInsert.withValue("data1", this.mNickname);
            list.add(newInsert.build());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof NicknameData)) {
                return false;
            }
            return TextUtils.equals(this.mNickname, ((NicknameData) obj).mNickname);
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.NICKNAME;
        }

        public String getNickname() {
            return this.mNickname;
        }

        public int hashCode() {
            String str = this.mNickname;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mNickname);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("nickname: ");
            Pa.append(this.mNickname);
            return Pa.toString();
        }
    }

    public class NoteData implements EntryElement {
        public final String mNote;

        public NoteData(String str) {
            this.mNote = str;
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/note");
            newInsert.withValue("data1", this.mNote);
            list.add(newInsert.build());
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

        public EntryLabel getEntryLabel() {
            return EntryLabel.NOTE;
        }

        public String getNote() {
            return this.mNote;
        }

        public int hashCode() {
            String str = this.mNote;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mNote);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("note: ");
            Pa.append(this.mNote);
            return Pa.toString();
        }
    }

    public class OrganizationData implements EntryElement {
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

        public void constructInsertOperation(List list, int i) {
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

        public String getDepartmentName() {
            return this.mDepartmentName;
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.ORGANIZATION;
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

        public String getOrganizationName() {
            return this.mOrganizationName;
        }

        public String getPhoneticName() {
            return this.mPhoneticName;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public int getType() {
            return this.mType;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mOrganizationName) && TextUtils.isEmpty(this.mDepartmentName) && TextUtils.isEmpty(this.mTitle) && TextUtils.isEmpty(this.mPhoneticName);
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public String toString() {
            return String.format("type: %d, organization: %s, department: %s, title: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), this.mOrganizationName, this.mDepartmentName, this.mTitle, Boolean.valueOf(this.mIsPrimary)});
        }
    }

    public class PhoneData implements EntryElement {
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

        public void constructInsertOperation(List list, int i) {
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

        public final EntryLabel getEntryLabel() {
            return EntryLabel.PHONE;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public String getNumber() {
            return this.mNumber;
        }

        public int getType() {
            return this.mType;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mNumber);
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public String toString() {
            return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[]{Integer.valueOf(this.mType), this.mNumber, this.mLabel, Boolean.valueOf(this.mIsPrimary)});
        }
    }

    public class PhotoData implements EntryElement {
        private final byte[] mBytes;
        private final String mFormat;
        private Integer mHashCode = null;
        private final boolean mIsPrimary;

        public PhotoData(String str, byte[] bArr, boolean z) {
            this.mFormat = str;
            this.mBytes = bArr;
            this.mIsPrimary = z;
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/photo");
            newInsert.withValue("data15", this.mBytes);
            if (this.mIsPrimary) {
                newInsert.withValue("is_primary", 1);
            }
            list.add(newInsert.build());
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

        public byte[] getBytes() {
            return this.mBytes;
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.PHOTO;
        }

        public String getFormat() {
            return this.mFormat;
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

        public boolean isEmpty() {
            byte[] bArr = this.mBytes;
            return bArr == null || bArr.length == 0;
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public String toString() {
            return String.format("format: %s: size: %d, isPrimary: %s", new Object[]{this.mFormat, Integer.valueOf(this.mBytes.length), Boolean.valueOf(this.mIsPrimary)});
        }
    }

    public class PostalData implements EntryElement {
        private static final int ADDR_MAX_DATA_SIZE = 7;
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

        public static PostalData constructPostalData(List list, int i, String str, boolean z, int i2) {
            String[] strArr = new String[7];
            int size = list.size();
            if (size > 7) {
                size = 7;
            }
            Iterator it = list.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                strArr[i3] = (String) it.next();
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

        public void constructInsertOperation(List list, int i) {
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

        public String getCountry() {
            return this.mCountry;
        }

        public final EntryLabel getEntryLabel() {
            return EntryLabel.POSTAL_ADDRESS;
        }

        public String getExtendedAddress() {
            return this.mExtendedAddress;
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

        public String getLabel() {
            return this.mLabel;
        }

        public String getLocalty() {
            return this.mLocalty;
        }

        public String getPobox() {
            return this.mPobox;
        }

        public String getPostalCode() {
            return this.mPostalCode;
        }

        public String getRegion() {
            return this.mRegion;
        }

        public String getStreet() {
            return this.mStreet;
        }

        public int getType() {
            return this.mType;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mPobox) && TextUtils.isEmpty(this.mExtendedAddress) && TextUtils.isEmpty(this.mStreet) && TextUtils.isEmpty(this.mLocalty) && TextUtils.isEmpty(this.mRegion) && TextUtils.isEmpty(this.mPostalCode) && TextUtils.isEmpty(this.mCountry);
        }

        public boolean isPrimary() {
            return this.mIsPrimary;
        }

        public String toString() {
            return String.format("type: %d, label: %s, isPrimary: %s, pobox: %s, extendedAddress: %s, street: %s, localty: %s, region: %s, postalCode %s, country: %s", new Object[]{Integer.valueOf(this.mType), this.mLabel, Boolean.valueOf(this.mIsPrimary), this.mPobox, this.mExtendedAddress, this.mStreet, this.mLocalty, this.mRegion, this.mPostalCode, this.mCountry});
        }
    }

    public class SipData implements EntryElement {
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

        public void constructInsertOperation(List list, int i) {
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

        public String getAddress() {
            return this.mAddress;
        }

        public EntryLabel getEntryLabel() {
            return EntryLabel.SIP;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public int getType() {
            return this.mType;
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

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mAddress);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("sip: ");
            Pa.append(this.mAddress);
            return Pa.toString();
        }
    }

    public class WebsiteData implements EntryElement {
        private final String mWebsite;

        public WebsiteData(String str) {
            this.mWebsite = str;
        }

        public void constructInsertOperation(List list, int i) {
            ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            newInsert.withValueBackReference("raw_contact_id", i);
            newInsert.withValue("mimetype", "vnd.android.cursor.item/website");
            newInsert.withValue("data1", this.mWebsite);
            newInsert.withValue("data2", 1);
            list.add(newInsert.build());
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

        public EntryLabel getEntryLabel() {
            return EntryLabel.WEBSITE;
        }

        public String getWebsite() {
            return this.mWebsite;
        }

        public int hashCode() {
            String str = this.mWebsite;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public boolean isEmpty() {
            return TextUtils.isEmpty(this.mWebsite);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("website: ");
            Pa.append(this.mWebsite);
            return Pa.toString();
        }
    }

    static {
        sImMap.put(VCardConstants.PROPERTY_X_AIM, 0);
        sImMap.put(VCardConstants.PROPERTY_X_MSN, 1);
        sImMap.put(VCardConstants.PROPERTY_X_YAHOO, 2);
        sImMap.put(VCardConstants.PROPERTY_X_ICQ, 6);
        sImMap.put(VCardConstants.PROPERTY_X_JABBER, 7);
        sImMap.put(VCardConstants.PROPERTY_X_SKYPE_USERNAME, 3);
        sImMap.put(VCardConstants.PROPERTY_X_GOOGLE_TALK, 5);
        sImMap.put(VCardConstants.ImportOnly.PROPERTY_X_GOOGLE_TALK_WITH_SPACE, 5);
    }

    public VCardEntry() {
        this(VCardConfig.VCARD_TYPE_V21_GENERIC, (Account) null);
    }

    private void addEmail(int i, String str, String str2, boolean z) {
        if (this.mEmailList == null) {
            this.mEmailList = new ArrayList();
        }
        this.mEmailList.add(new EmailData(str, i, str2, z));
    }

    private void addIm(int i, String str, String str2, int i2, boolean z) {
        if (this.mImList == null) {
            this.mImList = new ArrayList();
        }
        this.mImList.add(new ImData(i, str, str2, i2, z));
    }

    private void addNewOrganization(String str, String str2, String str3, String str4, int i, boolean z) {
        if (this.mOrganizationList == null) {
            this.mOrganizationList = new ArrayList();
        }
        this.mOrganizationList.add(new OrganizationData(str, str2, str3, str4, i, z));
    }

    private void addNickName(String str) {
        if (this.mNicknameList == null) {
            this.mNicknameList = new ArrayList();
        }
        this.mNicknameList.add(new NicknameData(str));
    }

    private void addNote(String str) {
        if (this.mNoteList == null) {
            this.mNoteList = new ArrayList(1);
        }
        this.mNoteList.add(new NoteData(str));
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

    private void addPhotoBytes(String str, byte[] bArr, boolean z) {
        if (this.mPhotoList == null) {
            this.mPhotoList = new ArrayList(1);
        }
        this.mPhotoList.add(new PhotoData(str, bArr, z));
    }

    private void addPostal(int i, List list, String str, boolean z) {
        if (this.mPostalList == null) {
            this.mPostalList = new ArrayList(0);
        }
        this.mPostalList.add(PostalData.constructPostalData(list, i, str, z, this.mVCardType));
    }

    private void addSip(String str, int i, String str2, boolean z) {
        if (this.mSipList == null) {
            this.mSipList = new ArrayList();
        }
        this.mSipList.add(new SipData(str, i, str2, z));
    }

    public static VCardEntry buildFromResolver(ContentResolver contentResolver) {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        return null;
    }

    public static VCardEntry buildFromResolver(ContentResolver contentResolver, Uri uri) {
        return null;
    }

    private String buildSinglePhoneticNameFromSortAsParam(Map map) {
        Collection collection = (Collection) map.get(VCardConstants.PARAM_SORT_AS);
        if (collection == null || collection.size() == 0) {
            return null;
        }
        if (collection.size() > 1) {
            StringBuilder Pa = C0632a.m1011Pa("Incorrect multiple SORT_AS parameters detected: ");
            Pa.append(Arrays.toString(collection.toArray()));
            Log.w(LOG_TAG, Pa.toString());
        }
        List<String> constructListFromValue = VCardUtils.constructListFromValue((String) collection.iterator().next(), this.mVCardType);
        StringBuilder sb = new StringBuilder();
        for (String append : constructListFromValue) {
            sb.append(append);
        }
        return sb.toString();
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
            List list = this.mEmailList;
            if (list == null || list.size() <= 0) {
                List list2 = this.mPhoneList;
                if (list2 == null || list2.size() <= 0) {
                    List list3 = this.mPostalList;
                    if (list3 == null || list3.size() <= 0) {
                        List list4 = this.mOrganizationList;
                        str = (list4 == null || list4.size() <= 0) ? null : ((OrganizationData) this.mOrganizationList.get(0)).getFormattedString();
                    } else {
                        str = ((PostalData) this.mPostalList.get(0)).getFormattedAddress(this.mVCardType);
                    }
                } else {
                    str = ((PhoneData) this.mPhoneList.get(0)).mNumber;
                }
            } else {
                str = ((EmailData) this.mEmailList.get(0)).mAddress;
            }
        }
        return str == null ? "" : str;
    }

    private void handleAndroidCustomProperty(List list) {
        if (this.mAndroidCustomDataList == null) {
            this.mAndroidCustomDataList = new ArrayList();
        }
        this.mAndroidCustomDataList.add(AndroidCustomData.constructAndroidCustomData(list));
    }

    private void handleNProperty(List list, Map map) {
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
                            String unused = this.mNameData.mSuffix = (String) list.get(4);
                        }
                        String unused2 = this.mNameData.mFamily = (String) list.get(0);
                    }
                    String unused3 = this.mNameData.mPrefix = (String) list.get(3);
                }
                String unused4 = this.mNameData.mMiddle = (String) list.get(2);
            }
            String unused5 = this.mNameData.mGiven = (String) list.get(1);
            String unused6 = this.mNameData.mFamily = (String) list.get(0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleOrgValue(int r8, java.util.List r9, java.util.Map r10, boolean r11) {
        /*
            r7 = this;
            java.lang.String r4 = r7.buildSinglePhoneticNameFromSortAsParam(r10)
            if (r9 != 0) goto L_0x0008
            java.util.List r9 = sEmptyList
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
            r3 = r2
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
            java.util.List r9 = r7.mOrganizationList
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

    private void handlePhoneticNameFromSound(List list) {
        int size;
        boolean z;
        if (TextUtils.isEmpty(this.mNameData.mPhoneticFamily) && TextUtils.isEmpty(this.mNameData.mPhoneticMiddle) && TextUtils.isEmpty(this.mNameData.mPhoneticGiven) && list != null && (size = list.size()) >= 1) {
            if (size > 3) {
                size = 3;
            }
            if (((String) list.get(0)).length() > 0) {
                int i = 1;
                while (true) {
                    if (i >= size) {
                        z = true;
                        break;
                    } else if (((String) list.get(i)).length() > 0) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    String[] split = ((String) list.get(0)).split(" ");
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
                        String unused6 = this.mNameData.mPhoneticGiven = (String) list.get(0);
                        return;
                    }
                }
            }
            if (size != 2) {
                if (size == 3) {
                    String unused7 = this.mNameData.mPhoneticMiddle = (String) list.get(2);
                }
                String unused8 = this.mNameData.mPhoneticFamily = (String) list.get(0);
            }
            String unused9 = this.mNameData.mPhoneticGiven = (String) list.get(1);
            String unused10 = this.mNameData.mPhoneticFamily = (String) list.get(0);
        }
    }

    private void handleSipCase(String str, Collection collection) {
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
                Iterator it = collection.iterator();
                boolean z2 = false;
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    String upperCase = str3.toUpperCase();
                    if (upperCase.equals(VCardConstants.PARAM_TYPE_PREF)) {
                        z2 = true;
                    } else if (upperCase.equals(VCardConstants.PARAM_TYPE_HOME)) {
                        i = 1;
                    } else if (upperCase.equals(VCardConstants.PARAM_TYPE_WORK)) {
                        i = 2;
                    } else if (i < 0) {
                        str2 = upperCase.startsWith("X-") ? str3.substring(2) : str3;
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

    private void handleTitleValue(String str) {
        List<OrganizationData> list = this.mOrganizationList;
        if (list == null) {
            addNewOrganization((String) null, (String) null, str, (String) null, 1, false);
            return;
        }
        for (OrganizationData organizationData : list) {
            if (organizationData.mTitle == null) {
                String unused = organizationData.mTitle = str;
                return;
            }
        }
        addNewOrganization((String) null, (String) null, str, (String) null, 1, false);
    }

    private void iterateOneList(List list, EntryElementIterator entryElementIterator) {
        if (list != null && list.size() > 0) {
            entryElementIterator.onElementGroupStarted(((EntryElement) list.get(0)).getEntryLabel());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                entryElementIterator.onElement((EntryElement) it.next());
            }
            entryElementIterator.onElementGroupEnded();
        }
    }

    private String listToString(List list) {
        int size = list.size();
        if (size <= 1) {
            return size == 1 ? (String) list.get(0) : "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            if (size - 1 > 0) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    private void tryHandleSortAsName(Map map) {
        Collection collection;
        if ((!VCardConfig.isVersion30(this.mVCardType) || (TextUtils.isEmpty(this.mNameData.mPhoneticFamily) && TextUtils.isEmpty(this.mNameData.mPhoneticMiddle) && TextUtils.isEmpty(this.mNameData.mPhoneticGiven))) && (collection = (Collection) map.get(VCardConstants.PARAM_SORT_AS)) != null && collection.size() != 0) {
            if (collection.size() > 1) {
                StringBuilder Pa = C0632a.m1011Pa("Incorrect multiple SORT_AS parameters detected: ");
                Pa.append(Arrays.toString(collection.toArray()));
                Log.w(LOG_TAG, Pa.toString());
            }
            List constructListFromValue = VCardUtils.constructListFromValue((String) collection.iterator().next(), this.mVCardType);
            int size = constructListFromValue.size();
            if (size > 3) {
                size = 3;
            }
            if (size != 2) {
                if (size == 3) {
                    String unused = this.mNameData.mPhoneticMiddle = (String) constructListFromValue.get(2);
                }
                String unused2 = this.mNameData.mPhoneticFamily = (String) constructListFromValue.get(0);
            }
            String unused3 = this.mNameData.mPhoneticGiven = (String) constructListFromValue.get(1);
            String unused4 = this.mNameData.mPhoneticFamily = (String) constructListFromValue.get(0);
        }
    }

    public void addChild(VCardEntry vCardEntry) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList();
        }
        this.mChildren.add(vCardEntry);
    }

    /* JADX WARNING: Removed duplicated region for block: B:143:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x024c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addProperty(com.android.vcard.VCardProperty r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.String r1 = r18.getName()
            java.util.Map r2 = r18.getParameterMap()
            java.util.List r3 = r18.getValueList()
            byte[] r4 = r18.getByteValue()
            if (r3 == 0) goto L_0x001a
            int r5 = r3.size()
            if (r5 != 0) goto L_0x001d
        L_0x001a:
            if (r4 != 0) goto L_0x001d
            return
        L_0x001d:
            r5 = 0
            if (r3 == 0) goto L_0x0029
            java.lang.String r6 = r0.listToString(r3)
            java.lang.String r6 = r6.trim()
            goto L_0x002a
        L_0x0029:
            r6 = r5
        L_0x002a:
            java.lang.String r7 = "VERSION"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x0034
            goto L_0x0412
        L_0x0034:
            java.lang.String r7 = "FN"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x0043
            com.android.vcard.VCardEntry$NameData r0 = r0.mNameData
            java.lang.String unused = r0.mFormatted = r6
            goto L_0x0412
        L_0x0043:
            java.lang.String r7 = "NAME"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x005e
            com.android.vcard.VCardEntry$NameData r1 = r0.mNameData
            java.lang.String r1 = r1.mFormatted
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0412
            com.android.vcard.VCardEntry$NameData r0 = r0.mNameData
            java.lang.String unused = r0.mFormatted = r6
            goto L_0x0412
        L_0x005e:
            java.lang.String r7 = "N"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x006b
            r0.handleNProperty(r3, r2)
            goto L_0x0412
        L_0x006b:
            java.lang.String r7 = "SORT-STRING"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x007a
            com.android.vcard.VCardEntry$NameData r0 = r0.mNameData
            java.lang.String unused = r0.mSortString = r6
            goto L_0x0412
        L_0x007a:
            java.lang.String r7 = "NICKNAME"
            boolean r7 = r1.equals(r7)
            if (r7 != 0) goto L_0x040f
            java.lang.String r7 = "X-NICKNAME"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x008c
            goto L_0x040f
        L_0x008c:
            java.lang.String r7 = "SOUND"
            boolean r7 = r1.equals(r7)
            java.lang.String r8 = "TYPE"
            if (r7 == 0) goto L_0x00b1
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x0412
            java.lang.String r2 = "X-IRMC-N"
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x0412
            int r1 = r0.mVCardType
            java.util.List r1 = com.android.vcard.VCardUtils.constructListFromValue(r6, r1)
            r0.handlePhoneticNameFromSound(r1)
            goto L_0x0412
        L_0x00b1:
            java.lang.String r7 = "ADR"
            boolean r7 = r1.equals(r7)
            java.lang.String r9 = "X-"
            java.lang.String r10 = "WORK"
            java.lang.String r11 = "HOME"
            r13 = 2
            java.lang.String r14 = "PREF"
            r12 = 1
            if (r7 == 0) goto L_0x0156
            java.util.Iterator r1 = r3.iterator()
        L_0x00c7:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00db
            java.lang.Object r4 = r1.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x00c7
            r1 = 0
            goto L_0x00dc
        L_0x00db:
            r1 = r12
        L_0x00dc:
            if (r1 == 0) goto L_0x00df
            return
        L_0x00df:
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x014b
            java.util.Iterator r1 = r1.iterator()
            r4 = r5
            r2 = -1
            r6 = 0
        L_0x00ee:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x014e
            java.lang.Object r7 = r1.next()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = r7.toUpperCase()
            boolean r16 = r8.equals(r14)
            if (r16 == 0) goto L_0x0106
            r6 = r12
            goto L_0x00ee
        L_0x0106:
            boolean r16 = r8.equals(r11)
            if (r16 == 0) goto L_0x010f
            r4 = r5
            r2 = r12
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
            if (r2 >= 0) goto L_0x00ee
            boolean r2 = r8.startsWith(r9)
            if (r2 == 0) goto L_0x0145
            java.lang.String r2 = r7.substring(r13)
            r4 = r2
            goto L_0x0146
        L_0x0145:
            r4 = r7
        L_0x0146:
            r2 = 0
            goto L_0x00ee
        L_0x0148:
            r4 = r5
            r2 = r13
            goto L_0x00ee
        L_0x014b:
            r4 = r5
            r2 = -1
            r6 = 0
        L_0x014e:
            if (r2 >= 0) goto L_0x0151
            r2 = r12
        L_0x0151:
            r0.addPostal(r2, r3, r4, r6)
            goto L_0x0412
        L_0x0156:
            java.lang.String r7 = "EMAIL"
            boolean r7 = r1.equals(r7)
            r15 = 4
            if (r7 == 0) goto L_0x01ba
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x01b0
            java.util.Iterator r1 = r1.iterator()
            r2 = -1
            r3 = 0
        L_0x016d:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = r1.next()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r7 = r4.toUpperCase()
            boolean r8 = r7.equals(r14)
            if (r8 == 0) goto L_0x0185
            r3 = r12
            goto L_0x016d
        L_0x0185:
            boolean r8 = r7.equals(r11)
            if (r8 == 0) goto L_0x018d
            r2 = r12
            goto L_0x016d
        L_0x018d:
            boolean r8 = r7.equals(r10)
            if (r8 == 0) goto L_0x0195
            r2 = r13
            goto L_0x016d
        L_0x0195:
            java.lang.String r8 = "CELL"
            boolean r8 = r7.equals(r8)
            if (r8 == 0) goto L_0x019f
            r2 = r15
            goto L_0x016d
        L_0x019f:
            if (r2 >= 0) goto L_0x016d
            boolean r2 = r7.startsWith(r9)
            if (r2 == 0) goto L_0x01ad
            java.lang.String r2 = r4.substring(r13)
            r5 = r2
            goto L_0x01ae
        L_0x01ad:
            r5 = r4
        L_0x01ae:
            r2 = 0
            goto L_0x016d
        L_0x01b0:
            r2 = -1
            r3 = 0
        L_0x01b2:
            if (r2 >= 0) goto L_0x01b5
            r2 = 3
        L_0x01b5:
            r0.addEmail(r2, r6, r5, r3)
            goto L_0x0412
        L_0x01ba:
            java.lang.String r7 = "ORG"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x01e9
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x01e3
            java.util.Iterator r1 = r1.iterator()
            r15 = 0
        L_0x01cf:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x01e4
            java.lang.Object r4 = r1.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = r4.equals(r14)
            if (r4 == 0) goto L_0x01cf
            r15 = r12
            goto L_0x01cf
        L_0x01e3:
            r15 = 0
        L_0x01e4:
            r0.handleOrgValue(r12, r3, r2, r15)
            goto L_0x0412
        L_0x01e9:
            java.lang.String r3 = "TITLE"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x01f6
            r0.handleTitleValue(r6)
            goto L_0x0412
        L_0x01f6:
            java.lang.String r3 = "ROLE"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0200
            goto L_0x0412
        L_0x0200:
            java.lang.String r3 = "PHOTO"
            boolean r3 = r1.equals(r3)
            java.lang.String r7 = "URL"
            if (r3 != 0) goto L_0x03d4
            java.lang.String r3 = "LOGO"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0214
            goto L_0x03d4
        L_0x0214:
            java.lang.String r3 = "TEL"
            boolean r3 = r1.equals(r3)
            java.lang.String r4 = "sip:"
            if (r3 == 0) goto L_0x027c
            int r1 = r0.mVCardType
            boolean r1 = com.android.vcard.VCardConfig.isVersion40(r1)
            if (r1 == 0) goto L_0x023d
            boolean r1 = r6.startsWith(r4)
            if (r1 == 0) goto L_0x022f
            r3 = r5
            r1 = r12
            goto L_0x023f
        L_0x022f:
            java.lang.String r1 = "tel:"
            boolean r1 = r6.startsWith(r1)
            if (r1 == 0) goto L_0x023d
            java.lang.String r1 = r6.substring(r15)
            r3 = r1
            goto L_0x023e
        L_0x023d:
            r3 = r6
        L_0x023e:
            r1 = 0
        L_0x023f:
            if (r1 == 0) goto L_0x024c
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            r0.handleSipCase(r6, r1)
            goto L_0x0412
        L_0x024c:
            int r1 = r6.length()
            if (r1 != 0) goto L_0x0253
            return
        L_0x0253:
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.Object r2 = com.android.vcard.VCardUtils.getPhoneTypeFromStrings(r1, r3)
            boolean r4 = r2 instanceof java.lang.Integer
            if (r4 == 0) goto L_0x0268
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r15 = r2.intValue()
            goto L_0x026d
        L_0x0268:
            java.lang.String r5 = r2.toString()
            r15 = 0
        L_0x026d:
            if (r1 == 0) goto L_0x0276
            boolean r1 = r1.contains(r14)
            if (r1 == 0) goto L_0x0276
            goto L_0x0277
        L_0x0276:
            r12 = 0
        L_0x0277:
            r0.addPhone(r15, r3, r5, r12)
            goto L_0x0412
        L_0x027c:
            java.lang.String r3 = "X-SKYPE-PSTNNUMBER"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x029a
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x0293
            boolean r1 = r1.contains(r14)
            if (r1 == 0) goto L_0x0293
            goto L_0x0294
        L_0x0293:
            r12 = 0
        L_0x0294:
            r1 = 7
            r0.addPhone(r1, r6, r5, r12)
            goto L_0x0412
        L_0x029a:
            java.util.Map r3 = sImMap
            boolean r3 = r3.containsKey(r1)
            if (r3 == 0) goto L_0x02f7
            java.util.Map r3 = sImMap
            java.lang.Object r1 = r3.get(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            java.lang.Object r2 = r2.get(r8)
            java.util.Collection r2 = (java.util.Collection) r2
            if (r2 == 0) goto L_0x02e7
            java.util.Iterator r2 = r2.iterator()
            r3 = -1
            r16 = 0
        L_0x02bd:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x02e4
            java.lang.Object r4 = r2.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r5 = r4.equals(r14)
            if (r5 == 0) goto L_0x02d2
            r16 = r12
            goto L_0x02bd
        L_0x02d2:
            if (r3 >= 0) goto L_0x02bd
            boolean r5 = r4.equalsIgnoreCase(r11)
            if (r5 == 0) goto L_0x02dc
            r3 = r12
            goto L_0x02bd
        L_0x02dc:
            boolean r4 = r4.equalsIgnoreCase(r10)
            if (r4 == 0) goto L_0x02bd
            r3 = r13
            goto L_0x02bd
        L_0x02e4:
            r5 = r16
            goto L_0x02e9
        L_0x02e7:
            r3 = -1
            r5 = 0
        L_0x02e9:
            if (r3 >= 0) goto L_0x02ed
            r4 = r12
            goto L_0x02ee
        L_0x02ed:
            r4 = r3
        L_0x02ee:
            r2 = 0
            r0 = r17
            r3 = r6
            r0.addIm(r1, r2, r3, r4, r5)
            goto L_0x0412
        L_0x02f7:
            java.lang.String r3 = "NOTE"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0304
            r0.addNote(r6)
            goto L_0x0412
        L_0x0304:
            boolean r3 = r1.equals(r7)
            if (r3 == 0) goto L_0x0321
            java.util.List r1 = r0.mWebsiteList
            if (r1 != 0) goto L_0x0315
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r12)
            r0.mWebsiteList = r1
        L_0x0315:
            java.util.List r0 = r0.mWebsiteList
            com.android.vcard.VCardEntry$WebsiteData r1 = new com.android.vcard.VCardEntry$WebsiteData
            r1.<init>(r6)
            r0.add(r1)
            goto L_0x0412
        L_0x0321:
            java.lang.String r3 = "BDAY"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0332
            com.android.vcard.VCardEntry$BirthdayData r1 = new com.android.vcard.VCardEntry$BirthdayData
            r1.<init>(r6)
            r0.mBirthday = r1
            goto L_0x0412
        L_0x0332:
            java.lang.String r3 = "ANNIVERSARY"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0343
            com.android.vcard.VCardEntry$AnniversaryData r1 = new com.android.vcard.VCardEntry$AnniversaryData
            r1.<init>(r6)
            r0.mAnniversary = r1
            goto L_0x0412
        L_0x0343:
            java.lang.String r3 = "X-PHONETIC-FIRST-NAME"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0352
            com.android.vcard.VCardEntry$NameData r0 = r0.mNameData
            java.lang.String unused = r0.mPhoneticGiven = r6
            goto L_0x0412
        L_0x0352:
            java.lang.String r3 = "X-PHONETIC-MIDDLE-NAME"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0361
            com.android.vcard.VCardEntry$NameData r0 = r0.mNameData
            java.lang.String unused = r0.mPhoneticMiddle = r6
            goto L_0x0412
        L_0x0361:
            java.lang.String r3 = "X-PHONETIC-LAST-NAME"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0370
            com.android.vcard.VCardEntry$NameData r0 = r0.mNameData
            java.lang.String unused = r0.mPhoneticFamily = r6
            goto L_0x0412
        L_0x0370:
            java.lang.String r3 = "IMPP"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0389
            boolean r1 = r6.startsWith(r4)
            if (r1 == 0) goto L_0x0412
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            r0.handleSipCase(r6, r1)
            goto L_0x0412
        L_0x0389:
            java.lang.String r3 = "X-SIP"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x03a2
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L_0x0412
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            r0.handleSipCase(r6, r1)
            goto L_0x0412
        L_0x03a2:
            java.lang.String r2 = "X-ANDROID-CUSTOM"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x03b4
            int r1 = r0.mVCardType
            java.util.List r1 = com.android.vcard.VCardUtils.constructListFromValue(r6, r1)
            r0.handleAndroidCustomProperty(r1)
            goto L_0x0412
        L_0x03b4:
            java.lang.String r2 = r1.toUpperCase()
            boolean r2 = r2.startsWith(r9)
            if (r2 == 0) goto L_0x0412
            java.util.List r2 = r0.mUnknownXData
            if (r2 != 0) goto L_0x03c9
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r0.mUnknownXData = r2
        L_0x03c9:
            java.util.List r0 = r0.mUnknownXData
            android.util.Pair r2 = new android.util.Pair
            r2.<init>(r1, r6)
            r0.add(r2)
            goto L_0x0412
        L_0x03d4:
            java.lang.String r1 = "VALUE"
            java.lang.Object r1 = r2.get(r1)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x03e5
            boolean r1 = r1.contains(r7)
            if (r1 == 0) goto L_0x03e5
            goto L_0x0412
        L_0x03e5:
            java.lang.Object r1 = r2.get(r8)
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x040a
            java.util.Iterator r1 = r1.iterator()
            r15 = 0
        L_0x03f2:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x040b
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = r14.equals(r2)
            if (r3 == 0) goto L_0x0406
            r15 = r12
            goto L_0x03f2
        L_0x0406:
            if (r5 != 0) goto L_0x03f2
            r5 = r2
            goto L_0x03f2
        L_0x040a:
            r15 = 0
        L_0x040b:
            r0.addPhotoBytes(r5, r4, r15)
            goto L_0x0412
        L_0x040f:
            r0.addNickName(r6)
        L_0x0412:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardEntry.addProperty(com.android.vcard.VCardProperty):void");
    }

    public void consolidateFields() {
        this.mNameData.displayName = constructDisplayName();
    }

    public ArrayList constructInsertOperations(ContentResolver contentResolver, ArrayList arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList();
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
            newInsert.withValue("starred", 1);
        }
        arrayList.add(newInsert.build());
        arrayList.size();
        iterateAllData(new C1497d(this, arrayList, size));
        arrayList.size();
        return arrayList;
    }

    public final String getBirthday() {
        BirthdayData birthdayData = this.mBirthday;
        if (birthdayData != null) {
            return birthdayData.mBirthday;
        }
        return null;
    }

    public final List getChildlen() {
        return this.mChildren;
    }

    public String getDisplayName() {
        NameData nameData = this.mNameData;
        if (nameData.displayName == null) {
            nameData.displayName = constructDisplayName();
        }
        return this.mNameData.displayName;
    }

    public final List getEmailList() {
        return this.mEmailList;
    }

    public final List getImList() {
        return this.mImList;
    }

    public final NameData getNameData() {
        return this.mNameData;
    }

    public final List getNickNameList() {
        return this.mNicknameList;
    }

    public final List getNotes() {
        return this.mNoteList;
    }

    public final List getOrganizationList() {
        return this.mOrganizationList;
    }

    public final List getPhoneList() {
        return this.mPhoneList;
    }

    public final List getPhotoList() {
        return this.mPhotoList;
    }

    public final List getPostalList() {
        return this.mPostalList;
    }

    public boolean getStarred() {
        return this.mStarred;
    }

    public List getUnknownXData() {
        return this.mUnknownXData;
    }

    public final List getWebsiteList() {
        return this.mWebsiteList;
    }

    public boolean isIgnorable() {
        C1498e eVar = new C1498e(this, (C1496c) null);
        iterateAllData(eVar);
        return eVar.getResult();
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

    public void setStarred(boolean z) {
        this.mStarred = z;
    }

    public String toString() {
        C1499f fVar = new C1499f(this, (C1496c) null);
        iterateAllData(fVar);
        return fVar.toString();
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
}
