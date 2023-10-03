package com.google.common.collect;

import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

abstract class Cut implements Comparable, Serializable {
    private static final long serialVersionUID = 0;
    final Comparable endpoint;

    final class AboveAll extends Cut {
        /* access modifiers changed from: private */
        public static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0;

        private AboveAll() {
            super((Comparable) null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        /* renamed from: a */
        public int compareTo(Cut cut) {
            return cut == this ? 0 : 1;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8612a(StringBuilder sb) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo8613b(StringBuilder sb) {
            sb.append("+∞)");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public boolean mo8614c(Comparable comparable) {
            return false;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: ll */
        public Comparable mo8617ll() {
            throw new IllegalStateException("range unbounded on this side");
        }

        public String toString() {
            return "+∞";
        }
    }

    final class AboveValue extends Cut {
        private static final long serialVersionUID = 0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AboveValue(Comparable comparable) {
            super(comparable);
            if (comparable != null) {
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8612a(StringBuilder sb) {
            sb.append('(');
            sb.append(this.endpoint);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo8613b(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(']');
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public boolean mo8614c(Comparable comparable) {
            return Range.m4424a(this.endpoint, comparable) < 0;
        }

        public int hashCode() {
            return ~this.endpoint.hashCode();
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("/");
            Pa.append(this.endpoint);
            Pa.append("\\");
            return Pa.toString();
        }
    }

    final class BelowAll extends Cut {
        /* access modifiers changed from: private */
        public static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0;

        private BelowAll() {
            super((Comparable) null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        /* renamed from: a */
        public int compareTo(Cut cut) {
            return cut == this ? 0 : -1;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8612a(StringBuilder sb) {
            sb.append("(-∞");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo8613b(StringBuilder sb) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public boolean mo8614c(Comparable comparable) {
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: ll */
        public Comparable mo8617ll() {
            throw new IllegalStateException("range unbounded on this side");
        }

        public String toString() {
            return "-∞";
        }
    }

    final class BelowValue extends Cut {
        private static final long serialVersionUID = 0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        BelowValue(Comparable comparable) {
            super(comparable);
            if (comparable != null) {
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8612a(StringBuilder sb) {
            sb.append('[');
            sb.append(this.endpoint);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo8613b(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(')');
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public boolean mo8614c(Comparable comparable) {
            return Range.m4424a(this.endpoint, comparable) <= 0;
        }

        public int hashCode() {
            return this.endpoint.hashCode();
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("\\");
            Pa.append(this.endpoint);
            Pa.append("/");
            return Pa.toString();
        }
    }

    Cut(Comparable comparable) {
        this.endpoint = comparable;
    }

    /* renamed from: b */
    static Cut m4094b(Comparable comparable) {
        return new BelowValue(comparable);
    }

    /* renamed from: jl */
    static Cut m4095jl() {
        return AboveAll.INSTANCE;
    }

    /* renamed from: kl */
    static Cut m4096kl() {
        return BelowAll.INSTANCE;
    }

    /* renamed from: a */
    public int compareTo(Cut cut) {
        if (cut == BelowAll.INSTANCE) {
            return 1;
        }
        if (cut == AboveAll.INSTANCE) {
            return -1;
        }
        int a = Range.m4424a(this.endpoint, cut.endpoint);
        if (a != 0) {
            return a;
        }
        boolean z = this instanceof AboveValue;
        if (z == (cut instanceof AboveValue)) {
            return 0;
        }
        if (z) {
            return 1;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo8612a(StringBuilder sb);

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract void mo8613b(StringBuilder sb);

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract boolean mo8614c(Comparable comparable);

    public boolean equals(Object obj) {
        if (!(obj instanceof Cut)) {
            return false;
        }
        try {
            if (compareTo((Cut) obj) == 0) {
                return true;
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ll */
    public Comparable mo8617ll() {
        return this.endpoint;
    }

    /* renamed from: a */
    static Cut m4093a(Comparable comparable) {
        return new AboveValue(comparable);
    }
}
