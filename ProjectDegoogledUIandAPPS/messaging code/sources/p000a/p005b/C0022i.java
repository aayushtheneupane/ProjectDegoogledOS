package p000a.p005b;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: a.b.i */
final class C0022i implements Set {
    final /* synthetic */ C0026m this$0;

    C0022i(C0026m mVar) {
        this.this$0 = mVar;
    }

    public boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        int colGetSize = this.this$0.colGetSize();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.this$0.colPut(entry.getKey(), entry.getValue());
        }
        return colGetSize != this.this$0.colGetSize();
    }

    public void clear() {
        this.this$0.colClear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        int colIndexOfKey = this.this$0.colIndexOfKey(entry.getKey());
        if (colIndexOfKey < 0) {
            return false;
        }
        return C0018e.m26b(this.this$0.colGetEntry(colIndexOfKey, 1), entry.getValue());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean containsAll(java.util.Collection r6) {
        /*
            r5 = this;
            java.util.Iterator r6 = r6.iterator()
        L_0x0004:
            boolean r0 = r6.hasNext()
            r1 = 1
            if (r0 == 0) goto L_0x0036
            java.lang.Object r0 = r6.next()
            boolean r2 = r0 instanceof java.util.Map.Entry
            r3 = 0
            if (r2 != 0) goto L_0x0016
        L_0x0014:
            r0 = r3
            goto L_0x0033
        L_0x0016:
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            a.b.m r2 = r5.this$0
            java.lang.Object r4 = r0.getKey()
            int r2 = r2.colIndexOfKey(r4)
            if (r2 >= 0) goto L_0x0025
            goto L_0x0014
        L_0x0025:
            a.b.m r4 = r5.this$0
            java.lang.Object r1 = r4.colGetEntry(r2, r1)
            java.lang.Object r0 = r0.getValue()
            boolean r0 = p000a.p005b.C0018e.m26b(r1, r0)
        L_0x0033:
            if (r0 != 0) goto L_0x0004
            return r3
        L_0x0036:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p005b.C0022i.containsAll(java.util.Collection):boolean");
    }

    public boolean equals(Object obj) {
        return C0026m.equalsSetHelper(this, obj);
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 0;
        for (int colGetSize = this.this$0.colGetSize() - 1; colGetSize >= 0; colGetSize--) {
            Object colGetEntry = this.this$0.colGetEntry(colGetSize, 0);
            Object colGetEntry2 = this.this$0.colGetEntry(colGetSize, 1);
            if (colGetEntry == null) {
                i = 0;
            } else {
                i = colGetEntry.hashCode();
            }
            if (colGetEntry2 == null) {
                i2 = 0;
            } else {
                i2 = colGetEntry2.hashCode();
            }
            i3 += i ^ i2;
        }
        return i3;
    }

    public boolean isEmpty() {
        return this.this$0.colGetSize() == 0;
    }

    public Iterator iterator() {
        return new C0024k(this.this$0);
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.this$0.colGetSize();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }
}
