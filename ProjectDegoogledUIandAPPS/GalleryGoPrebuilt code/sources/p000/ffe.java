package p000;

import android.util.SparseIntArray;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: ffe */
/* compiled from: PG */
public final class ffe implements fcx, ffc {

    /* renamed from: a */
    public final Set f9442a;

    /* renamed from: b */
    public final List f9443b;

    /* renamed from: c */
    public final SparseIntArray f9444c;

    /* renamed from: d */
    public final List f9445d;

    /* renamed from: e */
    public final SparseIntArray f9446e;

    /* renamed from: f */
    private final hso f9447f;

    public ffe(Set set, List list, SparseIntArray sparseIntArray, List list2, SparseIntArray sparseIntArray2) {
        this.f9442a = Collections.unmodifiableSet(set);
        this.f9443b = Collections.unmodifiableList(list);
        this.f9444c = sparseIntArray;
        this.f9445d = Collections.unmodifiableList(list2);
        this.f9446e = sparseIntArray2;
        boolean z = true;
        ife.m12845a(!set.isEmpty(), (Object) "Must have at least one graft");
        ife.m12845a((list.size() == sparseIntArray.size() && list2.size() == sparseIntArray2.size()) ? z : false, (Object) "All children must have a parent specified.");
        this.f9447f = hso.m12033a((Object) fhg.m8902a((ffc) (ffd) set.iterator().next()));
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ife.m12890c(fhg.m8902a((ffc) (ffd) it.next()).equals(this.f9447f.get(0)));
        }
    }

    /* renamed from: a */
    public final List mo5573a() {
        return this.f9447f;
    }

    /* renamed from: b */
    public final fdx mo5574b() {
        return fhg.m8902a((ffc) this);
    }
}
