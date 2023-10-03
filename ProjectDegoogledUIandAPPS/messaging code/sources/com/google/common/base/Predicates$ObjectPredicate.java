package com.google.common.base;

enum Predicates$ObjectPredicate implements C1509F {
    ALWAYS_TRUE {
        public boolean apply(Object obj) {
            return true;
        }

        public String toString() {
            return "Predicates.alwaysTrue()";
        }
    },
    ALWAYS_FALSE {
        public boolean apply(Object obj) {
            return false;
        }

        public String toString() {
            return "Predicates.alwaysFalse()";
        }
    },
    IS_NULL {
        public boolean apply(Object obj) {
            return obj == null;
        }

        public String toString() {
            return "Predicates.isNull()";
        }
    },
    NOT_NULL {
        public boolean apply(Object obj) {
            return obj != null;
        }

        public String toString() {
            return "Predicates.notNull()";
        }
    };

    /* access modifiers changed from: package-private */
    /* renamed from: Jl */
    public C1509F mo8542Jl() {
        return this;
    }
}
