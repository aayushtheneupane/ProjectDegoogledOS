package com.google.common.base;

import java.util.Iterator;

public final class Splitter {
    /* access modifiers changed from: private */
    public final int limit;
    /* access modifiers changed from: private */
    public final boolean omitEmptyStrings;
    private final Strategy strategy;
    /* access modifiers changed from: private */
    public final CharMatcher trimmer;

    private static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset = 0;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        protected SplittingIterator(Splitter splitter, CharSequence charSequence) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = charSequence;
        }

        /* access modifiers changed from: package-private */
        public abstract int separatorEnd(int i);

        /* access modifiers changed from: package-private */
        public abstract int separatorStart(int i);
    }

    private interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy2) {
        CharMatcher none = CharMatcher.none();
        this.strategy = strategy2;
        this.omitEmptyStrings = false;
        this.trimmer = none;
        this.limit = Integer.MAX_VALUE;
    }

    /* renamed from: on */
    public static Splitter m70on(char c) {
        final CharMatcher is = CharMatcher.m64is(c);
        return new Splitter(new Strategy() {
            public Iterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    /* access modifiers changed from: package-private */
                    public int separatorEnd(int i) {
                        return i + 1;
                    }

                    /* access modifiers changed from: package-private */
                    public int separatorStart(int i) {
                        return CharMatcher.this.indexIn(this.toSplit, i);
                    }
                };
            }
        });
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence charSequence) {
        if (charSequence != null) {
            return new Iterable<String>() {
                public Iterator<String> iterator() {
                    return Splitter.this.strategy.iterator(Splitter.this, charSequence);
                }

                public String toString() {
                    Joiner on = Joiner.m66on(", ");
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    on.appendTo(sb, iterator());
                    sb.append(']');
                    return sb.toString();
                }
            };
        }
        throw new NullPointerException();
    }

    public Splitter trimResults() {
        CharMatcher whitespace = CharMatcher.whitespace();
        if (whitespace != null) {
            return new Splitter(this.strategy, this.omitEmptyStrings, whitespace, this.limit);
        }
        throw new NullPointerException();
    }

    /* renamed from: on */
    public static Splitter m71on(final String str) {
        MoreObjects.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return m70on(str.charAt(0));
        }
        return new Splitter(new Strategy() {
            public Iterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) {
                    public int separatorEnd(int i) {
                        return str.length() + i;
                    }

                    public int separatorStart(int i) {
                        int length = str.length();
                        int length2 = this.toSplit.length() - length;
                        while (i <= length2) {
                            int i2 = 0;
                            while (i2 < length) {
                                if (this.toSplit.charAt(i2 + i) != str.charAt(i2)) {
                                    i++;
                                } else {
                                    i2++;
                                }
                            }
                            return i;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    private Splitter(Strategy strategy2, boolean z, CharMatcher charMatcher, int i) {
        this.strategy = strategy2;
        this.omitEmptyStrings = z;
        this.trimmer = charMatcher;
        this.limit = i;
    }
}
