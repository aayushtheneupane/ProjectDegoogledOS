package com.google.common.collect;

import com.google.common.base.C1509F;
import java.util.Iterator;

/* renamed from: com.google.common.collect.ba */
class C1643ba extends C1642b {

    /* renamed from: eO */
    final /* synthetic */ Iterator f2512eO;

    /* renamed from: fO */
    final /* synthetic */ C1509F f2513fO;

    C1643ba(Iterator it, C1509F f) {
        this.f2512eO = it;
        this.f2513fO = f;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Gl */
    public Object mo9135Gl() {
        while (this.f2512eO.hasNext()) {
            Object next = this.f2512eO.next();
            if (this.f2513fO.apply(next)) {
                return next;
            }
        }
        mo9136Hl();
        return null;
    }
}
