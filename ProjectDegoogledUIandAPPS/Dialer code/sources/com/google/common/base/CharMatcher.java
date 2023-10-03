package com.google.common.base;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.function.Predicate;

public abstract class CharMatcher implements Predicate<Character> {

    private static final class Any extends NamedFastMatcher {
        static final Any INSTANCE = new Any();

        private Any() {
            super("CharMatcher.any()");
        }

        public int indexIn(CharSequence charSequence, int i) {
            int length = charSequence.length();
            MoreObjects.checkPositionIndex(i, length);
            if (i == length) {
                return -1;
            }
            return i;
        }

        public boolean matches(char c) {
            return true;
        }

        public CharMatcher negate() {
            return None.INSTANCE;
        }

        /* renamed from: negate  reason: collision with other method in class */
        public Predicate m124negate() {
            return None.INSTANCE;
        }
    }

    private static final class Ascii extends NamedFastMatcher {
        static final Ascii INSTANCE = new Ascii();

        Ascii() {
            super("CharMatcher.ascii()");
        }

        public boolean matches(char c) {
            return c <= 127;
        }
    }

    private static final class BreakingWhitespace extends CharMatcher {
        static final CharMatcher INSTANCE = new BreakingWhitespace();

        private BreakingWhitespace() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            if (!(c == ' ' || c == 133 || c == 5760)) {
                if (c == 8199) {
                    return false;
                }
                if (!(c == 8287 || c == 12288 || c == 8232 || c == 8233)) {
                    switch (c) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                            break;
                        default:
                            return c >= 8192 && c <= 8202;
                    }
                }
            }
            return true;
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    private static final class Digit extends RangesMatcher {
        static final Digit INSTANCE = new Digit();

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Digit() {
            /*
                r6 = this;
                java.lang.String r0 = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０"
                char[] r1 = r0.toCharArray()
                r2 = 31
                char[] r3 = new char[r2]
                r4 = 0
            L_0x000b:
                if (r4 >= r2) goto L_0x0019
                char r5 = r0.charAt(r4)
                int r5 = r5 + 9
                char r5 = (char) r5
                r3[r4] = r5
                int r4 = r4 + 1
                goto L_0x000b
            L_0x0019:
                java.lang.String r0 = "CharMatcher.digit()"
                r6.<init>(r0, r1, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.CharMatcher.Digit.<init>():void");
        }
    }

    static abstract class FastMatcher extends CharMatcher {
        FastMatcher() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }
    }

    private static final class Invisible extends RangesMatcher {
        static final Invisible INSTANCE = new Invisible();

        private Invisible() {
            super("CharMatcher.invisible()", "\u0000­؀؜۝܏ ᠎   ⁦⁧⁨⁩⁪　?﻿￹￺".toCharArray(), "  ­؄؜۝܏ ᠎‏ ⁤⁦⁧⁨⁩⁯　﻿￹￻".toCharArray());
        }
    }

    private static final class JavaDigit extends CharMatcher {
        static final JavaDigit INSTANCE = new JavaDigit();

        private JavaDigit() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return Character.isDigit(c);
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    private static final class JavaIsoControl extends NamedFastMatcher {
        static final JavaIsoControl INSTANCE = new JavaIsoControl();

        private JavaIsoControl() {
            super("CharMatcher.javaIsoControl()");
        }

        public boolean matches(char c) {
            return c <= 31 || (c >= 127 && c <= 159);
        }
    }

    private static final class JavaLetter extends CharMatcher {
        static final JavaLetter INSTANCE = new JavaLetter();

        private JavaLetter() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return Character.isLetter(c);
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    private static final class JavaLetterOrDigit extends CharMatcher {
        static final JavaLetterOrDigit INSTANCE = new JavaLetterOrDigit();

        private JavaLetterOrDigit() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return Character.isLetterOrDigit(c);
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    private static final class JavaLowerCase extends CharMatcher {
        static final JavaLowerCase INSTANCE = new JavaLowerCase();

        private JavaLowerCase() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return Character.isLowerCase(c);
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    private static final class JavaUpperCase extends CharMatcher {
        static final JavaUpperCase INSTANCE = new JavaUpperCase();

        private JavaUpperCase() {
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return Character.isUpperCase(c);
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    static abstract class NamedFastMatcher extends FastMatcher {
        private final String description;

        NamedFastMatcher(String str) {
            if (str != null) {
                this.description = str;
                return;
            }
            throw new NullPointerException();
        }

        public final String toString() {
            return this.description;
        }
    }

    private static class Negated extends CharMatcher {
        final CharMatcher original;

        Negated(CharMatcher charMatcher) {
            if (charMatcher != null) {
                this.original = charMatcher;
                return;
            }
            throw new NullPointerException();
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return !this.original.matches(c);
        }

        public Predicate negate() {
            return this.original;
        }

        public String toString() {
            return GeneratedOutlineSupport.outline11(new StringBuilder(), this.original, ".negate()");
        }
    }

    static class NegatedFastMatcher extends Negated {
        NegatedFastMatcher(CharMatcher charMatcher) {
            super(charMatcher);
        }
    }

    private static final class None extends NamedFastMatcher {
        static final None INSTANCE = new None();

        private None() {
            super("CharMatcher.none()");
        }

        public int indexIn(CharSequence charSequence, int i) {
            MoreObjects.checkPositionIndex(i, charSequence.length());
            return -1;
        }

        public boolean matches(char c) {
            return false;
        }

        public CharMatcher negate() {
            return Any.INSTANCE;
        }

        /* renamed from: negate  reason: collision with other method in class */
        public Predicate m127negate() {
            return CharMatcher.any();
        }
    }

    private static class RangesMatcher extends CharMatcher {
        private final String description;
        private final char[] rangeEnds;
        private final char[] rangeStarts;

        RangesMatcher(String str, char[] cArr, char[] cArr2) {
            this.description = str;
            this.rangeStarts = cArr;
            this.rangeEnds = cArr2;
            MoreObjects.checkArgument(cArr.length == cArr2.length);
            int i = 0;
            while (i < cArr.length) {
                MoreObjects.checkArgument(cArr[i] <= cArr2[i]);
                int i2 = i + 1;
                if (i2 < cArr.length) {
                    MoreObjects.checkArgument(cArr2[i] < cArr[i2]);
                }
                i = i2;
            }
        }

        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return CharMatcher.super.apply((Character) obj);
        }

        public boolean matches(char c) {
            int binarySearch = Arrays.binarySearch(this.rangeStarts, c);
            if (binarySearch >= 0) {
                return true;
            }
            int i = (~binarySearch) - 1;
            if (i < 0 || c > this.rangeEnds[i]) {
                return false;
            }
            return true;
        }

        public Predicate negate() {
            return new Negated(this);
        }

        public String toString() {
            return this.description;
        }
    }

