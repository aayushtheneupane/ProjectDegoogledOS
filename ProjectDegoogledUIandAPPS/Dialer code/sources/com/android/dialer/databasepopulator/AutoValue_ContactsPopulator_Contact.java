package com.android.dialer.databasepopulator;

import com.android.dialer.databasepopulator.ContactsPopulator;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.ByteArrayOutputStream;
import java.util.List;

final class AutoValue_ContactsPopulator_Contact extends ContactsPopulator.Contact {
    private final String accountName;
    private final String accountType;
    private final List<ContactsPopulator.Email> emails;
    private final boolean isStarred;
    private final String name;
    private final List<ContactsPopulator.PhoneNumber> phoneNumbers;
    private final ByteArrayOutputStream photoStream;
    private final int pinned;

    static final class Builder extends ContactsPopulator.Contact.Builder {
        private String accountName;
        private String accountType;
        private List<ContactsPopulator.Email> emails;
        private Boolean isStarred;
        private String name;
        private List<ContactsPopulator.PhoneNumber> phoneNumbers;
        private ByteArrayOutputStream photoStream;
        private Integer pinned;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact build() {
            String str = "";
            if (this.accountType == null) {
                str = GeneratedOutlineSupport.outline8(str, " accountType");
            }
            if (this.accountName == null) {
                str = GeneratedOutlineSupport.outline8(str, " accountName");
            }
            if (this.isStarred == null) {
                str = GeneratedOutlineSupport.outline8(str, " isStarred");
            }
            if (this.pinned == null) {
                str = GeneratedOutlineSupport.outline8(str, " pinned");
            }
            if (this.phoneNumbers == null) {
                str = GeneratedOutlineSupport.outline8(str, " phoneNumbers");
            }
            if (this.emails == null) {
                str = GeneratedOutlineSupport.outline8(str, " emails");
            }
            if (str.isEmpty()) {
                return new AutoValue_ContactsPopulator_Contact(this.accountType, this.accountName, this.name, this.isStarred.booleanValue(), this.pinned.intValue(), this.photoStream, this.phoneNumbers, this.emails, (C04691) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setAccountName(String str) {
            if (str != null) {
                this.accountName = str;
                return this;
            }
            throw new NullPointerException("Null accountName");
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setAccountType(String str) {
            if (str != null) {
                this.accountType = str;
                return this;
            }
            throw new NullPointerException("Null accountType");
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setEmails(List<ContactsPopulator.Email> list) {
            if (list != null) {
                this.emails = list;
                return this;
            }
            throw new NullPointerException("Null emails");
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setIsStarred(boolean z) {
            this.isStarred = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setName(String str) {
            this.name = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setPhoneNumbers(List<ContactsPopulator.PhoneNumber> list) {
            if (list != null) {
                this.phoneNumbers = list;
                return this;
            }
            throw new NullPointerException("Null phoneNumbers");
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setPhotoStream(ByteArrayOutputStream byteArrayOutputStream) {
            this.photoStream = byteArrayOutputStream;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ContactsPopulator.Contact.Builder setPinned(int i) {
            this.pinned = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_ContactsPopulator_Contact(String str, String str2, String str3, boolean z, int i, ByteArrayOutputStream byteArrayOutputStream, List list, List list2, C04691 r9) {
        this.accountType = str;
        this.accountName = str2;
        this.name = str3;
        this.isStarred = z;
        this.pinned = i;
        this.photoStream = byteArrayOutputStream;
        this.phoneNumbers = list;
        this.emails = list2;
    }

    public boolean equals(Object obj) {
        String str;
        ByteArrayOutputStream byteArrayOutputStream;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContactsPopulator.Contact)) {
            return false;
        }
        ContactsPopulator.Contact contact = (ContactsPopulator.Contact) obj;
        if (this.accountType.equals(contact.getAccountType()) && this.accountName.equals(contact.getAccountName()) && ((str = this.name) != null ? str.equals(((AutoValue_ContactsPopulator_Contact) contact).name) : ((AutoValue_ContactsPopulator_Contact) contact).name == null)) {
            AutoValue_ContactsPopulator_Contact autoValue_ContactsPopulator_Contact = (AutoValue_ContactsPopulator_Contact) contact;
            if (this.isStarred == autoValue_ContactsPopulator_Contact.isStarred && this.pinned == contact.getPinned() && ((byteArrayOutputStream = this.photoStream) != null ? byteArrayOutputStream.equals(autoValue_ContactsPopulator_Contact.photoStream) : autoValue_ContactsPopulator_Contact.photoStream == null) && this.phoneNumbers.equals(contact.getPhoneNumbers()) && this.emails.equals(contact.getEmails())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public String getAccountName() {
        return this.accountName;
    }

    /* access modifiers changed from: package-private */
    public String getAccountType() {
        return this.accountType;
    }

    /* access modifiers changed from: package-private */
    public List<ContactsPopulator.Email> getEmails() {
        return this.emails;
    }

    /* access modifiers changed from: package-private */
    public boolean getIsStarred() {
        return this.isStarred;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public List<ContactsPopulator.PhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    /* access modifiers changed from: package-private */
    public ByteArrayOutputStream getPhotoStream() {
        return this.photoStream;
    }

    /* access modifiers changed from: package-private */
    public int getPinned() {
        return this.pinned;
    }

    public int hashCode() {
        int hashCode = (((this.accountType.hashCode() ^ 1000003) * 1000003) ^ this.accountName.hashCode()) * 1000003;
        String str = this.name;
        int i = 0;
        int hashCode2 = (((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ (this.isStarred ? 1231 : 1237)) * 1000003) ^ this.pinned) * 1000003;
        ByteArrayOutputStream byteArrayOutputStream = this.photoStream;
        if (byteArrayOutputStream != null) {
            i = byteArrayOutputStream.hashCode();
        }
        return this.emails.hashCode() ^ ((((hashCode2 ^ i) * 1000003) ^ this.phoneNumbers.hashCode()) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Contact{accountType=");
        outline13.append(this.accountType);
        outline13.append(", accountName=");
        outline13.append(this.accountName);
        outline13.append(", name=");
        outline13.append(this.name);
        outline13.append(", isStarred=");
        outline13.append(this.isStarred);
        outline13.append(", pinned=");
        outline13.append(this.pinned);
        outline13.append(", photoStream=");
        outline13.append(this.photoStream);
        outline13.append(", phoneNumbers=");
        outline13.append(this.phoneNumbers);
        outline13.append(", emails=");
        return GeneratedOutlineSupport.outline11(outline13, this.emails, "}");
    }
}
