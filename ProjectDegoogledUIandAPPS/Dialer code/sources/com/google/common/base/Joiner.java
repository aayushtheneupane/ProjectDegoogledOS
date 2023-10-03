package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;

public class Joiner {
    private final String separator;

    private Joiner(String str) {
        if (str != null) {
            this.separator = str;
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: on */
    public static Joiner m66on(String str) {
        return new Joiner(str);
    }

    public final StringBuilder appendTo(StringBuilder sb, Iterator<?> it) {
        if (sb != null) {
            try {
                if (it.hasNext()) {
                    sb.append(toString(it.next()));
                    while (it.hasNext()) {
                        sb.append(this.separator);
                        sb.append(toString(it.next()));
                    }
                }
                return sb;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final String join(Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        appendTo(sb, it);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public CharSequence toString(Object obj) {
        if (obj != null) {
            return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
        }
        throw new NullPointerException();
    }

    /* renamed from: on */
    public static Joiner m65on(char c) {
        return new Joiner(String.valueOf(c));
    }
}
