package com.android.vcard;

import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VCardSourceDetector implements VCardInterpreter {
    private static Set<String> APPLE_SIGNS = new HashSet(Arrays.asList(new String[]{"X-PHONETIC-FIRST-NAME", "X-PHONETIC-MIDDLE-NAME", "X-PHONETIC-LAST-NAME", "X-ABADR", "X-ABUID"}));
    private static Set<String> FOMA_SIGNS = new HashSet(Arrays.asList(new String[]{"X-SD-VERN", "X-SD-FORMAT_VER", "X-SD-CATEGORIES", "X-SD-CLASS", "X-SD-DCREATED", "X-SD-DESCRIPTION"}));
    private static Set<String> JAPANESE_MOBILE_PHONE_SIGNS = new HashSet(Arrays.asList(new String[]{"X-GNO", "X-GN", "X-REDUCTION"}));
    private static String TYPE_FOMA_CHARSET_SIGN = "X-SD-CHAR_CODE";
    private static Set<String> WINDOWS_MOBILE_PHONE_SIGNS = new HashSet(Arrays.asList(new String[]{"X-MICROSOFT-ASST_TEL", "X-MICROSOFT-ASSISTANT", "X-MICROSOFT-OFFICELOC"}));
    private int mParseType = 0;
    private String mSpecifiedCharset;
    private int mVersion = -1;

    public void onEntryEnded() {
    }

    public void onEntryStarted() {
    }

    public void onVCardEnded() {
    }

    public void onVCardStarted() {
    }

    public void onPropertyCreated(VCardProperty vCardProperty) {
        String name = vCardProperty.getName();
        List<String> valueList = vCardProperty.getValueList();
        if (name.equalsIgnoreCase("VERSION") && valueList.size() > 0) {
            String str = valueList.get(0);
            if (str.equals("2.1")) {
                this.mVersion = 0;
            } else if (str.equals("3.0")) {
                this.mVersion = 1;
            } else if (str.equals("4.0")) {
                this.mVersion = 2;
            } else {
                Log.w("vCard", "Invalid version string: " + str);
            }
        } else if (name.equalsIgnoreCase(TYPE_FOMA_CHARSET_SIGN)) {
            this.mParseType = 3;
            if (valueList.size() > 0) {
                this.mSpecifiedCharset = valueList.get(0);
            }
        }
        if (this.mParseType == 0) {
            if (WINDOWS_MOBILE_PHONE_SIGNS.contains(name)) {
                this.mParseType = 4;
            } else if (FOMA_SIGNS.contains(name)) {
                this.mParseType = 3;
            } else if (JAPANESE_MOBILE_PHONE_SIGNS.contains(name)) {
                this.mParseType = 2;
            } else if (APPLE_SIGNS.contains(name)) {
                this.mParseType = 1;
            }
        }
    }

    public int getEstimatedType() {
        int i = this.mParseType;
        if (i == 2) {
            return 402653192;
        }
        if (i == 3) {
            return 939524104;
        }
        int i2 = this.mVersion;
        if (i2 == 0) {
            return -1073741824;
        }
        if (i2 == 1) {
            return -1073741823;
        }
        return i2 == 2 ? -1073741822 : 0;
    }

    public String getEstimatedCharset() {
        if (TextUtils.isEmpty(this.mSpecifiedCharset)) {
            return this.mSpecifiedCharset;
        }
        int i = this.mParseType;
        if (i == 1) {
            return "UTF-8";
        }
        if (i == 2 || i == 3 || i == 4) {
            return "SHIFT_JIS";
        }
        return null;
    }
}
