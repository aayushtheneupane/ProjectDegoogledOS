package com.android.p032ex.chips;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* renamed from: com.android.ex.chips.w */
public class C0705w {

    /* renamed from: hv */
    private static final Pattern f829hv = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(1?[ ]*\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])");

    public static boolean isPhoneNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return f829hv.matcher(str).matches();
    }
}
