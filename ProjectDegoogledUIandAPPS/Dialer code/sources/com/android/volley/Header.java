package com.android.volley;

import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class Header {
    private final String mName;
    private final String mValue;

    public Header(String str, String str2) {
        this.mName = str;
        this.mValue = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Header.class != obj.getClass()) {
            return false;
        }
        Header header = (Header) obj;
        if (!TextUtils.equals(this.mName, header.mName) || !TextUtils.equals(this.mValue, header.mValue)) {
            return false;
        }
        return true;
    }

    public final String getName() {
        return this.mName;
    }

    public final String getValue() {
        return this.mValue;
    }

    public int hashCode() {
        return this.mValue.hashCode() + (this.mName.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Header[name=");
        outline13.append(this.mName);
        outline13.append(",value=");
        return GeneratedOutlineSupport.outline12(outline13, this.mValue, "]");
    }
}
