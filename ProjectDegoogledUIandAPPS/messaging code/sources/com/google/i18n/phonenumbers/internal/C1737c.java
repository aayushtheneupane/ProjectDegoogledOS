package com.google.i18n.phonenumbers.internal;

import java.util.regex.Pattern;

/* renamed from: com.google.i18n.phonenumbers.internal.c */
public class C1737c {
    private C1736b cache;

    public C1737c(int i) {
        this.cache = new C1736b(i);
    }

    public Pattern getPatternForRegex(String str) {
        Pattern pattern = (Pattern) this.cache.get(str);
        if (pattern != null) {
            return pattern;
        }
        Pattern compile = Pattern.compile(str);
        this.cache.put(str, compile);
        return compile;
    }
}
