package com.android.dialer.shortcuts;

import com.android.dialer.shortcuts.DialerShortcut;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_DialerShortcut extends DialerShortcut {
    private final long contactId;
    private final String displayName;
    private final String lookupKey;
    private final int rank;

    static final class Builder extends DialerShortcut.Builder {
        private Long contactId;
        private String displayName;
        private String lookupKey;
        private Integer rank;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public DialerShortcut build() {
            String str = "";
            if (this.contactId == null) {
                str = GeneratedOutlineSupport.outline8(str, " contactId");
            }
            if (this.lookupKey == null) {
                str = GeneratedOutlineSupport.outline8(str, " lookupKey");
            }
            if (this.displayName == null) {
                str = GeneratedOutlineSupport.outline8(str, " displayName");
            }
            if (this.rank == null) {
                str = GeneratedOutlineSupport.outline8(str, " rank");
            }
            if (str.isEmpty()) {
                return new AutoValue_DialerShortcut(this.contactId.longValue(), this.lookupKey, this.displayName, this.rank.intValue(), (C05571) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public DialerShortcut.Builder setContactId(long j) {
            this.contactId = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public DialerShortcut.Builder setDisplayName(String str) {
            if (str != null) {
                this.displayName = str;
                return this;
            }
            throw new NullPointerException("Null displayName");
        }

        /* access modifiers changed from: package-private */
        public DialerShortcut.Builder setLookupKey(String str) {
            if (str != null) {
                this.lookupKey = str;
                return this;
            }
            throw new NullPointerException("Null lookupKey");
        }

        /* access modifiers changed from: package-private */
        public DialerShortcut.Builder setRank(int i) {
            this.rank = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_DialerShortcut(long j, String str, String str2, int i, C05571 r6) {
        this.contactId = j;
        this.lookupKey = str;
        this.displayName = str2;
        this.rank = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DialerShortcut)) {
            return false;
        }
        DialerShortcut dialerShortcut = (DialerShortcut) obj;
        if (this.contactId == ((AutoValue_DialerShortcut) dialerShortcut).contactId) {
            AutoValue_DialerShortcut autoValue_DialerShortcut = (AutoValue_DialerShortcut) dialerShortcut;
            if (this.lookupKey.equals(autoValue_DialerShortcut.lookupKey) && this.displayName.equals(autoValue_DialerShortcut.displayName) && this.rank == autoValue_DialerShortcut.rank) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public long getContactId() {
        return this.contactId;
    }

    /* access modifiers changed from: package-private */
    public String getDisplayName() {
        return this.displayName;
    }

    /* access modifiers changed from: package-private */
    public String getLookupKey() {
        return this.lookupKey;
    }

    /* access modifiers changed from: package-private */
    public int getRank() {
        return this.rank;
    }

    public int hashCode() {
        long j = this.contactId;
        return this.rank ^ ((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.lookupKey.hashCode()) * 1000003) ^ this.displayName.hashCode()) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DialerShortcut{contactId=");
        outline13.append(this.contactId);
        outline13.append(", lookupKey=");
        outline13.append(this.lookupKey);
        outline13.append(", displayName=");
        outline13.append(this.displayName);
        outline13.append(", rank=");
        outline13.append(this.rank);
        outline13.append("}");
        return outline13.toString();
    }
}
