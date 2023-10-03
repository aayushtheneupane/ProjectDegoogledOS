package com.android.dialer.oem;

import com.android.dialer.oem.CequintCallerIdManager;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_CequintCallerIdManager_CequintCallerIdContact extends CequintCallerIdManager.CequintCallerIdContact {
    private final String geolocation;
    private final String name;
    private final String photoUri;

    static final class Builder extends CequintCallerIdManager.CequintCallerIdContact.Builder {
        private String geolocation;
        private String name;
        private String photoUri;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public CequintCallerIdManager.CequintCallerIdContact build() {
            return new AutoValue_CequintCallerIdManager_CequintCallerIdContact(this.name, this.geolocation, this.photoUri, (C05051) null);
        }

        /* access modifiers changed from: package-private */
        public CequintCallerIdManager.CequintCallerIdContact.Builder setGeolocation(String str) {
            this.geolocation = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public CequintCallerIdManager.CequintCallerIdContact.Builder setName(String str) {
            this.name = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public CequintCallerIdManager.CequintCallerIdContact.Builder setPhotoUri(String str) {
            this.photoUri = str;
            return this;
        }
    }

    /* synthetic */ AutoValue_CequintCallerIdManager_CequintCallerIdContact(String str, String str2, String str3, C05051 r4) {
        this.name = str;
        this.geolocation = str2;
        this.photoUri = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CequintCallerIdManager.CequintCallerIdContact)) {
            return false;
        }
        String str = this.name;
        if (str != null ? str.equals(((AutoValue_CequintCallerIdManager_CequintCallerIdContact) obj).name) : ((AutoValue_CequintCallerIdManager_CequintCallerIdContact) obj).name == null) {
            String str2 = this.geolocation;
            if (str2 != null ? str2.equals(((AutoValue_CequintCallerIdManager_CequintCallerIdContact) obj).geolocation) : ((AutoValue_CequintCallerIdManager_CequintCallerIdContact) obj).geolocation == null) {
                String str3 = this.photoUri;
                if (str3 == null) {
                    if (((AutoValue_CequintCallerIdManager_CequintCallerIdContact) obj).photoUri == null) {
                        return true;
                    }
                } else if (str3.equals(((AutoValue_CequintCallerIdManager_CequintCallerIdContact) obj).photoUri)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String geolocation() {
        return this.geolocation;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.geolocation;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.photoUri;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String name() {
        return this.name;
    }

    public String photoUri() {
        return this.photoUri;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CequintCallerIdContact{name=");
        outline13.append(this.name);
        outline13.append(", geolocation=");
        outline13.append(this.geolocation);
        outline13.append(", photoUri=");
        return GeneratedOutlineSupport.outline12(outline13, this.photoUri, "}");
    }
}
