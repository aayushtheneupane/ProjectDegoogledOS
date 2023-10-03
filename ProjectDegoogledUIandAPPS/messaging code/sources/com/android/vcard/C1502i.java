package com.android.vcard;

import java.util.Set;

/* renamed from: com.android.vcard.i */
class C1502i extends C1501h {
    public C1502i() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qk */
    public Set mo8489Qk() {
        return VCardParser_V40.sKnownPropertyNameSet;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ra */
    public String mo8490Ra(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '\\' || i >= length - 1) {
                sb.append(charAt);
            } else {
                i++;
                char charAt2 = str.charAt(i);
                if (charAt2 == 'n' || charAt2 == 'N') {
                    sb.append("\n");
                } else {
                    sb.append(charAt2);
                }
            }
            i++;
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Sk */
    public String mo8492Sk() {
        return VCardConstants.VERSION_V40;
    }

    /* access modifiers changed from: protected */
    public int getVersion() {
        return 2;
    }

    public C1502i(int i) {
        super(i);
    }
}
