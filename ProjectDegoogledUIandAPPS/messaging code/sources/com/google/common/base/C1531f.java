package com.google.common.base;

/* renamed from: com.google.common.base.f */
class C1531f extends C1545t {
    C1531f() {
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
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

    public String toString() {
        return "CharMatcher.BREAKING_WHITESPACE";
    }
}
