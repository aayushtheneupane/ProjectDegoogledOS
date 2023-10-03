package com.android.contacts.quickcontact;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebAddress {
    static Pattern sAddressPattern = Pattern.compile("(?:(http|https|file)\\:\\/\\/)?(?:([-A-Za-z0-9$_.+!*'(),;?&=]+(?:\\:[-A-Za-z0-9$_.+!*'(),;?&=]+)?)@)?([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯%_-][a-zA-Z0-9 -퟿豈-﷏ﷰ-￯%_\\.-]*|\\[[0-9a-fA-F:\\.]+\\])?(?:\\:([0-9]*))?(\\/?[^#]*)?.*", 2);
    private String mAuthInfo;
    private String mHost;
    private String mPath;
    private int mPort;
    private String mScheme;

    public WebAddress(String str) throws ParseException {
        if (str != null) {
            this.mScheme = "";
            this.mHost = "";
            this.mPort = -1;
            this.mPath = "/";
            this.mAuthInfo = "";
            Matcher matcher = sAddressPattern.matcher(str);
            if (matcher.matches()) {
                String group = matcher.group(1);
                if (group != null) {
                    this.mScheme = group.toLowerCase(Locale.ROOT);
                }
                String group2 = matcher.group(2);
                if (group2 != null) {
                    this.mAuthInfo = group2;
                }
                String group3 = matcher.group(3);
                if (group3 != null) {
                    this.mHost = group3;
                }
                String group4 = matcher.group(4);
                if (group4 != null && group4.length() > 0) {
                    try {
                        this.mPort = Integer.parseInt(group4);
                    } catch (NumberFormatException unused) {
                        throw new ParseException("Bad port");
                    }
                }
                String group5 = matcher.group(5);
                if (group5 != null && group5.length() > 0) {
                    if (group5.charAt(0) == '/') {
                        this.mPath = group5;
                    } else {
                        this.mPath = "/" + group5;
                    }
                }
                if (this.mPort == 443 && this.mScheme.equals("")) {
                    this.mScheme = "https";
                } else if (this.mPort == -1) {
                    if (this.mScheme.equals("https")) {
                        this.mPort = 443;
                    } else {
                        this.mPort = 80;
                    }
                }
                if (this.mScheme.equals("")) {
                    this.mScheme = "http";
                    return;
                }
                return;
            }
            throw new ParseException("Bad address");
        }
        throw new NullPointerException();
    }

    public String toString() {
        String str;
        String str2 = "";
        if ((this.mPort == 443 || !this.mScheme.equals("https")) && (this.mPort == 80 || !this.mScheme.equals("http"))) {
            str = str2;
        } else {
            str = ":" + Integer.toString(this.mPort);
        }
        if (this.mAuthInfo.length() > 0) {
            str2 = this.mAuthInfo + "@";
        }
        return this.mScheme + "://" + str2 + this.mHost + str + this.mPath;
    }

    public class ParseException extends Exception {
        public String response;

        ParseException(String str) {
            this.response = str;
        }
    }
}
