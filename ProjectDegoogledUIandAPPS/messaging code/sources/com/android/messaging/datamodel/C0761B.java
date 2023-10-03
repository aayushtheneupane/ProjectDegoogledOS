package com.android.messaging.datamodel;

import com.android.messaging.datamodel.p038b.C0866f;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.android.messaging.datamodel.B */
public class C0761B {

    /* renamed from: Sx */
    private final HashSet f982Sx = new HashSet();

    /* renamed from: Tx */
    private final Object f983Tx = new Object();

    /* renamed from: Sd */
    public void mo5865Sd() {
        HashSet hashSet;
        synchronized (this.f983Tx) {
            hashSet = (HashSet) this.f982Sx.clone();
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((C0866f) it.next()).mo6142Fh();
        }
    }

    /* renamed from: a */
    public void mo5866a(C0760A a) {
        synchronized (this.f983Tx) {
            this.f982Sx.add(a);
        }
    }
}
