package com.android.voicemail.impl.mail.internet;

import android.util.Base64InputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.james.mime4j.codec.QuotedPrintableInputStream;

public class MimeUtility {
    private static final Pattern PATTERN_CR_OR_LF = Pattern.compile("\r|\n");

    public static String getHeaderParameter(String str, String str2) {
        if (str == null) {
            return null;
        }
        String[] split = unfold(str).split(";");
        if (str2 == null) {
            return split[0].trim();
        }
        String lowerCase = str2.toLowerCase();
        for (String str3 : split) {
            if (str3.trim().toLowerCase().startsWith(lowerCase)) {
                String[] split2 = str3.split("=", 2);
                if (split2.length < 2) {
                    return null;
                }
                String trim = split2[1].trim();
                return (!trim.startsWith("\"") || !trim.endsWith("\"")) ? trim : trim.substring(1, trim.length() - 1);
            }
        }
        return null;
    }

    public static InputStream getInputStreamForContentTransferEncoding(InputStream inputStream, String str) {
        if (str != null) {
            String headerParameter = getHeaderParameter(str, (String) null);
            if ("quoted-printable".equalsIgnoreCase(headerParameter)) {
                return new QuotedPrintableInputStream(inputStream);
            }
            if ("base64".equalsIgnoreCase(headerParameter)) {
                return new Base64InputStream(inputStream, 0);
            }
        }
        return inputStream;
    }

    public static boolean mimeTypeMatches(String str, String str2) {
        return Pattern.compile(str2.replaceAll("\\*", "\\.\\*"), 2).matcher(str).matches();
    }

    public static String unfold(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_CR_OR_LF.matcher(str);
        if (!matcher.find()) {
            return str;
        }
        matcher.reset();
        return matcher.replaceAll("");
    }
}
