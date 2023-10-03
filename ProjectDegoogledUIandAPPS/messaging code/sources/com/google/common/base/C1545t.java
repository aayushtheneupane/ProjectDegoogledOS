package com.google.common.base;

import java.util.Arrays;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.google.common.base.t */
public abstract class C1545t implements C1509F {
    public static final C1545t ANY = new C1537l("CharMatcher.ANY");

    /* renamed from: CM */
    public static final C1545t f2412CM = new C1529d("CharMatcher.ASCII", 0, 127);

    /* renamed from: DM */
    private static final String f2413DM;

    /* renamed from: EM */
    static final int f2414EM = Integer.numberOfLeadingZeros(31);
    public static final C1545t NONE = new C1538m("CharMatcher.NONE");
    final String description;

    static {
        new C1531f();
        StringBuilder sb = new StringBuilder(31);
        for (int i = 0; i < 31; i++) {
            sb.append((char) ("0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".charAt(i) + 9));
        }
        f2413DM = sb.toString();
        new C1544s("CharMatcher.DIGIT", "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".toCharArray(), f2413DM.toCharArray());
        new C1532g("CharMatcher.JAVA_DIGIT");
        new C1533h("CharMatcher.JAVA_LETTER");
        new C1534i("CharMatcher.JAVA_LETTER_OR_DIGIT");
        new C1535j("CharMatcher.JAVA_UPPER_CASE");
        new C1536k("CharMatcher.JAVA_LOWER_CASE");
        m4017a(0, 31).mo8550a(m4017a(127, 159)).mo8557Ta("CharMatcher.JAVA_ISO_CONTROL");
        new C1544s("CharMatcher.INVISIBLE", "\u0000­؀؜۝܏ ᠎   ⁦⁧⁨⁩⁪　?﻿￹￺".toCharArray(), "  ­؄؜۝܏ ᠎‏ ⁤⁦⁧⁨⁩⁯　﻿￹￻".toCharArray());
        new C1544s("CharMatcher.SINGLE_WIDTH", "\u0000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
        new C1530e("WHITESPACE");
    }

    C1545t(String str) {
        this.description = str;
    }

    /* renamed from: a */
    public static C1545t m4017a(char c, char c2) {
        C1508E.checkArgument(c2 >= c);
        return new C1529d("CharMatcher.inRange('" + m4021g(c) + "', '" + m4021g(c2) + "')", c, c2);
    }

    /* renamed from: b */
    public static C1545t m4018b(char c) {
        StringBuilder Pa = C0632a.m1011Pa("CharMatcher.is('");
        Pa.append(m4021g(c));
        Pa.append("')");
        return new C1539n(Pa.toString(), c);
    }

    /* renamed from: c */
    public static C1545t m4020c(char c) {
        StringBuilder Pa = C0632a.m1011Pa("CharMatcher.isNot('");
        Pa.append(m4021g(c));
        Pa.append("')");
        return new C1526a(Pa.toString(), c);
    }

    /* renamed from: g */
    private static String m4021g(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ta */
    public C1545t mo8557Ta(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public boolean apply(Object obj) {
        return mo8551d(((Character) obj).charValue());
    }

    /* renamed from: d */
    public abstract boolean mo8551d(char c);

    /* renamed from: d */
    public boolean mo8555d(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!mo8551d(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    public boolean mo8556e(CharSequence charSequence) {
        return mo8554c(charSequence) == -1;
    }

    public C1545t negate() {
        return new C1542q(this);
    }

    public String toString() {
        return this.description;
    }

    protected C1545t() {
        this.description = super.toString();
    }

    /* renamed from: b */
    public static C1545t m4019b(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return NONE;
        }
        if (length == 1) {
            return m4018b(charSequence.charAt(0));
        }
        if (length != 2) {
            char[] charArray = charSequence.toString().toCharArray();
            Arrays.sort(charArray);
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char g : charArray) {
                sb.append(m4021g(g));
            }
            sb.append("\")");
            return new C1527b(sb.toString(), charArray);
        }
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        StringBuilder Pa = C0632a.m1011Pa("CharMatcher.anyOf(\"");
        Pa.append(m4021g(charAt));
        Pa.append(m4021g(charAt2));
        Pa.append("\")");
        return new C1528c(Pa.toString(), charAt, charAt2);
    }

    /* renamed from: c */
    public int mo8554c(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (mo8551d(charSequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public C1545t mo8550a(C1545t tVar) {
        if (tVar != null) {
            return new C1543r(this, tVar, "CharMatcher.or(" + this + ", " + tVar + ")");
        }
        throw new NullPointerException();
    }
}
