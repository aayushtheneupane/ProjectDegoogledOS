package com.google.common.collect;

import com.google.common.base.C1509F;
import java.util.Iterator;
import java.util.Set;

/* renamed from: com.google.common.collect.ib */
class C1665ib extends C1671kb {

    /* renamed from: XP */
    final /* synthetic */ Set f2530XP;

    /* renamed from: YP */
    final /* synthetic */ C1509F f2531YP;

    /* renamed from: ZP */
    final /* synthetic */ Set f2532ZP;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1665ib(Set set, C1509F f, Set set2) {
        super((C1662hb) null);
        this.f2530XP = set;
        this.f2531YP = f;
        this.f2532ZP = set2;
    }

    public boolean contains(Object obj) {
        return this.f2530XP.contains(obj) && !this.f2532ZP.contains(obj);
    }

    public boolean isEmpty() {
        return this.f2532ZP.containsAll(this.f2530XP);
    }

    public Iterator iterator() {
        return C1652ea.m4568a(this.f2530XP.iterator(), this.f2531YP);
    }

    public int size() {
        return C1652ea.m4580e((Iterator) C1652ea.m4568a(this.f2530XP.iterator(), this.f2531YP));
    }
}