    private static final class SingleWidth extends RangesMatcher {
        static final SingleWidth INSTANCE = new SingleWidth();

        private SingleWidth() {
            super("CharMatcher.singleWidth()", "\u0000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
        }
    }

    static final class Whitespace extends NamedFastMatcher {
        static final Whitespace INSTANCE = new Whitespace();
        static final int SHIFT = Integer.numberOfLeadingZeros(31);

        Whitespace() {
            super("CharMatcher.whitespace()");
        }

        public boolean matches(char c) {
            return " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　".charAt((48906 * c) >>> SHIFT) == c;
        }
    }

    static {
        Whitespace whitespace = Whitespace.INSTANCE;
        CharMatcher charMatcher = BreakingWhitespace.INSTANCE;
        Ascii ascii = Ascii.INSTANCE;
        Digit digit = Digit.INSTANCE;
        JavaDigit javaDigit = JavaDigit.INSTANCE;
        JavaLetter javaLetter = JavaLetter.INSTANCE;
        JavaLetterOrDigit javaLetterOrDigit = JavaLetterOrDigit.INSTANCE;
        JavaUpperCase javaUpperCase = JavaUpperCase.INSTANCE;
        JavaLowerCase javaLowerCase = JavaLowerCase.INSTANCE;
        JavaIsoControl javaIsoControl = JavaIsoControl.INSTANCE;
        Invisible invisible = Invisible.INSTANCE;
        SingleWidth singleWidth = SingleWidth.INSTANCE;
        Any any = Any.INSTANCE;
        None none = None.INSTANCE;
    }

    protected CharMatcher() {
    }

    static /* synthetic */ String access$100(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    /* renamed from: is */
    public static CharMatcher m64is(char c) {
        return new C0838Is(c);
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public int indexIn(CharSequence charSequence, int i) {
        int length = charSequence.length();
        MoreObjects.checkPositionIndex(i, length);
        while (i < length) {
            if (matches(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public abstract boolean matches(char c);

    /* renamed from: com.google.common.base.CharMatcher$Is */
    private static final class C0838Is extends FastMatcher {
        private final char match;

        C0838Is(char c) {
            this.match = c;
        }

        public boolean matches(char c) {
            return c == this.match;
        }

        public CharMatcher negate() {
            return new IsNot(this.match);
        }

        public String toString() {
            return GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("CharMatcher.is('"), CharMatcher.access$100(this.match), "')");
        }

        /* renamed from: negate  reason: collision with other method in class */
        public Predicate m125negate() {
            return new IsNot(this.match);
        }
    }

    private static final class IsNot extends FastMatcher {
        private final char match;

        IsNot(char c) {
            this.match = c;
        }

        public boolean matches(char c) {
            return c != this.match;
        }

        public CharMatcher negate() {
            return new C0838Is(this.match);
        }

        public String toString() {
            return GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("CharMatcher.isNot('"), CharMatcher.access$100(this.match), "')");
        }

        /* renamed from: negate  reason: collision with other method in class */
        public Predicate m126negate() {
            return new C0838Is(this.match);
        }
    }
}
