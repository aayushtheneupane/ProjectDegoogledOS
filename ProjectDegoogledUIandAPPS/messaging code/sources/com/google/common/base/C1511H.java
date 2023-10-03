package com.google.common.base;

import java.util.Collection;

/* renamed from: com.google.common.base.H */
public final class C1511H {
    static {
        C1504A.m3945e(',');
    }

    /* renamed from: b */
    public static C1509F m3970b(C1509F f) {
        return new Predicates$NotPredicate(f);
    }

    /* renamed from: d */
    public static C1509F m3971d(Collection collection) {
        return new Predicates$InPredicate(collection, (C1510G) null);
    }

    /* renamed from: y */
    public static C1509F m3972y(Object obj) {
        if (obj != null) {
            return new Predicates$IsEqualToPredicate(obj, (C1510G) null);
        }
        Predicates$ObjectPredicate predicates$ObjectPredicate = Predicates$ObjectPredicate.IS_NULL;
        predicates$ObjectPredicate.mo8542Jl();
        return predicates$ObjectPredicate;
    }
}
