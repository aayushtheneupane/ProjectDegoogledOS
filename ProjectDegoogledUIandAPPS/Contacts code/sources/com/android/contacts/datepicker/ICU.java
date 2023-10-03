package com.android.contacts.datepicker;

public class ICU {
    public static char[] getDateFormatOrder(String str) {
        char[] cArr = new char[3];
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == 'd' || charAt == 'L' || charAt == 'M' || charAt == 'y') {
                if (charAt == 'd' && !z) {
                    cArr[i2] = 'd';
                    i2++;
                    z = true;
                } else if ((charAt == 'L' || charAt == 'M') && !z2) {
                    cArr[i2] = 'M';
                    i2++;
                    z2 = true;
                } else if (charAt == 'y' && !z3) {
                    cArr[i2] = 'y';
                    i2++;
                    z3 = true;
                }
            } else if (charAt == 'G') {
                continue;
            } else if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                throw new IllegalArgumentException("Bad pattern character '" + charAt + "' in " + str);
            } else if (charAt != '\'') {
                continue;
            } else {
                if (i < str.length() - 1) {
                    int i3 = i + 1;
                    if (str.charAt(i3) == '\'') {
                        i = i3;
                    }
                }
                int indexOf = str.indexOf(39, i + 1);
                if (indexOf != -1) {
                    i = indexOf + 1;
                } else {
                    throw new IllegalArgumentException("Bad quoting in " + str);
                }
            }
            i++;
        }
        return cArr;
    }
}
