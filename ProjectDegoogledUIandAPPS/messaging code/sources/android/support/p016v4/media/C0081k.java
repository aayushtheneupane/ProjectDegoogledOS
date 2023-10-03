package android.support.p016v4.media;

import android.os.Bundle;
import android.support.p016v4.media.session.C0107q;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.media.k */
class C0081k {

    /* renamed from: ke */
    private final List f97ke = new ArrayList();
    private final List mCallbacks = new ArrayList();

    /* renamed from: a */
    public C0084n mo491a(Bundle bundle) {
        for (int i = 0; i < this.f97ke.size(); i++) {
            if (C0107q.areSameOptions((Bundle) this.f97ke.get(i), bundle)) {
                return (C0084n) this.mCallbacks.get(i);
            }
        }
        return null;
    }

    public List getCallbacks() {
        return this.mCallbacks;
    }

    public List getOptionsList() {
        return this.f97ke;
    }
}
