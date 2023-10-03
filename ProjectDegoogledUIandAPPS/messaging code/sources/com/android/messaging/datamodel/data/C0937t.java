package com.android.messaging.datamodel.data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.android.messaging.datamodel.data.t */
class C0937t implements Iterator {

    /* renamed from: EB */
    private int f1335EB = -1;
    final /* synthetic */ C0938u this$0;

    C0937t(C0938u uVar) {
        this.this$0 = uVar;
    }

    public boolean hasNext() {
        return this.f1335EB < this.this$0.f1336FB.size() - 1;
    }

    public Object next() {
        this.f1335EB++;
        if (this.f1335EB < this.this$0.f1336FB.size()) {
            return (ParticipantData) this.this$0.f1336FB.valueAt(this.f1335EB);
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
