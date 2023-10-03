package org.apache.james.mime4j.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public final class MimeUtil {

    private static final class Rfc822DateFormat extends SimpleDateFormat {
        private static final long serialVersionUID = 1;

        public Rfc822DateFormat() {
            super("EEE, d MMM yyyy HH:mm:ss ", Locale.US);
        }

        public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            StringBuffer format = super.format(date, stringBuffer, fieldPosition);
            int i = ((this.calendar.get(16) + this.calendar.get(15)) / 1000) / 60;
            if (i < 0) {
                format.append('-');
                i = -i;
            } else {
                format.append('+');
            }
            format.append(String.format("%02d%02d", new Object[]{Integer.valueOf(i / 60), Integer.valueOf(i % 60)}));
            return format;
        }
    }

    static {
        new Random();
        new ThreadLocal<DateFormat>() {
            /* access modifiers changed from: protected */
            public Object initialValue() {
                return new Rfc822DateFormat();
            }
        };
    }

    public static boolean isBase64Encoding(String str) {
        return "base64".equalsIgnoreCase(str);
    }

    public static boolean isMultipart(String str) {
        return str != null && str.toLowerCase().startsWith("multipart/");
    }

    public static boolean isSameMimeType(String str, String str2) {
        return (str == null || str2 == null || !str.equalsIgnoreCase(str2)) ? false : true;
    }

    public static String unfold(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == 13 || charAt == 10) {
                int length2 = str.length();
                StringBuilder sb = new StringBuilder(length2);
                if (i > 0) {
                    sb.append(str.substring(0, i));
                }
                while (true) {
                    i++;
                    if (i >= length2) {
                        return sb.toString();
                    }
                    char charAt2 = str.charAt(i);
                    if (!(charAt2 == 13 || charAt2 == 10)) {
                        sb.append(charAt2);
                    }
                }
            } else {
                i++;
            }
        }
        return str;
    }
}
