package org.apache.james.mime4j.stream;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class NameValuePair {
    private final String name;
    private final String value;

    public NameValuePair(String str, String str2) {
        if (str != null) {
            this.name = str;
            this.value = str2;
            return;
        }
        throw new IllegalArgumentException("Name may not be null");
    }

    public boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameValuePair)) {
            return false;
        }
        NameValuePair nameValuePair = (NameValuePair) obj;
        if (this.name.equals(nameValuePair.name)) {
            String str = this.value;
            String str2 = nameValuePair.value;
            if (str == null) {
                z = str2 == null;
            } else {
                z = str.equals(str2);
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = 629 + (str != null ? str.hashCode() : 0);
        String str2 = this.value;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return (hashCode * 37) + i;
    }

    public String toString() {
        if (this.value == null) {
            return this.name;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("=");
        sb.append("\"");
        return GeneratedOutlineSupport.outline12(sb, this.value, "\"");
    }
}
